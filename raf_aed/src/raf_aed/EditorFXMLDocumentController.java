/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raf_aed;

import editor.modelo.ListaPersonas;
import editor.modelo.Persona;
import editor.modelo.RegistroPersona;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import editor.modelo.TratarFicheros;
import javafx.scene.control.TextArea;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.web.HTMLEditor;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * @author Danieldb
 */
public class EditorFXMLDocumentController implements Initializable {
    
    @FXML
    private TextArea editorTextArea;
    
    private ListaPersonas listaPersonas;
    private TratarFicheros gestor = new TratarFicheros();
    
    @FXML
    private void handleAbrirAction(ActionEvent event) throws IOException, ClassNotFoundException {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.TXT", "txt");
        fileChooser.setFileFilter(filtro);
        int seleccion = fileChooser.showOpenDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            editorTextArea.setText(this.gestor.abrirFichero(fileChooser.getSelectedFile()));
        }
    }
    
    @FXML
    private void handleGuardarAction(ActionEvent event) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.TXT", "txt");
        fileChooser.setFileFilter(filtro);
        int seleccion = fileChooser.showSaveDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            this.gestor.guardarFichero(fileChooser.getSelectedFile(), this.listaPersonas);
        }
    }
    @FXML
    private void handleGuardarComoAction(ActionEvent event) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.TXT", "txt");
        fileChooser.setFileFilter(filtro);
        int seleccion = fileChooser.showSaveDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            this.gestor.guardarFichero(fileChooser.getSelectedFile(), this.listaPersonas);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.listaPersonas = new ListaPersonas();
        Persona persona = new Persona("Roberto", "Ricardo", 23);
        Persona personaDos = new Persona("Modifico", "Posicion", 99);
        try {
            this.editorTextArea.setText(this.gestor.anadirRegistro(persona));
            this.editorTextArea.setText(this.gestor.modificarRegistro(personaDos, 412));
        } catch (IOException ex) {
            Logger.getLogger(EditorFXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditorFXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
