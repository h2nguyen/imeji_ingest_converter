/**
 * 
 */
package core.converter;

import java.beans.IntrospectionException;

import core.vo.generated.formats.interfaces.IMdProfileFormat;
import core.vo.imeji.MetadataProfile;

/**
 * @author hnguyen
 * @param <T>
 *
 */
public abstract class MdProfileConverter<T> {	
	public abstract MetadataProfile getMdProfile(T tObject, String title, String description, Class<? extends IMdProfileFormat> enumClass) throws IntrospectionException;
}
