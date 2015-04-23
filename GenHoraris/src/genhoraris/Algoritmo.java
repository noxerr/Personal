/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package genhoraris;

/**
 *
 * @author dani__000
 */
public class Algoritmo {
    
    public static int[][][] alg(int[][][] Aulas, int[][][] Labs){
        int[][][] horari = new int[GenHoraris.NumGraus][GenHoraris.Dies_setmana][GenHoraris.Torns_mati];
        /*
        * El nostre algoritme es basará en anar omplint grau per grau, sempre el més compacte posible, és a dir,
        * Mentres hi hagin classes disponibles a la hora següent, agafarem la següent hora, (primera o segona), y, 
        * per tal que no quedin hores lliures, com sabem que els laboratoris els fan per separats, es comprovarà
        * que quedi un laboratori lliure a ultima hora i un grup farà el laboratori d'una assignatura i un altre grup
        * el d'un altre assignatura.
        */
        for (int a = 0; a < GenHoraris.NumGraus; a++){
            for (int b = 0; b < GenHoraris.Dies_setmana; b++){
                for (int c = 0; c < GenHoraris.Torns_mati; c++){
                    horari[a][b][c] = -1;
                }
            }
        }
        
        for (int a = 0; a < GenHoraris.Dies_setmana; a++){
            for (int b = 0; b < GenHoraris.Torns_mati; b++){
                for (int c = 0; c < GenHoraris.NumClases; c++){
                    Aulas[a][b][c] = -1;
                }
            }
        }
        
        for (int a = 0; a < GenHoraris.Dies_setmana; a++){
            for (int b = 0; b < GenHoraris.Torns_mati; b++){
                for (int c = 0; c < GenHoraris.NumLabs; c++){
                    Labs[a][b][c] = -1;
                }
            }
        }
        
        
        for (int i = 0; i< GenHoraris.NumGraus; i++){
            alg(Aulas, Labs, horari, i);
        }
        return horari;
    }
    
    
    
    
    
    
    public static void alg(int[][][] Aulas, int[][][] Labs,
        int[][][] horari, int NumGrau){
    
        int dia = 0, torn = 0;
        int a, b, c;
        for (a = 0; a < GenHoraris.Subjects_Grau; a++){
            b = 0;
            while (Aulas[dia][torn][b] != -1 || torn >= 2){
                if (Aulas[dia][torn][b] != -1){
                    if (b < GenHoraris.NumClases-1) b++;
                    else{
                        b = 0;
                        if (torn >= 2) {
                            torn = 0;
                            dia++;
                            b = 0;
                        }
                        else {
                            b = 0;
                            torn++;
                        }
                    }
                }
                else{
                    torn = 0;
                    dia++;
                    b = 0;
                }
            }
            Aulas[dia][torn][b] = 1;
            horari[NumGrau][dia][torn] = b*100 + a*10; //aula * 100 + numAsignatura*10 + 0*grupo(= 0)
            torn++;
        }
        
        dia = 0;
        torn = 0;
        for (c = 0; c < GenHoraris.Subjects_Grau; c++){
            b = 0;
            int grup;
            for (grup = 0; grup < 2; grup++){
                while ((dia < 5) && (Labs[dia][torn][b] != -1 || horari[NumGrau][dia][torn] != -1)){
                    if (horari[NumGrau][dia][torn] != -1){
                        if (torn >= 2) {
                                torn = 0;
                                dia++;
                                b = 0;
                            }
                        else {
                            torn++;
                            b = 0;
                        }
                    }
                    else if (Labs[dia][torn][b] != -1){
                        if (b < GenHoraris.NumLabs-1) b++;
                        else{
                            b = 0;
                            if (torn >= 2) {
                                torn = 0;
                                dia++;
                            }
                            else torn++;
                        }
                    }
                }
                if (dia < 5) Labs[dia][torn][b] = 1;
                if (dia < 5)horari[NumGrau][dia][torn] = b*100 + c*10 + grup + 1; 
                //aula * 100 + numAsignatura*10 + numgrup (0 o 1)
            }
        }
    }

}
