package com.sxm.mobile.pages;
import java.util.HashMap;
import java.util.logging.Logger;

import io.appium.java_client.AppiumDriver;



public class NetworkVirtualizationUtil extends ListenerTest {

	private final static Logger LOGGER = Logger.getLogger(NetworkVirtualizationUtil.class

			.getName());

	private static AppiumDriver driver;



	public NetworkVirtualizationUtil(AppiumDriver driver) {

		super();

		this.driver = driver;

	}

	

	

	// To start network virtualization

		public static void startNetworkVirtualization(AppiumDriver driver, String networkProfile) {

			HashMap<Object, Object> params = new HashMap<>();

			params.put("profile", networkProfile);

			driver.executeScript("mobile:vnetwork:start", params);

		}



		// To start network virtualization

		public static void noNetworkZone(AppiumDriver driver, String bandwidthin) {

			HashMap<Object, Object> params = new HashMap<>();

			params.put("bandwidth.in", bandwidthin);

			driver.executeScript("mobile:vnetwork:start", params);

		}



		// To start network virtualization

		public static void UpdateNetworkVirtualization(AppiumDriver driver, String networkProfile) {

			HashMap<Object, Object> params = new HashMap<>();

			params.put("profile", networkProfile);

			driver.executeScript("mobile:vnetwork:update", params);

		}



		// To stop network virtualization

		public static void stopNetworkVirtualization(AppiumDriver driver) {

			HashMap<Object, Object> params = new HashMap<>();

			driver.executeScript("mobile:vnetwork:stop", params);

		}



		// Returns ux timer

		// Wind Tunnel: Gets the requested timer

		public static long timerGet(AppiumDriver driver, String timerType) {

			String command = "mobile:timer:info";

			HashMap<String, String> params = new HashMap<String, String>();

			params.put("type", timerType);

			long result = (Long) driver.executeScript(command, params);

			return result;

		}



		public static long getUXTimer(AppiumDriver driver) {

			long timer = timerGet(driver, "ux");

			return timer;

		}

		

		



}
