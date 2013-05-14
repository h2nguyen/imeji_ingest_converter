package core.j2j.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collection;
import java.util.List;

import org.opensaml.xml.util.LazyList;

import com.hp.hpl.jena.Jena;
import com.hp.hpl.jena.rdf.model.Resource;

import core.vo.imeji.Item;

/**
 * For persistence of {@link List} in {@link Jena}. <br/>
 * - A lazy {@link List} should be defined for all {@link List} which might get huge. To avoid some READ or WRITE
 * operations to last too long, it is preferable to avoid such lists<br/>
 * - Lazy {@link List} must be used with caution, since they are skipped by certain operations<br/>
 * <br/>
 * - Example: {@link List} of {@link Item} in a {@link Collection} are defined as {@link LazyList}. When loading a
 * collection, j2j doesn't load the complete {@link Collection} {@link Resource}, the {@link Item} {@link List} is not
 * loaded. To get the item, a {@link Search} is made (mucg faster that loading)
 * 
 * @author saquet (initial creation)
 * @author $Author$ (last modification)
 * @version $Revision$ $LastChangedDate$
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface j2jLazyList
{
    String value();
}
