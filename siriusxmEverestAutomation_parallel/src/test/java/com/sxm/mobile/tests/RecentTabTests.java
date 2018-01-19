package com.sxm.mobile.tests;

import org.testng.annotations.Test;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

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
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
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
import com.sxm.framework.exception.AndriodException;
import com.sxm.framework.exception.InValidOSException;
import com.sxm.framework.exception.InValidToolException;
import com.sxm.mobile.pages.Common;
import com.sxm.mobile.pages.Login;
import com.sxm.mobile.pages.factory.SXMMobilePageFactory;

@SuppressWarnings({ "unused", "static-access" })
@Listeners({ Screenshot.class })
public class RecentTabTests extends RefactorMobileTestCase {
	private final static Logger LOGGER = Logger.getLogger(RecentTabTests.class.getName());
	private Map<String, UserObj> users = UserHandler.getInstance(locale, env).getUserMap();
	private String referenceurl;
	public UserObj usr = null;

	/*
	 * Login to app
	 */
	@Parameters({ "platform", "login_credential" })
	@org.testng.annotations.BeforeClass(alwaysRun = true)
	public void initializeDrivers(String plt, @Optional String loginCredential, ITestContext context)
			throws InValidOSException, InValidToolException {
		Common common = new Common(driver);
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			getPageFactory().getLogin().loginBeforeStart(loginCredential);
			Common.impicitWait(10);
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/*
	 * Verify User navigates to Recent Tab for a normal user 
	 * This is existing user so would be mostly having tracks in recently played as part of test suite
	 */
	@Test(suiteName = "RecentTab", testName = "Recent tab - Verify user is able to navigate to page", description = "Recent tab - Verify user is able to navigate to page",priority=1, enabled = true, groups = {
			"EVQAAND-399" })
	public void RecentPage_RecentTabScreen() {
		Common common = new Common(driver);
		Common.log("Recent tab - Verify user is able to navigate to page");
		try 
		{
			common.implicitWait(5);
			Common.log("Navigate to Recent Tab");
			getPageFactory().getRecentPage().clickOnRecentTab();
			Common.log("Verify mini NPL shows on the page");
			getPageFactory().getEvehome().miniBar();
			Common.log("Verify the elements displayed on the page");
			Assert.assertTrue(getPageFactory().getRecentPage().verifyRecentPage(false), "Not all Recent Tab elements were found on page");
		} catch (AndriodException ex) 
		{
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}
	
	/*
	 * Verify User navigates to Recent Tab for a normal user 
	 * This is existing user so would be mostly having tracks in recently played as part of test suite
	 */
	@Test(suiteName = "RecentTab", testName = "Recent tab - Verify user is able to delete Recent Played Tile", description = "Recent tab - Verify user is able to delete Recent Played Tile",priority=1, enabled = true, groups = {
			"" })
	public void RecentPage_DeleteRecentlyPlayed() {
		Common common = new Common(driver);
		Common.log("Recent tab - Verify user is able to delete Recent Played Tile");
		try 
		{
			common.implicitWait(5);
			Common.log("Navigate to Recent Tab");
			getPageFactory().getRecentPage().clickOnRecentTab();
			Common.log("Delete the recent tile");
			getPageFactory().getRecentPage().deleteRecentTile();
			Common.log("Recent tile was deleted");
		} catch (AndriodException ex) 
		{
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	
}
