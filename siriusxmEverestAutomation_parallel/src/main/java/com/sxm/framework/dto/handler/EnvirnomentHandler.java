package com.sxm.framework.dto.handler;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.sxm.framework.dto.Envirnoment;

/**
 * Class to load the Enviroment from resource file for a particular
 * Platform.Each Platform to maintain separate env.properties file and use this
 * class to load them to be used. This is used for both Mobile and Web test
 * cases
 * 
 * @author subramanyamp
 *
 */
public class EnvirnomentHandler {

	private static EnvirnomentHandler _instance;
	private static InputStream in;
	private static final String FILEPATH = "src/test/resources/env.properties";
	private static Properties envs = new Properties();
	private static Envirnoment env= null;

	public static EnvirnomentHandler getInstance() {
		if (_instance == null) {
			_instance = new EnvirnomentHandler();
		}
		return _instance;
	}

	static {
		try {
			in = new FileInputStream(System.getProperty("user.dir") + "/"
					+ FILEPATH);
			envs.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Envirnoment getEnvirnoment() {
        if (env == null) {
            env = new Envirnoment();
            env.setPassword(envs.getProperty("password"));
            env.setUserName(envs.getProperty("username"));
            env.setUrl(envs.getProperty("url"));
            env.setBrowser(envs.getProperty("browser"));
            env.setLocal(Boolean.parseBoolean(envs.getProperty("islocal")));
            env.setChannel(envs.getProperty("channel"));
            env.setGridURL(envs.getProperty("gridurl"));
            env.setTestdroid(Boolean.parseBoolean(envs.getProperty("isTestdroid")));
            env.setTestdroiduser(envs.getProperty("testdroiduser"));
            env.setTestdroidpass(envs.getProperty("testdroidpass"));
            env.setTestdroidproj(envs.getProperty("testdroidproj"));
            env.setLocale(envs.getProperty("locale"));
            env.setEnv(envs.getProperty("env"));
            env.setDeviceType(envs.getProperty("deviceType"));
            env.setFboauthToken(envs.getProperty("fboauthToken"));
            
            env.setTwittertoken(envs.getProperty("twitter.oauthToken"));
            env.setTwitterSecretToken(envs.getProperty("twitter.secretToken"));
            env.setTwitterConsumerkey(envs.getProperty("twitter.consumerKey"));
            env.setTwitterConsumerSecretKey(envs.getProperty("twitter.consumerSecretKey"));
           
        }	
		return env;
	}
}