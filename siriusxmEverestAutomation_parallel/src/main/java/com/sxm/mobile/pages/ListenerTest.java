package com.sxm.mobile.pages;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.omg.CORBA.OMGVMCID;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
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
import com.sxm.framework.dto.UserObj;
import com.sxm.framework.dto.handler.UserHandler;
import com.sxm.framework.exception.AndriodException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

/**
 * Created by andrey.smirnov on 14.06.2016.
 */
public class ListenerTest extends RefactorMobileTestCase implements IResultListener {
	 
	/*private ExtentReports reporter = new ExtentReports("build/"+obj.getClass()+".html", true, DisplayOrder.NEWEST_FIRST,
			NetworkMode.ONLINE, Locale.ENGLISH);*/
	protected static ExtentTest testReporter;
	
	
	WebElement element = null;
	@Override
	public void onTestStart(ITestResult result) {
		//driver code
		AppiumDriver driver = null;
		if(null != result.getTestContext().getAttribute("Driver"))
		{
			driver = (AppiumDriver) result.getTestContext().getAttribute("Driver");
		}
		Common common = new Common(driver);
		if(className=="report"){
			on(result.getTestClass().getName());
		}
		testReporter = reporter.startTest(result.getMethod().getMethodName(), "Report Description");
		testReporter.log(LogStatus.INFO, "Starting test " + result.getMethod().getMethodName());
		perfectoReportCreation(result.getTestClass().getName(),result.getMethod().getMethodName(), driver);
		testReporter.log(LogStatus.INFO, reportiumClient.getReportUrl());
		result.setAttribute("perfectoReportLink", reportiumClient.getReportUrl());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		AppiumDriver driver = null;
		if(null != result.getTestContext().getAttribute("Driver"))
		{
			driver = (AppiumDriver) result.getTestContext().getAttribute("Driver");
		}
		reportiumClient.testStop(TestResultFactory.createSuccess());
		result.setAttribute("perfectoReportLink", reportiumClient.getReportUrl());
		Common.log("On Test Success: " + result.getAttribute("perfectoReportLink"));
		reporter.endTest(testReporter);
		reporter.flush();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		AppiumDriver driver = null;
		if(null != result.getTestContext().getAttribute("Driver"))
		{
			driver = (AppiumDriver) result.getTestContext().getAttribute("Driver");
		}
		Throwable Exception = result.getThrowable();
		reportiumClient.testStop(TestResultFactory.createFailure("Fail",Exception));
		result.setAttribute("perfectoReportLink", reportiumClient.getReportUrl());
		Common.log("On Test Failure: " + result.getAttribute("perfectoReportLink"));
		Common common = new Common(driver);
		
		reporter.endTest(testReporter);
		reporter.flush();
		driver.resetApp();
		common.impicitWait(5);
		common.loginToApp();
		Common.impicitWait(4);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		Throwable Exception = result.getThrowable();
		reportiumClient.testStop(TestResultFactory.createFailure("Skip",Exception));
		// TODO Auto-generated method stub
		reporter.endTest(testReporter);
		reporter.flush();
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
		/*String RpoURL = reportiumClient.getReportUrl();
		System.out.println(RpoURL);*/
		// reporter.close();
		
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