package sample;

import robocode.*;
import java.awt.Color;
import java.awt.geom.*;
import java.util.*;

/**
 * @author: Dani Roca Lopez 47946965s
 * Usaremos radianes y posicion absoluta del enemigo
 */
public class DNI47946965 extends AdvancedRobot
{
	/**
	 * run: Noxerr's robot. Definimos el comportamiento.
	 */
	Hashtable targets;	//Guardaremos los enemigos en una tabla de hash
	Enemy target;		//El objetivo que escojamos
	final double PI = Math.PI;	//valor PI
	int direction = 1;	//La direccion en la que vamos, 1 adelante, -1 atras
	double firePower;	//Con que fuerza dispararemos
	double midpointstrength = 0;	
	int contadorTurnos = 0;		//Los turnos que hace desde que ha cambiado la fuerza
        //double dist;
        @Override
	public void run() {
		targets = new Hashtable();  //inicializamos la tabla
		target = new Enemy();       //inicializamos el enemigo
                //dist = target.distance;
		target.distance = 100000;   //Distancia inicial.
		setColors(Color.WHITE,Color.cyan,Color.green);	//Color del robot
		//Las siguientes 2 lineas indican que los turnos del radar y la pistola son independientes
		setAdjustGunForRobotTurn(true); //la pistola gira con el robot
		setAdjustRadarForGunTurn(true); //el radar gira con la pistola
		turnRadarRightRadians(2*PI);	//Giramos el radar entero para ver el panorama
		while(true) {
			antiGravMove();		//Como se mueve el robot
			escogeFuerza();		//Como escogemos la fuerza
			doScanner();		//Escaneo
			apuntar();                //Vamos a prevenir donde puede ir el enemigo
			//out.println(target.distance);
			fire(firePower);        //Disparar con la fuerza determinada anteriormente
			execute();		
		}
	}
	
	void escogeFuerza() {
		firePower = 450/target.distance;//Escogemos la fuerza segun la distancia
		if (firePower > 3) {
			firePower = 3;
		}
	}
	
	void antiGravMove() {
            double xforce = 0;
	    double yforce = 0;
	    double force, ang;
	    GravPoint p;
            Enemy en;
            Enumeration e = targets.elements(); //buscamos los elementos de la hash table
	    //Miramos la lista de enemigos restantes
            while (e.hasMoreElements()) {
                en = (Enemy)e.nextElement(); //miramos si el siguiente elemento esta vivo
		if (en.live) {
			p = new GravPoint(en.x,en.y, -1000); //punto clave
		        force = p.power/Math.pow(getRang(getX(),getY(),p.x,p.y),2); //algoritmo
		        //Encontrar el angulo desde el punto a nosotros
		        ang = normalizarDir(Math.PI/2 - Math.atan2(getY() - p.y, getX() - p.x)); //obtener angulo
		        //Anadimos los componentes de esta fuerza al total, con sus direciones respectivas
		        xforce += Math.sin(ang) * force;
		        yforce += Math.cos(ang) * force;
		} //asi sabemos donde es mejor moverse, para no estar cerca de los enemigos
	    }
	    
	/**La siguiente seccion nos da un punto medio con un random (positive or negative) de poderes.
	Esta fuerza cambia cada 5 turnos y va desde -1000 hasta 1000. Asi tendremos un movimiento mas
        optimizado.**/
            contadorTurnos++; //contador de turnos
            if (contadorTurnos > 5) {
			contadorTurnos = 0;
			midpointstrength = (Math.random() * 2000) - 1000;
            }
            p = new GravPoint(getBattleFieldWidth()/2, getBattleFieldHeight()/2, midpointstrength); 
            //obtener tamano tablero
            force = p.power/Math.pow(getRang(getX(),getY(),p.x,p.y),1.5); //algoritmo de aglomeracion
	    ang = normalizarDir(Math.PI/2 - Math.atan2(getY() - p.y, getX() - p.x)); //obtener angulo
	    xforce += Math.sin(ang) * force;
	    yforce += Math.cos(ang) * force;
	   
	    /**Las siguientes lineas son para evitar las paredes. Solo afecta si el robot esta cerca, 
	    ya que la aglomeracion por las paredes disminuye *3.**/
	    xforce += 5000/Math.pow(getRang(getX(), getY(), getBattleFieldWidth(), getY()), 3);
	    xforce -= 5000/Math.pow(getRang(getX(), getY(), 0, getY()), 3);
	    yforce += 5000/Math.pow(getRang(getX(), getY(), getX(), getBattleFieldHeight()), 3);
	    yforce -= 5000/Math.pow(getRang(getX(), getY(), getX(), 0), 3);
	    
	    //Mover en la direccion estimada.
	    goTo(getX()-xforce,getY()-yforce);
	}
	
	/**Mover a las coordenadas x, y**/
	void goTo(double x, double y) {
	    double dist = 20; 
	    double angle = Math.toDegrees(valAbsolutoDir(getX(),getY(),x,y)); //obtener angulo deseado
	    double r = turnTo(angle); //enfocar en funcion del angulo
	    setAhead(dist * r); //avanzar
	}


	/**Escoge el menor recorrido para apuntar al angulo deseado, luego devuelve la dir. a la que tiene
         * que ir el robot.**/
	int turnTo(double angle) {
	    double ang;
    	int dir;
	    ang = normalizarDir(getHeading() - angle);
	    if (ang > 90) { //escoger angulo mas corto e indicar direccion
	        ang -= 180;
	        dir = -1;
	    }
	    else if (ang < -90) {
	        ang += 180;
	        dir = -1;
	    }
	    else {
	        dir = 1;
	    }
	    setTurnLeft(ang);
	    return dir;
	}

	/**Mantener el scanner girando**/
	void doScanner() {
		setTurnRadarLeftRadians(2*PI);
	}
	
	/**Apunta al siguiente sitio que se prevee que ira el oponente**/
	void apuntar() {
		long time = getTime() + (int)Math.round((getRang(getX(),getY(),target.x,target.y)/(20-(3*firePower))));
		Point2D.Double p = target.guessPosition(time);
		
		//Prepara el siguiente tiro
		double gunOffset = getGunHeadingRadians() - (Math.PI/2 - Math.atan2(p.y - getY(), p.x - getX()));
		setTurnGunLeftRadians(normalizarDir(gunOffset));
	}
	
	
	//Si la direccion no esta entre -pi y pi, cambiamos para normalizar el angulo
	double normalizarDir(double ang) {
		if (ang > PI)
			ang -= 2*PI;
		if (ang < -PI)
			ang += 2*PI;
		return ang;
	}
	
	//Si no apuntamos entre 0 y 2pi, lo movemos para que este en el rango deseado.
	double normalizarHead(double ang) {
		if (ang > 2*PI)
			ang -= 2*PI;
		if (ang < 0)
			ang += 2*PI;
		return ang;
	}
	
	//Return = dist ente 2 puntos (x,y)Final - (x,y) inicio
	public double getRang( double x1,double y1, double x2,double y2 )
	{
		double xo = x2-x1;
		double yo = y2-y1;
		double h = Math.sqrt( xo*xo + yo*yo );
		return h;	
	}
	
	//gets the absolute bearing between to x,y coordinates
	public double valAbsolutoDir( double x1,double y1, double x2,double y2 )
	{
		double xo = x2-x1;
		double yo = y2-y1;
		double h = getRang( x1,y1, x2,y2 );
		if( xo > 0 && yo > 0 )
		{
			return Math.asin( xo / h );
		}
		if( xo > 0 && yo < 0 )
		{
			return Math.PI - Math.asin( xo / h );
		}
		if( xo < 0 && yo < 0 )
		{
			return Math.PI + Math.asin( -xo / h );
		}
		if( xo < 0 && yo > 0 )
		{
			return 2.0*Math.PI - Math.asin( -xo / h );
		}
		return 0;
	}


	/**
	 * onScannedRobot: Que hacer al ver otro robot
     * @param e
	 */
        @Override
	public void onScannedRobot(ScannedRobotEvent e) {
		Enemy en;
		if (targets.containsKey(e.getName())) { //comprobamos si la hashtable tiene ese target
			en = (Enemy)targets.get(e.getName()); //si es asi, lo seleccionamos como objetivo
		} else {
			en = new Enemy(); //si no esta en la tabla lo seleccionamos y lo metemos
			targets.put(e.getName(),en);
		}
		//obtiene el valor absoluto del bearing en el punto que se encuentra el robot
		double valAbsolutoDir_rad = (getHeadingRadians()+e.getBearingRadians())%(2*PI);
                
		//Las lineas de abajo serviran para la clase enemy
                //Es info. sobre el enemigo que nos ayudara a determinar su posicion
		en.name = e.getName();
		double h = normalizarDir(e.getHeadingRadians() - en.heading);
		h = h/(getTime() - en.time);
		en.changehead = h;
		en.x = getX()+Math.sin(valAbsolutoDir_rad)*e.getDistance(); 
		en.y = getY()+Math.cos(valAbsolutoDir_rad)*e.getDistance();
		en.bearing = e.getBearingRadians();
		en.heading = e.getHeadingRadians();
		en.time = getTime();		//Momento del juego en el que se ha hecho el scan
		en.speed = e.getVelocity();
		en.distance = e.getDistance();	
		en.live = true;
		if ((en.distance < target.distance)||(target.live == false)) { //si se ha modificado la info, actualizamos
			target = en;
		}
	}
		
        @Override
	public void onRobotDeath(RobotDeathEvent e) { //si nuestro robot muere simulamos que el objetivo
		Enemy en = (Enemy)targets.get(e.getName()); //tambien ha muerto para que no siga intentando
		en.live = false;                            //atacar
	}	
}

class Enemy { //clase enemigo
	/*
	 * Realmente deberiamos usar los getName(), setName()...
	 * Aqui pero llevaria mas trabajo
	 */
	String name; //Declaramos todos las variables que usamos unas lineas mas arriba
	public double bearing,heading,speed,x,y,distance,changehead;
	public long time; 		//hora en la que se ha producido el scan
	public boolean live; 	//Variable que indica si esta vivo
	public Point2D.Double guessPosition(long when) {
		double diff = when - time;
		double newY = y + Math.cos(heading) * speed * diff;
		double newX = x + Math.sin(heading) * speed * diff;
		
		return new Point2D.Double(newX, newY);
	}
}

/**Coordenadas y info concentracion de fuerzas en un punto de "gravedad"**/
class GravPoint {
    public double x,y,power;
    public GravPoint(double pX,double pY,double pPower) {
        x = pX;
        y = pY;
        power = pPower;
    }
}
