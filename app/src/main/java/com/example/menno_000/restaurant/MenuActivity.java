package com.example.menno_000.restaurant;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuAdapter;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by menno_000 on 9-3-2018.
 */

public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback {

    // Initialising global variables
    static String retrievedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        MenuRequest request = new MenuRequest(this);
        request.getMenu(this);

        // Retrieving the intent
        Intent intent = getIntent();
        retrievedCategory = (String) intent.getSerializableExtra("clicked_category");
    }

    @Override
    public void gotMenu(ArrayList<MenuItem> content) {
        // Initialise adapter (won't work)
        MenuAdapter adapter = new MenuAdapter(this, content);

        // Set the adapter
        ListView dishes = findViewById(R.id.menu_list);
        dishes.setAdapter(adapter);
    }

    @Override
    public void gotMenuError(String message) {
        Log.d("error message", "Something went wrong in MenuActivity.java!");
    }
}
