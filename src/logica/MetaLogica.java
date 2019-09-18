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
 *Esta clase tiene se conecta con el controlador de la BD y se encarga gestionar todas las operaciones de las metas
 * @author Victor
 */
public class MetaLogica {
    private MetaJpaController metaCont;
    private ObjetivoJpaController objCont;
    /**
     * Constructor de la clase
     */    
    public MetaLogica(){
        metaCont = new MetaJpaController();
        objCont = new ObjetivoJpaController();
    }
     /**
     * Retorna una mata
     * @param id ID de la meta
     */
    public Meta getMeta(String id) {
        int i = Integer.parseInt(id);
        return metaCont.findMeta(i);
    }
     /**
     * Crea y agrega una meta de un objetivo al sistema
     * @param text descripcion de la meta
     * @param o Objetivo al cual pertenece la meta
     */
    public void agregarMeta(String text,Objetivo o) {
        Meta m = new Meta();
        m.setDescripcion(text);
        m.setCodObjetivo(o);
        metaCont.create(m);
        
    }
    /**
     * Retorna un objetivo
     * @param o Objetivo
     */
    public Objetivo obtenerObjetivo(Objetivo o){
        return objCont.findObjetivo(o.getCodigo());
    }
     /**
     * Elimina una meta de un objetivo al sistema
     * @param id ID de la meta
     */
    public void eliminarMeta(String id) {
        try {
            int i = Integer.parseInt(id);
            metaCont.destroy(i);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(MetaLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Modifica una meta de un objetivo al sistema
     * @param text Nueva meta de la iniciativa
     * @param met meta que se va a modificar
     */
    public void modificarMeta(String text, Meta met) {
        try {
            met.setDescripcion(text);
            metaCont.edit(met);
        } catch (Exception ex) {
            Logger.getLogger(MetaLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
