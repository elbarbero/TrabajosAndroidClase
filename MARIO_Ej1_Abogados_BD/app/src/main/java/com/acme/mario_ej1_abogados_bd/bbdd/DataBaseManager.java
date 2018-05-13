package com.acme.mario_ej1_abogados_bd.bbdd;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;

public abstract class DataBaseManager {

    private BDHelper helper;
    private SQLiteDatabase db;

    public DataBaseManager(Context ctx) {
        helper= new BDHelper(ctx);
        db=helper.getWritableDatabase();
    }

    public void cerrar(){
        db.close();
    }

    abstract public void eliminar(String id);
    abstract public void eliminarTodo();
    abstract public Cursor cargarCursor();
    abstract Boolean compruebaRegistro(String id);

    abstract public void insertarTodosEnBD() throws ParseException;

    /**
     * GET Y SET
     * @return
     */
    public BDHelper getHelper() {
        return helper;
    }

    public void setHelper(BDHelper helper) {
        this.helper = helper;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }
}
