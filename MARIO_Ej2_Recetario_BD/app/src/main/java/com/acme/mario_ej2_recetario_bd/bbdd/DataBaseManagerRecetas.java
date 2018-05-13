package com.acme.mario_ej2_recetario_bd.bbdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.acme.mario_ej2_recetario_bd.controlador.OperacionesDatos;
import com.acme.mario_ej2_recetario_bd.modelo.Categoria;
import com.acme.mario_ej2_recetario_bd.modelo.Ingredientes;
import com.acme.mario_ej2_recetario_bd.modelo.Receta;

import java.util.ArrayList;
import java.util.List;

public class DataBaseManagerRecetas extends DataBaseManager {

    List<Receta> listRecetas;

    public static final String TABLE_NAME_RECETAS = "Recetas";

    public static final String REC_ID = "_id";
    public static final String REC_IDCATEGORIA = "id_Categoria";
    public static final String REC_NOMBRE = "nombre";
    public static final String REC_INGREDIENTES = "ingredientes";
    public static final String REC_RUTAIMG = "ruta_imagen";
    public static final String REC_TIEMPOCOCCION = "tiempo_coccion";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME_RECETAS + " (" +
                    REC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    REC_IDCATEGORIA + " INTEGER NOT NULL," +
                    REC_NOMBRE + " VARCHAR(52) NOT NULL UNIQUE," +
                    REC_INGREDIENTES + " TEXT NOT NULL," +
                    REC_RUTAIMG + " INTEGER," +
                    REC_TIEMPOCOCCION + " INTEGER)";

    public DataBaseManagerRecetas(Context ctx) {
        super(ctx);
    }

    @Override
    public void cerrar() {
        super.getDb().close();
    }

    public void insertarTodosEnBD() {
        // Gets the data repository in write mode
        OperacionesDatos.agregarRecetasAlArray();
        String nombreIngredientes = "";

        for (int i = 0; i < OperacionesDatos.misRecetas.size(); i++) {
// Create a new map of values, where column names are the keys
            nombreIngredientes = "";
            ContentValues values = new ContentValues();
            // values.put(EstructuraBBDD.CAT_ID, i);
            values.put(REC_IDCATEGORIA, OperacionesDatos.misRecetas.get(i).getCategoria().getId());
            values.put(REC_NOMBRE, OperacionesDatos.misRecetas.get(i).getNombre());
            for (int a = 0; a < OperacionesDatos.misRecetas.get(i).getMisIngredientes().size(); a++) {
                nombreIngredientes = nombreIngredientes + ", " + OperacionesDatos.misRecetas.get(i).getMisIngredientes().get(a).getNombre();
            }
            values.put(REC_INGREDIENTES, nombreIngredientes);
            values.put(REC_RUTAIMG, OperacionesDatos.misRecetas.get(i).getFoto());
            values.put(REC_TIEMPOCOCCION, OperacionesDatos.misRecetas.get(i).getTiempo());

            long newRowId = this.getDb().insert(TABLE_NAME_RECETAS, null, values);

        }
    }

    public void insertarUnaRecetaEnBD(int idcategoria, String nombre, ArrayList<Ingredientes> misingredientes, int imagen, int tiempoCoccion) {
        // Gets the data repository in write mode
        String nombreIngredientes = "";
        Categoria cat = new Categoria(idcategoria);

        nombreIngredientes = "";
        ContentValues values = new ContentValues();
        // values.put(EstructuraBBDD.CAT_ID, i);
        values.put(REC_IDCATEGORIA, cat.getId());
        values.put(REC_NOMBRE, nombre);
        for (int a = 0; a < misingredientes.size(); a++) {
            nombreIngredientes = nombreIngredientes + ", " + misingredientes.get(a).getNombre();
        }
        values.put(REC_INGREDIENTES, nombreIngredientes);
        if (imagen == 0) {

        } else {
            values.put(REC_RUTAIMG, imagen);
        }
        values.put(REC_TIEMPOCOCCION, tiempoCoccion);

        long newRowId = this.getDb().insert(TABLE_NAME_RECETAS, null, values);

    }

    public List<Receta> buscarRecetaPorCualquierCriterio(int posSpinner, int categoria, String ingrediente, int tiempo, int id) {

        String sql = "";
        listRecetas = new ArrayList<>();

        if (posSpinner == 0) {
            sql = "SELECT * from  " + TABLE_NAME_RECETAS + " order by " + REC_NOMBRE + " ASC";
        } else if (posSpinner == 1) {
            sql = "SELECT * from  " + TABLE_NAME_RECETAS + " where " + REC_IDCATEGORIA + " =" + "'" + categoria + "'";
        } else if (posSpinner == 2) {
            sql = "SELECT * from  " + TABLE_NAME_RECETAS + " where " + REC_ID + " =" + id;
        } else if (posSpinner == 3) {
            sql = "SELECT * from  " + TABLE_NAME_RECETAS + " where " + REC_TIEMPOCOCCION + " =" + tiempo;
        } else if (posSpinner == 4) {
            sql = "SELECT * from  " + TABLE_NAME_RECETAS + " where " + REC_INGREDIENTES + " like " + "'%" + ingrediente + "%'";
        }

        Cursor c = this.getDb().rawQuery(sql, null);

        while (c.moveToNext()) {
            Receta miReceta = new Receta();

            ArrayList<Ingredientes> misIngredientes = new ArrayList<>();

            miReceta.setId(c.getInt(c.getColumnIndex("_id")));//AQUI LE DECIMOS QUE RECUPERE EL VALOR DE DE LA COLUMNA QUE SE LLAMA ASI
            miReceta.setCategoria(new Categoria(c.getInt(c.getColumnIndex("id_Categoria"))));
            miReceta.setNombre(c.getString(c.getColumnIndex("nombre")));
            String[] ingredients = c.getString(c.getColumnIndex("ingredientes")).split(",");

            for (int i = 1; i < ingredients.length; i++) {
                misIngredientes.add(new Ingredientes(ingredients[i]));
            }

            miReceta.setMisIngredientes(misIngredientes);
            miReceta.setFoto(c.getInt(c.getColumnIndex("ruta_imagen")));
            miReceta.setTiempo(c.getInt(c.getColumnIndex("tiempo_coccion")));
            listRecetas.add(miReceta);
        }
        return listRecetas;
    }

    public List<Receta> buscarRecetaPorID(int id) {

        String sql = "";
        listRecetas = new ArrayList<>();

        sql = "SELECT * from  " + TABLE_NAME_RECETAS + " where " + REC_ID + " =" + id;

        Cursor c = this.getDb().rawQuery(sql, null);

        while (c.moveToNext()) {
            Receta miReceta = new Receta();

            ArrayList<Ingredientes> misIngredientes = new ArrayList<>();

            miReceta.setId(c.getInt(c.getColumnIndex("_id")));//AQUI LE DECIMOS QUE RECUPERE EL VALOR DE DE LA COLUMNA QUE SE LLAMA ASI
            miReceta.setCategoria(new Categoria(c.getInt(c.getColumnIndex("id_Categoria"))));
            miReceta.setNombre(c.getString(c.getColumnIndex("nombre")));
            String[] ingredients = c.getString(c.getColumnIndex("ingredientes")).split(",");

            for (int i = 1; i < ingredients.length; i++) {
                misIngredientes.add(new Ingredientes(ingredients[i]));
            }

            miReceta.setMisIngredientes(misIngredientes);
            miReceta.setFoto(c.getInt(c.getColumnIndex("ruta_imagen")));
            miReceta.setTiempo(c.getInt(c.getColumnIndex("tiempo_coccion")));
            listRecetas.add(miReceta);
        }
        return listRecetas;
    }


    @Override
    public void eliminar(String id) {
//SIEMPRE UN ARRAY DE STRINGS-->lo mismo con el update
        super.getDb().delete(TABLE_NAME_RECETAS, REC_ID + "=?", new String[]{id});
    }

    @Override
    public void eliminarTodo() {

        super.getDb().execSQL("DELETE FROM " + TABLE_NAME_RECETAS + ";");
        Log.d("Recetas_eliminar", "Datos borrados");

    }

    @Override
    public Boolean compruebaRegistro(String id) {

        boolean esta = true;
        Cursor resultSet = super.getDb().rawQuery("Select * from " + TABLE_NAME_RECETAS + " WHERE " + REC_ID + "=" + id, null);
        if (resultSet.getCount() <= 0)
            esta = false;
        else
            esta = true;

        return esta;
    }

    public Boolean compruebaRegistroPorNombre(String nombre) {

        boolean esta = true;
        Cursor resultSet = super.getDb().rawQuery("Select * from " + TABLE_NAME_RECETAS + " WHERE " + REC_ID + "=" + "'" + nombre + "'", null);
        if (resultSet.getCount() <= 0)
            esta = false;
        else
            esta = true;

        return esta;
    }

    public Cursor cargarCursor() {
        String[] columnas = new String[]{REC_ID, REC_IDCATEGORIA, REC_NOMBRE, REC_INGREDIENTES, REC_RUTAIMG, REC_TIEMPOCOCCION};

        return super.getDb().query(TABLE_NAME_RECETAS, columnas, null, null, null, null, null);
    }


    public List<Receta> obtenerRecetasDeBD() {

        listRecetas = new ArrayList<>();
        Cursor c = cargarCursor();

        while (c.moveToNext()) {
            Receta miReceta = new Receta();
            ArrayList<Ingredientes> misIngredientes = new ArrayList<>();

            miReceta.setId(c.getInt(c.getColumnIndex("_id")));//AQUI LE DECIMOS QUE RECUPERE EL VALOR DE DE LA COLUMNA QUE SE LLAMA ASI
            miReceta.setCategoria(new Categoria(c.getInt(c.getColumnIndex("id_Categoria"))));
            miReceta.setNombre(c.getString(c.getColumnIndex("nombre")));
            String[] ingredients = c.getString(c.getColumnIndex("ingredientes")).split(",");

            for (int i = 1; i < ingredients.length; i++) {
                misIngredientes.add(new Ingredientes(ingredients[i]));
            }

            miReceta.setMisIngredientes(misIngredientes);
            miReceta.setFoto(c.getInt(c.getColumnIndex("ruta_imagen")));
            miReceta.setTiempo(c.getInt(c.getColumnIndex("tiempo_coccion")));

            listRecetas.add(miReceta);
        }
        return listRecetas;
    }

}
