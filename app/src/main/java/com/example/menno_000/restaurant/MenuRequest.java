package com.example.menno_000.restaurant;

import android.content.Context;
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

public class MenuRequest extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    // Initialise global variables
    public Context context;
    public MenuRequest(Context aContext) {
        this.context = aContext;
    }

    // Initialse Callback
    public Callback callback;
    public interface Callback {
        void gotMenu(ArrayList<MenuItem> categories);
        void gotMenuError(String message);
    }

    // Retrieve the menu items from the URL
    public void getMenu(Callback activity) {
        callback = activity;
        RequestQueue queue = Volley.newRequestQueue((Context) activity);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/menu", null, this, this);
        queue.add(jsonObjectRequest);
    }

    @Override
    // Do something when it succeeds
    public void onResponse(JSONObject response) {
        try {
            // Retrieve the items from the JSONArray
            JSONArray answer = response.getJSONArray("menu");
            String menu = MenuActivity.retrievedCategory;
            ArrayList<MenuItem> menuList = new ArrayList<>();

            // Iterate through the items in the array
            for (int i = 0; i<answer.length(); i++) {
                JSONObject dish = answer.getJSONObject(i);

                // If the item is the same as the clicked item, save it
                if (dish.getString("category").equals(menu)) {
                    String name = dish.getString("name");
                    String description = dish.getString("description");
                    String url = dish.getString("image_url");
                    Integer price = dish.getInt("price");
                    String category = dish.getString("category");

                    MenuItem menuItem = new MenuItem(name, description, url, price, category);
                    menuList.add(menuItem);
                }
            }
            // Check if it worked, else give the items to MenuActivity
            if(menuList.isEmpty()) {callback.gotMenuError("There is no menu");}
            else {callback.gotMenu(menuList);}
        }
        catch (JSONException e) {e.printStackTrace();}
    }

    @Override
    // Error check
    public void onErrorResponse(VolleyError error) {
        callback.gotMenuError("Something went wrong");
    }

}