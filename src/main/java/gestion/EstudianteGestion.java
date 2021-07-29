
package gestion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Conexion;
import modelo.Estudiante;

/*
C create
R ead
U pdate
D elete
*/


public class EstudianteGestion {
    
    // Sección que permite realizar la inserción de estudiantes en base de datos 
    
    private static final String SQL_INSERT_ESTUDIANTE= "insert into estudiante (idEstudiante,nombre,"
            + "apellido1,apellido2,fechaNaci,fechaIngr,genero) values (?,?,?,?,?,?,?) ";
    
    public static boolean insertar (Estudiante estudiante){
        
        try {
            PreparedStatement sentencia= Conexion.getConexion().prepareCall(SQL_INSERT_ESTUDIANTE);
            sentencia.setString(1,estudiante.getId());
            sentencia.setString(2,estudiante.getNombre());
            sentencia.setString(3,estudiante.getApellido1());
            sentencia.setString(4,estudiante.getApellido2());
            sentencia.setObject(5, estudiante.getFechaNaci());
            sentencia.setObject(6, estudiante.getFechaIngr());
            sentencia.setString(7, ""+estudiante.getGenero());
            return sentencia.executeUpdate()>0; // retorna true si lo inserta, false de lo contrario. 

        } catch (SQLException ex) {
            Logger.getLogger(EstudianteGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false; // No se logró insertar
    }
    
    
    //Sección que permite consultar la información de un estudiante en específico
    
    private static final String SQL_SELECT_ESTUDIANTE= "select * from estudiante where idEstudiante=?";
    
    public static Estudiante getEstudiante (String id){
        
        
        Estudiante estudiante=null;
        
        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SQL_SELECT_ESTUDIANTE);
            consulta.setString(1, id);
            ResultSet datos= consulta.executeQuery();
            
            if (datos.next()){
                
                estudiante= new Estudiante(
                datos.getString(2),
                datos.getString(3),
                datos.getString(4),
                datos.getString(5),
                datos.getDate(6),
                datos.getDate(7),
                datos.getString(8).charAt(0)
                );
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EstudianteGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return estudiante;
        
    }
    
    //Sección que permite editar la información de un estudiante en base de datos
    
    private static final String SQL_UPDATE_ESTUDIANTE = "update estudiante set nombre=?,apellido1=?,"
            + "apellido2=?,fechaNaci=?,fechaIngr=?,genero=? where idEstudiante=?";
    
    public static boolean actualiza(Estudiante estudiante){
        
        try {
            PreparedStatement sentencia= Conexion.getConexion().prepareCall(SQL_UPDATE_ESTUDIANTE);
            sentencia.setString(1, estudiante.getNombre());
            sentencia.setString(2, estudiante.getApellido1());
            sentencia.setString(3, estudiante.getApellido2());
            sentencia.setObject(4, estudiante.getFechaNaci());
            sentencia.setObject(5, estudiante.getFechaIngr());
            sentencia.setString(6, ""+estudiante.getGenero());
            sentencia.setString(7, estudiante.getId());
            return sentencia.executeUpdate()>0; //retorna true si lo inserta, caso contrario false
            
        } catch (SQLException ex) {
            Logger.getLogger(EstudianteGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    // Sección que permite eliminar la información de un estudiante de la base de datos
    private static final String SQL_DELETE_ESTUDIANTE= "delete from estudiante where idEstudiante=?";
    
    
    public static boolean eliminar (Estudiante estudiante){
        
        
        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SQL_DELETE_ESTUDIANTE);
            consulta.setString(1,estudiante.getId());
            return consulta.executeUpdate()>0; 
            
            
        } catch (SQLException ex) {
            Logger.getLogger(EstudianteGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    // Sección que permite traer de base de datos los estudiantes que se encuentran almacenados
    //private static final String SQL_SELECT_ESTUDIANTES="Select * from estudiante Limit 0,200";
    private static final String SQL_SELECT_ESTUDIANTES="Select * from estudiante";
    
    public static ArrayList<Estudiante> getEstudiantes(){
        
        ArrayList<Estudiante> lista= new ArrayList<>();
        
        try {
            PreparedStatement consulta= Conexion.getConexion().prepareStatement(SQL_SELECT_ESTUDIANTES);
            ResultSet rs= consulta.executeQuery();
            while (rs!=null && rs.next()){
                lista.add(new Estudiante (rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getDate(6),
                    rs.getDate(7),
                    rs.getString(8).charAt(0)));
            }     
        } catch (SQLException ex) {
            Logger.getLogger(EstudianteGestion.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return lista;
    }
    
    
}
