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
public class SearchTest extends RefactorMobileTestCase {
	private final static Logger LOGGER = Logger.getLogger(SearchTest.class.getName());
	private Map<String, UserObj> users = UserHandler.getInstance(locale, env).getUserMap();
	private String referenceurl;
	public UserObj usr = null;

	/*
	 * @org.testng.annotations.BeforeClass(alwaysRun = true) public void
	 * login()throws InValidOSException, InValidToolException {
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
	 * validation of Search – Entry Box – Clear button
	 */
	@Test(suiteName = "Search", testName = "Search", description = "Search – Entry Box – Clear button", enabled = true, groups = {
			"MOBANDEVER-692" })
	public void verifySearchClearButton() {
		Common common = new Common(driver);
		Common.log("verifying Search –Entry Box –Clear button MOBANDEVER-692");
		try {
			Common.impicitWait(4);
			getPageFactory().getSearch().clickOnSearch();
			Common.impicitWait(4);
			getPageFactory().getSearch().searchFilter("how");
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * Validation of Search – First Time Search State
	 */
	@Test(suiteName = "Search", testName = "Search", description = "Search – First Time Search State", enabled = true, groups = {
			"MOBANDEVER-694" })
	public void verifyFirstSearchState() {
		Common common = new Common(driver);
		Common.log("Search – First Time Search State MOBANDEVER-694");
		try {
			Common.impicitWait(4);
			getPageFactory().getSearch().clickOnSearch();
			Common.impicitWait(4);
			getPageFactory().getSearch().validateFirstTimeSearch();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * validation of Search – Entry Box
	 */
	@Test(suiteName = "Search", testName = "Search", description = "Search – Entry Box", enabled = true, groups = {
			"MOBANDEVER-254(691)" })
	public void verifySearchEntry() {
		Common common = new Common(driver);
		Common.log("validating Search – Entry Box MOBANDEVER-254");
		try {
			Common.impicitWait(4);
			getPageFactory().getSearch().clickOnSearch();
			Common.impicitWait(4);
			getPageFactory().getSearch().validateSearchEntry();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * Validating Search – Cancel functionality
	 */
	@Test(suiteName = "Search", testName = "Search", description = "Search – Cancel", enabled = true, groups = {
			"MOBANDEVER-241(693)" })
	public void verifySearchcancel() {
		Common common = new Common(driver);
		Common.log("validating Search – Cancel  MOBANDEVER-241");
		try {
			Common.impicitWait(4);
			getPageFactory().getSearch().clickOnSearch();
			Common.impicitWait(4);
			getPageFactory().getSearch().searchFilter("si");
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * validating Search – Previous/ Recent Searches - Header functionality
	 */
	@Test(suiteName = "Search", testName = "Recent Searches - Header", description = "Search – Previous/ Recent Searches - Header", enabled = true, groups = {
			"MOBANDEVER-283(695)" })
	public void verifyRecentSearch() {
		Common common = new Common(driver);
		Common.log("validating Search – Previous/ Recent Searches - Header MOBANDEVER-695");
		try {
			Common.impicitWait(4);
			getPageFactory().getSearch().clickOnSearch();
			getPageFactory().getSearch().recentSearch();
			Common.impicitWait(4);
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * validating Search – Previous/ Recent Searches - Header
	 */
	@Test(suiteName = "Search", testName = "Video On Demand Carousel - Tiles", description = "Search – Video On Demand Carousel - Tiles", enabled = true, groups = {
			"MOBANDEVER-380(710)" })
	public void verifyRecentSearchAudioOndemand() {
		Common common = new Common(driver);
		Common.log("validating Search – Previous/ Recent Searches - Header MOBANDEVER-710");
		try {
			Common.impicitWait(4);
			getPageFactory().getSearch().clickOnSearch();
			getPageFactory().getSearch().recentSearchcount();
			Common.impicitWait(4);
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * validating Search – Top Results – View fewer results CTA
	 */
	@Test(suiteName = "Search", testName = " Top Results – View fewer results CTA", description = "Search – Top Results – View fewer results CTA", enabled = true, groups = {
			"MOBANDEVER-374(704)" })
	public void verifySearchTopResults() {
		Common common = new Common(driver);
		Common.log("validating Search – Top Results – View fewer results CTA MOBANDEVER-704");
		try {
			Common.impicitWait(4);
			getPageFactory().getSearch().clickOnSearch();
			getPageFactory().getSearch().searchTopResult();
			Common.impicitWait(4);
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * Validaing Search by Channel Number
	 */
	@Test(suiteName = "Search", testName = "Search by Channel Number", description = "Search - Search by Channel Number", enabled = true, groups = {
			"MOBANDEVER-1005" })
	public void testVerifySearchByChannelNumber() {
		Common common = new Common(driver);
		Common.log("Validating Search by Channel Number MOBANDEVER-1005");
		try {
			Common.impicitWait(4);
			getPageFactory().getSearch().clickOnSearch();
			getPageFactory().getSearch().searchByChannelNumber();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}
	
	
	/**
	 * Validating Search – Suggested Searches
	 * MOBANDEVER-699
	 */
	@Test(suiteName = "Search", testName = "Search – Suggested Searches", description = "Search – Suggested Searches", enabled = true, groups = {
			"MOBANDEVER-699" })
	public void testVerifySearcSuggestedSearches() {
		Common common = new Common(driver);
		Common.log("Validating Search – Suggested Searches-699");
		try {
			Common.impicitWait(4);
			getPageFactory().getSearch().clickOnSearch();
			getPageFactory().getSearch().verifySuggestedSearch();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}
	
	 
		/**
		 * Validating Search – Audio On Demand Carousel
		 * MOBANDEVER-706
		 */
		@Test(suiteName = "Search", testName = "Search – Audio On Demand Carousel", description = "Search – Audio On Demand Carousel", enabled = true, groups = {
				"MOBANDEVER-706" })
		public void testVerifySearchAudioOnDemandCarousel() {
			Common common = new Common(driver);
			Common.log("Validating Search – Audio On Demand Carousel-MOBANDEVER 706");
			try {
				Common.impicitWait(4);
				getPageFactory().getSearch().clickOnSearch();
				getPageFactory().getSearch().verifySearchAudionDemandCarousel();
			} catch (AndriodException ex) {
				Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
				Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			}
		}
		
		
	/**
	 * MOBANDEVER-700
	 * validating Search – Top Results – Header
	 */
	@Test(suiteName = "Search", testName = "Search – Top Results – Header", description = "Search – Top Results – Header", enabled = true, groups = {
			"MOBANDEVER-700" })
	public void testVerifySearchTopResultsHeader() {
		Common common = new Common(driver);
		Common.log("Validating Search – Audio On Demand Carousel-MOBANDEVER 700");
		try {
			Common.impicitWait(4);
			getPageFactory().getSearch().clickOnSearch();
			getPageFactory().getSearch().recentSearch();
			} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

}
