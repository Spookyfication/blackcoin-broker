package com.blackphantom.blackcoinbroker;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by BPS on 25.05.2017.
 */

public class AktualisiereKurse extends AsyncTask<String, Integer, String[]> {
    private final String LOG_TAG = AktualisiereKurse.class.getSimpleName();
    private Context context;
    private TextView TV_BlkKurs, TV_Marktkap, TV_BlkAenderung;

    public AktualisiereKurse(Context context, TextView TV_BlkKurs, TextView TV_Marktkap, TextView TV_BlkAenderung){
        this.context = context;
        this.TV_BlkKurs = TV_BlkKurs;
        this.TV_Marktkap = TV_Marktkap;
        this.TV_BlkAenderung = TV_BlkAenderung;
    }

    @Override
    protected String[] doInBackground(String... strings) {
        String anfrageString = "https://api.coinmarketcap.com/v1/ticker/blackcoin/?convert=EUR";
        HttpURLConnection httpURLConnection = null;
        BufferedReader bufferedReader = null;

        String kurseString = "";
        try{
            URL url = new URL(anfrageString);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();

            if (inputStream == null) {
                return null;
            }
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                kurseString += line + "\n";
            }
            if (kurseString.length() == 0) {
                return null;
            }
            Log.v(LOG_TAG, "Aktiendaten XML-String: " + kurseString);
            publishProgress(1,1);
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }

        String BlkKurs = kurseString;
        String[] returnString = {BlkKurs};
        return returnString;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {

    }

    @Override
    protected void onPostExecute(String[] strings) {
        try {
            JSONArray jArray = new JSONArray(strings[0]);

            JSONObject jObject = jArray.getJSONObject(0);

            String kursEuro = jObject.getString("price_eur");
            String kursDollar = jObject.getString("price_usd");
            String marktkapitalisierungEuro = jObject.getString("market_cap_eur");
            String marktkapitalisierungDollar = jObject.getString("market_cap_usd");
            String aenderung24 = jObject.getString("percent_change_24h");
            TV_BlkKurs.setText(kursEuro.substring(0,7)+" €/BLK");
            TV_Marktkap.setText("Marktkapitalisierung: "+marktkapitalisierungEuro+"€");
            if(aenderung24.charAt(0) == '-'){
                TV_BlkAenderung.setTextColor(Color.RED);
            }else{
                TV_BlkAenderung.setTextColor(Color.GREEN);
            }
            TV_BlkAenderung.setText(aenderung24+"%");
        } catch (JSONException e) {
            Log.e("JSONException", "Error: " + e.toString());
        }
    }
}
