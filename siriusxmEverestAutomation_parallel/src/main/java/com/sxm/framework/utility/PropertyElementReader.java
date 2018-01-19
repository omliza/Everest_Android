package com.sxm.framework.utility;

import static org.testng.Assert.fail;

import java.util.Properties;

import org.testng.Reporter;

public class PropertyElementReader {

    private static PropertyElementReader _instance;
    private static String mylocale = "en_US";

    // here base element is one which is holding all the baseElement properties
    //android can be base element
    private static Properties baseElements = new Properties();
    
    //Overriding properties will hold the overriding element
    //iphone can re-use scripts from android, iphone can be overriding properties
    //For web dont maintain anything if it is not required.
    private static Properties overridingElements = new Properties();
 
    public static PropertyElementReader getInstance(String locale) {
        if (_instance == null) {
            mylocale = locale;
            _instance = new PropertyElementReader();
        }
        return _instance;
    }

    private PropertyElementReader() {
        loadPropertyElements();
    }

    public String getPropertyElement(String key) {   
        if ((overridingElements != null) 
                && (overridingElements.containsKey(key))) {
            return overridingElements.getProperty(key);
        }

        if ((baseElements != null) 
                && (baseElements.containsKey(key))) {
            return baseElements.getProperty(key);
        }
        
        fail("Could not find property element for key '" + key + "'");
        return null;
    }

    private void loadPropertyElements() {
        try {
            if (mylocale.equals("en_US")) {
                baseElements.load(getClass().getResourceAsStream(
                        "/elements/baseElements.properties"));
            } else if(mylocale.equals("en_CA")) {
                baseElements.load(getClass().getResourceAsStream(
                        "/elements/baseElements_en_CA.properties"));
            }
        } catch (Exception e) {
            Reporter.log("Could not load base element file.", 3);
        }
        
       try {
    	   overridingElements.load(getClass().getResourceAsStream("/elements/overridingElements.properties"));
        } catch (Exception e) {
            Reporter.log("Could not load store element file.", 3);
        }
    }
}