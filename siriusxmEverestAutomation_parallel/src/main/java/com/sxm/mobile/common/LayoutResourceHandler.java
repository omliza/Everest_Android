package com.sxm.mobile.common;

import static org.testng.Assert.fail;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class LayoutResourceHandler {
    private static LayoutResourceHandler _instance;
    private static InputStream in;

    private static final String FILEPATH_MOBILE = "src/test/resources/layout_mobile.properties";
    private static final String FILEPATH_TABLET = "src/test/resources/layout_tablet.properties";

    private static Properties layouts = new Properties();

    private static String type = "mobile";
            
    
    public static LayoutResourceHandler getInstance(String deviceType) {
        type = deviceType;
  
        if (_instance == null) {
            _instance = new LayoutResourceHandler();
        }
        return _instance;
    }

    private LayoutResourceHandler() {
        loadLayoutProperties();
    }

    private void loadLayoutProperties() {
        try {
            if (type.equals("mobile")) {
                in = new FileInputStream(System.getProperty("user.dir") + "/"
                        + FILEPATH_MOBILE);
            } else if (type.equals("tablet")) {
                in = new FileInputStream(System.getProperty("user.dir") + "/"
                        + FILEPATH_TABLET);
            }

            layouts.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPropertyElement(String key) {
        if ((layouts != null) && (layouts.containsKey(key))) {
            return layouts.getProperty(key);
        }

        fail("Could not find property element for key '" + key + "'");
        return null;
    }
}