
package olimpicos;

import java.io.Serializable;

/**
 * @author Edwin Mendez
 */
public class CDestacadoColombiano implements Serializable{

private String NombreDestacadosColombiano;
private String ImagenDestacadosColombiano;    
private String PerfilDestacadosColombiano;    
private String LogrosDestacadosColombiano;    
private String AñoMedallaDestacadosColombiano;    
private String TipoMedallaDestacadosColombiano;    
private String DeporteDestacadosColombiano;    

    public CDestacadoColombiano(
            String NombreDestacadosColombiano, 
            String ImagenDestacadosColombiano, 
            String PerfilDestacadosColombiano, 
            String LogrosDestacadosColombiano, 
            String AñoMedallaDestacadosColombiano, 
            String TipoMedallaDestacadosColombiano, 
            String DeporteDestacadosColombiano) {
        
        this.NombreDestacadosColombiano = NombreDestacadosColombiano;
        this.ImagenDestacadosColombiano = ImagenDestacadosColombiano;
        this.PerfilDestacadosColombiano = PerfilDestacadosColombiano;
        this.LogrosDestacadosColombiano = LogrosDestacadosColombiano;
        this.AñoMedallaDestacadosColombiano = AñoMedallaDestacadosColombiano;
        this.TipoMedallaDestacadosColombiano = TipoMedallaDestacadosColombiano;
        this.DeporteDestacadosColombiano = DeporteDestacadosColombiano;
    }

    public String getNombreDestacadosColombiano() {
        return NombreDestacadosColombiano;
    }

    public void setNombreDestacadosColombiano(String NombreDestacadosColombiano) {
        this.NombreDestacadosColombiano = NombreDestacadosColombiano;
    }

    public String getImagenDestacadosColombiano() {
        return ImagenDestacadosColombiano;
    }

    public void setImagenDestacadosColombiano(String ImagenDestacadosColombiano) {
        this.ImagenDestacadosColombiano = ImagenDestacadosColombiano;
    }

    public String getPerfilDestacadosColombiano() {
        return PerfilDestacadosColombiano;
    }

    public void setPerfilDestacadosColombiano(String PerfilDestacadosColombiano) {
        this.PerfilDestacadosColombiano = PerfilDestacadosColombiano;
    }

    public String getLogrosDestacadosColombiano() {
        return LogrosDestacadosColombiano;
    }

    public void setLogrosDestacadosColombiano(String LogrosDestacadosColombiano) {
        this.LogrosDestacadosColombiano = LogrosDestacadosColombiano;
    }

    public String getAñoMedallaDestacadosColombiano() {
        return AñoMedallaDestacadosColombiano;
    }

    public void setAñoMedallaDestacadosColombiano(String AñoMedallaDestacadosColombiano) {
        this.AñoMedallaDestacadosColombiano = AñoMedallaDestacadosColombiano;
    }

    public String getTipoMedallaDestacadosColombiano() {
        return TipoMedallaDestacadosColombiano;
    }

    public void setTipoMedallaDestacadosColombiano(String TipoMedallaDestacadosColombiano) {
        this.TipoMedallaDestacadosColombiano = TipoMedallaDestacadosColombiano;
    }

    public String getDeporteDestacadosColombiano() {
        return DeporteDestacadosColombiano;
    }

    public void setDeporteDestacadosColombiano(String DeporteDestacadosColombiano) {
        this.DeporteDestacadosColombiano = DeporteDestacadosColombiano;
    }
    
}
