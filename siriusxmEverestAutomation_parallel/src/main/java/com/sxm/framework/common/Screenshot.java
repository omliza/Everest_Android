package com.sxm.framework.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import org.testng.annotations.Test;

import com.perfecto.reportium.client.ReportiumClient;
import com.selenium.mobile.base.RefactorMobileTestCase;
import com.sxm.framework.dto.PerfectoCapability;
import com.sxm.framework.dto.handler.EnvirnomentHandler;
import com.sxm.framework.dto.handler.PerfectoCapabilityHandler;
import com.sxm.mobile.pages.Common;

import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class Screenshot extends TestListenerAdapter {
    private final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";

    private volatile static WriteExcel we;

    private volatile static int i = 1;
    private volatile static WritableSheet sheet;
    
    private volatile static WritableSheet summarySheet;
    private volatile static List<WritableSheet> sheetList;

    private String testName = null;
    private String group;
    
    private static PerfectoCapability perfectocapability = PerfectoCapabilityHandler.getInstance()
            .getPerfectoCapability();
    

    public Screenshot() {

        we = WriteExcel.getInstance();

        /*
         * String channel =
         * EnvirnomentHandler.getInstance().getEnvirnoment().getChannel();
         * if("android".equalsIgnoreCase(channel)){ driver =
         * appium.getAndroidDriver(); } else
         * if("IOS".equalsIgnoreCase(channel)){ driver = appium.getIOSDriver();
         * } else{ driver = selenium.getDriver(); }
         */

        //add time stamp to test report
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy_HHmmss"); 
        String reportName = "Report " + formatter.format(new Date()) +".xls";
        
        String directoryName = "src/test/resources/testreports/";
        File directoryPath = new File(directoryName);
        if (! directoryPath.exists()){
        	directoryPath.mkdir();
        }
        String filepath = directoryName +reportName;
        
       // String filepath = "src/test/resources/testreports/"+reportName;
        filepath = System.getProperty("user.dir") + "/" + filepath;
        System.out.println("filepath = "+filepath);
        we.setOutputFile(filepath);
        try {
        	we.creatWorkbook();
        } catch (WriteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.testng.TestListenerAdapter#onTestFailure(org.testng.ITestResult)
     */
    @Override
    public void onTestFailure(ITestResult tr) {

        synchronized (this) {

            // WebDriver driver = (WebDriver) tr.getAttribute("driver");
        	try {
        		sheetList = we.getSheet1();
        		sheet = sheetList.get(0);
        		summarySheet = sheetList.get(1);
			} catch (WriteException e1) {
				e1.printStackTrace();
			} catch (BiffException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

            System.out.println("value of testname is   " + testName);
            if (testName != tr.getMethod().getMethodName()) {
                Common.log("-Failed-", true);
                Common.log("Test method name is:- "
                        + tr.getMethod().getMethodName(), true);
                Common.log("Test name is:- "
                        + tr.getMethod().getConstructorOrMethod().getMethod()
                                .getAnnotation(Test.class).testName(), true);
                try {

                    group = Arrays.toString(
                            tr.getMethod().getConstructorOrMethod().getMethod()
                                    .getAnnotation(Test.class).groups())
                            .replace("[", "");
                    group = group.replace("]", "");
                    testName = tr.getMethod().getMethodName();

                    we.createContent(sheet, 0, i, group);
                    
                    we.createContent(sheet, 1, i,
                            tr.getMethod().getConstructorOrMethod().getMethod()
                                    .getAnnotation(Test.class).suiteName());
                    
                    we.createContent(sheet, 2, i, tr.getMethod()
                            .getConstructorOrMethod().getMethod()
                            .getAnnotation(Test.class).testName());

                    we.createContent(sheet, 3, i, tr.getMethod()
                            .getConstructorOrMethod().getMethod()
                            .getAnnotation(Test.class).description());

                    we.createContent(sheet, 4, i, "Fail");
                    
                    we.createContent(sheet, 5, i, getDate());
                    
                  //print exception
                    Throwable exception = tr.getThrowable();
                    we.createContent(sheet, 6, i, exception.getMessage());
                    
                    we.createContent(sheet, 8, i, tr.getMethod().getMethodName());
                    
                    if(null != tr.getTestContext().getAttribute("DeviceModel")
    						&& null != tr.getTestContext().getAttribute("DeviceID"))
    				{
    					we.createContent(sheet, 9, i, tr.getTestContext().getAttribute("DeviceModel").toString());
    					we.createContent(sheet, 10, i, tr.getTestContext().getAttribute("DeviceID").toString());
    				}else{
    					we.createContent(sheet, 9, i,  perfectocapability.getModel());
    					we.createContent(sheet, 10, i, perfectocapability.getDeviceName());
    				}
                    Common.log("In Excel on Test Failure: " + tr.getAttribute("perfectoReportLink").toString());
					
					if(!tr.getAttribute("perfectoReportLink").toString().isEmpty())
						we.createContent(sheet, 11, i, tr.getAttribute("perfectoReportLink").toString());
					else
						we.createContent(sheet, 11, i, "perfectoReportLink");
                    
                    i++;
                    
                    //write summary excel
                    populateSummaryReportForFail(tr, summarySheet, "Fail");
                } catch (Exception e) {

                    e.printStackTrace();
                } finally{
                	we.closeFile();
                }

                System.out.println("value of testname is in if condition   "
                        + testName);
            }

            System.setProperty(ESCAPE_PROPERTY, "false");
            boolean islocal = EnvirnomentHandler.getInstance().getEnvirnoment()
                    .isLocal();

            if (islocal) {

                WebDriver driver = (WebDriver) tr.getTestContext()
                        .getAttribute("driver");
                /* WebDriver augmentedDriver = new Augmenter().augment(driver); */

                File file = new File("");
                Calendar lCDateTime = Calendar.getInstance();
                String a = lCDateTime.getTimeInMillis() + ".png";
                Reporter.setCurrentTestResult(tr);
                try {
                    System.out.println(file.getCanonicalPath());

                    if (driver != null) {
                        File scrFile = ((TakesScreenshot) driver)
                                .getScreenshotAs(OutputType.FILE);
                        String f = file.getCanonicalPath() + File.separator
                                + "target" + File.separator
                                + "surefire-reports" + File.separator
                                + lCDateTime.getTimeInMillis() + ".png";
                        File dest = new File(f);

                        FileUtils.copyFile(scrFile, dest);
                        // FileUtils.copyFile(scrFile, new File(f));
                        StringBuilder href = new StringBuilder();
                        href.append("<a href=");
                        href.append("'.." + File.separator + "surefire-reports");
                        href.append(File.separator + a
                                + "' target=\"_blank\">ScreenShot_");
                        href.append(tr.getName() + "</a>");
                        System.out
                                .println("-------------------------------------------");

                        Common.log(href.toString());

                        Reporter.setCurrentTestResult(null);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.testng.TestListenerAdapter#onTestSkipped(org.testng.ITestResult)
     */
    @Override
    public void onTestSkipped(ITestResult tr) {

        synchronized (this) {
        	try {
        		sheetList = we.getSheet1();
        		sheet = sheetList.get(0);
        		summarySheet = sheetList.get(1);
			} catch (WriteException e2) {
				e2.printStackTrace();
			} catch (BiffException e2) {
				e2.printStackTrace();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
            Common.log("-Skipped-", true);
            Common.log("Test Method name is:- "
                    + tr.getMethod().getMethodName(), true);
            Common.log("Test name is:- "
                    + tr.getMethod().getConstructorOrMethod().getMethod()
                            .getAnnotation(Test.class).testName(), true);
            try {
                String group = Arrays.toString(
                        tr.getMethod().getConstructorOrMethod().getMethod()
                                .getAnnotation(Test.class).groups()).replace(
                        "[", "");
                group = group.replace("]", "");

                we.createContent(sheet, 0, i, group);
                
                we.createContent(sheet, 1, i,
                        tr.getMethod().getConstructorOrMethod().getMethod()
                                .getAnnotation(Test.class).suiteName());

                we.createContent(sheet, 2, i,
                        tr.getMethod().getConstructorOrMethod().getMethod()
                                .getAnnotation(Test.class).testName());

                we.createContent(sheet, 3, i,
                        tr.getMethod().getConstructorOrMethod().getMethod()
                                .getAnnotation(Test.class).description());

                we.createContent(sheet, 4, i, "Skip");
                
                we.createContent(sheet, 5, i, getDate());
                
                //print exception
                Throwable exception = tr.getThrowable();
                we.createContent(sheet, 6, i, exception.getMessage());
                
                we.createContent(sheet, 8, i, tr.getMethod().getMethodName());

                if(null != tr.getTestContext().getAttribute("DeviceModel")
						&& null != tr.getTestContext().getAttribute("DeviceID"))
				{
					we.createContent(sheet, 9, i, tr.getTestContext().getAttribute("DeviceModel").toString());
					we.createContent(sheet, 10, i, tr.getTestContext().getAttribute("DeviceID").toString());
				}else{
					we.createContent(sheet, 9, i,  perfectocapability.getModel());
					we.createContent(sheet, 10, i, perfectocapability.getDeviceName());
				}
                if(!tr.getAttribute("perfectoReportLink").toString().isEmpty())
					we.createContent(sheet, 11, i, tr.getAttribute("perfectoReportLink").toString());
				else
					we.createContent(sheet, 11, i, "perfectoReportLink");
                i++;
                
                //write summary excel
                populateSummaryReportForSkip(tr, summarySheet, "Skip");
                
            } catch (WriteException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } finally{
            	we.closeFile();
            }
            boolean islocal = EnvirnomentHandler.getInstance().getEnvirnoment()
                    .isLocal();

            if (islocal) {
                WebDriver driver = (WebDriver) tr.getTestContext()
                        .getAttribute("driver");

                System.setProperty(ESCAPE_PROPERTY, "false");

                File file = new File("");
                Calendar lCDateTime = Calendar.getInstance();
                String a = lCDateTime.getTimeInMillis() + ".png";
                Reporter.setCurrentTestResult(tr);
                try {
                    System.out.println(file.getCanonicalPath());
                    File scrFile = ((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.FILE);
                    String f = file.getCanonicalPath() + File.separator
                            + "target" + File.separator + "surefire-reports"
                            + File.separator + lCDateTime.getTimeInMillis()
                            + ".png";
                    File dest = new File(f);

                    Files.copy(scrFile.toPath(), dest.toPath());
                    // FileUtils.copyFile(scrFile, new File(f));
                    StringBuilder href = new StringBuilder();
                    href.append("<a href=");
                    href.append("'.." + File.separator + "surefire-reports");
                    href.append(File.separator + a
                            + "' target=\"_blank\">ScreenShot_");
                    href.append(tr.getName() + "</a>");
                    System.out
                            .println("-------------------------------------------");

                    Common.log(href.toString());

                    Reporter.setCurrentTestResult(null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /** write summary report excel for skipped test cases
     * @param tr
     * @param summarySheet 
     * @param skipString
     */
    private void populateSummaryReportForSkip(ITestResult tr, WritableSheet summarySheet, String skipString) {
    	System.out.println("In populateSummaryReportForSkip");
    	Common.log("Inside populateSummaryReportForSkip");
    	 synchronized (this) {
    		
    	try {
    		 String moduleName = tr.getMethod().getConstructorOrMethod().getMethod()
    				 .getAnnotation(Test.class).suiteName();
    		 
    		if(null != summarySheet)
    		{
    			//getStatusCount(moduleName, summarySheet, skipString);
    			//log in 
    			if(moduleName.equalsIgnoreCase(summarySheet.getCell(0,1).getContents()))
    			{
    				populateSkipCount(summarySheet, skipString, 1);
    			}
    			//home
    			else if(moduleName.equalsIgnoreCase(summarySheet.getCell(0,2).getContents()))
    			{
    				populateSkipCount(summarySheet, skipString, 2);
    			}
    			//Catagories
    			else if(moduleName.equalsIgnoreCase(summarySheet.getCell(0,3).getContents()))
    			{
    				populateSkipCount(summarySheet, skipString, 3);
    			}
    			//Favorites
    			else if(moduleName.equalsIgnoreCase(summarySheet.getCell(0,4).getContents()))
    			{
    				populateSkipCount(summarySheet, skipString, 4);
    			}
    			//NowPlaying
     	        else if(moduleName.equalsIgnoreCase(summarySheet.getCell(0,5).getContents()))
     			{
     	        	populateSkipCount(summarySheet, skipString, 5);
     			}
     			//Howard
     	       else if(moduleName.equalsIgnoreCase(summarySheet.getCell(0,6).getContents()))
    			{
     	    	   populateSkipCount(summarySheet, skipString, 6);
    			}
     			//profile
     	       else if(moduleName.equalsIgnoreCase(summarySheet.getCell(0,7).getContents()))
   				{
     	    	   populateSkipCount(summarySheet, skipString, 7);
   				}
     			//search
     	       else if(moduleName.equalsIgnoreCase(summarySheet.getCell(0,8).getContents()))
     	       {
     	    	  populateSkipCount(summarySheet, skipString, 8);
     	       }
    			
    			//minibar
     	      else if(moduleName.equalsIgnoreCase(summarySheet.getCell(0,9).getContents()))
    	       {
    	    	  populateSkipCount(summarySheet, skipString, 9);
    	       }
    		}
 		} catch (WriteException e) {
 				e.printStackTrace();
 		} catch (Exception e) {
 				e.printStackTrace();
 		}
    		 
    	
    }
	}
    
    public void populateSkipCount(WritableSheet summarySheet, String skipString, int rowNoOfModule) throws RowsExceededException, NumberFormatException, WriteException
    {
    	String statusCountStr ="";
    	if("Skip".equalsIgnoreCase(skipString)){
			statusCountStr = summarySheet.getCell(3, rowNoOfModule).getContents();
			if(null != statusCountStr && !statusCountStr.equals("")){
				we.addNumber(summarySheet, 3, rowNoOfModule, Integer.parseInt(statusCountStr)+1);
			}else
			{
				we.addNumber(summarySheet, 3, rowNoOfModule, 1);
			}			
		}
    }
    
    /** write summary report excel for skipped test cases
     * @param tr
     * @param summarySheet 
     * @param skipString
     */
    private void populateSummaryReportForFail(ITestResult tr, WritableSheet summarySheet, String failString) {
    	System.out.println("In populateSummaryReportForFail");
    	Common.log("Inside populateSummaryReportForFail");
    	 synchronized (this) {
    		
    	try {
    		 String moduleName = tr.getMethod().getConstructorOrMethod().getMethod()
    				 .getAnnotation(Test.class).suiteName();
    		 
    		 if(null != summarySheet)
     		{
     			//getStatusCount(moduleName, summarySheet, skipString);
     			//log in 
     			if(moduleName.equalsIgnoreCase(summarySheet.getCell(0,1).getContents()))
     			{
     				populateFailCount(summarySheet, failString, 1);
     			}
     			//home
     			else if(moduleName.equalsIgnoreCase(summarySheet.getCell(0,2).getContents()))
     			{
     				populateFailCount(summarySheet, failString, 2);
     			}
     			//Catagories
     			else if(moduleName.equalsIgnoreCase(summarySheet.getCell(0,3).getContents()))
     			{
     				populateFailCount(summarySheet, failString, 3);
     			}
     			//Favorites
     			else if(moduleName.equalsIgnoreCase(summarySheet.getCell(0,4).getContents()))
     			{
     				populateFailCount(summarySheet, failString, 4);
     			}
     	         //NowPlaying
     	        else if(moduleName.equalsIgnoreCase(summarySheet.getCell(0,5).getContents()))
     			{
     				populateFailCount(summarySheet, failString, 5);
     			}
     			//Howard
     	       else if(moduleName.equalsIgnoreCase(summarySheet.getCell(0,6).getContents()))
    			{
    				populateFailCount(summarySheet, failString, 6);
    			}
     			//profile
     	       else if(moduleName.equalsIgnoreCase(summarySheet.getCell(0,7).getContents()))
   				{
     	    	   populateFailCount(summarySheet, failString, 7);
   				}
     			//search
     	       else if(moduleName.equalsIgnoreCase(summarySheet.getCell(0,8).getContents()))
     	       {
     	    	   populateFailCount(summarySheet, failString, 8);
     	       }
     			//minibar
	     	   else if(moduleName.equalsIgnoreCase(summarySheet.getCell(0,9).getContents()))
	   	       {
	     		  populateFailCount(summarySheet, failString, 9);
	   	       }
     	         
     		}
 		} catch (WriteException e) {
 				e.printStackTrace();
 		} catch (Exception e) {
 				e.printStackTrace();
 		}
    		 
    	
    }
	}
    
    /**
     * @param summarySheet
     * @param skipString
     * @param rowNoOfModule
     * @throws RowsExceededException
     * @throws NumberFormatException
     * @throws WriteException
     */
    public void populateFailCount(WritableSheet summarySheet, String failString, int rowNoOfModule) throws RowsExceededException, NumberFormatException, WriteException
    {
    	String statusCountStr ="";
    	if("Fail".equalsIgnoreCase(failString)){
			statusCountStr = summarySheet.getCell(2, rowNoOfModule).getContents();
			if(null != statusCountStr && !statusCountStr.equals("")){
				we.addNumber(summarySheet, 2, rowNoOfModule, Integer.parseInt(statusCountStr)+1);
			}else
			{
				we.addNumber(summarySheet, 2, rowNoOfModule, 1);
			}
			
		}
    }
    
    /** write summary report excel for skipped test cases
     * @param tr
     * @param summarySheet 
     * @param skipString
     */
    private void populateSummaryReportForPass(ITestResult tr, WritableSheet summarySheet, String passString) {
    	System.out.println("In populateSummaryReportForPass");
    	Common.log("Inside populateSummaryReportForPass");
    	 synchronized (this) {
    		
    	try {
    		 String moduleName = tr.getMethod().getConstructorOrMethod().getMethod()
    				 .getAnnotation(Test.class).suiteName();
    		 
    		 
    		 if(null != summarySheet)
     		{
     			//getStatusCount(moduleName, summarySheet, skipString);
     			//log in 
     			if(moduleName.equalsIgnoreCase(summarySheet.getCell(0,1).getContents()))
     			{
     				populatePassCount(summarySheet, passString, 1, tr);
     			}
     			//home
     			else if(moduleName.equalsIgnoreCase(summarySheet.getCell(0,2).getContents()))
     			{
     				populatePassCount(summarySheet, passString, 2, tr);
     			}
     			//Catagories
     			else if(moduleName.equalsIgnoreCase(summarySheet.getCell(0,3).getContents()))
     			{
     				populatePassCount(summarySheet, passString, 3, tr);
     			}
     			//Favorites
     			else if(moduleName.equalsIgnoreCase(summarySheet.getCell(0,4).getContents()))
     			{
     				populatePassCount(summarySheet, passString, 4, tr);
     			}
     			//NowPlaying
     	        else if(moduleName.equalsIgnoreCase(summarySheet.getCell(0,5).getContents()))
     			{
     	        	populatePassCount(summarySheet, passString, 5, tr);
     			}
     			//Howard
     	       else if(moduleName.equalsIgnoreCase(summarySheet.getCell(0,6).getContents()))
    			{
     	    	  populatePassCount(summarySheet, passString, 6, tr);
    			}
     			//profile
     	       else if(moduleName.equalsIgnoreCase(summarySheet.getCell(0,7).getContents()))
   				{
     	    	  populatePassCount(summarySheet, passString, 7, tr);
   				}
     			//search
     	       else if(moduleName.equalsIgnoreCase(summarySheet.getCell(0,8).getContents()))
     	       {
     	    	  populatePassCount(summarySheet, passString, 8, tr);
     	       }
     	      else if(moduleName.equalsIgnoreCase(summarySheet.getCell(0,9).getContents()))
    	       {
    	    	  populatePassCount(summarySheet, passString, 9, tr);
    	       }
     		}
 		} catch (WriteException e) {
 				e.printStackTrace();
 		} catch (Exception e) {
 				e.printStackTrace();
 		}
    		 
    	
    }
	}
    
    /**
     * @param summarySheet
     * @param skipString
     * @param rowNoOfModule
     * @throws RowsExceededException
     * @throws NumberFormatException
     * @throws WriteException
     */
    public void populatePassCount(WritableSheet summarySheet, String passString, int rowNoOfModule, ITestResult tr) throws RowsExceededException, NumberFormatException, WriteException
    {
    	String statusCountStr ="";
    	
    	if("Pass".equalsIgnoreCase(passString)){
			statusCountStr = summarySheet.getCell(1,rowNoOfModule).getContents();
			if(null != statusCountStr && !statusCountStr.equals("")){
				we.addNumber(summarySheet, 1, rowNoOfModule, Integer.parseInt(statusCountStr)+1);
			}else
			{
				we.addNumber(summarySheet, 1, rowNoOfModule, 1);
			}
			we.addLabel(summarySheet, 5, rowNoOfModule, tr.getAttribute("perfectoReportLink").toString());
		}
    }


	/*
     * (non-Javadoc)
     * 
     * @see org.testng.TestListenerAdapter#onTestSuccess(org.testng.ITestResult)
     */
    @Override
    public void onTestSuccess(ITestResult tr) {
        synchronized (this) {

        	System.out.println("In onTestSuccess");
        	try {
        		sheetList = we.getSheet1();
        		sheet = sheetList.get(0);
        		summarySheet = sheetList.get(1);
			} catch (WriteException e) {
				e.printStackTrace();
			} catch (BiffException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
            if (testName == tr.getMethod().getMethodName()) {
                i--;
            }
            Common.log("-Passed-", true);
            Common.log("Test Method name is:- "
                    + tr.getMethod().getMethodName(), true);
            Common.log("Test name is:- "
                    + tr.getMethod().getConstructorOrMethod().getMethod()
                            .getAnnotation(Test.class).testName(), true);
            Common.log("Test desc is:- "
                    + tr.getMethod().getConstructorOrMethod().getMethod()
                            .getAnnotation(Test.class).description(), true);
            try {
                String group = Arrays.toString(
                        tr.getMethod().getConstructorOrMethod().getMethod()
                                .getAnnotation(Test.class).groups()).replace(
                        "[", "");
                group = group.replace("]", "");
                we.createContent(sheet, 0, i, group);
                
                we.createContent(sheet, 1, i,
                        tr.getMethod().getConstructorOrMethod().getMethod()
                                .getAnnotation(Test.class).suiteName());

                we.createContent(sheet, 2, i,
                        tr.getMethod().getConstructorOrMethod().getMethod()
                                .getAnnotation(Test.class).testName());

                we.createContent(sheet, 3, i,
                        tr.getMethod().getConstructorOrMethod().getMethod()
                                .getAnnotation(Test.class).description());

                we.createContent(sheet, 4, i, "Pass");
             
                we.createContent(sheet, 5, i, getDate());
                
                we.createContent(sheet, 6, i, "Passed: No Exception");
                
                we.createContent(sheet, 8, i, tr.getMethod().getMethodName());
                
                if(null != tr.getTestContext().getAttribute("DeviceModel")
						&& null != tr.getTestContext().getAttribute("DeviceID"))
				{
					we.createContent(sheet, 9, i, tr.getTestContext().getAttribute("DeviceModel").toString());
					we.createContent(sheet, 10, i, tr.getTestContext().getAttribute("DeviceID").toString());
				}else{
					we.createContent(sheet, 9, i,  perfectocapability.getModel());
					we.createContent(sheet, 10, i, perfectocapability.getDeviceName());
				}
                Common.log("On Test Success1:In Excel: " + tr.getAttribute("perfectoReportLink"));
                if(!tr.getAttribute("perfectoReportLink").toString().isEmpty())
					we.createContent(sheet, 11, i, tr.getAttribute("perfectoReportLink").toString());
				else
					we.createContent(sheet, 11, i, "perfectoReportLink");
                i++;
                
              //write summary excel
                populateSummaryReportForPass(tr, summarySheet, "Pass");
            } catch (WriteException e1) {
                e1.printStackTrace();
            } finally{
            	we.closeFile();
            }

        }

    }

    @Override
    public void onStart(ITestContext context) {
    	System.out.println("onStart");
        /*
         * System.out.println("----------------------"+context.getSuite().
         * getParameter("platform"));
         * 
         * if("android".equalsIgnoreCase(context.getSuite().getParameter("platform"
         * ))){ driver = appium.getAndroidDriver();
         * 
         * } else
         * if("IOS".equalsIgnoreCase(context.getSuite().getParameter("platform"
         * ))){ driver = appium.getIOSDriver(); } else{ driver =
         * selenium.getDriver(); }
         */
    }
    
    @Override
    public void onFinish(ITestContext testContext) {
		System.out.println("On Finish Started....");
		try {
			we.createLatestReport();
		} catch (BiffException | WriteException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
	private String getDate() {
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("MM.dd.yyyy hh:mm:ss a zzz");
		return ft.format(dNow);
	}

}