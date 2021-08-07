package com.orange.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.orange.helpers.*;
import com.orange.helpers.BaseLibrary;
import com.orange.helpers.UtilityMethods;
import com.orange.library.HomePageActions;
import com.orange.library.LoginPageActions;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPageTest extends LoginPageActions {
	
	private static final boolean True = false;

	@BeforeSuite
	public void startReportAndDriver() throws Exception {
		startdriver();
	}
	
	@AfterTest
	public void closetest() {
		extent.endTest(logger);
	}

	/*  @DataProvider(name="login")
    public Object[][] userFormData() throws Exception
    {
		ExcelReaders excelReaders = new ExcelReaders();
		String path = System.getProperty("user.dir")+"\\TestData\\TestData.xlsx";
        Object[][] data = excelReaders.testData(path, "LoginPage");
        return data;
    } */
	//@Test(dataProvider = "login",priority = 1, enabled = true)
	//public void orangeEmptyLoginValdation(String username, String password) throws Throwable {
	
	@Test(priority = 1, enabled = false)
	public void orangeEmptyLoginValdation() throws Throwable {

		logger = extent.startTest("Login user validation");
		try {
			
			//validateLoginPage(username,password);
			validateLoginPage();
			Thread.sleep(2000);
			logger.log(LogStatus.PASS,"Login User validated successfully");

		} catch (Exception e) {
			takeScreenShotHalfPage(driver);
			e.printStackTrace();
			logger.log(LogStatus.FAIL, e.getMessage());
		} 

	}
	
	@Test(priority = 2, enabled = false)
	public void FooterVlidation() throws Throwable {

		logger = extent.startTest("Footer Links Validation");
		try {
			
			//validateLoginPage(username,password);
			FooterTabs();
			Thread.sleep(2000);
			logger.log(LogStatus.PASS,"Footer Links Validation successfully");

		} catch (Exception e) {
			takeScreenShotHalfPage(driver);
			e.printStackTrace();
			logger.log(LogStatus.FAIL, e.getMessage());
		} 

	}
	
	@Test(priority = 3, enabled = false)
	public void createAccountValidation() throws Throwable {

		logger = extent.startTest("Create Account Validation");
		try {
			
			//validateLoginPage(username,password);
			CreateAccount();
			Thread.sleep(2000);
			logger.log(LogStatus.PASS,"Create Account Validation successfully");

		} catch (Exception e) {
			takeScreenShotHalfPage(driver);
			e.printStackTrace();
			logger.log(LogStatus.FAIL, e.getMessage());
		} 

	}
	
	@Test(priority = 4, enabled = false)
	public void loginAccountValidation() throws Throwable {

		logger = extent.startTest("Login Validation");
		try {
			
			//validateLoginPage(username,password);
			LoginAccount();
			Thread.sleep(2000);
			logger.log(LogStatus.PASS,"Login Validation successfully");

		} catch (Exception e) {
			takeScreenShotHalfPage(driver);
			e.printStackTrace();
			logger.log(LogStatus.FAIL, e.getMessage());
		} 

	}
	
	@Test(priority = 5, enabled = false)
	public void LoginHEM() throws Throwable {

		logger = extent.startTest("HRM_Login Links Validation");
		try {
			
			//validateLoginPage(username,password);
			HRMLogin();
			Thread.sleep(2000);
			logger.log(LogStatus.PASS,"HRM_Login Validation successfully");

		} catch (Exception e) {
			takeScreenShotHalfPage(driver);
			e.printStackTrace();
			logger.log(LogStatus.FAIL, e.getMessage());
		} 

	}

	//@Test(priority = 6, enabled = true)
	public void LogoutHRM() throws Throwable {

		logger = extent.startTest("HRM_Logout Links Validation");
		try {
			
			//validateLoginPage(username,password);
			HRMlogout();
			Thread.sleep(2000);
			logger.log(LogStatus.PASS,"HRM_Logout Validation successfully");

		} catch (Exception e) {
			takeScreenShotHalfPage(driver);
			e.printStackTrace();
			logger.log(LogStatus.FAIL, e.getMessage());
		} 

	}
	@Test(priority = 7, enabled = true)
	public void webshopDemo() throws Throwable {

		logger = extent.startTest("WebDemo Li");
		try {
			
			//validateLoginPage(username,password);
			WebshopDemo();
			Thread.sleep(2000);
			logger.log(LogStatus.PASS,"WebDemo Registration Validation successfully");

		} catch (Exception e) {
			takeScreenShotHalfPage(driver);
			e.printStackTrace();
			logger.log(LogStatus.FAIL, e.getMessage());
		} 

	}
	
	
}
