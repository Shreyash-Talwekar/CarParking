package com.shreyash25.carparking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Directions extends Fragment {
    private Button locate;
    private TextView textView1,textView2,textView3;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //moveToNewActivity();

        return inflater.inflate(R.layout.fragment_directions,null);
    }
    private void moveToNewActivity() {
        Intent i = new Intent(getActivity(), Map.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        ((Activity) getActivity()).overridePendingTransition(0,0);

    }
    private void select()
    {

    }

}
