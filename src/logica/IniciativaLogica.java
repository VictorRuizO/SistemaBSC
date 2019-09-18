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
 *Esta clase tiene se conecta con el controlador de la BD y se encarga gestionar todas las operaciones de las iniciativas
 * @author Victor
 */
public class IniciativaLogica {
    
    private IniciativaJpaController iniCont;
    private ObjetivoJpaController objCont;
    /**
     * Constructor de la clase
     */
    public IniciativaLogica(){
        iniCont = new IniciativaJpaController();
        objCont = new ObjetivoJpaController();
    }
     /**
     * Retorna una iniciativa
     * @param id ID de la iniciativa
     */
    public Iniciativa getIniciativa(String id) {
        int i = Integer.parseInt(id);
        return iniCont.findIniciativa(i);
    }
     /**
     * Crea y agrega una iniciativa de un objetivo al sistema
     * @param text descripcion de la iniciativa
     * @param o Objetivo al cual pertenece la iniciativa
     */
    public void agregarIniciativa(String text,Objetivo o) {
        Iniciativa m = new Iniciativa();
        m.setDescripcion(text);
        m.setCodObjetivo(o);
        iniCont.create(m);
        
    }
     /**
     * Retorna un objetivo
     * @param o Objetivo
     */
    public Objetivo obtenerObjetivo(Objetivo o){
        return objCont.findObjetivo(o.getCodigo());
    }
     /**
     * Elimina una iniciativa de un objetivo al sistema
     * @param id ID de la iniciativa
     */
    public void eliminarIniciativa(String id) {
        try {
            int i = Integer.parseInt(id);
            iniCont.destroy(i);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(MetaLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     /**
     * Modifica una iniciativa de un objetivo al sistema
     * @param text Nueva descripcion de la iniciativa
     * @param met Iniciativa que se va a modificar
     */
    public void modificarIniciativa(String text, Iniciativa met) {
        try {
            met.setDescripcion(text);
            iniCont.edit(met);
        } catch (Exception ex) {
            Logger.getLogger(MetaLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

