import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.util.*;

public class Fifteen extends JPanel{
	private Board b;
	private Board b2;
	
	public Fifteen(int d) {
		
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		b = new Board(d, 550);
		
		Color backColor = Color.white;
		setBackground(backColor);

		JFrame frame = new JFrame();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setTitle("Game of Fifteen");
	    frame.setResizable(false);
	    frame.add(b, BorderLayout.CENTER);
	    frame.pack();
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	    frame.setSize(550, 571);
	    
	    
	    frame.addKeyListener(new KeyListener() {
	        @Override
	        public void keyTyped(KeyEvent e) {
	        	
	        }

	        @Override
	        public void keyPressed(KeyEvent e) {
	        	switch (e.getKeyCode()) {
	        	case KeyEvent.VK_RIGHT:
	        		if(!b.gameOver) {
	        			b.moveRight();
	        		}
	        		displayGameOver();
	        		break;
	        	case KeyEvent.VK_LEFT:
	        		if(!b.gameOver) {
	        			b.moveLeft();
	        		}
		        	displayGameOver();
	        		break;
	        	case KeyEvent.VK_UP:
	        		if(!b.gameOver) {
	        			b.moveUp();
	        		}
		        	displayGameOver();
	        		break;
	        	case KeyEvent.VK_DOWN:
	        		if(!b.gameOver) {
	        			b.moveDown();
	        		}
		        	displayGameOver();
	        		break;
	        	case KeyEvent.VK_R:
	        			b.restart();
	        		break;
	        	}
	        }

	        @Override
	        public void keyReleased(KeyEvent e) {
	        }
	    });
	    
	}
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int x = 0;
		
		do {
			System.out.println("Enter the size board that you want: ");
			x = reader.nextInt();
		}while(x < 3);
		
		
	
		
		Fifteen game = new Fifteen(x);
	}
	
	public void displayGameOver() {
		if(b.gameOver) {
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(this, "Congratulations, you win! \nPlay again?", "Game Over", dialogButton);
			if(dialogResult == 0) {
			  b.restart();
			} else {
			  System.exit(0);
			} 
		}
	}


}
