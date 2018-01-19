/**
 * 
 */
package com.sxm.framework.dto;

import org.openqa.selenium.WebElement;

/**
 * @author sxm2
 * Andriod version of WebElement
 *
 */
public class AndriodWebElement {
	 
	WebElement webElement;
	
	String errorMessage;

	/**
	 * @return the webElement
	 */
	public WebElement getWebElement() {
		return webElement;
	}

	/**
	 * @param webElement the webElement to set
	 */
	public void setWebElement(WebElement webElement) {
		this.webElement = webElement;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	
	

}
