package com.example.androidcourse;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CountryRecyclerAdapter extends RecyclerView.Adapter<CountryRecyclerAdapter.CountryViewHolder> {
    private ArrayList<Country> countryArrayList = new ArrayList<Country>();
    private Context context;

    public CountryRecyclerAdapter(Context context, ArrayList<Country> countryArrayList){
        this.context = context.getApplicationContext();
        this.countryArrayList = countryArrayList;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.list_view;

        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        CountryRecyclerAdapter.CountryViewHolder viewHolder = new CountryRecyclerAdapter.CountryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {

        final Country country =countryArrayList.get(position);
        if (country != null){
            holder.countryFlagView.setImageResource(country.getCountryFlag());
            holder.countryNameView.setText(country.getCountryName());
            holder.linearLayout.setOnClickListener( new View.OnClickListener (){
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
    }

    @Override
    public int getItemCount() {
        return countryArrayList.size();
    }


    public class CountryViewHolder extends RecyclerView.ViewHolder{

        TextView countryNameView;
        ImageView countryFlagView;
        LinearLayout linearLayout;


        public CountryViewHolder(View itemView){
            super(itemView);

            countryNameView = itemView.findViewById(R.id.country_name);
            countryFlagView = itemView.findViewById(R.id.country_flag);
            linearLayout = itemView.findViewById(R.id.list_layout);
        }
    }
}
