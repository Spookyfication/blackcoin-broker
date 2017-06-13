package com.blackphantom.blackcoinbroker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private TextView TV_BlkKurse;
    private TextView TV_Marktkap;
    private TextView TV_BlkAenderung;
    private TextView TV_BlkAenderungEuro;
    private TextView TV_Euro;
    private TextView TV_Percent;
    private TextView TV_Uhrzeit;
    private ListView LV_Depots;
    private DatabaseHandler db;
    private ArrayList<Depot> depots;
    private ArrayAdapter<Depot> LV_Depots_Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Context c = this;
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(c);
                builder.setTitle("Anzahl Bitcoins eingeben");
                final EditText input = new EditText(c);
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int blk = Integer.parseInt(input.getText().toString());
                        db = new DatabaseHandler(c);
                        db.addDepot(new Depot(blk, 0.0, new Date()));
                    }
                });
                builder.setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

        TV_BlkKurse = (TextView) findViewById(R.id.TV_BlkKurs);
        TV_Marktkap = (TextView) findViewById(R.id.TV_Marktkap);
        TV_BlkAenderung = (TextView) findViewById(R.id.TV_BlkAenderung);
        TV_BlkAenderungEuro = (TextView) findViewById(R.id.TV_BlkAenderungEuro);
        TV_Euro = (TextView) findViewById(R.id.TV_Euro);
        TV_Percent = (TextView) findViewById(R.id.TV_Percent);
        TV_Uhrzeit = (TextView) findViewById(R.id.TV_Uhrzeit);
        LV_Depots = (ListView) findViewById(R.id.LV_Depots);
        db = new DatabaseHandler(this);
        depots = db.getAllDepots();
        LV_Depots_Adapter = new DepotArrayAdapter(this, 0, depots);
        //ArrayAdapter<String> LV_Depots_Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        LV_Depots.setAdapter(LV_Depots_Adapter);



        AktualisiereKurse aktKurse = new AktualisiereKurse(this, TV_BlkKurse, TV_Marktkap, TV_BlkAenderung, TV_BlkAenderungEuro, TV_Euro, TV_Percent, TV_Uhrzeit, depots, LV_Depots_Adapter);
        aktKurse.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if(id == R.id.action_aktualisiere) {
            AktualisiereKurse aktKurse = new AktualisiereKurse(this, TV_BlkKurse, TV_Marktkap, TV_BlkAenderung, TV_BlkAenderungEuro, TV_Euro, TV_Percent, TV_Uhrzeit, depots, LV_Depots_Adapter);
            aktKurse.execute();
        }

        return super.onOptionsItemSelected(item);
    }
}
