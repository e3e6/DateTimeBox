package dateTimeBox.dtDialog.SetDatePanel;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;

import javax.swing.JLabel;

public class SetDateController implements MouseListener, PropertyChangeListener{
	private SetDatePanel view;
	private SetDateModel sdtModel;
	
	public SetDateController(SetDatePanel mainWindow, SetDateModel setDateTimeModel) {
		this.view = mainWindow;
		this.sdtModel = setDateTimeModel;
				
		view.setMouseListener(this);
		view.setPropertyListener(this);
		
		updateDateBtnText();
		updateTableModel();
	}

	public void updateDateBtnText(){
			
		switch (sdtModel.getTable_mode()) {
		case 0: //model.MODE_DAY
			view.setBtnText("<html><right>" + sdtModel.getMonthFull(sdtModel.getCurrentDate().get(Calendar.MONTH)) + 
							" " +
							Integer.toString(sdtModel.getCurrentDate().get(Calendar.YEAR)) + "</right></html>");
			break;
			
		case 1: //model.MODE_MONTH:
			view.setBtnText(Integer.toString(sdtModel.getCurrentDate().get(Calendar.YEAR)));
			break;		
			
		case 2: //model.MODE_YEAR:
				view.setBtnText(sdtModel.getCurrentDecade() + "-" + (sdtModel.getCurrentDecade() + 9));
				break;
		default:
			break;
		}
	}

	//обновление содержимого «таблицы»
	public void updateTableModel(){
		view.updateLabels(sdtModel.getCurrentModel(), sdtModel.getCurrentColorPattern(), sdtModel.getCurrentSelected());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		String action_com = ((JLabel)e.getSource()).getName();
		
		if(action_com.equals("leftBtn")){

			sdtModel.switchNextCurrentDate(false);

		}else if (action_com.equals("rightBtn")){

			sdtModel.switchNextCurrentDate(true);
			
		}else if (action_com.equals("setBtn")){
	
			sdtModel.switchNextMode();
		}
	
		//обновить надпись на кнопке
		updateDateBtnText();
		
		//обновляем содержимое «таблицы»
		updateTableModel();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		((JLabel)e.getSource()).setForeground(Color.BLUE);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		((JLabel)e.getSource()).setForeground(Color.BLACK);
	}

	@Override
	public void mousePressed(MouseEvent e) {
	
	}	


	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		sdtModel.setCurrentFromText(((JLabel)evt.getNewValue()).getText());

		if (sdtModel.getTable_mode() > 0) {
			sdtModel.switchPreviousMode();
			updateDateBtnText();
			updateTableModel();
		}
	}
}
