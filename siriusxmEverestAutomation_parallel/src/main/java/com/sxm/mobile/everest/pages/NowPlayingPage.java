package com.sxm.mobile.everest.pages;

import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.sxm.framework.dto.handler.EnvirnomentHandler;
import com.sxm.framework.exception.AndriodException;
import com.sxm.framework.utility.PropertyElementReader;
import com.sxm.mobile.pages.Common;
import com.sxm.mobile.pages.ErrorMessageConstant;
import com.sxm.mobile.pages.ValidationUtil;

import io.appium.java_client.AppiumDriver;

public class NowPlayingPage {
	private final static Logger LOGGER = Logger.getLogger(Home.class.getName());
	private AppiumDriver<WebElement> driver;
	private String platform = EnvirnomentHandler.getInstance().getEnvirnoment().getChannel();
	Boolean flag = false;

	public NowPlayingPage(AppiumDriver driver) {
		super();
		this.driver = driver;

	}

	private String locale = EnvirnomentHandler.getInstance().getEnvirnoment().getLocale();
	private PropertyElementReader elementReader = PropertyElementReader.getInstance(locale);

	/**
	 * code for cliking sports channel
	 */
	public void clickOnSports() throws AndriodException {
		Common common = new Common(driver);
		try {
			ValidationUtil validationUtil = new ValidationUtil();
			validationUtil.validateWebElement("Home.Sports", driver).click();
			Common.impicitWait(3);
			common.scrollUntilTextExists("NHL Play-by-Play");
			validationUtil.validateDisplayWebElement("Sports.SportsTalk", driver, "Sports Talk is not displayed")
					.click();
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void verifySportsPlayChannel() throws AndriodException {
		Common common = new Common(driver);
		try {
			ValidationUtil validationUtil = new ValidationUtil();
			// Click on channel
			validationUtil.validateWebElement("Sports.Channel1", driver).click();
			common.implicitWait(4);
			validationUtil.validateDisplayWebElement("Everest.Minimizebutton", driver,
					ErrorMessageConstant.MinimizeBtn_NotDisplayed);
			common.log("Navigated to NOW Paying Screen");
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void verifyDisallowedProgressbar() {
		Common common = new Common(driver);
		try {
			ValidationUtil validationUtil = new ValidationUtil();
			validationUtil.validateWebElement("News.Channel1", driver).click();
			Common.impicitWait(3);
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void clickDisallowedChannel() {
		Common common = new Common(driver);
		try {
			ValidationUtil validationUtil = new ValidationUtil();
			validationUtil.validateWebElement("News.Channel1", driver).click();
			Common.impicitWait(3);
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void verifyProgressbar() {
		// TODO Auto-generated method stub
		Common common = new Common(driver);
		// TODO Auto-generated method stub
		try {
			common.waitforElment("NPL.ProgressBar");
			ValidationUtil validationUtil = new ValidationUtil();
			validationUtil.validateDisplayWebElement("NPL.ProgressBar", driver, "Progress bar is not displayed");
			common.log("Progress bar is displayed");
			validationUtil.validateDisplayWebElement("NPL.StartTime", driver,
					ErrorMessageConstant.StartTime_NotDisplayed);
			validationUtil.validateDisplayWebElement("NPL.EndTime", driver, ErrorMessageConstant.EndTime_NotDisplayed);
			String starttime = validationUtil.validateWebElement("NPL.StartTime", driver).getText();
			common.log("Start time" + starttime);
			String endtime = validationUtil.validateWebElement("NPL.EndTime", driver).getText();
			common.log("End Time=" + endtime);
			common.log("Time is displayed in the format X:XX am/pm");
		} catch (Exception e) {
			// TODO: handle exception
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}
	
	

	/**
	 * Forward skip counts
	 */
	public void validateSkipCount() {
		Common common = new Common(driver);
		try {
			// TODO Auto-generated method stub
			// String songbeforeclick = getSongName();
			ValidationUtil validationUtil = new ValidationUtil();
			if(validationUtil.validateWebElement("NPL.PlayPause", driver).isDisplayed()){
				validationUtil.validateWebElement("NPL.PlayPause", driver).click();
			}
			common.implicitWait(5);
			WebElement fwdButton = validationUtil.validateWebElement("NPL.FwdSkip", driver);
			WebElement bkwButton = validationUtil.validateWebElement("NPL.BkwdSkip", driver);
			for (int i = 0; i <= 5; i++) {
				int count = 0;
				fwdButton.click();
				common.implicitWait(3);
				count++;
				if (fwdButton.getAttribute("enabled").equals("false")) {
					common.log("Total Count of forward skips till end : "+count);
					bkwButton.click();
					common.log("Clicked to backward skip");
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void validateProgressBarTime() {
		// TODO Auto-generated method stub
		Common common = new Common(driver);
		try {
			ValidationUtil validationUtil = new ValidationUtil();
			validationUtil.validateDisplayWebElement("NPL.StartTime", driver,
					ErrorMessageConstant.StartTime_NotDisplayed);
			validationUtil.validateDisplayWebElement("NPL.EndTime", driver, ErrorMessageConstant.EndTime_NotDisplayed);
			String starttime = validationUtil.validateWebElement("NPL.StartTime", driver).getText();
			common.log("Start time" + starttime);
			String endtime = validationUtil.validateWebElement("NPL.EndTime", driver).getText();
			common.log("End Time=" + endtime);
			common.log("Time is displayed in the format X:XX am/pm");
		} catch (Exception e) {
			// TODO: handle exception
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
		
	}
	
	
	public void validateBasicPlayerControls(){
		Common common = new Common(driver);
		try {
			ValidationUtil validationUtil = new ValidationUtil();
			WebElement nplPlayPause = validationUtil.validateDisplayWebElement("NPL.PlayPause", driver,
					ErrorMessageConstant.NPLPlayPause_NotDisplayed);
			nplPlayPause.click();
			Common.impicitWait(8);
			common.log("Clicked on play/pause icon.. music is paused");
			nplPlayPause.click();
			Common.impicitWait(4);
			common.log("Clicked on play/pause icon.. music is played");
			validationUtil.validateWebElement("Everest.Minimizebutton", driver).click();
			} catch (Exception e) {
			// TODO: handle exception
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
		
	}
}