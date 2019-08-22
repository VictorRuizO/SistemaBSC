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
import logica.IniciativaLogica;
import modelo.Iniciativa;
import modelo.Meta;
import modelo.Objetivo;

/**
 * FXML Controller class
 *
 * @author Victor
 */
public class IniciativasController implements Initializable {

    @FXML
    private JFXButton cerrar;
    @FXML
    private JFXTextField codigo_obj;
    @FXML
    private JFXTextArea desc_area;
    @FXML
    private Label label_info;
    
    
    private VentanaController ventP;
    private Objetivo obj;
    private IniciativaLogica logicaIni;
    @FXML
    private JFXListView<TextFlow> list_ini;
    @FXML
    private JFXButton add_ini;
    @FXML
    private JFXButton rem_ini;
    @FXML
    private JFXButton upd_ini;
    @FXML
    private JFXTextField codigo_ini;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        logicaIni = new IniciativaLogica();
        setIcons();
    }    

    @FXML
    private void addini(MouseEvent event) {
        setLabel(null);
        if(desc_area.getText().equals("")){
            label_info.setText("Descripción vacia.");
            label_info.setVisible(true);
            return;
        }
        
        try {
            Iniciativa met = logicaIni.getIniciativa(list_ini.getSelectionModel().getSelectedItem().getId());
            if(desc_area.getText().equals(met.getDescripcion())){
                label_info.setText("Ya existe una iniciativa con esa descripción.");
                label_info.setVisible(true);
                return;
            }
        } catch (Exception e) {
        }
        
        logicaIni.agregarIniciativa(desc_area.getText(),obj);
        label_info.setText("Iniciativa agregada con éxito.");
        label_info.setVisible(true);
        obj=logicaIni.obtenerObjetivo(obj);
        update();
    }

    @FXML
    private void elimIni(MouseEvent event) {
        setLabel(null);
        
        try {
            String id = list_ini.getSelectionModel().getSelectedItem().getId();
             logicaIni.eliminarIniciativa(id);
            label_info.setText("La iniciativa se eliminó con éxito.");
            label_info.setVisible(true);
            obj=logicaIni.obtenerObjetivo(obj);
            update();   
            
        } catch (Exception e) {
                label_info.setText("No se ha seleccionado un iniciativa.");
                label_info.setVisible(true);
                return;
        }
    }

    @FXML
    private void modIni(MouseEvent event) {
        setLabel(null);
        if(desc_area.getText().equals("")){
            label_info.setText("Descripción vacia.");
            label_info.setVisible(true);
            return;
        }
        Iniciativa met;
        try {
            met = logicaIni.getIniciativa(list_ini.getSelectionModel().getSelectedItem().getId());
            if(desc_area.getText().equals(met.getDescripcion())){
                label_info.setText("No se ha modificado la iniciativa.");
                label_info.setVisible(true);
                return;
            }
        } catch (Exception e) {
            label_info.setText("No se ha seleccionado una iniciativa.");
            label_info.setVisible(true);
            return;
        }
        
        logicaIni.modificarIniciativa(desc_area.getText(),met);
        label_info.setText("Meta modificada con éxito.");
        label_info.setVisible(true);
        obj=logicaIni.obtenerObjetivo(obj);
        update();
    }

    @FXML
    private void exit(MouseEvent event) {
        Window window = ((Node)(event.getSource())).getScene().getWindow(); 
        if (window instanceof Stage){
            ((Stage) window).close();
        }
    }

    @FXML
    private void setLabel(MouseEvent event) {
        label_info.setVisible(false);
    }
    
    public void update(){
        list_ini.getItems().clear();
        desc_area.setText("");
        codigo_ini.setText("Código Iniciativa");
        for(Iniciativa ini:obj.getIniciativaList()){
            TextFlow tf = new TextFlow();
            tf.setTextAlignment(TextAlignment.JUSTIFY); 
            tf.setLineSpacing(1.0); 
            tf.setPrefWidth(5);
            
            
            final Text leftText = TextBuilder.create()
                .text(ini.getDescripcion())
                .translateX(0)
                .font(Font.font(null, FontWeight.NORMAL, 11))
                .build();
            tf.setId(ini.getCodigo()+"");
            tf.getChildren().add(leftText);           
            list_ini.getItems().add(tf);
        }
    }
    
    private void setIcons(){
        
        URL linkUpd = getClass().getResource("/imagenes/settings.png");
        Image imgUpd = new Image(linkUpd.toString(),13,13,false,true);
        upd_ini.setGraphic((new ImageView(imgUpd)));
        
        URL linkRem = getClass().getResource("/imagenes/minus.png");
        Image imgRem = new Image(linkRem.toString(),13,13,false,true);
        rem_ini.setGraphic((new ImageView(imgRem)));
        
        URL linkAdd = getClass().getResource("/imagenes/plus.png");
        Image imgAdd = new Image(linkAdd.toString(),13,13,false,true);
        add_ini.setGraphic((new ImageView(imgAdd)));
        
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
            
            Iniciativa met = logicaIni.getIniciativa(list_ini.getSelectionModel().getSelectedItem().getId());
        
            desc_area.setText(met.getDescripcion());
            codigo_ini.setText(codigo_ini.getPromptText()+": "+String.format("%4d", met.getCodigo()).replace(' ','0'));
            
        } catch (Exception e) {
        }
        
    }

    
    
    
}
