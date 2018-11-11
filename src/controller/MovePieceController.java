package controller;

import javax.swing.JOptionPane;

import model.Model;
import model.Piece;
import model.Puzzle;
import view.KlotskiApplication;

/**
 * This controller handles trying to move a piece on the board. When a keyboard button is pressed, 
 * this controller is called to move the selected piece.
 * 
 * This controller also handles congratulating the user for winning the game. 
 * It throws a message and resets the puzzle. 
 * @author Yil Verdeja
 *
 */
public class MovePieceController {
	
	Model model;
	KlotskiApplication app;
	int keyPressed;
	
	/**
	 * Constructor that takes in the current model of the application, the application GUI, and the direction of the pressed key, which 
	 * is defined by the KeyEvent constants
	 * @param model
	 * @param app
	 * @param keyPressed
	 */
	public MovePieceController(Model model, KlotskiApplication app, int keyPressed) {
		this.model = model;
		this.app = app;
		this.keyPressed = keyPressed;
	}
	
	/**
	 * This method attempts to move the selected piece on the board if possible and 
	 * resets the puzzle if the user has solved the puzzle.
	 * 
	 * If a piece is not selected, it will not be moved.
	 * If a piece is moving towards a taken space or outside the puzzle's boundaries, it will not be moved.
	 */
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
		
		app.setNumberOfMoves(model.getNumMoves());
		app.getPuzzleView().refresh();
		if (puzzle.isSolved()) {
			JOptionPane.showMessageDialog(app, "You won, with "+model.getNumMoves()+" moves! \n"
					+ "Press OK to restart!");
			new ResetPuzzleController().resetPuzzle(model, app);
		}
	}
	
}
