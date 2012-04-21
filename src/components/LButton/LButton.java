package components.LButton;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class LButton extends JLabel implements MouseListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Boolean selected;
	private Boolean highlighted;
	
	private Color textColor;

	public LButton(String text, Color textColor, Boolean selected) {
		super(text);
		
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setBorder(BorderFactory.createEmptyBorder(LINE_THICK, LINE_THICK, LINE_THICK, LINE_THICK));
		this.addMouseListener(this);
		this.setOpaque(true);
		
		this.selected = selected;
		this.highlighted = false;
		
		this.textColor = textColor;
		
		updateView();
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if (textColor == null) {
			setSelected(true);
		
		}
		
		updateView();
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		if (textColor == null) {
			setHighlighted(true);		
		}
		
		updateView();
	}
	@Override
	public void mouseExited(MouseEvent e) {
		setHighlighted(false);
		updateView();
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void updateView(){

		this.setForeground(selected ? COLOR_DARK_BLUE : textColor);

		this.setBackground(highlighted ? COLOR_LIGHT_BLUE : null);

		this.setBorder((highlighted || selected) ? new LineBorder(COLOR_DARK_BLUE, LINE_THICK, true):
												   BorderFactory.createEmptyBorder(LINE_THICK, LINE_THICK, LINE_THICK, LINE_THICK));
	}
	
	public Boolean isSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	@SuppressWarnings("unused")
	private Boolean isHighlighted() {
		return highlighted;
	}

	private void setHighlighted(Boolean highlighted) {
		this.highlighted = highlighted;
	}
	
	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	public static final Color COLOR_LIGHT_BLUE = new Color(224, 238, 254);
	public static final Color COLOR_DARK_BLUE = new Color(0, 125, 255);
	public static final int LINE_THICK = 2;
}
