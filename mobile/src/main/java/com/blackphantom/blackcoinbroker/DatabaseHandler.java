package com.blackphantom.blackcoinbroker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by BPS on 09.06.2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "blackcoinbroker";
    private static final String TABLE_DEPOTS = "depots";
    private static final String KEY_ID = "id";
    private static final String KEY_ANZAHL = "anzahlBlackcoins";
    private static final String KEY_KAUFPREIS = "kaufpreis";
    private static final String KEY_DATUM = "datum";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_DEPOT_TABLE = "CREATE TABLE " + TABLE_DEPOTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_ANZAHL + " FLOAT,"
                + KEY_KAUFPREIS + " FLOAT," + KEY_DATUM + " DATE" + ")";
        sqLiteDatabase.execSQL(CREATE_DEPOT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_DEPOTS);

        onCreate(sqLiteDatabase);
    }

    public void addDepot(Depot depot){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ANZAHL, depot.getAnzahlBlackcoins());
        values.put(KEY_KAUFPREIS, depot.getKaufpreis());
        values.put(KEY_DATUM, depot.getDatum().toString());
        db.insert(TABLE_DEPOTS, null, values);
        db.close();
    }

    public Depot getDepot(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DEPOTS, new String[] { KEY_ID,
                        KEY_ANZAHL, KEY_KAUFPREIS, KEY_DATUM }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Depot depot = null;
        try {
            depot = new Depot(Integer.parseInt(cursor.getString(0)),
                    Double.parseDouble(cursor.getString(1)), Double.parseDouble(cursor.getString(2)), format.parse(cursor.getString(3)));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return depot;
    }
}
