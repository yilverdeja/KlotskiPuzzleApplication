package controller;

import java.awt.Point;

import model.Model;
import model.Piece;
import model.Puzzle;
import view.PuzzleView;

public class SelectPieceController{
	
	PuzzleView puzzleView;
	Model model;
	Point mouseCursor;
	
	public SelectPieceController(Point mouseCursor, Model model, PuzzleView puzzleView) {
		this.puzzleView = puzzleView;
		this.model = model;
		this.mouseCursor = mouseCursor;
	}
	
	public void selectPiece() {
		Puzzle puzzle = model.getPuzzle();
		
		Piece selectedPiece = puzzle.getPieceAtPosition((int)mouseCursor.getX(), (int)mouseCursor.getY());
		if (selectedPiece == null) return;
		
		puzzle.selectPiece(selectedPiece);
		
		puzzleView.refresh();
	}
	
}
