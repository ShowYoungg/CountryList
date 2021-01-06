package com.example.androidcourse;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DescriptionActivity extends AppCompatActivity {
    private String jsonResponse = "";
    private ArrayList<String[]> countryCurrency = new ArrayList<>();
    private String currencyPair;
    private TextView description;
    private ImageView flagImage;
    private ProgressBar progressBar;


    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        String textInsideDescriptionTextView = description.getText().toString();
        outState.putString("DescriptionTextView", textInsideDescriptionTextView);
        //outState.putString("CurrencyPair", currencyPair);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        description = findViewById(R.id.description);
        flagImage = findViewById(R.id.flag);
        progressBar = findViewById(R.id.progress_bar);

        Intent intent = getIntent();
        String descriptionText = intent.getStringExtra("Description");
        int flag = intent.getIntExtra("Flag", R.drawable.usa);
        String currency = intent.getStringExtra("Currency");
        currencyPair = "USD" + currency; //USD + NGN = USDNGN

        description.setText(descriptionText);
        flagImage.setImageResource(flag);

        final String urlString = "http://www.apilayer.net/api/live?access_key=YOUR_API_KEY";

        //        new AsyncTask<Void, Void, String>() {
//            @Override
//            protected String doInBackground(Void... voids) {
//                String jsonResponse = null;
//                try {
//                    jsonResponse = getResponseFromHttpUrl(urlString);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                Log.i("JSONRESSS", jsonResponse);
//                return jsonResponse;
//            }
//
//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//                Toast.makeText(DescriptionActivity.this, s, Toast.LENGTH_LONG).show();
//            }
//        }.execute();

        if (savedInstanceState == null) {

            final Handler handler = new Handler() {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    //super.handleMessage(msg);
                    if (msg.what == 0) {
                        //Toast.makeText(DescriptionActivity.this, jsonResponse, Toast.LENGTH_SHORT).show();
                        parseJSON(jsonResponse);

                    } else {
                        //showError();
                    }
                }
            };

            new Thread() {
                @Override
                public void run() {
                    try {
                        jsonResponse = getResponseFromHttpUrl(urlString);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.i("JSONRESSS", jsonResponse);
                    if (!jsonResponse.equals("")) {
                        handler.sendEmptyMessage(0);
                    } else {
                        handler.sendEmptyMessage(1);
                    }
                }
            }.start();

        } else {
            description.setText(savedInstanceState.getString("DescriptionText"));
        }


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

    private void parseJSON(String json) {
        try {
            JSONObject mainJSONObject = new JSONObject(json);
            boolean success = mainJSONObject.getBoolean("success");
            JSONObject quotes = mainJSONObject.getJSONObject("quotes");
            //Determine the exact currency pair
            //double USDAED = quotes.getDouble("USDAED");
            double ourRate = quotes.getDouble(currencyPair);
            description.append("\nThe currency rate of the country is " + currencyPair + ": " + ourRate);
            resultDialog();
            progressBar.setVisibility(View.GONE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void resultDialog() {
        new AlertDialog.Builder(DescriptionActivity.this)
                .setTitle(getResources().getString(R.string.app_name))
                .setMessage("The currency pair is " + currencyPair + " and will be appended to the description text")
                .setNegativeButton("DECLINE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //We will set description text empty if user declines
                        description.setText("");
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("ACCEPT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //If the user accept, dismiss the dialog bar.
                        dialog.dismiss();
                    }
                }).setCancelable(false).show();
    }


}
