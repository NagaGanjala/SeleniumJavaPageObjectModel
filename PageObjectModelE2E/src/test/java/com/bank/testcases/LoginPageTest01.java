/*
 * @author : Naga Ganjala
 */
package com.bank.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.bank.base.BaseTest;
import com.bank.pages.LoginPage;
import com.bank.testreport.ExtentTestManager;

public class LoginPageTest01 extends BaseTest {
	LoginPage loginPage;
	

	@BeforeMethod
	public void setUp() {
		loginPage = new LoginPage();
			
	}

	@Test
	public void loginTest() throws IOException {
		try {
			ExtentTestManager.getTest().assignCategory("SmokeTest");
			loginPage.setUserName(config.getProperty("username"));
			log.info("Entered username");
			ExtentTestManager.getTest().log(Status.INFO, "Entered username: "+config.getProperty("username"));
			loginPage.setPassword(config.getProperty("password"));
			log.info("Entered password");
			ExtentTestManager.getTest().log(Status.INFO, "Entered password: "+config.getProperty("password"));
			loginPage.clickSubmit();
			ExtentTestManager.getTest().log(Status.INFO, "Clicked on Submit Button");
			System.out.println("Title of the page is:" + driver.getTitle());
			ExtentTestManager.getTest().log(Status.INFO, "Title of the page is:" + driver.getTitle());
			log.info("Title of the page is:" + driver.getTitle());
			if (driver.getTitle().equals("Guru99 Bank Manager HomePage")) {
				Assert.assertTrue(true);
				System.out.println("Login Test Case is Passed");
				log.info("Login Test Case is Passed");
				ExtentTestManager.getTest().log(Status.PASS, "Login Test Case is Passed");
			} else {
				Assert.assertTrue(false);
				System.out.println("Login Test Case is Failed");
				log.info("Login Test Case is Failed");
				ExtentTestManager.getTest().log(Status.FAIL, "Login Test Case is Failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, e);
		}
	}
}
