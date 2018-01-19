package com.sxm.framework.common;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.client.ReportiumClientFactory;
import com.perfecto.reportium.model.Job;
import com.perfecto.reportium.model.PerfectoExecutionContext;
import com.perfecto.reportium.model.Project;
import com.sxm.framework.dto.Capability;
import com.sxm.framework.dto.Envirnoment;
import com.sxm.framework.dto.PerfectoCapability;
import com.sxm.framework.dto.handler.CapabilityHandler;
import com.sxm.framework.dto.handler.EnvirnomentHandler;
import com.sxm.framework.dto.handler.PerfectoCapabilityHandler;
import com.sxm.framework.exception.AndriodException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class AppiumDriverUtil {
	private static Capability capability = CapabilityHandler.getInstance().getCapability();
	private static PerfectoCapability perfectocapability = PerfectoCapabilityHandler.getInstance()
			.getPerfectoCapability();
	private static Envirnoment env = EnvirnomentHandler.getInstance().getEnvirnoment();

	private AndroidDriver androiddriver = null;
	private IOSDriver iosdriver = null;
	// private IOSDriver driver;
	AppiumDriver iosAppiumDriver;
	ReportiumClient reportiumClient;

	public static void main(String args[]) throws IOException {
		AppiumDriverUtil appiumdriver = new AppiumDriverUtil();
		// System.out.println(appiumdriver.getPerfectoAndroidDriver());
	}

	public AndroidDriver getAndroidDriver() throws AndriodException {
		if (androiddriver == null && env.isLocal()) {
			File appDir = new File(capability.getFilePath());
			File app = new File(appDir, capability.getApplicationName());
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("device", capability.getDevice());
			capabilities.setCapability("platform", capability.getPlatform());
			capabilities.setCapability("version", capability.getVersion());
			capabilities.setCapability("newCommandTimeout", "300");
			capabilities.setCapability("deviceType", capability.getDeviceType());
			capabilities.setCapability("deviceName", capability.getDeviceName());
			capabilities.setCapability("platformName", capability.getPlatformName());
			capabilities.setCapability("app", app.getAbsolutePath());
			capabilities.setCapability("app-package", capability.getAppPackage());
			capabilities.setCapability("app-activity", capability.getAppActvity());

			try {
				androiddriver = new AndroidDriver(new URL(capability.getAppiumServerPath()), capabilities);
				Thread.sleep(5000);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return androiddriver;
		} else if (androiddriver == null && !env.isLocal()) {
			androiddriver = (AndroidDriver) getTestDroidDriver(capability.getPlatform());
			return androiddriver;
		}
		return androiddriver;
	}

	// TODO implement for IOS driver
	public IOSDriver getIOSDriver() {
		// set up appium
		if (iosdriver == null && env.isLocal()) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("device", capability.getDevice());
			capabilities.setCapability("deviceName", capability.getDeviceName());
			// capabilities.setCapability("udid",
			// "2bd76408db2e3ad25e2142038579baa8b2c3aff3");
			capabilities.setCapability("platformName", capability.getPlatformName());
			capabilities.setCapability("platformVersion", capability.getVersion());
			capabilities.setCapability("bundleid", capability.getBundleid());
			File appDir = new File(capability.getFilePath());
			File app = new File(appDir, capability.getApplicationName());
			capabilities.setCapability("app", app.getAbsolutePath());
			try {
				iosdriver = new IOSDriver(new URL(capability.getAppiumServerPath()), capabilities);
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			return iosdriver;
		} else if (iosdriver == null && !env.isLocal()) {
			iosdriver = (IOSDriver) getTestDroidDriver(capability.getPlatform());
			return iosdriver;
		} else {
			return iosdriver;
		}
	}

	public AndroidDriver getPerfectoAndroidDriver(String deviceId) throws AndriodException {
		if (androiddriver == null && env.isLocal()) {
			/*
			 * For Android required capabilities are: Browser Name, Host, User
			 * Name, Password,Device Name, Automation Name, App, App Package
			 */
			String browserName = perfectocapability.getBrowserName();
			String host = perfectocapability.getHost();
			String userName = perfectocapability.getUser();
			String password = perfectocapability.getPassword();
			// String deviceName = perfectocapability.getDeviceName();
			String automationName = perfectocapability.getAutomationName();
			String app = perfectocapability.getApp();
			String appPackage = perfectocapability.getAppPackage();

			DesiredCapabilities capabilities = new DesiredCapabilities(browserName, "", Platform.ANY);
			capabilities.setCapability("user", userName);
			capabilities.setCapability("password", password);
			if (null != deviceId && !deviceId.equals("")) {
				capabilities.setCapability("deviceName", deviceId);
			} else {
				String deviceId1 = perfectocapability.getDeviceName();
				capabilities.setCapability("deviceName", deviceId1);
			}
			// capabilities.setCapability("deviceName", deviceName);
			capabilities.setCapability("automationName", automationName);
			capabilities.setCapability("app", app);// uncomment
			capabilities.setCapability("appPackage", appPackage);

			// Call this method if you want the script to share the devices with
			// the Perfecto Lab plugin.
			try {
				PerfectoLabUtils.setExecutionIdCapability(capabilities, host);

				// PerfectoLabUtils.uploadMedia(host,
				// "punyasloka.pradhan@tcs.com",
				// "Welcome2104","C:\\Softwares\\SiriusXM_1491510741.apk" ,
				// "PRIVATE:SiriusXM_1491510741.apk");
				// Application settings examples.

				// capabilities.setCapability("appActivity",
				// ".activities.BrowseActivity");
				// For iOS:
				// capabilities.setCapability("bundleId",
				// "com.yoctoville.errands");

				// Add a persona to your script (see
				// https://community.perfectomobile.com/posts/1048047-available-personas)
				// capabilities.setCapability(WindTunnelUtils.WIND_TUNNEL_PERSONA_CAPABILITY,
				// WindTunnelUtils.GEORGIA);

				// Name your script
				// capabilities.setCapability("scriptName", "AppiumTest");
				/*
				 * System.getProperties().put("http.proxyHost",
				 * "proxy.tcs.com");
				 * System.getProperties().put("http.proxyPort", "8080");
				 * System.getProperties().put("http.proxyHost",
				 * "proxy.tcs.com");
				 * System.getProperties().put("http.proxyPort", "8080");
				 */

				try {
					androiddriver = new AndroidDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"),
							capabilities);

					// IOSDriver driver = new IOSDriver(new URL("https://" +
					// host +
					// "/nexperience/perfectomobile/wd/hub"), capabilities);
					androiddriver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

					// Reporting client. For more details, see
					// https://github.com/perfectocode/samples/wiki/reporting
					PerfectoExecutionContext perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
							.withProject(new Project("My Project", "1.0")).withJob(new Job("My Job", 45))
							.withContextTags("tag1").withWebDriver(androiddriver).build();
					reportiumClient = new ReportiumClientFactory()
							.createPerfectoReportiumClient(perfectoExecutionContext);
				} catch (MalformedURLException e) {
					e.printStackTrace();
					throw new AndriodException(this.getClass().getSimpleName(),
							Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
				}
			} catch (IOException e) {
				throw new AndriodException(this.getClass().getSimpleName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
			} catch (AndriodException ex) {
				throw new AndriodException(this.getClass().getSimpleName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(), ex.getMessage());
			}

		}
		return androiddriver;
	}

	@SuppressWarnings("rawtypes")
	public AppiumDriver getPerfectoiOSDriver() throws MalformedURLException, IOException, AndriodException {
		if (iosAppiumDriver == null && env.isLocal()) {
			System.out.println("Run started");

			String browserName = perfectocapability.getBrowserName();
			DesiredCapabilities capabilities = new DesiredCapabilities(browserName, "", Platform.ANY);
			String host = perfectocapability.getHost();

			String userName = perfectocapability.getUser();
			capabilities.setCapability("user", userName);

			String password = perfectocapability.getPassword();
			capabilities.setCapability("password", password);

			// TODO: Change your device ID
			String deviceId = perfectocapability.getDeviceName();
			capabilities.setCapability("deviceName", deviceId);

			// Use the automationName capability to define the required
			// framework - Appium (this is the default) or PerfectoMobile.
			String automationName = perfectocapability.getAutomationName();
			capabilities.setCapability("automationName", automationName);

			// Call this method if you want the script to share the devices with
			// the Perfecto Lab plugin.
			try {
				PerfectoLabUtils.setExecutionIdCapability(capabilities, host);

				// Application settings examples.
				String app = perfectocapability.getApp();
				// capabilities.setCapability("app", app);

				// For Android:
				// capabilities.setCapability("appPackage",
				// "com.google.android.keep");
				// capabilities.setCapability("appActivity",
				// ".activities.BrowseActivity");
				// For iOS:
				String bundleId = perfectocapability.getBundleId();
				capabilities.setCapability("bundleId", bundleId);

				// Add a persona to your script (see
				// https://community.perfectomobile.com/posts/1048047-available-personas)
				// capabilities.setCapability(WindTunnelUtils.WIND_TUNNEL_PERSONA_CAPABILITY,
				// WindTunnelUtils.GEORGIA);

				// Name your script
				// capabilities.setCapability("scriptName", "AppiumTest");

				// AndroidDriver driver = new AndroidDriver(new URL("https://" +
				// host + "/nexperience/perfectomobile/wd/hub"), capabilities);
				IOSDriver driver = new IOSDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"),
						capabilities);
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

				// Reporting client. For more details, see
				// http://developers.perfectomobile.com/display/PD/Reporting
				PerfectoExecutionContext perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
						.withProject(new Project("My Project", "1.0")).withJob(new Job("My Job", 45))
						.withContextTags("tag1").withWebDriver(driver).build();
				ReportiumClient reportiumClient = new ReportiumClientFactory()
						.createPerfectoReportiumClient(perfectoExecutionContext);

				iosAppiumDriver = driver;

				System.out.println("Run ended");

			} catch (MalformedURLException e) {
				e.printStackTrace();
				throw new AndriodException(this.getClass().getSimpleName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
			} catch (IOException e) {
				throw new AndriodException(this.getClass().getSimpleName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(), e.getMessage());
			} catch (AndriodException ex) {
				throw new AndriodException(this.getClass().getSimpleName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(), ex.getMessage());
			}
		}
		return iosAppiumDriver;
	}

	// Just to check
	private static void switchToContext(RemoteWebDriver driver, String context) {
		RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", context);
		executeMethod.execute(DriverCommand.SWITCH_TO_CONTEXT, params);
	}

	private static String getCurrentContextHandle(RemoteWebDriver driver) {
		RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
		String context = (String) executeMethod.execute(DriverCommand.GET_CURRENT_CONTEXT_HANDLE, null);
		return context;
	}

	private static List<String> getContextHandles(RemoteWebDriver driver) {
		RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
		List<String> contexts = (List<String>) executeMethod.execute(DriverCommand.GET_CONTEXT_HANDLES, null);
		return contexts;
	}
	//

	private static AppiumDriver getTestDroidDriver(final String platform) {
		AppiumDriver driver = null;
		String testdroid_username = capability.getTestdroid_username();
		String testdroid_password = capability.getTestdroid_password();

		if (StringUtils.isEmpty(testdroid_password) || StringUtils.isEmpty(testdroid_username)) {
			throw new IllegalArgumentException(
					"Missing TESTDROID_USERNAME or/and TESTDROID_PASSWORD environment variables");
		}

		String fileUUID = null;
		try {
			fileUUID = TestDroidUtil.uploadFile(
					capability.getFilePath() + File.separator + capability.getApplicationName(),
					capability.getTestdroidServerPath(), testdroid_username, testdroid_password);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", capability.getPlatformName());
		capabilities.setCapability("testdroid_target", capability.getTestdroid_target());
		capabilities.setCapability("deviceName", capability.getDeviceName());

		capabilities.setCapability("testdroid_username", capability.getTestdroid_username());
		capabilities.setCapability("testdroid_password", capability.getTestdroid_password());

		if ("android".equalsIgnoreCase(platform)) {
			capabilities.setCapability("testdroid_project", "SiriusXMAndroid");
			capabilities.setCapability("testdroid_testrun", "Regression Android Run" + System.currentTimeMillis());
		} else if ("IOS".equalsIgnoreCase(platform)) {
			capabilities.setCapability("testdroid_project", "SiriusXMIOS");
			capabilities.setCapability("testdroid_testrun", "Regression IOS Run" + System.currentTimeMillis());
		}

		// See available devices at:
		// https://cloud.testdroid.com/#public/devices
		capabilities.setCapability("testdroid_device", capability.getTestdroid_device());
		capabilities.setCapability("testdroid_app", fileUUID);
		System.out.println("Capabilities:" + capabilities.toString());

		System.out.println("Creating Appium session, this may take couple minutes..");
		try {

			if ("android".equalsIgnoreCase(platform)) {
				driver = new AndroidDriver(new URL(capability.getTestdroidServerPath() + "/wd/hub"), capabilities);
			} else if ("IOS".equalsIgnoreCase(platform)) {
				driver = new IOSDriver(new URL(capability.getTestdroidServerPath() + "/wd/hub"), capabilities);
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return driver;
	}
}