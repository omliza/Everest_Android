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
@Listeners({ Screenshot.class })
public class HowardStern extends RefactorMobileTestCase {
	private final static Logger LOGGER = Logger.getLogger(HowardStern.class.getName());
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
		/*if(getPageFactory().getEvehome().minimizebutton().isDisplayed()){
			getPageFactory().getEvehome().minimizebutton().click();
		}*/
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
	
	@Test(testName="Unrestricted-Howard Stern category",enabled=true,groups={"MOBANDEVER-206"})
	public void validateHowardChannelLogo(){
		getPageFactory().getEvehome().clickOnTalk();
		getPageFactory().getHoward().clickOnHowardStern();
		getPageFactory().getHoward().verifyHowardChannelLogo();
		
	}
	
	@Test(testName="Unrestricted-Howard Stern category",enabled=true,groups={"MOBANDEVER-344"})
	public void howardSternBackButton(){
		getPageFactory().getEvehome().clickOnTalk();
		getPageFactory().getHoward().clickOnHowardStern();
		getPageFactory().getEvehome().clickBackButton();
		
		
		
	}
	
	@Test(testName="Unrestricted-Howard Stern category",description="Now Playing - Live - Unrestricted - Channel Number",enabled=true,groups={"MOBANDEVER-207"})
	public void validateHowardChannelNumber(){
		getPageFactory().getEvehome().clickOnTalk();
		getPageFactory().getHoward().clickOnHowardStern();
		getPageFactory().getHoward().verifyHowardChannelNUmber();
		
	}
	@Test(testName="Unrestricted-Howard Stern category",enabled=true,groups={"MOBANDEVER-211"})
	public void validateHowardShowNamePdt(){
		getPageFactory().getEvehome().clickOnTalk();
		getPageFactory().getHoward().clickOnHowardStern();
	    getPageFactory().getHoward().verifyUnrestrictedShownamePDT();
	    getPageFactory().getEvehome().nplShowName();
	    getPageFactory().getEvehome().nplPDT();
	    getPageFactory().getEvehome().clickminimize();
	    
	}
	
	@Test(testName="Unrestricted-Howard Stern category",description="Now Playing - On Demand - Unrestricted - Show name & PDT",enabled=true,groups={"MOBANDEVER-243"})
	public void validateHowardOnDEmnadShowNamePdt(){
		getPageFactory().getEvehome().clickOnTalk();
		getPageFactory().getHoward().clickOnHowardStern();
	    getPageFactory().getHoward().verifyUnrestrictedOnDemandShownamePDT();
	    getPageFactory().getEvehome().nplShowName();
	    getPageFactory().getEvehome().nplPDT();
	    getPageFactory().getEvehome().clickminimize();
	    
	}
	
	
	@Test(testName="Unrestricted-Howard Stern category",enabled=true,groups={"MOBANDEVER-209"})
	public void validateUnrestrictedShowArt(){
		getPageFactory().getEvehome().clickOnTalk();
		getPageFactory().getHoward().clickOnHowardStern();
		getPageFactory().getHoward().verifyUnrestrictedShowArt();
		getPageFactory().getEvehome().nowPlayingArt();
		getPageFactory().getEvehome().clickminimize();
	}
	
	
	@Test(testName="Unrestricted-Howard Stern category",enabled=true,groups={"MOBANDEVER-212"})
	public void validateUnrestrictedProgressBar(){
		getPageFactory().getEvehome().clickOnTalk();
		getPageFactory().getHoward().clickOnHowardStern();
		getPageFactory().getHoward().verifyUnrestrictedProgressbar();
		getPageFactory().getEvehome().clickminimize();
	}
	
	@Test(testName="Unrestricted-Howard Stern category",enabled=true,groups={"MOBANDEVER-242"})
	public void validateUnrestrictedOnDemandShowArt(){
		getPageFactory().getEvehome().clickOnTalk();
		getPageFactory().getHoward().clickOnHowardStern();
		getPageFactory().getHoward().verifyUnrestrictedShowArt();
		getPageFactory().getEvehome().nowPlayingArt();
		getPageFactory().getEvehome().clickminimize();
	}
	
	@Test(testName="Unrestricted-Howard Stern category",enabled=true,groups={"MOBANDEVER-357"})
	public void validateHowardShowListing(){
		getPageFactory().getEvehome().clickOnTalk();
		getPageFactory().getHoward().clickOnHowardStern();
		getPageFactory().getHoward().verifyUnrestrictedShowListing();
		
	}
	
	
	@Test(testName="Unrestricted-Howard Stern category",enabled=true,groups={"MOBANDEVER-358"})
	public void validateHowardShowListingItem(){
		getPageFactory().getEvehome().clickOnTalk();
		getPageFactory().getHoward().clickOnHowardStern();
		getPageFactory().getHoward().howardShowListingItem();
		getPageFactory().getEvehome().episodecnt();
		
	}
	
	
	@Test(testName="Unrestricted-Howard Stern category",enabled=true,groups={"MOBANDEVER-356"})
	public void ValidateHowardHeader(){
		getPageFactory().getEvehome().clickOnTalk();
		getPageFactory().getHoward().clickOnHowardStern();
		getPageFactory().getHoward().howardHeader();
	}
	
	@Test(testName="Unrestricted-Howard Stern category",enabled=true,groups={"MOBANDEVER-345"})
	public void ValidateHowardBreadcrumb(){
		getPageFactory().getEvehome().clickOnTalk();
		getPageFactory().getHoward().clickOnHowardStern();
		getPageFactory().getEvehome().howardbreadcrumb();
	}
	
	@Test(testName="Unrestricted-Howard Stern category",enabled=true,groups={"MOBANDEVER-348"})
	public void verifyHowardcarousel(){
		getPageFactory().getEvehome().clickOnTalk();
		getPageFactory().getHoward().clickOnHowardStern();
		getPageFactory().getEvehome().howardCarousel();
	}
	
	@Test(testName="Unrestricted-Howard Stern category",enabled=true,groups={"MOBANDEVER-347"})
	public void verifyHowardcarousel1(){
		getPageFactory().getEvehome().clickOnTalk();
		getPageFactory().getHoward().clickOnHowardStern();
		getPageFactory().getHoward().howardCarousel1();
	}
	

	@Test(testName="Unrestricted-Howard Stern category",enabled=true,groups={"MOBANDEVER-351"})
	public void verifyHowardcarousel2(){
		getPageFactory().getEvehome().clickOnTalk();
		getPageFactory().getHoward().clickOnHowardStern();
		getPageFactory().getHoward().howardCarousel1();
	}
	
	@Test(testName="Unrestricted-Howard Stern category",enabled=true,groups={"MOBANDEVER-215"})
	public void verifyhowardCategory(){
		getPageFactory().getEvehome().clickOnTalk();
		getPageFactory().getHoward().clickOnHowardStern();
		getPageFactory().getHoward().howardCarousel1();
	}
	
	
	@Test(testName="Unrestricted-Howard Stern category",enabled=true,groups={"MOBANDEVER-349"})
	public void Howardcarousel1(){
		getPageFactory().getEvehome().clickOnTalk();
		getPageFactory().getHoward().clickOnHowardStern();
		getPageFactory().getEvehome().verifyhowardCarousel();;
	}
	
	@Test(testName="Unrestricted-Howard Stern category",enabled=true,groups={"MOBANDEVER-350"})
	public void Howardcarousel2(){
		getPageFactory().getEvehome().clickOnTalk();
		getPageFactory().getHoward().clickOnHowardStern();
		getPageFactory().getEvehome().verifyhowardCarousel();;
	}
	
	@Test(testName="Unrestricted-Howard Stern category",description="Now Playing - On Demand - Unrestricted - Show Logo & ON DEMAND text",enabled=true,groups={"MOBANDEVER-240"})
	public void HowardShowLogo(){
		getPageFactory().getEvehome().clickOnTalk();
		getPageFactory().getHoward().clickOnHowardStern();
		getPageFactory().getHoward().unrestrictedShowlogoonDemand();;
	}
	

	@Test(testName="Now Playing Apron-Live-Related Module-Available Shows on ",enabled=true,groups={"MOBANDEVER-637"})
	public void validateUnrestrictedShowsAvaialble() throws InterruptedException{
		getPageFactory().getEvehome().clickOnTalk();
		getPageFactory().getHoward().clickOnHowardStern();
		getPageFactory().getHoward().unrestrictedAvaialbleShows();
		
	}
	
	
}	







	



