package main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import model.Model;
import view.KlotskiApplication;

public class Main {

	public static void main(String[] args) {
		Model model = new Model();
		final KlotskiApplication app = new KlotskiApplication(model);
		
		app.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
//				if (new QuitController().confirm(app)) {
					app.dispose();
//				}
			}
		});
		
		app.setVisible(true);
		
	}

}
