package com.sxm.mobile.aod.tests;

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
public class HomeTest extends RefactorMobileTestCase {
	private final static Logger LOGGER = Logger.getLogger(HomeTest.class.getName());
	private Map<String, UserObj> users = UserHandler.getInstance(locale, env).getUserMap();
	private String referenceurl;
	public UserObj usr = null;

	/*
	 * @org.testng.annotations.BeforeClass(alwaysRun = true) public void login()
	 * throws InValidOSException, InValidToolException {
	 * LOGGER.info(users.get("prod1").getUserName());
	 * driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	 * getPageFactory().getLogin().login(); Common.impicitWait(5); }
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

	/**
	 * MOBANDEVER-479 Validation of Home - Footer
	 */
	@Test(suiteName = "Home", testName = "Home", description = "Home - Footer", enabled = true, groups = {
			"MOBANDEVER-479" })
	public void verifyHomeFooter() {
		Common common = new Common(driver);
		Common.log("verifying Home Footer elements MOBANDEVER-479");
		try {
			Common.impicitWait(5);
			getPageFactory().getHome().homeFooter();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-468 Validation of Home - Super Categories – Category Listing
	 * Header
	 */
	@Test(suiteName = "Home", testName = "Home", description = "Home - Super Categories – Category Listing Header", enabled = true, groups = {
			"MOBANDEVER-468" })
	public void verifyHomecategories() {
		Common common = new Common(driver);
		Common.log("verifying Home Super Categories elements MOBANDEVER-468");
		try {
			Common.impicitWait(5);
			getPageFactory().getHome().homeCategories();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-459 Validation of Home – Supercategory Navigation tabs
	 */
	@Test(suiteName = "Home", testName = "Home", description = "Home – Supercategory Navigation tabs", enabled = true, groups = {
			"MOBANDEVER-459" })
	public void verifyHomeSuperCategorytab() {
		Common common = new Common(driver);
		Common.log("verifying Home – Supercategory Navigation tabs MOBANDEVER-459");
		try {
			Common.impicitWait(5);
			getPageFactory().getHome().homeCategories();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	@Test(suiteName = "Home", testName = "Home", description = "Home - Super Categories – Linear Hero Carousel", enabled = true, groups = {
			"MOBANDEVER-463" })
	public void verifyHomeSuperCategoryCarousel() {
		Common common = new Common(driver);
		Common.log("verifying Home – Supercategory Linear Hero Carousel MOBANDEVER-463");
		try {
			Common.impicitWait(5);
			getPageFactory().getHome().homeCategories();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-466 Validation of Home - Super Categories - Carousel Content
	 * Tiles
	 */
	@Test(suiteName = "Home", testName = "Home", description = "Home - Super Categories - Carousel Content Tiles", enabled = true, groups = {
			"MOBANDEVER-466" })
	public void verifyHomeSuperCategoryCarouselContent() {
		Common common = new Common(driver);
		Common.log("verifying Home – Supercategory Carousel Content Tiles MOBANDEVER-466");
		try {
			Common.impicitWait(5);
			getPageFactory().getHome().homeCategories();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-467 validation of Home - Super Categories – Category Listing
	 */
	@Test(suiteName = "Home", testName = "Home", description = "Home - Super Categories – Category Listing", enabled = true, groups = {
			"MOBANDEVER-467" })
	public void verifyHomeSuperCategoryCategoryListing() {
		Common common = new Common(driver);
		Common.log("verifying Home - Super Categories – Category Listing MOBANDEVER-467");
		try {
			Common.impicitWait(5);
			getPageFactory().getHome().homeCategories();
			getPageFactory().getHome().CategoryListing();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

}
