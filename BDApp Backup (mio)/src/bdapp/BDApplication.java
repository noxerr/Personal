/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
FileInputStream fileIn=new FileInputStream("pedido.txt");
DataInputStream entrada=new DataInputStream(fileIn));

boolean readBoolean();
byte readByte();
int readUnsignedByte();
short readShort();
int readUnsignedShort();
char readChar();
int readInt();
String readLine();
long readLong();
float readFloat();
double readDouble();

----

FileOutputStream fileOut=new FileOutputStream("pedido.txt");
DataOutputStream salida=new DataOutputStream(fileOut));

void writeBoolean(boolean v);
void writeByte(int v);
void writeBytes(String s);
void writeShort(int v);
void writeChars(String s);
void writeChar(int v);
void writeInt(int v);
void writeLong(long v);
void writeFloat(float v);
void writeDouble(double v);

*/
        /*String hola ="hola que tal?";
        byte[] cuantos;
        cuantos = hola.getBytes();
        int ab = cuantos.length;
        System.out.println("tiene "+ ab + " bytes.");


int random = (int )(Math.random() * 50 + 1);

Random rand = new Random();
int  n = rand.nextInt(50) + 1;
\\50 is the maximum and the 1 is our minimum

Random ran = new Random();
ran.nextInt(37);
*/









package bdapp;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Executors;
//import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dani__000
 */    

public class BDApplication {
    public static boolean fin = false;
    public static Double n;
    public static int Numeros_ran = 1000000;
    //File.PathSeparator;
    public static void main(String[] args) { 
        //final int n = Integer.parseInt(args[1]);
        Scanner in = new Scanner(System.in);        
        Runnable runable1 = new Runnable() {
            @Override
            public void run() {
                Long t1 = new Date().getTime();
                //System.nanoTime()
                //System.currentTimeMillis()
                try {
                    Ej1();
                } catch (IOException ex) {
                    Logger.getLogger(BDApplication.class.getName()).log(Level.SEVERE, null, ex);
                }
                Long t2 = new Date().getTime();
                System.out.println("Time: " + (t2-t1) +".");
            }
        };
        
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
                Numeros n0 = new Numeros();
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
        
        //System.out.println(n);
        //runable2.run();
        while (1 == 1){
            n = in.nextDouble();
            Executors.newSingleThreadExecutor().execute(runable3);
        }
       // Executors.newSingleThreadExecutor().execute(runable2); //iniciamos una vez el runnable del frame
        //Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(runable1, 3000, 999600, TimeUnit.MILLISECONDS);
        //este runnable lo iniciamos a los 3 segundos de darle al play y una vez cada 999600 ms (es por poner un numero)
        
    }
    
    
    
    
    public static void Ej1() throws IOException{
        try (DataInputStream entrada = new DataInputStream 
                        (new FileInputStream ("C:\\Users\\dani__000\\Desktop\\PRPROP\\BDApp\\src\\bdapp\\Ventas.c"))) {
            try (DataOutputStream salida = new DataOutputStream
                                (new FileOutputStream ("C:\\Users\\dani__000\\Desktop\\PRPROP\\BDApp\\src\\bdapp\\Ventas.c"))) {
                double[] precios={1350, 400, 890, 6200, 8730};
                int[] unidades={5, 7, 12, 8, 30};
                String[] descripciones={"paquetes de papel", "lápices", "bolígrafos", "carteras", "mesas"};
                //String num = "123";
                //int parseInt = Integer.parseInt(num);
                //(If you have it in a StringBuffer, you'll need to do Integer.parseInt(myBuffer.toString()); instead).
                //String.valueOf(parseInt);
                for (int i=0; i<precios.length; i ++) {
                    //System.out.println(descripciones[i]);
                    salida.writeBytes(descripciones[i]);
                    salida.writeChar('\n');
                    //System.out.print(unidades[i] + " ");
                    //salida.writeBytes(String.valueOf(unidades[i])); //para verlo bn en el archivo
                    //salida.writeInt(3 + 48);// = 3 (escribe en asci)
                    salida.writeInt(unidades[i]); //para verlo bn dspues en el input.
                    salida.writeChar('\t');
                    //System.out.println(precios[i]);
                    //salida.writeBytes(String.valueOf(precios[i]));
                    salida.writeDouble(precios[i]);
                    salida.writeChar('\n');
                }
            }
            
            
            double precio;
            int unidad;
            String descripcion;
            double total=0.0;
            try {
                while ((descripcion=entrada.readLine())!=null) {
                    unidad=entrada.readInt();
                    entrada.readChar();       //lee el carácter tabulador
                    precio=entrada.readDouble();
                    System.out.println("has pedido "+unidad+" "+descripcion+" a "+precio+" pts.");
                    total=total+unidad*precio;
                    entrada.skipBytes(2);
                }
            }catch (EOFException e) {}
            System.out.println("por un TOTAL de: "+total+" pts.");
        }
    }
        
    public static void Crear_rand() throws FileNotFoundException, IOException{
        long t1 = new Date().getTime();
        DataOutputStream Numeros = new DataOutputStream
            (new FileOutputStream("C:\\Users\\dani__000\\Desktop\\PRPROP\\BDApp\\src\\bdapp\\Num_rand.c"));
        DataOutputStream Sec = new DataOutputStream
            (new FileOutputStream("C:\\Users\\dani__000\\Desktop\\PRPROP\\BDApp\\src\\bdapp\\Secuencia.c"));
        /*DataOutputStream prueba = new DataOutputStream
            (new FileOutputStream("C:\\Users\\dani__000\\Desktop\\PRPROP\\BDApp\\src\\bdapp\\prueba.c"));
        DataInputStream prueba2 = new DataInputStream
            (new FileInputStream("C:\\Users\\dani__000\\Desktop\\PRPROP\\BDApp\\src\\bdapp\\prueba.c"));*/
        int vector[] = new int[37];
        Random ran = new Random();
        int nu = 0;
        while (nu < Numeros_ran){
            int next = ran.nextInt(37);
            vector[next]++;
            //Sec.writeBytes(Integer.toString(next));
            //Sec.writeByte('\n');
            Sec.writeInt(next);
            nu++;
        }
        nu = 0;
        long t2 = new Date().getTime();
        System.out.println("Total: " + (t2 - t1) + " milisegundos");
        int coon = 0;
        int max = 0;
        int min = vector[0];
        int vmax = 0, vmin = 0;
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
            //Numeros.writeInt(vector[coon]);
            Numeros.writeBytes(Integer.toString(vector[coon]));
            Numeros.writeChar('\n');
            coon++;
        }
        System.out.println("\nmax i num: " + max + " " + vmax + ". min i num: "+ min + " " + vmin);
    }
}


