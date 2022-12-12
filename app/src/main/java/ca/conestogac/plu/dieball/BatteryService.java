package ca.conestogac.plu.dieball;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;

public class BatteryService extends Service {
    public BatteryService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        BatteryManager batteryManager = (BatteryManager) getSystemService(BATTERY_SERVICE);
        int batteryLevel = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);

        final NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        final Intent intent = new Intent(getApplicationContext(), MainActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        final PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent,
                PendingIntent.FLAG_IMMUTABLE);

            NotificationChannel channel = new NotificationChannel("ch1",
                    "channel01",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("channel for notifications");
            mNotificationManager.createNotificationChannel(channel);

            if(batteryLevel < 20) {
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "ch1")
                        .setSmallIcon(R.mipmap.ic_launcher) // notification icon
                        .setContentTitle("Battery Level Low!")
                        .setContentText("Battery level is " + batteryLevel + "%")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setAutoCancel(true); // clear notification after click
                mBuilder.setContentIntent(pendingIntent);
                mNotificationManager.notify(0, mBuilder.build());
            }
    }
}