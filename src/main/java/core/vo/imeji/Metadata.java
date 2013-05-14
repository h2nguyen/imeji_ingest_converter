/**
 * License: src/main/resources/license/escidoc.license
 */
package core.vo.imeji;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import core.helper.IdentifierUtil;
import core.j2j.annotations.j2jDataType;
import core.j2j.annotations.j2jId;
import core.j2j.annotations.j2jResource;
import core.vo.imeji.predefinedMetadata.ConePerson;
import core.vo.imeji.predefinedMetadata.Date;
import core.vo.imeji.predefinedMetadata.Geolocation;
import core.vo.imeji.predefinedMetadata.License;
import core.vo.imeji.predefinedMetadata.Link;
import core.vo.imeji.predefinedMetadata.Number;
import core.vo.imeji.predefinedMetadata.Publication;
import core.vo.imeji.predefinedMetadata.Text;


/**
 * Abstract class for metadata of an {@link Item}.
 * 
 * @author saquet (initial creation)
 * @author $Author$ (last modification)
 * @version $Revision$ $LastChangedDate$
 */
@j2jResource("http://imeji.org/terms/metadata")
@j2jId(getMethod = "getId", setMethod = "setId")
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "metadata", namespace = "http://imeji.org/terms")
@XmlSeeAlso({ Text.class, Number.class, ConePerson.class, Date.class, Geolocation.class, License.class, Link.class,
        Publication.class })
public abstract class Metadata
{
    // private URI id = URI.create("http://imeji.org/terms/metadata/" + UUID.randomUUID());
    private URI id = IdentifierUtil.newURI(Metadata.class);
    private int pos = 0;

    @XmlEnum(Types.class)
    public enum Types
    {
    	TEXT(Text.class), 
    	NUMBER(Number.class), 
    	CONE_PERSON(ConePerson.class), 
    	DATE(Date.class), 
    	GEOLOCATION(Geolocation.class),
    	LICENSE(License.class), 
    	LINK(Link.class), 
    	PUBLICATION(Publication.class);
        
        private Class<? extends Metadata> clazz = null;

        private Types(Class<? extends Metadata> clazz)
        {
            this.clazz = clazz;
        }

        public Class<? extends Metadata> getClazz()
        {
            return clazz;
        }

        public String getClazzNamespace()
        {
            return clazz.getAnnotation(j2jDataType.class).value();
        }
    }

    public Metadata()
    {
    }

    public String getTypeNamespace()
    {
        return this.getClass().getAnnotation(j2jDataType.class).value();
    }

    public int compareTo(Metadata imd)
    {
        if (imd.getPos() > this.pos)
            return -1;
        else if (imd.getPos() == this.pos)
            return 0;
        else
            return 1;
    }

    public abstract void copy(Metadata metadata);

    public abstract URI getStatement();

    public abstract void setStatement(URI namespace);

    public abstract String asFulltext();

    protected void copyMetadata(Metadata metadata)
    {
        this.id = metadata.getId();
    }

    @XmlAttribute(name = "id")
    public URI getId()
    {
        return id;
    }

    public void setId(URI id)
    {
        this.id = id;
    }

    @XmlElement(name = "position", namespace = "http://imeji.org/terms")
    public int getPos()
    {
        return pos;
    }

    public void setPos(int pos)
    {
        this.pos = pos;
    }

    public Object getValueFromMethod(String methodName)
    {
        Method method;
        Object ret = null;
        try
        {
            method = this.getClass().getMethod(methodName);
            ret = method.invoke(this);
        }
        catch (SecurityException e)
        {
            e.printStackTrace();
        }
        catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;
    }
}
