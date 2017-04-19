package com.nonexistentware.igor.batterystatus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class BatteryStatus extends AppCompatActivity {

    private TextView batteryVoltage, batteryTemperature, batteryTechnology, batteryStatus,
            batteryHealth, dischargingText, phoneModelTxt, androidVersionTxt, barTxt, batteryCapacity;

    private ImageView imageView5, imageView7, imageView8, imageView9, imageView10,
            volts, temp, health, batteryType, charging;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_status);


        batteryVoltage = (TextView) findViewById(R.id.batteryVoltage);
        batteryTemperature = (TextView) findViewById(R.id.batteryTemperature);
        batteryTechnology = (TextView) findViewById(R.id.batteryTechnology);
        batteryStatus = (TextView) findViewById(R.id.batteryStatus);
        batteryHealth = (TextView) findViewById(R.id.batteryHealth);

        imageView5 = (ImageView) findViewById(R.id.imageView5);
        imageView7 = (ImageView) findViewById(R.id.imageView7);
        imageView8 = (ImageView) findViewById(R.id.imageView8);
        imageView9 = (ImageView) findViewById(R.id.imageView9);
        imageView10 = (ImageView) findViewById(R.id.imageView10);

        volts = (ImageView) findViewById(R.id.volts);
        temp = (ImageView) findViewById(R.id.temp);
        health = (ImageView) findViewById(R.id.health);
        batteryType = (ImageView) findViewById(R.id.batteryType);
        charging = (ImageView) findViewById(R.id.charging);

        //model


        this.registerReceiver(this.myBatteryReceiver,
                new IntentFilter(Intent.ACTION_BATTERY_CHANGED));


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
                batteryStatus.setText("" + strStatus); // status

            }

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



        }
    };
}

