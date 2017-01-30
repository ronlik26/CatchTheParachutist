package game.vehicle;

/**
 * Location class
 * @author Ron
 *
 */
public class Location {
	
	private int _x;
	private int _y;
	
	public Location(int x, int y){
		_x = x;
		_y = y;
	}

	public int getX(){
		return _x;
	}
	
	public int getY(){
		return _y;
	}
	
	public void updateLocation(int x, int y){
		_x = x;
		_y = y;
	}

}
