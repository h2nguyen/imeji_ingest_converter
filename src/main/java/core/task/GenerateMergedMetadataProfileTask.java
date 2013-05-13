/**
 * 
 */
package main.java.core.task;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.xml.bind.JAXBException;

import main.java.core.jaxb.JaxbIngestProfileZuse;
import main.java.core.vo.MetadataProfile;
import main.java.core.vo.generated.ZUSE;

import org.xml.sax.SAXException;

/**
 * @author hnguyen
 *
 */
public class GenerateMergedMetadataProfileTask extends SwingWorker<String,Void> {

	protected String inputFilename1;
	protected String inputFilename2;
	protected String outputFilename;
	protected JProgressBar progressBar;
	protected JLabel label;
	
	protected boolean stopFlag;
	protected Timer timer;
	protected int counter;
	
	public GenerateMergedMetadataProfileTask(String inputFilename1, String inputFilename2, JProgressBar progressBar, JLabel lblNotification) {		
		this(inputFilename1, inputFilename2 , getOutputFilename(inputFilename1), progressBar, lblNotification);
	}

	public GenerateMergedMetadataProfileTask(String inputFilename1, String inputFilename2, String outputFilename, JProgressBar progressBar, JLabel lblNotification) {
		this.inputFilename1 = inputFilename1;
		this.inputFilename2 = inputFilename2;
		this.outputFilename = outputFilename;
		this.progressBar = progressBar;
		this.label = lblNotification;
		init();
	}
	
	private void init() {
		this.stopFlag = false;		
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				progressBar.setValue(counter++ % progressBar.getMaximum());
			}
		};
		
		this.timer = new Timer(10 /*millisecond*/, taskPerformer);
		this.counter = 0;
	}

	private static String getOutputFilename(String inputFilename) {
		int i = inputFilename.lastIndexOf('.');
		if(i < 0)
			return inputFilename + "_mdMerged";
		return inputFilename.substring(0, i) + "_mdMerged" + inputFilename.substring(i);		
	}
	

	@Override
	protected String doInBackground() throws Exception {

		JaxbIngestProfileZuse jmp = new JaxbIngestProfileZuse();
		
		MetadataProfile offline = null;
		MetadataProfile online  = null;
		try {			
			this.timer.start();
			offline = jmp.unmarshalMdProfile(this.inputFilename1);
			online  = jmp.unmarshalMdProfile(this.inputFilename2);
			this.stop();
			
			MetadataProfilesMergerTask mdmt = new MetadataProfilesMergerTask(this.outputFilename, offline, online, progressBar, label);
			mdmt.execute();
			
		} catch (JAXBException e1) {
			this.label.setText("Invalid XML file");
		} catch (SAXException e1) { 
			this.label.setText("Invalid XML file");
		}
		
		return this.outputFilename;
	}
	
	

	public void stop() {
		this.stopFlag = true;
		if(this.timer.isRunning()) {
			this.timer.stop();
		}
		this.counter = 0;
		this.progressBar.setValue(this.progressBar.getMinimum());
	}

}
