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
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Separator;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
    private Button add_met_1;
    @FXML
    private Button rem_met_1;
    @FXML
    private Button upd_met_1;
    @FXML
    private Button add_ind_1;
    @FXML
    private Button rem_ind_1;
    @FXML
    private Button upd_ind_1;
    @FXML
    private Button add_ini_1;
    @FXML
    private Button rem_ini_1;
    @FXML
    private Button upd_ini_1;
    @FXML
    private Circle logo;
    @FXML
    private JFXListView<TextFlow> list_obj_1;
    @FXML
    private JFXListView<TextFlow> list_met_1;
    @FXML
    private JFXListView<TextFlow> list_ind_1;
    @FXML
    private JFXListView<TextFlow> list_ini_1;
    @FXML
    private Button add_usuario;
    @FXML
    private Button mod_usuario;
    @FXML
    private Button elim_usuario;
    @FXML
    private JFXListView<?> list_obj_11;
    @FXML
    private JFXListView<?> list_met_2;
    @FXML
    private JFXListView<?> list_ind_11;
    @FXML
    private JFXListView<?> list_ini_11;
    @FXML
    private Button add_obj_11;
    @FXML
    private Button rem_obj_11;
    @FXML
    private Button upd_obj_11;
    @FXML
    private Button add_met_11;
    @FXML
    private Button rem_met_11;
    @FXML
    private Button upd_met_11;
    @FXML
    private Button add_ind_11;
    @FXML
    private Button rem_ind_11;
    @FXML
    private Button upd_ind_11;
    @FXML
    private Button add_ini_11;
    @FXML
    private Button rem_ini_11;
    @FXML
    private Button upd_ini_11;
    @FXML
    private JFXListView<?> list_obj_12;
    @FXML
    private JFXListView<?> list_met_11;
    @FXML
    private JFXListView<?> list_ind_12;
    @FXML
    private JFXListView<?> list_ini_12;
    @FXML
    private Button add_obj_12;
    @FXML
    private Button rem_obj_12;
    @FXML
    private Button upd_obj_12;
    @FXML
    private Button add_met_12;
    @FXML
    private Button rem_met_12;
    @FXML
    private Button upd_met_12;
    @FXML
    private Button add_ind_12;
    @FXML
    private Button rem_ind_12;
    @FXML
    private Button upd_ind_12;
    @FXML
    private Button add_ini_12;
    @FXML
    private Button rem_ini_12;
    @FXML
    private Button upd_ini_12;
    @FXML
    private JFXListView<?> list_obj_13;
    @FXML
    private JFXListView<?> list_met_12;
    @FXML
    private JFXListView<?> list_ind_13;
    @FXML
    private JFXListView<?> list_ini_13;
    @FXML
    private Button add_obj_13;
    @FXML
    private Button rem_obj_13;
    @FXML
    private Button upd_obj_13;
    @FXML
    private Button add_met_13;
    @FXML
    private Button rem_met_13;
    @FXML
    private Button upd_met_13;
    @FXML
    private Button add_ind_13;
    @FXML
    private Button rem_ind_13;
    @FXML
    private Button upd_ind_13;
    @FXML
    private Button add_ini_13;
    @FXML
    private Button rem_ini_13;
    @FXML
    private Button upd_ini_13;
    @FXML
    private AnchorPane main_pane;

    

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
                .font(Font.font(null, FontWeight.NORMAL, 9))
                .build();
            
            tf.getChildren().add(leftText);
            list_obj_1.getItems().add(tf);
            //list_ini_1.getItems().add(tf);
         }
         
    }    
    

    @FXML
    private void exit(MouseEvent event) {
        Window window = ((Node)(event.getSource())).getScene().getWindow();   

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
        
        URL linkAdd = getClass().getResource("/imagenes/plus.png");
        Image imgAdd = new Image(linkAdd.toString(),13,13,false,true);
        add_obj_1.setGraphic((new ImageView(imgAdd)));
        add_met_1.setGraphic((new ImageView(imgAdd)));
        add_ind_1.setGraphic((new ImageView(imgAdd)));
        add_ini_1.setGraphic((new ImageView(imgAdd)));
        
        URL linkRem = getClass().getResource("/imagenes/minus.png");
        Image imgRem = new Image(linkRem.toString(),13,13,false,true);
        rem_obj_1.setGraphic((new ImageView(imgRem)));
        rem_met_1.setGraphic((new ImageView(imgRem)));
        rem_ind_1.setGraphic((new ImageView(imgRem)));
        rem_ini_1.setGraphic((new ImageView(imgRem)));
        
        URL linkUpd = getClass().getResource("/imagenes/settings.png");
        Image imgUpd = new Image(linkUpd.toString(),13,13,false,true);
        upd_obj_1.setGraphic((new ImageView(imgUpd)));
        upd_met_1.setGraphic((new ImageView(imgUpd)));
        upd_ind_1.setGraphic((new ImageView(imgUpd)));
        upd_ini_1.setGraphic((new ImageView(imgUpd)));
        
        URL linkLogo = getClass().getResource("/imagenes/settings.png");
        Image imgLogo = new Image(linkLogo.toString(),100,100,false,true);
        //logo.setGraphic((new ImageView(imgLogo)));
        
    }
    
}
