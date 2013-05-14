package module.zusearchive.controller;

import java.io.File;

import core.vo.imeji.MetadataProfile;


public class ZuseXml2ImejiIngestObject {

	private MetadataProfile metadataProfile;
	private File zuseXmlFile;
	private File imejiXmlFile;
	
	public ZuseXml2ImejiIngestObject(String zuseXmlFileInput, String imejiXmlFileOutput) {
		this(new File(zuseXmlFileInput), new File(imejiXmlFileOutput));
	}
	
	public ZuseXml2ImejiIngestObject(File zuseXmlFileInput, File imejiXmlFileOutput) {
		this.zuseXmlFile = zuseXmlFileInput;
		this.imejiXmlFile = imejiXmlFileOutput;
	}
	
	public MetadataProfile getMetadataProfileFromXmlFile(File inputFile) {
		return parseXmlFileToMetadataProfile(inputFile);
	}
	
	private MetadataProfile parseXmlFileToMetadataProfile(File inputFile) {
		MetadataProfile mdp = new MetadataProfile();
		
		return mdp;
	}
	

}
