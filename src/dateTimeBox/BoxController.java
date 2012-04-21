package dateTimeBox;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import dateTimeBox.dtDialog.DateTimeDialog;



import ru.topcode.skillbase.regexp.DateValidator;
import ru.topcode.skillbase.regexp.Time24HoursValidator;

public class BoxController implements ActionListener, DocumentListener {
	private DateTimeBox view;
	private DateTimeDialog dialog;
	
	public BoxController(DateTimeBox view) {
		this.view = view;
		view.setBtnActionListener(this);
		view.addEditDocumentListener(this);	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
			dialog = new DateTimeDialog(view.getMenuShowPosition(), view.getTime());
				
				dialog.addPropertyChangeListener("timeChanged", new PropertyChangeListener() {
					
					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						view.setTime((Calendar)evt.getNewValue());
					}
				});
				
				//dialog.setVisible(true);
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
	
	}


	@Override
	public void insertUpdate(DocumentEvent e) {
		//с какого символа начинается время
		int timeStartPos = (" dd.MM.yyyy HH:mm:ss ").indexOf("H");

		view.validTime(new Time24HoursValidator().validate(view.getSTime(timeStartPos, 8))&&
			               new DateValidator().validate(view.getSTime(1, 10)));

	}


	@Override
	public void removeUpdate(DocumentEvent e) {
	
	}
}
