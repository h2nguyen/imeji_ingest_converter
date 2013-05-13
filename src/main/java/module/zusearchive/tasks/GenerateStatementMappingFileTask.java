package module.zusearchive.tasks;

import java.util.Collection;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import module.zusearchive.jaxb.JaxbIngestProfileZuse;
import module.zusearchive.jaxb.ZuseArchiveSchemaFilename;
import core.j2j.misc.LocalizedString;
import core.jaxb.JaxbUtil;
import core.mapper.StatementIdMapper;
import core.mapper.StatementsIdMapper;
import core.vo.MetadataProfile;
import core.vo.Statement;

public class GenerateStatementMappingFileTask extends SwingWorker<String, String> {

	protected String profileFilename;
	protected String outputFilename;
	protected JProgressBar progressBar;
	protected JLabel label;
	
	public GenerateStatementMappingFileTask(String profileFilename, JProgressBar progressBar, JLabel lblNotification) {		
		this(profileFilename, getOutputFilename(profileFilename), progressBar, lblNotification);
	}

	public GenerateStatementMappingFileTask(String profileFilename, String outputFilename, JProgressBar progressBar, JLabel lblNotification) {
		
		this.profileFilename = profileFilename;
		this.outputFilename = outputFilename;
		this.progressBar = progressBar;
		this.label = lblNotification;
		
	}
	
	private static String getOutputFilename(String inputFilename) {
		int i = inputFilename.lastIndexOf('.');
		if(i < 0)
			return inputFilename + "_stsMapped";
		return inputFilename.substring(0, i) + "_stsMapped" + inputFilename.substring(i);		
	}

	@Override
	protected String doInBackground() throws Exception {
		
		MetadataProfile mdp = new JaxbIngestProfileZuse().unmarshalMdProfile(this.profileFilename);
		
		Collection<Statement> stCol = mdp.getStatements();
		
		StatementsIdMapper stsIdMapper = new StatementsIdMapper();
		
		for (Statement statement : stCol) {
			String attrId = statement.getId().toString();
			String tag = "";

			for (LocalizedString localizedString : statement.getLabels()) {
				tag = localizedString.getValue();
				break;
			}			
			
//			stsIdMapper.getStatementIdMapper().add(new StatementIdMapper(attr,"",tag));
			stsIdMapper.getStatementIdMapper().add(new StatementIdMapper(attrId,attrId,tag));
		}
		
		JaxbUtil.marshal(ZuseArchiveSchemaFilename.ZUSE_STATEMENTSIDMAPPING_XSDFILE, this.outputFilename, stsIdMapper);
		return this.outputFilename;
	}

	
	public void done() {
		
	}
}
