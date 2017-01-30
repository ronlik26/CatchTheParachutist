package game.graphic;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.CtpGame;
import game.vehicle.Vehicle;

/**
 * Game Panel class
 * @author Ron
 *
 */
public class GamePanel extends JPanel {

	private static String SEA_IMG_PATH = "images/sea.png";
	private static String BACKGROUND_IMG_PATH = "images/background.png";
	private static String LIVES_L_MSG = "Lives: ";
	private static String SCORE_L_MSG = "Score: ";
	
	private static int LIVES_L_X = 20;
	private static int LIVES_L_Y = 10;
	private static int LIVES_L_WIDTH = 50;
	private static int LIVES_L_HEIGHT = 20;
	
	private static int SCORE_L_X = 100;
	private static int SCORE_L_Y = 10;
	private static int SCORE_L_WIDTH = 100;
	private static int SCORE_L_HEIGHT = 20;
	
	private static int X_SEA_LOC = 0;
	private static int Y_SEA_LOC = 500;
	
	
    private ArrayList<Vehicle> _vehicles;
    private int _score;
    private int _lives;
    JLabel _livesl;
    JLabel _scorel;
    
    // Ctor
    GamePanel(int score, int lives){
    	_score = score;
    	_lives = lives;
    	_vehicles = new ArrayList<Vehicle>();
    	setLayout(null);
    	setFocusable(true);
    	_livesl = new JLabel(LIVES_L_MSG + String.valueOf(_lives));
    	_livesl.setBounds(LIVES_L_X, LIVES_L_Y, LIVES_L_WIDTH, LIVES_L_HEIGHT);   
     
    	_scorel = new JLabel(SCORE_L_MSG + String.valueOf(_score));
    	_scorel.setBounds(SCORE_L_X,SCORE_L_Y,SCORE_L_WIDTH,SCORE_L_HEIGHT);
    	
    	add(_livesl);
    	add(_scorel);
    }
    
    // The method that paint the objects on screen
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		URL url = CtpGame.class.getResource(BACKGROUND_IMG_PATH);
		Image image = new ImageIcon(url).getImage();
		g2d.drawImage(image,0,0,null);
		url = CtpGame.class.getResource(SEA_IMG_PATH);
		image = new ImageIcon(url).getImage();
		g2d.drawImage(image,X_SEA_LOC,Y_SEA_LOC,null);
    	_livesl.setText(LIVES_L_MSG + String.valueOf(_lives));
    	_scorel.setText(SCORE_L_MSG + String.valueOf(_score));
		for (int i = 0; i < _vehicles.size();i++){
			if (_vehicles.get(i).getIsVisible()){
				g2d.drawImage(_vehicles.get(i).getImage(),_vehicles.get(i).getLoc().getX(),_vehicles.get(i).getLoc().getY(),null);
			}
		}
		repaint();
	}
	
    public void addVehicle(Vehicle v){
    	_vehicles.add(v);
    }
    
    public void setLives(int l){
    	_lives = l;
    }
    
    public void setScore(int s){
    	_score = s;
    }

}



