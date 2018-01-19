package com.sxm.mobile.common;

import static org.testng.Assert.fail;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ReferenceLinkResourceHandler {
    private static ReferenceLinkResourceHandler _instance;
    private static InputStream in;

    private static final String FILEPATH = "src/test/resources/reference.properties";

    private static Properties reference = new Properties();

    public static ReferenceLinkResourceHandler getInstance() {
        if (_instance == null) {
            _instance = new ReferenceLinkResourceHandler();
        }
        return _instance;
    }

    private ReferenceLinkResourceHandler() {
        loadLayoutProperties();
    }

    private void loadLayoutProperties() {
        try {
            in = new FileInputStream(System.getProperty("user.dir") + "/"
                    + FILEPATH);

            reference.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPropertyElement(String key) {
        if ((reference != null) && (reference.containsKey(key))) {
            return reference.getProperty(key);
        }

        fail("Could not find property element for key '" + key + "'");
        return null;
    }
}