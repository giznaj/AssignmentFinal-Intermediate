import java.awt.Graphics;

/**
 * @author Aaron Toth 300770784
 * @version 1.0
 * Final Assignment for Java Intermediate
 * derived class of the super class (Alien.java) 
 */
public class Martian extends Alien
{
	final int WIDTH = 50, HEIGHT = 50;
	final int arcSize = 50;
	
	/**
	 * constructor that calls the super classes constructor.  since the super class constructor has an 
	 * overloaded constructor that takes parameters (4 of them), then this class when instantiated must call the 
	 * super class constructor first in it's own constructor.
	 */
	public Martian()
	{
		super("Mork", "Jupiterian", "Jupiter", 4);
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
	 * draws the alien (Martian)
	 * @param pen is the object used to render the alien
	 * @param x holds the int value for the starting x coordinate on the grid
	 * @param y holds the int value for the starting y coordinate on the grid
	 */
	public void draw(Graphics pen, int x, int y)
	{
		pen.drawRoundRect(x, y, WIDTH, HEIGHT, arcSize, arcSize); //head
		pen.drawRoundRect((x+10), (y+10), 10, 10, 12, 12); //left eye
		pen.drawRoundRect((x+30), (y+10), 10, 10, 12, 12); //right eye
		pen.drawLine((x+10), (y+35), 65, 165); //(start x, start y, finish x, finish y) - mouth
		pen.drawLine((x+25), y, (x+25), (y-20)); //antenna
		pen.drawLine((x+15), (y-15), 60, (y-15)); //antenna
	}
	
	/**
	 * writes the description of the drawings
	 * @param pen is the graphics object that is used to write the text to the applet
	 * @param x represents the starting x coordinate for the string
	 * @param y represents the starting y coordinate for the string
	 */
	public void drawString(Graphics pen, int x, int y)
	{
		pen.drawString("Found a Martian", x, y);
	}
}
