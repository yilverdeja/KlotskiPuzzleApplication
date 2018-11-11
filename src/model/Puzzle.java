package model;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * The puzzle class handles all the pieces that are on the 4x5 board, and is used to reset the board, to select pieces, to verify 
 * if the puzzle is complete, and to validate attempted moves.
 * @author Yil Verdeja
 *
 */
public class Puzzle {
	ArrayList<Piece> pieces;
	Model model;
	
	
	public Puzzle(Model model, ArrayList<Piece> pieces) {
		this.model = model;
		this.pieces = pieces;
	}
	
	/**
	 * Initial constructor that takes in the model of the application and sets that starting piece configuration 
	 * @param model
	 */
	public Puzzle(Model model) {
		this.model = model;
		this.pieces = new ArrayList<Piece>();
		setStartingConfiguration();
	}
	
	/**
	 * Adds 10 pieces to the board, which includes a main piece
	 */
	private void setStartingConfiguration() {
		try {
			pieces.add(new Piece(model, "Main", new Coordinate(2,1)));
			pieces.add(new Piece(model, "Tall", new Coordinate(0,0)));
			pieces.add(new Piece(model, "Tall", new Coordinate(0,2)));
			pieces.add(new Piece(model, "Tall", new Coordinate(1,1)));
			pieces.add(new Piece(model, "Wide", new Coordinate(1,3)));
			pieces.add(new Piece(model, "Wide", new Coordinate(2,4)));
			pieces.add(new Piece(model, "Square", new Coordinate(1,0)));
			pieces.add(new Piece(model, "Square", new Coordinate(2,0)));
			pieces.add(new Piece(model, "Square", new Coordinate(3,0)));
			pieces.add(new Piece(model, "Square", new Coordinate(3,3)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns all the pieces on the board
	 * @return
	 */
	public ArrayList<Piece> getPieces(){
		return pieces;
	}
	
	/**
	 * Checks if the puzzle is solved by checking if the main piece is on the board. If the main piece is 
	 * out of the board (its top left position is at coordinate (1,5) then the puzzle is solved.
	 * @return
	 */
	public boolean isSolved() {
		Coordinate winningCoord = new Coordinate(1, 5);
		for (Piece piece: pieces) {
			if (piece.isMain && piece.coord.equals(winningCoord)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Resets the puzzle by removing the pieces on the board and resetting the starting configuration.
	 * @return
	 */
	public boolean resetPuzzle() {
		pieces.clear();
		boolean cleared = pieces.isEmpty();
		setStartingConfiguration();
		return cleared && !pieces.isEmpty();
	}
	
	/**
	 * Given a certain coordinate, the piece that holds that position is returned
	 * @param coord
	 * @return
	 */
	public Piece getPieceAtCoordinate(Coordinate coord) {
		return getPieceAtPosition(coord.getXPos()*model.SQUARE_SIZE, coord.getYPos()*model.SQUARE_SIZE);
	}
	
	/**
	 * Position is the panel position which goes from (0,0) to (240, 300). 
	 * The piece at this position is returned.
	 * @param xPos
	 * @param yPos
	 * @return
	 */
	public Piece getPieceAtPosition(int xPos, int yPos) {
		// Transforms the panel position to the top left grid position on the board
		int modX = xPos/model.SQUARE_SIZE;
		int modY = yPos/model.SQUARE_SIZE;
		
		for (Piece piece: pieces) {
			int x = piece.getCoord().getXPos();
			int y = piece.getCoord().getYPos();
			
			// Gets the size of each piece, and returns the piece if the position entered is within those boundaries
			if ((modX >= x && modX < x+piece.pieceWidth) && 
					modY >= y && modY < y+piece.pieceHeight) {
				return piece;
			}
			
		}
		
		return null;
	}
	
	/**
	 * Selects the a piece on the board, and unselects the rest
	 * @param p
	 */
	public void selectPiece(Piece p) {
		for (Piece piece: pieces) {
			if (p.equals(piece)) {
				piece.togglePieceSelect(true);
			} else {
				piece.togglePieceSelect(false);
			}
		}
	}
	
	/**
	 * Goes through all pieces and returns the selected piece, if any
	 * @return
	 */
	public Piece getSelectedPiece() {
		for (Piece piece: pieces) {
			if (piece.isSelected)
				return piece;
		}
		return null;
	}
	
	/**
	 * Attempts to move the piece in a certain direction. The piece cannot move if another piece is 
	 * blocking its path, or if the boards boundaries are blocking it. A piece can also not move if it is not 
	 * selected. 
	 * @param direction
	 * @param piece
	 * @return
	 */
	public boolean tryMove(int direction, Piece piece) {
		if (!piece.isSelected) return false;
		
		ArrayList<Coordinate> tiles = new ArrayList<Coordinate>();
		Coordinate newCoord = null;
		
		if (direction == KeyEvent.VK_UP) {
			// Gets the tiles above the piece (min 1, max 2)
			tiles.add(new Coordinate(piece.getCoord().getXPos(), piece.getCoord().getYPos()-1));
			if (piece.pieceWidth == 2) { 
				tiles.add(new Coordinate(piece.getCoord().getXPos()+1, piece.getCoord().getYPos()-1));
			}

			if (!validateMove(tiles, piece)) return false;
			
			// Moves the piece towards that direction if there are no problems
			newCoord = new Coordinate(piece.getCoord().getXPos(), piece.getCoord().getYPos()-1);
			
		} else if (direction == KeyEvent.VK_DOWN) {
			tiles.add(new Coordinate(piece.getCoord().getXPos(), piece.getCoord().getYPos()+piece.pieceHeight));
			
			if (piece.pieceWidth == 2) { 
				tiles.add(new Coordinate(piece.getCoord().getXPos()+1, piece.getCoord().getYPos()+piece.pieceHeight));
			}

			if (!validateMove(tiles, piece)) return false;
			
			newCoord = new Coordinate(piece.getCoord().getXPos(), piece.getCoord().getYPos()+1);
		} else if (direction == KeyEvent.VK_LEFT) {
			tiles.add(new Coordinate(piece.getCoord().getXPos()-1, piece.getCoord().getYPos()));
			
			if (piece.pieceHeight == 2) { 
				tiles.add(new Coordinate(piece.getCoord().getXPos()-1, piece.getCoord().getYPos()+1));
			}

			if (!validateMove(tiles, piece)) return false;
		
			
			newCoord = new Coordinate(piece.getCoord().getXPos()-1, piece.getCoord().getYPos());
		} else if (direction == KeyEvent.VK_RIGHT) {
			tiles.add(new Coordinate(piece.getCoord().getXPos()+piece.pieceWidth, piece.getCoord().getYPos()));
			
			if (piece.pieceHeight == 2) { 
				tiles.add(new Coordinate(piece.getCoord().getXPos()+piece.pieceWidth, piece.getCoord().getYPos()+1));
			}

			if (!validateMove(tiles, piece)) return false;
			
			newCoord = new Coordinate(piece.getCoord().getXPos()+1, piece.getCoord().getYPos());
		}
		
		if (newCoord == null) return false;
		movePiece(newCoord, piece);
		tiles.clear();
		return true;
		
	}
	
	/**
	 * Changes the coordinate of the piece if the move is validated
	 * @param c
	 * @param piece
	 */
	private void movePiece(Coordinate c, Piece piece) {
		piece.movePiece(c);
		model.incrementMoves();
	}
	
	/**
	 * Checks whether the tiles that the piece will move to are in bound and not occupied 
	 * by another piece.
	 * @param tiles
	 * @param piece
	 * @return
	 */
	private boolean validateMove(ArrayList<Coordinate> tiles, Piece piece) {
		for (Coordinate c: tiles) {
			if(!c.withinBounds(piece)) {
				System.out.println("Puzzle: Move is not within bounds");
				return false; // out of boundary
			}
			if (getPieceAtCoordinate(c) != null) {
				System.out.println("Puzzle: A piece already exists there!");
				return false; //piece exists
			}
		}
		return true;
	}
	
}
