package com.nonexistentware.igor.batterystatus;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ProgressBar;
import android.widget.TextView;




public class MainActivity extends AppCompatActivity {


    private TextView batteryLevel, batteryVoltage, batteryTemperature,
            batteryTechnology, batteryStatus, batteryHealth,
            batteryChargingMethod, batteryChargingTime, USBType, ACType, infoText ;
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

        batteryLevel = (TextView) findViewById(R.id.batterylevel);
        batteryVoltage = (TextView) findViewById(R.id.batteryvoltage);
        batteryTemperature = (TextView) findViewById(R.id.batterytemperature);
        batteryTechnology = (TextView) findViewById(R.id.batterytechology);
        batteryStatus = (TextView) findViewById(R.id.batterystatus);
        batteryHealth = (TextView) findViewById(R.id.batteryhealth);
        batteryChargingMethod = (TextView) findViewById(R.id.batteryChargingMethod);
        batteryChargingTime = (TextView) findViewById(R.id.batteryChargingTime);
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        USBType = (TextView) findViewById(R.id.USBType);
        ACType = (TextView) findViewById(R.id.ACType);
        infoText = (TextView) findViewById(R.id.infoText);

        // IN/VISIBLE types
        USBType.setVisibility(View.INVISIBLE);
        ACType.setVisibility(View.INVISIBLE);


        this.registerReceiver(this.myBatteryReceiver,
                new IntentFilter(Intent.ACTION_BATTERY_CHANGED));


        registerReceiver(mBroadcastReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));


        infoText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent about = new Intent(MainActivity.this, AboutScreen.class);
                startActivity(about);
            }
        });

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

                //battery health indication






                //charging tipe
                if (status==BatteryManager.BATTERY_PLUGGED_USB) {
                    USBType.setVisibility(View.VISIBLE);
                }

                if (status==BatteryManager.BATTERY_PLUGGED_AC) {
                    ACType.setVisibility(View.VISIBLE);
                }


                //chronometer Time remaining to full
                if (status==BatteryManager.BATTERY_STATUS_CHARGING) {
                    chronometer.start();
                }


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

        Intent fullintent = new Intent(this, MainActivity.class);
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
                .setContentText("A/C plugged");

        Intent acIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, acIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());


    }



}