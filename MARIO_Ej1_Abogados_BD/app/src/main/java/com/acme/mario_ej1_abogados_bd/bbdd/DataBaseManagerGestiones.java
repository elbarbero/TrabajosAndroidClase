package com.acme.mario_ej1_abogados_bd.bbdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.acme.mario_ej1_abogados_bd.controlador.OperacionesDatos;
import com.acme.mario_ej1_abogados_bd.modelo.Abogado;
import com.acme.mario_ej1_abogados_bd.modelo.Caso;
import com.acme.mario_ej1_abogados_bd.modelo.Gestion;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DataBaseManagerGestiones extends DataBaseManager {

    List<Gestion> listGestiones;

    public static final String TABLE_NAME_GESTIONES = "Gestiones";

    public static final String GES_ID = "_id";
    public static final String GES_CODCASO = "codigo_caso";
    public static final String GES_FECHA = "fecha";
    public static final String GES_DESCRIPCION = "descripcion";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME_GESTIONES + " (" +
                    GES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    GES_CODCASO + " INTEGER NOT NULL," +
                    GES_FECHA + " TIMESTAMP," +
                    GES_DESCRIPCION + " VARCHAR(100) NOT NULL UNIQUE)";

    public DataBaseManagerGestiones(Context ctx) {
        super(ctx);
    }

    @Override
    public void cerrar() {
        super.getDb().close();
    }

    public void insertarTodosEnBD(){
        OperacionesDatos.agregarGestionesAlArray();

        for (int i = 0; i < OperacionesDatos.misGestiones.size(); i++) {

            ContentValues values = new ContentValues();
            values.put(GES_CODCASO, OperacionesDatos.misGestiones.get(i).getCaso().getId());
            values.put(GES_FECHA, OperacionesDatos.misGestiones.get(i).getFecha());
            values.put(GES_DESCRIPCION, OperacionesDatos.misGestiones.get(i).getDescripcion());

            this.getDb().insert(TABLE_NAME_GESTIONES, null, values);
        }
    }

    public long insertarUnaGestionEnBD(int idCaso, String fecha, String descripcion) {

        ContentValues values = new ContentValues();
        values.put(GES_CODCASO, idCaso);
        values.put(GES_FECHA, fecha);
        values.put(GES_DESCRIPCION, descripcion);

        long newRowId = this.getDb().insert(TABLE_NAME_GESTIONES, null, values);
        return newRowId;
    }

    public List<Gestion> buscarGestionPorId(int id){
        String sql = "";
        listGestiones = new ArrayList<>();

        sql = "SELECT * from  " + TABLE_NAME_GESTIONES + " where " + GES_ID + " =" + id;
        Cursor c = this.getDb().rawQuery(sql, null);

        if (c.moveToFirst()) {
            Gestion miGestion = new Gestion();
            miGestion.setId(c.getInt(c.getColumnIndex("_id")));//AQUI LE DECIMOS QUE RECUPERE EL VALOR DE DE LA COLUMNA QUE SE LLAMA ASI
            miGestion.setCaso(new Caso(c.getInt(c.getColumnIndex("codigo_caso"))));
            miGestion.setFecha(c.getString(c.getColumnIndex("fecha")));
            miGestion.setDescripcion(c.getString(c.getColumnIndex("descripcion")));
            listGestiones.add(miGestion);
        }
        return listGestiones;
    }

    public List<Gestion> buscarGestionPorCaso(int codCaso) {
        String sql = "";
        listGestiones = new ArrayList<>();
        sql = "SELECT * from  " + TABLE_NAME_GESTIONES + " where " + GES_CODCASO + " =" + "'" + codCaso + "'";
        Cursor c = this.getDb().rawQuery(sql, null);

        while (c.moveToNext()) {
            Gestion miGestion = new Gestion();

            miGestion.setId(c.getInt(c.getColumnIndex("_id")));//AQUI LE DECIMOS QUE RECUPERE EL VALOR DE DE LA COLUMNA QUE SE LLAMA ASI
            miGestion.setCaso(new Caso(c.getInt(c.getColumnIndex("codigo_caso"))));
            miGestion.setFecha(c.getString(c.getColumnIndex("fecha")));
            miGestion.setDescripcion(c.getString(c.getColumnIndex("descripcion")));
            listGestiones.add(miGestion);
        }
        return listGestiones;
    }

    @Override
    public void eliminar(String id) {
//SIEMPRE UN ARRAY DE STRINGS-->lo mismo con el update
        super.getDb().delete(TABLE_NAME_GESTIONES, GES_ID + "=?", new String[]{id});
    }

    @Override
    public void eliminarTodo() {

        super.getDb().execSQL("DELETE FROM " + TABLE_NAME_GESTIONES + ";");
        Log.d("Gestiones_eliminar", "Datos borrados");

    }

    @Override
    public Boolean compruebaRegistro(String id) {

        boolean esta = true;
        Cursor resultSet = super.getDb().rawQuery("Select * from " + TABLE_NAME_GESTIONES + " WHERE " + GES_ID + "=" + id, null);
        if (resultSet.getCount() <= 0)
            esta = false;
        else
            esta = true;

        return esta;
    }

    public Cursor cargarCursor() {
        String[] columnas = new String[]{GES_ID, GES_CODCASO,GES_FECHA, GES_DESCRIPCION};

        return super.getDb().query(TABLE_NAME_GESTIONES, columnas, null, null, null, null, null);
    }


    public List<Gestion> obtenerGestionesDeBD() {

        listGestiones = new ArrayList<>();
        Cursor c = cargarCursor();

        while (c.moveToNext()) {
            Gestion miGestion = new Gestion();

            miGestion.setId(c.getInt(c.getColumnIndex("_id")));//AQUI LE DECIMOS QUE RECUPERE EL VALOR DE DE LA COLUMNA QUE SE LLAMA ASI
            miGestion.setCaso(new Caso(c.getInt(c.getColumnIndex("codigo_caso"))));
            miGestion.setFecha(c.getString(c.getColumnIndex("fecha")));
            miGestion.setDescripcion(c.getString(c.getColumnIndex("descripcion")));
            listGestiones.add(miGestion);
        }
        return listGestiones;
    }

}
