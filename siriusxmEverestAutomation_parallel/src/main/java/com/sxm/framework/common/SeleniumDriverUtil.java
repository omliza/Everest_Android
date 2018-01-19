package com.sxm.framework.common;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Reporter;

import com.gargoylesoftware.htmlunit.javascript.configuration.BrowserName;
import com.sxm.framework.dto.Envirnoment;
import com.sxm.framework.dto.handler.EnvirnomentHandler;

/**
 * This class contains Returns Driver Object and will be called for web test
 * cases environment test cases has to be executed
 * 
 * @author pawankumar_p
 */
public class SeleniumDriverUtil {
    private static String URL;

    private static Envirnoment env = EnvirnomentHandler.getInstance()
            .getEnvirnoment();
    private WebDriver driver;

    /**
     * @return
     */
    public WebDriver getDriver() {
        // Reporter.log("in BROWSER loop",true);
        DesiredCapabilities desiredCapabilities = null;
		String os = System.getProperty("os.name").toLowerCase().substring(0, 3);
		System.out.println("OS NAME"+System.getProperty("os.name"));
		System.out.println("OS::"+os);
		if (os.equals("mac")) {
			if (env.getBrowser().equalsIgnoreCase(
					"chrome")) {
				 if (driver == null) {
		                if (!env.isLocal()) {
		                    String FILEPATH = "src/test/resources/chromedriver.exe";
		                    FILEPATH = System.getProperty("user.dir") + "/" + FILEPATH;
		                    File file = new File(FILEPATH);
		                    System.setProperty("webdriver.chrome.driver",
		                    		FILEPATH);
		                    try {
		        	            desiredCapabilities = new DesiredCapabilities().chrome();
		        	            desiredCapabilities.setBrowserName("googlechrome");
		        	            desiredCapabilities.setPlatform(Platform.WINDOWS);
		                    driver = new RemoteWebDriver(new java.net.URL(
	                                env.getGridURL()),desiredCapabilities);
		                    }catch(Exception e){
		                    	e.printStackTrace();
		                    }
		                }else{
		                    String FILEPATH = "src/test/resources/chromedriver";
		                    FILEPATH = System.getProperty("user.dir") + "/" + FILEPATH;
		                	System.setProperty("chrome.binary",FILEPATH);
		                	driver = new ChromeDriver();
		                }
			} 
			}else if (env.getBrowser().equalsIgnoreCase(
					"firefox")) {
	            // TODO
	            desiredCapabilities = new DesiredCapabilities();
	            desiredCapabilities.setBrowserName("firefox");
	            desiredCapabilities.setPlatform(Platform.WINDOWS);

	            FirefoxProfile profile = new FirefoxProfile();
	            profile.setPreference("BROWSER.cache.disk.enable", false);
	            profile.setPreference("BROWSER.cache.memory.enable", false);
	            profile.setPreference("BROWSER.cache.offline.enable", false);
	            profile.setPreference("network.http.use-cache", false);
	            if (driver == null) {
	                if (!env.isLocal()) {
	                    try {
	                        driver = new RemoteWebDriver(new java.net.URL(
	                                env.getGridURL()), desiredCapabilities);
	                    } catch (MalformedURLException e) {
	                        e.printStackTrace();
	                    }
	                } else {
	                    driver = new FirefoxDriver(profile);
	                }

	            } else
	                return driver;

	        
			} else if (env.getBrowser().equalsIgnoreCase(
					"safari")) {
				driver = new SafariDriver();
			}
		}else{
        if (env.getBrowser().equalsIgnoreCase("firefox")) {
            // TODO
            desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setBrowserName("firefox");
            desiredCapabilities.setPlatform(Platform.WINDOWS);

            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("BROWSER.cache.disk.enable", false);
            profile.setPreference("BROWSER.cache.memory.enable", false);
            profile.setPreference("BROWSER.cache.offline.enable", false);
            profile.setPreference("network.http.use-cache", false);
            if (driver == null) {
                if (!env.isLocal()) {
                    try {
                        driver = new RemoteWebDriver(new java.net.URL(
                                env.getGridURL()), desiredCapabilities);
                    } catch (MalformedURLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else {
                    driver = new FirefoxDriver(profile);
                }

            } else
                return driver;

        } else if (env.getBrowser().equalsIgnoreCase("ie")) {
            if (driver == null) {
                String FILEPATH = "src/test/resources/IEDriverServer.exe";
                FILEPATH = System.getProperty("user.dir") + "/" + FILEPATH;
                File file = new File(FILEPATH);
                System.setProperty("webdriver.ie.driver",
                        file.getAbsolutePath());
                desiredCapabilities = DesiredCapabilities.internetExplorer();
                desiredCapabilities
                        .setCapability(
                                InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
                                true);
                desiredCapabilities.setCapability(
                        InternetExplorerDriver.INITIAL_BROWSER_URL,
                        "http://player-k2int4.mountain.siriusxm.com/#/login");

                desiredCapabilities.setCapability(
                        CapabilityType.ACCEPT_SSL_CERTS, true);
                desiredCapabilities.setJavascriptEnabled(true);
                desiredCapabilities.setCapability("requireWindowFocus", true);
                desiredCapabilities.setCapability("enablePersistentHover",
                        false);
                desiredCapabilities.setCapability("EnableNativeEvents", false);

                driver = new InternetExplorerDriver(desiredCapabilities);
            } else
                return driver;

        } else if (env.getBrowser().equalsIgnoreCase("chrome")) {

			 if (driver == null) {
	                if (!env.isLocal()) {
	                    String FILEPATH = "src/test/resources/chromedriver.exe";
	                    FILEPATH = System.getProperty("user.dir") + "/" + FILEPATH;
	                    File file = new File(FILEPATH);
	                    System.setProperty("webdriver.chrome.driver",
	                            file.getAbsolutePath());
	                	System.setProperty("chrome.binary",FILEPATH);
	                    try {
	                    driver = new RemoteWebDriver(new java.net.URL(
                               env.getGridURL()),DesiredCapabilities.chrome());
	                    }catch(Exception e){
	                    	e.printStackTrace();
	                    }
	                }else{
	                    String FILEPATH = "src/test/resources/chromedriver.exe";
	                    FILEPATH = System.getProperty("user.dir") + "/" + FILEPATH;
	                    File file = new File(FILEPATH);
	                    System.setProperty("webdriver.chrome.driver",
	                            file.getAbsolutePath());
	                	driver = new ChromeDriver();
	                }
		} 
		
//            if (driver == null) {
//                String FILEPATH = "src/test/resources/chromedriver.exe";
//                FILEPATH = System.getProperty("user.dir") + "/" + FILEPATH;
//                File file = new File(FILEPATH);
//                System.setProperty("webdriver.chrome.driver",
//                        file.getAbsolutePath());
//
//                driver = new ChromeDriver();
//            }
        } else if (env.getBrowser().equalsIgnoreCase("qnx")) {

            ChromeOptions options = new ChromeOptions();
            // set some options
            DesiredCapabilities dc = DesiredCapabilities.chrome();
            dc.setCapability("browserStartWindow", "*");

            try {
                driver = new RemoteWebDriver(new URL(env.getGridURL()), dc);
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        else if (env.getBrowser().equalsIgnoreCase("safari")) {
            if (driver == null)
                driver = new SafariDriver();
            return driver;
        }
		}
        return driver;

    }

    public static String getLoginURL() {
        Reporter.log("URL is:" + URL, true);
        return URL;

    }
}