/**
 * 
 */
package main.java.core.task;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

import main.java.core.helper.ZuseMdProfile;
import main.java.core.j2j.misc.LocalizedString;
import main.java.core.jaxb.JaxbIngestProfile;
import main.java.core.vo.Item;
import main.java.core.vo.Items;
import main.java.core.vo.Metadata;
import main.java.core.vo.MetadataProfile;
import main.java.core.vo.MetadataSet;
import main.java.core.vo.Item.Visibility;
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
public class ItemWithProfileConverterTask extends ItemConverterTask {

	private MetadataProfile mdp;
	
	public ItemWithProfileConverterTask(String inputFilename, ZUSE zo, MetadataProfile mdp, JProgressBar progressBar, JLabel label) {
		this(inputFilename,getOutputFilename(inputFilename),zo,mdp,progressBar,label);		
	}
	
	public ItemWithProfileConverterTask(String inputFilename, String outputFilename, ZUSE zo, MetadataProfile mdp, JProgressBar progressBar, JLabel label) {
		super(inputFilename,outputFilename,zo,progressBar,label);
		this.mdp = mdp;
	}
	
	private static String getOutputFilename(String inputFilename) {
		int i = inputFilename.lastIndexOf('.');
		if(i < 0)
			return inputFilename + "_itemized_with_profile";
		return inputFilename.substring(0, i) + "_itemized_with_profile" + inputFilename.substring(i);		
	}
	
	
	@Override
	protected String doInBackground() throws Exception {
		this.items.clear();
		
		for (int i = 0; i < zo.getoUnterlagen().size() && !this.stopFlag; i++) {

			items.add(ItemWithProfileConverterTask.convertFromZuseMdProfile(zo.getoUnterlagen().get(i),this.mdp));
			
			int val = (int) (100 * (double)(i+1) / zo.getoUnterlagen().size());
			this.progressBar.setValue(val);
			this.label.setText("Converting items job: "+ val + " % done!" );
		}
		
		new JaxbIngestProfile().marshalItems(this.outputFilename, new Items(items));
		
		this.stop();
		return this.outputFilename;
	}

	public static Item convertFromZuseMdProfile(OUnterlagen oul, MetadataProfile mdProfile) throws IntrospectionException {
		
		Item item = new Item();
		
		item.setCreated(Calendar.getInstance());
		item.setCreatedBy(mdProfile.getCreatedBy());
		item.setDiscardComment(mdProfile.getDescription());
		item.setModified(mdProfile.getModified());
		item.setModifiedBy(mdProfile.getCreatedBy());
		item.setStatus(mdProfile.getStatus());
		item.setVersion(mdProfile.getVersion());
		item.setVersionDate(mdProfile.getVersionDate());
		item.setCollection(mdProfile.getId());
		
		String filename = "DMA_";
		String bestand = "";
		String signatur = "";
		String filenamePosfix = ".jpg";
		
		item.setFullImageUrl(URI.create("http://no.full-image.set/yet"));
		item.setFulltextIndex("initial full text index");
		
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
							String tag = propertyDescriptor.getReadMethod().getName();
							String value = (String) oul.getValueFromMethod(tag);
							text.setText(value);
							
							text.setStatement(URI.create(((ZuseMdProfile) z.get(i)).getAttributes()[ZuseMdProfile.Column.STATEMENT_ID.ordinal()]));							
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
		item.setThumbnailImageUrl(URI.create("http://no.thumbnail-image.url/set/yet"));
		item.setVisibility(Visibility.PRIVATE);
		item.setWebImageUrl(URI.create("http://no.wev-image.url/set/yet"));
		
		//DMA_<bestand>_<signatur>.jpg
		bestand = bestand.replace(" ", "_").replace("/", "_");
		signatur = signatur.replace(" ", "_").replace("/", "_");
		
		filename += bestand + "_" + signatur + filenamePosfix; 
		item.setFilename(filename);
		
		return item;
	}
}
