package com.sxm.mobile.tests;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.testng.Assert;
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
	public void nplUnrestricted_validateHowardChannelLogo() {
		Common common = new Common(driver);
		Common.log("Validating Unrestricted Channel Logo MOBANDEVER-206");
		try {
			Common.log("Click on Talk SuperCategory");
			getPageFactory().getEvehome().clickOnTalk();
			//common.scrollUntilTextExists("Entertainment");
			Common.log("In Talk SuperCategory -> Click on Howard Stern");
			getPageFactory().getEvehome().clickOnHowardStern();
			Common.log("Currently in Howard Stern Category -> Verify Howard Channel Logo");
			getPageFactory().getHoward().verifyHowardChannelLogo();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-207:EVQAAND-279 Validation of Now Playing - Live - Unrestricted - Channel
	 * Number
	 */
	@Test(suiteName = "HowardSternCategory", testName = "Unrestricted-Howard Stern category", description = "Now Playing - Live - Unrestricted - Channel Number", enabled = true, groups = {
			"MOBANDEVER-207:EVQAAND-279" })
	public void nplUnrestricted_validateHowardChannelNumber() {
		Common common = new Common(driver);
		Common.log("Validating Unrestricted Channel Number MOBANDEVER-207");
		try {
			Common.log("Click on Talk SuperCategory");
			getPageFactory().getEvehome().clickOnTalk();
			//common.scrollUntilTextExists("Entertainment");
			Common.log("In Talk SuperCategory -> Click on Howard Stern");
			getPageFactory().getEvehome().clickOnHowardStern();
			Common.log("Currently in Howard Stern Category -> Verify Howard Channel Number");
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
	public void nplUnrestricted_validateHowardShowNamePdt() {
		Common common = new Common(driver);
		Common.log("Validating Unrestricted Show Name and PDT MOBANDEVER-211");
		try {
			Common.log("Click on Talk SuperCategory");
			getPageFactory().getEvehome().clickOnTalk();
			//common.scrollUntilTextExists("Entertainment");
			Common.log("In Talk SuperCategory -> Click on Howard Stern");
			getPageFactory().getEvehome().clickOnHowardStern();
			Common.log("Currently in Howard Stern Category -> Naviagte to NPL for a episode");
			getPageFactory().getHoward().verifyUnrestrictedShownamePDT();
			Common.log("Verifying Show Name in NPL");
			getPageFactory().getEvehome().nplShowName();
			Common.log("Verifying Show PDT in NPL");
			getPageFactory().getEvehome().nplPDT();
			Common.log("Minimize NPL");
			getPageFactory().getEvehome().clickminimize();
			Common.log("Minimized NPL");
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
	public void nplUnrestricted_validateHowardOnDEmnadShowNamePdt() {
		Common common = new Common(driver);
		Common.log("Validating Unrestricted Show Name and PDT MOBANDEVER-243");
		try {
			Common.log("Click on Talk SuperCategory");
			getPageFactory().getEvehome().clickOnTalk();
			//common.scrollUntilTextExists("Entertainment");
			Common.log("Click on Howard Stern Category");
			getPageFactory().getEvehome().clickOnHowardStern();
			Common.log("Select a Episode and navigate to NPL");
			getPageFactory().getHoward().verifyUnrestrictedOnDemandShownamePDT();
			Common.log("Verifying Show Name in NPL");
			getPageFactory().getEvehome().nplShowName();
			Common.log("Verifying Show PDT in NPL");
			getPageFactory().getEvehome().nplPDT();
			Common.log("Minmize NPL");
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
	public void nplUnrestricted_validateUnrestrictedShowArt() {
		Common common = new Common(driver);
		Common.log("Validating Unrestricted Show Art MOBANDEVER-209");
		try {
			Common.log("Click on Talk SuperCategory");
			getPageFactory().getEvehome().clickOnTalk();
			//common.scrollUntilTextExists("Entertainment");
			Common.log("Click on Howard Stern Category");
			getPageFactory().getEvehome().clickOnHowardStern();
			Common.log("Select a Episode and navigate to NPL");
			getPageFactory().getHoward().verifyUnrestrictedShowArt();
			Common.log("Verify Album Art");
			getPageFactory().getEvehome().nowPlayingArt();
			Common.log("Minimize NPL");
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
	public void nplUnrestricted_validateUnrestrictedOnDemandShowArt() {
		Common common = new Common(driver);
		Common.log("Validating Unrestricted On Demand Show Art MOBANDEVER-242");
		try {
			Common.log("Click on Talk SuperCategory");
			getPageFactory().getEvehome().clickOnTalk();
			//common.scrollUntilTextExists("Entertainment");
			Common.log("Click on Howard Stern Category");
			getPageFactory().getEvehome().clickOnHowardStern();
			Common.log("Select a Episode and navigate to NPL");
			getPageFactory().getHoward().verifyUnrestrictedShowArt();
			Common.log("Verify Album Art");
			getPageFactory().getEvehome().nowPlayingArt();
			Common.log("Minimize NPL");
			getPageFactory().getEvehome().clickminimize();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * EVQAAND-339 : MOBANDEVER-212 Validation of Now Playing - Live - Unrestricted - Progress
	 * bar with segments
	 */
	@Test(suiteName = "HowardSternCategory", testName = "Unrestricted-Howard Stern category", description = "Now Playing - Live - Unrestricted - Progress bar with segments", enabled = true, groups = {
			"MOBANDEVER-212:EVQAAND-339" })
	public void nplUnrestricted_validateUnrestrictedProgressBar() {
		Common common = new Common(driver);
		Common.log("Validating unrestricted Progress bar MOBANDEVER-212");
		try {
			Common.log("Click on Talk SuperCategory");
			getPageFactory().getEvehome().clickOnTalk();
			//common.scrollUntilTextExists("Entertainment");
			Common.log("Click on Howard Stern Category");
			getPageFactory().getEvehome().clickOnHowardStern();
			Common.log("Select 1st Episode from 1st Show and verify Progress bar");
			getPageFactory().getHoward().verifyUnrestrictedProgressbar();
			Common.log("Minimize NPL");
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
	public void nplUnrestricted_verifyhowardCategory() {
		Common common = new Common(driver);
		Common.log("Verifying Howard Caurosel presence MOBANDEVER-347");
		try {
			Common.log("Click on Talk SuperCategory");
			getPageFactory().getEvehome().clickOnTalk();
			//common.scrollUntilTextExists("Entertainment");
			Common.log("Click on Howard Stern Category");
			getPageFactory().getEvehome().clickOnHowardStern();
			Common.log("Validate Howard Carousel is displayed");
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
	public void nplUnrestricted_HowardShowLogo() {
		Common common = new Common(driver);
		Common.log("Validating Unrestricted On Demand ShowLogo MOBANDEVER-240");
		try {
			Common.log("Click on Talk SuperCategory");
			getPageFactory().getEvehome().clickOnTalk();
			//common.scrollUntilTextExists("Entertainment");
			Common.log("Click on Howard Stern Category");
			getPageFactory().getEvehome().clickOnHowardStern();
			Common.log("Click on Talk SuperCategory");
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
	public void nplUnrestricted_howardSuperCategory() {
		Common common = new Common(driver);
		Common.log("Validation of Howard as a Super Category MOBANDEVER-912");
		try {
			Common.log("Validate all supercategories and Howard as one of them ");
			getPageFactory().getHome().homeCategories();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}
	
	/**
	 * MOBANDEVER-217:EVQAAND-259
	 * Validating Now Playing - Live - Unrestricted - Linear tuner arrows  
	 */
	@Test(suiteName = "HowardSternCategory", testName = "Now Playing - Live - Unrestricted - Linear tuner arrows ",description="Now Playing - Live - Unrestricted - Linear tuner arrows ", enabled = true, groups = {
			"MOBANDEVER-217:EVQAAND-259" })
	public void nplUnrestricted_LinearTuner() {
		Common common = new Common(driver);
		Common.log("Validating Now Playing - Live - Unrestricted - Linear tuner arrows  MOBANDEVER-217");
		try {
			Common.log("Click on Talk SuperCategory");
			getPageFactory().getEvehome().clickOnTalk();
			// common.scrollUntilTextExists("Entertainment");
			Common.log("Click on Howard Stern Category");
			getPageFactory().getEvehome().clickOnHowardStern();
			Common.log("Click on Talk SuperCategory");
			getPageFactory().getHoward().verifyUnrestrictedShowArt();
			getPageFactory().getCategory().verifyLinerTuner();
			getPageFactory().getEvehome().clickminimize();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}
	
	@Test(suiteName = "HowardSternCategory", testName = "Now Playing - Live - Unrestricted - Show name & PDT", description = "Now Playing - Live - Unrestricted - Show name & PDT", enabled = true, groups = {
			"MOBANDEVER-211:EVQAAND-257" })
	public void nplUnrestricted_ShownamePDT() {
		Common common = new Common(driver);
		Common.log("Validating Now Playing - Live - Unrestricted - Show name & PDT MOBANDEVER-211");
		try {
			Common.log("Click on Talk SuperCategory");
			getPageFactory().getEvehome().clickOnTalk();
			// common.scrollUntilTextExists("Entertainment");
			Common.log("Click on Howard Stern Category");
			getPageFactory().getEvehome().clickOnHowardStern();
			Common.log("Select a Episode and navigate to NPL");
			getPageFactory().getHoward().verifyUnrestrictedShowArt();
			getPageFactory().getEvehome().nplShowName();
			getPageFactory().getEvehome().nplPDT();
			getPageFactory().getEvehome().clickminimize();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}
	
	
	@Test(suiteName = "HowardSternCategory", testName = "Now Playing - On Demand - Unrestricted - Elapsed and remaining time", description = "Now Playing - On Demand - Unrestricted - Elapsed and remaining time", enabled = true, groups = {
			"MOBANDEVER-247:EVQAAND-48" })
	public void nplUnrestricted_ElapsedTime() {
		Common common = new Common(driver);
		Common.log("Validating Unrestricted Elapsed and remaining time MOBANDEVER-247");
		try {
			Common.log("Click on Talk SuperCategory");
			getPageFactory().getEvehome().clickOnTalk();
			// common.scrollUntilTextExists("Entertainment");
			Common.log("Click on Howard Stern Category");
			getPageFactory().getEvehome().clickOnHowardStern();
			Common.log("Select a Episode and navigate to NPL");
			getPageFactory().getHoward().verifyUnrestrictedShowArt();
			getPageFactory().getEvehome().validateNPLScreen();
			Common.log("Minimize NPL");
		
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}
	
	
	@Test(suiteName = "HowardSternCategory", testName = "Now Playing - Live - Unrestricted - Player controls", description = "Now Playing - Live - Unrestricted - Player controls", enabled = true, groups = {
			"MOBANDEVER-216:EVQAAND-208" })
	public void nplUnrestricted_PlayerControls() {
		Common common = new Common(driver);
		Common.log("Validating Unrestricted Player controls MOBANDEVER-216");
		try {
			Common.log("Click on Talk SuperCategory");
			getPageFactory().getEvehome().clickOnTalk();
			// common.scrollUntilTextExists("Entertainment");
			Common.log("Click on Howard Stern Category");
			getPageFactory().getEvehome().clickOnHowardStern();
			Common.log("Select a Episode and navigate to NPL");
			getPageFactory().getHoward().verifyUnrestrictedShowArt();
			getPageFactory().getEvehome().validatePlayerControls();
			Common.log("Minimize NPL");
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}



}
