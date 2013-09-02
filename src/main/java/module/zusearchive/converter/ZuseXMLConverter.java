package module.zusearchive.converter;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import module.zusearchive.vo.generated.OUnterlagen;
import module.zusearchive.vo.generated.formats.enums.ZuseXMLMDEnumType;

import core.converter.Converter;
import core.j2j.misc.LocalizedString;
import core.vo.imeji.Item;
import core.vo.imeji.Items;
import core.vo.imeji.Metadata;
import core.vo.imeji.MetadataProfile;
import core.vo.imeji.MetadataSet;
import core.vo.imeji.Statement;
import core.vo.imeji.predefinedMetadata.ConePerson;
import core.vo.imeji.predefinedMetadata.Date;
import core.vo.imeji.predefinedMetadata.Geolocation;
import core.vo.imeji.predefinedMetadata.License;
import core.vo.imeji.predefinedMetadata.Link;
import core.vo.imeji.predefinedMetadata.Publication;
import core.vo.imeji.predefinedMetadata.Text;
import core.vo.imeji.predefinedMetadata.Number;

public class ZuseXMLConverter extends
		Converter<OUnterlagen, ZuseXMLMDEnumType> {

	@Override
	public MetadataProfile getMdProfile(OUnterlagen tObject, String title,
			String description,
			List<ZuseXMLMDEnumType> enumList)
			throws IntrospectionException {

		MetadataProfile mdp = new MetadataProfile();
		mdp.setDescription(description);
		mdp.setTitle(title);

		Collection<Statement> statements = new LinkedList<Statement>();
		List<?> z = enumList;
		int counter = 0;
		int methodeCounter = 0;
		try {
			for (PropertyDescriptor propertyDescriptor : Introspector
					.getBeanInfo(tObject.getClass()).getPropertyDescriptors()) {
				if (propertyDescriptor.getReadMethod().getReturnType() != String.class)
					continue;

				for (int i = methodeCounter; i < z.size(); i++) {
					String method = ((ZuseXMLMDEnumType) z.get(i))
							.getAttributes()[ZuseXMLMDEnumType.Column.METHOD_NAME
							.ordinal()];

					if (propertyDescriptor.getReadMethod().getName()
							.contains(method)) {

						String type = ((ZuseXMLMDEnumType) z
								.get(i)).getAttributes()[ZuseXMLMDEnumType.Column.TYPE
								.ordinal()];
						if (type.isEmpty()) {
							methodeCounter = i+1;
							continue;
						}
							

						Statement statement = new Statement();
						statement
								.setId(URI
										.create(((ZuseXMLMDEnumType) z
												.get(i)).getAttributes()[ZuseXMLMDEnumType.Column.STATEMENT_ID
												.ordinal()]));
						String labelDE = ((ZuseXMLMDEnumType) z
								.get(i)).getAttributes()[ZuseXMLMDEnumType.Column.DE_LABEL
								.ordinal()];
						String labelEN = ((ZuseXMLMDEnumType) z
								.get(i)).getAttributes()[ZuseXMLMDEnumType.Column.EN_LABEL
								.ordinal()];
						statement.getLabels().add(
								new LocalizedString(labelDE, "de"));
						statement.getLabels().add(
								new LocalizedString(labelEN, "en"));
						statement.setPos(counter++);

						Metadata.Types aType = Enum.valueOf(
								Metadata.Types.class, type.toUpperCase());
						switch (aType) {
						case TEXT:
							statements.add(statement);
							methodeCounter = i+1;
							break;

						case CONE_PERSON:
							statements.add(statement);
							methodeCounter = i+1;
							break;

						case DATE:
							statements.add(statement);
							methodeCounter = i+1;
							break;

						case GEOLOCATION:
							statements.add(statement);
							methodeCounter = i+1;
							break;

						case LICENSE:
							statements.add(statement);
							methodeCounter = i+1;
							break;

						case LINK:
							statements.add(statement);
							methodeCounter = i+1;
							break;

						case NUMBER:
							statements.add(statement);
							methodeCounter = i+1;
							break;

						case PUBLICATION:
							statements.add(statement);
							methodeCounter = i+1;
							break;

						default:
							statements.add(statement);
							methodeCounter = i+1;
							break;
						}
						break;
					}
				}
			}
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mdp.setStatements(statements);

		return mdp;
	}

	@Override
	public Item getItem(OUnterlagen tObject,
			List<ZuseXMLMDEnumType> enumList)
			throws IntrospectionException {
		Item item = new Item();

		item.setCreated(Calendar.getInstance());

		String filename = "DMA_";
		String bestand = "";
		String signatur = "";
		String filenamePosfix = ".jpg";

		List<MetadataSet> mdsl = new ArrayList<MetadataSet>();
		MetadataSet mds = new MetadataSet();
		Collection<Metadata> mdl = new ArrayList<Metadata>();

		List<?> z = enumList;

		int counter = 0;
		for (PropertyDescriptor propertyDescriptor : Introspector.getBeanInfo(
				tObject.getClass()).getPropertyDescriptors()) {
			if (propertyDescriptor.getReadMethod().getReturnType() != String.class)
				continue;

			for (int i = 0; i < z.size(); i++) {
				String method = ((ZuseXMLMDEnumType) z.get(i))
						.getAttributes()[ZuseXMLMDEnumType.Column.METHOD_NAME
						.ordinal()];
				if (propertyDescriptor.getReadMethod().getName()
						.contains(method)) {

					String type = ((ZuseXMLMDEnumType) z.get(i))
							.getAttributes()[ZuseXMLMDEnumType.Column.TYPE
							.ordinal()];
					if (type.isEmpty())
						continue;

					Metadata.Types aType = Enum.valueOf(Metadata.Types.class,
							type.toUpperCase());
					switch (aType) {
					case TEXT:

						Text text = new Text();
						String tag = propertyDescriptor.getReadMethod()
								.getName();
						String value = (String) tObject.getValueFromMethod(tag);
						text.setText(value);
						// TODO: big problem here for the statement matching!!!!

						text.setStatement(URI.create(((ZuseXMLMDEnumType) z
								.get(i)).getAttributes()[ZuseXMLMDEnumType.Column.STATEMENT_ID
								.ordinal()]));
						text.setPos(counter++);
						mdl.add(text);

						// DMA_<bestand>_<signatur>.jpg
						// getBestand, getSignatur

						if (tag.equalsIgnoreCase("getBestand")) {
							bestand = value;
						}

						if (tag.equalsIgnoreCase("getSignatur")) {
							signatur = value;
						}

						break;

					case CONE_PERSON:
						
						//TODO: need implementation of merging this metadata
						ConePerson conePerson = new ConePerson();
						mdl.add(conePerson);
						break;

					case DATE:
						
						//TODO: need implementation of merging this metadata
						Date date = new Date();
						mdl.add(date);
						break;

					case GEOLOCATION:
						
						//TODO: need implementation of merging this metadata
						Geolocation geolocation = new Geolocation();
						mdl.add(geolocation);
						break;

					case LICENSE:
						
						//TODO: need implementation of merging this metadata
						License license = new License();
						mdl.add(license);
						break;

					case LINK:
						
						//TODO: need implementation of merging this metadata
						Link link = new Link();
						mdl.add(link);
						break;

					case NUMBER:
						
						//TODO: need implementation of merging this metadata
						Number number = new Number();
						mdl.add(number);
						break;

					case PUBLICATION:
						
						//TODO: need implementation of merging this metadata
						Publication publication = new Publication();
						mdl.add(publication);
						break;

					default:
						
						//TODO: need implementation of merging this metadata
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

		// DMA_<bestand>_<signatur>.jpg
		bestand = bestand.replace(" ", "_").replace("/", "_");
		signatur = signatur.replace(" ", "_").replace("/", "_");

		filename += bestand + "_" + signatur + filenamePosfix;
		item.setFilename(filename);

		return item;
	}
	
	@Override
	public Item getItem(OUnterlagen tObject, List<ZuseXMLMDEnumType> enumList,
			MetadataProfile mdProfile) throws IntrospectionException {
		Item item = new Item();

		item.setCreated(Calendar.getInstance());

		String filename = "DMA_";
		String bestand = "";
		String signatur = "";
		String filenamePosfix = ".jpg";

		List<MetadataSet> mdsl = new ArrayList<MetadataSet>();
		MetadataSet mds = new MetadataSet();
		
		mds.setProfile(mdProfile.getId());
		
		
		
		Collection<Metadata> mdl = new ArrayList<Metadata>();

		List<?> z = enumList;

		Iterator<Statement> it = mdProfile.getStatements().iterator();
		Statement sts = null;
		
		for (PropertyDescriptor propertyDescriptor : Introspector.getBeanInfo(
				tObject.getClass()).getPropertyDescriptors()) {
			if (propertyDescriptor.getReadMethod().getReturnType() != String.class)
				continue;

			for (int i = 0; i < z.size() && it.hasNext(); i++) {
				
				String method = ((ZuseXMLMDEnumType) z.get(i)).getAttributes()[ZuseXMLMDEnumType.Column.METHOD_NAME.ordinal()];
				
				if (propertyDescriptor.getReadMethod().getName().contains(method)) {

					String type = ((ZuseXMLMDEnumType) z.get(i)).getAttributes()[ZuseXMLMDEnumType.Column.TYPE.ordinal()];
					
					if (type.isEmpty()) {
						continue;
					}					
					
					Metadata.Types aType = Enum.valueOf(Metadata.Types.class, type.toUpperCase());
					
					sts = it.next();
					
					switch (aType) {
						case TEXT:
							
							Text text = new Text();
							
							String tag = propertyDescriptor.getReadMethod().getName();
							String value = (String) tObject.getValueFromMethod(tag);
							text.setText(value);
							
							text.setStatement(sts.getId());
							
							text.setPos(sts.getPos());
							mdl.add(text);
	
							// DMA_<bestand>_<signatur>.jpg
							// getBestand, getSignatur
	
							if (tag.equalsIgnoreCase("getBestand")) {
								bestand = value;
							}
	
							if (tag.equalsIgnoreCase("getSignatur")) {
								signatur = value;
							}
	
							break;
	
						case CONE_PERSON:
							
							//TODO: need implementation of merging this metadata
							ConePerson conePerson = new ConePerson();
							
							conePerson.setStatement(sts.getId());
							conePerson.setPos(sts.getPos());
							
							mdl.add(conePerson);
							break;
	
						case DATE:
							
							//TODO: need implementation of merging this metadata
							Date date = new Date();
							
							date.setStatement(sts.getId());
							date.setPos(sts.getPos());
							
							mdl.add(date);
							break;
	
						case GEOLOCATION:
							
							//TODO: need implementation of merging this metadata
							Geolocation geolocation = new Geolocation();
							
							geolocation.setStatement(sts.getId());
							geolocation.setPos(sts.getPos());
							
							mdl.add(geolocation);
							break;
	
						case LICENSE:
							
							//TODO: need implementation of merging this metadata
							License license = new License();
							
							license.setStatement(sts.getId());
							license.setPos(sts.getPos());
							
							mdl.add(license);
							break;
	
						case LINK:
							
							//TODO: need implementation of merging this metadata
							Link link = new Link();
							
							link.setStatement(sts.getId());
							link.setPos(sts.getPos());
							
							mdl.add(link);
							break;
	
						case NUMBER:
							
							//TODO: need implementation of merging this metadata
							Number number = new Number();
							
							number.setStatement(sts.getId());
							number.setPos(sts.getPos());
							
							mdl.add(number);
							break;
	
						case PUBLICATION:
							
							//TODO: need implementation of merging this metadata
							Publication publication = new Publication();
							
							publication.setStatement(sts.getId());
							publication.setPos(sts.getPos());
							
							mdl.add(publication);
							break;
	
						default:
							
							//TODO: need implementation of merging this metadata
							Text defaultTxt = new Text();
							
							defaultTxt.setStatement(sts.getId());
							defaultTxt.setPos(sts.getPos());
							
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

		// DMA_<bestand>_<signatur>.jpg
		bestand = bestand.replace(" ", "_").replace("/", "_");
		signatur = signatur.replace(" ", "_").replace("/", "_");

		filename += bestand + "_" + signatur + filenamePosfix;
		item.setFilename(filename);

		return item;
	}

	@Override
	public Items getItems(List<OUnterlagen> tObject,
			List<ZuseXMLMDEnumType> enumList) throws IntrospectionException {
		Collection<Item> itemColection = new ArrayList<Item>();
		
		for (OUnterlagen oul : tObject) {			
			itemColection.add(this.getItem(oul, enumList));
		}
		
		return new Items(itemColection);
	}

	@Override
	public Items getItems(List<OUnterlagen> tObject,
			List<ZuseXMLMDEnumType> enumList, MetadataProfile mdProfile)
			throws IntrospectionException {
		Collection<Item> itemColection = new ArrayList<Item>();
		
		for (OUnterlagen oul : tObject) {			
			itemColection.add(this.getItem(oul, enumList, mdProfile));
		}
		
		return new Items(itemColection);
	}

	public static String getOfflineMDFilename(
			String inputFilename) {
		int i = inputFilename.lastIndexOf('.');
		if(i < 0)
			return inputFilename + "_offline_md";
		return inputFilename.substring(0, i) + "_offline_md" + inputFilename.substring(i);
	}

	public static String getOfflineItemsFilename(
			String inputFilename) {
		int i = inputFilename.lastIndexOf('.');
		if(i < 0)
			return inputFilename + "_offline_items";
		return inputFilename.substring(0, i) + "_offline_items" + inputFilename.substring(i);
	}

}