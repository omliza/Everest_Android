package com.sxm.mobile.everest.pages;

//import static org.testng.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.apache.xml.utils.ThreadControllerWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;

import com.gargoylesoftware.htmlunit.javascript.host.media.rtc.webkitRTCPeerConnection;
import com.sun.jna.platform.win32.OaIdl.CURRENCY;
import com.sxm.framework.dto.handler.EnvirnomentHandler;
import com.sxm.framework.exception.AndriodException;
import com.sxm.framework.utility.PropertyElementReader;
import com.sxm.mobile.common.ReadAttributeValue;
import com.sxm.mobile.pages.Common;
import com.sxm.mobile.pages.ErrorMessageConstant;
import com.sxm.mobile.pages.ValidationUtil;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.ios.IOSKeyCode;

public class HowardStern {
	ValidationUtil validationUtil = new ValidationUtil();
	private final static Logger LOGGER = Logger.getLogger(HowardStern.class.getName());
	private AppiumDriver<WebElement> driver;
	private String platform = EnvirnomentHandler.getInstance().getEnvirnoment().getChannel();
	Boolean flag = false;
	int channelcount = 0;
	int showCount = 0;
	Common common = new Common(driver);

	public HowardStern(AppiumDriver driver) {
		super();
		this.driver = driver;

	}

	private String locale = EnvirnomentHandler.getInstance().getEnvirnoment().getLocale();
	private PropertyElementReader elementReader = PropertyElementReader.getInstance(locale);

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

	public void verifyHowardChannelLogo() throws AndriodException {
		Common common = new Common(driver);
		try {

			common.scrollUntilTextExists("Booey 100");
			Common.log("Select the 1st Show");
			validationUtil.validateWebElement("Show", driver).click();
			Common.impicitWait(2);
			Common.log("Select the 1st Episode");
			validationUtil.validateWebElement("Episode", driver).click();
			Common.impicitWait(4);
			Common.log("Clicked on selected Episode");
			Common.log("Navigated to Now Playing Screen");
			Common.impicitWait(4);
			nplLogo();
			Common.log("Minmized Now Playing Screen");
			validationUtil.validateWebElement("Everest.Minimizebutton", driver).click();
		} catch (Exception ex) {
			Common.log("Exception occured: " + ex.getMessage());
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), ex.getMessage());
		}
	}

	/**
	 * @throws AndriodException
	 * Validating Show logo, On demand text
	 */
	public void verifyHowardVideosShowLogo() throws AndriodException {
		Common common = new Common(driver);
		try {
			Common.impicitWait(4);
			nplLogo();
			Common.log("Minmized Now Playing Screen");
			WebElement ondemandtext = validationUtil.validateWebElement("NPL.ChNumber", driver);
			if (ondemandtext.getText().equalsIgnoreCase("On Demand")) {
				Common.log("On Demand text is displayed on NPL");
			} else {
				throw new AndriodException(this.getClass().getSimpleName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(), "On Demand text is not displayed on NPL");
			}
			validationUtil.validateWebElement("Everest.Minimizebutton", driver).click();
		} catch (Exception ex) {
			Common.log("Exception occured: " + ex.getMessage());
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), ex.getMessage());
		}
	}
	
	
	/**
	 * Playing Howard Video channel 
	 */
	public void playVideoEpisode(){
		Common common = new Common(driver);
		try {
			common.scrollUntilTextExists("Howard Videos");
			common.scrollToDown(1);
			Common.log("Select the 1st Video");
			validationUtil.validateWebElement("Show", driver).click();
			Common.impicitWait(2);
			Common.log("Select the 1st Video episode");
			validationUtil.validateWebElement("Episode", driver).click();
			Common.impicitWait(4);
			Common.log("Clicked on selected Episode");
			Common.impicitWait(3);
			validationUtil.validateDisplayWebElement("Everest.Minimizebutton", driver, ErrorMessageConstant.MinimizeBtn_NotDisplayed);
			Common.log("Navigated to Now Playing Screen");
		} catch (Exception e) {
			// TODO: handle exception
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}
	
	/**
	 * Verifying npl show Logo
	 */
	public void nplLogo() {
		Common common = new Common(driver);
		try {
			ValidationUtil validationUtil = new ValidationUtil();
			validationUtil.validateDisplayWebElement("NPL.Logo", driver, "Logo is  not present on the NPL screen");
			Common.log("Logo is present on the NPL screen");
		} catch (Exception e) {
			Common.log("Show Logo is not present in the screen");
			throw new AndriodException(e.getMessage());
		}
	}

	public void verifyHowardChannelNUmber() throws AndriodException {
		Common common = new Common(driver);
		try {
			common.scrollUntilTextExists("Booey 100");
			Common.log("Select the 1st Show");
			validationUtil.validateWebElement("Show", driver).click();
			Common.impicitWait(3);
			Common.log("Select the 1st Episode");
			validationUtil.validateWebElement("Episode", driver).click();
			Common.impicitWait(3);
			Common.log("Clicked the 1st Episode");
			Common.log("Navigated to Now Playing Screen");
			Common.impicitWait(4);
			nplChnumber();
			validationUtil.validateWebElement("Everest.Minimizebutton", driver).click();
		} catch (Exception ex) {
			Common.log("Exception observed: " + ex.getMessage());
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), ex.getMessage());
		}
	}

	public void howardStren() {
		Common common = new Common(driver);
		try {
			LOGGER.info("Validating Howard Stern Page Category.");
			Common.impicitWait(3);

			List<WebElement> webElement = driver
					.findElements(By.xpath(elementReader.getPropertyElement("Howard.CaroselCount")));
			int caroselsize = webElement.size();
			Common.log("Carosel present:" + caroselsize);
			List<WebElement> Noofchannel = driver
					.findElements(By.xpath(elementReader.getPropertyElement("Howard.Count")));
			int channelcount = Noofchannel.size();
			Common.log(" Howard episode Count:" + channelcount);
			Common.impicitWait(3);
			validationUtil.validateWebElement("Howard.Episode1", driver).click();
			Common.impicitWait(2);
			validationUtil.validateDisplayWebElement("Howard.EpisodeIcon", driver,
					"howardstern episode icon is not present");
			// Common.log(driver.findElement(By.xpath(elementReader.getPropertyElement("Howard.EpisodeIcon"))).getText());
			Common.log("Show icon is present for episode");
			Common.impicitWait(3);
			validationUtil.validateWebElement("Everest.NavigateBack", driver).click();
			Common.log("Navigate Back");
			Common.impicitWait(3);
			validationUtil.validateWebElement("Howard.Episode2", driver).click();
			Common.impicitWait(2);
			validationUtil.validateDisplayWebElement("Howard.EpisodeIcon", driver,
					"howardstern episode icon is not present");
			// Common.log(driver.findElement(By.xpath(elementReader.getPropertyElement("Howard.EpisodeIcon"))).getText());
			Common.log("Show icon is present for episode");
			Common.impicitWait(3);
			validationUtil.validateWebElement("Howard.EpisodeClick", driver).click();
			Common.impicitWait(3);
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	public void howardCarousel() {
		Common common = new Common(driver);
		try {
			Common.log("Landed on Howard Items menu page");
			Assert.assertTrue(howardcarousel().isDisplayed());
			Common.log("Howard Carosel item is present.");
			howardcarousel().click();
			Common.impicitWait(3);// *[@resource-id='com.sirius.android.everest.us.dev:id/howard_main_scroll_view']/group[1]/group[1]/view[1]/group[2]/group[1]
			Assert.assertTrue(minimizebutton().isDisplayed());
			Common.log("Navigate to Now playing screen");
			minimizebutton().click();
			Common.impicitWait(3);
			// driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.HowardCarousel2"))).click();
			driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.HowardCarousel2"))).click();
			Common.impicitWait(2);
			Assert.assertTrue(minimizebutton().isDisplayed());
			Common.log("Navigate to Now playing screen");
			Common.log("Selecting the Channel Carousel ,navigates to NPL screen.");
		} catch (Exception e) {
			// TODO: handle exception
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	public void clickminimize() {
		minimizebutton().click();
	}

	public WebElement howardcarousel() {
		return validationUtil.validateWebElement("Howard.Caurosel", driver);
	}

	public WebElement minimizebutton() {
		return validationUtil.validateWebElement("Everest.Minimizebutton", driver);
	}

	public WebElement howardBreadcrumb() {
		return validationUtil.validateWebElement("Everest.Breadcrumb", driver);
	}

	public WebElement onDemandtext() {
		return validationUtil.validateWebElement("Everest.NPOnDemand", driver);
	}

	public WebElement howoardepiicon() {
		return validationUtil.validateWebElement("Howard.EpisodeIcon", driver);
	}

	public WebElement back() {
		return validationUtil.validateWebElement("Everest.NavigateBack", driver);
	}

	public void showName() {
		Common common = new Common(driver);
		try {
			validationUtil.validateDisplayWebElement("NPL.ShowName1", driver, "Showname is not present in the screen");
			Common.log("Show name is present on the NPL screen");
		} catch (Exception e) {
			Common.log("Showname is not present in the screen");
		}
	}

	public void nowPlayingArt() {
		Common common = new Common(driver);
		try {
			driver.findElement(By.xpath("//*[@resource-id='com.sirius.android.everest.us.dev:id/now_playing_art']"))
					.isDisplayed();
			Common.log("Now Playing Art is present on the NPL screen");
			// driver.findElement(By.xpath("//*[@resource-id='com.sirius.android.everest.us.dev:id/now_playing_art']")).isDisplayed();
		} catch (Exception e) {
			Common.log("Now Playing Art is not present in the screen");
			// Assert.fail();

		}
	}

	public void nplChnumber() {
		Common common = new Common(driver);
		try {
			// driver.findElement(By.xpath(elementReader.getPropertyElement("NPL.ChNumber"))).isDisplayed();
			ValidationUtil validationUtil = new ValidationUtil();
			validationUtil.validateDisplayWebElement("NPL.ChNumber", driver,
					"Channel number is not present in the screen");
			Common.log("Channel number is present on the NPL screen");
		} catch (Exception e) {
			Common.log("Channel number is not present in the screen");
		}
	}

	public void clickOnHowardStern() throws AndriodException {
		Common common = new Common(driver);
		try {
			// common.scrollUntilTextExists("Howard Stern");
			Common.impicitWait(4);
			WebElement cathoward = validationUtil.validateWebElement("Home.Howard", driver);
			cathoward.click();
			Common.log("Clicked on Howard Stern");
			Common.impicitWait(2);
			/*
			 * WebElement subcathoward =
			 * validationUtil.validateWebElement("Home.SubCatHoward", driver);
			 * subcathoward.click(); Common.log(
			 * "Clicked on sub category Howard stern");
			 */
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					" Category Howard stern is not present in the screen");
		}
	}

	public void clickHoward() {
		Common common = new Common(driver);
		try {
			Common.impicitWait(4);
			validationUtil.validateDisplayWebElement("Home.Howard", driver,
					ErrorMessageConstant.Howardstern_NotDisplayed);
			Common.log("Clicked on Howard Stern");
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					" Category Howard stern is not present in the screen");
		}
	}

	public void verifyUnrestrictedShownamePDT() throws AndriodException {
		Common common = new Common(driver);
		try {
			common.scrollUntilTextExists("Booey 100");
			Common.log("Select the 1st Show");
			validationUtil.validateWebElement("Show", driver).click();
			Common.impicitWait(2);
			Common.log("Select the 1st Episode");
			validationUtil.validateWebElement("Episode", driver).click();
			Common.impicitWait(2);
			Common.log("Navigated to Now Playing Screen");
			Common.impicitWait(4);
		} catch (Exception ex) {
			Common.log("Exception occured: " + ex.getMessage());
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), ex.getMessage());
		}

	}

	public void verifyUnrestrictedOnDemandShownamePDT() throws AndriodException {
		Common common = new Common(driver);
		try {
			common.scrollUntilTextExists("Booey 100");
			Common.log("Select the 1st Show");
			validationUtil.validateWebElement("Show", driver).click();
			Common.impicitWait(2);
			Common.log("Select the 1st Episode");
			validationUtil.validateWebElement("Episode", driver).click();
			Common.impicitWait(2);
			Common.log("Navigated to Now Playing Screen");
			Common.impicitWait(4);
		} catch (Exception ex) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), ex.getMessage());
		}
	}

	public void verifyUnrestrictedShowArt() throws AndriodException {
		Common common = new Common(driver);
		try {
			Common.impicitWait(4);
			common.scrollUntilTextExists("Booey 100");
			Common.impicitWait(2);
			Common.log("Select the 1st Show");
			validationUtil.validateWebElement("Show", driver).click();
			Common.impicitWait(2);
			Common.log("Select the 1st Episode");
			validationUtil.validateWebElement("Episode", driver).click();
			Common.impicitWait(2);
			Common.log("Navigated to Now Playing Screen");
			Common.impicitWait(4);
		} catch (Exception ex) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), ex.getMessage());
		}

	}

	public void verifyUnrestrictedProgressbar() throws AndriodException {
		Common common = new Common(driver);
		try {
			common.scrollUntilTextExists("Booey 100");
			Common.log("Select the 1st Show");
			validationUtil.validateWebElement("Show", driver).click();
			Common.impicitWait(2);
			Common.log("Select the 1st Episode");
			validationUtil.validateWebElement("Episode", driver).click();
			Common.impicitWait(2);
			Common.log("Navigated to Now Playing Screen");
			Common.impicitWait(4);
			validationUtil.validateDisplayWebElement("NPL.StartTime", driver, "Start time is not displayed");
			validationUtil.validateDisplayWebElement("NPL.EndTime", driver, "End time is not displayed");
			String starttime = validationUtil.validateWebElement("NPL.StartTime", driver).getText();
			Common.log("Start time" + starttime);
			String endtime = validationUtil.validateWebElement("NPL.EndTime", driver).getText();
			Common.log("End Time=" + endtime);
			Common.log("Time is displayed in the format X:XX am/pm");
		} catch (Exception ex) {

			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), ex.getMessage());
		}

	}

	public void unrestrictedShowlogoonDemand() throws AndriodException {
		Common common = new Common(driver);
		try {
			common.scrollUntilTextExists("Booey 100");
			Common.log("Select the 1st Show");
			validationUtil.validateWebElement("Show", driver).click();
			Common.impicitWait(4);
			Common.log("Select the 1st Episode");
			validationUtil.validateWebElement("Episode", driver).click();
			Common.impicitWait(2);
			Common.log("Navigated to Now Playing Screen");
			Common.impicitWait(5);
			Common.log("Verifying Album Art");
			nowPlayingArt();
		} catch (Exception e) {
			Common.log("Exception occured: " + e.getMessage());
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void validateNPLScreen() throws AndriodException {
		Common common = new Common(driver);
		try {
			ValidationUtil validationUtil = new ValidationUtil();
			validationUtil.validateDisplayWebElement("NPL.StartTime", driver, "Start time is not displayed");
			validationUtil.validateDisplayWebElement("NPL.EndTime", driver, "End Time is not displayed");
			String starttime = validationUtil.validateWebElement("NPL.StartTime", driver).getText();
			Common.log("Start time" + starttime);
			String endtime = validationUtil.validateWebElement("NPL.EndTime", driver).getText();
			Common.log("End Time=" + endtime);
			Common.log("Time is displayed in the format X:XX am/pm");
			try {
				if (!validationUtil.validateWebElement("Everest.NPOnDemand", driver).isDisplayed()) {
					Common.log("It is not an On Demand Show");
				}
			} catch (AndriodException en) {
				Common.log("It is not an On Demand Show");
			}

			nplLogo();
			minimizebutton().click();
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void howardCarousel1() throws AndriodException {
		try {
			Common common = new Common(driver);
			ValidationUtil validationUtil = new ValidationUtil();
			validationUtil.validateDisplayWebElement("Howard.Carousels", driver, "Howard Caurosel is not displayed");
			Common.log("Howard Caurosel are avavilable.");
		} catch (Exception e) {
			// TODO: handle exception
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void verifyUnrestrictedShowListing() {
		Common common = new Common(driver);
		try {
			common.scrollToDown(2);
			howardshowCount();
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	public void howardshowCount() {
		Common common = new Common(driver);
		try {
			List<WebElement> Noofshows = driver
					.findElements(By.xpath(elementReader.getPropertyElement("Howard.Showlist")));
			int showCount = Noofshows.size();
			Common.log("Show List Count:" + showCount);

		} catch (Exception e) {
			throw new AndriodException(e.getMessage());
		}
	}

	public void howardShowListingItem() {
		Common common = new Common(driver);
		try {
			common.scrollUntilTextExists("Booey 100");
			validationUtil.validateWebElement("Howard.Show1", driver).click();
			Common.impicitWait(2);
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	public WebElement saveForlater() {
		Common common = new Common(driver);
		return driver.findElement(By.xpath(elementReader.getPropertyElement("NowPlaying.SaveLater")));
	}

	public void ValidateSaveForLater() {
		Common common = new Common(driver);
		try {
			LOGGER.info("Validating Unrestricted On Demand Save For Later MOBANDEVER-337");
			clickOnHowardStern();
			Common.impicitWait(3);
			common.scrollToDown(2);
			validationUtil.validateWebElement("Howard.Episode1", driver).click();
			Common.impicitWait(2);
			WebElement item2 = validationUtil.validateWebElement("Howard.FirstEpisode", driver);
			item2.click();
			Common.log("Navigated to Now Playing Screen");
			common.scrollToDown(1);
			Assert.assertTrue(saveForlater().isDisplayed());
			Common.log("Save For Later is present for the  On Demand Episode");
			common.scrollToDown(1);
			validationUtil.validateWebElement("Everest.Minimizebutton", driver).click();
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	public void howardSaveLater() {
		Common common = new Common(driver);
		try {
			LOGGER.info("Validating Unrestricted Save For Later MOBANDEVER-330");
			clickOnHowardStern();
			Common.impicitWait(3);
			validationUtil.validateWebElement("Howard.Caurosel", driver).click();
			Common.log("Navigated to Now Playing Screen");
			common.scrollToDown(1);
			// Assert.assertTrue(saveForlater().isDisplayed());
			validationUtil.validateDisplayWebElement("NowPlaying.SaveLater", driver, "Save later is not displaying ");
			Common.log("Save For Later is present for the  unrestricted  Episode");
			common.scrollToDown(1);
			validationUtil.validateWebElement("Everest.Minimizebutton", driver).click();
		} catch (Exception e) {
			// TODO: handle exception
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void closelaunchapp() {
		Common common = new Common(driver);
		driver.closeApp();
		Common.impicitWait(2);
		driver.launchApp();
		Common.impicitWait(3);
	}

	public void avashowcnt() {
		Common common = new Common(driver);
		try {
			List<WebElement> Noofshows = driver
					.findElements(By.xpath(elementReader.getPropertyElement("NPL.AvalableShows")));
			int showCount = Noofshows.size();
			Common.log(" Avaialble Show Count:" + showCount);

		} catch (Exception e) {
			throw new AndriodException("NO shows are present");
		}
	}

	public void unrestrictedAvaialbleShows() throws AndriodException {
		Common common = new Common(driver);
		try {
			Common.log("Verifying available shows for Unrestricted category MOBANDEVER 637");
			validationUtil.validateWebElement("Everest.Howardcarousel1", driver).click();
			Common.impicitWait(3);
			Common.log("Navigated to NPL screen");
			// WebElement ele=driver.findElement(By.xpath("//*[@text='Available
			// shows on Howard 100']"));
			// common.scrollTillElementFound(By.xpath(elementReader.getPropertyElement("NPL.HowardAvalaibleShows")));
			common.scrollToDown(2);
			Common.impicitWait(3);
			avashowcnt();
			common.scrollChannelsdown(3);
			validationUtil.validateWebElement("Everest.Minimizebutton", driver).click();
			/*
			 * Common.swipeRightToLeft(); Common.impicitWait(3); avashowcnt();
			 */
		} catch (Exception e) {
			// TODO: handle exception
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void validateRelatedcontents() throws InterruptedException {
		Common common = new Common(driver);
		try {
			common.scrollToDown(1);
			validationUtil.validateWebElement("Howard.Show1", driver).click();
			Common.impicitWait(3);
			validationUtil.validateWebElement("Howard.Episode", driver).click();
			Common.log("navigated to NPL screen");
			common.scrollToDown(1);
			WebElement relatedItem = validationUtil.validateDisplayWebElement("Howard.RelatedContent", driver,
					"Related content Header is not present on the screen NPL");
			// Assert.assertTrue(relatedItem.isDisplayed());
			Common.log("Related Content Header is present in the NPL screen");
			common.swipeRightToLeft();
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void validatedisallowedRelatedcontents() throws InterruptedException {
		Common common = new Common(driver);
		try {
			validationUtil.validateWebElement("Everest.Showcount", driver).click();
			Common.impicitWait(3);
			validationUtil.validateWebElement("Howard.FirstEpisode", driver).click();
			Common.log("navigated to NPL screen");
			common.scrollToDown(1);
			validationUtil.validateDisplayWebElement("Howard.RelatedContent", driver,
					"Related Content Header is not present in the NPL screen");
			Common.log("Related Content Header is present in the NPL screen");
			common.swipeRightToLeft();
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void validateDmcaRelatedcontent() throws InterruptedException {
		Common common = new Common(driver);
		try {
			validationUtil.validateWebElement("Pop.Channel1", driver).click();
			validationUtil.validateWebElement("Howard.FirstEpisode", driver).click();
			Common.log("navigated to NPL screen");
			common.scrollToDown(1);
			validationUtil.validateDisplayWebElement("Howard.RelatedContent", driver,
					"Related Content Header is not present in the NPL screen");
			Common.log("Related Content Header is present in the NPL screen");
			common.swipeRightToLeft();
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void howardHeader() throws AndriodException {
		Common common = new Common(driver);
		try {
			common.scrollUntilTextExists("Booey 100");
			validationUtil.validateWebElement("Show", driver).click();
			Common.impicitWait(2);
			common.waitforElment("Howard.Episode");
			validationUtil.validateDisplayWebElement("Howard.Episode", driver,
					ErrorMessageConstant.Episodes_NotDisplayed);
			validationUtil.validateDisplayWebElement("Howard.HeaderIcon", driver,
					ErrorMessageConstant.HowardSternHeader_NotDisplayed);
			Common.log("Howad Stern Header is displayed");
			// WebElement element =
			// driver.findElement(By.xpath(elementReader.getPropertyElement("Howard.Episode1")));
			// common.scrollTillElementFound(element);
			// Assert.assertTrue(element.isDisplayed());
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}
}
