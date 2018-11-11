package model;

import java.awt.Color;

public class Piece {
	
	Coordinate coord; //Top Left Position of the piece
	int pieceHeight, pieceWidth;
	boolean isSelected;
	boolean isMain;
	Color pieceColor;
	Model model;
	
	public Piece(Model model, String pieceType, Coordinate coord) throws Exception {
		this.model = model;
		this.isSelected = false;
		this.isMain = false;
		setPieceType(pieceType);
		this.coord = coord;
		setPieceColor();
	}

	private void setPieceType(String pieceType) throws Exception {
		if (pieceType.equals("Main")) {
			pieceHeight = pieceWidth = 2;
			isMain = true;
		} else if (pieceType.equals("Tall")) {
			pieceHeight = 2;
			pieceWidth = 1;
		} else if (pieceType.equals("Wide")) {
			pieceHeight = 1;
			pieceWidth = 2;
		} else if (pieceType.equals("Square")) {
			pieceHeight = pieceWidth = 1;
		} else {
			throw new Exception("Not a Valid Piece Type");
		}
	}

	/**
	 * The Main Piece is set to Red while other pieces are set to Gray
	 */
	private void setPieceColor() {
		if (isMain) {
			pieceColor = Color.RED;
		} else {
			pieceColor = Color.GRAY;
		}
	}
	
	/**
	 * When a piece is selected, the piece is Darkened as a highlight. When it
	 * is not selected, it returns back to normal color.
	 * @param select
	 */
	public void togglePieceSelect(boolean select) {
		if ((isSelected && select) || (!isSelected && !select)) return;
		
		if (select) { 
			pieceColor = pieceColor.darker();
			isSelected = true;
		} else { 
			pieceColor = pieceColor.brighter();
			isSelected = false;
		}
	}
	
	public Coordinate getCoord() {
		return coord;
	}
	
	public int getPieceHeight() {
		return pieceHeight;
	}
	
	public int getPieceWidth() {
		return pieceWidth;
	}
	
	public boolean equals(Piece o) {
		if (this.coord.equals(o.coord)) {
			return true;
		}
		return false;
	}

	public Color getPieceColor() {
		return pieceColor;
	}
	
	public void movePiece(Coordinate coord) {
		this.coord = coord;
	}
}
