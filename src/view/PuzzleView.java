package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.Model;
import model.Piece;
import model.Puzzle;

/**
 * The PuzzleView class is where the puzzle is drawn, and where the cursor interacts. All the changes to 
 * the puzzle and all its pieces can be seen in this view.
 * @author Yil Verdeja
 *
 */
public class PuzzleView extends JPanel {
	

	private static final long serialVersionUID = 1L;
	
	Model model;
	
	/**
	 * Constructor that create the panel taking in the model of the application
	 */
	public PuzzleView(Model model) {
		this.model = model;
	}
	
	/**
	 * Paints each piece that exists in the puzzle and their specified position, and 
	 * given their color, height and width
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Puzzle puzzle = model.getPuzzle();
		
		int offset = 0;
		int slotSize = 5;
		
		g.setColor(Color.white);
		g.fillRect(0, 0, model.PUZZLE_WIDTH, model.PUZZLE_HEIGHT);
		
		for (Piece piece: puzzle.getPieces()) {
			
			
			int xPanelPos = piece.getCoord().getXPos()*model.SQUARE_SIZE+offset;
			int yPanelPos = piece.getCoord().getYPos()*model.SQUARE_SIZE+offset;
			int pieceHeight = piece.getPieceHeight()*model.SQUARE_SIZE-offset*2;
			int pieceWidth = piece.getPieceWidth()*model.SQUARE_SIZE-offset*2;
			
			g.setColor(piece.getPieceColor());
			g.fillRect(xPanelPos, yPanelPos, 
					pieceWidth, pieceHeight);
			
			g.setColor(Color.BLACK);
			g.drawRect(xPanelPos, yPanelPos, 
					pieceWidth, pieceHeight);
			
		}
		
		g.setColor(Color.black);
		g.fillRect(model.PUZZLE_WIDTH/4, model.PUZZLE_HEIGHT-slotSize, model.PUZZLE_WIDTH/2, slotSize);
		
	}
	
	/**
	 * Refreshes the view after every validated action
	 */
	public void refresh() {
		repaint();
	}

}
