package rsrc;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

/**
 * Тень
 * @author aleksandr.zrelykh
 *
 */
public class ImagePanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6449080372290695868L;
	private BufferedImage aImage;
	
    public ImagePanel() {
		// TODO Auto-generated constructor stub
    	super();
	}
	
    public ImagePanel(LayoutManager mgr) {
		// TODO Auto-generated constructor stub
    	super(mgr);
    	
    	
	}
    
    public void createShadowPicture(Rectangle bounds, Point point) {
    	
    	int width = bounds.width;
        int height = bounds.height;

        
        int aX = point.x;
        int aY = point.y;
        
        int extra = 14;

 
        aImage = new BufferedImage(width + extra, height + extra, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) aImage.getGraphics();
 
        try {
            Robot robot = new Robot(getGraphicsConfiguration().getDevice());
            BufferedImage capture = robot.createScreenCapture(new Rectangle(aX, aY, width + extra, height + extra));
            g2.drawImage(capture, null, 0, 0);
        } catch (AWTException e) { }
 
        BufferedImage shadow = new BufferedImage(width + extra, height + extra, BufferedImage.TYPE_INT_ARGB); 
        
        Graphics g = shadow.getGraphics();
        		 g.setColor(new Color(0.0f, 0.0f, 0.0f, 0.3f));
        		 g.fillRoundRect(6, 6, width, height, 12, 12);
 
        g2.drawImage(shadow, getBlurOp(7), 0, 0);
        //g2.drawImage(image, 0, 0, this);*/
    }
 
    private ConvolveOp getBlurOp(int size) {
        float[] data = new float[size * size];
        float value = 1 / (float) (size * size);
        for (int i = 0; i < data.length; i++) {
            data[i] = value;
        }
        return new ConvolveOp(new Kernel(size, size, data));
    }
    
	public BufferedImage getImage() {
        return aImage;
    }
    public void setImage(BufferedImage image) {
        this.aImage = image;
    }
    public void paintComponent(Graphics g) {
       // super.paintComponent(g);
        if(aImage != null){
            g.drawImage(aImage, 0, 0, getWidth(), getHeight(), null);
        }
    }
}