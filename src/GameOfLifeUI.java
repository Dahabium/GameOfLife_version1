

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

public class GameOfLifeUI extends JPanel {

	private GameOfLife game;
	private int squaresize;
	private Cell[][] grid;
	
	public GameOfLifeUI(int numbcols, int numbrows, int squaresize, GameOfLife game) {
		
		this.game = game;
		this.squaresize = squaresize;
		setPreferredSize(new Dimension(numbcols*squaresize,numbrows*squaresize));
		
		this.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				toggleGridValue(e.getX(),e.getY());
			}
		});
	}

	protected void toggleGridValue(int x, int y) {
		int i = x/squaresize;
		int j = y/squaresize;
		if (grid[i][j].getState().isAlive())
			grid[i][j].setDead();
		else
			grid[i][j].setAlive();
		repaint();
	}

	public void tick() {
		this.repaint();
	}

	public void notifyMe(Cell[][] newGrid) {
		grid = newGrid;
		tick();
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		//draw background
		drawgrid(g2);
		
		//draw alive cells
		g2.setColor(Color.YELLOW);
		for (int i=0; i<grid.length; i++)
			for (int j=0; j<grid[0].length; j++) 
				if (grid[i][j].getState().isAlive())
					g2.fill(new Rectangle2D.Double(i*squaresize+1,j*squaresize+1,squaresize-1,squaresize-1));
	}

	public void drawgrid(Graphics2D g2) {
		//fillbackground
		g2.setColor(Color.LIGHT_GRAY);
		g2.fill(getVisibleRect());
		//drawgrid
		g2.setColor(Color.GRAY);
		for (int i=0;i<=grid.length;i++)
			g2.drawLine(i*squaresize, 0, i*squaresize,grid[0].length*squaresize);
		for (int i=0;i<=grid[0].length;i++)
			g2.drawLine(0,i*squaresize,grid.length*squaresize,i*squaresize);
	}
		
}

