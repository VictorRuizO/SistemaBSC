/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Separator;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBuilder;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
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
    private ScrollPane scp_obj_1;
    @FXML
    private VBox vbox_obj_1;
    @FXML
    private ScrollPane scp_met_1;
    @FXML
    private VBox vbox_met_1;
    @FXML
    private ScrollPane scp_ind_1;
    @FXML
    private VBox vbox_ind_1;
    @FXML
    private ScrollPane scp_ini_1;
    @FXML
    private VBox vbox_ini_1;
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

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setIcons();
        
        vbox_obj_1.setSpacing(10);
        vbox_obj_1.setPrefWidth(120);
        vbox_obj_1.setPadding(new Insets(0, 5, 0, 5));
        scp_obj_1.setHbarPolicy(ScrollBarPolicy.NEVER);
        scp_obj_1.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        //scp.set
        for (int i=0;i<30;i++){
            TextFlow tf = new TextFlow();
            tf.setTextAlignment(TextAlignment.JUSTIFY); 
            tf.setLineSpacing(1.0); 
            tf.setPrefWidth(5);
            tf.setId(i+"");
            final Text leftText = TextBuilder.create()
                .text("Left bdadg aigd sg asdgai dgsa dgasidyga disgadil gsail dgas dihgasidagsdia gsd agsd agds yagd ydgasdad ada sda dsad d sada sda ds ad d asd a dsa d   ")
                .translateX(0)
                .font(Font.font(null, FontWeight.NORMAL, 9))
                .build();
            
            Separator s= new Separator();
            s.setPrefWidth(105);
            ObservableList list = tf.getChildren(); 
 
            list.addAll(leftText,s);    
            //panel_obj_fincaias.getItems().add(l);
            vbox_obj_1.getChildren().add(tf);
        }
        for (Node n:vbox_obj_1.getChildren()){
            System.out.println(n.getId());
        }
        
    }    

    @FXML
    private void exit(MouseEvent event) {
        Window window = ((Node)(event.getSource())).getScene().getWindow();   

        if (window instanceof Stage){
            ((Stage) window).close();
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
    }
    
}
