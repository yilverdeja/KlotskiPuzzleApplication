package model;

/**
 * The Coordinate class handles the top left x and y position corner on the puzzle tiles. The puzzle is split into a 
 * 4 x 5 grid.
 * @author Yil Verdeja
 *
 */
public class Coordinate {
	int xPos, yPos;
	final int rows = 5;
	final int cols = 4;
	
	/**
	 * Constructor takes in the x and y position of the top left of a piece
	 * @param xPos
	 * @param yPos
	 */
	public Coordinate(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	/**
	 * Gets the tiles x position on the puzzle grid
	 * @return
	 */
	public int getXPos() {
		return xPos;
	}
	
	/**
	 * Gets the tiles y position on the puzzle grid
	 * @return
	 */
	public int getYPos() {
		return yPos;
	}
	
	/**
	 * Checks if piece is within the 4x5 bounds of the puzzle. If the piece is the main piece, 
	 * it can exist out of the puzzle within the coordinates (1,5) and (2,6). Once the Main piece 
	 * has its coordinate at (1,5) that game is won.
	 * @param p
	 * @return
	 */
	public boolean withinBounds(Piece p) {
		boolean isMain = p.isMain;
		if (xPos >= 0 && xPos < cols && yPos >= 0 && yPos < rows) {
			return true;
		}
		
		if (isMain && xPos >= 1 && xPos <= 2 && yPos >= 5 && yPos <= 6) {
			return true;
		}
		return false;
	}
	
	/**
	 * Overriding the equals method. Coordinates equal one another if they have the same x and y positions.
	 * @param o
	 * @return
	 */
	public boolean equals(Coordinate o) {
		if (xPos == o.xPos && yPos == o.yPos) return true;
		return false;
	}
}
