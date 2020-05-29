/*
 * @author : Naga Ganjala
 */
package com.bank.testlisteners;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.bank.base.BaseTest;
import com.bank.testreport.ExtentManager;
import com.bank.testreport.ExtentTestManager;
import com.bank.utilities.TestUtil;

public class TestListener extends BaseTest implements ITestListener {
	String filePath = "TestReport\\Screenshots\\";
	public void onStart(ITestContext context) {
		log.info("*** Test Suite " + context.getName() + " started ***");
	}

	public void onFinish(ITestContext context) {
		log.info(("*** Test Suite " + context.getName() + " ending ***"));
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
	}

	public void onTestStart(ITestResult result) {
		log.info("==============================================");
		log.info(result.getName() + " Test Case Started.....");
		log.info(("*** Running test method " + result.getMethod().getMethodName() + "..."));
		ExtentTestManager.startTest(result.getMethod().getMethodName());
		ExtentTestManager.getTest().log(Status.INFO, "Test Case Started");
	}

	public void onTestSuccess(ITestResult result) {
		log.info(result.getName() + " Test Case Passed");
		// This will print the class name in which the method is present
		// log.info("Class Name: "+result.getTestClass());
		// This will print the priority of the method.
		// If the priority is not defined it will print the default priority as
		// 'o'
		// log.info("Priority of this method is " + result.getMethod().getPriority());
		log.info("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
		ExtentTestManager.getTest().log(Status.PASS, "Test Case passed");
	}
//	public void onTestFailure(ITestResult result) {
//		log.info(result.getName()+" Test Case Failed");
//		log.error("*** Test execution " + result.getMethod().getMethodName() + " failed...");
//		//adding error message to the Extent report
//		ExtentTestManager.getTest().fail(result.getThrowable());
//		ExtentTestManager.getTest().log(Status.FAIL, "Test Case Failed");
//	}
	// Replace this if you want to capture the screen shot

	public void onTestFailure(ITestResult result) {
    	log.info("***** Error "+result.getName()+" Test case has failed *****");
    	log.error("*** Test execution " + result.getMethod().getMethodName() + " failed...");
    	String methodName=result.getName().toString().trim();  
    	ExtentTestManager.getTest().log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test Case has FAILED ", ExtentColor.RED));
        ExtentTestManager.getTest().fail(result.getThrowable());
       
	
   	 try {
   		TestUtil.takeScreenShot(filePath,methodName, driver);
   		String path=System.getProperty("user.dir")+"\\"+TestUtil.screenshotName;
			 ExtentTestManager.getTest().addScreenCaptureFromPath(path.replaceAll("/", "\\"));
			 ExtentTestManager.getTest().log(Status.FAIL,"<img src=\""+path.replaceAll("/", "\\")+"\"width=\"50\" height=\"50\""+">");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
	public void onTestSkipped(ITestResult result) {
		log.info(result.getName()+" Test Case Skipped");
		log.info("*** Test " + result.getMethod().getMethodName() + " skipped...");
		ExtentTestManager.getTest().log(Status.SKIP, "Test Case Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		log.info("*** Test failed but within percentage % " + result.getMethod().getMethodName());
	}
}
