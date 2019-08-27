/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
package vista;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
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
import logica.VentanaLogica;
import modelo.Administrador;
import modelo.Area;
import modelo.Indicador;
import modelo.Objetivo;
import modelo.Usuario;

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
    private JFXListView<TextFlow> list_obj_2;
    @FXML
    private JFXListView<TextFlow> list_ind_2;
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
    private JFXListView<TextFlow> list_obj_3;
    @FXML
    private JFXListView<TextFlow> list_ind_3;
    @FXML
    private Button add_obj_3;
    @FXML
    private Button rem_obj_3;
    @FXML
    private Button upd_obj_3;
    @FXML
    private JFXListView<TextFlow> list_obj_4;
    @FXML
    private JFXListView<TextFlow> list_ind_4;
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

    private Usuario usuarioLog;
    private Boolean esUsuario;
    private Administrador adminLog;
    private VentanaLogica logicaVent;
    @FXML
    private JFXButton upd_boton;
    @FXML
    private JFXButton bbdd_settings;
    @FXML
    private Label label_info;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setIcons();
        logicaVent=new VentanaLogica(); 
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
        setLabel();
        try {
            FXMLLoader root = new FXMLLoader();
            root.setLocation(getClass().getResource("add_usuario.fxml"));
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Agregar Usuario");
            stage.setScene(new Scene(root.load()));
            stage.show();
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void mod(MouseEvent event) {
        setLabel();
        try {
            FXMLLoader root = new FXMLLoader();
            root.setLocation(getClass().getResource("modificar_usuario.fxml"));
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Modificar Usuario");
            stage.setScene(new Scene(root.load()));
            stage.show();
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void elim(MouseEvent event) {
        setLabel();
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
        
        URL linkMeta = getClass().getResource("/imagenes/flag-goal.png");
        Image imgMeta = new Image(linkMeta.toString(),20,20,false,true);
        metas1.setGraphic((new ImageView(imgMeta)));
        metas2.setGraphic((new ImageView(imgMeta)));
        metas3.setGraphic((new ImageView(imgMeta)));
        metas4.setGraphic((new ImageView(imgMeta)));
        
        URL linkIni = getClass().getResource("/imagenes/light.png");
        Image imgIni = new Image(linkIni.toString(),20,20,false,true);
        inici1.setGraphic((new ImageView(imgIni)));
        inici2.setGraphic((new ImageView(imgIni)));
        inici3.setGraphic((new ImageView(imgIni)));
        inici4.setGraphic((new ImageView(imgIni)));
        
        URL linkUpdb = getClass().getResource("/imagenes/update.png");
        Image imgUpdb = new Image(linkUpdb.toString(),20,20,false,true);
        upd_boton.setGraphic((new ImageView(imgUpdb)));
        
        URL linkBD = getClass().getResource("/imagenes/database-settings.png");
        Image imgBD = new Image(linkBD.toString(),20,20,false,true);
        bbdd_settings.setGraphic((new ImageView(imgBD)));
        
        URL linkError = getClass().getResource("/imagenes/error.png");
        Image imgError = new Image(linkError.toString(),20,20,false,true);
        label_info.setGraphic((new ImageView(imgError)));
    }

    @FXML
    private void addObj(MouseEvent event) {
        setLabel();
        try {
            String[] areas = logicaVent.getAreas();
            String a="";
            if(event.getSource()==add_obj_1){
                a=areas[0];
            }
            else if(event.getSource()==add_obj_2){
                a=areas[1];
            }
            else if(event.getSource()==add_obj_3){
                a=areas[2];
            }
            else if(event.getSource()==add_obj_4){
                a=areas[3];
            }
            FXMLLoader root = new FXMLLoader();
            root.setLocation(getClass().getResource("add_objetivo.fxml"));
            Scene sce = new Scene(root.load());
            AddObjetivoController add = (AddObjetivoController)root.getController();
            add.recibirParametros(logicaVent.getArea(a),this);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Agregar Objetivo");
            stage.setScene(sce);
            stage.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            
        }
       
        
    }

    @FXML
    private void elimObj(MouseEvent event) {
        setLabel();
         try {
             Objetivo obj=null;
             if(event.getSource()==rem_obj_1){
                 String cod = list_obj_1.getSelectionModel().getSelectedItem().getId();
                obj = logicaVent.getObjetivo(cod);
             }
             else if(event.getSource()==rem_obj_2){
                 String cod = list_obj_2.getSelectionModel().getSelectedItem().getId();
                obj = logicaVent.getObjetivo(cod);
             }
             else if(event.getSource()==rem_obj_3){
                 String cod = list_obj_3.getSelectionModel().getSelectedItem().getId();
                obj = logicaVent.getObjetivo(cod);
             }
             else if(event.getSource()==rem_obj_4){
                 String cod = list_obj_4.getSelectionModel().getSelectedItem().getId();
                obj = logicaVent.getObjetivo(cod);
             }
            
             
            FXMLLoader root = new FXMLLoader();
            root.setLocation(getClass().getResource("elim_objetivo.fxml"));
            Scene sce = new Scene(root.load());
            ElimObjetivoController ec= (ElimObjetivoController)root.getController();
            ec.recibeParametros(obj,this);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Eliminar Objetivo");
            stage.setScene(sce);
            stage.show();
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
         catch(RuntimeException e){
            label_info.setText("Objetivo no seleccionado.");    
            label_info.setVisible(true);             
         }
    }

    @FXML
    private void modObj(MouseEvent event) {
        setLabel();
        try {
             Objetivo obj=null;
             if(event.getSource()==upd_obj_1){
                 String cod = list_obj_1.getSelectionModel().getSelectedItem().getId();
                obj = logicaVent.getObjetivo(cod);
             }
             else if(event.getSource()==upd_obj_2){
                 String cod = list_obj_2.getSelectionModel().getSelectedItem().getId();
                obj = logicaVent.getObjetivo(cod);
             }
             else if(event.getSource()==upd_obj_3){
                 String cod = list_obj_3.getSelectionModel().getSelectedItem().getId();
                obj = logicaVent.getObjetivo(cod);
             }
             else if(event.getSource()==upd_obj_4){
                 String cod = list_obj_4.getSelectionModel().getSelectedItem().getId();
                obj = logicaVent.getObjetivo(cod);
             }
            
            
             
            FXMLLoader root = new FXMLLoader();
            root.setLocation(getClass().getResource("mod_objetivo.fxml"));
            Scene sce = new Scene(root.load());
            ModObjetivoController ec= (ModObjetivoController)root.getController();
            ec.recibeParametros(obj,this);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Modificar Objetivo");
            stage.setScene(sce);
            stage.show();
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
         catch(RuntimeException e){
            label_info.setText("Objetivo no seleccionado.");    
            label_info.setVisible(true);              
         }
    }

     @FXML
    private void addInd(MouseEvent event) {
        setLabel();
        try {
             Objetivo obj=null;
             if(event.getSource()==add_ind_1){
                 String cod = list_obj_1.getSelectionModel().getSelectedItem().getId();
                obj = logicaVent.getObjetivo(cod);
             }
             else if(event.getSource()==add_ind_2){
                 String cod = list_obj_2.getSelectionModel().getSelectedItem().getId();
                obj = logicaVent.getObjetivo(cod);
             }
             else if(event.getSource()==add_ind_3){
                 String cod = list_obj_3.getSelectionModel().getSelectedItem().getId();
                obj = logicaVent.getObjetivo(cod);
             }
             else if(event.getSource()==add_ind_4){
                 String cod = list_obj_4.getSelectionModel().getSelectedItem().getId();
                obj = logicaVent.getObjetivo(cod);
             }
            
             
            FXMLLoader root = new FXMLLoader();
            root.setLocation(getClass().getResource("add_indicador.fxml"));
            Scene sce = new Scene(root.load());
            AddIndicadorController ec= (AddIndicadorController)root.getController();
            ec.recibeParametros(obj,this);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Agregar Indicador");
            stage.setScene(sce);
            stage.show();
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
         catch(RuntimeException e){
            label_info.setText("Objetivo no seleccionado.");    
            label_info.setVisible(true);              
         }
    }

    @FXML
    private void elimInd(MouseEvent event) {
        setLabel();
         try {
             Indicador ind=null;
             if(event.getSource()==rem_ind_1){
                 String cod = list_ind_1.getSelectionModel().getSelectedItem().getId();
                ind = logicaVent.getIndicador(cod);
             }
             else if(event.getSource()==rem_ind_2){
                 String cod = list_ind_2.getSelectionModel().getSelectedItem().getId();
                ind = logicaVent.getIndicador(cod);
             }
             else if(event.getSource()==rem_ind_3){
                 String cod = list_ind_3.getSelectionModel().getSelectedItem().getId();
                ind = logicaVent.getIndicador(cod);
             }
             else if(event.getSource()==rem_ind_4){
                 String cod = list_ind_4.getSelectionModel().getSelectedItem().getId();
                ind = logicaVent.getIndicador(cod);
             }
            
             
            FXMLLoader root = new FXMLLoader();
            root.setLocation(getClass().getResource("elim_indicador.fxml"));
            Scene sce = new Scene(root.load());
            ElimIndicadorController ec= (ElimIndicadorController)root.getController();
            ec.recibeParametros(ind,this);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Eliminar Indicador");
            stage.setScene(sce);
            stage.show();
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
         catch(RuntimeException e){
            label_info.setText("Indicador no seleccionado.");    
            label_info.setVisible(true);             
         }
    }

    @FXML
    private void modInd(MouseEvent event) {
        setLabel();
        try {
             Indicador ind=null;
             if(event.getSource()==upd_ind_1){
                 String cod = list_ind_1.getSelectionModel().getSelectedItem().getId();
                ind = logicaVent.getIndicador(cod);
             }
             else if(event.getSource()==upd_ind_2){
                 String cod = list_ind_2.getSelectionModel().getSelectedItem().getId();
                ind = logicaVent.getIndicador(cod);
             }
             else if(event.getSource()==upd_ind_3){
                 String cod = list_ind_3.getSelectionModel().getSelectedItem().getId();
                ind = logicaVent.getIndicador(cod);
             }
             else if(event.getSource()==upd_ind_4){
                 String cod = list_ind_4.getSelectionModel().getSelectedItem().getId();
                ind = logicaVent.getIndicador(cod);
             }
            
             
            FXMLLoader root = new FXMLLoader();
            root.setLocation(getClass().getResource("mod_indicador.fxml"));
            Scene sce = new Scene(root.load());
            ModIndicadorController ec= (ModIndicadorController)root.getController();
            ec.recibeParametros(ind,this);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Modificar Indicador");
            stage.setScene(sce);
            stage.show();
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
         catch(RuntimeException e){
            label_info.setText("Indicador no seleccionado.");    
            label_info.setVisible(true);             
         }
    }

    @FXML
    private void metas(MouseEvent event) {
        setLabel();
       try {
             Objetivo obj=null;
             if(event.getSource()==metas1){
                 String cod = list_obj_1.getSelectionModel().getSelectedItem().getId();
                obj = logicaVent.getObjetivo(cod);
             }
             else if(event.getSource()==metas2){
                 String cod = list_obj_2.getSelectionModel().getSelectedItem().getId();
                obj = logicaVent.getObjetivo(cod);
             }
             else if(event.getSource()==metas3){
                 String cod = list_obj_3.getSelectionModel().getSelectedItem().getId();
                obj = logicaVent.getObjetivo(cod);
             }
             else if(event.getSource()==metas4){
                 String cod = list_obj_4.getSelectionModel().getSelectedItem().getId();
                obj = logicaVent.getObjetivo(cod);
             }
            
             
            FXMLLoader root = new FXMLLoader();
            root.setLocation(getClass().getResource("metas.fxml"));
            Scene sce = new Scene(root.load());
            MetasController ec= (MetasController)root.getController();
            ec.recibeParametros(obj,this);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Metas");
            stage.setScene(sce);
            stage.show();
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
         catch(RuntimeException e){
            label_info.setText("Objetivo no seleccionado.");    
            label_info.setVisible(true);           
         }
    }
    
    @FXML
    private void iniciativas(MouseEvent event) {
        setLabel();
       try {
             Objetivo obj=null;
             if(event.getSource()==inici1){
                 String cod = list_obj_1.getSelectionModel().getSelectedItem().getId();
                obj = logicaVent.getObjetivo(cod);
             }
             else if(event.getSource()==inici2){
                 String cod = list_obj_2.getSelectionModel().getSelectedItem().getId();
                obj = logicaVent.getObjetivo(cod);
             }
             else if(event.getSource()==inici3){
                 String cod = list_obj_3.getSelectionModel().getSelectedItem().getId();
                obj = logicaVent.getObjetivo(cod);
             }
             else if(event.getSource()==inici4){
                 String cod = list_obj_4.getSelectionModel().getSelectedItem().getId();
                obj = logicaVent.getObjetivo(cod);
             }
            
             
            FXMLLoader root = new FXMLLoader();
            root.setLocation(getClass().getResource("iniciativas.fxml"));
            Scene sce = new Scene(root.load());
            IniciativasController ec= (IniciativasController)root.getController();
            ec.recibeParametros(obj,this);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Iniciativas");
            stage.setScene(sce);
            stage.show();
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
         catch(RuntimeException e){
            label_info.setText("Objetivo no seleccionado.");    
            label_info.setVisible(true);           
         }
    }
    @FXML
    private void minimizar(MouseEvent event) {
        Window window = ((Node)(event.getSource())).getScene().getWindow(); 
        if (window instanceof Stage){
            ((Stage) window).setIconified(true);
        }
    }
    @FXML
    private void accionObj1(MouseEvent event){
        setLabel();
        try {
            list_ind_1.getItems().clear();
            for(Indicador ind:logicaVent.getIndicadores(list_obj_1.getSelectionModel().getSelectedItem().getId())){
                TextFlow tf = new TextFlow();
                tf.setTextAlignment(TextAlignment.JUSTIFY); 
                tf.setLineSpacing(1.0); 
                tf.setPrefWidth(5);


                final Text leftText = TextBuilder.create()
                    .text(ind.getDescripcion())
                    .translateX(0)
                    .font(Font.font(null, FontWeight.NORMAL, 11))
                    .build();
                tf.setId(ind.getCodigo()+"");
                tf.getChildren().add(leftText);           
                list_ind_1.getItems().add(tf);
        }
        } catch (Exception e) {
        }
        
    }
    
    @FXML
    private void accionObj2(MouseEvent event){
        setLabel();
        try {
            
            list_ind_2.getItems().clear();
            for(Indicador ind:logicaVent.getIndicadores(list_obj_2.getSelectionModel().getSelectedItem().getId())){
                TextFlow tf = new TextFlow();
                tf.setTextAlignment(TextAlignment.JUSTIFY); 
                tf.setLineSpacing(1.0); 
                tf.setPrefWidth(5);


                final Text leftText = TextBuilder.create()
                    .text(ind.getDescripcion())
                    .translateX(0)
                    .font(Font.font(null, FontWeight.NORMAL, 11))
                    .build();
                tf.setId(ind.getCodigo()+"");
                tf.getChildren().add(leftText);           
                list_ind_2.getItems().add(tf);
            }
        } catch (Exception e) {
        }
        
    }
    
    @FXML
    private void accionObj3(MouseEvent event){
        setLabel();
        try {
            list_ind_3.getItems().clear();
            for(Indicador ind:logicaVent.getIndicadores(list_obj_3.getSelectionModel().getSelectedItem().getId())){
                TextFlow tf = new TextFlow();
                tf.setTextAlignment(TextAlignment.JUSTIFY); 
                tf.setLineSpacing(1.0); 
                tf.setPrefWidth(5);


                final Text leftText = TextBuilder.create()
                    .text(ind.getDescripcion())
                    .translateX(0)
                    .font(Font.font(null, FontWeight.NORMAL, 11))
                    .build();
                tf.setId(ind.getCodigo()+"");
                tf.getChildren().add(leftText);           
                list_ind_3.getItems().add(tf);
            }
        } catch (Exception e) {
        }
        
    }
    
    @FXML
    private void accionObj4(MouseEvent event){
        setLabel();
        try {
            list_ind_4.getItems().clear();
            for(Indicador ind:logicaVent.getIndicadores(list_obj_4.getSelectionModel().getSelectedItem().getId())){
                TextFlow tf = new TextFlow();
                tf.setTextAlignment(TextAlignment.JUSTIFY); 
                tf.setLineSpacing(1.0); 
                tf.setPrefWidth(5);


                final Text leftText = TextBuilder.create()
                    .text(ind.getDescripcion())
                    .translateX(0)
                    .font(Font.font(null, FontWeight.NORMAL, 11))
                    .build();
                tf.setId(ind.getCodigo()+"");
                tf.getChildren().add(leftText);           
                list_ind_4.getItems().add(tf);
            }
        } catch (Exception e) {
        }
        
    }
    @FXML
    private void update(MouseEvent event){
        list_obj_1.getItems().clear();
        list_obj_2.getItems().clear();
        list_obj_3.getItems().clear();
        list_obj_4.getItems().clear();
        
        list_ind_1.getItems().clear();
        list_ind_2.getItems().clear();
        list_ind_3.getItems().clear();
        list_ind_4.getItems().clear();
        
        String[] as = logicaVent.getAreas();
        
        
        for(Objetivo obj:logicaVent.getObjetivos(as[0])){
            TextFlow tf = new TextFlow();
            tf.setTextAlignment(TextAlignment.JUSTIFY); 
            tf.setLineSpacing(1.0); 
            tf.setPrefWidth(5);
            
            
            final Text leftText = TextBuilder.create()
                .text(obj.getDescripcion())
                .translateX(0)
                .font(Font.font(null, FontWeight.NORMAL, 11))
                .build();
            tf.setId(obj.getCodigo()+"");
            tf.getChildren().add(leftText);           
            list_obj_1.getItems().add(tf);
        }
        
        for(Objetivo obj:logicaVent.getObjetivos(as[1])){
            TextFlow tf = new TextFlow();
            tf.setTextAlignment(TextAlignment.JUSTIFY); 
            tf.setLineSpacing(1.0); 
            tf.setPrefWidth(5);
            
            
            final Text leftText = TextBuilder.create()
                .text(obj.getDescripcion())
                .translateX(0)
                .font(Font.font(null, FontWeight.NORMAL, 11))
                .build();
            tf.setId(obj.getCodigo()+"");
            tf.getChildren().add(leftText);           
            list_obj_2.getItems().add(tf);
        }
        
        for(Objetivo obj:logicaVent.getObjetivos(as[2])){
            TextFlow tf = new TextFlow();
            tf.setTextAlignment(TextAlignment.JUSTIFY); 
            tf.setLineSpacing(1.0); 
            tf.setPrefWidth(5);
            
            
            final Text leftText = TextBuilder.create()
                .text(obj.getDescripcion())
                .translateX(0)
                .font(Font.font(null, FontWeight.NORMAL, 11))
                .build();
            tf.setId(obj.getCodigo()+"");
            tf.getChildren().add(leftText);           
            list_obj_3.getItems().add(tf);
        }
        
        for(Objetivo obj:logicaVent.getObjetivos(as[3])){
            TextFlow tf = new TextFlow();
            tf.setTextAlignment(TextAlignment.JUSTIFY); 
            tf.setLineSpacing(1.0); 
            tf.setPrefWidth(5);
            
            
            final Text leftText = TextBuilder.create()
                .text(obj.getDescripcion())
                .translateX(0)
                .font(Font.font(null, FontWeight.NORMAL, 11))
                .build();
            tf.setId(obj.getCodigo()+"");
            tf.getChildren().add(leftText);           
            list_obj_4.getItems().add(tf);
        }
    }
    private void setLabel(){
        label_info.setVisible(false);
    }
    public void update(){
        update(null);
    }
    public void recibeParametrosUsuario(Usuario usuario){
        esUsuario=true;
        usuarioLog=usuario;
        labelUsuario.setText(usuarioLog.getNombres()+"\n"+usuarioLog.getApellidos());
        labelArea.setText(usuarioLog.getCodArea().getNombre());
        add_usuario.setVisible(false);
        elim_usuario.setVisible(false);
        mod_usuario.setVisible(false);
        
        confBotones(usuarioLog.getCodArea().getCodigo());
        update(null);
    }
    
    public void recibeParametrosAdministrador(Administrador admin){
        esUsuario=false;
        adminLog=admin;
        labelUsuario.setText(adminLog.getNombres()+"\n"+adminLog.getApellidos());
        labelArea.setText("Administrador");
        bbdd_settings.setVisible(true);
        add_usuario.setVisible(true);
        elim_usuario.setVisible(true);
        mod_usuario.setVisible(true);
        confBotones("ADMIN");
        update();
    }

    private void confBotones(String codigo) {
        
        String[] as = logicaVent.getAreas();
        
        if(codigo.equals(as[0])||codigo.equals("ADMIN")){
            add_obj_1.setDisable(false);
            rem_obj_1.setDisable(false);
            upd_obj_1.setDisable(false);
            add_ind_1.setDisable(false);
            rem_ind_1.setDisable(false);
            upd_ind_1.setDisable(false);
            metas1.setDisable(false);
            inici1.setDisable(false);
        }
        if(codigo.equals(as[1])||codigo.equals("ADMIN")){
            add_obj_2.setDisable(false);
            rem_obj_2.setDisable(false);
            upd_obj_2.setDisable(false);
            add_ind_2.setDisable(false);
            rem_ind_2.setDisable(false);
            upd_ind_2.setDisable(false);
            metas2.setDisable(false);
            inici2.setDisable(false);
        }
        if(codigo.equals(as[2])||codigo.equals("ADMIN")){
            add_obj_3.setDisable(false);
            rem_obj_3.setDisable(false);
            upd_obj_3.setDisable(false);
            add_ind_3.setDisable(false);
            rem_ind_3.setDisable(false);
            upd_ind_3.setDisable(false);
            metas3.setDisable(false);
            inici3.setDisable(false);
        }
        if(codigo.equals(as[3])||codigo.equals("ADMIN")){
            add_obj_4.setDisable(false);
            rem_obj_4.setDisable(false);
            upd_obj_4.setDisable(false);
            add_ind_4.setDisable(false);
            rem_ind_4.setDisable(false);
            upd_ind_4.setDisable(false);
            metas4.setDisable(false);
            inici4.setDisable(false);
        }
        
    }

    @FXML
    private void BBDD(MouseEvent event) {
        System.out.println("BBDD");
    }

    

    
}