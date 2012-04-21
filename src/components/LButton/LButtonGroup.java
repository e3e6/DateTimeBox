package components.LButton;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class LButtonGroup implements MouseListener {

	private List<LButton> buttons;
	
	public LButtonGroup() {
		super();
		buttons = new ArrayList<LButton>();
	}

	public List<LButton> getButtons() {
		return buttons;
	}

	public void setButtons(List<LButton> buttons) {
		this.buttons = buttons;
	}

	public void add (LButton e){
		buttons.add(e);
		e.addMouseListener(this);
	}
	
	public void remove (LButton e){
		buttons.remove(e);
		e.removeMouseListener(this);
	}
	
	public void clear (){
		buttons.clear();
	}
	
	public LButton getButton(int index){
		return (LButton) buttons.get(index);
	}
	
	public void addListener(LButton e){
		e.addMouseListener(this);
	}
	
	public void clearSelected(){
		for (int i = 0; i < buttons.size(); i++){
			buttons.get(i).setSelected(false);
		}
	}
	
	public void updateButtonsState(){
		for (int i = 0; i < buttons.size(); i++){
			buttons.get(i).updateView();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		clearSelected();
		
		((LButton)e.getSource()).setSelected(true);
		
		updateButtonsState();
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

	
}
