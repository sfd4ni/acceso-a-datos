/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.ListaPrimos;
import modelo.TratarFicheros;
import vista.VistaPrincipal;


public class Controlador implements ActionListener{
    
    
    private VistaPrincipal vistaPpl;
    private TratarFicheros gestor;
    private ListaPrimos listaPrimos;

    public Controlador( TratarFicheros gestor, VistaPrincipal vistaPpl) {
        this.vistaPpl = vistaPpl;
        this.gestor = gestor;
        this.vistaPpl.guardarPrimos.addActionListener(this);
        this.vistaPpl.guardarPrimoAPrimo.addActionListener(this);
    }
    

    public void iniciar(){
        
        vistaPpl.setTitle("Primos");
        vistaPpl.setLocationRelativeTo(null);
        vistaPpl.setVisible(true);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        this.listaPrimos= new ListaPrimos();
        
        
               
        Object oEvent = e.getSource();
        
        if (oEvent==vistaPpl.guardarPrimos) {
            this.listaPrimos.crearListaPrimos();
            this.gestor.guardarPrimos(listaPrimos);
        }
        
        if (oEvent == vistaPpl.guardarPrimoAPrimo) {
            this.listaPrimos.calcularPrimoAPrimo();
        }
    }
    
}
