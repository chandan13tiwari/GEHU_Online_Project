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

public class Note extends FragmentActivity  {


    private StorageReference storageReference;
    private DatabaseReference databaseReference;

    private Button btnChoose;
    private Button btnUpload;
    private TextView textFile;
    private Uri imguri;
    private Intent intenetdata;
    private ProgressBar progressBar;


    public static final String FB_STORAGE_PATH = "uploads/";
    public static final String FB_DATABASE_PATH = "uploads";
    UploadPDF uploadPDF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);



        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("UploadedFiles");
        uploadPDF = new UploadPDF();



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

                        PDFUploads upload = new PDFUploads();

                        String file = textFile.getText().toString().trim();


                        upload.setName(file);



                        upload.setUrl(taskSnapshot.getDownloadUrl().toString());

                        databaseReference.push().setValue(upload).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(Note.this, "File uploaded!", Toast.LENGTH_LONG).show();
                                    Note.this.finish();
                                }
                                else
                                    Toast.makeText(Note.this, "Not uploaded", Toast.LENGTH_SHORT).show();
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

}



