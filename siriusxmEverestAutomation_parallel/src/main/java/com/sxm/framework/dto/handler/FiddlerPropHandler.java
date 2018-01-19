package com.sxm.framework.dto.handler;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.sxm.framework.dto.FiddlerProxy;

public class FiddlerPropHandler {

	private static FiddlerPropHandler _instance;
	private static InputStream in;
	private static final String FILEPATH = "src/test/resources/fiddler.properties";
	private static Properties fiddler = new Properties();

	static {
		try {
			in = new FileInputStream(System.getProperty("user.dir") + "/"
					+ FILEPATH);
			fiddler.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static FiddlerPropHandler getInstance() {
		if (_instance == null) {
			_instance = new FiddlerPropHandler();
		}
		return _instance;
	}

	public static FiddlerProxy getFiddlerProxy() {
		FiddlerProxy proxy = new FiddlerProxy();
		proxy.setProxyHost(fiddler.getProperty("proxyHost"));
		proxy.setProxyPort(fiddler.getProperty("proxyPort"));
		return proxy;
	}

	public static void main(String[] args) {

		FiddlerProxy proxy = FiddlerPropHandler.getInstance().getFiddlerProxy();
		System.out.println(proxy.getProxyHost());
	}
}
