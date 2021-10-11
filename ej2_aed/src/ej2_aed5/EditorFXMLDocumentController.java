/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ej2_aed5;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import editor.modelo.TratarFicheros;
import java.io.IOException;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * @author Danieldb
 */
public class EditorFXMLDocumentController implements Initializable {
    
    @FXML
    private HTMLEditor editorHtmlEditor;
    @FXML
    private AnchorPane ventanaAnchorPane;
    private TratarFicheros gestor = new TratarFicheros();
    
    @FXML
    private void handleAbrirAction(ActionEvent event) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.TXT", "txt", ".HTML", "html");
        fileChooser.setFileFilter(filtro);
        int seleccion = fileChooser.showOpenDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            editorHtmlEditor.setHtmlText(this.gestor.abrirFichero(fileChooser.getSelectedFile()));
        }
    }
    
    @FXML
    private void handleGuardarAction(ActionEvent event) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.TXT", "txt");
        fileChooser.setFileFilter(filtro);
        fileChooser.setFileFilter(new FileNameExtensionFilter("HTML Files (*.htm, *.html)", "htm", "html"));
        int seleccion = fileChooser.showSaveDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            this.gestor.guardarFichero(fileChooser.getSelectedFile(), editorHtmlEditor.getHtmlText());
        }
    }
    @FXML
    private void handleGuardarComoAction(ActionEvent event) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.TXT", "txt");
        fileChooser.setFileFilter(filtro);
        fileChooser.setFileFilter(new FileNameExtensionFilter("HTML Files (*.htm, *.html)", "htm", "html"));
        int seleccion = fileChooser.showSaveDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            this.gestor.guardarFichero(fileChooser.getSelectedFile(), editorHtmlEditor.getHtmlText());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
