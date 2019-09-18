/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import modelo.Area;
import modelo.Indicador;
import modelo.Iniciativa;
import modelo.Meta;
import modelo.Objetivo;
import pesistencia.IndicadorJpaController;
import pesistencia.IniciativaJpaController;
import pesistencia.MetaJpaController;
import pesistencia.ObjetivoJpaController;

/**
 * Esta clase tiene se conecta con el controlador de la BD y se encarga de agregar objetivos
 * @author Victor
 */
public class AddObjetivoLogica {
    
    private ObjetivoJpaController objCont;
    private MetaJpaController metaCont;
    private IndicadorJpaController indCont;
    private IniciativaJpaController iniCont;
    
     /**
     * Constructor de la clase
     */
    public AddObjetivoLogica(){
        objCont = new ObjetivoJpaController();
        metaCont = new MetaJpaController();
        indCont = new IndicadorJpaController();
        iniCont = new IniciativaJpaController();
    }
     /**
     * Construye y agrega objetivos en el sistema
     * @param obj Desctripcion del objetivo
     * @param meta Descripcion de la meta del objetivo
     * @param ini Descripcion de la iniciativa del objetivo
     * @param ind Descipcion de la indicacion del objetivo
     * @param area Area a la cual pertenence el objetivo
     */
    public void agregarObjetivo(String obj, String meta, String ini, String ind, Area area){
        Objetivo o = new Objetivo();
        o.setDescripcion(obj);
        o.setCodArea(area);
        
        Indicador indi = new Indicador();
        indi.setDescripcion(ind);
        indi.setCodObjetivo(o);
        
        Meta m = new Meta();
        m.setDescripcion(meta);
        m.setCodObjetivo(o);
        
        Iniciativa inic = new Iniciativa();
        inic.setDescripcion(ini);
        inic.setCodObjetivo(o);
        
        objCont.create(o);
        metaCont.create(m);
        indCont.create(indi);
        iniCont.create(inic);
    }
    
    
}
