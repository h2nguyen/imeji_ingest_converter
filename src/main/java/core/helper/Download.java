/**
 * 
 */
package core.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author hnguyen
 * 
 */
public class Download {

	/**
	 * 
	 */
	public Download() {
		// TODO Auto-generated constructor stub
	}

	public static int getXML(String urlString) {
		try {
			URL url = new URL(urlString);
			URLConnection connection = url.openConnection();
			// connection.setRequestProperty("User-Agent", userAgent);
			// connection.setRequestProperty("Cookie", cookie);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new FileWriter("file.xml"));
			// connection.close();
			String line = "";
			while ((line = br.readLine()) != null) {
				bw.write(line + '\n');
			}

			bw.flush();
			br.close();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();

			return -1;
		}

		return 1;
	}
}
