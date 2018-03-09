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

    public Callback callback;
    public interface Callback {
        void gotMenu(ArrayList<MenuItem> categories);
        void gotMenuError(String message);
    }

    public Context context;
    public MenuRequest(Context aContext) {
        this.context = aContext;
    }

    public void getMenu(Callback activity) {
        RequestQueue queue = Volley.newRequestQueue((Context) activity);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/menu", null, this, this);
        queue.add(jsonObjectRequest);

        callback = activity;
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            JSONArray answer = response.getJSONArray("menu");
            String menu = MenuActivity.retrievedCategory;
            ArrayList<MenuItem> menuList = new ArrayList<>();

            Log.d("error message", menuList.toString());

            for (int i = 0; i<answer.length(); i++) {
                JSONObject dish = answer.getJSONObject(i);

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

            if(menuList.isEmpty()) {
                callback.gotMenuError("There is no menu");
            } else {
                callback.gotMenu(menuList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        callback.gotMenuError("Something went wrong");
    }

}