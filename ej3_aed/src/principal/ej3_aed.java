/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import controlador.Controlador;
import modelo.TratarFicheros;
import vista.VistaPrincipal;


public class ej3_aed {


    public static void main(String[] args) {
        TratarFicheros gestor = new TratarFicheros();
        VistaPrincipal vistappl = new VistaPrincipal();
        Controlador controlador = new Controlador(gestor,vistappl);
        controlador.iniciar();
        
    }
    
}
