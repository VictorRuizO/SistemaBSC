/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Victor
 */
@Entity
@Table(name = "administrador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Administrador.findAll", query = "SELECT a FROM Administrador a")
    , @NamedQuery(name = "Administrador.findByDi", query = "SELECT a FROM Administrador a WHERE a.di = :di")
    , @NamedQuery(name = "Administrador.findByNombres", query = "SELECT a FROM Administrador a WHERE a.nombres = :nombres")
    , @NamedQuery(name = "Administrador.findByApellidos", query = "SELECT a FROM Administrador a WHERE a.apellidos = :apellidos")
    , @NamedQuery(name = "Administrador.findByFechaNac", query = "SELECT a FROM Administrador a WHERE a.fechaNac = :fechaNac")
    , @NamedQuery(name = "Administrador.findByEmail", query = "SELECT a FROM Administrador a WHERE a.email = :email")
    , @NamedQuery(name = "Administrador.findByContrasena", query = "SELECT a FROM Administrador a WHERE a.contrasena = :contrasena")})
public class Administrador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "di")
    private String di;
    @Basic(optional = false)
    @Column(name = "nombres")
    private String nombres;
    @Basic(optional = false)
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "fecha_nac")
    @Temporal(TemporalType.DATE)
    private Date fechaNac;
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "contrasena")
    private String contrasena;

    public Administrador() {
    }

    public Administrador(String di) {
        this.di = di;
    }

    public Administrador(String di, String nombres, String apellidos, String contrasena) {
        this.di = di;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.contrasena = contrasena;
    }

    public String getDi() {
        return di;
    }

    public void setDi(String di) {
        this.di = di;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (di != null ? di.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Administrador)) {
            return false;
        }
        Administrador other = (Administrador) object;
        if ((this.di == null && other.di != null) || (this.di != null && !this.di.equals(other.di))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Administrador[ di=" + di + " ]";
    }
    
}
