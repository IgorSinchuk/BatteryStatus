package com.nonexistentware.igor.batterystatus;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.BatteryManager;

import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;





public class MainActivity extends AppCompatActivity {


    private Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

    private ImageView imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7,
            buttonLight, charging, batteryStatus, aboutApp;
    private Chronometer chronometer;

    private TextView textView;

    private TextView batteryVoltage, batteryTemperature, batteryTechnology, batteryStatusTxt,
            batteryHealth, dischargingText, phoneModelTxt, androidVersionTxt, barTxt;

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra("level", 0);
            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
            progressBar.setProgress(level);
            TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText("" + Integer.toString(level) + "%");


        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageView = (ImageView) findViewById(R.id.imageView);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        imageView4 = (ImageView) findViewById(R.id.imageView4);
        imageView6 = (ImageView) findViewById(R.id.facebook);
        charging = (ImageView) findViewById(R.id.charging);
        batteryStatus = (ImageView) findViewById(R.id.batteryStatus);
        aboutApp = (ImageView) findViewById(R.id.aboutApp);


        textView = (TextView) findViewById(R.id.textView);

        batteryVoltage = (TextView) findViewById(R.id.batteryVoltageTxt);
        batteryTemperature = (TextView) findViewById(R.id.batteryTemperatureTxt);
        batteryTechnology = (TextView) findViewById(R.id.batteryTechnologyTxt);
        batteryStatusTxt = (TextView) findViewById(R.id.batteryStatusTxt);
        batteryHealth = (TextView) findViewById(R.id.batteryHealthTxt);


        imageView2.setVisibility(View.INVISIBLE); // battery icon
        imageView3.setVisibility(View.INVISIBLE); // USB icon
        imageView4.setVisibility(View.INVISIBLE); // plug icon
        charging.setVisibility(View.INVISIBLE); // charging icon

        this.registerReceiver(this.myBatteryReceiver,
                new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        registerReceiver(mBroadcastReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));


        batteryStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent batteryStatus = new Intent(MainActivity.this, BatteryStatus.class);
                startActivity(batteryStatus);
            }
        });

        aboutApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aboutApp = new Intent(MainActivity.this, AboutScreen.class);
                startActivity(aboutApp);
            }
        });
    }


    private BroadcastReceiver myBatteryReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context arg0, Intent arg1) {
            // TODO Auto-generated method stub

            if (arg1.getAction().equals(Intent.ACTION_BATTERY_CHANGED)) {
                batteryVoltage.setText(" " //voltage
                        + String.valueOf((float) arg1.getIntExtra("voltage", 0) / 1000) + "V");
                batteryTemperature.setText("  " //Temperature:
                        + String.valueOf((float) arg1.getIntExtra("temperature", 0) / 10) + "C");
                batteryTechnology.setText("  " + arg1.getStringExtra("technology")); //Technology

                int status = arg1.getIntExtra("status", BatteryManager.BATTERY_STATUS_UNKNOWN);
                String strStatus;
                if (status == BatteryManager.BATTERY_STATUS_CHARGING) {
                    strStatus = " Charging";
                } else if (status == BatteryManager.BATTERY_STATUS_DISCHARGING) {
                    strStatus = " Discharging";
                } else if (status == BatteryManager.BATTERY_STATUS_NOT_CHARGING) {
                    strStatus = " Discharging";
                } else if (status == BatteryManager.BATTERY_STATUS_FULL) {
                    strStatus = " Full";
                } else {
                    strStatus = " Unknown";
                }
                batteryStatusTxt.setText("" + strStatus); // status

                int health = arg1.getIntExtra("health", BatteryManager.BATTERY_HEALTH_UNKNOWN);
                String strHealth;
                if (health == BatteryManager.BATTERY_HEALTH_GOOD) {
                    strHealth = " Good";
                } else if (health == BatteryManager.BATTERY_HEALTH_OVERHEAT) {
                    strHealth = " Over Heat";
                } else if (health == BatteryManager.BATTERY_HEALTH_DEAD) {
                    strHealth = " Dead";
                } else if (health == BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE) {
                    strHealth = " Over Voltage";
                } else if (health == BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE) {
                    strHealth = " Unspecified Failure";
                } else {
                    strHealth = " Unknown";
                }
                batteryHealth.setText("" + strHealth); // Health

                if (status == BatteryManager.BATTERY_STATUS_CHARGING) { // charging ac/usb icon on
                    charging.setVisibility(View.VISIBLE);

                } else if (status == BatteryManager.BATTERY_STATUS_NOT_CHARGING) {  // charging ac/usb icon off
                    charging.setVisibility(View.INVISIBLE);
                }

                // usb plugged
                if (status==BatteryManager.BATTERY_PLUGGED_USB) {
                    imageView3.setVisibility(View.VISIBLE);//usb
                    imageView2.setVisibility(View.INVISIBLE);
                } else if (status==BatteryManager.BATTERY_PLUGGED_AC) {
                    imageView3.setVisibility(View.INVISIBLE);
                    imageView4.setVisibility(View.VISIBLE); //plug
                    imageView2.setVisibility(View.INVISIBLE);
                } else if (status==BatteryManager.BATTERY_STATUS_NOT_CHARGING) {
                    imageView3.setVisibility(View.INVISIBLE);
                    imageView4.setVisibility(View.INVISIBLE);
                    imageView2.setVisibility(View.VISIBLE);
                }
                if (status==BatteryManager.BATTERY_PLUGGED_AC) {
                    imageView4.setVisibility(View.VISIBLE);
                } else  if (status==BatteryManager.BATTERY_STATUS_NOT_CHARGING) {
                    imageView2.setVisibility(View.VISIBLE); //battery icon
                }
                //notifications
                if (status==BatteryManager.BATTERY_STATUS_FULL) {
                    fullBatteryNotification();
                }
            }
        }

    };
            public void fullBatteryNotification () {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.elec)
                        .setContentTitle("From BatteryStatus")
                        .setContentText("Battery charged!")
                        .setSound(soundUri);
                //.setSound();

                Intent fullIntent = new Intent(this, MainActivity.class);
                PendingIntent contentIntent = PendingIntent.getActivity(this, 0, fullIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(contentIntent);

                // Add as notification
                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(0, builder.build());


            }

            /**public void usbConnected() {
             NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
             .setSmallIcon(R.drawable.elec)
             .setContentTitle("From BatteryStatus")
             .setContentText("Charging from usb can be slower");


             Intent usbIntent = new Intent(this, MainActivity.class);
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
             .setContentText("AC plugged");

             Intent acIntent = new Intent(this, MainActivity.class);
             PendingIntent contentIntent = PendingIntent.getActivity(this, 0, acIntent,
             PendingIntent.FLAG_UPDATE_CURRENT);
             builder.setContentIntent(contentIntent);

             // Add as notification
             NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
             manager.notify(0, builder.build());


             }

             */





}
