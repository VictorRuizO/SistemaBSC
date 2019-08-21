/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import modelo.Indicador;
import modelo.Objetivo;
import pesistencia.IndicadorJpaController;
import pesistencia.ObjetivoJpaController;

/**
 *
 * @author Victor
 */
public class VentanaLogica {
    
    private ObjetivoJpaController objCont;
    private IndicadorJpaController indCont;
    
    public VentanaLogica(){
        objCont = new ObjetivoJpaController();
        indCont = new IndicadorJpaController();
    }
    
    public List<Objetivo> getObjetivos(String area){
        return objCont.findObjetivoByArea(area);
    }
    
    public List<Indicador> getIndicadores(String onb){
        return  indCont.findIndicadorByObjetivo(onb);
    }

    public Objetivo getObjetivo(String cod) {
        int id = Integer.parseInt(cod);
        return objCont.findObjetivo(id);
    }
}
