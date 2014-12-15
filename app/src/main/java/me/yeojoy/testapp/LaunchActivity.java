package me.yeojoy.testapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import my.lib.MyLog;

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
                MyLog.i(TAG, "go to NetworkActivity");
                newIntent = new Intent(this, NetworkActivity.class);
                startActivity(newIntent);
                break;
            case R.id.btn_maps:
                MyLog.i(TAG, "go to MapsActivity");
                newIntent = new Intent(this, MapsActivity.class);
                startActivity(newIntent);
                break;
        }
    }
}
