package com.sourcey.materiallogindemo;

import java.io.File;
import java.util.Calendar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.provider.OpenableColumns;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by HP on 04-May-19.
 */

public class SemRegistration extends FragmentActivity  {

    static EditText DateEdit;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private EditText textName;
    private EditText textPhone;
    private EditText textAddress;
    private EditText textEmail;
    private EditText textEnroll;
    private EditText textBranch;
    private EditText textCourse;
    private EditText textMno;
    private EditText textFee;
    private EditText textBack;
    private Button btnChoose;
    private Button btnUpload;
    private TextView textFile;
    private Uri imguri;
    private Intent intenetdata;
    private ProgressBar progressBar;


    public static final String FB_STORAGE_PATH = "studentdetails/";
    public static final String FB_DATABASE_PATH = "studentdetails";
    UploadPDF uploadPDF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semregistration);

        Intent i = getIntent();
        Bundle bd = i.getExtras();
        String username = null, userphone = null;
        if (bd != null) {
            username = (String) bd.get("name");
            userphone = (String) bd.get("phone");
        }

        System.out.println("Name: " + username);
        EditText name = findViewById(R.id.username);
        EditText phone = findViewById(R.id.editTextMobile);

        name.setText(username);
        phone.setText(userphone);

        DateEdit = (EditText) findViewById(R.id.editTextFee);
        DateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTruitonDatePickerDialog(v);
            }
        });


        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("StudentRegDetails");
        uploadPDF = new UploadPDF();


        textName = (EditText) findViewById(R.id.username);
        textPhone = (EditText) findViewById(R.id.editTextMobile);
        textAddress = (EditText) findViewById(R.id.editTextAddress);
        textEmail = (EditText) findViewById(R.id.editTextEmail);
        textEnroll = (EditText) findViewById(R.id.editTextEnroll);
        textBranch = (EditText) findViewById(R.id.editTextBranch);
        textCourse = (EditText) findViewById(R.id.editTextCourse);
        textMno = (EditText) findViewById(R.id.editTextMNo);
        textFee = (EditText) findViewById(R.id.editTextFee);
        textBack = (EditText) findViewById(R.id.editTextBack);
        btnChoose = (Button) findViewById(R.id.btnChoose);
        btnUpload = (Button) findViewById(R.id.btnUpload);
        textFile = (TextView) findViewById(R.id.textFile);
        //progressBar = (ProgressBar) findViewById(R.id.progressbar);


        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPDFFile();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String name = textName.getText().toString().trim();
//                String phone = textPhone.getText().toString().trim();
//                String address = textAddress.getText().toString().trim();
//                String email = textEmail.getText().toString().trim();
//                String enroll = textEnroll.getText().toString().trim();
//                String branch = textBranch.getText().toString().trim();
//                String course = textCourse.getText().toString().trim();
//                String mno = textMno.getText().toString().trim();
//                String fee = textFee.getText().toString().trim();
//                String back = textBack.getText().toString().trim();
//
//                uploadPDF.setName(name);
//                uploadPDF.setPhone(phone);
//                uploadPDF.setAddress(address);
//                uploadPDF.setEmail(email);
//                uploadPDF.setEnroll(enroll);
//                uploadPDF.setBranch(branch);
//                uploadPDF.setCourse(course);
//                uploadPDF.setMno(mno);
//                uploadPDF.setFee(fee);
//                uploadPDF.setBacklog(back);
//
//                databaseReference.push().setValue(uploadPDF);

                uploadPDFFile(intenetdata.getData());
                //Toast.makeText(SemRegistration.this, "Data Uploaded..", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void selectPDFFile() {

        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select PDF File"), 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        intenetdata = data;

//        if(requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null){
//            //uploadPDFFile(data.getData());
//
//            textFile.setText("File Selected!! Press Upload Form to upload data..");
//        }


        if (resultCode == RESULT_OK) {
            // Get the Uri of the selected file
            Uri uri = data.getData();
            String uriString = uri.toString();
            File myFile = new File(uriString);
            String path = myFile.getAbsolutePath();
            String displayName = null;

            if (uriString.startsWith("content://")) {
                Cursor cursor = null;
                try {
                    cursor = this.getContentResolver().query(uri, null, null, null, null);
                    if (cursor != null && cursor.moveToFirst()) {
                        displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                    }
                } finally {
                    cursor.close();
                }
            } else if (uriString.startsWith("file://")) {
                displayName = myFile.getName();
            }

            textFile.setText(displayName);

    }



    }
//
    private void uploadPDFFile(Uri data){


//        final ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setTitle("Loading...");
//        progressDialog.show();
//
//        final String filename = System.currentTimeMillis()+".pdf";
//        StorageReference reference = storageReference.child(FB_STORAGE_PATH + System.currentTimeMillis() + ".pdf");
//        reference.putFile(data)
//                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
//                        while (!uri.isComplete());
//                        Uri url = uri.getResult();
//
//                        UploadPDF uploadPDF = new UploadPDF();
//                        uploadPDF.setUrl(url.toString());
//                        databaseReference.child(databaseReference.push().getKey()).setValue(uploadPDF);
//                        Toast.makeText(SemRegistration.this, "File Uploaded", Toast.LENGTH_SHORT).show();
//                        progressDialog.dismiss();
//                    }
//                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
//                progressDialog.setMessage("Uploaded: " + (int) progress+"%");
//            }
//        });




        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.show();

        final String filename = System.currentTimeMillis()+".pdf";
        StorageReference reference = storageReference.child(FB_STORAGE_PATH + System.currentTimeMillis() + ".pdf");
        reference.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @SuppressWarnings("VisibleForTests")
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressDialog.dismiss();
                        //Toast.makeText(SemRegistration.this, "Data Uploaded!", Toast.LENGTH_SHORT).show();

                        UploadPDF upload = new UploadPDF();


                        String name = textName.getText().toString().trim();
                        String phone = textPhone.getText().toString().trim();
                        String address = textAddress.getText().toString().trim();
                        String email = textEmail.getText().toString().trim();
                        String enroll = textEnroll.getText().toString().trim();
                        String branch = textBranch.getText().toString().trim();
                        String course = textCourse.getText().toString().trim();
                        String mno = textMno.getText().toString().trim();
                        String fee = textFee.getText().toString().trim();
                        String back = textBack.getText().toString().trim();

                        upload.setName(name);
                        upload.setPhone(phone);
                        upload.setAddress(address);
                        upload.setEmail(email);
                        upload.setEnroll(enroll);
                        upload.setBranch(branch);
                        upload.setCourse(course);
                        upload.setMno(mno);
                        upload.setFee(fee);
                        upload.setBacklog(back);



                        upload.setUrl(taskSnapshot.getDownloadUrl().toString());

                        databaseReference.child(name).setValue(upload).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(SemRegistration.this, "Data uploaded!", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getApplicationContext(), OutScreen.class);
                                    startActivity(intent);
                                    SemRegistration.this.finish();

                                }
                                else
                                    Toast.makeText(SemRegistration.this, "Not uploaded", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @SuppressWarnings("VisibleForTests")
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                        progressDialog.setMessage("Uploaded: " + (int) progress+"%");
                    }
                });













    }







    public void showTruitonDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public static class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            DateEdit.setText(day + "/" + (month + 1) + "/" + year);
        }
    }
}



