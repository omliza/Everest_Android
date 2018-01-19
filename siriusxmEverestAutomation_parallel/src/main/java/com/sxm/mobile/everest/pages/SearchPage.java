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

public class SearchPage {
	private final static Logger LOGGER = Logger.getLogger(SearchPage.class.getName());
	private AppiumDriver<WebElement> driver;
	private String platform = EnvirnomentHandler.getInstance().getEnvirnoment().getChannel();

	ValidationUtil validationUtil = new ValidationUtil();
	Common common = new Common(driver);

	public SearchPage(AppiumDriver driver) {
		super();
		this.driver = driver;

	}

	private String locale = EnvirnomentHandler.getInstance().getEnvirnoment().getLocale();
	private PropertyElementReader elementReader = PropertyElementReader.getInstance(locale);

	/**
	 * code for clilcking on search icon
	 * 
	 * @throws AndriodException
	 */
	public void clickOnSearch() throws AndriodException {
		Common common = new Common(driver);
		try {
			common.waitforElment("Home.SearchIcon");
			WebElement serach = validationUtil.validateDisplayWebElement("Home.SearchIcon", driver,
					ErrorMessageConstant.Search_NotDisplayed);
			serach.click();
			Common.log("clicked on search Icon");
			try {
				validationUtil.validateDisplayWebElement("Search.Searchbox", driver,
						ErrorMessageConstant.SearchBox_NotDisplayed);
			} catch (Exception e) {
				// TODO: handle exception
				serach.click();
			}
		} catch (Exception e) {
			Common.log("Serach icon is not present in the screen");
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Serach icon is not present in the screen");
		}
	}

	public void verifySuggestedSearch() {
		Common common = new Common(driver);
		common.implicitWait(4);
		try {
			// search text box element
			WebElement searchentry = validationUtil.validateDisplayWebElement("Search.Searchbox", driver,
					ErrorMessageConstant.SearchBox_NotDisplayed);
			Common.log("Search filter is displayed");
			searchentry.click();
			Common.impicitWait(2);
			searchentry.clear();
			common.implicitWait(4);
			searchentry.sendKeys("sir");
			Common.impicitWait(3);
			validationUtil.validateDisplayWebElement("Search.SuggestedSearch", driver,
					ErrorMessageConstant.SuggestedSearch_NotDisplayed);
			Common.log("Suggested Search  header is displayed");
			List<WebElement> Noofshows = driver
					.findElements(By.xpath(elementReader.getPropertyElement("Search.SuggestedSerachCount")));
			if (null != Noofshows && Noofshows.size() > 0) {
				Common.log("Search Count:" + Noofshows.size());
				Common.log("Shows are present.");
			} else {
				Common.log("Shows are not present.");
			}
			validationUtil.validateWebElement("Search.SuggestedSerachChannelClick", driver).click();
			common.implicitWait(4);
			validationUtil.validateDisplayWebElement("Everest.Minimizebutton", driver,
					ErrorMessageConstant.MinimizeBtn_NotDisplayed);
			Common.log("Navigated to NPL screen");
			validationUtil.validateWebElement("Everest.Minimizebutton", driver).click();
			searchentry.click();
			Common.impicitWait(2);
			searchentry.clear();
			common.implicitWait(4);
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void verifySuggestedSearchHeader() {
		Common common = new Common(driver);
		common.implicitWait(4);
		try {
			// search text box element
			WebElement searchentry = validationUtil.validateDisplayWebElement("Search.Searchbox", driver,
					ErrorMessageConstant.SearchBox_NotDisplayed);
			Common.log("Search filter is displayed");
			searchentry.click();
			Common.impicitWait(2);
			searchentry.clear();
			common.implicitWait(4);
			searchentry.sendKeys("sir");
			Common.impicitWait(3);
			validationUtil.validateDisplayWebElement("Search.SuggestedSearch", driver,
					ErrorMessageConstant.SuggestedSearch_NotDisplayed);
			Common.log("Suggested Search  header is displayed");
			List<WebElement> Noofshows = driver
					.findElements(By.xpath(elementReader.getPropertyElement("Search.SuggestedSerachCount")));
			if (null != Noofshows && Noofshows.size() > 0) {
				Common.log("Search Count:" + Noofshows.size());
				Common.log("Shows are present.");
			} else {
				Common.log("Shows are not present.");
			}
			validationUtil.validateDisplayWebElement("Search.SuggestedSearch", driver,
					ErrorMessageConstant.SuggestedSearch_NotDisplayed);
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void verifySearchAudionDemandCarousel() throws AndriodException {
		Common common = new Common(driver);
		try {
			// search text box element
			WebElement searchentry = validationUtil.validateDisplayWebElement("Search.Searchbox", driver,
					ErrorMessageConstant.SearchBox_NotDisplayed);
			Common.log("Search filter is displayed");
			searchentry.click();
			Common.impicitWait(2);
			searchentry.clear();
			Common.impicitWait(4);
			searchentry.sendKeys("snow");
			Common.impicitWait(4);
			validationUtil.validateWebElement("Search.SearchItem1", driver).click();
			Common.impicitWait(4);
			common.scrollUntilTextExists("Audio On Demand");
			validationUtil.validateDisplayWebElement("Search.AudioDemand", driver,
					ErrorMessageConstant.RecentSearchAudio_NotDisplayed);
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	/**
	 * code for search box presence
	 * 
	 * @throws AndriodException
	 */
	public void validateSearchEntry() throws AndriodException {
		Common common = new Common(driver);
		try {
			Common.impicitWait(3);
			validationUtil.validateDisplayWebElement("Search.Searchbox", driver,
					ErrorMessageConstant.SearchBox_NotDisplayed);
			Common.log("Search Box is present");
		} catch (Exception e) {
			// TODO: handle exception
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	/**
	 * code for first time search
	 */
	public void validateFirstTimeSearch() {
		Common common = new Common(driver);
		try {
			WebElement searchentry = validationUtil.validateDisplayWebElement("Search.Searchbox", driver,
					ErrorMessageConstant.SearchBox_NotDisplayed);
			Common.log("Search Box is present");
			WebElement searchText = validationUtil.validateDisplayWebElement("Search.FirstTimeSearch", driver,
					ErrorMessageConstant.FirstTimeSearch_NotDisplayed);
			Common.log(searchText.getText());
			Common.impicitWait(4);
		} catch (Exception e) {
			Common.log("Searches are there for the user");
		}
	}

	public WebElement filterSearch() {
		return driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.SearchFilter")));
	}

	public WebElement searchCancel() {
		return driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.CancelFilter")));
	}

	public WebElement searchClose() {
		return validationUtil.validateWebElement("Search.SearchClose", driver);
	}

	/**
	 * code for shows count
	 * 
	 * @throws AndriodException
	 */
	public void Showdescription() throws AndriodException {
		Common common = new Common(driver);
		try {
			ValidationUtil validationUtil = new ValidationUtil();
			List<WebElement> Noofshows = driver
					.findElements(By.xpath(elementReader.getPropertyElement("Search.SearchCount")));
			if (null != Noofshows && Noofshows.size() > 0) {
				Common.log("Show Count:" + Noofshows.size());
				Common.log("Shows are present.");
			} else {
				Common.log("Shows are not present.");
			}
		} catch (Exception e) {
			WebElement results = validationUtil.validateDisplayWebElement("Search.NoResultsPage", driver,
					ErrorMessageConstant.SearchNoresults_NotDisplayed);
			common.log(results.getText());

		}
	}

	/**
	 * code for search filter
	 * 
	 * @throws AndriodException
	 */
	public void searchFilter(String string) throws AndriodException {
		Common common = new Common(driver);
		try {
			// search text box element
			WebElement searchentry = validationUtil.validateDisplayWebElement("Search.Searchbox", driver,
					ErrorMessageConstant.SearchBox_NotDisplayed);
			Common.log("Search filter is displayed");
			searchentry.click();
			Common.impicitWait(2);
			searchentry.clear();
			for (int i = 0; i < string.length(); i++) {
				char c = string.charAt(i);
				String s = new StringBuilder().append(c).toString();
				searchentry.sendKeys(s);
				Common.impicitWait(3);
				Showdescription();
			}
			// search close element
			validationUtil.validateDisplayWebElement("Search.SearchClose", driver,
					ErrorMessageConstant.SearchBoxClose_NotDisplayed);
			Common.log("Search close button is present.");
			WebElement closefilter = validationUtil.validateWebElement("Search.SearchClose", driver);
			closefilter.click();
			Common.impicitWait(3);
			// search back button
			validationUtil.validateWebElement("Search.BackButton", driver).click();
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	/**
	 * code for recent search header
	 */
	public void recentSearch() throws AndriodException {
		Common common = new Common(driver);
		try {
			// search text box element
			WebElement searchentry = validationUtil.validateDisplayWebElement("Search.Searchbox", driver,
					ErrorMessageConstant.SearchBox_NotDisplayed);
			Common.log("Search filter is displayed");
			searchentry.click();
			Common.impicitWait(2);
			searchentry.clear();
			common.implicitWait(4);
			/*
			 * searchentry.clear(); for (int i = 0; i < string.length(); i++) {
			 * char c = string.charAt(i); String s = new
			 * StringBuilder().append(c).toString(); searchentry.sendKeys(s);
			 * Common.impicitWait(3); Showdescription(); }
			 */
			validationUtil.validateDisplayWebElement("Search.RecentSearch", driver,
					ErrorMessageConstant.RecentSearch_NotDisplayed);
			Common.log("Recent search  header is displayed");
			// search close element
			/*
			 * validationUtil.validateDisplayWebElement("Search.SearchClose",
			 * driver, ErrorMessageConstant.SearchBoxClose_NotDisplayed);
			 * Common.log("Search close button is present."); WebElement
			 * closefilter =
			 * validationUtil.validateWebElement("Search.SearchClose", driver);
			 * closefilter.click(); Common.impicitWait(3);
			 */
			// search back button
			validationUtil.validateWebElement("Search.BackButton", driver).click();
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void verifyRecentSearch() {
		Common common = new Common(driver);
		try {
			// search text box element
			WebElement searchentry = validationUtil.validateDisplayWebElement("Search.Searchbox", driver,
					ErrorMessageConstant.SearchBox_NotDisplayed);
			Common.log("Search filter is displayed");
			searchentry.click();
			Common.impicitWait(2);
			searchentry.clear();
			searchentry.sendKeys("snow");
			Common.impicitWait(4);
			validationUtil.validateWebElement("Search.SearchItem1", driver).click();
			Common.impicitWait(4);
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void recentSearchcount() throws AndriodException {
		Common common = new Common(driver);
		try {
			// search text box element
			WebElement searchentry = validationUtil.validateDisplayWebElement("Search.Searchbox", driver,
					ErrorMessageConstant.SearchBox_NotDisplayed);
			Common.log("Search filter is displayed");
			searchentry.click();
			Common.impicitWait(2);
			searchentry.clear();
			searchentry.sendKeys("snow");
			Common.impicitWait(4);
			validationUtil.validateWebElement("Search.SearchItem1", driver).click();
			Common.impicitWait(4);
			common.scrollToDown(1);
			validationUtil.validateDisplayWebElement("Search.AudioDemand", driver,
					ErrorMessageConstant.RecentSearchAudio_NotDisplayed);
			validationUtil.validateWebElement("Search.ClickAudio", driver).click();
			Common.impicitWait(3);
			validationUtil.validateDisplayWebElement("Everest.Minimizebutton", driver,
					ErrorMessageConstant.MinimizeBtn_NotDisplayed);
			Common.log("navigated to NOW playing Screen.");
			validationUtil.validateWebElement("Everest.Minimizebutton", driver).click();
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void searchTopResult() throws AndriodException {
		Common common = new Common(driver);
		try {
			// search text box element
			WebElement searchentry = validationUtil.validateDisplayWebElement("Search.Searchbox", driver,
					ErrorMessageConstant.SearchBox_NotDisplayed);
			Common.log("Search filter is displayed");
			searchentry.click();
			Common.impicitWait(2);
			searchentry.clear();
			searchentry.sendKeys("snow");
			Common.impicitWait(4);
			validationUtil.validateWebElement("Search.SearchItem1", driver).click();
			Common.impicitWait(4);
			/*
			 * Common.scrollUntilTextExists("View more results");
			 * validationUtil.validateWebElement("Search.SearchTopResult",
			 * driver).click(); Common.impicitWait(3);
			 * Common.scrollUntilTextExists("View fewer results");
			 * validationUtil.validateWebElement("Search.SearchTopResult",
			 * driver).click(); Common.impicitWait(4);
			 */
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void searchByChannelNumber() throws AndriodException {
		Common common = new Common(driver);
		try {
			WebElement searchentry = validationUtil.validateDisplayWebElement("Search.Searchbox", driver,
					ErrorMessageConstant.SearchBox_NotDisplayed);
			Common.log("Search filter is displayed");
			searchentry.click();
			Common.impicitWait(2);
			searchentry.clear();
			searchentry.sendKeys("32");
			validationUtil.validateWebElement("Search.SearchByChannelNumber", driver).click();
			Common.impicitWait(3);
			validationUtil.validateDisplayWebElement("Everest.Minimizebutton", driver,
					ErrorMessageConstant.MinimizeBtn_NotDisplayed);
			Common.log("Navigated to Now playing screen");
			validationUtil.validateWebElement("Everest.Minimizebutton", driver).click();
			Common.impicitWait(2);
			searchentry.clear();
			searchentry.sendKeys("2");
			validationUtil.validateWebElement("Search.SearchByChannelNumber", driver).click();
			Common.impicitWait(3);
			validationUtil.validateDisplayWebElement("Everest.Minimizebutton", driver,
					ErrorMessageConstant.MinimizeBtn_NotDisplayed);
			Common.log("Navigated to Now playing screen");
			validationUtil.validateWebElement("Everest.Minimizebutton", driver).click();
			Common.impicitWait(2);
			searchentry.clear();
			searchentry.sendKeys("100");
			validationUtil.validateWebElement("Search.SearchByChannelNumber", driver).click();
			Common.impicitWait(3);
			validationUtil.validateDisplayWebElement("Everest.Minimizebutton", driver,
					ErrorMessageConstant.MinimizeBtn_NotDisplayed);
			Common.log("Navigated to Now playing screen");
			validationUtil.validateWebElement("Everest.Minimizebutton", driver).click();
			Common.impicitWait(2);
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	/**
	 * Selecting the episode channels from history
	 */
	public void seachFromHistory(String string) {
		Common common = new Common(driver);
		try {
			boolean value = false;
			// TODO Auto-generated method stub
			common.waitforElment("Search.Searchbox");
			WebElement searchentry = validationUtil.validateDisplayWebElement("Search.Searchbox", driver,
					ErrorMessageConstant.SearchBox_NotDisplayed);
			Common.log("Search Textbox is displayed");
			common.implicitWait(5);
			List<WebElement> recentSearch = validationUtil.validateListWebElement("Everest.RecentSearch", driver);
			for (WebElement e : recentSearch) {
				if (e.getText().equalsIgnoreCase(string)) {
					e.click();
					common.log("Clicked on the channel from recent search");
					value = true;
					common.implicitWait(3);
				}
			}
			Common.log("Select from Recent search");
			if (value == false) {
				searchentry.clear();
				searchentry.sendKeys(string);
				common.implicitWait(2);
				List<WebElement> searched = validationUtil.validateListWebElement("Everest.Search.Channel", driver);
				for (WebElement e : searched) {
					if (e.getText().equalsIgnoreCase(string)) {
						e.click();
						common.log("Clicked on the channel from recent search");
						common.implicitWait(3);
						break;
					}
				}
				common.implicitWait(3);
				Common.log("Clicked on Search to get Search results");
			} else {
				Common.log("Channel was not selected from Recent Search History");
			}

		} catch (Exception e) {
			Common.log("Unable to select Channel from Recent Search History. Please debug the test separately");
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	/**
	 * Verifying video On-Demand
	 */
	public void VerifyVideoOndemandCarousel() {
		try {
			Common common = new Common(driver);
			validationUtil.validateDisplayWebElement("Search.AudioDemand", driver,
					ErrorMessageConstant.SearchAudoiOnDemand_NotDisplayed);
			common.implicitWait(3);
			// TODO Auto-generated method stub
			common.scrollUntilTextExists("Video On Demand");
			common.scrollToDown(1);
			common.implicitWait(3);
			validationUtil.validateDisplayWebElement("Everest.Search.Video", driver,
					ErrorMessageConstant.VideoTiles_NotDisplayed);
			validationUtil.validateWebElement("Everest.Search.FirstVideo", driver).click();
			common.implicitWait(4);
			WebElement minimizebutton = validationUtil.validateDisplayWebElement("Everest.Minimizebutton", driver,
					ErrorMessageConstant.MinimizeBtn_NotDisplayed);
			common.log("Navigated to NPL after clicking on Video On-Demand channel");
			validationUtil.validateWebElement("NPL.ChNumber", driver).click();
			if (minimizebutton.isDisplayed()) {
				minimizebutton.click();
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void VerifyAudioOndemandCarousel() {
		// TODO Auto-generated method stub
		Common common = new Common(driver);
		common.scrollUntilTextExists("Audio On Demand");
		verifyAODCarousal();
	}

	public void searchChannel(String string) {
		// TODO Auto-generated method stub
		WebElement searchentry = validationUtil.validateDisplayWebElement("Search.Searchbox", driver,
				ErrorMessageConstant.SearchBox_NotDisplayed);
		searchentry.clear();
		searchentry.sendKeys(string);
	}

	public void verifyAODCarousal() throws AndriodException {
		Common common = new Common(driver);
		try {
			validationUtil.validateDisplayWebElement("Search.AODHeader", driver,
					ErrorMessageConstant.AudioHeader_NotDisplayed);
			String yAODHeader = validationUtil.validateWebElement("Search.AODHeader", driver).getAttribute("y");
			String tAODCarousal = validationUtil.validateWebElement("Search.AODCaurosels", driver).getAttribute("y");
			if (Integer.parseInt(yAODHeader) < Integer.parseInt(tAODCarousal)) {
				common.log("The AOD Carousal is present below AOD Header");
			} else {
				common.log("The AOD Carousal is not  present below AOD Header");
			}
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void clickAODCarousel() {
		Common common = new Common(driver);
		try {
			validationUtil.validateWebElement("Search.ClickAODCaurosels", driver).click();
			common.implicitWait(4);
			validationUtil.validateDisplayWebElement("Everest.Minimizebutton", driver,
					ErrorMessageConstant.MinimizeBtn_NotDisplayed);
			Common.log("Navigated to NPL screen");
			validationUtil.validateWebElement("Everest.Minimizebutton", driver).click();
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void verifyVODCarousels() {
		common.implicitWait(3);
		Common common = new Common(driver);
		try {
			common.scrollUntilTextExists("Video On Demand");
			validationUtil.validateDisplayWebElement("Search.VODHeader", driver,
					ErrorMessageConstant.AudioHeader_NotDisplayed);
			String yAODHeader = validationUtil.validateWebElement("Search.VODHeader", driver).getAttribute("y");
			String tAODCarousal = validationUtil.validateWebElement("Search.VODCaurosels", driver).getAttribute("y");
			if (Integer.parseInt(yAODHeader) < Integer.parseInt(tAODCarousal)) {
				common.log("The VOD Carousal is present below VOD Header");
			} else {
				common.log("The VOD Carousal is not  present below VOD Header");
			}
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

}
