package model;

import java.util.ArrayList;

public class Model {
	
	final public int SQUARE_SIZE = 60;
	final public int PUZZLE_HEIGHT = SQUARE_SIZE*5;
	final public int PUZZLE_WIDTH = SQUARE_SIZE*4;
	
	Puzzle puzzle;
	int numMoves = 0;
	
	public Model(boolean normal) {
		if (normal)
			puzzle = new Puzzle(Model.this);
		else {
			ArrayList<Piece> puzzlePieces = new ArrayList<Piece>();
			try {
				puzzlePieces.add(new Piece(Model.this, "Main", new Coordinate(2,1)));
			} catch (Exception e) {
				e.printStackTrace();
			}
			puzzle = new Puzzle(Model.this, puzzlePieces);
		}
	}
	
	public Puzzle getPuzzle() {
		return puzzle;
	}
	
	public int getWindowHeight() {
		return PUZZLE_HEIGHT*2;
	}
	
	public int getWindowWidth() {
		return PUZZLE_WIDTH*2;
	}
	
	public void incrementMoves() {
		numMoves++;
	}
	
	public int getNumMoves() {
		return numMoves;
	}
	
	public boolean reset() {
		numMoves = 0;
		return puzzle.resetPuzzle();
	}
	
}
