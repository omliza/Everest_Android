package com.sxm.framework.common;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Class to Re-execute the failed test cases implements IRetryAnalyzer interface
 * 
 * @author pawankumar_p
 *
 */
public class Retry implements IRetryAnalyzer {
	private int retryCount = 0;
	private int maxRetryCount = 1;

	public boolean retry(ITestResult result) {

		if (retryCount < maxRetryCount) {
			retryCount++;
			return true;
		}
		return false;
	}
}