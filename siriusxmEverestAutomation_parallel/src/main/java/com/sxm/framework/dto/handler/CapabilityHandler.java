package com.sxm.framework.dto.handler;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.sxm.framework.dto.Capability;

/**
 * Class to load the capability from resource file for a particular 
 * Platform.Each Platform to maintain separate capabilities.properties
 * file and use this class to load them to be used.
 * This is used for Mobile test cases
 * 
 * @author subramanyamp
 *
 */
public class CapabilityHandler {
    private static CapabilityHandler _instance;
	private static InputStream in;
	private static final String FILEPATH = "src/test/resources/capability.properties";
	private static Properties capabilities = new Properties();
	private static Capability cap = null;

    static {
		try {
			in = new FileInputStream(System.getProperty("user.dir") + "/"
					+ FILEPATH);
			capabilities.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static CapabilityHandler getInstance() {
        if (_instance == null) {
            _instance = new CapabilityHandler();
        }
        return _instance;
    }

	public static Capability getCapability() {
        if (cap == null) {
            cap = new Capability();
            cap.setApplicationName(capabilities.getProperty("appName"));
            cap.setAppPackage(capabilities.getProperty("appPackage"));
            cap.setDevice(capabilities.getProperty("device"));
            cap.setDeviceName(capabilities.getProperty("deviceName"));
            cap.setPlatformName(capabilities.getProperty("platformName"));
            cap.setFilePath(capabilities.getProperty("filePath"));
            cap.setDeviceType(capabilities.getProperty("deviceType"));
            cap.setBundleid(capabilities.getProperty("bundleid"));
            cap.setPlatform(capabilities.getProperty("platform"));
            cap.setAppActvity(capabilities.getProperty("app-activity"));
            cap.setVersion(capabilities.getProperty("version"));
            cap.setAppiumServerPath(capabilities
                    .getProperty("appiumServerPath"));

            cap.setTestdroid_app(capabilities.getProperty("testdroid_app"));

            cap.setTestdroid_device(capabilities
                    .getProperty("testdroid_device"));
            cap.setTestdroid_password(capabilities
                    .getProperty("testdroid_password"));
            cap.setTestdroid_project(capabilities
                    .getProperty("testdroid_project"));
            cap.setTestdroid_target(capabilities
                    .getProperty("testdroid_target"));

            cap.setTestdroid_testrun(capabilities
                    .getProperty("testdroid_testrun"));
            cap.setTestdroid_username(capabilities
                    .getProperty("testdroid_username"));
            cap.setTestdroidServerPath(capabilities
                    .getProperty("testdroid_server_path"));
        }
		
		return cap;
	}
}