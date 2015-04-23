/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package genhoraris;

import java.awt.AWTException;
import java.util.ArrayList;
/*import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;*/


/**
 *
 * @author dani__000
 */
public class Graus extends Organitzacio {

    //public Graus() {

        /*LinkedList<String> Graus_Q1 = new LinkedList(Arrays.asList("Mecanica","Electrica",
                "Industrial", "Informatica", "Sist_Electronics", "Disseny"));
        Iterator<String> it = Graus_Q1.iterator();*/ 
    //}    
    public static String[] _Graus = new String[6];
    public static Graus Mecanica, Electrica, Industrial, Informatica, Sist_Electronics, Disseny;
    
    public static void Graus() throws AWTException {
        Mecanica = new Graus("Mecanica", 1);
        Electrica = new Graus("Electrica", 2);
        Industrial = new Graus("Industrial", 3);
        Informatica = new Graus("Informatica", 4); 
        Sist_Electronics = new Graus("Sist_Electronics", 5);
        Disseny = new Graus("Disseny", 6);

        _Graus[0] = Mecanica.toString1();
        _Graus[1] = Electrica.toString1();
        _Graus[2] = Industrial.toString1();
        _Graus[3] = Informatica.toString1();
        _Graus[4] = Sist_Electronics.toString1();
        _Graus[5] = Disseny.toString1();
        //System.out.println("All working");
        //public LinkedList<Organitzacio> Graus_Q1;
    }

    public Graus(String name, int ID) {
        super(name, ID);
    }
    

    @Override
    public String toString1(){
        return Name + super.toString1();
    }
    
}
