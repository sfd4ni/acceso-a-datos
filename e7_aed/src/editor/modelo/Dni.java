/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor.modelo;

import java.io.Serializable;

/**
 *
 * @author Danieldb
 */
public class Dni implements Serializable {
    private int numero;
    private char letra;
    public boolean correcto;
    
    public Dni(int numeroInput, char letraInput) {
        this.numero = numeroInput;
        this.letra = letraInput;
        this.correcto = false;
        comprobarDni();
        
    }
    
    private void comprobarDni() {
        if (("" + this.numero).length() == 8) {
            if (("" + this.letra).matches("[a-zA-Z]+")) {
               if (comprobarLetra(this.numero).equals(this.letra + "")) {
                   correcto = true;
               }
            }
        }
    }
    
    private String comprobarLetra(int dni) {
        String letrasDni = "TRWAGMYFPDXBNJZSQVHLCKE";
        return letrasDni.substring(dni%23, dni%23+1);
    }
    
    public String imprimirDni() {
        if (this.correcto) {
            return this.numero + "" + this.letra;
        }
        return "Dni Incorrecto";
    }
    
}
