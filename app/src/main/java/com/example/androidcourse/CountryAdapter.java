package com.example.androidcourse;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CountryAdapter extends ArrayAdapter<Country> {

    ArrayList<Country> countryArrayList = new ArrayList<>();
    Context context = getContext().getApplicationContext();

    public CountryAdapter(@NonNull Context context, ArrayList<Country> listOfCountries) {
        super(context, 0, listOfCountries);

        countryArrayList = listOfCountries;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final Country country = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view, parent, false);
        }

        TextView countryNameView = convertView.findViewById(R.id.country_name);
        ImageView countryFlagView = convertView.findViewById(R.id.country_flag);
        LinearLayout linearLayout = convertView.findViewById(R.id.list_layout);

        if (country != null) {
            countryNameView.setText(country.getCountryName());
            countryFlagView.setImageResource(country.getCountryFlag());

            linearLayout.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DescriptionActivity.class );
                    intent.putExtra("Description", country.getCountryDescription());
                    intent.putExtra("Flag", country.getCountryFlag());
                    intent.putExtra("Currency", country.getCurrency());
                    context.startActivity(intent);
                }
            });
        }

        return convertView;
    }
}
