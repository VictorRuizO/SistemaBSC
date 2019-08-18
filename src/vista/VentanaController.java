/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
package vista;

import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBuilder;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Victor
 */
public class VentanaController implements Initializable {

    @FXML
    private Button cerrar;
    @FXML
    private Button add_obj_1;
    @FXML
    private Button rem_obj_1;
    @FXML
    private Button upd_obj_1;
    
    @FXML
    private Button add_ind_1;
    @FXML
    private Button rem_ind_1;
    @FXML
    private Button upd_ind_1;
    
    @FXML
    private ImageView logo;
    @FXML
    private JFXListView<TextFlow> list_obj_1;
    @FXML
    private JFXListView<TextFlow> list_ind_1;
    @FXML
    private Button add_usuario;
    @FXML
    private Button mod_usuario;
    @FXML
    private Button elim_usuario;
    @FXML
    private AnchorPane main_pane;
    @FXML
    private Button min;
    @FXML
    private Label labelUsuario;
    @FXML
    private Label labelArea;
    @FXML
    private JFXListView<?> list_obj_2;
    @FXML
    private JFXListView<?> list_ind_2;
    @FXML
    private Button add_obj_2;
    @FXML
    private Button rem_obj_2;
    @FXML
    private Button upd_obj_2;
    @FXML
    private Button add_ind_2;
    @FXML
    private Button rem_ind_2;
    @FXML
    private Button upd_ind_2;
    @FXML
    private JFXListView<?> list_obj_3;
    @FXML
    private JFXListView<?> list_ind_3;
    @FXML
    private Button add_obj_3;
    @FXML
    private Button rem_obj_3;
    @FXML
    private Button upd_obj_3;
    @FXML
    private JFXListView<?> list_obj_4;
    @FXML
    private JFXListView<?> list_ind_4;
    @FXML
    private Button add_obj_4;
    @FXML
    private Button rem_obj_4;
    @FXML
    private Button upd_obj_4;
    @FXML
    private Button add_ind_4;
    @FXML
    private Button rem_ind_4;
    @FXML
    private Button upd_ind_4;
    @FXML
    private Button metas1;
    @FXML
    private Button inici1;
    @FXML
    private Button metas2;
    @FXML
    private Button inici2;
    @FXML
    private Button metas3;
    @FXML
    private Button inici3;
    @FXML
    private Button metas4;
    @FXML
    private Button inici4;
    @FXML
    private Button add_ind_3;
    @FXML
    private Button rem_ind_3;
    @FXML
    private Button upd_ind_3;

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setIcons();
        
         for (int i=0;i<30;i++){
            TextFlow tf = new TextFlow();
            tf.setTextAlignment(TextAlignment.JUSTIFY); 
            tf.setLineSpacing(1.0); 
            tf.setPrefWidth(5);
            
            
            final Text leftText = TextBuilder.create()
                .text("Left bdadg aigd sg asdgai dgsa dgasidyga disgadil gsail dgas dihgasidagsdia gsd agsd agds yagd ydgasdad ada sda dsad d sada sda ds ad d asd a dsa d   ")
                .translateX(0)
                .font(Font.font(null, FontWeight.NORMAL, 11))
                .build();
            
            tf.getChildren().add(leftText);
            list_obj_1.getItems().add(tf);
            //list_ini_1.getItems().add(tf);
         }
         
    }    
    

    @FXML
    private void exit(MouseEvent event) {
        Window window = ((Node)(event.getSource())).getScene().getWindow();   
        System.out.println("asdadsd");
        if (window instanceof Stage){
            ((Stage) window).close();
        }
    }
    
    @FXML
    private void add(MouseEvent event) {
        try {
            FXMLLoader root = new FXMLLoader();
            root.setLocation(getClass().getResource("add_usuario.fxml"));
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root.load()));
            stage.show();
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void mod(MouseEvent event) {
        try {
            FXMLLoader root = new FXMLLoader();
            root.setLocation(getClass().getResource("modificar_usuario.fxml"));
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root.load()));
            stage.show();
            //main_pane.setDisable(true);
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void elim(MouseEvent event) {
        try {
            FXMLLoader root = new FXMLLoader();
            root.setLocation(getClass().getResource("eliminar_usuario.fxml"));
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root.load()));
            stage.show();
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void setIcons(){
        
        URL linkCerrar = getClass().getResource("/imagenes/close.png");
        Image imgCerrar = new Image(linkCerrar.toString(),20,20,false,true);
        cerrar.setGraphic((new ImageView(imgCerrar)));
        
        URL linkmin = getClass().getResource("/imagenes/window-minimize.png");
        Image imgmin = new Image(linkmin.toString(),18,18,false,true);
        min.setGraphic((new ImageView(imgmin)));
        
        URL linkAdd = getClass().getResource("/imagenes/plus.png");
        Image imgAdd = new Image(linkAdd.toString(),13,13,false,true);
        add_obj_1.setGraphic((new ImageView(imgAdd)));
        add_ind_1.setGraphic((new ImageView(imgAdd)));
        add_obj_2.setGraphic((new ImageView(imgAdd)));
        add_ind_2.setGraphic((new ImageView(imgAdd)));
        add_obj_3.setGraphic((new ImageView(imgAdd)));
        add_ind_3.setGraphic((new ImageView(imgAdd)));
        add_obj_4.setGraphic((new ImageView(imgAdd)));
        add_ind_4.setGraphic((new ImageView(imgAdd)));
        
        URL linkRem = getClass().getResource("/imagenes/minus.png");
        Image imgRem = new Image(linkRem.toString(),13,13,false,true);
        rem_obj_1.setGraphic((new ImageView(imgRem)));
        rem_ind_1.setGraphic((new ImageView(imgRem)));
        rem_obj_2.setGraphic((new ImageView(imgRem)));
        rem_ind_2.setGraphic((new ImageView(imgRem)));
        rem_obj_3.setGraphic((new ImageView(imgRem)));
        rem_ind_3.setGraphic((new ImageView(imgRem)));
        rem_obj_4.setGraphic((new ImageView(imgRem)));
        rem_ind_4.setGraphic((new ImageView(imgRem)));
        
        URL linkUpd = getClass().getResource("/imagenes/settings.png");
        Image imgUpd = new Image(linkUpd.toString(),13,13,false,true);
        upd_obj_1.setGraphic((new ImageView(imgUpd)));
        upd_ind_1.setGraphic((new ImageView(imgUpd)));
        upd_obj_2.setGraphic((new ImageView(imgUpd)));
        upd_ind_2.setGraphic((new ImageView(imgUpd)));
        upd_obj_3.setGraphic((new ImageView(imgUpd)));
        upd_ind_3.setGraphic((new ImageView(imgUpd)));
        upd_obj_4.setGraphic((new ImageView(imgUpd)));
        upd_ind_4.setGraphic((new ImageView(imgUpd)));
        
        URL linkAddUser = getClass().getResource("/imagenes/account-plus.png");
        Image imgAddUser = new Image(linkAddUser.toString(),20,20,false,true);
        add_usuario.setGraphic((new ImageView(imgAddUser)));
        
        URL linkElimUser = getClass().getResource("/imagenes/account-minus.png");
        Image imgElimUser = new Image(linkElimUser.toString(),20,20,false,true);
        elim_usuario.setGraphic((new ImageView(imgElimUser)));
        
        URL linkModUser = getClass().getResource("/imagenes/account-edit.png");
        Image imgModUser = new Image(linkModUser.toString(),20,20,false,true);
        mod_usuario.setGraphic((new ImageView(imgModUser)));
        
        URL linkLog = getClass().getResource("/imagenes/logo.png");
        Image imgLog = new Image(linkLog.toString(),200,200,false,true);
        logo.setImage(imgLog);
        
        
        
    }

    @FXML
    private void addObj(MouseEvent event) {
        try {
            FXMLLoader root = new FXMLLoader();
            root.setLocation(getClass().getResource("add_objetivo.fxml"));
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root.load()));
            stage.show();
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void elimObj(MouseEvent event) {
         try {
            FXMLLoader root = new FXMLLoader();
            root.setLocation(getClass().getResource("elim_objetivo.fxml"));
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root.load()));
            stage.show();
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void modObj(MouseEvent event) {
        try {
            FXMLLoader root = new FXMLLoader();
            root.setLocation(getClass().getResource("mod_objetivo.fxml"));
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root.load()));
            stage.show();
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void minimizar(MouseEvent event) {
        Window window = ((Node)(event.getSource())).getScene().getWindow(); 
        if (window instanceof Stage){
            ((Stage) window).setIconified(true);
        }
    }
    
}
