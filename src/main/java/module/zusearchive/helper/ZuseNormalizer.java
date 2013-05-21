/**
 * 
 */
package module.zusearchive.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import module.zusearchive.vo.generated.formats.ZuseNormFormat;

/**
 * @author hnguyen
 *
 */
public class ZuseNormalizer {
	private File inputFile;
	private File outputFile;
	
	public ZuseNormalizer(String inputFilename) {
		this(new File(inputFilename));
	}
	
	public ZuseNormalizer(String inputFilename, String outputFilename) {
		this(new File(inputFilename),new File(outputFilename));
	}
	
	public ZuseNormalizer(File in) {
		this(in, new File(ZuseNormalizer.getOutputFilename(in.getName())));		
	}
	
	public ZuseNormalizer(File in, File out) {
		this.setInputFile(in);
		this.setOutputFile(out);
	}
	
	private static String getOutputFilename(String inputFilename) {
		int i = inputFilename.lastIndexOf('.');
		if(i < 0)
			return inputFilename + "_normalized";
		return inputFilename.substring(0, i) + "_normalized" + inputFilename.substring(i);		
	}

	public File getInputFile() {
		return inputFile;
	}

	public void setInputFile(File inputFile) {
		this.inputFile = inputFile;
	}

	public File getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(File outputFile) {
		this.outputFile = outputFile;
	}
	

	public File normalizeFile() {
		if(this.normalizeProcess(this.inputFile,this.outputFile))
			return this.outputFile;
		return null;
	}
	
	public File getNormalizedFile(String inputFilename, String outputFilename) {
		File inputFile = new File(inputFilename);
		File outputFile = new File(outputFilename);
		
		if(this.normalizeProcess(inputFile, outputFile))
			return outputFile;
		return null;
	}
	
	
	private boolean normalizeProcess(File inputFile, File outputFile) {
		try {
			
			if(!inputFile.exists()) {				
				return false;
			}
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
	        	         
	        String line = "";

	        String oldString = "";
	        
	        while((line = reader.readLine()) != null) {
	        	oldString += line + "\r\n";
	        }
	        
	        reader.close();
	        
	        String newString = new String(oldString);
	        
	        for (int i = 0; i < ZuseNormFormat.KEYSTOREPLACE_OUL.length; i++) {
	        	newString = newString.replaceAll(ZuseNormFormat.KEYSTOREPLACE_OUL[i][0],
	        			ZuseNormFormat.KEYSTOREPLACE_OUL[i][1]);
	        }
	        
	        FileWriter writer = new FileWriter(outputFile);
	        writer.write(newString);	        
	        writer.close();

	    } catch (IOException ioe) {
	        ioe.printStackTrace();
	    }
		
		return true;
	}
}
