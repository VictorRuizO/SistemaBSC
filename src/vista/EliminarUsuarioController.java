/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import logica.UsuarioLogica;
import modelo.Usuario;

/**
 * FXML Controller class
 *Esta clase el la clase controladora de la interfaz eliminar usuarios
 * @author Victor
 */
public class EliminarUsuarioController implements Initializable {

    @FXML
    private Button cerrar;
    @FXML
    private JFXComboBox<String> usuarios;
    @FXML
    private JFXTextField fecha_nac;
    @FXML
    private JFXTextField contrasena;
    @FXML
    private JFXTextField apellidos;
    @FXML
    private JFXTextField nombres;
    @FXML
    private JFXTextField di;
    @FXML
    private JFXTextField area;
    @FXML
    private JFXTextField mail;
    
    private UsuarioLogica logicaUsu;
    private Usuario usuSel;
    private List<Usuario> listaUsu;
    @FXML
    private Label label_info;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        logicaUsu=new UsuarioLogica();
        listaUsu=logicaUsu.obtenerUsuarios();
        usuSel = null;
        usuarios.getEditor().setOnKeyPressed(new EventHandler<KeyEvent>(){

            @Override
            public void handle(KeyEvent event) {
                filter(usuarios.getValue());
            }

        });
        setUsuarios();
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

    private void setUsuarios(){
        usuarios.getItems().clear();
        for(Usuario u:listaUsu)
            usuarios.getItems().add(u.getNombres()+" "+u.getApellidos()+" - "+u.getDi());
    }

    private void setLabel() {
        label_info.setVisible(false);
    }


    @FXML
    private void eliminar(MouseEvent event) {
        if(usuSel==null){
            label_info.setText("No se ha seleccionado usuario.");
            label_info.setVisible(true);
            return;
        }
        logicaUsu.eliminarUsuario(usuSel.getDi());
        exit(null);
    }
    
    private void filter(String st){
        
        
        if(st.equals("")){
            setUsuarios();
            return;
        }
        
        setUsuarios();
        List<String> newL = new ArrayList<>();
        for(String s:usuarios.getItems()){
            if(s.startsWith(st)){
                newL.add(s);
            }
        }
        usuarios.getItems().clear();
        usuarios.getItems().addAll(newL);
            
        
        
    }

    @FXML
    private void accion(ActionEvent event) {
        setLabel();
        try {
            String[] g = usuarios.getSelectionModel().getSelectedItem().split(" ");
            String s=g[g.length-1];
            usuSel = logicaUsu.getUsuario(s);
            
            if(usuSel==null) return;
            
            di.setText(usuSel.getDi());
            nombres.setText(usuSel.getNombres());
            apellidos.setText(usuSel.getApellidos());
            String fecha = (new SimpleDateFormat("dd/MM/yyyy")).format(usuSel.getFechaNac());
            fecha_nac.setText(fecha);
            mail.setText(usuSel.getEmail());
            contrasena.setText(usuSel.getContrasena());
            area.setText(usuSel.getCodArea().getNombre());
            
        } catch (Exception e) {
        }
    }
    
}
