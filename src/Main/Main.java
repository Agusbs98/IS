package Main;

import javax.swing.SwingUtilities;

import Controller.Controller;
import views.MainWindow;

public class Main {
	
	private static void initGUI()throws Exception{
		Controller ctrl = new Controller();
		SwingUtilities.invokeAndWait(new Runnable() {
			@Override
			public void run() {
				new MainWindow(ctrl);
			}
		});
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		initGUI();
		}catch(Exception e) {
			System.err.println("Something went wrong ...");
			System.err.println();
			e.printStackTrace();
		}
	}

}

