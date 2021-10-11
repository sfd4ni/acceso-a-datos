/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.TratarFicheros;
import vista.VistaPrincipal;


public class Controlador implements ActionListener{
    
    
    private VistaPrincipal vistaPpl;
    private TratarFicheros gestor;

    public Controlador( TratarFicheros gestor, VistaPrincipal vistaPpl ) {
        this.vistaPpl = vistaPpl;
        this.gestor = gestor;
        this.vistaPpl.abrirMenuItem.addActionListener(this);
        this.vistaPpl.guardarMenuItem.addActionListener(this);
        this.vistaPpl.guardarComoMenuItem.addActionListener(this);
    }

    public void iniciar(){
        vistaPpl.setTitle("Operacions_MVC");
        vistaPpl.setLocationRelativeTo(null);
        vistaPpl.setVisible(true);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
   
        Object oEvent = e.getSource();
        
        if (oEvent==vistaPpl.abrirMenuItem) {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.TXT", "txt", ".HTML", "html");
            fileChooser.setFileFilter(filtro);
            int seleccion = fileChooser.showOpenDialog(vistaPpl);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                try {
                    this.vistaPpl.ficheroTextArea.setText(this.gestor.abrirFichero(fileChooser.getSelectedFile()));
                } catch (IOException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        if (oEvent == vistaPpl.guardarMenuItem || oEvent == vistaPpl.guardarComoMenuItem) {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.TXT", "txt", ".HTML", "html");
            fileChooser.setFileFilter(filtro);
            int seleccion = fileChooser.showSaveDialog(vistaPpl);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                this.gestor.guardarFichero(fileChooser.getSelectedFile(), 
                    this.vistaPpl.ficheroTextArea.getText());
            }
        }  
    }
    
}
