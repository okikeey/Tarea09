/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

public class Usuario {
    
    //Atributos
    private String idUsuario;
    private String pwUsuario;
    private String nombreUsuario;
    private String idRol;
    
    // Constructor

    public Usuario() {
        
    }
    
    public Usuario(String idUsuario, String nombreUsuario, String idRol) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.idRol = idRol;
    }

   //GET and SET

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getPwUsuario() {
        return pwUsuario;
    }

    public void setPwUsuario(String pwUsuario) {
        this.pwUsuario = pwUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getIdRol() {
        return idRol;
    }

    public void setIdRol(String idRol) {
        this.idRol = idRol;
    }

}
