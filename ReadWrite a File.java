/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bdapp;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author dani__000
 */    
public class BDApplication {
    
    
    public static void main(String[] args) {
        char cadena;
        int salto = 13;
         FileReader entrada=null;
         FileWriter salida=null;
         StringBuffer str=new StringBuffer();
         try  {
            entrada=new FileReader("C:\\Users\\dani__000\\Desktop\\PRPROP\\BDApp\\src\\bdapp\\texto.txt");
            salida=new FileWriter("C:\\Users\\dani__000\\Desktop\\PRPROP\\BDApp\\src\\bdapp\\copia.c");
            int c;
            while((c=entrada.read())!=-1){
                str.append((char)c);
                str.append(c);
                if (c == 13) {
                    //System.out.println("ahora ha salido");
                    cadena = '\n';
                    //System.out.println(cadena);
                }
                else cadena = '.';
                salida.append(cadena);
            }
            System.out.println(str);
            System.out.println("--------------------------------------");
            //salida.write(cadena);
            //salida.append("hey" + salto + "hola");
            //salida.write(salto);
       }catch (IOException ex) {
            System.out.println(ex);
       }finally{
//cerrar los flujos de datos
            if(entrada!=null){
                try{
                    entrada.close();
                }catch(IOException ex){}
            }
            if(salida!=null){
                try{
                    salida.close();
                }catch(IOException ex){}
            }
            System.out.println("el bloque finally siempre se ejecuta");
       }
       
         /*try{
         salida=new FileWriter("C:\\Users\\dani__000\\Desktop\\PRPROP\\BDApp\\src\\bdapp\\texto.txt");
         salida.write("hola que tal");
         }
         catch (IOException ex2){
             System.out.println(ex2);
         }*/
         
    }
}


