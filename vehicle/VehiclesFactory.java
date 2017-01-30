package game.vehicle;

/**
 * This class implement the factory pattern but not in the full capacity because
 * at the moment there is only one type of parachute but by using the factory pattern
 * it gives the opportunity to extend the software in the future
 * @author Ron
 *
 */
public class VehiclesFactory {
	
	private static String BOAT_IMG_PATH = "images/boat.png";
	private static String PARACHUT_IMG_PATH = "images/parachutist.png";
	private static String PLANE_IMG_PATH = "images/plane.png";
	/**
	 * The method get number of parachutes
	 * and according to that create the ComputerParachutes
	 * 
	 * @param number of parachutes, parachute difficulty
	 * @return an array of parachutes 
	 */
    public static ComputerParachute[] createParachutes(int numOfPara, String difficulty) {
    	ComputerParachute [] parachuts  = new ComputerParachute[numOfPara];
        for(int i = 0; i < numOfPara; i++){
        	parachuts[i] = new ComputerParachute(PARACHUT_IMG_PATH, difficulty);

        }
        return parachuts;
    }
    
	/**
	 * The method create boat
	 * 
	 * 
	 * @param 
	 * @return a plane
	 */
    public static UserBoat createBoat() {

        return new UserBoat(BOAT_IMG_PATH);
    }
    
	/**
	 * The method create plane
	 * 
	 * 
	 * @param 
	 * @return a plane
	 */
    public static ComputerPlane createPlane() {

        return new ComputerPlane(PLANE_IMG_PATH);
    }
}



