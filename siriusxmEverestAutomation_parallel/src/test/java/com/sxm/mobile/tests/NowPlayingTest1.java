/**
 * 
 */
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
public class NowPlayingTest1 extends RefactorMobileTestCase {
	private final static Logger LOGGER = Logger.getLogger(NowPlayingTest1.class.getName());
	private Map<String, UserObj> users = UserHandler.getInstance(locale, env).getUserMap();
	private String referenceurl;
	public UserObj usr = null;

	
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
	
	
	
	
	@Test(suiteName = "NowPlaying", testName = "Now Playing - Live - Sports Play by Play channels ", description = "Now Playing - Live - Sports Play by Play channels ", enabled = true, groups = {
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
	
	

	@Test(suiteName = "NowPlaying", testName = "Now Playing - On Demand - Disallowed", description = "Now Playing - On Demand - Disallowed - Progress bar", enabled = true, groups = {
			"MOBANDEVER-262:EVQAAND-175" })
	public void nowPlaying_OnDemanddisallowedProgressbar() {
		Common common = new Common(driver);
		Common.log("Validating Now Playing - On Demand - Disallowed - Progress bar- MOBANDEVER 262");
		try {
			getPageFactory().getEvehome().clickNews();
			common.scrollUntilTextExists("News/Public Radio");
			getPageFactory().getEvehome().clickSubCatNews();
			getPageFactory().getNowplayingpage().clickDisallowedChannel();
			getPageFactory().getEvehome().validateNPLScreen();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}
	
	
	@Test(suiteName = "NowPlaying", testName = "Now Playing - On Demand - Disallowed", description = "Now Playing - Live - Disallowed - Linear tuner arrows ", enabled = true, groups = {
			"MOBANDEVER-238:EVQAAND-170" })
	public void nowPlaying_DisallowedLinearTuner() {
		Common common = new Common(driver);
		Common.log("Validating Now Playing - Live - Disallowed - Linear tuner arrows - MOBANDEVER 238");
		try {
			getPageFactory().getEvehome().clickNews();
			common.scrollUntilTextExists("News/Public Radio");
			getPageFactory().getEvehome().clickSubCatNews();
			getPageFactory().getNowplayingpage().clickDisallowedChannel();
			getPageFactory().getCategory().verifyLinerTuner();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}
	
	@Test(suiteName = "NowPlaying", testName = "NowNow Playing -Minimize button ", description = "Now Playing -Minimize button for Disallowed Live", enabled = true, groups = {
	"MOBANDEVER-268:EVQAAND-282" })
	public void verifyNowPlayingDisallowedMinimizeButton() {
		Common common = new Common(driver);
		Common.log("Verifying Now Playing -Minimize button for Disallowed Live-268");
		try {
			getPageFactory().getEvehome().clickNews();
			getPageFactory().getEvehome().scrollUntilTextExists("Canadian");
			getPageFactory().getEvehome().clickSubCatNews();
			getPageFactory().getEvehome().clickNewsChannel1();
			getPageFactory().getEvehome().testMiniBarNavigation();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}
	
	
	
	

}
