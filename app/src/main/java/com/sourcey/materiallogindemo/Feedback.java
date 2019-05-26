package com.sourcey.materiallogindemo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by HP on 11-Sep-18.
 */

public class Feedback extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
    }

    public void subFeed(View view){

        AlertDialog alertDialog = new AlertDialog.Builder(
                Feedback.this).create();

        // Setting Dialog Title
        alertDialog.setTitle("Thank You!!");

        // Setting Dialog Message
        alertDialog.setMessage("We appreciate your Valueable Feedback...:)");

        // Setting Icon to Dialog
        //alertDialog.setIcon();

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
                Toast.makeText(getApplicationContext(), "We just do our best for your Betrer Experience..:)", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        // Showing Alert Message
        alertDialog.show();



    }
}
