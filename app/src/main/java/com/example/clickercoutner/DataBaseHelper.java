package com.example.clickercoutner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.google.android.material.tabs.TabLayout;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME_AND_EXT = "ClickApp.db";
    public static final String ID = "id";
    public static final String CLICKS_TABLE = "clicksTable";
    public static final String NUMBER_OF_CLICKS = "numberOfClicks";
    public static final String TIME = "Time";
    public static final String IS_HIGHEST = "isHighest";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME_AND_EXT, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String clickTable = "CREATE TABLE " + CLICKS_TABLE + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NUMBER_OF_CLICKS + " INTEGER NOT NULL, "
                + TIME + " TEXT NOT NULL, "
                + IS_HIGHEST + " BOOL NOT NULL" + ")";

        db.execSQL(clickTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertRecord(Clicks click ) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(NUMBER_OF_CLICKS, click.getCounterClicks());
        cv.put(TIME, click.getTime());
        cv.put(IS_HIGHEST, click.isHighest());

        boolean inserted = db.insert(CLICKS_TABLE, null, cv) > 0;

        Log.d("sql database", String.valueOf(inserted));
    }

    public int getHighestScore() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + CLICKS_TABLE + " WHERE " + IS_HIGHEST + "= 1", null);

        int clicks = 0;

        while(cursor.moveToNext())
            clicks = cursor.getInt(1);

        return clicks;


    }
}
