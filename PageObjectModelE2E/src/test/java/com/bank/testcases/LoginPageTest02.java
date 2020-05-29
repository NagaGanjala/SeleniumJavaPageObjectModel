/*
 * @author : Naga Ganjala
 */
package com.bank.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.bank.base.BaseTest;
import com.bank.pages.HomePage;
import com.bank.pages.LoginPage;
import com.bank.testreport.ExtentTestManager;
import com.bank.utilities.ExcelReader;


public class LoginPageTest02 extends BaseTest {
	LoginPage loginPage;
	HomePage homePage;
	
	@BeforeMethod
	public void setUp(){
		loginPage = new LoginPage();
		homePage=new HomePage();
	}
	
		
	@Test(dataProvider="LoginData")
	public void bankLoginAndLogout(String user,String pwd) throws InterruptedException
	{
		
		try {
			ExtentTestManager.getTest().assignCategory("RegressionTest");
			Thread.sleep(1000);
			driver.navigate().to(config.getProperty("baseURL"));
			loginPage.setUserName(user);
			log.info("Entered username");
			ExtentTestManager.getTest().log(Status.INFO, "Entered username: "+user);
			System.out.println("Entered username:"+user);
			loginPage.setPassword(pwd);
			log.info("Entered password");
			System.out.println("Entered password:"+pwd);
			ExtentTestManager.getTest().log(Status.INFO, "Entered password: "+pwd);
			loginPage.clickSubmit();
			ExtentTestManager.getTest().log(Status.INFO, "Clicked Submit Button");
			Thread.sleep(3000);
		    //Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			if(isAlertPresent()==true)
			{
				System.out.println(driver.switchTo().alert().getText());
			    if(driver.switchTo().alert().getText().contains("User or Password is not valid")) {
			    	//close alert
			    	driver.switchTo().alert().accept();
			    	Assert.assertEquals(false, true,"Invalid User Name or Password Entered");
			    }
				
			}
			System.out.println("Title of the page is:"+driver.getTitle());
			log.info("Title of the page is:"+driver.getTitle());
			ExtentTestManager.getTest().log(Status.INFO, "Title of the page is:"+driver.getTitle());
			
			Assert.assertEquals(driver.getTitle(), "Guru99 Bank Manager HomePage");
			System.out.println("Login Test Case is Passed");
			log.info("Login test passed");
			ExtentTestManager.getTest().log(Status.INFO, "Login test passed");
			homePage.clickTab(homePage.logout);
			Thread.sleep(3000);
			if(isAlertPresent()==true)
			{
				driver.switchTo().alert().accept();//close alert
				driver.switchTo().defaultContent();
				System.out.println("Title of the page is:"+driver.getTitle());
				ExtentTestManager.getTest().log(Status.INFO, "Title of the page is:"+driver.getTitle());
				log.info("Title of the page is:"+driver.getTitle());
				Assert.assertEquals(driver.getTitle(), "Guru99 Bank Home Page");
				System.out.println("Sucessfylly Logged out");
				log.info("Sucessfylly Logged out");
				ExtentTestManager.getTest().log(Status.INFO, "Sucessfylly Logged out");
			}else {
				System.out.println("Title of the page is:"+driver.getTitle());
				log.info("Title of the page is:"+driver.getTitle());
				ExtentTestManager.getTest().log(Status.INFO, "Title of the page is:"+driver.getTitle());
				Assert.assertEquals(driver.getTitle(), "Guru99 Bank Home Page");
				System.out.println("Sucessfylly Logged out");
				log.info("Sucessfylly Logged out");
				ExtentTestManager.getTest().log(Status.INFO, "Sucessfylly Logged out");
			}
			
		}catch(Exception e) {
			log.error(e);
			ExtentTestManager.getTest().log(Status.ERROR, e.getMessage());
		}
		
	}
	
	
	@DataProvider(name="LoginData")
	String [][] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"/src/test/java/com/bank/testData/LoginData.xlsx";
		
		int rownum=ExcelReader.getRowCount(path, "Sheet1");
		System.out.println("Total no of Rows:"+rownum);
		log.info("Total no of Rows:"+rownum);
		int colcount=ExcelReader.getCellCount(path,"Sheet1",1);
		System.out.println("Total no of Columns:"+colcount);
		log.info("Total no of Columns:"+colcount);
		String logindata[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				logindata[i-1][j]=ExcelReader.getCellData(path,"Sheet1", i,j);//1 0
			}
				
		}
	return logindata;
	}
	
}
