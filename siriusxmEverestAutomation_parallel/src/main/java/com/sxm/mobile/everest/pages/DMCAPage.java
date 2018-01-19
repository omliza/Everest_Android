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

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.host.media.rtc.webkitRTCPeerConnection;
import com.sxm.framework.common.Retry;
import com.sxm.framework.dto.handler.EnvirnomentHandler;
import com.sxm.framework.exception.AndriodException;
import com.sxm.framework.utility.PropertyElementReader;
import com.sxm.mobile.common.ReadAttributeValue;
import com.sxm.mobile.pages.Common;
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

public class DMCAPage {
	private final static Logger LOGGER = Logger.getLogger(DMCAPage.class.getName());
	private AppiumDriver<WebElement> driver;
	private String platform = EnvirnomentHandler.getInstance().getEnvirnoment().getChannel();
	Boolean flag = false;
	int channelcount = 0;
	int showCount = 0;
	ValidationUtil validationUtil = new ValidationUtil();
	Common common = new Common(driver);

	public DMCAPage(AppiumDriver driver) {
		super();
		this.driver = driver;

	}

	private String locale = EnvirnomentHandler.getInstance().getEnvirnoment().getLocale();
	private PropertyElementReader elementReader = PropertyElementReader.getInstance(locale);

	public void clickOnDemand() {
		Common common = new Common(driver);
		try {
			Common.impicitWait(2);
			common.waitforElment("Everest.OnDemand");
			validationUtil.validateWebElement("Everest.OnDemand", driver).click();
			Common.log("OnDemand  tab is clicked");
			common.waitforElment("Everest.OnDemandHeader");
			validationUtil.validateDisplayWebElement("Everest.OnDemandHeader", driver,
					"Ondemand Header is not present");
			Common.log("On Demand Header is present");
		} catch (Exception e) {
			Common.log(e.getMessage());
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void favlist() {
		Common common = new Common(driver);
		try {
			List<WebElement> shows = driver
					.findElements(By.xpath(elementReader.getPropertyElement("Favorites.ListCount")));
			int channelcount = shows.size();
			Common.log("No. of channels present:" + channelcount);
			if (channelcount > 0) {
				Common.log("Favorite items are present.");
				Common.log("Favorite items count:" + channelcount);
			} else {
				Common.log("Favorite items are  not present");
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			driver.findElement(By.xpath(elementReader.getPropertyElement("Favorites.NoFavorites"))).isDisplayed();
			System.out.println(
					driver.findElement(By.xpath(elementReader.getPropertyElement("Favorites.NoFavorites"))).getText());
		}
	}

	public void episodeClick() {
		Common common = new Common(driver);
		try {
			try {
				validationUtil.validateWebElement("Episoede.SecondEpisode", driver).click();
				Common.log("Clicked on episode");
			} catch (Exception e) {
				validationUtil.validateWebElement("Episoede.FirstEpisode", driver).click();
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	public void favoriteShow() {
		Common common = new Common(driver);
		try {
			validationUtil.validateWebElement("Show", driver).click();// clicked
																		// on
																		// 1st
																		// show
																		// of
																		// rock
			Common.impicitWait(2);
			validationUtil.validateWebElement("Episode", driver).click();
			Common.impicitWait(3);
			Common.log("Navigated to NPL screen");
			WebElement favicon = validationUtil.validateWebElement("NPL.FavoriteIcon", driver);
			validationUtil.validateDisplayWebElement("NPL.FavoriteIcon", driver, "Favorite Icon is not displayed");
			String flag = favicon.getAttribute("checked");
			if (flag.equalsIgnoreCase("false")) {
				favicon.click();
			} else {
			Common.log("Show is already markes as Favorite");
			}
			Common.impicitWait(4);
			String name = validationUtil.validateWebElement("NPL.PDT", driver).getText();
			Common.log("Show name:" + name);
			validationUtil.validateWebElement("Everest.Minimizebutton", driver).click();
			Common.impicitWait(4);
			validationUtil.validateWebElement("Home.FavoriteIcon", driver).click();
			Common.impicitWait(5);
			validationUtil.validateWebElement("Favorites.Edit", driver).click();
			/*
			 * List<WebElement> shows = driver.findElements(By.xpath(
			 * elementReader.getPropertyElement("Favorites.ListCount")));
			 */
			if (driver.getPageSource().contains(name)) {
				common.scrollUntilTextExists(name);
				Assert.assertTrue(true, "Show present in Favorite list");
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	public void unfavoriteShow() {
		Common common = new Common(driver);
		try {

			validationUtil.validateWebElement("Everest.Home", driver).click();
			Common.impicitWait(3);
			validationUtil.validateWebElement("Music.Rockchannel", driver).click();
			Common.impicitWait(3);
			clickOnDemand();
			validationUtil.validateWebElement("Show", driver).click();// clicked
																			// on
																			// 1st
																			// show
																			// of
																			// rock
			Common.impicitWait(2);
			driver.findElement(By.xpath("Episode")).click();// clicked on
																	// 1st
																	// episode
			Common.impicitWait(2);
			Common.log("Navigated to NPL screen");
			WebElement favicon = validationUtil.validateDisplayWebElement("NPL.FavoriteIcon", driver,
					"Favorite icon is not displayed");
			boolean flag = favicon.isSelected();
			if (flag = true) {
				favicon.click();
			} else {
			Common.log("Show is already markes as unFavorite");
			}
			String name = validationUtil.validateWebElement("NPL.PDT", driver).getText();
			Common.log("Show name:" + name);
			validationUtil.validateWebElement("Everest.Minimizebutton", driver).click();
			Common.impicitWait(4);
			validationUtil.validateWebElement("Home.FavoriteIcon", driver).click();
			Common.impicitWait(3);
			/*
			 * List<WebElement> shows = driver.findElements(By.xpath(
			 * elementReader.getPropertyElement("Favorites.ListCount")));
			 */
			if (driver.getPageSource().contains(name)) {
				common.scrollUntilTextExists(name);
				Assert.assertTrue(false, "Show is  not present in the Favorite list");
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	

	public void verifyOnDemandDMCA() {
		Common common = new Common(driver);
		try {
			clickOnDemand();
			Common.log("Verifying On Demand Show list.");
//			common.scrollToDown(1);
			showCount();
			validationUtil.validateWebElement("Show", driver).click();
			Common.impicitWait(4);
			validationUtil.validateWebElement("Episode", driver).click();
			Common.impicitWait(3);
		} catch (Exception e) {
			// TODO: handle exception
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	public void validatePlayerControls() {
		Common common = new Common(driver);
		try {
			WebElement element = validationUtil.validateDisplayWebElement("NPL.PlayPause", driver,
					"NPL play pause button is not present");// play/pause icon
															// present
			element.click();
			Common.impicitWait(8);
			Common.log("Clicked on play/pause icon.. music is paused");
			element.click();
			Common.impicitWait(4);
			Common.log("Clicked on play/pause icon.. music is played");
			validationUtil.validateWebElement("Everest.Minimizebutton", driver).click();
		} catch (Exception e) {
			// TODO: handle exception
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	public WebElement minimizebutton() {
		Common common = new Common(driver);
		return driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.Minimizebutton")));
	}

	public void showCount() throws AndriodException {
		Common common = new Common(driver);
		try {
			List<WebElement> Noofshows = driver
					.findElements(By.xpath(elementReader.getPropertyElement("Everest.Showcount")));
			if (null != Noofshows && Noofshows.size() > 0) {
				Common.log("No. of channels present:" + Noofshows.size());
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), "No shows are present.");
		}
	}
}
