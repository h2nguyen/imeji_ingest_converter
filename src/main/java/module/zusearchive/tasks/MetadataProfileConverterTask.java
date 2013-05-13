package module.zusearchive.tasks;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import module.zusearchive.helper.ZuseMdProfile;
import module.zusearchive.vo.generated.OUnterlagen;
import module.zusearchive.vo.generated.ZUSE;
import core.j2j.misc.LocalizedString;
import core.jaxb.JaxbIngestProfile;
import core.vo.Item;
import core.vo.Metadata;
import core.vo.MetadataProfile;
import core.vo.Statement;

public class MetadataProfileConverterTask extends SwingWorker<String, Void> {

	protected JProgressBar progressBar;
	protected JLabel label;
	
	protected List<Item> items;
	protected ZUSE zo;
	protected String inputFilename;
	protected String outputFilename;
	protected boolean stopFlag;
	
	public MetadataProfileConverterTask(String inputFilename, ZUSE zo, JProgressBar progressBar, JLabel label) {
		this(inputFilename,getOutputFilename(inputFilename),zo,progressBar,label);		
	}
	
	public MetadataProfileConverterTask(String inputFilename, String outputFilename, ZUSE zo, JProgressBar progressBar, JLabel label) {	
		this.zo = zo;		
		this.progressBar = progressBar;
		this.label = label;
		this.inputFilename = inputFilename;
		this.outputFilename = outputFilename;
		
		init();
	}

	
	protected void init() {
		this.items = new ArrayList<Item>();
		this.stopFlag = false;
	}
	
	private static String getOutputFilename(String inputFilename) {
		int i = inputFilename.lastIndexOf('.');
		if(i < 0)
			return inputFilename + "_mdProfiled";
		return inputFilename.substring(0, i) + "_mdProfiled" + inputFilename.substring(i);		
	}
	
	public static URI getNewStatementURI() {		
		return URI.create("http://zuse.zib.de/statement/" + UUID.randomUUID());
	}
	
	public static MetadataProfile convertFromZuseMdProfile(OUnterlagen oul, String title, String description) throws IntrospectionException {
		MetadataProfile mdp = new MetadataProfile();
		mdp.setDescription("description");
		mdp.setTitle("title");		
		
		Collection<Statement> statements = new LinkedList<Statement>();
		
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
					
					Statement statement = new Statement();
					statement.setId(URI.create(((ZuseMdProfile) z.get(i)).getAttributes()[ZuseMdProfile.Column.STATEMENT_ID.ordinal()]));
					String labelDE = ((ZuseMdProfile) z.get(i)).getAttributes()[ZuseMdProfile.Column.DE_LABEL.ordinal()];
					String labelEN = ((ZuseMdProfile) z.get(i)).getAttributes()[ZuseMdProfile.Column.EN_LABEL.ordinal()];					
					statement.getLabels().add(new LocalizedString(labelDE, "de"));
					statement.getLabels().add(new LocalizedString(labelEN, "en"));
					statement.setPos(counter++);										
					
					Metadata.Types aType = Enum.valueOf(Metadata.Types.class, type.toUpperCase());					
					switch(aType) {
						case TEXT:							
							statements.add(statement);
							break;
							
						case CONE_PERSON:
							statements.add(statement);
							break;
							
						case DATE:
							statements.add(statement);
							break;
							
						case GEOLOCATION:
							statements.add(statement);
							break;
							
						case LICENSE:
							statements.add(statement);
							break;
							
						case LINK:
							statements.add(statement);
							break;
							
						case NUMBER:
							statements.add(statement);
							break;
							
						case PUBLICATION:
							statements.add(statement);
							break;
							
						default:
							statements.add(statement);
							break;
					}
					break;
				}
			}
		}
		
		
		mdp.setStatements(statements);
		
		return mdp;
	}
	
	
	public static URI getMetadataProfileURI() {
		return URI.create("http://zuse.zib.de/terms/metadata/" + UUID.randomUUID());		
	}

	@Override
	protected String doInBackground() throws Exception {
				
		MetadataProfile mdp = null;

		for (int i = 0; i < zo.getoUnterlagen().size() && !this.stopFlag; i++) {			
			mdp = MetadataProfileConverterTask.convertFromZuseMdProfile(zo.getoUnterlagen().get(0), "Collection title", "Collection description");
			int val = (int) (100 * (double)(i+1) / zo.getoUnterlagen().size());
			this.progressBar.setValue(val);
			this.label.setText("Converting items job: "+ val + " % done!" );
			
			//TODO: you can implement here for other profiles, like oFoto and so
			
			// do only for one profile
			break;
		}
		
		this.stop();
		
		if(mdp == null) {
			return "";
		}
		
		new JaxbIngestProfile().marshalMdProfile(this.outputFilename, mdp);
		
		
		
		return this.outputFilename;
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
}
