/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelo.Area;
import pesistencia.AreaJpaController;

/**
 *
 * @author Victor
 */
public class SistemaBSC extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ventana.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        Area a = new Area("001");
        a.setDescripcion("desc");
        a.setNombre("nomarea");
        AreaJpaController c = new AreaJpaController();
        try {
            c.create(a);
        } catch (Exception ex) {
            Logger.getLogger(SistemaBSC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
