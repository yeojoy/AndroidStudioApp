package me.yeojoy.testapp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

/**
 * Created by yeojoy on 15. 1. 2..
 */
public class HandlerThreadActivity extends Activity {

    private Handler mHandler = null;
    private HandlerThread mHandlerThread = null;
    
    private TextView mTv;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        
        mHandlerThread = new HandlerThread("MyWorker");
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper());
//        mHandler = new Handler();

        mTv = (TextView) findViewById(R.id.tv);
    }
    
    public void onClick(View view) {
        Runnable worker = new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(3000);

//                Toast.makeText(HandlerThreadActivity.this,
//                        "5초 작업 끝", Toast.LENGTH_SHORT).show();
                mTv.setText("3초 작업 끝");
            }
        };
        mHandler.post(worker);
        
    }
}
