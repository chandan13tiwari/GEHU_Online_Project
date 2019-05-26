package com.sourcey.materiallogindemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

//import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by HP on 17-May-19.
 */

public class StudentDetails extends AppCompatActivity {


    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    User user;

    TextView username, name, phone, address, email, enroll, branch, course, mno, fee, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentdetails);


        listView = (ListView) findViewById(R.id.listView);
        user = new User();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("StudentRegDetails");
        list = new ArrayList<>();

        adapter = new ArrayAdapter<String>(this, R.layout.list_layout, R.id.tv1, list);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){


                    user = ds.getValue(User.class);
                    System.out.println("Check"+listView);
//                    list.add(user.getName().toString()+"\n "+user.getPhone().toString()+"\n "+user.getAddress().toString()
//                    +" "+user.getEmail().toString()+"\n "+user.getEnroll().toString()+"\n "+user.getBranch().toString()+"\n "+
//                    user.getCourse().toString()+"\n "+user.getMno().toString()+"\n "+user.getFee().toString()+"\n "+user.getBacklog().toString());
//
                    if(user.getCourse().toString().isEmpty()){
                        list.add(user.getName().toString() + " - " + user.getBranch().toString());
                    }
                    else
                        list.add(user.getName().toString() + " - " + user.getBranch().toString() + " ("+user.getCourse().toString()+")");


                }
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


}
