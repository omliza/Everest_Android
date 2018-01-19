package com.sxm.mobile.tests;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
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
public class NowPlayingVideoTest extends RefactorMobileTestCase {
	private final static Logger LOGGER = Logger.getLogger(NowPlayingDMCATest.class.getName());
	private Map<String, UserObj> users = UserHandler.getInstance(locale, env).getUserMap();
	private String referenceurl;
	public UserObj usr = null;

	@BeforeClass(alwaysRun = true)
	public void login() throws InValidOSException, InValidToolException {
		LOGGER.info(users.get("prod1").getUserName());
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		getPageFactory().getLogin().login();
		Common.impicitWait(5);
	}

	/**
	 * EVQAAND-267 Validate the Ondemand text and Show logo displayed in Now
	 * Playing Video screen
	 */
	@Test(suiteName = "NowPlaying", testName = "validateOndemnadTextShowLogo", description = "Validate the Ondemand text and Show logo displayed in Now Playing Video screen", enabled = true, groups = {
			"EVQAAND-267" })
	public void validateVideoOndemnadTextShowLogo() {
		Common common = new Common(driver);
		Common.log("Validate the Ondemand text and Show logo EVQAAND-267");
		try {
			Common.log("Click on Talk SuperCategory");
			getPageFactory().getEvehome().clickOnTalk();
			// common.scrollUntilTextExists("Entertainment");
			Common.log("In Talk SuperCategory -> Click on Howard Stern");
			getPageFactory().getEvehome().clickOnHowardStern();
			Common.log("playing Video Episode");
			getPageFactory().getHoward().playVideoEpisode();
			Common.log("Currently in Howard Stern Category -> Verify Howard Video show Logo");
			getPageFactory().getHoward().verifyHowardVideosShowLogo();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}

	}

	@Test(suiteName = "NowPlaying", testName = "validateProgressBar", description = "Now Playing - Video - On Demand - Progress bar with segments", enabled = true, groups = {
			"EVQAAND-261" })
	public void validateNPVideoProgressBar() {
		Common common = new Common(driver);
		Common.log("Now Playing - Video - On Demand - Progress bar with segments  EVQAAND-261");
		try {
			Common.log("Click on Talk SuperCategory");
			getPageFactory().getEvehome().clickOnTalk();
			// common.scrollUntilTextExists("Entertainment");
			Common.log("In Talk SuperCategory -> Click on Howard Stern");
			getPageFactory().getEvehome().clickOnHowardStern();
			Common.log("playing Video Episode");
			getPageFactory().getHoward().playVideoEpisode();
			Common.log("Validating Video channel progress bar");
			getPageFactory().getNowplayingpage().verifyProgressbar();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}

	}
	
	
	@Test(suiteName = "NowPlaying", testName = "Now Playing - Video - On Demand – Basic Player Controls", description = "Now Playing - Video - On Demand – Basic Player Controls", enabled = true, groups = {
			"EVQAAND-281" })
	public void validateNPOnDemandBasicPlayerControls() {
		Common common = new Common(driver);
		Common.log("Now Playing - Video - On Demand – Basic Player Controls  EVQAAND-281");
		try {
			Common.log("Click on Talk SuperCategory");
			getPageFactory().getEvehome().clickOnTalk();
			// common.scrollUntilTextExists("Entertainment");
			Common.log("In Talk SuperCategory -> Click on Howard Stern");
			getPageFactory().getEvehome().clickOnHowardStern();
			Common.log("playing Video Episode");
			getPageFactory().getHoward().playVideoEpisode();
			Common.log("Validating Video  Basic Player Controls");
			getPageFactory().getNowplayingpage().validateBasicPlayerControls();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}

	}
	
	

	@Test(suiteName = "NowPlaying", testName = "Now Playing - Video - On Demand – Hard-code temporary navigation into Now Playing video screen", description = "Now Playing - Video - On Demand –  navigation into Now Playing video screen", enabled = true, groups = {
			"EVQAAND-264" })
	public void validateNPOnDemandNavigation() {
		Common common = new Common(driver);
		Common.log("Now Playing - Video - On Demand – navigation into Now Playing video screen  EVQAAND-264");
		try {
			Common.log("Click on Talk SuperCategory");
			getPageFactory().getEvehome().clickOnTalk();
			// common.scrollUntilTextExists("Entertainment");
			Common.log("In Talk SuperCategory -> Click on Howard Stern");
			getPageFactory().getEvehome().clickOnHowardStern();
			Common.log("playing Video Episode");
			getPageFactory().getHoward().playVideoEpisode();
			getPageFactory().getEvehome().clickBackButton();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}


	@Test(suiteName = "NowPlaying", testName = "Now Playing - Video - On Demand - Elapsed and remaining time", description = "Now Playing - Video - On Demand - Elapsed and remaining time", enabled = true, groups = {
			"EVQAAND-78" })
	public void validateNPVideoElapsedRemainingTime() {
		Common common = new Common(driver);
		Common.log("Now Playing - Video - On Demand - Elapsed and remaining time  EVQAAND-78");
		try {
			Common.log("Click on Talk SuperCategory");
			getPageFactory().getEvehome().clickOnTalk();
			// common.scrollUntilTextExists("Entertainment");
			Common.log("In Talk SuperCategory -> Click on Howard Stern");
			getPageFactory().getEvehome().clickOnHowardStern();
			Common.log("playing Video Episode");
			getPageFactory().getHoward().playVideoEpisode();
			Common.log("Validating Video channel progress bar");
			getPageFactory().getNowplayingpage().verifyProgressbar();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}

	}
	
	@Test(suiteName = "NowPlaying", testName = "Now Playing - Video - On Demand - Show name & PDT", description = "Now Playing - Video - On Demand - Show name & PDT", enabled = true, groups = {
			"EVQAAND-185" })
	public void validateNPVideoOnDemandShownamePDT() {
		Common common = new Common(driver);
		Common.log("Now Playing - Video - On Demand - Show name & PDT  EVQAAND-185");
		try {
			Common.log("Click on Talk SuperCategory");
			getPageFactory().getEvehome().clickOnTalk();
			// common.scrollUntilTextExists("Entertainment");
			Common.log("In Talk SuperCategory -> Click on Howard Stern");
			getPageFactory().getEvehome().clickOnHowardStern();
			Common.log("playing Video Episode");
			getPageFactory().getHoward().playVideoEpisode();
			Common.log("Validating  Video - On Demand - Show name & PDT");
			getPageFactory().getEvehome().nplShowName();
			getPageFactory().getEvehome().nplPDT();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}

	}


	
}
