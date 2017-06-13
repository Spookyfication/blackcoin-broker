package com.blackphantom.blackcoinbroker;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by BPS on 13.06.2017.
 */

public class DepotArrayAdapter extends ArrayAdapter<Depot> {
    private Context context;
    private List<Depot> depots;

    public DepotArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Depot> objects) {
        super(context, resource, objects);

        this.context = context;
        this.depots = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Depot depot = depots.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.depot_layout, null);

        TextView TV_Actual_Price = (TextView) view.findViewById(R.id.TV_Actual_Price);
        TextView TV_Amount = (TextView) view.findViewById(R.id.TV_Amount);
        TextView TV_Datum = (TextView) view.findViewById(R.id.TV_Datum);
        TextView TV_Kaufpreis = (TextView) view.findViewById(R.id.TV_Kaufpreis);

        String actual_price = String.valueOf(depot.getWertBlackcoins()).substring(0, String.valueOf(depot.getWertBlackcoins()).lastIndexOf(".")+2);
        TV_Actual_Price.setText(actual_price+"€");
        TV_Amount.setText(String.valueOf(depot.getAnzahlBlackcoins()));
        //TV_Kaufpreis.setText(String.valueOf(depot.getKaufpreis()));
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        TV_Datum.setText(format.format(depot.getDatum()));

        return view;
    }
}
