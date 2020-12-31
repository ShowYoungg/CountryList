package com.example.androidcourse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        TextView description = findViewById(R.id.description);
        ImageView flagImage = findViewById(R.id.flag);

        Intent intent = getIntent();
        String descriptionText = intent.getStringExtra("Description");
        int flag = intent.getIntExtra("Flag", R.drawable.usa);

        description.setText(descriptionText);
        flagImage.setImageResource(flag);
    }
}
