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
public class OnDemandTest extends RefactorMobileTestCase {
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

	/**
	 * MOBANDEVBER-282 Validating On Demand Show Listing
	 */
	@Test(suiteName = "Categories", testName = "On Demand Show Listing", description = "Categories - On Demand Shows Tab", enabled = true, groups = {
			"MOBANDEVBER-282" })
	public void onDemandShowstab() {
		Common common = new Common(driver);
		common.log("Verifying On Demnad Show Listing MOBANDEVER-282");
		try {
			getPageFactory().getEvehome().clickonMusic();
			getPageFactory().getEvehome().scrollUntilTextExists("Hip-Hop");
			getPageFactory().getEvehome().clickMucisSubRock();
			getPageFactory().getEvehome().clickOnDemand();
			getPageFactory().getCategory().OnDemandshowListing();
		} catch (AndriodException ex) {
			common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVBER-394 Validating Categories - On Demand Episode Listing - On
	 * Demand Episode List
	 */
	@Test(suiteName = "Categories", testName = "Demnad Show episodesrecent Banner", description = "Categories - On Demand Episode Listing - On Demand Episode List", enabled = true, groups = {
			"MOBANDEVBER-394" })
	public void verifyRecentbanner() {
		Common common = new Common(driver);
		common.log("Verifying On Demnad Show episodesrecent Banner MOBANDEVER 394");
		try {
			getPageFactory().getEvehome().clickonMusic();
			getPageFactory().getEvehome().scrollUntilTextExists("Hip-Hop");
			getPageFactory().getEvehome().clickMucisSubHipHop();
			getPageFactory().getEvehome().clickOnDemand();
			getPageFactory().getCategory().recentBanner();
		} catch (AndriodException ex) {
			common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-393 validation of Categories - On Demand Episode Listing -
	 * Back Button
	 */
	@Test(suiteName = "Categories", testName = "EpisodeListingBackButton", description = "Categories - On Demand Episode Listing - Back Button", enabled = true, groups = {
			"MOBANDEVBER-393" })
	public void verifyEpisodeListingBackButton() {
		Common common = new Common(driver);
		common.log("Executing episodeListingBackButton method-MOBANDEVER 393");
		try {
			getPageFactory().getEvehome().clickonMusic();
			getPageFactory().getEvehome().scrollUntilTextExists("Hip-Hop");
			getPageFactory().getEvehome().clickMucisSubHipHop();
			getPageFactory().getEvehome().clickOnDemand();
			getPageFactory().getCategory().episodeListingBackButton();
		} catch (AndriodException ex) {
			common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * MOBANDEVER-260 validation of Now Playing - On Demand - Disallowed - Show
	 * Art
	 */
	@Test(suiteName = "NowPlaying", testName = "Disallowed", description = "Now Playing - On Demand - Disallowed - Show Art", enabled = true, groups = {
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

}
