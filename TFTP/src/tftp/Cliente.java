
package tftp;

/**
 *
 * @author dani__000
 */
import java.util.Scanner;

public class Cliente extends TFTP {
        
        static boolean modoTrace = false;
        static boolean hayPerdidaPaquetes = false;
        
        public Cliente(int tamanoBuffer, int puerto) throws Exception {
                super(tamanoBuffer, puerto, 1);
        }

        public Cliente(boolean soyServidor) {
                super(soyServidor);
        }

        
        public static void mostrarOpciones()
        {
            System.out.println("FORMA DE USO:  java Cliente [IPSERVIDOR] [PUERTOSERVIDOR] [PUERTOCLIENTE] {-t}");
            System.out.println("EJEMPLO: java Cliente 88.124.53.124 12345 12344\n\n");
            System.out.println("\nput) Enviar archivo");
            System.out.println("get) Recibir archivo");
            System.out.println("exit) Salir");
        }
        
        public static void tratarArgumentos(String[] args)
        {
                for(int i=0; i<args.length;i++)
                {
                        if(args[i].compareTo("-d") == 0)
                        {
                                hayPerdidaPaquetes = true;
                        }
                        if(args[i].compareTo("-t") == 0)
                        {
                                modoTrace = true;
                        }     
                }
        }

        public static void main(String[] args) throws Exception {
                  Cliente cliente = new Cliente(false);
                  tratarArgumentos(args);
                  cliente.ponerParametros(modoTrace, hayPerdidaPaquetes);
                  cliente.setIPServidor(args[0]);
                  cliente.setPuertoServidor(args[1]);
                  cliente.setPuertoCliente(args[2]);
                  cliente.abrirSocket();
                  int opcion=0;
                  System.out.println("El cliente se esta ejecutando\n" +
                                        "Escribe \"salir\" para terminar la aplicacion.");
                  mostrarOpciones();
                  System.out.print("tftp> ");
                  Scanner sc = new Scanner(System.in);
                  String cadenaLeida = sc.nextLine();
                  while(cadenaLeida.compareTo("exit")!=0)
                        {
                        if(cadenaLeida.compareTo("get")==0)
                                opcion = 1;
                        else if(cadenaLeida.compareTo("put")==0)
                                opcion = 2;
          
                        cliente.transferenciaAcabada = false;
                        cliente.setSiguientePaquete(opcion);
                        while(!cliente.transferenciaAcabada)
                                {
                                cliente.prepararPaquete(cliente.getSiguienteTipoPaquete());
                                cliente.enviarPaqueteUDP();
                                cliente.recibirPaqueteUDP();
                                }
                        System.out.print("tftp> ");
                        cadenaLeida = sc.nextLine();
                        }
                        cliente.cerrarSocket();
                        System.out.println("El cliente ha terminado satisfactoriamente");
        }
}