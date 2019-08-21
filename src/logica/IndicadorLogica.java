/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Indicador;
import modelo.Objetivo;
import pesistencia.IndicadorJpaController;
import pesistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Victor
 */
public class IndicadorLogica {
    
    private IndicadorJpaController indCont;
    
    public IndicadorLogica(){
        indCont = new IndicadorJpaController();
    }

    public void agregarIndicador(String text, Objetivo obj) {
        Indicador i = new Indicador();
        i.setDescripcion(text);
        i.setCodObjetivo(obj);
        
        indCont.create(i);
    }

    public void eliminarIndicador(Indicador ind) {
        try {
            indCont.destroy(ind.getCodigo());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(IndicadorLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificarIndicador(Indicador ind, String text) {
        try {
            ind.setDescripcion(text);
            indCont.edit(ind);
        } catch (Exception ex) {
            Logger.getLogger(IndicadorLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
