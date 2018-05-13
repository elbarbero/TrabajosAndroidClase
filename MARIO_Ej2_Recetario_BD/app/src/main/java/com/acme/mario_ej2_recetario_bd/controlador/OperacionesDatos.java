package com.acme.mario_ej2_recetario_bd.controlador;

import com.acme.mario_ej2_recetario_bd.R;
import com.acme.mario_ej2_recetario_bd.modelo.Categoria;
import com.acme.mario_ej2_recetario_bd.modelo.Ingredientes;
import com.acme.mario_ej2_recetario_bd.modelo.Receta;
import com.acme.mario_ej2_recetario_bd.modelo.Usuario;

import java.util.ArrayList;

/**
 * Created by Mario on 31/12/2017.
 */

public class OperacionesDatos {

    public static ArrayList<Categoria> misCategorias;
    public static ArrayList<Ingredientes> misIngredientes;
    public static ArrayList<Receta> misRecetas;
    public static ArrayList<Usuario> misUsuarios;


    public static void agregarCategoriasAlArray() {
        misCategorias = new ArrayList<>();

        misCategorias.add(new Categoria("Precocinados", R.drawable.uno));
        misCategorias.add(new Categoria("Al horno", R.drawable.dos));
        misCategorias.add(new Categoria("Postres", R.drawable.tres));
    }

    public static void agregarIngredientesAlArray() {

        misIngredientes = new ArrayList<>();

        misIngredientes.add(new Ingredientes("Pizza"));
        misIngredientes.add(new Ingredientes("Pollo"));
        misIngredientes.add(new Ingredientes("Tomate"));
        misIngredientes.add(new Ingredientes("Lentejas"));
        misIngredientes.add(new Ingredientes("Leche"));
        misIngredientes.add(new Ingredientes("Ajo"));
        misIngredientes.add(new Ingredientes("Patatas"));
        misIngredientes.add(new Ingredientes("Calabacin"));
        misIngredientes.add(new Ingredientes("Chocolate"));
        misIngredientes.add(new Ingredientes("Arroz"));

    }

    public static void agregarRecetasAlArray() {
        misRecetas = new ArrayList<>();
        ArrayList<Ingredientes> misIngredientes = new ArrayList<>();

        misIngredientes.add(new Ingredientes("Arroz"));
        misIngredientes.add(new Ingredientes("Leche"));
        misRecetas.add(new Receta(3, "Arroz con leche", 30, misIngredientes, R.drawable.arrozconleche));

        misIngredientes = new ArrayList<>();
        misIngredientes.add(new Ingredientes("Pollo"));
        misIngredientes.add(new Ingredientes("Patatas"));
        misRecetas.add(new Receta(2, "Pollo con patatas", 40, misIngredientes, R.drawable.polloconpatatas));

        misIngredientes = new ArrayList<>();
        misIngredientes.add(new Ingredientes("Pizza"));
        misRecetas.add(new Receta(1, "Pizza", 5, misIngredientes, R.drawable.pizza));

        misIngredientes = new ArrayList<>();
        misIngredientes.add(new Ingredientes("Chocolate"));
        misIngredientes.add(new Ingredientes("Leche"));
        misRecetas.add(new Receta(3, "Tarta de chocolate", 60, misIngredientes, R.drawable.tartachocolate));

        misIngredientes = new ArrayList<>();
        misIngredientes.add(new Ingredientes("Patatas"));
        misIngredientes.add(new Ingredientes("Calabacin"));
        misIngredientes.add(new Ingredientes("Arroz"));
        misRecetas.add(new Receta(2, "Popurri", 30, misIngredientes, R.drawable.popurricomida));
    }

    public static void agregarUsuariosAlArray() {
        misUsuarios = new ArrayList<>();

        misUsuarios.add(new Usuario("Mario", "elbarbero", "xxx"));
        misUsuarios.add(new Usuario("Lucia", "lalucia", "xxx2"));
        misUsuarios.add(new Usuario("Luis", "elluis", "xxx3"));
        misUsuarios.add(new Usuario("Anonymous", "invitado", "invitado"));
    }
}




