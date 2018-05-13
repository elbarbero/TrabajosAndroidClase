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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataBaseManagerCasos extends DataBaseManager {

    List<Caso> listCasos;

    public static final String TABLE_NAME_CASOS = "Casos";

    public static final String CAS_ID = "_id";
    public static final String CAS_DENOMINACION = "denominacion";
    public static final String CAS_FECHAAPERTURA = "fecha_apertura";
    public static final String CAS_CARACTERISTICAS = "caracteristicas";
    public static final String CAS_ABOGADO = "abogado_responsable";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME_CASOS + " (" +
                    CAS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    CAS_DENOMINACION + " VARCHAR(20) NOT NULL," +
                    CAS_FECHAAPERTURA + " TIMESTAMP," +
                    CAS_CARACTERISTICAS + " VARCHAR(100)," +
                    CAS_ABOGADO + " VARCHAR(10))";

    public DataBaseManagerCasos(Context ctx) {
        super(ctx);
    }

    @Override
    public void cerrar() {
        super.getDb().close();
    }

    public void insertarTodosEnBD() {
        // Gets the data repository in write mode
        OperacionesDatos.agregarCasosAlArray();

        for (int i = 0; i < OperacionesDatos.misCasos.size(); i++) {

            ContentValues values = new ContentValues();
            values.put(CAS_DENOMINACION, OperacionesDatos.misCasos.get(i).getDenominacion());
            values.put(CAS_FECHAAPERTURA, OperacionesDatos.misCasos.get(i).getFechaApertura());
            values.put(CAS_CARACTERISTICAS, OperacionesDatos.misCasos.get(i).getCaracteristicas());
            values.put(CAS_ABOGADO, OperacionesDatos.misCasos.get(i).getAbogado().getNumColegiado());

            long newRowId = this.getDb().insert(TABLE_NAME_CASOS, null, values);

        }
    }

    public long insertarUnCasoEnBD(String denominacion, String fechaApertura, String caracteristicas, String numColegiado) {
        ContentValues values = new ContentValues();
        values.put(CAS_DENOMINACION, denominacion);
        values.put(CAS_FECHAAPERTURA, String.valueOf(fechaApertura));
        values.put(CAS_CARACTERISTICAS, caracteristicas);
        values.put(CAS_ABOGADO, numColegiado);

        long newRowId = this.getDb().insert(TABLE_NAME_CASOS, null, values);
        return newRowId;
    }

    public List<Caso> buscarCasoPorId(int id) {
        String sql = "";
        listCasos = new ArrayList<>();

        sql = "SELECT * from  " + TABLE_NAME_CASOS + " where " + CAS_ID + " =" + id;

        Cursor c = this.getDb().rawQuery(sql, null);

        if (c.moveToFirst()) {
            Caso miCaso = new Caso();

            miCaso.setId(c.getInt(c.getColumnIndex("_id")));//AQUI LE DECIMOS QUE RECUPERE EL VALOR DE DE LA COLUMNA QUE SE LLAMA ASI
            miCaso.setDenominacion(c.getString(c.getColumnIndex("denominacion")));
            miCaso.setFechaApertura(c.getString(c.getColumnIndex("fecha")));
            miCaso.setCaracteristicas(c.getString(c.getColumnIndex("caracteristicas")));
            miCaso.setAbogado(new Abogado(c.getInt(c.getColumnIndex("abogado_responsable"))));
            listCasos.add(miCaso);
        }
        return listCasos;
    }

    public List<Caso> buscarCasoPorAbogado(String numColegiado) {
        String sql = "";
        listCasos = new ArrayList<>();
      /*  SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date d=new Date();*/

        sql = "SELECT * from  " + TABLE_NAME_CASOS + " where " + CAS_ABOGADO + " =" + "'" + numColegiado + "'";

        Cursor c = this.getDb().rawQuery(sql, null);

        while (c.moveToNext()) {
            Caso miCaso = new Caso();

            miCaso.setId(c.getInt(c.getColumnIndex("_id")));//AQUI LE DECIMOS QUE RECUPERE EL VALOR DE DE LA COLUMNA QUE SE LLAMA ASI
            miCaso.setDenominacion(c.getString(c.getColumnIndex("denominacion")));
            miCaso.setFechaApertura(c.getString(c.getColumnIndex("fecha_apertura")));
            miCaso.setCaracteristicas(c.getString(c.getColumnIndex("caracteristicas")));
            miCaso.setAbogado(new Abogado(c.getInt(c.getColumnIndex("abogado_responsable"))));
            listCasos.add(miCaso);
        }
        return listCasos;
    }


    @Override
    public void eliminar(String id) {
//SIEMPRE UN ARRAY DE STRINGS-->lo mismo con el update
        super.getDb().delete(TABLE_NAME_CASOS, CAS_ID + "=?", new String[]{id});
    }

    @Override
    public void eliminarTodo() {

        super.getDb().execSQL("DELETE FROM " + TABLE_NAME_CASOS + ";");
        Log.d("Casos_eliminar", "Datos borrados");

    }

    @Override
    public Boolean compruebaRegistro(String id) {

        boolean esta = true;
        Cursor resultSet = super.getDb().rawQuery("Select * from " + TABLE_NAME_CASOS + " WHERE " + CAS_ID + "=" + id, null);
        if (resultSet.getCount() <= 0) {
            esta = false;
        }else {
            esta = true;
        }
        return esta;
    }

    public Cursor cargarCursor() {
        String[] columnas = new String[]{CAS_ID, CAS_ABOGADO,CAS_CARACTERISTICAS, CAS_FECHAAPERTURA, CAS_DENOMINACION};

        return super.getDb().query(TABLE_NAME_CASOS, columnas, null, null, null, null, null);
    }


    public List<Caso> obtenerCasosDeBD() {

        listCasos = new ArrayList<>();
        Cursor c = cargarCursor();
   /*     SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date d=new Date();*/

        while (c.moveToNext()) {
            Caso miCaso = new Caso();
            miCaso.setId(c.getInt(c.getColumnIndex("_id")));//AQUI LE DECIMOS QUE RECUPERE EL VALOR DE DE LA COLUMNA QUE SE LLAMA ASI
            miCaso.setDenominacion(c.getString(c.getColumnIndex("denominacion")));
            miCaso.setFechaApertura(c.getString(c.getColumnIndex("fecha_apertura")));
            miCaso.setCaracteristicas(c.getString(c.getColumnIndex("caracteristicas")));
            miCaso.setAbogado(new Abogado(c.getInt(c.getColumnIndex("abogado_responsable"))));
            listCasos.add(miCaso);
        }
        return listCasos;
    }

    public List<Caso> obtenerNombresCasos() {

        listCasos = new ArrayList<>();
        String sql = "SELECT " + CAS_DENOMINACION + " from  " + TABLE_NAME_CASOS;
        Cursor c = this.getDb().rawQuery(sql, null);
        while (c.moveToNext()) {
            Caso miCaso = new Caso();
            miCaso.setDenominacion(c.getString(c.getColumnIndex("denominacion")));
            listCasos.add(miCaso);
        }
        return listCasos;
    }

    public List<Caso> obtenerUnCasoPorNombre(String denominacion) {

        String sql = "";
        listCasos = new ArrayList<>();

        sql = "SELECT * from  " + TABLE_NAME_CASOS + " where " + CAS_DENOMINACION + " =" + "'" + denominacion.trim() + "'";

        Cursor c = this.getDb().rawQuery(sql, null);

        if (c.moveToFirst()) {
            Caso miCaso = new Caso();

            miCaso.setId(c.getInt(c.getColumnIndex(CAS_ID)));//AQUI LE DECIMOS QUE RECUPERE EL VALOR DE DE LA COLUMNA QUE SE LLAMA ASI
            miCaso.setDenominacion(c.getString(c.getColumnIndex(CAS_DENOMINACION)));
            miCaso.setFechaApertura(c.getString(c.getColumnIndex(CAS_FECHAAPERTURA)));
            miCaso.setCaracteristicas(c.getString(c.getColumnIndex(CAS_CARACTERISTICAS)));
            miCaso.setAbogado(new Abogado(c.getString(c.getColumnIndex(CAS_ABOGADO))));
            listCasos.add(miCaso);
        }
        return listCasos;
    }
}
