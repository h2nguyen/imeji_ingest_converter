/**
 * 
 */
package core.vo.generated.formats;

import java.util.Arrays;
import java.util.List;

import core.vo.generated.formats.interfaces.IMdProfileFormat;

/**
 * @author hnguyen
 * @param <E>
 * @param <T>
 * @param <T>
 *
 */
public abstract class NormFormat {
	
	public static String[] norm(String string) {
		System.setProperty("file.encoding", "UTF-8");

		String[] filteredString = new String[2];

		filteredString[0] = string;

		filteredString[1] = string.toLowerCase().replace("-", "_")
				.replace("ä", "ae").replace("ö", "oe").replace("ü", "ue")
				.replace("ß", "ss");

		return filteredString;
	}
	
	public static String norm1(String string) {
		System.setProperty("file.encoding", "UTF-8");

		return string.toLowerCase().replace("-", "_")
				.replace("ä", "ae").replace("ö", "oe").replace("ü", "ue")
				.replace("ß", "ss");


	}
	
	public static List<?> enum2list(Class<? extends IMdProfileFormat> cls) {
		return (List<?>) Arrays.asList(cls.getEnumConstants());		
	}
	
	public static List<?> enum2list(Enum<?> enumCls) {
		return (List<?>) Arrays.asList(enumCls.getClass().getEnumConstants());		
	}
}
