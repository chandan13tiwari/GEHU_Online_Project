package com.sourcey.materiallogindemo;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

import butterknife.BindView;


public class Homepage extends AppCompatActivity {

    ViewFlipper v_flipper;
    //CardView regcard, videocard, feecard, questcard;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        int images[] = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3};

        v_flipper = findViewById(R.id.v_flipper);

        //for loop
        for(int image: images) {
            flipperImages(image);
        }
    }

    public void flipperImages(int image)
    {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);

        // animation
        v_flipper.setInAnimation(this, android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this, android.R.anim.slide_out_right);

    }

    public void registrationCard(View view)
    {
        Intent intent = new Intent(this, RegForms.class);
        startActivity(intent);

    }


    public void videoCard(View view)
    {
        Toast.makeText(this, "Online Video Lectures", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LectureVideo.class);
        startActivity(intent);

    }


    public void feeCard(View view)
    {

        Toast.makeText(this, "Online Fee Submission", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, PaytmPaymentSample.class);
        startActivity(intent);

    }


    public void questCard(View view)
    {
        Toast.makeText(this, "Adminisrator Login", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, AdminActivity.class);
        startActivity(intent);

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.homepage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_logout) {


            onBackPressed();

//            final ProgressDialog progressDialog = new ProgressDialog(Homepage.this,
//            R.style.AppTheme_Dark_Dialog);
//            progressDialog.setIndeterminate(true);
//            progressDialog.setMessage("Exiting..");
//            progressDialog.show();
//
//            new android.os.Handler().postDelayed(
//                    new Runnable() {
//                        public void run() {
//
//
//                            //progressDialog.dismiss();
//                        }
//                    }, 3000);
//
//            this.finish();
//            return false;

        }


        else if(id == R.id.action_forms){

            Intent intent = new Intent(this, RegForms.class);
            startActivity(intent);
        }



        else if(id == R.id.action_feedback){

            Intent intent = new Intent(this, Feedback.class);
            startActivity(intent);
        }



        else if(id == R.id.action_about){

            AlertDialog alertDialog = new AlertDialog.Builder(
                    Homepage.this).create();

            // Setting Dialog Title
            alertDialog.setTitle("Credits");

            // Setting Dialog Message
            alertDialog.setMessage("Designed by Chandan Tiwari\nB.Tech CSE\nGraphic Era Hill University, Bhimtal\ngolu13tiwaris@gmail.com");

            // Setting Icon to Dialog
            //alertDialog.setIcon();

            // Setting OK Button
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // Write your code here to execute after dialog closed
                    Toast.makeText(getApplicationContext(), "You just saw the Contacts details of the Developer of this Application", Toast.LENGTH_LONG).show();
                }
            });

            // Showing Alert Message
            alertDialog.show();
        }





//        else if(id == R.id.action_notes){
//            Intent intent = new Intent(this, Note.class);
//            startActivity(intent);
//        }


        else if(id == R.id.action_video){
            Intent intent = new Intent(this, LectureVideo.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        AlertDialog alertDialog = new AlertDialog.Builder(
                Homepage.this).create();

        // Setting Dialog Title
        alertDialog.setTitle("Exit!!");

        // Setting Dialog Message
        alertDialog.setMessage("Clicking OK will Exit..");

        // Setting Icon to Dialog
        //alertDialog.setIcon();

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
                final ProgressDialog progressDialog = new ProgressDialog(Homepage.this,
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Exiting..");
                progressDialog.show();

                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {


                                //progressDialog.dismiss();
                            }
                        }, 3000);



                Toast.makeText(getApplicationContext(), "Visit us Again", Toast.LENGTH_LONG).show();
                Homepage.this.finish();

            }
        });




        // Showing Alert Message
        alertDialog.show();



    }
}
