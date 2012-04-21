package dateTimeBox.dtDialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.text.ParseException;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;


import rsrc.Constant;
import rsrc.ImagePanel;
import rsrc.TopBorder;
import dateTimeBox.dtDialog.SetDatePanel.SetDatePanel;
import dateTimeBox.dtDialog.SetTimePanel.SetTimePanel;


public class DateTimeDialog extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SetDatePanel setDatePanel;
	private SetTimePanel setTimePanel;
	
	private JButton btnOk;
	private JButton btnCancel;
	private Calendar date;

	
	public DateTimeDialog(Point location, Calendar date) {

		this.date = date;
		
		JPanel content = new JPanel(new GridBagLayout());
		
		/*content.add(new JLabel("Задать дату и время:"), new GridBagConstraints(0, 0, 2, 1, 0, 0, GridBagConstraints.WEST, 
																				  GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		*/
		content.add(DatePanel(), new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.WEST, 
														  GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
		content.add(TimePanel(), new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.WEST, 
														  GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
		content.add(ButtonPanel(), new GridBagConstraints(0, 2, 2, 1, 1, 0, GridBagConstraints.WEST, 
															GridBagConstraints.HORIZONTAL, new Insets(5, 0, 0, 0), 0, 5));
		
		content.setBackground(Color.WHITE);
		
		content.setBorder(BorderFactory.createLineBorder(Constant.LITE_BLUE_COLOR, 2));
		
		int ex = 14;
		
		ImagePanel shadowPanel = new ImagePanel(new GridBagLayout());
				   shadowPanel.add(content, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, ex, ex), 0, 0));
		
				   
		getContentPane().add(shadowPanel);
		setLocation(location);
		

		
		//content.setBorder(new DropShadowBorder());
		setUndecorated( true );
		
		//setModal(true);
		pack();
		
		
		
		//shadow = new ShadowWindow(this);
		
		//Реакция на щёлчёк пользователя за пределами окна выбора времени.
		addWindowFocusListener(new WindowFocusListener() {
			
			@Override
			public void windowLostFocus(WindowEvent e) {

				btnCancel.doClick();
			}
			
			@Override
			public void windowGainedFocus(WindowEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		setVisible(true);
		shadowPanel.createShadowPicture(content.getBounds(), content.getLocationOnScreen());

	}

	
	public JPanel DatePanel(){
		
		JPanel datePanel = new JPanel(new GridBagLayout());
			   datePanel.setPreferredSize(new Dimension(250, 250));
			   
			   /*datePanel.add(new JLabel("Дата:"), new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST, 
						GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
			   	*/			 
			   				 setDatePanel = new SetDatePanel(date);
			   datePanel.add(setDatePanel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.EAST, 
					GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));

			   datePanel.setBackground(Color.WHITE);
			   
		return datePanel;
			   
	}
	
	public JPanel TimePanel(){
		JPanel timePanel = new JPanel(new GridBagLayout());
		   timePanel.setPreferredSize(new Dimension(250, 250));
		   
		  /* timePanel.add(new JLabel("Время:"), new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, 
																	  GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		   */				 setTimePanel = new SetTimePanel(date);	
		   timePanel.add(setTimePanel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, 
				   											  GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
		   timePanel.setBackground(Color.WHITE);

		   return timePanel;
	}
	
	private JPanel ButtonPanel(){
		JPanel buttonPanel = new JPanel(new GridBagLayout());
		
		btnOk = new JButton("Выбрать");
				btnOk.addActionListener(this);
		
		btnCancel = new JButton("Отмена");
				btnCancel.addActionListener(this);
		
			   buttonPanel.add(btnOk, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.EAST, 
					   										 GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
			   buttonPanel.add(btnCancel, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.EAST, 
					   											 GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
			   
			   buttonPanel.setBackground(Constant.DEFAULT_BACKGROUND);
			   buttonPanel.setBorder(new TopBorder(Constant.LITE_BLUE_COLOR, 2));
		return buttonPanel;
	}
	
	public Calendar getTime() {
		Calendar selectedTime = Calendar.getInstance();
		
		String time = setDatePanel.getDate() + " " + setTimePanel.getTime();

		try {
			selectedTime.setTime(Constant.DEFAULT_DATE_FORMAT.parse(time));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	
		return selectedTime;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals("Выбрать")){
			firePropertyChange("timeChanged", null, getTime());
		}
		
		dispose();
	}
}
