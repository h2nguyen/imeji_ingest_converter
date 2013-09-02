/**
 * 
 */
package module.zusearchive.converter;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import jxl.read.biff.BiffException;

import module.zusearchive.tasks.ExcelEntryHandler;
import module.zusearchive.vo.generated.ExcelEntry;
import module.zusearchive.vo.generated.formats.enums.ZuseXMLMDEnumType;
import core.j2j.misc.LocalizedString;
import core.vo.imeji.Item;
import core.vo.imeji.Items;
import core.vo.imeji.Metadata;
import core.vo.imeji.MetadataProfile;
import core.vo.imeji.Statement;

/**
 * @author hnguyen
 *
 */
public class ZuseExcelConverter {

	public MetadataProfile getMdProfile(File file, String title, String description) {
		
		MetadataProfile mdp = null;
		ExcelEntry mdProfileExcel = null;
		
		
//		try {
//			mdp = new MetadataProfile();
//			mdProfileExcel = ExcelEntryHandler.getMetadataFromExcelFile(file);			
//			
//			
//			mdp.setDescription(description);
//			mdp.setTitle(title);
//
//			Collection<Statement> statements = new LinkedList<Statement>();
//			List<?> z = enumList;
//			int counter = 0;
//			int methodeCounter = 0;
//			try {
//				for (PropertyDescriptor propertyDescriptor : Introspector
//						.getBeanInfo(tObject.getClass()).getPropertyDescriptors()) {
//					if (propertyDescriptor.getReadMethod().getReturnType() != String.class)
//						continue;
//
//					for (int i = methodeCounter; i < z.size(); i++) {
//						String method = ((ZuseXMLMDEnumType) z.get(i))
//								.getAttributes()[ZuseXMLMDEnumType.Column.METHOD_NAME
//								.ordinal()];
//
//						if (propertyDescriptor.getReadMethod().getName()
//								.contains(method)) {
//
//							String type = ((ZuseXMLMDEnumType) z
//									.get(i)).getAttributes()[ZuseXMLMDEnumType.Column.TYPE
//									.ordinal()];
//							if (type.isEmpty()) {
//								methodeCounter = i+1;
//								continue;
//							}
//								
//
//							Statement statement = new Statement();
//							statement
//									.setId(URI
//											.create(((ZuseXMLMDEnumType) z
//													.get(i)).getAttributes()[ZuseXMLMDEnumType.Column.STATEMENT_ID
//													.ordinal()]));
//							String labelDE = ((ZuseXMLMDEnumType) z
//									.get(i)).getAttributes()[ZuseXMLMDEnumType.Column.DE_LABEL
//									.ordinal()];
//							String labelEN = ((ZuseXMLMDEnumType) z
//									.get(i)).getAttributes()[ZuseXMLMDEnumType.Column.EN_LABEL
//									.ordinal()];
//							statement.getLabels().add(
//									new LocalizedString(labelDE, "de"));
//							statement.getLabels().add(
//									new LocalizedString(labelEN, "en"));
//							statement.setPos(counter++);
//
//							Metadata.Types aType = Enum.valueOf(
//									Metadata.Types.class, type.toUpperCase());
//							switch (aType) {
//							case TEXT:
//								statements.add(statement);
//								methodeCounter = i+1;
//								break;
//
//							case CONE_PERSON:
//								statements.add(statement);
//								methodeCounter = i+1;
//								break;
//
//							case DATE:
//								statements.add(statement);
//								methodeCounter = i+1;
//								break;
//
//							case GEOLOCATION:
//								statements.add(statement);
//								methodeCounter = i+1;
//								break;
//
//							case LICENSE:
//								statements.add(statement);
//								methodeCounter = i+1;
//								break;
//
//							case LINK:
//								statements.add(statement);
//								methodeCounter = i+1;
//								break;
//
//							case NUMBER:
//								statements.add(statement);
//								methodeCounter = i+1;
//								break;
//
//							case PUBLICATION:
//								statements.add(statement);
//								methodeCounter = i+1;
//								break;
//
//							default:
//								statements.add(statement);
//								methodeCounter = i+1;
//								break;
//							}
//							break;
//						}
//					}
//				}
//			} catch (IntrospectionException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//			mdp.setStatements(statements);
//
//			
//			
//		} catch (BiffException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return mdp;
	}

	public Item getItems(File file)
			throws IntrospectionException {
		// TODO Auto-generated method stub
		return null;
	}



	public Items getItems(List<ExcelEntry> tObject) {
		// TODO Auto-generated method stub
		return null;
	}

}
