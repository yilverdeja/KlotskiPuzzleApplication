package controller;

import javax.swing.JOptionPane;

import model.Model;
import view.KlotskiApplication;

public class ResetPuzzleController {
	private boolean confirm(KlotskiApplication app){
		return JOptionPane.showConfirmDialog(app, "Do you wish to reset the puzzle?") == JOptionPane.OK_OPTION;
	}
	
	public void resetPuzzle(Model model, KlotskiApplication app) {
		if(model.getPuzzle().isSolved() || confirm(app)) {
			boolean reset = model.reset();
			if (reset) System.out.println("Board was reset");
			app.getMoveCounter().setText("0");
			app.getPuzzleView().refresh();
		}
	}
}
