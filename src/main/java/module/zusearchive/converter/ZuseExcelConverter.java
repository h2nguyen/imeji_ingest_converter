/**
 * 
 */
package module.zusearchive.converter;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import jxl.read.biff.BiffException;

import module.zusearchive.tasks.ZuseExcelEntryHandler;
import module.zusearchive.vo.generated.ExcelEntry;
import core.j2j.misc.LocalizedString;
import core.vo.imeji.Item;
import core.vo.imeji.Items;
import core.vo.imeji.Metadata;
import core.vo.imeji.MetadataProfile;
import core.vo.imeji.MetadataSet;
import core.vo.imeji.Statement;
import core.vo.imeji.predefinedMetadata.Text;

/**
 * @author hnguyen
 *
 */
public class ZuseExcelConverter {

	public MetadataProfile getMdProfile(File file, String title, String description) throws BiffException, IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		MetadataProfile mdp = null;
		ExcelEntry mdProfileExcel = null;
		
		
		try {
			mdp = new MetadataProfile();
			mdProfileExcel = ZuseExcelEntryHandler.getMetadataFromExcelFile(file);			
			
			
			mdp.setDescription(description);
			mdp.setTitle(title);

			Collection<Statement> statements = new LinkedList<Statement>();
			
			int positionCounter = 0;
			
			Statement statementCached = null;
			String cachedMethod = "";
			boolean canBeAdded = false;
			
			try {
				for (PropertyDescriptor propertyDescriptor : Introspector
						.getBeanInfo(mdProfileExcel.getClass()).getPropertyDescriptors()) {
					
					if (propertyDescriptor.getReadMethod().getName().equalsIgnoreCase("getClass")) {
						continue;
					}

					// starts to pair the issue
					if (propertyDescriptor.getReadMethod().getReturnType() == boolean.class) {
						if(propertyDescriptor.getName().contains(cachedMethod)) {
							if(propertyDescriptor.getName().contains("Multi")) {
								if(canBeAdded) {
									statementCached.setPos(positionCounter++);
									if(((Boolean) propertyDescriptor.getReadMethod().invoke(mdProfileExcel)).booleanValue())
										statementCached.setMaxOccurs("unbounded");
									statements.add(statementCached);
									canBeAdded = false;
								}
									
							} else if(((Boolean) propertyDescriptor.getReadMethod().invoke(mdProfileExcel)).booleanValue()) {
								canBeAdded = true;
							}
						}
						continue;
					}
					
					Statement statement = new Statement();
					
					String labelEN = (String) propertyDescriptor.getReadMethod().invoke(mdProfileExcel);
					statement.getLabels().add(new LocalizedString(labelEN, "en"));
					statementCached = statement;
					cachedMethod = propertyDescriptor.getName();
					
				}
			} catch (IntrospectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			mdp.setStatements(statements);
			
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mdp;
	}

	
	public Item getItem(ExcelEntry entry)
			throws IntrospectionException, BiffException, IOException, URISyntaxException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Item item = new Item();

		item.setCreated(Calendar.getInstance());

		List<MetadataSet> mdsl = new ArrayList<MetadataSet>();
		MetadataSet mds = new MetadataSet();
		Collection<Metadata> mdl = new ArrayList<Metadata>();

		int positionCounter = 0;
		Metadata md = null;
		String cachedMethod = "";
		boolean canBeAdded = false;

		for (PropertyDescriptor propertyDescriptor : Introspector.getBeanInfo(
				entry.getClass()).getPropertyDescriptors()) {
			
			if (propertyDescriptor.getReadMethod().getName().equalsIgnoreCase("getClass")) {
				continue;
			}

			// starts to pair the issue
			if (propertyDescriptor.getReadMethod().getReturnType() == boolean.class) {
				if(propertyDescriptor.getName().contains(cachedMethod)) {
					if(propertyDescriptor.getName().contains("Multi")) {
						if(canBeAdded) {
							md.setPos(positionCounter++);
							if(((Boolean) propertyDescriptor.getReadMethod().invoke(entry)).booleanValue()) {
								//TODO: add mulitple issue
//								md.setMaxOccurs("unbounded");
							}
								
							mdl.add(md);
							canBeAdded = false;
						}
							
					} else if(((Boolean) propertyDescriptor.getReadMethod().invoke(entry)).booleanValue()) {
						canBeAdded = true;
					}
				}
				continue;
			}
			
			Text text = new Text();
			text.setText((String) propertyDescriptor.getReadMethod().invoke(entry));
			md = text;
			cachedMethod = propertyDescriptor.getName();
		}

		mds.setMetadata(mdl);
		mdsl.add(mds);
		item.setMetadataSets(mdsl);

		return item;
	}
	
	public Items getItems(File file)
			throws IntrospectionException, BiffException, IOException, URISyntaxException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return getItems(ZuseExcelEntryHandler.getDataFromExcelFile(file));
	}



	public Items getItems(List<ExcelEntry> tObject) throws BiffException, IntrospectionException, IOException, URISyntaxException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		ArrayList<Item> itemList = new ArrayList<Item>(tObject.size());
		
		for (ExcelEntry entry : tObject) {
			Item item = this.getItem(entry);
			itemList.add(item);
		}
		
		return  new Items(itemList);
	}
	
	public Item getItem(ExcelEntry entry, MetadataProfile mdp)
			throws IntrospectionException, BiffException, IOException, URISyntaxException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Item item = new Item();

		item.setCreated(Calendar.getInstance());

		List<MetadataSet> mdsl = new ArrayList<MetadataSet>();
		MetadataSet mds = new MetadataSet();
		mds.setProfile(mdp.getId());
		
		
		
		Collection<Metadata> mdl = new ArrayList<Metadata>();

		int positionCounter = 0;
		Metadata md = null;
		String cachedMethod = "";
		boolean canBeAdded = false;
		
		Statement sts = null;

		for (PropertyDescriptor propertyDescriptor : Introspector.getBeanInfo(
				entry.getClass()).getPropertyDescriptors()) {
			
			if (propertyDescriptor.getReadMethod().getName().equalsIgnoreCase("getClass")) {
				continue;
			}
			
			for (Iterator<Statement> it = mdp.getStatements().iterator(); it.hasNext();) {
				
				sts = it.next();
				
				// starts to pair the issue
				if (propertyDescriptor.getReadMethod().getReturnType() == boolean.class) {
					if(propertyDescriptor.getName().contains(cachedMethod)) {
						if(propertyDescriptor.getName().contains("Multi")) {
							if(canBeAdded) {
								md.setPos(positionCounter++);
								if(((Boolean) propertyDescriptor.getReadMethod().invoke(entry)).booleanValue()) {
									//TODO: add mulitple issue
//									md.setMaxOccurs("unbounded");
								}
									
								mdl.add(md);
								canBeAdded = false;
							}
								
						} else if(((Boolean) propertyDescriptor.getReadMethod().invoke(entry)).booleanValue()) {
							canBeAdded = true;
						}
					}
					continue;
				}
				
				Text text = new Text();
				text.setText((String) propertyDescriptor.getReadMethod().invoke(entry));
				text.setStatement(sts.getId());
				text.setPos(sts.getPos());
				md = text;
				cachedMethod = propertyDescriptor.getName();
			}

			
		}

		mds.setMetadata(mdl);
		mdsl.add(mds);
		item.setMetadataSets(mdsl);

		return item;
	}
	
	public Items getItems(File file, MetadataProfile mdp)
			throws IntrospectionException, BiffException, IOException, URISyntaxException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return getItems(ZuseExcelEntryHandler.getDataFromExcelFile(file), mdp);
	}



	public Items getItems(List<ExcelEntry> tObject, MetadataProfile mdp) throws BiffException, IntrospectionException, IOException, URISyntaxException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		ArrayList<Item> itemList = new ArrayList<Item>(tObject.size());
		
		for (ExcelEntry entry : tObject) {
			Item item = this.getItem(entry, mdp);
			itemList.add(item);
		}
		
		return  new Items(itemList);
	}

}
