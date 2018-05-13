package com.acme.mario_ej2_recetario_bd.bbdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.acme.mario_ej2_recetario_bd.controlador.OperacionesDatos;
import com.acme.mario_ej2_recetario_bd.modelo.Ingredientes;

import java.util.List;

public class DataBaseManagerIngredientes extends DataBaseManager {

    List<Ingredientes> listIngredientes;

    public static final String TABLE_NAME_INGREDIENTES = "Ingredientes";

    public static final String ING_ID = "_id";
    public static final String ING_NOMBRE = "nombre";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME_INGREDIENTES + " (" +
                    ING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    ING_NOMBRE + " VARCHAR(50))";

    public DataBaseManagerIngredientes(Context ctx) {
        super(ctx);
    }

    @Override
    public void cerrar() {
        super.getDb().close();
    }

    public void insertarTodosEnBD() {
        // Gets the data repository in write mode
        OperacionesDatos.agregarIngredientesAlArray();

        for (int i = 0; i < OperacionesDatos.misIngredientes.size(); i++) {
// Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            // values.put(EstructuraBBDD.CAT_ID, i);
            values.put(ING_NOMBRE, OperacionesDatos.misIngredientes.get(i).getNombre());

            long newRowId = this.getDb().insert(TABLE_NAME_INGREDIENTES, null, values);
        }
    }

    @Override
    public void eliminar(String id) {
//SIEMPRE UN ARRAY DE STRINGS-->lo mismo con el update
        super.getDb().delete(TABLE_NAME_INGREDIENTES, ING_ID + "=?", new String[]{id});
    }

    @Override
    public void eliminarTodo() {

        super.getDb().execSQL("DELETE FROM " + TABLE_NAME_INGREDIENTES + ";");
        Log.d("Ingredientes_eliminar", "Datos borrados");

    }

    @Override
    public Boolean compruebaRegistro(String id) {

        boolean esta = true;
        Cursor resultSet = super.getDb().rawQuery("Select * from " + TABLE_NAME_INGREDIENTES + " WHERE " + ING_NOMBRE + "=" + id, null);
        if (resultSet.getCount() <= 0)
            esta = false;
        else
            esta = true;

        return esta;
    }

    public Boolean compruebaRegistroPorNombre(String nombre) {
        boolean esta = true;
        String[] ingredients = nombre.split(",");

        if (nombre.compareTo("") == 0) {
            esta = false;
        } else {

            for (int i = 0; i < ingredients.length; i++) {

                Cursor resultSet = super.getDb().rawQuery("Select * from " + TABLE_NAME_INGREDIENTES + " WHERE " + ING_NOMBRE + "=" + "'" + ingredients[i].trim() + "'", null);
                if (resultSet.getCount() <= 0) {
                    esta = false;

                } else {
                    esta = true;
                }
            }
        }
        return esta;
    }

    public Cursor cargarCursor() {
        String[] columnas = new String[]{ING_ID, ING_NOMBRE};

        return super.getDb().query(TABLE_NAME_INGREDIENTES, columnas, null, null, null, null, null);
    }

}
