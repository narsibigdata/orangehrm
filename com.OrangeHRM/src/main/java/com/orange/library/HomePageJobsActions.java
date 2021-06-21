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

import com.orange.pageobjects.HomepageObjectsJobs;

import com.orange.pageobjects.LoginPageObjects;

import com.relevantcodes.extentreports.LogStatus;

public class HomePageJobsActions extends UtilityMethods {

	

	public void validateAddJobs() throws Throwable {
		
		HomepageObjectsJobs homePagejobsobject = new HomepageObjectsJobs(driver,logger);
		
		mouseHover(homePagejobsobject.admin,"Admin");
		mouseHover(homePagejobsobject.jobs,"Jobs");
		click(homePagejobsobject.jobTitles, "Job Titles");
		
		click(homePagejobsobject.addButton, "Add");
		enterValue(homePagejobsobject.jobTitle, "Tester", "Job Title");
		enterValue(homePagejobsobject.jobDescription, "Manual & Automation Testing", "Job Description");
		enterValue(homePagejobsobject.note, "Manual & Automation Testing", "Job Note");
		
		click(homePagejobsobject.saveButton, "Save Button");
		logger.log(LogStatus.PASS, "Add Jobs Success");

	}
	

}
