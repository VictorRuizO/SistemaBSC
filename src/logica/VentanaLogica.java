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
 *Esta clase tiene se conecta con el controlador de la BD y se encarga de obtener la informacion necesaria para mostrar en la ventana principal
 * @author Victor
 */
public class VentanaLogica {
    
    private ObjetivoJpaController objCont;
    private IndicadorJpaController indCont;
    private AreaJpaController areaCont;
    /**
     * Constructor de la clase
     */     
    public VentanaLogica(){
        objCont = new ObjetivoJpaController();
        indCont = new IndicadorJpaController();
        areaCont = new AreaJpaController();
    }
     /**
     * Retorna los objetivos de una area en especifico
     * @param area area de los objetivos
     * @return Lista de objetivos
     */       
    public List<Objetivo> getObjetivos(String area){
        return objCont.findObjetivoByArea(area);
    }
     /**
     * Retorna los indicadores de un objetivo en especifico
     * @param onb codigo del objetivo
     * @return Lista de indicadores
     */       
    public List<Indicador> getIndicadores(String onb){
        return  indCont.findIndicadorByObjetivo(onb);
    }
     /**
     * Retorna un objetivo
     * @param cod codigo del objetivo
     * @return Objetivo
     */ 
    public Objetivo getObjetivo(String cod) {
        int id = Integer.parseInt(cod);
        return objCont.findObjetivo(id);
    }
     /**
     * Retorna los codigos de las areas del sistema
     * @return Lista de codigos de las areas
     */ 
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
     /**
     * Retorna un indicador
     * @param cod codigo del indicador
     * @return Indicador
     */ 
    public Indicador getIndicador(String cod) {
        int i=Integer.parseInt(cod);
        return indCont.findIndicador(i);
    }
     /**
     * Retorna un area
     * @param cod codigo del area
     * @return Area
     */ 
    public Area getArea(String a) {
        return areaCont.findArea(a);
    }
     /**
     * Retorna el nuemro de objetivos de un area en especifico
     * @param area area de los objetivos
     * @return numero de objetivos
     */ 
    public int getCountObj(String area) {
        int n=objCont.findObjetivoByArea(area).size();
        System.out.println(n);
        return n;
    }
}
