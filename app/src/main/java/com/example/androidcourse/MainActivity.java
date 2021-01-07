package com.example.androidcourse;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    //private ListView listView;
    //private GridView gridView;
    private RecyclerView recyclerView;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "I am starting", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "I am resuming", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.google.com"));
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "I am going to pause for now", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "The app is getting destroyed", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        if (sharedPreferences != null){
            int visitCode = 0;
            visitCode = sharedPreferences.getInt("VisitCode", 0);
            if (visitCode == 1){
                Toast.makeText(this, "You are an existing user", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Welcome New User", Toast.LENGTH_SHORT).show();
            }
        }

        //listView = findViewById(R.id.list);
        //gridView = findViewById(R.id.list);
        recyclerView = findViewById(R.id.list);

        ArrayList<Country> countries = new ArrayList<>();
        countries.add(new Country("Nigeria", R.drawable.nigeria, "Nigeria is a multi-cultural country with over 100 languages and dialects. It has a  population of 209 million", "NGN"));
        countries.add(new Country("Ghana", R.drawable.ghana, "Ghana is a country in West Africa region colonised by The Great Britain", "GHS"));
        countries.add(new Country("Egypt", R.drawable.egypt, "Egypt is a country geographically apportioned to Africa, but political, it is Arab", "EGP"));
        countries.add(new Country("Japan", R.drawable.japan, "Japan is a country in the far east Asia, third world best economy", "JPY"));
        countries.add(new Country("India", R.drawable.india, "India, a nuclear power country situated in Asia", "INR"));
        countries.add(new Country("China", R.drawable.china, "China is a country in Asia, second world best economy", "CNY"));
        countries.add(new Country("Australia", R.drawable.australia, "Aussies are known to be the largest country in Australasia", "AUD"));
        countries.add(new Country("Portugal", R.drawable.portugal, "This was once a great power in Europe, people there are called Portuguese", "EUR"));
        countries.add(new Country("The USA", R.drawable.usa, "The United State of America is the world leader in terms of military, economy, education among many others", "USD"));
        countries.add(new Country("NewZealand", R.drawable.new_zealand, "NewZealand is a native English speaking country with one of the world best economy as Australia", "NZD"));


        //CountryAdapter countryAdapter = new CountryAdapter(this, countries);
        //listView.setAdapter(countryAdapter);
        //gridView.setAdapter(countryAdapter);

        CountryRecyclerAdapter countryRecyclerAdapter = new CountryRecyclerAdapter(this, countries);
        //GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        //recyclerView.setLayoutManager(gridLayoutManager);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(countryRecyclerAdapter);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("VisitCode", 1);
        //editor.putString("VisitCode", "Existing User");
        //editor.putBoolean("VisitCode", true);
        editor.apply();
    }
}
