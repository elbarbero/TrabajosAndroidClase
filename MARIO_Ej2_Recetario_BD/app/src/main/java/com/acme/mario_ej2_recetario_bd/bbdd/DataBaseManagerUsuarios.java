package com.acme.mario_ej2_recetario_bd.bbdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.acme.mario_ej2_recetario_bd.controlador.OperacionesDatos;
import com.acme.mario_ej2_recetario_bd.modelo.Usuario;

import java.util.List;

public class DataBaseManagerUsuarios extends DataBaseManager {

    List<Usuario> listUsuarios;

    public static final String TABLE_NAME_USUARIOS = "Usuarios";
    public static final String USER_ID = "_id";
    public static final String USER_NOMBRE = "nombre";
    public static final String USER_LOGIN = "login";
    public static final String USER_CONTRASENA = "password";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME_USUARIOS + " (" +
                    USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    USER_NOMBRE + " VARCHAR(100) NOT NULL UNIQUE," +
                    USER_LOGIN + " VARCHAR(10) NOT NULL," +
                    USER_CONTRASENA + " VARCHAR(8) NOT NULL)";

    public DataBaseManagerUsuarios(Context ctx) {
        super(ctx);
    }


    @Override
    public void cerrar() {
        super.getDb().close();
    }


    public void insertarTodosEnBD() {

        OperacionesDatos.agregarUsuariosAlArray();
        for (int i = 0; i < OperacionesDatos.misUsuarios.size(); i++) {

            ContentValues values = new ContentValues();
            values.put(USER_NOMBRE, OperacionesDatos.misUsuarios.get(i).getNombre());
            values.put(USER_LOGIN, OperacionesDatos.misUsuarios.get(i).getNick());
            values.put(USER_CONTRASENA, OperacionesDatos.misUsuarios.get(i).getContrasena());

            this.getDb().insert(TABLE_NAME_USUARIOS, null, values);
        }
    }

    @Override
    public void eliminar(String id) {
//SIEMPRE UN ARRAY DE STRINGS-->lo mismo con el update
        super.getDb().delete(TABLE_NAME_USUARIOS, USER_ID + "=?", new String[]{id});
    }

    @Override
    public void eliminarTodo() {

        super.getDb().execSQL("DELETE FROM " + TABLE_NAME_USUARIOS + ";");
        Log.d("Zonas_eliminar", "Datos borrados");

    }

    @Override
    public Cursor cargarCursor() {
        String[] columnas = new String[]{USER_ID, USER_NOMBRE, USER_LOGIN, USER_CONTRASENA};

        return super.getDb().query(TABLE_NAME_USUARIOS, columnas, null, null, null, null, null);
    }

    @Override
    public Boolean compruebaRegistro(String id) {

        boolean esta = true;
        Cursor resultSet = super.getDb().rawQuery("Select * from " + TABLE_NAME_USUARIOS + " WHERE " + USER_ID + "=" + id, null);

        if (resultSet.getCount() <= 0)
            esta = false;
        else
            esta = true;

        return esta;
    }

    public Boolean buscarUsuarioPorLogin(String login, String pass) {

        boolean esta = true;
        Cursor resultSet = super.getDb().rawQuery("Select * from " + TABLE_NAME_USUARIOS + " WHERE " + USER_LOGIN + "=" + "'" + login + "'" + " and " + USER_CONTRASENA + "=" + "'" + pass + "'", null);

        if (resultSet.getCount() <= 0)
            esta = false;
        else
            esta = true;

        return esta;
    }

}
