package bcf;

import java.awt.Color;
import robocode.BulletMissedEvent;
import robocode.HitByBulletEvent;
import robocode.HitWallEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;
import robocode.WinEvent;

/**
 * Dummy. A very simple robot.
 * @author Bernardino Casas
 */
public class Dummy extends Robot
{
    private int count = 0;

    /**
     * run: Dummy's default behavior
     */
    public void run() {	
	setColors(Color.magenta,Color.blue,Color.yellow); // body,gun,radar
	
	// Robot main loop
	while(true) {
	    if (count < 15) {
		turnGunRight(15);
	    }
	    else {
		count = 0;
		ahead(30);
	    }
	    ++count;
	}
    }
    
    /**
     * onScannedRobot: Fire hard depending on the distance.
     */
    public void onScannedRobot(ScannedRobotEvent e) {
	count = 0;
	if (e.getDistance() < 100) {
	    fire(2);
	}
	else {
	    fire(1);
	}
	scan();
    }
    
    /**
     * onHitByBullet: Turn left and ahead.
     */
    public void onHitByBullet(HitByBulletEvent e) {
	turnLeft(65);
	ahead(100);
    }

    /**
     * onHitWall:  Change the direction.
     */
    public void onHitWall(HitWallEvent e) {
	turnRight(180);
	ahead(50);
    }

    /**
     * onWin:  Do a victory dance.
     */
    public void onWin(WinEvent e) {
	for (int i = 0; i < 50; i++) {
	    turnRight(30);
	    turnLeft(30);
	}
    }
}
