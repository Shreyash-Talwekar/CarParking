package com.shreyash25.carparking;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

@SuppressLint("ValidFragment")
public class myBooking extends Fragment {
    String a,b,c,d;
    TextView companyinfo;
    public myBooking() {
    }

//    @SuppressLint("ValidFragment")
//    public myBooking(String s1, String s2, String s3, String s4) {
//        S1 = s1;
//        S2 = s2;
//        S3 = s3;
//        S4 = s4;
//    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_mybooking, container, false);
        companyinfo=(TextView)myView.findViewById(R.id.companyinfo);
        a="About Our Company: In today's busy world don't waste your precious time in search of parking ,give us the oppurtunity to serve you.";
        companyinfo.setText(a);
        myView.findViewById(R.id.show_map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), Map.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0,0);
            }
        });
//        Intent details = getActivity().getIntent();
//        a=details.getStringExtra("useremail");
//        b=details.getStringExtra("userdate");
//        c=details.getStringExtra("timest");
//        d=details.getStringExtra("timeed");
//        t1.setText(a);
//        t2.setText(a);
//        t3.setText(c);
//        t4.setText(d);
//        myref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                User value=dataSnapshot.getValue(User.class);
//                t1.setText(value.getEmai());
//                t2.setText(value.getBookD());
//                t3.setText(value.getTimeS());
//                t4.setText(value.getTimeE());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        return myView;
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        refrence.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot myrecords:dataSnapshot.getChildren())
//                {
//                    String date=myrecords.child("bookD").getValue().toString();
//                    String emailid=myrecords.child("emai").getValue().toString();
//                    int timestrt=myrecords.child("timeE").getValue(Integer.class);
//                    int timeen=myrecords.child("timeS").getValue(Integer.class);
//                    int id=myrecords.child("id").getValue(Integer.class);
//                    if(!TextUtils.isEmpty(emailid))
//                    {
//                        t1.setText(id);
//                        t2.setText(date);
//                        t3.setText(timestrt+":00");
//                        t4.setText(timeen+":00");
//                        break;
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
}
