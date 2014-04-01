import java.awt.Graphics;

/**
 * @author Aaron Toth 300770784
 * @version 1.0
 * Final Assignment for Java Intermediate
 * derived class of the super class (Alien.java) 
 */
public class Jupiterian extends Alien
{
	final int WIDTH = 70, HEIGHT = 70;
	final int arcSize = 70;
	
	/**
	 * constructor that calls the super classes constructor.  since the super class constructor has an 
	 * overloaded constructor that takes parameters (4 of them), then this class when instantiated must call the 
	 * super class constructor first in it's own constructor.
	 */
	public Jupiterian()
	{
		super("Gazoo", "Martian", "Zetox", 2);
	}
	
	/**
	 * returns the value stored in the private field 'homePlanet' in the super class.  achieved by calling the 
	 * super class public getter method.  need to do this for private fields in the super class.
	 * @return homePlanet a private string in the super class.  Represents the Aliens place of birth 
	 */
	public String getHomePlanet()
	{
		return super.getHomePlanet();
	}
	
	/**
	 * returns the value stored in the private field 'numberOfEyes' in the super class.  achieved by calling the 
	 * super class public getter method.  need to do this for private fields in the super class.
	 * @return numberOfEyes from the super class private field
	 */
	public int getNumberOfEyes()
	{
		return super.getNumberOfEyes();
	}
	
	/**
	 * method returns the value stored in the name field.  this method in the derived class has access to the 
	 * 'name' field in the super class because it has an access modifier of 'protected'
	 * @param returns the value stored in the 'name' field
	 */ 
	public String getName()
	{
		return name;
	}
	
	/**
	 * method returns the value stored in the typeOfAlien field.  this method in the derived class has access to
	 * the 'typeOfName' field in the super class because it has an access modifier of 'protected'
	 * @return typeOfAlien String value that is either Martian or Jupiterian
	 */
	public String getTypeOfAlien()
	{
		return typeOfAlien;
	}
	
	/**
	 * draws the alien (Jupiterian)
	 * @param x holds the int value for the starting x coordinate on the grid
	 * @param y holds the int value for the starting y coordinate on the grid
	 */
	public void draw(Graphics pen, int x, int y)
	{
		pen.drawRoundRect(x, y, WIDTH, HEIGHT, arcSize, arcSize);
		pen.drawRoundRect((x+11), (y+15), 13, 13, 12, 12); //left eye
		pen.drawRoundRect((x+45), (y+15), 13, 13, 12, 12); //right eye
		pen.drawRoundRect((x+28), (y+38), 5, 5, 1, 1); //nose
		pen.drawRoundRect((x+37), (y+38), 5, 5, 1, 1); //nose
		pen.drawLine((x+20), (y+55), 193, 85); //(start x, start y, finish x, finish y) - mouth
		
	}
	
	/**
	 * writes the description of the drawings
	 * @param pen is the graphics object that is used to write the text to the applet
	 * @param x represents the starting x coordinate for the string
	 * @param y represents the starting y coordinate for the string
	 */
	public void drawString(Graphics pen, int x, int y)
	{
		pen.drawString("I'm a Jupiterian", x, y);
	}
}
