package com.shreyash25.carparking;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pedromassango.doubleclick.DoubleClick;
import com.pedromassango.doubleclick.DoubleClickListener;

import java.util.Calendar;
import java.util.Date;

public class startBooking extends Fragment  {

    private ImageView image1,image2,image3,image4,image5,image6,image7,image8,imagex;
    private ProgressBar progressBar;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    int day,month,year,start,end;
    String filtertimestart,filtertimeend;
    Spinner filterTimestart;
    Spinner filterTimeend;
    private static final String TAG="startBooking";
    private boolean click;
    private final int TAG1=1,TAG2=2,TAG3=3,TAG4=4,TAG5=5,TAG6=6,TAG7=7,TAG8=8;
    //TextView dat,tim;
    public String selected_date;
     View myView;
     public int ID;
    String date,time;
    DatePickerDialog.OnDateSetListener listener;
    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_booking, container, false);
        click=false;
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Records");
        image1=(ImageView)myView.findViewById(R.id.im00);
        image2=(ImageView)myView.findViewById(R.id.im01);
        image3=(ImageView)myView.findViewById(R.id.im10);
        image4=(ImageView)myView.findViewById(R.id.im11);
        image5=(ImageView)myView.findViewById(R.id.im20);
        image6=(ImageView)myView.findViewById(R.id.im21);
        image7=(ImageView)myView.findViewById(R.id.im30);
        image8=(ImageView)myView.findViewById(R.id.im31);
        filterTimestart=(Spinner) myView.findViewById(R.id.filtertimestart);
        filterTimeend=(Spinner) myView.findViewById(R.id.filtertimeend);
        ArrayAdapter<CharSequence> startadapter=ArrayAdapter.createFromResource(getActivity(),R.array.start_time,android.R.layout.simple_spinner_item);
        startadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterTimestart.setAdapter(startadapter);
        ArrayAdapter<CharSequence>endadapter=ArrayAdapter.createFromResource(getActivity(),R.array.end_time,android.R.layout.simple_spinner_item);
        endadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterTimeend.setAdapter(endadapter);
        launch();
            return myView;
    }
@RequiresApi(api = Build.VERSION_CODES.N)
private void launch()
{

    myclick1(myView,image1,R.id.im00);
    myclick(myView,image2,R.id.im01);
    myclick1(myView,image3,R.id.im10);
    myclick(myView,image4,R.id.im11);
    myclick1(myView,image5,R.id.im20);
    myclick(myView,image6,R.id.im21);
    myclick1(myView,image7,R.id.im30);
    myclick(myView,image8,R.id.im31);
    progressBar=(ProgressBar)myView.findViewById(R.id.progressbar);

    Calendar calendar=Calendar.getInstance();
    date=DateFormat.getDateInstance().format(calendar.getTime());
    //dat=(TextView)myView.findViewById(R.id.tert1);
    //tim=(TextView)myView.findViewById(R.id.time1);
    SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
    time="Current time:"+format.format(calendar.getTime());
    ((Spinner) myView.findViewById(R.id.filtertimestart)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            filtertimestart=adapterView.getItemAtPosition(i).toString();
           // Toast.makeText(getContext(),"Starting time : "+filtertimestart,Toast.LENGTH_SHORT).show();
            start=Integer.parseInt(filtertimestart);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    });
    ((Spinner) myView.findViewById(R.id.filtertimeend)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            filtertimeend=adapterView.getItemAtPosition(i).toString();
           // Toast.makeText(getContext(),"End time : "+filtertimeend,Toast.LENGTH_SHORT).show();
            end=Integer.parseInt(filtertimeend);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    });
    myView.findViewById(R.id.boook).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if(click==true) {

                progressBar.setVisibility(View.VISIBLE);
                Intent i = new Intent(getActivity(), BookingDetails.class);
                progressBar.setVisibility(View.GONE);
                i.putExtra("photoid",ID);
                if(selected_date!=null)
                    i.putExtra("selecteddate",selected_date);
                // i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    Bundle bundle=new Bundle();
//                    bundle.putInt("abc",ID);
                startActivity(i);
            }
            else{
                Toast.makeText(getActivity(),"Select a Slot to Book",Toast.LENGTH_SHORT).show();
                return;
            }
        }
    });
    myView.findViewById(R.id.filter).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //dat.setText(date);
            //tim.setText(time);
            DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int monthofyear, int dayofmonth) {
                    datePicker.setMinDate(System.currentTimeMillis()-1000);
                    monthofyear=monthofyear+1;
                    selected_date=dayofmonth+"/"+monthofyear+"/"+year;
                    //dat.setText(selected_date);
                    applychanges(selected_date );
                }

            },year,month,day);
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
            datePickerDialog.show();


        }
    });
}

@RequiresApi(api = Build.VERSION_CODES.N)
private void applychanges(String dateselect)
{
    Toast.makeText(getActivity(),dateselect,Toast.LENGTH_SHORT).show();
//    for (DataSnapshot allrecords : dataSnapshot.getChildren()) {
//        Colection use = allrecords.getValue(Collection.class);
//        if (selected_date == use.getBookDate()) {

//            swtichcase(use.getid());
//            if (use.getid() % 2 == 0) {
//                imagex.setImageResource(R.drawable.car0);
//
//            } else {
//                imagex.setImageResource(R.drawable.car11);
//
//            }
//        }
//    }
//    launch();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot allrecords : dataSnapshot.getChildren()) {
                    //Collection use = allrecords.getValue(Collection.class);
                    int id = allrecords.child("id").getValue(Integer.class);
                    int time1=allrecords.child("timestart").getValue(Integer.class);
                    int time2=allrecords.child("timeend").getValue(Integer.class);
                    String date = allrecords.child("bookDate").getValue().toString();
                    if (selected_date.equals(date)) {

                        if(start<time2 && start>time1){
                            swtichcase(id);
                            if (id % 2 == 0) {
                                imagex.setImageResource(R.drawable.car0);

                            } else {
                                imagex.setImageResource(R.drawable.car11);

                            }
                        }
                        else if (start<time1 && end>time2)
                        {
                            swtichcase(id);
                            if (id % 2 == 0) {
                                imagex.setImageResource(R.drawable.car0);

                            } else {
                                imagex.setImageResource(R.drawable.car11);
                            }
                        }
                        else if(end>time1&& end<time2){
                                swtichcase(id);
                                if (id % 2 == 0) {
                                    imagex.setImageResource(R.drawable.car0);

                                } else {
                                    imagex.setImageResource(R.drawable.car11);

                                }
                        }
                        else;
                    }
                }
                launch();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

}
    private void swtichcase(int k)
    {
        switch (k)
        {
            case 1:
                imagex=image1;
                //Toast.makeText(getActivity(),"1 is selected",Toast.LENGTH_LONG).show();
                break;
            case 2:
                imagex=image2;
                //Toast.makeText(getActivity(),"2 is selected",Toast.LENGTH_LONG).show();
                break;
            case 3:
                imagex=image3;
                //Toast.makeText(getActivity(),"3 is selected",Toast.LENGTH_LONG).show();
                break;
            case 4:
                imagex=image4;
                //Toast.makeText(getActivity(),"4 is selected",Toast.LENGTH_LONG).show();
                break;
            case 5:
                imagex=image5;
                //Toast.makeText(getActivity(),"5 is selected",Toast.LENGTH_LONG).show();
                break;
            case 6:
                imagex=image6;
                //Toast.makeText(getActivity(),"6 is selected",Toast.LENGTH_LONG).show();
                break;
            case 7:
                imagex=image7;
                //Toast.makeText(getActivity(),"7 is selected",Toast.LENGTH_LONG).show();
                break;
            case 8:
                imagex=image8;
                //Toast.makeText(getActivity(),"8 is selected",Toast.LENGTH_LONG).show();
                break;

        }
    }

    private void myclick1(final View myView, final ImageView image , final int id)
    {
        myView.findViewById(id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image.setImageResource(R.drawable.car11);
                click=true;
                myView.findViewById(id).setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        image.setImageResource(R.drawable.car1);
                        click=false;

                        return click;
                    }
                });
                switch (id)
                {
                    case R.id.im00:
                        ID=TAG1;
                        break;
                    case R.id.im10:
                        ID=TAG3;
                        break;
                    case R.id.im20:
                        ID=TAG5;
                        break;
                    case R.id.im30:
                        ID=TAG7;
                        break;
                }

            }
        });
    }

    private void myclick(final View myView, final ImageView image , final int id)
    {
        myView.findViewById(id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image.setImageResource(R.drawable.car0);
                click=true;
                myView.findViewById(id).setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        image.setImageResource(R.drawable.car);
                        click=false;

                        return click;
                    }
                });
                switch (id)
                {
                    case R.id.im01:
                        ID=TAG2;
                        break;
                    case R.id.im11:
                        ID=TAG4;
                        break;
                    case R.id.im21:
                        ID=TAG6;
                        break;
                    case R.id.im31:
                        ID=TAG8;
                        break;
                }

            }
        });
    }


}
