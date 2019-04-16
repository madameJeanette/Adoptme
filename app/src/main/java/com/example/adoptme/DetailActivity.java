package com.example.adoptme;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.adoptme.MainActivity.EXTRA_AGE;
import static com.example.adoptme.MainActivity.EXTRA_NAME;
import static com.example.adoptme.MainActivity.EXTRA_URL;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String catName = intent.getStringExtra(EXTRA_NAME);
        int ageCount = intent.getIntExtra(EXTRA_AGE, 0);

        ImageView imageView = findViewById(R.id.image_view_detail);
        TextView textViewName = findViewById(R.id.text_view_name_detail);
        TextView textViewAge = findViewById(R.id.text_view_age_detail);

        Picasso.get().load(imageUrl).fit().centerInside().into(imageView); //center image in imageview
        textViewName.setText(catName);
        textViewAge.setText("Age " + ageCount);
    }




    /**
     * This method is called when the Open Location in Map button is clicked. It will open the
     * a map to the location represented by the variable addressString using implicit Intents.
     *
     * @param v Button that was clicked.
     */
    public void onClickOpenAddressButton(View v) {
        // Store an address in a String

        String addressString = getString(R.string.geo_street);

        // Use Uri.Builder with the appropriate scheme and query to form the Uri for the address
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("geo")
                .path("0,0")
                .appendQueryParameter("q", addressString);
        Uri addressUri = builder.build();

        // Replace the Toast with a call to showMap, passing in the Uri from the previous step
        showMap(addressUri);
    }

// Create a method called showMap with a Uri as the single parameter

    /**
     * This method will fire off an implicit Intent to view a location on a map.
     * <p>
     * When constructing implicit Intents, you can use either the setData method or specify the
     * URI as the second parameter of the Intent's constructor,

     *
     * @param geoLocation The Uri representing the location that will be opened in the map
     */
    private void showMap(Uri geoLocation) {
        //  Create an Intent with action type, Intent.ACTION_VIEW
        /*
         * Again, we create an Intent with the action, ACTION_VIEW because we want to VIEW the
         * contents of this Uri.
         */
        Intent intent = new Intent(Intent.ACTION_VIEW);

        // Set the data of the Intent to the Uri passed into this method
        /*
         * Using setData to set the Uri of this Intent has the exact same affect as passing it in
         * the Intent's constructor. This is simply an alternate way of doing this.
         */
        intent.setData(geoLocation);


        // Verify that this Intent can be launched and then call startActivity
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }

    public void onClickOpenAdoptButton(View v) {
        //  String that contains a URL ( make sure it starts with http:// or https:// )
        String urlAsString = getString(R.string.adopt_url);

        // call to openWebPage, passing in the URL String from the previous step
        openWebPage(urlAsString);

    }


    private void openWebPage(String url) {
        // Use Uri.parse to parse the String into a Uri
        /*
         * We wanted to demonstrate the Uri.parse method because its usage occurs frequently. You
         * could have just as easily passed in a Uri as the parameter of this method.
         */
        Uri webpage = Uri.parse(url);

        //  Create an Intent with Intent.ACTION_VIEW and the webpage Uri as parameters
        /*
         * Here, we create the Intent with the action of ACTION_VIEW. This action allows the user
         * to view particular content. In this case, our webpage URL.
         */
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        // Verify that this Intent can be launched and then call startActivity
        /*
         * This is a check we perform with every implicit Intent that we launch. In some cases,
         * the device where this code is running might not have an Activity to perform the action
         * with the data we've specified. Without this check, in those cases your app would crash.
         */
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
