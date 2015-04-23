/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package genhoraris;

import java.awt.AWTException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author dani__000
 */
public class Organitzacio {
    
    String Name;
    LinkedList<String> Subjects;
    
    Organitzacio(String name, int ID){
        this.Name = name;
        if (ID == 4 || ID == 5) 
            this.Subjects = new LinkedList(Arrays.asList("FOPR", "INCO", "FOMA", "EMPR", "FISI"));
        else if (ID == 3)
            this.Subjects = new LinkedList(Arrays.asList("FISI", "QUIM", "SOSTE", "FOMA", "INFO")); 
        else if (ID == 2)
            this.Subjects = new LinkedList(Arrays.asList("INFO", "FISI", "QUIM", "SOSTE", "FOMA")); 
        else if (ID == 1)
            this.Subjects = new LinkedList(Arrays.asList("SOSTE", "QUIM", "FISI", "FOMA", "INFO")); 
        else
            this.Subjects = new LinkedList(Arrays.asList("FOMA", "INFO", "FISI", "SOSTE", "QUIM")); 
    }
    

    public String toString1(){
        String frase = ": ";
        Iterator it = Subjects.iterator();
        if (it.hasNext()) frase = frase + it.next().toString();
        while (it.hasNext()) frase = frase + ", " + it.next().toString();
        
        return (frase + ".");
    }
    
    public static int[][][] Organitzacio(String[] args) throws AWTException{
        int[][][] Horari; // El primer es el grau, el segon el dia, el tercer el torn y el cuart la clase
        int[][][] Aulas = new int [GenHoraris.Dies_setmana][GenHoraris.Torns_mati][GenHoraris.NumClases];
        int[][][] Labs = new int [GenHoraris.Dies_setmana][GenHoraris.Torns_mati][GenHoraris.NumLabs];
        //Cuan algun dels vectors de 2-3D, en alguna posició tingui un valor diferent a 0, significarà
        //que ja se li ha assignat "parella", és a dir, estudiants-clase.
        
        Horari = Algoritmo.alg(Aulas, Labs);

        GenHoraris.fin = true;
        return Horari;
    }
   
    
    public static void horariToTable(String[][] id, int grau, int[][][] horari){
        int value, asig, x, y;
        String uno = "", dos = "", tres = "", cuatro = "", cinco = "";
        String asignatura = "";
        trans(uno, dos, tres, cuatro, cinco, grau);
        //System.out.println(GenHoraris.uno);
        for (int a = 0; a < GenHoraris.Dies_setmana; a++){
            for (int b = 0; b < GenHoraris.Torns_mati; b++){
                value = horari[grau][a][b];
                x = value/10;
                y = value/100;
                asig = x - y*10;
                if (asig == 0) asignatura = GenHoraris.uno;
                else if (asig == 1) asignatura = GenHoraris.dos;
                else if (asig == 2) asignatura = GenHoraris.tres;
                else if (asig == 3) asignatura = GenHoraris.cuatro;
                else if (asig == 4) asignatura = GenHoraris.cinco;
                //System.out.println(uno);
                if (value%10 == 0){
                    id[a][b] = asignatura + ", Aula: " + y;
                }
                else{
                    if (value%10 == 1){
                        id[a][b] = asignatura + ", Lab: " + y + " - Grup: 1";
                    }
                    else{
                        id[a][b] = asignatura + ", Lab: " + y + " - Grup: 2";
                    }
                }
                //id[a][b] = "" + value;
            }
        }
    }
    
    
    
    public static void trans(String uno, String dos, String tres, String cuatro, 
            String cinco, int grau){
        if (grau == 0){
            GenHoraris.uno = Graus.Mecanica.Subjects.get(0);
            //System.out.println(Graus.Mecanica.Subjects.get(0));
            GenHoraris.dos = Graus.Mecanica.Subjects.get(1);
            GenHoraris.tres = Graus.Mecanica.Subjects.get(2);
            GenHoraris.cuatro = Graus.Mecanica.Subjects.get(3);
            GenHoraris.cinco = Graus.Mecanica.Subjects.get(4);
        }
        else if (grau == 1){
            GenHoraris.uno = Graus.Electrica.Subjects.get(0);
            GenHoraris.dos = Graus.Electrica.Subjects.get(1);
            GenHoraris.tres = Graus.Electrica.Subjects.get(2);
            GenHoraris.cuatro = Graus.Electrica.Subjects.get(3);
            GenHoraris.cinco = Graus.Electrica.Subjects.get(4);
        }
        else if (grau == 2){
            GenHoraris.uno = Graus.Industrial.Subjects.get(0);
            GenHoraris.dos = Graus.Industrial.Subjects.get(1);
            GenHoraris.tres = Graus.Industrial.Subjects.get(2);
            GenHoraris.cuatro = Graus.Industrial.Subjects.get(3);
            GenHoraris.cinco = Graus.Industrial.Subjects.get(4);
        }
        else if (grau == 3){
            GenHoraris.uno = Graus.Informatica.Subjects.get(0);
            GenHoraris.dos = Graus.Informatica.Subjects.get(1);
            GenHoraris.tres = Graus.Informatica.Subjects.get(2);
            GenHoraris.cuatro = Graus.Informatica.Subjects.get(3);
            GenHoraris.cinco = Graus.Informatica.Subjects.get(4);
        }
        else if (grau == 4){
            GenHoraris.uno = Graus.Sist_Electronics.Subjects.get(0);
            GenHoraris.dos = Graus.Sist_Electronics.Subjects.get(1);
            GenHoraris.tres = Graus.Sist_Electronics.Subjects.get(2);
            GenHoraris.cuatro = Graus.Sist_Electronics.Subjects.get(3);
            GenHoraris.cinco = Graus.Sist_Electronics.Subjects.get(4);
        }
        else{//grau = 5
            GenHoraris.uno = Graus.Disseny.Subjects.get(0);
            GenHoraris.dos = Graus.Disseny.Subjects.get(1);
            GenHoraris.tres = Graus.Disseny.Subjects.get(2);
            GenHoraris.cuatro = Graus.Disseny.Subjects.get(3);
            GenHoraris.cinco = Graus.Disseny.Subjects.get(4);
        }
        //System.out.println(uno);
    }
    
    
}
