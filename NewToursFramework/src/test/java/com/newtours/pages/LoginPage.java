package com.newtours.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver ldriver) {
		this.driver= ldriver;
	}
	
	//Best practice for locate page elements for Page object model
	@FindBy(name="userName") WebElement uname;
	
	@FindBy(name="password") WebElement pword;
	
	@FindBy(xpath="//input[@value='Submit']") WebElement submitButton;
	
	public void loginToNewTours(String loginUserName, String loginPassword) {
	
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			
		}
		
		uname.sendKeys(loginUserName);
		pword.sendKeys(loginPassword);
		submitButton.click();
	}

}
