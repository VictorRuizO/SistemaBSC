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
 *Esta clase tiene se conecta con el controlador de la BD y se encarga gestionar todas las operaciones de los indicadores
 * @author Victor
 */
public class IndicadorLogica {
    
    private IndicadorJpaController indCont;
    /**
     * Constructor de la clase
     */
    public IndicadorLogica(){
        indCont = new IndicadorJpaController();
    }
     /**
     * Crea y agrega un indicador de un objetivo al sistema
     * @param text descripcion del indicador
     * @param obj Objetivo al cual pertenece el indicador
     */
    public void agregarIndicador(String text, Objetivo obj) {
        Indicador i = new Indicador();
        i.setDescripcion(text);
        i.setCodObjetivo(obj);
        
        indCont.create(i);
    }
     /**
     * Elimina un indicador del sistema
     * @param ind Indicador el cual se va a eliminar
     */
    public void eliminarIndicador(Indicador ind) {
        try {
            indCont.destroy(ind.getCodigo());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(IndicadorLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     /**
     * Modifica un indicador (su descripcion)
     * @param ind Indicador que se va a modificar
     * @param text Nueva descripcion del indicador
     */
    public void modificarIndicador(Indicador ind, String text) {
        try {
            ind.setDescripcion(text);
            indCont.edit(ind);
        } catch (Exception ex) {
            Logger.getLogger(IndicadorLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
