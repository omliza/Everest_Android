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
import com.sxm.framework.common.Retry;
import com.sxm.framework.common.Screenshot;
import com.sxm.framework.dto.UserObj;
import com.sxm.framework.dto.handler.UserHandler;
import com.sxm.framework.exception.AndriodException;
import com.sxm.framework.exception.InValidOSException;
import com.sxm.framework.exception.InValidToolException;
import com.sxm.mobile.pages.Common;

@SuppressWarnings({ "unused", "static-access" })
@Listeners({ Screenshot.class })
public class MiniNowPlayingBarTest extends RefactorMobileTestCase {
	private final static Logger LOGGER = Logger.getLogger(EDPMenuTest.class.getName());
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

	@Test(suiteName = "MiniBar", testName = "Now playing screen-Mini Bar", description = "Validating Mini bar", enabled = true, groups = {
			"MOBANDEVER-300" })
	public void verifyMiniBar() {
		Common common = new Common(driver);
		Common.log("Verifying navigation from Mini Bar menu MOBANDEVER-300");
		try {
			getPageFactory().getEvehome().miniBar();
			getPageFactory().getEvehome().testMiniBarNavigation();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	@Test(suiteName = "MiniBar", testName = "Now playing screen-Mini Bar", description = "Validating Mini bar", enabled = true, groups = {
			"MOBANDEVER-299" })
	public void verifyMiniBarScreen() {
		Common common = new Common(driver);
		Common.log("Verifying navigation from Mini Bar menu MOBANDEVER-299");
		try {
			getPageFactory().getEvehome().miniBar();
			getPageFactory().getEvehome().testMiniBarNavigation();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	@Test(suiteName = "MiniBar", testName = "Now playing screen-Mini Bar", description = "Validating Mini bar", enabled = true, groups = {
			"MOBANDEVER-301" })
	public void verifyMiniBar1() {
		Common common = new Common(driver);
		Common.log("Verifying navigation from Mini Bar menu MOBANDEVER-301");
		try {
			getPageFactory().getEvehome().miniBar();
			getPageFactory().getEvehome().testMiniBarNavigation();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-1047 Verifying Add Elevation and Shadow to the Mini Bar
	 */
	@Test(suiteName = "MiniBar", testName = "Add Elevation and Shadow to the Mini Bar", description = "Validating  Elevation and Shadow to the Mini Bar", enabled = true, groups = {
			"MOBANDEVER-1047" })
	public void verifyElevationShadowMiniBar() {
		Common common = new Common(driver);
		Common.log("Verifying Add Elevation and Shadow to the Mini Bar MOBANDEVER-1047");
		try {
			Common.impicitWait(3);
			getPageFactory().getHome().homeCategories();
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Music Categories");
			getPageFactory().getEvehome().clickMucisSubRock();
			getPageFactory().getEvehome().clickchannel();
			getPageFactory().getEvehome().clickminimize();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-987 Validating Alpha/Beta Requirement - Send Feedback floating
	 * button
	 */
	@Test(suiteName = "MiniBar", testName = "Send Feedback floating button", description = "Validating Alpha/Beta Requirement - Send Feedback floating button", enabled = true, groups = {
			"MOBANDEVER-987" })
	public void verifySendFeddbackFloating() {
		Common common = new Common(driver);
		Common.log("Validating Alpha/Beta Requirement - Send Feedback floating button MOBANDEVER-987");
		getPageFactory().getHome().homeCategories();
		getPageFactory().getEvehome().floatingButton();
		getPageFactory().getFavorites().clickFavoriteIcon();
		getPageFactory().getEvehome().floatingButton();
		getPageFactory().getProfile().clickProfile();
		getPageFactory().getEvehome().floatingButton();
		getPageFactory().getEvehome().clickOnFloatingButton();
	}

}
