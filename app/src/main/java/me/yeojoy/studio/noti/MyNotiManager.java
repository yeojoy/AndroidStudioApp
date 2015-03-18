package me.yeojoy.studio.noti;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.widget.RemoteViews;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.text.SimpleDateFormat;
import java.util.Date;

import me.yeojoy.studio.BuildConfig;
import me.yeojoy.studio.PushActivity;
import me.yeojoy.studio.R;
import me.yeojoy.studio.dto.NotiData;

/**
 * Created by yeojoy on 15. 1. 6..
 */
public class MyNotiManager {

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
                data.setIconResourceId(R.drawable.ic_launcher);
                notificationWithInBoxStyle(context, data, PushActivity.class);
//                notificationWithColorFont(context, data, PushActivity.class);
                break;
            
            case 3:
                data.setIconResourceId(R.drawable.icon);
                notificationWithBigText(context, data, PushActivity.class);
                break;
            case 4:
                Glide.with(context)
//                        .load(PUSH_BANNER_IMAGE_URL)
                        .load("http://www.pandawill.com/images/v/201011/1289033586.jpg")
                        .asBitmap()
                        .into(new SimpleTarget<Bitmap>(640, 480) {
                            @Override
                            public void onResourceReady(Bitmap bitmap,
                                    GlideAnimation<? super Bitmap> glideAnimation) {
                                data.setIconResourceId(R.drawable.icon_1);
//                                notificationWithBigPicture(context, data,
//                                        bitmap, PushActivity.class);
                                notificationWithRemoteViews(context, data,
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

    private static void notificationWithInBoxStyle(Context context, NotiData data,
                                                Class<?> activityClass) {

        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.icon_1);

        Intent intent = new Intent(context, activityClass);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, 
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(data.getIconResourceId())
                .setTicker("ticker_" + data.getTitle())
                .setAutoCancel(true)
                
                .setContentTitle("5 New mails from yeojoy.kim")
                .setContentText(data.getMessage())
                .setLargeIcon(largeIcon);


        NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
        // Spannable로 스타일을 먹인 것. 안 그러면 일반 평문이 됨. 스타일 변경 된 거 사이에 공백 3칸이 괜찮음.
        Spannable sb = new SpannableString("yeojoy   hi");
        sb.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        sb.setSpan(new StyleSpan(android.graphics.Typeface.ITALIC), 6, sb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        style.addLine(sb);

        sb = new SpannableString("yeojoy   hello");
        sb.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        style.addLine(sb);
        
        
        style.setBigContentTitle("2 New Messages.")
                .setSummaryText("+3 more");
        
        builder.setStyle(style);
        builder.setContentIntent(pendingIntent);
        
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
        style.setSummaryText(data.getSummary());
        style.setBigContentTitle("b_" + data.getTitle());
        style.bigText("b_" + data.getMessage());

        builder.setStyle(style);
        builder.setContentIntent(pendingIntent);
        
        builder.addAction(android.R.drawable.ic_dialog_alert, "Alert", pendingIntent);

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
        style.setSummaryText(data.getSummary());
        style.bigPicture(banner);

        builder.setStyle(style);
        builder.setContentIntent(pendingIntent);

        builder.setDefaults(Notification.DEFAULT_VIBRATE);
        builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(data.getId(), builder.build());
    }

    /**
     * "이.밥.차" 스타일의 Notification 구현.
     * @param context
     * @param data
     * @param banner
     * @param activityClass
     */
    private static void notificationWithRemoteViews(Context context,
            NotiData data, Bitmap banner, Class<?> activityClass) {

        Intent intent = new Intent(context, activityClass);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);


        if (banner == null)
            banner = BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.banner);

        // 기본적인 Notification에 대한 정보는 있어야 함.
        Notification.Builder builder = new Notification.Builder(context)
                .setContentIntent(pendingIntent)
                .setTicker(data.getTitle())
                .setSmallIcon(data.getIconResourceId());

        Notification noti = new Notification.InboxStyle(builder).build();

        String when = new SimpleDateFormat("HH시 mm분").format(new Date());

        noti.contentView = new RemoteViews(BuildConfig.APPLICATION_ID, R.layout.noti_content);
        noti.contentView.setImageViewBitmap(R.id.iv_content, banner);
        noti.contentView.setTextViewText(R.id.tv_title, data.getTitle());
        noti.contentView.setTextViewText(R.id.tv_desc, data.getMessage());
        noti.contentView.setTextViewText(R.id.tv_when, when);

        noti.bigContentView = new RemoteViews(BuildConfig.APPLICATION_ID, R.layout.noti_big_content);
        noti.bigContentView.setImageViewBitmap(R.id.iv_content, banner);
        noti.bigContentView.setTextViewText(R.id.tv_title, data.getTitle());
        noti.bigContentView.setTextViewText(R.id.tv_desc, data.getMessage());
        noti.bigContentView.setTextViewText(R.id.tv_when, when);

        noti.defaults = Notification.DEFAULT_VIBRATE;
        noti.sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(data.getId(), noti);
    }
}
