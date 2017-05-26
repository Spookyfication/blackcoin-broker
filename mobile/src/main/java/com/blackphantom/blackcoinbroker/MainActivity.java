package com.blackphantom.blackcoinbroker;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView TV_BlkKurse;
    private TextView TV_Marktkap;
    private TextView TV_BlkAenderung;
    private TextView TV_BlkAenderungEuro;
    private TextView TV_Euro;
    private TextView TV_Percent;
    private TextView TV_Uhrzeit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Funtion to add your Wallet will be added later", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        TV_BlkKurse = (TextView) findViewById(R.id.TV_BlkKurs);
        TV_Marktkap = (TextView) findViewById(R.id.TV_Marktkap);
        TV_BlkAenderung = (TextView) findViewById(R.id.TV_BlkAenderung);
        TV_BlkAenderungEuro = (TextView) findViewById(R.id.TV_BlkAenderungEuro);
        TV_Euro = (TextView) findViewById(R.id.TV_Euro);
        TV_Percent = (TextView) findViewById(R.id.TV_Percent);
        TV_Uhrzeit = (TextView) findViewById(R.id.TV_Uhrzeit);
        AktualisiereKurse aktKurse = new AktualisiereKurse(this, TV_BlkKurse, TV_Marktkap, TV_BlkAenderung, TV_BlkAenderungEuro, TV_Euro, TV_Percent, TV_Uhrzeit);
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
            AktualisiereKurse aktKurse = new AktualisiereKurse(this, TV_BlkKurse, TV_Marktkap, TV_BlkAenderung, TV_BlkAenderungEuro, TV_Euro, TV_Percent, TV_Uhrzeit);
            aktKurse.execute();
        }

        return super.onOptionsItemSelected(item);
    }
}
