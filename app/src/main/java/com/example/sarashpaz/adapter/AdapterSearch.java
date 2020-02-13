package com.example.sarashpaz.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sarashpaz.R;
import com.example.sarashpaz.Tozihat;
import com.example.sarashpaz.model.ItemRecycler;

import java.util.ArrayList;

public class AdapterSearch extends ArrayAdapter {
    public int resourceId;
    public Activity activity;
    public ArrayList<ItemRecycler> data;
    TextView txtName;
    public static boolean flag_serch=false;
    public static String nameMontakhab;

    public AdapterSearch(Activity activity, int resourceId, ArrayList<ItemRecycler> object) {
        super(activity, resourceId, object);
        this.resourceId = resourceId;
        this.activity = activity;
        this.data = object;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        view = this.activity.getLayoutInflater().inflate(this.resourceId, null);
        txtName = view.findViewById(R.id.txt_search);
        flag_serch=true;
        ItemRecycler foods = new ItemRecycler();
        foods =data.get(position);
        txtName.setText(foods.getuName());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(activity, Tozihat.class);
                intent.putExtra("title_search",data.get(position).getuName());
                intent.putExtra("img_search",data.get(position).getuAvatar());
                intent.putExtra("mavad_search",data.get(position).getMavad());
                intent.putExtra("tahaieh_search",data.get(position).getTahaieh());
                activity.startActivity(intent);
            }
        });
        return view;
    }
}
