/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import modelo.Administrador;
import modelo.Usuario;
import pesistencia.AdministradorJpaController;
import pesistencia.UsuarioJpaController;


public class Login {
    
    private UsuarioJpaController usuCont;
    private AdministradorJpaController adminCont;
    private Usuario usuarioLog;
    private Administrador adminLog;
    
    public Login(){
        usuCont = new UsuarioJpaController();
        adminCont = new AdministradorJpaController();
    }
    
    public int login(String usuario,String contrasena){
        
        usuarioLog = usuCont.findUsuario(usuario);
        if (usuarioLog==null){
            return 0;
        }
        else if (usuarioLog.getContrasena().equals(contrasena)) {
            
            return 1;
        }
        
        return 2;
    }
    
    public Usuario getUsuario(){
        return usuCont.findUsuario(usuarioLog.getDi());
    }


    public int adminLogin(String text, String text0) {
        adminLog = adminCont.findAdministrador(text);
        if(adminLog==null){
            return -1;
        }
        else if(adminLog.getContrasena().equals(text0)){
            return 0;
        }
        
        return 1;
    }

    public Administrador getAdmin() {
        return adminLog;
    }
}
