package com.sxm.mobile.everest.pages;

import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.sxm.framework.dto.handler.EnvirnomentHandler;
import com.sxm.framework.exception.AndriodException;
import com.sxm.framework.utility.PropertyElementReader;
import com.sxm.mobile.pages.Common;
import com.sxm.mobile.pages.ErrorMessageConstant;
import com.sxm.mobile.pages.ValidationUtil;

import facebook4j.Music;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class FavoritesPage {
	private final static Logger LOGGER = Logger.getLogger(FavoritesPage.class.getName());
	private AppiumDriver<WebElement> driver;
	private String platform = EnvirnomentHandler.getInstance().getEnvirnoment().getChannel();
	Boolean flag = false;
	int channelcount = 0;
	int showCount = 0;
	ValidationUtil validationUtil = new ValidationUtil();
	Common common = new Common(driver);

	public FavoritesPage(AppiumDriver driver) {
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

	public String unrestrictedFavoriteIcon() throws AndriodException {
		Common common = new Common(driver);
		ValidationUtil validationUtil = new ValidationUtil();
		validationUtil.validateWebElement("Howard.NPCarousel", driver).click();
		/*
		 * common.scrollToDown(1); Common.waitforElment("Howard.Show1");
		 * validationUtil.validateWebElement("Howard.Show1", driver).click();
		 * Common.impicitWait(2);
		 * validationUtil.validateWebElement("Howard.Episode", driver).click();
		 */
		Common.impicitWait(5);
		Common.log("Navigated to NPL screen");
		WebElement favicon = validationUtil.validateDisplayWebElement("NPL.FavoriteIcon", driver,
				"Favorite icon is not displayed on npl screen");
		String flag = favicon.getAttribute("checked");
		// boolean flag = favicon.isEnabled();
		if (flag.equals("true")) {
			System.out.println("Channel is already marked as favorite");
		} else {
			System.out.println("Channel is not favorite");
			favicon.click();
		}
		WebElement showname = validationUtil.validateWebElement("NPL.ShowName", driver);
		Common.log("Show Name is :" + showname.getText());
		return showname.getText();
	}

	public String disallowedFavoriteIcon() {
		WebElement showname = null;
		try {
			Common common = new Common(driver);
			ValidationUtil validationUtil = new ValidationUtil();
			// common.scrollToDown(1);
			validationUtil.validateWebElement("News.Channel1", driver).click();
			Common.impicitWait(2);
			Common.log("Navigated to NPL screen");
			WebElement favicon = validationUtil.validateDisplayWebElement("NPL.FavoriteIcon", driver,
					"Favorite icon is not displayed on npl screen");
			Assert.assertTrue(favicon.isDisplayed());
			String flag = favicon.getAttribute("checked");
			// boolean flag = favicon.isEnabled();
			if (flag.equals("true")) {
				System.out.println("Channel is already marked as favorite");
			} else {
				System.out.println("Channel is not favorite");
				favicon.click();
			}
			showname = validationUtil.validateWebElement("NPL.ShowName", driver);
			Common.log("Show Name is :" + showname.getText());
		} catch (Exception e) {
			// TODO: handle exception
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
		return showname.getText();
	}

	public String dmcaFavoriteIcon() {
		Common common = new Common(driver);
		try {
			ValidationUtil validationUtil = new ValidationUtil();
			validationUtil.validateWebElement("Pop.Channel1", driver).click();
			Common.impicitWait(2);
			Common.log("Navigated to NPL screen");
			WebElement favicon = validationUtil.validateDisplayWebElement("NPL.FavoriteIcon", driver,
					"Favorite icon is not displayed on npl screen");
			String flag = favicon.getAttribute("checked");
			// boolean flag = favicon.isEnabled();
			if (flag.equals("true")) {
				System.out.println("Channel is already marked as favorite");
			} else {
				System.out.println("Channel is not favorite");
				favicon.click();
			}
			WebElement showname = validationUtil.validateWebElement("NPL.ShowName", driver);
			Common.log("Show Name is :" + showname.getText());
			return showname.getText();
		} catch (Exception e) {
			// TODO: handle exception
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	/**
	 * Searching for the favorited element in favorited list
	 * 
	 * @param showname
	 */
	public void searchShowName(String showname) {
		Common common = new Common(driver);
		try {

			Common.impicitWait(3);
			common.scrollToDown(1);
			System.out.println(showname);
			List<WebElement> channelsize = driver.findElements(
					By.xpath("//android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView"));
			if (null != channelsize && channelsize.size() > 0) {
				Common.log("Channel Count:" + channelsize.size());
				Common.log("Favorite items are present.");
			} else {
				Common.log("Favorite items are  not present");
			}
			// for (int i = 0; i <4; i++) {
			boolean value = false;
			for (WebElement we : channelsize) {
				System.out.println(we.getText());
				if (we.getText().equals(showname)) {
					Common.log("Show is present in the list");
					value = true;
					break;
				}
			}

			if (value == false) {
				try {
					driver.findElement(By.xpath(elementReader.getPropertyElement("Favorites.NoFavorites")))
							.isDisplayed();
					System.out.println(driver.findElement(By.xpath("//*[@text='No Favorites Yet']")).getText());
				} catch (Exception e) {
					// TODO: handle exception
					throw new AndriodException(ErrorMessageConstant.Show_NotDisplayed);
				}

			}

		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void clickBackButton() {
		Common common = new Common(driver);
		((AndroidDriver) driver).pressKeyCode(4);
		Common.impicitWait(3);
	}

	public void clickFavoriteIcon() throws AndriodException {
		Common common = new Common(driver);
		ValidationUtil validationUtil = new ValidationUtil();
		Common.impicitWait(3);
		try {
			WebElement favicon = validationUtil.validateDisplayWebElement("Home.FavoriteIcon", driver,
					"Favorite Icon is not present on the screen");
			Common.log("Favourite icon is present in the screen");
			favicon.click();
			Common.impicitWait(3);
			Common.log("Successfully clicked on favourite icon");
		} catch (AndriodException e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Favorite Icon is not present on the screen");
		}
	}

	public void removeFavItem() throws AndriodException {
		Common common = new Common(driver);
		try {
			Common.impicitWait(3);
			common.waitforElment("Favorites.Edit");
			validationUtil.validateWebElement("Favorites.Edit", driver).click();
			Common.log("Favorites is in Edit Mode");
			validationUtil.validateDisplayWebElement("Favorites.Removefav", driver, "Remove button is present");
			validationUtil.validateWebElement("Favorites.Removefav", driver).click();
			Common.impicitWait(3);
			Common.log("Removed the favorite Channel from the list");
		} catch (AndriodException e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void verifyfavUI() throws AndriodException {
		Common common = new Common(driver);
		try {
			WebElement favEdit = validationUtil.validateDisplayWebElement("Favorites.Edit", driver,
					"Edit button is not displayed");
			Common.log("Favorite edit text is :" + favEdit.getText());
			favEdit.click();
			common.waitforElment("Favorites.Done");
			WebElement favDone = validationUtil.validateDisplayWebElement("Favorites.Done", driver,
					"Done button is not displayed");
			Common.log("Favorite done text is :" + favDone.getText());
			WebElement favPress = validationUtil.validateDisplayWebElement("Favorites.Text", driver,
					"Test is not displayed");
			Common.log("Favorite Hold text is :" + favPress.getText());
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void validatefavoritesScreen() {
		Common common = new Common(driver);
		try {
			List<WebElement> channelsize = driver
					.findElements(By.xpath(elementReader.getPropertyElement("Favorites.ListCount")));
			if (null != channelsize && channelsize.size() > 0) {
				Common.log("Channel Count:" + channelsize.size());
				Common.log("Channels are present.");
			} else {
				Common.log("Channels are not present.");
			}
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), "NO Channels are present");
		}
		Common.impicitWait(2);
		clickBackButton();
	}

	public WebElement favedit() {
		Common common = new Common(driver);
		return validationUtil.validateWebElement("Favorites.Edit", driver);
	}

	public WebElement confirmbtn() {
		Common common = new Common(driver);
		return validationUtil.validateWebElement("Favorites.Done", driver);
	}

	public void FavEditButton() {
		Common common = new Common(driver);
		validationUtil.validateDisplayWebElement("Favorites.Edit", driver, "Edit button is not displayed");
		favedit().click();
		// driver.findElement(By.xpath("//*[@resourceid='com.sirius.android.everest:id/action_mode_close_button']")).isDisplayed();
		Common.log("Favorite list is in Edit mode");
		Common.impicitWait(5);
		clickBackButton();
		// driver.findElementByXPath(elementReader.getPropertyElement("Everest.Home")).click();
		Common.impicitWait(4);
		clickBackButton();
	}

	public void verifyFavConfirmButton() {
		try {
			Common common = new Common(driver);
			favedit().click();
			Common.log("Favorite list is in Edit mode");
			Common.impicitWait(5);
			validationUtil.validateDisplayWebElement("Favorites.Done", driver,
					ErrorMessageConstant.ComfirmButton_NotDisplayed);
			// validationUtil.validateDisplayWebElement("Favorites.Text",
			// driver,
			// ErrorMessageConstant.FavoritesText_NotDisplayed);
			confirmbtn().click();
			Common.log("Navigated back to Favorites List");
		} catch (Exception e) {
			// TODO: handle exception
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	public void favoritesList() {
		Common common = new Common(driver);
		try {
			List<WebElement> channelsize = driver
					.findElements(By.xpath(elementReader.getPropertyElement("Favorites.ListCount")));
			if (null != channelsize && channelsize.size() > 0) {
				Common.log("Channel Count:" + channelsize.size());
				Common.log("Channels are present.");
			} else {
				Common.log("Channels are not present.");
			}
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), "NO Channels are present");
		}
		Common.impicitWait(3);

	}

	public void favoritesBreadcrumb() {
		Common common = new Common(driver);
		try {
			WebElement favoritesBrdCrumb = common
					.getElement(By.xpath(elementReader.getPropertyElement("Favorites.FavoriteBreadcrumb")));
			Assert.assertTrue(favoritesBrdCrumb.isDisplayed());
			if (favoritesBrdCrumb.getText().equalsIgnoreCase("Favorites")) {
				Assert.assertTrue(true);
				Common.log("Favorites Breadcrumb is present in Favorite page");
			} else {
				Common.log("Favorites Breadcrumb validation failed");

			}
		} catch (Exception e) {
			Common.log("Favourite Breadcrumb is not present in the screen");
		}
	}

	// added by prashanth
	public void verifyingFavoriteList(String string) {
		try {
			Common common = new Common(driver);
			Common.impicitWait(2);
			boolean value = false;
			validationUtil.validateWebElement("Home.FavoriteIcon", driver).click();
			Common.impicitWait(5);
			validationUtil.validateWebElement("Favorites.Edit", driver).click();
			common.scrollToDown(7);
			for (int i = 0; i < 5; i++) {
				List<WebElement> shows = driver
						.findElements(By.xpath(elementReader.getPropertyElement("Favorite.FavoritedEpisodeList")));
				for (WebElement e : shows) {
					if (e.getText().equalsIgnoreCase(string)) {
						Assert.assertTrue(true, "Show present in Favorite list");
						value = true;
						break;
					}
				}
				if (value == true) {
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public String favoritingShow() {
		Common common = new Common(driver);
		String name = null;
		try {
			WebElement favicon = validationUtil.validateWebElement("NPL.FavoriteIcon", driver);
			validationUtil.validateDisplayWebElement("NPL.FavoriteIcon", driver, "Favorite Icon is not displayed");
			String flag = favicon.getAttribute("checked");
			if (flag.equalsIgnoreCase("false")) {
				favicon.click();
			} else {
				favicon.click();
				Common.impicitWait(3);
				favicon.click();
			}
			Common.impicitWait(4);
			name = validationUtil.validateWebElement("NPL.ShowName", driver).getText();
			Common.log("Show name:" + name);
		} catch (Exception e) {
			// TODO: handle exception
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
		return name;
	}

	public void unfavoriteShow() {
		Common common = new Common(driver);
		try {
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
		} catch (Exception e) {
			// TODO: handle exception
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

}
