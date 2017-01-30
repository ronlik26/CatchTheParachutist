package game.vehicle;

import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import game.CtpGame;
import game.graphic.GamePanel;

/**
 * This abstract class represent the vehicle object
 * @author Ron
 *
 */
public abstract class Vehicle {
	
	public static String BOAT_TYPE = "boat";
	public static String PLANE_TYPE = "plane";
	public static String PARACHUT_TYPE = "parachut";
	
	private Location _loc;
	private Dimension _imageDim;
	private Image _imagePic;
	protected boolean _isVisible;
	
	// Ctor
	Vehicle(String imagePath,int x, int y){
		
		URL url = CtpGame.class.getResource(imagePath);
		ImageIcon img = new ImageIcon(url);
		int height = img.getIconHeight();
		int width = img.getIconWidth();
		_imageDim = new Dimension(width, height);
		_imagePic = img.getImage();
		_loc = new Location(x, y);
		_isVisible = true;
	}
	
	/**
	 * This method is the one 
	 * @param game
	 */
    public abstract void doAction(CtpGame game);
    public abstract String getType();
    
    /**
     * This method update the location of the vehicle 
     * @param x
     * @param y
     */
    public void updateLocation(int x, int y){
    	_loc.updateLocation(x, y);
    }
    
    public Location getLoc(){
    	return _loc;
    }
    
    public Image getImage(){
    	return _imagePic;
    }
    
    /**
     * get the dimension of the image which represent the vehicle 
     * @return image dimension
     */
    public Dimension getDimension(){
    	return _imageDim;
    }
    
    /**
     * get whether or not vehicle image is visible 
     * @return is visible or not
     */
    public boolean getIsVisible(){
    	return _isVisible;
    }
    
}
