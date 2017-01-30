package game.graphic;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener ;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import game.vehicle.Vehicle;

/**
 * This class represent all the gui interaction in the game
 * @author Ron
 *
 */
public class GameGUI {
	
	private static int X_FRAME_DIM = 1024;
	private static int Y_FRAME_DIM = 700;
	private static String TITLE_MSG = "Game Over";
	private static String MSG = "Your score is: ";
	
    /** The frame in the game.*/
    private JFrame _frame;
    
    /** The panel the game is drawn in. */
    private GamePanel _panel;
    
    /** The keyboard listener that keeps track of pressed keys.*/
    private KeyboardListener  _listener;
    
    
    public GameGUI(int score, int lives){
        _listener = new KeyboardListener();
        _frame = new JFrame();
        _panel = new GamePanel(score, lives);
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_frame.setTitle("Catch The Parachutist Game");
		_frame.setSize(X_FRAME_DIM, Y_FRAME_DIM); //frame size
		_frame.setVisible(true);   //frame visibility	
        _frame.addKeyListener(_listener);
        _frame.getContentPane().add(_panel);
    }
    
    
    /** 
     * draws the images in the buffer on the screen.
     */
    public void drawToScreen(int score, int lives){
    	_panel.setLives(lives);
    	_panel.setScore(score);
        _panel.repaint();

    }
    
    public void addVehicle(Vehicle v){
        _panel.addVehicle(v);
    }
    
    /**
     * Checks if the left arrow key is pressed on the keyboard.
     * @return true if the key is pressed. false otherwise.
     */
    public boolean isLeftPressed(){
        return (_listener.isKeyPressed(KeyEvent.VK_LEFT) && 
            !_listener.isKeyPressed(KeyEvent.VK_RIGHT));
    }

    /**
     * Checks if the right arrow key is pressed on the keyboard.
     * @return true if the key is pressed. false otherwise.
     */
    public boolean isRightPressed(){
        return (_listener.isKeyPressed(KeyEvent.VK_RIGHT) && 
                !_listener.isKeyPressed(KeyEvent.VK_LEFT));
    }
	
	
    /**
     * Checks if the escape key is pressed on the keyboard.
     * @return true if the key is pressed. false otherwise.
     */
    public boolean isEscPressed() {
        return _listener.isKeyPressed(KeyEvent.VK_ESCAPE);
    }
    
    /**
     * Pop up a message when the game over
     * @param score
     */
    public void drawGameOver(int score){
    	JOptionPane.showMessageDialog(_frame, MSG + score, TITLE_MSG, JOptionPane.PLAIN_MESSAGE);
    }
	


}
