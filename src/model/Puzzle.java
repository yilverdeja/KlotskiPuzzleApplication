package model;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Puzzle {
	ArrayList<Piece> pieces;
	int numberOfMoves;
	Model model;
	
	public Puzzle(Model model, ArrayList<Piece> pieces) {
		this.model = model;
		this.pieces = pieces;
		this.numberOfMoves = 0;
	}
	
	public Puzzle(Model model) {
		this.model = model;
		this.pieces = new ArrayList<Piece>();
		this.numberOfMoves = 0;
		setStartingConfiguration();
	}
	
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
	
	public ArrayList<Piece> getPieces(){
		return pieces;
	}
	
	/**
	 * Checks if the puzzle is solved by checking if the main piece is on the board
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
	
	public boolean resetPuzzle() {
		pieces.clear();
		boolean cleared = pieces.isEmpty();
		setStartingConfiguration();
		return cleared && !pieces.isEmpty();
	}
	
	public int getNumberOfMoves() {
		return numberOfMoves;
	}
	
	public void incrementMoveCounter() {
		numberOfMoves++;
	}
	
	public Piece getPieceAtCoordinate(Coordinate coord) {
		return getPieceAtPosition(coord.getXPos()*model.SQUARE_SIZE, coord.getYPos()*model.SQUARE_SIZE);
	}

	public Piece getPieceAtPosition(int xPos, int yPos) {
		int modX = xPos/model.SQUARE_SIZE;
		int modY = yPos/model.SQUARE_SIZE;
		
		for (Piece piece: pieces) {
			int x = piece.getCoord().getXPos();
			int y = piece.getCoord().getYPos();
			
			if ((modX >= x && modX < x+piece.pieceWidth) && 
					modY >= y && modY < y+piece.pieceHeight) {
				return piece;
			}
			
		}
		
		return null;
	}
	
	public void selectPiece(Piece p) {
		for (Piece piece: pieces) {
			if (p.equals(piece)) {
				piece.togglePieceSelect(true);
			} else {
				piece.togglePieceSelect(false);
			}
		}
	}
	
	public Piece getSelectedPiece() {
		for (Piece piece: pieces) {
			if (piece.isSelected)
				return piece;
		}
		return null;
	}
	
	public boolean tryMove(int direction, Piece piece) {
		if (!piece.isSelected) return false;
		
		ArrayList<Coordinate> tiles = new ArrayList<Coordinate>();
		Coordinate newCoord = null;
		
		if (direction == KeyEvent.VK_UP) {
			tiles.add(new Coordinate(piece.getCoord().getXPos(), piece.getCoord().getYPos()-1));
			if (piece.pieceWidth == 2) { 
				tiles.add(new Coordinate(piece.getCoord().getXPos()+1, piece.getCoord().getYPos()-1));
			}

			if (!validateMove(tiles, piece)) return false;
			
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
	
	private void movePiece(Coordinate c, Piece piece) {
		piece.movePiece(c);
	}
	
	private boolean validateMove(ArrayList<Coordinate> tiles, Piece piece) {
		for (Coordinate c: tiles) {
			System.out.println(c.getXPos()+"; "+c.getYPos());
			if(!c.withinBounds(piece)) {
				System.out.println("Not in Bounds");
				return false; // out of boundary
			}
			if (getPieceAtCoordinate(c) != null) {
				System.out.println("Piece exists here!");
				return false; //piece exists
			}
		}
		return true;
	}
	
}
