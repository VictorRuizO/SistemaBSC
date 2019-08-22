/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBuilder;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.Window;
import logica.MetaLogica;
import modelo.Meta;
import modelo.Objetivo;

/**
 * FXML Controller class
 *
 * @author Victor
 */
public class MetasController implements Initializable {

    @FXML
    private JFXListView<TextFlow> list_meta;
    @FXML
    private JFXButton add_meta;
    @FXML
    private JFXButton rem_meta;
    @FXML
    private JFXButton upd_meta;
    @FXML
    private JFXButton cerrar;
    
    private VentanaController ventP;
    private Objetivo obj;
    private MetaLogica logicaMeta;
    @FXML
    private JFXTextField codigo_obj;
    @FXML
    private JFXTextArea desc_area;
    @FXML
    private Label label_info;
    @FXML
    private JFXTextField codigo_meta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        logicaMeta=new MetaLogica();
        setIcons();
    }    
    
    @FXML
    private void setLabel(MouseEvent event) {
        label_info.setVisible(false);
    }

    @FXML
    private void addMeta(MouseEvent event) {
        setLabel(null);
        if(desc_area.getText().equals("")){
            label_info.setText("Descripción vacia.");
            label_info.setVisible(true);
            return;
        }
        
        try {
            Meta met = logicaMeta.getMeta(list_meta.getSelectionModel().getSelectedItem().getId());
            if(desc_area.getText().equals(met.getDescripcion())){
                label_info.setText("Ya existe una meta con esa descripción.");
                label_info.setVisible(true);
                return;
            }
        } catch (Exception e) {
        }
        
        logicaMeta.agregarMeta(desc_area.getText(),obj);
        label_info.setText("Meta agregada con éxito.");
        label_info.setVisible(true);
        obj=logicaMeta.obtenerObjetivo(obj);
        update();
    }

    @FXML
    private void elimMeta(MouseEvent event) {
        setLabel(null);
        
        try {
            String id = list_meta.getSelectionModel().getSelectedItem().getId();
             logicaMeta.eliminarMeta(id);
            label_info.setText("La meta se eliminó con éxito.");
            label_info.setVisible(true);
            obj=logicaMeta.obtenerObjetivo(obj);
            update();   
            
        } catch (Exception e) {
                label_info.setText("No se ha seleccionado una meta.");
                label_info.setVisible(true);
                return;
        }
        
        
    }

    @FXML
    private void modMeta(MouseEvent event) {
        setLabel(null);
        if(desc_area.getText().equals("")){
            label_info.setText("Descripción vacia.");
            label_info.setVisible(true);
            return;
        }
        Meta met;
        try {
            met = logicaMeta.getMeta(list_meta.getSelectionModel().getSelectedItem().getId());
            if(desc_area.getText().equals(met.getDescripcion())){
                label_info.setText("No se ha modificado la meta.");
                label_info.setVisible(true);
                return;
            }
        } catch (Exception e) {
            label_info.setText("No se ha seleccionado una meta.");
            label_info.setVisible(true);
            return;
        }
        
        logicaMeta.modificarMeta(desc_area.getText(),met);
        label_info.setText("Meta modificada con éxito.");
        label_info.setVisible(true);
        obj=logicaMeta.obtenerObjetivo(obj);
        update();
    }

    @FXML
    private void exit(MouseEvent event) {
        Window window = ((Node)(event.getSource())).getScene().getWindow(); 
        if (window instanceof Stage){
            ((Stage) window).close();
        }
    }
    
    
    public void update(){
        list_meta.getItems().clear();
        desc_area.setText("");
        codigo_meta.setText("Código Meta");
        for(Meta meta:obj.getMetaList()){
            TextFlow tf = new TextFlow();
            tf.setTextAlignment(TextAlignment.JUSTIFY); 
            tf.setLineSpacing(1.0); 
            tf.setPrefWidth(5);
            
            
            final Text leftText = TextBuilder.create()
                .text(meta.getDescripcion())
                .translateX(0)
                .font(Font.font(null, FontWeight.NORMAL, 11))
                .build();
            tf.setId(meta.getCodigo()+"");
            tf.getChildren().add(leftText);           
            list_meta.getItems().add(tf);
        }
    }
    
    private void setIcons(){
        
        URL linkUpd = getClass().getResource("/imagenes/settings.png");
        Image imgUpd = new Image(linkUpd.toString(),13,13,false,true);
        upd_meta.setGraphic((new ImageView(imgUpd)));
        
        URL linkRem = getClass().getResource("/imagenes/minus.png");
        Image imgRem = new Image(linkRem.toString(),13,13,false,true);
        rem_meta.setGraphic((new ImageView(imgRem)));
        
        URL linkAdd = getClass().getResource("/imagenes/plus.png");
        Image imgAdd = new Image(linkAdd.toString(),13,13,false,true);
        add_meta.setGraphic((new ImageView(imgAdd)));
        
        URL linkCerrar = getClass().getResource("/imagenes/close.png");
        Image imgCerrar = new Image(linkCerrar.toString(),20,20,false,true);
        cerrar.setGraphic((new ImageView(imgCerrar)));
        
        URL linkError = getClass().getResource("/imagenes/error.png");
        Image imgError = new Image(linkError.toString(),20,20,false,true);
        label_info.setGraphic((new ImageView(imgError)));
    }
    
    public void recibeParametros(Objetivo o,VentanaController v){
        obj=o;
        ventP=v;
        codigo_obj.setText(codigo_obj.getPromptText()+": "+String.format("%4d", obj.getCodigo()).replace(' ','0'));
        update();
    }

    @FXML
    private void accionList(MouseEvent event) {
        setLabel(null);
        try {
            
            Meta met = logicaMeta.getMeta(list_meta.getSelectionModel().getSelectedItem().getId());
        
            desc_area.setText(met.getDescripcion());
            codigo_meta.setText(codigo_meta.getPromptText()+": "+String.format("%4d", met.getCodigo()).replace(' ','0'));
            
        } catch (Exception e) {
        }
        
    }
    
}
