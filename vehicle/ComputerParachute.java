package game.vehicle;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.concurrent.ThreadLocalRandom;

import game.CtpGame;

/**
 * This is a computer parachute class, 
 * which represent a vehicle that been controlled by the computer
 * and represent a parachute on screen
 * @author Ron
 *
 */
public class ComputerParachute extends ComputerVehicle {
	
	private static int EASY_DROP = 20;
	private static int MEDIUM_DROP = 40;
	private static int HARD_DROP = 60;
	private static int END_OF_SCREEN = 500;
	private static int MIN_TIME_PARA_FIRST_APPEAR = 5;
	private static int MAX_TIME_PARA_FIRST_APPEAR = 30;
	private static int MIN_TIME_PARA_REAPPEAR = 0;
	private static int MAX_TIME_PARA_REAPPEAR = 10;
	private static int INIT_X_LOC_PARA = 0;
	private static int INIT_Y_LOC_PARA = 0;
	
	private int _levelOfSpeed;
	private int _queuetime;

	// Ctor
	public ComputerParachute(String imagePath, String difficulty) {
		super(imagePath, INIT_X_LOC_PARA, INIT_Y_LOC_PARA);
		setUpDifficulty(difficulty);
		_isVisible = false;
		_queuetime = ThreadLocalRandom.current().nextInt(MIN_TIME_PARA_FIRST_APPEAR,
				MAX_TIME_PARA_FIRST_APPEAR + 1);
	}
	
	// Enum that represent the different levels of difficulty in the game
	public enum Difficulty {
		EASY("easy"),
		MEDIUM("medium"),
		HARD("hard")
	    ;

	    private final String text;

	    private Difficulty(final String text) {
	        this.text = text;
	    }

	    @Override
	    public String toString() {
	        return text;
	    }
	}

	@Override
	public void doAction(CtpGame game) {
		// suspend the parachute of a period of time
		if (_queuetime > 0){
			_queuetime--;
		}
		else{
			Location loc = this.getLoc();
			// make the parachute visible again and according to the plane location
			if (_isVisible == false){
			
				ComputerPlane plane = game.getPlane();
				Location locPlane = plane.getLoc();
				this.updateLocation(locPlane.getX(), locPlane.getY() + _levelOfSpeed);
				_isVisible = true;
				_queuetime = 0;
			
			}
			else
			{
				// the parachute fall into the sea without the boat catching it
				if (loc.getY() + _levelOfSpeed > END_OF_SCREEN){
					_isVisible = false;
					_queuetime = ThreadLocalRandom.current().nextInt(MIN_TIME_PARA_REAPPEAR,
							MAX_TIME_PARA_REAPPEAR + 1);
					game.updateLives();
				}
				else{
					// the parachute keep droping down on screen
					this.updateLocation(loc.getX(), loc.getY() + _levelOfSpeed);
				}
			}
		}


	}

	@Override
	public String getType() {
		return PARACHUT_TYPE;
	}
	
	private void setUpDifficulty(String difficulty){
		if (difficulty.equals(Difficulty.EASY.text)){
			_levelOfSpeed = EASY_DROP;
		}
		else if (difficulty.equals(Difficulty.MEDIUM.text)){
			_levelOfSpeed = MEDIUM_DROP;
		}
		else if (difficulty.equals(Difficulty.HARD.text)){
			_levelOfSpeed = HARD_DROP;
		}
		
	}
	
	public void checkCollision(CtpGame game){
		UserBoat boat = game.getBoat();
		Location locP = this.getLoc();
		Location locB = boat.getLoc();
		Dimension dimP = this.getDimension();
		Dimension dimB = boat.getDimension();
		// use rectangles of the boat and the parachute in order to check if they collide
		Rectangle boatRect = new Rectangle(locB.getX(),locB.getY(),dimB.height,dimB.width); 
		Rectangle paraRect = new Rectangle(locP.getX(),locP.getY(),dimP.height,dimP.width);
		if(paraRect.intersects(boatRect)){
			_isVisible = false;
			// get random time for the next time the parachute will appear
			_queuetime = ThreadLocalRandom.current().nextInt(MIN_TIME_PARA_REAPPEAR,
					MAX_TIME_PARA_REAPPEAR + 1);
			this.updateLocation(INIT_X_LOC_PARA, INIT_Y_LOC_PARA);
			game.updateScore();
		}
	}


}
