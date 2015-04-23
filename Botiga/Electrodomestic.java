/**
 *
 * @author dani__000
 */
import java.util.ArrayList;

public class Electrodomestic {
    private String nom;
    private String companyia;
    private double preu;
    public  int total;
    
    public static ArrayList _Cartuxo = new ArrayList();
    public static ArrayList _Emmagatzemable = new ArrayList();
    public static ArrayList _Paper = new ArrayList();
    public static ArrayList _Piles = new ArrayList();
    
    
    public Electrodomestic (String nom, String companyia, double preu){
        this.nom = nom;
        this.companyia = companyia;
        this.preu = preu;
        total++;
    }
    
    public String toString1() {
        return "Electrodomestic {Nom: " + nom + ", Companyia: " + companyia + 
                ", Preu: " + preu + "}.";
    }
    
    public int total() {
        return total;
    }
}




