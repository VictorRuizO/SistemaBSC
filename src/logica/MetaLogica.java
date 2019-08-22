/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Indicador;
import modelo.Meta;
import modelo.Objetivo;
import pesistencia.MetaJpaController;
import pesistencia.ObjetivoJpaController;
import pesistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Victor
 */
public class MetaLogica {
    private MetaJpaController metaCont;
    private ObjetivoJpaController objCont;
    
    public MetaLogica(){
        metaCont = new MetaJpaController();
        objCont = new ObjetivoJpaController();
    }

    public Meta getMeta(String id) {
        int i = Integer.parseInt(id);
        return metaCont.findMeta(i);
    }

    public void agregarMeta(String text,Objetivo o) {
        Meta m = new Meta();
        m.setDescripcion(text);
        m.setCodObjetivo(o);
        metaCont.create(m);
        
    }

    public Objetivo obtenerObjetivo(Objetivo o){
        return objCont.findObjetivo(o.getCodigo());
    }

    public void eliminarMeta(String id) {
        try {
            int i = Integer.parseInt(id);
            metaCont.destroy(i);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(MetaLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificarMeta(String text, Meta met) {
        try {
            met.setDescripcion(text);
            metaCont.edit(met);
        } catch (Exception ex) {
            Logger.getLogger(MetaLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
