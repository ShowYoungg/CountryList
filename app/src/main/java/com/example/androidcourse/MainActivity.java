package com.example.androidcourse;


import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //private ListView listView;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //listView = findViewById(R.id.list);
        gridView = findViewById(R.id.list);
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(new Country("Nigeria", R.drawable.nigeria, "Nigeria is a multi-cultural country with over 100 languages and dialects. It has a  population of 209 million"));
        countries.add(new Country("Ghana", R.drawable.ghana, "Ghana is a country in West Africa region colonised by The Great Britain"));
        countries.add(new Country("Egypt", R.drawable.egypt, "Egypt is a country geographically apportioned to Africa, but political, it is Arab"));
        countries.add(new Country("Japan", R.drawable.japan, "Japan is a country in the far east Asia, third world best economy"));
        countries.add(new Country("India", R.drawable.india, "India, a nuclear power country situated in Asia"));
        countries.add(new Country("China", R.drawable.china, "China is a country in Asia, second world best economy"));
        countries.add(new Country("Australia", R.drawable.australia, "Aussies are known to be the largest country in Australasia"));
        countries.add(new Country("Portugal", R.drawable.portugal, "This was once a great power in Europe, people there are called Portuguese"));
        countries.add(new Country("The USA", R.drawable.usa, "The United State of America is the world leader in terms of military, economy, education among many others"));
        countries.add(new Country("NewZealand", R.drawable.new_zealand, "NewZealand is a native English speaking country with one of the world best economy as Australia "));

        CountryAdapter countryAdapter = new CountryAdapter(this, countries);
        //listView.setAdapter(countryAdapter);
        gridView.setAdapter(countryAdapter);
    }
}
