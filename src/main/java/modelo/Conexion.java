/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

//Import de clases propias para establecer la conexión con la base de datos
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Conexion {
    
    //Sección de atributos
    
    private static Conexion conexion;
    //Connection String 
    private static final String DBURL= "jdbc:mysql://localhost:3306/ugeneral?serverTimezone=UTC"; 
    private static Connection conn=null;
    
    private Conexion (){
        
        try{
            
            //Indicar cual es driver o controlador para conectarme a la base de datos
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            conn=DriverManager.getConnection(DBURL, "ugeneral_user","Prueba123_");
            
            
            // Diferentes errores o excepciones que se pueden presentar al crear la conexión con la bd.
        }catch(ClassNotFoundException | SQLException | NoSuchMethodException | SecurityException |
                 InstantiationException | IllegalAccessException | IllegalArgumentException | 
                 InvocationTargetException ex){
            
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null, ex);
            
        }   
    }
    
    //Al ser sincronizado significa que no va a haber más  de un proceso de conexión abierto
    public static synchronized Connection getConexion(){

        //En caso de que sea la primera petición para abrir comunicación con bd
        if (conexion==null){
            conexion=new Conexion();
        }
        return conn;
        
    }
    
    
}
