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

// Initialise the MenuAdapter Class, this would not work for some reason
public class MenuAdapter extends ArrayAdapter<MenuItem> {

    // Initialse global variables
    private Context context;
    public ArrayList menuItems;
    ImageView image;

    // Initialise the adapter
    public MenuAdapter(Context context, ArrayList<MenuItem> menuItems) {
        super(context, 0, menuItems);
        this.context = context;
        this.menuItems = menuItems;
    }

    @Override
    public View getView(int number, View view, @NonNull ViewGroup pop) {
        // Find out which item was clicked
        MenuItem item = getItem(number);

        // Get information about the clicked item
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.activity_menu_item, pop, false);
        }

        // Set the text in the corresponding Menu layout file
        image = view.findViewById(R.id.image);
        TextView title = view.findViewById(R.id.name);
        TextView price = view.findViewById(R.id.price);

        title.setText(item.getName());
        price.setText(item.getPrice().toString());

        return view;
    }
}