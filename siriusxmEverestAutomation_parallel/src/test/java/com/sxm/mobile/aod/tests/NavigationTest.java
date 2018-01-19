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
public class NavigationTest extends RefactorMobileTestCase {
	private final static Logger LOGGER = Logger.getLogger(NavigationTest.class.getName());
	private Map<String, UserObj> users = UserHandler.getInstance(locale, env).getUserMap();
	private String referenceurl;
	public UserObj usr = null;

	public boolean isExist(By by) {
		boolean flag = false;
		try {
			WebElement el = driver.findElement(by);
			flag = true;
		} catch (Exception ex) {
			flag = false;
		}
		return flag;
	}

	/*
	 * @org.testng.annotations.BeforeClass(alwaysRun = true) public void login()
	 * throws InValidOSException, InValidToolException {
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

	@Test(suiteName = "MiniBar", testName = "Main Navigation Tab", description = "Main Navigation - Tap twice to return to root page", enabled = true, groups = {
			"MOBANDEVER-298" })
	public void validateMainNavigation() {
		Common common = new Common(driver);
		Common.log("Validating Main Navigation tab MOBANDEVER-298");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Music Categories");
			getPageFactory().getEvehome().clickMucisSubHipHop();
			getPageFactory().getFavorites().clickFavoriteIcon();
			getPageFactory().getEvehome().validatemainnavigation();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	@Test(suiteName = "MiniBar", testName = "Main Navigation Tab", description = "Main Navigation - Display nav", enabled = true, groups = {
			"MOBANDEVER-296" })
	public void mainNavigationverify() {
		Common common = new Common(driver);
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Music Categories");
			getPageFactory().getEvehome().clickMucisSubHipHop();
			getPageFactory().getFavorites().clickFavoriteIcon();
			getPageFactory().getEvehome().validatemainnavigation();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	@Test(suiteName = "MiniBar", testName = "Main Navigation Tab", description = "Main Navigation - Navigate to section", enabled = true, groups = {
			"MOBANDEVER-297" })
	public void mainNavigation() {
		Common common = new Common(driver);
		Common.log("Validating Main Navigation tab MOBANDEVER-297");
		try {
			getPageFactory().getEvehome().verifymainnavigation();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	@Test(suiteName = "MiniBar", testName = "Now playing screen-Mini Bar", description = "Video - Now Playing - All Video Types - Minimize button", enabled = true, groups = {
			"MOBANDEVER-442" })
	public void vedioMiniBar() {
		Common common = new Common(driver);
		Common.log("Vaidating Vedoi button MOBANDEVER-442");
		/*
		 * try{ getPageFactory().getEvehome().clickVedio();
		 * getPageFactory().getEvehome().clickminimize(); }catch
		 * (AndriodException ex) { Common.errorlog("Exception occured in : "+
		 * this.getClass().getSimpleName()+" : "+ ex.getMessage()); Assert.fail(
		 * "Exception occured in : "+ this.getClass().getSimpleName()+" : "+
		 * ex.getMessage()); }
		 */
	}

	@Test(suiteName = "MiniBar", testName = "Video - Now Playing – Back button", description = "Video - Now Playing – Back button", enabled = true, groups = {
			"MOBANDEVER-622" })
	public void videobackButton() {
		Common common = new Common(driver);
		Common.log("Validating Video Now Playing  Back button  MOBANDEVER-622");
		/*
		 * try{ getPageFactory().getEvehome().clickVedio();
		 * Common.impicitWait(4);
		 * getPageFactory().getEvehome().clickBackButton(); }catch
		 * (AndriodException ex) { Common.errorlog("Exception occured in : "+
		 * this.getClass().getSimpleName()+" : "+ ex.getMessage()); Assert.fail(
		 * "Exception occured in : "+ this.getClass().getSimpleName()+" : "+
		 * ex.getMessage()); }
		 */
	}

}
