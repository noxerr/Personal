/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package peClase;

import java.awt.AWTException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author dani__000
 */
public class PE {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     * @throws java.awt.AWTException
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws InterruptedException, AWTException, FileNotFoundException, IOException {
        //Robot rob = new Robot();
        int n = Integer.parseInt(args[0]);
        //int n = 1000000;
        
        /*DataOutputStream out = new DataOutputStream(new FileOutputStream("Vectores.rtf"));
        //DataOutputStream out2 = new DataOutputStream(new FileOutputStream("Vectores.rtf"));
        int i, j;
        int tamano3 = 750000;
        for (j = 0; j<2; j++){
            for (i = 0; i < tamano3; i++){
                Random ran = new Random();
                out.writeBytes(String.valueOf((short)ran.nextInt(50000)));
                //out2.writeShort(j);
                out.writeChar('\n');
            }
            out.writeChar('\n');
        }*/
        //DataInputStream in = new DataInputStream(new FileInputStream("Vectores.rtf"));
        
        //in.readLine();
        long mitjana = 0;
        for (int k = 0; k < 5; k++){
            try (DataInputStream in = new DataInputStream(new FileInputStream("C:\\Users\\dani__000\\Desktop\\Vectores.rtf"))) {
                short[] veco1 = new short[n];
                String str;
                short e;
                int a;
                String substring;
                for (int i = 0; i < n; i++){
                    str = in.readLine();
                    if (str.length() > 2){
                        substring = str.substring(0, str.length()-1);
                        a = Integer.parseInt(substring);
                        e = (short) a;
                        veco1[i] = e;
                    }
                    //veco1[1] = (short) in.readChar();
                    //veco1[i] = (short)in.readUnsignedShort();
                    //in.skipBytes(2);
                }
                Algoritmo alg = new Algoritmo();
                Long time1 = System.nanoTime()/1000000;
                alg.quickSort(veco1, 0, n-1);
                
                Long time2 = System.nanoTime()/1000000;
                System.out.println((time2-time1));
                mitjana = mitjana + (time2-time1);
            }
        }
        mitjana = mitjana/5;
        System.out.println("Mitjana de "+n+": " + mitjana );
        /*for(int i=0; i<10; i++){
        System.out.println(veco1[i]);
        }*/
    }
    
}
