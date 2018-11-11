package main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import controller.QuitController;
import model.Model;
import view.KlotskiApplication;

/**
 * This class creates a model, and opens the application. When the application is closed, 
 * a confirmation window is opened and prompts the user of the request.
 * @author Yil Verdeja
 *
 */
public class Main {
	
	/**
	 * main method to start the program
	 * @param args
	 */
	public static void main(String[] args) {
		Model model = new Model();
		final KlotskiApplication app = new KlotskiApplication(model);
		
		app.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				if (new QuitController().confirm(app)) {
					app.dispose();
				}
			}
		});
		
		app.setVisible(true);
		
	}

}
