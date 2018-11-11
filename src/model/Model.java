package model;

import java.util.ArrayList;

/**
 * The model class is in charge of the creation of the puzzle and the handles the number of moves the player has made
 * @author Yil Verdeja
 *
 */
public class Model {
	
	// Constants of the Model class to determine the grid size on the puzzle view panel
	final public int SQUARE_SIZE = 60;
	final public int PUZZLE_HEIGHT = SQUARE_SIZE*5;
	final public int PUZZLE_WIDTH = SQUARE_SIZE*4;
	
	Puzzle puzzle;
	int numMoves;
	
	/**
	 * Initial Model constructor to initialize the puzzle with pieces in set configuration
	 */
	public Model() {
		puzzle = new Puzzle(Model.this);
		numMoves = 0;
	}
	
	/**
	 * This Constructor is for testing purposes to place a single piece on the puzzle board at a specific coordinate
	 * @param pieceType
	 * @param coord
	 * @throws Exception
	 */
	public Model(int pieceType, Coordinate coord) throws Exception {
		if (pieceType < 1 || pieceType > 4) {
			throw new Exception("Wrong pieceType! 1 - Main; 2 - Wide; 3 - Tall; 4 - Square");
		}
		
		ArrayList<Piece> puzzlePieces = new ArrayList<Piece>();
		try {
			if (pieceType == 1) {
				puzzlePieces.add(new Piece(Model.this, "Main", coord));
			} else if (pieceType == 2) {
				puzzlePieces.add(new Piece(Model.this, "Wide", coord));
			} else if (pieceType == 3) {
				puzzlePieces.add(new Piece(Model.this, "Tall", coord));
			} else if (pieceType == 4) {
				puzzlePieces.add(new Piece(Model.this, "Square", coord));
			}
		} catch (Exception e) {
			
		}
		
		puzzle = new Puzzle(Model.this, puzzlePieces);
		numMoves = 0;
		
	}
	
	/**
	 * Get's the puzzle of the model
	 * @return
	 */
	public Puzzle getPuzzle() {
		return puzzle;
	}
	
	/**
	 * Increments the number of moves by 1
	 */
	public void incrementMoves() {
		numMoves++;
	}
	
	/**
	 * Gets the number of moves
	 * @return
	 */
	public int getNumMoves() {
		return numMoves;
	}
	
	/**
	 * Resets the model, thus reseting the puzzle and the number of moves
	 * @return
	 */
	public boolean reset() {
		numMoves = 0;
		return puzzle.resetPuzzle();
	}
	
}
