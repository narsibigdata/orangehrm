package com.orange.pageobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orange.helpers.BaseLibrary;
import com.relevantcodes.extentreports.ExtentTest;


public class HomepageObjects extends BaseLibrary {
	
	public HomepageObjects(WebDriver driver,ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		PageFactory.initElements(driver, this);
	}
	 
	@FindBy(id = "menu_admin_viewAdminModule")
    public WebElement admin;

	@FindBy(id = "searchSystemUser_userName")
    public WebElement username;
	
	@FindBy(id = "searchSystemUser_userType")
    public WebElement userRole;

	@FindBy(id = "searchSystemUser_employeeName_empName")
    public WebElement employName;
	
	@FindBy(xpath = "//div[@class='ac_results']//li")
    public List<WebElement> employAutoSuggestName;
	
	@FindBy(id = "searchSystemUser_status")
    public WebElement status;
	
	@FindBy(id = "searchBtn")
	public WebElement searchButton;
	
	@FindBy(id = "resetBtn")
	public WebElement reset;
	
	@FindBy(id = "btnAdd")
	public WebElement addButton;
	
	
	@FindBy(id = "UserHeading")
	public WebElement addUserText;
	
	@FindBy(id = "systemUser_userType")
	public WebElement userRoleAdd;
	
	@FindBy(id = "systemUser_employeeName_empName")
	public WebElement employeeName;
	
	@FindBy(id = "systemUser_userName")
	public WebElement usernameAdd;
	
	@FindBy(id = "systemUser_status")
	public WebElement statusAdd;
	
	@FindBy(id = "systemUser_password")
	public WebElement passwordAdd;
	
	@FindBy(id = "systemUser_confirmPassword")
	public WebElement confirmPassword;
	
	@FindBy(id = "btnSave")
	public WebElement saveButton;
	
	@FindBy(xpath = "//a[@class='toggle tiptip activated']")
	public WebElement systemUsersLink;
	
	@FindBy(xpath = "//table[@id='resultTable']//td")
    public List<WebElement> employeeTable;
	
}
