/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Objetivo;
import pesistencia.ObjetivoJpaController;
import pesistencia.exceptions.NonexistentEntityException;

/**
 *Esta clase tiene se conecta con el controlador de la BD y se encarga gestionar las modificaciones de los objetivos
 * @author Victor
 */
public class ModObjetivoLogica {
    
    private ObjetivoJpaController objCont;
    /**
     * Constructor de la clase
     */      
    public ModObjetivoLogica(){
        objCont = new ObjetivoJpaController();
    }
     /**
     * Modifica un objetivo (su descripcion)
     * @param obj Objetivo que se va a modificar con la modificacion
     */    
    public void modificarObjetivo(Objetivo obj){
        try {
            objCont.edit(obj);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ModObjetivoLogica.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ModObjetivoLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
