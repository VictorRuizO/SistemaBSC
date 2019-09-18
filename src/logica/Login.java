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

/**
 *Esta clase tiene se conecta con el controlador de la BD y se encarga identificar que usuario o que administrador va a usar el sistema
 * @author Victor
 */
public class Login {
    
    private UsuarioJpaController usuCont;
    private AdministradorJpaController adminCont;
    private Usuario usuarioLog;
    private Administrador adminLog;
    /**
     * Constructor de la clase
     */
    public Login(){
        usuCont = new UsuarioJpaController();
        adminCont = new AdministradorJpaController();
    }
     /**
     * Identifica que usuario va utizar el sistema, 0:Usuario no registrado, 1: Login OK, 2:Error en la contraseña
     * @param usuario ID del usuario que se intenta logear
     * @param contrasena contraseña del usuario que se intenta logear
     */
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
     /**
     * Retorna una el usuario logeado
     */
    public Usuario getUsuario(){
        return usuCont.findUsuario(usuarioLog.getDi());
    }

     /**
     * Identifica que administrador va utizar el sistema, -1:Usuario no registrado, 0: Login OK, 1:Error en la contraseña
     * @param text  ID del administrado que se intenta logear
     * @param text0  contraseña del administrador que se intenta logear
     */
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
     /**
     * Retorna una el administrador logeado
     */
    public Administrador getAdmin() {
        return adminLog;
    }
}
