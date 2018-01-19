package com.sxm.mobile.common;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import org.openqa.selenium.OutputType;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.internal.IResultListener;

import com.perfecto.reportium.test.result.TestResultFactory;
import com.perfectomobile.httpclient.utils.FileUtils;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.NetworkMode;
import com.selenium.mobile.base.MobileTestCase;
import com.selenium.mobile.base.RefactorMobileTestCase;
import com.sxm.mobile.pages.Common;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

/**
 * Created by andrey.smirnov on 14.06.2016.
 */
public class ListenerTest extends RefactorMobileTestCase implements IResultListener {
	 
	/*private ExtentReports reporter = new ExtentReports("build/"+obj.getClass()+".html", true, DisplayOrder.NEWEST_FIRST,
			NetworkMode.ONLINE, Locale.ENGLISH);*/
	private ExtentTest testReporter;

	@Override
	public void onTestStart(ITestResult result) {
		if(className=="report"){
			on(result.getTestClass().getName());
		}
		testReporter = reporter.startTest(result.getMethod().getMethodName(), "Some description");
		testReporter.log(LogStatus.INFO, "Starting test " + result.getMethod().getMethodName());
		//perfectoReportCreation(result.getTestClass().getName(),result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		reportiumClient.testStop(TestResultFactory.createSuccess());
		String screenShotPath;
		try {
			screenShotPath = takescreenshot(driver);
			testReporter.log(LogStatus.PASS, "Snapshot below: " + testReporter.addScreenCapture(screenShotPath));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Throwable Exception = result.getThrowable();
		reportiumClient.testStop(TestResultFactory.createFailure("Fail",Exception));
		// TODO Auto-generated method stub
		String screenShotPath;
		try {
			screenShotPath = takescreenshot(driver);
			System.out.println(driver);
			testReporter.log(LogStatus.FAIL, "Snapshot below: " + testReporter.addScreenCapture(screenShotPath));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onConfigurationSuccess(ITestResult itr) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onConfigurationFailure(ITestResult itr) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onConfigurationSkip(ITestResult itr) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onFinish(ITestContext context) {
		reporter.flush();
		// reporter.close();
		String reportURL = reportiumClient.getReportUrl();
		Common.log("Link :-"+reportURL);
	}
	public String takescreenshot(AppiumDriver driver) throws IOException {
		String imageName = System.currentTimeMillis() + ".png";
		File srcfile = driver.getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir") + "\\screenshot\\" + imageName;
		File destination = new File(dest);
		FileUtils.copyFile(srcfile, destination);
		return dest;
	}
}