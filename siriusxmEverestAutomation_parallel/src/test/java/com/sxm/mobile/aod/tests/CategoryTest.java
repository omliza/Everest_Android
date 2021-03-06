package com.sxm.mobile.aod.tests;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.selenium.mobile.base.RefactorMobileTestCase;
import com.sxm.framework.common.Retry;
import com.sxm.framework.common.Screenshot;
import com.sxm.framework.dto.UserObj;
import com.sxm.framework.dto.handler.UserHandler;
import com.sxm.framework.exception.AndriodException;
import com.sxm.framework.exception.InValidOSException;
import com.sxm.framework.exception.InValidToolException;
import com.sxm.framework.exception.AndriodException;
import com.sxm.mobile.pages.Common;

@SuppressWarnings({ "unused", "static-access" })
@Listeners({ Screenshot.class })
public class CategoryTest extends RefactorMobileTestCase {
	private final static Logger LOGGER = Logger.getLogger(CategoryTest.class.getName());
	private Map<String, UserObj> users = UserHandler.getInstance(locale, env).getUserMap();
	private String referenceurl;
	public UserObj usr = null;

	/*
	 * @Parameters("platform")
	 * 
	 * @org.testng.annotations.BeforeClass(alwaysRun = true) public void login()
	 * {Common common = new Common(driver);
	 * driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
	 * MOBANDEVER-270: Validating Categories - On Demand Filter - No Results
	 * Screen
	 */
	@Test(suiteName = "Categories", testName = "Category On Demand NO Result ", description = "Categories - On Demand Filter - No Results Screen", enabled = true, groups = {
			"MOBANDEVER-270" })
	public void categoryOnDemandNoResult() {
		Common common = new Common(driver);
		Common.log("Verifying Category On Demand NO Result  MOBANDEVER-270");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Dance/Electronic");
			getPageFactory().getEvehome().clickMucisSubRock();
			getPageFactory().getEvehome().clickOnDemand();
			getPageFactory().getEvehome().categoryNoResult();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-288 : Validating Categories - On Demand Show List Item
	 */
	@Test(suiteName = "Categories", testName = "On DEmand Shows List", description = "Verifying On demand Shows List Item", enabled = true, groups = {
			"MOBANDEVER-288" })
	public void OnDemandShowsValidation() {
		Common common = new Common(driver);
		Common.log("Validation of On Demand Shows List MOBANDEVER-288");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Dance/Electronic");
			getPageFactory().getEvehome().clickMucisSubHipHop();
			getPageFactory().getEvehome().clickOnDemand();
			getPageFactory().getEvehome().onDemandShowsList();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}

	}

	/**
	 * MOBANDEVER-282 : Validating Categories - On Demand Shows Tab
	 */
	@Test(suiteName = "Categories", testName = "On DEmand Shows List", description = "Verifying On demand Shows Tab", enabled = true, groups = {
			"MOBANDEVER-282" })
	public void verifyOnDemandShowsValidation() {
		Common common = new Common(driver);
		Common.log("Validation of On Demand Shows List MOBANDEVER-282");
		try {
			Common.impicitWait(5);
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Dance/Electronic");
			getPageFactory().getEvehome().clickMucisSubHipHop();
			getPageFactory().getEvehome().clickOnDemand();
			getPageFactory().getEvehome().onDemandShowsList();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-290 : validating Categories - On Demand Show List - Channel
	 * Header
	 */
	@Test(suiteName = "Categories", testName = "On Demand Channel Header", description = "Verifying On demand Shows- Channel Header", enabled = true, groups = {
			"MOBANDEVER-290" })
	public void verifyOnDemandHeader() {
		Common common = new Common(driver);
		Common.log("Validation of On Demand Channel Header MOBANDEVER-290");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Dance/Electronic");
			getPageFactory().getEvehome().clickMucisSubHipHop();
			getPageFactory().getEvehome().clickOnDemand();
			getPageFactory().getEvehome().onDemandShowHeader();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-287 : validating Categories - On Demand Show List
	 */
	@Test(suiteName = "Categories", testName = "On DEmand Shows List", description = "Verifying On demand Show List", enabled = true, groups = {
			"MOBANDEVER-287" })
	public void verifyOnDemandShowslistTab() {
		Common common = new Common(driver);
		Common.log("Validation of On DEmand Shows List MOBANDEVER-287");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Dance/Electronic");
			getPageFactory().getEvehome().clickMucisSubHipHop();
			getPageFactory().getEvehome().clickOnDemand();
			getPageFactory().getEvehome().onDemandShows();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-276 : validating Categories - Back Button
	 */
	@Test(suiteName = "Categories", testName = "Categories", description = "Verifying Categories - Back Button", enabled = true, groups = {
			"MOBANDEVER-276" })
	public void verifyCategorybackButton() {
		Common common = new Common(driver);
		Common.log("Verifying Categories - Back Button MOBANDEVER-276");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Dance/Electronic");
			getPageFactory().getEvehome().clickMucisSubHipHop();
			getPageFactory().getEvehome().channelList();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-277 : validating Categories - Channel List
	 */
	@Test(suiteName = "Categories", testName = "Categories", description = "Verifying Channel List Category ", enabled = true, groups = {
			"MOBANDEVER-277" })
	public void verifyChannelCategory() {
		Common common = new Common(driver);
		Common.log("Verifying Channel list MOBANDEVER-277");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Dance/Electronic");
			getPageFactory().getEvehome().clickMucisSubHipHop();
			getPageFactory().getEvehome().verifychannelList();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-281 : validating Categories - Live Channel Tab
	 */
	@Test(suiteName = "Categories", testName = "Categories", description = "Verifying Categories - Live Channel Tab ", enabled = true, groups = {
			"MOBANDEVER-281" })
	public void verifyLiveChannelTab() {
		Common common = new Common(driver);
		Common.log("Verifying Categories - Live Channel Tab MOBANDEVER-281");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Dance/Electronic");
			getPageFactory().getEvehome().clickMucisSubHipHop();
			getPageFactory().getEvehome().channelListverification();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-282 : validating Categories - On Demand Shows Tab
	 */
	@Test(suiteName = "Categories", testName = "Categories", description = "Verifying Categories - On Demand Shows Tab ", enabled = true, groups = {
			"MOBANDEVER-282" })
	public void verifyOndemandShowsTab() {
		Common common = new Common(driver);
		Common.log("Verifying Categories - On Demand Shows Tab MOBANDEVER-282");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Dance/Electronic");
			getPageFactory().getEvehome().clickMucisSubHipHop();
			getPageFactory().getEvehome().onDemandShowsTab();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-291 : validating Categories - Channel Filter - Filtered
	 * Results
	 */
	@Test(suiteName = "Categories", testName = "Categories", description = "Verifying Categories - Channel Filter - Filtered Results", enabled = true, groups = {
			"MOBANDEVER-291" })
	public void verifyChannelFilter() {
		Common common = new Common(driver);
		Common.log("Verifying  - Channel Filter - Filtered Results-MOBANDEVER-291");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Dance/Electronic");
			getPageFactory().getEvehome().clickMucisSubHipHop();
			getPageFactory().getEvehome().testVerifyChannelFilter();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-283 : validating Categories - Channel Filter - Clear Button
	 */
	@Test(suiteName = "Categories", testName = "Categories", description = "Verifying Categories - Channel Filter - Clear Button", enabled = true, groups = {
			"MOBANDEVER-283" })
	public void verifyChannelClearButton() {
		Common common = new Common(driver);
		Common.log("Verifying  - Channel Filter - Clear Button clear Button-MOBANDEVER-283");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Dance/Electronic");
			getPageFactory().getEvehome().clickMucisSubHipHop();
			getPageFactory().getEvehome().channelClearButton();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-284 : validating Categories - Channel Filter - Cancel Button
	 */
	@Test(suiteName = "Categories", testName = "Categories", description = "Verifying Categories - Channel Filter - Cancel Button", enabled = true, groups = {
			"MOBANDEVER-284" })
	public void verifyChannelFilterCancelButton() {
		Common common = new Common(driver);
		Common.log("Validation  - Channel Filter - Cancel Button MOBANDEVER-284");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Dance/Electronic");
			getPageFactory().getEvehome().clickMucisSubHipHop();
			getPageFactory().getEvehome().channelCancelButton();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-291 : validation of Categories - Channel Filter - Filtered
	 * Results
	 */
	@Test(suiteName = "Categories", testName = "Categories", description = "Verifying Categories - Channel Filter - Filtered Results", enabled = true, groups = {
			"MOBANDEVER-291" })
	public void verifyChannelFilterResults() {
		Common common = new Common(driver);
		Common.log("Validation of Categories - Channel Filter - Filtered Results MOBANDEVER-291");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Dance/Electronic");
			getPageFactory().getEvehome().clickMucisSubHipHop();
			getPageFactory().getEvehome().testVerifyChannelFilter();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-285 : Validating Categories - Channel Filter - No Results
	 * Screen
	 */
	@Test(suiteName = "Categories", testName = "Categories", description = "Categories - Channel Filter - No Results Screen", enabled = true, groups = {
			"MOBANDEVER-285" })
	public void verifyChannelFilterNoResultScreen() {
		Common common = new Common(driver);
		Common.log("Verifying Categories - Channel Filter - No Results Screen-MOBANDEVER-285");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Dance/Electronic");
			getPageFactory().getEvehome().clickMucisSubHipHop();
			getPageFactory().getEvehome().testVerifyChannelFilterNoResult();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-279 : Validating Categories - Channel Filter
	 */
	@Test(suiteName = "Categories", testName = "Categories", description = "Categories - Channel Filter", enabled = true, groups = {
			"MOBANDEVER-279" })
	public void ChannelFilter() {
		Common common = new Common(driver);
		Common.log("Verifying Channel Filter Result-MOBANDEVER-279");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Dance/Electronic");
			getPageFactory().getEvehome().clickMucisSubHipHop();
			getPageFactory().getEvehome().channelfilter();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-292 : validating Categories - On Demand Filter - Filtered
	 * Results
	 */
	@Test(suiteName = "Categories", testName = "Categories", description = "Categories - On Demand Filter - Filtered Results", enabled = true, groups = {
			"MOBANDEVER-292" })
	public void onDemandFilteredResults() {
		Common common = new Common(driver);
		Common.log("Validation of Categories - On Demand Filter - Filtered Results MOBANDEVER-292");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Dance/Electronic");
			getPageFactory().getEvehome().clickMucisSubHipHop();
			getPageFactory().getEvehome().clickOnDemand();
			getPageFactory().getEvehome().onDemandFilteredREsults();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}

	}

	/**
	 * MOBANDEVER-271 : Validation of Categories - On Demand Filter - Cancel
	 * Button
	 */
	@Test(suiteName = "Categories", testName = "Categories", description = "Categories - On Demand Filter - Cancel Button", enabled = true, groups = {
			"MOBANDEVER-271" })
	public void verifyonDemandTabCancelButton() {
		Common common = new Common(driver);
		Common.log("Validation ofCategories - On Demand Filter - Cancel Button MOBANDEVER-271");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("R&B");
			getPageFactory().getEvehome().clickMucisSubRock();
			getPageFactory().getEvehome().clickOnDemand();
			getPageFactory().getEvehome().cancelButton();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}

	}

	/**
	 * MOBANDEVER-272 : Validation of Categories - On Demand Filter - Clear
	 * Button
	 */
	@Test(suiteName = "Categories", testName = "Categories", description = "Categories - On Demand Filter - Clear Button", enabled = true, groups = {
			"MOBANDEVER-272" })
	public void onDemandTabCloseButton() {
		Common common = new Common(driver);
		Common.log("Validation of Categories - On Demand Filter - Close Button MOBANDEVER-272");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("R&B");
			getPageFactory().getEvehome().clickMucisSubPop();
			getPageFactory().getEvehome().clickOnDemand();
			getPageFactory().getEvehome().closeButton();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}

	}

	/**
	 * MOBANDEVER-273 : validation of Categories - On Demand Filter - Filter
	 * Text Field
	 */
	@Test(suiteName = "Categories", testName = "Categories", description = "Categories - On Demand Filter - Filter Text Field", enabled = true, groups = {
			"MOBANDEVER-273" })
	public void onDemandTabValidation() {
		Common common = new Common(driver);
		Common.log("Verifying Categories - On Demand Filter - Filter Text Field-MOBANDEVER-273");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("R&B");
			getPageFactory().getEvehome().clickMucisSubRock();
			getPageFactory().getEvehome().clickOnDemand();
			getPageFactory().getEvehome().VerifyonDemand();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-392 : Validation of Categories - On Demand Episode Listing -
	 * Show logo
	 */
	@Test(suiteName = "Categories", testName = "Categories", description = "Categories - On Demand Episode Listing - Show logo", enabled = true, groups = {
			"MOBANDEVER-392" })
	public void ValidateonDemandEpisodeListing() {
		Common common = new Common(driver);
		Common.log("Validating Categories - On Demand Episode Listing - Show logo MOBANDEVER_392");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Dance/Electronic");
			getPageFactory().getEvehome().clickMucisSubHipHop();
			getPageFactory().getEvehome().clickOnDemand();
			getPageFactory().getEvehome().episodeListingShowLogo();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-274 : Validation of Categories - On Demand Filter
	 */
	@Test(suiteName = "Categories", testName = "Categories", description = "Categories - On Demand Filter", enabled = true, groups = {
			"MOBANDEVER-274" })
	public void verifyonDemandFilter() {
		Common common = new Common(driver);
		Common.log("Validation of On Demand Filter MOBANDEVER-274");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Dance/Electronic");
			getPageFactory().getEvehome().clickMucisSubHipHop();
			getPageFactory().getEvehome().clickOnDemand();
			getPageFactory().getEvehome().onDemandFilter();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}

	}

	/**
	 * MOBANDEVER-275 : Validation of Categories - Breadcrumb
	 */
	@Test(suiteName = "Categories", testName = "Breadcrumb", description = "Categories - Breadcrumb", enabled = true, groups = {
			"MOBANDEVER-275" })
	public void validateBreadcrumb() {
		Common common = new Common(driver);
		Common.log("Validating Categories Breadcrumb-MOBANDEVER-275");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Dance/Electronic");
			getPageFactory().getEvehome().clickMucisSubRock();
			getPageFactory().getEvehome().verifyBreadcrumb();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

}
