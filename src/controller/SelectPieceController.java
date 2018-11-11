package controller;

import java.awt.Point;

import model.Model;
import model.Piece;
import model.Puzzle;
import view.KlotskiApplication;

/**
 * The SelectPieceController handles a mouse press in the puzzle view JPanel, and selects a piece 
 * if that mouse press is within the pieces boundaries
 * 
 * @author Yil Verdeja
 *
 */
public class SelectPieceController{
	
	KlotskiApplication app;
	Model model;
	Point mouseCursor;
	
	/**
	 * This constructor takes in the model of the application, the applications GUI and a mouse cursor event
	 * @param mouseCursor
	 * @param model
	 * @param app
	 */
	public SelectPieceController(Point mouseCursor, Model model, KlotskiApplication app) {
		this.app = app;
		this.model = model;
		this.mouseCursor = mouseCursor;
	}
	
	/**
	 * Process to select a piece on the board
	 * 
	 * No piece is selected if no piece was clicked on.
	 */
	public void selectPiece() {
		Puzzle puzzle = model.getPuzzle();
		Piece selectedPiece = puzzle.getPieceAtPosition((int)mouseCursor.getX(), (int)mouseCursor.getY());
		if (selectedPiece == null) return;
		
		puzzle.selectPiece(selectedPiece);
		
		app.getPuzzleView().refresh();
	}
	
}
