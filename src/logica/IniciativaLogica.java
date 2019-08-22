/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Iniciativa;
import modelo.Objetivo;
import pesistencia.IniciativaJpaController;
import pesistencia.ObjetivoJpaController;
import pesistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Victor
 */
public class IniciativaLogica {
    
    private IniciativaJpaController iniCont;
    private ObjetivoJpaController objCont;
    
    public IniciativaLogica(){
        iniCont = new IniciativaJpaController();
        objCont = new ObjetivoJpaController();
    }

    public Iniciativa getIniciativa(String id) {
        int i = Integer.parseInt(id);
        return iniCont.findIniciativa(i);
    }

    public void agregarIniciativa(String text,Objetivo o) {
        Iniciativa m = new Iniciativa();
        m.setDescripcion(text);
        m.setCodObjetivo(o);
        iniCont.create(m);
        
    }

    public Objetivo obtenerObjetivo(Objetivo o){
        return objCont.findObjetivo(o.getCodigo());
    }

    public void eliminarIniciativa(String id) {
        try {
            int i = Integer.parseInt(id);
            iniCont.destroy(i);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(MetaLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificarIniciativa(String text, Iniciativa met) {
        try {
            met.setDescripcion(text);
            iniCont.edit(met);
        } catch (Exception ex) {
            Logger.getLogger(MetaLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

