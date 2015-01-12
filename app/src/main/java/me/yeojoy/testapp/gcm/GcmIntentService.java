package me.yeojoy.testapp.gcm;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import me.yeojoy.testapp.PushActivity;
import me.yeojoy.testapp.R;
import me.yeojoy.testapp.dto.NotiData;
import me.yeojoy.testapp.noti.MyNotiManager;

/**
 * Created by yeojoy on 15. 1. 5..
 */
public class GcmIntentService extends IntentService {
    private static final String TAG = GcmIntentService.class.getSimpleName();
            
    private Context mContext;
    
    public GcmIntentService() {
        super("GcmIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "onHandleIntent()");
        
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        // The getMessageType() intent parameter must be the intent you received
        // in your BroadcastReceiver.
        String messageType = gcm.getMessageType(intent);

        mContext = this;
        
        if (!extras.isEmpty()) {  // has effect of unparcelling Bundle
            /*
             * Filter messages based on message type. Since it is likely that GCM
             * will be extended in the future with new message types, just ignore
             * any message types you're not interested in, or that you don't
             * recognize.
             */

            Log.d(TAG, "Received Bundle : " + intent.getExtras().toString());
            
            NotiData data = new NotiData();
            if (extras.getString("id") != null)
                data.setId(Integer.parseInt(extras.getString("id")));
            if (extras.getString("type") != null)
                data.setType(Integer.parseInt(extras.getString("type")));
            if (extras.getString("option") != null)
                data.setMessage(extras.getString("option"));
            if (extras.getString("collapse_key") != null)
                data.setCollapseKey(extras.getString("collapse_key"));
            if (extras.getString("from") != null)
                data.setFrom(extras.getString("from"));
            if (extras.getString("default") != null)
                data.setTitle(extras.getString("default"));
            
            data.setWakelockId(extras.getInt("android.support.content.wakelockid"));

            if (GoogleCloudMessaging.
                    MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
                data.setMessage("Send error: " + extras.toString());
                MyNotiManager.showNotification(mContext, data);
            } else if (GoogleCloudMessaging.
                    MESSAGE_TYPE_DELETED.equals(messageType)) {
                data.setMessage("Deleted messages on server: " +
                        extras.toString());
                MyNotiManager.showNotification(mContext, data);
                // If it's a regular GCM message, do some work.
            } else if (GoogleCloudMessaging.
                    MESSAGE_TYPE_MESSAGE.equals(messageType)) {

                MyNotiManager.showNotification(mContext, data);

            }
            Log.i(TAG, "Data: " + data.toString());
        }
        
        // Release the wake lock provided by the WakefulBroadcastReceiver.
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }
}
