package com.mario.mario_ej3_buscadorinmuebles_bd.BBDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.mario.mario_ej3_buscadorinmuebles_bd.CONTROLADOR.OperacionesDatos;
import com.mario.mario_ej3_buscadorinmuebles_bd.MODELO.Inmueble;
import com.mario.mario_ej3_buscadorinmuebles_bd.MODELO.Zona;
import com.mario.mario_ej3_buscadorinmuebles_bd.VISTA.BusquedaInmuebles;

import java.util.ArrayList;
import java.util.List;

public class DataBaseManagerInmueble extends DataBaseManager {

    List<Inmueble> listInmueble;

    public static final String TABLE_NAME_INMUEBLES = "Inmuebles";

    public static final String CAT_ID = "_id";
    public static final String CAT_IDZONA = "id_Zona";
    public static final String CAT_UBICACION = "ubicacion";
    public static final String CAT_PRECIO = "precio";
    public static final String CAT_TIPO = "tipo_propiedad";
    public static final String CAT_NUMHABITA = "num_habitaciones";
    public static final String CAT_RUTAIMG = "ruta_imagen";
    public static final String CAT_NUMCONSULTAS = "num_consultas";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME_INMUEBLES + " (" +
                    CAT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    CAT_IDZONA + " INTEGER ,"+
                    CAT_UBICACION + " VARCHAR(100) NOT NULL," +
                    CAT_PRECIO + " DECIMAL(10,3)," +
                    CAT_TIPO + " INTEGER," +
                    CAT_NUMHABITA + " INTEGER," +
                    CAT_RUTAIMG + " INTEGER," +
                    CAT_NUMCONSULTAS + " INTEGER)";

    public DataBaseManagerInmueble(Context ctx) {
        super(ctx);
    }

    @Override
    public void cerrar() {
        super.getDb().close();
    }

    public void insertarTodosEnBD() {
        // Gets the data repository in write mode
        OperacionesDatos.agregarInmueblesAlArray();

        for (int i = 0; i < OperacionesDatos.misInmuebles.size(); i++) {

            ContentValues values = new ContentValues();

            values.put(CAT_IDZONA, OperacionesDatos.misInmuebles.get(i).getId_zona().getId());
            values.put(CAT_UBICACION, OperacionesDatos.misInmuebles.get(i).getUbicacion());
            values.put(CAT_PRECIO, OperacionesDatos.misInmuebles.get(i).getPrecio());
            values.put(CAT_TIPO, OperacionesDatos.misInmuebles.get(i).getTipo_propiedad());
            values.put(CAT_NUMHABITA, OperacionesDatos.misInmuebles.get(i).getNum_habitaciones());
            values.put(CAT_RUTAIMG, OperacionesDatos.misInmuebles.get(i).getRuta_imagen());
            values.put(CAT_NUMCONSULTAS, OperacionesDatos.misInmuebles.get(i).getNum_consultas());

            long newRowId = this.getDb().insert(TABLE_NAME_INMUEBLES, null, values);
        }

    }

    public List<Inmueble> consultarInmueblesMasVistos( int limit) {

        listInmueble= new ArrayList<>();
        String[] projection = {
                CAT_ID,
                CAT_IDZONA,
                CAT_UBICACION,
                CAT_PRECIO,
                CAT_TIPO,
                CAT_NUMHABITA,
                CAT_RUTAIMG,
                CAT_NUMCONSULTAS,
        };
        String selection = CAT_NUMCONSULTAS+" >= ?";
        String[] selectionArgs = {"0"};

        String sortOrder =
                CAT_NUMCONSULTAS + " DESC";

        Cursor c = this.getDb().query(
                TABLE_NAME_INMUEBLES,                     // The table to query
                projection,                               // The columns to return
                selection,                            // The columns for the WHERE clause
                selectionArgs,                         // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        for (int i = 0; i < limit; i++) {
            if (c.moveToPosition(i)) {

                Inmueble inmueble = new Inmueble();

                inmueble.setId(c.getInt(c.getColumnIndex("_id")));//AQUI LE DECIMOS QUE RECUPERE EL VALOR DE DE LA COLUMNA QUE SE LLAMA ASI
                inmueble.setId_zona(new Zona(c.getInt(c.getColumnIndex("id_Zona"))));
                inmueble.setUbicacion(c.getString(c.getColumnIndex("ubicacion")));
                inmueble.setPrecio(c.getDouble(c.getColumnIndex("precio")));
                inmueble.setTipo_propiedad(c.getInt(c.getColumnIndex("tipo_propiedad")));
                inmueble.setNum_habitaciones(c.getInt(c.getColumnIndex("num_habitaciones")));
                inmueble.setRuta_imagen(c.getInt(c.getColumnIndex("ruta_imagen")));
                inmueble.setNum_consultas(c.getInt(c.getColumnIndex("num_consultas")));
                listInmueble.add(inmueble);

            }
        }
        return listInmueble;
    }

    public void actualizarNumConsultas() {
        int numConsultasActualizado = 0;
        String sql;

        for (int i = 0; i <  listInmueble.size(); i++) {
            numConsultasActualizado =  listInmueble.get(i).getNum_consultas();
            numConsultasActualizado = numConsultasActualizado + 1;
            sql = "UPDATE Inmuebles SET num_consultas= " + numConsultasActualizado + " where _id= " + listInmueble.get(i).getId();

            this.getDb().execSQL(sql);
            listInmueble.get(i).setNum_consultas(numConsultasActualizado);//ACTUQALIZAMOS EL VALOR EN EL ARRAY
        }
    }

    public List<Inmueble>  buscarInmueblesPorPrecio(String precioMayor, String precioMenor) {
        listInmueble= new ArrayList<>();

        String sql = "SELECT * from Inmuebles where " + CAT_PRECIO + " >" + precioMenor + " and " + CAT_PRECIO + " <" + precioMayor;
        Cursor c = this.getDb().rawQuery(sql, null);

        while (c.moveToNext()) {

            Inmueble inmueble = new Inmueble();

            inmueble.setId(c.getInt(c.getColumnIndex("_id")));//AQUI LE DECIMOS QUE RECUPERE EL VALOR DE DE LA COLUMNA QUE SE LLAMA ASI
            inmueble.setId_zona(new Zona(c.getInt(c.getColumnIndex("id_Zona"))));
            inmueble.setUbicacion(c.getString(c.getColumnIndex("ubicacion")));
            inmueble.setPrecio(c.getDouble(c.getColumnIndex("precio")));
            inmueble.setTipo_propiedad(c.getInt(c.getColumnIndex("tipo_propiedad")));
            inmueble.setNum_habitaciones(c.getInt(c.getColumnIndex("num_habitaciones")));
            inmueble.setRuta_imagen(c.getInt(c.getColumnIndex("ruta_imagen")));
            inmueble.setNum_consultas(c.getInt(c.getColumnIndex("num_consultas")));
            listInmueble.add(inmueble);
        }
        BusquedaInmuebles.busqueda = true;
     return listInmueble;
    }

    public List<Inmueble>  buscarInmueblesPorTipo(int posRadioButon) {

        listInmueble= new ArrayList<>();

        String sql = "SELECT * from Inmuebles where " + CAT_TIPO + " =" + String.valueOf(posRadioButon);
        Cursor c = this.getDb().rawQuery(sql, null);

        while (c.moveToNext()) {
            Inmueble inmueble = new Inmueble();

            inmueble.setId(c.getInt(c.getColumnIndex("_id")));//AQUI LE DECIMOS QUE RECUPERE EL VALOR DE DE LA COLUMNA QUE SE LLAMA ASI
            inmueble.setId_zona(new Zona(c.getInt(c.getColumnIndex("id_Zona"))));
            inmueble.setUbicacion(c.getString(c.getColumnIndex("ubicacion")));
            inmueble.setPrecio(c.getDouble(c.getColumnIndex("precio")));
            inmueble.setTipo_propiedad(c.getInt(c.getColumnIndex("tipo_propiedad")));
            inmueble.setNum_habitaciones(c.getInt(c.getColumnIndex("num_habitaciones")));
            inmueble.setRuta_imagen(c.getInt(c.getColumnIndex("ruta_imagen")));
            inmueble.setNum_consultas(c.getInt(c.getColumnIndex("num_consultas")));
            listInmueble.add(inmueble);

        }
        BusquedaInmuebles.busqueda = true;
        return listInmueble;
    }

    public List<Inmueble>  buscarInmueblesPorHabitaciones(String numHabitaciones) {

        listInmueble= new ArrayList<>();

        String sql = "SELECT * from Inmuebles where " + CAT_NUMHABITA + " =" + numHabitaciones;
        Cursor c = this.getDb().rawQuery(sql, null);

        while (c.moveToNext()) {

            Inmueble inmueble = new Inmueble();

            inmueble.setId(c.getInt(c.getColumnIndex("_id")));//AQUI LE DECIMOS QUE RECUPERE EL VALOR DE DE LA COLUMNA QUE SE LLAMA ASI
            inmueble.setId_zona(new Zona(c.getInt(c.getColumnIndex("id_Zona"))));
            inmueble.setUbicacion(c.getString(c.getColumnIndex("ubicacion")));
            inmueble.setPrecio(c.getDouble(c.getColumnIndex("precio")));
            inmueble.setTipo_propiedad(c.getInt(c.getColumnIndex("tipo_propiedad")));
            inmueble.setNum_habitaciones(c.getInt(c.getColumnIndex("num_habitaciones")));
            inmueble.setRuta_imagen(c.getInt(c.getColumnIndex("ruta_imagen")));
            inmueble.setNum_consultas(c.getInt(c.getColumnIndex("num_consultas")));
            listInmueble.add(inmueble);
        }
        BusquedaInmuebles.busqueda = true;

        return listInmueble;
    }

    public List<Inmueble> buscarInmueblesPorZona(String numZona) {
        listInmueble= new ArrayList<>();

        String sql = "SELECT * from Inmuebles where " + CAT_IDZONA + " =" + numZona;
        Cursor c = this.getDb().rawQuery(sql, null);

        while (c.moveToNext()) {
            Inmueble inmueble = new Inmueble();

            inmueble.setId(c.getInt(c.getColumnIndex("_id")));//AQUI LE DECIMOS QUE RECUPERE EL VALOR DE DE LA COLUMNA QUE SE LLAMA ASI
            inmueble.setId_zona(new Zona(c.getInt(c.getColumnIndex("id_Zona"))));
            inmueble.setUbicacion(c.getString(c.getColumnIndex("ubicacion")));
            inmueble.setPrecio(c.getDouble(c.getColumnIndex("precio")));
            inmueble.setTipo_propiedad(c.getInt(c.getColumnIndex("tipo_propiedad")));
            inmueble.setNum_habitaciones(c.getInt(c.getColumnIndex("num_habitaciones")));
            inmueble.setRuta_imagen(c.getInt(c.getColumnIndex("ruta_imagen")));
            inmueble.setNum_consultas(c.getInt(c.getColumnIndex("num_consultas")));
            listInmueble.add(inmueble);
        }
        BusquedaInmuebles.busqueda = true;
       return listInmueble;
    }

    @Override
    public void eliminar(String id) {
//SIEMPRE UN ARRAY DE STRINGS-->lo mismo con el update
        super.getDb().delete(TABLE_NAME_INMUEBLES, CAT_ID + "=?", new String[]{id});
    }

    @Override
    public void eliminarTodo() {

        super.getDb().execSQL("DELETE FROM "+ TABLE_NAME_INMUEBLES+";");
        Log.d("Inmuebles_eliminar", "Datos borrados");

    }

    @Override
    public Boolean compruebaRegistro(String id) {

        boolean esta=true;
        Cursor resultSet= super.getDb().rawQuery("Select * from " + TABLE_NAME_INMUEBLES + " WHERE " + CAT_ID + "=" + id, null);
        if(resultSet.getCount()<=0)
            esta=false;
        else
            esta=true;

        return esta;
    }

    public Cursor cargarCursor() {
        String[] columnas= new String[]{CAT_ID, CAT_IDZONA, CAT_UBICACION, CAT_PRECIO, CAT_TIPO, CAT_NUMHABITA, CAT_RUTAIMG, CAT_NUMCONSULTAS};

        return super.getDb().query(TABLE_NAME_INMUEBLES,columnas,null,null,null,null,null );
    }

    public List<Inmueble> getInmueblesList(){ //ME DEVUELVE TODOS LOS INMUEBLES
       if(!BusquedaInmuebles.busqueda) {
           listInmueble = new ArrayList<>();
           Cursor c = cargarCursor();

           while (c.moveToNext()) {
               Inmueble inmueble = new Inmueble();

               inmueble.setId(c.getInt(c.getColumnIndex("_id")));//AQUI LE DECIMOS QUE RECUPERE EL VALOR DE DE LA COLUMNA QUE SE LLAMA ASI
               inmueble.setId_zona(new Zona(c.getInt(c.getColumnIndex("id_Zona"))));
               inmueble.setUbicacion(c.getString(c.getColumnIndex("ubicacion")));
               inmueble.setPrecio(c.getDouble(c.getColumnIndex("precio")));
               inmueble.setTipo_propiedad(c.getInt(c.getColumnIndex("tipo_propiedad")));
               inmueble.setNum_habitaciones(c.getInt(c.getColumnIndex("num_habitaciones")));
               inmueble.setRuta_imagen(c.getInt(c.getColumnIndex("ruta_imagen")));
               inmueble.setNum_consultas(c.getInt(c.getColumnIndex("num_consultas")));

               listInmueble.add(inmueble);
           }
       }
        return listInmueble;
    }

}
