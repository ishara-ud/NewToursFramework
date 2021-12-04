package com.newtours.pages;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.newtours.utility.BrowserFactory;
import com.newtours.utility.ConfigDataProvider;
import com.newtours.utility.ExcelDataProvider;
import com.newtours.utility.Helper;

public class BaseClass {
	
	public WebDriver driver;
	public ExcelDataProvider excel;
	public ConfigDataProvider config;
	public ExtentReports report;
	public ExtentTest logger;
	
	
	@BeforeSuite
	public void setupSuite() {
		
		//Reporter.log is displayed in TestNG report and execution log, but not in Extent Reports
		Reporter.log("Setting up reports and Test is getting ready!",true);
		
		excel= new ExcelDataProvider();
		config= new ConfigDataProvider();
		
		ExtentHtmlReporter extent=new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"//Reports//NewTours_Report_"+Helper.getCurrentDateTime()+".html"));
		report=new ExtentReports();
		report.attachReporter(extent);
		
		Reporter.log("Setting Done- Test can be started!", true);
	}
	
	// "browser" parameter must match with the parameter in pom.xml{browser}
	@Parameters({"browser","urlToBeTested"})
	@BeforeClass
	public void setup(String browser, String url ) {
		Reporter.log("Trying to start Browser and Getting application ready!", true);
		//Pass Browser and URL using configuration file
		//driver=BrowserFactory.startApplication(driver, config.getBrowser(), config.getQAURL());
		driver=BrowserFactory.startApplication(driver,browser, url);
		Reporter.log("Browser and Application is up and running!", true);
		
	}
	
	@AfterClass
	public void tearDown() {
		BrowserFactory.quitBrowser(driver);
	}
	
	//Run After every test method
	@AfterMethod
	public void tearDownMethod(ITestResult result) throws IOException {
		
		Reporter.log("Test is about to end", true);
		
		if(result.getStatus()==ITestResult.FAILURE) {
			Helper.captureScreenshot(driver);
			logger.fail("Test Failed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
			
		}
		else if (result.getStatus()==ITestResult.SUCCESS) {
			logger.pass("Test Passed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		else if (result.getStatus()==ITestResult.SKIP) {
			logger.skip("Test Skipped", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		
		report.flush();
		Reporter.log("Test Completed >>> Reports Generated!", true);
	}


}
