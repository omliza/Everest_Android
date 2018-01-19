package com.sxm.mobile.aod.tests;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.junit.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.selenium.mobile.base.RefactorMobileTestCase;
import com.sxm.framework.common.Screenshot;
import com.sxm.framework.dto.UserObj;
import com.sxm.framework.dto.handler.UserHandler;
import com.sxm.framework.exception.AndriodException;
import com.sxm.framework.exception.InValidOSException;
import com.sxm.framework.exception.InValidToolException;
import com.sxm.mobile.pages.Common;

@SuppressWarnings({ "unused", "static-access" })
@Listeners({ Screenshot.class })
public class OnDemandEpisodesListingTest extends RefactorMobileTestCase {
	private final static Logger LOGGER = Logger.getLogger(CategoryTest.class.getName());
	private Map<String, UserObj> users = UserHandler.getInstance(locale, env).getUserMap();
	private String referenceurl;
	public UserObj usr = null;

	/*
	 * @Parameters("platform")
	 * 
	 * @org.testng.annotations.BeforeClass(alwaysRun = true) public void
	 * login(){Common common = new Common(driver);
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
	 * MOBANDEVER-396 : Validation of Categories - On Demand Episode Listing -
	 * Display Tile
	 */
	@Test(suiteName = "Categories", testName = "VerifyOnDemandEpisodeTile", description = "Categories - On Demand Episode Listing - Display Tile", enabled = true, groups = {
			"MOBANDEVER-396" })
	public void onDemandEpisodeTile() {
		Common common = new Common(driver);
		Common.log("Executing Categories - On Demand Episode Listing - Display Tile-MOBANDEVER 396");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Music Categories");
			getPageFactory().getEvehome().clickMucisSubHipHop();
			getPageFactory().getEvehome().clickOnDemand();
			getPageFactory().getEvehome().VerifyOnDemandEpisodeTile();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-398 : validation of Categories - On Demand Episode Listing -
	 * Airdate
	 */
	@Test(suiteName = "Categories", testName = "VerifyOnDemandEpisodeAirdate", description = "Categories - On Demand Episode Listing - Airdate", enabled = true, groups = {
			"MOBANDEVER-398" })
	public void onDemandEpisodeAirdate() {
		Common common = new Common(driver);
		Common.log(" Verifying Categories - On Demand Episode Listing - Airdate");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Music Categories");
			getPageFactory().getEvehome().clickMucisSubHipHop();
			getPageFactory().getEvehome().clickOnDemand();
			getPageFactory().getEvehome().VerifyOnDemandEpisodeAirdate();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-402 : Validation of Categories - On Demand Episode Listing -
	 * Episode Filter - Cancel Button
	 */
	@Test(suiteName = "Categories", testName = "Epidode Filter - Cancel button", description = "Categories - On Demand Episode Listing - Episode Filter - Cancel Button", enabled = true, groups = {
			"MOBANDEVER-402" })
	public void verifyOnDemandEpisodeFilterCancel() {
		Common common = new Common(driver);
		Common.log("Verifying Epidode Filter-Cancel Button MOBANDEVER 402");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Music Categories");
			getPageFactory().getEvehome().clickMucisSubRock();
			getPageFactory().getEvehome().clickOnDemand();
			getPageFactory().getEvehome().testEpisodeFilterCancelButton();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-403: Validation of Episode Filter - No Results Screen
	 */
	@Test(suiteName = "Categories", testName = "Episode Filter - No Results Screen", description = "Categories - On Demand Episode Listing - Episode Filter - No Results Screen", enabled = true, groups = {
			"MOBANDEVER-403" })
	public void verifyOnDemandEpisodeFilterNoResult() {
		Common common = new Common(driver);
		Common.log("VerifyingEpisode Filter - No Results Screen-MOBANDEVER 403");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Music Categories");
			getPageFactory().getEvehome().clickMucisSubHipHop();
			getPageFactory().getEvehome().clickOnDemand();
			getPageFactory().getEvehome().testEpisodeFilterNoResult();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER 400: Verifying Episode Listing - Episode Filter-MOBANDEVER 400
	 */
	@Test(suiteName = "Categories", testName = "Episode Listing - Episode Filter", description = "Categories - On Demand Episode Listing - Episode Filter", enabled = true, groups = {
			"MOBANDEVER-400" })
	public void verifyOnDemandEpisodeFilter() {
		Common common = new Common(driver);
		Common.log("Verifying Episode Listing - Episode Filter-MOBANDEVER 400");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Music Categories");
			getPageFactory().getEvehome().clickMucisSubHipHop();
			getPageFactory().getEvehome().clickOnDemand();
			getPageFactory().getEvehome().testEpisodeFilter1();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-401 : Validation of Categories - On Demand Episode Listing -
	 * Episode Filter - Clear Button
	 */
	@Test(suiteName = "Categories", testName = "Episode Filter - Clear Button", description = "Categories - On Demand Episode Listing - Episode Filter - Clear Button", enabled = true, groups = {
			"MOBANDEVER-401" })
	public void OnDemandEpisodeFilterCloseButton() {
		Common common = new Common(driver);
		Common.log("Verifying Episode Filter - Clear Button -MOBANDEVER 401");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Music Categories");
			getPageFactory().getEvehome().clickMucisSubRock();
			getPageFactory().getEvehome().clickOnDemand();
			getPageFactory().getEvehome().EpisodeFilterCloseButton();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

}
