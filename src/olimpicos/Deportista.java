
package olimpicos;

import java.io.Serializable;

public class Deportista implements Serializable{
   
private String Nombre;        
private String Deporte;        
private String Ruta;    
    
//constructor    
public Deportista( String Nombre , String Deporte , String Ruta) {

this.Nombre = Nombre;    
this.Deporte = Deporte;    
this.Ruta = Ruta; 

}


public String getNombre(){
return Nombre;
}
public void setNombre( String Nombre){
this.Nombre = Nombre;    
}
 
public String getDeporte(){
return Deporte;
}
public void setDeporte( String Deporte){
this.Deporte = Deporte;    
}

public String getRuta(){
return Ruta;
}
public void setRuta( String Ruta ){
this.Ruta = Ruta; 
}



}
