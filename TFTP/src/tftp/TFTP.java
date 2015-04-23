package tftp;

/**
 *
 * @author dani__000
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TFTP
{
        protected int tamanoBuffer;
        private final int tamData = 512;
        protected byte[] buffer;
        
        private DatagramSocket socketCliente;
        private DatagramSocket socketServidor;
        protected DatagramPacket paquete;
        
        protected InetAddress direccionCliente;
        protected int puertoCliente;// = 12344;
        
        protected InetAddress direccionServidor;
        protected int puertoServidor;// = 12345;
        
        protected boolean soyServidor;
        protected boolean conexionEstablecida = false;
        
        private int numBloqueServidor = 0;
        private int numBloqueCliente = 0;
        private int tipoError = 0;
        protected int siguienteTipoPaquete=0;
        
        
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        
        int bytesLeidos = 0;
        
        boolean modoTrace = false;
        public boolean transferenciaAcabada = false;
        private BufferedWriter archivoTrace;
        
        //variables para perdidas de paquetes
        private boolean hayErroresPaquete;
        
        public TFTP(int tamanoBuffer, int puerto, int seleccion) throws Exception {
                super();
                this.tamanoBuffer = tamanoBuffer;               
                buffer = new byte[tamanoBuffer];
        }
        
        public TFTP(boolean soyServidor)
        {
                this.soyServidor = soyServidor;
                tamanoBuffer = 600;
                buffer = new byte[tamanoBuffer];
        }
        
        public void ponerParametros(boolean modoTrace, boolean hayPerdidaPaquetes)
        {
                if(modoTrace == true)
                {
                        this.modoTrace = true;
                        try {
                                archivoTrace = new BufferedWriter(new FileWriter("trace.txt"));
                        } catch (IOException e) {
                                System.out.println("No se pudo crear el archivo trace.txt");
                        }
                }
                this.hayErroresPaquete = hayPerdidaPaquetes;              
        }
        
        public void setTrace(){
                modoTrace = true;
                try {
                        archivoTrace = new BufferedWriter(new FileWriter("trace.txt"));
                } catch (IOException e) {
                        System.out.println("No se pudo crear el archivo trace.txt");
                }
        }

        
        public int getNumBloque(){
                if(soyServidor) return numBloqueServidor;
                else return numBloqueCliente;
        }

        
        protected void abrirSocket(){
                try{       
                        if(soyServidor)
                        {
                                socketServidor = new DatagramSocket(puertoServidor, InetAddress.getLocalHost());
                                //socketServidor.setSoTimeout(10000);
                        }
                        else
                        {
                                socketCliente = new DatagramSocket(puertoCliente, InetAddress.getLocalHost());
                                //socketCliente.setSoTimeout(10000);
                        }
                        
                        if(modoTrace)
                                archivoTrace.write("Socket creado correctamente."+System.getProperty("line.separator"));
                        
                } catch (IOException ex) {} 
                catch (Exception e){}
        }
        
        protected void cerrarSocket(){
                socketCliente.close();
                try{
                if(modoTrace){
                        archivoTrace.write("Socket cerrado correctamente."+System.getProperty("line.separator"));
                        archivoTrace.close();
                }
                } catch (IOException e){}
        }
        
        protected void setIPCliente(String IPString)
        {
                try {
                        direccionCliente = InetAddress.getByName(IPString);
                } catch (UnknownHostException e) {
                        System.err.println("No existe el host");
                }               
        }
        
        protected void setIPServidor(String IPString)
        {
                try {
                        direccionServidor = InetAddress.getByName(IPString);
                } catch (UnknownHostException e) {
                        System.err.println("No existe el host");
                }               
        }
        
        protected void setPuertoCliente(String puertoCliente)
        {
                this.puertoCliente = Integer.parseInt(puertoCliente);
        }
        
        protected void setPuertoServidor(String puertoServidor)
        {
                this.puertoServidor = Integer.parseInt(puertoServidor);
        }
        
        protected int getTipoError()
        {
                return tipoError;
        }
        
        protected int getSiguienteTipoPaquete()
        {
                return siguienteTipoPaquete;
        }
        
        protected void setSiguientePaquete(int tipo)
        {
                siguienteTipoPaquete = tipo;
                if(tipo==0)
                {
                        System.out.println("Elige una opcion valida.");
                        transferenciaAcabada = true;
                }
        }
        
        protected boolean transferenciaAcabada()
        {
                return transferenciaAcabada;
        }
        
        protected void setTimeout(int time){
            try {
            if(soyServidor) socketServidor.setSoTimeout(time);
            else socketCliente.setSoTimeout(time);
            } catch (SocketException e) {}
        }
        
        private boolean errorComprobarArchivo(boolean lectura, String nombreArchivo)
        {
                File archivo = new File(nombreArchivo);
                boolean salida=false;
                if(soyServidor && lectura && !archivo.exists())
                {
                    tipoError = 1;
                    salida = true;
                }
                if(soyServidor && !lectura && archivo.exists())
                {
                    tipoError = 2;
                    salida = true;
                }
                if(!soyServidor && !lectura && archivo.exists())
                {
                    tipoError = 2;
                    salida = true;
                }
                
                return salida;
        }
        
        private int arr2int(byte[] arr, int start) {
                int low = arr[start] & 0xff;
                int high = arr[start+1] & 0xff;
                int valor = (int)( high << 8 | low ); 
                return Math.abs(valor);
        }

        
        private byte[] shortABytes(short s) {
        return new byte[]{(byte)(s & 0x00FF),(byte)((s & 0xFF00)>>8)};
    }
        
        protected void desempaquetar()
        {
                byte[] opCode = new byte[2];
                byte[] arrayData;
                opCode[0] = buffer[0];
                opCode[1] = buffer[1];
                byte[] numBloque = new byte[2];
                int numBloqueInt, fin;
                int inicio = 2;
                byte[] cadenaChar;
                int codigo = arr2int(opCode, 0);
                String cadena;
                
                switch(codigo)
                {
                case 1: //PAQUETE RRQ
                        fin = inicio;
                        while(buffer[fin] != 0)
                        {
                                fin++;
                        }
                        cadenaChar = new byte[fin-inicio];
                        System.arraycopy(buffer, inicio,cadenaChar,0,fin-inicio);
                        cadena = new String(cadenaChar);
                        
                        if(errorComprobarArchivo(true,cadena))
                                siguienteTipoPaquete = 5;
                        else
                        {
                                try {
                                        fis = new FileInputStream(cadena);
                                bis = new BufferedInputStream(fis);
                                numBloqueServidor = 1;
                                siguienteTipoPaquete = 3;
                                } catch (FileNotFoundException e) {
                                        
                                }
                        }

                        inicio = fin+1;
                        fin = inicio;
                        while(buffer[fin] != 0)
                        {
                                fin++;
                        }
                        cadenaChar = new byte[fin-inicio];
                        System.arraycopy(buffer, inicio,cadenaChar,0,fin-inicio);
                        cadena = new String(cadenaChar);
                        break;
                        
                case 2: //PAQUETE WRQ
                        fin = inicio;
                        while(buffer[fin] != 0)
                        {
                                fin++;
                        }
                        cadenaChar = new byte[fin-inicio];
                        System.arraycopy(buffer, inicio,cadenaChar,0,fin-inicio);
                        cadena = new String(cadenaChar);
                        
                        if(errorComprobarArchivo(false,cadena))
                                siguienteTipoPaquete = 5;
                        else
                        {
                                try {
                                        fos = new FileOutputStream(cadena);
                                        bos = new BufferedOutputStream(fos);
                                } catch (FileNotFoundException e1) {
                                        siguienteTipoPaquete = 5;
                                        tipoError = 2;
                                }
                                
                                numBloqueServidor = 0;
                                siguienteTipoPaquete = 4;
                        }
                                                
                        inicio = fin+1;
                        fin = inicio;
                        while(buffer[fin] != 0)
                        {
                                fin++;
                        }
                        cadenaChar = new byte[fin-inicio];
                        System.arraycopy(buffer, inicio,cadenaChar,0,fin-inicio);
                        cadena = new String(cadenaChar);
                        break;
                        
                case 3: //PAQUETE DATA
                        numBloque[0] = buffer[2];
                        numBloque[1] = buffer[3];
                        byte[] crcBytes = new byte[2];
                        numBloqueInt = arr2int(numBloque, 0);
                        if(soyServidor)
                                numBloqueServidor = numBloqueInt;
                        else
                                numBloqueCliente = numBloqueInt;
                        
                        inicio = 4;
                        
                        try {
                                if(modoTrace)
                                        archivoTrace.write("Numero de bloque leido (en DATA): "+numBloqueInt+System.getProperty("line.separator"));
                                inicio = 4;
                                arrayData = new byte[tamData];
                                arrayData = java.util.Arrays.copyOfRange(buffer, inicio, paquete.getLength());
                                
                                if(modoTrace){
                                        archivoTrace.write("arrayData length: "+arrayData.length+"\n");
                                        archivoTrace.write("Buffer["+(paquete.getLength()-2)+"]: "+buffer[paquete.getLength()-2]+"  Buffer["+(paquete.getLength()-1)+"]: "+buffer[paquete.getLength()-1]+"\n");
                                }
                                

                                
                                if(paquete.getLength() < tamData)
                                {
                                        if(soyServidor)
                                                transferenciaAcabada = true;
                                }
                                
                                bos.write(arrayData,0,arrayData.length);

                        } catch (IOException e1) {
                        }

                        try {
                                if(soyServidor)
                                {
                                        numBloqueServidor = numBloqueInt;
                                        if(paquete.getLength()-inicio < tamData)
                                        {
                                                transferenciaAcabada = true;
                                                tipoError = 0;
                                                siguienteTipoPaquete = 4;
                                                bos.close();
                                        }
                                }
                                else
                                {
                                        numBloqueCliente = numBloqueInt;
                                        siguienteTipoPaquete = 4;
                                        if(paquete.getLength()-inicio < tamData)
                                        {
                                                transferenciaAcabada = true;
                                                tipoError = 0;
                                                siguienteTipoPaquete = 4;
                                                bos.close();
                                        }
                                }
                        } catch (IOException e) {
                        }
                        break;
        
                case 4: //PAQUETE ACK
                        numBloque[0] = buffer[2];
                        numBloque[1] = buffer[3];
                        numBloqueInt = arr2int(numBloque, 0);
                        
                        if(modoTrace)
                                try{
                                archivoTrace.write("Numero de bloque leido: "+numBloqueInt+System.getProperty("line.separator"));
                                } catch (IOException e){}
                        
                        if(soyServidor)
                        {
                                if(numBloqueInt == numBloqueServidor)
                                {
                                        numBloqueServidor++;
                                        siguienteTipoPaquete = 3;
                                }
                        }
                        else{
                            if(numBloqueInt == numBloqueCliente){
                                 numBloqueCliente++;
                                 siguienteTipoPaquete = 3;
                            }
                        }
                        break;
                        
                case 5: //PAQUETE ERROR
                        byte[] numError = new byte[2];
                        numError[0] = buffer[2];
                        numError[1] = buffer[3];
                        int numErrorInt = arr2int(numError, 0);
                        String descripcionError = "";
                        switch(numErrorInt)
                        {
                        case 1:
                                descripcionError = "Archivo no encontrado";
                                break;
                        case 2:
                                descripcionError = "Violacion de acceso";
                                break;
                        case 3:
                                descripcionError = "Usuario ya existente";
                                break;
                        case 0:
                                descripcionError = "No definido";
                                break;
                        }
                        System.out.println("Numero de error leido: "+numErrorInt+"\t"+descripcionError);
                        transferenciaAcabada = true;
                        
                        if(modoTrace)
                                try{
                                archivoTrace.write("Numero de error leido: "+numErrorInt+"\t"+descripcionError+System.getProperty("line.separator"));
                                } catch (IOException e){}
                        
                        break;
                }
        }

        protected void prepararPaquete(int tipo) throws IOException {
                Scanner sc = new Scanner(System.in);
                int longitudDelBuffer = 0;
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] opCode = new byte[2];
                byte[] numBloque = new byte[2];
                byte[] nombreArchivo;
                byte[] modo;
                byte zero = 0;
                String cadena;
                
                switch(tipo)
                {
                case 1: //Paquete tipo RRQ
                        baos = new ByteArrayOutputStream();
                        //CODIGO OPERADOR
                        opCode = shortABytes((short)1);
                        baos.write(opCode);
                        longitudDelBuffer = longitudDelBuffer + opCode.length;
                        
                        //NOMBRE DEL ARCHIVO
                        System.out.print("Escribe el nombre del archivo: ");
                        cadena = sc.nextLine();
                        if(!errorComprobarArchivo(false, cadena))
                        {
                                nombreArchivo = cadena.getBytes();
                                baos.write(nombreArchivo, 0, nombreArchivo.length);
                                longitudDelBuffer = longitudDelBuffer + nombreArchivo.length;
                                try {
                                        fos = new FileOutputStream(cadena);
                                        bos = new BufferedOutputStream(fos);
                                } catch (FileNotFoundException e1) {
                                        siguienteTipoPaquete = 5;
                                        tipoError = 2;
                                }
                        }
                        
                        //BYTE CERO
                        baos.write(zero);
                        longitudDelBuffer++;
                        
                        //MODO DE TRANSFERENCIA
                        cadena = "binary";
                        modo = cadena.getBytes();
                        baos.write(modo, 0, modo.length);
                        longitudDelBuffer = longitudDelBuffer + modo.length;
                        
                        //BYTE CERO
                        baos.write(zero);
                        longitudDelBuffer++;
                        break;
                        
                case 2: //Paquete tipo WRQ
                        baos = new ByteArrayOutputStream();
                        //CODIGO OPERADOR
                        opCode = shortABytes((short)2);
                        baos.write(opCode);
                        longitudDelBuffer = longitudDelBuffer + opCode.length;
                        
                        //NOMBRE DEL ARCHIVO
                        System.out.print("Escribe el nombre del archivo: ");
                        cadena = sc.nextLine();
                        try{
                        fis = new FileInputStream(cadena);
                        bis = new BufferedInputStream(fis);
                        }catch (FileNotFoundException e1) {
                                System.out.println("No se encuentra el archivo");
                                siguienteTipoPaquete = 0;
                                transferenciaAcabada = true;
                                break;
                        }
                        nombreArchivo = cadena.getBytes();
                        baos.write(nombreArchivo, 0, nombreArchivo.length);
                        longitudDelBuffer = longitudDelBuffer + nombreArchivo.length;

                        //BYTE CERO
                        baos.write(zero);
                        longitudDelBuffer++;
                        
                        //MODO DE TRANSFERENCIA
                        cadena = "binary";
                        modo = cadena.getBytes();
                        baos.write(modo, 0, modo.length);
                        longitudDelBuffer = longitudDelBuffer + modo.length;
                        
                        //BYTE CERO
                        baos.write(zero);
                        longitudDelBuffer++;
                        
                        numBloqueCliente = 0;
                        break;
                        
                case 3: //Paquete tipo DATA
                        baos = new ByteArrayOutputStream();
                        //CODIGO OPERADOR
                        opCode = shortABytes((short)3);
                        baos.write(opCode);
                        longitudDelBuffer = longitudDelBuffer + opCode.length;
                        
                        //NUMERO DE BLOQUE
                        if(soyServidor)
                                numBloque = shortABytes((short)this.numBloqueServidor);
                        else
                                numBloque = shortABytes((short)this.numBloqueCliente);
                        baos.write(numBloque,0,numBloque.length);
                        longitudDelBuffer = longitudDelBuffer + numBloque.length;
                        
                        //DATOS
                        byte[] data = new byte[tamData];
                        bytesLeidos = bis.read(data);
                        baos.write(data,0,bytesLeidos);
                        longitudDelBuffer = longitudDelBuffer + bytesLeidos;
                        
                        if(bytesLeidos > 0 && bytesLeidos < tamData)
                        {
                                transferenciaAcabada = true;
                                bis.close();
                        }
                        break;
                        
                case 4: //Paquete tipo ACK
                        baos = new ByteArrayOutputStream();
                
                        opCode = new byte[2];
                        opCode = shortABytes((short)4);
                        baos.write(opCode);
                        longitudDelBuffer = longitudDelBuffer + opCode.length;
                        
                        if(soyServidor)
                                numBloque = shortABytes((short)this.numBloqueServidor);
                        else
                                numBloque = shortABytes((short)this.numBloqueCliente);
                        baos.write(numBloque,0,numBloque.length);
                        longitudDelBuffer = longitudDelBuffer + numBloque.length;
                        break;
                        
                case 5: //Paquete tipo ERROR
                        baos = new ByteArrayOutputStream();
                
                        opCode = new byte[2];
                        opCode = shortABytes((short)5);
                        baos.write(opCode);
                        longitudDelBuffer = longitudDelBuffer + opCode.length;
                                        
                        numBloque = shortABytes((short)tipoError);
                        baos.write(numBloque,0,numBloque.length);
                        longitudDelBuffer = longitudDelBuffer + numBloque.length;
                        
                        String descripcionError = "";
                        switch(tipoError)
                        {
                        case 1:
                                descripcionError = "Archivo no encontrado";
                                break;
                        case 2:
                                descripcionError = "Violacion de acceso";
                                break;
                        case 3:
                                descripcionError = "Archivo ya existente";
                                break;
                        case 0:
                                descripcionError = "No definido";
                                break;
                        }
                        byte[] arrayDescError = descripcionError.getBytes();
                        baos.write(arrayDescError, 0, arrayDescError.length);
                        longitudDelBuffer = longitudDelBuffer + arrayDescError.length;
                        
                        tipoError = 0;
                        break;
                }
                this.paquete = new DatagramPacket(baos.toByteArray(),longitudDelBuffer);
        }
        
        protected void enviarPaqueteUDP(){
                try {
                if(soyServidor)
                {
                        this.paquete.setAddress(direccionCliente);
                        this.paquete.setPort(puertoCliente);
                        socketServidor.send(paquete);
                        if(siguienteTipoPaquete == 5)
                                siguienteTipoPaquete = 0;
                        if(modoTrace)
                                archivoTrace.write("Paquete enviado a "+paquete.getAddress()+System.getProperty("line.separator"));
                }else{
                        this.paquete.setAddress(direccionServidor);
                        this.paquete.setPort(puertoServidor);
                        socketCliente.send(paquete);
                        if(modoTrace)
                                archivoTrace.write("Paquete enviado a "+paquete.getAddress()+System.getProperty("line.separator"));
                }
                } catch (IOException e) {}
        }
        
        protected void recibirPaqueteUDP() throws IOException {
             BufferedOutputStream bufferedOutput = null;
             java.util.Arrays.fill(buffer,(byte) 0);
        
        try {
           paquete = new DatagramPacket(buffer, buffer.length);
           if(soyServidor) {
                socketServidor.receive(paquete);
           }
           else socketCliente.receive(paquete);
        } catch(IOException e) {
                   System.out.println("SE CIERRA EL SOCKET");
                   cerrarSocket();
                   if(modoTrace) archivoTrace.close();
                   System.exit(0);
           };
        try{    
                if(soyServidor) {
                     if(modoTrace)
                           archivoTrace.write("\nLongitud del paquete recibido: "+paquete.getLength()+System.getProperty("line.separator"));
                direccionCliente = paquete.getAddress();
                puertoCliente = paquete.getPort();
                }
                if(modoTrace)
                        archivoTrace.write("Paquete recibido de "+paquete.getAddress().getHostAddress()+":"+paquete.getPort()+System.getProperty("line.separator"));
                
                desempaquetar();
        } finally {
         //Close the BufferedOutputStream
         try {
             if (bufferedOutput != null) {
                 bufferedOutput.flush();
                 bufferedOutput.close();
             }
         } catch (IOException ex) {
         }
        }
        } 
}