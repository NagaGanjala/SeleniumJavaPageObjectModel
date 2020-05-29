package com.bank.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.bank.base.BaseTest;
import com.bank.pages.HomePage;
import com.bank.pages.LoginPage;
import com.bank.pages.NewCustomerPage;
import com.bank.testreport.ExtentTestManager;
import com.bank.utilities.TestUtil;

public class NewCustomerPageTest extends BaseTest{
	LoginPage loginPage;
	NewCustomerPage customerPage;
	HomePage homePage;
	
	@BeforeMethod
	public void setUp() {
		loginPage = new LoginPage();
		customerPage=new NewCustomerPage();
		homePage=new HomePage();
	}
	
	
	@Test
	public void addNewCustomer() throws InterruptedException, IOException
	{
		ExtentTestManager.getTest().assignCategory("FunctionalTest");
		driver.get(config.getProperty("baseURL"));
		loginPage.setUserName(config.getProperty("username"));
		log.info("User name is provided: "+config.getProperty("username"));
		ExtentTestManager.getTest().log(Status.INFO, "Entered username: "+config.getProperty("username"));
		loginPage.setPassword(config.getProperty("password"));
		log.info("Passsword is provided: "+config.getProperty("password"));
		ExtentTestManager.getTest().log(Status.INFO, "Entered password: "+config.getProperty("password"));
		loginPage.clickSubmit();
		Thread.sleep(3000);		
		homePage.clickTab(homePage.newCutomer);		
		log.info("providing customer details....");
		System.out.println("providing customer details....");
		ExtentTestManager.getTest().log(Status.INFO, "providing customer details.... ");
		
		customerPage.setcustomerName("NagaGanjalaaa");
		ExtentTestManager.getTest().log(Status.INFO, "Entered customer name.... ");
		customerPage.setMale();
		ExtentTestManager.getTest().log(Status.INFO, "Selected the Gender.... ");
		customerPage.selectDob("23", "05", "2020");
		ExtentTestManager.getTest().log(Status.INFO, "DOB Entered.... ");
		Thread.sleep(5000);
		customerPage.setAddress("ChandaNagar");
		ExtentTestManager.getTest().log(Status.INFO, "Address Entered.... ");
		customerPage.custcity("HYDERABAD");
		ExtentTestManager.getTest().log(Status.INFO, "City Entered.... ");
		customerPage.custstate("TS");
		ExtentTestManager.getTest().log(Status.INFO, "State Entered.... ");
		customerPage.custpinno("500050");
		ExtentTestManager.getTest().log(Status.INFO, "PINCODE Entered.... ");
		customerPage.custtelephoneno("9966525534");
		ExtentTestManager.getTest().log(Status.INFO, "TelePhone Entered.... ");
		String email=TestUtil.randomestring()+"@gmail.com";
		customerPage.custemailid(email);
		ExtentTestManager.getTest().log(Status.INFO, "Email Entered.... "+email);
		customerPage.setPassWord("XxYyZzAbC");
		ExtentTestManager.getTest().log(Status.INFO, "Password Entered.... ");
		customerPage.custsubmit();
		ExtentTestManager.getTest().log(Status.INFO, "Submitted Request.... ");
		Thread.sleep(3000);
		log.info("validation started....");
		ExtentTestManager.getTest().log(Status.INFO, "Validation Started.... ");
		if(TestUtil.isAlertPresent()==true)
		{
			driver.switchTo().alert().accept();
		}
		
		boolean res=driver.getPageSource().contains("Customer Registered Successfully!!!");
		
		if(res==true)
		{
			Assert.assertTrue(true);
			log.info("test case passed....");
			ExtentTestManager.getTest().log(Status.PASS, "Test Case has Passed.... ");
			
		}
		else
		{
			log.info("test case failed....");
			ExtentTestManager.getTest().log(Status.FAIL, "Test Case has Failed.... ");
			Assert.assertTrue(false);
		}
			
	}
}
