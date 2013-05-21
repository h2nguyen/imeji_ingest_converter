/**
 * 
 */
package module.zusearchive.jaxb;

import core.jaxb.JaxbGenericObject;

/**
 * @author hnguyen
 * @param <T>
 *
 */
public class JaxbZuseGenericObject<T> extends JaxbGenericObject<T> {	
	
	public JaxbZuseGenericObject(Class<T> type) {
		super(type, ZuseArchiveSchemaFilename.ZUSEARCHIVE_XSDFILE);		
	}	

}
