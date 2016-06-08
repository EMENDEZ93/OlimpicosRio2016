
package olimpicos;

import java.io.Serializable;

public class CDestacado implements Serializable{

private String NombreDestacados;    
private String ImagenDestacados;    
private String NacionalidadDestacados;    
private String PerfilDestacados;    
private String LogrosDestacados;    
private String OroDestacados;    
private String PlataDestacados;    
private String BronceDestacados;    

    public CDestacado(
            String NombreDestacados, 
            String ImagenDestacados, 
            String NacionalidadDestacados, 
            String PerfilDestacados, 
            String LogrosDestacados, 
            String OroDestacados, 
            String PlataDestacados, 
            String BronceDestacados) {
        
        this.NombreDestacados = NombreDestacados;
        this.ImagenDestacados = ImagenDestacados;
        this.NacionalidadDestacados = NacionalidadDestacados;
        this.PerfilDestacados = PerfilDestacados;
        this.LogrosDestacados = LogrosDestacados;
        this.OroDestacados = OroDestacados;
        this.PlataDestacados = PlataDestacados;
        this.BronceDestacados = BronceDestacados;
    }

    public String getNombreDestacados() {
        return NombreDestacados;
    }

    public void setNombreDestacados(String NombreDestacados) {
        this.NombreDestacados = NombreDestacados;
    }

    public String getImagenDestacados() {
        return ImagenDestacados;
    }

    public void setImagenDestacados(String ImagenDestacados) {
        this.ImagenDestacados = ImagenDestacados;
    }

    public String getNacionalidadDestacados() {
        return NacionalidadDestacados;
    }

    public void setNacionalidadDestacados(String NacionalidadDestacados) {
        this.NacionalidadDestacados = NacionalidadDestacados;
    }

    public String getPerfilDestacados() {
        return PerfilDestacados;
    }

    public void setPerfilDestacados(String PerfilDestacados) {
        this.PerfilDestacados = PerfilDestacados;
    }

    public String getLogrosDestacados() {
        return LogrosDestacados;
    }

    public void setLogrosDestacados(String LogrosDestacados) {
        this.LogrosDestacados = LogrosDestacados;
    }

    public String getOroDestacados() {
        return OroDestacados;
    }

    public void setOroDestacados(String OroDestacados) {
        this.OroDestacados = OroDestacados;
    }

    public String getPlataDestacados() {
        return PlataDestacados;
    }

    public void setPlataDestacados(String PlataDestacados) {
        this.PlataDestacados = PlataDestacados;
    }

    public String getBronceDestacados() {
        return BronceDestacados;
    }

    public void setBronceDestacados(String BronceDestacados) {
        this.BronceDestacados = BronceDestacados;
    }


    
}
