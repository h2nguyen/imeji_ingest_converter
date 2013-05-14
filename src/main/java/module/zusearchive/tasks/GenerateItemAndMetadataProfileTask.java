package module.zusearchive.tasks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.xml.bind.JAXBException;

import module.zusearchive.jaxb.JaxbZuseProfile;
import module.zusearchive.vo.generated.ZUSE;

import org.xml.sax.SAXException;


public class GenerateItemAndMetadataProfileTask extends SwingWorker<String[], Void> {

	protected String inputFilename;
	protected String itemOutputFilename;
	protected String mdProfileOutputFilename;
	protected JProgressBar progressBar;
	protected JLabel label;
	
	protected boolean stopFlag;
	protected Timer timer;
	protected int counter;
	
	public GenerateItemAndMetadataProfileTask(String inputFilename, JProgressBar progressBar, JLabel lblNotification) {		
		this(inputFilename, getOutputFilenameItemize(inputFilename), getOutputFilenameMdProfile(inputFilename), progressBar, lblNotification);
	}

	public GenerateItemAndMetadataProfileTask(String inputFilename, String itemOutputFilename, String mdProfileOutputFilename, JProgressBar progressBar, JLabel lblNotification) {
		this.inputFilename = inputFilename;
		this.itemOutputFilename = itemOutputFilename;
		this.mdProfileOutputFilename = mdProfileOutputFilename;
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

	private static String getOutputFilenameItemize(String inputFilename) {
		int i = inputFilename.lastIndexOf('.');
		if(i < 0)
			return inputFilename + "_itemized";
		return inputFilename.substring(0, i) + "_itemized" + inputFilename.substring(i);		
	}
	
	private static String getOutputFilenameMdProfile(String inputFilename) {
		int i = inputFilename.lastIndexOf('.');
		if(i < 0)
			return inputFilename + "_mdProfiled";
		return inputFilename.substring(0, i) + "_mdProfiled" + inputFilename.substring(i);		
	}
	

	@Override
	protected String[] doInBackground() throws Exception {

		JaxbZuseProfile jmp = new JaxbZuseProfile();
		
		ZUSE zo = null;
		ItemAndMdProfileConverterTask imdpct = null;
		try {			
			this.timer.start();
			zo = jmp.unmarshalZuseObject(this.inputFilename);
			this.stop();
			
			imdpct = new ItemAndMdProfileConverterTask(this.inputFilename, zo, progressBar, label);
			imdpct.execute();			
			
		} catch (JAXBException e1) {
			this.label.setText("Invalid XML file");
		} catch (SAXException e1) { 
			this.label.setText("Invalid XML file");
		}
		
		return imdpct.get();
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
