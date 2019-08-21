/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javax.swing.JOptionPane;
import logica.Login;
import modelo.Usuario;

/**
 * FXML Controller class
 *
 * @author Victor
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField usuario;
    @FXML
    private JFXPasswordField contrasena;
    @FXML
    private Button cerrar;
    @FXML
    private ImageView logo;
    @FXML
    private Button login;
    
    
    private Login logicaLogin;
    @FXML
    private Label label_info;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Eventos
        final EventHandler<KeyEvent> keyEventHandler =
        new EventHandler<KeyEvent>() {
            public void handle(final KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    login();
                }
            }
        };
        contrasena.setOnKeyReleased(keyEventHandler);
        
        logicaLogin = new Login();
        
        setIcons();
        
        
    }    

    @FXML
    private void login(MouseEvent event) {
        login();
    }
    
    @FXML
    private void setLabel(MouseEvent event) {
        label_info.setVisible(false);
    }
    
    
    
    private void login() {
        
        if(usuario.getText().equals("") || contrasena.getText().equals("")){
            //campos vacios
            label_info.setText("Campos vacios.");
            label_info.setVisible(true);
            return;
        }
        
        int respLog = logicaLogin.login(usuario.getText(), contrasena.getText());
        label_info.setVisible(false);
        
        if (respLog==0){
            //Usuario no registrado
            label_info.setText("Usuario no registrado.");
            label_info.setVisible(true);
            return;
        }
        else if(respLog==2){
            //no coincide contraseña
            label_info.setText("No coinside su usuario con su contraseña..");
            label_info.setVisible(true);
            return;
        }
        
        try {
            FXMLLoader root = new FXMLLoader();
            root.setLocation(getClass().getResource("ventana.fxml"));
            Scene sce = new Scene(root.load());
            VentanaController vc= (VentanaController)root.getController();
            vc.recibeParametrosUsuario(logicaLogin.getUsuario());
            
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Sistema BSC");
            stage.setScene(sce);
            stage.show();
            //Hide this current window (if this is what you want)
            usuario.getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void exit(MouseEvent event) {
        Window window = usuario.getScene().getWindow();   

        if (window instanceof Stage){
            ((Stage) window).close();
        }
    }
    
    
    public void setIcons(){
        //Iconos
        URL linkCerrar = getClass().getResource("/imagenes/close.png");
        Image imgCerrar = new Image(linkCerrar.toString(),20,20,false,true);
        cerrar.setGraphic((new ImageView(imgCerrar)));
        
        URL linkLogo = getClass().getResource("/imagenes/logo.png");
        Image imgLogo = new Image(linkLogo.toString(),220,220,false,true);
        logo.setImage(imgLogo);
        
        URL linkError = getClass().getResource("/imagenes/error.png");
        Image imgError = new Image(linkError.toString(),20,20,false,true);
        label_info.setGraphic((new ImageView(imgError)));
    }
    
    
    
}
