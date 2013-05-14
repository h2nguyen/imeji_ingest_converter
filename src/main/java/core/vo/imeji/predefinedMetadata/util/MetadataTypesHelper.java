/**
 * License: src/main/resources/license/escidoc.license
 */
package core.vo.imeji.predefinedMetadata.util;

import core.vo.imeji.Metadata;
import core.vo.imeji.Metadata.Types;

/**
 * Helper to work with {@link Metadata} {@link Types}
 * 
 * @author saquet (initial creation)
 * @author $Author$ (last modification)
 * @version $Revision$ $LastChangedDate$
 */
public class MetadataTypesHelper
{
    /**
     * Return the {@link Types} for one namespace
     * 
     * @param namespace
     * @return
     */
    public static Metadata.Types getTypesForNamespace(String namespace)
    {
        for (Types t : Types.values())
        {
            if (t.getClazzNamespace().equals(namespace))
            {
                return t;
            }
        }
        return null;
    }
}
