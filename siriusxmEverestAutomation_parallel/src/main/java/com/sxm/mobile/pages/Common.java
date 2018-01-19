package com.sxm.mobile.pages;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.google.common.base.Function;
import com.sxm.framework.dto.UserObj;
import com.sxm.framework.dto.handler.UserHandler;
import com.sxm.framework.exception.AndriodException;
import com.sxm.mobile.common.ReadAttributeValue;
import com.sxm.mobile.common.ReadAttributeValueWithXpath;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

//this class is were we will use all the reuseable functions e.g getwindow handle.

public class Common extends ListenerTest {

	private  AppiumDriver driver;
	private Map<String, UserObj> users = UserHandler.getInstance(locale, env).getUserMap();
	public Common(AppiumDriver driver) {
		super();
		this.driver = driver;
	}

	// private static AndroidDriver driver =
	// (AndroidDriver)AppiumDriverUtil.getAndroidDriver(); // This will read the
	// configuration.java >
	// Gets the webdriver
	// instance

	public  void swipeLeft() {
		Dimension size = driver.manage().window().getSize();

		int startx = (int) (size.width * 0.8);

		int endx = (int) (size.width * 0.20);

		int starty = size.height / 2;

		driver.swipe(startx, starty, endx, starty, 1000);
	}

	public  void swiperight() {
		Dimension size = driver.manage().window().getSize();

		int endx = (int) (size.width * 0.8);

		int startx = (int) (size.width * 0.20);

		int starty = size.height / 2;

		driver.swipe(startx, starty, endx, starty, 1000);
	}

	public  void scrollChannels(int count, WebElement e) {
		try {
			if (!(e.isDisplayed())) {
				for (int i = 0; i <= count; i++) {
					Dimension dimensions = driver.manage().window().getSize();
					Double screenHeightStart = dimensions.getHeight() * 0.5;
					int scrollStart = screenHeightStart.intValue();
					System.out.println("s=" + scrollStart);
					Double screenHeightEnd = dimensions.getHeight() * 0.2;
					int scrollEnd = screenHeightEnd.intValue();
					driver.swipe(0, scrollStart, 0, scrollEnd, 2000);
					try {
						e.isDisplayed();
						break;
					} catch (Exception p) {
						Reporter.log("element not found", true);
					}
				}
			} else {
				Reporter.log("element not found", true);
			}
		} catch (Exception e1) {

		}

	}

	public  WebElement getElement(By by) {
		WebElement el = null;
		try {
			impicitWait(5);
			el = driver.findElement(by);
		} catch (Exception e) {
			System.out.println("element not present in screen");
		}

		return el;

	}

	public  void scrollChannelstillVisible(int count, String xpathvalue, String lookup, String expected) {
		System.out.println("scrollChannelstillVisible start");
		try {
			String s = ReadAttributeValueWithXpath.getAttributes(xpathvalue, lookup, driver.getPageSource());
			System.out.println("s: " + s);
			if (!s.equals(expected)) {
				for (int i = 0; i <= count; i++) {
					Dimension dimensions = driver.manage().window().getSize();
					Double screenHeightStart = dimensions.getHeight() * 0.5;
					int scrollStart = screenHeightStart.intValue();
					System.out.println("s=" + scrollStart);
					Double screenHeightEnd = dimensions.getHeight() * 0.2;
					int scrollEnd = screenHeightEnd.intValue();
					driver.swipe(0, scrollStart, 0, scrollEnd, 2000);
					try {
						if (ReadAttributeValueWithXpath.getAttributes(xpathvalue, lookup, driver.getPageSource())
								.contains(expected))
							break;
					} catch (Exception p) {
						Reporter.log("element not found", true);
					}
				}
			} else {
				Reporter.log("element not found", true);
			}
		} catch (Exception e1) {

		}
		// scrollChannelsUp(1);
		System.out.println("scrollChannelstillVisible end");
	}

	public  void elementVisible(String xpathvalue, String lookup, String expected) {
		try {
			String s = ReadAttributeValueWithXpath.getAttributes(xpathvalue, lookup, driver.getPageSource());

			if (s.equals(expected)) {
				Assert.assertTrue(true);
			} else {
				Assert.fail("Element Not Found..");

			}
		} catch (Exception e) {

		}

	}

	public  boolean elementDisplayed(String xpathvalue, String lookup, String expected) {
		boolean flag = false;
		try {
			impicitWait(2);
			String s = ReadAttributeValueWithXpath.getAttributes(xpathvalue, lookup, driver.getPageSource());
			impicitWait(2);
			System.out.println("\nQuality : Expexted=" + expected + " Actual=" + s);
			if (s.equals(expected)) {
				Assert.assertTrue(true);
				flag = true;
			}
		} catch (Exception e) {

		}
		return flag;
	}

	public  boolean elementDisplayedByName(String xpathvalue, String lookup, String expected) {
		boolean flag = false;
		try {
			String s = ReadAttributeValue.getAttributes(xpathvalue, lookup, driver.getPageSource());
			if (s.equalsIgnoreCase(expected)) {
				Assert.assertTrue(true);
				flag = true;
			}
		} catch (Exception e) {
			System.out.println("Go to Application setting Error");
		}
		return flag;
	}

	public  void scrollChannelstillVisibleByName(int count, String xpathvalue, String lookup, String expected) {
		try {
			String s = ReadAttributeValue.getAttributes(xpathvalue, lookup, driver.getPageSource());

			if (!s.equals(expected)) {

				for (int i = 0; i <= count; i++) {
					Dimension dimensions = driver.manage().window().getSize();
					Double screenHeightStart = dimensions.getHeight() * 0.5;
					int scrollStart = screenHeightStart.intValue();
					System.out.println("s=" + scrollStart);
					Double screenHeightEnd = dimensions.getHeight() * 0.2;
					int scrollEnd = screenHeightEnd.intValue();
					driver.swipe(0, scrollStart, 0, scrollEnd, 2000);
					try {
						if (ReadAttributeValue.getAttributes(xpathvalue, lookup, driver.getPageSource())
								.contains(expected)) {
							System.out.println("element Found");
							break;
						}
					} catch (Exception p) {
						Reporter.log("element not found", true);
					}
				}
			} else {
				Reporter.log("element found", true);
			}
		} catch (Exception e1) {

		}
		scrollChannelsUp(1);
	}
	public  boolean scrollToElementaxct(By by){
		boolean done = false;
		for(int i=0;i<=8;i++) {
			try {
//				future.get(8, TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
				scrollToDown(1);
				if (driver.findElement(by).isDisplayed()) {
					done = true;
					break;
				}
				} catch (Exception e) {
			}
		}
		return done;
	}
	public  void scrollTillElementFound(By by) {
		WebElement element = driver.findElement(by);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public  boolean scrollFound(WebElement webelement, int scrollPoints) {
		try {
			Actions dragger = new Actions(driver);
			// drag downwards
			int numberOfPixelsToDragTheScrollbarDown = 10;
			for (int i = 10; i < scrollPoints; i = i + numberOfPixelsToDragTheScrollbarDown) {
				dragger.moveToElement(webelement).clickAndHold().moveByOffset(0, numberOfPixelsToDragTheScrollbarDown)
						.release(webelement).build().perform();
			}
			Thread.sleep(500);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public  void scrollTo(By element, int count) {
		Boolean isExists = false;
		for (int i = 0; i < count; i++) {
			isExists = isElementPresent(driver, element);
			Reporter.log("" + isExists, true);
			if (isExists) {
				break;
			} else {
				swipeUP();
			}
		}
		Reporter.log("" + isExists, true);
	}
	public  void scrollChannelsUp(int j) {
		System.out.println("Scroll Up");

		for (int i = 0; i < j; i++) {
			Dimension size = driver.manage().window().getSize();

			int starty = (int) (size.height * 0.80);

			// Find endy point which is at top side of screen.
			int endy = (int) (size.height * 0.40);

			// Find horizontal point where you wants to swipe. It is in middle
			// of screen width.
			int startx = size.width / 2;

			// Swipe from Bottom to Top.
			driver.swipe(0, starty, 0, endy, 2000);
		}

	}

	public  void scrollChannelsdown(int j) {
		System.out.println("Scroll Down");
		for (int i = 0; i <= j; i++) {
			Dimension size = driver.manage().window().getSize();
			// System.out.println(i);

			int starty = (int) (size.height * 0.80);

			// Find endy point which is at top side of screen.
			int endy = (int) (size.height * 0.40);

			// Find horizontal point where you wants to swipe. It is in middle
			// of screen width.
			int startx = size.width / 2;

			// Swipe from Bottom to Top.
			driver.swipe(0, endy, 0, starty, 2000);

		}

	}

	public  void getWindowHandle(String title) {
		Set<String> handles = driver.getWindowHandles();// We will use this if
														// windows is greater
														// than 1 - otherwise we
														// just getwindowhandle
														// ();
		if (handles.size() >= 1) {
			System.out.println("Number of broiwsers opened are" + handles.size());
			for (String handle : handles) {
				driver.switchTo().window(handle);
				if (driver.getTitle().contains(title)) {
					driver.getWindowHandle();
					break;
				}

			}
		}

	}

	public  void swipeUpElement(MobileElement element, int duration) {
		int topY = element.getLocation().getY();
		int bottomY = topY + element.getSize().getHeight();
		int centerX = element.getLocation().getX() + (element.getSize().getWidth() / 2);
		driver.swipe(centerX, bottomY, centerX, topY, duration);
	}

	public  void visibility(WebElement e) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'visibility: visible;');", e);
	}

	public  void display(WebElement e) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'display: block;');", e);
	}

	public  void top(WebElement e) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'top: -700px;');", e);
	}

	public  void top1(WebElement e, int px) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'top: -390px;');", e);
	}

	public  WebElement explicitWait(final By by) {
		WebElement element = null;
		try {
			// WebDriverWait wait = new WebDriverWait(driver, 30);
			// wait.until(ExpectedConditions.presenceOfElementLocated(by));
			final Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					// Wait for the condition
					.withTimeout(15, TimeUnit.SECONDS)
					// which to check for the condition with interval of 5
					// seconds.
					.pollingEvery(2, TimeUnit.SECONDS)
					// Which will ignore the NoSuchElementException
					.ignoring(NoSuchElementException.class).ignoring(TimeoutException.class)
					.ignoring(ElementNotVisibleException.class);
			element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {

					return wait.until(ExpectedConditions.presenceOfElementLocated(by));
				}
			});

		} catch (TimeoutException toe) {
			Reporter.log("--with in specified time element not able to find out OR given locator is wrong--", true);
			toe.printStackTrace();
			Assert.assertTrue(false);
		}
		return element;
	}

	public  void slidermovement(WebElement e, int pixelsLeft, int pixelright) {
		Actions dragger = new Actions(driver);
		dragger.moveToElement(e).clickAndHold().moveByOffset(pixelsLeft, pixelright).release().perform();
	}

	public void dragAndDropBy() {
		Actions actn = new Actions(driver);
		actn.dragAndDropBy(driver.findElement(By.className("mCSB_dragger_bar")), 0, -50).build().perform();

	}

	public  void moveto(WebElement e) {
		try {
			Actions actn = new Actions(driver);
			actn.moveToElement(e).perform();
			impicitWait(10);
		} catch (MoveTargetOutOfBoundsException mtobe) {
			Reporter.log(
					"-please wait some more time and perform move to operation to avoid this exception-because when you are trying to move the element is not visible-",
					true);
			mtobe.printStackTrace();
		}

	}

	public  void swipeToElement(MobileElement e) {

		for (int i = 0; i < 4; i++) {
			try {
				e.isDisplayed();
				break;
			} catch (Exception q) {
				// TODO Auto-generated catch block
				// ((AppiumDriver) driver).swipe(305, 1552, 775, 1605, 2000);

				// ((MobileElement) e).swipe(SwipeElementDirection.UP,2000);
				driver.swipe(475, 500, 475, 400, 400);
			}

		}
	}

	public  void swipeElementExample(WebElement el) {
		String orientation = ((AppiumDriver) driver).getOrientation().value();

		// get the X coordinate of the upper left corner of the element, then
		// add the element's width to get the rightmost X value of the element
		int leftX = el.getLocation().getX();
		int rightX = leftX + el.getSize().getWidth();

		// get the Y coordinate of the upper left corner of the element, then
		// subtract the height to get the lowest Y value of the element
		int upperY = el.getLocation().getY();
		int lowerY = upperY - el.getSize().getHeight();
		int middleY = (upperY - lowerY) / 2;

		if (orientation.equals("portrait")) {
			// Swipe from just inside the left-middle to just inside the
			// right-middle of the element over 500ms
			((AppiumDriver) driver).swipe(leftX + 5, middleY, rightX - 5, middleY, 500);
		} else if (orientation.equals("landscape")) {
			// Swipe from just inside the right-middle to just inside the
			// left-middle of the element over 500ms
			((AppiumDriver) driver).swipe(rightX - 5, middleY, leftX + 5, middleY, 500);
		}
	}

	/**
	 *
	 * @param locator
	 * @throws Exception
	 */
	// This was my breakthrough. My first partially working code.
	public void mouseOverClick(final WebElement e, WebDriver driver) {
		final long startTime = System.currentTimeMillis();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS).pollingEvery(2,
					TimeUnit.SECONDS);
			// .ignoring(NoSuchElementException.class );
			wait.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					try {
						Actions action = new Actions(driver);
						action.moveToElement(e).click().build().perform();
						implicitWait(2);
						return true;
					} catch (StaleElementReferenceException e) {
						return false;
					}
				}
			});
		} catch (NoSuchElementException nsee) {
			System.out.println("no such element exception::::");
		}

		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Finished click after waiting for " + totalTime + " milliseconds.");
	}

	public void scrolling() {

		List<WebElement> image = driver.findElements(By.xpath(
				"/html/body/div[3]/div/div/div[2]/div[1]/div[1]/div/div[2]/div/div[2]/div/div/div[1]/div/div[2]/div[2]/div//span[2]"));

		for (WebElement clickimg : image) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", clickimg);
			Reporter.log(clickimg.getText(), true);
		}
	}

	public void contextclick(WebElement e) {
		Actions a = new Actions(driver);
		a.contextClick(e).perform();
	}

	public  void dragAndDrop(AndroidDriver driver, WebElement drag, WebElement drop) {
		// Actions a = new Actions(driver);
		// a.dragAndDrop(drag, drop).release().perform();
		TouchAction action = new TouchAction((AndroidDriver) driver);
		action.longPress(drag).moveTo(drop).release().perform();
	}

	public  void dragAndDrop1(IOSDriver driver, WebElement drag, WebElement drop) {
		// Actions a = new Actions(driver);
		// a.dragAndDrop(drag, drop).release().perform();
		TouchAction action = new TouchAction((IOSDriver) driver);
		action.longPress(drag).moveTo(drop).release().perform();
	}

	public static void impicitWait(int seconds) {
		try {
			seconds = seconds * 1000;
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public  void waitforElment(String string){
		for(int i=0;i<=10;i++){
			impicitWait(1);
			try {
				if(driver.findElement(By.xpath(elementReader.getPropertyElement(string))).isDisplayed()){
					break;
				}else {
					impicitWait(2);
				}
			} catch (Exception e) {
				impicitWait(3);
			}
		}
	}

	public void waitSleep(int time) {
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void clickHoldDragDrop(WebElement e, WebElement e1) {
		Actions action = new Actions(driver);
		action.moveToElement(e).build().perform();
		action.clickAndHold(e).build().perform();
		action.dragAndDrop(e, e1).build().perform();
		action.release(e).perform();

	}

	/**
	 * Page scroll down through java script.For down increase y-axis
	 */
	public void scrollDown() {
		// EventFiringWebDriver evd=new EventFiringWebDriver(driver);
		// evd.executeScript("window.scrollBy(100,600)", "");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,400)", "");

	}

	public void scrollUp() {
		// EventFiringWebDriver evd=new EventFiringWebDriver(driver);
		// evd.executeScript("window.scrollBy(100,600)", "");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-200)", "");

	}

	public void scrollIntoView() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollTop = arguments[1];", driver.findElement(By.id("entity-detail-page")),
				100);

	}

	public void assertEquals(String s, String s1) {
		Assert.assertEquals(s, s1);
		Reporter.log("" + s1 + " and " + s + " are same", true);
	}

	public void verifyText(String s, String s1) {
		boolean flag = false;
		if (s1.contains(s)) {
			flag = true;
			Reporter.log("" + s1 + " and " + s + " are same", true);
			Assert.assertTrue(flag);
		} else {
			Reporter.log("" + s1 + " and " + s + " are not same", true);
			Assert.assertTrue(flag);
		}

	}

	/**
	 * containsAll() returns true if the collection contains all the elements in
	 * the parameter collection, and false if not.
	 * 
	 * @param <T>
	 * @param a
	 * @param b
	 */
	public <T> void verifyListData(List<T> a, List<T> b) {
		boolean flag = false;
		String mismatchVins = null;
		if (a.containsAll(b)) {
			flag = a.removeAll(b);
			if (flag) {
				mismatchVins = getMismatchVins(a);
			}
		} else {
			flag = b.removeAll(a);
			if (flag) {
				mismatchVins = getMismatchVins(b);
			}
		}
		if (mismatchVins != null) {
			Reporter.log("not equals data:" + mismatchVins, true);
			Assert.assertTrue(false);
		} else {
			Reporter.log("equals data:", true);
			Assert.assertTrue(true);
		}

	}

	/**
	 * 
	 * @param b
	 * @return
	 */
	public <T> String getMismatchVins(List<T> b) {
		StringBuilder mismatchVins = new StringBuilder();
		int i = 0;
		for (T mismatch : b) {
			i++;
			if (i < b.size() && i != b.size()) {
				mismatchVins.append(mismatch).append(",");
			} else {
				mismatchVins.append(mismatch);
			}
			if (i == b.size()) {
				break;
			}
		}
		String mismatch1;
		if (mismatchVins.length() > b.size()) {
			mismatch1 = mismatchVins.substring(0, 99);
		} else {
			mismatch1 = mismatchVins.toString();
		}
		return mismatch1;
	}

	public String mainWindowHandle() {
		String mainWindow = driver.getWindowHandle();
		System.out.println(mainWindow + ":main window");
		return mainWindow;
	}

	public String mainWindowTitle() {
		String mainWindowTitle = driver.getTitle();
		System.out.println(mainWindowTitle + ":main window title");
		return mainWindowTitle;
	}

	public void childWindowHandles(String mainWindowHandle) {
		// maximizeWindow();
		Set<String> s = driver.getWindowHandles();
		Iterator<String> ite = s.iterator();
		while (ite.hasNext()) {
			String childWindow = ite.next().toString();
			if (!childWindow.contains(mainWindowHandle)) {
				driver.switchTo().window(childWindow);
				String childWindow_title = driver.getTitle();
				System.out.println(childWindow_title + ":after switching child window");
				implicitWait(10);
			}
		}
	}

	public boolean switchToMainWidnow(String mainWindowTitle) {
		boolean flag = false;
		int dSize = driver.getWindowHandles().size();
		System.out.println("windows size before closing child window:" + dSize);
		if (dSize > 1) {
			driver.close();
		}
		int dSize1 = driver.getWindowHandles().size();
		System.out.println("windows size after closing child window:" + dSize1);
		Set<String> availableWindows = driver.getWindowHandles();
		if (!availableWindows.isEmpty()) {
			for (String windowId : availableWindows) {
				if (driver.switchTo().window(windowId).getTitle().equals(mainWindowTitle)) {
					flag = true;
				}
			}
		}
		return flag;
	}

	public void switchToDefault() {
		driver.switchTo().defaultContent(); // you are now outside both frames
	}

	public  void waitForVisible(final By by, int waitTime) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}

	public  void waitForElementVisible(WebElement ele, long i) {
		WebDriverWait wait = new WebDriverWait(driver, i);
		wait.until(ExpectedConditions.elementToBeClickable(ele));

	}

	public  void waitForElementTillDisplayed(WebElement ele, long i) {
		WebDriverWait wait = new WebDriverWait(driver, i);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public  void implicitWait(int seconds) {
		try {
			driver.manage().timeouts().implicitlyWait(seconds * 5, TimeUnit.SECONDS);
		} catch (TimeoutException toe) {
			toe.printStackTrace();
		}
	}

	public void implicitWait(int seconds, WebDriver driver) {

		driver.manage().timeouts().implicitlyWait(seconds * 10, TimeUnit.SECONDS);
	}

	public void pageLoad(int seconds) {
		driver.manage().timeouts().pageLoadTimeout(seconds * 10, TimeUnit.SECONDS);
	}

	public boolean waitForElement(WebElement e, int sleeptTime, int rounds) {
		boolean flag = false;

		L1: for (int i = 0; i < rounds; i++) {
			Reporter.log("-waiting for element " + (i + 1) + " time-", true);
			impicitWait(sleeptTime);
			try {
				if (isElementPresent(e)) {
					flag = true;
					Assert.assertTrue(flag);
					break;
				} else {
					throw new Exception("element not displayed");
				}
			} catch (Exception ee) {
				Reporter.log("-wait for element-" + e, true);
				continue L1;
			}
		}

		if (flag) {
			Reporter.log("element displayed-" + flag, true);

		} else {
			Reporter.log("element not displayed in the given time-" + flag, true);

		}

		return flag;
	}

	public  boolean waitForDriver(WebElement e, int sleeptTime, int rounds) {
		boolean flag = false;
		L1: for (int i = 1; i <= rounds; i++) {

			try {
				Thread.sleep(1000 * sleeptTime);
				if (isElementPresent(e)) {
					flag = true;
					Assert.assertTrue(flag);
					break;

				} else {
					throw new Exception("element not displayed");
				}
			} catch (Exception ee) {
				Reporter.log(i + "-waiting for element-", true);
				continue L1;
			}
		}

		if (flag) {
			Reporter.log("-element displayed-" + flag, true);

		} else {
			Reporter.log("-element not displayed in the given time-" + flag, true);

		}

		return flag;
	}

	public boolean waitForDriver(WebElement e, int sleeptTime, int rounds, int subSleepTime) {
		boolean flag = false;
		L1: for (int i = 1; i <= rounds; i++) {
			driver.manage().timeouts().implicitlyWait(sleeptTime * 10, TimeUnit.SECONDS);
			try {
				if (isElementPresent(e)) {
					flag = true;
					Assert.assertTrue(flag);
					break;
				} else if (i == rounds) {
					driver.navigate().refresh();
					driver.manage().timeouts().implicitlyWait(subSleepTime * 10, TimeUnit.SECONDS);
					if (isElementPresent(e)) {
						flag = true;
						Assert.assertTrue(flag);
						Reporter.log("after reloading the page element is able to find out.", true);
						break;
					} else {
						throw new Exception("element not displayed");
					}
				} else {
					throw new Exception("element not displayed");
				}
			} catch (Exception ee) {
				Reporter.log(i + "-waiting for element-" + e, true);
				continue L1;
			}
		}

		if (flag) {
			Reporter.log("-element displayed-" + flag, true);

		} else {
			Reporter.log("-element not displayed in the given time-" + flag, true);

		}

		return flag;
	}

	/*
	 * public boolean waitForElement(WebElement e, String attribute, String
	 * matchesData) { boolean flag = false; L1: for (int i = 0; i < 3; i++) {
	 * try { if (e.isDisplayed()) { if
	 * (e.getAttribute(attribute).equals(matchesData)) { flag = true; } else {
	 * throw new Exception( "specified attribute element not displayed"); } } }
	 * catch (Exception ee) { Reporter.log("-No such element-", true); continue
	 * L1; }
	 * 
	 * }
	 * 
	 * return flag; }
	 */
	public void waitForElement(final WebElement e, int timeOut, WebDriver driver) {
		new WebDriverWait(driver, timeOut) {
		}.until(new ExpectedCondition<Boolean>() {

			public Boolean apply(WebDriver driverObject) {
				return isElementPresent(e);

			}
		});
	}

	public static boolean isElementPresent(WebElement e) {
		boolean flag = false;
		try {
			if (e.isDisplayed()) {
				flag = true;
				Reporter.log("element is displayed-" + flag, true);
			}
		} catch (NoSuchElementException nsee) {
			flag = false;
			Reporter.log("element not displayed in the page or element locator has given wrongly-" + flag, true);
		}

		return flag;
	}

	public  boolean isElementPresent(AppiumDriver driver, By by) {
		impicitWait(2);
		try {
			driver.findElement(by);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * make the element to be clickable
	 **/
	public WebElement elementToBeClickable(final By by, int timeInSeconds) {
		return new FluentWait<WebDriver>(driver)
				// Wait for the condition
				.withTimeout(timeInSeconds * 10, TimeUnit.SECONDS)
				// which to check for the condition with interval of 5 seconds.
				.pollingEvery(2, TimeUnit.SECONDS)
				// Which will ignore the NoSuchElementException
				.ignoring(NoSuchElementException.class).ignoring(ElementNotVisibleException.class)
				.ignoring(WebDriverException.class).until(new ExpectedCondition<WebElement>() {
					public ExpectedCondition<WebElement> FluentWaitMethodNameObj = ExpectedConditions
							.elementToBeClickable(by);

					public WebElement apply(WebDriver driver) {
						WebElement element = FluentWaitMethodNameObj.apply(driver);
						try {
							if (element != null && element.isDisplayed()) {
								return element;
							} else {
								return null;
							}
						} catch (StaleElementReferenceException e) {
							Reporter.log("--Stale Element Exception--", true);
							e.printStackTrace();
							return null;
						} catch (TimeoutException toe) {
							Reporter.log("--Time Out Exception--", true);
							toe.printStackTrace();
							return null;
						}
					}

				});
	}

	/**
	 * 
	 * @param e
	 * @return
	 */
	public WebElement presenceOfElement(final By by, int timeInSeconds) {

		return new FluentWait<WebDriver>(driver)
				// Wait for the condition
				.withTimeout(timeInSeconds * 10, TimeUnit.SECONDS)
				// which to check for the condition with interval of 5 seconds.
				.pollingEvery(2, TimeUnit.SECONDS)
				// Which will ignore the NoSuchElementException
				.ignoring(NoSuchElementException.class).ignoring(ElementNotVisibleException.class)
				.ignoring(WebDriverException.class).until(new ExpectedCondition<WebElement>() {
					public ExpectedCondition<WebElement> FluentWaitMethodNameObj = ExpectedConditions
							.presenceOfElementLocated(by);

					public WebElement apply(WebDriver driver) {
						WebElement element = FluentWaitMethodNameObj.apply(driver);
						try {
							if (element != null && element.isDisplayed()) {
								return element;
							} else {
								return null;
							}
						} catch (StaleElementReferenceException e) {
							Reporter.log("--Stale Element Exception--", true);
							e.printStackTrace();
							return null;
						} catch (TimeoutException toe) {
							Reporter.log("--Time Out Exception--", true);
							toe.printStackTrace();
							return null;
						}
					}

				});
	}

	/**
	 * 
	 * @param locator
	 * @return
	 */
	public WebElement visibilityOfElement(final By by, int timeInSeconds) {
		return new FluentWait<WebDriver>(driver)
				// Wait for the condition
				.withTimeout(timeInSeconds * 10, TimeUnit.SECONDS)
				// which to check for the condition with interval of 5 seconds.
				.pollingEvery(2, TimeUnit.SECONDS)
				// Which will ignore the NoSuchElementException
				.ignoring(NoSuchElementException.class).ignoring(ElementNotVisibleException.class)
				.ignoring(WebDriverException.class).until(new ExpectedCondition<WebElement>() {
					public ExpectedCondition<WebElement> FluentWaitMethodNameObj = ExpectedConditions
							.visibilityOfElementLocated(by);

					public WebElement apply(WebDriver driver) {
						WebElement element = FluentWaitMethodNameObj.apply(driver);
						try {
							if (element != null && element.isDisplayed()) {
								return element;
							} else {
								return null;
							}
						} catch (StaleElementReferenceException e) {
							Reporter.log("--Stale Element Exception--", true);
							e.printStackTrace();
							return null;
						} catch (TimeoutException toe) {
							Reporter.log("--Time Out Exception--", true);
							toe.printStackTrace();
							return null;
						}
					}

				});
	}

	/*
	 * public void highlight( WebElement element) { try{
	 * 
	 * for (int i = 0; i < 2; i++) { //JavascriptExecutor js =
	 * (JavascriptExecutor) driver; ((JavascriptExecutor) driver).executeScript(
	 * "arguments[0].setAttribute('style', arguments[1]);" , element,
	 * "color: yellow; border: 10px solid yellow; background-color: green;");
	 * ((JavascriptExecutor) driver).executeScript(
	 * "arguments[0].setAttribute('style', arguments[1]);" , element,
	 * "color: yellow; border: 10px solid yellow; background-color: green;");
	 * ((JavascriptExecutor) driver).executeScript(
	 * "arguments[0].setAttribute('style', arguments[1]);" , element,
	 * "color: yellow; border: 10px solid yellow; background-color: green;");
	 * ((JavascriptExecutor) driver).executeScript(
	 * "arguments[0].setAttribute('style', arguments[1]);" , element,
	 * "color: yellow; border: 10px solid yellow; background-color: green;");
	 * ((JavascriptExecutor) driver).executeScript(
	 * "arguments[0].setAttribute('style', arguments[1]);" , element,
	 * "color: yellow; border: 10px solid yellow; background-color: green;");
	 * ((JavascriptExecutor) driver).executeScript(
	 * "arguments[0].setAttribute('style', arguments[1]);" , element, ""); }
	 * }catch(WebDriverException e){ e.printStackTrace();
	 * 
	 * } }
	 */

	/*
	 * public void highlight(String text) { try {
	 * 
	 * for (int i = 0; i < 4; i++) { JavascriptExecutor js =
	 * (JavascriptExecutor) driver; js.executeScript(
	 * "arguments[0].setAttribute('style', arguments[1]);", text,
	 * "color: yellow; border: 10px solid yellow; background-color: green;");
	 * js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
	 * text,
	 * "color: yellow; border: 10px solid yellow; background-color: green;");
	 * js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
	 * text,
	 * "color: yellow; border: 10px solid yellow; background-color: green;");
	 * js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
	 * text,
	 * "color: yellow; border: 10px solid yellow; background-color: green;");
	 * js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
	 * text,
	 * "color: yellow; border: 10px solid yellow; background-color: green;");
	 * js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
	 * text, ""); } } catch (WebDriverException e) { e.printStackTrace(); } }
	 */

	/*
	 * public void clearAllHistory() { try { Actions action=new Actions(driver);
	 * action
	 * .keyDown(Keys.CONTROL).keyDown(Keys.SHIFT).keyDown(Keys.DELETE).perform
	 * ();
	 * action.keyUp(Keys.DELETE).keyUp(Keys.SHIFT).keyUp(Keys.CONTROL).perform
	 * (); impicitWait(2); action.sendKeys(Keys.chord("e")).perform();
	 * action.keyDown(Keys.ENTER).keyUp(Keys.ENTER).perform();
	 * }catch(IllegalArgumentException ilae) {
	 * 
	 * ilae.printStackTrace();
	 * 
	 * }
	 * 
	 * }
	 */
	public String datemethod(String dateFrmat) {
		Date date = null;
		date = new Date();
		DateFormat formatter = null;// "dd MMM yyyy HH:mm:ss z"
		formatter = new SimpleDateFormat(dateFrmat);
		formatter.setTimeZone(TimeZone.getTimeZone("EST"));
		Reporter.log("Time in EST  " + formatter.format(date), true);
		return formatter.format(date);
	}

	public long timeDiff(String date, String date1) {
		DateFormat formatter = null;
		formatter = new SimpleDateFormat("HH:mm:ss z");
		formatter.setTimeZone(TimeZone.getTimeZone("EST"));
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = formatter.parse(date);
			d2 = formatter.parse(date1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long diff = d2.getTime() - d1.getTime();// as given
		long seconds = TimeUnit.MILLISECONDS.toSeconds(diff);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);
		Reporter.log("in minutes      " + minutes);
		Reporter.log("in seconds      " + seconds);

		return seconds;

	}

	public  void adbCommand(String shellCommandcommand, long time) {

		String command = String.format(shellCommandcommand);
		Process process = null;

		try {
			process = Runtime.getRuntime().exec(command);
			System.out.println("after adb command");
			Thread.sleep(time);

		} catch (Exception e2) {
			// TODO Auto-generated catch block

		}
	}

	public  void waitmethod() {
		try {
			By e = By.id("xyz");
			driver.findElement(e).click();
		} catch (Exception p) {
			// TODO Auto-generated catch block

		}
	}

	public  void swipeUP() {
		driver.swipe(475, 500, 475, 200, 400);
		impicitWait(5);
	}

	public  void resetApp() {
		impicitWait(5);
		driver.resetApp();
		impicitWait(15);
	}

	public  void reLaunchApp() {
		driver.closeApp();
		driver.launchApp();
	}

	public  void scrollToElement(WebElement element) {
		System.out.println("scrollToElement start");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("element", ((RemoteWebElement) element).getId());
		scrollObject.put("direction", "down");
		js.executeScript("mobile: scroll", scrollObject);
		impicitWait(2);
		System.out.println("scrollToElement end");
	}

	public  void scrollForAWhile() {
		for (int i = 0; i <= 2; i++) {
			Dimension dimensions = driver.manage().window().getSize();
			Double screenHeightStart = dimensions.getHeight() * 0.5;
			int scrollStart = screenHeightStart.intValue();
			System.out.println("s=" + scrollStart);
			Double screenHeightEnd = dimensions.getHeight() * 0.2;
			int scrollEnd = screenHeightEnd.intValue();
			driver.swipe(0, scrollStart, 0, scrollEnd, 2000);
		}
		impicitWait(2);
	}

	public  void scroll() {
		for (int i = 0; i <= 3; i++) {
			Dimension dimensions = driver.manage().window().getSize();
			Double screenHeightStart = dimensions.getHeight() * 0.5;
			int scrollStart = screenHeightStart.intValue();
			System.out.println("s=" + scrollStart);
			Double screenHeightEnd = dimensions.getHeight() * 0.2;
			int scrollEnd = screenHeightEnd.intValue();
			driver.swipe(0, scrollStart, 0, scrollEnd, 2000);
		}
		impicitWait(2);
	}

	public  void scroll1() {
		for (int i = 0; i <= 1; i++) {
			Dimension dimensions = driver.manage().window().getSize();
			Double screenHeightStart = dimensions.getHeight() * 0.5;
			int scrollStart = screenHeightStart.intValue();
			System.out.println("s=" + scrollStart);
			Double screenHeightEnd = dimensions.getHeight() * 0.2;
			int scrollEnd = screenHeightEnd.intValue();
			driver.swipe(0, scrollStart, 0, scrollEnd, 2000);
		}
		impicitWait(2);
	}

	public  void scrollForAWhile1() {
		Dimension dimensions = driver.manage().window().getSize();
		int height = dimensions.getHeight();
		System.out.println("height: " + height);
		Double screenHeightStart = dimensions.getHeight() * 0.3;
		int scrollStart = screenHeightStart.intValue();
		System.out.println("s=" + scrollStart);
		Double screenHeightEnd = dimensions.getHeight() * 0.1;
		int scrollEnd = screenHeightEnd.intValue();
		System.out.println("e=" + scrollEnd);
		int startx = dimensions.width / 2;
		driver.swipe(scrollStart, startx, scrollEnd, startx, 2000);
		impicitWait(10);
	}

	public  void swipeRightToLeft() throws InterruptedException {
		Dimension size;
		// Get the size of screen.
		size = driver.manage().window().getSize();
		System.out.println(size);

		// Find swipe start and end point from screen's with and height.
		// Find startx point which is at right side of screen.
		int startx = (int) (size.width * 0.70);
		// Find endx point which is at left side of screen.
		int endx = (int) (size.width * 0.30);
		// Find vertical point where you wants to swipe. It is in middle of
		// screen height.
		int starty = size.height / 2;
		System.out.println("startx = " + startx + " ,endx = " + endx + " , starty = " + starty);

		// Swipe from Right to Left.
		driver.swipe(startx, starty, endx, starty, 3000);
		Thread.sleep(2000);

	}
	
	public  void swipeToRight(By by) {
		try {
		WebElement ele = driver.findElement(by);
		String x = ele.getAttribute("x");
		String y = ele.getAttribute("y");
		Map<String, Object> params21 = new HashMap<>();
		params21.put("location", x + "," + y);
		params21.put("operation", "down");
		Object result21 = driver.executeScript("mobile:touch:tap", params21);

		Map<String, Object> params22 = new HashMap<>();
		List<String> coordinates22 = new ArrayList<>();
		coordinates22.add("365,117");
		coordinates22.add("42,117");
		params22.put("location", coordinates22);
		params22.put("auxiliary", "notap");
		Object result22 = driver.executeScript("mobile:touch:drag", params22);

		Map<String, Object> params23 = new HashMap<>();
		params23.put("location", "0,117");
		params23.put("operation", "up");
		Object result23 = driver.executeScript("mobile:touch:tap", params23);
		} catch (Exception e) {

		}
		}

		public  void swipeToLeft(By by) {
		impicitWait(4);
		try {
		WebElement ele = driver.findElement(by);
		String x = ele.getAttribute("x");
		String y = ele.getAttribute("y");

		Map<String, Object> params48 = new HashMap<>();
		params48.put("location", x + "," + y);
		params48.put("operation", "down");
		Object result48 = driver.executeScript("mobile:touch:tap", params48);

		Map<String, Object> params49 = new HashMap<>();
		List<String> coordinates49 = new ArrayList<>();
		coordinates49.add("184,107");
		coordinates49.add("459,107");
		params49.put("location", coordinates49);
		params49.put("auxiliary", "notap");
		Object result49 = driver.executeScript("mobile:touch:drag", params49);

		Map<String, Object> params51 = new HashMap<>();
		params51.put("location", "478,107");
		params51.put("operation", "up");
		Object result51 = driver.executeScript("mobile:touch:tap", params51);
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}

		}

	public  String elementExit(String xpathvalue, String lookup) {
		String s = null;
		try {
			s = ReadAttributeValueWithXpath.getAttributes(xpathvalue, lookup, driver.getPageSource());

			System.out.println("====s===" + s);
		} catch (Exception e) {
		}
		return s;
	}

	public  void scrollPagetillElementIsVisible(WebElement desiredElement) {
		boolean check = true;
		while (check) {

			String isVisible = desiredElement.getAttribute("text");
			System.out.println("isVisible: " + isVisible);
			if (isVisible.equalsIgnoreCase("false")) {
				scrollToDown(1);
				impicitWait(1);
			} else if (isVisible.equalsIgnoreCase("true")) {
				check = false;
			}
		}

	}

	public  static void log(String logs) {
		Reporter.log(logs);
		//testReporter.log(LogStatus.INFO, logs);
		System.out.println(logs);
	}
	
	public static void errorlog(String logs) {
		String ANSI_RED = "\u001B[31m";
		Reporter.log(logs+ANSI_RED);
		//testReporter.log(LogStatus.INFO, logs+ANSI_RED);
		System.out.println(logs+ANSI_RED);
	}

	public   static void log(String logs, boolean dis) {
		Reporter.log(logs, dis);
		//testReporter.log(LogStatus.INFO, logs);
		System.out.println(logs);
	}

	public static void log(String logs, int dis) {
		Reporter.log(logs, dis);
		//testReporter.log(LogStatus.INFO, logs);
		System.out.println(logs);
	}

	public static void log(String logs, int integer, boolean dis) {
		Reporter.log(logs, integer, dis);
		//testReporter.log(LogStatus.INFO, logs);
		System.out.println(logs);

	}
	public boolean isExist(By by){
		boolean flag = false;
		try{
			WebElement el = driver.findElement(by);
			flag = true;
		}
		catch(Exception ex){
			flag = false;
		}
		return flag;
	}

	public  void scrollUntilTextExists(String string) {
		Common common = new Common(driver);
		boolean value = false;
		for (int i = 0; i <= 10; i++) {
			if (!driver.getPageSource().contains(string)) {
				scrollToDown(1);
			}
			else {
				value = true;	
				break;}
		}
		if(value == false){
			for (int i = 0; i <= 10; i++) {
				if (!driver.getPageSource().contains(string)) {
					scrollToUp(1);
				}
				else {
					break;}
			}
		}
	}

	public  void scrollToDown(int j) {
		for (int i = 0; i <= j; i++) {
			Dimension dimensions = driver.manage().window().getSize();
			Double screenHeightStart = dimensions.getHeight() * 0.4;
			int scrollStart = screenHeightStart.intValue();
			//System.out.println("s=" + scrollStart);
			Double screenHeightEnd = dimensions.getHeight() * 0.2;
			int scrollEnd = screenHeightEnd.intValue();
			driver.swipe(0, scrollStart, 0, scrollEnd, 2000);
		}
	}
	public  void scrollToUp(int j) {
		for (int i = 0; i <= j; i++) {
			Dimension dimensions = driver.manage().window().getSize();
			Double screenHeightStart = dimensions.getHeight() * 0.3;
			int scrollStart = screenHeightStart.intValue();
			//System.out.println("s=" + scrollStart);
			Double screenHeightEnd = dimensions.getHeight() * 0.6;
			int scrollEnd = screenHeightEnd.intValue();
			driver.swipe(0, scrollStart, 0, scrollEnd, 2000);
		}
	}

	public void loginToApp() {
		Common common = new Common(driver);
		Common.impicitWait(5);
		ValidationUtil validationUtil = new ValidationUtil();
		try{
		UserObj usr = users.get("prod1");
		System.out.println("In Login method");
		common.waitForElementTillDisplayed(driver.findElement(By.xpath(elementReader.getPropertyElement("Login.UserName"))), 20);
		common.waitforElment("Login.UserName");
		element = validationUtil.validateWebElement("Login.UserName", driver);
		Common.log("Entering Username");
		element.clear();
		element.sendKeys(usr.getUserName());
		element = validationUtil.validateWebElement("Login.Password", driver);
		Common.log("Entering Password");
		element.clear();
		element.sendKeys(usr.getPassword());
        Common.impicitWait(4);
        validationUtil.validateWebElement("Login.Signin", driver).click();
        Common.impicitWait(7);
		}catch(AndriodException e){
			throw new AndriodException(this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), 
					e.getMessage());
		}
		
	}
	
	/*
	 * Start Capturing Device Vitals
	 */
	public void deviceVitalsStart(AppiumDriver driver) {
		Map<String, Object> params = new HashMap<>();
		List<String> vitals1 = new ArrayList<>();
		vitals1.add("all");
		params.put("vitals", vitals1);
		List<String> sources1 = new ArrayList<>();
		sources1.add("everest");
		sources1.add("device");
		params.put("sources", sources1);
		System.out.println("Device Vitals starts");
		Object result = driver.executeScript("mobile:monitor:start", params);
	}

	/*
	 * Stop Capturing Device Vitals
	 */
	public void deviceVitalsStop(AppiumDriver driver) {
		Map<String, Object> params = new HashMap<>();
		System.out.println("Device Vitals stops");
		driver.executeScript("mobile:monitor:stop", params);
	}	

}
