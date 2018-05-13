package com.mbs.recyclercolores;

import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.List;

/**
 * Created by alumnoDAM on 16/10/2017.
 */

public class AdaptadorElemento extends RecyclerView.Adapter<AdaptadorElemento.DatosHolder> {

    private List<Color> datos;
    private RecyclerViewOnItemClickListener escucha;

    public AdaptadorElemento(List<Color> datos, RecyclerViewOnItemClickListener escucha) {
        this.datos = datos;
        this.escucha=escucha;
    }

    @Override
   /* public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }*/
   //le ponemos el nombre de una clase de tipo Holder. la clase DatosHolder la tenemos más abajo
    public DatosHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View fila= LayoutInflater.from(parent.getContext()).inflate(R.layout.fila,parent,false);
        return new DatosHolder(fila);
    }

    @Override
    public void onBindViewHolder(DatosHolder holder, int position) {
Color colores=datos.get(position);
        holder.getTextoAdaptador().setText(colores.getColor());//la nueva informacion que vamos a mostrar
       /* GradientDrawable gradiente=(GradientDrawable)holder.getCirculoAdaptador().getBackground();//circulo es de tipo Gradient, en la siguiente línea le ccambiamos el color
        gradiente.setColor(android.graphics.Color.parseColor(color.getColor()));*///del objeto de ese tipo me cambia el color

        GradientDrawable gradiente=(GradientDrawable)holder.getCirculoAdaptador().getBackground();
        gradiente.setColor(android.graphics.Color.parseColor(colores.getColorIngles()));//ponemos el nombre en ingles para que android lo coga  no casque
    }

    @Override
    //me devuelve el numero de lementos
    public int getItemCount() {
        return datos.size();
    }

    public class DatosHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//aqui ponemos la informacion que queremos mostrar.
// En esta clase tengo toda la información de todos los elementos(todos los view) que estoy mostrando
        View circuloAdaptador;
        TextView textoAdaptador;
        Button boton;

        public DatosHolder(View itemView) {
            super(itemView);
            circuloAdaptador=itemView.findViewById(R.id.circulo);
            textoAdaptador=(TextView)itemView.findViewById(R.id.texto);
            boton=(Button)itemView.findViewById(R.id.button);
            itemView.setOnClickListener(this);//te implementa el onClick en este contexto(en la clase DatosHolder)
            //el interface sirve para que todos los elementos tengan el mismo comportamiento, a través del onClick
            boton.setOnClickListener(this);
        }

        public View getCirculoAdaptador() {
            return circuloAdaptador;
        }

        public TextView getTextoAdaptador() {
            return textoAdaptador;
        }

        @Override
        public void onClick(View view) {
            //getAdapterPosition()-->ES DONDE HEMOS PULSADO
escucha.onClick(view,getAdapterPosition());
        }
    }

}
