/**
 * 
 */
package core.vo.generated.formats;

import core.vo.generated.formats.interfaces.IMdProfileFormat;

/**
 * @author hnguyen
 * @param <T>
 *
 */
public abstract class NormFormat {	

	public abstract Enum<? extends IMdProfileFormat> geteMdProfileFormat();

	public abstract void seteMdProfileFormat(Enum<? extends IMdProfileFormat> eMdProfileFormat);

	public abstract String[][] getKeyStorePlaces();

	public abstract void setKeyStorePlace(String[][] keyStorePlaces);
	
	public static String[] norm(String string) {

		System.setProperty("file.encoding", "UTF-8");

		String[] filteredString = new String[2];

		filteredString[0] = string;

		filteredString[1] = string.toLowerCase().replace("-", "_")
				.replace("ä", "ae").replace("ö", "oe").replace("ü", "ue")
				.replace("ß", "ss");

		return filteredString;
	}
}
