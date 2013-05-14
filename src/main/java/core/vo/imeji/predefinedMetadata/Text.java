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
 * {@link Metadata} of type text
 * 
 * @author saquet (initial creation)
 * @author $Author$ (last modification)
 * @version $Revision$ $LastChangedDate$
 */
@j2jResource("http://imeji.org/terms/metadata")
@j2jDataType("http://imeji.org/terms/metadata#text")
@j2jId(getMethod = "getId", setMethod = "setId")
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "text", namespace = "http://imeji.org/terms/metadata")
public class Text extends Metadata
{
    @j2jLiteral("http://imeji.org/terms/text")
    private String text;
    @j2jResource("http://imeji.org/terms/statement")
    private URI statement;

    public Text()
    {
    }

    @XmlElement(name = "text", namespace="http://imeji.org/terms")
    public String getText()
    {
        return text;
    }

    public void setText(String str)
    {
        text = str;
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
        if (metadata instanceof Text)
        {
            this.text = ((Text)metadata).getText();
            this.statement = metadata.getStatement();
        }
    }

    @Override
    public String asFulltext()
    {
        return text;
    }
}
