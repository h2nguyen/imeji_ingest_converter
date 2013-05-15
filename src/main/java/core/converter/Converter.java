/**
 * 
 */
package core.converter;

import java.beans.IntrospectionException;
import java.util.List;

import core.vo.imeji.Item;
import core.vo.imeji.Items;
import core.vo.imeji.MetadataProfile;

/**
 * @author hnguyen
 * @param <T>
 * @param <E>
 *
 */
public abstract class Converter<T, E> {
	/**
	 * 
	 * @param tObject
	 * @param title
	 * @param description
	 * @param enumList
	 * @return
	 * @throws IntrospectionException
	 */
	public abstract MetadataProfile getMdProfile(T tObject, String title, String description, List<E> enumList) throws IntrospectionException;
	
	/**
	 * 
	 * @param tObject
	 * @param enumList
	 * @return
	 * @throws IntrospectionException
	 */
	public abstract Item getItem(T tObject, List<E> enumList) throws IntrospectionException;
	
	/**
	 * 
	 * @param tObject
	 * @param enumList
	 * @return
	 * @throws IntrospectionException
	 */
	public abstract Items getItems(List<T> tObject, List<E> enumList) throws IntrospectionException;
}
