package dateTimeBox.dtDialog.SetDatePanel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;

import dateTimeBox.dtDialog.SetDatePanel.CalendarButtonPanel.CalendarButtonPanel;




public class SetDatePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1796114673381589728L;

	private SetDateModel model;
	
	private CalendarButtonPanel cbPanel;
	private JLabel leftBtn;
	private JLabel rightBtn;
	private JLabel setBtn;
	
	public SetDatePanel(Calendar date) {
		super(new GridBagLayout());
		
		leftBtn = new JLabel("<<");
				leftBtn.setName("leftBtn");
		setBtn = new JLabel("2010");
				setBtn.setName("setBtn");
		rightBtn = new JLabel(">>");
				rightBtn.setName("rightBtn");
		
		setBtn.setHorizontalAlignment(JLabel.CENTER);
		cbPanel = new CalendarButtonPanel();
		cbPanel.setBackground(Color.WHITE);

		
		add(leftBtn, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST, 
												  GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		add(setBtn, new GridBagConstraints(1, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, 
												  GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		add(rightBtn, new GridBagConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.EAST, 
													GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		add(cbPanel, new GridBagConstraints(0, 1, 3, 1, 1, 1, GridBagConstraints.WEST, 
												   GridBagConstraints.BOTH, new Insets(0, 5, 5, 5), 0, 0));

		//setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setBackground(Color.WHITE);
		
		model = new SetDateModel(date);
		new SetDateController(this, model);
	}

	public void setBtnText(String text){
		setBtn.setText(text);
	}
	
	public void updateLabels(String[][] data, Boolean[][] colorPattern, Boolean[][] selected){

		cbPanel.updateLabels(data, colorPattern, selected);
	}
	

	public void setMouseListener(MouseListener ml){
		leftBtn.addMouseListener(ml);
		rightBtn.addMouseListener(ml);
		setBtn.addMouseListener(ml);
	}
	
	//Задаём слушателя нажатия кнопки (надписи)
	public void setPropertyListener(PropertyChangeListener pcl){
		cbPanel.addPropertyChangeListener("button", pcl);
	}
	
	public String getDate(){
		return new SimpleDateFormat("dd.MM.yyyy").format(model.getCurrentDate().getTime());
	}
}
