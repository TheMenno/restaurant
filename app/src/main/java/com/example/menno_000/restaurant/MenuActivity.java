package com.example.menno_000.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by menno_000 on 9-3-2018.
 */

public class MenuActivity extends AppCompatActivity {

    static String retrievedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        MenuRequest request = new MenuRequest(this);
        request.getMenu(this);

        // 'Opening' the intent given by the previous page
        Intent intent = getIntent();
        retrievedCategory = (String) intent.getSerializableExtra("clicked_category");
    }

    @Override
    public void gotMenu(ArrayList<MenuItem> items) {
        // Add custom adapter to ListView
        MenuAdapter adapter = new MenuAdapter(this, items);
        // Attach adapter to ListView
        ListView dishes = findViewById(R.id.menu_list);
        dishes.setAdapter(adapter);
    }

    @Override
    public void gotMenuItemsError(String message) {
        // Display error message
    }
}
