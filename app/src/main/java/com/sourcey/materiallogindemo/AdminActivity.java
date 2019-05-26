package com.sourcey.materiallogindemo;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by HP on 13-May-19.
 */

public class AdminActivity extends AppCompatActivity{

    EditText username, password;
    Button btn;
    ConstraintLayout first;
    LinearLayout second;
    TextView topText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.editPassword);
        btn = (Button) findViewById(R.id.button);

        first = (ConstraintLayout) findViewById(R.id.first_step);
        second = (LinearLayout) findViewById(R.id.adminscreen);
        topText = findViewById(R.id.topText);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = username.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if("admin".equals(uname) && "admin".equals(pass)){
                    Toast.makeText(AdminActivity.this, "Done!", Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(getApplicationContext(), AdminScreen.class);
//                    startActivity(intent);

                    topText.setText("Admin\nDashboard");
                    first.setVisibility(View.GONE);
                    btn.setVisibility(View.GONE);
                    second.setVisibility(View.VISIBLE);




                }
            }
        });




    }


    public void studentCard(View view){

        Intent intent = new Intent(this, StudentDetails.class);
        startActivity(intent);

    }


    public void uploadCard(View view){
        Intent intent = new Intent(this, Note.class);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        AlertDialog alertDialog = new AlertDialog.Builder(
                AdminActivity.this).create();

        // Setting Dialog Title
        alertDialog.setTitle("Logout!!");

        // Setting Dialog Message
        alertDialog.setMessage("Clicking OK will Logged you out..");

        // Setting Icon to Dialog
        //alertDialog.setIcon();

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
                final ProgressDialog progressDialog = new ProgressDialog(AdminActivity.this,
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



                Toast.makeText(getApplicationContext(), "Home Page", Toast.LENGTH_LONG).show();
                AdminActivity.this.finish();

            }
        });




        // Showing Alert Message
        alertDialog.show();



    }


}
