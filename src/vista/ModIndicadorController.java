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
import modelo.Indicador;

/**
 * FXML Controller class
 *
 * @author Victor
 */
public class ModIndicadorController implements Initializable {

    @FXML
    private JFXButton cerrar;
    @FXML
    private JFXTextArea descripcion;
    @FXML
    private Label label_info;
    @FXML
    private JFXTextField codigoInd;
        
    private IndicadorLogica logica;
    private Indicador ind;
    private VentanaController ventP;
    private String descAnt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        logica = new IndicadorLogica();
        
        
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

    
    public void recibeParametros(Indicador o, VentanaController v){
        ind = o;
        ventP=v;
        codigoInd.setText(codigoInd.getPromptText()+": "+String.format("%4d", ind.getCodigo()).replace(' ','0'));
        descripcion.setText(o.getDescripcion());
        descAnt=o.getDescripcion();
    }

    @FXML
    private void mod(MouseEvent event) {
        if(descripcion.getText().equals(descAnt)){
            label_info.setText("No se modicó el indicador");
            label_info.setVisible(true);
            return;
        }
        
        logica.modificarIndicador(ind,descripcion.getText());
        ventP.update();
        exit(null);
    }
    
}
