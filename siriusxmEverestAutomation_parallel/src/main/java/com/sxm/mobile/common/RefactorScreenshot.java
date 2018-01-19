package com.sxm.mobile.common;

import java.io.File;
import java.io.IOException;
//import java.nio.file.Files;
import java.util.Arrays;
import java.util.Calendar;

import jxl.write.WritableSheet;
import jxl.write.WriteException;

import com.sxm.framework.dto.handler.*;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import org.testng.annotations.Test;

public class RefactorScreenshot extends TestListenerAdapter {
    private final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";
    private  WebDriver driver;
    public RefactorScreenshot() {
        
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.testng.TestListenerAdapter#onTestFailure(org.testng.ITestResult)
     */
    @Override
    public void onTestFailure(ITestResult tr) {


        WebDriver driver = (WebDriver) tr.getTestContext()
                .getAttribute("driver");
        System.out.println("onTestFailure"+driver);
        screenShots(driver);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.testng.TestListenerAdapter#onTestSkipped(org.testng.ITestResult)
     */
    @Override
    public void onTestSkipped(ITestResult tr) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.testng.TestListenerAdapter#onTestSuccess(org.testng.ITestResult)
     */
    @Override
    public void onTestSuccess(ITestResult tr) {
        
        WebDriver driver = (WebDriver) tr.getTestContext()
                .getAttribute("driver");
        
        System.out.println("onTestSuccess"+driver);
        screenShots(driver);
    }

    public void screenShots(WebDriver driver) {
        System.setProperty(ESCAPE_PROPERTY, "false");

        File file = new File("");
        Calendar lCDateTime = Calendar.getInstance();
        String a = lCDateTime.getTimeInMillis() + ".png";
        try {
            System.out.println(file.getCanonicalPath());
            File scrFile = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);
            String f = file.getCanonicalPath() + File.separator + "target"
                    + File.separator + "surefire-reports" + File.separator
                    + lCDateTime.getTimeInMillis() + ".png";
            File dest = new File(f);
            FileUtils.copyFile(scrFile, dest);
            StringBuilder href = new StringBuilder();
            href.append("<a href=");
            href.append("'.." + File.separator + "surefire-reports");
            href.append(File.separator + a + "' target=\"_blank\">ScreenShot_");

            Reporter.log(href.toString() + "</a>");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}