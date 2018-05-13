package com.example.mario_portatil.barbero_mario_2ev.bbdd;

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
    public static final String DATABASE_NAME = "Profesor.sqlite";

    public BDHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataBaseManagerUsuarios.SQL_CREATE_ENTRIES);
        db.execSQL(DataBaseManagerNotas.SQL_CREATE_ENTRIES);
        db.execSQL(DataBaseManagerAlumnos.SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseManagerUsuarios.TABLE_NAME_USUARIOS);
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseManagerNotas.TABLE_NAME_NOTAS);
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseManagerAlumnos.TABLE_NAME_ALUMNOS);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
