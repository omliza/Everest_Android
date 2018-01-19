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

	/**
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

	/**
	 * Validating Profile - Sign Out
	 */
	@Test(suiteName = "Profile", testName = "Profile", description = "Profile - Sign Out", enabled = true, groups = {
			"MOBANDEVER-629(527)" })
	public void ProfileSignOutValidation() {
		Common common = new Common(driver);
		Common.log("Validating Profile Sign out MOBANDEVER-629");
		try {
			getPageFactory().getProfile().profileSignOut();
			getPageFactory().getLogin().login();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * Verify that Profile Screen is displayed with Profile tab highlighted on
	 * tapping the Profile
	 */
	@Test(suiteName = "Profile", testName = "Profile", description = "Verify that Profile Screen is displayed with Profile tab highlighted on tapping the Profile", enabled = true, groups = {
			"MOBANDEVER-681" })
	public void validateProfile() {
		Common common = new Common(driver);
		Common.log("Validating Profile Sign in MOBANDEVER-681");
		try {
			getPageFactory().getProfile().clickProfile();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * Validate whether the following texts are displayed in Profile Screen
	 */
	@Test(suiteName = "Profile", testName = "Profile", description = "Validate whether the following texts are displayed in Profile Screen", enabled = true, groups = {
			"MOBANDEVER-680" })
	public void verifyProfileMenuList() {
		Common common = new Common(driver);
		Common.log("Validating Profile contents MOBANDEVER-680");
		try {
			getPageFactory().getProfile().clickProfile();
			getPageFactory().getProfile().verifyProfileMenu();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * Validate the Recently Played, Saved for Later and Notifications in
	 * Profile
	 */
	@Test(suiteName = "Profile", testName = "Profile", description = "Validate the Recently Played, Saved for Later and Notifications in Profile ", enabled = true, groups = {
			"MOBANDEVER-682" })
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

	/**
	 * Validate whether About screen is displayed in Profile
	 */
	@Test(suiteName = "Profile", testName = "Profile", description = "Validate whether About screen is displayed in Profile", enabled = true, groups = {
			"MOBANDEVER-770" })
	public void validateAbout() {
		Common common = new Common(driver);
		Common.log("Verifying presence of about link on the Profile Menu MOBANDEVER-770");
		try {
			getPageFactory().getProfile().clickProfile();
			getPageFactory().getProfile().verifyAbout();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * Validate the Version and Build number displayed in About Screen
	 */
	@Test(suiteName = "Profile", testName = "Profile", description = "Validate the Version and Build number displayed in About Screen", enabled = true, groups = {
			"MOBANDEVER-773" })
	public void validateBuildVersion() {
		Common common = new Common(driver);
		Common.log("Verifying presence of Build Number and Version on the Profile Menu MOBANDEVER-773");
		try {
			getPageFactory().getProfile().clickProfile();
			getPageFactory().getProfile().verifyVersion();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * Validate whether Customer Agreement document is displayed
	 */
	@Test(suiteName = "Profile", testName = "Profile", description = "Validate whether Customer Agreement document is displayed", enabled = true, groups = {
			"MOBANDEVER-771" })
	public void validateCustomerAgreement() {
		Common common = new Common(driver);
		Common.log("Verifying Customet Agreemnet Page MOBANDEVER-771");
		try {
			getPageFactory().getProfile().clickProfile();
			getPageFactory().getProfile().verifyCustomerAgreement();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * Validate whether Privacy Policy document is displayed
	 */
	@Test(suiteName = "Profile", testName = "Profile", description = "Validate whether Privacy Policy document is displayed", enabled = true, groups = {
			"MOBANDEVER-772" })
	public void validatePrivacyPolicy() {
		Common common = new Common(driver);
		Common.log("Verifying Privacy Policy Page MOBANDEVER-772");
		try {
			getPageFactory().getProfile().clickProfile();
			getPageFactory().getProfile().verifyPrivacyPolicy();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * Validating Profile - Send Feedback
	 */
	@Test(suiteName = "Profile", testName = "Profile", description = "Profile - Send Feedback", enabled = true, groups = {
			"MOBANDEVER-564" })
	public void validateSendFeedback() {
		Common common = new Common(driver);
		Common.log("Validating Profile Send feedback MODANDEVER-564");
		try {
			getPageFactory().getProfile().clickProfile();
			common.scrollUntilTextExists("Help & Support");
			getPageFactory().getProfile().sendFeedback();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/**
	 * Validating Profile - Manage Account Settings
	 */
	@Test(suiteName = "Profile", testName = "Profile - Manage Account Settings", description = "Profile - Manage Account Settings", enabled = true, groups = {
			"MOBANDEVER-567" })
	public void validateProfileManageAccout() {
		Common common = new Common(driver);
		Common.log("Validating Profile - Manage Account Settings MOBANDEVER-567");
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

	@Test(suiteName = "Profile", testName = "Profile - Help and Support", description = "Profile - Help and Support", enabled = true, groups = {
			"MOBANDEVER-563" })
	public void validateProfileHelpSupport() {
		Common common = new Common(driver);
		Common.log("Validating Profile - Help and Support MOBANDEVER-563");
		try {
			Common.impicitWait(3);
			getPageFactory().getProfile().clickProfile();
			Common.impicitWait(3);
			common.scrollUntilTextExists("Sign Out");
			getPageFactory().getProfile().verifyHelpSupport();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

}
