package com.selenium.mobile.base;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;

import com.sxm.framework.common.AppiumDriverUtil;
import com.sxm.framework.common.ExcelUtil;
import com.sxm.framework.common.TestSession;
import com.sxm.framework.common.WriteExcel;
import com.sxm.framework.dto.Envirnoment;
import com.sxm.framework.dto.UserObj;
import com.sxm.framework.dto.handler.EnvirnomentHandler;
import com.sxm.framework.dto.handler.UserHandler;
import com.sxm.framework.exception.InValidOSException;
import com.sxm.framework.exception.InValidToolException;
import com.sxm.framework.utility.PropertyElementReader;
import com.sxm.mobile.common.TestdroidDeviceRunService;
import com.sxm.mobile.pages.Common;
import com.sxm.mobile.pages.Logfiledata;
import com.sxm.mobile.pages.factory.SXMMobilePageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.ios.IOSDriver;
import jxl.write.WritableWorkbook;

/**
 * Base class for all mobile tests
 * 
 * @author subramanyamp
 *
 */
public class MobileTestCase {

    protected AppiumDriver driver;
    private AppiumDriverUtil appiumutil = new AppiumDriverUtil();
    
    private ExcelUtil ds;
    private WritableWorkbook workbook;
    private TestSession session;
    private SXMMobilePageFactory pageFactory;
    private Logfiledata file;
    private String platform;
    private Common common;
     
    public static  UserObj usr =null;
    private String locale = EnvirnomentHandler.getInstance().getEnvirnoment().getLocale();
    private String env = EnvirnomentHandler.getInstance().getEnvirnoment().getEnv();
    private PropertyElementReader elementReader = PropertyElementReader
            .getInstance(locale);
  
    private Map<String, UserObj> users = UserHandler.getInstance(locale, env).getUserMap();
    
    @Parameters("platform")
    @org.testng.annotations.BeforeClass(alwaysRun = true)
    public void initializeDrivers(String plt, ITestContext context) throws InValidOSException,
            InValidToolException, IOException { 
        System.out.println(locale);
        System.out.println(elementReader.getPropertyElement("Login.UserName"));
        System.out.println(elementReader.getPropertyElement("Login.Password"));
        System.out.println(users.get("prod1").getUserName());     
        platform = plt;
        if (platform.equalsIgnoreCase("android")) {
            System.out.println("In android");
            driver = appiumutil.getAndroidDriver();
            
            context.setAttribute("driver", driver);

        } else if (platform.equalsIgnoreCase("IOS")) {
            driver = appiumutil.getIOSDriver();
        }
        else if (platform.equalsIgnoreCase("Perfecto_Android")) {
        	System.out.println("In Perfecto");
            driver = appiumutil.getPerfectoAndroidDriver("");
			context.setAttribute("driver", driver);
        }
        else if (platform.equalsIgnoreCase("Perfecto_IOS")) {
            driver = appiumutil.getPerfectoiOSDriver();
        }
        pageFactory = new SXMMobilePageFactory(driver);
        common = new Common(driver);
        Common.impicitWait(10);
        // TODO below should move to some common class
        /*try {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            
			driver.findElement(By.id(elementReader.getPropertyElement("Login.LoginButtonMain"))).click();
		} catch (Exception e) {
             e.printStackTrace();
		}*/
        System.out.println("---------------------1111111111111"+driver);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        int index = 0;
        Envirnoment  env = EnvirnomentHandler.getInstance().getEnvirnoment();
        
        System.out.println(env.getTestdroiduser() + "--" + "--"+env.getTestdroidproj());
        if (EnvirnomentHandler.getInstance().getEnvirnoment().isTestdroid()) {
            try {
                index = new TestdroidDeviceRunService(
                        env.getTestdroiduser(),
                        env.getTestdroidpass(), env.getTestdroidproj())
                        .getDeviceRunIndex() + index;
                System.out.println("index no " + index);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        Common.impicitWait(20);
  usr = users.get("prod"+(++index));
        
        System.out.println("user="+usr.getUserName());
        
        System.out.println(driver);
        
        try{
        File file = new File("");
        File scrFile = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);
        String f = file.getCanonicalPath() + File.separator
                + "target" + File.separator
                + "surefire-reports" + File.separator
                + "dammm" + ".png";
        
        File dest = new File(f);

        FileUtils.copyFile(scrFile, dest);
        
        
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
        try { 
        	/*Dimension dimensions = driver.manage().window().getSize();
            int screenWidth = dimensions.getWidth();
            int screenHeight = dimensions.getHeight();
            
            System.out.println("screenWidth" + screenWidth);
            System.out.println("screenHeight" + screenHeight);         
            Dimension dimensions1 = driver.findElement(
                    By.id(elementReader.getPropertyElement("Login.Loginlogo"))).getSize();
            int screenWidth1 = dimensions1.getWidth();
            int screenHeight1 = dimensions1.getHeight();
            
            System.out.println("screenWidth1" + screenWidth1);
            System.out.println("screenHeight1" + screenHeight1); 
            
            int i = (screenWidth1/screenWidth)*100;
            System.out.println("Width % is "+i);*/
        	
        	/*driver.findElement(By.id(elementReader
    				.getPropertyElement("Welcome.Listennow"))).click();*/
          /*driver.findElement(
                    By.id(elementReader.getPropertyElement("Login.UserName")))
                    .sendKeys(usr.getUserName());
            System.out.println("Username Provided");
            driver.findElement(
                    By.id(elementReader.getPropertyElement("Login.Password")))
                    .sendKeys(usr.getPassword());
            System.out.println("Password Given");
            getPageFactory().getHome().hideKeyBoard();
            driver.findElement(
                    By.id(elementReader.getPropertyElement("Login.LoginButton")))
                    .click();*/
        } catch (Exception e) {
            //e.printStackTrace();
        }
		Common.impicitWait(10);
		/*try{
			WebElement technicalPopUp = driver.findElement(By.id(elementReader.getPropertyElement("TechnicalIssue.popup")));
			technicalPopUp.click();
		}catch (Exception e) {
		}*/
    	
	     //	driver.findElement(By.id("com.sirius:id/viewchannellist")).click();
		//getPageFactory().getHome().navigateToMainMenu();			
		//Common.impicitWait(10);

	}

	public Logfiledata getFile() {
		return file;
	}

    public void setFile(Logfiledata file) {
        this.file = file;
    }

	public ExcelUtil getDs() {
		return ds;
	}

	public void setDs(ExcelUtil ds) {
		this.ds = ds;
	}

	public WritableWorkbook getWorkbook() {
		return workbook;
	}

	public void setWorkbook(WritableWorkbook workbook) {
		this.workbook = workbook;
	}

	public TestSession getSession() {
		return session;
	}

	public void setSession(TestSession session) {
		this.session = session;
	}

	public SXMMobilePageFactory getPageFactory() {
		return pageFactory;
	}

	public void setPageFactory(SXMMobilePageFactory pageFactory) {
		this.pageFactory = pageFactory;
	}

	/*
	 * @AfterMethod(alwaysRun = true) public void afterMethod() { //
	 * file.deletefilesfrommobile();
	 * 
	 * if(platform.equals("android")){ ((AndroidDriver)driver).resetApp(); }
	 * else { ((IOSDriver)driver).resetApp(); }
	 * 
	 * // driver.quit(); }
	 */

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public AppiumDriver getDriver() {
		if ("android".equalsIgnoreCase(platform)) {
			return ((AndroidDriver) driver);

		} else if ("IOS".equalsIgnoreCase(platform)) {
			return ((IOSDriver) driver);
		} 
		else if ("Perfecto_Android".equalsIgnoreCase(platform)) {
			return ((AndroidDriver) driver);
		} 
		else if ("Perfecto_IOS".equalsIgnoreCase(platform)) {
			return ((IOSDriver) driver);
		}
		
		else {
			return null;
		}
	}

    public void setDriver(AppiumDriver driver) {
        this.driver = driver;
    }

	@AfterClass(alwaysRun = true)
	public void closeApp() {
		if ("android".equals(platform)) {
			Reporter.log("in after class", true);
			((AndroidDriver) driver).resetApp();
			driver.quit();
		} else if ("ios".equals(platform)) {
			// ((IOSDriver) driver).resetApp();
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			driver.quit();
		}
		if ("Perfecto_Android".equals(platform)) {
			Reporter.log("in after class", true);
			((AndroidDriver) driver).resetApp();
			driver.quit();
		} else if ("Perfecto_IOS".equals(platform)) {
			// ((IOSDriver) driver).resetApp();
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			driver.quit();
		}
		
		try{
			WriteExcel.getInstance().closeFile();
		}catch (Exception e) {
		}
	}

	
	@AfterMethod(alwaysRun = true)
	public void closeMenu() {
		Reporter.log("in after method", true);
                try {
            driver.findElement(
                    By.id(elementReader
                            .getPropertyElement("Home.MinimisedPlayhead.TrackName")))
                    .click();
        } catch (Exception e) {

        }
//            	getPageFactory().getHome().closeNavigationMenu();
            /*	try{
            		ChannelEDP chEDP = new ChannelEDP(driver);
            		chEDP.closeEDP();
            	}catch(Exception e){
            		
            	}*/
            	
               /* try {
                    driver.findElement(
                            By.id(elementReader
                                    .getPropertyElement("RestrictedChannelNPL.expandCompanianContent")))
                            .click();
                } catch (Exception e) {

                }  
		
        try {
			driver.findElement(By.id(elementReader.getPropertyElement("Home.NavigaionMenu1"))).isDisplayed();
		} catch (Exception e) {
			driver.swipe(475, 200, 475, 500, 400);
		}
        try{
        	WebElement technicalPopUp = driver.findElement(By.id(elementReader.getPropertyElement("TechnicalIssue.popup")));
        	technicalPopUp.click();
        }catch (Exception e) {
		}
	
		
		//getPageFactory().getHome().hideKeyBoard();
		
		//driver.swipe(475, 200, 475, 500, 400);
		//driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		//getPageFactory().getRestrictedChannelNPL().closePopUp();
		//driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		//getPageFactory().getHome().closePage();
	//	driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		//getPageFactory().getHome().closeNavigationMenu();
		
		/*try {
			driver.findElement(By.id(elementReader.getPropertyElement("RestrictedChannelNPL.expandCompanianContent"))).click();
			}
			catch(Exception e){
				
			}
		
		try {
	    	  
	    	 WebElement closeendofdemand = driver
	 				.findElement(By.id(elementReader.getPropertyElement("NPL.EndonDemandShow.closebutton")));
	 		closeendofdemand.click();
	 		
	    	 }
	    	 catch(Exception e){
	    		 WebElement navmenu = driver
	    	 				.findElement(By.xpath(elementReader.getPropertyElement("Home.NavigationMenuMenu")));
	    		
	    		  }*/
/*		try {
			
			 WebElement searchmenu = driver
				.findElement(By.id(elementReader.getPropertyElement("Home.searchIcon")));
			 if(searchmenu.getAttribute("selected").contains("true"))
				 searchmenu.click(); 
		}
		catch(Exception e){
  		
  		
  		  }
		
*/	}
	
	   public static void manualType(AndroidDriver driver, String text) {
	        for (int i = 0; i < text.length(); i++) {
	            if (Character.isDigit(text.charAt(i))) {
	                driver.pressKeyCode(AndroidKeyCode.KEYCODE_0 + (text.charAt(i) - '0'));
	            } else if (Character.isUpperCase(text.charAt(i))) {
	                driver.pressKeyCode(AndroidKeyCode.KEYCODE_A + (text.charAt(i) - 'A'), AndroidKeyCode.META_SHIFT_ON);
	            } else if (Character.isLowerCase(text.charAt(i))) {
	                driver.pressKeyCode(AndroidKeyCode.KEYCODE_A + (text.charAt(i) - 'a'));
	            } else {
	                throw new UnsupportedOperationException("Unknown character for manual typing: " + text.charAt(i));
	            }
	        }
	    }
	
	
}