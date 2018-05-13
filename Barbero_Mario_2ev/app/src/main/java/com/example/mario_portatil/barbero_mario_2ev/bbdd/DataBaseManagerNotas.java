package com.example.mario_portatil.barbero_mario_2ev.bbdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.mario_portatil.barbero_mario_2ev.controlador.OperacionesDatos;
import com.example.mario_portatil.barbero_mario_2ev.modelo.Alumno;
import com.example.mario_portatil.barbero_mario_2ev.modelo.Notas;

import java.util.ArrayList;
import java.util.List;

public class DataBaseManagerNotas extends DataBaseManager {

    List<Notas> listnotas;

    public static final String TABLE_NAME_NOTAS = "Notas";
    public static final String NOT_ID = "_id";
    public static final String NOT_CODALUMNO = "cod_alumno";
    public static final String NOT_FECHACONTROL = "fecha_control";
    public static final String NOT_CALIFICACION = "calificacion";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME_NOTAS + " (" +
                    NOT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    NOT_CODALUMNO + " INTEGER NOT NULL," +
                    NOT_FECHACONTROL + " TIMESTAMP,"+
                    NOT_CALIFICACION + " FLOAT)";

    public DataBaseManagerNotas(Context ctx) {
        super(ctx);
    }


    @Override
    public void cerrar() {
        super.getDb().close();
    }


    public void insertarTodosEnBD() {

        OperacionesDatos.agregarNotasAlArray();
        for (int i = 0; i < OperacionesDatos.misNotas.size(); i++) {

            ContentValues values = new ContentValues();
            values.put(NOT_CODALUMNO, OperacionesDatos.misNotas.get(i).getAlumno().getId());
            values.put(NOT_FECHACONTROL, OperacionesDatos.misNotas.get(i).getFechaControl());
            values.put(NOT_CALIFICACION, OperacionesDatos.misNotas.get(i).getCalificacion());

            this.getDb().insert(TABLE_NAME_NOTAS, null, values);
        }
    }

    @Override
    public void eliminar(String id) {
//SIEMPRE UN ARRAY DE STRINGS-->lo mismo con el update
        super.getDb().delete(TABLE_NAME_NOTAS, NOT_ID + "=?", new String[]{id});
    }

    @Override
    public void eliminarTodo() {

        super.getDb().execSQL("DELETE FROM " + TABLE_NAME_NOTAS + ";");
        Log.d("Notas_eliminar", "Datos borrados");

    }

    @Override
    public Cursor cargarCursor() {
        String[] columnas = new String[]{NOT_ID, NOT_CODALUMNO, NOT_FECHACONTROL, NOT_CALIFICACION};

        return super.getDb().query(TABLE_NAME_NOTAS, columnas, null, null, null, null, null);
    }

    @Override
    Boolean compruebaRegistro(String id) {
        return null;
    }

    public long insertarUnaNotaEnBD(int idAlumno , String fechaControl, float nota) {
        ContentValues values = new ContentValues();
        values.put(NOT_CODALUMNO, idAlumno);
        values.put(NOT_FECHACONTROL, fechaControl);
        values.put(NOT_CALIFICACION, nota);

        long newRowId = this.getDb().insert(TABLE_NAME_NOTAS, null, values);
        return newRowId;
    }

    public boolean compararFechas(String fecha, int idalumno){
        String sql = "";
    boolean esta=false;
        sql = "SELECT * from " + TABLE_NAME_NOTAS +
                " where strftime('%Y-%m-%d', "+ NOT_FECHACONTROL + ")"
                + " =" + "'" + fecha.trim() + "'" + " and " + NOT_CODALUMNO + "=" + "'" + idalumno + "'" ;
        Cursor c = this.getDb().rawQuery(sql, null);
        if (c.getCount() <= 0)
            esta = false;
        else
            esta = true;
        return esta;
    }

    public List<Notas> obtenerNotasAlumno(int idAlumno){
        String sql = "";
        listnotas=new ArrayList<>();

        sql = "SELECT _id, calificacion from " + TABLE_NAME_NOTAS +
                " where " + NOT_CODALUMNO + "="+ idAlumno;

        Cursor c = this.getDb().rawQuery(sql, null);
        while (c.moveToNext()) {
            Notas misNotas = new Notas();
          //  Alumno a=new Alumno(c.getInt(c.getColumnIndex(NOT_CODALUMNO)));
           // misNotas.setAlumno(a);
            misNotas.setId(c.getInt(c.getColumnIndex(NOT_ID)));
            misNotas.setCalificacion(c.getFloat(c.getColumnIndex(NOT_CALIFICACION)));
            listnotas.add(misNotas);
        }
        return listnotas;
    }

}
