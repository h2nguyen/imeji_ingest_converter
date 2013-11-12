package module.zusearchive.misc;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;

import core.jaxb.JaxbGenericObject;
import core.vo.imeji.Item;
import core.vo.imeji.Items;
import core.vo.imeji.Metadata;
import core.vo.imeji.MetadataSet;

public class CreateHtmlTable {

	public CreateHtmlTable() {
		// TODO Auto-generated constructor stub
	}

	public Items getImejiItems(String xmlFile) throws JAXBException, SAXException {
		
		JaxbGenericObject<Items> jaxb = new JaxbGenericObject<Items>(Items.class);
		Items items = jaxb.unmarshal(xmlFile);
		return items;
	}
	
	public String createTable(Items items) {
		String htmlTable = "";
		
		List<Item> itemList = items.getItem();
		
		itemList = sortItems(itemList);
		
		return htmlTable;
	}

	private List<Item> sortItems(List<Item> itemList) {
		List<Item> sortedItemList = new ArrayList<Item>();
		
		
		for (Item item : itemList) {
			if(sortedItemList.isEmpty()) {
				sortedItemList.add(item);
				continue;
			}
			for (int i = 0; i < sortedItemList.size() - 1; i++) {
				
				Item sortedItem = sortedItemList.get(i);
				
				int sortedItemYear = getYear(sortedItem);
				int itemYear = getYear(item);
				
				if(itemYear >= sortedItemYear)
				
				
				sortedItemList.add(i, item);
			}
			
		}
		
		
		
		return sortedItemList;
	}
	
	private int getYear(Item item) {
		int year = -1;
		MetadataSet mds = item.getMetadataSet();		
		ArrayList<Metadata> md = (ArrayList<Metadata>) mds.getMetadata();
		
		for (Metadata metadata : md) {
			if(metadata.getPos() == 3) {
				year = Integer.valueOf((String)metadata.getValueFromMethod("getText"));				
			}
		}
		
		return year;
	}
	
}
