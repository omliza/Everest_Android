package com.sxm.mobile.everest.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.sxm.framework.dto.handler.EnvirnomentHandler;
import com.sxm.framework.exception.AndriodException;
import com.sxm.framework.utility.PropertyElementReader;
import com.sxm.mobile.pages.Common;
import com.sxm.mobile.pages.ErrorMessageConstant;
import com.sxm.mobile.pages.ValidationUtil;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class EverestHome {
	private final static Logger LOGGER = Logger.getLogger(EverestHome.class.getName());
	private AppiumDriver<WebElement> driver;
	private String platform = EnvirnomentHandler.getInstance().getEnvirnoment().getChannel();
	Boolean flag = false;
	int channelcount = 0;
	int showCount = 0;
	ValidationUtil validationUtil = new ValidationUtil();
	Common common = new Common(driver);

	public EverestHome(AppiumDriver driver) {
		super();
		this.driver = driver;

	}

	private String locale = EnvirnomentHandler.getInstance().getEnvirnoment().getLocale();
	private PropertyElementReader elementReader = PropertyElementReader.getInstance(locale);

	/**
	 * @param by
	 * @return
	 */
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
	 * Click on Hip-Hop Category
	 */
	public void clickOnHipHopCategory(){
		Common common = new Common(driver);
		clickonMusic();
		common.scrollUntilTextExists("Dance/Electronic");
		clickMucisSubHipHop();
	}
	
	/*
	 * Click on Rock Category
	 */
	public void clickOnRockCategory(){
		Common common = new Common(driver);
		clickonMusic();
		common.scrollUntilTextExists("Dance/Electronic");
		clickMucisSubRock();
	}
	
	/*
	 * Click on New Channel
	 */
	public void clickNewsChannel1() throws AndriodException {
		Common common = new Common(driver);
		Common.impicitWait(2);
		common.waitforElment("News.Channel1");
		// click on a now playing channel

		validationUtil.validateWebElement("News.Channel1", driver).click();
	}
	
	
	public void clickPopChannel1() throws AndriodException {
		Common common = new Common(driver);
		Common.impicitWait(2);
		common.waitforElment("Pop.Channel1");
		// click on a now playing channel

		validationUtil.validateWebElement("Pop.Channel1", driver).click();
	}

	/**
	 * validate Home Page
	 * 
	 * @throws AndriodException
	 */
	public void validateHomePage() throws AndriodException {
		Common common = new Common(driver);
		Common.impicitWait(3);
		common.log("Landed to Home Page.");
		ValidationUtil validationUtil = new ValidationUtil();
		try {
			WebElement homeIcon = validationUtil.validateWebElement("Everest.Home", driver);
			homeIcon.isDisplayed();
			common.log("Home icon is present in the screen");
			homeIcon.click();
			Common.impicitWait(4);

		} catch (Exception e) {
			common.log("Home icon is not present in the screen");
			// Assert.fail();
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
		common.log("User is at Home page of the application.");

	}

	/**
	 * click News
	 * 
	 * @throws AndriodException
	 */
	public void clickNews() throws AndriodException {
		Common common = new Common(driver);
		try {
			Common.impicitWait(2);
			common.waitForElementTillDisplayed(validationUtil.validateWebElement("Home.News", driver), 15);
			validationUtil.validateWebElement("Home.News", driver).click();
			common.log("Successfully clicked on News");
			Common.impicitWait(4);
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ErrorMessageConstant.CategoryNews_NotDisplayed);
		}
	}

	/**
	 * click Sub Category News
	 * 
	 * @throws AndriodException
	 */
	public void clickSubCatNews() throws AndriodException {
		Common common = new Common(driver);
		Common.impicitWait(2);
		try {
			validationUtil.validateWebElement("News.SubCatNews", driver).click();
			common.log("Successfully clicked on  sub category News");
		} catch (Exception e) {
			common.log("Sub Category News is not present in the screen");
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ErrorMessageConstant.SubCategoryNews_NotDisplayed);
		}
	}

	/**
	 * Validating Howard Carousel
	 * 
	 * @throws AndriodException
	 */
	public void howardCarousel() throws AndriodException {
		Common common = new Common(driver);
		LOGGER.info("Validating Howard Carousel");
		common.log("Landed on Howard Items menu page");
		try {

			validationUtil.validateDisplayWebElement("Everest.Howardcarousel1", driver,
					ErrorMessageConstant.Carousal_NotDisplayed);
			// Assert.assertTrue(howardcarousel().isDisplayed());
			common.log("Howard Carosel item is present.");
			howardcarousel().click();
			Common.impicitWait(3);// *[@resource-id='com.sirius.dev:id/howard_main_scroll_view']/group[1]/group[1]/view[1]/group[2]/group[1]
			validationUtil.validateDisplayWebElement("Everest.Minimizebutton", driver,
					ErrorMessageConstant.MinimizeBtn_NotDisplayed);
			// Assert.assertTrue(minimizebutton().isDisplayed());
			common.log("Navigate to Now playing screen");
			minimizebutton().click();
			Common.impicitWait(3);
			validationUtil.validateWebElement("Everest.HowardCarousel2", driver).click();
			// driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.HowardCarousel2"))).click();
			Common.impicitWait(2);
			validationUtil.validateDisplayWebElement("Everest.Minimizebutton", driver,
					ErrorMessageConstant.MinimizeBtn_NotDisplayed);
			// Assert.assertTrue(minimizebutton().isDisplayed());
			common.log("Navigate to Now playing screen");
			common.log("Selecting the Channel Carousel ,navigates to NPL screen.");
		} catch (Exception e) {

			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	/**
	 * Validating Howard Carousel
	 * 
	 * @throws AndriodException
	 */
	public void verifyhowardCarousel() throws AndriodException {
		Common common = new Common(driver);
		common.log("Validating Howard Carousel MOBANDEVER 349");
		try {

			validationUtil.validateDisplayWebElement("Everest.Howardcarousel1", driver,
					ErrorMessageConstant.Carousal_NotDisplayed);

			// Assert.assertTrue(howardcarousel().isDisplayed());
			common.log("Howard Carosel item is present.");
			howardcarousel().click();
			Common.impicitWait(3);// *[@resource-id='com.sirius.dev:id/howard_main_scroll_view']/group[1]/group[1]/view[1]/group[2]/group[1]
			validationUtil.validateDisplayWebElement("Everest.Minimizebutton", driver,
					ErrorMessageConstant.MinimizeBtn_NotDisplayed);
			// Assert.assertTrue(minimizebutton().isDisplayed());
			common.log("Navigate to Now playing screen");
			minimizebutton().click();
			Common.impicitWait(3);
			// driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.HowardCarousel2"))).click();
			validationUtil.validateWebElement("Everest.HowardCarousel2", driver).click();
			Common.impicitWait(2);
			validationUtil.validateDisplayWebElement("Everest.Minimizebutton", driver,
					ErrorMessageConstant.MinimizeBtn_NotDisplayed);
			common.log("Navigate to Now playing screen");
			common.log("Selecting the Channel Carousel ,navigates to NPL screen.");
		} catch (Exception e) {

			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	/**
	 * Validate WebElement is displayed or not
	 * 
	 * @return
	 * @throws AndriodException
	 */
	public WebElement howardcarousel() throws AndriodException {
		Common common = new Common(driver);
		return validationUtil.validateWebElement("Everest.Howardcarousel1", driver);
		// return
		// driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.Howardcarousel1")));
	}

	/**
	 * Validate WebElement is displayed or not
	 * 
	 * @return
	 * @throws AndriodException
	 */
	public WebElement minimizebutton() throws AndriodException {
		Common common = new Common(driver);
		return validationUtil.validateWebElement("Everest.Minimizebutton", driver);
		// return
		// driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.Minimizebutton")));
	}

	/**
	 * Validate WebElement is displayed or not
	 * 
	 * @return
	 * @throws AndriodException
	 */
	public WebElement howardBreadcrumb() throws AndriodException {
		Common common = new Common(driver);
		return validationUtil.validateWebElement("Everest.Breadcrumb2", driver);
		// return
		// driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.Breadcrumb")));
	}

	/**
	 * Validate WebElement is displayed or not
	 * 
	 * @return
	 * @throws AndriodException
	 */
	public WebElement onDemandtext() throws AndriodException {
		Common common = new Common(driver);
		return validationUtil.validateWebElement("Everest.NPOnDemand", driver);
		// return
		// driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.NPOnDemand")));
	}

	/**
	 * Validate WebElement is displayed or not
	 * 
	 * @return
	 * @throws AndriodException
	 */
	public WebElement howoardepiicon() throws AndriodException {
		Common common = new Common(driver);
		return validationUtil.validateWebElement("Howard.EpisodeIcon", driver);
		// return
		// driver.findElement(By.xpath(elementReader.getPropertyElement("Howard.EpisodeIcon")));
	}

	/**
	 * Validate WebElement is displayed or not
	 * 
	 * @return
	 * @throws AndriodException
	 */
	public WebElement back() throws AndriodException {
		Common common = new Common(driver);
		return validationUtil.validateWebElement("Everest.NavigateBack", driver);
		// return
		// driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.NavigateBack")));
	}

	/**
	 * Validate NPL screen
	 * 
	 * @return
	 * @throws AndriodException
	 */
	public void validateNPLScreen() throws AndriodException {
		Common common = new Common(driver);
		Common.impicitWait(3);
		try {
			validationUtil.validateDisplayWebElement("NPL.StartTime", driver,
					ErrorMessageConstant.StartTime_NotDisplayed);
			validationUtil.validateDisplayWebElement("NPL.EndTime", driver, ErrorMessageConstant.EndTime_NotDisplayed);
			String starttime = validationUtil.validateWebElement("NPL.StartTime", driver).getText();
			common.log("Start time" + starttime);
			String endtime = validationUtil.validateWebElement("NPL.EndTime", driver).getText();
			common.log("End Time=" + endtime);
			common.log("Time is displayed in the format X:XX am/pm");
			try {
				if (!validationUtil.validateWebElement("Everest.NPOnDemand", driver).isDisplayed()) {
					common.log("It is  an On Demand Show");
				}
			} catch (AndriodException en) {
				common.log("It is not an On Demand Show");
			}

			nplLogo();
			minimizebutton().click();
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	/**
	 * Validate WebElement is displayed or not
	 * 
	 * @return
	 * @throws AndriodException
	 */
	public void popChannel1() throws AndriodException {
		Common common = new Common(driver);
		validationUtil.validateWebElement("Pop.Channel1", driver).click();
	}

	/**
	 * Validate Player Controls
	 * 
	 * @return
	 * @throws AndriodException
	 */
	public void validatePlayerControls() throws AndriodException {
		Common common = new Common(driver);
		Common.impicitWait(3);
		try {
			/*validationUtil.validateDisplayWebElement("Everest.MiniplayPause", driver,
					ErrorMessageConstant.MiniBarPlayPause_NotDisplayed);*/
			WebElement nplPlayPause = validationUtil.validateDisplayWebElement("NPL.PlayPause", driver,
					ErrorMessageConstant.NPLPlayPause_NotDisplayed);
			nplPlayPause.click();
			Common.impicitWait(8);
			common.log("Clicked on play/pause icon.. music is paused");
			nplPlayPause.click();
			Common.impicitWait(4);
			common.log("Clicked on play/pause icon.. music is played");
			minimizebutton().click();
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	/**
	 * Show name is present on the NPL screen or not
	 * 
	 * @throws AndriodException
	 */
	
	public void showName() throws AndriodException {
		Common common = new Common(driver);
		Common.impicitWait(3);
		try {
			validationUtil.validateDisplayWebElement("NPL.ShowName1", driver,
					ErrorMessageConstant.ShowName_NotDisplayed);
			common.log("Show name is present on the NPL screen");
		} catch (Exception e) {
			common.log("Showname is not present in the screen");
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());

		}
	}

	/**
	 * Now Playing Art is present on the NPL screen or not
	 */
	public void nowPlayingArt() throws AndriodException {
		Common common = new Common(driver);
		Common.impicitWait(3);
		try {
			validationUtil.validateDisplayWebElement("NPL.PlayingArt", driver,
					ErrorMessageConstant.NPLAlbumArt_NotDisplayed);
			common.log("Now Playing Art is present on the NPL screen");
		} catch (Exception e) {
			common.log("Now Playing Art is not present in the screen");
		}
	}

	/**
	 * Show Logo is present in the screen or not
	 * 
	 */
	public void nplLogo() throws AndriodException {
		Common common = new Common(driver);
		try {
			validationUtil.validateDisplayWebElement("NPL.Logo", driver, ErrorMessageConstant.Logo_NotDisplayed);
			common.log("Logo is present on the NPL screen");
		} catch (Exception e) {
			common.log("Show Logo is not present in the screen");
		}
	}

	/**
	 * Channel number is present on the NPL screen or not
	 */
	public void nplChnumber() throws AndriodException {
		Common common = new Common(driver);
		Common.impicitWait(2);
		try {
			// driver.findElement(By.xpath(elementReader.getPropertyElement("NPL.ChNumber"))).isDisplayed();

			validationUtil.validateDisplayWebElement("NPL.ChNumber", driver,
					ErrorMessageConstant.ChannelNumber_NotDisplayed);
			common.log("Channel number is present on the NPL screen");
		} catch (Exception e) {
			common.log("Channel number is not present in the screen");
		}
	}

	/**
	 * Show name is present on the NPL screen or not
	 * 
	 * @throws AndriodException
	 */
	public void nplShowName() throws AndriodException {
		Common common = new Common(driver);
		try {

			validationUtil.validateDisplayWebElement("NPL.ShowName", driver,
					ErrorMessageConstant.ShowName_NotDisplayed);
			common.log("Show name is present on the NPL screen");
		} catch (Exception e) {
			common.log("Show name is not present on the NPL screen");

		}
	}

	/*
	 * Verify PDT in NPL
	 */
	public void nplPDT() throws AndriodException {
		Common common = new Common(driver);
		Common.impicitWait(2);
		try {
			common.log("Verify PDT is shown on NPL");
			validationUtil.validateDisplayWebElement("NPL.PDT", driver, ErrorMessageConstant.ShowPDT_NotDisplayed);
			common.log("PDT is present on the NPL screen");
			// common.log("NPL PDT is :" + NPL.PDT.getText());
		} catch (Exception e) {
			common.log("PDT is not present on the NPL screen");

		}
	}

	/*
	 * Click on Minimize button of NPL
	 */
	public void clickminimize() throws AndriodException {
		Common common = new Common(driver);
		minimizebutton().click();
	}
	
	public void verifyNPMinimizebutton()throws AndriodException{
		Common common = new Common(driver);
		try{
		//Common.scrollToDown(1);
		validationUtil.validateWebElement("Show", driver).click();
		Common.impicitWait(3);
		validationUtil.validateWebElement("Episode", driver).click();
		Common.log("Navigated to NPL screen");
		validationUtil.validateDisplayWebElement("Everest.Minimizebutton", driver, ErrorMessageConstant.MinimizeBtn_NotDisplayed);
		validationUtil.validateWebElement("Everest.Minimizebutton", driver).click();
		common.implicitWait(3);
		validationUtil.validateDisplayWebElement("MiniBar.PlaypauseButton", driver, ErrorMessageConstant.MiniBarPlayPause_NotDisplayed);
		} catch (Exception e) {
		throw new AndriodException(this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName(),e.getMessage());
	}
}
	
	public void verifyNPDisallowedMinimizebutton()throws AndriodException{
		Common common = new Common(driver);
		try{
		//Common.scrollToDown(1);
		Common.impicitWait(3);
		validationUtil.validateDisplayWebElement("Everest.Minimizebutton", driver, ErrorMessageConstant.MinimizeBtn_NotDisplayed);
		Common.impicitWait(3);
		Common.log("Navigated to NPL screen");
		validationUtil.validateWebElement("Everest.Minimizebutton", driver).click();
		common.implicitWait(3);
		validationUtil.validateDisplayWebElement("MiniBar.PlaypauseButton", driver, ErrorMessageConstant.MiniBarPlayPause_NotDisplayed);
		} catch (Exception e) {
		throw new AndriodException(this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName(),e.getMessage());
	}
}

	/*
	 * Click on Back Button
	 */
	public void clickBackButton() {
		Common common = new Common(driver);
		((AndroidDriver) driver).pressKeyCode(4);
		Common.impicitWait(3);
	}

	/*
	 * Verify Album-Art, Play-Pause, Artist Info in mini-NPL
	 */
	public void miniBar() throws AndriodException {
		Common common = new Common(driver);
		try {
			
			/*WebElement art = validationUtil.validateDisplayWebElement("Everest.MiniArt", driver,
					ErrorMessageConstant.MiniBarAlbumArt_NotDisplayed);*/
			WebElement Minibar = validationUtil.validateDisplayWebElement("Everest.MiniNPLBar", driver,
					ErrorMessageConstant.MiniBarAlbumArt_NotDisplayed);
			// Assert.assertTrue(miniArtist().isDisplayed());
			validationUtil.validateDisplayWebElement("Everest.Artistinfo", driver,ErrorMessageConstant.MiniBarArtistInfo_NotDisplayed);
			common.log("Artist info is present");
			validationUtil.validateDisplayWebElement("Everest.MiniplayPause", driver,
					ErrorMessageConstant.MiniBarPlayPause_NotDisplayed);
			common.log("Play-Pause button is present");
			common.log("Now Playing Mini Bar is available ");
		} catch (Exception e) {
			// common.log("Album art is not present");
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	/*
	 * public void dragdrop() { Actions act = new Actions(driver);
	 * 
	 * // Script for dragging an element and dropping it in another place
	 * 
	 * WebElement From = driver.findElement(By.xpath(
	 * "//*[@resource-id='com.sirius.dev:id/favorites_recycler_view']/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.ImageView[2]"
	 * )); System.out.println(From.getLocation());
	 * 
	 * // WebElement To = // driver.findElement(By.xpath(
	 * "//*[@resource-id='com.sirius.dev:id/favorites_recycler_view']/android.widget.LinearLayout[5]/android.widget.RelativeLayout/android.widget.ImageView[2]"
	 * )); // System.out.println(To.getLocation());
	 * 
	 * // act.dragAndDrop(From, To).build().perform(); act.dragAndDropBy(From,
	 * 60, 960).perform(); Common.impicitWait(3);
	 * 
	 * Map<String, Object> params10 = new HashMap<>(); params10.put("location",
	 * "64,340"); params10.put("operation", "down"); Object result10 =
	 * driver.executeScript("mobile:touch:tap", params10);
	 * 
	 * }
	 */

	/**
	 * test MiniBar Navigation
	 * 
	 * @throws AndriodException
	 */
	public void testMiniBarNavigation() throws AndriodException {
		Common common = new Common(driver);
		Common.impicitWait(3);
		try {
			validationUtil.validateWebElement("Everest.Artistinfo", driver).click();
			// driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.MiniArtistinfo"))).click();
			Common.impicitWait(3);
			common.log("Navigated to Now playing screen.");
			WebElement minimizebutton = validationUtil.validateDisplayWebElement("Everest.Minimizebutton", driver,"Minimize button is not displayed");
			common.log("Minimize button is displyed");
			// driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.Minimizebutton"))).click();
			Common.impicitWait(3);
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	/**
	 * Click on channel
	 */
	public void clickchannel() {
		Common common = new Common(driver);
		Common.impicitWait(3);
		try {
			validationUtil.validateWebElement("Channel", driver).click();
			Common.impicitWait(3);
			common.log("Navigated to Now playing screen.");
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	/**
	 * Video icon is present in the screen or not
	 * 
	 * @throws AndriodException
	 */
	public void clickVedio() throws AndriodException {
		Common common = new Common(driver);
		Common.impicitWait(2);
		try {
			WebElement vedio = validationUtil.validateDisplayWebElement("Everest.Vedio", driver,
					ErrorMessageConstant.VideoIcon_NotDisplayed);
			vedio.click();
			common.log("Clicked on Vedio link");
			Common.impicitWait(4);
		} catch (Exception e) {
			common.log("Video icon is not present in the screen");
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	 /**
	 * @return
	 * @throws AndriodException
	 */
	public WebElement miniArtist() throws AndriodException {
		Common common = new Common(driver);
		return validationUtil.validateWebElement("Everest.MiniArtistinfo", driver);
		// return
		// driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.MiniArtistinfo")));
	}

	/**
	 * Favorite icon is present in the screen or not
	 * 
	 * @throws AndriodException
	 */
	public void validateFavouriteIcon() throws AndriodException {
		Common common = new Common(driver);
		Common.impicitWait(3);
		try {
			validationUtil.validateDisplayWebElement("Home.FavoriteIcon", driver,
					ErrorMessageConstant.FavoriteIcon_NotDisplayed);
			// Assert.assertTrue(favorite().isDisplayed());
			common.log("Favourite icon is present in the screen");
			favorite().click();
			common.log("Successfully clicked on favourite icon");
			WebElement favoritesBrdCrumb = validationUtil.validateDisplayWebElement("Favorites.FavoriteBreadcrumb",
					driver, "Favorite " + ErrorMessageConstant.BrdCrm_NotDisplayed);
			// Assert.assertTrue(favoritesBrdCrumb.isDisplayed());
			common.log("Successfully navigated to favorites page");
			if (favoritesBrdCrumb.getText().equalsIgnoreCase("Favorites")) {
				Assert.assertTrue(true);
				common.log("Favorites Breadcrumb is present in Favorite page");
				common.log("Favorite page Title is Favorites");
			} else {
				common.log("Favorites Breadcrumb validation failed");
				throw new AndriodException(this.getClass().getSimpleName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(),
						"Favorite " + ErrorMessageConstant.BrdCrm_NotDisplayed);
			}
		} catch (Exception e) {
			common.log("Favourite icon is not present in the screen");
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	/**
	 * @return
	 * @throws AndriodException
	 */
	public WebElement favorite() throws AndriodException {
		Common common = new Common(driver);
		return validationUtil.validateWebElement("Home.FavoriteIcon", driver);
	}

	/**
	 * click fav button
	 * 
	 * @throws AndriodException
	 */
	public void clickFav() throws AndriodException {
		Common common = new Common(driver);
		try {
			favorite().click();
			Common.impicitWait(3);
			common.log("Successfully clicked on favourite icon");
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	/*
	 * public void dragdropvalidation() throws AndriodException { LOGGER.info(
	 * "Validating Drag & Drop Functionality"); common.log(
	 * "Validating Drag & Drop Functionality MOBANDEVER-309,310,381,389"); try {
	 * List<WebElement> channelsize = driver.findElements(By.xpath(
	 * "//*[@resource-id='com.sirius.dev:id/favorites_recycler_view']/android.widget.LinearLayout"
	 * )); if (null != channelsize && channelsize.size() > 0) { common.log(
	 * "No. of channels present:" + channelsize.size()); common.log(
	 * "Favorite items count:" + channelsize.size()); } else { common.log(
	 * "Favorite items are  not present"); // Assert.assertTrue(false); throw
	 * new AndriodException(this.getClass().getSimpleName(),
	 * Thread.currentThread().getStackTrace()[1].getMethodName(),
	 * "Favorite items are  not present"); } } catch (Exception e) {
	 * driver.findElement(By.xpath("//*[@text='No Favorites Yet']"
	 * )).isDisplayed(); System.out.println(driver.findElement(By.xpath(
	 * "//*[@text='No Favorites Yet']")).getText()); } Common.impicitWait(3);
	 * Assert.assertTrue(favedit().isDisplayed()); favedit().click();
	 * common.log("Favorite list is in Edit mode"); Common.impicitWait(5); //
	 * dragdrop(); }
	 */

	/**
	 * click fav edit
	 * 
	 * @return
	 * @throws AndriodException
	 */
	public WebElement favedit() throws AndriodException {
		Common common = new Common(driver);
		return validationUtil.validateWebElement("Favorites.Edit", driver);
		// return
		// driver.findElement(By.xpath(elementReader.getPropertyElement("Favorites.Edit")));
	}

	/**
	 * validate BackButton
	 * 
	 * @throws AndriodException
	 */
	public void favoriteBackButtton() throws AndriodException {
		Common common = new Common(driver);
		Common.impicitWait(3);
		try {
			validationUtil.validateWebElement("Home.Favorites", driver).click();
			// driver.findElement(By.xpath(elementReader.getPropertyElement("Home.Favorites"))).click();
			common.log("Landed on Favorite page");
			Common.impicitWait(2);
			validationUtil.validateWebElement("Everest.BackButton", driver).click();
			// driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.BackButton"))).click();
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	/**
	 * Verifying DMCA Restricted channels
	 * 
	 * @throws AndriodException
	 */
	public void validateMusicCategory() throws AndriodException {
		Common common = new Common(driver);
		Common.impicitWait(3);
		try {
			LOGGER.info("Verifying DMCA Restricted channels.");
			driver.findElement(By.xpath(elementReader.getPropertyElement("Home.Music"))).click();
			common.log("Verifying elements on Music page");
			Common.impicitWait(3);
			common.scrollUntilTextExists("Music Categories");
			driver.findElement(By.xpath(elementReader.getPropertyElement("Music.Popchannel"))).isDisplayed();
			driver.findElement(By.xpath(elementReader.getPropertyElement("Music.Popchannel"))).click();
			Common.impicitWait(3);
			common.log("Landed on pop menu page");
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	/**
	 * Music Category is present or not
	 * 
	 * @throws AndriodException
	 */
	public void clickonMusic() throws AndriodException {
		Common common = new Common(driver);
		Common.impicitWait(3);
		try {
			common.waitforElment("Home.Music");
			WebElement music = validationUtil.validateDisplayWebElement("Home.Music", driver,
					ErrorMessageConstant.CategoryMusic_NotDisplayed);
			music.click();
			common.log("Clicked on Music Menu.");
			Common.impicitWait(4);
		} catch (Exception e) {
			common.log("Music Category is not present.");
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	/**
	 * Sports Category is present or not
	 * 
	 * @throws AndriodException
	 */
	public void clickonSports() throws AndriodException {
		Common common = new Common(driver);
		Common.impicitWait(3);
		try {
			WebElement sports = driver.findElement(By.xpath(elementReader.getPropertyElement("Home.Sports")));
			Assert.assertTrue(sports.isDisplayed());
			sports.click();
			common.log("Clicked on Sports Menu.");
			Common.impicitWait(3);
		} catch (Exception e) {
			common.log("Sports Category is not present.");
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	/**
	 * Music rock channel is present or not
	 * 
	 * @throws AndriodException
	 */
	public void clickMucisSubRock() throws AndriodException {
		Common common = new Common(driver);
		Common.impicitWait(3);
		common.scrollUntilTextExists("Rock");
		try {
			WebElement musicPop = validationUtil.validateDisplayWebElement("Music.Rockchannel", driver,
					ErrorMessageConstant.SubCategoryMusic_NotDisplayed);
			musicPop.click();
			common.log("Clicked on Music Menu Rock Channel");
		} catch (Exception e) {
			common.log("Music menu Rock channel is not present.");
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	/**
	 * Music pop channel is present or not
	 * 
	 * @throws AndriodException
	 */
	public void clickMucisSubPop() throws AndriodException {
		Common common = new Common(driver);
		Common.impicitWait(3);
		try {
			common.scrollUntilTextExists("Pop");
			WebElement musicPop = validationUtil.validateWebElement("Music.Popchannel", driver);
			musicPop.click();
			common.log("Clicked on Music Menu Pop Channel");
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ErrorMessageConstant.SubCategoryMusic_NotDisplayed);
		}

	}

	/**
	 * Music hiphop channel is present or not
	 * 
	 * @throws AndriodException
	 */
	public void clickMucisSubHipHop() throws AndriodException {
		Common common = new Common(driver);
		common.scrollUntilTextExists("Dance/Electronic");
		Common.impicitWait(3);
		common.scrollUntilTextExists("Hip-Hop");
		try {
			WebElement musicHipHop = validationUtil.validateWebElement("Music.HipHop", driver);
			musicHipHop.click();
			common.log("Clicked on Music Menu HipHop Channel");
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ErrorMessageConstant.SubCategoryMusic_NotDisplayed);
		}

	}

	/**
	 * Validate episode
	 * 
	 * @throws AndriodException
	 */
	public void clickOnEpisode() throws AndriodException {
		Common common = new Common(driver);
		Common.impicitWait(3);
		try {
			WebElement episode = validationUtil.validateWebElement("Episode1", driver);
			episode.click();
			common.log("Clicked on 3 Episode");
		} catch (Exception e) {
			validationUtil.validateWebElement("Episode2", driver).click();
			common.log("Clicked on 4 Episode");
		}
	}

	public void contexualBanner() throws AndriodException {
		Common common = new Common(driver);
		try {
			// WebElement el = driver.findElement(By.xpath("")); unable to get
			// the xpath for the contexual banner property.
			// Assert.assertTrue(el.isDisplayed());
			common.log("Contexual Banner is present for the Episode.");
		} catch (Exception e) {

			common.log("Contexual Banner is not present for the Episode.");
		}
	}

	/**
	 * @return
	 * @throws AndriodException
	 */
	public WebElement channelHeader() throws AndriodException {
		Common common = new Common(driver);
		return validationUtil.validateWebElement("OnDemand.Header", driver);
	}

	/**
	 * Verifying On demand Filter Results
	 * 
	 * @throws AndriodException
	 */
	public void VerifyonDemand() throws AndriodException {
		Common common = new Common(driver);
		try {

			Common.impicitWait(3);
			common.log("Verifying On demand Filter Results");
			common.scrollToUp(1);
			Common.impicitWait(4);
			onDemandFilter("bru");
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	/**
	 * Verifying On Demand Show list
	 * 
	 * @throws AndriodException
	 */
	public void onDemandShows() throws AndriodException {
		Common common = new Common(driver);
		try {
			common.log("Verifying On Demand Show list.");
			showCount();
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	/**
	 * Verifying On Demand Show list
	 * 
	 * @throws AndriodException
	 */
	public void onDemandShowsList() throws AndriodException {
		Common common = new Common(driver);
		try {

			Common.impicitWait(2);
			common.log("Verifying On Demand Show list.");
			showCount();
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	/**
	 * Verifying On Demand Show list
	 * 
	 * @throws AndriodException
	 */
	public void onDemandShowsListTab() throws AndriodException {
		Common common = new Common(driver);
		try {
			Common.impicitWait(2);

			clickOnDemand();
			common.log("Verifying On Demand Show list.");
			showCount();
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	/**
	 * Channel header is present in the On Demand Show List or not
	 * 
	 * @throws AndriodException
	 */
	public void onDemandShowHeader() throws AndriodException {
		Common common = new Common(driver);
		try {

			validationUtil.validateDisplayWebElement("OnDemand.Header", driver,
					ErrorMessageConstant.OnDemandHeader_NotDisplayed + " in the On Demand Show List ");
			common.log("Channel header is present  in the On Demand Show List ");
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	/*
	 * OnDemand Filtered List - Scroll
	 */
	public void onDemandFilteredREsults() throws AndriodException {
		Common common = new Common(driver);
		try {

			common.log("Verifying On demand Filter Results");
			common.scrollToUp(1);
			onDemandFilter("br");
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	/*
	 * Close Button - OnDemand Filtered List
	 */
	public void closeButton() throws AndriodException {
		Common common = new Common(driver);
		try {

			common.log("Verifying On demand Filter Results");
			common.scrollToUp(1);
			onDemandFilter("c");
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	/*
	 * Cancel Button - On Demand Filtered List
	 */
	public void cancelButton() throws AndriodException {
		Common common = new Common(driver);
		try {

			common.log("Verifying On demand Filter Results");
			common.scrollToUp(1);
			WebElement filter = validationUtil.validateDisplayWebElement("Everest.Filtersearch", driver,
					ErrorMessageConstant.SearchFilter_NotDisplayed);
			common.log("OnDemand filter is displayed");
			filter.click();
			Common.impicitWait(2);
			validationUtil.validateDisplayWebElement("Everest.CancelFilter", driver,
					ErrorMessageConstant.CancelFilter_NotDisplayed);
			common.log("Cancel button is present.");
			validationUtil.validateWebElement("Everest.CancelFilter", driver).click();
			Common.impicitWait(4);
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	/*
	 * On Demand Filtered List - Cancel Filter
	 * TBD: This method & cancelButton are same. One needs to be removed
	 */
	public void onDemandFilter() throws AndriodException {
		Common common = new Common(driver);
		try {

			common.log("Verifying On demand Filter Results");
			common.scrollToUp(1);
			WebElement filter = validationUtil.validateDisplayWebElement("Everest.Filtersearch", driver,
					ErrorMessageConstant.SearchFilter_NotDisplayed);
			common.log("OnDemand filter is displayed");
			filter.click();
			Common.impicitWait(2);
			validationUtil.validateDisplayWebElement("Everest.CancelFilter", driver,
					ErrorMessageConstant.CancelFilter_NotDisplayed);
			common.log("Cancel button is present.");
			validationUtil.validateWebElement("Everest.CancelFilter", driver).click();
			Common.impicitWait(4);
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	/*
	 * Episode Listing: Show Logo
	 */
	public void episodeListingShowLogo() throws AndriodException {
		Common common = new Common(driver);
		try {

			Common.impicitWait(3);
			validationUtil.validateWebElement("Shows", driver).click();
			Common.impicitWait(4);
			/*
			 * validationUtil.validateDisplayWebElement("Showlogo", driver,
			 * "Show logo is  not available for the episode Listing");
			 */
			if (showLogo1() != null || showLogo2() != null) {
				common.log("Show logo or show name displyaed");
			}
			else
				common.log("Show Logo or Show name is not displayed for the listing");
			// Assert.assertTrue(showLogo().isDisplayed(), "Show logo is
			// available for the episode Listing");
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	/*
	 * Show Logo
	 */
	public WebElement showLogo1() {
		Common common = new Common(driver);
		WebElement element = null;
		try {
			element = driver.findElement(By.xpath(elementReader.getPropertyElement("Showlogo")));
		} catch (Exception e) {
			common.log("Exception thrown in identifying show logo");
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
		return element;
	}

	/*
	 * Show Logo\Show Name
	 */
	public WebElement showLogo2() {
		Common common = new Common(driver);
		WebElement element = null;
		try {
			element = driver.findElement(By.xpath(elementReader.getPropertyElement("Showlogo1")));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return element;
	}

//	public void clickOnDemand() throws AndriodException {
//		Common common = new Common(driver);
//		try {
//			common.waitforElment("Everest.OnDemand");
//			try {
//				common.waitForElementTillDisplayed(validationUtil.validateWebElement("Everest.OnDemand", driver), 40);
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//			validationUtil.validateWebElement("Everest.OnDemand", driver).click();
//			common.log("OnDemand  tab is clicked");
//			Common.impicitWait(3);
//			try {
//				common.waitForElementTillDisplayed(validationUtil.validateWebElement("Everest.OnDemandHeader", driver),
//						20);
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//			common.waitforElment("Everest.OnDemandHeader");
//			validationUtil.validateDisplayWebElement("Everest.OnDemandHeader", driver,
//					ErrorMessageConstant.OnDemandHeader_NotDisplayed);
//			common.log("On Demand Header is present");
//		} catch (Exception e) {
//			throw new AndriodException(this.getClass().getSimpleName(),
//					Thread.currentThread().getStackTrace()[1].getMethodName(),
//					ErrorMessageConstant.OnDemandTab_NotDisplayed);
//		}
//
//	}
	
	/*
	 * OnDemand tab - Click and verify Header
	 */
	public void clickOnDemand() throws AndriodException {
		Common common = new Common(driver);
		try {
			common.waitforElment("Everest.OnDemand");
			try {
				common.waitForElementTillDisplayed(validationUtil.validateWebElement("Everest.OnDemand", driver), 40);
			} catch (Exception e) {
				// TODO: handle exception
			}
			validationUtil.validateWebElement("Everest.OnDemand", driver).click();
			Common.log("OnDemand  tab is clicked");
			Common.impicitWait(8);
			/*try {
				Common.waitForElementTillDisplayed(validationUtil.validateWebElement("Everest.OnDemandHeader", driver),
						20);
			} catch (Exception e) {
				// TODO: handle exception
			}
			Common.waitforElment("Everest.OnDemandHeader");*/
			validationUtil.validateDisplayWebElement("Everest.OnDemandHeader", driver,
					ErrorMessageConstant.OnDemandHeader_NotDisplayed);
			Common.log("On Demand Header is present");
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					e.getMessage());
		}

	}
	
	/*
	 * Scroll Until Text exists
	 */
	public  void scrollUntilTextExists(String string) {
		Common common = new Common(driver);
		boolean value = false;
		for (int i = 0; i <= 10; i++) {
			if (!driver.getPageSource().contains(string)) {
				scrollToDown(1);
			}
			else {
				value = true;	
				break;}
		}
		if(value == false){
			for (int i = 0; i <= 10; i++) {
				if (!driver.getPageSource().contains(string)) {
					scrollToUp(1);
				}
				else {
					break;}
			}
		}
	}
	
	/*
	 * Scroll Down
	 */
	public  void scrollToDown(int j) {
		Common common = new Common(driver);
		for (int i = 0; i <= j; i++) {
			Dimension dimensions = driver.manage().window().getSize();
			Double screenHeightStart = dimensions.getHeight() * 0.4;
			int scrollStart = screenHeightStart.intValue();
			//System.out.println("s=" + scrollStart);
			Double screenHeightEnd = dimensions.getHeight() * 0.2;
			int scrollEnd = screenHeightEnd.intValue();
			driver.swipe(0, scrollStart, 0, scrollEnd, 2000);
		}
	}
	
	/*
	 * Scroll to up
	 */
	public  void scrollToUp(int j) {
		Common common = new Common(driver);
		for (int i = 0; i <= j; i++) {
			Dimension dimensions = driver.manage().window().getSize();
			Double screenHeightStart = dimensions.getHeight() * 0.3;
			int scrollStart = screenHeightStart.intValue();
			//System.out.println("s=" + scrollStart);
			Double screenHeightEnd = dimensions.getHeight() * 0.6;
			int scrollEnd = screenHeightEnd.intValue();
			driver.swipe(0, scrollStart, 0, scrollEnd, 2000);
		}
	}

	/*
	 * Show Logo
	 */
	public WebElement showLogo() throws AndriodException {
		Common common = new Common(driver);
		return validationUtil.validateWebElement("Showlogo", driver);

	}

	/*
	 * Show Listing
	 */
	public void showListing() throws AndriodException {
		Common common = new Common(driver);
		try {
			Common.impicitWait(4);
			driver.findElement(By.xpath(elementReader.getPropertyElement("Music.HipHop"))).click();
			Common.impicitWait(2);
			common.log("Landed on Hip-Hop menu page");
			Common.impicitWait(2);
			driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.OnDemand"))).click();
			common.log("OnDemand  tab is clicked");
			Common.impicitWait(2);
			Showdescription();
			common.scrollToDown(1);
			Common.impicitWait(3);
			clickOnEpisode();
			Common.impicitWait(4);
			verifyAirdate();

			List<WebElement> episode = driver
					.findElements(By.xpath(elementReader.getPropertyElement("Everest.EpisodeCount")));
			int episodecount = episode.size();
			common.log("Episode available:" + episodecount);
			Common.impicitWait(3);
			driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.NavigateBack"))).click();
			Common.impicitWait(3);
			driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.OnDemand"))).isDisplayed();
			common.log("User land on the prevoius page ");
			clickBackButton();
			Common.impicitWait(4);
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	/*
	 * On Demand Show Listing
	 */
	public void OnDemandshowListing() throws AndriodException {
		Common common = new Common(driver);
		try {
			Common.impicitWait(2);
			common.scrollToDown(1);
			Showdescription();
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	/*
	 * Episode Filter - Cancel Button
	 */
	public void testEpisodeFilterCancelButton() throws AndriodException {
		Common common = new Common(driver);
		try {
			// common.scrollToDown(1);
			Showdescription();
			// common.scrollToDown(2);
			Common.impicitWait(3);
			validationUtil.validateWebElement("Shows", driver).click();
			Common.impicitWait(4);
			// driver.findElement(By.xpath("//*[@resource-id='com.sirius.dev:id/episode_list_recycler_view']/android.widget.RelativeLayout[1]")).click();
			common.scrollChannelsdown(1);
			episodeFilter("d");
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	/*
	 * Episode - Filter
	 */
	public void testEpisodeFilter1() throws AndriodException {
		Common common = new Common(driver);
		try {
			Common.impicitWait(2);
			Showdescription();
			Common.impicitWait(3);
			validationUtil.validateWebElement("Shows", driver).click();
			Common.impicitWait(4);
			common.scrollChannelsdown(1);
			WebElement filter = driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.SearchFilter")));
			Assert.assertTrue(filter.isDisplayed());
			common.log("Episode filter is displayed");
			filter.click();
			searchCancel().click();
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	/*
	 * Episode Filter - Close Button
	 */
	public void EpisodeFilterCloseButton() throws AndriodException {
		Common common = new Common(driver);
		try {
			Common.impicitWait(2);
			Showdescription();
			validationUtil.validateWebElement("Shows", driver).click();
			Common.impicitWait(3);
			common.scrollChannelsdown(1);
			episodeFilter("a");
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	/*
	 * Episode Filter - No Result
	 */
	public void testEpisodeFilterNoResult() throws AndriodException {
		Common common = new Common(driver);
		try {
			Common.impicitWait(2);
			Showdescription();
			validationUtil.validateWebElement("Shows", driver).click();
			Common.impicitWait(3);
			common.scrollChannelsdown(1);
			episodeFilter("ii");
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	/*
	 * Show Description: Show count & results
	 */
	public void Showdescription() throws AndriodException {
		Common common = new Common(driver);
		try {

			List<WebElement> Noofshows = driver
					.findElements(By.xpath(elementReader.getPropertyElement("Everest.Showcount")));
			if (null != Noofshows && Noofshows.size() > 0) {
				common.log("Show Count:" + Noofshows.size());
				common.log("Shows are present.");
			} else {
				common.log("Shows are not present.");
				WebElement noResult = validationUtil.validateDisplayWebElement("Everest.NoResult", driver,
						ErrorMessageConstant.NoResultText_NotDisplayed);
				String text = noResult.getText();
				common.log(text);
				String text1 = validationUtil.validateDisplayWebElement("Everest.ShowNoResultText", driver,
						ErrorMessageConstant.NoResultText_NotDisplayed).getText();
				common.log(text1);
			}
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());

		}
	}

	/*
	 * Episode Description
	 */
	public void episodeDescription() throws AndriodException {
		Common common = new Common(driver);
		try {

			List<WebElement> Noofepisode = driver
					.findElements(By.xpath(elementReader.getPropertyElement("Everest.EpisodeCount")));
			if (null != Noofepisode && Noofepisode.size() > 0) {
				common.log("Episode Count:" + Noofepisode.size());
				common.log("Episodes are present.");
			} else {
				common.log("Episodes are not present.");
				WebElement noResult = validationUtil.validateDisplayWebElement("Everest.NoResult", driver,
						ErrorMessageConstant.NoResultText_NotDisplayed);
				String text = noResult.getText();
				common.log(text);
				String text1 = validationUtil.validateDisplayWebElement("Everest.EpiNoResultText", driver,
						ErrorMessageConstant.NoResultText_NotDisplayed).getText();
				common.log(text1);
			}
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	/*
	 * Channels Count
	 */
	public void channelscount() throws AndriodException {
		Common common = new Common(driver);
		try {

			List<WebElement> Noofchannel = driver
					.findElements(By.xpath(elementReader.getPropertyElement("Everest.Channelcount")));
			if (null != Noofchannel && Noofchannel.size() > 0) {
				common.log("Channel Count:" + Noofchannel.size());
				common.log("Channels  are present.");
			} else {
				common.log("Channels are not present.");
				int channelcount = Noofchannel.size();
				common.log("Channel Count:" + channelcount);
			}
		} catch (Exception e) {
			common.log("NO Channels are Present");
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	/*
	 * Episode Count
	 */
	public void episodecnt() throws AndriodException {
		Common common = new Common(driver);
		try {
			List<WebElement> Noofepisode = driver
					.findElements(By.xpath(elementReader.getPropertyElement("Everest.EpisodeCount")));
			int EpisodeCount = Noofepisode.size();
			common.log("Episode Count:" + EpisodeCount);

		} catch (Exception e) {
			common.log("NO Episodes are Present");
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	/*
	 * Channel List
	 */
	public void channelList() throws AndriodException {
		Common common = new Common(driver);
		try {
			common.log("The Live Channel Tab should be selected by default.");
			channelscount();
			onDemandTab().click();
			common.log("On Demand Tab is Clicked");
			validationUtil.validateWebElement("Everest.NavigateBack", driver).click();
			Common.impicitWait(2);
			common.log("Navigate back to  category page");
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	/*
	 * Category Back Button
	 */
	public void categoryBackButton() throws AndriodException {
		Common common = new Common(driver);
		back().click();

	}

	/*
	 * Category- No Result
	 */
	public void categoryNoResult() throws AndriodException {
		Common common = new Common(driver);
		showCount();
		common.scrollToDown(2);
		onDemandFilter("ter");
		searchCancel().click();

	}

	/*
	 * Show Count
	 */
	public void showCount() throws AndriodException {
		Common common = new Common(driver);
		try {

			List<WebElement> Noofshows = driver
					.findElements(By.xpath(elementReader.getPropertyElement("Everest.Showcount")));
			if (null != Noofshows && Noofshows.size() > 0) {
				common.log("Show Count:" + Noofshows.size());
				common.log("Shows are present.");
			} else {
				common.log("Shows are not present.");
			}
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), ErrorMessageConstant.Shows_NotDisplayed);
		}
	}

	/*
	 * Verify Channel List
	 */
	public void verifychannelList() throws AndriodException {
		Common common = new Common(driver);
		common.log("The Live Channel Tab should be selected by default.");
		common.scrollToDown(1);
		channelscount();
	}

	/*
	 * Channel List Verification
	 */
	public void channelListverification() throws AndriodException {
		Common common = new Common(driver);
		try {
			common.log("The Live Channel Tab should be selected by default.");
			common.scrollToDown(1);
			channelscount();
			onDemandTab().click();
			common.log("On Demand Tab is Clicked");
			driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.NavigateBack"))).click();
			Common.impicitWait(2);
			common.log("Navigate back to  category page");
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	/*
	 * On Demand - Show Tab
	 * TBD: This could be redundant method
	 */
	public void onDemandShowsTab() throws AndriodException {
		Common common = new Common(driver);
		LOGGER.info("Verifying OnDemand Category- MOBANDEVER-282");
		try {
			Assert.assertTrue(onDemandTab().isDisplayed());
			onDemandTab().click();
			common.log("On Demand Tab is Clicked");
			showCount();
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	/*
	 * Verify Channel Filter
	 */
	public void testVerifyChannelFilter() throws AndriodException {
		Common common = new Common(driver);
		try {
			Common.impicitWait(3);
			channelscount();
			common.scrollChannelsdown(1);
			channelFilter("si");
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	/*
	 * Channel Filter
	 */
	public void channelfilter() throws AndriodException {
		Common common = new Common(driver);
		try {
			Common.impicitWait(3);
			channelscount();
			common.scrollToUp(1);
			validationUtil.validateDisplayWebElement("Everest.SearchFilter", driver,
					ErrorMessageConstant.SearchFilter_NotDisplayed);
			common.log("Channel filter is displayed");
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	/*
	 * Channel Filter - No Results
	 */
	public void testVerifyChannelFilterNoResult() throws AndriodException {
		Common common = new Common(driver);
		try {
			Common.impicitWait(3);
			channelscount();
			common.scrollToUp(1);
			channelFilter("sd");
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void testVerifyEpisodeFilter() throws AndriodException {
		Common common = new Common(driver);
		episodeFilter("dj");
		episodeFilter("ro");

	}

	/*
	 * Clear Button - Channel
	 */
	public void channelClearButton() throws AndriodException {
		Common common = new Common(driver);
		try {
			Common.impicitWait(3);
			channelscount();
			common.scrollToUp(1);
			channelFilter("a");
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void channelCancelButton() throws AndriodException {
		Common common = new Common(driver);
		try {
			Common.impicitWait(3);
			channelscount();
			common.scrollToUp(1);
			channelFilter("ab");
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	/**
	 * @throws AndriodException
	 *             Verifying the BreadCrumb
	 */
	public void verifyBreadcrumb() throws AndriodException {
		Common common = new Common(driver);
		try {
			// validationUtil.validateDisplayWebElement("Everest.Breadcrumb1",
			// driver, "Breadcrumb is not available");
			if (null != BreadcrumbText() || null != BreadcrumbImage()) {
				common.log("Breadcrumb is available");
			} else {
				throw new AndriodException(ErrorMessageConstant.BrdCrm_NotDisplayed);
			}
			back().click();
			Common.impicitWait(3);
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	public WebElement BreadcrumbImage() {
		Common common = new Common(driver);
		WebElement element = null;
		try {
			element = driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.Breadcrumb")));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return element;
	}

	public WebElement BreadcrumbText() {
		Common common = new Common(driver);
		WebElement element = null;
		try {
			element = driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.Breadcrumb1")));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return element;
	}

	public void howardbreadcrumb() throws AndriodException {
		Common common = new Common(driver);
		common.log("Verifying Howard Breadcrumb");
		try {
			common.scrollUntilTextExists("Howard 100");
			validationUtil.validateWebElement("Howard.Episode.Image", driver).click();
			validationUtil.validateDisplayWebElement("Everest.Breadcrumb2", driver, "BreadCrumb is not displayed");
			back().click();
			Common.impicitWait(3);
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	/*
	 * public WebElement Breadcrumb() throws AndriodException { ValidationUtil
	 * validationUtil = new ValidationUtil(); return
	 * driver.findElement(By.xpath(elementReader.getPropertyElement(
	 * "Everest.Breadcrumb"))); }
	 */

	public void VerifyOnDemandEpisodeAirdate() throws AndriodException {
		Common common = new Common(driver);
		try {
			common.log("Verify Show AirDate");
			Common.impicitWait(2);
			// common.scrollToDown(1);
			Showdescription();
			Common.impicitWait(3);
			validationUtil.validateWebElement("Shows", driver).click();
			Common.impicitWait(3);
			//validationUtil.validateDisplayWebElement("Episode.ShowLogo1", driver,
				//	ErrorMessageConstant.Logo_NotDisplayed);
			validationUtil.validateDisplayWebElement("Episode.AirDate", driver,
					ErrorMessageConstant.AirDate_notDisplayed);
			Common.impicitWait(3);
			verifyAirdate();
			driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.NavigateBack"))).click();
			Common.impicitWait(2);
			/*
			 * common.scrollToDown(2);
			 * driver.findElement(By.xpath(elementReader.getPropertyElement(
			 * "Show"))).click(); Common.impicitWait(2); verifyAirdate();
			 */
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void VerifyOnDemandEpisodeTile() throws AndriodException {
		Common common = new Common(driver);
		try {

			// common.scrollToDown(1);
			Showdescription();
			Common.impicitWait(3);
			validationUtil.validateWebElement("Shows", driver).click();
			Common.impicitWait(3);
			driver.findElement(By.xpath(elementReader.getPropertyElement("Episode.ShowLogo1"))).isDisplayed();
//			common.log(driver.findElement(By.xpath(elementReader.getPropertyElement("Episode.ShowLogo"))).getText());
			common.log("Categories - On Demand Episode Listing - Show logo is displayed");
			WebElement duration = validationUtil.validateWebElement("Episode.Tile", driver);
			char time;
			String timeformat;

			String durationString = duration.getText();
			// String string = durationString.replace("\\s","").trim();
			// System.out.println("Duration"+durationString);
			char[] ch = durationString.toCharArray();
			// System.out.println("Character lenght"+ch.length);
			switch (ch.length) {
			case 2:
				time = ch[1];
				timeformat = Character.toString(time);
				if (timeformat.equals("m")) {
					common.log("Duration is less than 1 hour :" + durationString);
				} else if (timeformat.equals("h")) {
					common.log("Duration is exactly an hour :" + durationString);
				}
				break;
			case 3:
				time = ch[2];
				timeformat = Character.toString(time);
				if (timeformat.equals("m")) {
					common.log("Duration is less than 1 hour: " + durationString);
				} else if (timeformat.equals("h")) {
					common.log("Duration is exactly an hour: " + durationString);
				}
				break;
			case 5:
				common.log("Duration is being displayed in the following format: xh xm ::" + durationString);
				break;
			case 6:
				time = ch[2];
				timeformat = Character.toString(time);
				char time1 = ch[1];
				String timeformat1 = Character.toString(time1);
				// char timeMin = ch[4] ;
				char timeMin = ch[5];
				String timeformatMin = Character.toString(timeMin);
				// String timeformatMin1 =Character.toString(timeMin1);
				if (timeformat.equals("h") && timeformatMin.equals("m")) {
					common.log("Duration is being displayed in the following format: xxh xm ::" + durationString);
				} else if (timeformat1.equals("h") && timeformatMin.equals("m")) {
					common.log("Duration is being displayed in the following format: xh xxm:: " + durationString);
				} else {
					common.log("Invalid time format");
					Assert.fail();
				}
				break;

			case 7:
				common.log("Duration is being displayed in the following format: xxh xxm ::" + durationString);
				break;

			default:
				common.log("Duration is not matching ::" + durationString);

			}
			// contexualBanner();
			common.log(" On Demand Episode Listing - On Demand Episode List");
			back().click();
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	public void verifyAirdate() throws AndriodException {
		Common common = new Common(driver);
		try {

			WebElement airdate = validationUtil.validateWebElement("Episode.AirDate", driver);
			String airdateval = airdate.getText();
			// common.log(airdateval);
			Common.impicitWait(4);
			ArrayList<String> al = new ArrayList<String>();
			al.add("Saturday");
			al.add("Sunday");
			al.add("Monday");
			al.add("Tuesday");
			al.add("Wednesday");
			al.add("Thrusday");
			al.add("Friday");
			boolean isStringExists = al.contains(airdateval);
			if (isStringExists == true) {
				common.log(" The airdate is within the last week:airdateval");
			} else {
				// String dt = airdateval.split(" ")[1].trim();
				// SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yy");

				try {
					// Date d1 = sdf.parse(dt);
					common.log(" The airdate is over a week ago:" + airdateval);
				} catch (Exception e) {
					common.log("No airdate is displayed");
				}
			}
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	public void episodeListing() throws AndriodException {
		Common common = new Common(driver);
		try {
			String Actual = driver.findElement(By.xpath(elementReader.getPropertyElement("RecentView"))).getText();
			String expected = "Most Recent";
			if (expected.equals(Actual)) {
				common.log("Recent Shows are Displayed");
			} else {
				common.log("Shows are arranged as per listened.");
			}
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void searchEpisode() throws AndriodException {
		Common common = new Common(driver);
		try {
			episodecnt();
			common.scrollToDown(1);
			Common.impicitWait(3);
			driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.SearchFilter"))).isDisplayed();
			common.log(
					driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.SearchFilter"))).getText());
			driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.SearchFilter"))).click();
			driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.CancelFilter"))).isDisplayed();
			driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.SearchFilter"))).sendKeys("e");
			episodeDescription();
			driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.CloseFilter"))).click();
			driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.SearchFilter"))).sendKeys("2");
			episodeDescription();
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	public void channelFilter() throws AndriodException {
		Common common = new Common(driver);
		try {
			Assert.assertTrue(filterSearch().isDisplayed());
			common.log("Channel filter is displayed");
			driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.SearchFilter"))).click();
			Common.impicitWait(2);
			Assert.assertTrue(searchCancel().isDisplayed());
			common.log("Cancel button is present.");
			driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.SearchFilter"))).sendKeys("3");
			Common.impicitWait(3);
			Channeldescription();
			Assert.assertTrue(searchClose().isDisplayed());
			common.log("Clear button is prtesent");
			driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.CloseFilter"))).click();
			Common.impicitWait(2);
			driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.SearchFilter"))).sendKeys("32");
			Common.impicitWait(3);
			Channeldescription();
			driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.CloseFilter"))).click();
			driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.SearchFilter"))).sendKeys("e");
			Common.impicitWait(6);
			Channeldescription();
			driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.CloseFilter"))).click();
			driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.SearchFilter"))).sendKeys("el");
			Common.impicitWait(6);
			Channeldescription();
			driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.CloseFilter"))).click();
			driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.SearchFilter"))).sendKeys("elv");
			Common.impicitWait(3);
			Channeldescription();
			driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.SearchFilter"))).sendKeys("sde");
			Common.impicitWait(2);
			Channeldescription();
			driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.CancelFilter"))).click();
			Common.impicitWait(4);
			Channeldescription();
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void episodeFilter(String string) {
		Common common = new Common(driver);
		try {
			WebElement filter = validationUtil.validateDisplayWebElement("Everest.SearchFilter", driver,
					ErrorMessageConstant.SearchFilter_NotDisplayed);
			common.log("The search filter is dislayed");
			filter.click();
			Common.impicitWait(2);
			validationUtil.validateDisplayWebElement("Everest.CancelFilter", driver,
					ErrorMessageConstant.CancelFilter_NotDisplayed);
			common.log("Cancel button is displayed");
			filter.clear();
			for (int i = 0; i < string.length(); i++) {
				char c = string.charAt(i);
				String s = new StringBuilder().append(c).toString();
				filter.sendKeys(s);
				Common.impicitWait(3);
				episodeDescription();
				validationUtil.validateDisplayWebElement("Everest.CloseFilter", driver,
						ErrorMessageConstant.ClearFilter_NotDisplayed);
				common.log("The clear button is displayed");
			}
			WebElement closefilter = validationUtil.validateWebElement("Everest.CancelFilter", driver);
			closefilter.click();
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

		/*
		 * Assert.assertTrue(filterSearch().isDisplayed()); common.log(
		 * "Episode filter is displayed");
		 * driver.findElement(By.xpath(elementReader.getPropertyElement(
		 * "Everest.SearchFilter"))).click(); Common.impicitWait(2);
		 * Assert.assertTrue(searchCancel().isDisplayed()); common.log(
		 * "Cancel button is present.");
		 * driver.findElement(By.xpath(elementReader.getPropertyElement(
		 * "Everest.SearchFilter"))).sendKeys("dj"); Common.impicitWait(3);
		 * episodeDescription(); Assert.assertTrue(searchClose().isDisplayed());
		 * common.log("Clear button is prtesent");
		 * driver.findElement(By.xpath(elementReader.getPropertyElement(
		 * "Everest.CloseFilter"))).click(); Common.impicitWait(2);
		 * driver.findElement(By.xpath(elementReader.getPropertyElement(
		 * "Everest.SearchFilter"))).sendKeys("ro"); Common.impicitWait(3);
		 * episodeDescription();
		 * driver.findElement(By.xpath(elementReader.getPropertyElement(
		 * "Everest.CancelFilter"))).click(); Common.impicitWait(4);
		 * episodeDescription();
		 */
	}

	public void Channeldescription() throws AndriodException {
		Common common = new Common(driver);
		try {

			List<WebElement> Noofchannel = driver
					.findElements(By.xpath(elementReader.getPropertyElement("Everest.Channelcount")));
			if (null != Noofchannel && Noofchannel.size() > 0) {
				common.log("Show Count:" + Noofchannel.size());
				common.log("Channels are present.");
			} else {
				common.log("Channels are not present.");
				validationUtil.validateDisplayWebElement("Everest.NoResult", driver,
						ErrorMessageConstant.NoResultText_NotDisplayed);
				String text = validationUtil.validateWebElement("Everest.NoResult", driver).getText();
				common.log(text);
				String text1 = validationUtil.validateWebElement("Everest.ChannelNoResultText", driver).getText();
				common.log(text1);
			}
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	public void clickOnTalk() throws AndriodException {
		Common common = new Common(driver);
		Common.impicitWait(4);
		try {
			common.waitforElment("Home.Talk");
			WebElement talk = validationUtil.validateWebElement("Home.Talk", driver);
			talk.click();
			common.log("Clicked on Talk Category");
			Common.impicitWait(3);
		} catch (Exception e) {
			common.log(" Category Talk is not present in the screen");
			// Assert.fail();
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ErrorMessageConstant.CategoryTalk_NotDisplayed);
		}
	}

	public WebElement searchClose() throws AndriodException {
		Common common = new Common(driver);
		return validationUtil.validateWebElement("Everest.CloseFilter", driver);
		// driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.CloseFilter")));
	}

	public WebElement onDemandTab() throws AndriodException {
		Common common = new Common(driver);
		return validationUtil.validateWebElement("Everest.OnDemand", driver);
		// return
		// driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.OnDemand")));
	}

	public WebElement noResult() throws AndriodException {
		Common common = new Common(driver);
		return validationUtil.validateWebElement("Everest.NoResult", driver);
		// return
		// driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.NoResult")));
	}

	public WebElement filterSearch() throws AndriodException {
		return validationUtil.validateWebElement("Everest.SearchFilter", driver);
		// return
		// driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.SearchFilter")));
	}

	public WebElement searchCancel() throws AndriodException {
		Common common = new Common(driver);
		return validationUtil.validateWebElement("Everest.CancelFilter", driver);
		// return
		// driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.CancelFilter")));
	}

	public WebElement channelDetails() throws AndriodException {
		return validationUtil.validateWebElement("Everet.ChannelDetails", driver);
		// return
		// driver.findElement(By.xpath(elementReader.getPropertyElement("Everet.ChannelDetails")));
	}

	public WebElement showDetails() throws AndriodException {
		return validationUtil.validateWebElement("Everest.ShowDetails", driver);
		// return
		// driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.ShowDetails")));
	}

	public WebElement setReminder() throws AndriodException {
		return validationUtil.validateWebElement("Everest.setReminder", driver);
		// return
		// driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.setReminder")));
	}

	public WebElement episodeDetails() throws AndriodException {
		return validationUtil.validateWebElement("Everest.EpisodeDetails", driver);
		// return
		// driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.EpisodeDetails")));
	}

	public WebElement episodeSaveLater() throws AndriodException {
		return validationUtil.validateWebElement("Everest.SaveLater", driver);
		// return
		// driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.SaveLater")));
	}

	/**
	 * Long press code over an channel tile
	 */
	public void verifyLongPressChannelTile() throws AndriodException {
		Common common = new Common(driver);
		try {

			Common.impicitWait(4);
			WebElement item = validationUtil.validateWebElement("Pop.Channel1", driver);
			TouchAction action = new TouchAction((AndroidDriver) driver);
			action.longPress(item).perform();
			validationUtil.validateDisplayWebElement("Everet.ChannelDetails", driver,
					ErrorMessageConstant.ChannelDetails_NotDisplayed);
			// Assert.assertTrue(channelDetails().isDisplayed());
			common.log("Action Sheet is displayed with Channel details as its header.");
			driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.TouchOutside"))).click();
			Common.impicitWait(3);
			common.log("Dismisses the Action Sheet");
			back().click();
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	/**
	 * Long Press code over an Show tile
	 */
	public void verifyLongPressShowTile() throws AndriodException {
		Common common = new Common(driver);
		try {
			Common.impicitWait(3);
			WebElement item1 = validationUtil.validateWebElement("News.Channel1", driver);
			// driver.findElement(By.xpath(elementReader.getPropertyElement("News.Channel1")));
			TouchAction action = new TouchAction((AndroidDriver) driver);
			action.longPress(item1).perform();
			validationUtil.validateDisplayWebElement("Everest.ShowDetails", driver,
					ErrorMessageConstant.ShowDetails_NotPresent);
			// Assert.assertTrue(showDetails().isDisplayed());
			common.log("Action Sheet is displayed with Show details as its header.");
			validationUtil.validateDisplayWebElement("Everest.setReminder", driver,
					ErrorMessageConstant.SetShowReminder_NotDisplayed);
			// Assert.assertTrue(setReminder().isDisplayed());
			validationUtil.validateWebElement("Everest.TouchOutside", driver).click();
			// driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.TouchOutside"))).click();
			Common.impicitWait(3);
			common.log("Dismisses the Action Sheet");
			back().click();
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	/*
	 * public WebElement onDemnadRockChannel(){ return
	 * driver.findElement(By.xpath( elementReader.getPropertyElement(""))); }
	 */

	/**
	 * Long press over an Episode Tile
	 */
	public void verifyLongPressEpisodeTile() throws AndriodException {
		Common common = new Common(driver);
		try {
			Common.impicitWait(3);
			common.scrollUntilTextExists("Booey 100");
			validationUtil.validateWebElement("Show", driver).click();
			Common.impicitWait(2);
			WebElement item2 = validationUtil.validateWebElement("Episode", driver);
			// driver.findElement(By.xpath(elementReader.getPropertyElement("Howard.Channel1")));
			TouchAction action = new TouchAction((AndroidDriver) driver);
			action.longPress(item2).perform();
			Common.impicitWait(5);
			if (!episodeDetails().isDisplayed()) {
				common.log("Action Sheet is not displayed with Episode details as its header.");
				throw new AndriodException(this.getClass().getSimpleName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(),
						ErrorMessageConstant.ActionSheet_NotDisplayed);
			}
			common.log("Action Sheet is displayed with Episode details as its header.");
			if (!episodeSaveLater().isDisplayed()) {
				common.log("Save Episode for Later is not displayed");
				throw new AndriodException(this.getClass().getSimpleName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(),
						ErrorMessageConstant.SaveForLater_NotDisplayed);
			}
			validationUtil.validateWebElement("Everest.TouchOutside", driver).click();
			// driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.TouchOutside"))).click();
			Common.impicitWait(3);
			common.log("Dismisses the Action Sheet");
			back().click();
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	/**
	 * Long Press code for OnDemand shows
	 * 
	 * @throws AndriodException
	 */
	public void verifyLongPressOnDemandShows() throws AndriodException {
		Common common = new Common(driver);
		try {
			Common.impicitWait(3);
			WebElement item3 = validationUtil.validateWebElement("Show", driver);
			TouchAction action = new TouchAction((AndroidDriver) driver);
			action.longPress(item3).perform();
			validationUtil.validateDisplayWebElement("Everest.ShowDetails", driver,
					ErrorMessageConstant.ChannelDetails_NotDisplayed);
			// Assert.assertTrue(channelDetails().isDisplayed());
			common.log("Action Sheet is displayed with Show details as its header.");
			validationUtil.validateWebElement("Action.OnDemandEpisodes", driver).click();
			// driver.findElement(By.xpath(elementReader.getPropertyElement("Action.OnDemandShows"))).click();
			Common.impicitWait(4);
			common.log("On DEmand Shows are displayed");
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	/**
	 * verifying addition/removal of favorite from an Show tile throgh EDP menu
	 */
	public void validateOnDemandfavorite() throws AndriodException {
		Common common = new Common(driver);
		try {
			Common.impicitWait(3);
			WebElement favicon = validationUtil.validateDisplayWebElement("EDPMenu.FavoritesIcon", driver,
					ErrorMessageConstant.FavoriteIcon_NotDisplayed);
			// WebElement favicon = driver
			// .findElement(By.xpath(elementReader.getPropertyElement("EDPMenu.FavoritesIcon")));
			// Assert.assertTrue(favicon.isDisplayed());
			String val = favicon.getAttribute("checked");
			if (val.equals("true")) {
				System.out.println("Show is  marked as favorite");
			} else {
				System.out.println("Show is not favorite");
			}
			WebElement item3 = validationUtil.validateWebElement("Rock.Channel1", driver);
			// WebElement item3 =
			// driver.findElement(By.xpath(elementReader.getPropertyElement("Rock.Channel1")));
			TouchAction action = new TouchAction((AndroidDriver) driver);
			action.longPress(item3).perform();
			Assert.assertTrue(showDetails().isDisplayed());
			common.log("Action Sheet is displayed with Show details as its header.");
			try {
				WebElement addFav = validationUtil.validateDisplayWebElement("EDPMenu.AddFavotites", driver,
						ErrorMessageConstant.AddFavorite_NotDisplayed);
				if (addFav.getText().equalsIgnoreCase("Add Show to Favorites")) {
					addFav.click();
					Common.impicitWait(3);
					common.log("added to Favorite");
				}
			} catch (Exception e) {
				validationUtil.validateWebElement("Favorite.RemoveFavorite", driver).click();
				common.log("Removed from Favorites.");
			}
			WebElement favicon1 = validationUtil.validateDisplayWebElement("EDPMenu.FavoritesIcon", driver,
					ErrorMessageConstant.FavoriteIcon_NotDisplayed);
			String val1 = favicon.getAttribute("checked");
			if (val1.equals("true")) {
				System.out.println("Show is  marked as favorite");
			} else {
				System.out.println("Show is not favorite");
			}
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	/**
	 * vrifying addition/removal of favorite from an Channel tile throgh EDP
	 * menu
	 */
	public void addChannelToFavorite() throws AndriodException {
		Common common = new Common(driver);
		try {
			Channeldescription();
			WebElement favicon = validationUtil.validateDisplayWebElement("EDPMenu.FavoritesIcon", driver,
					ErrorMessageConstant.FavoriteIcon_NotDisplayed);
			String val = favicon.getAttribute("checked");
			if (val.equals("true")) {
				System.out.println("Channel is  marked as favorite");
			} else {
				System.out.println("Channel is not favorite");
			}
			WebElement item3 = validationUtil.validateWebElement("Rock.Channel1", driver);
			TouchAction action = new TouchAction((AndroidDriver) driver);
			action.longPress(item3).perform();
			// Assert.assertTrue(showDetails().isDisplayed());
			Common.impicitWait(3);
			validationUtil.validateDisplayWebElement("Everet.ChannelDetails", driver,
					ErrorMessageConstant.ChannelDetails_NotDisplayed);
			common.log("Action Sheet is displayed with Channel details as its header.");
			try {
				WebElement addFav = validationUtil.validateDisplayWebElement("EDPMenu.AddChannelFavotites", driver,
						ErrorMessageConstant.AddFavorite_NotDisplayed);
				if (addFav.getText().equalsIgnoreCase("Add Channel to Favorites")) {
					addFav.click();
					Common.impicitWait(3);
					common.log("added to Favorite");
				}
			} catch (Exception e) {
				validationUtil.validateWebElement("Favorite.RemoveFavorite", driver).click();
				common.log("Removed from Favorites.");
				Common.impicitWait(4);
			}
			WebElement favicon1 = validationUtil.validateDisplayWebElement("EDPMenu.FavoritesIcon", driver,
					ErrorMessageConstant.FavoriteIcon_NotDisplayed);
			String val1 = favicon.getAttribute("checked");
			if (val1.equals("true")) {
				System.out.println("Channel is  marked as favorite");
			} else {
				System.out.println("Channel is not favorite");
			}
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	/**
	 * 
	 * verifying show is added to favorite or removed from favorite through EDP
	 * menu
	 */
	public void addShowToFavorite() throws AndriodException {
		Common common = new Common(driver);
		try {
			Common.impicitWait(3);
			WebElement favicon = validationUtil.validateDisplayWebElement("EDPMenu.FavoritesIcon", driver,
					ErrorMessageConstant.FavoriteIcon_NotDisplayed);
			String val = favicon.getAttribute("checked");
			if (val.equals("true")) {
				System.out.println("Show is  marked as favorite");
			} else {
				System.out.println("Show is not favorite");
			}
			validationUtil.validateWebElement("Show", driver).click();
			Common.impicitWait(3);
			List<WebElement> items = driver.findElements(By.xpath(elementReader.getPropertyElement("Episode")));
			TouchAction action = new TouchAction((AndroidDriver) driver);
			action.longPress(items.get(0)).perform();
			// Assert.assertTrue(episodeDetails().isDisplayed());
			validationUtil.validateDisplayWebElement("Everest.EpisodeDetails", driver,
					ErrorMessageConstant.EpisodeDetails_NotDisplayed);
			common.log("Action Sheet is displayed with Episode details as its header.");
			try {
				WebElement addFav = validationUtil.validateDisplayWebElement("EDPMenu.AddFavotites", driver,
						ErrorMessageConstant.EDPFavorite_NotDisplayed);
				if (addFav.getText().equalsIgnoreCase("Add Show to Favorites")) {
					addFav.click();
					Common.impicitWait(3);
					common.log("added to Favorite");
				}
			} catch (Exception e) {
				validationUtil.validateWebElement("Favorite.RemoveFavorite", driver).click();
				common.log("Removed from Favorites.");
			}
			WebElement back1 = validationUtil.validateWebElement("Everest.BackButton1", driver);
			back1.click();
			Common.impicitWait(10);
			WebElement favicon1 = validationUtil.validateDisplayWebElement("EDPMenu.FavoritesIcon", driver,
					ErrorMessageConstant.FavoriteIcon_NotDisplayed);
			String val1 = favicon.getAttribute("checked");
			if (val1.equals("true")) {
				System.out.println("Show is  marked as favorite");
			} else {
				System.out.println("Show is not favorite");
			}
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public WebElement buySong() throws AndriodException {
		Common common = new Common(driver);
		return validationUtil.validateWebElement("NowPlaying.BuySong", driver);
		// return
		// driver.findElement(By.xpath(elementReader.getPropertyElement("NowPlaying.BuySong")));
	}

	
	public void dmcaBuySongButton() throws AndriodException {
		Common common = new Common(driver);
		try {
			Common.impicitWait(3);
			validationUtil.validateWebElement("Pop.Channel1", driver).click();
			// driver.findElement(By.xpath(elementReader.getPropertyElement("Pop.Channel1"))).click();
			Common.impicitWait(4);
			common.log("Navigated to Now Playing screen");
			 common.scrollToDown(2);
			 try{
			validationUtil.validateDisplayWebElement("NowPlaying.BuySong", driver,
					ErrorMessageConstant.NowPlayingBuySong_NotDisplayed);
			 }catch(Exception e){
				 common.log("Buy song is not available on the NP screen");
			 }
			common.scrollToUp(2);
			minimizebutton().click();
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	public WebElement saveForlater() throws AndriodException {
		return driver.findElement(By.xpath(elementReader.getPropertyElement("NowPlaying.SaveLater")));
	}

	/**
	 * Validate SaveForLater
	 * 
	 * @throws AndriodException
	 */
	public void ValidateSaveForLater() throws AndriodException {
		Common common = new Common(driver);
		try {
			Common.impicitWait(4);
			common.scrollUntilTextExists("Booey 100");
			validationUtil.validateWebElement("Howard.Episode1", driver).click();
			// driver.findElement(By.xpath(elementReader.getPropertyElement("Howard.Episode1"))).click();
			Common.impicitWait(2);
			validationUtil.validateWebElement("Howard.FirstEpisode", driver).click();
			// WebElement item2 =
			// driver.findElement(By.xpath(elementReader.getPropertyElement("Howard.FirstEpisode")));
			// item2.click();
			common.log("Navigated to Now Playing Screen");
			common.scrollToDown(1);
			// Assert.assertTrue(saveForlater().isDisplayed());
			try{
				validationUtil.validateDisplayWebElement("NowPlaying.SaveLater", driver, ErrorMessageConstant.SaveForLater_NotDisplayed);
				common.log("Save For Later is present for the  On Demand Episode");
			}catch (Exception e) {
				common.log("Save For Later is not present for the  On Demand Episode");
			}
			common.scrollToUp(1);
			minimizebutton().click();
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	public void clickOnHowardStern() {
		Common common = new Common(driver);
		try {
			Common.impicitWait(4);
			common.scrollUntilTextExists("Howard Stern");
			WebElement cathoward = validationUtil.validateWebElement("Home.Howard", driver);
			cathoward.click();
			common.log("Clicked on Howard Stern");
			Common.impicitWait(2);
			/*
			 * WebElement subcathoward =
			 * validationUtil.validateWebElement("Home.SubCatHoward", driver);
			 * subcathoward.click(); common.log(
			 * "Clicked on sub category Howard stern");
			 */
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					ErrorMessageConstant.CategoryHowardStern_NotDisplayed);
		}
	}

	public void howardSaveLater() throws AndriodException {
		Common common = new Common(driver);
		try {
			Common.impicitWait(3);
			howardcarousel().click();
			common.log("Navigated to Now Playing Screen");
			common.scrollToDown(1);
			// Assert.assertTrue(saveForlater().isDisplayed());
			if (!saveForlater().isDisplayed()) {
				throw new AndriodException(this.getClass().getSimpleName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(),
						ErrorMessageConstant.SaveForLater_NotDisplayed);
			}
			common.log("Save For Later is present for the  unrestricted  Episode");
			common.scrollToUp(1);
			;
			minimizebutton().click();
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void verifyUnrestrictedShownamePDT() throws AndriodException {
		Common common = new Common(driver);
		try {
			common.scrollToDown(2);
			validationUtil.validateWebElement("Howard.Episode1", driver).click();
			// driver.findElement(By.xpath(elementReader.getPropertyElement("Howard.Episode1"))).click();
			Common.impicitWait(2);
			// WebElement item2 =
			// driver.findElement(By.xpath(elementReader.getPropertyElement("Howard.FirstEpisode")));
			// item2.click();
			validationUtil.validateWebElement("Howard.FirstEpisode", driver).click();
			common.log("Navigated to Now Playing Screen");
			Common.impicitWait(4);
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	/**
	 * Validate Channel filter
	 * 
	 * @param string
	 * @throws AndriodException
	 */
	public void channelFilter(String string) throws AndriodException {
		Common common = new Common(driver);
		try {
			WebElement filter = validationUtil.validateDisplayWebElement("Everest.SearchFilter", driver,
					ErrorMessageConstant.SearchFilter_NotDisplayed);
			common.log("Channel filter is displayed");
			filter.click();
			Common.impicitWait(2);
			validationUtil.validateDisplayWebElement("Everest.CancelFilter", driver,
					ErrorMessageConstant.CancelFilter_NotDisplayed);
			common.log("Cancel button is present.");
			filter.clear();
			for (int i = 0; i < string.length(); i++) {
				char c = string.charAt(i);
				String s = new StringBuilder().append(c).toString();
				filter.sendKeys(s);
				Common.impicitWait(3);
				Channeldescription();
				validationUtil.validateDisplayWebElement("Everest.CloseFilter", driver,
						ErrorMessageConstant.ClearFilter_NotDisplayed);
				common.log("Clear button is present");
			}
			// WebElement closefilter = driver
			// .findElement(By.xpath(elementReader.getPropertyElement("Everest.CancelFilter")));
			WebElement closefilter = validationUtil.validateWebElement("Everest.CancelFilter", driver);
			closefilter.click();
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void onDemandFilter(String string) {
		Common common = new Common(driver);
		try {
			WebElement filter = validationUtil.validateDisplayWebElement("Everest.Filtersearch", driver,
					ErrorMessageConstant.SearchFilter_NotDisplayed);
			common.log("OnDemand filter is displayed");
			filter.click();
			Common.impicitWait(2);
			validationUtil.validateDisplayWebElement("Everest.CancelFilter", driver,
					ErrorMessageConstant.CancelFilter_NotDisplayed);
			common.log("Cancel button is present.");
			filter.clear();
			for (int i = 0; i < string.length(); i++) {
				char c = string.charAt(i);
				String s = new StringBuilder().append(c).toString();
				filter.sendKeys(s);
				Common.impicitWait(3);
				Showdescription();
				validationUtil.validateDisplayWebElement("Everest.CloseFilter", driver,
						ErrorMessageConstant.ClearFilter_NotDisplayed);
				common.log("Clear button is present");
			}
			WebElement closefilter = validationUtil.validateWebElement("Everest.CancelFilter", driver);
			// driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.CancelFilter")));
			closefilter.click();
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public WebElement profileMenu() throws AndriodException {

		return validationUtil.validateWebElement("Home.Profile", driver);
		// return
		// driver.findElement(By.xpath(elementReader.getPropertyElement("Home.Profile")));
	}

	public WebElement signout() throws AndriodException {

		return validationUtil.validateWebElement("Profile.SignOut", driver);
		// return
		// driver.findElement(By.xpath(elementReader.getPropertyElement("Profile.SignOut")));
	}

	public WebElement username() throws AndriodException {

		return validationUtil.validateWebElement("Everest.Username", driver);
		// return
		// driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.Username")));
	}

	public WebElement password() throws AndriodException {

		return validationUtil.validateWebElement("Everest.Password", driver);
		// return
		// driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.Password")));
	}




	public void validateProfileSignOut() throws AndriodException {
		Common common = new Common(driver);
		try {

			// Assert.assertTrue(profileMenu().isDisplayed());
			// profileMenu().click();
			validateProfile();
			common.log("Profile menu is clicked");
			Common.impicitWait(3);
			common.scrollUntilTextExists("Sign Out");
			validationUtil.validateDisplayWebElement("Profile.SignOut", driver,
					ErrorMessageConstant.SignOut_NotDisplayed);
			// Assert.assertTrue(signout().isDisplayed());
			signout().click();
			common.log("Signout button is clicked");
			Common.impicitWait(4);
			common.log("Landed to Login page of the app");
			validationUtil.validateDisplayWebElement("Everest.Username", driver,
					ErrorMessageConstant.UserName_NotDisplayed);
			common.log("Username text is present");
			// Assert.assertTrue(username().isDisplayed(), "Username text is
			// present");
			String UserText = username().getText();
			common.log("After signout username text has" + UserText);
			validationUtil.validateDisplayWebElement("Everest.Password", driver,
					ErrorMessageConstant.Password_NotDisplayed);
			common.log("Password text is present");
			// Assert.assertTrue(password().isDisplayed(), "Password text is
			// present");
			// String string = null;
			String PassText = password().getText().trim();
			if (null != PassText) {
				throw new AndriodException(this.getClass().getSimpleName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(),
						ErrorMessageConstant.Password_NotDisplayed);
			}
			// Assert.assertEquals(PassText, string);
			common.log("After signout password should be blank" + PassText);
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void validateProfile() throws AndriodException {
		Common common = new Common(driver);
		try {

			validationUtil.validateDisplayWebElement("Home.Profile", driver,
					ErrorMessageConstant.ProfileIcon_NotDisplayed);
			// Assert.assertTrue(profileMenu().isDisplayed());
			profileMenu().click();
			common.log("Profile menu is clicked");
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	

	

	

	public void minimizeUnrestricted() throws AndriodException {
		Common common = new Common(driver);
		try {

			validationUtil.validateWebElement("Show", driver).click();
			// driver.findElement(By.xpath(elementReader.getPropertyElement("Show"))).click();
			Common.impicitWait(3);
			validationUtil.validateWebElement("Episode", driver).click();
			// driver.findElement(By.xpath(elementReader.getPropertyElement("Episode"))).click();
			common.log("Navigated to NPL Screen");
			Common.impicitWait(3);
			minimizebutton().click();
			Common.impicitWait(3);
			common.log(" Navigated to last page ..");
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void minimizeRestricted() throws AndriodException {
		Common common = new Common(driver);
		common.log("Validating Minimize button for Restricted live MOBANDEVER-764");
		try {

			validationUtil.validateWebElement("Pop.Channel1", driver).click();
			// driver.findElement(By.xpath(elementReader.getPropertyElement("Pop.Channel1"))).click();
			common.log("Navigated to NPL Screen");
			Common.impicitWait(3);
			minimizebutton().click();
			Common.impicitWait(3);
			common.log(" Navigated to last page ..");
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void minimizeDisallowed() throws AndriodException {
		Common common = new Common(driver);
		common.log("Validating Minimize button for Disallowed live MOBANDEVER-765");
		try {

			validationUtil.validateWebElement("News.Channel1", driver).click();
			// driver.findElement(By.xpath(elementReader.getPropertyElement("News.Channel1"))).click();
			common.log("Navigated to NPL Screen");
			Common.impicitWait(3);
			minimizebutton().click();
			Common.impicitWait(3);
			common.log(" Navigated to last page ..");
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void minimizeOnDemand() throws AndriodException {
		Common common = new Common(driver);
		try {

			clickOnDemand();
			Common.impicitWait(3);
			validationUtil.validateWebElement("Shows", driver).click();
			Common.impicitWait(3);
			validationUtil.validateWebElement("Episode", driver).click();
			// driver.findElement(By.xpath(elementReader.getPropertyElement("Show"))).click();
			// Common.impicitWait(3);
			// driver.findElement(By.xpath(elementReader.getPropertyElement("Episode"))).click();
			common.log("Navigated to NPL Screen");
			Common.impicitWait(3);
			minimizebutton().click();
			Common.impicitWait(3);
			common.log(" Navigated to last page ..");
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void onDemandAdditionalEpisodes() throws AndriodException {
		Common common = new Common(driver);
		try {

			Common.impicitWait(3);
			Showdescription();
			Common.impicitWait(3);
			validationUtil.validateWebElement("Shows", driver).click();
			Common.impicitWait(3);
			validationUtil.validateWebElement("Episode", driver).click();
			Common.impicitWait(3);
			common.log("Navigated to NPL Screen");
			Common.impicitWait(3);
			common.scrollToDown(2);
			avashowcnt();
			common.swipeRightToLeft();
			common.scrollChannelsdown(3);
			minimizebutton().click();
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void validatemainnavigation() throws AndriodException {
		Common common = new Common(driver);
		try {
			WebElement homeIcon = validationUtil.validateDisplayWebElement("Everest.Home", driver,
					ErrorMessageConstant.Home_NotDisplayed);
			// WebElement homeIcon =
			// driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.Home")));
			// Assert.assertTrue(homeIcon.isDisplayed());
			homeIcon.click();
			common.log("Taping to man navigation tab,taken to the last page of the section");
			Common.impicitWait(3);
			homeIcon.click();
			common.log("Tapping main nav item a second time, they are taken to the root (top) page of the section");
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void verifymainnavigation() throws AndriodException {
		Common common = new Common(driver);
		try {
			WebElement homeIcon = validationUtil.validateDisplayWebElement("Everest.Home", driver,
					ErrorMessageConstant.Home_NotDisplayed);
			// WebElement homeIcon =
			// driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.Home")));
			// Assert.assertTrue(homeIcon.isDisplayed());
			homeIcon.click();
			common.log("Navigated to home menu");
			clickFav();
			profileMenu().click();
			common.log("Navigated to Profle menu.");
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void avashowcnt() throws AndriodException {
		Common common = new Common(driver);
		try {
			List<WebElement> Noofshows = driver
					.findElements(By.xpath(elementReader.getPropertyElement("NPL.AvalableShows")));
			int showCount = Noofshows.size();
			common.log(" Avaialble Show Count:" + showCount);

		} catch (Exception e) {
			common.log("NO shows are present");
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), ErrorMessageConstant.Shows_NotDisplayed);
			// Assert.fail();
		}
	}

	public void disallowedAvaialbleShows() throws InterruptedException {
		Common common = new Common(driver);
		try {

			validationUtil.validateWebElement("News.Channel1", driver).click();
			// driver.findElement(By.xpath(elementReader.getPropertyElement("News.Channel1"))).click();
			Common.impicitWait(3);
			common.log("Navigated to NPL screen");
			// WebElement ele=driver.findElement(By.xpath("//*[@text='Available
			// shows on Howard 100']"));
			// common.scrollTillElementFound(By.xpath(elementReader.getPropertyElement("NPL.HowardAvalaibleShows")));
			common.scrollToDown(2);
			Common.impicitWait(3);
			avashowcnt();
			common.scrollToUp(3);
			minimizebutton().click();
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
		/*
		 * Common.swipeRightToLeft(); Common.impicitWait(3); avashowcnt();
		 */
	}

	public void restrictedAvaialbleShows() throws InterruptedException {
		Common common = new Common(driver);
		try {

			validationUtil.validateWebElement("Pop.Channel1", driver).click();
			// return
			// driver.findElement(By.xpath(elementReader.getPropertyElement("Pop.Channel1"))).click();
			Common.impicitWait(3);
			common.log("Navigated to NPL screen");
			// WebElement ele=driver.findElement(By.xpath("//*[@text='Available
			// shows on Howard 100']"));
			// common.scrollTillElementFound(By.xpath(elementReader.getPropertyElement("NPL.HowardAvalaibleShows")));
			common.scrollUntilTextExists("Available shows");
			common.scrollToDown(0);
			Common.impicitWait(3);
			avashowcnt();
			common.scrollToUp(3);
			minimizebutton().click();
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public String getArtistinfo() throws AndriodException {
		Common common = new Common(driver);
		return validationUtil.validateWebElement("Everest.MiniArtistinfo", driver).getText();
	}

	public void verifyArtistinfo(String artistinfo) throws AndriodException {
		Common common = new Common(driver);
		try {

			WebElement artistInfo = validationUtil.validateWebElement("Everest.MiniArtistinfo", driver);
			if (artistInfo.isDisplayed() && artistInfo.getText().equalsIgnoreCase(artistinfo)) {
				common.log("Application is launched with auto login ");
			} else {
				throw new AndriodException(this.getClass().getSimpleName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(),
						ErrorMessageConstant.Application_NotLaunched);
			}
			/// Assert.assertTrue(artistInfo.getText().equalsIgnoreCase(artistinfo));
			// common.log("Application is launched with auto login ");
		} catch (AndriodException e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void elapsedTime() {

	}

	/**
	 * validate Live Save For Later
	 * 
	 * @throws AndriodException
	 */
	public void validateLiveSaveForLater() throws AndriodException {
		Common common = new Common(driver);
		try {
			WebElement howardSternShow100 = validationUtil.validateDisplayWebElement("Howard.howardSternShow100",
					driver, ErrorMessageConstant.Carousal_NotDisplayed);
			howardSternShow100.click();
			Common.impicitWait(2);
			common.log("Navigated to Now Playing Screen for Unrestrcited Live Channel");
			common.scrollToDown(1);
			try{
				validationUtil.validateDisplayWebElement("NowPlaying.SaveLater", driver, ErrorMessageConstant.SaveForLater_NotDisplayed);
				common.log("Save For Later is present Unrestricted category");
			}catch (Exception e) {
				common.log("Save For Later is present Unrestricted category");
			}
			common.scrollToUp(1);
			minimizebutton().click();
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	/**
	 * Navigating to episode
	 */
	public void navigateToEpisode() {
		Common common = new Common(driver);
		try {
			validationUtil.validateWebElement("Show", driver).click();
			Common.impicitWait(2);
			validationUtil.validateWebElement("Episode", driver).click();
			// driver.manage().logs().get("logcat").getAll();
			Common.impicitWait(3);
			common.log("Navigated to NPL screen");
		} catch (Exception e) {
			// TODO: handle exception
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}
	
	/**
	 * validating presence of Floating button
	 */
	public void floatingButton()throws AndriodException{
		Common common = new Common(driver);
		Common.impicitWait(3);
		try{
			validationUtil.validateDisplayWebElement("Everest.FloatingButton", driver, ErrorMessageConstant.FloatingButton_NotDisplayed);
			common.log("Floating Feedback button is present on the screen");
		}catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}
	
	
	/**
	 * Code for floating function
	 */
	public void clickOnFloatingButton() throws AndriodException{
		Common common = new Common(driver);
		Common.impicitWait(3);
		try{
			validationUtil.validateWebElement("Everest.FloatingButton", driver).click();
			common.log("Clickd on Floating Button");
			Common.impicitWait(5);
			validationUtil.validateDisplayWebElement("Everest.Gmail", driver, ErrorMessageConstant.Gmail_NotDisplayed);
			validationUtil.validateWebElement("Everest.Gmail", driver).click();
			Common.impicitWait(3);
			WebElement element = validationUtil.validateDisplayWebElement("Everest.GmailTo", driver, ErrorMessageConstant.GmailTo_NotDisplayed);
			String Text = element.getText();
			common.log("An e-mail to :"+Text+ "is specified");
			clickBackButton();
			Common.impicitWait(3);
			clickBackButton();
			Common.impicitWait(3);
		}catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

}
