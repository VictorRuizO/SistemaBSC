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
 *
 * @author Victor
 */
public class ModObjetivoLogica {
    
    private ObjetivoJpaController objCont;
    
    public ModObjetivoLogica(){
        objCont = new ObjetivoJpaController();
    }
    
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
