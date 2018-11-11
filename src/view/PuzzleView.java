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
		
		for (Piece piece: puzzle.getPieces()) {
			
			int xPanelPos = piece.getCoord().getXPos()*model.SQUARE_SIZE;
			int yPanelPos = piece.getCoord().getYPos()*model.SQUARE_SIZE;
			int pieceHeight = piece.getPieceHeight()*model.SQUARE_SIZE;
			int pieceWidth = piece.getPieceWidth()*model.SQUARE_SIZE;
			
			g.setColor(piece.getPieceColor());
			g.fillRect(xPanelPos, yPanelPos, 
					pieceWidth, pieceHeight);
			
			g.setColor(Color.BLACK);
			g.drawRect(xPanelPos, yPanelPos, 
					pieceWidth, pieceHeight);
			
		}
		
	}
	
	/**
	 * Refreshes the view after every validated action
	 */
	public void refresh() {
		repaint();
	}

}
