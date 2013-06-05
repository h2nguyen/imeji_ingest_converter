package core;

import java.beans.IntrospectionException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.xml.bind.JAXBException;

import module.zusearchive.converter.ZuseConverter;
import module.zusearchive.helper.ZuseNormalizer;
import module.zusearchive.jaxb.JaxbZuseGenericObject;
import module.zusearchive.vo.generated.OUnterlagen;
import module.zusearchive.vo.generated.OZuse;
import module.zusearchive.vo.generated.formats.ZuseNormFormat.ZuseMDEnumType;

import org.junit.Test;
import org.xml.sax.SAXException;

import core.jaxb.ImejiSchemaFilename;
import core.jaxb.JaxbGenericObject;
import core.jaxb.JaxbUtil;
import core.mapper.ItemsMapperTask;
import core.mapper.MdProfileMapperTask;
import core.mapper.StatementIdMapper;
import core.mapper.StatementsIdMapper;
import core.task.enums.Task;
import core.task.enums.Update;
import core.vo.imeji.Item;
import core.vo.imeji.Items;
import core.vo.imeji.MetadataProfile;

public class ZuseTest {

	// @Test
	public void normalizeProcessTest() {
		String inputFilename = "src/test/resources/_10_entries.xml";
		String outputFilename = "src/test/resources/_10_entries_out.xml";
		ZuseNormalizer n = new ZuseNormalizer(inputFilename, outputFilename);
		n.normalizeFile();
	}

	// @Test
	public void ingestConverterProcessTest() throws JAXBException,
			SAXException, IntrospectionException, FileNotFoundException {
		String filenameUnmarshal = "src/test/resources/_10_entries_out.xml";

		OZuse zo = (new JaxbZuseGenericObject<OZuse>(OZuse.class))
				.unmarshal(filenameUnmarshal);
		JaxbUtil.toString(zo);

		// ZuseConverter zmdpconv = new ZuseConverter();
		//
		// // MetadataProfile mdp = zmdpconv.getMdProfile(oul, "profile name",
		// // "profile description", ZuseEnumType.getEnumList());
		// // JaxbUtil.toString(mdp);
		// // OUnterlagen oul = zo.getoUnterlagen().get(0);
		// // JaxbUtil.toString(oul);
		// // Item item = zmdpconv.getItem(oul, ZuseEnumType.getEnumList());
		// // JaxbUtil.toString(item);
		//
		// Items items = zmdpconv.getItems(zo.getoUnterlagen(),
		// ZuseEnumType.getEnumList());
		// // JaxbUtil.toString(items);
		//
		// String itemsFile = "src/test/resources/_10_items_out.xml";
		//
		// (new JaxbGenericObject<Items>(Items.class)).marshal(itemsFile,
		// items);
	}

	// @Test
	public void mdProfileProcessTest() throws JAXBException, SAXException,
			IntrospectionException, FileNotFoundException {
		String filenameUnmarshal = "src/test/resources/_10_entries_out.xml";

		OZuse zo = (new JaxbZuseGenericObject<OZuse>(OZuse.class))
				.unmarshal(filenameUnmarshal);

		ZuseConverter zmdpconv = new ZuseConverter();

		OUnterlagen oul = zo.getoUnterlagen().get(0);

		MetadataProfile mdp = zmdpconv.getMdProfile(oul,
				"profile name offline", "profile description offline",
				ZuseMDEnumType.getEnumList());
		// JaxbUtil.toString(mdp);

		String mdpFile = "src/test/resources/_mdp_offline_with_statements.xml";

		(new JaxbGenericObject<MetadataProfile>(MetadataProfile.class))
				.marshal(mdpFile, mdp);
	}

	
	//@Test
	public void itemsProcessTest() throws JAXBException, SAXException,
			IntrospectionException, FileNotFoundException {
		String filenameUnmarshal = "src/test/resources/_10_entries_out.xml";

		OZuse zo = (new JaxbZuseGenericObject<OZuse>(OZuse.class))
				.unmarshal(filenameUnmarshal);

		ZuseConverter zmdpconv = new ZuseConverter();

		OUnterlagen oul = zo.getoUnterlagen().get(0);

		MetadataProfile mdp = zmdpconv.getMdProfile(oul,
				"profile name offline", "profile description offline",
				ZuseMDEnumType.getEnumList());		
		
		Items items = zmdpconv.getItems(zo.getoUnterlagen(), ZuseMDEnumType.getEnumList(), mdp);
		
//		JaxbUtil.toString(mdp);
//		JaxbUtil.toString(items);

		String filenameMdp = "src/test/resources/myMdp.xml";
		String filenameItem = "src/test/resources/myItems.xml";
		FileOutputStream fosMdp = new FileOutputStream(new File(filenameMdp));
		FileOutputStream fosItem = new FileOutputStream(new File(filenameItem));

		JaxbGenericObject.writeToOutputStream(mdp, fosMdp);
		JaxbGenericObject.writeToOutputStream(items, fosItem);
	}
	
	// @Test
	public void itemProcessTest() throws JAXBException, SAXException,
			IntrospectionException, FileNotFoundException {
		String filenameUnmarshal = "src/test/resources/_17_items_online.xml";

		Items items = new JaxbGenericObject<Items>(Items.class)
				.unmarshal(filenameUnmarshal);

		Item item = items.getItem().get(0);

		// JaxbGenericObject.toString(item);
		String filename = "src/test/resources/item_online.xml";
		FileOutputStream fos = new FileOutputStream(new File(filename));

		JaxbGenericObject.writeToOutputStream(item, fos);
	}

	// @Test
	public void testCreateStatementIdMapper() throws FileNotFoundException,
			JAXBException, SAXException {
		StatementsIdMapper stsIdMapper = new StatementsIdMapper();

		StatementIdMapper stIdMapper1 = new StatementIdMapper();
		stIdMapper1.setStatementOffline("stOffline1");
		stIdMapper1.setStatementOnline("stOnline1");
		stIdMapper1.setTagName("tagName1");
		StatementIdMapper stIdMapper2 = new StatementIdMapper();
		stIdMapper2.setStatementOffline("stOffline2");
		stIdMapper2.setStatementOnline("stOnline2");
		stIdMapper2.setTagName("tagName2");

		stsIdMapper.getStatementIdMapper().add(stIdMapper1);
		stsIdMapper.getStatementIdMapper().add(stIdMapper2);

		JaxbUtil.marshal(ImejiSchemaFilename.STATEMENTSIDMAPPING_XSDFILE,
				"src/test/resources/mapping.xml", stsIdMapper);

	}

	//@Test
	public void mergeMdProfileMapper() throws FileNotFoundException,
			JAXBException, SAXException, InterruptedException,
			ExecutionException {

		String filenameMdpOnline = "src/test/resources/_mdp_online_empty.xml";
		String filenameMdpOffline = "src/test/resources/_mdp_offline_with_statements.xml";

		MdProfileMapperTask mdpmt = new MdProfileMapperTask(filenameMdpOnline,
				filenameMdpOffline, Task.UPDATE);
		mdpmt.execute();

		MetadataProfile fileMerge = mdpmt.get();

		String filename = "src/test/resources/mergedMdp.xml";
		FileOutputStream fos = new FileOutputStream(new File(filename));

		JaxbGenericObject.writeToOutputStream(fileMerge, fos);

	}

	@Test
	public void generateIngestFileProcessTest() throws FileNotFoundException,
			JAXBException, SAXException, InterruptedException,
			ExecutionException, IntrospectionException {

		String folder = "ingest4";
		
		// create a normalized file from raw file
		String inputFilename = "src/test/resources/"+folder+"/raw_entries.xml";
		String outputFilename = "src/test/resources/"+folder+"/raw_entries_normalized.xml";
		ZuseNormalizer n = new ZuseNormalizer(inputFilename, outputFilename);
		n.normalizeFile();
		
		// get specific metadata profile (here in our case the zuse object) java object from normalized file
		String filenameEntriesNormalizedOffline = "src/test/resources/"+folder+"/raw_entries_normalized.xml";
		OZuse oz = new JaxbZuseGenericObject<OZuse>(OZuse.class).unmarshal(filenameEntriesNormalizedOffline);
		ZuseConverter zmdpconv = new ZuseConverter();
		List<OUnterlagen> ouls = oz.getoUnterlagen();
		// generate an imeji meta data profile from the specific object
		MetadataProfile raw_gen_mdp = zmdpconv.getMdProfile(
				ouls.get(0),
				"Generated metadata profile",
				"The metadata profile is generated from the Zuse object",
				ZuseMDEnumType.getEnumList());
		String filenameMdpOffline = "src/test/resources/"+folder+"/mdp_offline.xml";
		FileOutputStream fos_raw_gen_mdp = new FileOutputStream(new File(filenameMdpOffline));
		JaxbGenericObject.writeToOutputStream(raw_gen_mdp, fos_raw_gen_mdp);
		
		
		// merge meta data profile process
		String filenameMdpOnline = "src/test/resources/"+folder+"/mdp_online.xml"; // download from the internet
		String filenameMdpOffline2 = "src/test/resources/"+folder+"/mdp_offline.xml";
		MdProfileMapperTask mdpmt = new MdProfileMapperTask(filenameMdpOnline, filenameMdpOffline2, Task.UPDATE);
		mdpmt.execute();
		MetadataProfile mdpMerged = mdpmt.get();
		String filenameMdpMerged = "src/test/resources/"+folder+"/mdp_merged.xml";
		FileOutputStream fos_mdp_merged = new FileOutputStream(new File(filenameMdpMerged));
		JaxbGenericObject.writeToOutputStream(mdpMerged, fos_mdp_merged);
		
		
		// generate the imeji items from the specific object (Zuse object) with the provided final merged meta data profile
		String filenameMdpMerged2 = "src/test/resources/"+folder+"/mdp_merged.xml";
		MetadataProfile merged_mdp = new JaxbGenericObject<MetadataProfile>(MetadataProfile.class).unmarshal(filenameMdpMerged2);
		String filenameEntriesNormalizedOffline2 = "src/test/resources/"+folder+"/raw_entries_normalized.xml";
		OZuse oz2 = new JaxbZuseGenericObject<OZuse>(OZuse.class).unmarshal(filenameEntriesNormalizedOffline2);
		ZuseConverter zmdpconv2 = new ZuseConverter();
		List<OUnterlagen> ouls2 = oz2.getoUnterlagen();
		Items items = zmdpconv2.getItems(ouls2, ZuseMDEnumType.getEnumList(), merged_mdp);
		String filenameItemsOffline = "src/test/resources/"+folder+"/items_offline.xml";
		FileOutputStream fos_items = new FileOutputStream(new File(filenameItemsOffline));
		JaxbGenericObject.writeToOutputStream(items, fos_items);
		
		// merge the online items with the offline generated items
		String filenameItemsOnline = "src/test/resources/"+folder+"/items_online.xml"; // download from the internet
		String filenameItemsOffline2 = "src/test/resources/"+folder+"/items_offline.xml";
		ItemsMapperTask ismt = new ItemsMapperTask(filenameItemsOnline,
				filenameItemsOffline2, Task.OVERWRITE, Update.UPDATE_BY_FILENAME);
		ismt.execute();
		Items itemsMerged = ismt.get();
		String filename = "src/test/resources/"+folder+"/mergedItems.xml";
		FileOutputStream fos_final_items_to_ingest = new FileOutputStream(new File(filename));

		JaxbGenericObject.writeToOutputStream(itemsMerged, fos_final_items_to_ingest);

	}
}
