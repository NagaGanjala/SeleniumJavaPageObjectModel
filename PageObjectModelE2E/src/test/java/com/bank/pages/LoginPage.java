/*
 * @author : Naga Ganjala
 */
package com.bank.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bank.base.BaseTest;


public class LoginPage extends BaseTest {

	//Page Factory - OR:Object Repository	
	@FindBy(name="uid")
	@CacheLookup
	WebElement txtUserName;
	
	@FindBy(name="password")
	@CacheLookup
	WebElement txtPassword;
	
	@FindBy(name="btnLogin")
	@CacheLookup
	WebElement btnLogin;
	
	
	@FindBy(xpath="//input[@name='btnReset']")
	@CacheLookup
	WebElement btnReset;
	
	//Initializing the Page Objects://lazy initializing 
		public LoginPage()
		{
			PageFactory.initElements(driver, this);
		}
		
	//Actions:	
	public void setUserName(String uname)
	{
		txtUserName.sendKeys(uname);
	}
	
	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	
	
	public void clickSubmit()
	{
		btnLogin.click();
	}	
	
	public void clickLogout()
	{
		btnReset.click();
	}
	
	
	
}









