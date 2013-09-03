/**
 * License: src/main/resources/license/escidoc.license
 */
package core.vo.imeji;

import java.net.URI;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import core.j2j.annotations.j2jId;
import core.j2j.annotations.j2jLiteral;
import core.j2j.annotations.j2jResource;

/**
 * An organization
 * 
 * @author saquet (initial creation)
 * @author $Author$ (last modification)
 * @version $Revision$ $LastChangedDate$
 */
@j2jResource("http://purl.org/escidoc/metadata/profiles/0.1/organizationalunit")
@j2jId(getMethod = "getId", setMethod = "setId")
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "organizationalunit", namespace = "http://purl.org/escidoc/metadata/profiles/0.1")
public class Organization implements Cloneable
{
    private URI id;
    @j2jLiteral("http://purl.org/dc/terms/title")
    private String name;
    @j2jLiteral("http://purl.org/dc/terms/description")
    private String description;
    @j2jLiteral("http://purl.org/dc/terms/identifier")
    private String identifier;
    @j2jLiteral("http://purl.org/escidoc/metadata/terms/0.1/city")
    private String city;
    @j2jLiteral("http://purl.org/escidoc/metadata/terms/0.1/country")
    private String country;
    private int pos = 0;

    public Organization()
    {
        // TODO Auto-generated constructor stub
    }

    @XmlElement(name = "title", namespace = "http://purl.org/dc/terms")
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @XmlElement(name = "description", namespace = "http://purl.org/dc/terms")
    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @XmlElement(name = "identifier", namespace = "http://purl.org/dc/terms")
    public String getIdentifier()
    {
        return identifier;
    }

    public void setIdentifier(String identifier)
    {
        this.identifier = identifier;
    }

    @XmlElement(name = "city", namespace = "http://purl.org/escidoc/metadata/terms/0.1")
    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    @XmlElement(name = "country", namespace = "http://purl.org/escidoc/metadata/terms/0.1")
    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
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

    public int compareTo(Organization o)
    {
        if (o.getPos() > this.pos)
            return -1;
        else if (o.getPos() == this.pos)
            return 0;
        else
            return 1;
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

    @Override
    public Organization clone()
    {
        Organization clone = new Organization();
        clone.city = this.city;
        clone.country = this.country;
        clone.description = this.description;
        clone.identifier = this.identifier;
        clone.name = this.name;
        clone.pos = this.pos;
        return clone;
    }
}
