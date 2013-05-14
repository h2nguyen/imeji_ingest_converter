package module.zusearchive.converter;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.net.URI;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import module.zusearchive.vo.generated.OUnterlagen;
import module.zusearchive.vo.generated.enums.ZuseOUnterlagenEMdProfileFormat;

import core.converter.MdProfileConverter;
import core.j2j.misc.LocalizedString;
import core.vo.generated.formats.interfaces.IMdProfileFormat;
import core.vo.imeji.Metadata;
import core.vo.imeji.MetadataProfile;
import core.vo.imeji.Statement;

public class ZuseMdProfileConverter extends MdProfileConverter {

	public ZuseMdProfileConverter() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public <T> MetadataProfile getMdProfile(Class<T> object,
			String title, String description, Class<? extends IMdProfileFormat> enumClass)
			throws IntrospectionException {
		
		MetadataProfile mdp = new MetadataProfile();
		mdp.setDescription(description);
		mdp.setTitle(title);

		Collection<Statement> statements = new LinkedList<Statement>();
		
		List<?> z = (List<?>) ZuseOUnterlagenEMdProfileFormat.enum2list(enumClass);
		int counter = 0;
		try {
			for (PropertyDescriptor propertyDescriptor : Introspector
					.getBeanInfo(object.getClass()).getPropertyDescriptors()) {
				if (propertyDescriptor.getReadMethod().getReturnType() != String.class)
					continue;

				for (int i = 0; i < z.size(); i++) {
					String method = ((ZuseOUnterlagenEMdProfileFormat) z.get(i))
							.getAttributes()[ZuseOUnterlagenEMdProfileFormat.Column.METHOD_NAME
							.ordinal()];
					
					String s = propertyDescriptor.getReadMethod().getName();
					
					if (propertyDescriptor.getReadMethod().getName()
							.contains(method)) {

						String type = ((ZuseOUnterlagenEMdProfileFormat) z
								.get(i)).getAttributes()[ZuseOUnterlagenEMdProfileFormat.Column.TYPE
								.ordinal()];
						if (type.isEmpty())
							continue;

						Statement statement = new Statement();
						statement
								.setId(URI
										.create(((ZuseOUnterlagenEMdProfileFormat) z
												.get(i)).getAttributes()[ZuseOUnterlagenEMdProfileFormat.Column.STATEMENT_ID
												.ordinal()]));
						String labelDE = ((ZuseOUnterlagenEMdProfileFormat) z
								.get(i)).getAttributes()[ZuseOUnterlagenEMdProfileFormat.Column.DE_LABEL
								.ordinal()];
						String labelEN = ((ZuseOUnterlagenEMdProfileFormat) z
								.get(i)).getAttributes()[ZuseOUnterlagenEMdProfileFormat.Column.EN_LABEL
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
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mdp.setStatements(statements);

		return mdp;
	}

	// public static MetadataProfile getMdProfile(OUnterlagen oul, String title,
	// String description) throws IntrospectionException {
	// MetadataProfile mdp = new MetadataProfile();
	// mdp.setDescription(description);
	// mdp.setTitle(title);
	//
	// Collection<Statement> statements = new LinkedList<Statement>();
	//
	// List<?> z = (List<?>)
	// ZuseNormFormat.MdProfileFormat.enum2list(ZuseNormFormat.MdProfileFormat.class);
	// int counter = 0;
	// for(PropertyDescriptor propertyDescriptor :
	// Introspector.getBeanInfo(oul.getClass()).getPropertyDescriptors()){
	// if(propertyDescriptor.getReadMethod().getReturnType() != String.class)
	// continue;
	//
	// for (int i = 0; i < z.size(); i++) {
	// String method = ((ZuseNormFormat.MdProfileFormat)
	// z.get(i)).getAttributes()[ZuseNormFormat.MdProfileFormat.Column.METHOD_NAME.ordinal()];
	// if(propertyDescriptor.getReadMethod().getName().contains(method)) {
	//
	// String type = ((ZuseNormFormat.MdProfileFormat)
	// z.get(i)).getAttributes()[ZuseNormFormat.MdProfileFormat.Column.TYPE.ordinal()];
	// if(type.isEmpty())
	// continue;
	//
	// Statement statement = new Statement();
	// statement.setId(URI.create(((ZuseNormFormat.MdProfileFormat)
	// z.get(i)).getAttributes()[ZuseNormFormat.MdProfileFormat.Column.STATEMENT_ID.ordinal()]));
	// String labelDE = ((ZuseNormFormat.MdProfileFormat)
	// z.get(i)).getAttributes()[ZuseNormFormat.MdProfileFormat.Column.DE_LABEL.ordinal()];
	// String labelEN = ((ZuseNormFormat.MdProfileFormat)
	// z.get(i)).getAttributes()[ZuseNormFormat.MdProfileFormat.Column.EN_LABEL.ordinal()];
	// statement.getLabels().add(new LocalizedString(labelDE, "de"));
	// statement.getLabels().add(new LocalizedString(labelEN, "en"));
	// statement.setPos(counter++);
	//
	// Metadata.Types aType = Enum.valueOf(Metadata.Types.class,
	// type.toUpperCase());
	// switch(aType) {
	// case TEXT:
	// statements.add(statement);
	// break;
	//
	// case CONE_PERSON:
	// statements.add(statement);
	// break;
	//
	// case DATE:
	// statements.add(statement);
	// break;
	//
	// case GEOLOCATION:
	// statements.add(statement);
	// break;
	//
	// case LICENSE:
	// statements.add(statement);
	// break;
	//
	// case LINK:
	// statements.add(statement);
	// break;
	//
	// case NUMBER:
	// statements.add(statement);
	// break;
	//
	// case PUBLICATION:
	// statements.add(statement);
	// break;
	//
	// default:
	// statements.add(statement);
	// break;
	// }
	// break;
	// }
	// }
	// }
	//
	//
	// mdp.setStatements(statements);
	//
	// return mdp;
	// }

}
