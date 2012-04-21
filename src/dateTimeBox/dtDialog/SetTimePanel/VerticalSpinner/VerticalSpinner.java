package dateTimeBox.dtDialog.SetTimePanel.VerticalSpinner;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.MaskFormatter;

import dateTimeBox.DateTimeBox;

public class VerticalSpinner extends JPanel implements ActionListener, MouseListener {

	private int limit;
	
	public static final int MARGIN = 0;
	public static final int LIGHT_BLUE_COLOR = 0xD5EAFF;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel up;
	JLabel down;
	JFormattedTextField textField;
	
	public VerticalSpinner(int limit, int value) {
		super(new GridBagLayout());
		
		this.limit = limit;
		
		//TODO image 
		
		InputStream isUp = DateTimeBox.class.getClassLoader().getResourceAsStream( "rsrc/up.png" );
		InputStream isDown = DateTimeBox.class.getClassLoader().getResourceAsStream( "rsrc/down.png" );

		try {
			up = new JLabel(new ImageIcon(ImageIO.read(isUp)));
			down = new JLabel(new ImageIcon(ImageIO.read(isDown)));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			MaskFormatter formatter = new MaskFormatter(" ## ");
						  formatter.setPlaceholderCharacter('0');
	  	
				  textField = new JFormattedTextField(formatter);
				  textField.setHorizontalAlignment(JFormattedTextField.CENTER);
				  textField.setEditable(false);
				  textField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
				  textField.setBackground(new Color(0xFFFFFF));
				  
					if (value < 10)
						textField.setText("0" + value);
					else
						textField.setText("" + value);
				  
		} catch (ParseException e) {e.printStackTrace();};
		
		textField.addMouseListener(this);
		
		up.addMouseListener(this);
		up.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		up.setName("up");
						
		down.addMouseListener(this);
		down.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		down.setName("down");
		
		this.add(up, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTH, 
											GridBagConstraints.BOTH, new Insets(MARGIN, MARGIN, 0, MARGIN), 0, 0));
		this.add(textField, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.NORTH, 
											GridBagConstraints.BOTH, new Insets(0, MARGIN, 0, MARGIN), 0, 0));
		this.add(down, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.NORTH, 
											GridBagConstraints.BOTH, new Insets(0, MARGIN, MARGIN, MARGIN), 0, 0));
		
		setBackground(Color.WHITE);
		setOpaque(true);
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		if (e.getSource().getClass()== JLabel.class){
			((JLabel)e.getSource()).setForeground(Color.BLUE);
			((JLabel)e.getSource()).setBackground(Color.BLUE);
			((JLabel)e.getSource()).setBorder(new LineBorder(new Color(LIGHT_BLUE_COLOR), 2, true));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		if (e.getSource().getClass() == JLabel.class){
			((JLabel)e.getSource()).setForeground(Color.BLACK);
			((JLabel)e.getSource()).setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource().getClass() == JLabel.class){
			String command = e.getComponent().getName();
		
			int number = -1;
		
			try {
				number = Integer.parseInt(textField.getText(1, 2));
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}
		
		
			if (command.equals("up")){
				number++;
				if (number > limit-1 ) number = 0;
			} else if (command.equals("down")){
				number--;
				if (number < 0 ) number = limit-1;
			}
			
			if (number < 10)
				textField.setText("0" + number);
			else
				textField.setText("" + number);
		} else {
			((JFormattedTextField)e.getSource()).setText(" 00 ");
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public String  getText(){
		try {
			return textField.getText(1, 2);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		
		return "00";
	}

}
