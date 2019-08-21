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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import logica.ElimObjetivoLogica;
import modelo.Objetivo;

/**
 * FXML Controller class
 *
 * @author Victor
 */
public class ElimObjetivoController implements Initializable {

    @FXML
    private Button cerrar;
    @FXML
    private JFXTextArea descripcion;
    @FXML
    private JFXTextField codigo;
    
    
    private Objetivo obj;
    private ElimObjetivoLogica logic;
    private VentanaController ventP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        logic=new ElimObjetivoLogica();
        setIcons();
    }    

    @FXML
    private void exit(MouseEvent event) {
        Window window = cerrar.getScene().getWindow();   

        if (window instanceof Stage){
            ((Stage) window).close();
        }
    }
    
    private void setIcons(){
        URL linkCerrar = getClass().getResource("/imagenes/close.png");
        Image imgCerrar = new Image(linkCerrar.toString(),20,20,false,true);
        cerrar.setGraphic((new ImageView(imgCerrar)));
        
        
    }
    public void recibeParametros(Objetivo o,VentanaController v){
        obj=o;
        ventP=v;
        codigo.setText(String.format("%4d", obj.getCodigo()).replace(' ','0'));
        descripcion.setText(obj.getDescripcion());
    }

    @FXML
    private void eliminar(MouseEvent event) {
        logic.eliminar(obj);
        ventP.update();
        exit(null);
        
    }
    
}
