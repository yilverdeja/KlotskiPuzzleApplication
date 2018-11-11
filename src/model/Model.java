package model;

import java.util.ArrayList;

public class Model {
	
	final public int SQUARE_SIZE = 60;
	final public int PUZZLE_HEIGHT = SQUARE_SIZE*5;
	final public int PUZZLE_WIDTH = SQUARE_SIZE*4;
	
	Puzzle puzzle;
	int numMoves;
	
	public Model() {
		puzzle = new Puzzle(Model.this);
		numMoves = 0;
	}
	
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
	
	public Puzzle getPuzzle() {
		return puzzle;
	}
	
	public void incrementMoves() {
		numMoves++;
	}
	
	public int getNumMoves() {
		System.out.println("NUM MOVES: "+numMoves);
		return numMoves;
	}
	
	public boolean reset() {
		numMoves = 0;
		return puzzle.resetPuzzle();
	}
	
}
