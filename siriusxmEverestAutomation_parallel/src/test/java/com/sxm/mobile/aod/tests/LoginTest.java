package com.sxm.mobile.aod.tests;

import java.util.Map;
import java.util.logging.Logger;

import org.junit.Assert;
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

	/**
	 * MOBANDEVER-688
	 * validating Login - Enter  wrong username and password to Sign In 
	 */
	@Test(suiteName="Login", testName = "Login - Enter username and password to Sign In - Fail", description = "Login - Enter username and password to Sign In - Fail", enabled = true, groups = {
			"MOBANDEVER-688" })
	public void loginWithWrongCredential() throws AndriodException{
		Common common = new Common(driver);
		Common.log("Login - Enter username and password to Sign In - Fail-- started MOBANDEVER-688");
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
	
	
	
	/**
	 * MOBANDEVER-689
	 * Validate whether we are able to login successfully post getting the error message for incorrect login
	 */
	@Test(suiteName="Login", testName = "Login - Enter username and password to Sign In - Fail", description = "Validate whether we are able to login successfully post getting the error message for incorrect login", enabled = true, groups = {
	"MOBANDEVER-689" })
	public void verifyloginWithWrongCredential()throws AndriodException {
		Common common = new Common(driver);
		Common.log("Login - Enter username and password to Sign In - Fail-- started MOBANDEVER-689");
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
	
	

	/**
	 * MOBANDEVER-758
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
	
	

	/**
	 * MOBANDEVER-666
	 * Validate Login - Auto-login 
	 */
	@Test(suiteName="Login" ,testName = "Login - Auto-login", description = "Validate Login - Auto-login", enabled = true, groups = {
			"MOBANDEVER-666" })
	public void loginAutologin()throws AndriodException {
		Common common = new Common(driver);
		Common.log("Login with valid credential MOBANDEVER-666");
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
	
	
	/**
	 * MOBANDEVER-515
	 * Validate the login with Busines Credential
	 */
	@Test(suiteName="Login" ,testName="Login - Music For Business (MFB) Login", description = "Validate the login with Busines Credential",enabled=true, groups={"MABANDEVER-515"})
	public void loginWithBusinessCredential() throws AndriodException{
		Common common = new Common(driver);
		Common.log(" Validating Login with Business Credential MOBANDEVER-515");
		try{
		common.resetApp();
		driver.launchApp();
		getPageFactory().getLogin().loginWithBusinessCredentials();
		getPageFactory().getLogin().verifyChannelLineUpForBizUser();
	}catch (AndriodException ex) {		
		Common.errorlog("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());			
		Assert.fail("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());	
		}
	}
	
	
	
	/**
	 * MOBANDEVER-774
	 * Verifying Login Credential Security
	 */
	@Test(suiteName="Login" ,testName="Login - Credentials Security",description="Login - Credentials Security",enabled=true,groups={"MOBANDEVER-774"})
	public void validateCredentialSecurity() throws AndriodException{
		Common common = new Common(driver);
		Common.log("Verifying Login Credential Security MOBANDEVER-774");
		try{
		common.resetApp();
		driver.launchApp();
		getPageFactory().getLogin().loginCredentialSecurity();
		}catch (AndriodException ex) {		
			Common.errorlog("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());			
			Assert.fail("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());	
	    }
	}
	
	/**
	 * MOBANDEVER-758
	 * @Validate the login for US app
	 */
	@Test(suiteName="Login" ,testName="Validate the login for US app",enabled=true,groups={"MOBANDEVER-758"})
	public void validateUsLogin() throws AndriodException{
		Common common = new Common(driver);
		Common.log("Verifying the Login for US app MOBANDEVER-758");
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
	
	/**
	 * EVQAAND-236
	 * Validate the login for CA app
	 */
	@Test(suiteName="Login" ,testName="Validate the login for CA app",enabled=false,groups={"EVQAAND-236"})
	public void validateCALogin() throws AndriodException{
		Common common = new Common(driver);
		Common.log("Verifying the Login for CA app EVQAAND-236: MOBANDEVER-524");
		try{
		common.resetApp();
		driver.launchApp();
		getPageFactory().getLogin().loginForCA();
		Common.log("Logged in succesfully for CA users");
		}catch (AndriodException ex) {		
			Common.errorlog("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());			
			Assert.fail("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());	
	    }
	}
	
	
	
	
	
	
	
	/**
	 * MOBANDEVER-655
	 *Validating  the password field is cleared when the application is logged out MOBANDEVER-655
	 */
	@Test(suiteName="Login" ,testName="Validate whether the password field is cleared when the application is logged out",description="the password field is cleared when the application is logged out",enabled=true,groups={"MOBANDEVER-655"})
	public void verifyPasswordFeild() throws AndriodException{
		Common common = new Common(driver);
	Common.log("Validating  the password field is cleared when the application is logged out MOBANDEVER-655");
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
	
	
	
	
	/**
	 * MOBANDEVER-216
	 *  validate Login - Select Privacy, Customer Agreement, Locating You links
	 */
	@Test(suiteName="Login" ,testName="Login - Select Privacy, Customer Agreement, Locating You links",enabled=true,groups={"MOBANDEVER-216"})
	public void validateLoginElements(){
		Common common = new Common(driver);
		Common.log("Select Privacy, Customer Agreement, Locating You links MOBANDEVER 216");
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
	
	
	
	/**
	 * MOBANDEVER-511
	 * Verifying Login - Enter username and password to Sign In - Success MOBANDEVER-511 
	 */
	@Test(suiteName="Login" ,testName = "Login - Enter username and password to Sign In - Success", description = "Login - Enter username and password to Sign In - Fail", enabled = true, groups = {
	"MOBANDEVER-688" })
		public void loginWithRightCredential(){
		Common common = new Common(driver);
		Common.log("Verifying Login - Enter username and password to Sign In - Success MOBANDEVER-511 ");
		try{
		common.resetApp();
		driver.launchApp();
		getPageFactory().getLogin().login();
		Common.log("Sucessfully logged into the app");
		}catch (AndriodException ex) {		
			Common.errorlog("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());			
			Assert.fail("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());	
	    }
	}
	
	@Test(suiteName="Login" ,testName="Login - SignIn button should be enabled",description="Login - SignIn button should be enabled only when user enters minimum 2 characters in username and 6 characters in password",
			enabled=true,groups={"MOBANDEVER-511"})
	public void validateLoginSignInButtonEnabled() throws AndriodException{
		Common common = new Common(driver);
		Common.log("Verifying Login - SignIn button should be enabled MOBANDEVER-511");
		try{
		common.resetApp();
		driver.launchApp();
		getPageFactory().getLogin().validateSignInbuttonEnabled();
		}catch (AndriodException ex) {		
			Common.errorlog("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());			
			Assert.fail("Exception occured in : "+ this.getClass().getSimpleName()+" : "+ ex.getMessage());	
	    }
	}
	
	@Test(suiteName="Login" ,testName="Login - Pre-fill Username after logging out",description="Login - Pre-fill Username after logging out",enabled=true,groups={"MOBANDEVER-514"})
	public void validateLoginPrefillUsername() throws AndriodException{
		Common common = new Common(driver);
		Common.log("Verifying Login - Pre-fill Username after logging out MOBANDEVER-514");
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
	
	
	
	
	
	
	
}









