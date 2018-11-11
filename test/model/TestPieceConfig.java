package model;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

/**
 * Using JUnit5, tests a modified configuration of the puzzle. Each model has a puzzle with a single specific piece. This allows for much easier movement 
 * and to test the winning case of the puzzle
 * @author Yil Verdeja
 *
 */
class TestPieceConfig {
	
	Model model1, model2, model3, model4;
	Puzzle puzzle1, puzzle2, puzzle3, puzzle4;
	Piece piece1, piece2, piece3, piece4;

	@BeforeEach
	void setUp() throws Exception {
		model1 = new Model(1, new Coordinate(1,1));
		model2 = new Model(2, new Coordinate(1,1));
		model3 = new Model(3, new Coordinate(1,1));
		model4 = new Model(4, new Coordinate(1,1));
		
		puzzle1 = model1.getPuzzle();
		puzzle2 = model2.getPuzzle();
		puzzle3 = model3.getPuzzle();
		puzzle4 = model4.getPuzzle();
		
		piece1 = puzzle1.getPieces().get(0);
		piece2 = puzzle2.getPieces().get(0);
		piece3 = puzzle3.getPieces().get(0);
		piece4 = puzzle4.getPieces().get(0);
		
		piece1.togglePieceSelect(true);
		piece2.togglePieceSelect(true);
		piece3.togglePieceSelect(true);
		piece4.togglePieceSelect(true);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testMovementUp() {
		assertTrue(puzzle1.tryMove(KeyEvent.VK_UP, piece1));
		assertTrue(puzzle2.tryMove(KeyEvent.VK_UP, piece2));
		assertTrue(puzzle3.tryMove(KeyEvent.VK_UP, piece3));
		assertTrue(puzzle4.tryMove(KeyEvent.VK_UP, piece4));
	}
	
	@Test
	void testMovementDown() {
		assertTrue(puzzle1.tryMove(KeyEvent.VK_DOWN, piece1));
		assertTrue(puzzle2.tryMove(KeyEvent.VK_DOWN, piece2));
		assertTrue(puzzle3.tryMove(KeyEvent.VK_DOWN, piece3));
		assertTrue(puzzle4.tryMove(KeyEvent.VK_DOWN, piece4));
	}
	
	@Test
	void testMovementLeft() {
		assertTrue(puzzle1.tryMove(KeyEvent.VK_LEFT, piece1));
		assertTrue(puzzle2.tryMove(KeyEvent.VK_LEFT, piece2));
		assertTrue(puzzle3.tryMove(KeyEvent.VK_LEFT, piece3));
		assertTrue(puzzle4.tryMove(KeyEvent.VK_LEFT, piece4));
	}
	
	@Test
	void testMovementRight() {
		assertTrue(puzzle1.tryMove(KeyEvent.VK_RIGHT, piece1));
		assertTrue(puzzle2.tryMove(KeyEvent.VK_RIGHT, piece2));
		assertTrue(puzzle3.tryMove(KeyEvent.VK_RIGHT, piece3));
		assertTrue(puzzle4.tryMove(KeyEvent.VK_RIGHT, piece4));
	}
	
	@Test
	void testSolved() {
		puzzle1.tryMove(KeyEvent.VK_DOWN, piece1); // 2,1
		puzzle1.tryMove(KeyEvent.VK_DOWN, piece1); // 3,1
		puzzle1.tryMove(KeyEvent.VK_DOWN, piece1); // 4,1
		puzzle1.tryMove(KeyEvent.VK_DOWN, piece1); // 5,1
		assertTrue(puzzle1.isSolved());
	}
	
	@Test
	void testMovementCount() {
		assertEquals(model1.getNumMoves(), 0);
		puzzle1.tryMove(KeyEvent.VK_LEFT, piece1);
		assertEquals(model1.getNumMoves(), 1);
		puzzle1.tryMove(KeyEvent.VK_LEFT, piece1);
		assertEquals(model1.getNumMoves(), 1); // invalid move
		puzzle1.tryMove(KeyEvent.VK_UP, piece1);
		assertEquals(model1.getNumMoves(), 2);
	}
	
	@Test
	void testPieceColor() {
		assertEquals(piece1.getPieceColor(), Color.RED.darker());
		assertEquals(piece2.getPieceColor(), Color.GRAY.darker());
		assertEquals(piece3.getPieceColor(), Color.GRAY.darker());
		assertEquals(piece4.getPieceColor(), Color.GRAY.darker());
	}
	
	@Test
	void testToggleSelect() {
		assertTrue(piece1.isSelected);
		piece1.togglePieceSelect(false);
		assertFalse(piece1.isSelected);
	}
	
	@Test
	void testNotValidPieceType() {
		try {
			new Piece(model1, "Banana", new Coordinate(1, 1));
			fail("Should throw an exception");
		} catch (Exception e) {
			assertTrue(true);
		}
	}

}
