
package ruleta;

import java.text.DecimalFormat;
import javax.swing.JButton;
//import javax.swing.ImageIcon;
import javax.swing.JFrame;
//import javax.swing.JDialog;
//import javax.swing.JTextField;

/**
 *
 * @author dani__000
 */
public class Ruleta2 {
    
    public static final double MAX_TIRADAS = 9;
    public static final double NUMS_RULETA = 37;
    public static final double AMOUNT_FIND = 20;
    public static final double Tirada_Ini = 1;
    public static double SumaPerdidas = 0;
    public static final double RentaSiToca = 2;
    public static final double CasosNoFav = 19;
    //JFrame frame = new JFrame("Conjunto");
    //JDialog diag = new JDialog(frame);
    
    
    
    
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        double MaxInversion;
        double MaxBeneficios = 0;
        double BenefAcumulados = 0;
        double InvAcumulada = 0;
        int TiradaMaxB = 0;
        double BMedios, InvMedia, BMedios2;
        double[] inversiones = new double[(int) MAX_TIRADAS];
        
        DecimalFormat df = new DecimalFormat("0.000"); 
        double Total, Total2;
        double ChanceHit = (1 -  Math.pow((CasosNoFav/NUMS_RULETA), MAX_TIRADAS));
        
        //JFrame frame = new JFrame("Config");
        //JButton butt = new JButton("CButton");
        for (int i = 1; i <= MAX_TIRADAS; i++){
            SumaPerdidas += i * Tirada_Ini;
            inversiones[i-1] = SumaPerdidas;
            double aux = (i * Tirada_Ini * RentaSiToca) - SumaPerdidas;
            if (MaxBeneficios < aux) {
                MaxBeneficios = aux;
                TiradaMaxB = i;
            }
            BenefAcumulados += aux;
        }
        
        for (int i = 0; i < MAX_TIRADAS; i++){
            InvAcumulada += inversiones[i];
        }
        InvMedia = InvAcumulada / MAX_TIRADAS;
        
        MaxInversion = SumaPerdidas;
        BMedios = (BenefAcumulados - MaxInversion) / (MAX_TIRADAS + 1);
        BMedios2 = BenefAcumulados / MAX_TIRADAS;
        
        Total2 = BMedios2 * ChanceHit - InvMedia * (1-ChanceHit);
        Total = Total2 * AMOUNT_FIND;
        
        System.out.println("Max Inversion: " + MaxInversion + ". Beneficios Medios: " + df.format(BMedios2) + 
                "\nRenta: " + df.format(Total2) + " Chance hit: " + df.format(ChanceHit) + "\nTirada: " +
                TiradaMaxB + " Renta mejor tirada: " + MaxBeneficios + "\nInv Media: " + df.format(InvMedia) + "\n\nTOTAL: " 
                + df.format(Total));
    }

}
