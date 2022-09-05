package com.example.clickercoutner;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.sql.SQLData;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME_AND_EXT = "ClickApp.db";
    public static final String ID = "id";

    public DataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME_AND_EXT, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String clickTable =
                ID + " INT AUTOINCREAMENT PRIMARYKEY";

        db.execSQL(clickTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
