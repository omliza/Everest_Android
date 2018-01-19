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
public class ProfileTest extends RefactorMobileTestCase {
	private final static Logger LOGGER = Logger.getLogger(ProfileTest.class.getName());
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
	 * Validating Profile - Sign Out
	 * EVQAAND-117:EVQAAND-137 : from Login Epic
	 */
	@Test(suiteName = "Profile", testName = "Profile - Sign Out", description = "Validate whether the user is able to login after signing out from the app", 
			enabled = true, priority=1 ,groups = {"EVQAAND-117:EVQAAND-137" })
	public void ProfileSignOutValidation() {
		Common common = new Common(driver);
		Common.log("Validate whether the user is able to login after signing out from the app : EVQAAND-117:EVQAAND-137");
		try {
			getPageFactory().getProfile().profileSignOut();
			getPageFactory().getLogin().login();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/*
	 * EVQAAND-248:MOBANDEVER-559 : Verify that Profile Screen is displayed with Profile tab highlighted on
	 */
	@Test(suiteName = "Profile", testName = "Profile: Profile Tab highlighted", description = "Verify that Profile Screen is displayed with Profile tab highlighted on tapping the Profile", enabled = true,priority=1 ,groups = {
			"EVQAAND-248" })
	public void validateProfile() {
		Common common = new Common(driver);
		Common.log("Validating Profile Sign in EVQAAND-248:MOBANDEVER-559");
		try {
			getPageFactory().getProfile().clickProfile();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/*
	 * EVQAAND-91: MOBANDEVER-559:Validate whether the following texts are displayed in Profile Screen
	 */
	@Test(suiteName = "Profile", testName = "Profile: Verify Texts displayed on screen", description = "Validate whether the following texts are displayed in Profile Screen", enabled = true, 
			priority=2,groups = {"EVQAAND-91" })
	public void verifyProfileMenuList() {
		Common common = new Common(driver);
		Common.log("Validating Profile contents EVQAAND-91: MOBANDEVER-559");
		try {
			common.reLaunchApp();
			getPageFactory().getProfile().clickProfile();
			getPageFactory().getProfile().verifyProfileMenu();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/*
	 * TBD: This is not yet Implemented feature - DISABLED THIS TEST CASE
	 * EVQAAND-86: MOBANDEVER-559:Validate the Recently Played, Saved for Later and Notifications in
	 * Profile
	 */
	@Test(suiteName = "Profile", testName = "Profile : Recently Played, Saved for Later and Notifications in Profile screen", description = "Validate the Recently Played, Saved for Later and Notifications in Profile ", 
			enabled = false, groups = {"EVQAAND-86" })
	public void validateNotificationProfile() {
		Common common = new Common(driver);
		try {
			getPageFactory().getProfile().clickProfile();
			getPageFactory().getProfile().validateProfileNotification();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/*
	 * EVQAAND-160: MOBANDEVER-566: Validate whether About screen is displayed in Profile
	 */
	@Test(suiteName = "Profile", testName = "Profile: Validate whether About screen is displayed in Profile", description = "Validate whether About screen is displayed in Profile", enabled = true, priority=1, groups = {
			"EVQAAND-160" })
	public void validateAbout() {
		Common common = new Common(driver);
		Common.log("Verifying presence of about link on the Profile Menu -> EVQAAND-160: MOBANDEVER-566");
		try {
			getPageFactory().getProfile().clickProfile();
			getPageFactory().getProfile().verifyAbout();
			getPageFactory().getProfile().clickBackButton();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/*
	 * EVQAAND-109: Validate the Version and Build number displayed in About Screen
	 */
	@Test(suiteName = "Profile", testName = "Profile: Version and Build number displayed in About Screen", description = "Validate the Version and Build number displayed in About Screen", enabled = true, priority=1,groups = {
			"EVQAAND-109" })
	public void validateBuildVersion() {
		Common common = new Common(driver);
		Common.log("Verifying presence of Build Number and Version on the Profile Menu EVQAAND-109");
		try {
			getPageFactory().getProfile().clickProfile();
			getPageFactory().getProfile().verifyVersion();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/*
	 * EVQAAND-293: Validate whether Customer Agreement document is displayed
	 */
	@Test(suiteName = "Profile", testName = "Profile: Validate whether Customer Agreement document is displayed", description = "Validate whether Customer Agreement document is displayed", enabled = true, priority=2,groups = {
			"EVQAAND-293" })
	public void validateCustomerAgreement() {
		Common common = new Common(driver);
		Common.log("Verifying Customet Agreemnet Page EVQAAND-293");
		try {
			getPageFactory().getProfile().clickProfile();
			getPageFactory().getProfile().verifyCustomerAgreement();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/*
	 * EVQAAND-88:: Validate whether Privacy Policy document is displayed
	 */
	@Test(suiteName = "Profile", testName = "Profile: Privacy Policy document is displayed", description = "Validate whether Privacy Policy document is displayed", enabled = true,priority=2, groups = {
			"EVQAAND-88" })
	public void validatePrivacyPolicy() {
		Common common = new Common(driver);
		Common.log("Verifying Privacy Policy Page EVQAAND-88");
		try {
			getPageFactory().getProfile().clickProfile();
			getPageFactory().getProfile().verifyPrivacyPolicy();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/*
	 * EVQAAND-159: Validating Profile - Send Feedback
	 */
	@Test(suiteName = "Profile", testName = "Profile - Send Feedback Appentive Dialouge", description = "Profile - Verify Send Feedback Appentive Dialouge", enabled = true, priority=2, groups = {
			"EVQAAND-159" })
	public void validateSendFeedback() {
		Common common = new Common(driver);
		Common.log("Profile - Send Feedback Appentive Dialouge EVQAAND-159");
		try {
			driver.closeApp();
			driver.launchApp();
			getPageFactory().getProfile().clickProfile();
			common.scrollUntilTextExists("Help & Support");
			getPageFactory().getProfile().sendFeedback();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/*
	 * EVQAAND-265: MOBANDEVER-567: Validating Profile - Manage Account Settings
	 */
	@Test(suiteName = "Profile", testName = "Profile - Manage Account Settings", description = "Profile - Validate Manage Account Settings", enabled = true,priority=2 ,groups = {
			"EVQAAND-265" })
	public void validateProfileManageAccout() {
		Common common = new Common(driver);
		Common.log("Validating Profile - Manage Account Settings EVQAAND-265");
		try {
			Common.impicitWait(3);
			getPageFactory().getProfile().clickProfile();
			Common.impicitWait(3);
			common.scrollUntilTextExists("Sign Out");
			getPageFactory().getProfile().verifManageAccount();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/*
	 * EVQAAND-15: Profile - Help and Support
	 */
	@Test(suiteName = "Profile", testName = "Profile - Help and Support", description = "Profile - Validate Help and Support links", enabled = true, priority=2,groups = {
			"EVQAAND-15" })
	public void validateProfileHelpSupport() {
		Common common = new Common(driver);
		Common.log("Validating Profile - Help and Support EVQAAND-15");
		try {
			Common.impicitWait(3);
			getPageFactory().getProfile().clickProfile();
			Common.impicitWait(3);
			common.scrollUntilTextExists("About");
			getPageFactory().getProfile().verifyHelpSupport();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}
	
	/*
	 * EVQAAND-309: Profile - Ford SYNC App Link 
	 */
	@Test(suiteName = "Profile", testName = "Profile - Ford SYNC App Link", description = "Profile - Validate Ford SYNC App Link ", enabled = true, priority=2,groups = {
			"EVQAAND-309" })
	public void validateProfileFordSYNCAppLink () {
		Common common = new Common(driver);
		Common.log("Verifying Profile -Validate Ford SYNC App Link  EVQAAND-309");
		try {
			getPageFactory().getProfile().clickProfile();
			common.implicitWait(4);
			common.scrollUntilTextExists("About");
			getPageFactory().getProfile().verifyFordSynLink();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}
	
	/*
	 * EVQAAND-118: The User wants to access and change Application Settings so that he/she
	 * can control various aspects of the User Experience.
	 * This functionality is NOT YET IMPLEMENTED
	 */
	@Test(suiteName = "Profile", testName = "Profile - Application Settings", description = "Profile - Validate Application Settings", enabled = true, priority = 2, groups = {
			"EVQAAND-118" })
	public void testVerifyApplicationSettings() {
		Common common = new Common(driver);
		common.log("Executing EVQAAND-118");
		try {
			getPageFactory().getProfile().clickProfile();
			getPageFactory().getProfile().clickOnApplicationSetting();
			getPageFactory().getProfile().validateApplicationSettingsBrdCrumb();
			getPageFactory().getProfile().validateApplicationSettingsPageHeadersAndSubHeaders();
			//getPageFactory().getProfile().testTuneStartToggle();
			//getPageFactory().getProfile().testOverrideToggle();
			Common.log("This test will Fail - as Application Settings feature is not yet implemented");
		} catch (AndriodException ex) {
			common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}
	
	/*
	 *  EVQAAND-182: MOBANDEVER-560: Profile - Recently Played
	 *  NOTE: THIS FEATURE WILL BE REDESIGNED - DISABLING THIS TEST
	 */
	@Test(suiteName = "Profile", testName = "Profile - Recently Played", description = "Profile - Verify Recently Played Carousels", enabled = false, priority=1,groups = {
			"EVQAAND-182" })
	public void testverifyProfileRecentlyPlayed () {
		Common common = new Common(driver);
		Common.log("Validating Profile - Recently Played MOBANDEVER-560");
		try {
			Common.log("This test case will Fail - since Recently Played is no longer part of Profile page");
			Common.impicitWait(3);
			getPageFactory().getProfile().clickProfile();
			Common.impicitWait(3);
			getPageFactory().getProfile().verifyRecentlyPlayed();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}
	
	/*
	 * EVQAAND-331
	 * MOBANDEVER-987 Validating Alpha/Beta Requirement - Send Feedback floating
	 * button
	 */
	@Test(suiteName = "Profile", testName = "Send Feedback floating button", description = "Validating Alpha/Beta Requirement - Send Feedback floating button", enabled = true,priority=1, groups = {
			"EVQAAND-331" })
	public void verifySendFeedbackFloating() {
		Common common = new Common(driver);
		Common.log("Validating Alpha/Beta Requirement - Send Feedback floating button EVQAAND-331:MOBANDEVER-987");
		getPageFactory().getHome().homeCategories();
		getPageFactory().getEvehome().floatingButton();
		getPageFactory().getFavorites().clickFavoriteIcon();
		getPageFactory().getEvehome().floatingButton();
		getPageFactory().getProfile().clickProfile();
		getPageFactory().getEvehome().floatingButton();
		getPageFactory().getEvehome().clickOnFloatingButton();
	}
	
	/**
	 * TBD: To delete this test case as its duplicate
	 * Validating Profile - Sign Out
	 */
	@Test(suiteName = "Profile", testName = "Profile", description = "Profile - Sign Out", enabled = true, groups = {
			"MOBANDEVER-626(527)" })
	public void verifyProfileSignOut() {
		Common common = new Common(driver);
		Common.log("Validating Profile Sign out MOBANDEVER-626");
		try {
			getPageFactory().getProfile().profileSignOut();
			getPageFactory().getLogin().login();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

}
