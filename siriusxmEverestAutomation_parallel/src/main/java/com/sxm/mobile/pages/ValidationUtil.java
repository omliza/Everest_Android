package com.sxm.mobile.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.sxm.framework.dto.AndriodWebElement;
import com.sxm.framework.dto.handler.EnvirnomentHandler;
import com.sxm.framework.exception.AndriodException;
import com.sxm.framework.utility.PropertyElementReader;

import io.appium.java_client.AppiumDriver;

/**Validation Util: util methods for different validation
 * @author sxm2
 *
 */
public class ValidationUtil {
	private String locale = EnvirnomentHandler.getInstance().getEnvirnoment().getLocale();
	private PropertyElementReader elementReader = PropertyElementReader.getInstance(locale);

	/**validate WebElement
	 * @param string
	 * @param driver
	 * @return
	 */
	public WebElement validateWebElement(String string, AppiumDriver driver) throws AndriodException{
		WebElement element = null;
		try{
		element = driver.findElement(By.xpath(elementReader.getPropertyElement(string)));
		
		}catch(Exception ex)
		{
			Common.log(ex.getMessage());
			throw new AndriodException(this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), 
					"KEY - "+string+" : "+ex.getMessage());
		}
		return element;
	}
	public List<WebElement> validateListWebElement(String string, AppiumDriver driver) throws AndriodException{
		List<WebElement> element = null;
		try{
		element = driver.findElements(By.xpath(elementReader.getPropertyElement(string)));
		
		}catch(Exception ex)
		{
			Common.log(ex.getMessage());
			throw new AndriodException(this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), 
					"KEY - "+string+" : "+ex.getMessage());
		}
		return element;
	}
	
	
	/** validate Display WebElement
	 * @param string
	 * @param driver
	 * @param errorMessage 
	 * @return
	 * @throws AndriodException
	 */
	public WebElement validateDisplayWebElement(String string, AppiumDriver driver, String errorMessage) throws AndriodException{
		WebElement element = null;
		try{
		element = driver.findElement(By.xpath(elementReader.getPropertyElement(string)));
		if(!element.isDisplayed())
		{
			Common.log(errorMessage);
			System.out.println(errorMessage);
			throw new AndriodException(this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), 
					errorMessage);
		}
		}catch(Exception ex)
		{
			Common.log(errorMessage);
			throw new AndriodException(this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), 
					errorMessage);
		}
		return element;
	}
	
	/*
	 * Find Element by Id
	 */
	public WebElement GetElementById(String string, AppiumDriver driver, String errorMessage) throws AndriodException{
		WebElement element = null;
		try{
		element = driver.findElement(By.id(elementReader.getPropertyElement(string)));
		if(!element.isDisplayed())
		{
			Common.log(errorMessage);
			throw new AndriodException(this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), 
					errorMessage);
		}
		}catch(Exception ex)
		{
			Common.log(errorMessage);
			throw new AndriodException(this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), 
					errorMessage);
		}
		return element;
	}

}
