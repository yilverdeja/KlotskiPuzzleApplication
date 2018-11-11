package controller;

import javax.swing.JOptionPane;

import model.Model;
import view.KlotskiApplication;

/**
 * The Reset Puzzle Controller handles a reset of the puzzle and the number of moves once the user requests to reset. The 
 * application opens a confirmation window after the users request to reset puzzle. It does not open a confirmation window 
 * if the puzzle is already solved.
 * @author Yil Verdeja
 *
 */
public class ResetPuzzleController {
	
	/**
	 * Opens a confirmation window to confirm if user wants to reset the puzzle
	 * @param app
	 * @return
	 */
	private boolean confirm(KlotskiApplication app){
		return JOptionPane.showConfirmDialog(app, "Do you wish to reset the puzzle?") == JOptionPane.OK_OPTION;
	}
	
	/**
	 * This method resets the model and changes the number of moves on the application JLabel to 0.
	 * @param model
	 * @param app
	 */
	public void resetPuzzle(Model model, KlotskiApplication app) {
		if(model.getPuzzle().isSolved() || confirm(app)) {
			boolean reset = model.reset();
			if (reset) System.out.println("Board was reset");
			app.getMoveCounter().setText("0");
			app.getPuzzleView().refresh();
		}
	}
}
