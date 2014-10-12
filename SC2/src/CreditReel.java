import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.lang.Thread;

public class CreditReel extends Applet implements ActionListener {
	private TextField textBox;
	private Button enter;
	private String credits;
	private int width, height;
	private int x, y;
	
	public void init() {
		textBox = new TextField("", 20);
		enter = new Button("ENTER");
		credits = "Replace this text.";
		width = getSize().width; height = getSize().height;
		x = width/2 ; y = height + 50;
		
		add(textBox); add(enter);	
	}
	
	public void paint(Graphics g) {
		try {
			g.drawString(credits, x-(credits.length()*3), y%300);
			y = y + 3;
			Thread.sleep(100);
			repaint();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//g.drawString("ERROR", width/2, height/2);
		}
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == enter) {
			credits = textBox.getText();
			textBox.setText("");
		}
		repaint();
	}
}
