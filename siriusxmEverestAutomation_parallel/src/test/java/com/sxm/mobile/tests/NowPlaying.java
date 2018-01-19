package com.sxm.mobile.tests;

import org.testng.annotations.Test;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.selenium.mobile.base.RefactorMobileTestCase;
import com.sxm.framework.common.AppiumDriverUtil;
import com.sxm.framework.common.Screenshot;
import com.sxm.framework.dto.Envirnoment;
import com.sxm.framework.dto.UserObj;
import com.sxm.framework.dto.handler.EnvirnomentHandler;
import com.sxm.framework.dto.handler.UserHandler;
import com.sxm.framework.exception.InValidOSException;
import com.sxm.framework.exception.InValidToolException;
import com.sxm.mobile.pages.Common;
import com.sxm.mobile.pages.Login;
import com.sxm.mobile.pages.factory.SXMMobilePageFactory;

@SuppressWarnings({ "unused", "static-access" })
//@Listeners({ Screenshot.class })
public class NowPlaying extends RefactorMobileTestCase {
	private final static Logger LOGGER = Logger.getLogger(NowPlaying.class.getName());
	private Map<String, UserObj> users = UserHandler.getInstance(locale, env).getUserMap();
	private String referenceurl;
	public UserObj usr = null;
	public boolean isExist(By by){
		boolean flag = false;
		try{
			WebElement el = driver.findElement(by);
			flag = true;
		}
		catch(Exception ex){
			flag = false;
		}
		return flag;
	}
	
	
	
	
	@Parameters("platform")
	@org.testng.annotations.BeforeClass(alwaysRun = true)
	public void initializeDrivers(String plt, ITestContext context)throws InValidOSException, InValidToolException {
		System.out.println(locale);
		LOGGER.info(users.get("prod1").getUserName());
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		getPageFactory().getLogin().login();
		Common.impicitWait(5);
	}
	
	

	@AfterMethod(alwaysRun = true)
	public void homeIcon(){
		try{
			WebElement homeIcon = driver.findElement(
					By.xpath(elementReader.getPropertyElement("Everest.Home")));
			homeIcon.isDisplayed();
			Common.log("Home icon is present in the screen");
			homeIcon.click();
			Common.log("Successfully clicked on Home icon");
		}
		catch(Exception e){
			Common.log("Home icon is not present in the screen");
			Assert.fail();
		}
	}
	
	
	
	
	
	@Test(testName="Now Playing Apron-DMCA Restricted",enabled=true,groups={"MOBANDEVER-325",})
	public void verifyBuySong(){
		getPageFactory().getEvehome().clickMucisSubPop();
		getPageFactory().getEvehome().dmcaBuySongButton();
	}
	
	@Test(testName="Now Playing Apron-Unrestricted- On Demand-Save for Later",enabled=true,groups={"MOBANDEVER-337"})
	public void  VerifySaveForLater(){
		getPageFactory().getEvehome().clickOnHowardStern();
		getPageFactory().getEvehome().ValidateSaveForLater();
		
	}
	
	@Test(testName="Now Playing Apron - Unrestricted - Live - Save for Later",enabled=true,groups={"MOBANDEVER-330"})
	public void verifyUnrestrictedSaveLater(){
		getPageFactory().getHoward().clickOnHowardStern();
		getPageFactory().getEvehome().howardSaveLater();
	}
	
	
	
	
	
	
}	







	



