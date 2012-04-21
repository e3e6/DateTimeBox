package demo;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import dateTimeBox.DateTimeBox;

public class WinDemo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1492845169756254869L;

	public WinDemo() throws HeadlessException {
		setLayout(new GridBagLayout());
		
		JPanel panel = new JPanel(new GridBagLayout());
		
		panel.add(new DateTimeBox("Выберите дату и время", Calendar.getInstance()), new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		
		add(panel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new WinDemo();
	}

}
