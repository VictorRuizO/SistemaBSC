/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import logica.ModObjetivoLogica;
import modelo.Objetivo;

/**
 * FXML Controller class
 *Esta clase el la clase controladora de la interfaz de modificar objetivo
 * @author Victor
 */
public class ModObjetivoController implements Initializable {

    @FXML
    private Button cerrar;
    
    private Objetivo obj;
    @FXML
    private JFXTextArea descripcion;
    @FXML
    private JFXTextField codigo;
    
    @FXML
    private Label label_info;
    
    private VentanaController ventP;
    private String descAnt;
    private ModObjetivoLogica logicaMod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setIcons();
        logicaMod=new ModObjetivoLogica();
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
        
        URL linkLabel = getClass().getResource("/imagenes/error.png");
        Image imgLabel = new Image(linkLabel.toString(),20,20,false,true);
        label_info.setGraphic((new ImageView(imgLabel)));
    }
    /**
     * Recibe los paramentros necesarios para la interfaz
     * @param o Objetivo a modificar
     * @param v Ventana principal
     */  
    public void recibeParametros(Objetivo o,VentanaController v){
        obj=o;
        ventP=v;
        codigo.setText(String.format("%4d", obj.getCodigo()).replace(' ','0'));
        descripcion.setText(obj.getDescripcion());
        descAnt=obj.getDescripcion();
    }

    @FXML
    private void modificar(MouseEvent event) {
        if(descripcion.getText().equals(descAnt)){
            label_info.setText("No se ha modificado el objetivo.");
            label_info.setVisible(true);
            return;
        }
        obj.setDescripcion(descripcion.getText());
        logicaMod.modificarObjetivo(obj);
        ventP.update();
        exit(null);
    }
    
}
