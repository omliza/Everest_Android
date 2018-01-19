package com.selenium.mobile.base;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.client.ReportiumClientFactory;
import com.perfecto.reportium.model.Job;
import com.perfecto.reportium.model.PerfectoExecutionContext;
import com.perfecto.reportium.model.Project;
import com.perfecto.reportium.test.TestContext;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.NetworkMode;
import com.sxm.framework.common.AppiumDriverUtil;
import com.sxm.framework.common.WriteExcel;
import com.sxm.framework.dto.Envirnoment;
import com.sxm.framework.dto.PerfectoCapability;
import com.sxm.framework.dto.UserObj;
import com.sxm.framework.dto.handler.EnvirnomentHandler;
import com.sxm.framework.dto.handler.PerfectoCapabilityHandler;
import com.sxm.framework.dto.handler.UserHandler;
import com.sxm.framework.exception.AndriodException;
import com.sxm.framework.utility.PropertyElementReader;
import com.sxm.mobile.common.ReferenceLinkResourceHandler;
import com.sxm.mobile.common.TestdroidDeviceRunService;
import com.sxm.mobile.pages.Common;
import com.sxm.mobile.pages.ErrorMessageConstant;
import com.sxm.mobile.pages.ListenerTest;
import com.sxm.mobile.pages.NetworkVirtualizationUtil;
import com.sxm.mobile.pages.ValidationUtil;
import com.sxm.mobile.pages.factory.SXMMobilePageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

/**
 * Base class for all Refactoring Layout mobile tests
 *
 */
public class RefactorMobileTestCase {
	protected String className = "report";
	protected ExtentReports reporter;
	//public static AppiumDriver driver;
	public  AppiumDriver driver;
	PerfectoExecutionContext perfectoExecutionContext;
	protected static ReportiumClient reportiumClient;

	private AppiumDriverUtil appiumutil = new AppiumDriverUtil();

	protected static String locale = EnvirnomentHandler.getInstance().getEnvirnoment().getLocale();
	protected static PropertyElementReader elementReader = PropertyElementReader.getInstance(locale);
	protected String env = EnvirnomentHandler.getInstance().getEnvirnoment().getEnv();

	protected String device = EnvirnomentHandler.getInstance().getEnvirnoment().getDeviceType();

	protected ReferenceLinkResourceHandler reference = ReferenceLinkResourceHandler.getInstance();

	private static PerfectoCapability perfectocapability = PerfectoCapabilityHandler.getInstance()
			.getPerfectoCapability();

	protected UserObj usr = null;
	private String platform;
	private String deviceName;
	private String deviceModel;
	private String deviceId;
	ITestContext context;
	private SXMMobilePageFactory pageFactory;

	public SXMMobilePageFactory getPageFactory() {
		return pageFactory;
	}

	/*
	 * @org.testng.annotations.BeforeMethod public void
	 * beforeMethodCleanup(ITestContext context) { Map<String, Object>
	 * params31=new HashMap<>(); Object
	 * result31=driver.executeScript("mobile:logs:start", params31); }
	 */

	/*
	 * @org.testng.annotations.AfterMethod public void
	 * afterMethodCleanup(ITestContext context) { Map<String, Object>
	 * params32=new HashMap<>(); Object
	 * result32=driver.executeScript("mobile:logs:stop", params32); LogEntries
	 * logEntries33=driver.manage().logs().get("device"); }
	 */

	@Parameters({ "platform", "parallel_run", "device_id", "device_model" })
	@BeforeClass(alwaysRun = true)
	public void setup(String plt, @Optional String parallelRun, @Optional String deviceID, @Optional String devModel, ITestContext context)
			throws InterruptedException, MalformedURLException, IOException, AndriodException {
		try {
			platform = plt;
			//for parallel run ...
			if(null != parallelRun && parallelRun.equalsIgnoreCase("Yes"))
			{
				System.out.println("Parallel run enable: " +parallelRun);
				//set device one credential
				if(null != deviceID && deviceID.equalsIgnoreCase("device_id_one")
						&& null != devModel && devModel.equalsIgnoreCase("device_model_one")) 
				{
					deviceId = perfectocapability.getDevice_id_one();
					deviceModel = perfectocapability.getDevice_model_one();
				}
				//set device two credential
				if(null != deviceID && deviceID.equals("device_id_two")
						&& null != devModel && devModel.equals("device_model_two")) 
				{
					deviceId = perfectocapability.getDevice_id_two();
					deviceModel = perfectocapability.getDevice_model_two();
				}
			}else
			{
				//sequential run
				deviceId = perfectocapability.getDeviceName();
				deviceModel = perfectocapability.getModel();
			}
			
			//for report columns..
			context.setAttribute("DeviceID", deviceId);
			context.setAttribute("DeviceModel", deviceModel);
						
			if (plt.equalsIgnoreCase("android")) {
				driver = appiumutil.getAndroidDriver();
			} else if (plt.equalsIgnoreCase("IOS")) {
				driver = appiumutil.getIOSDriver();
			} else if (platform.equalsIgnoreCase("Perfecto_Android")) {
				System.out.println("In Perfecto");
				driver = appiumutil.getPerfectoAndroidDriver(deviceId);
				Common common = new Common(driver);
				common.deviceVitalsStart(driver);
				context.setAttribute("driver", driver);
			} else if (platform.equalsIgnoreCase("Perfecto_IOS")) {
				driver = appiumutil.getPerfectoiOSDriver();
				// startApp("SiriusXM", driver);
				switchToContext(driver, "NATIVE_APP");
			}

			context.setAttribute("driver", driver);
			System.out.println(driver);
			Envirnoment envirnoment = EnvirnomentHandler.getInstance().getEnvirnoment();
			pageFactory = new SXMMobilePageFactory(driver);
			Common common = new Common(driver);
			context.setAttribute("Driver", driver);
			
			// Network virtualization code starts
			/*
			 * String networkProfile = perfectocapability.getNetworkProfile();
			 * Common.log("Network is running in " +networkProfile+ " mode");
			 * if(!"wifi".equalsIgnoreCase(networkProfile)) {
			 * pageFactory.getNetworkVirtualizationUtil().startNetworkVirtualization
			 * (driver, networkProfile); }
			 */

			int index = 0;

			if (EnvirnomentHandler.getInstance().getEnvirnoment().isTestdroid()) {
				try {
					index = new TestdroidDeviceRunService(envirnoment.getTestdroiduser(), envirnoment.getTestdroidpass(),
							envirnoment.getTestdroidproj()).getDeviceRunIndex() + index;
					System.out.println("index no " + index);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new AndriodException(this.getClass().getSimpleName(),
							Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
				
				}

			}
			usr = UserHandler.getInstance(locale, env).getUserMap().get("prod" + (index + 1));

		
		} catch (Exception e) {
			// TODO: handle exception

			throw new AndriodException(this.getClass().getSimpleName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
		}
	}

	public static void startApp(String appName, AppiumDriver<WebElement> d) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", appName);
		d.executeScript("mobile:application:open", params);
	}

	/*
	 * //@AfterClass(alwaysRun = true) public void closeApp() { if
	 * ("android".equals(platform)) { Reporter.log("in after class", true);
	 * ((AndroidDriver) driver).resetApp(); driver.quit(); } else if
	 * ("IOS".equals(platform)) { Reporter.log("in after suite",true);
	 * //me.logoutApp(); //getPageFactory().getMe().logoutApp();
	 * //driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	 * //driver.quit(); // driver.resetApp(); } if
	 * ("Perfecto_Android".equals(platform)) { Reporter.log("in after class",
	 * true); ((AndroidDriver) driver).resetApp(); driver.quit(); } else if
	 * ("Perfecto_IOS".equals(platform)) { // ((IOSDriver) driver).resetApp();
	 * driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	 * driver.quit(); }
	 * 
	 * // RefactorExcelReportUtil.getInstance().closeFile();
	 * 
	 * }
	 * 
	 * 
	 * //@AfterSuite(alwaysRun=true) // public void closeExcel(){ //
	 * WriteExcel.getInstance().closeFile(); // }
	 * 
	 * @DataProvider public Object[][] getOrientation() { if
	 * (device.equals("tablet")) { return new Object[][] { { "PORTRAIT" }, {
	 * "LANDSCAPE" } }; } else { return new Object[][] { { "PORTRAIT" } }; } }
	 */
	@DataProvider
	public Object[][] getLanguages() {

		return new Object[][] { { "en_US" }, { "en_CA" }, { "es_ES" }, { "fr_FR" } };

	}

	public void on(String a) {
		reporter = new ExtentReports("build/" + a + ".html", true, DisplayOrder.NEWEST_FIRST, NetworkMode.ONLINE,
				Locale.ENGLISH);
		className = a;
	}

	protected static void switchToContext(RemoteWebDriver driver, String context) {
		RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", context);
		executeMethod.execute(DriverCommand.SWITCH_TO_CONTEXT, params);
	}

	/**
	 * Click on home button
	 */
	@AfterMethod(alwaysRun = false)
	public void homeIcon() {
		ValidationUtil validationUtil = new ValidationUtil();
		try {
			Common common = new Common(driver);
			common.reLaunchApp();
			WebElement homeIcon = validationUtil.validateDisplayWebElement("Everest.Home", driver, ErrorMessageConstant.Home_NotDisplayed);
			Common.log("Home icon is present in the screen");
			homeIcon.click();
		} catch (Exception e) {
			Common.log("Home icon is not present in the screen");
			driver.resetApp();
			Common.impicitWait(2);
			driver.launchApp();
			Common.impicitWait(7);
			try {
				getPageFactory().getLogin().login();
				WebElement homeIcon = driver.findElement(By.xpath(elementReader.getPropertyElement("Everest.Home")));
				homeIcon.click();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	/*
	 * @AfterMethod public void closeApp(){ driver.closeApp();
	 * Common.impicitWait(3); driver.launchApp(); Common.impicitWait(3); }
	 */

	@AfterClass(alwaysRun = true)
	public void closeApp() {
		Common common = new Common(driver);
		common.deviceVitalsStop(driver);
		SimpleDateFormat ft = new SimpleDateFormat("MMM d h a");
		String string = ft.format(new Date());
		System.out.println("Time of report Generation :- "+string);
		String RpoURL = reportiumClient.getReportUrl();
		System.out.println("Perfecto report link :- "+RpoURL);
		pageFactory.getNetworkVirtualizationUtil().stopNetworkVirtualization(driver);
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

		try {
			WriteExcel.getInstance().closeFile();
		} catch (Exception e) {
		}
	}

	public void perfectoReportCreation(String a, String methodName, AppiumDriver driver) {
		System.out.println(driver);
		SimpleDateFormat ft = new SimpleDateFormat("h:mm a");
		String string = ft.format(new Date());
		String[] a1 = a.split("\\.");
		String className1 = a1[a1.length - 1];
		perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
				.withProject(new Project("SirisXM_Android_Everest", "1.0")).withJob(new Job("My Job", 45))
				.withContextTags("Android_Everest").withWebDriver(driver).build();
		reportiumClient = new ReportiumClientFactory().createPerfectoReportiumClient(perfectoExecutionContext);
		reportiumClient.testStart(methodName, new TestContext(className1));
		
	}
	/*@AfterSuite
	public void perfectoReport(){
		SimpleDateFormat ft = new SimpleDateFormat("MMM d h a");
		String string = ft.format(new Date());
		System.out.println("Time of report Generation :- "+string);
		String RpoURL = reportiumClient.getReportUrl();
		System.out.println("Perfecto report link :- "+RpoURL);
	}*/

}
