import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class MouseTextControl extends Applet implements ActionListener, MouseMotionListener {
	private String message;
	private int x, y;
	private int width, height;
	private TextField textEntry;
	private Button enter;
	
	public void init() {
		width = getSize().width; height = getSize().height;
		x = width/2; y = height/2;
		//textEntry = new TextField("Enter text", 20);
		enter = new Button("ENTER");
		message = "Hello World!";
		
		addMouseMotionListener(this);
		
		//add(textEntry); 
		//add(enter);
	}
	
	public void paint(Graphics g) {
		g.drawString(message, x, y);
	}
	
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		repaint();
		e.consume();
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	//public void actionPerformed(ActionEvent e) {
	//	if (e.getSource() == enter) {
	//		message = textEntry.getText();
	//	}
	//	repaint();
	//}
}
