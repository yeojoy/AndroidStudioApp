package me.yeojoy.testapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import me.yeojoy.testapp.dto.NotiData;
import me.yeojoy.testapp.noti.MyNotiManager;

/**
 * Created by yeojoy on 14. 12. 12..
 */
public class NotiActivity extends Activity {

    private static final String TAG = NotiActivity.class.getSimpleName();

    private int notificationType = 1;
    private int notificationId = 100001;
    
    private EditText mTitle, mMessage, mSummary;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noti);
        
        mTitle = (EditText) findViewById(R.id.et_push_title);
        mMessage = (EditText) findViewById(R.id.et_push_msg);
        mSummary = (EditText) findViewById(R.id.et_push_sum);
    }

    // Button OnClick Listener
    public void onClick(View v) {
        if (v.getId() == R.id.btn_noti_send) {
            
            String title = mTitle.getText().toString();
            if (TextUtils.isEmpty(title)) {
                title = mTitle.getHint().toString();
            }
            String msg = mMessage.getText().toString();
            if (TextUtils.isEmpty(msg)) {
                msg = mMessage.getHint().toString();
            }
            String summary = mSummary.getText().toString();
            if (TextUtils.isEmpty(summary)) {
                summary = mSummary.getHint().toString();
            }
            
            NotiData notiData = new NotiData();
            notiData.setId(notificationId);
            notiData.setTitle(title);
            notiData.setMessage(msg);
            notiData.setSummary(summary);
            notiData.setType(notificationType);
            
            MyNotiManager.showNotification(this, notiData);
        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        
        
        
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_type_1:
                if (checked)
                    notificationType = 1;
                    break;
            case R.id.radio_type_2:
                if (checked)
                    notificationType = 2;
                    break;
            case R.id.radio_type_3:
                if (checked)
                    notificationType = 3;
                    break;
            case R.id.radio_type_4:
                if (checked)
                    notificationType = 4;
                    break;
                
            case R.id.radio_id_1:
                if (checked)
                    notificationId = 100001;
                    break;
            case R.id.radio_id_2:
                if (checked)
                    notificationId = 100002;
                    break;
            case R.id.radio_id_3:
                if (checked)
                    notificationId = 100003;
                    break;
            case R.id.radio_id_4:
                if (checked)
                    notificationId = 100004;
                    break;
        }
        
        Log.d(TAG, "Notification Type : " + notificationType 
                + ", id : " + notificationId);
    }
}
