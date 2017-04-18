package com.nonexistentware.igor.batterystatus;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;



public class AboutScreen extends AppCompatActivity {


    private TextView textVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_screen);



        textVersion = (TextView) findViewById(R.id.textVersion);




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

