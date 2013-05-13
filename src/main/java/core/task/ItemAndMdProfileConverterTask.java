/**
 * 
 */
package main.java.core.task;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;


import main.java.core.converter.MetadataProfileConverter;
import main.java.core.helper.ZuseMdProfile;
import main.java.core.jaxb.JaxbIngestProfile;
import main.java.core.vo.Item;
import main.java.core.vo.Items;
import main.java.core.vo.Metadata;
import main.java.core.vo.MetadataProfile;
import main.java.core.vo.MetadataSet;
import main.java.core.vo.Item.Visibility;
import main.java.core.vo.Properties.Status;
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


/**
 * @author hnguyen
 *
 */
public class ItemAndMdProfileConverterTask extends SwingWorker<String[],Void>{

	protected JProgressBar progressBar;
	protected JLabel label;
	
	protected List<Item> items;
	protected ZUSE zo;
	protected String inputFilename;
	protected String itemOutputFilename;
	protected String mdProfileOutputFilename;
	protected boolean stopFlag;
	
	public ItemAndMdProfileConverterTask(String inputFilename, ZUSE zo, JProgressBar progressBar, JLabel label) {
		this(inputFilename,getOutputFilenameItemize(inputFilename),getOutputFilenameMdProfile(inputFilename),zo,progressBar,label);		
	}
	
	public ItemAndMdProfileConverterTask(String inputFilename, String itemOutputFilename, String mdProfileOutputFilename, ZUSE zo, JProgressBar progressBar, JLabel label) {	
		this.zo = zo;		
		this.progressBar = progressBar;
		this.label = label;
		this.inputFilename = inputFilename;
		this.itemOutputFilename = itemOutputFilename;
		this.mdProfileOutputFilename = mdProfileOutputFilename;
		
		init();
	}

	
	protected void init() {
		this.items = new ArrayList<Item>();
		this.stopFlag = false;
	}


	public static URI getNewMetadataURI() {
		return URI.create("http://zuse.zib.de/terms/metadata/" + UUID.randomUUID());
	}
	
	public static List<Item> convertFromZuseMdProfile(List<OUnterlagen> ouls) throws IntrospectionException {
		List<Item> items = new ArrayList<Item>();
		for (OUnterlagen oul : ouls) {
			items.add(ItemAndMdProfileConverterTask.convertFromZuseMdProfile(oul));
		}		
		return items;
	}
	
	public static Item convertFromZuseMdProfile(OUnterlagen oul) throws IntrospectionException {
		
		Item item = new Item();
		
		item.setCreated(Calendar.getInstance());		
		
		List<MetadataSet> mdsl = new ArrayList<MetadataSet>();
		MetadataSet mds = new MetadataSet();
		Collection<Metadata> mdl = new ArrayList<Metadata>();

		List<?> z = (List<?>) ZuseMdProfile.enum2list(ZuseMdProfile.class);
		int counter = 0;		
		for(PropertyDescriptor propertyDescriptor : Introspector.getBeanInfo(oul.getClass()).getPropertyDescriptors()){
			if(propertyDescriptor.getReadMethod().getReturnType() != String.class)
				continue;
			
			for (int i = 0; i < z.size(); i++) {
				String method = ((ZuseMdProfile) z.get(i)).getAttributes()[ZuseMdProfile.Column.METHOD_NAME.ordinal()];
				if(propertyDescriptor.getReadMethod().getName().contains(method)) {
					
					String type = ((ZuseMdProfile) z.get(i)).getAttributes()[ZuseMdProfile.Column.TYPE.ordinal()];
					if(type.isEmpty())
						continue;

					Metadata.Types aType = Enum.valueOf(Metadata.Types.class, type.toUpperCase());					
					switch(aType) {
						case TEXT:
							Text text = new Text();
							String value = (String) oul.getValueFromMethod(propertyDescriptor.getReadMethod().getName());
							text.setText(value);
							text.setStatement(URI.create(((ZuseMdProfile) z.get(i)).getAttributes()[ZuseMdProfile.Column.STATEMENT_ID.ordinal()]));
							text.setPos(counter++);
							mdl.add(text);
							break;
							
						case CONE_PERSON:
							ConePerson conePerson = new ConePerson();
							mdl.add(conePerson);
							break;
							
						case DATE:
							Date date = new Date();
							mdl.add(date);
							break;
							
						case GEOLOCATION:
							Geolocation geolocation = new Geolocation();
							mdl.add(geolocation);
							break;
							
						case LICENSE:
							License license = new License();
							mdl.add(license);
							break;
							
						case LINK:
							Link link = new Link();
							mdl.add(link);
							break;
							
						case NUMBER:
							Number number = new Number();
							mdl.add(number);
							break;
							
						case PUBLICATION:
							Publication publication = new Publication();
							mdl.add(publication);
							break;
							
						default:
							Text defaultTxt = new Text();
							mdl.add(defaultTxt);
							break;
					}
					break;
				}
			}
		}

		mds.setMetadata(mdl);		
		mdsl.add(mds);
		item.setMetadataSets(mdsl);
		
		return item;
	}
	
	public static Item convertFromZuseMdProfile(OUnterlagen oul, MetadataProfile mdProfile) throws IntrospectionException {
		
		Item item = new Item();
		
		item.setCollection(mdProfile.getId());
		item.setCreated(mdProfile.getCreated());
		item.setCreatedBy(mdProfile.getCreatedBy());
		item.setDiscardComment(mdProfile.getDiscardComment());
		item.setEscidocId("not available");
		
		String filename = "DMA_";
		String bestand = "";
		String signatur = "";
		String filenamePosfix = ".jpg";
		
		item.setFullImageUrl(URI.create("http://not.available.yet"));
		item.setFulltextIndex("not available");
		item.setModified(mdProfile.getModified());
		item.setModifiedBy(mdProfile.getModifiedBy());
		item.setStatus(mdProfile.getStatus());
		item.setThumbnailImageUrl(URI.create("http://not.available.yet"));
		item.setVersion(mdProfile.getVersion());
		item.setVersionDate(mdProfile.getVersionDate());
		item.setVisibility(Visibility.PRIVATE);
		item.setWebImageUrl(URI.create("http://not.available.yet"));
		
		List<MetadataSet> mdsl = new ArrayList<MetadataSet>();
		MetadataSet mds = new MetadataSet();
		Collection<Metadata> mdl = new ArrayList<Metadata>();

		
		Collection<Statement> sts = mdProfile.getStatements();
		Iterator<Statement> itSts = sts.iterator();
		
		
		
		List<?> z = (List<?>) ZuseMdProfile.enum2list(ZuseMdProfile.class);
		int counter = 0;		
		for(PropertyDescriptor propertyDescriptor : Introspector.getBeanInfo(oul.getClass()).getPropertyDescriptors()){
			if(propertyDescriptor.getReadMethod().getReturnType() != String.class)
				continue;
			
			for (int i = 0; i < z.size(); i++) {
				String method = ((ZuseMdProfile) z.get(i)).getAttributes()[ZuseMdProfile.Column.METHOD_NAME.ordinal()];
				if(propertyDescriptor.getReadMethod().getName().contains(method)) {
					
					String type = ((ZuseMdProfile) z.get(i)).getAttributes()[ZuseMdProfile.Column.TYPE.ordinal()];
					if(type.isEmpty())
						continue;

					Metadata.Types aType = Enum.valueOf(Metadata.Types.class, type.toUpperCase());					
					switch(aType) {
						case TEXT:
							Text text = new Text();
							String tag = propertyDescriptor.getReadMethod().getName();
							String value = (String) oul.getValueFromMethod(tag);
							text.setText(value);
							//TODO: big problem here for the statement matching!!!!
							
							text.setStatement(itSts.next().getId());							
							text.setPos(counter++);
							mdl.add(text);
							
							//DMA_<bestand>_<signatur>.jpg
							//getBestand, getSignatur
							
							if(tag.equalsIgnoreCase("getBestand")) {
								bestand = value;
							}
							
							if(tag.equalsIgnoreCase("getSignatur")) {
								signatur = value;
							}
							
							break;
							
						case CONE_PERSON:
							ConePerson conePerson = new ConePerson();
							mdl.add(conePerson);
							break;
							
						case DATE:
							Date date = new Date();
							mdl.add(date);
							break;
							
						case GEOLOCATION:
							Geolocation geolocation = new Geolocation();
							mdl.add(geolocation);
							break;
							
						case LICENSE:
							License license = new License();
							mdl.add(license);
							break;
							
						case LINK:
							Link link = new Link();
							mdl.add(link);
							break;
							
						case NUMBER:
							Number number = new Number();
							mdl.add(number);
							break;
							
						case PUBLICATION:
							Publication publication = new Publication();
							mdl.add(publication);
							break;
							
						default:
							Text defaultTxt = new Text();
							mdl.add(defaultTxt);
							break;
					}
					break;
				}
			}
		}

		mds.setMetadata(mdl);		
		mdsl.add(mds);
		item.setMetadataSets(mdsl);
		
		//DMA_<bestand>_<signatur>.jpg
		bestand = bestand.replace(" ", "_").replace("/", "_");
		signatur = signatur.replace(" ", "_").replace("/", "_");
		
		filename += bestand + "_" + signatur + filenamePosfix; 
		item.setFilename(filename);
		
		return item;
	}

	@Override
	protected String[] doInBackground() throws Exception {
		this.items.clear();
		
		MetadataProfile mdp = null;
		for (int i = 0; i < zo.getoUnterlagen().size() && !this.stopFlag; i++) {
			if(mdp == null)
				mdp = MetadataProfileConverter.convertFromZuseMdProfile(zo.getoUnterlagen().get(i), "", "");
			
			items.add(ItemAndMdProfileConverterTask.convertFromZuseMdProfile(zo.getoUnterlagen().get(i),mdp));
			
			int val = (int) (100 * (double)(i+1) / zo.getoUnterlagen().size());
			this.progressBar.setValue(val);
			this.label.setText("Converting items job: "+ val + " % done!" );
		}
		
		new JaxbIngestProfile().marshalItems(this.itemOutputFilename, new Items(items));
		new JaxbIngestProfile().marshalMdProfile(this.mdProfileOutputFilename, mdp);
		
		this.stop();
		
		String[] outputFilename = new String[2];
		
		outputFilename[0] = this.itemOutputFilename;
		outputFilename[1] = this.mdProfileOutputFilename;
		
		return outputFilename;
	}

	public void done() {
	    this.progressBar.setValue(progressBar.getMinimum());
	    this.stopFlag = false;
	    this.label.setText("Item file generated!" );		
	}

	protected void stop() {
		this.stopFlag = true;
		this.progressBar.setValue(this.progressBar.getMinimum());		
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
}
