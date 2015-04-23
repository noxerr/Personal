
/**
 *
 * @author dani__000
 */
public class Cartuxo extends Electrodomestic {
    private String tipus;
    private String impresora;
    public static int quants;
    
    public Cartuxo (String nom, String companyia, double preu, String tipus, 
            String impresora){
        super(nom, companyia, preu);
        this.tipus = tipus;
        this.impresora = impresora;
        quants++;
    }
    
    public String toString1(){
        return super.toString1() + "Cartuxo{Tipus: " + tipus + ". Impresora: " + 
                impresora + "}.";
    }
}
