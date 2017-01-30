package game.vehicle;

import game.CtpGame;

/**
 * This is a computer plane class, 
 * which represent a vehicle that been controlled by the computer
 * and represent a plane on screen
 * @author Ron
 *
 */
public class ComputerPlane extends ComputerVehicle {
	
	private static int INIT_X_LOC_PLANE = 850;
	private static int INIT_Y_LOC_PLANE = 0;
	private static int PLANE_SPEED = 30;

	public ComputerPlane(String imagePath) {
		super(imagePath, INIT_X_LOC_PLANE, INIT_Y_LOC_PLANE);
	}

	@Override
	public void doAction(CtpGame game) {
		Location loc = this.getLoc();
		// continue left until the end of the screen reached, then start all over
		if (loc.getX() > 0){
			this.updateLocation(loc.getX() - PLANE_SPEED, loc.getY());
		}
		else{
			this.updateLocation(INIT_X_LOC_PLANE, loc.getY());
		}
	}

	@Override
	public String getType() {
		return PLANE_TYPE;
	}

}
