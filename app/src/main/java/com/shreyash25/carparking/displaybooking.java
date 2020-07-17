package com.shreyash25.carparking;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class displaybooking extends AppCompatActivity {

    DatabaseReference refrence;
    FirebaseDatabase datab;
    ListView listView;
    String emaildisplay;
    List<User>userList;
    String Emaildisplay,Datedisplay;
   int iddisplay,startdisplay,enddisplay;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaybooking);
        listView=(ListView)findViewById(R.id.mylist);
        Intent display=getIntent();
        emaildisplay=display.getStringExtra("useremail");
        userList=new ArrayList<>();
        datab=FirebaseDatabase.getInstance();
        refrence=datab.getReference("CarParking");

        refrence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot myrecords: dataSnapshot.getChildren())
                {
                    Datedisplay=myrecords.child("bookD").getValue(String.class);
                    Emaildisplay=myrecords.child("emai").getValue(String.class);
                    iddisplay= (int) myrecords.child("id").getValue();
                    enddisplay= (int) myrecords.child("timeE").getValue();
                    startdisplay= (int) myrecords.child("timeS").getValue();


                    if(emaildisplay.equals(Emaildisplay)) {
                        user=new User(startdisplay,enddisplay,Emaildisplay,Datedisplay,iddisplay);
                        userList.add(user);
                    }
                }
                UserList adapter=new UserList(displaybooking.this,userList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
        @Override
    public void onStart() {
        super.onStart();

    }
}
