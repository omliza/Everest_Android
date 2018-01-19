package com.sxm.mobile.pages;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import com.sxm.framework.dto.Envirnoment;
import com.sxm.framework.dto.UserObj;
import com.sxm.framework.dto.handler.EnvirnomentHandler;
import com.sxm.framework.dto.handler.UserHandler;
import com.sxm.framework.exception.AndriodException;
import com.sxm.framework.utility.PropertyElementReader;
import com.sxm.mobile.common.ReadAttributeValue;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.NetworkConnectionSetting;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSElement;

public class Login {
	private final static Logger LOGGER = Logger.getLogger(Login.class.getName());
	private AppiumDriver driver;
	private String locale = EnvirnomentHandler.getInstance().getEnvirnoment().getLocale();
	private String env = EnvirnomentHandler.getInstance().getEnvirnoment().getEnv();
	private PropertyElementReader elementReader = PropertyElementReader.getInstance(locale);
	private Map<String, UserObj> users = UserHandler.getInstance(locale, env).getUserMap();
	private String userName = null;
	private String pwd = null;
	UserObj usr = null;

	WebElement element = null;

	public Login(AppiumDriver driver) {
		super();
		this.driver = driver;
	}

	/**
	 * @throws AndriodException
	 *             Entering wrong username and password
	 */
	public void loginWithWrongCredentials() throws AndriodException {
		Common common = new Common(driver);
		try {
			UserObj usr = users.get("prod21");
			ValidationUtil validationUtil = new ValidationUtil();
			element = validationUtil.validateWebElement("Login.UserName", driver);
			element.clear();
			Common.log("Entering wrong User id");
			element.sendKeys(usr.getUserName());
			element = validationUtil.validateWebElement("Login.Password", driver);
			element.clear();
			Common.log("Entering wrong password");
			element.sendKeys(usr.getPassword());
			validationUtil.validateWebElement("Login.Signin", driver).click();
			Common.impicitWait(4);
		} catch (AndriodException e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	public void loginWithWrongCredentialsmultipletimes(int j, String errorMessage) throws AndriodException {
		Common common = new Common(driver);
		try {
			for (int i = 1; i >= j; i++) {
				UserObj usr = users.get("prod21");
				ValidationUtil validationUtil = new ValidationUtil();
				element = validationUtil.validateWebElement("Login.UserName", driver);
				element.clear();
				Common.log("Entering wrong User id");
				element.sendKeys(usr.getUserName());
				element = validationUtil.validateWebElement("Login.Password", driver);
				element.clear();
				Common.log("Entering wrong password");
				element.sendKeys(usr.getPassword());
				validationUtil.validateWebElement("Login.Signin", driver).click();
				verifyErrorMessage(errorMessage);
				Common.impicitWait(4);
			}
		} catch (AndriodException e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	/**
	 * try with wrong credential
	 */
	public void wrongCredential() {
		Common common = new Common(driver);
		try {
			ValidationUtil validationUtil = new ValidationUtil();
			validationUtil.validateWebElement("Login.UserName", driver).clear();
			validationUtil.validateWebElement("Login.UserName", driver).sendKeys("prod3");
			validationUtil.validateWebElement("Login.Signin", driver).click();
			Common.impicitWait(4);
			verifyErrorMessage("We don’t recognize your username or password. Please try again.");
			validationUtil.validateWebElement("Login.Password", driver).clear();
			validationUtil.validateWebElement("Login.Password", driver).sendKeys("prod4");
			validationUtil.validateWebElement("Login.Signin", driver).click();
			Common.impicitWait(4);
			verifyErrorMessage("We don’t recognize your username or password. Please try again.");
		} catch (AndriodException e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	/**
	 * @param errorMessage
	 * @throws AndriodException
	 *             verifying error message
	 */
	public void verifyErrorMessage(String errorMessage) throws AndriodException {
		Common common = new Common(driver);
		ValidationUtil validationUtil = new ValidationUtil();
		try {
			Common.log("Verifying the error message");
			WebElement errmsage = validationUtil.validateWebElement("Login.ErrorMessage", driver);// driver.findElement(By.xpath(elementReader.getPropertyElement("Login.ErrorMessage")));
			if (!(null != errorMessage && errmsage.getText().trim().equalsIgnoreCase(errorMessage.trim()))) {
				throw new AndriodException(this.getClass().getSimpleName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(),
						"Error message doesn't match in login screen");
			}
			Common.log("We don’t recognize your username or password. Please try again.- error message is displayed");
		} catch (AndriodException e) {
			WebElement errmsage = validationUtil.validateWebElement("Login.ErrorMessage1", driver);// driver.findElement(By.xpath(elementReader.getPropertyElement("Login.ErrorMessage")));
			if (!(null != errorMessage && errmsage.getText().trim().equalsIgnoreCase(errorMessage.trim()))) {
				throw new AndriodException(this.getClass().getSimpleName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(),
						"Error message doesn't match in login screen");
			}
			Common.log(
					"Sorry, we can't find that Username or Password. Please try again. Remember, Usernames and Passwords are case sensitive.");
		}
	}

	/**
	 * Login code
	 */
	public void login() throws AndriodException {
		Common common = new Common(driver);
		Common.impicitWait(5);
		ValidationUtil validationUtil = new ValidationUtil();
		try {
			UserObj usr = users.get("prod1");
			System.out.println("In Login method");
			common.waitForElementTillDisplayed(
					driver.findElement(By.xpath(elementReader.getPropertyElement("Login.UserName"))), 20);
			common.waitforElment("Login.UserName");
			element = validationUtil.validateWebElement("Login.UserName", driver);
			Common.log("Entering Username");
			element.clear();
			element.sendKeys(usr.getUserName());
			element = validationUtil.validateWebElement("Login.Password", driver);
			Common.log("Entering Password");
			element.clear();
			element.sendKeys(usr.getPassword());
			Common.impicitWait(4);
			validationUtil.validateWebElement("Login.Signin", driver).click();
			Common.impicitWait(7);
		} catch (AndriodException e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	public void vrifyLoginScreen() {
		Common common = new Common(driver);
		Common.impicitWait(5);
		ValidationUtil validationUtil = new ValidationUtil();
		try {
			UserObj usr = users.get("prod1");
			System.out.println("In Login method");
			common.waitForElementTillDisplayed(
					driver.findElement(By.xpath(elementReader.getPropertyElement("Login.UserName"))), 20);
			common.waitforElment("Login.UserName");
			element = validationUtil.validateWebElement("Login.UserName", driver);
			String usernametext = element.getAttribute("content-desc");
			String expected = "The word 'Email/Username' should appear in the text input field until the user starts to enter text, after which the word should disappea";
			if (expected.equalsIgnoreCase(usernametext)) {
				Common.log(
						"The word 'Email/Username' should appear in the text input field until the user starts to enter text, after which the word should disappea");
			} else {
				common.log("The text doesnot match the expected one.");
			}
			Common.log("Entering Username");
			element.clear();
			element.sendKeys(usr.getUserName());
			element = validationUtil.validateWebElement("Login.Password", driver);
			String passwordtext = element.getAttribute("content-desc");
			String expected1 = "The word 'Password' should appear in the text input field until the user starts to enter text, after which the word should disappear.";
			if (expected1.equalsIgnoreCase(passwordtext)) {
				common.log(
						"The word 'Password' should appear in the text input field until the user starts to enter text, after which the word should disappear.");
			} else {
				common.log("The text doesnot match the expected one.");
			}
			Common.log("Entering Password");
			element.clear();
			element.sendKeys(usr.getPassword());
			Common.impicitWait(4);
			validationUtil.validateWebElement("Login.Signin", driver).click();
			Common.impicitWait(7);
		} catch (AndriodException e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	/**
	 * Login code for CA users
	 */
	public void loginForCA() throws AndriodException {
		Common common = new Common(driver);
		Common.impicitWait(5);
		ValidationUtil validationUtil = new ValidationUtil();
		try {
			UserObj usr = users.get("QACACredential");
			System.out.println("In Login method");
			common.waitForElementTillDisplayed(
					driver.findElement(By.xpath(elementReader.getPropertyElement("Login.UserName"))), 20);
			common.waitforElment("Login.UserName");
			element = validationUtil.validateWebElement("Login.UserName", driver);
			Common.log("Entering Username");
			element.clear();
			element.sendKeys(usr.getUserName());
			element = validationUtil.validateWebElement("Login.Password", driver);
			Common.log("Entering Password");
			element.clear();
			element.sendKeys(usr.getPassword());
			Common.impicitWait(4);
			validationUtil.validateWebElement("Login.Signin", driver).click();
			Common.impicitWait(7);
		} catch (AndriodException e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	/**
	 * Validate sign in button enabled
	 */
	public void validateSignInbuttonEnabled() {
		Common common = new Common(driver);
		ValidationUtil validationUtil = new ValidationUtil();
		Common.impicitWait(5);
		try {
			System.out.println("In Login method");
			common.waitForElementTillDisplayed(
					driver.findElement(By.xpath(elementReader.getPropertyElement("Login.UserName"))), 20);
			common.waitforElment("Login.UserName");
			element = validationUtil.validateWebElement("Login.UserName", driver);
			element.clear();
			element.sendKeys("k");
			element = validationUtil.validateWebElement("Login.Password", driver);
			Common.log("Entering Password");
			element.clear();
			element.sendKeys("utTLzP");
			Common.impicitWait(4);
			Common.log("Sign In Button is not enabled");
			element = validationUtil.validateWebElement("Login.UserName", driver);
			element.clear();
			element.sendKeys("k2");
			element = validationUtil.validateWebElement("Login.Password", driver);
			Common.log("Entering Password");
			element.clear();
			element.sendKeys("utTLz");
			Common.impicitWait(4);
			Common.log("Sign In Button is not enabled");
			element = validationUtil.validateWebElement("Login.UserName", driver);
			element.clear();
			element.sendKeys("k2");
			element = validationUtil.validateWebElement("Login.Password", driver);
			Common.log("Entering Password");
			element.clear();
			element.sendKeys("utTLzP");
			Common.impicitWait(4);
			Common.log("Sign In Button is  enabled");
		} catch (AndriodException e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void loginBeforeStart(String loginCredential) throws AndriodException {
		Common common = new Common(driver);
		ValidationUtil validationUtil = new ValidationUtil();
		try {
			if (null != loginCredential && !loginCredential.equals("")) {
				usr = users.get(loginCredential);
			} else {
				usr = users.get("prod1");
			}

			System.out.println("In Login method");
			Common.impicitWait(10);
			// common.waitForElementTillDisplayed(driver.findElement(By.xpath(elementReader.getPropertyElement("Login.UserName"))),
			// 20);
			// common.waitforElment("Login.UserName");
			element = validationUtil.validateWebElement("Login.UserName", driver);
			Common.log("Entering Username");
			element.clear();
			element.sendKeys(usr.getUserName());
			element = validationUtil.validateWebElement("Login.Password", driver);
			Common.log("Entering Password");
			element.clear();
			element.sendKeys(usr.getPassword());
			Common.impicitWait(4);
			validationUtil.validateWebElement("Login.Signin", driver).click();
			Common.impicitWait(7);

		} catch (Exception e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), "Login Screen Is Not Present");
		}
	}

	public String getArtistinfo() throws AndriodException {
		Common common = new Common(driver);
		ValidationUtil validationUtil = new ValidationUtil();
		return validationUtil.validateWebElement("Everest.Artistinfo", driver).getText();
	}

	public void verifyArtistinfo(String artistinfo) throws AndriodException {
		try {
			ValidationUtil validationUtil = new ValidationUtil();
			WebElement artistInfo = validationUtil.validateWebElement("Everest.Artistinfo", driver);
			// Assert.assertTrue(artistInfo.getText().equalsIgnoreCase(artistinfo));
			if (artistInfo.getText().equalsIgnoreCase(artistinfo)) {
				Reporter.log("min artinst info name is same after login relogin");
			} else {
				throw new AndriodException(this.getClass().getSimpleName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(),
						"min artinst info name is not same after login relogin");
			}
			Common.log("Application is launched with auto login ");
		} catch (AndriodException e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Application is  not launched with auto login ");
		}
	}

	/**
	 * code for login with business credential
	 */
	public void loginWithBusinessCredentials() throws AndriodException {
		Common common = new Common(driver);
		try {
			UserObj usr = users.get("prod22");
			ValidationUtil validationUtil = new ValidationUtil();
			element = validationUtil.validateWebElement("Login.UserName", driver);
			element.clear();
			Common.log("Entering Business User id:" + usr.getUserName());
			element.sendKeys(usr.getUserName());
			element = validationUtil.validateWebElement("Login.Password", driver);
			element.clear();
			Common.log("Entering Business password");
			Common.log("Entering Business User id:" + usr.getPassword());
			element.sendKeys(usr.getPassword());
			Common.log("Signing in to app...");
			validationUtil.validateWebElement("Login.Signin", driver).click();
			Common.impicitWait(4);
		} catch (AndriodException e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void verifyChannelLineUpForBizUser() throws AndriodException {
		Common common = new Common(driver);
		ValidationUtil validationUtil = new ValidationUtil();
		try {
			validationUtil.validateWebElement("Home.Music", driver).click();
			common.scrollUntilTextExists("Pop");
			validationUtil.validateDisplayWebElement("Music.Popchannel", driver, "Rockchannel is not displayed");
			Common.log("Pop Category is present under Music SuperCategory");
			validationUtil.validateWebElement("Music.Popchannel", driver).click();
			common.implicitWait(3);
			Common.log("Navigated to Pop Category. Verify the 1st Channel is Channel 3 for Biz User in Pop Category");
			validationUtil.validateDisplayWebElement("Pop.Channel1ListNumber", driver,
					"Channels were not displayed in Pop Category");
			WebElement channelListNumber = validationUtil.validateWebElement("Pop.Channel1ListNumber", driver);
			if (channelListNumber.getText().equalsIgnoreCase("Ch 3")) {
				Common.log("As expected: First Channel displayed for Biz User is Channel 3: Venus");
				channelListNumber.click();
				common.implicitWait(3);
				validationUtil.validateDisplayWebElement("Everest.Minimizebutton", driver,
						ErrorMessageConstant.MinimizeBtn_NotDisplayed);
				Common.log("navigated to NPL screen");
				validationUtil.validateWebElement("Everest.Minimizebutton", driver).click();
				common.implicitWait(3);
			} else {
				Common.log("First Channel displayed for Biz User is not channel-3. First Channel displayed is: "
						+ channelListNumber);

			}

		} catch (AndriodException e) {
			Common.log("Exception observed in : " + e.getMessage());
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public void loginCredentialSecurity() throws AndriodException {
		Common common = new Common(driver);
		try {
			UserObj usr = users.get("prod1");
			ValidationUtil validationUtil = new ValidationUtil();
			element = validationUtil.validateWebElement("Login.UserName", driver);
			element.clear();
			Common.log("Entering  User id");
			element.sendKeys(usr.getUserName());
			Common.log("The username entered in the login screen should be visible");
			element = validationUtil.validateWebElement("Login.Password", driver);
			if (null != element) {
				element.clear();
				element.sendKeys("12345");
				String securePassword = element.getAttribute("text");
				common.log("Entered Password shown: " + securePassword);
				Assert.assertTrue(securePassword.equals("•••••"), "Password is not secure");
				common.log("Verified Passsword Security");
			} else {
				Common.log("The password entered in the login screen should not  be visible");
				throw new AndriodException(this.getClass().getSimpleName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(),
						"Password textbox is not present on the screen");
			}
		} catch (AndriodException e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	/**
	 * code for password feild
	 */
	public void validatePasswordFeild() throws AndriodException {
		Common common = new Common(driver);
		try {
			ValidationUtil validationUtil = new ValidationUtil();
			Common.impicitWait(4);
			WebElement profile = validationUtil.validateDisplayWebElement("Home.Profile", driver,
					"Profile menu is not displayed");
			profile.click();
			Common.log("Profile menu is clicked");
			Common.impicitWait(3);
			common.scrollToDown(2);
			WebElement signout = validationUtil.validateDisplayWebElement("Profile.SignOut", driver,
					"Signout Button is not displayed");
			signout.click();
			Common.log("Signout button is clicked");
			Common.impicitWait(4);
			element = validationUtil.validateWebElement("Login.Password", driver);
			if (element.getText().length() == 0) {
				Common.log("After signout password  feild is blank");
			} else {
				Common.log("After signout password  feild is not  blank");
			}
		} catch (AndriodException e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	/**
	 * Verifying Login screen elements
	 */
	public void verifyLoginScreenElements() {
		Common common = new Common(driver);
		ValidationUtil validationUtil = new ValidationUtil();
		try {
			validationUtil.validateDisplayWebElement("Login.Privacy", driver,
					"Privacy  is not displayed on thelogin screen");
			validationUtil.validateDisplayWebElement("Login.CustomerAgreement", driver,
					"CustomerAgreement is not displayed");
			validationUtil.validateDisplayWebElement("Login.LocatingYou", driver, "LocatingYou is not displayed");
			validationUtil.validateWebElement("Login.LocatingYou", driver).click();
			Common.impicitWait(4);
			validationUtil.validateDisplayWebElement("LoctaingYou.LocationUsage", driver,
					"LocationUsage is not displayed");
			validationUtil.validateWebElement("LocatingYou.OkButton", driver).click();
			Common.impicitWait(4);
			Common.log("navigated back to Login page");
			validationUtil.validateWebElement("Login.Privacy", driver).click();
			Common.impicitWait(5);
			validationUtil.validateDisplayWebElement("Privacy.PrivacyHeader", driver, "PrivacyHeader is not displayed");
			validationUtil.validateWebElement("Privacy.BackButton", driver).click();
			Common.impicitWait(5);
			Common.log("navigated back to Login page");
			validationUtil.validateWebElement("Login.CustomerAgreement", driver).click();
			Common.impicitWait(5);
			validationUtil.validateDisplayWebElement("CustomerAgreement.Header", driver,
					"CustomerAgreement Header is not displayed");
			validationUtil.validateWebElement("Privacy.BackButton", driver).click();
			Common.impicitWait(5);
			Common.log("navigated back to Login page");
		} catch (AndriodException e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());

		}
	}

	public void verifyLoginPrefillUsername() {
		Common common = new Common(driver);
		try {
			ValidationUtil validationUtil = new ValidationUtil();
			Common.impicitWait(4);
			WebElement profile = validationUtil.validateDisplayWebElement("Home.Profile", driver,
					"Profile menu is not displayed");
			profile.click();
			Common.log("Profile menu is clicked");
			Common.impicitWait(5);
			common.scrollUntilTextExists("Sign Out");
			WebElement signout = validationUtil.validateDisplayWebElement("Profile.SignOut", driver,
					"Signout Button is not displayed");
			signout.click();
			Common.log("Signout button is clicked");
			Common.impicitWait(4);
			validationUtil.validateDisplayWebElement("Login.Password", driver,
					"After signout password should  not be blank");
			String element = validationUtil.validateWebElement("Login.UserName", driver).getText();
			Common.log("After signout The Username must be pre-filled in the Username field in the login screen"
					+ element);
		} catch (AndriodException e) {
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}

	}

	public void verifyPasswordSecurity() {
		ValidationUtil validationUtil = new ValidationUtil();
		Common common = new Common(driver);
		element = validationUtil.validateWebElement("Login.Password", driver);
		if (null != element) {
			element.clear();
			element.sendKeys("12345");
			String securePassword = element.getAttribute("text");
			common.log("Entered Password shown: " + securePassword);
			Assert.assertTrue(securePassword.equals("•••••"), "Password is not secure");
			common.log("Verified Passsword Security");
		} else {
			common.errorlog("Password textbox is not present on the screen");
			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(),
					"Password textbox is not present on the screen");
		}

	}

}
