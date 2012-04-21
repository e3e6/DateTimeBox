package dateTimeBox.shadow;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
 
public class ShadowedWindow extends JWindow {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8337528527831422998L;

	private BufferedImage splash = null;
 
    public ShadowedWindow(BufferedImage image) {
        createShadowPicture(image);
    }
 
    public void paint(Graphics g) {
        if (splash != null) {
            g.drawImage(splash, 0, 0, null);
        }
    }
    
    private void createShadowPicture(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int extra = 14;
 
        setSize(new Dimension(width + extra, height + extra));
        setLocationRelativeTo(null);
        Rectangle windowRect = getBounds();
 
        splash = new BufferedImage(width + extra, height + extra, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) splash.getGraphics();
 
        try {
            Robot robot = new Robot(getGraphicsConfiguration().getDevice());
            BufferedImage capture = robot.createScreenCapture(new Rectangle(windowRect.x, windowRect.y, windowRect.width + extra, windowRect.height + extra));
            g2.drawImage(capture, null, 0, 0);
        } catch (AWTException e) { }
 
        BufferedImage shadow = new BufferedImage(width + extra, height + extra, BufferedImage.TYPE_INT_ARGB); 
        Graphics g = shadow.getGraphics();
        g.setColor(new Color(0.0f, 0.0f, 0.0f, 0.3f));
        g.fillRoundRect(6, 6, width, height, 12, 12);
 
        g2.drawImage(shadow, getBlurOp(7), 0, 0);
        g2.drawImage(image, 0, 0, this);
    }
 
    private ConvolveOp getBlurOp(int size) {
        float[] data = new float[size * size];
        float value = 1 / (float) (size * size);
        for (int i = 0; i < data.length; i++) {
            data[i] = value;
        }
        return new ConvolveOp(new Kernel(size, size, data));
    }
 
    public static void main(String[] args) {
        try {
            BufferedImage image = ImageIO.read(ShadowedWindow.class.getResourceAsStream("images/splash.png"));
            ShadowedWindow window = new ShadowedWindow(image);
            window.setVisible(true);
            Timer timer = new Timer(5000, new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    System.exit(0);
                }
            });
            timer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

