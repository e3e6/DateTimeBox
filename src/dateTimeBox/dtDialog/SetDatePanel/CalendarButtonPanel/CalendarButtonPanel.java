package dateTimeBox.dtDialog.SetDatePanel.CalendarButtonPanel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import components.LButton.LButton;
import components.LButton.LButtonGroup;


public class CalendarButtonPanel extends JPanel implements MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Insets ins = new Insets(0, 0, 0, 0);
	private LButtonGroup group;
	
	
	public CalendarButtonPanel() {
		super(new GridBagLayout());
		group = new LButtonGroup();
	}
	
	private void fillPanel(String[][] captions, Boolean[][] colorPattern, Boolean[][] selected){
		
		for (int m = 0; m < captions.length; m++)
			for (int n = 0; n < captions[0].length; n++){
				LButton btn = new LButton(captions[m][n], colorPattern[m][n]?null:COLOR_INACTIVE_MONTH, selected[m][n]);
						btn.addMouseListener(this);
						
				this.add(btn, new GridBagConstraints(n, m, 1, 1, 1, 1, GridBagConstraints.WEST, 
																  	   GridBagConstraints.BOTH, ins, 0, 0));
				if (colorPattern[m][n])  {
					group.add(btn);
				}
			}
	}
	
	public void updateLabels(String[][] data, Boolean[][] colorPattern, Boolean[][] selected){

		this.removeAll();
		this.repaint();
		group.clear();

		
		fillPanel(data, colorPattern, selected);
		
		this.revalidate();
		this.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.firePropertyChange("button", null, e.getComponent());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
	public static final Color COLOR_INACTIVE_MONTH = Color.GRAY.brighter();
}
