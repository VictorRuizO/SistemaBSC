/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import modelo.Usuario;
import pesistencia.UsuarioJpaController;


public class Login {
    
    private UsuarioJpaController usuCont;
    private Usuario usuarioLog;
    
    public Login(){
        usuCont = new UsuarioJpaController();
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
}
