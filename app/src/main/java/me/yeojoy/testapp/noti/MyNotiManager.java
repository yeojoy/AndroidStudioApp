package me.yeojoy.testapp.noti;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import me.yeojoy.testapp.BuildConfig;
import me.yeojoy.testapp.PushActivity;
import me.yeojoy.testapp.R;
import me.yeojoy.testapp.dto.NotiData;

/**
 * Created by yeojoy on 15. 1. 6..
 */
public class MyNotiManager {

    private static final String PUSH_TITLE = "안녕하세요 제목 구간입니다.";
    private static final String PUSH_CONTENT = "푸쉬 내용 구간입니다. 푸쉬 내용"
            + " 구간입니다. 푸쉬 내용 구간입니다. 푸쉬 내용 구간입니다. 푸쉬"
            + " 내용 구간입니다. 푸쉬 내용 구간입니다.";
    private static final String PUSH_BANNER_IMAGE_URL = "http://healthconnect.cafe24.com/wp-content/uploads/2012/08/main_silder_02.jpg";

    /**
     * Notification UI 중 타이틀 구간의 폰트의 색깔을 조정한 경우 입니다.
     * RemoteViews 를 통해 custom layout을 바인드 해서 아래와 같이 구현 합니다
     * Android 4.1에서 부터 추가된 기능으로 하위 버젼에서는 RemoteViews를 통한
     * custom이 되지 않습니다, 하위 버젼에서는 일반적인 Notification UI가 노출 됩니다.
     */
    public static void showNotification(final Context context, final NotiData data) {
        switch (data.getType()) {
            case 2:
                data.setTitle("ColorFont Noti");
                data.setIconResourceId(R.drawable.ic_launcher);
                notificationWithColorFont(context, data, PushActivity.class);
                break;
            case 3:
                data.setTitle("Big Text Noti");
                data.setIconResourceId(R.drawable.icon);
                notificationWithBigText(context, data, PushActivity.class);
                break;
            case 4:
                Glide.with(context)
                        .load(PUSH_BANNER_IMAGE_URL)
                        .asBitmap()
                        .into(new SimpleTarget<Bitmap>(640, 480) {
                            @Override
                            public void onResourceReady(Bitmap bitmap,
                                    GlideAnimation<? super Bitmap> glideAnimation) {
                                data.setTitle("Bit Picture Noti");
                                data.setIconResourceId(R.drawable.icon_1);
                                notificationWithBigPicture(context, data,
                                        bitmap, PushActivity.class);
                            }
                        });

                break;
            
            default:
                data.setTitle("Default Text Noti");
                data.setIconResourceId(R.drawable.icon);
                sendDefaultNotification(context, data);
                break;
        }
        
    }

    // Put the message into a notification and post it.
    // This is just one simple example of what you might choose to do with
    // a GCM message.
    private static void sendDefaultNotification(Context context, NotiData data) {
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, PushActivity.class), 0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle(data.getTitle())
                        .setContentText(data.getMessage());

        mBuilder.setContentIntent(contentIntent);
        notificationManager.notify(data.getId(), mBuilder.build());
    }
    
    private static void notificationWithColorFont(Context context, NotiData data,
                                                  Class<?> activityClass) {

        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(), 
                R.drawable.icon_1);

        RemoteViews remoteViews = new RemoteViews(BuildConfig.APPLICATION_ID,
                R.layout.push);

        Intent intent = new Intent(context, activityClass);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setAutoCancel(true)
                .setSmallIcon(data.getIconResourceId())
                .setLargeIcon(largeIcon)
                .setTicker("ticker_" + data.getTitle())
                .setContentTitle(data.getTitle())
                .setContentText(data.getMessage())
                .setContentIntent(pendingIntent)
                .setContent(remoteViews);

        remoteViews.setTextViewText(R.id.push_title, "view_" + data.getTitle());
        remoteViews.setTextViewText(R.id.push_message, "view_" + data.getMessage());

        builder.setDefaults(Notification.DEFAULT_VIBRATE);
        builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        NotificationManager notificationManager = (NotificationManager) 
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(data.getId(), builder.build());
    }

    /**
     * Notification UI 중 BigTextStyle 입니다.
     * 노출된 notification에서 두 손가락을 이용해 아래로 드래그 하면 expand 되면서
     * 긴 텍스트가 노출됩니다.
     * Android 4.1에서 부터 추가된 기능으로 하위 버젼에서는 expand 되지 않습니다,
     * 하위 버젼에서는 일반적인 Notification UI가 노출 됩니다.
     */

    private static void notificationWithBigText(Context context, NotiData data,
                                                Class<?> activityClass) {

        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.icon_1);

        Intent intent = new Intent(context, activityClass);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, 
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(data.getIconResourceId())
                .setLargeIcon(largeIcon)
                .setTicker("ticker_" + data.getTitle())
                .setContentTitle(data.getTitle())
                .setContentText(data.getMessage())
                .setAutoCancel(true);

        NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle();
        style.setSummaryText("Joycity summary Text");
        style.setBigContentTitle("b_" + data.getTitle());
        style.bigText("b_" + data.getMessage());

        builder.setStyle(style);
        builder.setContentIntent(pendingIntent);

        builder.setDefaults(Notification.DEFAULT_VIBRATE);
        builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        NotificationManager notificationManager = (NotificationManager) 
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(data.getId(), builder.build());
    }

    /**
     * Notification UI 중 BigPictureStyle 입니다.
     * 노출된 notification에서 두 손가락을 이용해 아래로 드래그 하면 
     * expand 되면서 banner 이미지가 노출됩니다.
     * Android 4.1에서 부터 추가된 기능으로 하위 버젼에서는 expand 되지 않습니다,
     * 하위 버젼에서는 일반적인 Notification UI가 노출 됩니다.
     */

    private static void notificationWithBigPicture(Context context, NotiData data,
           Bitmap banner, Class<?> activityClass) {

        Intent intent = new Intent(context, activityClass);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, 
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (banner == null)
            banner = BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.banner);
        
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(data.getIconResourceId())
                .setTicker("ticker_" + data.getTitle())
                .setContentTitle(data.getTitle())
                .setContentText(data.getMessage())
                .setAutoCancel(true);

        NotificationCompat.BigPictureStyle style
                = new NotificationCompat.BigPictureStyle();
        style.setBigContentTitle("b_" + data.getTitle());
        style.setSummaryText("summaryText");
        style.bigPicture(banner);

        builder.setStyle(style);
        builder.setContentIntent(pendingIntent);

        builder.setDefaults(Notification.DEFAULT_VIBRATE);
        builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(data.getId(), builder.build());
    }
}
