package olimpicos;

public class CPaises implements Comparable<CPaises> {

    public String NombrePais;

//variables para medallas    
    public int MOro, MPlata, MBronce, MTotal;

    public CPaises(String NombrePaise, int MOro, int MPlata, int MBronce) {

        this.NombrePais = NombrePaise;
        this.MOro = MOro;
        this.MPlata = MPlata;
        this.MBronce = MBronce;
        this.MTotal = MOro + MPlata + MBronce;

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
