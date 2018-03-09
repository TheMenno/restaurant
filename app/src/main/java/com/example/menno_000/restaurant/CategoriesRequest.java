package com.example.menno_000.restaurant;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by menno_000 on 9-3-2018.
 */

public class CategoriesRequest extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    // Initialise an interface which can be interacted with later on
    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    // Initialising certain global variables
    public Callback callback;
    public Context context;
    public CategoriesRequest(Context aContext) {
        this.context = aContext;
    }

    // Retrieve the categories from the source URL
    public void getCategories(Callback Activity) {
        callback = Activity;
        RequestQueue queue = Volley.newRequestQueue((Context) Activity);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/categories", null, this, this);
        queue.add(jsonObjectRequest);
    }

    @Override
    // Do something when it succeeds
    public void onResponse(JSONObject response) {
        try {
            // Create an ArrayList
            JSONArray answer = response.getJSONArray("categories");
            ArrayList<String> categories = new ArrayList<>();

            // Put the categories in the ArrayList
            for (int i = 0; i<answer.length(); i++) {
                String gory = answer.getString(i);
                categories.add(gory);
            }

            // Check if it worked, then give the list to CategoriesActivity
            if(categories.isEmpty()) {callback.gotCategoriesError("Could not find the categories");}
            else {callback.gotCategories(categories);}
        }
        catch (JSONException e) {e.printStackTrace();}
    }

    @Override
    // Error message
    public void onErrorResponse(VolleyError error) {
        callback.gotCategoriesError("Something went wrong");
    }

}