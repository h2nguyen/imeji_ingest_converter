/**
 * License: src/main/resources/license/escidoc.license
 */
package core.vo;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import core.j2j.annotations.j2jId;
import core.j2j.annotations.j2jList;
import core.j2j.annotations.j2jLiteral;
import core.j2j.annotations.j2jModel;
import core.j2j.annotations.j2jResource;

/**
 * Profile where {@link Item} {@link Metadata} are defined
 * 
 * @author saquet (initial creation)
 * @author $Author$ (last modification)
 * @version $Revision$ $LastChangedDate$
 */
@j2jResource("http://imeji.org/terms/mdprofile")
@j2jId(getMethod = "getId", setMethod = "setId")
@j2jModel("metadataProfile")
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "mdprofile", namespace = "http://imeji.org/terms")
public class MetadataProfile extends Properties
{    
    @j2jLiteral("http://purl.org/dc/elements/1.1/title")
    private String title;
    @j2jLiteral("http://purl.org/dc/elements/1.1/description")
    private String description;
    @j2jList("http://imeji.org/terms/statement")
    private Collection<Statement> statements = new ArrayList<Statement>();

	@XmlElement(name = "title", namespace = "http://purl.org/dc/elements/1.1")
    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    @XmlElement(name = "description", namespace = "http://purl.org/dc/elements/1.1")
    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
    
    @XmlElement(name = "statement", namespace = "http://imeji.org/terms")
    public Collection<Statement> getStatements()
    {
        return statements;
    }

    public void setStatements(Collection<Statement> statements)
    {
        this.statements = statements;
    }
}
