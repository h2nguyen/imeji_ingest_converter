package gui;
import gui.IngestFrame;

import java.awt.EventQueue;

import org.apache.log4j.BasicConfigurator;

public class IngestGUI {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args){
		System.setProperty("file.encoding", "UTF-8");
		BasicConfigurator.configure();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IngestFrame window = new IngestFrame();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	

}
