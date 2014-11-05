package cn.edu.gdmec.s07131026.servicethreaddome;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static final Runnable Refreshlabel = null;
	Button btn1,btn2;
	static TextView mytv;
	Intent myit=new Intent("cn.edu.gdmec.s07131026.servicethreaddome.ThreadService");
	
	static Handler myHandler = new Handler();
	private static double randomDouble;
	
	
	private static Runnable Refreshlable = new Runnable(){

		@Override
		public void run() {
			// TODO Auto-generated method stub
			mytv.setText(String.valueOf(randomDouble));
			Log.d("runable","settext");
		}
		
	};
	public static void UpdateGUI(double refshDouble){
		randomDouble = refshDouble;
		myHandler.post(Refreshlable);
		Log.d("mainacti","update");
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        mytv = (TextView) findViewById(R.id.textView1);
        btn1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startService(myit);
			}
        	
        });
        btn2.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				stopService(myit);
			}
        	
        });
        
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
