package com.example.mario.ej2_numeros_primos;


import java.util.ArrayList;

public class Primos {
    ArrayList<Integer> vector;


    public Primos() {
        this.vector = new ArrayList<>();
        vector.add(1);
    }

    private boolean siEsPrimo(int numero/*, int contPrimos*/) {

        int divisor = 2;
        boolean esPrimo = true;
        while (((esPrimo) && (divisor < numero))) {
            if (numero % divisor == 0) {
                esPrimo = false;
            } else {
                divisor++;
            }
        }
        return esPrimo;
    }

    public int meterEnArray(int numero) {
        int elemento = vector.get(vector.size() - 1) + 1;
        while (numero > vector.size()) {
            if (siEsPrimo(elemento)) {
                vector.add(elemento);
            }
            elemento++;
        }
        return vector.get(numero - 1);
    }
}