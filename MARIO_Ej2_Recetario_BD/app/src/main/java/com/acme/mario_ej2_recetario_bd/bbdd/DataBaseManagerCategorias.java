package com.acme.mario_ej2_recetario_bd.bbdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.acme.mario_ej2_recetario_bd.controlador.OperacionesDatos;
import com.acme.mario_ej2_recetario_bd.modelo.Categoria;

import java.util.ArrayList;
import java.util.List;

public class DataBaseManagerCategorias extends DataBaseManager {

    List<Categoria> listCategorias;

    public static final String TABLE_NAME_CATEGORIAS = "Categorias";

    public static final String CAT_ID = "_id";
    public static final String CAT_DENOMINACION = "nombre";
    public static final String CAT_RUTAIMG = "ruta_imagen";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME_CATEGORIAS + " (" +
                    CAT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    CAT_DENOMINACION + " VARCHAR(20) NOT NULL UNIQUE," +
                    CAT_RUTAIMG + " INTEGER)";

    public DataBaseManagerCategorias(Context ctx) {
        super(ctx);
    }

    @Override
    public void cerrar() {
        super.getDb().close();
    }

    public void insertarTodosEnBD() {
        // Gets the data repository in write mode
        OperacionesDatos.agregarCategoriasAlArray();

        for (int i = 0; i < OperacionesDatos.misCategorias.size(); i++) {

            ContentValues values = new ContentValues();
            // values.put(EstructuraBBDD.CAT_ID, i);
            values.put(CAT_DENOMINACION, OperacionesDatos.misCategorias.get(i).getDenominacion());
            values.put(CAT_RUTAIMG, OperacionesDatos.misCategorias.get(i).getImagen());

            long newRowId = this.getDb().insert(TABLE_NAME_CATEGORIAS, null, values);
        }

    }

    public List<Categoria> buscarCategoriaPorID(int id) {

        String sql = "";
        listCategorias = new ArrayList<>();

        sql = "SELECT * from  " + TABLE_NAME_CATEGORIAS + " where " + CAT_ID + " =" + id;

        Cursor c = this.getDb().rawQuery(sql, null);

        while (c.moveToNext()) {
            Categoria miCategoria = new Categoria();

            miCategoria.setId(c.getInt(c.getColumnIndex("_id")));//AQUI LE DECIMOS QUE RECUPERE EL VALOR DE DE LA COLUMNA QUE SE LLAMA ASI
            miCategoria.setDenominacion(c.getString(c.getColumnIndex("nombre")));
            miCategoria.setImagen(c.getInt(c.getColumnIndex("ruta_imagen")));

            listCategorias.add(miCategoria);
        }
        return listCategorias;
    }

    @Override
    public void eliminar(String id) {
//SIEMPRE UN ARRAY DE STRINGS-->lo mismo con el update
        super.getDb().delete(TABLE_NAME_CATEGORIAS, CAT_ID + "=?", new String[]{id});
    }

    @Override
    public void eliminarTodo() {

        super.getDb().execSQL("DELETE FROM " + TABLE_NAME_CATEGORIAS + ";");
        Log.d("Categorias_eliminar", "Datos borrados");

    }

    @Override
    public Boolean compruebaRegistro(String id) {

        boolean esta = true;

        if (id.compareTo("") == 0) {
            esta = false;
        } else {

            Cursor resultSet = super.getDb().rawQuery("Select * from " + TABLE_NAME_CATEGORIAS + " WHERE " + CAT_ID + "=" + id, null);
            if (resultSet.getCount() <= 0)
                esta = false;
            else
                esta = true;
        }

        return esta;
    }

    public Cursor cargarCursor() {
        String[] columnas = new String[]{CAT_ID, CAT_DENOMINACION, CAT_RUTAIMG};

        return super.getDb().query(TABLE_NAME_CATEGORIAS, columnas, null, null, null, null, null);
    }
}
