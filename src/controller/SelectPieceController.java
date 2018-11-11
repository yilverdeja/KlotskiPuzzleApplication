package controller;

import java.awt.Point;

import model.Model;
import model.Piece;
import model.Puzzle;
import view.KlotskiApplication;

public class SelectPieceController{
	
	KlotskiApplication app;
	Model model;
	Point mouseCursor;
	
	public SelectPieceController(Point mouseCursor, Model model, KlotskiApplication app) {
		this.app = app;
		this.model = model;
		this.mouseCursor = mouseCursor;
	}
	
	public void selectPiece() {
		Puzzle puzzle = model.getPuzzle();
		System.out.println("Mouse at x: "+mouseCursor.getX()+"; y: "+mouseCursor.getY());
		Piece selectedPiece = puzzle.getPieceAtPosition((int)mouseCursor.getX(), (int)mouseCursor.getY());
		if (selectedPiece == null) return;
		
		puzzle.selectPiece(selectedPiece);
		
		app.getPuzzleView().refresh();
	}
	
}
