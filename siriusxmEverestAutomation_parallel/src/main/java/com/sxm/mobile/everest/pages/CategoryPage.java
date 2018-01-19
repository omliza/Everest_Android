/**
 * 
 */
package com.sxm.mobile.everest.pages;

import java.sql.DriverAction;
import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import com.sxm.framework.dto.handler.EnvirnomentHandler;
import com.sxm.framework.exception.AndriodException;
import com.sxm.framework.utility.PropertyElementReader;
import com.sxm.mobile.pages.Common;
import com.sxm.mobile.pages.ErrorMessageConstant;
import com.sxm.mobile.pages.ValidationUtil;

import io.appium.java_client.AppiumDriver;

/**
 * @author sxm2
 *
 */
public class CategoryPage {
	private final static Logger LOGGER = Logger.getLogger(CategoryPage.class.getName());
	private AppiumDriver<WebElement> driver;
	private String platform = EnvirnomentHandler.getInstance().getEnvirnoment()
			.getChannel();
	Boolean flag = false;
	int channelcount = 0;
	int showCount=0;
	ValidationUtil validationUtil = new ValidationUtil();
	Common common = new Common(driver);
	public CategoryPage(AppiumDriver driver) {
		super();
		this.driver = driver;
		
	}
	private String locale = EnvirnomentHandler.getInstance().getEnvirnoment()
			.getLocale();
	private PropertyElementReader elementReader = PropertyElementReader
			.getInstance(locale);
	
	
	public void OnDemandshowListing()throws AndriodException{
		Common common = new Common(driver);
		try{
		Common.impicitWait(2);
		common.scrollToDown(1);
		Showdescription();
			} catch (Exception e) {
		throw new AndriodException(this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName(),e.getMessage());
	}
}
	
	
	public void Showdescription() throws AndriodException{
		try {
		ValidationUtil validationUtil = new ValidationUtil();
		List<WebElement> Noofshows = driver
		.findElements(By.xpath(elementReader.getPropertyElement("Everest.Showcount")));
		if (null != Noofshows && Noofshows.size() > 0) {
		Common.log("Show Count:" + Noofshows.size());
		Common.log("Shows are present.");
		} else {
		Common.log("Shows are not present.");
		WebElement noResult = validationUtil.validateDisplayWebElement("Everest.NoResult", driver, "No result is  not present"); 
		String text = noResult.getText();
		Common.log(text);
		String text1 = validationUtil.validateDisplayWebElement("Everest.ShowNoResultText", driver, "No result text is  not present").getText();
		Common.log(text1);
		}
		} catch (Exception e) {
		throw new AndriodException(this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), 
		e.getMessage());

		}
		}

	
		public WebElement recentView() {
			return validationUtil.validateWebElement("Episode.RecentView", driver);
		}
		

		public void recentBanner()throws AndriodException {
			Common common = new Common(driver);
			try{
			ValidationUtil validationUtil = new ValidationUtil();
			Common.impicitWait(3);
			validationUtil.validateWebElement("Show", driver).click();
			//clickOnEpisode();
			Common.impicitWait(4);
			validationUtil.validateDisplayWebElement("Episode.RecentView", driver, "Recent Banner  is not displayed on the screen");
			Common.log("Recent Banner displayed on the screen :" + recentView().getText());
		}catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName(),e.getMessage());
		}
	}
		
		public void episodeListingBackButton()throws AndriodException{
			Common common = new Common(driver);
			try{
			ValidationUtil validationUtil = new ValidationUtil();
			//common.scrollToDown(1);
			Showdescription();
			Common.impicitWait(3);
			validationUtil.validateWebElement("Show", driver).click();
			Common.impicitWait(3);
			validationUtil.validateWebElement("Everest.NavigateBack", driver).click();
			Common.impicitWait(2);
			} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName(),e.getMessage());

		}
		
	}

		public void disallowedOnDemandShowArt()throws AndriodException{
			Common common = new Common(driver);
			try{
			//Common.scrollToDown(1);
			validationUtil.validateWebElement("Show", driver).click();
			Common.impicitWait(3);
			validationUtil.validateWebElement("Episode", driver).click();
			Common.log("Navigated to NPL screen");
			nowPlayingArt();
			validationUtil.validateWebElement("Everest.Minimizebutton", driver).click();
			} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName(),e.getMessage());
		}
	}
		
		public void nowPlayingArt() {
			Common common = new Common(driver);
			try {
				validationUtil.validateDisplayWebElement("NPL.PlayingArt", driver, "Now playing art is not displayed"); 
				Common.log("Now Playing Art is present on the NPL screen");
				// driver.findElement(By.xpath("//*[@resource-id='com.sirius.dev:id/now_playing_art']")).isDisplayed();
			} catch (Exception e) {
				Common.log("Now Playing Art is not present on the NPL screen");
			}
		}
		public void disallowedShowArt()throws AndriodException{
			Common common = new Common(driver);
			try {
				Common.impicitWait(2);
				validationUtil.validateWebElement("News.Channel1", driver).click();
				Common.impicitWait(4);
				nowPlayingArt();
				validationUtil.validateWebElement("Everest.Minimizebutton", driver).click();
				Common.impicitWait(3);
			}catch (Exception ex) {
				throw new AndriodException(this.getClass().getSimpleName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(), ex.getMessage());
			}
			
		}
		
		public void nplLogo()throws AndriodException {
			Common common = new Common(driver);
			try {
				common.waitforElment("NPL.Logo");
				validationUtil.validateDisplayWebElement("NPL.Logo", driver, "Channel Logo is not displayed on the NPL page");
				Common.log("Logo is present on the NPL screen");
			} catch (Exception e) {
				throw new AndriodException(this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName(),"Show Logo is not present in the screen");
			}
		}
		
		
		public void verifyLinerTuner(){
			Common common = new Common(driver);
			try {
				common.implicitWait(1);
				validationUtil.validateDisplayWebElement("NPL.PreviousLinerTunerArrow", driver, ErrorMessageConstant.PreviousLinearTuner_NotDisplayed);
				validationUtil.validateDisplayWebElement("NPL.NextLinerTunerArrow", driver, ErrorMessageConstant.NextLinearTuner_NotDisplayed);
				common.log("The liner tuner arrows are displayed");	
		}catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName(),"Show Logo is not present in the screen");
		}
		}
		
		public void disallowedChannelLogo()throws AndriodException {
			Common common = new Common(driver);
			try {
				common.waitforElment("News.Channel1");
				Common.impicitWait(2);
				validationUtil.validateWebElement("News.Channel1", driver).click();
				Common.impicitWait(5);
				nowPlayingArt();
				validationUtil.validateWebElement("Everest.Minimizebutton", driver).click();
				Common.impicitWait(3);
			} catch (Exception ex) {
				Common.errorlog(ex.getMessage());
				throw new AndriodException(this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName(),ex.getMessage());
			}
		}

		public void nplChnumber()throws AndriodException {
			Common common = new Common(driver);
			try {
				validationUtil.validateDisplayWebElement("NPL.ChNumber", driver, "Channel number is not present");
				Common.log("Channel number is present on the NPL screen");
			} catch (Exception e) {
				Common.errorlog("Channel number is not present in the screen");
				// Assert.fail();
				throw new AndriodException(this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName(),e.getMessage());

			}
		}
		

		public void disallowedChannelNumber()throws AndriodException {
			Common common = new Common(driver);
			try {
				Common.impicitWait(2);
				validationUtil.validateWebElement("News.Channel1", driver).click();
				Common.impicitWait(3);
				common.waitforElment("NPL.ChNumber");
				validationUtil.validateDisplayWebElement("NPL.ChNumber", driver, "channels number is not displayed");
				validationUtil.validateWebElement("Everest.Minimizebutton", driver).click();
				Common.impicitWait(3);
			} catch (Exception ex) {
				throw new AndriodException(this.getClass().getSimpleName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(), ex.getMessage());
			}
		}

		public void nplShowName()throws AndriodException {
			Common common = new Common(driver);
			try {
				WebElement showname = validationUtil.validateDisplayWebElement("NPL.ShowName", driver,"Show name is not present in the npl page");
				Common.log("Show name is present on the NPL screen");
				Common.log("Show Name is :" + showname.getText());
			} catch (Exception e) {
				throw new AndriodException(this.getClass().getSimpleName(),
						"Show name is not present in the npl page");

			}
		}
		
		public void nplPDT()throws AndriodException {
			Common common = new Common(driver);
			try {
				WebElement showPDT = validationUtil.validateDisplayWebElement("NPL.PDT", driver, "PDT line is not present in the NPL");
				Common.log("Show PDT is present on the NPL screen");
				Common.log("Show PDT is :" + showPDT.getText());
			} catch (Exception e) {
				throw new AndriodException(Thread.currentThread().getStackTrace()[1].getMethodName(),"PDT line is not present in NPL");

			}
		}
		

		public void disallowedShowNamePDT()throws AndriodException {
			Common common = new Common(driver);
			try {
				Common.impicitWait(4);
				nplShowName();
				nplPDT();
				validationUtil.validateWebElement("Everest.Minimizebutton", driver).click();
			} catch (Exception ex) {
				throw new AndriodException(this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName(),ex.getMessage());
			}
		}
		
		public void disallowedPlayerControls()throws AndriodException {
			Common common = new Common(driver);
			try {
				common.waitforElment("NPL.PlayPause");
				WebElement element = validationUtil.validateDisplayWebElement("NPL.PlayPause", driver, "Play/Puse button is not displayed");
				element.click();
				Common.impicitWait(8);
				Common.log("Clicked on play/pause icon.. music is paused");
				element.click();
				Common.impicitWait(4);
				Common.log("Clicked on play/pause icon.. music is played");
				validationUtil.validateWebElement("Everest.Minimizebutton", driver).click();
			} catch (Exception e) {
				// TODO: handle exception
				throw new AndriodException(this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName(),e.getMessage());
			}
		}
		
		

		public void validateOndemanddisallowed()throws AndriodException {
			Common common = new Common(driver);
			try {
				Common.impicitWait(3);
				WebElement newsShow = validationUtil.validateDisplayWebElement("Show", driver, "Expected Show is not displaying");
				newsShow.click();
				Common.impicitWait(4);
				validationUtil.validateWebElement("Episode", driver).click();
				Common.impicitWait(3);
			} catch (Exception e) {
				throw new AndriodException(this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(),e.getMessage());
			}

		}
		
}
