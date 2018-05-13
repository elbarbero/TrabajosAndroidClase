package com.mario.fragments.controlador;


import com.mario.fragments.R;
import com.mario.fragments.modelo.Correo;
import com.mario.fragments.modelo.Usuario;

/**
 * Created by adminmj on 13/12/2017.
 */

public class OperacionesDatos {
    public static Usuario usuario;

    public static void cargarDatos()
    {
        usuario=new Usuario();
        //inicializo los datos de mi usuario
        usuario.setNombre("Profesor");
        usuario.setCurso("2º DAM");

        //inicializo la lista de correos
        usuario.getListaCorreos().add(new Correo("c1", R.drawable.foto1,"Usuario 1","Asunto 1","Contenido guardado del correo de este usuario que no se puede leer en un minuto"));
        usuario.getListaCorreos().add(new Correo("c2", R.drawable.foto2,"Usuario 2","Asunto 2","Contenido guardado del correo de este usuario que no se puede leer en un minuto"));
        usuario.getListaCorreos().add(new Correo("c3",R.drawable.foto3,"Usuario 3","Asunto 3","Contenido guardado del correo de este usuario que no se puede leer en un minuto"));
        usuario.getListaCorreos().add(new Correo("c4",R.drawable.foto4,"Usuario 4","Asunto 4","Contenido guardado del correo de este usuario que no se puede leer en un minuto"));
        usuario.getListaCorreos().add(new Correo("c5",R.drawable.foto1,"Usuario 1","Asunto 5","Contenido guardado del correo de este usuario que no se puede leer en un minuto"));
        usuario.getListaCorreos().add(new Correo("c6",R.drawable.foto5,"Usuario 5","Asunto 6","Contenido guardado del correo de este usuario que no se puede leer en un minuto"));
        usuario.getListaCorreos().add(new Correo("c7",R.drawable.foto6,"Usuario 6","Asunto 7","Contenido guardado del correo de este usuario que no se puede leer en un minuto"));
        usuario.getListaCorreos().add(new Correo("c8",R.drawable.foto2,"Usuario 2","Asunto 8","Contenido guardado del correo de este usuario que no se puede leer en un minuto"));
        usuario.getListaCorreos().add(new Correo("c9",R.drawable.foto5,"Usuario 5","Asunto 9","Contenido guardado del correo de este usuario que no se puede leer en un minuto"));
        usuario.getListaCorreos().add(new Correo("c10",R.drawable.foto3,"Usuario 3","Asunto 10","Contenido guardado del correo de este usuario que no se puede leer en un minuto"));
        usuario.getListaCorreos().add(new Correo("c11",R.drawable.foto4,"Usuario 4","Asunto 11","Contenido guardado del correo de este usuario que no se puede leer en un minuto"));
        usuario.getListaCorreos().add(new Correo("c12",R.drawable.foto6,"Usuario 6","Asunto 12","Contenido guardado del correo de este usuario que no se puede leer en un minuto"));
    }
}
