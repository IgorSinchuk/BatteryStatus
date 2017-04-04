package com.nonexistentware.igor.batterystatus;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;



public class AboutScreen extends AppCompatActivity {

    private ImageView facebookIcon, volt, temp, battery, charging,
            health, usb, ac, discharg, mobile, chronom, bar;
    private TextView textVersion, elecTxt, tempTxt, typeTxt,
            chargingxt, healthTxt, usbtxt, acTxt, dischargTxt,
            mobileTxt, chronomTxt, barTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_screen);


        facebookIcon = (ImageView) findViewById(R.id.facebookIcon);
        textVersion = (TextView) findViewById(R.id.textVersion);

        //legend images
        volt = (ImageView) findViewById(R.id.volt);
        temp = (ImageView) findViewById(R.id.temp);
        battery = (ImageView) findViewById(R.id.batery);
        charging = (ImageView) findViewById(R.id.charging);
        health = (ImageView) findViewById(R.id.health);
        usb = (ImageView) findViewById(R.id.usb);
        ac = (ImageView) findViewById(R.id.ac);
        discharg = (ImageView) findViewById(R.id.discharg);
        mobile = (ImageView) findViewById(R.id.mobile);
        chronom = (ImageView) findViewById(R.id.chronom);


        //text
        elecTxt = (TextView) findViewById(R.id.elecTxt);
        tempTxt = (TextView) findViewById(R.id.tempTxt);
        typeTxt = (TextView) findViewById(R.id.typeTxt);
        chargingxt = (TextView) findViewById(R.id.chargingxt);
        healthTxt = (TextView) findViewById(R.id.healthTxt);
        usbtxt = (TextView) findViewById(R.id.usbtxt);
        acTxt = (TextView) findViewById(R.id.acTxt);
        dischargTxt = (TextView) findViewById(R.id.dischargTxt);
        mobileTxt = (TextView) findViewById(R.id.mobileTxt);
        chronomTxt = (TextView) findViewById(R.id.chronomTxt);



    }

    public void showAlert(View view) {
        AlertDialog.Builder faceAlert = new AlertDialog.Builder(this);
        faceAlert.setMessage("Do you want to proceed?")
                .setTitle("To developer Facebook profile")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent openFacebook = new Intent(Intent.ACTION_VIEW);
                        openFacebook.setData(Uri.parse("https://www.facebook.com/profile.php?id=100003140149327"));
                        startActivity(openFacebook);


                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })

                .create();
        faceAlert.show();



    }
}

