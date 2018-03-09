package com.example.menno_000.restaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by menno_000 on 9-3-2018.
 */

public class MenuAdapter extends ArrayAdapter<MenuItem> {

    private Context context;
    public ArrayList menuItems;
    private int resource;

    public MenuAdapter(Context context, ArrayList<MenuItem> menuItems) {
        super(context, 0, menuItems);
        this.context = context;
        this.resource = resource;
        this.menuItems = menuItems;
    }

    public TextView title;
    public TextView price;
    ImageView image;

    @Override
    public View getView(int i, View v, @NonNull ViewGroup p) {
        // Get the data from this position
        MenuItem item = getItem(i);
        // Inflate view
        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.activity_menu_item, p, false);
        }
        // Find Views
        title = v.findViewById(R.id.name);
        price = v.findViewById(R.id.price);
        image = v.findViewById(R.id.image);
        // Populate
        assert item != null;
        title.setText(item.getName());
        price.setText(item.getPrice().toString());
        //loadImageFromUrl(item.getImageUrl());

        return v;
    }
}