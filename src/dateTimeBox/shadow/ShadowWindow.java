package dateTimeBox.shadow;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.geom.Line2D;

import javax.swing.JWindow;

import com.sun.awt.AWTUtilities;

public class ShadowWindow extends JWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8167444755744783701L;

	public ShadowWindow(final Window parent) {
		// TODO Auto-generated constructor stub
		setBackground(Color.GRAY);

		if (AWTUtilities.isTranslucencyCapable(this.getGraphicsConfiguration())){
			setBackground(Color.BLACK);
			AWTUtilities.setWindowOpacity(this, 0.5f);
		}
		
		setLocation(parent.getX() + SHIFT, parent.getY() + SHIFT);
		setSize(parent.getSize());

		
		
		
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		int h = getHeight();
		int w = getWidth();
		
		int a = 255/SHIFT;

		Line2D line = new Line2D.Double();
		
		for(int i = 0; i < SHIFT; i++){
			g2.setPaint(new Color(0, 0, 0, 0 + i*a));
			
			line.setLine(0, h - i, w - i, h - i);
			g2.draw(line);
			
			line.setLine(w - i, 0, w - i, h - i);
			g2.draw(line);

		}


	}
	
	public static final int SHIFT = 5;
}
