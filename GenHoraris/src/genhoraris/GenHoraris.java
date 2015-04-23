/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package genhoraris;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel Roca Lopez
 */
public class GenHoraris {

    public static boolean fin = false;
    public static final int NumClases = 8; //aules normals, suposarem nomes les del edifici A, ja que ho fem només del Q1
    public static final int NumLabs = 20; //aules informatica + laboratoris del A: 20
    public static final int Subjects_Grau = 5; // Assignatures per grau (suposem 5 per cadascun)
    public static final int NumGraus = 6; //
    public static final int NumGrupsLab = 2;
    
    private static int[][][] Horari_Def;
    
    public static int Torns_mati = 3; //de 8:30-10.30, 10.30-12.30, 12.30-14.30.
    public static int Dies_setmana = 5; //entenem que els dies setmana es el temps máx. per complir les hores/assignatura
    
    //public static LinkedList<String> Graus_Q1 = new LinkedList(Arrays.asList("Mecanica","Electrica",
    //   "Industrial", "Informatica", "Sist_Electronics", "Disseny"));
    public static String[][] ID0 = new String [5][3];
    public static String[][] ID1 = new String [5][3];
    public static String[][] ID2 = new String [5][3];
    public static String[][] ID3 = new String [5][3];
    public static String[][] ID4 = new String [5][3];
    public static String[][] ID5 = new String [5][3];
    

    public static String uno, dos, tres, cuatro, cinco;
    
    
    
    public static void main(String[] args) {
        //String[] hey = {"hola", "hey"};
        
        final String[] a = args;
        
        //LinkedList<Organitzacio> listaGrados = new LinkedList<>();
        
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    long t1 = new Date().getTime();
                    Graus.Graus();
                    Horari_Def = Organitzacio.Organitzacio(a);
                    //Frame fr = new Frame();
                    ////////////
                    Organitzacio.horariToTable(ID0, 0, Horari_Def);
                    Organitzacio.horariToTable(ID1, 1, Horari_Def);
                    Organitzacio.horariToTable(ID2, 2, Horari_Def);
                    Organitzacio.horariToTable(ID3, 3, Horari_Def);
                    Organitzacio.horariToTable(ID4, 4, Horari_Def);
                    Organitzacio.horariToTable(ID5, 5, Horari_Def);
                    ////////////
                    new Frame().setVisible(true);
                    
                    /*for (int j = 0; j < NumGraus; j++){
                        for (int b = 0; b < Dies_setmana; b++){
                            for (int c = 0; c < Torns_mati; c++){
                                System.out.print(Horari_Def[j][b][c] + " ");
                            }
                            System.out.print("\n");
                        }
                        System.out.print("\n");
                    }*/
                    long t2 = new Date().getTime();
                    System.out.println("Total T ejecucion: " + (t2 - t1) + " milisegundos");
                    //if (fin) System.exit(0);
                } catch (AWTException ex) {
                    Logger.getLogger(GenHoraris.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        
        Executors.newSingleThreadExecutor().execute(runnable);
        
    }
    
    
}
