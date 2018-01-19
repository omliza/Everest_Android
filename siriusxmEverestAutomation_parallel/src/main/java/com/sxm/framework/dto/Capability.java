package com.sxm.framework.dto;

/**
 * Capability Value object to carry them across the projects
 * 
 * @author subramanyamp
 *
 */
public class Capability {
	private String filePath;
	private String device;
	private String applicationName;
	private String platform;
	private String version;
	private String deviceType;
	private String deviceName;
	private String platformName;
	private String appPackage;
	private String appiumServerPath;
	private String bundleid;

	private String appActvity;
	private String testdroid_username;
	private String testdroid_password;
	private String testdroid_target;
	private String testdroid_project;
	private String testdroid_testrun;
	private String testdroid_device;
	private String testdroid_app;
	private String testdroidServerPath;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public String getAppPackage() {
		return appPackage;
	}

	public void setAppPackage(String appPackage) {
		this.appPackage = appPackage;
	}

	public String getAppActvity() {
		return appActvity;
	}

	public void setAppActvity(String appActvity) {
		this.appActvity = appActvity;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getAppiumServerPath() {
		return appiumServerPath;
	}

	public void setAppiumServerPath(String appiumServerPath) {
		this.appiumServerPath = appiumServerPath;
	}

	public String getTestdroid_username() {
		return testdroid_username;
	}

	public void setTestdroid_username(String testdroid_username) {
		this.testdroid_username = testdroid_username;
	}

	public String getTestdroid_password() {
		return testdroid_password;
	}

	public void setTestdroid_password(String testdroid_password) {
		this.testdroid_password = testdroid_password;
	}

	public String getTestdroid_target() {
		return testdroid_target;
	}

	public void setTestdroid_target(String testdroid_target) {
		this.testdroid_target = testdroid_target;
	}

	public String getTestdroid_project() {
		return testdroid_project;
	}

	public void setTestdroid_project(String testdroid_project) {
		this.testdroid_project = testdroid_project;
	}

	public String getTestdroid_testrun() {
		return testdroid_testrun;
	}

	public void setTestdroid_testrun(String testdroid_testrun) {
		this.testdroid_testrun = testdroid_testrun;
	}

	public String getTestdroid_device() {
		return testdroid_device;
	}

	public void setTestdroid_device(String testdroid_device) {
		this.testdroid_device = testdroid_device;
	}

	public String getTestdroid_app() {
		return testdroid_app;
	}

	public void setTestdroid_app(String testdroid_app) {
		this.testdroid_app = testdroid_app;
	}

	public String getTestdroidServerPath() {
		return testdroidServerPath;
	}

	public void setTestdroidServerPath(String testdroidServerPath) {
		this.testdroidServerPath = testdroidServerPath;
	}

    public String getBundleid() {
        return bundleid;
    }

    public void setBundleid(String bundleid) {
        this.bundleid = bundleid;
    }
}