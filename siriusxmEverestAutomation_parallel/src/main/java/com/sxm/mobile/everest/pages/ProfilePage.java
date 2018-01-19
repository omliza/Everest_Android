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

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class ProfilePage {
	private final static Logger LOGGER = Logger.getLogger(ProfilePage.class.getName());
	private AppiumDriver<WebElement> driver;
	private String platform = EnvirnomentHandler.getInstance().getEnvirnoment().getChannel();
	Boolean flag = false;
	int channelcount = 0;
	int showCount = 0;
	ValidationUtil validationUtil = new ValidationUtil();
	Common common = new Common(driver);

	public ProfilePage(AppiumDriver driver) {
		super();
		this.driver = driver;

	}

	private String locale = EnvirnomentHandler.getInstance().getEnvirnoment().getLocale();
	private PropertyElementReader elementReader = PropertyElementReader.getInstance(locale);

	
	
	
	// 26092017
		@SuppressWarnings("null")
		public void profileSignOut() throws AndriodException {
			Common common = new Common(driver);
			Common.impicitWait(5);
			try {
				validationUtil.validateWebElement("Home.Profile", driver).click();
				Common.log("Profile menu is clicked");
				Common.impicitWait(2);
				common.scrollUntilTextExists("Sign Out");
				Common.impicitWait(5);
				for (int i = 0; i < 6; i++) {
					try {
						if (driver.findElement(By.xpath(elementReader.getPropertyElement("Profile.SignOut")))
								.isDisplayed()) {
							break;
						} else {
							common.scrollToDown(1);
						}
					} catch (Exception e) {
					}
				}
				validationUtil.validateWebElement("Profile.SignOut", driver).click();
				Common.log("Signout button is clicked");
				Common.impicitWait(4);
				Common.log("Landed to Login page of the app");
				validationUtil.validateDisplayWebElement("Login.UserName", driver,
						ErrorMessageConstant.UserName_NotDisplayed);
				String UserText = username().getText();
				Common.log("After signout username text has" + UserText);
				validationUtil.validateDisplayWebElement("Login.Password", driver,
						ErrorMessageConstant.Password_NotDisplayed);
				String string = "";
				String PassText = password().getText().trim();
				Common.log("Length of the sting " + PassText.length());
				Assert.assertEquals(PassText, string);
				Common.log("After signout password should be blank" + PassText);
			} catch (Exception e) {
				throw new AndriodException(this.getClass().getSimpleName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
			}
		}
		
		
		public WebElement username() throws AndriodException {
			return validationUtil.validateWebElement("Login.UserName", driver);
		}

		public WebElement password() throws AndriodException {
			return validationUtil.validateWebElement("Login.Password", driver);
		}
		
		
		/**
		 * Video icon is present in the screen
		 * 
		 * @throws AndriodException
		 */
		public void clickProfile() throws AndriodException {
			Common common = new Common(driver);
			try {
				Common.impicitWait(5);
				validationUtil.validateDisplayWebElement("Home.Profile", driver,
						ErrorMessageConstant.ProfileIcon_NotDisplayed);
				profileMenu().click();
				Common.log("Profile menu is clicked");
				Common.impicitWait(3);
			} catch (Exception e) {
				Common.log("Profile icon is not present in the screen");
				throw new AndriodException(this.getClass().getSimpleName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(),
						ErrorMessageConstant.ProfileIcon_NotDisplayed);
			}
		}
		
		
		public WebElement profileMenu() throws AndriodException {
			return validationUtil.validateWebElement("Home.Profile", driver);
		}

		public WebElement signout() throws AndriodException {
			return validationUtil.validateWebElement("Profile.SignOut", driver);
		}
		
		
		public void verifyProfileMenu() throws AndriodException {
			Common common = new Common(driver);
			try {
				common.scrollUntilTextExists("Help & Support");
				validationUtil.validateDisplayWebElement("Profile.AppSettings", driver,
						ErrorMessageConstant.ApplicationSettings_NotDisplayed);
				// WebElement appsetting =
				// driver.findElement(By.xpath(elementReader.getPropertyElement("Profile.AppSettings")));
				// .assertTrue(appsetting.isDisplayed(), "Application Settings is
				// present on the profile Menu");
				validationUtil.validateDisplayWebElement("Profile.HelpSupport", driver,
						ErrorMessageConstant.HelpnSupport_NotDisplayed);
				// WebElement help =
				// driver.findElement(By.xpath(elementReader.getPropertyElement("Profile.HelpSupport")));
				// .assertTrue(help.isDisplayed(), "Help & Support is present.");
				common.scrollToDown(1);
				validationUtil.validateDisplayWebElement("Profile.SendFeedback", driver,
						ErrorMessageConstant.SendFeedBack_NotDisplayed);
				// WebElement sendfeedback =
				// driver.findElement(By.xpath(elementReader.getPropertyElement("Profile.SendFeedback")));
				// Assert.assertTrue(sendfeedback.isDisplayed(), "SendFeedback is
				// present on the profile menu.");
				validationUtil.validateDisplayWebElement("Profile.AppLink", driver,
						ErrorMessageConstant.AppLink_NotDisplayed);
				validationUtil.validateDisplayWebElement("Profile.About", driver, ErrorMessageConstant.About_NotDisplayed);
				validationUtil.validateDisplayWebElement("Profile.SignOut", driver, "Signout button is not present");
				// Assert.assertTrue(signout().isDisplayed(), "Signout is present");
				validationUtil.validateDisplayWebElement("Profile.ManageAccount", driver,
						ErrorMessageConstant.ManageAccountSettings_NotDisplayed);
			} catch (Exception e) {
				throw new AndriodException(this.getClass().getSimpleName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
			}
		}
		

		
		public void clickOnApplicationSetting(){
			Common common = new Common(driver);
			try {
				common.scrollUntilTextExists("Help & Support");
				validationUtil.validateDisplayWebElement("Profile.AppSettings", driver,
						ErrorMessageConstant.ApplicationSettings_NotDisplayed).click();
			}catch (Exception e) {
				throw new AndriodException(this.getClass().getSimpleName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
			}
				
		}
		
		public void validateApplicationSettingsBrdCrumb() throws AndriodException {
			Common common = new Common(driver);
			Common.log("Executing validateApplicationSettingsBrdCrumb method");
			try {
				validationUtil.validateDisplayWebElement("Profile.ApplicationSetting.BreadCrumb", driver,
						ErrorMessageConstant.Profile_ApplicationSettingsBrdCrumb_NotDisplayed);
				common.log("Application Settings Bread Crumb is present");
			} catch (Exception e) {
				throw new AndriodException(this.getClass().getSimpleName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
			}
		}
		
		public void validateApplicationSettingsPageHeadersAndSubHeaders() throws AndriodException {
			Common common = new Common(driver);
			Common.log("Executing validateApplicationSettingsPageHeadersAndSubHeaders method");
			try {
				validationUtil.validateDisplayWebElement("Profile.ApplicationSetting.StreamingQuality.Header", driver,
						ErrorMessageConstant.Profile_ApplicationSettingsStreamingQualityHeader_NotDisplayed);
				common.log("Application Settings Streaming Quality Header is present");
				validationUtil.validateDisplayWebElement("Profile.ApplicationSetting.StreamingQuality.AudioQuality.Header",
						driver,
						ErrorMessageConstant.Profile_ApplicationSettingsStreamingQualityAudioQualityHeader_NotDisplayed);
				common.log("Application Settings Streaming Quality Audio Quality Header is present");
				validationUtil.validateDisplayWebElement("Profile.ApplicationSetting.OtherSettings.Header", driver,
						ErrorMessageConstant.Profile_ApplicationSettingsOtherSettingsHeader_NotDisplayed);
				common.log("Application Settings Other settings Header is present");
				validationUtil.validateDisplayWebElement("Profile.ApplicationSetting.OtherSettings.TuneStart", driver,
						ErrorMessageConstant.Profile_ApplicationSettingsOtherSettingsTuneStart_NotDisplayed);
				common.log("Application Settings Other Settings Tune Start is present");
				validationUtil.validateDisplayWebElement("Profile.ApplicationSetting.OtherSettings.MiniP", driver,
						ErrorMessageConstant.Profile_ApplicationSettingsOtherSettingsMiniP_NotDisplayed);
				common.log("Application Settings Other settings Mini Player is present");
				common.scrollToDown(1);
				validationUtil.validateDisplayWebElement("Profile.ApplicationSetting.OtherSettings.Override", driver,
						ErrorMessageConstant.Profile_ApplicationSettingsOtherSettingsOverride_NotDisplayed);
				common.log("Application Settings Other Settings Screen Lock Override is present");
			} catch (Exception e) {
				common.errorlog(e.getMessage());
				throw new AndriodException(this.getClass().getSimpleName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
			}
		}
		
		
		public void testTuneStartToggle() throws AndriodException {
			Common common = new Common(driver);
			try {
				WebElement button = validationUtil
						.validateWebElement("Profile.ApplicationSetting.OtherSettings.TuneStart.Btn", driver);
				String enabled = validationUtil
						.validateWebElement("Profile.ApplicationSetting.OtherSettings.TuneStart.Btn", driver)
						.getAttribute("checked");
				if (Integer.parseInt(enabled) == 1) {
					common.log("The tune start button is in enabled state and is by default");
				} else if (Integer.parseInt(enabled) == 0) {
					common.log("The tune start button is in disabled state");
				}
				button.click();
				enabled = validationUtil
						.validateWebElement("Profile.ApplicationSetting.OtherSettings.TuneStart.Btn", driver)
						.getAttribute("checked");
				if (Integer.parseInt(enabled) == 1) {
					common.log("The tune start button is in enabled state after clicking");
				} else if (Integer.parseInt(enabled) == 0) {
					common.log("The tune start button is in disabled state after clicking");
				}
			} catch (Exception e) {
				common.errorlog("Tune Start Toggle button is not present");
				throw new AndriodException(this.getClass().getSimpleName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(),
						e.getMessage());
			}
		}
		
		public void testOverrideToggle() throws AndriodException {
			Common common = new Common(driver);
			LOGGER.info("Executing testOverrideToggle method");
			try {
				WebElement button = validationUtil
						.validateWebElement("Profile.ApplicationSetting.OtherSettings.Override.Btn", driver);
				String enabled = validationUtil
						.validateWebElement("Profile.ApplicationSetting.OtherSettings.Override.Btn", driver)
						.getAttribute("checked");
				if (Integer.parseInt(enabled) == 1) {
					common.log("The Screen Lock Override Toggle button is in enabled state and is by default");
				} else if (Integer.parseInt(enabled) == 0) {
					common.log("The Screen Lock Override Toggle button is in disabled state");
				}
				button.click();
				enabled = validationUtil.validateWebElement("Profile.ApplicationSetting.OtherSettings.Override.Btn", driver)
						.getAttribute("checked");
				if (Integer.parseInt(enabled) == 1) {
					common.log("The Screen Lock Override Toggle button is in enabled state after clicking");
				} else if (Integer.parseInt(enabled) == 0) {
					common.log("The Screen Lock Override Toggle button is in disabled state after clicking");
				}
			} catch (Exception e) {
				common.errorlog("Screen Lock Override Toggle button is not present");
				throw new AndriodException(this.getClass().getSimpleName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(),
						e.getMessage());
			}
		}

		
		

		public void validateProfileNotification() throws AndriodException {
			Common common = new Common(driver);
			Common.log("Validating Profile contents MABANDEVER-682");
			try {
				Common.impicitWait(6);
/*
				validationUtil.validateDisplayWebElement("About.Recent", driver,
						ErrorMessageConstant.RecentlyPlayed_NotDisplayed);*/
			} catch (Exception e) {
				throw new AndriodException(this.getClass().getSimpleName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
			}
		}
		
		

		public void verifyAbout() throws AndriodException {
			Common common = new Common(driver);
			try {
				common.scrollUntilTextExists("Sign Out");
				validationUtil.validateDisplayWebElement("Profile.About", driver, ErrorMessageConstant.About_NotDisplayed);
			} catch (Exception e) {
				throw new AndriodException(this.getClass().getSimpleName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
			}
		}

		public void verifyVersion() throws AndriodException {
			Common common = new Common(driver);
			try {
				common.scrollUntilTextExists("Sign Out");
				WebElement about = validationUtil.validateWebElement("Profile.About", driver);
				about.click();
				WebElement buildno = validationUtil.validateDisplayWebElement("About.Build", driver,
						ErrorMessageConstant.AboutBuildNumber_NotDisplayed);
				Common.log("Build no is :" + buildno);
				WebElement version = validationUtil.validateDisplayWebElement("About.Version", driver,
						ErrorMessageConstant.AboutVersion_NotDisplayed);
				Common.log("Version is :" + version);
				validationUtil.validateWebElement("About.BackButton", driver).click();
			} catch (Exception e) {
				throw new AndriodException(this.getClass().getSimpleName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
			}
		}

		public void verifyCustomerAgreement() throws AndriodException {
			Common common = new Common(driver);
			try {
				common.scrollUntilTextExists("Sign Out");
				Common.impicitWait(7);
				WebElement about = validationUtil.validateWebElement("Profile.About", driver);
				about.click();
				WebElement agreement = validationUtil.validateDisplayWebElement("About.Customeragreement", driver,
						ErrorMessageConstant.AboutCustomerAgreement_NotDisplayed);
				agreement.click();
				Common.impicitWait(3);
				WebElement agreementHeader = validationUtil.validateDisplayWebElement("About.CustomerAgreementHeader",
						driver, ErrorMessageConstant.AboutCustomerAgreementHeader_NotDisplayed);
				WebElement agreemnetpage = validationUtil.validateDisplayWebElement("About.CustomeragreementPDF", driver,
						ErrorMessageConstant.AboutCustomerAgreementPDF_NotDisplayed);
				// Assert.assertTrue(agreemnetpage.isDisplayed());
				Common.log("Customer Agreemnet PDF is opened");
				validationUtil.validateWebElement("About.Backbutton", driver).click();
			} catch (Exception e) {
				throw new AndriodException(this.getClass().getSimpleName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
			}
		}

		public void verifyPrivacyPolicy() throws AndriodException {
			Common common = new Common(driver);
			try {
				Common.impicitWait(3);
				common.scrollUntilTextExists("Sign Out");
				Common.impicitWait(3);
				WebElement about = validationUtil.validateWebElement("Profile.About", driver);
				about.click();
				WebElement privacy = validationUtil.validateDisplayWebElement("About.Privacypolicy", driver,
						ErrorMessageConstant.AboutPrivacypolicy_NotDisplayed);
				// WebElement privacy =
				// driver.findElement(By.xpath(elementReader.getPropertyElement("About.Privacypolicy")));
				// Assert.assertTrue(privacy.isDisplayed());
				privacy.click();
				Common.impicitWait(6);
				WebElement privacyheader = validationUtil.validateDisplayWebElement("About.PrivacyPolicyHeader", driver,
						ErrorMessageConstant.AboutPrivacypolicyHeader_NotDisplayed);
				// WebElement privacyheader = driver
				// .findElement(By.xpath(elementReader.getPropertyElement("About.PrivacyPolicyHeader")));
				// Assert.assertTrue(privacyheader.isDisplayed());
				WebElement privacypage = validationUtil.validateDisplayWebElement("About.PrivacyPolicyPDF", driver,
						ErrorMessageConstant.AboutPrivacypolicyPDF_NotDisplayed);
				// WebElement privacypage = driver
				// .findElement(By.xpath(elementReader.getPropertyElement("About.PrivacyPolicyPDF")));
				// Assert.assertTrue(privacypage.isDisplayed());
				Common.log("Privacy Policy PDF is opened");
				validationUtil.validateWebElement("About.Backbutton", driver).click();
				// driver.findElement(By.xpath(elementReader.getPropertyElement("About.Backbutton"))).click();
			} catch (Exception e) {
				throw new AndriodException(this.getClass().getSimpleName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
			}
		}
		/*
		 * about.click(); WebElement agreement=driver.findElement(By.xpath(
		 * "//*[@text='Customer Agreement']"));
		 * Assert.assertTrue(agreement.isDisplayed()); WebElement
		 * privacy=driver.findElement(By.xpath("//*[@text='Privacy Policy']"));
		 * Assert.assertTrue(privacy.isDisplayed()); //*[@content-desc='Showing
		 * viewer.']/android.view.View[2]/android.view.View[2]/android.view.View[1]/
		 * android.view.View[1]/android.view.View[1]/android.view.View[1]/android.
		 * view.View[5] }
		 */
		
		

		/**
		 * @throws AndriodException
		 */
		public void sendFeedback() throws AndriodException {
			Common common = new Common(driver);
			try {
				Common.impicitWait(3);
				common.waitforElment("Profile.SendFeedBack");
				validationUtil.validateWebElement("Profile.SendFeedBack", driver).click();
				Common.impicitWait(5);
				validationUtil.validateDisplayWebElement("SendFeedback.MessageCentre", driver,
						ErrorMessageConstant.SendFeedBack_NotDisplayed);
				Common.log("Appentive Dialouge is opened up");
				validationUtil.validateWebElement("Everest.BackButton", driver).click();
				validationUtil.validateDisplayWebElement("Home.Profile", driver,
						ErrorMessageConstant.ProfileIcon_NotDisplayed);
				Common.log("Navigated back to Profile page.");
				Common.impicitWait(2);
			} catch (Exception e) {
				throw new AndriodException(this.getClass().getSimpleName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
			}
		}
		
		/*
		 * Long Press on Send Feedback
		 */
		public void longPressSendfedback(){
			Common common = new Common(driver);
			try {
				Common.impicitWait(3);
				common.waitforElment("Profile.SendFeedBack");
				WebElement item1 = validationUtil.validateWebElement("Profile.SendFeedBack", driver);
				TouchAction action = new TouchAction((AndroidDriver) driver);
				action.longPress(item1).perform();
				Common.impicitWait(5);
				validationUtil.validateDisplayWebElement("Everest.Gmail", driver, ErrorMessageConstant.Gmail_NotDisplayed).click();
				WebElement element = validationUtil.validateDisplayWebElement("Everest.GmailTo", driver, ErrorMessageConstant.GmailTo_NotDisplayed);
				String Text = element.getText();
				common.log("An e-mail to :"+Text+ "is specified");
				clickBackButton();
				Common.impicitWait(3);
				clickBackButton();
				Common.impicitWait(3);
				
		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
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
		
	/**
	 * Code for manage Account for Profile
	 */
	public void verifManageAccount()throws AndriodException{
		Common common = new Common(driver);
		try{
			WebElement manageAccount = validationUtil.validateDisplayWebElement("Profile.ManageAccount", driver, ErrorMessageConstant.ProfileManageAccount_NotDisplayed)	;
			manageAccount.click();
			Common.impicitWait(8);
			Common.log("Opened a browser outside of the app and navigated  to https://care.siriusxm.com");
			} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
		}
	
	
	
	public void verifyHelpSupport()throws AndriodException{
		Common common = new Common(driver);
		try{
			WebElement helpsupport = validationUtil.validateDisplayWebElement("Profile.Help&Support", driver, ErrorMessageConstant.ProfileHelpSupport_NotDisplayed);
			helpsupport.click();
			Common.impicitWait(8);
			validationUtil.validateDisplayWebElement("Profile.HelpHeaderFile", driver, ErrorMessageConstant.ProfileHelpSupportHeader_NotDisplayed);
			Common.log("Opened a browser window on the device above the application/new tab in the browser");
			validationUtil.validateWebElement("Profile.BackButton", driver).click();
			Common.impicitWait(5);
			Common.log("navigated back to profile menu page");
			}catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}
	
	public void verifyRecentlyPlayed(){
		Common common = new Common(driver);
		try{
			validationUtil.validateDisplayWebElement("Profile.RecentPlayed", driver, ErrorMessageConstant.RecentlyPlayed_NotDisplayed);
			try{
				List<WebElement> NoofCarousels = driver
						.findElements(By.xpath(elementReader.getPropertyElement("Profile.RecentlyPlayedCaurosels")));
				if (null != NoofCarousels && NoofCarousels.size() > 0) {
					common.log("Caurosels Count:" + NoofCarousels.size());
					common.log("Caurosels are present.");
				}
			}catch (Exception e) {
				common.log("Caurosels are not present.");
			}
		}catch (Exception e) {
			common.log("Recently Played category is not available.");
		}
	}
	
	
	public void verifyFordSynLink(){
		Common common = new Common(driver);
		try{
			validationUtil.validateDisplayWebElement("Profile.FordSYNCAppLink", driver, ErrorMessageConstant.FordSynLink_NotDisplayed).click();
			common.log("Ford SYNC AppLink is clicked");
			common.implicitWait(4);
			validationUtil.validateDisplayWebElement("Profile.WebViewHeader", driver, ErrorMessageConstant.WebviewHeader_NotDisplayed);
			validationUtil.validateWebElement("Profile.BackButton", driver).click();
			common.implicitWait(4);
		}catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}
	
	
}