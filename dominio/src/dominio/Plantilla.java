

package dominio;

import java.util.HashMap;
import java.util.Map;
import dominio.controlador.CtrlPlantilla;

/**
 *
 * @author dani__000
 */
public class Plantilla {

    private String nom;
    private int votacioEq, votacioDif, reunio, conferencia, dinar, lleure, partit, edat, religio;
    //private CtrlPlantilla control;
    
    //para k lo kereis?
    //Map<String, Double> Pair = new HashMap<String, Double>();

    // CREADORA
    // Pre: no hi ha cap plantilla amb nom “nom”
    // Post: crea una plantilla amb nom “nom”
    public Plantilla(String nom){
        this.nom = nom;
        //control = new CtrlPlantilla();
    }

    // MODIFICADORA
    // Pre: plantilla amb nom diferent de “default”
    // Post: ara la ponderacio de votacioEq val ‘p’
    public void modpVotacio(int p){
        this.votacioEq = p;
    }

    // Pre: plantilla amb nom diferent de “default”
    // Post: ara la ponderacio de votacioDif val ‘p’
    public void modpVotacioDif(int p){
        this.votacioDif = p;
    }

    // Pre: plantilla amb nom diferent de “default”
    // Post: ara la ponderacio de reunio val ‘p’
    public void modpReunio(int p){
        this.reunio = p;
    }

    // Pre: plantilla amb nom diferent de “default”
    // Post: ara la ponderacio de conferencia val ‘p’
    public void modpConf(int p){
        this.conferencia = p;
    }


    // Pre: plantilla amb nom diferent de “default”
    // Post: ara la ponderacio de dinar val ‘p’
    public void modpDinar(int p){
        this.dinar = p;
    }

    // Pre: plantilla amb nom diferent de “default”
    // Post: ara la ponderacio de Lleure val ‘p’
    public void modpLleure(int p){
        this.lleure = p;
    }

    // Pre: plantilla amb nom diferent de “default”
    // Post: ara la ponderacio de partit val ‘p’
    public void modpPartit(int p){
        this.partit = p;
    }

    // Pre: plantilla amb nom diferent de “default”
    // Post: ara la ponderacio de edat val ‘p’
    public void modpEdat(int p){
        this.edat = p;
    }

    // Pre: plantilla amb nom diferent de “default”
    // Post: ara la ponderacio de reunio val ‘p’
    public void modpReligio(int p){
        this.religio = p;
    }

    // CONSULTORA
    //Pre: dip1 i dip2 existeixen i son diputats diferents
    //Post: retorna l’afinitat entre dip1 i dip2
    public int calculAfinitat(int[] lista){
        int total = 0;
        Object[] a = getPond();
        Map<String,Integer> mapa = (Map) a[1];
        int i = 0;
        for (String clave : mapa.keySet()) {   
            int valor = mapa.get(clave);
            total += (valor*lista[i]);
            if (i<9)i++; //para evitar errores
        }
        
        return total;
    }

    // Pre: existeix la plantilla
    // Post: retorna dos object, el [0] es el string del nom y el [1] un hashmap
    // amb la llista de atributs - ponderacio
    public Object[] getPond(){
        Object[] retorno = new Object[2];
        retorno[0] = this.nom;
        Map<String, Integer> Pair = new HashMap<String, Integer>();
        Pair.put("votacioEq", this.votacioEq);
        Pair.put("votacioDif", this.votacioDif);
        Pair.put("reunio", this.reunio);
        Pair.put("conferencia", this.conferencia);
        Pair.put("dinar", this.dinar);
        Pair.put("lleure", this.lleure);
        Pair.put("partit", this.partit);
        Pair.put("edat", this.edat);
        Pair.put("religio", this.religio);
        retorno[1] = Pair;
        return retorno;        
        //PARA OBTENER EL RESULTADO, CREAIS OTRO OBJECT COMO LA 1A LINEA DE LA FUNCION
        //LO IGUALAIS A GETPOND Y LUEGO HACEIS STRING NOMBRE = (STRING) OBJECTO[0] Y
        //MAP ... TAL = NEW HASHMAP TAL.. ; TAL = (MAP<TAL,TAL>) OBJETO[1]
    }
    
    
    public void cargarPlantilla(){
        int[] nueva = CtrlPlantilla.cargarPlantilla(this.nom);
        try {
            if (nueva == null) throw new Exception();
            else {
                System.out.println("Existe y se ha cargado satisfactoriamente");
                modpVotacio(nueva[0]);
                modpVotacioDif(nueva[1]);
                modpReunio(nueva[2]);
                modpConf(nueva[3]);
                modpDinar(nueva[4]);
                modpLleure(nueva[5]);
                modpPartit(nueva[6]);
                modpEdat(nueva[7]);
                modpReligio(nueva[8]);
            }
        } catch (Exception e) {
            System.out.println("No existe la plantilla");
        }
    }
    
    public void guardarPlantilla(){
        Object[] ob;
        ob = getPond();
        CtrlPlantilla.guardarPlantilla(ob);
    }

    public static void main(String[] args) {
        Plantilla p = new Plantilla("pepito");
        p.cargarPlantilla();
        p.guardarPlantilla();
        int[] lista = new int[9];
        for (int i = 0; i <9; i++){
            lista[i] = i;
        }
        int total = p.calculAfinitat(lista);
        System.out.println("Total: " + total);
    }
    
}
