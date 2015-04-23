/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Scanner;

/**
 *
 * @author Ferran
 */
public class exercici1 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      String paraula1, paraula2, paraula3, paraula4, paraula5;
      String[] taula = new String[5];  
      Scanner inputDevice = new Scanner(System.in);
      System.out.println("Entra la primera paraula >> ");
      paraula1 = inputDevice.nextLine();
      System.out.println("Entra la segona paraula >> ");
      paraula2 = inputDevice.nextLine();
      System.out.println("Entra la tercera paraula >> ");
      paraula3 = inputDevice.nextLine();
      System.out.println("Entra la quarta paraula >> ");
      paraula4 = inputDevice.nextLine();
      System.out.println("Entra la cinquena paraula >> ");
      paraula5 = inputDevice.nextLine();
      taula[0] = paraula1;
      taula[1] = paraula2;
      taula[2] = paraula3;
      taula[3] = paraula4;
      taula[4] = paraula5;
      boolean res = false;
      for(int i = 0; i < 4 ; i++){
          int n = 0;
          while(n < 5){
              if(n == i)n++;
              for(int j = 0; j < taula[i].length()-1 && !res; j++){
              String aux = taula[i].substring(j, taula[i].length());
              res = taula[n].startsWith(aux);
              if(res){
                  String sortida = taula[i].substring(0, taula[i].length() - aux.length());
                  System.out.println(sortida + taula[n]);
              }
              }  
            res = false;
            n++;
          }   
      }
    }
    
}
