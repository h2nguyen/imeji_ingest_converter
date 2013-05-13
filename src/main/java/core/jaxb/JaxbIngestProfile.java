/**
 * 
 */
package main.java.core.jaxb;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;


import main.java.core.jaxb.interfaces.IJaxbIngestProfile;
import main.java.core.jaxb.interfaces.IJaxbItem;
import main.java.core.jaxb.interfaces.IJaxbItems;
import main.java.core.jaxb.interfaces.IJaxbMetadataProfile;
import main.java.core.jaxb.interfaces.IJaxbMetadataProfiles;
import main.java.core.vo.IngestProfile;
import main.java.core.vo.Item;
import main.java.core.vo.Items;
import main.java.core.vo.MetadataProfile;
import main.java.core.vo.MetadataProfiles;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

/**
 * @author hnguyen
 */
public class JaxbIngestProfile extends JaxbUtil implements IJaxbItem, IJaxbItems, IJaxbMetadataProfile,
        IJaxbMetadataProfiles, IJaxbIngestProfile
{
    private Logger logger = Logger.getLogger(JaxbIngestProfile.class);

    /**
	 * 
	 */
    public JaxbIngestProfile()
    {
        // TODO Auto-generated constructor stub
    }

    
    public void marshalItem(String xmlFilename, Item item) throws JAXBException, SAXException, FileNotFoundException
    {
        String xsdFilename = IJaxbItem.xsdFilename;
        if (xmlFilename.isEmpty() || xsdFilename.isEmpty())
        {
            logger.error("\nSchema file or Xml file not provided!");
            return;
        }
        super.marshal(xsdFilename, xmlFilename, item);
    }

    
    public Item unmarshalItem(String xmlFilename) throws JAXBException, SAXException
    {
        String xsdFilename = IJaxbItem.xsdFilename;
        if (xmlFilename.isEmpty() || xsdFilename.isEmpty())
        {
            logger.error("\nSchema file or Xml file not provided!");
            return null;
        }
        return unmarshal(xsdFilename, xmlFilename, Item.class);
    }

	
	public void marshalItem(File xmlFile, Item item) throws JAXBException, SAXException, FileNotFoundException
	{
		String xsdFilename = IJaxbItem.xsdFilename;
        if (xmlFile == null || xsdFilename.isEmpty())
        {
            logger.error("\nSchema file or Xml file not provided!");
            return;
        }
        super.marshal(xsdFilename, xmlFile, item);
	}

	
	public Item unmarshalItem(File xmlFile) throws JAXBException, SAXException
	{
		String xsdFilename = IJaxbItem.xsdFilename;
        if (xmlFile == null || xsdFilename.isEmpty())
        {
            logger.error("\nSchema file or Xml file not provided!");
            return null;
        }
        return unmarshal(xsdFilename, xmlFile, Item.class);
	}
    
    
    
    public void marshalItems(String xmlFilename, Items items) throws JAXBException, SAXException, FileNotFoundException
    {
        String xsdFilename = IJaxbItems.xsdFilename;
        if (xmlFilename.isEmpty() || xsdFilename.isEmpty())
        {
            logger.error("\nSchema file or Xml file not provided!");
            return;
        }
        super.marshal(xsdFilename, xmlFilename, items);
    }

    
    public Items unmarshalItems(String xmlFilename) throws JAXBException, SAXException
    {
        String xsdFilename = IJaxbItems.xsdFilename;
        if (xmlFilename.isEmpty() || xsdFilename.isEmpty())
        {
            logger.error("\nSchema file or Xml file not provided!");
            return null;
        }
        return unmarshal(xsdFilename, xmlFilename, Items.class);
    }
    
	
	public void marshalItems(File xmlFile, Items items) throws JAXBException, SAXException, FileNotFoundException {
		String xsdFilename = IJaxbItems.xsdFilename;
        if (xmlFile == null || xsdFilename.isEmpty())
        {
            logger.error("\nSchema file or Xml file not provided!");
            return;
        }
        super.marshal(xsdFilename, xmlFile, items);
	}

	
	public Items unmarshalItems(File xmlFile) throws JAXBException, SAXException {
		String xsdFilename = IJaxbItems.xsdFilename;
		if (xmlFile == null || xsdFilename.isEmpty())
		{
		    logger.error("\nSchema file or Xml file not provided!");
			return null;
		}
		return unmarshal(xsdFilename, xmlFile, Items.class);
	}

    
    public void marshalMdProfile(String xmlFilename, MetadataProfile mdp) throws JAXBException, SAXException, FileNotFoundException
    {
        String xsdFilename = IJaxbMetadataProfile.xsdFilename;
        if (xmlFilename.isEmpty() || xsdFilename.isEmpty())
        {
            logger.error("\nSchema file or Xml file not provided!");
            return;
        }
        super.marshal(xsdFilename, xmlFilename, mdp);
    }

    
    public MetadataProfile unmarshalMdProfile(String xmlFilename) throws JAXBException, SAXException
    {
        String xsdFilename = IJaxbMetadataProfile.xsdFilename;
        if (xmlFilename.isEmpty() || xsdFilename.isEmpty())
        {
            logger.error("\nSchema file or Xml file not provided!");
            return null;
        }
        return super.unmarshal(xsdFilename, xmlFilename, MetadataProfile.class);
    }
    
	
	public void marshalMdProfile(File xmlFile, MetadataProfile mdp) throws JAXBException, SAXException, FileNotFoundException {
		String xsdFilename = IJaxbMetadataProfile.xsdFilename;
	    if (xmlFile == null || xsdFilename.isEmpty())
	    {
	        logger.error("\nSchema file or Xml file not provided!");
	        return;
	    }
		super.marshal(xsdFilename, xmlFile, mdp);
	}

	
	public MetadataProfile unmarshalMdProfile(File xmlFile) throws JAXBException, SAXException {
		String xsdFilename = IJaxbMetadataProfile.xsdFilename;
        if (xmlFile == null || xsdFilename.isEmpty())
        {
            logger.error("\nSchema file or Xml file not provided!");
            return null;
        }
        return super.unmarshal(xsdFilename, xmlFile, MetadataProfile.class);
	}

    
    public void marshalMdProfiles(String xmlFilename, MetadataProfiles mdps) throws JAXBException, SAXException, FileNotFoundException
    {
        String xsdFilename = IJaxbMetadataProfiles.xsdFilename;
        if (xmlFilename.isEmpty() || xsdFilename.isEmpty())
        {
            logger.error("\nSchema file or Xml file not provided!");
        }
        super.marshal(xsdFilename, xmlFilename, mdps);
    }

    
    public MetadataProfiles unmarshalMdProfiles(String xmlFilename) throws JAXBException, SAXException
    {
        String xsdFilename = IJaxbMetadataProfiles.xsdFilename;
        if (xmlFilename.isEmpty() || xsdFilename.isEmpty())
        {
            logger.error("\nSchema file or Xml file not provided!");
            return null;
        }
        return super.unmarshal(xsdFilename, xmlFilename, MetadataProfiles.class);
    }
    
	
	public void marshalMdProfiles(File xmlFile, MetadataProfiles mdps) throws JAXBException, SAXException, FileNotFoundException {
		String xsdFilename = IJaxbMetadataProfiles.xsdFilename;
        if (xmlFile == null || xsdFilename.isEmpty())
        {
            logger.error("\nSchema file or Xml file not provided!");
            return;
        }
        super.marshal(xsdFilename, xmlFile, mdps);
	}

	
	public MetadataProfiles unmarshalMdProfiles(File xmlFile) throws JAXBException, SAXException {
		String xsdFilename = IJaxbMetadataProfiles.xsdFilename;
        if (xmlFile == null || xsdFilename.isEmpty())
        {
            logger.error("\nSchema file or Xml file not provided!");
            return null;
        }
        return super.unmarshal(xsdFilename, xmlFile, MetadataProfiles.class);
	}

    
    public void marshalIngestProfile(String xmlFilename, IngestProfile ingestProfile) throws JAXBException, SAXException, FileNotFoundException
    {
        String xsdFilename = IJaxbIngestProfile.xsdFilename;
        if (xmlFilename.isEmpty() || xsdFilename.isEmpty())
        {
            logger.error("\nSchema file or Xml file not provided!");
            return;
        }
        super.marshal(xsdFilename, xmlFilename, ingestProfile);
    }

    
    public IngestProfile unmarshalIngestProfile(String xmlFilename) throws JAXBException, SAXException
    {
        String xsdFilename = IJaxbIngestProfile.xsdFilename;
        if (xmlFilename.isEmpty() || xsdFilename.isEmpty())
        {
            logger.error("\nSchema file or Xml file not provided!");
            return null;
        }
        return super.unmarshal(xsdFilename, xmlFilename, IngestProfile.class);
    }

	
	public void marshalIngestProfile(File xmlFile, IngestProfile ingestProfile) throws JAXBException, SAXException, FileNotFoundException {
		String xsdFilename = IJaxbIngestProfile.xsdFilename;
        if (xmlFile == null || xsdFilename.isEmpty())
        {
            logger.error("\nSchema file or Xml file not provided!");
            return;
        }
        super.marshal(xsdFilename, xmlFile, ingestProfile);		
	}

	
	public IngestProfile unmarshalIngestProfile(File xmlFile) throws JAXBException, SAXException {
		String xsdFilename = IJaxbIngestProfile.xsdFilename;
        if (xmlFile == null || xsdFilename.isEmpty())
        {
            logger.error("\nSchema file or Xml file not provided!");
            return null;
        }
        return super.unmarshal(xsdFilename, xmlFile, IngestProfile.class);
	}
}
