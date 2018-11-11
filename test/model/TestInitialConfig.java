package model;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sun.glass.events.KeyEvent;

@SuppressWarnings("restriction")
class TestInitialConfig {
	
	Model model;
	Puzzle puzzle;
	ArrayList<Piece> pieces;
	
	
	@BeforeEach
	void setUp() throws Exception {
		model = new Model();
		puzzle = model.getPuzzle();
		pieces = puzzle.getPieces();
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void testInitialConfiguration() {
		for (Piece piece: pieces) {
			Coordinate coord = piece.getCoord();
			if (coord.equals(new Coordinate(3,3))) {
				assertTrue(true);
			} else if (coord.equals(new Coordinate(2,1))) {
				assertTrue(true);
			} else if (coord.equals(new Coordinate(0,0))) {
				assertTrue(true);
			} else if (coord.equals(new Coordinate(0,2))) {
				assertTrue(true);
			} else if (coord.equals(new Coordinate(1,1))) {
				assertTrue(true);
			} else if (coord.equals(new Coordinate(1,3))) {
				assertTrue(true);
			} else if (coord.equals(new Coordinate(2,4))) {
				assertTrue(true);
			} else if (coord.equals(new Coordinate(1,0))) {
				assertTrue(true);
			} else if (coord.equals(new Coordinate(2,0))) {
				assertTrue(true);
			} else if (coord.equals(new Coordinate(3,0))) {
				assertTrue(true);
			} else {
				fail("Should never get here");
			}
		}
	}
	
	@Test
	void testInitialColors() {
		for (Piece piece: pieces) {
			if (piece.isMain) {
				assertEquals(piece.getPieceColor(), Color.RED);
			} else {
				assertEquals(piece.getPieceColor(), Color.GRAY);
			}
		}
		
	}
	
	@Test
	void testSelectNoPieceInitial() {
		assertNull(puzzle.getSelectedPiece());
	}
	
	@Test
	void testSelectPieces() {
		Piece piece;
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 4; col ++) {
				piece = puzzle.getPieceAtCoordinate(new Coordinate(col, row));
				if (piece != null) { 
					puzzle.selectPiece(piece);
					assertEquals(piece, puzzle.getSelectedPiece());
				} else {
					assertNull(piece);
				}
				
			}
		}
	}
	
	@SuppressWarnings("restriction")
	@Test
	void testMoveWithoutSelecting() {
		Piece piece;
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 4; col ++) {
				piece = puzzle.getPieceAtCoordinate(new Coordinate(col, row));
				if (piece != null) {
					assertFalse(puzzle.tryMove(KeyEvent.VK_UP, piece));
				}
			}
		}
	}
	
	@SuppressWarnings("restriction")
	@Test
	void testInvalidMoveSquare() {
		Piece piece = puzzle.getPieceAtCoordinate(new Coordinate(1,0));
		assertTrue(!piece.isMain && piece.getPieceHeight() == 1 && piece.getPieceWidth() == 1);
		puzzle.selectPiece(piece);
		assertFalse(puzzle.tryMove(KeyEvent.VK_UP, piece));
		assertFalse(puzzle.tryMove(KeyEvent.VK_DOWN, piece));
		assertFalse(puzzle.tryMove(KeyEvent.VK_LEFT, piece));
		assertFalse(puzzle.tryMove(KeyEvent.VK_RIGHT, piece));
	}
	
	@SuppressWarnings("restriction")
	@Test
	void testInvalidMoveWide() {
		Piece piece = puzzle.getPieceAtCoordinate(new Coordinate(1,3));
		assertTrue(!piece.isMain && piece.getPieceHeight() == 1 && piece.getPieceWidth() == 2);
		puzzle.selectPiece(piece);
		assertFalse(puzzle.tryMove(KeyEvent.VK_UP, piece));
		assertFalse(puzzle.tryMove(KeyEvent.VK_DOWN, piece));
		assertFalse(puzzle.tryMove(KeyEvent.VK_LEFT, piece));
		assertFalse(puzzle.tryMove(KeyEvent.VK_RIGHT, piece));
	}
	
	@SuppressWarnings("restriction")
	@Test
	void testInvalidMoveTall() {
		Piece piece = puzzle.getPieceAtCoordinate(new Coordinate(0,0));
		assertTrue(!piece.isMain && piece.getPieceHeight() == 2 && piece.getPieceWidth() == 1);
		puzzle.selectPiece(piece);
		assertFalse(puzzle.tryMove(KeyEvent.VK_UP, piece));
		assertFalse(puzzle.tryMove(KeyEvent.VK_DOWN, piece));
		assertFalse(puzzle.tryMove(KeyEvent.VK_LEFT, piece));
		assertFalse(puzzle.tryMove(KeyEvent.VK_RIGHT, piece));
	}
	
	@SuppressWarnings("restriction")
	@Test
	void testInvalidMoveMain() {
		Piece piece = puzzle.getPieceAtCoordinate(new Coordinate(2,1));
		assertTrue(piece.isMain && piece.getPieceHeight() == 2 && piece.getPieceWidth() == 2);
		puzzle.selectPiece(piece);
		assertFalse(puzzle.tryMove(KeyEvent.VK_UP, piece));
		assertFalse(puzzle.tryMove(KeyEvent.VK_DOWN, piece));
		assertFalse(puzzle.tryMove(KeyEvent.VK_LEFT, piece));
		assertFalse(puzzle.tryMove(KeyEvent.VK_RIGHT, piece));
	}
	
	@Test
	void testNotSolved() {
		assertFalse(puzzle.isSolved());
	}
	
	@Test
	void testResetPuzzle() {
		Piece piece = puzzle.getPieceAtCoordinate(new Coordinate(2,4));
		puzzle.selectPiece(piece);
		puzzle.tryMove(KeyEvent.VK_LEFT, piece);
		puzzle.tryMove(KeyEvent.VK_LEFT, piece);
		assertEquals(model.getNumMoves(), 2);
		model.reset();
		assertEquals(model.getNumMoves(), 0);
		testInitialConfiguration();
	}

}
