package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import model.Model;
import model.Piece;
import model.Puzzle;

public class PuzzleView extends JPanel {
	
	Model model;
	/**
	 * Create the panel.
	 */
	public PuzzleView(Model model) {
		this.model = model;
	}
	
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

	public void refresh() {
		repaint();
	}

}
