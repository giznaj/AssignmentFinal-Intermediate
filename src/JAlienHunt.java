import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

/**
 * @author Aaron Toth 300770784
 * @version 1.0
 * Final Assignment for Java Intermediate
 */
public class JAlienHunt extends JApplet implements ActionListener
{
	/**
	 * needed for this class that extends JApplet.  takes care of warning: 
	 * "The serializable class does not declare a static final serialVersionUID field of type long"
	 */
	private static final long serialVersionUID = 1L;
	
	//random generator for mixing up the aliens/buttons
	private final Random randomGenNumber = new Random();
	
	//applet buttons
	private JButton oneButton = new JButton("#1");
	private JButton twoButton = new JButton("#2");
	private JButton threeButton = new JButton("#3");
	private JButton fourButton = new JButton("#4");
	private JButton fiveButton = new JButton("#5");
	private JButton sixButton = new JButton("#6");
	private JButton sevenButton = new JButton("#7");
	private JButton eightButton = new JButton("#8");
	
	//applet menu
	private JMenuBar mainBar = new JMenuBar();
	private JMenu menu1 = new JMenu("File");
	private JMenuItem play = new JMenuItem("Play");
	private JMenuItem exit = new JMenuItem("Exit");
	
	//Martian classes
	private Martian newMartian = new Martian();
	private Jupiterian newJupiterian = new Jupiterian();
	
	//array of 0's and 1's.  This will be randomized and each index value will be associated with one button
	private int[] alienArray = {0, 0, 1, 1, 1, 1, 1, 1};
	
	//array of buttons
	private JButton[] buttonArray = new JButton[8];
	
	Container con = getContentPane();
	
	/**
	 * initialize the two alien objects.  once initialized, this applet can call the draw methods in each of 
	 * the alien objects
	 */
	public void init()
	{
		buttonArray[0] = oneButton;
		buttonArray[1] = twoButton;
		buttonArray[2] = threeButton;
		buttonArray[3] = fourButton;
		buttonArray[4] = fiveButton;
		buttonArray[5] = sixButton;
		buttonArray[6] = sevenButton;
		buttonArray[7] = eightButton;
		
		//disables all buttons before the user decides to play the game
		for(int disableIndex = 0; disableIndex < buttonArray.length; disableIndex++)
		{
			buttonArray[disableIndex].setEnabled(false);
		}
		
		//sets the layout for the applet and adds the eight buttons to the screen
		con.setLayout(new FlowLayout());
		con.add(oneButton);
		con.add(twoButton);
		con.add(threeButton);
		con.add(fourButton);
		con.add(fiveButton);
		con.add(sixButton);
		con.add(sevenButton);
		con.add(eightButton);
		
		//adds the menu bar and menu items to the applet/screen
		setJMenuBar(mainBar);
		mainBar.add(menu1);
		menu1.add(play);
		menu1.add(exit);
		
		//add the actionListener to the elments on the applet
		oneButton.addActionListener(this);
		twoButton.addActionListener(this);
		threeButton.addActionListener(this);
		fourButton.addActionListener(this);
		fiveButton.addActionListener(this);
		sixButton.addActionListener(this);
		sevenButton.addActionListener(this);
		eightButton.addActionListener(this);
		play.addActionListener(this);
		exit.addActionListener(this);
		
		//sets the size of the applet window
		setSize(240, 180);	
	}
	
	
	/**
	 * @param buttonPress is the source of the action the user executed (button clicked)
	 */
	public void actionPerformed(ActionEvent userChoice)
	{
		try
		{
			//enables all the buttons for the user to guess where the aliens (martians and jupeterians) are
			if(userChoice.getSource() == play) //user starts the game
			{
				for(int enableIndex = 0; enableIndex < buttonArray.length; enableIndex++)
				{
					buttonArray[enableIndex].setEnabled(true);
				}
			}
			
			else if(userChoice.getSource() == exit) //user closes the application using the File -> Exit menu
			{
				System.exit(0);
			}
			
			else if(userChoice.getSource() == oneButton)
			{
				oneButton.setEnabled(false);
			}
			
			else if(userChoice.getSource() == twoButton)
			{
				twoButton.setEnabled(false);
			}
			
			else if(userChoice.getSource() == threeButton)
			{
				threeButton.setEnabled(false);
			}
			
			else if(userChoice.getSource() == fourButton)
			{
				fourButton.setEnabled(false);
			}
			
			else if(userChoice.getSource() == fiveButton)
			{
				fiveButton.setEnabled(false);
			}
			
			else if(userChoice.getSource() == sixButton)
			{
				sixButton.setEnabled(false);
			}
			
			else if(userChoice.getSource() == sevenButton)
			{
				sevenButton.setEnabled(false);
			}
			
			else if(userChoice.getSource() == eightButton)
			{
				eightButton.setEnabled(false);
			}
		}
		
		catch(Exception e)
		{
			System.out.println("Error Message :" + e.getMessage());
		}
	}
	
	/**
	 * 
	 */
	public void shuffleArray()
	{
		int indexToReplace; //start at index 0 and loop until array.length-1
		int randomIndex; //select a random index from the array to switch with current loop index
		int tempNumber; //holds the value of the index that is being switched so there is backup (swapping)
		
		for(indexToReplace = 0; indexToReplace < alienArray.length ; indexToReplace++) //loop through entire array
		{
			randomIndex = randomGenNumber.nextInt(alienArray.length-1); //generate random (between 0 - array.length-1)
			tempNumber = alienArray[indexToReplace]; //assign value in loop current index to tempNumber
			alienArray[indexToReplace] = alienArray[randomIndex]; //assign value in random index to current loop index
			alienArray[randomIndex] = tempNumber; //assign the value in tempNumber to the random index value
		}
		
		displayArray(); //call the print method()
	}
	
	
	public void displayArray()
	{
		System.out.printf( "%s%8s\n", "Index", "Value" );
		int index = 0; // used to increment the loop
		for(index = 0; index < alienArray.length; ++index)
		{
			System.out.printf( "%5d%8d\n", index, alienArray[index]);
		}
		
		System.out.println();
	}
}
