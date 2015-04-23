
/**
 *
 * @author dani__000
 */
public class Emmagatzemable extends Electrodomestic {
    // Fitxer Emmagatzemable.java
    private double mida;
    public static int quants;
    
    public Emmagatzemable (String nom, String companyia, double preu, double mida){
        super(nom, companyia, preu);
        this.mida = mida;
        quants++;
    }
    
    public String toString1(){
        return super.toString1() + "Emmagatzemable{Capacitat: " + mida + "}.";
    }
}
