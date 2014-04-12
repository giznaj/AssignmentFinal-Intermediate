/**
 * @author Aaron Toth 300770784
 * @version 1.0
 * Final Assignment for Java Intermediate
 * 
 * This abstract class is extended by Jupiterian and Martian
 */
public abstract class Alien

{	
	/**
	 * private and protected class members for the application.  only the protected members are directly accessible
	 * by the derived classes.  the private members need to be accessed by local or overridden public methods.
	 */
	protected String name;
	protected String typeOfAlien;
	private String homePlanet;
	private int numberOfEyes;	
	
	/**
	 * overloaded constructor for the class.  any derived class of this class will have to call the super constructor
	 * and pass it 4 parameters as defined the the constructor of this class
	 * @param name holds the name String that the alien is given
	 * @param typeOfAlien represents the type of Alien the object is (Martian or Jupiterian)
	 * @param homePlanet represents the planet where the alien originates from
	 * @param numberOfEyes value that holds the number of eyes the alien has
	 */
	public Alien(String name, String typeOfAlien, String homePlanet, int numberOfEyes)
	{
		this.name = name;
		this.typeOfAlien = typeOfAlien;
		this.homePlanet = homePlanet;
		this.numberOfEyes = numberOfEyes;
	}
	
	/**
	 * abstract getter method.  To use this method, it has to be implemented from within the derived classes
	 * @return name which represent the name of the alien
	 */
	abstract String getName();
	
	/**
	 * abstract getter method.  To use this method, it has to be implemented from within the derived classes
	 * @return typeOfAlien which represents the type of alien (Martian or Jupiterian)
	 */
	abstract String getTypeOfAlien();
	
	/**
	 * public method that can be used in the super class or overridden in the derived classes.
	 * @return homePlanet which is used to identify where the alien was born or comes from
	 */
	public String getHomePlanet()
	{
		return homePlanet;
	}
	
	/**
	 * public method that can be used in the super class or overridden in the derived classes.
	 * @return numberOfEyes that represents the number of eyes the alien has
	 */
	public int getNumberOfEyes()
	{
		return numberOfEyes;
	}
	
	/**
	 * user defined toString() method that will return more than the default class.toString() method
	 * @return classDetails.  This is a concatenated string of all the fields of the alien
	 */
	public String toString()
	{
		String classDetails = getName() + ", a " + getTypeOfAlien() + " is from " + getHomePlanet() + " and has "
				+ getNumberOfEyes() + " eyes!";
		
		return classDetails;
	}
}
