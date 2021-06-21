package com.orange.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.orange.helpers.BaseLibrary;

import com.orange.library.HomePageActions;
import com.orange.library.LoginPageActions;
import com.relevantcodes.extentreports.LogStatus;

public class HomePageTest extends HomePageActions {
	
	@AfterTest
	public void closetest() {
		extent.endTest(logger);
	}
	
	@Test(priority = 2, enabled = true)

	public void orangeAddUserValdation() throws Throwable {

		logger = extent.startTest("Add user validation");
		try {
			
			validateAddUser();
			logger.log(LogStatus.PASS,"Add User validated successfully");

		} catch (Exception e) {
			takeScreenShotHalfPage(driver);
			e.printStackTrace();
			logger.log(LogStatus.FAIL, e.getMessage());
		} 

	}
	@Test(priority = 3, enabled = true)

	public void orangeSystemUsersValdation() throws Throwable {

		logger = extent.startTest("System Users validation");
		try {
			validateSystemUsers();
			logger.log(LogStatus.PASS,"LoginPage validated successfully");

		} catch (Exception e) {
			takeScreenShotHalfPage(driver);
			e.printStackTrace();
			logger.log(LogStatus.FAIL, e.getMessage());
		} 

	}
	

}