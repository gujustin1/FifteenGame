import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Square {
	
	private int x,y;
	private int value;
	private static final Color SQUARE_COLOR = new Color(31,62, 204);
	private static final Color SQUARE_COLOR2 = new Color(31,204, 62);
	
	public Square(int x, int y, int v) {
		this.x = x;
		this.y = y;
		value = v;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setValue(int v) {
		value = v;
	}
	
	public void draw(Graphics2D g, int tileSize) {
		
		if(value == 0) {
			g.setColor(Color.WHITE);
		    g.fillRoundRect(x * tileSize, y * tileSize, tileSize, tileSize, 25, 25);
		}
		else {
			 g.setColor(SQUARE_COLOR);
		      g.fillRoundRect(x * tileSize, y * tileSize, tileSize, tileSize, 25, 25);
		      g.setColor(Color.BLACK);
		      g.drawRoundRect(x * tileSize, y * tileSize, tileSize, tileSize, 25, 25);
		      g.setColor(Color.WHITE);
		      
		      drawString(g, String.valueOf(value), tileSize);
		}
	}

	public void drawString(Graphics2D g, String s, int tileSize) {//Found code online on how to center a string
		  FontMetrics fm = g.getFontMetrics();
		  int asc = fm.getAscent();
		  int desc = fm.getDescent();
		  g.drawString(s,  (x * tileSize) + (tileSize - fm.stringWidth(s)) / 2, 
		  (y * tileSize) + (asc + (tileSize - (asc + desc)) / 2));
	}
}
