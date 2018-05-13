package com.acme.mario_ej1_abogados_bd.bbdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mario on 20/01/2018.
 */

public class BDHelper extends SQLiteOpenHelper {
    SQLiteDatabase db;

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Bufete.sqlite";

    public BDHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataBaseManagerAbogados.SQL_CREATE_ENTRIES);
        db.execSQL(DataBaseManagerCasos.SQL_CREATE_ENTRIES);
        db.execSQL(DataBaseManagerGestiones.SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseManagerAbogados.TABLE_NAME_ABOGADOS);
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseManagerCasos.TABLE_NAME_CASOS);
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseManagerGestiones.TABLE_NAME_GESTIONES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
