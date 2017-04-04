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


    private TextView batteryvoltage, batterytemperature, batterytechnology, batterystatus,
            batteryhealth, dischargingText, phoneModelTxt, androidVersionTxt, barTxt;

    private Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

    private ImageView r1, r2, r3, r4, r5, r6;

    private ImageView batteryVoltage, batteryTemperature,
            batteryTechnology, batteryStatus, batteryHealth,
            batteryChargingMethod, batteryChargingTime, USBType, ACType, infoText,
            phoneModel, lastFullTime, discharging, androidVersion, mobileIMG, bar;
    private Chronometer chronometer;


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

        batteryVoltage = (ImageView) findViewById(R.id.batteryvoltage);
        batteryTemperature = (ImageView) findViewById(R.id.batterytemperature);
        batteryTechnology = (ImageView) findViewById(R.id.batterytechology);
        batteryStatus = (ImageView) findViewById(R.id.batterystatus);
        batteryHealth = (ImageView) findViewById(R.id.batteryhealth);
        //batteryChargingMethod = (ImageView) findViewById(R.id.batteryChargingMethod);
        //batteryChargingTime = (ImageView) findViewById(R.id.batteryChargingTime);
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        USBType = (ImageView) findViewById(R.id.USBType);
        ACType = (ImageView) findViewById(R.id.ACType);
        //infoText = (ImageView) findViewById(R.id.infoText);
        phoneModel = (ImageView) findViewById(R.id.phoneModel);
        //lastFullTime = (ImageView) findViewById(R.id.lastFullTime);
        discharging = (ImageView) findViewById(R.id.discharging);
        mobileIMG = (ImageView) findViewById(R.id.mobileIMG);
        bar = (ImageView) findViewById(R.id.bar);


        batteryvoltage = (TextView) findViewById(R.id.batteryVoltage);
        batterytemperature = (TextView) findViewById(R.id.batteryTemperature);
        batterytechnology = (TextView) findViewById(R.id.batteryTechnology);
        batterystatus = (TextView) findViewById(R.id.batteryStatus);
        batteryhealth = (TextView) findViewById(R.id.batteryHealth);
        phoneModelTxt = (TextView) findViewById(R.id.phoneModelTxt);
        barTxt = (TextView) findViewById(R.id.barTxt);


        //images
        r1 = (ImageView) findViewById(R.id.imageView);
        r2 = (ImageView) findViewById(R.id.imageView2);
        r3 = (ImageView) findViewById(R.id.imageView3);
        r4 = (ImageView) findViewById(R.id.imageView4);
        r5 = (ImageView) findViewById(R.id.imageView5);
        r6 = (ImageView) findViewById(R.id.imageView6);


        // IN/VISIBLE types

        USBType.setVisibility(View.INVISIBLE);
        ACType.setVisibility(View.INVISIBLE);
        discharging.setVisibility(View.INVISIBLE);


        this.registerReceiver(this.myBatteryReceiver,
                new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        registerReceiver(mBroadcastReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));


        //model
        phoneModelTxt.setText(Build.MODEL);
        //android version

        barTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent about = new  Intent(MainActivity.this, AboutScreen.class);
                startActivity(about);
            }
        });


    }


    private BroadcastReceiver myBatteryReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context arg0, Intent arg1) {
            // TODO Auto-generated method stub

            if (arg1.getAction().equals(Intent.ACTION_BATTERY_CHANGED)) {
                batteryvoltage.setText("" //voltage
                        + String.valueOf((float) arg1.getIntExtra("voltage", 0) / 1000) + "V");
                batterytemperature.setText(" " //Temperature:
                        + String.valueOf((float) arg1.getIntExtra("temperature", 0) / 10) + "C");
                batterytechnology.setText("" + arg1.getStringExtra("technology")); //Technology

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
                batterystatus.setText("" + strStatus); // status

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
                batteryhealth.setText("" + strHealth); // Health


                //battery health indication
                if (status == BatteryManager.BATTERY_HEALTH_DEAD) {

                }


                //charging type

                //usb charging
                if (status == BatteryManager.BATTERY_PLUGGED_USB) {
                    USBType.setVisibility(View.VISIBLE);
                    discharging.setVisibility(View.INVISIBLE);

                } else {
                    if (status == BatteryManager.BATTERY_STATUS_NOT_CHARGING) {
                        discharging.setVisibility(View.VISIBLE);
                        USBType.setVisibility(View.INVISIBLE);
                        batteryhealth.setVisibility(View.VISIBLE);
                    }
                }

                //ac charging
                if (status == BatteryManager.BATTERY_PLUGGED_AC) {
                    ACType.setVisibility(View.VISIBLE);
                    discharging.setVisibility(View.INVISIBLE);
                    USBType.setVisibility(View.INVISIBLE);
                } else {
                    if (status == BatteryManager.BATTERY_STATUS_DISCHARGING) {
                        discharging.setVisibility(View.VISIBLE);
                        ACType.setVisibility(View.INVISIBLE);
                        batteryhealth.setVisibility(View.VISIBLE);
                    }
                }


                //chronometer Time remaining to full
                if (status == BatteryManager.BATTERY_STATUS_CHARGING) {
                    chronometer.start();
                } else {
                    if (status == BatteryManager.BATTERY_STATUS_FULL) {
                        chronometer.stop();
                    } else {
                        if (status == BatteryManager.BATTERY_STATUS_CHARGING) {
                            chronometer.stop();
                        }
                    }
                }


                // notifications
                if (status == BatteryManager.BATTERY_STATUS_FULL) {
                    fullBatteryNotification();
                }

            }
        }

    };


    public void fullBatteryNotification() {
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