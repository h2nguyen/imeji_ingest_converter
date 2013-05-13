package main.java.core.task;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;

import main.java.core.helper.ZuseMdProfile;
import main.java.core.j2j.misc.LocalizedString;
import main.java.core.jaxb.JaxbIngestProfile;
import main.java.core.jaxb.JaxbIngestProfileZuse;
import main.java.core.vo.Item;
import main.java.core.vo.Items;
import main.java.core.vo.Metadata;
import main.java.core.vo.MetadataProfile;
import main.java.core.vo.Statement;
import main.java.core.vo.generated.OUnterlagen;
import main.java.core.vo.generated.ZUSE;
import main.java.core.vo.predefinedMetadata.ConePerson;
import main.java.core.vo.predefinedMetadata.Date;
import main.java.core.vo.predefinedMetadata.Geolocation;
import main.java.core.vo.predefinedMetadata.License;
import main.java.core.vo.predefinedMetadata.Link;
import main.java.core.vo.predefinedMetadata.Number;
import main.java.core.vo.predefinedMetadata.Publication;
import main.java.core.vo.predefinedMetadata.Text;

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

		JaxbIngestProfileZuse jmp = new JaxbIngestProfileZuse();
		
		ZUSE zo = null;
		try {			
			this.timer.start();
			zo = jmp.unmarshalZuseObject(this.inputFilename);
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
