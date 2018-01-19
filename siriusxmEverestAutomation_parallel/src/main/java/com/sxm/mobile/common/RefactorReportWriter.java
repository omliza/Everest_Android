package com.sxm.mobile.common;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import jxl.write.WritableSheet;
import jxl.write.WriteException;

public class RefactorReportWriter {
	private volatile static RefactorExcelReportUtil we;
	private volatile static int i = 1;
	private volatile static WritableSheet sheet;

	public RefactorReportWriter() {
		we = RefactorExcelReportUtil.getInstance();

		String filepath = "src/test/resources/Report.xls";
		filepath = System.getProperty("user.dir") + "/" + filepath;
		we.setOutputFile(filepath);
		try {
			sheet = we.getSheet();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void writeRowToSheet(String UiName, String pageName, String reference, String dim, String expectedPercent, 
			String actualPercent, String perfectoReportLink,
			WebDriver driver) {
//		Reporter.log("-Writing a Row number-" + i, true);
		try {
			we.createContent(sheet, 1, i, i + "");
			we.createContent(sheet, 2, i, pageName);
			we.createContent(sheet, 3, i, reference);
			we.createContent(sheet, 4, i, UiName);
			we.createContent(sheet, 5, i, dim);
			we.createContent(sheet, 6, i, expectedPercent);
			we.createContent(sheet, 7, i, actualPercent);
			we.createContent(sheet, 8, i, perfectoReportLink);
			i++;
		} catch (WriteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}