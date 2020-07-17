package com.shreyash25.carparking;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class UserList extends ArrayAdapter<User> {
    private Activity context;
    private String email,date;
    private int id,starttime,endtime;
    List<User>userList;
    View listviewitem;
    User user;

    public UserList(Activity context, List<User> userList)
    {
        super(context,R.layout.list_layout,userList);
        this.context=context;
        this.userList=userList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listviewitem=inflater.inflate(R.layout.list_layout,null,true);

        TextView textEmail=(TextView)listviewitem.findViewById(R.id.textEmail);
        TextView textBookD=(TextView)listviewitem.findViewById(R.id.TextBookD);
        TextView textBookID=(TextView)listviewitem.findViewById(R.id.TextBookID);
        TextView texttimeS=(TextView)listviewitem.findViewById(R.id.texttimeS);
        TextView texttimeE=(TextView)listviewitem.findViewById(R.id.texttimeE);
        user=userList.get(position);
        textEmail.setText(user.getEmai());
        textBookD.setText(user.getBookD());
        textBookID.setText(user.getId());
        texttimeS.setText(user.getTimeS());
        texttimeE.setText(user.getTimeE());
        return listviewitem;
    }
}
