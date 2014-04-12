import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
	
	//keep track of the aliens found
	private int jupiteriansFound;
	private int martiansFound;
	
	//keep track of what button the user picked (according to the array of buttons)
	private int selectedIndex;
	
	//score to write to the scores file
	private int loggedScore = 0;
	
	//true if a game is in progress
	private boolean gameInProgress;
	
	//applet label
	private JLabel appletTicker = new JLabel("Alient Hunt 1.0");
	
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
	private JCheckBoxMenuItem soundOff = new JCheckBoxMenuItem("Sound off");
	
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
	AudioClip gameMusic; 
	AudioClip monkeyShout;
	AudioClip blowUpEarth;
	AudioClip drawingBoard;
	
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
		
		//adds the menu bar and menu items to the applet/screen
		setJMenuBar(mainBar);
		mainBar.add(menu1);
		mainBar.add(menu2);
		mainBar.add(menu3);
		menu1.add(play);
		menu1.add(exit);
		menu2.add(soundOff);
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
		soundOff.addActionListener(this);
				
		gameMusic = getAudioClip(getCodeBase(),"mysteryTune.au");
		monkeyShout = getAudioClip(getCodeBase(), "monkey.au");
		blowUpEarth = getAudioClip(getCodeBase(), "blowUpEarth.au");
		drawingBoard = getAudioClip(getCodeBase(), "drawingBoard.au");
		
		//sets the size of the applet window
		setSize(240, 280);
		
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
				
				if(soundOff.isSelected() == false) //prevent music from starting (if new game)
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
						"20 points for getting a PERFECT game");
			}
			
			//about popup window for the game
			else if(userChoice.getSource() == about)
			{
				JOptionPane.showMessageDialog(null,"Alien Hunt 1.0\nAaron Toth\n300770784");
			}
			
			//stop/start music on a current game
			else if(userChoice.getSource() == soundOff) 
			{
				if(soundOff.isSelected() == true)
					{
						gameMusic.stop();
					}
				
				else
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
		//check if the user found a jupiterian or an alien
		if(alienArray[selectedIndex] == 0) //jupiterian found
		{
			jupiteriansFound++;
			monkeyShout.play();
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
				gameMusic.stop();
				blowUpEarth.play();
			}
			
			repaint();
		}
		
		else if(alienArray[selectedIndex] == 1) //martian found
		{
			martiansFound++;
			System.out.println("You have found " + martiansFound + " Martians");
			
			if(martiansFound == 6)
			{
				if(jupiteriansFound == 1)
				{
					System.out.println("Game over, you win!");
					loggedScore = 10; //6 matians found and 1 jupiterian found
				}
				
				else if(jupiteriansFound == 0)
				{
					System.out.println("Game over, you win!");
					System.out.println("A PERFECT GAME!");
					loggedScore = 20; //6 martians found and 0 jupiterians found (perfect game)
				}
				//disables all buttons before the user decides to play the game
				for(int disableAll = 0; disableAll < buttonArray.length; disableAll++)
				{
					buttonArray[disableAll].setEnabled(false);
				}
				
				System.out.println("Earth has been saved!");
				logScores(loggedScore);
				gameMusic.stop();
				drawingBoard.play();
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
			//concatenate the score and text to be written to the scores.txt file
			String scoreString = "Scored " + loggedScore + " points on " + date.toString();
			
			//UNIX
			File file = new File("/home/aaron/Documents/Centennial/Intermediate/Assignments/Test/scores.txt");
			
			//Windoze
			//File file = new File("C:\\temp\\scores.txt");
			
			if(!file.exists())
			{
				System.out.println("Created the file: " + file.getName().toString() + " and added the first score");
				System.out.println("Path : " + file.getPath());
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
	
	/**
	 * draws the aliens on the screen
	 * @param
	 * pen is passed as the default graphics object for drawing the different aliens on the screen
	 */
	public void paint(Graphics pen)
	{
		super.paint(pen);
		if(alienArray[selectedIndex] == 0)
		{
			newJupiterian.draw(pen, 145, 120);
			newJupiterian.drawString(pen, 115, 240);
			con.setBackground(Color.RED);
		}
		
		else if(alienArray[selectedIndex] == 1)
		{
			newMartian.draw(pen, 25, 130);
			newMartian.drawString(pen, 10, 240);
			con.setBackground(Color.GREEN);
		}
		
		else
		{
			System.out.println("ran because tried to draw something that didn't exist");
		}
	}
}
