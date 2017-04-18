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

import android.os.Build;
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
        imageView6 = (ImageView) findViewById(R.id.imageView6);
        charging = (ImageView) findViewById(R.id.charging);
        batteryStatus = (ImageView) findViewById(R.id.batteryStatus);
        aboutApp = (ImageView) findViewById(R.id.aboutApp);


        textView = (TextView) findViewById(R.id.textView);



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


                int status = arg1.getIntExtra("status", BatteryManager.BATTERY_STATUS_UNKNOWN);
                String strStatus;
                if (status == BatteryManager.BATTERY_STATUS_CHARGING) {
                    strStatus = "Charging";
                } else if (status == BatteryManager.BATTERY_STATUS_DISCHARGING) {
                    strStatus = "Discharging";
                } else if (status == BatteryManager.BATTERY_STATUS_NOT_CHARGING) {
                    strStatus = "Discharging";
                } else if (status == BatteryManager.BATTERY_STATUS_FULL) {
                    strStatus = "Full";
                } else {
                    strStatus = "Unknown";
                }


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


                    // charging icon
                    if (status==BatteryManager.BATTERY_STATUS_CHARGING) {
                        charging.setVisibility(View.VISIBLE);
                    } else if (status==BatteryManager.BATTERY_STATUS_NOT_CHARGING) {
                        charging.setVisibility(View.INVISIBLE);
                    }
                    // plug icon
                    if (status==BatteryManager.BATTERY_PLUGGED_AC) {
                        imageView4.setVisibility(View.VISIBLE);
                    } else if (status==BatteryManager.BATTERY_STATUS_NOT_CHARGING) {
                        imageView4.setVisibility(View.INVISIBLE);
                    }
                    // usb icon
                    if (status==BatteryManager.BATTERY_PLUGGED_USB) {
                        imageView3.setVisibility(View.VISIBLE);
                    } else if (status==BatteryManager.BATTERY_STATUS_NOT_CHARGING) {
                        imageView3.setVisibility(View.INVISIBLE);
                    }
                    //discharging icon
                    if (status==BatteryManager.BATTERY_STATUS_DISCHARGING) {
                        imageView2.setVisibility(View.VISIBLE);
                    }
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
