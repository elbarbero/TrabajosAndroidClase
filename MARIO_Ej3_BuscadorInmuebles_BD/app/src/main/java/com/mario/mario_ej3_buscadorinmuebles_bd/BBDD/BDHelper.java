package com.mario.mario_ej3_buscadorinmuebles_bd.BBDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.mario.mario_ej3_buscadorinmuebles_bd.VISTA.FragmentoDetalle;
import com.mario.mario_ej3_buscadorinmuebles_bd.VISTA.FragmentoLista;

/**
 * Created by Mario on 20/01/2018.
 */

 public class BDHelper extends SQLiteOpenHelper {
    SQLiteDatabase db;

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Buscador.sqlite";

    public BDHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataBaseManagerZonas.SQL_CREATE_ENTRIES);
        db.execSQL(DataBaseManagerInmueble.SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DataBaseManagerZonas.TABLE_NAME_ZONAS);
        db.execSQL("DROP TABLE IF EXISTS "+DataBaseManagerInmueble.TABLE_NAME_INMUEBLES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


}
