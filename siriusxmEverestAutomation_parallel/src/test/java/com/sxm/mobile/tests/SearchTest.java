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
	 *EVQAAND-51: MOBANDEVER-692: validation of Search – Entry Box – Clear button
	 */
	@Test(suiteName = "Search", testName = "Search", description = "Search – Entry Box – Clear button", enabled = true, groups = {
			"EVQAAND-51" })
	public void verifySearchClearButton() {
		Common common = new Common(driver);
		Common.log("verifying Search –Entry Box –Clear button EVQAAND-51: MOBANDEVER-692");
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
	 * EVQAAND-306: MOBANDEVER-694: Validation of Search – First Time Search State
	 */
	@Test(suiteName = "Search", testName = "Search – First Time Search State", description = "EVQAAND-306: MOBANDEVER-694: Search – First Time Search State", enabled = true, groups = {
			"EVQAAND-306" })
	public void verifyFirstSearchState() {
		Common common = new Common(driver);
		Common.log("Search – First Time Search State -> EVQAAND-306: MOBANDEVER-694: ");
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
	 * EVQAAND-113: MOBANDEVER-691: validation of Search – Entry Box
	 */
	@Test(suiteName = "Search", testName = "EVQAAND-113: MOBANDEVER-691: Search: validation of Search – Entry Box", description = "Validate Search – Entry Box", enabled = true, groups = {
			"EVQAAND-113" })
	public void verifySearchEntry() {
		Common common = new Common(driver);
		Common.log("validating Search – Entry Box -> EVQAAND-113: MOBANDEVER-691");
		try {
			getPageFactory().getSearch().clickOnSearch();
			getPageFactory().getSearch().validateSearchEntry();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * EVQAAND-312: MOBANDEVER-693: Validating Search – Cancel functionality
	 */
	@Test(suiteName = "Search", testName = "Search - Cancel Functionality", description = "Search – Validate Cancel Functionality", enabled = true, groups = {
			"EVQAAND-312" })
	public void verifySearchcancel() {
		Common common = new Common(driver);
		Common.log("validating Search – Cancel  EVQAAND-312: MOBANDEVER-693:");
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
	 * EVQAAND-252: MOBANDEVER-695: validating Search – Previous/ Recent Searches - Header functionality
	 */
	@Test(suiteName = "Search", testName = "EVQAAND-252: MOBANDEVER-695: Search – Previous/ Recent Searches - Header", description = "EVQAAND-252: MOBANDEVER-695:Search – Previous/ Recent Searches - Header", enabled = true, groups = {
			"EVQAAND-252" })
	public void verifyRecentSearch() {
		Common common = new Common(driver);
		Common.log("validating Search – Previous/ Recent Searches - Header -> EVQAAND-252: MOBANDEVER-695");
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
	 * Aparajita - Please update OR delete it
	 * validating Search – Previous/ Recent Searches - Header
	 */
	@Test(suiteName = "Search", testName = "Video On Demand Carousel - Tiles", description = "Search – Video On Demand Carousel - Tiles", 
			enabled = false, groups = {"MOBANDEVER-380(710)"})
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
	 *
	 * EVQAAND-98: MOBANDEVER-701: validating Search – Top Results – 
	 */
	@Test(suiteName = "Search", testName = "Search – Top Results", description = "Search – Top Results", 
			enabled = false, groups = {"EVQAAND-98" })
	public void verifySearchTopResults() {
		Common common = new Common(driver);
		Common.log("validating Search – Top Results – EVQAAND-98: MOBANDEVER-701");
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
	 * EVQAAND-154: MOBANDEVER-1005: Validating Search by Channel Number
	 */
	@Test(suiteName = "Search", testName = "EVQAAND-154: MOBANDEVER-1005:Search by Channel Number & validate NPL", description = "Search - Search by Channel Number: Channel 2, 32, 100 and validate NPL", enabled = true, groups = {
			"EVQAAND-154" })
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
	 * EVQAAND-341: MOBANDEVER-710: Validating the Video On-Demand Carousel and Tiles After search
	 */
	@Test(suiteName = "Search", testName = "Video On Demand Carousel - Tiles", description = "Search – Video On Demand Carousel - Tiles", enabled = true, groups = {
			"EVQAAND-341" })
	public void verifySearchVideoOnDemandCarouselTiles() {
		Common common = new Common(driver);
		Common.log("validating Video On Demand Carousel - Tiles EVQAAND-341: MOBANDEVER-710:");
		try {
			Common.impicitWait(4);
			getPageFactory().getSearch().clickOnSearch();
			getPageFactory().getSearch().seachFromHistory("howard stern");
			getPageFactory().getSearch().VerifyVideoOndemandCarousel();
			Common.impicitWait(4);
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 *EVQAAND-308:MOBANDEVER-705:Validating the Audio On-Demand Caroucel and Tiles After serach
	 */
	@Test(suiteName = "Search", testName = "Audio On Demand Carousel – Header", description = "Search – Verify Audio On Demand Carousel Header text", enabled = true, groups = {
			"EVQAAND-308" })
	public void verifySearchAudioOnDemandCarouselHeader() {
		Common common = new Common(driver);
		Common.log("validating Audio On Demand Carousel - Tiles EVQAAND-308");
		try {
			Common.impicitWait(4);
			getPageFactory().getSearch().clickOnSearch();
			Common.log("Search for term - howard stern");
			getPageFactory().getSearch().seachFromHistory("howard stern");
			getPageFactory().getSearch().VerifyAudioOndemandCarousel();
			Common.impicitWait(4);
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	@Test(suiteName = "Search", testName = "EVQAAND-296:MOBANDEVER-706: Audio On Demand Carousel Header", description = "Search – Verify Audio On Demand Carousel Header", enabled = true, groups = {
			"EVQAAND-296" })
	public void verifySearchAudioOnDemandCarousel() {
		Common common = new Common(driver);
		Common.log("validating Audio On Demand Carousel EVQAAND-296");
		try {
			Common.impicitWait(4);
			getPageFactory().getSearch().clickOnSearch();
			getPageFactory().getSearch().seachFromHistory("howard stern");
			getPageFactory().getSearch().VerifyAudioOndemandCarousel();
			Common.impicitWait(4);
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * EVQAAND-13: MOBANDEVER-699: Validating Search – Suggested Searches MOBANDEVER-699
	 */
	@Test(suiteName = "Search", testName = "EVQAAND-13: MOBANDEVER-699:Search – Suggested Searches Header & playing search result", description = "Search – Validate Suggested Searches Header and user is navigated to NPL on selecting search result",
			enabled = true, groups = {"MOBANDEVER-699" })
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
	 * EVQAAND-32:MOBANDEVER 706: Validating Search – Audio On Demand Carousel MOBANDEVER-706
	 */
	@Test(suiteName = "Search", testName = "EVQAAND-32:MOBANDEVER 706: Search – Audio On Demand Carousel", description = "Search – Audio On Demand Carousel", enabled = true, groups = {
			"EVQAAND-32" })
	public void testVerifySearchAudioOnDemandCarousel() {
		Common common = new Common(driver);
		Common.log("Validating Search – Audio On Demand Carousel-EVQAAND-32:MOBANDEVER 706");
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
	 * Aparajita: Please update this test case as per test scenario
	 * EVQAAND-84: MOBANDEVER-700 validating Search – Top Results – Header
	 */
	@Test(suiteName = "Search", testName = "Search – Top Results – Header", description = "Search – Top Results – Header", 
			enabled = false, groups = {"MOBANDEVER-700" })
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
	/**
	 * EVQAAND-269: MOBANDEVER-703: Search: Top Results - View more results CTA
	 * TBD: "View more" link does not show up in UI
	 */
	@Test(suiteName = "Search", testName = "EVQAAND-269: MOBANDEVER-703: Search: Top Results - View more results CTA", description = "Not Implemented: Search – Top Results – View more results CTA",
			enabled = false, groups = {"EVQAAND-269" })
	public void testVerifySearchViewMoreResultsCTA() {
		Common common = new Common(driver);
		Common.log("Validating Top Results – View more results CTA -> EVQAAND-269: MOBANDEVER-703");
		try {
			Common.impicitWait(4);
			getPageFactory().getSearch().clickOnSearch();
			getPageFactory().getSearch().searchChannel("Snow");
			// TBD: To include validating "View More Top Results". This feature currently does not show up UI
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}
	
	/**
	 * MOBANDEVER-709: To delete this as its exact dupe of EVQAAND-341 written above
	 * validating Search – Video On Demand Carousel
	 */
	@Test(suiteName = "Search", testName = "Dupe: Search – Video On Demand Carousel", description = "Search – Video On Demand Carousel", enabled = false, groups = {
			"MOBANDEVER-709" })
	public void verifySearchVideoOnDemandCarousel() {
		Common common = new Common(driver);
		Common.log("validating Video On Demand Carousel - Tiles MOBANDEVER-709");
		try {
			Common.impicitWait(4);
			getPageFactory().getSearch().clickOnSearch();
			getPageFactory().getSearch().seachFromHistory("howard stern");
			getPageFactory().getSearch().VerifyVideoOndemandCarousel();
			Common.impicitWait(4);
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}
	
	
	/**
	 * EVQAAND-183: MOBANDEVER-897 
	 * Validating Search – No Search Results page
	 */
	@Test(suiteName = "Search", testName = "EVQAAND-183:MOBANDEVER-897:Search – No Search Results page", description = "EVQAAND-183: MOBANDEVER-897: Search – No Search Results page", enabled = true, groups = {
			"EVQAAND-183" })
	public void verifySearchNoSearchResultsPage() {
		Common common = new Common(driver);
		Common.log("validating Search – No Search Results page -> EVQAAND-183: MOBANDEVER-897");
		try {
			Common.impicitWait(4);
			getPageFactory().getSearch().clickOnSearch();
			Common.impicitWait(4);
			getPageFactory().getSearch().searchFilter("ghr");
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}
	
	
	/**
	 * EVQAAND-211
	 * MOBANDEVER-709: To delete this as its exact dupe of EVQAAND-341 written above
	 * validating Search – Video On Demand Carousel
	 */
	@Test(suiteName = "Search", testName = "Search – Video On Demand Carousel - Header", description = "Search – Video On Demand Carousel - Header", 
			enabled = false, groups = {"EVQAAND-211"})
	public void verifySearchVideoOnDemandCarouselHeader() {
		Common common = new Common(driver);
		Common.log("validating Search – Video On Demand Carousel - Header MOBANDEVER-708");
		try {
			Common.impicitWait(4);
			getPageFactory().getSearch().clickOnSearch();
			getPageFactory().getSearch().seachFromHistory("howard stern");
			getPageFactory().getSearch().verifyVODCarousels();
			Common.impicitWait(4);
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}
	
	
	@Test(suiteName = "Search", testName = "EVQAAND-263: MOBANDEVER-698:SSearch – Suggested Searches – Header", description = "Search – Suggested Searches – Header",
			enabled = true, groups = {"MOBANDEVER-698" })
	public void testVerifySearcSuggestedSearchesHeader() {
		Common common = new Common(driver);
		Common.log("Validating Search – Suggested Searches – Header MOBANDEVER-698");
		try {
			Common.impicitWait(4);
			getPageFactory().getSearch().clickOnSearch();
			getPageFactory().getSearch().verifySuggestedSearchHeader();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}
	
		@Test(suiteName = "Search", testName = "EVQAAND-87: MOBANDEVER-696: Search – Search – Previous/ Recent Searches", description = "EVQAAND-252: MOBANDEVER-695:Search – Previous/ Recent Searches", enabled = true, groups = {
			"EVQAAND-87" })
	public void verifyRecentPreviousSearch() {
		Common common = new Common(driver);
		Common.log("validating Search – Previous/ Recent Searches-> EVQAAND-87: MOBANDEVER-696");
		try {
			Common.impicitWait(4);
			getPageFactory().getSearch().clickOnSearch();
			getPageFactory().getSearch().verifyRecentSearch();
			Common.impicitWait(4);
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
		
	}
		
		@Test(suiteName = "Search", testName = "EVQAAND-13: MOBANDEVER-699:Search – Suggested Searches", description = "Search – Validate Suggested Searches ",
				enabled = true, groups = {"MOBANDEVER-699" })
		public void testVerifySuggestedSearches() {
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
		
		@Test(suiteName = "Search", testName = "EVQAAND-25: MOBANDEVER-707:Search – Audio On Demand Carousel - Tiles", description = "Search – Audio On Demand Carousel - Tiles ",
				enabled = true, groups = {"MOBANDEVER-707" })
		public void testVerifyAODCarousel() {
			Common common = new Common(driver);
			Common.log("Validating Search – Audio On Demand Carousel - Tiles-707");
			try {
				Common.impicitWait(4);
				getPageFactory().getSearch().clickOnSearch();
				getPageFactory().getSearch().seachFromHistory("howard stern");
				getPageFactory().getSearch().verifyAODCarousal();
				getPageFactory().getSearch().clickAODCarousel();
			} catch (AndriodException ex) {
				Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
				Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			}
		}

}

