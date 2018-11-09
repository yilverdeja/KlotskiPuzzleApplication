package controller;

import javax.swing.JOptionPane;

import view.KlotskiApplication;

public class QuitController {
	public boolean confirm(KlotskiApplication app) {
		return JOptionPane.showConfirmDialog(app, "Do you wish to exit the application?") == JOptionPane.OK_OPTION;
	}
}
