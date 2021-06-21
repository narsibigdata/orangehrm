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

public class LoginPageActions extends UtilityMethods {
	
	//public void validateLoginPage(String Username,String Password) throws Exception {
			
		public void validateLoginPage() throws Exception {  
		try {
			
		LoginPageObjects loginPageobject = new LoginPageObjects(driver,logger);
	       
			/* enterValue(loginPageobject.userName,Username , "UserName");
			enterValue(loginPageobject.passWord,Password, "Password");
			click(loginPageobject.submit, "Login Button");   */
		
		Thread.sleep(5000);
		String Logn = loginPageobject.loginText.getText();
		String CreateAccount = loginPageobject.createAccountText.getText();
		String ArloSmart = loginPageobject.ArloSmart.getText();
		String Cameras = loginPageobject.Cameras.getText();
		String Doorbells = loginPageobject.Doorbells.getText();
		String FloodlightCameras = loginPageobject.FloodlightCameras.getText();
		String Accessories = loginPageobject.Accessories.getText();
		String Support = loginPageobject.Support.getText();
	
		if(Logn.equals("Login") && CreateAccount.equals("Create an account") && ArloSmart.equals("AlroSmart") && Cameras.equals("Cameras") && Doorbells.equals("Doorbells") && FloodlightCameras.equals("Floodlightcameras") && Accessories.equals("Accessories") && Support.equals("Support"))
		{
		   
			logger.log(LogStatus.PASS, "login validation");     // Green
			
			logger.log(LogStatus.INFO, "Login text matches with "+Logn);
			logger.log(LogStatus.INFO, "Create Account text is   "+CreateAccount);
			logger.log(LogStatus.INFO, "AlroSmart is   "+ArloSmart);
			logger.log(LogStatus.INFO, "Cameras is   "+Cameras);
			logger.log(LogStatus.INFO, "Floodlightcameras is   "+FloodlightCameras);
			logger.log(LogStatus.INFO, "Accessories is   "+Accessories);
			logger.log(LogStatus.INFO, "Support is   "+Support);
			
		}
		// tabs list
		for(int i =0 ; i < loginPageobject.TabsList.size();i++ )
		{
           String text  = loginPageobject.TabsList.get(i).getText(); 
           if(text!="") 
           {
        	   
        	   logger.log(LogStatus.PASS,"Text is displayed ");     
   			
   			logger.log(LogStatus.INFO, " text matches with "+text);
           }
           else
        	   logger.log(LogStatus.FAIL,"Text is not  displayed "); 
		}
	}
		catch(Exception e ) {
			
			e.printStackTrace();
		}
		
		}
		public void FooterTabs() throws Exception {  
			try {
				
			LoginPageObjects loginPageobject = new LoginPageObjects(driver,logger);
		       
				
			
			// tabs list
			for(int i =0 ; i < loginPageobject.Footer_companyList.size();i++ )
			{
	           String text  = loginPageobject.Footer_companyList.get(i).getText(); 
	           if(text!="") 
	           {
	        	   
	        	   logger.log(LogStatus.PASS,"Text is displayed ");     
	   			
	   			logger.log(LogStatus.INFO, " text matches with "+text);
	           }
	           else
	        	   logger.log(LogStatus.FAIL,"Text is not  displayed "); 
			}
			
			for(int j =0 ; j < loginPageobject.Footer_SupportList.size();j++ )
			{
	           String text  = loginPageobject.Footer_SupportList.get(j).getText(); 
	           if(text!="") 
	           {
	        	   
	        	   logger.log(LogStatus.PASS,"Text is displayed ");     
	   			
	   			logger.log(LogStatus.INFO, " text matches with "+text);
	           }
	           else
	        	   logger.log(LogStatus.FAIL,"Text is not  displayed "); 
			}

		}
			catch(Exception e ) {
				
				e.printStackTrace();
			}
			
			} 
		public void CreateAccount() throws Exception {  
			try {
				
			LoginPageObjects loginPageobject = new LoginPageObjects(driver,logger);
			
		loginPageobject.FirstName.sendKeys("narasimha");
		loginPageobject.lastName.sendKeys("kaki");
		loginPageobject.Email.sendKeys("narasimha.kece@gmail.com");
		loginPageobject.ConfirmEmail.sendKeys("narasimha.kece@gmail.com");
		loginPageobject.PhoneNumber.sendKeys("9177370311");
		loginPageobject.Password.sendKeys("Narsi123");
		loginPageobject.ConfirmPassword.sendKeys("Narsi123");
		loginPageobject.arlocheckbox.click();
		loginPageobject.CreateAccount.click();
				
			}
			catch(Exception e ) {
				
				e.printStackTrace();
			}
			
			} 
		public void LoginAccount() throws Exception {  
				try {
				
					LoginPageObjects loginPageobject = new LoginPageObjects(driver,logger);
					
				loginPageobject.EmailLogin.sendKeys("narasimha.kece@gmail.com");
				loginPageobject.PasswordLogin.sendKeys("Narsi123");
				//loginPageobject.loginCheckbox.click();
				loginPageobject.loginAccount.click();
				Thread.sleep(5000);
				String UserInfo = loginPageobject.userInfo.getText();
				if (UserInfo != null) 
				{
					   logger.log(LogStatus.PASS,"UserInfo is displayed "+ UserInfo);
				} else logger.log(LogStatus.FAIL,"UserInfo is displayed "+ UserInfo);
					}
				
					catch(Exception e ) {
						
						e.printStackTrace();
					}
		}
		
		public void HRMLogin() throws Exception {  
			try {
			
				LoginPageObjects loginPageobject = new LoginPageObjects(driver,logger);
				Thread.sleep(5000);
				String UserInfo = loginPageobject.LOGINPanel.getText();
						
			loginPageobject.txtUsername.sendKeys("Admin");
			loginPageobject.txtPassword.sendKeys("admin123");
			loginPageobject.Submit.click();
						if (UserInfo != null) 
			{
				   logger.log(LogStatus.PASS,"UserInfo is displayed "+ UserInfo);
			} else logger.log(LogStatus.FAIL,"UserInfo is displayed "+ UserInfo);
				}
			
				catch(Exception e ) {
					
					e.printStackTrace();
				}
	}

		
		public void HRMlogout() throws Exception {  
			try {
			
				LoginPageObjects loginPageobject = new LoginPageObjects(driver,logger);
				Thread.sleep(5000);
				loginPageobject.welcome.click();
				Thread.sleep(1000);
				loginPageobject.logout.click();
		
				
				}
			
				catch(Exception e ) {
					
					e.printStackTrace();
				}
	}

}


