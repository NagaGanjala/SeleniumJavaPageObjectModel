package com.bank.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bank.base.BaseTest;

public class NewCustomerPage extends BaseTest {
	@FindBy(xpath="//input[@name='name']")
	WebElement customerName;
	
	@FindBy(xpath="//tr[5]//td[2]//input[1]")
	WebElement genderMale;
	
	@FindBy(xpath="//tr[5]//td[2]//input[2]")
	WebElement genderFemale;
	
	@FindBy(xpath="//input[@id='dob']")
	WebElement dob;
	
	@FindBy(xpath="//textarea[@name='addr']")
	WebElement address;
	
	@FindBy(xpath="//input[@name='city']")
	WebElement city;
	
	@FindBy(xpath="//input[@name='state']")
	WebElement state;
	
	@FindBy(name="pinno")
	WebElement pinno;
	
	@FindBy(name="telephoneno")
	WebElement telePhoneNumber;
	
	@FindBy(xpath="//input[@name='emailid']")
	WebElement Email;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement pwd;
	
	@FindBy(xpath="//input[@name='sub']")
	WebElement submitButton;
	
	//load the objects into PageFactory
	public NewCustomerPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Actions OR Methods which we can perform on New Customer Page
	
	public void setcustomerName(String name) {
		customerName.sendKeys(name);
	}
	
	public void setMale() {
		genderMale.click();
	}
	
	
	public void setFemale() {
		genderFemale.click();
	}
	
	public void selectDob(String dd,String mm,String yy) {
		dob.sendKeys(dd);
		dob.sendKeys(mm);		
		dob.sendKeys(yy);
		
	}
	public void setAddress(String adr) {
		address.sendKeys(adr);
	}	

	public void custcity(String ccity) {
		city.sendKeys(ccity);
	}

	public void custstate(String sstate) {
		state.sendKeys(sstate);
	}

	public void custpinno(String cpinno) {
		pinno.sendKeys(cpinno);
	}

	public void custtelephoneno(String ctelephoneno) {
	  telePhoneNumber.sendKeys(ctelephoneno);
	}

	public void custemailid(String cemailid) {
		Email.sendKeys(cemailid);
	}
	
	public void setPassWord(String pass) {
		pwd.sendKeys(pass);;
}

		public void custsubmit() {
			submitButton.click();
	}
	
	
	
}
