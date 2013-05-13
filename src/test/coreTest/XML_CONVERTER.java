package test.coreTest;
import main.java.core.task.ZuseReplacerTask;


public class XML_CONVERTER {

	public XML_CONVERTER() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		for (int i = 0; i < args.length; i++) {
			System.out.println(args[i]);
		}
		
		
		if(args.length < 2) {
			System.out.println("parameter under 3!");
			return;
		}
		
		String inputFilename = args[0];
		String outputFilename = args[1];
		
		if(inputFilename.equalsIgnoreCase(outputFilename)) {
			System.out.println("input file has the same name as output file!");
			return;
		}
		
		new ZuseReplacerTask(inputFilename, outputFilename, null, null);
	}

}
