package com.example.edu.sqliteprzyklad;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class dbAdapter extends RecyclerView.Adapter<dbAdapter.ViewHolder>{

    private final List<COVIDData> covidList;

    public dbAdapter(List<COVIDData>  covidData) {
        covidList = covidData;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.sample_data, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(dbAdapter.ViewHolder holder, int position) {
        COVIDData covidData = covidList.get(position);

        holder.countryTextView.setText("Country: "+covidData.getCountry());
        holder.totalSlashActiveTextView.setText(" Cases/Active: "+covidData.getCases()+"/"+covidData.getActive());
        holder.casesPerOneMillionTextView.setText(" cPerOneM: "+covidData.getCasesPerOneMillion());
        holder.testsPerMillionTextView.setText(" tPerOneM: "+covidData.getTestsPerOneMillion());
    }

    @Override
    public int getItemCount() {
        return covidList.size();
    }





    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView countryTextView;
        public TextView totalSlashActiveTextView;
        public TextView casesPerOneMillionTextView;
        public TextView testsPerMillionTextView;



        public ViewHolder(View itemView) {

            super(itemView);

            countryTextView = (TextView) itemView.findViewById(R.id.country);
            totalSlashActiveTextView = (TextView) itemView.findViewById(R.id.cases);

            casesPerOneMillionTextView = (TextView) itemView.findViewById(R.id.casesPerMillion);
            testsPerMillionTextView = (TextView) itemView.findViewById(R.id.testesPerMillion);
        }
    }



}
