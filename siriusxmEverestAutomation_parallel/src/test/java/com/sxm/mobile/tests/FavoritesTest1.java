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
public class FavoritesTest1 extends RefactorMobileTestCase {
	private final static Logger LOGGER = Logger.getLogger(FavoritesTest1.class.getName());
	private Map<String, UserObj> users = UserHandler.getInstance(locale, env).getUserMap();
	private String referenceurl;
	public UserObj usr = null;
	Common common = new Common(driver);

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

	/*
	 * @Test(suiteName="Favorites" ,testName="Favorites",enabled=true,
	 * groups={"MOBANDEVER-389"}) public void favoritedragdrop(){Common common =
	 * new Common(driver); try{
	 * getPageFactory().getEvehome().validateHomePage();
	 * getPageFactory().getEvehome().validateFavouriteIcon();
	 * getPageFactory().getEvehome().dragdropvalidation(); }catch
	 * (AndriodException ex) { Common.errorlog("Exception occured in : "+
	 * this.getClass().getSimpleName()+" : "+ ex.getMessage()); Assert.fail(
	 * "Exception occured in : "+ this.getClass().getSimpleName()+" : "+
	 * ex.getMessage()); }
	 * 
	 * }
	 */

	@Test(suiteName = "Favorites", testName = "Favorites", description = "Favorites - Edit Mode - Move Favorite Button", enabled = true, groups = {
			"MOBANDEVER-309" })
	public void validatefavorite() {
		Common common = new Common(driver);
		Common.log("Favorites - Edit Mode - Move Favorite Button");
		try {
			getPageFactory().getFavorites().clickFavoriteIcon();
			getPageFactory().getFavorites().verifyFavConfirmButton();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}

	}

	@Test(suiteName = "Favorites", testName = "Favorites-remove button", description = "Favorites - Edit Mode - Remove Favorite Button", enabled = true, groups = {
			"MOBANDEVER-310" })
	public void favoriteValidation() {
		Common common = new Common(driver);
		Common.log("Validating Favorites - Edit Mode - Remove Favorite Button MOBANDEVER-310");
		try {
			getPageFactory().getFavorites().clickFavoriteIcon();
			getPageFactory().getFavorites().removeFavItem();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}

	}

	@Test(suiteName = "Favorites", testName = "Favorites", description = "Favorites - Updated designs", enabled = true, groups = {
			"MOBANDEVER-623" })
	public void validatedFavoritesEdit() {
		Common common = new Common(driver);
		Common.log("Varifying Favorites UI Design MOBANDEVER-675(623)");
		try {
			getPageFactory().getFavorites().clickFavoriteIcon();
			getPageFactory().getFavorites().verifyfavUI();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	@Test(suiteName = "Favorites", testName = "Unrestricted-Favorite Channel", description = "Now Playing - Live - Unrestricted - Favorite channel icon", enabled = true, groups = {
			"MOBANDEVER-208" })
	public void validateunrestictedFavoriteIcon() {
		Common common = new Common(driver);
		Common.log("Now Playing -Live -Unrestricted -Favorite channel icon MOBANDEVER-208");
		try {
			getPageFactory().getEvehome().clickOnTalk();
			getPageFactory().getHoward().clickHoward();
			String showname = getPageFactory().getFavorites().unrestrictedFavoriteIcon();
			getPageFactory().getEvehome().clickminimize();
			// getPageFactory().getFavorites().clickFavoriteIcon();
			// getPageFactory().getFavorites().searchShowName(showname);
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}

	}

	@Test(suiteName = "Favorites", testName = "Favorite", description = "Now Playing - Live - Disallowed - Favorite channel icon", enabled = true, groups = {
			"MOBANDEVER-232" })
	public void validateDisallowedFavoriteIcon() {
		Common common = new Common(driver);
		Common.log("Now Playing - Live - Disallowed - Favorite channel icon MOBANDEVER-232");
		try {
			getPageFactory().getEvehome().clickNews();
			common.scrollUntilTextExists("News/Public Radio");
			getPageFactory().getEvehome().clickSubCatNews();
			String showname = getPageFactory().getFavorites().disallowedFavoriteIcon();
			getPageFactory().getEvehome().clickminimize();
			// getPageFactory().getFavorites().clickFavoriteIcon();
			// getPageFactory().getFavorites().searchShowName(showname);
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	@Test(suiteName = "Favorites", testName = "Favorite", description = "Now Playing - Live - DMCA Restricted - Favorite channel icon", enabled = true, groups = {
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
			// getPageFactory().getFavorites().searchShowName(showname);
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	@Test(suiteName = "Favorites", testName = "Favorite", description = "Now Playing - On Demand - Unrestricted - Favorite icon", enabled = true, groups = {
			"MOBANDEVER-241" })
	public void validateOnDemandRestrictedFavoriteIcon() {
		Common common = new Common(driver);
		Common.log("Now Playing - On Demand - Unrestricted - Favorite icon MOBANDEVER-241");
		try {
			common.implicitWait(5);
			getPageFactory().getEvehome().clickOnTalk();
			getPageFactory().getHoward().clickHoward();
			String showname = getPageFactory().getFavorites().unrestrictedFavoriteIcon();
			getPageFactory().getEvehome().clickminimize();
			getPageFactory().getFavorites().clickFavoriteIcon();
			// getPageFactory().getFavorites().searchShowName(showname);
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}
}
