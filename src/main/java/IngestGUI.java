package main.java;

import java.awt.EventQueue;

import main.java.gui.IngestFrame;

public class IngestGUI {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args){
		System.setProperty("file.encoding", "UTF-8");
		
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
