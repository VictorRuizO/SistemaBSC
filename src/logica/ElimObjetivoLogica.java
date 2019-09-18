/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Indicador;
import modelo.Iniciativa;
import modelo.Meta;
import modelo.Objetivo;
import pesistencia.IndicadorJpaController;
import pesistencia.IniciativaJpaController;
import pesistencia.MetaJpaController;
import pesistencia.ObjetivoJpaController;
import pesistencia.exceptions.IllegalOrphanException;
import pesistencia.exceptions.NonexistentEntityException;

/**
 *Esta clase tiene se conecta con el controlador de la BD y se encarga de eliminar objetivos
 * @author Victor
 */
public class ElimObjetivoLogica {
    
    private ObjetivoJpaController objCont;
    private MetaJpaController metaCont;
    private IndicadorJpaController indCont;
    private IniciativaJpaController iniCont;
     /**
     * Constructor de la clase
     */
    public ElimObjetivoLogica(){
        objCont = new ObjetivoJpaController();
        metaCont = new MetaJpaController();
        indCont = new IndicadorJpaController();
        iniCont = new IniciativaJpaController();
    }
    
     /**
     * Eliminar el objetivo ingredaro junto a sus metas, indicadores e iniciativas
     * @param obj Objetivo que se va e eliminar
     */
    public void eliminar(Objetivo obj){
        try {
            for(Meta m:obj.getMetaList())
                metaCont.destroy(m.getCodigo());
            for(Indicador ind:obj.getIndicadorList())
                indCont.destroy(ind.getCodigo());
            for(Iniciativa ini:obj.getIniciativaList())
                iniCont.destroy(ini.getCodigo());
            
            objCont.destroy(obj.getCodigo());
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(ElimObjetivoLogica.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ElimObjetivoLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
