package com.example.mario.ej3_calculadora;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtResuladoTot;
    private TextView txtResuladoNum;
    private Button btnCero, btnUno, btnDos, btnTres, btnCuatro, btnCinco, btnSeis, btnSiete, btnOcho, btnNueve;
    private Button btnComita, btnMasMinus;
    private Button btnIkual, btnMasMas, btnMenosMenos, btnPor, btnEntre;
    private Button btnBorrado, btnCC, btnCee;
    private Button btnTantoXCiento, btnRaices, btnAlCuadrado, btnUnoEntreX;
    double result = 0;
    int contadorSigno = 0;
    double tagNumeros = -1;
    String signo = "";
    String copiaSigno = "";
    boolean esDecimal = false;
    boolean operacionHecha = false;
    boolean cambioDeSigno = false;
    boolean primeraOperacion = true;
    boolean tantoXCientohecho = false;
    boolean CEpulsado = false;
    boolean Cpulsado = false;
    boolean botonesDeArriba = false;
    boolean parentesisQuitados=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResuladoTot = (TextView) findViewById(R.id.txtResultadoT);
        txtResuladoNum = (TextView) findViewById(R.id.txtResultNum);
        btnCero = (Button) findViewById(R.id.btnCero);
        btnUno = (Button) findViewById(R.id.btnUno);
        btnDos = (Button) findViewById(R.id.btnDos);
        btnTres = (Button) findViewById(R.id.btnTres);
        btnCuatro = (Button) findViewById(R.id.btnCuatro);
        btnCinco = (Button) findViewById(R.id.btnCinco);
        btnSeis = (Button) findViewById(R.id.btnSeis);
        btnSiete = (Button) findViewById(R.id.btnSiete);
        btnOcho = (Button) findViewById(R.id.btnOcho);
        btnNueve = (Button) findViewById(R.id.btnNueve);
        btnIkual = (Button) findViewById(R.id.btnIgual);
        btnComita = (Button) findViewById(R.id.btnComa);
        btnMasMinus = (Button) findViewById(R.id.btnMasMenos);
        btnMasMas = (Button) findViewById(R.id.btnMas);
        btnMenosMenos = (Button) findViewById(R.id.btnMenos);
        btnPor = (Button) findViewById(R.id.btnMultiplicar);
        btnEntre = (Button) findViewById(R.id.btnDividir);
        btnBorrado = (Button) findViewById(R.id.btnBorrar);
        btnCC = (Button) findViewById(R.id.btnC);
        btnCee = (Button) findViewById(R.id.btnCE);
        btnTantoXCiento = (Button) findViewById(R.id.btnTantoPorCiento);
        btnRaices = (Button) findViewById(R.id.btnRaiz);
        btnAlCuadrado = (Button) findViewById(R.id.btnElevar);
        btnUnoEntreX = (Button) findViewById(R.id.btnUnoX);

//----------------------------------------------------------------------
        btnCero.setOnClickListener(this);
        btnUno.setOnClickListener(this);
        btnDos.setOnClickListener(this);
        btnTres.setOnClickListener(this);
        btnCuatro.setOnClickListener(this);
        btnCinco.setOnClickListener(this);
        btnSeis.setOnClickListener(this);
        btnSiete.setOnClickListener(this);
        btnOcho.setOnClickListener(this);
        btnNueve.setOnClickListener(this);
        btnMasMas.setOnClickListener(this);
        btnMenosMenos.setOnClickListener(this);
        btnPor.setOnClickListener(this);
        btnEntre.setOnClickListener(this);
        btnIkual.setOnClickListener(this);
        btnCC.setOnClickListener(this);
        btnCee.setOnClickListener(this);
        btnComita.setOnClickListener(this);
        btnMasMinus.setOnClickListener(this);
        btnBorrado.setOnClickListener(this);
        btnRaices.setOnClickListener(this);
        btnAlCuadrado.setOnClickListener(this);
        btnUnoEntreX.setOnClickListener(this);
        btnTantoXCiento.setOnClickListener(this);
        View v=new View(this);
        activarBotones(v);
    }

    @Override
    public void onClick(View view) {
        double ResultadoBotonesDArriba = 0;
        btnBorrado.setEnabled(true);
        btnComita.setEnabled(true);
        String ResultadoArriba = txtResuladoNum.getText().toString();
        String ResultadoAbajo = txtResuladoTot.getText().toString();

        txtResuladoTot = (TextView) findViewById(R.id.txtResultadoT);
        int seleccionado = view.getId();

        copiaSigno = signo;
        signo = signosOperacion(seleccionado, view);
        try {
            switch (seleccionado) {
                case R.id.btnComa:
                    if (txtResuladoTot.getText().toString().compareTo("") == 0) {
                        txtResuladoTot.setText("0" + btnComita.getTag().toString());
                        txtResuladoNum.setText("0");
                    } else if (comprobarComa() == 1) {
                        txtResuladoTot.setText(txtResuladoTot.getText() + btnComita.getTag().toString());
                    }
                    btnComita.setEnabled(false);
                    esDecimal = true;
                    break;
                case R.id.btnBorrar:
                    borrarNumero(ResultadoArriba, ResultadoAbajo);
                    break;
                case R.id.btnTantoPorCiento:
                    operaciones(signo, copiaSigno, view);
                    break;
                case R.id.btnRaiz:
                    operacionDeBotonesDeArriba(view,ResultadoBotonesDArriba);
                    break;
                case R.id.btnElevar:
                    operacionDeBotonesDeArriba(view,ResultadoBotonesDArriba);
                    break;
                case R.id.btnUnoX:
                    operacionDeBotonesDeArriba(view,ResultadoBotonesDArriba);
                    break;
                case R.id.btnMasMenos:
                    cambioDeSigno = true;
                    break;
                case R.id.btnMas:
                    operaciones(signo, copiaSigno, view);
                    break;
                case R.id.btnMenos:
                    operaciones(signo, copiaSigno, view);
                    break;
                case R.id.btnDividir:
                    operaciones(signo, copiaSigno, view);
                    break;
                case R.id.btnMultiplicar:
                    operaciones(signo, copiaSigno, view);
                    break;
                case R.id.btnIgual:
                    operaciones(signo, copiaSigno, view);
                    btnBorrado.setEnabled(false);
                    break;
                case R.id.btnC:
                    Cpulsado = true;
                    borrarTeclaC(ResultadoArriba, ResultadoAbajo);
                    break;
                case R.id.btnCE:
                    CEpulsado = true;
                    txtResuladoTot.setText("");
                    txtResuladoNum.setText("");
                    contadorSigno = 0;
                    result = 0;
                    activarBotones(view);
                    break;
                default:

                    if (tagNumeros == -1 && operacionHecha || txtResuladoTot.getText().toString().compareToIgnoreCase("ERROR") == 0) {
                        if (!esDecimal) {
                            txtResuladoTot.setText("");
                            if (signo.equals("=")) {//SI EL PRIMER BOTON QUE PULSO DESPUES DEL IGUAL ES UN NUMERO
                                txtResuladoNum.setText("");
                                result = 0;
                            }
                        }
                    } else if (botonesDeArriba) {//SI EL PRIMER BOTON QUE PULSO DESPUES DE CUALQUIERA DE LOS BOTONES DE ARRIBA ES UN NUMERO
                        txtResuladoNum.setText("");
                        txtResuladoTot.setText("");
                        result = 0;
                    }
                    txtResuladoTot.setText(txtResuladoTot.getText() + view.getTag().toString());
                    tagNumeros = Double.parseDouble(view.getTag().toString());
                    esDecimal = false;
                    botonesDeArriba = false;
            }
            if (comprobarComa() > 1) {
                btnComita.setEnabled(false);
            }
            if (!cambioDeSigno) {
                if (view.getTag().toString().compareToIgnoreCase("=") != 0 && view.getTag().toString().compareToIgnoreCase("null") != 0) {//AQUI CONCATENO TODOS LOS CARACTERES EL TXT DE ARRIBA
                    txtResuladoNum.setText(txtResuladoNum.getText() + view.getTag().toString());
                }
            } else {
                cambiarSigno(ResultadoArriba,ResultadoAbajo);
            }
            activarBotones(view);
        } catch (Exception e) {
            txtResuladoTot.setText("CRASH");
        }
    }

    public String signosOperacion(int seleccionado, View view) {
        String signo2 = "";
        operacionHecha = false;
        switch (seleccionado) {
            case R.id.btnMas:
                signo2 = (view.getTag().toString());
                operacionHecha = true;
                break;
            case R.id.btnMenos:
                signo2 = (view.getTag().toString());
                operacionHecha = true;
                break;
            case R.id.btnMultiplicar:
                signo2 = (view.getTag().toString());
                operacionHecha = true;
                break;
            case R.id.btnDividir:
                signo2 = (view.getTag().toString());
                operacionHecha = true;
                break;
            case R.id.btnTantoPorCiento:
                signo2 = (view.getTag().toString());
                operacionHecha = true;
                break;
            case R.id.btnRaiz:
                signo2 = (view.getTag().toString());
                operacionHecha = true;
                break;
            case R.id.btnElevar:
                signo2 = (view.getTag().toString());
                operacionHecha = true;
                break;
            case R.id.btnUnoX:
                signo2 = (view.getTag().toString());
                operacionHecha = true;
                break;
            case R.id.btnIgual:
                signo2 = (view.getTag().toString());
                operacionHecha = true;
                break;
            default:
                signo2 = signo;
                operacionHecha = true;
        }
        return signo2;
    }

    public void operaciones(String signo, String copiaSigno, View view) {
        boolean hecho = false;
        btnComita.setEnabled(true);

        if (view.getTag().toString().compareTo("√") != 0 && view.getTag().toString().compareTo("x2") != 0 && view.getTag().toString().compareTo("1/x") != 0) {
            if (contadorSigno > 0) {
                //el tagNumeros es para que solo me haga las operaciones si he metido un numero, por lo que la operacion me la va a hacer cuando meta el numero y no el signo
                if (view.getTag().toString().equals("=") || !(view.getTag().toString().equals("=")) && tagNumeros >= 0) {//SEA A O NO SEA EL IGUAL ME VA A HACER LAS MISMAS OPERACIOENS

                    if (copiaSigno.compareTo("+") == 0) {
                        result = result + Double.parseDouble(txtResuladoTot.getText().toString());
                    } else if (copiaSigno.compareTo("-") == 0) {
                        result = result - Double.parseDouble(txtResuladoTot.getText().toString());
                    } else if (copiaSigno.compareTo("/") == 0) {
                        result = result / Double.parseDouble(txtResuladoTot.getText().toString());
                    } else if (copiaSigno.compareTo("*") == 0) {
                        result = result * Double.parseDouble(txtResuladoTot.getText().toString());
                    } else if (copiaSigno.compareTo("%") == 0) {
                        if (signo.compareToIgnoreCase("%") != 0 && !tantoXCientohecho) {
                            if (tagNumeros >= 0 || copiaSigno.compareTo("%") != 0) {
                                result = result * Double.parseDouble(txtResuladoTot.getText().toString());
                                tantoXCientohecho = false;
                            }
                        }

                        if (tantoXCientohecho) {
                            result = result * Double.parseDouble(txtResuladoTot.getText().toString());
                        }
                    }

                    if (signo.compareTo("%") == 0 && copiaSigno.compareTo("%") != 0) {
                        result = result / 100;
                        tantoXCientohecho = true;
                    }

                    if (view.getTag().toString().equals("=")) {
                        txtResuladoNum.clearComposingText();
                        txtResuladoNum.setText(Double.valueOf(result).toString());
                        contadorSigno = 0;
                    }
                    txtResuladoTot.setText(Double.valueOf(result).toString());
                    hecho = true;
                    tantoXCientohecho = false;

                    if (comprobarComa() > 1) {
                        btnComita.setEnabled(false);
                    }
                }
                tagNumeros = -1;
            }

            if (!hecho && contadorSigno == 0) {
                if (signo.compareToIgnoreCase("+") == 0) {
                    if (tagNumeros >= 0) {
                        if (copiaSigno.compareTo("=") != 0) {//X SI DESPUES DE PULSAR EL IGUAL, PULSO UN NUMERO Y HAGO CUALQUIER OPERACION
                            result = Double.parseDouble(txtResuladoTot.getText().toString());
                        } else {
                            result = result + Double.parseDouble(txtResuladoTot.getText().toString());
                        }
                    }
                }
                if (!primeraOperacion) {//SI NO ES LA PRIMERA OPERACION
                    if (signo.compareToIgnoreCase("-") == 0) {
                        if (tagNumeros >= 0) {
                            result = result - Double.parseDouble(txtResuladoTot.getText().toString());
                        }
                    } else if (signo.compareToIgnoreCase("/") == 0) {
                        if (tagNumeros >= 0) {
                            result = result / Double.parseDouble(txtResuladoTot.getText().toString());
                        }
                    } else if (signo.compareToIgnoreCase("*") == 0) {
                        if (tagNumeros >= 0) {
                            result = result * Double.parseDouble(txtResuladoTot.getText().toString());
                        }
                    } else if (signo.compareToIgnoreCase("%") == 0) {
                        if (tagNumeros >= 0 || copiaSigno.compareTo("%") != 0) {
                            result = result * Double.valueOf(txtResuladoTot.getText().toString());
                            tantoXCientohecho = true;
                        }
                    }
                } else {
                    if (signo.compareToIgnoreCase("-") == 0) {
                        if (tagNumeros >= 0) {
                            result = Double.parseDouble(txtResuladoTot.getText().toString());
                        }
                    } else if (signo.compareToIgnoreCase("/") == 0) {
                        if (tagNumeros >= 0) {
                            result = Double.parseDouble(txtResuladoTot.getText().toString());
                        }
                    } else if (signo.compareToIgnoreCase("*") == 0) {
                        if (tagNumeros >= 0) {
                            result = Double.parseDouble(txtResuladoTot.getText().toString());
                        }
                    } else if (signo.compareToIgnoreCase("%") == 0) {
                        if (tagNumeros >= 0 || copiaSigno.compareTo("%") != 0) {
                            result = (Double.parseDouble(txtResuladoTot.getText().toString())) / 100;
                            tantoXCientohecho = true;
                        }
                    } else if (signo.compareToIgnoreCase("=") == 0) {//SI LA PRIMERA VEZ QUE METO UN NUMERO SIN HACER NINGUNA OPERACION ANTES
                        result = Double.parseDouble(txtResuladoTot.getText().toString());
                        txtResuladoNum.setText(Double.valueOf(result).toString());
                    }
                }
                txtResuladoTot.setText(Double.valueOf(result).toString());
                tagNumeros = -1;
                contadorSigno++;
                primeraOperacion = true;
                btnBorrado.setEnabled(false);
            }
        }
    }

    public void operacionDeBotonesDeArriba(View v,double ResultadoBotonesDArriba){
        if (v.getTag().toString().compareTo("√") == 0 ) {

            if (Double.parseDouble(txtResuladoTot.getText().toString()) < 0) {
                txtResuladoTot.setText("ERROR");
                txtResuladoNum.setText("ERROR");
                result = 0;
            } else {
                ResultadoBotonesDArriba = Math.sqrt(Double.parseDouble(txtResuladoTot.getText().toString()));
                signo = "";
                if (result == 0) {
                    txtResuladoTot.setText(Double.valueOf(ResultadoBotonesDArriba).toString());
                    txtResuladoNum.setText(Double.valueOf(ResultadoBotonesDArriba).toString());
                } else {
                    if (copiaSigno.equals("=")) {
                        result = ResultadoBotonesDArriba;
                    } else {
                        result = result + ResultadoBotonesDArriba;
                    }
                    txtResuladoTot.setText(Double.valueOf(result).toString());
                    txtResuladoNum.setText(Double.valueOf(result).toString());
                }
                botonesDeArriba = true;
            }
        }else if( v.getTag().toString().compareTo("x2") == 0){
            ResultadoBotonesDArriba = Double.parseDouble(txtResuladoTot.getText().toString()) * Double.parseDouble(txtResuladoTot.getText().toString());
            signo = "";
            if (result == 0) {
                txtResuladoTot.setText(Double.valueOf(ResultadoBotonesDArriba).toString());
                txtResuladoNum.setText(Double.valueOf(ResultadoBotonesDArriba).toString());
            } else {
                if (copiaSigno.equals("=")) {
                    result = ResultadoBotonesDArriba;
                } else {
                    result = result + ResultadoBotonesDArriba;
                }
                txtResuladoTot.setText(Double.valueOf(result).toString());
                txtResuladoNum.setText(Double.valueOf(result).toString());
            }
            botonesDeArriba = true;
        }else if(v.getTag().toString().compareTo("1/x") == 0){
            ResultadoBotonesDArriba = 1 / (Double.parseDouble(txtResuladoTot.getText().toString()));
            signo = "";
            if (result == 0) {
                txtResuladoTot.setText(Double.valueOf(ResultadoBotonesDArriba).toString());
                txtResuladoNum.setText(Double.valueOf(ResultadoBotonesDArriba).toString());
            } else {
                if (copiaSigno.equals("=")) {
                    result = ResultadoBotonesDArriba;
                } else {
                    result = result + ResultadoBotonesDArriba;
                }
                txtResuladoTot.setText(Double.valueOf(result).toString());
                txtResuladoNum.setText(Double.valueOf(result).toString());
            }
            botonesDeArriba = true;
        }
    }

    public int comprobarComa() {
        String r = txtResuladoTot.getText().toString();
        String[] NumComasArray = r.split("\\.");
        return NumComasArray.length;
    }

    public void activarBotones(View v) {
        if (txtResuladoNum.getText().toString().compareTo("") == 0 || txtResuladoTot.getText().toString().compareTo("") == 0
                || txtResuladoTot.getText().toString().compareToIgnoreCase("ERROR") == 0 || signo.compareTo(v.getTag().toString()) == 0 && signo.compareTo("=") != 0 || CEpulsado || Cpulsado) {
            btnMasMas.setEnabled(false);
            btnMenosMenos.setEnabled(false);
            btnEntre.setEnabled(false);
            btnPor.setEnabled(false);
            btnMasMinus.setEnabled(false);
            btnTantoXCiento.setEnabled(false);
            btnRaices.setEnabled(false);
            btnAlCuadrado.setEnabled(false);
            btnUnoEntreX.setEnabled(false);
            btnBorrado.setEnabled(false);
        } else {
            btnMasMas.setEnabled(true);
            btnMenosMenos.setEnabled(true);
            btnEntre.setEnabled(true);
            btnPor.setEnabled(true);
            btnMasMinus.setEnabled(true);
            btnTantoXCiento.setEnabled(true);
            btnRaices.setEnabled(true);
            btnAlCuadrado.setEnabled(true);
            btnUnoEntreX.setEnabled(true);
        }
        CEpulsado = false;
        Cpulsado = false;
    }

    public void cambiarSigno(String ResultadoArriba,String ResultadoAbajo){
        String datos="";
        String antesDelParentesis="";
        int pos = 0;
        double menosUno = Double.parseDouble(txtResuladoTot.getText().toString()) * -1;
        txtResuladoTot.setText(String.valueOf(menosUno));
        if(menosUno<0) {
            pos = ResultadoArriba.lastIndexOf(ResultadoAbajo);
            datos = (txtResuladoNum.getText().toString().substring(0, pos)) + "(" + menosUno + ")";
        }else{
            int poss=ResultadoArriba.lastIndexOf("(-")+1;
            String[] hayParentesis = ResultadoArriba.split("");
            while (poss<hayParentesis.length) {
                antesDelParentesis = antesDelParentesis + hayParentesis[poss];
                poss++;
            }
            datos=txtResuladoNum.getText().toString().replace(antesDelParentesis, String.valueOf(menosUno));//PARA QUE ME BORRE EL PRIMER PARENTESIS
        }
        txtResuladoNum.setText(datos);
        if (signo.compareTo("=") == 0 || botonesDeArriba) {
            result = menosUno;
        }
        cambioDeSigno = false;
    }

    public void borrarNumero(String numArriba, String numAbajo) {
        int i = 0;
        int a = 0;
        if(txtResuladoTot.getText().length()==0){
            btnBorrado.setEnabled(false);
            parentesisQuitados=false;
        }else{
            String[] hayParentesis = numAbajo.split("\\(");
            if (hayParentesis.length ==1 ) {//SI TIENE 2 O MÁS POSICIONES EL ARRAY ES QUE TIENE PARENTESIS
                String borrar = hayParentesis[hayParentesis.length - 1] + ")";
                String[] borrarConParentesis = borrar.split("");
                txtResuladoNum.setText(txtResuladoNum.getText().toString().replace("(", ""));//PARA QUE ME BORRE EL PRIMER PARENTESIS
                txtResuladoNum.setText(txtResuladoNum.getText().toString().replace(")", ""));//PARA QUE ME BORRE EL ULTIMO PARENTESIS
                parentesisQuitados = true;
            }
            if(parentesisQuitados){//...SI ES DISTINTO DE 2 POSICIONES ES QUE NO TIENE PARENTESIS
                txtResuladoTot.setText(txtResuladoTot.getText().toString().substring(0, txtResuladoTot.length() - 1));
                txtResuladoNum.setText(txtResuladoNum.getText().toString().substring(0, txtResuladoNum.length() - 1));
            }
        }
    }

    public void borrarTeclaC(String numArriba, String numAbajo) {
        String[] hayParentesis = numArriba.split("\\(");
        if(txtResuladoTot.length()>0) {
            if (hayParentesis.length >= 2) {//----- PONGO ESTO PARA QUE NO ME CRASEE AL BORRAR NUMEROS ENTRE PARENTESIS
                String borrar = "(" + hayParentesis[hayParentesis.length - 1];
                txtResuladoTot.setText(txtResuladoTot.getText().toString().replace(numAbajo, ""));//LA PARTE DE ABAJO DEL TEXTO
                txtResuladoNum.setText(txtResuladoNum.getText().toString().replace(borrar, ""));//LA PARTE DE ARRIBA DEL TEXTO
            } else {
                txtResuladoTot.setText(txtResuladoTot.getText().toString().replace(numAbajo, ""));//REMPLAZAMOS EL TODO NUMERO POR UN VALOR VACIO, ES DECIR, BORRAMOS EL NUMERO
                txtResuladoNum.setText(txtResuladoNum.getText().toString().replace(numAbajo, ""));
            }
        }
    }
}