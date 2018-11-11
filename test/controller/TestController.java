package controller;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.awt.event.KeyEvent;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Coordinate;
import model.Model;
import model.Piece;
import model.Puzzle;
import view.KlotskiApplication;

class TestController {
	
	Model model;
	Puzzle puzzle;
	Piece piece;
	KlotskiApplication app;
	

	@BeforeEach
	void setUp() throws Exception {
		model = new Model(1, new Coordinate(0,0));
		app = new KlotskiApplication(model);
		puzzle = model.getPuzzle();
		piece = puzzle.getPieces().get(0);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testMovePieceController() {
		new MovePieceController(model, app, KeyEvent.VK_LEFT).tryMove();
		puzzle.selectPiece(piece);
		new MovePieceController(model, app, KeyEvent.VK_LEFT).tryMove();
		new MovePieceController(model, app, KeyEvent.VK_RIGHT).tryMove();
		assertTrue(piece.getCoord().equals(new Coordinate(1,0)));
	}
	
	@Test
	void testSelectPieceController() {
		puzzle.tryMove(KeyEvent.VK_DOWN, piece);
		new SelectPieceController(new Point(1, 1), model, app).selectPiece();
		puzzle.tryMove(KeyEvent.VK_DOWN, piece);
		assertTrue(piece.getCoord().equals(new Coordinate(0,1)));
	}
	
	@Test
	void testResetPuzzleController() {
		Model m;
		try {
			m = new Model(1, new Coordinate(1,3));
			KlotskiApplication a = new KlotskiApplication(m);
			Puzzle puz = m.getPuzzle();
			Piece pic = puz.getPieces().get(0);
			
			pic.togglePieceSelect(true);
			puz.tryMove(KeyEvent.VK_DOWN, pic);
			puz.tryMove(KeyEvent.VK_DOWN, pic);
			
			assertEquals(m.getNumMoves(), 2);
			new ResetPuzzleController().resetPuzzle(m, a);
			assertEquals(m.getNumMoves(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
