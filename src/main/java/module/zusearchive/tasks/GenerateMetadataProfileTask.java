package module.zusearchive.tasks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.xml.bind.JAXBException;

import module.zusearchive.vo.generated.OZuse;

import org.xml.sax.SAXException;

import core.jaxb.JaxbGenericObject;


public class GenerateMetadataProfileTask extends SwingWorker<String, Void> {

	protected String inputFilename;
	protected String outputFilename;
	protected JProgressBar progressBar;
	protected JLabel label;
	
	protected boolean stopFlag;
	protected Timer timer;
	protected int counter;
	
	public GenerateMetadataProfileTask(String inputFilename, JProgressBar progressBar, JLabel lblNotification) {		
		this(inputFilename, getOutputFilename(inputFilename), progressBar, lblNotification);
	}

	public GenerateMetadataProfileTask(String inputFilename, String outputFilename, JProgressBar progressBar, JLabel lblNotification) {
		this.inputFilename = inputFilename;
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
			return inputFilename + "_mdProfiled";
		return inputFilename.substring(0, i) + "_mdProfiled" + inputFilename.substring(i);		
	}
	

	@Override
	protected String doInBackground() throws Exception {

		JaxbGenericObject<OZuse> jmp = new JaxbGenericObject<OZuse>(OZuse.class);
		
		OZuse zo = null;
		try {			
			this.timer.start();
			zo = jmp.unmarshal(this.inputFilename);
			this.stop();
			
			MetadataProfileConverterTask mct = new MetadataProfileConverterTask(this.inputFilename, zo, progressBar, label);
			mct.execute();
			
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
