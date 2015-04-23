
/**
 *
 * @author dani__000
 */
public class Paper extends Electrodomestic {
    private double mida;
    private double numPag;
    public static int quants;
    
    public Paper (String nom, String companyia, double preu, double mida, 
            double numPag){
        super(nom, companyia, preu);
        this.mida = mida;
        this.numPag = numPag;
        quants++;
    }
    
    public String toString1(){
        return super.toString1() + "Paper{Mida del paper: " + mida + 
                ". Numero de Pagines: " + numPag + "}.";
    }
}    
