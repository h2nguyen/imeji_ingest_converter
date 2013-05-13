/**
 * License: src/main/resources/license/escidoc.license
 */
package core.vo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import core.j2j.annotations.j2jLiteral;
import core.j2j.annotations.j2jResource;

/**
 * Common properties to all imeji objects
 * 
 * @author saquet (initial creation)
 * @author $Author$ (last modification)
 * @version $Revision$ $LastChangedDate$
 */
@j2jResource("http://imeji.org/terms/properties")
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "properties", namespace = "http://imeji.org/terms")
@XmlSeeAlso({Item.class, MetadataProfile.class})
public class Properties
{
    private URI id;
    @j2jResource("http://purl.org/dc/terms/creator")    
    private URI createdBy;
    @j2jResource("http://imeji.org/terms/modifiedBy")
    private URI modifiedBy;
    @j2jLiteral("http://purl.org/dc/terms/created")
    private Calendar created;
    @j2jLiteral("http://purl.org/dc/terms/modified")
    private Calendar modified;
    @j2jLiteral("http://purl.org/dc/terms/issued")
    private Calendar versionDate;
    @j2jResource("http://imeji.org/terms/status")
    private URI status = Status.PENDING.getUri();
    @j2jLiteral("http://imeji.org/terms/versionNumber")
    private int version = 0;
    @j2jLiteral("http://imeji.org/terms/discardComment")
    private String discardComment;
    
    @XmlEnum(String.class)
    public enum Status
    {
        PENDING("http://imeji.org/terms/status#PENDING"),
        RELEASED("http://imeji.org/terms/status#RELEASED"),
        WITHDRAWN("http://imeji.org/terms/status#WITHDRAWN");
        
        private URI uri;

        private Status(String uri)
        {
            this.uri = URI.create(uri);
        }
                
        public URI getUri()
        {
            return uri;
        }
    }

    public Properties()
    {
        // TODO Auto-generated constructor stub
    }

    public void setCreatedBy(URI createdBy)
    {
        this.createdBy = createdBy;
    }

    @XmlElement(name = "creator", namespace = "http://purl.org/dc/terms")
    public URI getCreatedBy()
    {
        return createdBy;
    }

    public void setModifiedBy(URI modifiedBy)
    {
        this.modifiedBy = modifiedBy;
    }

    @XmlElement(name = "modifiedBy", namespace = "http://imeji.org/terms")
    public URI getModifiedBy()
    {
        return modifiedBy;
    }

    public void setStatus(Status status)
    {
        this.status = status.getUri();
    }

    @XmlElement(name = "status", namespace = "http://imeji.org/terms")
    public Status getStatus()
    {
        return Status.valueOf(status.getFragment());
    }

    public void setVersion(int version)
    {
        this.version = version;
    }

    @XmlElement(name = "version", namespace = "http://imeji.org/terms")
    public int getVersion()
    {
        return version;
    }

    @XmlElement(name = "discardComment", namespace = "http://imeji.org/terms")
    public String getDiscardComment()
    {
        return discardComment;
    }

    public void setDiscardComment(String discardComment)
    {
        this.discardComment = discardComment;
    }

    @XmlElement(name = "created", namespace = "http://purl.org/dc/terms")
    public Calendar getCreated()
    {
        return created;
    }

    public void setCreated(Calendar created)
    {
        this.created = created;
    }
    
    @XmlElement(name = "modified", namespace = "http://purl.org/dc/terms")
    public Calendar getModified()
    {
        return modified;
    }

    public void setModified(Calendar modified)
    {
        this.modified = modified;
    }
    
    @XmlElement(name = "issued", namespace = "http://purl.org/dc/terms")
    public Calendar getVersionDate()
    {
        return versionDate;
    }

    public void setVersionDate(Calendar versionDate)
    {
        this.versionDate = versionDate;
    }

    public void setId(URI id)
    {
        this.id = id;
    }
    
    @XmlAttribute(name = "id")
    public URI getId()
    {
        return id;
    }

    /**
     * return the id of this object defined in the last number in its {@link URI}.
     * 
     * @return
     */
    public String getIdString()
    {
        if (id != null)
        {
            return id.getPath().substring(id.getPath().lastIndexOf("/")+1);
        }
        return "";
    }

    /**
     * TODO : check this method
     * 
     * @param methodName
     * @return
     */
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
