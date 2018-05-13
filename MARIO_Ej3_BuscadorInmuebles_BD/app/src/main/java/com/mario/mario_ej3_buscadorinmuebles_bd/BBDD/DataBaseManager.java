package com.mario.mario_ej3_buscadorinmuebles_bd.BBDD;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public abstract class DataBaseManager {

    private BDHelper helper;
    private SQLiteDatabase db;

    public DataBaseManager(Context ctx) {
        //AMBOS ME DEVUELVEN UN OBJETO DE TIPO DATABASE
        helper= new BDHelper(ctx);
        db=helper.getWritableDatabase(); //ESTO YA INCLUYE TAMBIÉN EL MODO LECTURA
    }

    public void cerrar(){
        db.close();
    }

    abstract public void eliminar(String id);
    abstract public void eliminarTodo();
    abstract public Cursor cargarCursor();
    abstract Boolean compruebaRegistro(String id);
    abstract public void insertarTodosEnBD();

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
