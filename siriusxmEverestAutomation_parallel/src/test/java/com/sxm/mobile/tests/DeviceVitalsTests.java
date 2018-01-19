package com.sxm.mobile.tests;

import org.testng.annotations.Test;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import java.awt.geom.GeneralPath;
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
public class DeviceVitalsTests extends RefactorMobileTestCase {
	private final static Logger LOGGER = Logger.getLogger(OnDemandTest.class.getName());
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
			common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}
	
	/*
	 * Validate NPL for a Disallowed AOD
	 * Verify its Show Art
	 */
	@Test(suiteName = "DeviceVitals", testName = "Disallowed AOD - News Category", description = "Now Playing - On Demand - Disallowed - Show Art", enabled = true, groups = {
			"MOBANDEVER-260" })
	public void onDemanddisallowedShowArt() {
		Common common = new Common(driver);
		common.log("Verifying NP On Demand Disallowed ShowArt MOBANDEVER-260");
		try {
			getPageFactory().getEvehome().clickNews();
			getPageFactory().getEvehome().scrollUntilTextExists("Canadian");
			getPageFactory().getEvehome().clickSubCatNews();
			getPageFactory().getEvehome().clickOnDemand();
			getPageFactory().getCategory().disallowedOnDemandShowArt();
		} catch (AndriodException ex) {
			common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}
	
	/*
	 * Search - Audio Carousel Results are displayed
	 *EVQAAND-308:MOBANDEVER-705:Validating the Audio On-Demand Carousel and Tiles After search
	 */
	@Test(suiteName = "DeviceVitals", testName = "Search: Audio On Demand Carousel – Header", description = "Search – Verify Audio On Demand Carousel Header text", enabled = true, groups = {
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
	
	/*
	 * NPL for Sports -> Play by Play channel
	 */
	@Test(suiteName = "DeviceVitals", testName = "Now Playing - Live - Sports Play by Play channels ", description = "Now Playing - Live - Sports Play by Play channels ", enabled = true, groups = {
	"MOBANDEVER-1030:EVQAAND-3" })
	public void nowPlaying_SportsPlaybyPlaychannels() {
		Common common = new Common(driver);
		Common.log("Validating Now Playing - Live - Sports Play by Play channels - MOBANDEVER 1030");
		try {
		getPageFactory().getNowplayingpage().clickOnSports();
		getPageFactory().getNowplayingpage().verifySportsPlayChannel();
		getPageFactory().getEvehome().nowPlayingArt();
		getPageFactory().getEvehome().nplChnumber();
		getPageFactory().getEvehome().nplLogo();
		getPageFactory().getEvehome().validateNPLScreen();
		common.log("All now playing elements are present");
		} catch (AndriodException ex) {
		Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}
		
	/*
	* EVQAAND-72: Categories - On Demand Episode Listing - Air date
	* MOBANDEVER-398 : validation of Categories - On Demand Episode Listing -Airdate
	*/
	@Test(suiteName = "DeviceVitals", testName = "AOD : Hip-Hop: VerifyOnDemandEpisodeAirdate", description = "Categories - On Demand Episode Listing - Airdate", enabled = true, groups = {
		"EVQAAND-72:MOBANDEVER-398" })
	public void onDemandEpisodeAirdate() {
		Common common = new Common(driver);
		Common.log(" Verifying Categories - On Demand Episode Listing - Airdate -> EVQAAND-72:MOBANDEVER-398");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("R&B");
			getPageFactory().getEvehome().clickMucisSubHipHop();
			getPageFactory().getEvehome().clickOnDemand();
			getPageFactory().getEvehome().VerifyOnDemandEpisodeAirdate();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}
		
	/*
	* MOBANDEVER-209 Validation of Now Playing - Live - Unrestricted - Display Show Art
	*/
	@Test(suiteName = "DeviceVitals", testName = "Unrestricted-Howard Stern category", description = "Now Playing - Live - Unrestricted - Display Show Art", enabled = true, groups = {
		"MOBANDEVER-209" })
	public void nplUnrestricted_validateUnrestrictedShowArt() {
		Common common = new Common(driver);
		Common.log("Validating Unrestricted Show Art MOBANDEVER-209");
		try {
			Common.log("Click on Talk SuperCategory");
			getPageFactory().getEvehome().clickOnTalk();
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
	/*
	 * "DMCA NPL & Add and Remove Favorite - Tune to a DMCA (Music -> pop) channel
	 * and add it to Favorite and Delete from Favorite
	 * Delete from Favorite (not necessarily the same channel as played)
	 */
	@Test(suiteName = "DeviceVitals", testName = "DMCA NPL & Add and Remove Favorite", description = "Now Playing - Live - DMCA Restricted - Favorite channel icon", enabled = true, groups = {
	"MOBANDEVER-221" })
	public void validateDmcaFavoriteIcon() {
		Common common = new Common(driver);
		Common.log("Now Playing - Live - DMCA - Favorite channel icon MOBANDEVER-221");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Music Categories");
			getPageFactory().getEvehome().clickMucisSubPop();
			String showname = getPageFactory().getFavorites().dmcaFavoriteIcon();
			getPageFactory().getEvehome().clickminimize();
			getPageFactory().getFavorites().clickFavoriteIcon();
			getPageFactory().getFavorites().removeFavItem();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}
}