package core.j2j.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.hp.hpl.jena.rdf.model.Model;

/**
 * j2j {@link Model}
 * 
 * @author saquet (initial creation)
 * @author $Author$ (last modification)
 * @version $Revision$ $LastChangedDate$
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface j2jModel
{
    public String value();
}
