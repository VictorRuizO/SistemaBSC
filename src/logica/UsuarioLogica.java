/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Area;
import modelo.Usuario;
import pesistencia.AreaJpaController;
import pesistencia.UsuarioJpaController;
import pesistencia.exceptions.NonexistentEntityException;


/**
 *Esta clase tiene se conecta con el controlador de la BD y se encarga de gestionar llas operaciones de los usuarios
 * @author Victor
 */
public class UsuarioLogica {


    
    private UsuarioJpaController usuCont;
    private AreaJpaController areaCont;
    
    /**
     * Constructor de la clase
     */       
    public UsuarioLogica(){
        usuCont=new UsuarioJpaController();
        areaCont = new AreaJpaController();
    }
    /**
     * Agrega nuevos usuarios en el sistema
     * @param di ID del usuario
     * @param nom Nombre del usuario
     * @param apel Apellido del usuario
     * @param nac Fecha de nacimiento del usuario
     * @param mail Email del usuario
     * @param cont Contrasela del usuario
     * @param area Area del usuario
     */        
    public void agregarUsuaio(String di,String nom, String apel, LocalDate nac,String mail, String cont, Area area) {
        try {
            Usuario usu = new Usuario();
            usu.setDi(di);
            usu.setNombres(nom);
            usu.setApellidos(apel);
            Date d=new Date(nac.getYear()-1900, nac.getMonthValue()-1, nac.getDayOfMonth());
            usu.setFechaNac(d);
            usu.setEmail(mail);
            usu.setContrasena(cont);
            usu.setCodArea(area);
            usuCont.create(usu);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     /**
     * Retorna las areas del sistema
     * @return Areas del sistema
     */     
    public List<Area> getAreas(){
        
        return areaCont.findAreaEntities();
    }
     /**
     * Retorna un usuario del sistema
     * @param di ID del usuario
     * @return Usuario
     */      
    public Usuario getUsuario(String di){
        return usuCont.findUsuario(di);
    }
     /**
     * Retorna los usuarios del sistema
     * @return Usuarios del sistema
     */  
    public List<Usuario> obtenerUsuarios() {
        return usuCont.findUsuarioEntities();
    }
     /**
     * Modifica un usuario del sistema
     * @param usuSel Usuario que se va a modificar (con sus modificaciones)
     */  
    public void modificarUsuario(Usuario usuSel) {
        try {
            usuCont.edit(usuSel);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     /**
     * Elimina un usuario del sistema
     * @param usuSel Usuario que se va a eliinar 
     */  
    public void eliminarUsuario(String usuSel) {
        try {
            usuCont.destroy(usuSel);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(UsuarioLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}