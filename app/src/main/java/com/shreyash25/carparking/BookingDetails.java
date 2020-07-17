package com.shreyash25.carparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;

public class BookingDetails extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference ref,superref;
    TextView emal,bookdate;
    Spinner timeStart,timeEnd;
    int im_id;
    String s,timestart,timeend,chose_date,displayemail;
    User user;
    Collection collection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);
        timeStart=(Spinner)findViewById(R.id.timestart);
        timeEnd=(Spinner) findViewById(R.id.timeend);
        emal=(TextView)findViewById(R.id.mail);
        bookdate=(TextView)findViewById(R.id.date11);
        ArrayAdapter<CharSequence>startadapter=ArrayAdapter.createFromResource(this,R.array.start_time,android.R.layout.simple_spinner_item);
        startadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeStart.setAdapter(startadapter);
        ArrayAdapter<CharSequence>endadapter=ArrayAdapter.createFromResource(this,R.array.end_time,android.R.layout.simple_spinner_item);
        endadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeEnd.setAdapter(endadapter);
        ((Spinner) findViewById(R.id.timestart)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                timestart=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ((Spinner) findViewById(R.id.timeend)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 timeend=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        database=FirebaseDatabase.getInstance();
        ref=database.getReference("CarParking");
        superref=database.getReference("Records");
        user=new User();
        displayemail=emal.getText().toString();
        collection=new Collection();
        Intent iin=getIntent();
        im_id=iin.getIntExtra("photoid",0);
        chose_date=iin.getStringExtra("selecteddate");
        bookdate.setText(chose_date);
        findViewById(R.id.subd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=ref.push().getKey();
                String id2=superref.push().getKey();
                myvalues();
                loadUserInformation();
                ref.child(id).setValue(user);
                        superref.child(id2).setValue(collection);
                Toast.makeText(BookingDetails.this, "Details Entered Successfully", Toast.LENGTH_LONG).show();
                finish();
                Intent i = new Intent(BookingDetails.this, Parking.class);
                //i.putExtra("useremail",displayemail);
                startActivity(i);
//                i.putExtra("useremail",emal.getText().toString());
//                i.putExtra("userdate",bookdate.getText().toString());
//                i.putExtra("timest",timestart);
//                i.putExtra("timeed",timeend);
                //startActivity(i);
//                ref.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//
//                        myvalues();
//                        loadUserInformation();
//                        ref.child("User 01").setValue(user);
////                        superref.child(collectionid).setValue(collection);
//                        Toast.makeText(BookingDetails.this,"Details Entered Successfully",Toast.LENGTH_LONG).show();
//                        finish();
//                        Intent i=new Intent(BookingDetails.this,startBooking.class);
//                        startActivity(i);
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//            });
            }
        });
    }
    private  void myvalues()
    {
        user.setEmai(emal.getText().toString());
        Log.i("Email",emal.getText().toString());
        user.setBookD(chose_date);
        Log.i("BookDate",chose_date);
        user.setTimeS( Integer.parseInt(timestart));
        Log.i("TimeStart:",timestart);
        user.setTimeE(Integer.parseInt(timeend));
        Log.i("TimeEnd:",timeend);
        user.setId(im_id);
        Toast.makeText(getApplicationContext(),"ID: "+im_id,Toast.LENGTH_SHORT).show();

    }
    private void loadUserInformation()
    {
        collection.setBookDate(bookdate.getText().toString());
        collection.settimestart(Integer.parseInt(timestart));
        collection.settimeend(Integer.parseInt(timeend));
        collection.setid(im_id);
    }
}
