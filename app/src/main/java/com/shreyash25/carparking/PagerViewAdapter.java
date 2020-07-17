package com.shreyash25.carparking;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;


public class PagerViewAdapter extends FragmentStatePagerAdapter {
String []tabarray=new String[]{"Booking","About Parking" +
        ""};

    public PagerViewAdapter(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabarray[position];
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        switch (position)
        {
            case 0:
                fragment =new startBooking();
                break;
            case 1:
                fragment=new myBooking();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
