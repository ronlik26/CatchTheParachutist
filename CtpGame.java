package game;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import game.graphic.GameGUI;
import game.vehicle.ComputerParachute;
import game.vehicle.ComputerPlane;
import game.vehicle.UserBoat;
import game.vehicle.Vehicle;
import game.vehicle.VehiclesFactory;

// The game :)
public class CtpGame {
	
	private int POINTS_ON_CATCH = 10;
	private int LIFE_LOSE_FAIL_CATCH = 1;
	private int NUM_OF_LIVES = 3;
	private int INIT_SCORE = 0;
	private String CONFIG_PATH = "resources/config.properties";
    private GameGUI _gui;
    private boolean _isGameOver;
    private Vehicle[] _vehicles;
    private int _score;
    private int _lives;
    private String _difficulty;
    private ComputerParachute[] _parasuits;
    private ComputerPlane _plane;
    private UserBoat _boat;
	
    // Ctor
	public CtpGame(int numOfPara){
		// load configuration file
		ConfigFileParser cfp = new ConfigFileParser(CONFIG_PATH);
		_lives = cfp.getLives();
		_difficulty = cfp.getDifficulty();
		// create all the objects on game
		createVehicles(numOfPara, _difficulty);
		this._isGameOver = false;
		_score = INIT_SCORE;
		// load gui
		this._gui = new GameGUI(_score, _lives);
	}
	
    private void createVehicles(int numOfPara, String difficulty) {
    	_parasuits = VehiclesFactory.createParachutes(numOfPara, difficulty);
    	_plane = VehiclesFactory.createPlane();
    	_boat = VehiclesFactory.createBoat();
    	setUpTypes();
    	
    }
	
    // send to the gui all the vehicles 
	private void sendObjectsDraw(){
        for(int i=0; i<this._vehicles.length; i++){
            this._gui.addVehicle(_vehicles[i]);
        }
	}
	   /**
     * runs the game.
     */
    private void run() {
    	// send the vehicles to the gui for it to draw them
    	sendObjectsDraw();
    	// action listener which timer the action of the game
        ActionListener actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
	        	moveVehicles();
	        	checkCollisions();
	        	checkGameOver();
	           	drawAllObjects();
			}
        };
        Timer timer = new Timer( 300, actionListener );
        timer.start();
        // check if the user pressed escape or the game over and meanwhile
        // the thread sleep once in a while to reduce the CPU usage
        while(!this._gui.isEscPressed() && !_isGameOver){
        	try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        timer.stop();
        _gui.drawGameOver(_score);
        System.exit(0);
    }
    
    private void moveVehicles() {
        for(int i=0; i<this._vehicles.length; i++){
            this._vehicles[i].doAction(this);
        }
    }

    private void drawAllObjects() {
        this._gui.drawToScreen(_score, _lives);
    }
    
    private void checkCollisions() {
        for(int i=0; i<this._parasuits.length; i++){
        	this._parasuits[i].checkCollision(this);
        	
        }
    }
    
    private void checkGameOver(){
    	if (_lives <= 0){
    		_isGameOver = true;
    	}
    	
    }
    
    // create array of all the vehicles
    private void setUpTypes(){
    	int size = this._parasuits.length + 2;
    	Vehicle [] vehicles  = new Vehicle[size];
    	int index = 0;
        for(int i=0; i< _parasuits.length; i++){
        		vehicles[index] = (Vehicle) this._parasuits[i];
        		index++;
        }
        vehicles[index] = (Vehicle) this._plane;
        vehicles[index + 1] = (Vehicle) this._boat;

        this._vehicles = vehicles;
    }
        
    public UserBoat getBoat(){
    	return this._boat;
    }
    
    public ComputerPlane getPlane(){
    	return this._plane;
    }
    
    public GameGUI getGUI(){
    	return this._gui;
    }
    
    public void updateScore(){
    	_score += POINTS_ON_CATCH;
    }
         
    public void updateLives(){
    	_lives -= LIFE_LOSE_FAIL_CATCH;
    }

    // Main
    public static void main(String[] args){
    	CtpGame game = new CtpGame(3);
    	game.run();
    
    }
}
