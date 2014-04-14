import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.Date;
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
	
	private FileSystem fs = FileSystems.getDefault(); //creates FileSystem object
	private File scoresFile =  new File("OsNotKnown.txt");
	
	//keep track of the aliens found
	private int jupiteriansFound, martiansFound;
	
	//keep track of what button the user picked (according to the array of buttons)
	private int selectedIndex;
	
	//scores used for the game
	private int loggedScore = 0; //score of the game that just finished.  Score to be logged
	private int currentHighScore = 0; //current high score of the active session
	
	//true if a game is in progress
	private boolean gameInProgress;
	
	//applet label
	private JLabel appletTicker = new JLabel("Alient Hunt 1.0");
	private JLabel highScoreTicker = new JLabel("Current High Score: " + loggedScore);
	
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
	private JMenu menu2 = new JMenu("Options");
	private JMenu menu3 = new JMenu("Help");
	private JMenuItem play = new JMenuItem("Play");
	private JMenuItem exit = new JMenuItem("Exit");
	private JMenuItem rules = new JMenuItem("Rules of Game");
	private JMenuItem about = new JMenuItem("About");
	private JCheckBoxMenuItem musicOff = new JCheckBoxMenuItem("Music off");
	
	//Martian classes
	private Jupiterian newJupiterian = new Jupiterian(); //this alien is represented by a 0 in the alienArray[0]
	private Martian newMartian = new Martian(); //this alien is represented by a 1 in the alienArray[]
	
	//array of 0's and 1's.  This will be randomized and each index value will be associated with one button
	private int[] alienArray = {0, 0, 1, 1, 1, 1, 1, 1};
	
	//array of buttons
	private JButton[] buttonArray = new JButton[8];
	
	//container for all the buttons
	private Container con = getContentPane();
	
	//holds the value of the default baground.COLOR when the applet is run.  Used for resetting the COLOR
	Color bgColor;
	
	//declare the date object used for auditing the high scores
	Date date = new Date();
	
	//music for the game
	AudioClip gameMusic, monkeyShout, blowUpEarth, drawingBoard;
	
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
		bgColor = con.getBackground();
		con.add(oneButton);
		con.add(twoButton);
		con.add(threeButton);
		con.add(fourButton);
		con.add(fiveButton);
		con.add(sixButton);
		con.add(sevenButton);
		con.add(eightButton);
		con.add(appletTicker);
		con.add(highScoreTicker);
		
		//adds the menu bar and menu items to the applet/screen
		setJMenuBar(mainBar);
		mainBar.add(menu1);
		mainBar.add(menu2);
		mainBar.add(menu3);
		menu1.add(play);
		menu1.add(exit);
		menu2.add(musicOff);
		menu3.add(rules);
		menu3.add(about);
		
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
		rules.addActionListener(this);
		about.addActionListener(this);
		musicOff.addActionListener(this);
				
		gameMusic = getAudioClip(getCodeBase(),"mysteryTune.au");
		monkeyShout = getAudioClip(getCodeBase(), "monkey.au");
		blowUpEarth = getAudioClip(getCodeBase(), "blowUpEarth.au");
		drawingBoard = getAudioClip(getCodeBase(), "drawingBoard.au");
		
		//sets the size of the applet window
		setSize(240, 280);
		
		//determine that there is an active game
		gameInProgress = false;
		
		}
	
	/**
	 * @param buttonPress is the source of the action the user executed (button clicked)
	 */
	public void actionPerformed(ActionEvent userChoice)
	{
		try
		{
			//check if the user wants to play the game or exit the application (menu1)
			if(userChoice.getSource() == play)
			{
				//re-initialize the aliens found back to 0 for when the user starts a new game
				con.setBackground(bgColor);
				gameInProgress = true;
				play.setEnabled(false);
				jupiteriansFound = 0;
				martiansFound = 0;
				
				if(musicOff.isSelected() == false) //prevent music from starting (if new game)
				{
					gameMusic.loop();
				}
				
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
			
			else if(userChoice.getSource() == rules) //menu2
			{
				JOptionPane.showMessageDialog(null,"Find the 6 Martians before the 2 Jupiterians by\n" +
						"clicking the buttons one by one.  Good Luck!\n\nEarth's destiny is in your hands!!!!!\n\n" +
						"Screen turns GREEN when you find a Martian\n" +
						"Screen turns RED when you find a Jupiterian\n\n" +
						"0 points for losing the game\n" +
						"10 points for winning the game\n" +
						"20 points for getting a PERFECT game\n" +
						"Check Scores.txt for high scores and dates\n\n");
			}
			
			else if(userChoice.getSource() == about) //about popup window for the game
			{
				JOptionPane.showMessageDialog(null,"Game: Alien Hunt 1.0\nStudent: Aaron Toth\nStudent #: 300770784\n" +
						"Project: Final Assignment\n\n");
			}
			
			else if(userChoice.getSource() == musicOff) //stop/start music on a current game
			{
				if(musicOff.isSelected() == true) //no matter when the option is used, stop the music
				{
					gameMusic.stop();
				}
				
				else if(musicOff.isSelected() == false && gameInProgress == true) //
				{
					gameMusic.loop();
				}
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
		if(alienArray[selectedIndex] == 0) //Jupiterian found
		{
			con.setBackground(Color.RED); //change background to red
			jupiteriansFound++; //increment the number of Jupiterians found
			monkeyShout.play(); //play the sound when uncovering/finding a Jupiterian
			System.out.println("You have found " + jupiteriansFound + " Jupiterians"); //display the results to user
			
			if(jupiteriansFound == 2) //game is over if this becomes true
			{
				for(int disableAll = 0; disableAll < buttonArray.length; disableAll++) //disable all buttons.
				{
					buttonArray[disableAll].setEnabled(false);
				}
				
				System.out.println("Game over, you lose!"); //display message to the user
				System.out.println("Earth has been destroyed!"); //display message to the user
				loggedScore = 0; //score to be logged in scores.txt
				logScores(loggedScore); //send score to the logScores() method
				gameMusic.stop(); //stop the game music
				blowUpEarth.play(); //play the sound when the player loses.  (earth being destroyed)
				play.setEnabled(true); //enable the item 'File -> Play' menu item
			}
			
			repaint(); //apply the container changes
		}
		
		else if(alienArray[selectedIndex] == 1) //Martian found
		{
			con.setBackground(Color.GREEN); //change background to green
			martiansFound++; //increment the number of Martians found
			System.out.println("You have found " + martiansFound + " Martians"); //display the results to the user
			
			if(martiansFound == 6) //game over if this becomes true
			{
				for(int disableAll = 0; disableAll < buttonArray.length; disableAll++) //disable all buttons
				{
					buttonArray[disableAll].setEnabled(false);
				}
				
				if(jupiteriansFound == 1) //win, but not a perfect game (1 Jupiterian found)
				{
					System.out.println("Game over, you win!");
					loggedScore = 10; //6 Martians found and 1 Jupiterian found
				}
				
				else if(jupiteriansFound == 0) //perfect game.  no Jupiterians found
				{
					System.out.println("Game over, you win!");
					System.out.println("A PERFECT GAME!");
					loggedScore = 20; //6 Martians found and 0 Jupiterians found (perfect game)
				}
				
				System.out.println("Earth has been saved!"); //display a message to the user
				logScores(loggedScore); //send score to the logScores() method
				gameMusic.stop(); //stop the background music
				drawingBoard.play(); //play the sound when the player wins.  (earth being saved)
				play.setEnabled(true); //enable the item 'File -> Play' menu item
				
				if(loggedScore > currentHighScore) //write the new high score if it is actually higher
				{
					highScoreTicker.setText("Current High Score: " + loggedScore);
					currentHighScore = loggedScore;
				}
			}	
			
			repaint();
		}
		
		else
		{
			System.out.println("Invalid selection");
		}
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
	 * @param loggedScore is the score for the game just played.  Losing = 0, Winning = 10, Perfect Game = 20
	 */
	public void logScores(int loggedScore)
	{			
		try
		{
			String scoreString = "Scored " + loggedScore + " points on " + date.toString();
			if(fs.toString().contains("Linux")) //checks for file on a UNIX file system
			{
				scoresFile = new File("/home/aaron/Documents/Centennial/Intermediate/Assignments/Test/scores.txt");
			}
			
			else if(fs.toString().contains("Windows")) //checks for file on a Windows file system
			{
				scoresFile = new File("C:\\temp\\scores.txt");
			}
			
			if(!scoresFile.exists())
			{
				System.out.println("Created the file: " + scoresFile.getName().toString() + " and added the first score");
				System.out.println("Path : " + scoresFile.getPath());
				scoresFile.createNewFile();
    		}
			
			else
			{
				System.out.println("Updated the file: " + scoresFile.getName().toString() + " with the new scores");
			}
			
    		FileWriter fileWriter = new FileWriter(scoresFile,true);
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
	
	/**
	 * draws the aliens on the screen
	 * @param
	 * pen is passed as the default graphics object for drawing the different aliens on the screen
	 */
	public void paint(Graphics pen)
	{
		super.paint(pen);
		if(alienArray[selectedIndex] == 0 && gameInProgress == true)
		{
			newJupiterian.draw(pen, 145, 150);
			newJupiterian.drawString(pen, 115, 270);
		}
		
		else if(alienArray[selectedIndex] == 1 && gameInProgress == true)
		{
			newMartian.draw(pen, 25, 160);
			newMartian.drawString(pen, 10, 270);
		}
		
		else if (gameInProgress == false) //game over.  show both aliens
		{
			newJupiterian.draw(pen, 145, 150);
			newMartian.draw(pen, 25, 160);
		}
	}
}
