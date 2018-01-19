package com.sxm.framework.common;

import java.io.FileInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.util.PDFTextStripper;
import org.openqa.selenium.By;
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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.google.common.base.Function;

/**
 * This class contains all the common methods for common selenium events
 * which can be reused through out the project.
 * 
 * 
 */
public class WebCommon {
    private SeleniumDriverUtil selenium = new SeleniumDriverUtil();
	private  WebDriver driver = selenium.getDriver();

	/**
	 *This method is used for switch windows
	 */
	public  void getWindowHandle(String title) {
		Set<String> handles = driver.getWindowHandles();
		if (handles.size() >= 1) {
			System.out.println("Number of browsers opened are"
					+ handles.size());
			for (String handle : handles) {
				driver.switchTo().window(handle);
				if (driver.getTitle().contains(title)) {
					driver.getWindowHandle();
					break;
				}

			}
		}

	}

	/**
	 * method to handle scrolling to a particular webelement
	 * @param e
	 *         Webelement e
	 * 
	 */
	public  void scrollIntoViewPage(WebElement e) {

		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView();", e);
	}

	/**
	 * method to modify the css value of Top attribute
	 * @param e, px
	 *         Webelement e
	 *         Pixel position px
	 *            
	 */
	public  void top(WebElement e, int px) {
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].setAttribute('style', 'top: 70px;');", e);
	}

	/**
	 * method is used as handle Explicit wait
	 * 
	 */
	public  WebElement explicitWait(final By by) {
		final Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(60, TimeUnit.SECONDS)
				.pollingEvery(2, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class)
				.ignoring(TimeoutException.class)
				.ignoring(ElementNotVisibleException.class)
				.ignoring(Exception.class);
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return wait.until(ExpectedConditions
						.presenceOfElementLocated(by));
			}
		});
		return element;
	}

	/**
	 * @param mouseClick
	 *            method is to perform click operation using mouse interactions
	 *            This method was written to handle chrome related issues on
	 *            clicking on an element
	 */
	public  void mouseClick(WebElement e) {
		Actions action = new Actions(driver);
		action.moveToElement(e).click().perform();
	}

	/**
	 * @param slidermovement
	 *            method is to perform drag and drop the slider from one
	 *            position to other position *
	 */
	public void slidermovement(WebElement e, int pixelsLeft,
			int pixelright) {
		Actions dragger = new Actions(driver);
		dragger.moveToElement(e).clickAndHold()
				.moveByOffset(pixelsLeft, pixelright).release().perform();
	}

	/**
	 * @param moveto
	 *            (WebElement e) method is to perform mouse over on particular
	 *            webelement *
	 */
	public  void moveto(WebElement e) {
		try {
			Actions actn = new Actions(driver);
			actn.moveToElement(e).perform();
		} catch (MoveTargetOutOfBoundsException mtobe) {
			Reporter.log(
					"-please wait some more time and perform move to operation to avoid this exception-because when you are trying to move the element is not visible-",
					true);
			mtobe.printStackTrace();
		}

	}

	/**
	 * @param scrolling
	 *            () scrolling howard stern NPL page to see all the available
	 *            episodes *
	 */
	public  void scrolling() {

		List<WebElement> image = driver
				.findElements(By
						.xpath("/html/body/div[3]/div/div/div[2]/div[1]/div[1]/div/div[2]/div/div[2]/div/div/div[1]/div/div[2]/div[2]/div//span[2]"));

		for (WebElement clickimg : image) {
			((JavascriptExecutor) driver).executeScript(
					"arguments[0].scrollIntoView(true);", clickimg);
			Reporter.log(clickimg.getText(), true);
		}
	}

	/**
	 * @param contextclick
	 *            (WebElement e) to perform right click on a particular
	 *            webelement
	 */
	/**
	 * Metho
	 * @param e
	 */
	public void contextclick(WebElement e) {
		Actions a = new Actions(driver);
		a.contextClick(e).perform();
	}

	/**
	 * @param dragAndDrop
	 *            (WebElement drag, WebElement drop) to perform drag and drop of
	 *            a particular element on to the target element
	 */
	public  void dragAndDrop(WebElement drag, WebElement drop) {
		Actions a = new Actions(driver);
		a.dragAndDrop(drag, drop).perform();
	}

	/**
	 * @param dragAndDrop
	 *            (WebElement drag, WebElement drop) to perform drag and drop of
	 *            a particular element on to the target element
	 */
	public void impicitWait(int seconds) {
		try {
			seconds = seconds * 1000;
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public  void clickHoldDragDrop(WebElement e, WebElement e1) {
		Actions action = new Actions(driver);
		action.moveToElement(e).build().perform();
		// action.clickAndHold(e).build().perform();
		action.dragAndDrop(e, e1).build().perform();
		// action.release(e).perform();

	}

	/**
	 * Page scroll down through java script.For down increase y-axis
	 */
	public  void scrollDown() {		
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,200)", "");

	}

	/**
	 * verifyText(String s, String s1) Method to validate the text comparision
	 */

	public  void verifyText(String s, String s1) {
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
	public  <T> void verifyListData(List<T> a, List<T> b) {
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
	public  <T> String getMismatchVins(List<T> b) {
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

	/**
	 * mainWindowHandle() To handle multiple windows
	 */

	public String mainWindowHandle() {
		String mainWindow = driver.getWindowHandle();
		System.out.println(mainWindow + ":main window");
		return mainWindow;
	}

	/**
	 * mainWindowTitle() To handle multiple windows
	 */
	public String mainWindowTitle() {
		String mainWindowTitle = driver.getTitle();
		System.out.println(mainWindowTitle + ":main window title");
		return mainWindowTitle;
	}

	/**
	 * childWindowHandles(String mainWindowHandle) To handle multiple windows
	 */
	public void childWindowHandles(String mainWindowHandle) {
		// maximizeWindow();
		Set<String> s = driver.getWindowHandles();
		Iterator<String> ite = s.iterator();
		while (ite.hasNext()) {
			String childWindow = ite.next().toString();
			if (!childWindow.contains(mainWindowHandle)) {
				driver.switchTo().window(childWindow);
				String childWindow_title = driver.getTitle();
				System.out.println(childWindow_title
						+ ":after switching child window");
				implicitWait(10);
			}
		}
	}

	/**
	 * switchToMainWidnow(String mainWindowTitle) To handle multiple windows
	 */
	/*public boolean switchToMainWidnow(String mainWindowTitle) {
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
				if (driver.switchTo().window(windowId).getTitle()
						.equals(mainWindowTitle)) {
					flag = true;
				}
			}
		}
		return flag;
	}*/

	/**
	 * switchToDefault() To switch back to default page after perfomring actions
	 * in frame tag
	 */
	public void switchToDefault() {
		driver.switchTo().defaultContent(); // you are now outside both frames
	}

	/**
	 * verifyPDFText() To validate the pdf text on Siriusxm Login page
	 */
	public String verifyPDFText() {
		String output = null;
		try {
			URL url = new URL(driver.getCurrentUrl());
			URLConnection urlConn = url.openConnection();
			Reporter.log("current url:" + driver.getCurrentUrl(), true);
			FileInputStream fileToParse = new FileInputStream(urlConn.getURL()
					.getFile());
			PDFParser parser = new PDFParser(fileToParse);
			parser.parse();
			output = new PDFTextStripper().getText(parser.getPDDocument());
			Reporter.log("PDF text is:" + output, true);
			parser.getPDDocument().close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output;
	}

	/**
	 * implicitWait(int seconds) wait command for implicitly waiting for the
	 * page to load
	 */
	public void implicitWait(int seconds) {
		try {
			driver.manage().timeouts()
					.implicitlyWait(seconds * 10, TimeUnit.SECONDS);
		} catch (TimeoutException toe) {
			toe.printStackTrace();
		}
	}

	/**
	 * pageLoad(int seconds) wait for page to load before timout exception is
	 * thrown
	 */
	public void pageLoad(int seconds) {
		driver.manage().timeouts()
				.pageLoadTimeout(seconds * 10, TimeUnit.SECONDS);
	}

	/**
	 * waitForDriver(WebElement e, int sleeptTime, int rounds) wait for
	 * particular element and loop for every given number of seconds
	 */
	public boolean waitForDriver(WebElement e, int sleeptTime, int rounds) {
		boolean flag = false;

		L1: for (int i = 0; i < rounds; i++) {
			Reporter.log("-waiting for element " + (i + 1) + " time-", true);
			driver.manage().timeouts()
					.implicitlyWait(sleeptTime * 10, TimeUnit.SECONDS);
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
			Reporter.log("element not displayed in the given time-" + flag,
					true);

		}

		return flag;
	}

	/**
	 * waitForElement(final WebElement e, int timeOut,WebDriver driver) Selenium
	 * explicit wait command to wait for particular webelement
	 */
	public void waitForElement(final WebElement e, int timeOut,
			WebDriver driver) {
		new WebDriverWait(driver, timeOut) {
		}.until(new ExpectedCondition<Boolean>() {

			public Boolean apply(WebDriver driverObject) {
				return isElementPresent(e);

			}
		});
	}

	/**
	 * isElementPresent(WebElement e) Method to validate a particular webelement
	 * is available on webpage or not
	 */
	public boolean isElementPresent(WebElement e) {
		boolean flag = false;
		try {
			if (e.isDisplayed()) {
				flag = true;
				Reporter.log("element is displayed-" + flag, true);
			}
		} catch (NoSuchElementException nsee) {
			flag = false;
			Reporter.log(
					"element not displayed in the page or element locator has given wrongly-"
							+ flag, true);
		}

		return flag;
	}

	/**
	 * elementToBeClickable(final By by, int timeInSeconds) Method to perform
	 * click operation on any element once it is visible to perform the action
	 */
	public  WebElement elementToBeClickable(final By by, int timeInSeconds) {
		return new FluentWait<WebDriver>(driver)
				// Wait for the condition
				.withTimeout(timeInSeconds * 10, TimeUnit.SECONDS)
				// which to check for the condition with interval of 5 seconds.
				.pollingEvery(2, TimeUnit.SECONDS)
				// Which will ignore the NoSuchElementException
				.ignoring(NoSuchElementException.class)
				.ignoring(ElementNotVisibleException.class)
				.ignoring(WebDriverException.class)
				.until(new ExpectedCondition<WebElement>() {
					public ExpectedCondition<WebElement> FluentWaitMethodNameObj = ExpectedConditions
							.elementToBeClickable(by);

					public WebElement apply(WebDriver driver) {
						WebElement element = FluentWaitMethodNameObj
								.apply(driver);
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
	 * waitForElementPresent(final By by, int timeOutInSeconds) Method to check
	 * element is visible or not .If not available wait for the amount of the
	 * time given
	 */

	public void waitForElementPresent(final By by, int timeOutInSeconds) {
		WebElement element;
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); // nullify
																			// implicitlyWait()

			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			element = wait.until(ExpectedConditions
					.presenceOfElementLocated(by));

			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS); // reset
																			// implicitlyWait
			// return the element
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
