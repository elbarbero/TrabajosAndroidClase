package com.mario.mario_ej3_buscadorinmuebles_bd.BBDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.mario.mario_ej3_buscadorinmuebles_bd.CONTROLADOR.OperacionesDatos;

public class DataBaseManagerZonas extends DataBaseManager {
    public static final String TABLE_NAME_ZONAS = "Zonas";
    public static final String TABLE_NAME_INMUEBLES = "Inmuebles";
    public static final String ZON_ID = "_id";
    public static final String ZON_DENOM = "denominacion";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME_ZONAS + " (" +
                    ZON_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    ZON_DENOM + " TEXT)";

    public DataBaseManagerZonas(Context ctx) {
        super(ctx);
    }


    @Override
    public void cerrar() {
        super.getDb().close();
    }


    public void insertarTodosEnBD() {

        for (int i = 0; i < OperacionesDatos.misZonas.size(); i++) {
// Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(ZON_DENOM, OperacionesDatos.misZonas.get(i).getDenominacion());

            this.getDb().insert(TABLE_NAME_ZONAS, null, values);
        }
    }

    @Override
    public void eliminar(String id) {
//SIEMPRE UN ARRAY DE STRINGS-->lo mismo con el update
        super.getDb().delete(TABLE_NAME_ZONAS, ZON_ID + "=?", new String[]{id});
    }

    @Override
    public void eliminarTodo() {

        super.getDb().execSQL("DELETE FROM " + TABLE_NAME_ZONAS + ";");
        Log.d("Zonas_eliminar", "Datos borrados");

    }

    @Override
    public Cursor cargarCursor() {
        return null;
    }

    @Override
    public Boolean compruebaRegistro(String id) {

        boolean esta = true;

        Cursor resultSet = super.getDb().rawQuery("Select * from " + TABLE_NAME_ZONAS + " WHERE " + ZON_ID + "=" + id, null);

        if (resultSet.getCount() <= 0)
            esta = false;
        else
            esta = true;

        return esta;

    }

}
