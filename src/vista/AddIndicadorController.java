/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import logica.IndicadorLogica;
import modelo.Objetivo;

/**
 * FXML Controller class
 *Esta clase el la clase controladora de la interfaz agregar indicador
 * @author Victor
 */
public class AddIndicadorController implements Initializable {

    @FXML
    private JFXButton cerrar;
    @FXML
    private JFXTextArea descripcion;
    @FXML
    private JFXTextField codigoObj;
    @FXML
    private Label label_info;
    
    private IndicadorLogica logicaAdd;
    private Objetivo obj;
    private VentanaController ventP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        logicaAdd = new IndicadorLogica();
        
        
        setIcons();
    }    
    @FXML
    private void exit(MouseEvent event) {
        Window window = cerrar.getScene().getWindow();   

        if (window instanceof Stage){
            ((Stage) window).close();
        }
    }
    
   @FXML
    private void setLabel(MouseEvent event) {
        label_info.setVisible(false);
    }
    
     private void setIcons(){
        URL linkCerrar = getClass().getResource("/imagenes/close.png");
        Image imgCerrar = new Image(linkCerrar.toString(),20,20,false,true);
        cerrar.setGraphic((new ImageView(imgCerrar)));
        
        URL linkError = getClass().getResource("/imagenes/error.png");
        Image imgError = new Image(linkError.toString(),20,20,false,true);
        label_info.setGraphic((new ImageView(imgError)));
    }
    @FXML
    private void agg(MouseEvent event) {
        if(descripcion.getText().equals("")){
            label_info.setText("Descripción vacia.");
            label_info.setVisible(true);
            return;
        }
        
        logicaAdd.agregarIndicador(descripcion.getText(),obj);
        ventP.update();
        exit(null);
        
    }
    /**
     * Recibe los paramentros necesarios para la interfaz
     * @param o Objetivo del indicador
     * @param v Ventana principal
     */    
    public void recibeParametros(Objetivo o, VentanaController v){
        obj = o;
        ventP=v;
        codigoObj.setText(codigoObj.getPromptText()+": "+String.format("%4d", obj.getCodigo()).replace(' ','0'));
    }

    
}
