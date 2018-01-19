package com.sxm.mobile.aod.tests;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.junit.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.selenium.mobile.base.RefactorMobileTestCase;
import com.sxm.framework.common.Retry;
import com.sxm.framework.common.Screenshot;
import com.sxm.framework.dto.UserObj;
import com.sxm.framework.dto.handler.UserHandler;
import com.sxm.framework.exception.AndriodException;
import com.sxm.framework.exception.InValidOSException;
import com.sxm.framework.exception.InValidToolException;
import com.sxm.mobile.pages.Common;

@SuppressWarnings({ "unused", "static-access" })
@Listeners({ Screenshot.class })
public class NowPlayingApronTest extends RefactorMobileTestCase {
	private final static Logger LOGGER = Logger.getLogger(NowPlayingTest.class.getName());
	private Map<String, UserObj> users = UserHandler.getInstance(locale, env).getUserMap();
	private String referenceurl;
	public UserObj usr = null;

	/*@BeforeClass(alwaysRun = true)
	public void login() throws InValidOSException, InValidToolException {
		LOGGER.info(users.get("prod1").getUserName());
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		getPageFactory().getLogin().login();
		Common.impicitWait(5);
	}*/
	
	@Parameters({"platform", "login_credential"})
	@org.testng.annotations.BeforeClass(alwaysRun = true)
	public void initializeDrivers(String plt, @Optional String loginCredential, ITestContext context) 
			throws InValidOSException, InValidToolException {
		Common common = new Common(driver);
		try{
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			getPageFactory().getLogin().loginBeforeStart(loginCredential);
			Common.impicitWait(10);
		}catch (AndriodException ex) {		
			Common.errorlog("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());			
			Assert.fail("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());	
		}
	}
	

	@Test(suiteName = "NowPlaying", testName = "Now Playing Apron-DMCA Restricted", enabled = true, groups = {
			"MOBANDEVER-325", })
	public void verifyBuySong() {Common common = new Common(driver);
		Common.log("Verifying Now Playing Apron-DMCA Restricted MOBANDEVER-325");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Music Categories");
			getPageFactory().getEvehome().clickMucisSubPop();
			getPageFactory().getEvehome().dmcaBuySongButton();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	@Test(suiteName = "NowPlaying", testName = "Now Playing Apron-Unrestricted- On Demand-Save for Later", enabled = true, groups = {
			"MOBANDEVER-337" })
	public void verifyUnrestrictedonDemandSaveForLater() {Common common = new Common(driver);
		Common.log("VerifyingNow Playing Apron-Unrestricted- On Demand-Save for Later MOBANDEVER-337 ");
		try {
			getPageFactory().getEvehome().clickOnTalk();
			common.scrollUntilTextExists("Entertainment");
			getPageFactory().getHoward().clickOnHowardStern();
			getPageFactory().getEvehome().ValidateSaveForLater();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	@Test(suiteName = "NowPlaying", testName = "Now Playing Apron - Unrestricted - Live - Save for Later", enabled = true, groups = {
			"MOBANDEVER-330" })
	public void verifyUnrestrictedLiveSaveLater() {Common common = new Common(driver);
		Common.log("Verifying Now Playing Apron - Unrestricted - Live - Save for Later MOBANDEVER-330");
		try {
			getPageFactory().getEvehome().clickOnTalk();
			common.scrollUntilTextExists("Entertainment");
			getPageFactory().getEvehome().clickOnHowardStern();
			getPageFactory().getEvehome().validateLiveSaveForLater();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	@Test(suiteName = "NowPlaying", testName = "Now Playing Apron-Live-Related Module-Available Shows on ", enabled = true, groups = {
			"MOBANDEVER-639" })
	public void validateDisallowedShowsAvaialble() throws InterruptedException {
		Common common = new Common(driver);
		Common.log("Verifying available shows for Unrestricted category MOBANDEVER 639");
		try {
			Common.impicitWait(4);
			getPageFactory().getEvehome().clickNews();
			common.scrollUntilTextExists("News/Public Radio");
			getPageFactory().getEvehome().clickSubCatNews();
			getPageFactory().getEvehome().disallowedAvaialbleShows();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	@Test(suiteName = "NowPlaying", testName = "Now Playing Apron-Live-Related Module-Available Shows on ", enabled = true, groups = {
			"MOBANDEVER-638" })
	public void validateRestrictedShowsAvaialble() throws InterruptedException {
		Common common = new Common(driver);
		Common.log("Verifying available shows for Unrestricted category MOBANDEVER 638");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Music Categories");
			getPageFactory().getEvehome().clickMucisSubPop();
			getPageFactory().getEvehome().restrictedAvaialbleShows();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	@Test(suiteName = "NowPlaying", testName = "Now Playing Apron-Live-Related Module-Available Shows on ", enabled = true, groups = {
			"MOBANDEVER-637" })
	public void validateUnrestrictedShowsAvaialble() throws InterruptedException {
		Common common = new Common(driver);
		try {
			getPageFactory().getEvehome().clickOnTalk();
			common.scrollUntilTextExists("Entertainment");
			getPageFactory().getEvehome().clickOnHowardStern();
			getPageFactory().getHoward().unrestrictedAvaialbleShows();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

	@Test(suiteName = "NowPlaying", testName = "Now Playing Apron-On Demand-Related Module-Additional Episodes", enabled = true, groups = {
			"MOBANDEVER-792" })
	public void validateAdditionaEpisodes() throws AndriodException, InterruptedException {
		Common common = new Common(driver);
		Common.log("Verifying OnDemand Additional Episodes MOBANDEVER-792");
		try {
			getPageFactory().getEvehome().clickonMusic();
			common.scrollUntilTextExists("Music Categories");
			getPageFactory().getEvehome().clickMucisSubRock();
			getPageFactory().getEvehome().clickOnDemand();
			getPageFactory().getEvehome().onDemandAdditionalEpisodes();
		} catch (AndriodException ex) {
			Common.errorlog("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
			Assert.fail("Exception occured in : " + this.getClass().getSimpleName() + " : " + ex.getMessage());
		}
	}

}
