package com.orange.library;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.orange.helpers.HelperMethods;

import com.orange.helpers.UtilityMethods;
import com.orange.pageobjects.HomepageObjects;
import com.orange.pageobjects.LoginPageObjects;

import com.relevantcodes.extentreports.LogStatus;

public class HomePageActions extends UtilityMethods {
Select select;
public void validateAddUser() throws Throwable {
	
	    HomepageObjects homePageobject = new HomepageObjects(driver,logger);
		click(homePageobject.admin,"Admin");
		enterValue(homePageobject.username, "ZukerMD", "UserName");
		select = new Select(homePageobject.userRole);
		select.selectByIndex(1);
		enterValue(homePageobject.employName, "Fiona Grace", "EmployeeName");
		selectValueFromList(homePageobject.employAutoSuggestName,"Fiona Grace","Employee Name");
		select = new Select(homePageobject.status);
		select.selectByVisibleText("Enabled");
		click(homePageobject.searchButton, "Search Button");
		Thread.sleep(2000);
		click(homePageobject.addButton, "Add Button");
		String addUserText = getText(homePageobject.addUserText, "Add User");
		System.out.println("Text is    "+addUserText);
		logger.log(LogStatus.PASS, "Text is    "+addUserText);
		select = new Select(homePageobject.userRoleAdd);
		select.selectByVisibleText("Admin");
		enterValue(homePageobject.employeeName, "Fiona Grace", "EmpName");
		selectValueFromList(homePageobject.employAutoSuggestName,"Fiona Grace","Employee Name");
		enterValue(homePageobject.usernameAdd, "ZukerMD", "UserName");
		select = new Select(homePageobject.statusAdd);
		select.selectByVisibleText("Enabled");
		enterValue(homePageobject.passwordAdd, "Testing@123", "password");
		enterValue(homePageobject.confirmPassword, "Testing@123", "Confirmpassword");
		click(homePageobject.saveButton, "Save Button");
		logger.log(LogStatus.PASS, "Add Employee Success");

	}
	
public void validateSystemUsers() throws Throwable {
		
	    HomepageObjects homePageobject = new HomepageObjects(driver,logger);
		enterValue(homePageobject.username, "ZukerMf", "UserName");
		select = new Select(homePageobject.userRole);
		select.selectByIndex(1);
		enterValue(homePageobject.employName, "Fiona Grace", "EmployeeName");
		selectValueFromList(homePageobject.employAutoSuggestName,"Fiona Grace","Employee Name");
		select = new Select(homePageobject.status);
		select.selectByVisibleText("Enabled");
		click(homePageobject.searchButton, "Search Button");
		Thread.sleep(2000);
		
		for(int i =0 ; i<homePageobject.employeeTable.size();i++) {
			
			String employeeDetails  = homePageobject.employeeTable.get(i).getText();
			
			System.out.println(employeeDetails);
			
		}
		logger.log(LogStatus.PASS, "System Users Success");

	}
}
