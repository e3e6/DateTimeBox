package dateTimeBox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.MaskFormatter;

import rsrc.Constant;
import rsrc.LeftBorder;

public class DateTimeBox extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFormattedTextField textField;
	private JButton btn;
	

	
	public static final int MARGIN = 1;
	
	private Boolean autoFontResize = true;
	private JLabel lbl;
	private JPanel fieldPanel;
	
	/**
	 * 
	 * @param text   Подпись 
	 * @param calendar	Начальное значение даты и времени
	 */
	
	public DateTimeBox(String text, Calendar calendar) {
		super(new GridBagLayout());
		
		//InputStream is = DateTimeBox.class.getClassLoader().getResourceAsStream( "rsrc/arrow.png" );
		InputStream is = DateTimeBox.class.getClassLoader().getResourceAsStream( "rsrc/Misc-Calender-icon.png" );

		try {
			btn = new JButton(new ImageIcon(ImageIO.read(is)));
			//btn = new JButton("\u2715");
			btn.setFocusPainted(false);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		btn.setPreferredSize(new Dimension(20, btn.getPreferredSize().height));	
		btn.setMinimumSize(new Dimension(20, btn.getPreferredSize().height));	
		
		try {
			MaskFormatter formatter = new MaskFormatter(Constant.DATE_MASK);
						  formatter.setPlaceholderCharacter('0');
		  	
			textField = new JFormattedTextField(formatter);
			textField.setHorizontalAlignment(JFormattedTextField.CENTER);
			  			setTime(calendar);
			  			validTime(true);
						
			textField.setMinimumSize(new Dimension(textField.getPreferredSize().width, textField.getPreferredSize().height));
 
		} catch (ParseException e) {e.printStackTrace();};
		
		//изменяем размер шрифта при изменении размера текстового поля
		 textField.addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {
				JFormattedTextField component = (JFormattedTextField)e.getSource();
				Graphics2D g3 = (Graphics2D) component.getParent().getGraphics();
				
				if(autoFontResize && (g3 != null)){
						Font newFont  = component.getFont().deriveFont(Math.round(component.getHeight() * 0.5));
						
						if(newFont.getStringBounds(component.getText(), g3.getFontRenderContext()).getWidth() < component.getWidth()){
							component.setFont(newFont);
						}
				}
			}
		});
		
	

//--------------------------------		
		fieldPanel = new JPanel(new GridBagLayout());
		
				fieldPanel.add(textField, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				fieldPanel.add(btn, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 3, 0));
		
				textField.setBorder(null);
				btn.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 0));
				//btn.setOpaque(false);
				btn.setBackground(null);
				
				btn.addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						((JComponent) e.getSource()).setBackground(null);
						((JComponent) e.getSource()).setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 0));
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						((JComponent) e.getSource()).setBackground(Constant.LITE_BLUE_COLOR);
						((JComponent) e.getSource()).setBorder(new LeftBorder(Constant.LITE_BLUE_COLOR, 2));
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
				
				fieldPanel.setBackground(Color.WHITE);
				fieldPanel.setBorder(BorderFactory.createLineBorder(Constant.LITE_BLUE_COLOR, 2));		
//--------------------------------		
		if(text != null){
			
			lbl = new JLabel(text);
					lbl.setForeground(Color.GRAY);
					//lbl.setFont(lbl.getFont().deriveFont(Font.PLAIN));
			
			this.add(lbl, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		
		this.add(fieldPanel, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 5, 0, 0), 0, 0));
		
		this.setOpaque(false);
		
		new BoxController(this);
		
		
	
	}


	public void setBtnActionListener(BoxController al) {
		btn.addActionListener(al);
	}
	
	//раскрашиввает поле ввода, в зависимости от валидности указанной даты
	public void validTime(boolean b) {
		if (b) {
			textField.setBackground(new Color(Constant.COLOR_VALID));
			textField.setOpaque(false);
		} else {
			textField.setBackground(new Color(Constant.COLOR_WRONG));
			textField.setOpaque(true);
		}
	} 
	
	public void setTime(Calendar c) {
		textField.setText(new SimpleDateFormat((Constant.DATE_FORMAT)).format(c.getTime()));
    }
	
    public void addEditDocumentListener (DocumentListener d1){
    	textField.getDocument().addDocumentListener(d1);
    }


	public String getSTime(int x0, int x1) {
		try {
			return textField.getText(x0, x1);
		} catch (BadLocationException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//Get time
	public Calendar getTime() {
		DateFormat sdf = new SimpleDateFormat(Constant.DATE_FORMAT);
		
		try {
			sdf.parse(textField.getText());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return sdf.getCalendar();
    }
	
	public Point getMenuShowPosition(){
		return new Point(fieldPanel.getLocationOnScreen().x, fieldPanel.getLocationOnScreen().y+fieldPanel.getHeight());
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		
		for(int i = 0; i < this.getComponentCount(); i++){
			this.getComponent(i).setEnabled(enabled);
		}
	}


	public Boolean getAutoFont() {
		return autoFontResize;
	}


	public void setAutoFont(Boolean autoFont) {
		this.autoFontResize = autoFont;
	}
	
	/**
	 * ВОзвращает компонент, отвечающий за вывод информации на экран
	 * @return
	 */
	public JComponent getTextComponent(){
		return textField;
	}
	
	/**
	 * Возвращает компонент, отвечающий за отображение календаря.
	 * @return
	 */
	public JComponent getBtnComponent(){
		return btn;
	}
	
	/**
	 * 
	 * @return
	 */
	public JComponent getLabelComponent(){
		return lbl;
	}
}