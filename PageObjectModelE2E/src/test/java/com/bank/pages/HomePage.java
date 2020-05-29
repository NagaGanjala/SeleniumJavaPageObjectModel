/*
 * @author : Naga Ganjala
 */
package com.bank.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bank.base.BaseTest;


public class HomePage extends BaseTest {
	//Page Factory - OR:Object Repository	
	@FindBy(linkText="Log out")
	@CacheLookup
	public WebElement logout;
	
	@FindBy(xpath="//a[contains(text(),'New Customer')]")
	@CacheLookup
	public WebElement newCutomer;
	
	@FindBy(xpath="//a[contains(text(),'Edit Customer')]")
	@CacheLookup
	public WebElement editCustomer;
	
	
	@FindBy(xpath="//ul[@class='menusubnav']//a[contains(text(),'Delete Customer')]")
	@CacheLookup
	public WebElement deleteCustomer;
	
	//Initializing the Page Objects://lazy initializing 
		public HomePage()
		{
			PageFactory.initElements(driver, this);
		}
		
	//Actions:		
	
	public void clickTab(WebElement element)
	{
		try {
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].click()", element);
			Thread.sleep(2000);
		System.out.println("Element tab clicked");
		log.info("Element tab clicked");
		}catch(Exception e) {			
			System.out.println(e.getMessage());
			System.out.println(element.getText()+" tab click action failed");
			log.info(element.getText()+" tab click action failed");
			log.error(e.getMessage());
		}
	}	
}









