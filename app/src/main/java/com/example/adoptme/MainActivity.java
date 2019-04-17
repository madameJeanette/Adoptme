package com.example.adoptme;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.RelativeLayout;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CatAdapter.OnItemClickListener {
    public static final String EXTRA_URL = "imageUrl";  //constant
    public static final String EXTRA_NAME = "catName";
    public static final String EXTRA_AGE = "ageCount";

    private RecyclerView mRecyclerView;
    private CatAdapter mCatAdapter;
    private ArrayList<Cat> mCatList;
    private RequestQueue mRequestQueue;
    private RelativeLayout RL;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RL = findViewById(R.id.relativeLayout);
        //   LL = findViewById(R.id.linearLayout);
        mRecyclerView = findViewById(R.id.recycler_view); //find layout by id
        mRecyclerView.setHasFixedSize(true); //Set size to increase performance cuz we won' t change width and height
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mCatList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this); //We get a new request queue where we can add our JSON request
        parseJSON(); //call method to parse JSON from request.

    }


    private void parseJSON() {
        String url = getString(R.string.url_string);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("hits");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);

                                String catName = hit.getString("user");
                                String imageUrl = hit.getString("webformatURL");
                                int ageCount = hit.getInt("comments");

                                mCatList.add(new Cat(imageUrl, catName, ageCount));
                            }

                            mCatAdapter = new CatAdapter(MainActivity.this, mCatList);
                            mRecyclerView.setAdapter(mCatAdapter); //this will take care of passing the json data into our catList
                            // adds it to the adapter and sets the adapter on the recyclerview.
                            mCatAdapter.setOnItemClickListener(MainActivity.this); //click gets passed to the mainactivity
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, DetailActivity.class); //package item click w/ intent
        Cat clickedItem = mCatList.get(position); //Get the item out of the clicked position from the CatList.

        detailIntent.putExtra(EXTRA_URL, clickedItem.getImageUrl());
        detailIntent.putExtra(EXTRA_NAME, clickedItem.getName());
        detailIntent.putExtra(EXTRA_AGE, clickedItem.getAgeCount());

        startActivity(detailIntent); //start detail activity and pass the data with the intent package
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // onCreateOptionsMenu, use to inflate the menu
        getMenuInflater().inflate(R.menu.main, menu);
        // Return true to display  menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//if menu item selected use settings intent w/ settings activity
        int itemId = item.getItemId();
        switch (itemId) {

            case R.id.action_settings:
                Intent startSettingsActivity = new Intent(this, SettingsActivity.class);
                startActivity(startSettingsActivity);
                return true;


        }
        return super.onOptionsItemSelected(item);


    }

    @Override
    protected void onResume() {
        if (SettingsActivity.CHANGE_BC(this)) {
         //if setting changed change background color
            RL.setBackgroundColor(ContextCompat.getColor(this, R.color.background_cute));

        } else if (!(SettingsActivity.CHANGE_BC(this))) {
            RL.setBackgroundColor(ContextCompat.getColor(this, R.color.background_color));

        }
        super.onResume();
    }

}
