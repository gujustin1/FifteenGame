import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Board extends JPanel{ 
  private int size;
  private int numSquares;
  private int dimension;
  private static final Random RANDOM = new Random();
  private int[] flatBoard;
  private Square[][] board;
  private int tileSize;
  public boolean gameOver;
  
  public Board(int s, int d) {
	  
    size = s;
    dimension = d;
    gameOver = true;
    
    numSquares = size * size - 1; 
    flatBoard = new int[size * size];
    
    tileSize = d / size;
    
    board = new Square[size][size];
    
    int count = 1;
    for(int r = 0; r < size; r++) {
    	for(int c = 0; c < size; c++) {
        		board[r][c] = new Square(c, r, count % (size*size));
    		}
    }
    
    setPreferredSize(new Dimension(dimension, dimension));
    setBackground(Color.WHITE);
    setFont(new Font("Serif", Font.BOLD, 60));
    
    newGame();
    
  }
  
  private void newGame() {
    do {
      shuffle();
    } while(!isSolvable()); 
    
    gameOver = false;
  }
  
  public void restart() {
	  newGame();
	  repaint();
  }
  
  private void shuffle() {
	for (int i = 0; i < flatBoard.length; i++) {//get all numbers into original positions
		      flatBoard[i] = (i + 1) % flatBoard.length;
		    }
		    
    int n = size*size - 1;
    
    while (n > 1) {
      int r = RANDOM.nextInt(n--);
      int tmp = flatBoard[r];
      flatBoard[r] = flatBoard[n];
      flatBoard[n] = tmp;
    }
    
    make2D();
  }
  
  private boolean isSolvable() {//looked up online on how to determine if a board is solvable or not
    int count = 0;//count is the number of inversions, if the count is even then the board is solvable
    //the blank is always in the last position
    
    for (int i = 0; i < size*size -1; i++) {
      for (int j = 0; j < i; j++) {
        if (flatBoard[j] > flatBoard[i])
          count++;
      }
    }
    
    return count % 2 == 0;
  }
  
  private boolean won() {
	  int count = 1;
	  for(int r = 0; r < size; r++) {
		  for(int c = 0; c < size; c++) {
			  if(board[r][c].getValue() != count % (size*size)) {
				  return false;
			  }
			  count++;
		  }
	  }
	  
	  return true;
	
  }
  
  public void drawGrid(Graphics2D g) {
	 
	  for (int row = 0; row < board.length; row++) {
		  	for (int col = 0; col < board[0].length; col++) {
					board[row][col].draw(g, tileSize);
			}
	  }
	  
  }
  
	
	@Override
	  protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    Graphics2D g2D = (Graphics2D) g;
	    g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    drawGrid(g2D);
	  }
	
	private int getBlankR() {
		int r = 0;
		for(int ro = 0; ro < size; ro++) {
			for(int c = 0; c < size; c++) {
				if(board[ro][c].getValue() == 0) {
					r = ro;
					break;
				}
				
			}
		}
		return r;
	}
	
	private int getBlankC() {
		int c = 0;
		for(int r = 0; r < size; r++) {
			for(int co = 0; co < size; co++) {
				if(board[r][co].getValue() == 0) {
					c = co;
					break;
				}
				
			}
		}
		return c;
	}
	
	
	public void moveRight(){
		int r = getBlankR();
		int c = getBlankC();
		
		if(c == 0) {
			return;
		}
		else {
			board[r][c].setValue(board[r][c - 1].getValue());
			board[r][c-1].setValue(0);
			repaint();
		}
		
		if(won()) {
			gameOver = true;
		}
		
	}
	
	public void moveLeft(){
		int r = getBlankR();
		int c = getBlankC();
		
		if(c == size - 1) {
			return;
		}
		else {
			board[r][c].setValue(board[r][c + 1].getValue());
			board[r][c+1].setValue(0);
			repaint();
		}
		
		if(won()) {
			gameOver = true;
		}
		
	}
	public void moveUp(){
		int r = getBlankR();
		int c = getBlankC();
		
		if(r == size - 1) {
			return;
		}
		else {
			board[r][c].setValue(board[r + 1][c].getValue());
			board[r + 1][c].setValue(0);
			repaint();
		}
		
		if(won()) {
			gameOver = true;
		}
	
	}
	public void moveDown(){
		int r = getBlankR();
		int c = getBlankC();
		
		if(r == 0) {
			return;
		}
		else {
			board[r][c].setValue(board[r - 1][c].getValue());
			board[r - 1][c].setValue(0);
			repaint();
		}
		
		if(won()) {
			gameOver = true;
		}
	
	}
  
	private void make2D() {
	  int count = 0;
	  for(int r = 0; r < size; r++){
			for(int c = 0; c < size; c++) {
					board[r][c].setValue(flatBoard[count]);
					count++;
			}
	  }
	  
  }

  
}