/**
 * 
 */
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

/**
 * @author aparajita
 *  class elements for Home page
 */
public class Home {
	private final static Logger LOGGER = Logger.getLogger(Home.class.getName());
	private AppiumDriver<WebElement> driver;
	private String platform = EnvirnomentHandler.getInstance().getEnvirnoment().getChannel();
	Boolean flag = false;
	//int channelcount = 0;
	//int showCount = 0;

	

	public Home(AppiumDriver driver) {
		super();
		this.driver = driver;

	}

	private String locale = EnvirnomentHandler.getInstance().getEnvirnoment().getLocale();
	private PropertyElementReader elementReader = PropertyElementReader.getInstance(locale);



	public void homeFooter() throws AndriodException{
		Common common= new Common(driver);
		try{
			Common.impicitWait(3);
		ValidationUtil validationUtil = new ValidationUtil();
		common.waitforElment("Home.ForYou");
		validationUtil.validateDisplayWebElement("Home.ForYou", driver, "FOR YOU icon is not displayed");
		validationUtil.validateDisplayWebElement("Home.Music", driver, "Music icon is not displayed" );
		validationUtil.validateDisplayWebElement("Home.Sports", driver, "Sports icon is not displayed" );
		validationUtil.validateDisplayWebElement("Home.News", driver, "News icon is not displayed" );
		validationUtil.validateDisplayWebElement("Home.Talk", driver, "Talk icon is not displayed" );
		common.scrollUntilTextExists("Video");
		validationUtil.validateDisplayWebElement("Home.FooterHeader", driver, "FooterHeader is not displayed");
		validationUtil.validateDisplayWebElement("Home.FooterChannels", driver, "FooterChannels is not displayed");
		validationUtil.validateDisplayWebElement("Home.FooterOnDemand", driver, "FooterOnDemand is not displayed");
		validationUtil.validateDisplayWebElement("Home.FooterVideo", driver, "FooterVideo is not displayed");
		Common.log("Elements are available on the footer");
		common.scrollToUp(5);
		} catch (Exception e) {

			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void homeCategories() throws AndriodException{
		Common common= new Common(driver);
		try{
		ValidationUtil validationUtil = new ValidationUtil();
		validationUtil.validateDisplayWebElement("Home.ForYou", driver, "FOR YOU icon is not displayed");
		validationUtil.validateDisplayWebElement("Home.Sports", driver, "Sports icon is not displayed" );
		validationUtil.validateDisplayWebElement("Home.Music", driver, "Music icon is not displayed" );
		//validationUtil.validateWebElement("Home.Music", driver).click();
		//validationUtil.validateDisplayWebElement("Home.ForYou", driver, "FOR YOU icon is not displayed");
		validationUtil.validateDisplayWebElement("Home.News", driver, "News icon is not displayed" );
		validationUtil.validateDisplayWebElement("Home.Talk", driver, "Talk icon is not displayed" );
		validationUtil.validateWebElement("Home.News", driver).click();
		Common.impicitWait(3);
		validationUtil.validateDisplayWebElement("Home.Howard", driver, "Howard icon is not present.");
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}
	
	/**
	 * Code to verify navigation tab
	 */
	public void verifyNavigationTab(){
		Common common= new Common(driver);
		ValidationUtil validationUtil = new ValidationUtil();
		try{
			common.scrollToDown(2);
			validationUtil.validateDisplayWebElement("Home.News", driver, "FOR YOU icon is not displayed");
			validationUtil.validateDisplayWebElement("Home.Talk", driver, "News icon is not displayed" );
			Common.log("The tabs should remain locked at the top of the screen and content should flow under them");
		}catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}
	
	/*
	 * Code to verify Carousels
	 */
	public void verifyForYouCarousels(){
		Common common= new Common(driver);
		ValidationUtil validationUtil = new ValidationUtil();
		try{
			validationUtil.validateWebElement("Home.Music", driver).click();
			common.waitforElment("Home.ForYou");
			validationUtil.validateDisplayWebElement("Home.ForYou", driver, ErrorMessageConstant.ForYou_NotDisplayed).click();
			try{

				List<WebElement> NoofCarousels = driver
						.findElements(By.xpath(elementReader.getPropertyElement("ForYou.HeroCauroselsCount")));
				if (null != NoofCarousels && NoofCarousels.size() > 0) {
					common.log("Caurosels Count:" + NoofCarousels.size());
					common.log("Caurosels are present.");
				}
			}catch (Exception e) {
						common.log("Caurosels are not present.");
			}
			common.scrollUntilTextExists("Recommended For You");
			try{
				List<WebElement> NoofCarousels = driver
						.findElements(By.xpath(elementReader.getPropertyElement("ForYou.CauroselsCount")));
				if (null != NoofCarousels && NoofCarousels.size() > 0) {
					common.log("Caurosels Count:" + NoofCarousels.size());
					common.log("Caurosels are present.");
			}
			}catch (Exception e) {
				common.log("Caurosels are not present.");
			}
			common.scrollToUp(3);
			validationUtil.validateWebElement("ForYou.HeroCauroselsCount1", driver).click();
			common.implicitWait(3);
			validationUtil.validateDisplayWebElement("Everest.Minimizebutton", driver, ErrorMessageConstant .MinimizeBtn_NotDisplayed);
			Common.log("Navigated to NPL screen");
			validationUtil.validateWebElement("Everest.Minimizebutton", driver).click();
		}catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}
	
	/**
	 * code for verifying Hero caurosels
	 */
	public void verifHeroCaurosels(){
		Common common= new Common(driver);
		ValidationUtil validationUtil = new ValidationUtil();
		try{
			validationUtil.validateDisplayWebElement("Home.ForYou", driver, ErrorMessageConstant.ForYou_NotDisplayed).click();
			try{

				List<WebElement> NoofCarousels = driver
						.findElements(By.xpath(elementReader.getPropertyElement("ForYou.HeroCauroselsCount")));
				if (null != NoofCarousels && NoofCarousels.size() > 0) {
					common.log("Caurosels Count:" + NoofCarousels.size());
					common.log("Caurosels are present.");
				}
			}catch (Exception e) {
						common.log("Caurosels are not present.");
			}
			validationUtil.validateWebElement("ForYou.HeroCauroselsCount1", driver).click();
			common.implicitWait(3);
			validationUtil.validateDisplayWebElement("Everest.Minimizebutton", driver, ErrorMessageConstant .MinimizeBtn_NotDisplayed);
			Common.log("Navigated to NPL screen");
			validationUtil.validateWebElement("Everest.Minimizebutton", driver).click();
		}catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}
	
	
	/**
	 * code  for verify Linear herocarousels for super ctegory
	 */
	public void verifyLinearHeroCausosels(){
		Common common= new Common(driver);
		ValidationUtil validationUtil = new ValidationUtil();
		try{
			validationUtil.validateDisplayWebElement("Home.ForYou", driver, ErrorMessageConstant.ForYou_NotDisplayed).click();
			try{
				List<WebElement> NoofCarousels = driver
						.findElements(By.xpath(elementReader.getPropertyElement("ForYou.HeroCauroselsCount")));
				if (null != NoofCarousels && NoofCarousels.size() > 0) {
					common.log("Caurosels Count:" + NoofCarousels.size());
					common.log("Caurosels are present.");
				}
			}catch (Exception e) {
						common.log("Caurosels are not present.");
			}
			validationUtil.validateWebElement("Home.News", driver).click();
			Common.impicitWait(3);
			try{
				List<WebElement> NoofCarousels = driver
						.findElements(By.xpath(elementReader.getPropertyElement("ForYou.HeroCauroselsCount")));
				if (null != NoofCarousels && NoofCarousels.size() > 0) {
					common.log("Caurosels Count:" + NoofCarousels.size());
					common.log("Caurosels are present.");
				}
			}catch (Exception e) {
						common.log("Caurosels are not present.");
			}
			validationUtil.validateDisplayWebElement("Home.Talk", driver, "Talk icon is not displayed" );
			Common.impicitWait(3);
			try{
				List<WebElement> NoofCarousels = driver
						.findElements(By.xpath(elementReader.getPropertyElement("ForYou.HeroCauroselsCount")));
				if (null != NoofCarousels && NoofCarousels.size() > 0) {
					common.log("Caurosels Count:" + NoofCarousels.size());
					common.log("Caurosels are present.");
				}
			}catch (Exception e) {
						common.log("Caurosels are not present.");
			}
		}catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}
	
	
public void CategoryListing()throws AndriodException{
	Common common= new Common(driver);
	try{
	ValidationUtil validationUtil = new ValidationUtil();
	validationUtil.validateWebElement("Home.Sports", driver).click();
	Common.impicitWait(3);
	common.scrollUntilTextExists("NHL Play-by-Play");
	validationUtil.validateDisplayWebElement("Sports.SportsTalk", driver, "Sports Talk is not displayed" );
	validationUtil.validateDisplayWebElement("Sports.SportsNFL", driver, "Sports NFL is not displayed" );
	Common.log("Subcategory elements are present under Sports Category.");
	validationUtil.validateWebElement("Home.Music", driver).click();
	common.scrollUntilTextExists("R&B");
	//validationUtil.validateDisplayWebElement("Music.Popchannel", driver, "Popchannel is not displayed" );
	validationUtil.validateDisplayWebElement("Music.Rockchannel", driver, "Rockchannel is not displayed" );
	validationUtil.validateDisplayWebElement("Music.HipHop", driver, "HipHop is not displayed" );
	Common.log("Subcategory elements are present under Music Category.");
	validationUtil.validateWebElement("Home.News", driver).click();
	common.scrollUntilTextExists("Canadian");
	validationUtil.validateDisplayWebElement("News.SubCatNews", driver, "Sports Talk is not displayed" );
	validationUtil.validateDisplayWebElement("News.Politics", driver, "Sports NFL is not displayed" );
	Common.log("Subcategory elements are present under News Category.");
	} catch (Exception e) {

		throw new AndriodException(this.getClass().getSimpleName(),
				Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
	}
	
}


}