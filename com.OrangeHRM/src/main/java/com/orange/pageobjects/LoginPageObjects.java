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
import com.relevantcodes.extentreports.LogStatus;

public class LoginPageObjects extends BaseLibrary {
	
	

	public LoginPageObjects(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		PageFactory.initElements(driver, this);
		
	}

	
/*	@FindBy(id = "txtUsername")
    public WebElement userName;

	@FindBy(id = "txtPassword")
    public WebElement passWord;
	
	@FindBy(id = "btnLogin")
    public WebElement submit;      */
	
	@FindBy(xpath = "//div[@class='tab-pane--healdine']/h2[text()='Login']")
    public WebElement loginText;
	
	@FindBy(xpath = "//div[@class='tab-pane--healdine']/h2[text()='Create an account']")
    public WebElement createAccountText;
	
	@FindBy(xpath = "//li[@class='header__nav-item']/a[text()='Arlo Smart']")
    public WebElement ArloSmart;
	
	@FindBy(xpath = "//li[@class='header__nav-item']/a[text()='Cameras']")
    public WebElement Cameras;
	
	@FindBy(xpath = "//li[@class='header__nav-item']/a[text()='Doorbells']")
    public WebElement Doorbells;
	
	@FindBy(xpath = "//li[@class='header__nav-item']/a[text()='Floodlight Cameras']")
    public WebElement FloodlightCameras;
	
	
	@FindBy(xpath = "//li[@class='header__nav-item']/a[text()='Accessories']")
    public WebElement Accessories;
	
	@FindBy(xpath = "//li[@class='header__utilities-item item-support']/div/a[text()='Support']")
    public WebElement Support;
	
	@FindBy(xpath = "//ul[@class='header__nav-list']//a")
    public List<WebElement> TabsList;
	
	@FindBy(xpath = "(//div[@class='footer__nav-col-header legal-bold-text'])[3]/following-sibling::ul//a")
    public List<WebElement> Footer_SupportList;
	
	@FindBy(id = "registration-form-fname")
    public WebElement FirstName;
	
	@FindBy(id = "registration-form-lname")
    public WebElement lastName;
	
	@FindBy(id = "registration-form-email")
    public WebElement Email;
	
	@FindBy(id = "registration-form-email-confirm")
    public WebElement ConfirmEmail;
	
	@FindBy(id = "registration-form-phone")
    public WebElement PhoneNumber;
	
	@FindBy(id = "registration-form-password")
    public WebElement Password;
	
	@FindBy(xpath = "//input[@id='login-form-email']")
    public WebElement EmailLogin;

	@FindBy(xpath = "//input[@id='login-form-password']")
    public WebElement PasswordLogin;
	
	@FindBy(id = "registration-form-password-confirm")
    public WebElement ConfirmPassword;
	
	@FindBy(xpath = "//label[@for='add-to-email-list']")
    public WebElement arlocheckbox;
	
	@FindBy(xpath = "//button[@class='btn btn-block btn-primary']")
    public WebElement CreateAccount;
	
	@FindBy(xpath = "(//div[@class='footer__nav-col-header legal-bold-text'])[2]/following-sibling::ul//a")
    public List<WebElement> Footer_companyList;
	
	@FindBy(xpath = "(//div[@class='card-header']")
    public List<WebElement> loginAccountList;
	
	@FindBy(xpath = "//div[@class='card-body']")
    public WebElement userInfo;
	
		
	@FindBy(xpath = "//label[@class='custom-control-label legal-text']")
    public WebElement loginCheckbox;
	
	@FindBy(xpath = "//button[@class='btn btn-block btn-primary js-login-disable-onload']")
    public WebElement loginAccount;
	
	
	@FindBy(xpath = "//input[@id='email']")
    public WebElement UserLogin;

	@FindBy(xpath = "//input[@id='pass']")
    public WebElement UserPassword;
	
	@FindBy(xpath = "//label[@class='login_form_login_button uiButton uiButtonConfirm']")
    public WebElement Login;

	@FindBy(id = "logInPanelHeading")
    public WebElement LOGINPanel;
	
	@FindBy(id = "txtUsername")
    public WebElement txtUsername;
	
	@FindBy(id = "txtPassword")
    public WebElement txtPassword;
	
	@FindBy(id = "btnLogin")
    public WebElement Submit;
	
	@FindBy(id = "welcome")
    public WebElement welcome;
	
	@FindBy(xpath = "//a[@href='/index.php/auth/logout']")
    public WebElement logout;
	
	@FindBy(xpath = "//a[@href=\"/register\"]")
    public WebElement Register;
	
	
}
