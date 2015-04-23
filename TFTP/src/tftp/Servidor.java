/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tftp;

/**
 *
 * @author dani__000
 */

public class Servidor extends TFTP {
        
        public static int numBloqueServidor = 0;
        static boolean modoTrace = false;
        static boolean hayPerdidaPaquetes = false;
        
        public Servidor(boolean soyServidor) {
                super(soyServidor);
        }

        public static void mostrarCabecera() {
                System.out.println("FORMA DE USO:  java Servidor [PUERTOSERVIDOR] {-v}");
                System.out.println("EJEMPLO: java Servidor 12345\n\n");
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
                System.out.println("El servidor se esta ejecutando");
                Servidor servidor = new Servidor(true);
                tratarArgumentos(args);
                servidor.ponerParametros(modoTrace, hayPerdidaPaquetes);
                servidor.setPuertoServidor(args[0]);
                servidor.abrirSocket();
                int tipoPaquete;
                while(true)
                {
                         //while(!servidor.transferenciaAcabada)
                         //{
                                servidor.recibirPaqueteUDP();
                                tipoPaquete = servidor.getSiguienteTipoPaquete();
                                        if(tipoPaquete != 0)
                                        {
                                            servidor.setTimeout(10000);
                                            servidor.prepararPaquete(tipoPaquete);
                                            servidor.enviarPaqueteUDP();
                                        //}
                                }
                 }
                        //servidor.cerrarSocket();
        }
}
