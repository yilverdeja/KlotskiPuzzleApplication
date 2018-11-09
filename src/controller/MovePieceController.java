package controller;

import javax.swing.JOptionPane;

import model.Model;
import model.Piece;
import model.Puzzle;
import view.KlotskiApplication;

public class MovePieceController {
	
	Model model;
	KlotskiApplication app;
	int keyPressed;
	
	public MovePieceController(Model model, KlotskiApplication app, int keyPressed) {
		this.model = model;
		this.app = app;
		this.keyPressed = keyPressed;
	}
	
	public void tryMove(){
		Puzzle puzzle = model.getPuzzle();
		Piece selectedPiece = puzzle.getSelectedPiece();
		if (selectedPiece == null) {
			System.out.println("MovePieceController: Can't move a piece that is not selected!");
			return;
		}
		
		if (!puzzle.tryMove(keyPressed, selectedPiece)) {
			System.out.println("MovePieceController: Can't move there!");
			return;
		}
		
		model.incrementMoves();
		app.setNumberOfMoves(model.getNumMoves());
		app.getPuzzleView().refresh();
		if (puzzle.isSolved()) {
			JOptionPane.showMessageDialog(app, "You won, with "+model.getNumMoves()+" moves! \n"
					+ "Press OK to restart!");
			new ResetPuzzleController().resetPuzzle(model, app);
		}
	}
	
}
