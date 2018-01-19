package com.sxm.mobile.tests;

import org.testng.annotations.Test;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import java.awt.geom.GeneralPath;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.selenium.mobile.base.RefactorMobileTestCase;
import com.sxm.framework.common.AppiumDriverUtil;
import com.sxm.framework.common.Retry;
import com.sxm.framework.common.Screenshot;
import com.sxm.framework.dto.Envirnoment;
import com.sxm.framework.dto.UserObj;
import com.sxm.framework.dto.handler.EnvirnomentHandler;
import com.sxm.framework.dto.handler.UserHandler;
import com.sxm.framework.exception.InValidOSException;
import com.sxm.framework.exception.InValidToolException;
import com.sxm.mobile.pages.Common;
import com.sxm.mobile.pages.Login;
import com.sxm.mobile.pages.factory.SXMMobilePageFactory;

/*
 * These tests are currently not been called\tested
 */
@SuppressWarnings({ "unused", "static-access" })
@Listeners({ Screenshot.class })
public class Everest2 extends RefactorMobileTestCase {
	private final static Logger LOGGER = Logger.getLogger(OnDemandTest.class.getName());
	private Map<String, UserObj> users = UserHandler.getInstance(locale, env).getUserMap();
	private String referenceurl;
	public UserObj usr = null;
	Common common = new Common(driver);
	
	@BeforeClass(alwaysRun = true)
	public void login()throws InValidOSException, InValidToolException {
		LOGGER.info(users.get("prod1").getUserName());
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    getPageFactory().getLogin().login();
		Common.impicitWait(5);
	}
	
	
	@AfterMethod(alwaysRun = true)
	public void homeIcon(){
		try{
			WebElement homeIcon = driver.findElement(
					By.xpath(elementReader.getPropertyElement("Everest.Home")));
			homeIcon.isDisplayed();
			//Common.log("Home icon is present in the screen");
			homeIcon.click();
			Common.log("Successfully clicked on Home icon");
		}
		catch(Exception e){
			Common.log("Home icon is not present in the screen");
			Assert.fail();
		}
	}
	
	
	
	@Test(testName="Validate the related content module for Live Channels",retryAnalyzer = Retry.class,enabled=true,groups={"MOBANDEVER-737"})
	public void validateRelatedContent() throws InterruptedException{
		Common.log("Validating the related content module for Live Channels MOBANDEVER-737");
		getPageFactory().getEvehome().clickOnTalk();
		common.scrollUntilTextExists("Categories");
		getPageFactory().getHoward().clickOnHowardStern();
		getPageFactory().getHoward().validateRelatedcontents();
	}
	
	@Test(testName="Validate the related content module for Disallowed Ondemand shows",retryAnalyzer = Retry.class,enabled=true,groups={"MOBANDEVER-735"})
	public void validateDisallowedRelatedContent() throws InterruptedException{
		Common.log("Validating the related content module for Live Channels MOBANDEVER-735");
		common.scrollUntilTextExists("Categories");
		getPageFactory().getEvehome().clickNews();
		getPageFactory().getEvehome().clickSubCatNews();
		getPageFactory().getEvehome().clickOnDemand();
		getPageFactory().getHoward().validateRelatedcontents();
	}
	
	@Test(testName="Validate the related content module for Unrestricted Ondemand shows",retryAnalyzer = Retry.class,enabled=true,groups={"MOBANDEVER-734"})
	public void validateUnrestrictedOnDemnadRelatedContent() throws InterruptedException{
		Common.log("Validating the related content module for Live Channels MOBANDEVER-734");
		getPageFactory().getEvehome().clickOnTalk();
		common.scrollUntilTextExists("Categories");
		getPageFactory().getHoward().clickOnHowardStern();
		getPageFactory().getHoward().validateRelatedcontents();
	}
	
	
	@Test(testName="Validate the related content module for DMCA Ondemand shows",retryAnalyzer = Retry.class,enabled=true,groups={"MOBANDEVER-733"})
	public void validateDMCAOnDemnadRelatedContent() throws InterruptedException{
		Common.log("Validating the related content module for DMCA ondemand shows MOBANDEVER-733");
		common.scrollUntilTextExists("Dance/Electronic");
		getPageFactory().getEvehome().clickMucisSubPop();
		getPageFactory().getEvehome().clickOnDemand();
		getPageFactory().getHoward().validateDmcaRelatedcontent();
		
	}
	
	@Test(testName="Now Playing - On Demand -Disallowed -Elapsed and remaining time",retryAnalyzer = Retry.class,enabled=true,groups={"MOBANDEVER-636"})
	public void disallowedElapsedTime(){
		Common.log("Validating Disallowed elapsed and remaining time MOBANDEVER-636");
		common.scrollUntilTextExists("Categories");
		getPageFactory().getEvehome().clickNews();
		getPageFactory().getEvehome().clickSubCatNews();
		
	}
	
}
