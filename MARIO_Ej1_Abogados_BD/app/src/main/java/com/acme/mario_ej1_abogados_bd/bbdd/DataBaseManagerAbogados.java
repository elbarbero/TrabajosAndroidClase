package com.acme.mario_ej1_abogados_bd.bbdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.acme.mario_ej1_abogados_bd.controlador.OperacionesDatos;
import com.acme.mario_ej1_abogados_bd.modelo.Abogado;

import java.util.ArrayList;
import java.util.List;

public class DataBaseManagerAbogados extends DataBaseManager {

    List<Abogado> listAbogados;

    public static final String TABLE_NAME_ABOGADOS = "Abogados";

    public static final String ABG_ID = "_id";
    public static final String ABG_NUMCOLEGIADO = "num_colegiado";
    public static final String ABG_NOMBRE = "nombre";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME_ABOGADOS + " (" +
                    ABG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    ABG_NUMCOLEGIADO + " VARCHAR(10) NOT NULL UNIQUE," +
                    ABG_NOMBRE + " VARCHAR(40) NOT NULL UNIQUE)";

    public DataBaseManagerAbogados(Context ctx) {
        super(ctx);
    }

    @Override
    public void cerrar() {
        super.getDb().close();
    }

    public void insertarTodosEnBD() {
        // Gets the data repository in write mode
        OperacionesDatos.agregarAbogadosAlArray();

        for (int i = 0; i < OperacionesDatos.misAbogados.size(); i++) {
// Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(ABG_NOMBRE, OperacionesDatos.misAbogados.get(i).getNombre());
            values.put(ABG_NUMCOLEGIADO, OperacionesDatos.misAbogados.get(i).getNumColegiado());

            long newRowId = this.getDb().insert(TABLE_NAME_ABOGADOS, null, values);

        }
    }

    public long insertarUnAbogadoEnBD(String nombre, String numColegiado) {

        ContentValues values = new ContentValues();
        values.put(ABG_NOMBRE, nombre);
        values.put(ABG_NUMCOLEGIADO, numColegiado);

        long newRowId = this.getDb().insert(TABLE_NAME_ABOGADOS, null, values);
        return newRowId;
    }

    public List<Abogado> obtenerUnAbogado(int id) {

        String sql = "";
        listAbogados = new ArrayList<>();

        sql = "SELECT * from  " + TABLE_NAME_ABOGADOS + " where " + ABG_ID + " =" + "'" + id + "'";

        Cursor c = this.getDb().rawQuery(sql, null);

        if (c.moveToFirst()) {
            Abogado miAbogado = new Abogado();

            miAbogado.setId(c.getInt(c.getColumnIndex("_id")));//AQUI LE DECIMOS QUE RECUPERE EL VALOR DE DE LA COLUMNA QUE SE LLAMA ASI
            miAbogado.setNombre(c.getString(c.getColumnIndex("nombre")));
            miAbogado.setNumColegiado(c.getString(c.getColumnIndex("num_colegiado")));
            listAbogados.add(miAbogado);
        }
        return listAbogados;
    }

    public List<Abogado> obtenerUnAbogadoPorNombre(String nombre) {

        String sql = "";
        listAbogados = new ArrayList<>();

        sql = "SELECT * from  " + TABLE_NAME_ABOGADOS + " where " + ABG_NOMBRE + " =" + "'" + nombre.trim() + "'";

        Cursor c = this.getDb().rawQuery(sql, null);

        if (c.moveToFirst()) {
            Abogado miAbogado = new Abogado();

            miAbogado.setId(c.getInt(c.getColumnIndex("_id")));//AQUI LE DECIMOS QUE RECUPERE EL VALOR DE DE LA COLUMNA QUE SE LLAMA ASI
            miAbogado.setNombre(c.getString(c.getColumnIndex("nombre")));
            miAbogado.setNumColegiado(c.getString(c.getColumnIndex("num_colegiado")));
            listAbogados.add(miAbogado);
        }
        return listAbogados;
    }

    public List<Abogado> buscarAbogadoPorNumColegiado(String numColegiado) {

        String sql = "";
        listAbogados = new ArrayList<>();

        sql = "SELECT * from  " + TABLE_NAME_ABOGADOS + " where " + ABG_NUMCOLEGIADO + " =" + "'" + numColegiado + "'";

        Cursor c = this.getDb().rawQuery(sql, null);

        if (c.moveToFirst()) {
            Abogado miAbogado = new Abogado();

            miAbogado.setId(c.getInt(c.getColumnIndex("_id")));//AQUI LE DECIMOS QUE RECUPERE EL VALOR DE DE LA COLUMNA QUE SE LLAMA ASI
            miAbogado.setNombre(c.getString(c.getColumnIndex("nombre")));
            miAbogado.setNumColegiado(c.getString(c.getColumnIndex("num_colegiado")));
            listAbogados.add(miAbogado);
        }
        return listAbogados;
    }

    @Override
    public void eliminar(String id) {
        super.getDb().delete(TABLE_NAME_ABOGADOS, ABG_ID + "=?", new String[]{id});
    }

    @Override
    public void eliminarTodo() {

        super.getDb().execSQL("DELETE FROM " + TABLE_NAME_ABOGADOS + ";");
        Log.d("Abogados_eliminar", "Datos borrados");

    }

    @Override
    public Boolean compruebaRegistro(String id) {

        boolean esta = true;
        Cursor resultSet = super.getDb().rawQuery("Select * from " + TABLE_NAME_ABOGADOS + " WHERE " + ABG_ID + "=" + id, null);
        if (resultSet.getCount() <= 0)
            esta = false;
        else
            esta = true;

        return esta;
    }

    public Boolean compruebaRegistroporNumColegiado(String numColegiado) {

        boolean esta = true;
        Cursor resultSet = super.getDb().rawQuery("Select * from " + TABLE_NAME_ABOGADOS + " WHERE " + ABG_NUMCOLEGIADO + "=" + "'" + numColegiado + "'", null);
        if (resultSet.getCount() <= 0)
            esta = false;
        else
            esta = true;

        return esta;
    }

    public Cursor cargarCursor() {
        String[] columnas = new String[]{ABG_ID, ABG_NUMCOLEGIADO, ABG_NOMBRE};

        return super.getDb().query(TABLE_NAME_ABOGADOS, columnas, null, null, null, null, null);
    }

    public List<Abogado> obtenerAbogadosDeBD() {

        listAbogados = new ArrayList<>();
        Cursor c = cargarCursor();

        while (c.moveToNext()) {
            Abogado miAbogado = new Abogado();

            miAbogado.setId(c.getInt(c.getColumnIndex("_id")));//AQUI LE DECIMOS QUE RECUPERE EL VALOR DE DE LA COLUMNA QUE SE LLAMA ASI
            miAbogado.setNombre(c.getString(c.getColumnIndex("nombre")));
            miAbogado.setNumColegiado(c.getString(c.getColumnIndex("num_colegiado")));
            listAbogados.add(miAbogado);
        }
        return listAbogados;
    }

    public int contarRegistros() {
        Cursor resultSet = super.getDb().rawQuery("Select * from " + TABLE_NAME_ABOGADOS, null);
        return resultSet.getCount();
    }

    public List<Abogado> obtenerNombresAbogados() {

        listAbogados = new ArrayList<>();
        String sql = "SELECT " + ABG_NOMBRE + " from  " + TABLE_NAME_ABOGADOS;
        Cursor c = this.getDb().rawQuery(sql, null);
        while (c.moveToNext()) {
            Abogado miAbogado = new Abogado();
            miAbogado.setNombre(c.getString(c.getColumnIndex("nombre")));
            listAbogados.add(miAbogado);
        }
        return listAbogados;
    }

}
