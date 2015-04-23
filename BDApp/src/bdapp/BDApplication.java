package bdapp;

import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/* @author dani__000 */    

public class BDApplication {
    public static boolean fin = false;
    public static Double n;
    public static int Numeros_ran = 1000000; //CANTIDAD DE NUMEROS ALEATORIOS A GENERAR Y CON LOS CUALES OPERAR
    //File.PathSeparator;
    public static void main(String[] args) { 
        Scanner in = new Scanner(System.in);                
        Runnable runable2 = new Runnable() {
            @Override
            public void run() {
                Long t1 = new Date().getTime();
                try {
                    Crear_rand();
                } catch (IOException ex) {
                    Logger.getLogger(BDApplication.class.getName()).log(Level.SEVERE, null, ex);
                }
                Long t2 = new Date().getTime();
                System.out.println("Time: " + (t2-t1) +".");
            }
        };
        
        Runnable runable3 = new Runnable() {
            @Override
            public void run() {
                Long t1 = new Date().getTime();
                Grafico v = new Grafico();
                //Numeros n0 = new Numeros();
                Calculos c1 = new Calculos();
                try {
                    c1.run(n);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(BDApplication.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(BDApplication.class.getName()).log(Level.SEVERE, null, ex);
                }
                Long t2 = new Date().getTime();
                System.out.println("Total: " + (t2 - t1) + " milisegundos");
                if (fin == true) System.exit(0);
            }
        };
        
        while (1 == 1){
            n = in.nextDouble();
            Executors.newSingleThreadExecutor().execute(runable3);
        }        
    }
           
   
    public static void Crear_rand() throws FileNotFoundException, IOException{
        long t1 = new Date().getTime();
        
        //DIRECTORIO DONDE GUARDAREMOS LOS NUMEROS GENERADOS, Y OTRO ARCHIVO DONDE GUARDAREMOS
        //EL NUMERO DE VECES QUE HA SALIDO CADA NUMERO (PARA COMPROBAR LA VARIABILIDAD
        DataOutputStream Numeros = new DataOutputStream
            (new FileOutputStream("C:\\Users\\dani__000\\Desktop\\PRPROP\\BDApp\\src\\bdapp\\Num_rand.c"));
        DataOutputStream Sec = new DataOutputStream
            (new FileOutputStream("C:\\Users\\dani__000\\Desktop\\PRPROP\\BDApp\\src\\bdapp\\Secuencia.c"));

        int vector[] = new int[37];
        Random ran = new Random();
        int nu = 0;
        while (nu < Numeros_ran){
            int next = ran.nextInt(37);
            vector[next]++;
            Sec.writeInt(next);
            nu++;
        }
        long t2 = new Date().getTime();
        System.out.println("Total: " + (t2 - t1) + " milisegundos");
        int coon = 0, max = 0, min = vector[0], vmax = 0, vmin = 0;
        while (coon < 37){
            System.out.print(coon + vector[coon] + " -- ");
            if (vector[coon] > max) {
                max = vector[coon];
                vmax = coon;
            }
            else if (vector[coon] < min) {
                min = vector[coon];
                vmin = coon;
            }
            Numeros.writeBytes(Integer.toString(vector[coon]));
            Numeros.writeChar('\n');
            coon++;
        }
        System.out.println("\nmax i num: " + max + " " + vmax + ". min i num: "+ min + " " + vmin);
    }
}
