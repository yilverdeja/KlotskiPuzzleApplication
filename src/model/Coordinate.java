package model;

/**
 * The xPos and yPos are taken from the Top Left Corner of a coordinate tile
 * @author Yil Verdeja
 * @version 1.0
 *
 */
public class Coordinate {
	int xPos, yPos;
	final int rows = 5;
	final int cols = 4;
	
	public Coordinate(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public int getXPos() {
		return xPos;
	}
	
	public int getYPos() {
		return yPos;
	}
	
	public boolean withinBounds(Piece p) {
		boolean isMain = p.isMain;
		if (xPos >= 0 && xPos < cols && yPos >= 0 && yPos < rows) {
			return true;
		}
		
		if (isMain && xPos >= 1 && xPos <= 2 && yPos >= 5 && yPos <= 6) {
			return true;
		}
		System.out.println("Coordinate: not within bounds");
		return false;
	}
	
	public boolean equals(Coordinate o) {
		if (xPos == o.xPos && yPos == o.yPos) return true;
		return false;
	}
}
