
package controller;

import gestion.EstudianteGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Estudiante;


@Named(value = "estudianteController")
@SessionScoped
public class EstudianteController extends Estudiante implements Serializable {

    
    public EstudianteController() {
    }
    
    //Método para insertar datos desde capa de presentación
    public String inserta(){
        
        if (EstudianteGestion.insertar(this)){
            
            return "list.xhtml";
            
        }else {
            
            FacesMessage mensaje= new FacesMessage (FacesMessage.SEVERITY_ERROR,
            "Error","Posible identificación duplicada");
            FacesContext.getCurrentInstance().addMessage("editaEstudianteForm:identificacion", mensaje);
            return "edita.xhtml";
        }
 
    }
    //Método para modificar datos desde capa de presentación
     public String modifica(){
        
        if (EstudianteGestion.actualiza(this)){
            
            return "list.xhtml";
            
        }else {
            
            FacesMessage mensaje= new FacesMessage (FacesMessage.SEVERITY_ERROR,
            "Error","Posible identificación duplicada");
            FacesContext.getCurrentInstance().addMessage("editaEstudianteForm:identificacion", mensaje);
            return "edita.xhtml";
        }
 
    }
     
     //Método para eliminar datos desde capa de presentación
     public String elimina(){
        
        if (EstudianteGestion.eliminar(this)){
            
            return "list.xhtml";
            
        }else {
            
            FacesMessage mensaje= new FacesMessage (FacesMessage.SEVERITY_ERROR,
            "Error","Posiblemente el id no exista en base de datos");
            FacesContext.getCurrentInstance().addMessage("editaEstudianteForm:identificacion", mensaje);
            return "edita.xhtml";
        }
 
    }
    
    //Método para editar datos desde capa de presentación 
     
     public String edita (String id){
         
         Estudiante estudiante = EstudianteGestion.getEstudiante(id);
         
         if (estudiante!=null){
             
             this.setId(estudiante.getId());
             this.setNombre(estudiante.getNombre());
             this.setApellido1(estudiante.getApellido1());
             this.setApellido2(estudiante.getApellido2());
             this.setFechaNaci(estudiante.getFechaNaci());
             this.setFechaIngr(estudiante.getFechaIngr());
             this.setGenero(estudiante.getGenero());
             return "edita.xhtml";
         }else{
             FacesMessage mensaje= new FacesMessage (FacesMessage.SEVERITY_ERROR,
            "Error","Posiblemente el id no exista en base de datos");
            FacesContext.getCurrentInstance().addMessage("editaEstudianteForm:identificacion", mensaje);
            return "list.xhtml";
             
         }
         
     }

    public List<Estudiante> getEstudiantes(){
        
        return EstudianteGestion.getEstudiantes();
        
    }
    
}
