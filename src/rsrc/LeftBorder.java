package rsrc;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.AbstractBorder;

public class LeftBorder extends AbstractBorder {
  /**
	 * 
	 */
	private static final long serialVersionUID = 3843868091632123661L;

protected int thickness;

  protected Color lineColor;

  protected int gap;

  public LeftBorder(Color color) {
    this(color, 1, 1);
  }

  public LeftBorder(Color color, int thickness) {
    this(color, thickness, thickness);
  }

  public LeftBorder(Color color, int thickness, int gap) {
    lineColor = color;
    this.thickness = thickness;
    this.gap = gap;
  }

  public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
    Color oldColor = g.getColor();
    int i;

    g.setColor(lineColor);
    for (i = 0; i < thickness; i++) {
      g.drawLine(x, y + i , x, y + i + height);
    }
    g.setColor(oldColor);
  }

  public Insets getBorderInsets(Component c) {
    return new Insets(0, gap, 0, 0);
  }

  public Insets getBorderInsets(Component c, Insets insets) {
    insets.left = gap;
    insets.top = 0;
    insets.right = 0;
    insets.bottom = 0;
    return insets;
  }

  /**
   * Returns the color of the border.
   */
  public Color getLineColor() {
    return lineColor;
  }

  /**
   * Returns the thickness of the border.
   */
  public int getThickness() {
    return thickness;
  }

  /**
   * Returns whether or not the border is opaque.
   */
  public boolean isBorderOpaque() {
    return false;
  }

  public int getGap() {
    return gap;
  }

}