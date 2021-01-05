package com.example.androidcourse;


import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    }
}
