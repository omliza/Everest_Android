package com.sxm.mobile.tests;

import java.util.Map;
import java.util.logging.Logger;

import org.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.selenium.mobile.base.RefactorMobileTestCase;
import com.sxm.framework.common.Screenshot;
import com.sxm.framework.dto.UserObj;
import com.sxm.framework.dto.handler.UserHandler;
import com.sxm.framework.exception.AndriodException;
import com.sxm.mobile.pages.Common;

/**
 * @author aparajita
 *
 */
@Listeners({ Screenshot.class })
public class LoginTest extends RefactorMobileTestCase {
	private final static Logger LOGGER = Logger.getLogger(LoginTest.class.getName());
	private Map<String, UserObj> users = UserHandler.getInstance(locale, env).getUserMap();
	private String referenceurl;
	public UserObj usr = null;
	/*
	 * MOBANDEVER-688
	 * EVQAAND-232: MOBANDEVER-525: validating Login - Enter  wrong username and password to Sign In  
	 */
	@Test(suiteName="Login", testName = "Login - Enter username and password to Sign In - Fail", description = "Login - Enter username and password to Sign In - Fail", enabled = true, 
			priority=1,groups = {"EVQAAND-232" })
	public void loginWithWrongCredential() throws AndriodException{
		Common common = new Common(driver);
		Common.log("Login - Enter username and password to Sign In - Fail-- started EVQAAND-232: MOBANDEVER-525");
		try{
		common.resetApp();
		driver.launchApp();
		getPageFactory().getLogin().loginWithWrongCredentials();
		getPageFactory().getLogin().verifyErrorMessage("We don’t recognize your username or password. Please try again.");
		getPageFactory().getLogin().wrongCredential();
		}catch (AndriodException ex) {		
			Common.errorlog("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());			
			Assert.fail("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());	
	    }
	}
	
	/*
	 * MOBANDEVER-689
	 * EVQAAND-207: MOBANDEVER-525: Validate whether we are able to login successfully post getting the error message for incorrect login
	 */
	@Test(suiteName="Login", testName = "EVQAAND-207: MOBANDEVER-525:Login - Validate Successful Login after Error message", description = "Validate whether we are able to login successfully post getting the error message for incorrect login", enabled = true, priority=1,groups = {
	"EVQAAND-207" })
	public void verifyloginWithWrongCredential()throws AndriodException {
		Common common = new Common(driver);
		Common.log("EVQAAND-207: MOBANDEVER-525:Login - Validate Successful Login after Error message");
		try{
		common.resetApp();
		driver.launchApp();
		getPageFactory().getLogin().loginWithWrongCredentials();
		getPageFactory().getLogin()
			.verifyErrorMessage("We don’t recognize your username or password. Please try again.");
		getPageFactory().getLogin().login();
		Common.log("Logged in succesfully");
		}catch (AndriodException ex) {		
			Common.errorlog("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());			
			Assert.fail("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());	
	    }
	}
	
	/*
	 * MOBANDEVER-758 : TBD - this test case is redundant and needs to be removed
	 * Validate the login for us app with valid credential
	 */
	@Test(suiteName="Login" ,testName = "Validate the login for US app", description = "Validate the login for US app", enabled = true, groups = {
			"MOBANDEVER-758" })
	public void loginWithValidCredential() throws AndriodException{
		Common common = new Common(driver);
		Common.log("Login with valid credential MOBANDEVER-758");
		try{
			common.resetApp();
		driver.launchApp();
		getPageFactory().getLogin().login();
		Common.log("Logged in succesfully");
		}catch (AndriodException ex) {		
			Common.errorlog("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());			
			Assert.fail("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());	
	    }
	}
	
	/*
	 * MOBANDEVER-666 [OLD Test Case Id]
 	 * EVQAAND-47: MOBANDEVER-517: Validate Login - Auto-login  
	 */
	@Test(suiteName="Login" ,testName = "Login - Validate Auto-login", description = "Validate Login - Auto-login", enabled = true, 
			priority=1,groups = {"EVQAAND-47" })
	public void loginAutologin()throws AndriodException {
		Common common = new Common(driver);
		Common.log("Login with valid credential -> EVQAAND-47: MOBANDEVER-517:");
		try{
		common.resetApp();
		driver.launchApp();
		getPageFactory().getLogin().login();
		Common.log("Logged in succesfully");
		String string = getPageFactory().getLogin().getArtistinfo();
		Common.impicitWait(5);
		common.reLaunchApp();
		Common.impicitWait(5);
		getPageFactory().getLogin().verifyArtistinfo(string);
		}catch (AndriodException ex) {		
			Common.errorlog("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());			
			Assert.fail("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());	
	    }
	}
	
	/*
	* MOBANDEVER-515 : This test case fails currently
 	* EVQAAND-284: MOBANDEVER-515: Verify whether user is able to login with Business credentials and view the channel lineup for user's subscription package
	*/
	@Test(suiteName="Login" ,testName="Login - Music For Business (MFB) Login", description = "Validate the login with Busines Credential",
			enabled=true, priority=1,groups={"EVQAAND-284"})
	public void loginWithBusinessCredential() throws AndriodException{
		Common common = new Common(driver);
		Common.log(" Validating Login with Business Credential -> EVQAAND-284: MOBANDEVER-515");
		try{
		common.resetApp();
		driver.launchApp();
		getPageFactory().getLogin().loginWithBusinessCredentials();
		Common.log("User signed in with Business Credential - Success");
		Common.log("Verify Channel Lineup for Biz User");
		getPageFactory().getLogin().verifyChannelLineUpForBizUser();
		Common.log("Channel Lineup correctly displayed for Biz User");
	}catch (AndriodException ex) {		
		Common.errorlog("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());			
		Assert.fail("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());	
		}
	}
	
	/*
	 * EVQAAND-330: MOBANDEVER-521: Verifying Login Credential Security
	 */
	@Test(suiteName="Login" ,testName="Login - Credentials Security",description="Login - Credentials Security",
			enabled=true,priority=2, groups={"EVQAAND-330"})
	public void validateCredentialSecurity() throws AndriodException{
		Common common = new Common(driver);
		Common.log("Verifying Login Credential Security -> EVQAAND-330: MOBANDEVER-521:");
		try{
		common.resetApp();
		driver.launchApp();
		getPageFactory().getLogin().loginCredentialSecurity();
		}catch (AndriodException ex) {		
			Common.errorlog("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());			
			Assert.fail("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());	
	    }
	}
	
	/*
	 * EVQAAND-236: MOBANDEVER-524: Validate the login for US app
	 */
	@Test(suiteName="Login" ,testName="Validate the login for US app",
			enabled=true,priority=2,groups={"EVQAAND-236"})
	public void validateUsLogin() throws AndriodException{
		Common common = new Common(driver);
		Common.log("Verifying the Login for US app -> EVQAAND-236: MOBANDEVER-524");
		try{
		common.resetApp();
		driver.launchApp();
		getPageFactory().getLogin().login();
		Common.log("Logged in succesfully");
		}catch (AndriodException ex) {		
			Common.errorlog("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());			
			Assert.fail("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());	
	    }
	}
	
	/*
	 * EVQAAND-212 : This test will fail in US app
	 * Validate the login for CA app
	 */
	@Test(suiteName="Login" ,testName="Validate the login for CA app",enabled=true,priority=2,groups={"EVQAAND-212"})
	public void validateCALogin() throws AndriodException{
		Common common = new Common(driver);
		Common.log("Verifying the Login for CA app EVQAAND-212: MOBANDEVER-524");
		try{
		common.resetApp();
		driver.launchApp();
		getPageFactory().getLogin().loginForCA();
	/*	getPageFactory().getLogin()
		.verifyErrorMessage("We don’t recognize your username or password. Please try again.");*/
		Common.log("Logged in succesfully for CA users");
		}catch (AndriodException ex) {		
			Common.errorlog("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());			
			Assert.fail("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());	
	    }
	}
	
	/*
	 * MOBANDEVER-655
	 * EVQAAND-244: MOBANDEVER-514: Validating  the password field is cleared when the application is logged out MOBANDEVER-655
	 */
	@Test(suiteName="Login" ,testName="Validate whether the password field is cleared when the application is logged out",description="the password field is cleared when the application is logged out",
			enabled=true,priority=1,groups={"EVQAAND-244"})
	public void verifyPasswordFeild() throws AndriodException{
		Common common = new Common(driver);
	Common.log("Validating  the password field is cleared when the application is logged out EVQAAND-244: MOBANDEVER-514");
	try{
	common.resetApp();
	driver.launchApp();
	getPageFactory().getLogin().login();
	getPageFactory().getLogin().validatePasswordFeild();
	}catch (AndriodException ex) {		
		Common.errorlog("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());			
		Assert.fail("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());	
    }
	}
	
	/*
	 * MOBANDEVER-216 [Old Test case Id]
	 * EVQAAND-121:MOBANDEVER-510: validate Login - Select Privacy, Customer Agreement, Locating You links
	 */
	@Test(suiteName="Login" ,testName="Login - Select Privacy, Customer Agreement, Locating You links",
			enabled=true,priority=2,groups={"EVQAAND-121"})
	public void validateLoginElements(){
		Common common = new Common(driver);
		Common.log("Select Privacy, Customer Agreement, Locating You links EVQAAND-121:MOBANDEVER-510");
		try{
		common.resetApp();
		driver.launchApp();
		Common.impicitWait(3);
		getPageFactory().getLogin().verifyLoginScreenElements();
	}catch (AndriodException ex) {		
		Common.errorlog("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());			
		Assert.fail("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());	
    }
		
	}
	
	/*
	 * MOBANDEVER-511
	 * EVQAAND-202: MOBANDEVER-511 :Verifying Login - Enter username and password to Sign In - Success MOBANDEVER-511 
	 */
	@Test(suiteName="Login" ,testName = "Login - Enter username and password to Sign In - Success", description = "Login - Enter username and password to Sign In - Success", enabled = true, 
			priority=1,groups = {"EVQAAND-202" })
		public void loginWithRightCredential(){
		Common common = new Common(driver);
		Common.log("Verifying Login - Enter username and password to Sign In - Success -> EVQAAND-202: MOBANDEVER-511  ");
		try{
		common.resetApp();
		driver.launchApp();
		getPageFactory().getLogin().vrifyLoginScreen();
		Common.log("Sucessfully logged into the app");
		}catch (AndriodException ex) {		
			Common.errorlog("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());			
			Assert.fail("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());	
	    }
	}
	
	/*
	 * EVQAAND-46: MOBANDEVER-511: Login - SignIn button should be enabled only when user enters minimum 2 characters in username and 6 characters in password
	 */
	@Test(suiteName="Login" ,testName="Login - SignIn button should be enabled",description="Login - SignIn button should be enabled only when user enters minimum 2 characters in username and 6 characters in password",
			enabled=true,priority=2, groups={"EVQAAND-46"})
	public void validateLoginSignInButtonEnabled() throws AndriodException{
		Common common = new Common(driver);
		Common.log("Verifying Login - SignIn button should be enabled EVQAAND-46: MOBANDEVER-511");
		try{
		common.resetApp();
		driver.launchApp();
		getPageFactory().getLogin().validateSignInbuttonEnabled();
		}catch (AndriodException ex) {		
			Common.errorlog("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());			
			Assert.fail("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());	
	    }
	}
	
	/*
	 * EVQAAND-81: MOBANDEVER-514:Validate Login - Pre-fill Username after logging out
	 */
	@Test(suiteName="Login" ,testName="Login - Pre-fill Username after logging out",description="Login - Pre-fill Username after logging out",
			enabled=true,priority=2,groups={"EVQAAND-81"})
	public void validateLoginPrefillUsername() throws AndriodException{
		Common common = new Common(driver);
		Common.log("Verifying Login - Pre-fill Username after logging out -> EVQAAND-81: MOBANDEVER-514");
		try{
		common.resetApp();
		driver.launchApp();
		getPageFactory().getLogin().login();
		getPageFactory().getLogin().verifyLoginPrefillUsername();
		}catch (AndriodException ex) {		
			Common.errorlog("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());			
			Assert.fail("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());	
	    }
	}
	
	/*
	 * Note: This test is currently in progress.
	 * EVQAAND-268: Login - Enter username and password to Sign In - Error messages
	 */
	@Test(suiteName="Login", testName = "Login - Enter username and password to Sign In - Error messages", description = "Login - Enter username and password to Sign In - Error messages", enabled = true, 
			priority=2,groups = {"EVQAAND-268" })
	public void verifyloginWithWrongCredentialErrorMsg()throws AndriodException {
		Common common = new Common(driver);
		Common.log("EVQAAND-268: Login - Enter username and password to Sign In - Error messages");
		try{
		common.resetApp();
		driver.launchApp();
		getPageFactory().getLogin().loginWithWrongCredentialsmultipletimes(4, "We don’t recognize your username or password. Please try again.");
//		getPageFactory().getLogin()
//			.verifyErrorMessage("We don’t recognize your username or password. Please try again.");
		getPageFactory().getLogin().loginWithWrongCredentialsmultipletimes(10,"Sorry, we can't find that Username or Password. Please try again. Remember, Usernames and Passwords are case sensitive.");
		//getPageFactory().getLogin().login();
		//Common.log("Logged in succesfully");
		}catch (AndriodException ex) {		
			Common.errorlog("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());			
			Assert.fail("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());	
	    }
	}
	
	/*
	 * TBD: This test case is to be added
	 * Validate user gets signed-out if Sign-out while audio is playing
	 * After sign out - Kill the app and launch it. This should open Login screen
	 */
	@Test(suiteName="Login" ,testName="Login - Sign out while Audio is been played, then relaunch the app should ask for login",description="Login - Sign out while Audio is been played, then relaunch the app should ask for login",
			enabled=true,priority=2,groups={"EVQAAND-0000"})
	public void validateLoginAfterSignOutWhileAudioIsPlayed() throws AndriodException{
		Common common = new Common(driver);
		Common.log("Verifying Login - Sign out while Audio is been played, then relaunch the app should ask for login");
		try{
		common.resetApp();
		driver.launchApp();
		getPageFactory().getLogin().login();
		
		// Tune to a channel
		getPageFactory().getEvehome().clickonMusic();
		common.scrollUntilTextExists("Dance/Electronic");
		getPageFactory().getEvehome().clickMucisSubRock();
		getPageFactory().getEvehome().popChannel1();
		getPageFactory().getEvehome().clickminimize();
		// Sign out of app
		getPageFactory().getProfile().profileSignOut();
		driver.closeApp(); // Close the app
		driver.launchApp(); //Launch the app
		
		// Login again to app
		getPageFactory().getLogin().login();
		// Verify user is able to navigate to NPL
		getPageFactory().getEvehome().miniBar();
		getPageFactory().getEvehome().testMiniBarNavigation();
		}catch (AndriodException ex) {		
			Common.errorlog("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());			
			Assert.fail("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());	
	    }
	}
	
	
	@Test(suiteName="Login" ,testName="Validate whether the user is able to login after signing out from the app",description="Validate whether the user is able to login after signing out from the app",
			enabled=true,priority=2,groups={"EVQAAND-117"})
	public void validateLoginAfterSignout()throws AndriodException{
		Common common = new Common(driver);
		common.log("Validate whether the user is able to login after signing out from the app EVQAAND-117");
		try{
			common.resetApp();
			driver.launchApp();
			getPageFactory().getLogin().login();
			getPageFactory().getLogin().validatePasswordFeild();
			getPageFactory().getLogin().login();
		}catch (AndriodException ex) {		
			Common.errorlog("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());			
			Assert.fail("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());	
	    }
		
	}
}









