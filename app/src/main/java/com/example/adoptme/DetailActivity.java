package com.example.adoptme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
}
