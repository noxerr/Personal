
/**
 *
 * @author dani__000
 */
public class Piles extends Electrodomestic {
    private double quantitat;
    private String tipus;
    public static int quants;
    
    
    public Piles (String nom, String companyia, double preu, double quantitat, 
            String tipus){
        super(nom, companyia, preu);
        this.quantitat = quantitat;
        this.tipus = tipus;
        quants++;
    }
    
    public String toString1(){
        return super.toString1() + "Piles{Quantitat de piles: " + quantitat + 
                ". Tipus de Piles: " + tipus + "}.";
    }
}   
