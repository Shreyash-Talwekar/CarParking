package com.shreyash25.carparking;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Parking extends AppCompatActivity
         {
             String a,b,c,d;
    TextView booking,mybooking,directions;
             FirebaseAuth mAuth;
    ViewPager viewPager;
    PagerViewAdapter pagerViewAdapter;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout=(TabLayout)findViewById(R.id.tabs);
        ViewPager viewPager=(ViewPager)findViewById(R.id.viewpager);
            PagerViewAdapter pagerViewAdapter=new PagerViewAdapter(getSupportFragmentManager());
            viewPager.setAdapter(pagerViewAdapter);
            tabLayout.setupWithViewPager(viewPager);
        mAuth=FirebaseAuth.getInstance();



//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
        /*booking=(TextView)findViewById(R.id.booking);
        mybooking=(TextView)findViewById(R.id.mybooking);
        directions=(TextView)findViewById(R.id.directions);
        viewPager=(ViewPager)findViewById(R.id.fragment_container);
        pagerViewAdapter=new PagerViewAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerViewAdapter);
        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
            }
        });
        mybooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
            }
        });

        directions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2);
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                onChangeTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });*/

    }
//    private  void onChangeTab(int position)
//    {
//        if(position==0)
//        {
//            booking.setTextSize(25);
//            booking.setTextColor(getColor(R.color.dark_color));
//            mybooking.setTextSize(20);
//            mybooking.setTextColor(getColor(R.color.white_color));
//            directions.setTextSize(20);
//            directions.setTextColor(getColor(R.color.white_color));
//        }
//        if(position==1)
//        {
//            booking.setTextSize(25);
//            booking.setTextColor(getColor(R.color.white_color));
//            mybooking.setTextSize(20);
//            mybooking.setTextColor(getColor(R.color.dark_color));
//            directions.setTextSize(20);
//            directions.setTextColor(getColor(R.color.white_color));
//        }
//        if(position==2)
//        {
//            booking.setTextSize(25);
//            booking.setTextColor(getColor(R.color.white_color));
//            mybooking.setTextSize(20);
//            mybooking.setTextColor(getColor(R.color.white_color));
//            directions.setTextSize(20);
//            directions.setTextColor(getColor(R.color.dark_color));
//        }
//    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.parking, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.log_out) {
            FirebaseAuth.getInstance().signOut();
            finish();
            Intent logintent = new Intent(this, MainActivity.class);
            logintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(logintent);
        }



        return super.onOptionsItemSelected(item);
    }
             @Override
             protected void onStart() {
                 super.onStart();
                 if(mAuth.getCurrentUser()==null)
                 {

                     Intent i=new Intent(this,MainActivity.class);
                     finish();
                 }
                // else
                     FirebaseUser user=mAuth.getCurrentUser();
             }
             /*private void loadUserInformation()
             {
                 FirebaseUser user=mAuth.getCurrentUser();
             }*/

//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.logout) {
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }
}
