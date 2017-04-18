package com.nonexistentware.igor.batterystatus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class BatteryStatus extends AppCompatActivity {

    private TextView batteryvoltage, batterytemperature, batterytechnology, batterystatus,
            batteryhealth, dischargingText, phoneModelTxt, androidVersionTxt, barTxt;

    private ImageView imageView5, imageView7, imageView8, imageView9, imageView10,
            volts, temp, health, batteryType, charging;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_status);


        batteryvoltage = (TextView) findViewById(R.id.batteryVoltage);
        batterytemperature = (TextView) findViewById(R.id.batteryTemperature);
        batterytechnology = (TextView) findViewById(R.id.batteryTechnology);
        batterystatus = (TextView) findViewById(R.id.batteryStatus);
        batteryhealth = (TextView) findViewById(R.id.batteryHealth);
        phoneModelTxt = (TextView) findViewById(R.id.phoneModelTxt);

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
        phoneModelTxt.setText(Build.MODEL);

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




                }
            }

        };
    }

