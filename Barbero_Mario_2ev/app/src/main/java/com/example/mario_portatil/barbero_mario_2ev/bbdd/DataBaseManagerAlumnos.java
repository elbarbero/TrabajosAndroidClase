package com.example.mario_portatil.barbero_mario_2ev.bbdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.mario_portatil.barbero_mario_2ev.controlador.OperacionesDatos;
import com.example.mario_portatil.barbero_mario_2ev.modelo.Alumno;
import com.example.mario_portatil.barbero_mario_2ev.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class DataBaseManagerAlumnos extends DataBaseManager {

    List<Alumno> listAlumno;

    public static final String TABLE_NAME_ALUMNOS = "Alumnos";
    public static final String ALU_ID = "_id";
    public static final String ALU_NOMBRE = "nombre";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME_ALUMNOS + " (" +
                    ALU_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    ALU_NOMBRE + " VARCHAR(40) NOT NULL UNIQUE)";

    public DataBaseManagerAlumnos(Context ctx) {
        super(ctx);
    }


    @Override
    public void cerrar() {
        super.getDb().close();
    }


    public void insertarTodosEnBD() {

        OperacionesDatos.agragarAlumnosAlArray();
        for (int i = 0; i < OperacionesDatos.misAlumnos.size(); i++) {

            ContentValues values = new ContentValues();
            values.put(ALU_NOMBRE, OperacionesDatos.misAlumnos.get(i).getNombre());
            this.getDb().insert(TABLE_NAME_ALUMNOS, null, values);
        }
    }

    @Override
    public void eliminar(String id) {
//SIEMPRE UN ARRAY DE STRINGS-->lo mismo con el update
        super.getDb().delete(TABLE_NAME_ALUMNOS, ALU_ID + "=?", new String[]{id});
    }

    @Override
    public void eliminarTodo() {

        super.getDb().execSQL("DELETE FROM " + TABLE_NAME_ALUMNOS + ";");
        Log.d("Alumnos_eliminar", "Datos borrados");

    }

    @Override
    public Cursor cargarCursor() {
        String[] columnas = new String[]{ALU_ID, ALU_NOMBRE};

        return super.getDb().query(TABLE_NAME_ALUMNOS, columnas, null, null, null, null, null);
    }

    @Override
    public Boolean compruebaRegistro(String id) {

        boolean esta = true;
        Cursor resultSet = super.getDb().rawQuery("Select * from " + TABLE_NAME_ALUMNOS + " WHERE " + ALU_ID + "=" + id, null);

        if (resultSet.getCount() <= 0)
            esta = false;
        else
            esta = true;

        return esta;
    }

    public List<Alumno> obtenerNombresAlumnos() {

        listAlumno = new ArrayList<>();
        String sql = "SELECT " + ALU_NOMBRE + " from  " + TABLE_NAME_ALUMNOS;
        Cursor c = this.getDb().rawQuery(sql, null);
        while (c.moveToNext()) {
            Alumno miAlumno = new Alumno();
            miAlumno.setNombre(c.getString(c.getColumnIndex("nombre")));
            listAlumno.add(miAlumno);
        }
        return listAlumno;
    }

    public List<Alumno> obtenerUnAlumnoPorNombre(String nombre) {

        String sql = "";
        listAlumno = new ArrayList<>();

        sql = "SELECT * from  " + TABLE_NAME_ALUMNOS + " where " + ALU_NOMBRE + " =" + "'" + nombre.trim() + "'";

        Cursor c = this.getDb().rawQuery(sql, null);

        if (c.moveToFirst()) {
            Alumno miAlumno = new Alumno();
            miAlumno.setId(c.getInt(c.getColumnIndex(ALU_ID)));
            miAlumno.setNombre(c.getString(c.getColumnIndex(ALU_NOMBRE)));
            listAlumno.add(miAlumno);
        }
        return listAlumno;
    }

    public List<Alumno> obtenerAlumnos() {

        String sql = "";
        listAlumno = new ArrayList<>();

        sql = "SELECT * from  " + TABLE_NAME_ALUMNOS + " order by " + ALU_NOMBRE + " ASC";

        Cursor c = this.getDb().rawQuery(sql, null);

        while (c.moveToNext()) {
            Alumno miAlumno = new Alumno();
            miAlumno.setId(c.getInt(c.getColumnIndex(ALU_ID)));
            miAlumno.setNombre(c.getString(c.getColumnIndex(ALU_NOMBRE)));
            listAlumno.add(miAlumno);
        }
        return listAlumno;
    }

}
