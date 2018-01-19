package com.sxm.mobile.tests;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
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
public class HowardSternCategoryTest extends RefactorMobileTestCase {
	private final static Logger LOGGER = Logger.getLogger(NowPlayingUnrestrictedTest.class.getName());
	private Map<String, UserObj> users = UserHandler.getInstance(locale, env).getUserMap();
	private String referenceurl;
	public UserObj usr = null;
	Common common = new Common(driver);

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

	/*
	 * EVQAAND-276: MOBANDEVER-344:Howard Stern Category - Back Button
	 */
	@Test(suiteName = "HowardSternCategory", testName = "Howard Stern Category - Back Button", description="Howard Stern Category - Back Button",enabled = true, groups = {
			"EVQAAND-276: MOBANDEVER-344" })
	public void howardSternBackButton() {
		Common common = new Common(driver);
		try {
			getPageFactory().getEvehome().clickOnTalk();
			//common.scrollUntilTextExists("Talk Categories");
			getPageFactory().getHoward().clickOnHowardStern();
			getPageFactory().getEvehome().clickBackButton();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/*
	 * EVQAAND-162:MOBANDEVER-357:Howard Stern Category - Show Listing
	 */
	@Test(suiteName = "HowardSternCategory", testName = "Howard Stern Category - Show Listing",description="Unrestricted-Howard Stern Category - Show Listing", enabled = true, groups = {
			"EVQAAND-162:MOBANDEVER-357" })
	public void validateHowardShowListing() {
		Common common = new Common(driver);
		try {
			getPageFactory().getEvehome().clickOnTalk();
			//common.scrollUntilTextExists("Entertainment");
			getPageFactory().getHoward().clickOnHowardStern();
			getPageFactory().getHoward().verifyUnrestrictedShowListing();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	/*
	 * EVQAAND-173: Howard Stern Category - Channel Header
	 */
	@Test(suiteName = "HowardSternCategory", testName = "Unrestricted-Howard Stern category", description="Howard Stern Category - Channel Header",enabled = true, groups = {
			"EVQAAND-173:MOBANDEVER-356" })
	public void ValidateHowardHeader() {
		Common common = new Common(driver);
		Common.log("Validating howard header MOBANDEVER-356");
		try {
			getPageFactory().getEvehome().clickOnTalk();
			//common.scrollUntilTextExists("Entertainment");
			getPageFactory().getHoward().clickOnHowardStern();
			getPageFactory().getHoward().howardHeader();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	@Test(suiteName = "HowardSternCategory", testName = "Unrestricted-Howard Stern category", enabled = true, groups = {
			"MOBANDEVER-345" })
	public void ValidateHowardBreadcrumb() {
		Common common = new Common(driver);
		try {
			getPageFactory().getEvehome().clickOnTalk();
			//common.scrollUntilTextExists("Entertainment");
			getPageFactory().getHoward().clickOnHowardStern();
			getPageFactory().getEvehome().howardbreadcrumb();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	@Test(suiteName = "HowardSternCategory", testName = "Unrestricted-Howard Stern category", enabled = true, groups = {
			"MOBANDEVER-358" })
	public void validateHowardShowListingItem() {
		Common common = new Common(driver);
		Common.log("Validating Howard Stern Show listing Item MOBANDEVER-358");
		try {
			getPageFactory().getEvehome().clickOnTalk();
			//common.scrollUntilTextExists("Entertainment");
			getPageFactory().getHoward().clickOnHowardStern();
			getPageFactory().getHoward().howardShowListingItem();
			getPageFactory().getEvehome().episodecnt();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	@Test(suiteName = "HowardSternCategory", testName = "Unrestricted-Howard Stern category", enabled = true, groups = {
			"MOBANDEVER-348" })
	public void verifyHowardChannelcarousel() {
		Common common = new Common(driver);
		try {
			getPageFactory().getEvehome().clickOnTalk();
			//common.scrollUntilTextExists("Entertainment");
			getPageFactory().getHoward().clickOnHowardStern();
			getPageFactory().getEvehome().howardCarousel();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	@Test(suiteName = "HowardSternCategory", testName = "Unrestricted-Howard Stern category", enabled = true, groups = {
			"MOBANDEVER-347" })
	public void verifyHowardChannelcarouselHeader() {
		Common common = new Common(driver);
		try {
			getPageFactory().getEvehome().clickOnTalk();
			//common.scrollUntilTextExists("Entertainment");
			getPageFactory().getHoward().clickOnHowardStern();
			getPageFactory().getHoward().howardCarousel1();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	@Test(suiteName = "HowardSternCategory", testName = "Unrestricted-Howard Stern category", enabled = true, groups = {
			"MOBANDEVER-351" })
	public void verifyHowardLatestEpisodescarouse1() {
		Common common = new Common(driver);
		try {
			getPageFactory().getEvehome().clickOnTalk();
			//common.scrollUntilTextExists("Entertainment");
			getPageFactory().getHoward().clickOnHowardStern();
			getPageFactory().getHoward().howardCarousel1();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	@Test(suiteName = "HowardSternCategory", testName = "Unrestricted-Howard Stern category", enabled = true, groups = {
			"MOBANDEVER-349" })
	public void Howardcarousel1() {
		Common common = new Common(driver);
		try {
			getPageFactory().getEvehome().clickOnTalk();
			//common.scrollUntilTextExists("Entertainment");
			getPageFactory().getHoward().clickOnHowardStern();
			getPageFactory().getEvehome().verifyhowardCarousel();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	@Test(suiteName = "HowardSternCategory", testName = "Unrestricted-Howard Stern category", enabled = true, groups = {
			"MOBANDEVER-350" })
	public void Howardcarousel2() {
		Common common = new Common(driver);
		try {
			getPageFactory().getEvehome().clickOnTalk();
			//common.scrollUntilTextExists("Entertainment");
			getPageFactory().getHoward().clickOnHowardStern();
			getPageFactory().getEvehome().verifyhowardCarousel();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	@Test(suiteName = "HowardSternCategory", testName = "Howard as a Super Category", description = "Howard as a Super Category", enabled = true, groups = {
			"MOBANDEVER-682" })
	public void verifyHowardSuperCategory() {
		Common common = new Common(driver);
		Common.log("Validating Howard as a Super Category MOBANDEVER-682 ");
		try {
			getPageFactory().getEvehome().clickOnTalk();
			//common.swipeToLeft(By.xpath(elementReader.getPropertyElement("Home.Talk")));
			getPageFactory().getHoward().clickOnHowardStern();
			Common.log("Howard  is present as a Super Category");
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}
}
