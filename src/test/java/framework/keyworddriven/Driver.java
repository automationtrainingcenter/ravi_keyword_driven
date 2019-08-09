package framework.keyworddriven;

import java.lang.reflect.Method;

public class Driver {
	public static void main(String[] args) {
		Keywords keywords = new Keywords();
		Method[] methods = keywords.getClass().getMethods();
		
		
		// creaet ExcelHelper class objectws to read the data from excel files
		ExcelHelper caseDoc = new ExcelHelper();
		ExcelHelper stepDoc = new ExcelHelper();

		// open testcases and teststeps documents
		caseDoc.openExcel("", "hrmkeywords.xls", "testcases");
		stepDoc.openExcel("", "hrmkeywords.xls", "teststeps");

		int tcrows = caseDoc.getRowCount();
		for (int i = 1; i <= tcrows; i++) {
			// get the run mode of every row
			String runMode = caseDoc.getCellData(i, 2);
			// if runmode == yes then get test case name from test case doc column 2
			if (runMode.equalsIgnoreCase("yes")) {
				String tcdTCName = caseDoc.getCellData(i, 1);
				for (int j = 0; j <= stepDoc.getRowCount(); j++) {
					// get the test case name from test step doc column 1
					String tsdTCName = stepDoc.getCellData(j, 0);
					// compare test case doc tcName and test step doc tcName
					if(tcdTCName.equals(tsdTCName)) {
//						// if they are equals execute steps of that test case
						try {
							String testStepName = stepDoc.getCellData(j, 1);
							String locType = stepDoc.getCellData(j, 2);
							String locValue = stepDoc.getCellData(j, 3);
							String action = stepDoc.getCellData(j, 4);
							String data = stepDoc.getCellData(j, 5);
							for (Method method : methods) {
								if (method.getName().equals(action)) {
									method.invoke(keywords, locType, locValue, data);
									break;
								}
							}
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
							
						
						
					}
				}
			}
		}

	}

}
