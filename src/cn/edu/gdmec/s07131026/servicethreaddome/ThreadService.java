package cn.edu.gdmec.s07131026.servicethreaddome;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class ThreadService extends Service {
	private Runnable backgroundWork = new Runnable(){
		@Override
		public void run(){
			try{
				while(!Thread.interrupted()){
					//生成随机数
					double randomDouble=Math.random();
					//Log.d("threadserver","run");
					//调用主界面的更新方法向主界面发送生成的随机数
					MainActivity.UpdateGUI(randomDouble);
					//休眠等候1000毫秒
					Thread.sleep(1000);
				}
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	};

	private Thread workThread;
	 

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onCreate(){
		super.onCreate();
		Toast.makeText(this, "Service has cteated!",1000).show();
		workThread = new Thread(null,backgroundWork,"Workthread");
		
	}
	@Override
	public void onDestroy(){
		super.onDestroy();
		Toast.makeText(this,"(3)调用onDestory()",Toast.LENGTH_SHORT).show();
		//修改线程的终止属性
		workThread.interrupt();

	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		Toast.makeText(this,"(2)调用onStart()",Toast.LENGTH_SHORT).show();
		if (!workThread.isAlive()){
			//启动线程
			workThread.start();
			//Toast.makeText(this,"thread start",1000).show();
		}
	}}
