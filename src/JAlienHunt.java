import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;
import javax.swing.*;

/**
 * @author Aaron Toth 300770784
 * @version 1.0
 * The object of the game is to find all the Martians before finding both Jupiterians.
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
	
	//keep track of the aliens found
	private int jupiteriansFound;
	private int martiansFound;
	
	//keep track of what button the user picked (according to the array of buttons)
	private int selectedIndex = 8;
	
	//score to write to the scores file
	private int loggedScore = 0;
	
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
	private Jupiterian newJupiterian = new Jupiterian(); //this alien is represented by a 0 in the alienArray[0]
	private Martian newMartian = new Martian(); //this alien is represented by a 1 in the alienArray[]
	
	//array of 0's and 1's.  This will be randomized and each index value will be associated with one button
	private int[] alienArray = {0, 0, 1, 1, 1, 1, 1, 1};
	
	//array of buttons
	private JButton[] buttonArray = new JButton[8];
	
	//container for all the buttons
	Container con = getContentPane();
	
	//music for the game while hunting for the aliens
	AudioClip gameMusic; 

	
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
		for(int disableAll = 0; disableAll < buttonArray.length; disableAll++)
		{
			buttonArray[disableAll].setEnabled(false);
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
		
		gameMusic = getAudioClip(getCodeBase(),"mysteryTune.au");
		
		//sets the size of the applet window
		setSize(240, 180);	
	}
	
	/**
	 * loop through the sound file
	 */
	public void start()
	{
		gameMusic.loop();
	}
	
	/**
	 * stops the music loop
	 */
	public void stop()
	{
		gameMusic.stop();
	}

	
	/**
	 * @param buttonPress is the source of the action the user executed (button clicked)
	 */
	public void actionPerformed(ActionEvent userChoice)
	{
		try
		{
			//check if the user wants to play the game or exit the application
			if(userChoice.getSource() == play)
			{
				//re-initialize the aliens found back to 0 for when the user starts a new game
				jupiteriansFound = 0;
				martiansFound = 0;
				System.out.println("!! Alien Hunt !!");
				System.out.println("Find the 6 Martians before you find the 2 Jupeterians");
				shuffleArray(); //shuffle the array of aliens and enable all of the buttons
				for(int enableAll = 0; enableAll < buttonArray.length; enableAll++)
				{
					buttonArray[enableAll].setEnabled(true);
				}
			}
			
			else if(userChoice.getSource() == exit)
			{
				System.exit(0);
			}
			
			//check to see what button the user has pressed
			else if(userChoice.getSource() == oneButton)
			{
				selectedIndex = 0;
				oneButton.setEnabled(false);
				scoreKeeper();
			}
			
			else if(userChoice.getSource() == twoButton)
			{
				selectedIndex = 1;
				twoButton.setEnabled(false);
				scoreKeeper();
			}
			
			else if(userChoice.getSource() == threeButton)
			{
				selectedIndex = 2;
				threeButton.setEnabled(false);
				scoreKeeper();
			}
			
			else if(userChoice.getSource() == fourButton)
			{
				selectedIndex = 3;
				fourButton.setEnabled(false);
				scoreKeeper();
			}
			
			else if(userChoice.getSource() == fiveButton)
			{
				selectedIndex = 4;
				fiveButton.setEnabled(false);
				scoreKeeper();
			}
			
			else if(userChoice.getSource() == sixButton)
			{
				selectedIndex = 5;
				sixButton.setEnabled(false);
				scoreKeeper();
			}
			
			else if(userChoice.getSource() == sevenButton)
			{
				selectedIndex = 6;
				sevenButton.setEnabled(false);
				scoreKeeper();
			}
			
			else if(userChoice.getSource() == eightButton)
			{
				selectedIndex = 7;
				eightButton.setEnabled(false);
				scoreKeeper();
			}
		}
		
		catch(Exception e)
		{
			System.out.println("Error Message :" + e.getMessage());
		}
	}
	
	/**
	 * displays the output to the screen/console.
	 * shows the user what they have uncovered for each button press/click
	 * displays the running score.  when 2 Jupiterians are found, the game is over
	 */
	public void scoreKeeper()
	{
		//check if the user found a jupiterian or an alien
		if(alienArray[selectedIndex] == 0) //jupiterian found
		{
			jupiteriansFound++;
			con.setBackground(Color.RED);
			System.out.println("You have found " + jupiteriansFound + " Jupiterians");
			
			if(jupiteriansFound == 2)
			{
				//disables all buttons before the user decides to play the game
				for(int disableAll = 0; disableAll < buttonArray.length; disableAll++)
				{
					buttonArray[disableAll].setEnabled(false);
				}
				
				System.out.println("Game over, you lose!");
				System.out.println("Earth has been destroyed!");
				loggedScore = 0;
				logScores(loggedScore);
				stop();
			}	
		}
		
		else if(alienArray[selectedIndex] == 1) //martian found
		{
			martiansFound++;
			con.setBackground(Color.GREEN);
			System.out.println("You have found " + martiansFound + " Martians");
			
			if(martiansFound == 6)
			{
				if(jupiteriansFound == 1)
				{
					loggedScore = 10; //6 matians found and 1 jupiterian found
				}
				
				else if(jupiteriansFound == 0)
				{
					loggedScore = 20; //6 martians found and 0 jupiterians found (perfect game)
				}
				//disables all buttons before the user decides to play the game
				for(int disableAll = 0; disableAll < buttonArray.length; disableAll++)
				{
					buttonArray[disableAll].setEnabled(false);
				}
				
				System.out.println("Game over, you win!");
				logScores(loggedScore);
			}	
		}
		
		else
		{
			System.out.println("Invalid selection");
		}
		
		//display the aliens here
	}
	
	/**
	 * Shuffles the array of 0's and 1's.  Each of the values in the array is assigned via a parallel array to
	 * the array of buttons
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
	}
	
	/**
	 * Write scores to scores.txt file
	 */
	public void logScores(int loggedScore)
	{			
		try
		{
			//concatenate the score and text to be written to the scores.txt file
			String scoreString = "You scored " + loggedScore + " points";
			
			//UNIX
			File file = new File("/home/aaron/Documents/Centennial/Intermediate/Assignments/Test/scores.txt");
			
			//Windoze
			//File file = new File("C:\\temp\\scores.txt");
			
			if(!file.exists())
			{
				System.out.println("Created the file: " + file.getName().toString() + " and added the first score in " + file.getPath());
    			file.createNewFile();
    		}
			
			else
			{
				System.out.println("Updated the file: " + file.getName().toString() + " with the new scores");
			}
			
    		FileWriter fileWriter = new FileWriter(file,true);
    	    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
    	    bufferedWriter.write(scoreString);
    	    bufferedWriter.newLine();
    	    bufferedWriter.close();
		}
		
		catch(Exception e)
		{
			System.out.println("Exception : " + e.toString());
		}
	}
}
