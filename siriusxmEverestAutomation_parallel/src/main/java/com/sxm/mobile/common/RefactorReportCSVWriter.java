/**
 * 
 */
package com.sxm.mobile.common;

/**
 * @author HCL
 *
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;

import com.csvreader.CsvWriter;

public class RefactorReportCSVWriter {
	private final static Logger LOGGER = Logger
			.getLogger(RefactorReportCSVWriter.class.getName());
	private volatile static CsvWriter csvOutput = null;
	private volatile static boolean alreadyExists;
	private volatile static int i = 1;

	public RefactorReportCSVWriter() {
		LOGGER.info("::RefactorReportCSVWriter::");
		System.out.println("::RefactorReportCSVWriter::");
		String outputFile = "src/test/resources/Report.csv";
		alreadyExists = new File(outputFile).exists();
		if (alreadyExists) {
			new File(outputFile).delete();
		}
		alreadyExists = new File(outputFile).exists();
		LOGGER.info("write2CSVReport ");
		try {
			csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');
			csvOutput.flush();
			csvOutput.write("S.No");
			csvOutput.write("Page Name ");
			csvOutput.write("Reference UX ");
			csvOutput.write("UI Control Name ");
			csvOutput.write("Dimension ");
			csvOutput.write("PExpected Dimension % ");
			csvOutput.write("Actual Dimension % ");
			csvOutput.endRecord();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void write2CSVReport(String UiName, String pageName,
			String reference, String dim, String expectedPercent,
			String actualPercent, WebDriver driver) {
		try {
			LOGGER.info("::write2CSVReport::");
			csvOutput.write(String.valueOf(i));
			csvOutput.write(UiName);
			csvOutput.write(pageName);
			csvOutput.write(reference);
			csvOutput.write(dim);
			csvOutput.write(expectedPercent);
			csvOutput.write(actualPercent);
			csvOutput.endRecord();
			i++;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void closeApp() {
		if (csvOutput != null) {
			csvOutput.close();
		}
	}
}
