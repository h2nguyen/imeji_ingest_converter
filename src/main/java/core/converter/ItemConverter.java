/**
 * 
 */
package core.converter;

import java.beans.IntrospectionException;

import core.vo.imeji.Item;

/**
 * @author hnguyen
 * @param <T>
 *
 */
public abstract class ItemConverter<T> {	
	public abstract Item getMdProfile(T tObject, String title, String description) throws IntrospectionException;
}
