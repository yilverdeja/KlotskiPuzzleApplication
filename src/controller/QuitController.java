package controller;

import javax.swing.JOptionPane;

import view.KlotskiApplication;

/**
 * The Quit Controller handles opening a Confirmation window after the user requests to quit the application
 * @author Yil Verdeja
 *
 */
public class QuitController {
	public boolean confirm(KlotskiApplication app) {
		return JOptionPane.showConfirmDialog(app, "Do you wish to exit the application?") == JOptionPane.OK_OPTION;
	}
}
