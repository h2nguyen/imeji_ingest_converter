/**
 * License: src/main/resources/license/escidoc.license
 */
package core.vo.imeji.predefinedMetadata;

import java.net.URI;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import core.j2j.annotations.j2jDataType;
import core.j2j.annotations.j2jId;
import core.j2j.annotations.j2jLiteral;
import core.j2j.annotations.j2jResource;
import core.vo.imeji.Metadata;

/**
 * {@link Metadata} for links (URL)
 * 
 * @author saquet (initial creation)
 * @author $Author$ (last modification)
 * @version $Revision$ $LastChangedDate$
 */
@j2jResource("http://imeji.org/terms/metadata")
@j2jDataType("http://imeji.org/terms/metadata#link")
@j2jId(getMethod = "getId", setMethod = "setId")
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "link", namespace = "http://imeji.org/terms/metadata")
public class Link extends Metadata
{
    @j2jResource("http://imeji.org/terms/uri")
    private URI uri;
    @j2jLiteral("http://www.w3.org/2000/01/rdf-schema#label")
    private String label;
    @j2jResource("http://imeji.org/terms/statement")
    private URI statement;

    public Link()
    {
    }

    @XmlElement(name = "uri", namespace="http://imeji.org/terms")
    public java.net.URI getUri()
    {
        return uri;
    }

    public void setUri(java.net.URI uri)
    {
        this.uri = uri;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }
    
    @XmlElement(name="label", namespace = "http://www.w3.org/2000/01/rdf-schema#")
    public String getLabel()
    {
        return label;
    }

    @Override
    @XmlElement(name = "statement", namespace="http://imeji.org/terms")
    public URI getStatement()
    {
        return statement;
    }

    @Override
    public void setStatement(URI namespace)
    {
        this.statement = namespace;
    }

    @Override
    public void copy(Metadata metadata)
    {
        if (metadata instanceof Link)
        {
            this.label = ((Link)metadata).getLabel();
            this.uri = ((Link)metadata).getUri();
            this.statement = metadata.getStatement();
        }
    }

    @Override
    public String asFulltext()
    {
        return label + " " + uri.toString();
    }
}
