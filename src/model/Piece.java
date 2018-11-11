package model;

import java.awt.Color;

/**
 * A Piece is defined by it's position on the board, it's height and width, its type and color and whether its been selected. This class 
 * can change a pieces position and can toggle its selection
 * @author Yil Verdeja
 *
 */
public class Piece {
	
	Coordinate coord; //Top Left Position of the piece
	int pieceHeight, pieceWidth;
	boolean isSelected;
	boolean isMain;
	Color pieceColor;
	Model model;
	
	/**
	 * The constructor takes in the model, the pieceType and the top left position of that piece on the board
	 * @param model
	 * @param pieceType
	 * @param coord
	 * @throws Exception
	 */
	public Piece(Model model, String pieceType, Coordinate coord) throws Exception {
		this.model = model;
		this.isSelected = false;
		this.isMain = false;
		setPieceType(pieceType);
		this.coord = coord;
		setPieceColor();
	}
	
	/**
	 * There are four types of pieces on the board: Main, Tall, Wide and Square. The 
	 * main piece is a 2x2 RED piece that must leave the board through the slot in order for the puzzle to be solved. 
	 * The Tall piece is a 1x2 GRAY piece, the Wide piece is a 2x1 GRAY piece and the 
	 * Square piece is a 1x1 GRAY piece.
	 * @param pieceType
	 * @throws Exception
	 */
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
	
	/**
	 * Gets the Top left position of the piece on the board
	 * @return
	 */
	public Coordinate getCoord() {
		return coord;
	}
	
	/**
	 * Gets the height of the piece
	 * @return
	 */
	public int getPieceHeight() {
		return pieceHeight;
	}
	
	/**
	 * Gets the width of the piece
	 * @return
	 */
	public int getPieceWidth() {
		return pieceWidth;
	}
	
	/**
	 * Override the equals method. Pieces are equal to each other if they have the same position, and sizes.
	 * @param o
	 * @return
	 */
	public boolean equals(Piece o) {
		if ((this.coord.equals(o.coord)) 
				&& (this.pieceHeight == o.pieceHeight) 
				&& (this.pieceWidth == o.pieceWidth)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Gets the color of the piece
	 * @return
	 */
	public Color getPieceColor() {
		return pieceColor;
	}
	
	/**
	 * Changes the top left position of the piece to a new location
	 * @param coord
	 */
	public void movePiece(Coordinate coord) {
		this.coord = coord;
	}
}
