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
import org.openqa.selenium.Dimension;
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

	/*
	 * EVQAAND-152:MOBANDEVER-479 Validation of Home - Footer
	 */
	@Test(suiteName = "Home", testName = "Home", description = "Home - Footer", enabled = true, groups = {
			"EVQAAND-152:MOBANDEVER-479" })
	public void verifyHomeFooter() {
		Common common = new Common(driver);
		Common.log("verifying Home Footer elements -> EVQAAND-152:MOBANDEVER-479");
		try {
			Common.impicitWait(5);
			getPageFactory().getHome().homeFooter();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/*
	 * EVQAAND-31: MOBANDEVER-468: Validation of Home - Super Categories – Category Listing Header
	 * EVQAAND-177: MOBANDEVER-459: Home – Supercategory Navigation tabs
	 */
	@Test(suiteName = "Home", testName = "Home", description = "Home - Super Categories – Category Listing Header", enabled = true, groups = {
			"EVQAAND-31:EVQAAND-177:MOBANDEVER-468" })
	public void verifyHomecategories() {
		Common common = new Common(driver);
		Common.log("verifying Home Super Categories elements -> EVQAAND-31,EVQAAND-177:MOBANDEVER-468");
		try {
			Common.impicitWait(5);
			getPageFactory().getHome().homeCategories();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * EVQAAND-177
	 * MOBANDEVER-459 Validation of Home – Supercategory Navigation tabs
	 */
	@Test(suiteName = "Home", testName = "Home", description = "Home – Supercategory Navigation tabs", enabled = true, groups = {
			"EVQAAND-177:MOBANDEVER-459" })
	public void verifyHomeSuperCategoryNavigationtab() {
		Common common = new Common(driver);
		Common.log("verifying Home – Supercategory Navigation tabs EVQAAND-177:MOBANDEVER-459");
		try {
			Common.impicitWait(5);
			driver.closeApp();
			driver.launchApp();
			getPageFactory().getHome().homeCategories();
			getPageFactory().getHome().verifyNavigationTab();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/*
	 * TBD: To update this test to validate Carousel. Currently disabling this test
	 * EVQAAND-145: MOBANDEVER-463: Home - Super Categories – Linear Hero Carousel
	 */
	@Test(suiteName = "Home", testName = "Home - Super Categories – Linear Hero Carousel", description = "Home - Super Categories – Linear Hero Carousel", 
			enabled = false, groups = {"EVQAAND-145:MOBANDEVER-463"})
	public void verifyHomeSuperCategoryLinearCarousel() {
		Common common = new Common(driver);
		Common.log("verifying Home – Supercategory Linear Hero Carousel -> EVQAAND-145:MOBANDEVER-463");
		try {
			Common.impicitWait(5);
			driver.closeApp();
			driver.launchApp();
			//getPageFactory().getHome().homeCategories();
			getPageFactory().getHome().verifyLinearHeroCausosels();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/*
	 * TBD: To update this test to validate Carousel. Currently disabling this test
 	 * EVQAAND-40: MOBANDEVER-466 Validation of Home - Super Categories - Carousel Content
  	 * Tiles
	 */
	@Test(suiteName = "Home", testName = "Home", description = "Home - Super Categories - Carousel Content Tiles", enabled = true, groups = {
			"EVQAAND-40:MOBANDEVER-466" })
	public void verifyHomeSuperCategoryCarouselContent() {
		Common common = new Common(driver);
		Common.log("verifying Home – Supercategory Carousel Content Tiles -> EVQAAND-40: MOBANDEVER-466");
		try {
			Common.impicitWait(5);
			driver.closeApp();
			driver.launchApp();
			getPageFactory().getHome().homeCategories();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/*
	 * EVQAAND-136: MOBANDEVER-467 validation of Home - Super Categories – Category Listing
	 */
	@Test(suiteName = "Home", testName = "Home - Super Categories – Category Listing", description = "Home - Super Categories – Category Listing for Music,News, Sports", enabled = true, groups = {
			"EVQAAND-136:MOBANDEVER-467" })
	public void verifyHomeSuperCategoryCategoryListing() {
		Common common = new Common(driver);
		Common.log("verifying Home - Super Categories – Category Listing EVQAAND-136:MOBANDEVER-467");
		try {
			Common.impicitWait(5);
			driver.closeApp();
			driver.launchApp();
			getPageFactory().getHome().homeCategories();
			getPageFactory().getHome().CategoryListing();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}
	
	/*
	 * verifying Home – For You – Carousel Content Tiles
	 * EVQAAND-301:MOBANDEVER-462
	 */
	@Test(suiteName = "Home", testName = "Home", description = "Home – For You – Validate Carousel Content Tiles Count", enabled = true, groups = {
			"EVQAAND-301:MOBANDEVER-462" })
	public void verifyHomeForYouCarouselContentTiles() {
		Common common = new Common(driver);
		Common.log("verifying Home – For You – Carousel Content Tiles -> EVQAAND-301:MOBANDEVER-462");
		try {
			Common.impicitWait(5);
			driver.closeApp();
			driver.launchApp();
			getPageFactory().getHome().verifyForYouCarousels();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}
	
	/*
	 * Note: This test is same as EVQAAND-301
	 * TBD: To include Swipe operation till "For You" shows up
	 * verifying Home – For You - Hero Tiles
	 * EVQAAND-250: MOBANDEVER-1107
	 */
	@Test(suiteName = "Home", testName = "Home - For You - Hero Tiles", description = "Home - For You - Hero Tiles [Same as EVQAAND-250]", enabled = true, groups = {
			"EVQAAND-250:MOBANDEVER-1107" })
	public void verifyHomeForYouHeroTiles() {
		Common common = new Common(driver);
		Common.log("verifying Home - For You - Hero Tiles EVQAAND-250:MOBANDEVER-1107");
		try {
			Common.impicitWait(5);
			driver.closeApp();
			driver.launchApp();
			getPageFactory().getHome().verifHeroCaurosels();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/*
	 * TBD: To include Swipe operation till "For You" shows up
	 * EVQAAND-19:MOBANDEVER-464: Home - Super Categories – Content Carousels
	 */
	@Test(suiteName = "Home", testName = "Home", description = "Home - Super Categories – Content Carousels", enabled = true, groups = {
			"EVQAAND-19:MOBANDEVER-464" })
	public void verifyHomeSuperCarouselContent() {
		Common common = new Common(driver);
		Common.log("verifying Home - Super Categories – Content Carousels EVQAAND-19:MOBANDEVER-464");
		try {
			Common.impicitWait(5);
			driver.closeApp();
			driver.launchApp();
			getPageFactory().getHome().homeCategories();
			getPageFactory().getHome().verifyForYouCarousels();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}
}
