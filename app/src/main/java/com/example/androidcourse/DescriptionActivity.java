package com.example.androidcourse;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import androidx.appcompat.app.AppCompatActivity;

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

        final String urlString = "http://www.apilayer.net/api/live?access_key=YOUR_API_KEY";

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                String jsonResponse = null;
                try {
                    jsonResponse = getResponseFromHttpUrl(urlString);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.i("JSONRESSS", jsonResponse);
                return jsonResponse;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(DescriptionActivity.this, s, Toast.LENGTH_LONG).show();
            }
        }.execute();
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param uriString The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */

    public static String getResponseFromHttpUrl(String uriString) throws IOException {
        //The uri provided as string is converted to a URL object
        URL url = new URL(uriString);
        //An HttpURLConnection object is created with the url
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        //Here we try to read the stream or download that is coming from the api provided
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else return null;
        } finally {
            //finally, we should disconnect connection to the url when we have finished downloading
            urlConnection.disconnect();
        }
    }

}
