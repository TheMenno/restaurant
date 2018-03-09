package com.example.menno_000.restaurant;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {

    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        CategoriesRequest request = new CategoriesRequest(this);
        request.getCategories(this);

        listview = findViewById(R.id.categories_list);
        listview.setOnItemClickListener(new CategoryClickListener());
    }

    // Implementing a listener to know when a/which list item is clicked
    private class CategoryClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            // Get the clicked item
            String clickedCategory = (String) adapterView.getItemAtPosition(i);

            // Forwarding the clicked item to the next page
            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
            intent.putExtra("clicked_category", clickedCategory);
            startActivity(intent);
        }
    }

    @Override
    public void gotCategories(ArrayList<String> categories) {
        // Initialise an arrayadapter which will fill the list with available items
        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categories);

        // Add the items to the list
        ListView list_categories = findViewById(R.id.categories_list);
        list_categories.setAdapter(categoriesAdapter);
    }

    @Override
    public void gotCategoriesError(String message) {
        Log.d("error message", "Something went wrong in CategoriesActivity.java!");
    }
}
