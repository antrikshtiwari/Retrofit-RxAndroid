package com.example.sample.antriksh.retrofitrxandroidexample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sample.antriksh.retrofitrxandroidexample.R;
import com.example.sample.antriksh.retrofitrxandroidexample.api.Geoname;

import java.util.List;

public class GeoNamesAdapter extends
        RecyclerView.Adapter<GeoNamesAdapter.GeoNameViewHolder> {

    private List<Geoname> placesList;

    public GeoNamesAdapter(List<Geoname> placesList) {
        this.placesList = placesList;
    }

    @Override
    public int getItemCount() {

        if (placesList != null) {
            return placesList.size();
        } else {
            return 0;
        }
    }

    @Override
    public GeoNameViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.cardview_layout, viewGroup, false);

        return new GeoNameViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GeoNameViewHolder placesViewHolder, int position) {
        Geoname ci = placesList.get(position);
        placesViewHolder.country.setText(ci.getCountryName());
        placesViewHolder.continent.setText(ci.getContinentName());

        placesViewHolder.capital.setText(ci.getCapital());
        placesViewHolder.code.setText(ci.getCountryCode());

    }

    public class GeoNameViewHolder extends RecyclerView.ViewHolder {

        protected TextView country;
        protected TextView continent;
        protected TextView capital;
        protected TextView code;
        protected LinearLayout container;

        public GeoNameViewHolder(View v) {
            super(v);
            container = (LinearLayout) v.findViewById(R.id.item_layout_container);
            country = (TextView) v.findViewById(R.id.country);
            continent = (TextView) v.findViewById(R.id.continent);
            capital = (TextView) v.findViewById(R.id.capital);
            code = (TextView) v.findViewById(R.id.code);
        }
    }
}
