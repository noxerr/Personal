/**
 *
 * @author dani__000
 */
//Similar to Ejemplo.java


import java.util.Scanner;
import java.util.Iterator;


public class Electro {

    public static void main(String[] args) {
        
        //Scanner input = new Scanner(System.in);
        
        System.out.println("Benvinguts.\nAneu entran els electrodomestics"
                + " que compreu.\nEn acabar, teclejeu: finCompra.\n");
        
        String tipusElec, nom, companyia, tipus, impresora;
        double preu, mida, numPag, quantitat;
        tipusElec = ".";
        Scanner input = new Scanner(System.in);
        Scanner doubles = new Scanner (System.in);
        
        
        //LLISTA DE COMPRA
        //tipusElec = input.nextLine();
        while ((!"finCompra".equalsIgnoreCase(tipusElec)) && 
               (!"si".equalsIgnoreCase(tipusElec))){
        
            System.out.println("Entreu el tipus d'electrodomestic\n(Emmagatzemable,"
                    + " Cartuxo, Piles, Paper):");
           
            //Scanner input = new Scanner(System.in);
            tipusElec = input.nextLine();
            
            System.out.println("\nCom es diu el producte?");
            nom = input.nextLine();
            System.out.println("\nA quina companyia pertany?");
            companyia = input.nextLine();
            System.out.println("\nQuin preu te?");
            preu = doubles.nextDouble();
            
            if ("Emmagatzemable".equalsIgnoreCase(tipusElec)){
                
                System.out.println("\nQuina capacitat d'emmagatzament te?");
                mida = doubles.nextDouble();
                
                Vender a = new Vender(nom,companyia,preu,mida);
                Electrodomestic._Emmagatzemable.add(Vender.EmmagS);
                //Emmagatzemable a = new Emmagatzemable(nom,companyia,preu,mida);
                System.out.print(Emmagatzemable.quants + " emmagatzenables\n");
            }
            
            else if ("Cartuxo".equalsIgnoreCase(tipusElec)){
                
                System.out.println("\nQuin model es?");
                tipus = input.nextLine();
                
                System.out.println("\nPer a quina impresora es?");
                impresora = input.nextLine();
                
                new Vender(nom,companyia,preu,tipus,impresora);
                Electrodomestic._Cartuxo.add(Vender.cartuxoS);
                //Cartuxo a = new Cartuxo(nom,companyia,preu,tipus,impresora);
                System.out.print(Cartuxo.quants + " cartuxos\n");
            }
            
            else if ("Paper".equalsIgnoreCase(tipusElec)){
                
                System.out.println("\nQuin tamany te?");
                mida = doubles.nextDouble();
                
                System.out.println("\nCuantes pagines hi ha?");
                numPag = doubles.nextDouble();
                
                Vender a = new Vender(nom,companyia,preu,mida,numPag);
                Electrodomestic._Paper.add(Vender.PaperS);
                //Paper a = new Paper(nom,companyia,preu,mida,numPag);
                System.out.print(Paper.quants + " papers\n");
            }
            
            else if ("Piles".equalsIgnoreCase(tipusElec)){
                
                System.out.println("\nQuin tamany te?");
                quantitat = doubles.nextDouble();
                
                System.out.println("\nCuantes pagines hi ha?");
                tipus = input.nextLine();
                
                Vender a = new Vender(nom,companyia,preu,quantitat,tipus);
                Electrodomestic._Piles.add(Vender.PilesS);
                //Piles a = new Piles(nom,companyia,preu,quantitat,tipus);
                System.out.print(Piles.quants + " piles\n");
            }
            System.out.println("Heu acabat la compra? (Si per acabar)");
            tipusElec = input.nextLine();
            if("si".equalsIgnoreCase(tipusElec)) System.out.println("Fin");
        }
        
        
        //PRINT DE LES LLISTES
        if (!Electrodomestic._Cartuxo.isEmpty()){
            Iterator it = Electrodomestic._Cartuxo.iterator();
            //for (int i = 0; i < Electrodomestic._Cartuxo.size(); i++){
            while (it.hasNext()){
                System.out.println(it.next());
                //System.out.println(Electrodomestic._Cartuxo.get(i));
            }
        }
        if (!Electrodomestic._Emmagatzemable.isEmpty()){
            Iterator it = Electrodomestic._Emmagatzemable.iterator();
            //for (int i = 0; i < Electrodomestic._Cartuxo.size(); i++){
            while (it.hasNext()){
                System.out.println(it.next());
                //System.out.println(Electrodomestic._Cartuxo.get(i));
            }
        }
        if (!Electrodomestic._Paper.isEmpty()){
            Iterator it = Electrodomestic._Paper.iterator();
            //for (int i = 0; i < Electrodomestic._Cartuxo.size(); i++){
            while (it.hasNext()){
                System.out.println(it.next());
                //System.out.println(Electrodomestic._Cartuxo.get(i));
            }
        }
        if (!Electrodomestic._Piles.isEmpty()){
            Iterator it = Electrodomestic._Piles.iterator();
            //for (int i = 0; i < Electrodomestic._Cartuxo.size(); i++){
            while (it.hasNext()){
                System.out.println(it.next());
                //System.out.println(Electrodomestic._Cartuxo.get(i));
            }
        }
    }
}
        
        //System.out.println("Comença la aplicacio");
        //Runtime.getRuntime().exec("cls");
        
        /*Scanner hola = new Scanner(System.in);
        String abc;
        boolean a;
        abc = hola.nextLine();
        a = hola.nextBoolean();
        hola.findInLine(abc);
        
public class ScannerDemo {

   public static void main(String[] args) {

      String s = "Hello World! 3 + 3.0 = 6 true";

      // create a new scanner with the specified String Object
      Scanner scanner = new Scanner(s);

      // use US locale to be able to identify doubles in the string
      scanner.useLocale(Locale.US);

      // find the next double token and print it
      // loop for the whole scanner
      while (scanner.hasNext()) {

         // if the next is a double, print found and the double
         if (scanner.hasNextDouble()) {
            System.out.println("Found :" + scanner.nextDouble());
         }

         // if a double is not found, print "Not Found" and the token
         System.out.println("Not Found :" + scanner.next());
      }

      // close the scanner
      scanner.close();
   }
}
        
        */
