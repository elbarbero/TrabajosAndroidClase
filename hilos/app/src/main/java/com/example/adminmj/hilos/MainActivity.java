package com.example.adminmj.hilos;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int dificultad=100;
    Integer columna;
    boolean [] continuar={false,false,false};
    int []fotoId={R.drawable.tragaperras1,R.drawable.tragaperras2,R.drawable.tragaperras3
            ,R.drawable.tragaperras4,
            R.drawable.tragaperras5,R.drawable.tragaperras6,R.drawable.tragaperras7
            ,R.drawable.tragaperras8,R.drawable.tragaperras9};

    int [][]secuencia={ {0,1,2,3,4,5,6,7,8},
            {8,7,6,5,4,3,2,1,0},
            {4,5,3,2,1,0,6,7,8}};
    ImageView [][]imagev=new ImageView[3][3];
    TextView texto;
    Button boton1, boton2, boton3,boton4,boton5, boton6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        texto=(TextView)findViewById(R.id.texto);
        imagev[0][0]=(ImageView)findViewById(R.id.imageView11);
        imagev[1][0]=(ImageView)findViewById(R.id.imageView21);
        imagev[2][0]=(ImageView)findViewById(R.id.imageView31);
        imagev[0][1]=(ImageView)findViewById(R.id.imageView12);
        imagev[1][1]=(ImageView)findViewById(R.id.imageView22);
        imagev[2][1]=(ImageView)findViewById(R.id.imageView32);
        imagev[0][2]=(ImageView)findViewById(R.id.imageView13);
        imagev[1][2]=(ImageView)findViewById(R.id.imageView23);
        imagev[2][2]=(ImageView)findViewById(R.id.imageView33);
        boton1=findViewById(R.id.boton1);
        boton1.setOnClickListener(l);
        boton2=findViewById(R.id.boton2);
        boton2.setOnClickListener(l);
        boton3=findViewById(R.id.boton3);
        boton3.setOnClickListener(l);
        boton4=findViewById(R.id.boton4);
        boton4.setOnClickListener(l);
        boton5=findViewById(R.id.boton5);
        boton5.setOnClickListener(l);
        boton6=findViewById(R.id.boton6);
        boton6.setOnClickListener(l);



    }
    private View.OnClickListener l = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (v.getId()==R.id.boton4 ||
                    v.getId()==R.id.boton5 ||v.getId()==R.id.boton6)
            {
                switch( v.getId())
                {
                    case R.id.boton4: dificultad=500; break;
                    case R.id.boton5: dificultad=200; break;
                    case R.id.boton6: dificultad=100;
                }
            }
            else
            {
                switch( v.getId())
                {
                    case R.id.boton1: columna=0; break;
                    case R.id.boton2:columna=1; break;
                    case R.id.boton3: columna=2;
                }
                continuar[columna]=!continuar[columna];//ESTE ES UN CAMBIO DE ESTADO DEL TRUE A FALSE Y VICEVERSA
                if (continuar[columna])
                {
                    //  new MiAsyncTask().execute(columna);
                    new MiAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,columna);
                    ((Button)v).setText("PARAR");
                }
                else
                {
                    ((Button)v).setText("CONTINUAR");
                }
            }

        }
    };

    class MiAsyncTask  extends AsyncTask <Integer, Integer,String> // <EL Nº de columna con la que queremos trabajar, y la salida que vamos a mostrar>
   //---cada class "MiAsyncTask" es un nuevo hilo
    {

        @Override
        protected String doInBackground(Integer... params) {//"params" siempre es un array
            int columnaTrabajando=params[0];
            while (continuar[columnaTrabajando])
            {
                int elemento1=secuencia[columnaTrabajando][0];
                for ( int i=0; i<8;i++)
                    secuencia[columnaTrabajando][i]=secuencia[columnaTrabajando][i+1];
                secuencia[columnaTrabajando][8]=elemento1;
                try{
                    Thread.sleep(Math.abs(dificultad));//duerme el hilo los milisegundos que contenga la variable "DIFICULTAD"
                }
                catch( InterruptedException e)
                {
                    e.printStackTrace();
                }
                publishProgress(columnaTrabajando);
            }
            return "Stop columna "+ (columnaTrabajando+1);
        }
        @Override
        protected void onProgressUpdate (Integer...progreso)
        {
            int columnaProgreso=progreso[0];

            for ( int i=0;i<3;i++)
            {
                imagev[i][columnaProgreso].setImageResource(fotoId[secuencia[columnaProgreso][i]]);
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if ( !continuar[0] && !continuar[1] && !continuar[2])
            {
                if(secuencia[0][1]== secuencia[1][1] && secuencia[1][1]== secuencia[2][1])
                {
                    texto.setText("PREMIO");
                }
                else
                {
                    texto.setText("PRUEBE SUERTE OTRA VEZ");
                }
            }
            else
            {
                texto.setText(""+ s);
            }
        }
    }

}
