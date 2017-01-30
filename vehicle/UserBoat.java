package game.vehicle;

import game.CtpGame;
import game.graphic.GameGUI;

/**
 * This is a user boat class, 
 * which represent a vehicle that been controlled by the user
 * and represent a boat on screen
 * @author Ron
 *
 */
public class UserBoat extends UserVehicle {

	private static int BOAT_SPEED = 50;
	
	// Ctor
	public UserBoat(String imagePath) {
		super(imagePath, 500, 400);
		
	}

	@Override
	public void doAction(CtpGame game) {
		Location loc = this.getLoc();
		GameGUI inputGame = game.getGUI();
		// move left or right by a constant speed
		if (inputGame.isLeftPressed()){ 
			this.updateLocation(loc.getX() - BOAT_SPEED, loc.getY());
		} else if (inputGame.isRightPressed()){
			this.updateLocation(loc.getX() + BOAT_SPEED, loc.getY());
		}
	}

	@Override
	public String getType() {
		return BOAT_TYPE;
	}

}
