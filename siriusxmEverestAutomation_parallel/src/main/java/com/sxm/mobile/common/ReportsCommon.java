package com.sxm.mobile.common;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import com.sxm.framework.dto.handler.EnvirnomentHandler;

public class ReportsCommon {

	private static String type = EnvirnomentHandler.getInstance()
			.getEnvirnoment().getDeviceType();
	private static LayoutResourceHandler layout = LayoutResourceHandler
			.getInstance(type);
	private static RefactorReportWriter refactorReportWriter = new RefactorReportWriter();
	private static RefactorReportCSVWriter refactorReportCSVWriter = new RefactorReportCSVWriter();

	public static void write2Report(Dimension dimensions1, Dimension screen,
			String pageName, String reference, String key1, String key2,
			String perfectoReportLink,
			WebDriver driver) {
		float screenWidth = screen.getWidth();
		float screenHeight = screen.getHeight();

		float elementWidth = (float) dimensions1.getWidth();
		float elementHeight = (float) dimensions1.getHeight();

		Float calculatedElementwidthPercent = (elementWidth / screenWidth) * 100;

		Float calculatedElementheightPercent = (elementHeight / screenHeight) * 100;

		Float expectedwidthPercent = Float.parseFloat(layout
				.getPropertyElement(key1));
		Float expectedheightPercent = Float.parseFloat(layout
				.getPropertyElement(key2));

		// Writing width details to Excel Report
		refactorReportWriter.writeRowToSheet(key1, pageName, reference,
				"Width", expectedwidthPercent + "",
				calculatedElementwidthPercent + "", 
				perfectoReportLink + "", driver);

		// Writing to height details Excel Report
		refactorReportWriter.writeRowToSheet(key2, pageName, reference,
				"Height", expectedheightPercent + "",
				calculatedElementheightPercent + "", 
				perfectoReportLink+ "", driver);

	}
    /**
     * Method for writing the data to CSV file
     * @param dimensions1
     * @param screen
     * @param pageName
     * @param reference
     * @param key1
     * @param key2
     * @param driver
     */
	public static void write2CSVReport(Dimension dimensions1, Dimension screen,
			String pageName, String reference, String key1, String key2,
			WebDriver driver) {
		float screenWidth = screen.getWidth();
		float screenHeight = screen.getHeight();

		float elementWidth = (float) dimensions1.getWidth();
		float elementHeight = (float) dimensions1.getHeight();

		Float calculatedElementwidthPercent = (elementWidth / screenWidth) * 100;

		Float calculatedElementheightPercent = (elementHeight / screenHeight) * 100;

		Float expectedwidthPercent = Float.parseFloat(layout
				.getPropertyElement(key1));
		Float expectedheightPercent = Float.parseFloat(layout
				.getPropertyElement(key2));
		// Writing width details to CSV Report
		refactorReportCSVWriter.write2CSVReport(key1, pageName, reference,
				"Width", expectedwidthPercent + "",
				calculatedElementwidthPercent + "", driver);
		// Writing to height details CSV Report
		refactorReportCSVWriter.write2CSVReport(key2, pageName, reference,
				"Height", expectedheightPercent + "",
				calculatedElementheightPercent + "", driver);
	}

	public static void closeCSV() {
		refactorReportCSVWriter.closeApp();
	}

}
