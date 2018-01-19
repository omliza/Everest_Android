package com.sxm.mobile.everest.pages;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.sxm.framework.dto.handler.EnvirnomentHandler;
import com.sxm.framework.exception.AndriodException;
import com.sxm.framework.utility.PropertyElementReader;
import com.sxm.mobile.pages.Common;
import com.sxm.mobile.pages.TestConstants;
import com.sxm.mobile.pages.ValidationUtil;

import io.appium.java_client.AppiumDriver;

public class RecentPage {
	private final static Logger LOGGER = Logger.getLogger(RecentPage.class.getName());
	private AppiumDriver<WebElement> driver;
	private String platform = EnvirnomentHandler.getInstance().getEnvirnoment().getChannel();
	Boolean flag = false;
	int channelcount = 0;
	int showCount = 0;
	ValidationUtil validationUtil = new ValidationUtil();
	Common common = new Common(driver);

	public RecentPage(AppiumDriver driver) {
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

	public void VerifyRecentlyPlayed_EmptyList()
	{
		
	}

	public void clickEditButton()
	{
		
	}
	
	public void clickClearAllButton()
	{
		
	}
	
	public void verifyRecentlyPlayedTiles()
	{
		
	}
	
	/*
	 * Navigate to Recent tab in the navigation bar
	 */
	public void clickOnRecentTab()
	{
		Common common = new Common(driver);
		ValidationUtil validationUtil = new ValidationUtil();
		Common.impicitWait(3);
		try {
			WebElement recentTab = validationUtil.GetElementById("Recent.RecentTab", driver,
					"Recent tab is not present on the screen");
			Common.log("Recent icon is present in the screen");
			recentTab.click();
			Common.impicitWait(3);
			Common.log("Successfully clicked on Recent tab in Tabbar");
		} catch (AndriodException e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Recent Icon is not present on the screen");
		}
	}
	
	/*
	 * Verify the elements on Recently Played 
	 */
	public Boolean verifyRecentPage(Boolean isEmptyPage)
	{
		Common common = new Common(driver);
		ValidationUtil validationUtil = new ValidationUtil();
		Common.impicitWait(3);
		
		try
		{
			// Verify 'Edit' link
			WebElement editLink = validationUtil.GetElementById("Recent.EditLink", driver,
							"Edit Link was not found on the screen");
			if(editLink.isEnabled())
			{
				Common.log("Edit link is enabled");
			}
			else
			{
				Common.log("Edit link is not enabled");
				return false;
			}
		}
		catch(Exception ex)
		{
			Common.log("Recent - Edit link was not found on page");
			return false;
		}
				
		try
		{
			// Verify Tiles
			if(isEmptyPage)
			{
				WebElement noTilesText = validationUtil.GetElementById("Recent.NoRecentPlayed_RecentlyPlayedHeaderText", driver,
						"Recently Played header text for no tiles is not displayed");
				
				noTilesText = validationUtil.GetElementById("Recent.NoRecentlyPlayed_TextMessage", driver,
						"Recently Played text for no tiles is not displayed");
			}
			else {
				validationUtil.validateDisplayWebElement("Recent.FirstTile", driver,
					"Tiles were not found on the screen");
				System.out.println("Tiles were not found on the screen");
			}
		}
		catch(Exception ex)
		{
			System.out.println("Recent - Tiles was not found on page");
			//Common.log("Recent - Tiles was not found on page");
			return false;
		}
		return true;
	}
	
	/*
	 * Delete the Recent Tile
	 */
	public void deleteRecentTile()
	{
		Common common = new Common(driver);
		ValidationUtil validationUtil = new ValidationUtil();
		
		try
		{
			Common.log("Click on Edit link on Recent page");
			WebElement editLink = validationUtil.GetElementById("Recent.EditLink", driver, "Edit Link was not found on page");
			editLink.click();
			Common.log("Edit Link was clicked");
			WebElement deleteLink = validationUtil.GetElementById("Recent.DeleteLink", driver, "Delete link was not found on the tile");
			deleteLink.click();
			Common.log("Tile was deleted");
		}
		catch(Exception ex)
		{
			Assert.fail("Unable to delete the recently played tile");
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), ex.getMessage());
		}
	}
}
