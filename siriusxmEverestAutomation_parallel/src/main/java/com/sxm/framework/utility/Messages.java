package com.sxm.framework.utility;

import java.util.Locale;
import java.util.ResourceBundle;


/**
 * utility class for looking up internationalized messages based on page
 * and key.
 */
public class Messages {
    private final ResourceBundle appBundle;
  

    public Messages(final String baseName,
            final Locale locale) {
        appBundle = ResourceBundle.getBundle(baseName, locale);
      
    }

    /**
     * get a new Messages lookup object using a classloaded properties bundle
     * @param baseName base name for the properties file (ex com.gsi.WebMessages)
     * @param locale locale for choosing a message file variant
     * @return a shiny new Messages object ready to lookup messages by key
     */
    public static Messages getInstance(
            final String baseName, final Locale locale) {
        return new Messages(baseName, locale);
    }

    /**
     * get message by key
     * @param key
     * @return internationalized message
     */
    public String getMessage(final String key) {
      
        return appBundle.getString(key).trim();
    }

    /**
     * generate a local key based on page and message key
     * @param page context
     * @param msgKey local key
     * @return combined key for looking up messages
     */
    public static String getKey(final String page, final String msgKey) {
        if (null == page || null == msgKey) {
            return null;
        }

        return new StringBuilder(page).append(".").append(msgKey).toString();
    }
}