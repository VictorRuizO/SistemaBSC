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
 *
 * @author Victor
 */
public class UsuarioLogica {


    
    private UsuarioJpaController usuCont;
    private AreaJpaController areaCont;
    
    public UsuarioLogica(){
        usuCont=new UsuarioJpaController();
        areaCont = new AreaJpaController();
    }
    
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
    
    public List<Area> getAreas(){
        
        return areaCont.findAreaEntities();
    }
    
    public Usuario getUsuario(String di){
        return usuCont.findUsuario(di);
    }

    public List<Usuario> obtenerUsuarios() {
        return usuCont.findUsuarioEntities();
    }

    public void modificarUsuario(Usuario usuSel) {
        try {
            usuCont.edit(usuSel);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarUsuario(String usuSel) {
        try {
            usuCont.destroy(usuSel);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(UsuarioLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
