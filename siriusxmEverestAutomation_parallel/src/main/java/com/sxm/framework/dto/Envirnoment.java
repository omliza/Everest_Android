package com.sxm.framework.dto;


/**
 * This is Value objecct for handling the env.properties file
 * @author subramanyamp
 *
 */  
public class Envirnoment {	
	private String url;
	private String userName;
	private String password;
	private String browser;
	private boolean isLocal = true;
	private String channel;
	private String gridURL;
	private boolean isTestdroid =  false;
	private String testdroiduser;
	private String testdroidpass;
	private String testdroidproj;
	private String locale;
	private String env;
	private String deviceType="mobile";
	private String fboauthToken;
	private String twittertoken;
	private String twitterSecretToken;
    private String twitterConsumerkey;
    private String twitterConsumerSecretKey;
	
	public String getTwittertoken() {
        return twittertoken;
    }
    public void setTwittertoken(String twittertoken) {
        this.twittertoken = twittertoken;
    }
    public String getTwitterSecretToken() {
        return twitterSecretToken;
    }
    public void setTwitterSecretToken(String twitterSecretToken) {
        this.twitterSecretToken = twitterSecretToken;
    }
    public String getTwitterConsumerkey() {
        return twitterConsumerkey;
    }
    public void setTwitterConsumerkey(String twitterConsumerkey) {
        this.twitterConsumerkey = twitterConsumerkey;
    }
    public String getTwitterConsumerSecretKey() {
        return twitterConsumerSecretKey;
    }
    public void setTwitterConsumerSecretKey(String twitterConsumerSecretKey) {
        this.twitterConsumerSecretKey = twitterConsumerSecretKey;
    }
    
	
	public String getTestdroiduser() {
        return testdroiduser;
    }
    public void setTestdroiduser(String testdroiduser) {
        this.testdroiduser = testdroiduser;
    }
    public String getTestdroidpass() {
        return testdroidpass;
    }
    public void setTestdroidpass(String testdroidpass) {
        this.testdroidpass = testdroidpass;
    }
    public String getTestdroidproj() {
        return testdroidproj;
    }
    public void setTestdroidproj(String testdroidproj) {
        this.testdroidproj = testdroidproj;
    }
    public String getGridURL() {
        return gridURL;
    }
    public void setGridURL(String gridURL) {
        this.gridURL = gridURL;
    }
    public String getChannel() {
        return channel;
    }
    public void setChannel(String channel) {
        this.channel = channel;
    }
    public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public boolean isLocal() {
		return isLocal;
	}
	public void setLocal(boolean isLocal) {
		this.isLocal = isLocal;
	}
    public boolean isTestdroid() {
        return isTestdroid;
    }
    public void setTestdroid(boolean isTestdroid) {
        this.isTestdroid = isTestdroid;
    }
    public String getLocale() {
        return locale;
    }
    public void setLocale(String locale) {
        this.locale = locale;
    }
    public String getEnv() {
        return env;
    }
    public void setEnv(String env) {
        this.env = env;
    }
    public String getDeviceType() {
        return deviceType;
    }
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
    public String getFboauthToken() {
        return fboauthToken;
    }
    public void setFboauthToken(String fboauthToken) {
        this.fboauthToken = fboauthToken;
    } 
}