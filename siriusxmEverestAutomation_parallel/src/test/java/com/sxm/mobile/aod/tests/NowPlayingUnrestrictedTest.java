package com.sxm.mobile.aod.tests;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
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
public class NowPlayingUnrestrictedTest extends RefactorMobileTestCase {
	private final static Logger LOGGER = Logger.getLogger(NowPlayingUnrestrictedTest.class.getName());
	private Map<String, UserObj> users = UserHandler.getInstance(locale, env).getUserMap();
	private String referenceurl;
	public UserObj usr = null;

	/*
	 * @BeforeClass(alwaysRun = true) public void login()throws
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
	 * MOBANDEVER-206 Validation of Now Playing - Live - Unrestricted - Channel
	 * Logo
	 */
	@Test(suiteName = "HowardSternCategory", testName = "Unrestricted-Howard Stern category", description = "Now Playing - Live - Unrestricted - Channel Logo", enabled = true, groups = {
			"MOBANDEVER-206" })
	public void validateHowardChannelLogo() {
		Common common = new Common(driver);
		Common.log("Validating Unrestricted Channel Logo MOBANDEVER-206");
		try {
			getPageFactory().getEvehome().clickOnTalk();
			//common.scrollUntilTextExists("Entertainment");
			getPageFactory().getEvehome().clickOnHowardStern();
			getPageFactory().getHoward().verifyHowardChannelLogo();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-207 Validation of Now Playing - Live - Unrestricted - Channel
	 * Number
	 */
	@Test(suiteName = "HowardSternCategory", testName = "Unrestricted-Howard Stern category", description = "Now Playing - Live - Unrestricted - Channel Number", enabled = true, groups = {
			"MOBANDEVER-207" })
	public void validateHowardChannelNumber() {
		Common common = new Common(driver);
		Common.log("Validating Unrestricted Channel Number MOBANDEVER-207");
		try {
			getPageFactory().getEvehome().clickOnTalk();
			//common.scrollUntilTextExists("Entertainment");
			getPageFactory().getEvehome().clickOnHowardStern();
			getPageFactory().getHoward().verifyHowardChannelNUmber();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-211 Validation of Now Playing - Live - Unrestricted - Show
	 * name & PDT
	 */
	@Test(suiteName = "HowardSternCategory", testName = "Unrestricted-Howard Stern category", description = "Now Playing - Live - Unrestricted - Show name & PDT", enabled = true, groups = {
			"MOBANDEVER-211" })
	public void validateHowardShowNamePdt() {
		Common common = new Common(driver);
		Common.log("Validating Unrestricted Show Name and PDT MOBANDEVER-211");
		try {
			getPageFactory().getEvehome().clickOnTalk();
			//common.scrollUntilTextExists("Entertainment");
			getPageFactory().getEvehome().clickOnHowardStern();
			getPageFactory().getHoward().verifyUnrestrictedShownamePDT();
			getPageFactory().getEvehome().nplShowName();
			getPageFactory().getEvehome().nplPDT();
			getPageFactory().getEvehome().clickminimize();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}

	}

	/**
	 * Validation of Now Playing - On Demand - Unrestricted - Show name & PDT
	 */
	@Test(suiteName = "HowardSternCategory", testName = "Unrestricted-Howard Stern category", description = "Now Playing - On Demand - Unrestricted - Show name & PDT", enabled = true, groups = {
			"MOBANDEVER-243" })
	public void validateHowardOnDEmnadShowNamePdt() {
		Common common = new Common(driver);
		Common.log("Validating Unrestricted Show Name and PDT MOBANDEVER-243");
		try {
			getPageFactory().getEvehome().clickOnTalk();
			//common.scrollUntilTextExists("Entertainment");
			getPageFactory().getEvehome().clickOnHowardStern();
			getPageFactory().getHoward().verifyUnrestrictedOnDemandShownamePDT();
			getPageFactory().getEvehome().nplShowName();
			getPageFactory().getEvehome().nplPDT();
			getPageFactory().getEvehome().clickminimize();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}

	}

	/**
	 * MOBANDEVER-209 Validation of Now Playing - Live - Unrestricted - Display
	 * Show Art
	 */
	@Test(suiteName = "HowardSternCategory", testName = "Unrestricted-Howard Stern category", description = "Now Playing - Live - Unrestricted - Display Show Art", enabled = true, groups = {
			"MOBANDEVER-209" })
	public void validateUnrestrictedShowArt() {
		Common common = new Common(driver);
		Common.log("Validating Unrestricted Show Art MOBANDEVER-209");
		try {
			getPageFactory().getEvehome().clickOnTalk();
			//common.scrollUntilTextExists("Entertainment");
			getPageFactory().getEvehome().clickOnHowardStern();
			getPageFactory().getHoward().verifyUnrestrictedShowArt();
			getPageFactory().getEvehome().nowPlayingArt();
			getPageFactory().getEvehome().clickminimize();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-242 Validation of Now Playing - On Demand - Unrestricted -
	 * Show Art
	 */
	@Test(suiteName = "HowardSternCategory", testName = "Unrestricted-Howard Stern category", description = "Now Playing - On Demand - Unrestricted - Show Art", enabled = true, groups = {
			"MOBANDEVER-242" })
	public void validateUnrestrictedOnDemandShowArt() {
		Common common = new Common(driver);
		Common.log("Validating Unrestricted On Demand Show Art MOBANDEVER-242");
		try {
			getPageFactory().getEvehome().clickOnTalk();
			//common.scrollUntilTextExists("Entertainment");
			getPageFactory().getEvehome().clickOnHowardStern();
			getPageFactory().getHoward().verifyUnrestrictedShowArt();
			getPageFactory().getEvehome().nowPlayingArt();
			getPageFactory().getEvehome().clickminimize();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-212 Validation of Now Playing - Live - Unrestricted - Progress
	 * bar with segments
	 */
	@Test(suiteName = "HowardSternCategory", testName = "Unrestricted-Howard Stern category", description = "Now Playing - Live - Unrestricted - Progress bar with segments", enabled = true, groups = {
			"MOBANDEVER-212" })
	public void validateUnrestrictedProgressBar() {
		Common common = new Common(driver);
		Common.log("Validating unrestricted Progress bar MOBANDEVER-212");
		try {
			getPageFactory().getEvehome().clickOnTalk();
			//common.scrollUntilTextExists("Entertainment");
			getPageFactory().getEvehome().clickOnHowardStern();
			getPageFactory().getHoward().verifyUnrestrictedProgressbar();
			getPageFactory().getEvehome().clickminimize();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-347 Validation of Howard Stern Category - Channel Carousel
	 * Header
	 */
	@Test(suiteName = "HowardSternCategory", testName = "Unrestricted-Howard Stern category", enabled = true, groups = {
			"MOBANDEVER-347" })
	public void verifyhowardCategory() {
		Common common = new Common(driver);
		Common.log("Verifying Howard Caurosel presence MOBANDEVER-347");
		try {
			getPageFactory().getEvehome().clickOnTalk();
			//common.scrollUntilTextExists("Entertainment");
			getPageFactory().getEvehome().clickOnHowardStern();
			getPageFactory().getHoward().howardCarousel1();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-240 Validation of Now Playing - On Demand - Unrestricted -
	 * Show Logo & ON DEMAND text
	 */
	@Test(suiteName = "HowardSternCategory", testName = "Unrestricted-Howard Stern category", enabled = true, groups = {
			"MOBANDEVER-240" })
	public void HowardShowLogo() {
		Common common = new Common(driver);
		Common.log("Validating Unrestricted On Demand ShowLogo MOBANDEVER-240");
		try {
			getPageFactory().getEvehome().clickOnTalk();
			//common.scrollUntilTextExists("Entertainment");
			getPageFactory().getEvehome().clickOnHowardStern();
			getPageFactory().getHoward().unrestrictedShowlogoonDemand();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * Validation of Howard as a Super Category
	 */
	@Test(suiteName = "HowardSternCategory", testName = "Howard as a Super Category", description = "Validation of Howard as a Super Category", enabled = true, groups = {
			"MOBANDEVER-912" })
	public void howardSuperCategory() {
		Common common = new Common(driver);
		Common.log("Validation of Howard as a Super Category MOBANDEVER-912");
		try {
			getPageFactory().getHome().homeCategories();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

}
