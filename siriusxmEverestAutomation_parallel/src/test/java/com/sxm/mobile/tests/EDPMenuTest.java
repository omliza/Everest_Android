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
public class EDPMenuTest extends RefactorMobileTestCase {
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

	/*
	 * EVQAAND-22: EDP Menu - Channel Details
	 * MOBANDEVER 446 validating EDP Menu - Channel Details
	 */
	@Test(suiteName = "Categories", testName = "MOBANDEVER 446:EDP Menu - Verify EDP for Channel", description="EDP Menu - Verify EDP menu displayed for Channel"
			,enabled = true, groups = { "EVQAAND-22", })
	public void verifyChannelEDPMenu() {
		Common common = new Common(driver);
		Common.log("Validating Long Press Channel Item-EVQAAND-22:MOBANDEVER 446");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Dance/Electronic");
			getPageFactory().getEvehome().clickMucisSubHipHop();
			getPageFactory().getEvehome().verifyLongPressChannelTile();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/*
	 * EVQAAND-57: EDP Menu - Show Details
	 * MOBANDEVER 449 validating EDP Menu - Show Details
	 */
	@Test(suiteName = "Categories", testName = "MOBANDEVER 449:EDP Menu - Show Details", description="EDP Menu - Show Details",
			enabled = true, groups = { "EVQAAND-57", })
	public void verifyShowEDPMenu() {
		Common common = new Common(driver);
		Common.log("Validating Long Press Show Item-EVQAAND-57:MOBANDEVER 449");
		try {
			getPageFactory().getEvehome().clickNews();
			common.scrollUntilTextExists("News/Public Radio");
			getPageFactory().getEvehome().clickSubCatNews();
			getPageFactory().getEvehome().clickOnDemand();
			getPageFactory().getEvehome().verifyLongPressShowTile();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/*
	 * EVQAAND-93: EDP Menu - Episode Details : Save for Later EDP menu
	 * MOBANDEVER 452 validating EDP Menu - Episode Details
	 */
	@Test(suiteName = "Categories", testName = "MOBANDEVER 452:EDP Menu - Episode Details : Save for Later EDP menu", description="EDP Menu - Episode Details : Save for Later EDP menu is displayed",
			enabled = true, groups = { "EVQAAND-93", })
	public void verifyEpisodeEDPMenu() {
		Common common = new Common(driver);
		Common.log("Validating Long Press Episode Item-EVQAAND-93:MOBANDEVER 452");
		try {
			getPageFactory().getEvehome().clickOnTalk();
			common.scrollUntilTextExists("Entertainment");
			getPageFactory().getHoward().clickOnHowardStern();
			getPageFactory().getEvehome().verifyLongPressEpisodeTile();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/*
	 * EVQAAND-302: EDP Menu - Channel Details - On Demand Shows
	 * MOBANDEVER 447 Validating EDP Menu - Channel Details - On Demand Shows
	 */
	@Test(suiteName = "Categories", testName = "MOBANDEVER 447:EDP Menu - Channel Details - On Demand Shows displayed from OnDemand EDP", description="EDP Menu - Channel Details - On Demand Shows are displayed from OnDemand EDP Menu", 
			enabled = true, groups = { "EVQAAND-302", })
	public void verifyOnDemandEDPMenu() {
		Common common = new Common(driver);
		Common.log("Validating Long Press OnDemand Item-MOBANDEVER 447");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Dance/Electronic");
			getPageFactory().getEvehome().clickMucisSubHipHop();
			getPageFactory().getEvehome().clickOnDemand();
			getPageFactory().getEvehome().verifyLongPressOnDemandShows();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/*
	 * EVQAAND-62: EDP Menu - Show Details - Add Show to Favorites
	 * MOBANDEVER-451 Validating EDP Menu - Show Details - Add Show to Favorites
	 */
	@Test(suiteName = "Categories", testName = "MOBANDEVER-451:EDP Menu - Show Details - Add Show to Favorites", description = "EDP Menu - Show Details - Add Show to Favorites", enabled = true, groups = {
			"EVQAAND-62" })
	public void VerifyEDPFavorites() {
		Common common = new Common(driver);
		Common.log("Verifying EDP Menu -Show Details -Add Show to Favorites -> EVQAAND-62:MOBANDEVER-451 ");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Dance/Electronic");
			getPageFactory().getEvehome().clickMucisSubHipHop();
			getPageFactory().getEvehome().clickOnDemand();
			getPageFactory().getEvehome().validateOnDemandfavorite();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/*
	 * EVQAAND-33: EDP Menu - Channel Details - Add Channel to Favorites
	 * MOBANDEVER-448 validate EDP Menu - Channel Details - Add Channel to
	 * Favorites
	 */
	@Test(suiteName = "Categories", testName = "MOBANDEVER-448:EDP Menu - Add Channel to Favorites ", description = "EDP Menu - Channel Details - Add Channel to Favorites ", enabled = true, groups = {
			"EVQAAND-33" })
	public void VerifyEDPaddChannelToFavorites() {
		Common common = new Common(driver);
		Common.log("Verifying EDP Menu - Channel Details - Add Channel to Favorites -> EVQAAND-33:MOBANDEVER-448 ");
		try {
			Common.impicitWait(4);
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Dance/Electronic");
			getPageFactory().getEvehome().clickMucisSubHipHop();
			getPageFactory().getEvehome().addChannelToFavorite();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/*
	 * EVQAAND-116:Validate whether the added/ removed favorite shows are present under Favorite tab irrespective of navigation or log out
	 * MOBANDEVER-454 validate EDP Menu - Episode Details - Add Show to
	 * Favorites
	 */
	@Test(suiteName = "Categories", testName = "MOBANDEVER-454:validate EDP Menu - Episode Details - Add or Remove Show to Favorites from OnDemand EDP", description = "Validate whether the added favorite shows from AOD EDP menu are present under Favorite tab", enabled = true, groups = {
			"EVQAAND-116" })
	public void VerifyEDPAddShowToFavorites() {
		Common common = new Common(driver);
		Common.log("Verifying EDP Menu - Episode Details - Add or remove Show to Favorites -> EVQAAND-116:MOBANDEVER-454 ");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Dance/Electronic");
			getPageFactory().getEvehome().clickMucisSubHipHop();
			getPageFactory().getEvehome().clickOnDemand();
			getPageFactory().getEvehome().addShowToFavorite();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

}
