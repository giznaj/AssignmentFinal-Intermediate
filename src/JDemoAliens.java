import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * @author Aaron Toth 300770784
 * @version 1.0
 * Final Assignment for Java Intermediate
 */
public class JDemoAliens extends JApplet implements ActionListener
{
	/**
	 * needed for this class that extends JApplet.  takes care of warning: 
	 * "The serializable class does not declare a static final serialVersionUID field of type long"
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon image = new ImageIcon("bear.gif");
	private JButton oneButton = new JButton("#1");
	private JButton twoButton = new JButton("#2");
	private JButton threeButton = new JButton("#3");
	private JButton fourButton = new JButton("#4");
	private JButton fiveButton = new JButton("#5");
	private JButton sixButton = new JButton("#6");
	private JButton sevenButton = new JButton("#7");
	private JButton eightButton = new JButton("#8");
	Container con = getContentPane();
	
	/**
	 * declare two public alien objects
	 */
	public Martian newMartian;
	public Jupiterian newJupiterian;
	
	/**
	 * initialize the two alien objects.  once initialized, this applet can call the draw methods in each of 
	 * the alien objects
	 */
	public void init()
	{
		con.setLayout(new FlowLayout());
		con.add(oneButton);
		con.add(twoButton);
		con.add(threeButton);
		con.add(fourButton);
		con.add(fiveButton);
		con.add(sixButton);
		con.add(sevenButton);
		con.add(eightButton);
		
		oneButton.addActionListener(this);
		twoButton.addActionListener(this);
		threeButton.addActionListener(this);
		fourButton.addActionListener(this);
		fiveButton.addActionListener(this);
		sixButton.addActionListener(this);
		sevenButton.addActionListener(this);
		eightButton.addActionListener(this);
		
		newMartian = new Martian();		
		newJupiterian = new Jupiterian();	
	}
	
	/**
	 * @param pen is the Graphics class object that pass to this method used to draw the images
	 */
	public void paint(Graphics pen)
	{	
		super.paint(pen);
	      pen.drawImage(image.getImage(), 0, 0, 20, 20, this);
	}
	
	/**
	 * @param buttonPress is the source of the action the user executed (button clicked)
	 */
	public void actionPerformed(ActionEvent buttonPress)
	{
		System.out.println("Pressed " + buttonPress.getSource());
	}
}
