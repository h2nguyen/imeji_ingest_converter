package module.zusearchive.misc;

import java.io.File;
import java.util.ArrayList;

public class CreateZuseCollectionFolder {

	public CreateZuseCollectionFolder() {
		// TODO Auto-generated constructor stub
	}

	public static void createFolders(ArrayList<String> folders) {

		for (String folder : folders) {
			File theDir = new File(folder);

			// if the directory does not exist, create it
			if (!theDir.exists()) {
				System.out.println("creating directory: " + theDir.getAbsolutePath());
				boolean result = theDir.mkdir();

				if (result) {
					System.out.println("DIR created");
				}
			}
		}

	}

}
