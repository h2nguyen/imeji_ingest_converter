/**
 * 
 */
package core.converter;

import java.beans.IntrospectionException;

import core.vo.generated.formats.interfaces.IMdProfileFormat;
import core.vo.imeji.MetadataProfile;

/**
 * @author hnguyen
 *
 */
public abstract class MdProfileConverter {	
	public abstract <T> MetadataProfile getMdProfile(Class<T> object, String title, String description, Enum<? extends IMdProfileFormat> enumObject) throws IntrospectionException;
}
