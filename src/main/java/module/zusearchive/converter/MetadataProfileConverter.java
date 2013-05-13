package module.zusearchive.converter;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.net.URI;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import module.zusearchive.helper.ZuseMdProfile;
import module.zusearchive.vo.generated.OUnterlagen;

import core.j2j.misc.LocalizedString;
import core.vo.Metadata;
import core.vo.MetadataProfile;
import core.vo.Statement;

public class MetadataProfileConverter {

	public MetadataProfileConverter() {
		// TODO Auto-generated constructor stub
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
	
	
	public static URI getNewStatementURI() {
		return URI.create("http://zuse.zib.de/statement/" + UUID.randomUUID());
	}
	
	public static URI getMetadataProfileURI() {
		return URI.create("http://zuse.zib.de/terms/metadata/" + UUID.randomUUID());		
	}
}
