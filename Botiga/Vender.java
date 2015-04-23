
/**
 *
 * @author dani__000
 */
/*
L’aplicació hauria de ser capaç¸ d’afegir nous productes, 
anotar noves ventes i treure un informe amb la descripció 
de cada producte i els diners venuts (ordenat per benefici).
*/


public class Vender {
    public static String cartuxoS;
    public static String EmmagS;
    public static String PaperS;
    public static String PilesS;
    
    
    //Cartuxo
    public Vender (String nom, String companyia, double preu, String tipus, 
            String impresora){
        Cartuxo a = new Cartuxo(nom, companyia, preu, tipus, impresora);
        //Electrodomestic._Cartuxo.add(a);
        cartuxoS = a.toString1();
    }
    
    //Emmagatzemable
    public Vender (String nom, String companyia, double preu, double mida){
        Emmagatzemable a = new Emmagatzemable(nom, companyia, preu, mida);
        //Electrodomestic._Emmagatzemable.add(a);
        EmmagS = a.toString1();
    }
    
    //Paper
    public Vender(String nom, String companyia, double preu, double mida, 
            double numPag){
        Paper a = new Paper(nom, companyia, preu, mida, numPag);
        //Electrodomestic._Paper.add(a);
        PaperS = a.toString1();
    }
    
    //Piles
    public Vender(String nom, String companyia, double preu, double quantitat, 
            String tipus){
        Piles a = new Piles(nom, companyia, preu, quantitat, tipus);
        //Electrodomestic._Piles.add(a);
        PilesS = a.toString1();
    }
    
}
