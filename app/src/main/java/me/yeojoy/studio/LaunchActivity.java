package me.yeojoy.studio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * Created by yeojoy on 14. 12. 12..
 */
public class LaunchActivity extends Activity {

    private static final String TAG = LaunchActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
    }

    // Button OnClick Listener
    public void onClick(View v) {
        Intent newIntent = null;
        switch (v.getId()) {
            case R.id.btn_network:
                Log.i(TAG, "go to NetworkActivity");
                newIntent = new Intent(this, NetworkActivity.class);
                startActivity(newIntent);
                break;
            case R.id.btn_maps:
                Log.i(TAG, "go to MapsActivity");
                newIntent = new Intent(this, MapsActivity.class);
                startActivity(newIntent);
                break;
            case R.id.btn_graph:
                Log.i(TAG, "go to GraphActivity");
                newIntent = new Intent(this, GraphActivity.class);
                startActivity(newIntent);
                break;
            case R.id.btn_thread:
                Log.i(TAG, "go to HandlerThreadActivity");
                newIntent = new Intent(this, HandlerThreadActivity.class);
                startActivity(newIntent);
                break;
            case R.id.btn_list:
                Log.i(TAG, "go to MyListActivity");
                newIntent = new Intent(this, MyListActivity.class);
                startActivity(newIntent);
                break;
            case R.id.btn_push:
                Log.i(TAG, "go to PushActivity");
                newIntent = new Intent(this, PushActivity.class);
                startActivity(newIntent);
                break;
            case R.id.btn_noti:
                Log.i(TAG, "go to NotiActivity");
                newIntent = new Intent(this, NotiActivity.class);
                startActivity(newIntent);
                break;
        }
    }
}
