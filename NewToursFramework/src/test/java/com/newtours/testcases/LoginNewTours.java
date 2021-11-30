package com.newtours.testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.newtours.pages.BaseClass;
import com.newtours.pages.LoginPage;
import com.newtours.utility.Helper;

public class LoginNewTours extends BaseClass {
	
	
	@Test(priority=1)
	public void loginApplication() {
		
		logger=report.createTest("Login to NewTours");
	
		// Initialize the Login page and return the object of Login class
		LoginPage loginPage=PageFactory.initElements(driver, LoginPage.class);
		
		logger.info("Starting Application");
		
		// Example of Abstraction
		loginPage.loginToNewTours(excel.getStringData("Login", 0, 0), excel.getStringData("Login", 0, 1));
		
		logger.pass("Login Success");
		
		//Capture Screenshot
		//Helper.captureScreenshot(driver);
		
	}
	
}
