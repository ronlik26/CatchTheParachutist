package game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import game.graphic.GamePanel;

/**
 * This class represent a parser to the configuration file of the game
 * which hold settings the user can modify 
 * @author Ron
 *
 */
public class ConfigFileParser {
	
	private static String LIVES_PROPERTY = "lives";
	private static String DIFFICULTY_PROPERTY = "difficulty";
	private static String LIVES_EXP_MSG = "Lives must be a number";
	private static String DIFFICULTY_EXP_MSG = "Choose only one of the possible difficulties";
	private static String FILE_NOT_EXP_MSG = "property file not found in the classpath";
	String _path;
	InputStream _input;
	String _lives;
	String _diff;
	
	ConfigFileParser(String path){
		_path = path;
		_input = null;
		getValues();
	}
	
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
	
	public void getValues(){
		 
		try {
			Properties prop = new Properties();
			URL url = ConfigFileParser.class.getResource(_path);
			_input = new FileInputStream(url.getPath());
			
			if (_input != null) {
				prop.load(_input);
			} else {
				throw new FileNotFoundException(FILE_NOT_EXP_MSG);
			}

			// get the property value and print it out
			_lives = prop.getProperty(LIVES_PROPERTY);
			if (!isNumeric(_lives)){
				throw new Exception(LIVES_EXP_MSG);
			}
			_diff = prop.getProperty(DIFFICULTY_PROPERTY);
			
			if (!_diff.equals(Difficulty.EASY.text) 
					&& !_diff.equals(Difficulty.MEDIUM.text)
					&& !_diff.equals(Difficulty.HARD.text)){
				throw new Exception(DIFFICULTY_EXP_MSG);
			}

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			try {
				_input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static boolean isNumeric(String str)
	{
	    for (char c : str.toCharArray())
	    {
	        if (!Character.isDigit(c)) return false;
	    }
	    return true;
	}
	
	
	public int getLives(){
		
		return Integer.valueOf(_lives);
	}
	
	public String getDifficulty(){
		
		return _diff;
	}
}
