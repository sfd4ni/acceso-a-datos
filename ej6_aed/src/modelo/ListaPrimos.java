/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author Danieldb
 */
public class ListaPrimos {
    ArrayList<Integer> listaPrimos;
    
    public ListaPrimos() {
        listaPrimos = new ArrayList<>();
    }
    
    public void crearListaPrimos() {
        listaPrimos.add(2);
        for (int i = 3; i <= 10000000; i+=2) {
            boolean esPrimo = true;
            int j = 0;
            while (listaPrimos.get(j) * listaPrimos.get(j) < i) {
                if (i % listaPrimos.get(j) == 0) {
                    esPrimo = false;
                    break;
                }
                j++;
            }
            if (esPrimo) {
                listaPrimos.add(i);
            }
        }
    }
    public void calcularPrimoAPrimo() {
        listaPrimos.add(2);
        for (int i = 3; i <= 10000000; i+=2) {
            boolean esPrimo = true;
            int j = 0;
            while (listaPrimos.get(j) * listaPrimos.get(j) < i) {
                if (i % listaPrimos.get(j) == 0) {
                    esPrimo = false;
                    break;
                }
                j++;
            }
            if (esPrimo) {
                listaPrimos.add(i);
                TratarFicheros.guardarPrimoAPrimo(i);
            }
        }
    }
}
