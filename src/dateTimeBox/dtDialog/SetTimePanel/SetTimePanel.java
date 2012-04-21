package dateTimeBox.dtDialog.SetTimePanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;

import dateTimeBox.dtDialog.SetTimePanel.VerticalSpinner.VerticalSpinner;


public class SetTimePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VerticalSpinner spinHours;
	private VerticalSpinner spinMinutes;
	private VerticalSpinner spinSeconds;
	
	public SetTimePanel(Calendar date){
		super(new GridBagLayout());
		
		JLabel label = new JLabel(":");
		label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
		
		JLabel label2 = new JLabel(":");
		label2.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
		
		spinHours = new VerticalSpinner(24, date.get(Calendar.HOUR_OF_DAY));
		spinMinutes = new VerticalSpinner(60, date.get(Calendar.MINUTE));
		spinSeconds = new VerticalSpinner(60, date.get(Calendar.SECOND));
		
		add(spinHours, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST, 
															GridBagConstraints.NONE, new Insets(5, 5, 5, 0), 0, 0));
		add(label2, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, 
				  						   new Insets(0, 0, 0, 0), 0, 0));
		add(spinMinutes, new GridBagConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.WEST, 
															GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 0));
		add(label, new GridBagConstraints(3, 0, 1, 1, 0, 0, GridBagConstraints.WEST, 
										  GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 0));
		add(spinSeconds, new GridBagConstraints(4, 0, 1, 1, 0, 0, GridBagConstraints.WEST, 
														    GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		
		//setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setBackground(Color.WHITE);	
	}
	
	public String getTime(){
		return spinHours.getText()+ ":" + spinMinutes.getText() + ":" + spinSeconds.getText();
	}
}
