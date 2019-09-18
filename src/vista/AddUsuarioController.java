/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import logica.UsuarioLogica;
import modelo.Area;

/**
 * FXML Controller class
 *Esta clase el la clase controladora de la interfaz agregar usuarios
 * @author Victor
 */
public class AddUsuarioController implements Initializable {

    @FXML
    private Button cerrar;
    @FXML
    private JFXTextField nombres;
    @FXML
    private JFXTextField apellidos;
    @FXML
    private JFXDatePicker fecha_nac;
    @FXML
    private JFXPasswordField contrasena;
    @FXML
    private JFXPasswordField confir_contrasena;
    @FXML
    private JFXComboBox<String> area;
    @FXML
    private Label label_info;
    
    private UsuarioLogica logicaUsu;
    @FXML
    private JFXTextField di;
    @FXML
    private JFXTextField mail;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        logicaUsu=new UsuarioLogica();
        for(Area a:logicaUsu.getAreas()){
            area.getItems().add(a.getNombre());
        }
       
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
        
        URL linkError = getClass().getResource("/imagenes/error.png");
        Image imgError = new Image(linkError.toString(),20,20,false,true);
        label_info.setGraphic((new ImageView(imgError)));
    }
    
    @FXML
    private void setLabel(MouseEvent event) {
        label_info.setVisible(false);
    }

    @FXML
    private void registrar(MouseEvent event) {
        if(di.getText().equals("")){
            label_info.setText("Documento de identidad vacio.");
            label_info.setVisible(true);
            return;
        }
        
        if(logicaUsu.getUsuario(di.getText())!=null){
            label_info.setText("Usuario ya registrado.");
            label_info.setVisible(true);
            return;
        }
        
        if(nombres.getText().equals("")){
            label_info.setText("Nombres vacios.");
            label_info.setVisible(true);
            return;
        }
        
        if(apellidos.getText().equals("")){
            label_info.setText("Apellidos vacios.");
            label_info.setVisible(true);
            return;
        }
        
        if(fecha_nac.getValue()==null){
            label_info.setText("Fecha de nacimiento vacia.");
            label_info.setVisible(true);
            return;
        }
        if(mail.getText().equals("")){
            label_info.setText("Correo electronico vacio.");
            label_info.setVisible(true);
            return;
        }
        Boolean malo=true;
        for(char c:mail.getText().toCharArray()){
            if(c=='@'){
                malo = false;
                break;
            }
        }
        
        if(malo){
            label_info.setText("Correo electronico incorrecto.");
            label_info.setVisible(true);
            return;
        }
        if(contrasena.getText().equals("")){
            label_info.setText("Contraseña vacia.");
            label_info.setVisible(true);
            return;
        }
        
        if(!contrasena.getText().equals(confir_contrasena.getText())){
            label_info.setText("No coinsiden las contraseñas.");
            label_info.setVisible(true);
            return;
        }
        
        if(area.getSelectionModel().getSelectedItem().equals("")){
            label_info.setText("No se seleccionó un área.");
            label_info.setVisible(true);
            return;
        }
        
        logicaUsu.agregarUsuaio(di.getText(),nombres.getText(),apellidos.getText(),fecha_nac.getValue(),mail.getText(),contrasena.getText(),logicaUsu.getAreas().get(area.getSelectionModel().getSelectedIndex()));
        exit(null);
    }
    
}
