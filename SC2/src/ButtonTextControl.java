import java.awt.*;
import java.awt.event.*;
import java.applet.*;


public class ButtonTextControl extends Applet implements ActionListener {
	private String message;	// message to display
	private int x, y; // the location of the text
	private TextField textEntry; // where to enter the message
	private Button up, down, left, right; //directions to move the text
	private Button enter; // set the text
	private int width, height;
	
	public void init() { 	//set up Applet
		width = getSize().width; height = getSize().height;
		message = "Hello World";
		x = width/2 ; y = height/2;
		textEntry = new TextField("Change the text here.", 20);
		enter = new Button("Enter");
		up = new Button("UP"); down = new Button("DOWN");
		left = new Button("LEFT"); right = new Button("RIGHT");
		
		setBackground(Color.white);
		
		add(textEntry); 
		add(enter);
		add(up);
		add(down);
		add(left);
		add(right);
		
		enter.addActionListener(this);
		up.addActionListener(this);
		down.addActionListener(this);
		left.addActionListener(this);
		right.addActionListener(this);
	}
	
	public void paint(Graphics g) {
		if (y < 60 || y > 290) { 
			y = height/2;
		}
		if (x < 10 || x > 290) {
			x = width/2 - 20;
		}
		g.drawString(message, x, y);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		// what to do when buttons are pressed
		if (e.getSource() == enter) {
			message = textEntry.getText();
		}
		if (e.getSource() == up) {
			y = y - 10;
		}
		if (e.getSource() == down) {
			y = y + 10;
		}
		if (e.getSource() == left) {
			x = x - 10;
		}
		if (e.getSource() == right) {
			x = x + 10;
		}
		repaint();
	}
}
