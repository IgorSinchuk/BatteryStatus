package com.nonexistentware.igor.batterystatus;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;



public class  AboutBattery extends AppCompatActivity {


    private TextView batteryLevel, batteryVoltage, batteryTemperature,
            batteryTechnology, batteryStatus, batteryHealth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_battery);

        batteryLevel = (TextView) findViewById(R.id.batterylevel);
        batteryVoltage = (TextView) findViewById(R.id.batteryvoltage);
        batteryTemperature = (TextView) findViewById(R.id.batterytemperature);
        batteryTechnology = (TextView) findViewById(R.id.batterytechology);
        batteryStatus = (TextView) findViewById(R.id.batterystatus);
        batteryHealth = (TextView) findViewById(R.id.batteryhealth);

        this.registerReceiver(this.myBatteryReceiver,
                new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

    }

    private BroadcastReceiver myBatteryReceiver
            = new BroadcastReceiver() {

        @Override
        public void onReceive(Context arg0, Intent arg1) {
            // TODO Auto-generated method stub

            if (arg1.getAction().equals(Intent.ACTION_BATTERY_CHANGED)) {
                batteryLevel.setText("Level: "
                        + String.valueOf(arg1.getIntExtra("level", 0)) + "%");
                batteryVoltage.setText("Voltage: "
                        + String.valueOf((float) arg1.getIntExtra("voltage", 0) / 1000) + "V");
                batteryTemperature.setText("Temperature: "
                        + String.valueOf((float) arg1.getIntExtra("temperature", 0) / 10) + "c");
                batteryTechnology.setText("Technology: " + arg1.getStringExtra("technology"));

                int status = arg1.getIntExtra("status", BatteryManager.BATTERY_STATUS_UNKNOWN);
                String strStatus;
                if (status == BatteryManager.BATTERY_STATUS_CHARGING) {
                    strStatus = "Charging";
                } else if (status == BatteryManager.BATTERY_STATUS_DISCHARGING) {
                    strStatus = "Dis-charging";
                } else if (status == BatteryManager.BATTERY_STATUS_NOT_CHARGING) {
                    strStatus = "Not charging";
                } else if (status == BatteryManager.BATTERY_STATUS_FULL) {
                    strStatus = "Full";
                } else {
                    strStatus = "Unknown";
                }
                batteryStatus.setText("Status: " + strStatus);

                int health = arg1.getIntExtra("health", BatteryManager.BATTERY_HEALTH_UNKNOWN);
                String strHealth;
                if (health == BatteryManager.BATTERY_HEALTH_GOOD) {
                    strHealth = "Good";
                } else if (health == BatteryManager.BATTERY_HEALTH_OVERHEAT) {
                    strHealth = "Over Heat";
                } else if (health == BatteryManager.BATTERY_HEALTH_DEAD) {
                    strHealth = "Dead";
                } else if (health == BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE) {
                    strHealth = "Over Voltage";
                } else if (health == BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE) {
                    strHealth = "Unspecified Failure";
                } else {
                    strHealth = "Unknown";
                }
                batteryHealth.setText("Health: " + strHealth);


                // notifications 
                if (status == BatteryManager.BATTERY_STATUS_FULL) {
                    fullBatteryNotification();
                }
                if (status == BatteryManager.BATTERY_PLUGGED_USB) {
                    usbConnected();
                }

            }
        }

    };

    public void fullBatteryNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.elec)
                .setContentTitle("From BatteryStatus")
                .setContentText("Test");
        //.setSound();

        Intent fullintent = new Intent(this, AboutBattery.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, fullintent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());


    }

    public void usbConnected() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.elec)
                .setContentTitle("From BatteryStatus")
                .setContentText("Usb is slow");

        Intent usbIntent = new Intent(this, AboutBattery.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, usbIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());


    }

    public void acConnected() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.elec)
                .setContentTitle("From BatteryStatus")
                .setContentText("A/C plugged");

        Intent acIntent = new Intent(this, AboutBattery.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, acIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());


    }

}