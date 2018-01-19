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
import org.testng.annotations.BeforeClass;
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
public class NowPlayingTest extends RefactorMobileTestCase {
	private final static Logger LOGGER = Logger.getLogger(NowPlayingTest.class.getName());
	private Map<String, UserObj> users = UserHandler.getInstance(locale, env).getUserMap();
	private String referenceurl;
	public UserObj usr = null;

	/*
	 * @BeforeClass(alwaysRun = true) public void login() throws
	 * InValidOSException, InValidToolException {
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
	 * MOBANDEVER-230 Validation of Now Playing - Live - Disallowed - Channel
	 * Logo
	 */

	@Test(suiteName = "NowPlaying", testName = "Now Playing - Live - Disallowed - Category", description = "Validating Disallowed category", enabled = true, groups = {
			"MOBANDEVER-230" })
	public void disallowedCategoryChannelLogo() {
		Common common = new Common(driver);
		Common.log("Validating Disallowed category Channel Logo MOBANDEVER 230");
		try {
			getPageFactory().getEvehome().clickNews();
			common.scrollUntilTextExists("News/Public Radio");
			getPageFactory().getEvehome().clickSubCatNews();
			getPageFactory().getCategory().disallowedChannelLogo();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-231 Validation of Now Playing - Live - Disallowed - Channel
	 * Number
	 */
	@Test(suiteName = "NowPlaying", testName = "Now Playing - Live - Disallowed - Category", description = "Validating Disallowed category", enabled = true, groups = {
			"MOBANDEVER-231" })
	public void disallowedCategoryChannelNumber() {
		Common common = new Common(driver);
		Common.log("Validating Disallowed category Channel Number MOBANDEVER 231");
		try {
			getPageFactory().getEvehome().clickNews();
			common.scrollUntilTextExists("News/Public Radio");
			getPageFactory().getEvehome().clickSubCatNews();
			getPageFactory().getCategory().disallowedChannelNumber();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-233 validation of Now Playing - Live - Disallowed - Display
	 * Show art
	 */
	@Test(suiteName = "NowPlaying", testName = "Now Playing - Live - Disallowed - Category", description = "Validating Disallowed category", enabled = true, groups = {
			"MOBANDEVER-233" })
	public void disallowedCategoryShowArt() {
		Common common = new Common(driver);
		Common.log("Validating Disallowed category Show Art MOBANDEVER 233");
		try {
			getPageFactory().getEvehome().clickNews();
			common.scrollUntilTextExists("Canadian");
			getPageFactory().getEvehome().clickSubCatNews();
			getPageFactory().getCategory().disallowedShowArt();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-236 validation of Now Playing - Live - Disallowed - Progress
	 * bar (no segments)
	 */
	@Test(suiteName = "NowPlaying", testName = "Now Playing - Live - Disallowed - Category", description = "Validating Disallowed category", enabled = true, groups = {
			"MOBANDEVER-236" })
	public void verifydisallowedCategory() {
		Common common = new Common(driver);
		Common.log("Verifying Disallowed Category MOBANDEVER-236");
		try {
			getPageFactory().getEvehome().clickNews();
			common.scrollUntilTextExists("News/Public Radio");
			getPageFactory().getEvehome().clickSubCatNews();
			getPageFactory().getEvehome().clickNewsChannel1();
			getPageFactory().getEvehome().validateNPLScreen();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-235 validation of Now Playing - Live - Disallowed - Show name
	 * & PDT
	 */
	@Test(suiteName = "NowPlaying", testName = "Now Playing - Live - Disallowed - Category", description = "Validating Disallowed category", enabled = true, groups = {
			"MOBANDEVER-235" })
	public void disallowedCategoryShownamePDT() {
		Common common = new Common(driver);
		Common.log("Validating Disallowed category Show Name & PDT MOBANDEVER 235");
		try {
			getPageFactory().getEvehome().clickNews();
			common.scrollUntilTextExists("News/Public Radio");
			getPageFactory().getEvehome().clickSubCatNews();
			getPageFactory().getEvehome().clickNewsChannel1();
			getPageFactory().getCategory().disallowedShowNamePDT();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-237 validation of Now Playing - Live - Disallowed - Player
	 * controls
	 */
	@Test(suiteName = "NowPlaying", testName = "Now Playing - Live - Disallowed - Category", description = "Validating Disallowed category", enabled = true, groups = {
			"MOBANDEVER-237" })
	public void disallowedPalyercontrols() {
		Common common = new Common(driver);
		Common.log("Validating Player controls MOBANDEVER-237");
		try {
			getPageFactory().getEvehome().clickNews();
			common.scrollUntilTextExists("News/Public Radio");
			getPageFactory().getEvehome().clickSubCatNews();
			getPageFactory().getEvehome().clickNewsChannel1();
			getPageFactory().getCategory().disallowedPlayerControls();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-258 validation of Now Playing - On Demand - Disallowed - Show
	 * Logo & ON DEMAND text
	 */
	@Test(suiteName = "NowPlaying", testName = "Now Playing - Live - Disallowed - Category", description = "Validating On Demand Disallowed", enabled = true, groups = {
			"MOBANDEVER-258" })
	public void onDemanddisallowedshowLogo() {
		Common common = new Common(driver);
		Common.log("Validating Disallowed ShowLogo MOBANDEVER-258");
		try {
			getPageFactory().getEvehome().clickNews();
			common.scrollUntilTextExists("News/Public Radio");
			getPageFactory().getEvehome().clickSubCatNews();
			getPageFactory().getEvehome().clickOnDemand();
			getPageFactory().getCategory().validateOndemanddisallowed();
			getPageFactory().getEvehome().validateNPLScreen();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-261 validation of Now Playing - On Demand - Disallowed - Show
	 * name & PDT
	 */
	@Test(suiteName = "NowPlaying", testName = "Now Playing - Live - Disallowed - Category", description = "Validating On Demand Disallowed", enabled = true, groups = {
			"MOBANDEVER-261" })
	public void onDemanddisallowedShowNamePDT() {
		Common common = new Common(driver);
		Common.log("Validating Disallowed Show Name & PDT MOBANDEVER-261");
		try {
			getPageFactory().getEvehome().clickNews();
			common.scrollUntilTextExists("News/Public Radio");
			getPageFactory().getEvehome().clickSubCatNews();
			getPageFactory().getEvehome().clickOnDemand();
			getPageFactory().getCategory().validateOndemanddisallowed();
			getPageFactory().getCategory().nplShowName();
			getPageFactory().getCategory().nplPDT();
			getPageFactory().getEvehome().clickminimize();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-264 Validation of Now Playing - On Demand - Disallowed -
	 * Player controls
	 */
	@Test(suiteName = "NowPlaying", testName = "Now Playing - Live - Disallowed - Category", description = "Validating On Demand Disallowed", enabled = true, groups = {
			"MOBANDEVER-264" })
	public void onDemandDisallowedPlayerControls() {
		Common common = new Common(driver);
		Common.log("Validating Player controls MOBANDEVER-264");
		try {
			getPageFactory().getEvehome().clickNews();
			common.scrollUntilTextExists("News/Public Radio");
			getPageFactory().getEvehome().clickSubCatNews();
			getPageFactory().getEvehome().clickOnDemand();
			getPageFactory().getCategory().validateOndemanddisallowed();
			getPageFactory().getCategory().disallowedPlayerControls();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-224 Validation of Now Playing - Live - DMCA Restricted - Show
	 * name & PDT
	 */
	@Test(suiteName = "NowPlaying", testName = "DMCA Restricted", description = "Now Playing - Live - DMCA Restricted - Show name & PDT", enabled = true, groups = {
			"MOBANDEVER-224" })
	public void verifydmcaRestrictedShownamePDT() {
		Common common = new Common(driver);
		Common.log("verify dmca Restricted Showname & PDT MOBANDEVER-224");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Dance/Electronic");
			getPageFactory().getEvehome().clickMucisSubRock();
			getPageFactory().getEvehome().popChannel1();
			getPageFactory().getCategory().nplShowName();
			getPageFactory().getCategory().nplPDT();
			getPageFactory().getEvehome().clickminimize();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-219 validation of Now Playing - Live - DMCA Restricted -
	 * Channel Logo
	 */
	@Test(suiteName = "NowPlaying", testName = "DMCA Restricted", description = "Validating DMCA restricted", enabled = true, groups = {
			"MOBANDEVER-219" })
	public void dmcaRestrictedChannelLogo() {
		Common common = new Common(driver);
		Common.log("verifying DMCA restricted Channel Logo MOBANDEVER-219");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Dance/Electronic");
			getPageFactory().getEvehome().clickMucisSubRock();
			getPageFactory().getEvehome().popChannel1();
			getPageFactory().getCategory().nplLogo();
			getPageFactory().getEvehome().clickminimize();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-220 validation of Now Playing - Live - DMCA Restricted -
	 * Channel Number
	 */
	@Test(suiteName = "NowPlaying", testName = "DMCA Restricted", description = "Validating DMCA restricted", enabled = true, groups = {
			"MOBANDEVER-220" })
	public void dmcaRestrictedChannelNumber() {
		Common common = new Common(driver);
		Common.log("verifying DMCA restricted Channel Number MOBANDEVER-220");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Dance/Electronic");
			getPageFactory().getEvehome().clickMucisSubRock();
			getPageFactory().getEvehome().popChannel1();
			getPageFactory().getCategory().nplChnumber();
			getPageFactory().getEvehome().clickminimize();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-222 validation of Now Playing - Live - DMCA Restricted -
	 * Display Album art
	 */
	@Test(suiteName = "NowPlaying", testName = "DMCA Restricted", description = "Validating DMCA restricted", enabled = true, groups = {
			"MOBANDEVER-222" })
	public void dmcaRestrictedShowArt() {
		Common common = new Common(driver);
		Common.log("verifying DMCA restricted Show Art MOBANDEVER-222");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Dance/Electronic");
			getPageFactory().getEvehome().clickMucisSubRock();
			getPageFactory().getEvehome().popChannel1();
			getPageFactory().getCategory().nowPlayingArt();
			getPageFactory().getEvehome().clickminimize();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-253 Validation of Now Playing - On Demand - DMCA Restricted -
	 * Show name & PDT
	 */
	@Test(suiteName = "NowPlaying", testName = "DMCA Restricted", description = "Validating On Demand DMCA Restricted", enabled = true, groups = {
			"MOBANDEVER-253" })
	public void onDemanddmcaRestrictedShownamePDT() {
		Common common = new Common(driver);
		Common.log("verifying  On Demand DMCA restricted Show Name PDT MOBANDEVER-253");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Dance/Electronic");
			getPageFactory().getEvehome().clickMucisSubRock();
			getPageFactory().getdmca().verifyOnDemandDMCA();
			getPageFactory().getCategory().nplShowName();
			getPageFactory().getCategory().nplPDT();
			getPageFactory().getEvehome().clickminimize();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-252 Validation of Now Playing - On Demand - DMCA Restricted -
	 * Album Art
	 */
	@Test(suiteName = "NowPlaying", testName = "DMCA Restricted", description = "Validating On Demand DMCA Restricted", enabled = true, groups = {
			"MOBANDEVER-252" })
	public void verifyonDemanddmcaRestrictedAlbumArt() {
		Common common = new Common(driver);
		Common.log("verifying  On Demand DMCA restricted Album Art MOBANDEVER-252");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Dance/Electronic");
			getPageFactory().getEvehome().clickMucisSubRock();
			getPageFactory().getdmca().verifyOnDemandDMCA();
			getPageFactory().getCategory().nowPlayingArt();
			getPageFactory().getEvehome().clickminimize();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-256 validation of Now Playing - On Demand - DMCA Restricted -
	 * Player controls
	 */
	@Test(suiteName = "NowPlaying", testName = "DMCA Restricted", description = "Validating On Demand DMCA Restricted", enabled = true, groups = {
			"MOBANDEVER-256" })
	public void onDemandPlayerControls() {
		Common common = new Common(driver);
		Common.log("verifying  On Demand DMCA restricted Player Controls MOBANDEVER-256");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Dance/Electronic");
			getPageFactory().getEvehome().clickMucisSubRock();
			getPageFactory().getdmca().verifyOnDemandDMCA();
			getPageFactory().getdmca().validatePlayerControls();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-250 Validation of Now Playing - On Demand - DMCA Restricted -
	 * Show Logo & ON DEMAND text
	 */
	@Test(suiteName = "NowPlaying", testName = "DMCA Restricted", description = "Validating On Demand DMCA Restricted", enabled = true, groups = {
			"MOBANDEVER-250" })
	public void onDemanddmcaRestrictedShowLogo() {
		Common common = new Common(driver);
		Common.log("verifying  On Demand DMCA restricted Show Logo MOBANDEVER-250");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Dance/Electronic");
			getPageFactory().getEvehome().clickMucisSubRock();
			getPageFactory().getdmca().verifyOnDemandDMCA();
			getPageFactory().getEvehome().validateNPLScreen();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-206 validation of Now Playing - Live - Unrestricted - Channel
	 * Logo
	 */
	@Test(suiteName = "NowPlaying", testName = "Now Playing - Live - Unrestricted - Channel Logo", enabled = true, groups = {
			"MOBANDEVER-206" })
	public void validateHowardChannelLogo() {
		Common common = new Common(driver);
		try {
			getPageFactory().getEvehome().clickOnTalk();
			getPageFactory().getHoward().clickOnHowardStern();
			getPageFactory().getHoward().verifyHowardChannelLogo();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-251 validation of Now Playing - On Demand - DMCA Restricted -
	 * Favorite icon
	 */
	@Test(suiteName = "NowPlaying", testName = "DMCA", description = "Now Playing - On Demand - DMCA Restricted - Favorite icon", enabled = true, groups = {
			"MOBANDEVER-251" })
	public void dmcaOnDemandFavoriteIcon() {
		Common common = new Common(driver);
		Common.log("Now Playing - On Demand - DMCA Restricted - Favorite icon MOBANDEVER-251");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Dance/Electronic");
			Common.impicitWait(3);
			getPageFactory().getEvehome().clickMucisSubHipHop();
			Common.impicitWait(3);
			getPageFactory().getdmca().clickOnDemand();
			Common.impicitWait(3);
			getPageFactory().getEvehome().navigateToEpisode();
			String string = getPageFactory().getFavorites().favoritingShow();
			// getPageFactory().getEvehome().minimizebutton().click();
			// getPageFactory().getFavorites().verifyingFavoriteList(string);
			// getPageFactory().getEvehome().validateHomePage();
			// getPageFactory().getEvehome().clickMucisSubRock();
			getPageFactory().getFavorites().unfavoriteShow();
			getPageFactory().getEvehome().clickminimize();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-259 Validation of Now Playing - On Demand - Disallowed -
	 * Favorite icon
	 */
	@Test(suiteName = "NowPlaying", testName = "Disallowed", description = "Now Playing - On Demand - Disallowed - Favorite icon", enabled = true, groups = {
			"MOBANDEVER-259" })
	public void disallowedOnDemandFavoriteIcon() {
		Common common = new Common(driver);
		Common.log("Now Playing - On Demand - Disallowed - Favorite icon-259");
		try {
			getPageFactory().getEvehome().clickNews();
			common.scrollUntilTextExists("News/Public Radio");
			getPageFactory().getEvehome().clickSubCatNews();
			Common.impicitWait(3);
			getPageFactory().getdmca().clickOnDemand();
			Common.impicitWait(3);
			getPageFactory().getEvehome().navigateToEpisode();
			String string = getPageFactory().getFavorites().favoritingShow();
			/*
			 * getPageFactory().getEvehome().minimizebutton().click();
			 * getPageFactory().getFavorites().verifyingFavoriteList(string);
			 * getPageFactory().getEvehome().validateHomePage();
			 * getPageFactory().getEvehome().clickMucisSubRock()
			 */;
			getPageFactory().getFavorites().unfavoriteShow();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

}
