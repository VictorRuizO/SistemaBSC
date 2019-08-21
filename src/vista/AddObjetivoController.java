/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
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
import logica.AddObjetivoLogica;
import modelo.Area;

/**
 * FXML Controller class
 *
 * @author Victor
 */
public class AddObjetivoController implements Initializable {

    @FXML
    private Button cerrar;
    @FXML
    private JFXTextArea objetivo_area;
    @FXML
    private JFXButton cancelar;
    @FXML
    private JFXButton agreagar;
    @FXML
    private JFXTextArea meta_area;
    @FXML
    private JFXTextArea indicador_area;
    @FXML
    private JFXTextArea iniciativa_area;
    @FXML
    private Label label_info;
    
    private AddObjetivoLogica logicaAddObj;
    private Area area;
    private VentanaController ventP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        logicaAddObj = new AddObjetivoLogica();
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
        if(objetivo_area.getText().equals("")){
            label_info.setText("Objetivo vacio.");
            label_info.setVisible(true);
            return;
        }
        if(meta_area.getText().equals("")){
            label_info.setText("Meta vacio.");
            label_info.setVisible(true);
            return;
        }
        if(indicador_area.getText().equals("")){
            label_info.setText("Indicador vacio.");
            label_info.setVisible(true);
            return;
        }
        if(iniciativa_area.getText().equals("")){
            label_info.setText("Iniciativa vacio.");
            label_info.setVisible(true);
            return;
        }
        
        logicaAddObj.agregarObjetivo(objetivo_area.getText(), meta_area.getText(), iniciativa_area.getText(), indicador_area.getText(), area);
        ventP.update();
        exit(null);
        
    }
    
    public void recibirParametros(Area a, VentanaController v){
        area=a;
        ventP=v;
    }
    
}
