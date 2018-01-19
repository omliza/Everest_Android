package com.sxm.framework.dto.handler;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.sxm.framework.dto.Capability;
import com.sxm.framework.dto.PerfectoCapability;

public class PerfectoCapabilityHandler {

	private static PerfectoCapabilityHandler _instance;
	private static InputStream in;
	private static final String FILEPATH = "src/test/resources/perfectocapability.properties";
	private static Properties perfectocapabilities = new Properties();
	private static PerfectoCapability cap = null;

	static {
		try {
			in = new FileInputStream(System.getProperty("user.dir") + "/" + FILEPATH);
			perfectocapabilities.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static PerfectoCapabilityHandler getInstance() {
		if (_instance == null) {
			_instance = new PerfectoCapabilityHandler();
		}
		return _instance;
	}

	public static PerfectoCapability getPerfectoCapability() {
		if (cap == null) {
			cap = new PerfectoCapability();
			cap.setHost(perfectocapabilities.getProperty("host"));
			cap.setUser(perfectocapabilities.getProperty("user"));
			cap.setPassword(perfectocapabilities.getProperty("password"));
			cap.setBrowserName(perfectocapabilities.getProperty("browserName"));
			cap.setDeviceName(perfectocapabilities.getProperty("deviceName"));
			cap.setAutomationName(perfectocapabilities.getProperty("automationName"));
			cap.setApp(perfectocapabilities.getProperty("app"));
			cap.setAutoInstrument(perfectocapabilities.getProperty("autoInstrument"));
			cap.setAppPackage(perfectocapabilities.getProperty("appPackage"));
			cap.setBundleId(perfectocapabilities.getProperty("bundleId"));
			
			
			//cap.setAppActivity(perfectocapabilities.getProperty("appActivity"));
			//cap.setBundleId(perfectocapabilities.getProperty("bundleId"));
			cap.setDeviceType(perfectocapabilities.getProperty("deviceType"));
			cap.setPlatform(perfectocapabilities.getProperty("platform"));
			//cap.setScriptName(perfectocapabilities.getProperty("scriptName"));
			cap.setVersion(perfectocapabilities.getProperty("version"));
			cap.setDevice(perfectocapabilities.getProperty("device"));
			cap.setPlatformName(perfectocapabilities.getProperty("platformName"));
			cap.setPlatformVersion(perfectocapabilities.getProperty("platformVersion"));
			cap.setApplicationName(perfectocapabilities.getProperty("appName"));
			
			
			cap.setTestdroid_app(perfectocapabilities.getProperty("testdroid_app"));
			cap.setTestdroid_device(perfectocapabilities.getProperty("testdroid_device"));
			cap.setTestdroid_password(perfectocapabilities.getProperty("testdroid_password"));
			cap.setTestdroid_project(perfectocapabilities.getProperty("testdroid_project"));
			cap.setTestdroid_target(perfectocapabilities.getProperty("testdroid_target"));
			cap.setTestdroid_testrun(perfectocapabilities.getProperty("testdroid_testrun"));
			cap.setTestdroid_username(perfectocapabilities.getProperty("testdroid_username"));
			cap.setTestdroidServerPath(perfectocapabilities.getProperty("testdroid_server_path"));
			cap.setNetworkProfile(perfectocapabilities.getProperty("networkProfile"));
			
			cap.setModel(perfectocapabilities.getProperty("model"));
			cap.setOs(perfectocapabilities.getProperty("os"));
			cap.setOsVersion(perfectocapabilities.getProperty("osVersion"));
			cap.setDevice_id_one(perfectocapabilities.getProperty("device_id_one"));
			cap.setDevice_id_two(perfectocapabilities.getProperty("device_id_two"));
			cap.setDevice_model_one(perfectocapabilities.getProperty("device_model_one"));
			cap.setDevice_model_two(perfectocapabilities.getProperty("device_model_two"));
		}

		return cap;
	}

}
