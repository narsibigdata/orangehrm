package com.orange.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.orange.helpers.BaseLibrary;
import com.orange.helpers.UtilityMethods;
import com.orange.library.HomePageActions;
import com.orange.library.HomePageJobsActions;
import com.relevantcodes.extentreports.LogStatus;

public class HomePageJobsTest extends HomePageJobsActions {
	
	@AfterTest
	public void closetest() {
		extent.endTest(logger);
	}
		
	@Test(priority = 3, enabled = true)

	public void orangeAddJobsValdation() throws Throwable {

		logger = extent.startTest("Add user validation");
		try {
			
			validateAddJobs();
			logger.log(LogStatus.PASS,"Add Jobs validated successfully");

		} catch (Exception e) {
			takeScreenShotHalfPage(driver);
			e.printStackTrace();
			logger.log(LogStatus.FAIL, e.getMessage());
		} 

	}
	
	

}