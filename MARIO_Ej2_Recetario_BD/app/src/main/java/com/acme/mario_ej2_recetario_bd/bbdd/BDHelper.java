package com.acme.mario_ej2_recetario_bd.bbdd;

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
    public static final String DATABASE_NAME = "Recetario.sqlite";

    public BDHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataBaseManagerCategorias.SQL_CREATE_ENTRIES);
        db.execSQL(DataBaseManagerIngredientes.SQL_CREATE_ENTRIES);
        db.execSQL(DataBaseManagerRecetas.SQL_CREATE_ENTRIES);
        db.execSQL(DataBaseManagerUsuarios.SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseManagerUsuarios.TABLE_NAME_USUARIOS);
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseManagerRecetas.TABLE_NAME_RECETAS);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
