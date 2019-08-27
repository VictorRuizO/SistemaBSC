/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import modelo.Area;
import modelo.Indicador;
import modelo.Objetivo;
import pesistencia.AreaJpaController;
import pesistencia.IndicadorJpaController;
import pesistencia.ObjetivoJpaController;

/**
 *
 * @author Victor
 */
public class VentanaLogica {
    
    private ObjetivoJpaController objCont;
    private IndicadorJpaController indCont;
    private AreaJpaController areaCont;
    
    public VentanaLogica(){
        objCont = new ObjetivoJpaController();
        indCont = new IndicadorJpaController();
        areaCont = new AreaJpaController();
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

    public String[] getAreas() {
        String[] salida = new String[4];
        List<Area> lis = areaCont.findAreaEntities();
        
        for(int i=0;i<4;i++){
            try {
                salida[i]=lis.get(i).getCodigo();
            } catch (Exception e) {
                System.out.println("Error en la base de datos");
                System.exit(0);
            }
            
        }
        return salida;
    }

    public Indicador getIndicador(String cod) {
        int i=Integer.parseInt(cod);
        return indCont.findIndicador(i);
    }

    public Area getArea(String a) {
        return areaCont.findArea(a);
    }
}
