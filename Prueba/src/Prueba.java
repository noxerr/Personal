
/**
 *
 * @author dani__000
 */

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.util.Date;
//import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Prueba {
    
    /*
    Aquesta es la clase principal, amb el main, desde el qual cridarem a 
    la resta de clases.
    
    L'objectiu principal del programa es automatitzar el joc del "bejewelled".
    El joc és del estil Candy Crush saga, es a dir, consisteix a juntar pedres
    del mateix color juntant 3 o més. Tanmateix, si una de les que juntes és brillant,
    les 9 que estan al voltant també et donen punts i d'esapareixen.
    
    
    El nostre programa té 3 fases.
    
    1a: Captura la situació actual de la pantalla, coloca en una matriu de 8x8
    una inicial en cada posicio, que correspon al color de la pedra.
    
    2a: Té un ordre de prioritat a l'hora de decidir la jugada. En primer lloc,
    juntar 4 o mes ja que donen més punts i ens tornen una pedra brillant. 
    En segon lloc de prioritat, juntar 3 o mes on hi aparegui una pedra brillant,
    per tal de guanyar més punts.
    Per últim, moure qualsevol jugada que hi hagi.
    
    En totes tres prioritats, el resultat el comença a buscar desde sota de la matriu,
    es a dir, desde abajais a la dreta de tot, cap a l'esquerra i pujant, de manera que,
    si troba una jugada abaix de tot, sera millor que adalt ja que al moure abaix,
    es mouen casi totes les peces de la pantalla i pot generar combos.
    
    3a: Consisteix a fer el moviment. Rep la posició actual del ratoli i la posició de la pedra.
    Tot seguit, cridant a un altra clase, fem que es mogui però, el java ens dona una funció
    que es MouseMove la qual teletransporta el ratoli pero aixo ho poden detectar en el joc,
    per tant la nostra clase calcula la distancia entre la posicio final i inicial,
    fa un random de posicions intermitges per les que passarà el ratoli i random de velocitat.
    Tot seguit, fa el clic i sense "deixar anar" el ratoli, "simulant-ho, evidentment", el mou 
    a la posició on volem deixar la pedra.
    */
        
    public static float[] hsbvals = new float[3]; //En cada posició anira un valor del hue, saturation, brightness.

    public static String idPieza(Color a){
        String b;
        int red, blue, green, h, s;
        blue = a.getBlue();
        green = a.getGreen();
        red = a.getRed();
        Color.RGBtoHSB(red, green, blue, hsbvals); //com no tenim una funció que ens detecti el HSB, ho fem passant el RGB.
        h = (int)(hsbvals[0] * 360); //la h la multipliquem *360 per pasar-ho a graus.
        s = (int) (hsbvals[1] * 100); //Ho multipliquem *100 perque ens ho donen de 0 a 1
        
        if (h == 0 && s == 0) b = "W";
        else if (h >= 18 && h < 45) {
            b = "O";
            if (s < 20 && s != 0) brill = true; //El boolean brill significa que brilla.
        }
        else if (h >= 90 && h < 148) {
            b = "G";
            if (s < 20 && s != 0) brill = true;
        }
        else if(h > 265 && h <= 315){
            b = "P";
            if (s < 20 && s != 0) brill = true;
        }
        else if (h >= 165 && h < 255) {
            b = "B";
            if (s < 20 && s != 0) brill = true;
        }
        else if (h >= 45 && h < 85) {
            b = "Y";
            if (s < 20 && s != 0) brill = true;
        }
        else if ((h >= 0 && h < 18) || (h <= 360 && h > 315)) {
            b = "R";
            if (s < 20 && s != 0) brill = true;
        }
        else b = "1"; //Si en la matriu apareix un 1 significa que en aquella posició no ha detectat el color
        
        return b;
    }
    
        
    public static String[][] tabla = new String [8][8]; //matriz normal
    public static char[][] tablaB = new char [8][8]; //matriu que indica si aquella pos. brilla o no.
    public static boolean brill = false; //boolea que indica si brilla
    public static boolean fin = false; //boolea que es posa a true si la partida ha acabat, perque pari els clics.
    //public static int n = 0;
    public static void main (String[] args) throws AWTException {

        //probar(args);

        //Amb el sistema de runnable el que fem es cridar a una funcio un o mes cops, en el moment que volguem
        //i amb unes condicions que nosaltres decidim, a més, així podem calcular la eficiencia del nostre
        //programa. Posem un timer al principi i un al final, restem, i tenim els milisegons que ha trigat.
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    long t1 = new Date().getTime();
                    //System.out.println(t1);
                    if (!fin) probar(new String[0]);
                    long t2 = new Date().getTime();
                    //System.out.println(t2);
                    System.out.println("Total: " + (t2 - t1) + " milisegundos");
                    if (fin) System.exit(0);
                } catch (AWTException ex) {
                    Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        
        Runnable runnable2 = new Runnable(){ //el GhostMouse en un principio tenia que ser para mover el raton poco a poco
            //finalmente solo es un frame que sirve para parar los clics con una tecla, por si se volviese loco.
            @Override
            public void run(){
                GhostMouse ghost = new GhostMouse();
                ghost.run();
            }
        };
        //GhostMouse ghost = new GhostMouse();
        Executors.newSingleThreadExecutor().execute(runnable2); //iniciamos una vez el runnable del frame
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(runnable, 3000, 999600, TimeUnit.MILLISECONDS);
        //este runnable lo iniciamos a los 3 segundos de darle al play y una vez cada 999600 ms (es por poner un numero)
        
        
        /*Runnable runnable2 = new Runnable(){
            @Override
            public void run(){
                    System.exit(1);
            }
        };
        Executors.newSingleThreadScheduledExecutor().schedule(runnable2, 1, TimeUnit.MILLISECONDS);*/
    }
    
    public static void probar(String[] args) throws AWTException {
        Robot robot = new EasingRobot(); //en vez de la clase robot, llamamos a la subclass que hemos creado
        //robot.delay(3000); 
        int delays, delays2;
        //n++;
        while(!fin){
            long t1 = new Date().getTime();
        delays = (int) (Math.random()*40); delays2 = (int) (Math.random()*20); //creamos algunos random para despues
        robot.delay(270+delays);
        
        
        BufferedImage img = robot.createScreenCapture(new Rectangle (Toolkit.getDefaultToolkit().getScreenSize()));
        //Creamos una captura de pantalla, sobre la cual trabajaremos los pixels,
        
        int j = 0, y = 205, xinicio=627, yinicio=262;
        //ESTAS COORDENADAS SON MUY IMPORTANTES, es el valor fijo del mouse para saber en que cuadrado de la matriz ira
        //y es la coordenada y del punto del que miraremos el color, de la primera ficha, y lo mismo con la x de abajo
        int x, i;
        String temp;
        while (y < 640){ //mientras no se salga de la pantalla..
            x = 570;
            i = 0;
            while (x < 990){
                temp = idPieza(new Color(img.getRGB(x,y))); //aqui cogemos el color con la funcion de antes, segun las x,y
                tabla[i][j] = temp; //lo colocamos en la matriz
                if (brill) { //si el static boolean esta a true lo ponemos tmbn en la matriz de brillantes y ponemos a false
                    tablaB[i][j] = '1';
                    brill = false;
                }
                else tablaB[i][j] = '0'; //1 en la matriz de brill significa que en esa pos de la matriz normal, brilla
                i++;
                x += 57; //es lo que mide una piedra, por lo tanto, avanzamos al mismo punto de la siguiente piedra
            }
            j++;
            y += 57;
        }
        

        if ("O".equals(idPieza(new Color(img.getRGB(508,408)))) &&
            "Y".equals(idPieza(new Color(img.getRGB(491,453)))) &&
            "Y".equals(idPieza(new Color(img.getRGB(517,447)))) &&
            "O".equals(idPieza(new Color(img.getRGB(558,409)))) &&
            "O".equals(idPieza(new Color(img.getRGB(580,414)))) &&
            "O".equals(idPieza(new Color(img.getRGB(644,405)))) &&
            "R".equals(idPieza(new Color(img.getRGB(650,332))))) fin = true;
        //EN ACABAR LA PARTIDA, SURTEN UNES LLETRES VERMELLES I TRONGES AMB EL NOM DEL JOC
        //EL QUE FEM PER SABER QUE HEM ACABAT LA PARTIDA ES COMPROBAR UNS QUANTS PIXELS DE LA CAPTURA ANTERIOR
        //PER SABER QUE INDICA EL FIN.
        
        if (!fin){
        
        
        
            //AQUI TENIM TOTS ELS FOR QUE COMPROVEN ELS TIPUS DE JUGADA QUE FAREM, CADASCUN DELS BOOLEANS
            //INDICA SI JA HEM TROBAT JUGADA PER TAL DE "TALLAR" LES BRANQUES I GUANYAR EFICIENCIA.
            //EL NUMERO QUE ACOMPAÑA ALS TROBAT SEMPRE ES: 0 = DRETA, 1 ABAIX, 2 ESQUERRA, 3 ADALT.
            //ANEM AVANÇANT EN LA MATRIU I ANEM COMPROVANT ALS COSTATS, I EL CAS QUE SIGUI, PER EXEMPLE:
            //VERMELL, ALTRE, VERMELL, VERMELL, EN AQUEST CAS, COMPROVARIEM SI A SOBRE O ABAIX DEL "ALTRE"
            //HI HA UNA VERMELLA. EN CAMBI, SI ES ALTRE, V, V, ALTRE COMPROBARIEM ELS COSTATS DELS "ALTRE" QUE NO 
            //COINCIDEIXIN AMB LES V QUE JA TENIM. AIXÒ ES EL QUE FAN ELS FOR, CRIDANT A LES CLASES COMPROBAR.
            
            //En el primer for al buscar figures de 4 no importen les brillants, en la resta si, per tant tornem
            //a utilitzar el static boolean brill.
            
        //Obtener jugada
        int X = 0, Y = 0;
        int XX = 0, YY = 0;
        int XXX, YYY;
        int trobat0 = -1, trobat1 = -1, trobat2 = -1, trobat3 = -1, trobat4 = -1, trobat5 = -1;
        boolean switcher = false, switcherB = false, switcherF = false;
        //////////PRIMER
        //////////FOR
        ///////////
        //////////
        ////////////
        for(YYY = 7; YYY > 2 && !switcherF; YYY--){
            for (XXX = 7; XXX > 2 && !switcherF; XXX--){
                   if (!switcherF && tabla[XXX][YYY].equalsIgnoreCase(tabla [XXX-2][YYY]) && 
                           tabla[XXX][YYY].equalsIgnoreCase(tabla [XXX-3][YYY])){
                                trobat4 = Comprobar.comprobarTriHoriz(tabla[XXX][YYY], XXX-1, YYY);
                                if (trobat4 != -1){
                                    switcherF = true;
                                    X = XXX-1;
                                    Y = YYY-1;
                                }
                   }
                    if (!switcherF && trobat4 == -1 && tabla[XXX][YYY].equalsIgnoreCase(tabla [XXX-1][YYY]) && 
                           tabla[XXX][YYY].equalsIgnoreCase(tabla [XXX-3][YYY])){
                                trobat4 = Comprobar.comprobarTriHoriz(tabla[XXX][YYY], XXX-2, YYY);
                                if (trobat4 != -1){
                                    switcherF = true;
                                    X = XXX-2;
                                    Y = YYY-1;
                                }
                    }
                   ///
                    if (!switcherF && tabla[XXX][YYY].equalsIgnoreCase(tabla [XXX][YYY-2]) &&
                           tabla[XXX][YYY].equalsIgnoreCase(tabla [XXX][YYY-3])){
                                trobat5 = Comprobar.comprobarTriVert(tabla[XXX][YYY], XXX, YYY-1);
                                if (trobat5 != -1){
                                    switcherF = true;
                                    X = XXX-1;
                                    Y = YYY-1;
                                }
                    }
                    if (!switcherF && trobat5 == -1 && tabla[XXX][YYY].equalsIgnoreCase(tabla [XXX][YYY-1]) &&
                           tabla[XXX][YYY].equalsIgnoreCase(tabla [XXX][YYY-3])){
                           trobat5 = Comprobar.comprobarTriVert(tabla[XXX][YYY], XXX, YYY-2);
                           if (trobat5 != -1){
                               switcherF = true;
                               X = XXX-1;
                               Y = YYY-2;
                           }
                    }
            }
            if (!switcherF && YYY > 2 && tabla[0][YYY].equalsIgnoreCase(tabla [0][YYY-2]) &&
                    tabla[0][YYY].equalsIgnoreCase(tabla [0][YYY-3])){
                        trobat5 = Comprobar.comprobarTriVert(tabla[0][YYY], 0, YYY-1);
                        if (trobat5 != -1){
                            switcherF = true;
                            X = -1;
                            Y = YYY-1;
                        }
            }
            if (!switcherF && YYY > 2 && tabla[0][YYY].equalsIgnoreCase(tabla [0][YYY-1]) &&
                    tabla[0][YYY].equalsIgnoreCase(tabla [0][YYY-3])){
                        trobat5 = Comprobar.comprobarTriVert(tabla[0][YYY], 0, YYY-2);
                        if (trobat5 != -1){
                            switcherF = true;
                            X = -1;
                            Y = YYY-2;
                        }
            }
            //
            if (!switcherF && YYY > 2 && tabla[YYY][0].equalsIgnoreCase(tabla [YYY-2][0]) &&
                    tabla[YYY][0].equalsIgnoreCase(tabla [YYY-3][0])){
                        trobat4 = Comprobar.comprobarTriHoriz(tabla[YYY][0], YYY-1, 0);
                        if (trobat4 != -1){
                            switcherF = true;
                            X = YYY-1;
                            Y = -1;
                        }
            }
            if (!switcherF && YYY > 2 && tabla[YYY][0].equalsIgnoreCase(tabla [YYY-1][0]) &&
                    tabla[YYY][0].equalsIgnoreCase(tabla [YYY-3][0])){
                        trobat4 = Comprobar.comprobarTriHoriz(tabla[YYY][0], YYY-2, 0);
                        if (trobat4 != -1){
                            switcherF = true;
                            X = YYY-2;
                            Y = -1;
                        }
            }
            //
            if (!switcherF && YYY > 2 && tabla[1][YYY].equalsIgnoreCase(tabla [1][YYY-2]) &&
                    tabla[1][YYY].equalsIgnoreCase(tabla [1][YYY-3])){
                        trobat5 = Comprobar.comprobarTriVert(tabla[1][YYY], 1, YYY-1);
                        if (trobat5 != -1){
                            switcherF = true;
                            X = 0;
                            Y = YYY-1;
                        }
            }
            if (!switcherF && YYY > 2 && tabla[1][YYY].equalsIgnoreCase(tabla [1][YYY-1]) &&
                    tabla[1][YYY].equalsIgnoreCase(tabla [1][YYY-3])){
                        trobat5 = Comprobar.comprobarTriVert(tabla[1][YYY], 1, YYY-2);
                        if (trobat5 != -1){
                            switcherF = true;
                            X = 0;
                            Y = YYY-2;
                        }
            }
            //
            if (!switcherF && YYY > 2 && tabla[YYY][1].equalsIgnoreCase(tabla [YYY-2][1]) &&
                    tabla[YYY][1].equalsIgnoreCase(tabla [YYY-3][1])){
                        trobat4 = Comprobar.comprobarTriHoriz(tabla[YYY][1], YYY-1, 1);
                        if (trobat4 != -1){
                            switcherF = true;
                            X = YYY-1;
                            Y = 0;
                        }
            }
            if (!switcherF && YYY > 2 && tabla[YYY][1].equalsIgnoreCase(tabla [YYY-1][1]) &&
                    tabla[YYY][1].equalsIgnoreCase(tabla [YYY-3][1])){
                        trobat4 = Comprobar.comprobarTriHoriz(tabla[YYY][1], YYY-2, 1);
                        if(trobat4 != -1){
                            switcherF = true;
                            X = YYY-2;
                            Y = 0;
                        }
            }
        }   

        ////////////////
        //////////////////
        ///////////////
        ///////////////
        ///////////
        ////////////SEGUNDO
        //SECOND FOR
        if (!switcherF){
        for(YY = 7; YY > 0 && !switcherB; YY--){
            for (XX = 7; XX > 0 && !switcherB; XX--){
                   if (!switcherB && tabla[XX][YY].equalsIgnoreCase(tabla [XX-1][YY])){
                        if (tablaB[XX-1][YY] == '1' || tablaB[XX][YY] == '1') brill = true;
                        trobat0 = Comprobar2.comprobarDer(tabla[XX][YY], XX+1, YY);
                        trobat2 = Comprobar2.comprobarIzq(tabla[XX][YY], XX-2, YY);
                        if (trobat0 != -1 || trobat2 != -1) switcherB = true;
                        else brill = false;
                   }
                   if (!switcherB && tabla[XX][YY].equalsIgnoreCase(tabla [XX][YY-1])){
                        if (tablaB[XX][YY-1] == '1' || tablaB[XX][YY] == '1') brill = true;
                        trobat1 = Comprobar2.comprobarDown(tabla[XX][YY], XX, YY+1);
                        trobat3 = Comprobar2.comprobarUp(tabla[XX][YY], XX, YY-2);
                        if (trobat1 != -1 || trobat3 != -1) switcherB = true;
                        else brill = false;
                   }
                   if (!switcherB && XX>1 && tabla[XX][YY].equalsIgnoreCase(tabla [XX-2][YY])) {
                        if (tablaB[XX-2][YY] == '1' || tablaB[XX][YY] == '1') brill = true;
                        trobat4 = Comprobar2.comprobarTriHoriz(tabla[XX][YY], XX-1, YY);
                        if (trobat4 != -1) switcherB = true;
                        else brill = false;
                   }
                   if (!switcherB && YY > 1 && tabla[XX][YY].equalsIgnoreCase(tabla [XX][YY-2])) {
                        if (tablaB[XX][YY-2] == '1' || tablaB[XX][YY] == '1') brill = true;
                        trobat5 = Comprobar2.comprobarTriVert(tabla[XX][YY], XX, YY-1);
                        if (trobat5 != -1) switcherB = true;
                        else brill = false;
                   }
            }
            if (!switcherB && tabla[0][YY].equalsIgnoreCase(tabla [0][YY-1])){
                        if (tablaB[0][YY-1] == '1' || tablaB[0][YY] == '1') brill = true;
                        trobat1 = Comprobar2.comprobarDown(tabla[0][YY], 0, YY+1);
                        trobat3 = Comprobar2.comprobarUp(tabla[0][YY], 0, YY-2);
                        if (trobat1 != -1 || trobat3 != -1){
                            switcherB = true;
                            XX = -1;
                        }
                        else brill = false;
            }
            if (!switcherB && tabla[YY][0].equalsIgnoreCase(tabla [YY-1][0])){
                        if (tablaB[YY-1][0] == '1' || tablaB[YY][0] == '1') brill = true;
                        trobat0 = Comprobar2.comprobarDer(tabla[YY][0], YY+1, 0);
                        trobat2 = Comprobar2.comprobarIzq(tabla[YY][0], YY-2, 0);
                        if (trobat0 != -1 || trobat2 != -1) {
                            switcherB = true;
                            XX = YY-1;
                            YY = 0;
                        }
                        else brill = false;
            }
            if (!switcherB && YY > 1 && tabla[0][YY].equalsIgnoreCase(tabla [0][YY-2])){
                        if (tablaB[0][YY-2] == '1' || tablaB[0][YY] == '1') brill = true;
                        trobat5 = Comprobar2.comprobarTriVert(tabla[0][YY], 0, YY-1);
                        if (trobat5 != -1) {
                            switcherB = true;
                            XX = -1;
                        }
                        else brill = false;
            }
            if (!switcherB && YY > 1 && tabla[YY][0].equalsIgnoreCase(tabla [YY-2][0])){
                        if (tablaB[YY-2][0] == '1' || tablaB[YY][0] == '1') brill = true;
                        trobat4 = Comprobar2.comprobarTriHoriz(tabla[YY][0], YY-1, 0);
                        if (trobat4 != -1){
                            switcherB = true;
                            XX = YY-1;
                            YY = 0;
                        }
                        else brill = false;
            }
            if (!switcherB && YY > 1 && tabla[1][YY].equalsIgnoreCase(tabla [1][YY-2])){
                        if (tablaB[1][YY-2] == '1' || tablaB[1][YY] == '1') brill = true;
                        trobat5 = Comprobar2.comprobarTriVert(tabla[1][YY], 1, YY-1);
                        if (trobat5 != -1) {
                            switcherB = true;
                            XX = 0;
                        }
                        else brill = false;
            }
            if (!switcherB && YY > 1 && tabla[YY][1].equalsIgnoreCase(tabla [YY-2][1])){
                        if (tablaB[YY-2][1] == '1' || tablaB[YY][1] == '1') brill = true;
                        trobat4 = Comprobar2.comprobarTriHoriz(tabla[YY][1], YY-1, 1);
                        if (trobat4 != -1){
                            switcherB = true;
                            XX = YY-1;
                            YY = 1;
                        }
                        else brill = false;
            }
        }
        }
        /////
        //////////////////TERCEEERR FOR
        ///////////////
        /////////////
        /////////
        if (!switcherB && !switcherF){
        for(Y = 7; Y > 0 && !switcher; Y--){
            for (X = 7; X > 0 && !switcher; X--){
                   if (!switcher && tabla[X][Y].equalsIgnoreCase(tabla [X-1][Y])){
                        trobat0 = Comprobar.comprobarDer(tabla[X][Y], X+1, Y);
                        trobat2 = Comprobar.comprobarIzq(tabla[X][Y], X-2, Y);
                        if (trobat0 != -1 || trobat2 != -1) switcher = true;
                   }
                   if (!switcher && tabla[X][Y].equalsIgnoreCase(tabla [X][Y-1])){
                        trobat1 = Comprobar.comprobarDown(tabla[X][Y], X, Y+1);
                        trobat3 = Comprobar.comprobarUp(tabla[X][Y], X, Y-2);
                        if (trobat1 != -1 || trobat3 != -1) switcher = true;
                   }
                   if (!switcher && X>1 && tabla[X][Y].equalsIgnoreCase(tabla [X-2][Y])) {
                        trobat4 = Comprobar.comprobarTriHoriz(tabla[X][Y], X-1, Y);
                        if (trobat4 != -1) switcher = true;
                   }
                   if (!switcher && Y > 1 && tabla[X][Y].equalsIgnoreCase(tabla [X][Y-2])) {
                        trobat5 = Comprobar.comprobarTriVert(tabla[X][Y], X, Y-1);
                        if (trobat5 != -1) switcher = true;
                   }
            }
            if (!switcher && tabla[0][Y].equalsIgnoreCase(tabla [0][Y-1])){
                        trobat1 = Comprobar.comprobarDown(tabla[0][Y], 0, Y+1);
                        trobat3 = Comprobar.comprobarUp(tabla[0][Y], 0, Y-2);
                        if (trobat1 != -1 || trobat3 != -1){
                            switcher = true;
                            X = -1;
                        }
            }
            if (!switcher && tabla[Y][0].equalsIgnoreCase(tabla [Y-1][0])){
                        trobat0 = Comprobar.comprobarDer(tabla[Y][0], Y+1, 0);
                        trobat2 = Comprobar.comprobarIzq(tabla[Y][0], Y-2, 0);
                        if (trobat0 != -1 || trobat2 != -1) {
                            switcher = true;
                            X = Y-1;
                            Y = 0;
                        }
            }
            if (!switcher && Y > 1 && tabla[0][Y].equalsIgnoreCase(tabla [0][Y-2])){
                        trobat5 = Comprobar.comprobarTriVert(tabla[0][Y], 0, Y-1);
                        if (trobat5 != -1 || trobat2 != -1) {
                            switcher = true;
                            X = -1;
                        }
            }
            if (!switcher && Y > 1 && tabla[Y][0].equalsIgnoreCase(tabla [Y-2][0])){
                        trobat4 = Comprobar.comprobarTriHoriz(tabla[Y][0], Y-1, 0);
                        if (trobat4 != -1 || trobat2 != -1){
                            switcher = true;
                            X = Y-1;
                            Y = 0;
                        }
            }
            if (!switcher && Y > 1 && tabla[1][Y].equalsIgnoreCase(tabla [1][Y-2])){
                        trobat5 = Comprobar.comprobarTriVert(tabla[1][Y], 1, Y-1);
                        if (trobat5 != -1 || trobat2 != -1) {
                            switcher = true;
                            X = 0;
                        }
            }
            if (!switcher && Y > 1 && tabla[Y][1].equalsIgnoreCase(tabla [Y-2][1])){
                        trobat4 = Comprobar.comprobarTriHoriz(tabla[Y][1], Y-1, 1);
                        if (trobat4 != -1 || trobat2 != -1){
                            switcher = true;
                            X = Y-1;
                            Y = 1;
                        }
            }
        }
        }
        ////////////
        /////////////
        ////////////
        ////////////
        ///////////////////////// PRIMEROS CLICKS
        //PRIMEROS CLICS DE GEMAS
        
        //AQUESTA ES LA TERCERA FASE, LA DE FER ELS CLICS, COMPROBEM EN QUINA DIRECCIO HA TROBAT LA PEÇA
        //QUE SUBSTITUIREM I COMPROBEM AMB ELS SWITCHER, QUIN DELS CASOS ES (4, BRILLANT O 3 (NORMAL)).
        
        
        if (switcherB){
            int num = (int) (Math.random()*10-5);
            int num2 = (int) (Math.random()*10-6);
            if (trobat0 != -1){
                robot.mouseMove((XX+1)*57+xinicio+num, YY*57+yinicio+num2); //EN ELS VALORS XX, YY HEM GUARDAT LA POS
                                                                            //DE LA PEDRA EN LA MATRIU, I LI SUMEM EL INICI.
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.delay(5+delays2);
                switch (trobat0){
                    case 0:
                        robot.mouseMove((XX+2)*57+xinicio+num, YY*57+yinicio+num2);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        break;
                    case 1:
                        robot.mouseMove((XX+1)*57+xinicio+num, (YY+1)*57+yinicio+num2);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        break;
                    case 3:
                        robot.mouseMove((XX+1)*57+xinicio+num, (YY-1)*57+yinicio+num2);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        break;
                }
            }
            else if (trobat1 != -1){
                robot.mouseMove(XX*57+xinicio+num, (YY+1)*57+yinicio+num2);
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.delay(5+delays2);
                switch (trobat1){
                    case 0:
                        robot.mouseMove((XX+1)*57+xinicio+num, (YY+1)*57+yinicio+num2);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        break;
                    case 1:
                        robot.mouseMove(XX*57+xinicio+num, (YY+2)*57+yinicio+num2);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        break;
                    case 2:
                        robot.mouseMove((XX-1)*57+xinicio+num, (YY+1)*57+yinicio+num2);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        break;
                }
            }
            else if (trobat2 != -1){
                robot.mouseMove((XX-2)*57+xinicio+num, YY*57+yinicio+num2);
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.delay(5+delays2);
                switch (trobat2){
                    case 1:
                        robot.mouseMove((XX-2)*57+xinicio+num, (YY+1)*57+yinicio+num2);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        break;
                    case 2:
                        robot.mouseMove((XX-3)*57+xinicio+num, YY*57+yinicio+num2);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        break;
                    case 3:
                        robot.mouseMove((XX-2)*57+xinicio+num, (YY-1)*57+yinicio+num2);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        break;
                }
            }
            else if (trobat3 != -1){
                robot.mouseMove(XX*57+xinicio+num, (YY-2)*57+yinicio+num2);
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.delay(5+delays2);
                switch (trobat3){
                    case 0:
                        robot.mouseMove((XX+1)*57+xinicio+num, (YY-2)*57+yinicio+num2);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        break;
                    case 2:
                        robot.mouseMove((XX-1)*57+xinicio+num, (YY-2)*57+yinicio+num2);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        break;
                    case 3:
                        robot.mouseMove(XX*57+xinicio+num, (YY-3)*57+yinicio+num2);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        break;
                }
            }
            else if (trobat4 != -1){
                robot.mouseMove((XX-1)*57+xinicio+num, YY*57+yinicio+num2);
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.delay(5+delays2);
                switch (trobat4){
                    case 1:
                        robot.mouseMove((XX-1)*57+xinicio+num, (YY+1)*57+yinicio+num2);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        break;
                    case 3:
                        robot.mouseMove((XX-1)*57+xinicio+num, (YY-1)*57+yinicio+num2);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        break;
                }
            }
            else if (trobat5 != -1){
                robot.mouseMove(XX*57+xinicio+num, (YY-1)*57+yinicio+num2);
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.delay(5+delays2);
                switch (trobat5){
                    case 0:
                        robot.mouseMove((XX+1)*57+xinicio+num, (YY-1)*57+yinicio+num2);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        break;
                    case 2:
                        robot.mouseMove((XX-1)*57+xinicio+num, (YY-1)*57+yinicio+num2);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        break;
                }
            }
        }

//////////////
        //////////////
        //////////////
        //////////////
////////

/////////////////SEGUNDOS CLICKS

        //escoger click, 0 = derecha, 1 = abajo, 2 = izquierda, 3 = arriba
        if (switcher || switcherF){
            int num = (int) (Math.random()*10-5);
            int num2 = (int) (Math.random()*10-6);
            if (trobat0 != -1){
                robot.mouseMove((X+1)*57+xinicio+num, Y*57+yinicio+num2);
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.delay(5+delays2);
                switch (trobat0){
                    case 0:
                        robot.mouseMove((X+2)*57+xinicio+num, Y*57+yinicio+num2);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        break;
                    case 1:
                        robot.mouseMove((X+1)*57+xinicio+num, (Y+1)*57+yinicio+num2);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        break;
                    case 3:
                        robot.mouseMove((X+1)*57+xinicio+num, (Y-1)*57+yinicio+num2);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        break;
                }
            }
            else if (trobat1 != -1){
                robot.mouseMove(X*57+xinicio+num, (Y+1)*57+yinicio+num2);
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.delay(5+delays2);
                switch (trobat1){
                    case 0:
                        robot.mouseMove((X+1)*57+xinicio+num, (Y+1)*57+yinicio+num2);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        break;
                    case 1:
                        robot.mouseMove(X*57+xinicio+num, (Y+2)*57+yinicio+num2);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        break;
                    case 2:
                        robot.mouseMove((X-1)*57+xinicio+num, (Y+1)*57+yinicio+num2);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        break;
                }
            }
            else if (trobat2 != -1){
                robot.mouseMove((X-2)*57+xinicio+num, Y*57+yinicio+num2);
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.delay(5+delays2);
                switch (trobat2){
                    case 1:
                        robot.mouseMove((X-2)*57+xinicio+num, (Y+1)*57+yinicio+num2);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        break;
                    case 2:
                        robot.mouseMove((X-3)*57+xinicio+num, Y*57+yinicio+num2);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        break;
                    case 3:
                        robot.mouseMove((X-2)*57+xinicio+num, (Y-1)*57+yinicio+num2);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        break;
                }
            }
            else if (trobat3 != -1){
                robot.mouseMove(X*57+xinicio+num, (Y-2)*57+yinicio+num2);
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.delay(5+delays2);
                switch (trobat3){
                    case 0:
                        robot.mouseMove((X+1)*57+xinicio+num, (Y-2)*57+yinicio+num2);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        break;
                    case 2:
                        robot.mouseMove((X-1)*57+xinicio+num, (Y-2)*57+yinicio+num2);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        break;
                    case 3:
                        robot.mouseMove(X*57+xinicio+num, (Y-3)*57+yinicio+num2);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        break;
                }
            }
            else if (trobat4 != -1){
                robot.mouseMove((X-1)*57+xinicio+num, Y*57+yinicio+num2);
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.delay(5+delays2);
                switch (trobat4){
                    case 1:
                        robot.mouseMove((X-1)*57+xinicio+num, (Y+1)*57+yinicio+num2);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        break;
                    case 3:
                        robot.mouseMove((X-1)*57+xinicio+num, (Y-1)*57+yinicio+num2);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        break;
                }
            }
            else if (trobat5 != -1){
                robot.mouseMove(X*57+xinicio+num, (Y-1)*57+yinicio+num2);
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.delay(5+delays2);
                switch (trobat5){
                    case 0:
                        robot.mouseMove((X+1)*57+xinicio+num, (Y-1)*57+yinicio+num2);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        break;
                    case 2:
                        robot.mouseMove((X-1)*57+xinicio+num, (Y-1)*57+yinicio+num2);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        break;
                }
            }
        }
        

      /* Color a = robot.getPixelColor(628, 218);
        System.out.println(a);
        int mm, nn;
        for(mm=0;mm<8;mm++){
            for(nn=0;nn<8;nn++){
                System.out.print(tabla[nn][mm]);
            }
            System.out.println();
        }
    
      /*  System.out.print(idPieza(new Color(img.getRGB(508,408))));
        System.out.print(idPieza(new Color(img.getRGB(491,453))));
        System.out.print(idPieza(new Color(img.getRGB(517,444))));
        System.out.print(idPieza(new Color(img.getRGB(558,409))));
        System.out.print(idPieza(new Color(img.getRGB(588,442))));
        System.out.print(idPieza(new Color(img.getRGB(644,405))));
        System.out.print(idPieza(new Color(img.getRGB(650,332))));*/
    /*if ("O".equals(idPieza(new Color(img.getRGB(508,408)))) &&
            "Y".equals(idPieza(new Color(img.getRGB(491,453)))) &&
            "Y".equals(idPieza(new Color(img.getRGB(517,447)))) &&
            "O".equals(idPieza(new Color(img.getRGB(558,409)))) &&
            "O".equals(idPieza(new Color(img.getRGB(580,414)))) &&
            "O".equals(idPieza(new Color(img.getRGB(644,405)))) &&
            "R".equals(idPieza(new Color(img.getRGB(650,332))))) fin = true;*/
        
    //if (n>0) fin = true;
    }
        long t2 = new Date().getTime();
                    //System.out.println(t2);
                    System.out.println("Total: " + (t2 - t1) + " milisegundos");
    }
    }
   
}