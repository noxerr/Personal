



public class Ferran implements Jugador{
    //tabla
    private int[][] taula = new int[8][8];
    private final Ferran[] fills = new Ferran[8];
    private int Puntuacion;
    
    //private static int iteraciones = 0;
    //private static boolean fin = false;
    
    private static int columna_tirada = 0;
    //private static int actual = 0;
    private int[] jugadas = new int[8];
    private int[] jugadas2 = new int[8];//it 2
    private int[] jugadas3 = new int[8];//it 1
    private int[] iteraciones = new int[8];//it 0

     
    //tabla para los punteros
    /*private int punteros[][][] = new int[8][8][8];
    private int punteros2[][][][] = new int[8][8][8][8];
    //La primera cordenada de punteros es el hijo, las dos siguientes son la x */
    //i la y de la tabla
    
    
    

    @Override
    public int moviment(Tauler t, int color){
        Ferran arrel = new Ferran();
        //int tabla[][] = new int[8][8];
        //tabla[0][0] = 1;
        arrel.taula=llegir_tauler(t);
        crear_hijos (arrel, color, 0, 2, 0);
        
      int mejor = 0;
      int tirada = 0;
      if (t.movpossible(1)) tirada = 1;
      else if (t.movpossible(3)) tirada = 3;
      else if (t.movpossible(4)) tirada = 4;
      else if (t.movpossible(5)) tirada = 5;
      else if (t.movpossible(2)) tirada = 2;
      else if (t.movpossible(7)) tirada = 7;
      else if (t.movpossible(0)) tirada = 0;
      else if (t.movpossible(6)) tirada = 6;
      boolean parar = false;
      //int contador = 0;
      for (int k = 0; k <8 && !parar; k++){
                /*if (jugadas[k] == -2) {
                    tirada = k;
                    parar = true;
                }*/
                /*if (jugadas2[k] == -2) {
                    tirada = k;
                    parar = true;
                }*/
                if (jugadas[k] > mejor) {
                    mejor = jugadas[k];
                    tirada = k;
                    //parar = true;
                }
                //if (jugadas[k] == 3) contador++;
                //else jugadas[k]=3;
                
                //tirada = k;
                System.out.print(jugadas[k]);
                
       }
      /*if (contador > 2 && jugadas[0] != 5 && tirada == 0){
            if (t.movpossible(1)) tirada = 1;
            else if (t.movpossible(3)) tirada = 3;
            else if (t.movpossible(4)) tirada = 4;
            else if (t.movpossible(5)) tirada = 5;
            else if (t.movpossible(2)) tirada = 2;
            else if (t.movpossible(7)) tirada = 7;
            else if (t.movpossible(0)) tirada = 0;
            else if (t.movpossible(6)) tirada = 6;
      }*/
      /*System.out.print("\n 2: ");
      for (int k = 0; k <8; k++){
          System.out.print(jugadas2[k]);
      }*/
      //parar = false;
      System.out.print("\n");
      
       columna_tirada = tirada;
       for (int k = 0; k <8; k++){
           jugadas[k] = 0;
           iteraciones[k] = 0;
           //jugadas2[k] = 0;
       }
        //crear_hijos2 (-1, arrel);
        
        //iteraciones = 0;

        //print_tauler(arrel.taula);
        
        return columna_tirada;   
       
       
        
            
    }
        
    

  
    @Override
    public String nom(){
        return "Ferran i Dani";
    }

  private static int[][] llegir_tauler(Tauler t){
       int tabla[][] = new int[8][8];
       for(int y=0;y<8;y++){
           for(int x=0;x<8;x++){
              tabla[y][x]=t.getColor(x,y);
              
           }
       }
       return tabla;
  }
  
  private static void print_tauler(int[][] tabla){
      for(int y=7;y>=0;y--){
           for(int x=0;x<7;x++){
               System.out.print(tabla[x][y]);
           }
           System.out.print("\n");
       }
       System.out.println("");
  }
  
  private static boolean mov_posible (int columna, int[][] taula){
      boolean res = false;
      if(taula[columna][7] == 0)res=true;
        return res;      
  }
  
  
  private static int[][] copia_tabla (int[][] tabla){
      int[][] copia = new int[8][8];
      for (int y = 0; y < 8 ; y++){
              for (int x = 0; x < 8; x++){
                  copia[x][y] = tabla[x][y];
              }
      }
      return copia; 
  }
  
  private static void enfonsar(int[][] tabla, int x){
          for(int y = 7; y > 0 && tabla[x][y-1] == 0; y--){
                tabla[x][y-1] = tabla[x][y];
                tabla[x][y] = 0;
            
          }
  }
  
  
  private void crear_hijos (Ferran arrel, int color, int iteracion2, int columna, int pos){
      //int iteracion = iteracion2;
      int player;
      int iteracion = iteracion2;
      //System.out.println("piso: " + iteracion);
      if (iteracion < 4 && arrel != null){
            for (int m = 0; m < 8; m++){
               arrel.fills[m] = new Ferran();
               arrel.fills[m].taula = copia_tabla(arrel.taula);
            }    
            if( iteracion==0 || iteracion==2) player = color;
            else player = -1*color;
            for(int x = 0;x < 8; x++){
                 if (arrel.fills[x] != null && mov_posible(x, arrel.taula)){
                            arrel.fills[x].taula[x][7] = player;
                            enfonsar(arrel.fills[x].taula, x);
                            arrel.fills[x].Puntuacion = euristica(arrel.fills[x].taula, color);
                            //System.out.println("Puntuacion...: "+ arrel.fills[x].Puntuacion);
                            
                            if (arrel.fills[x].Puntuacion == -2){
                                //System.out.println("Puntuacion: "+ arrel.fills[x].Puntuacion);
                                arrel.fills[x] = null;
                                //arrel.Puntuacion = -2;
                                jugadas[columna] = -2;
                                iteraciones[columna] = pos;
                                //if (iteracion <2) jugadas2[x] = -2;
                            }
                            else if (arrel.fills[x].Puntuacion == 5 /*&& iteracion == 0*/){
                                //columna = x;
                                //columna_tirada = columna;
                                if (jugadas[columna] != -2) {
                                    jugadas[columna] = 5;
                                    iteraciones[columna] = pos;
                                }
                                else if (iteraciones[columna] != pos /*&& iteracion != 3*/ && iteracion != 3) jugadas[columna] = 5;
                                //if (iteracion <2 && jugadas2[x] != -2) jugadas2[x] = 5;
                                //System.out.println("Puntuacion: "+ arrel.fills[x].Puntuacion);
                            }
                            //else if (arrel.fills[x].Puntuacion == 5) columna = x;
                            
                            else if (arrel.fills[x].Puntuacion == 3) {
                                //columna_tirada = columna;
                                //actual = arrel.fills[x].Puntuacion;
                                if(jugadas[columna] != -2 && jugadas[columna] != 5){
                                    jugadas[columna] = 3;
                                    iteraciones[columna] = pos;
                                }
                                else if (iteraciones[columna] != pos /*&& iteracion != 3 */&& iteracion != 3) jugadas[columna] = 3;
                                //if (iteracion <2 && jugadas2[x] != -2) jugadas2[x] = 3;
                                //System.out.println("Puntuacion: "+ arrel.fills[x].Puntuacion);
                                //columna_tirada = columna;
                            }
                            else jugadas[x] = 0;
                            /*if (!mov_posible(columna_tirada, arrel.taula)){
                                if (mov_posible(0, arrel.taula)) columna_tirada = 0;
                                else if (mov_posible(1, arrel.taula)) columna_tirada = 1;
                                else if (mov_posible(2, arrel.taula)) columna_tirada = 2;
                                else if (mov_posible(3, arrel.taula)) columna_tirada = 3;
                                else if (mov_posible(4, arrel.taula)) columna_tirada = 4;
                                else if (mov_posible(5, arrel.taula)) columna_tirada = 5;
                                else if (mov_posible(6, arrel.taula)) columna_tirada = 6;
                                else if (mov_posible(7, arrel.taula)) columna_tirada = 7;
                            }*/
                            
                            
                            //print_tauler(arrel.fills[x].taula);    
                            //columna_tirada = x;
                 }
                 else arrel.fills[x] = null;

                 //if () crear_hijos(player, arrel.fills[x]);
            }
            
            for (int y = 0; y < 8; y++){
                     //System.out.println("piso2: " + iteracion);
                     if (iteracion == 0 && arrel.fills[y] != null) crear_hijos(arrel.fills[y], color, iteracion+1, y, y);
                     else if (iteracion < 3 && arrel.fills[y] != null) crear_hijos(arrel.fills[y], color, iteracion+1, columna, y);
            }
            //columna_tirada = columna;
            //iteracion++;
            //System.out.println(columna);
            
            
      }
   }
  
private int euristica(int[][] tabla, int color){
    //boolean fin;
    int puntos = 0;
    Puntuacion = puntos;
    //columna_tirada = 0;
    if(guanya_ferran(tabla,color))puntos = 5;
    else if(pierde_ferran(tabla,color))puntos = -2;
    else puntos = 3;
    //if (Puntuacion == 5) fin = true;
    return puntos;
}
//Devuelve true si ferran pierde
private boolean pierde_ferran(int[][]tabla, int color){
    boolean res=false;
    for(int y=0;y<8 && !res;y++){
        for(int x=0;x<8 && !res;x++){
            if(tabla[x][y] == (color*(-1))){
                res=mirar_possible_win(tabla,color*(-1), x, y,-1);
            }
        }
    }    
    return res;    
}

//Devuelve true si el jugador gana
private boolean guanya_ferran(int[][] tabla, int color){
    boolean res=false;
    for(int y=0;y<8 && !res;y++){
        for(int x=0;x<8 && !res;x++){
            if(tabla[x][y] == (color*(-1))){
                res=mirar_possible_win(tabla,color, x, y, 0);
            }
        }
    }    
    return res;    
}


//Mira las distintas posiblidades, dada una pieza , el jugador, i las coordenadas de esta misma
    @SuppressWarnings("empty-statement")
    private boolean mirar_possible_win(int[][] tabla, int color,int x,int y, int quien){
  boolean res=false;
  //print_tauler(tabla);
  //mirar horitzontal
  int a = x, b = y;
  int conth=1;
  if (quien == 0) conth=0;;
  while(conth < 4 && x<7 && tabla[x+1][y] == color){
      conth++;
      x++;
  }
  if (conth > 3)res=true;
  x = a;
  y = b;
  //mirar diagonal avall
  int contda=1;
  if (quien == 0) contda=0;;
  while(contda < 4 && x<7 && y>0 && tabla[x+1][y-1]== color && !res){
      contda++;
      x++;
      y--;
  }
  if (contda > 3)res=true;
  x = a;
  y = b;
  //mirar diagonal up
  int contdu=1;
  if (quien == 0) contdu=0;;
  while(contdu < 4 && x>0 && y<7 && tabla[x-1][y+1] == color && !res){
      contdu++;
      x--;
      y++;
  }
  if(contdu > 3)res=true;
  x = a;
  y = b;
  
  
  //////
  
    //mirar diagonal avall
  int contda2=1;
  if (quien == 0) contda2=0;;
  while(contda2 < 4 && x<7 && y<7 && tabla[x+1][y+1]== color && !res){
      contda2++;
      x++;
      y++;
  }
  if (contda2 > 3)res=true;
  x = a;
  y = b;
  //mirar diagonal up
  int contdu2=1;
  if (quien == 0) contdu2=0;;
  while(contdu2 < 4 && x<7 && y<7 && tabla[x+1][y+1] == color && !res){
      contdu2++;
      x++;
      y++;
  }
  if(contdu2 > 3)res=true;
  x = a;
  y = b;
  ///
  
  //mirar vertical
  int contv=1;
  if (quien == 0) contv=0;;
  while(contv < 4 && y<7 && tabla[x][y+1] == color && !res){
      contv++;
      y++;
  }
  if(contv >3)res=true;
  return res;
}
  
}
