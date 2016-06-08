package olimpicos;

import java.io.Serializable;

public class CPaises2 implements Comparable<CPaises> , Serializable{

    public String NombrePais;

//variables para medallas    
    public int MOro, MPlata, MBronce, MTotal;

    public CPaises2(String NombrePaise, int MOro, int MPlata, int MBronce) {

        this.NombrePais = NombrePaise;
        this.MOro = MOro;
        this.MPlata = MPlata;
        this.MBronce = MBronce;
        this.MTotal = MOro + MPlata + MBronce;

    }

    public String getNombrePais() {
        return NombrePais;
    }

    public void setNombrePais(String NombrePais) {
        this.NombrePais = NombrePais;
    }

    public int getMOro() {
        return MOro;
    }

    public void setMOro(int MOro) {
        this.MOro = MOro;
    }

    public int getMPlata() {
        return MPlata;
    }

    public void setMPlata(int MPlata) {
        this.MPlata = MPlata;
    }

    public int getMBronce() {
        return MBronce;
    }

    public void setMBronce(int MBronce) {
        this.MBronce = MBronce;
    }

    public int getMTotal() {
        return MTotal;
    }

    public void setMTotal(int MTotal) {
        this.MTotal = MTotal;
    }
    

    @Override
    public int compareTo(CPaises o) {
         if (MOro == o.MOro) {
            
             if (MPlata < o.MPlata) {
             
                 return -1;
            
             } else if (MPlata == o.MPlata) {
             
                 if (MBronce < o.MBronce) {
                 
                     return -1;
                
                 }
            
             }
        } else if (MOro < o.MOro) {
           
            return -1;
        
        } else if (MOro > o.MOro) {
        
            return 1;
       
        }
        return 0;
    }
}
