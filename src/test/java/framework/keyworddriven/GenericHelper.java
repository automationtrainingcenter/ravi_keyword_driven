package framework.keyworddriven;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GenericHelper {
	
	//time stamp method
	public static String getTime() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("dd_MMM_yy-HH_mm_ss");
		return df.format(date);
	}

	public static String getFilePath(String folderName, String fileName) {
		/*
		 * This method returns the path of the any file which are inside your project
		 * folder or inside another folder of project folder.
		 */
		return System.getProperty("user.dir") + File.separator + folderName + File.separator + fileName;
	}
	
	

}
