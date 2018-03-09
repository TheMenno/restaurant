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

    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    public Callback callback;
    public Context context;
    public CategoriesRequest(Context aContext) {
        this.context = aContext;
    }

    public void getCategories(Callback Activity) {
        RequestQueue queue = Volley.newRequestQueue((Context) Activity);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/categories", null, this, this);
        queue.add(jsonObjectRequest);

        callback = Activity;
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            JSONArray res = response.getJSONArray("categories");
            ArrayList<String> categories = new ArrayList<>();

            Log.d("error message", categories.toString());

            for (int i = 0; i<res.length(); i++) {
                String gory = res.getString(i);
                categories.add(gory);
            }

            if(categories.isEmpty()) {
                callback.gotCategoriesError("There are no categories");
            } else {
                callback.gotCategories(categories);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        callback.gotCategoriesError("Something went wrong");
    }

}