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
 *
 * @author Victor
 */
public class AddObjetivoLogica {
    
    private ObjetivoJpaController objCont;
    private MetaJpaController metaCont;
    private IndicadorJpaController indCont;
    private IniciativaJpaController iniCont;
    
    public AddObjetivoLogica(){
        objCont = new ObjetivoJpaController();
        metaCont = new MetaJpaController();
        indCont = new IndicadorJpaController();
        iniCont = new IniciativaJpaController();
    }
    
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
