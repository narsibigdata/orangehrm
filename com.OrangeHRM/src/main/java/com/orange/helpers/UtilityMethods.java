package com.orange.helpers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import io.codearte.jfairy.producer.person.PersonProperties;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class UtilityMethods extends BaseLibrary {

	// =============================================//
	/*
	 * Generate Random Names
	 */
	public Fairy data = Fairy.create();
	public Person firstName = null;

	public boolean results = true;

	public String firstNameM() {
		firstName = data.person(PersonProperties.male(), PersonProperties.minAge(21));
		return firstName.firstName();
	}

	public String lastNameM() {
		return firstName.lastName();
	}

	public void checkPageIsReady() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		if (js.executeScript("return document.readyState").toString().equals("complete")) {
			return;
		}
		for (int i = 1; i < 25; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

			}

			if (js.executeScript("return document.readyState").toString().equals("complete")) {
				break;
			}
		}
	}
	// =============================================//
	/*
	 * Generate Random Numbers
	 */

	public String RandomNumber(int length) {
		Random r = new Random();
		if (length == 9) {
			Integer result = 400000000 + (int) (r.nextFloat() * 500000000);
			return result.toString();

		} else if (length == 10) {
			Long result = 4000000000L + (long) (r.nextFloat() * 5000000000L);
			return result.toString();

		} else if (length == 6) {
			Long result = 400000L + (long) (r.nextFloat() * 500000L);
			return result.toString();

		} else if (length == 3) {
			Integer result = 100 + (int) (r.nextFloat() * 900);
			return result.toString();

		} else if (length == 2) {
			Integer result = 10 + (int) (r.nextFloat() * 90);
			return result.toString();

		} else if (length == 11) {
			Long result = 40000000000L + (int) (r.nextFloat() * 50000000000L);
			return result.toString();

		} else if (length == 12) {
			Long result = 400000000000L + (int) (r.nextFloat() * 500000000000L);
			return result.toString();
		} else

			return null;
	}

	public String generateRandomString(int length) {
		return RandomStringUtils.randomAlphabetic(length);
	}

	public String generateRandomNumber(int length) {
		return RandomStringUtils.randomNumeric(length);
	}

	public String generateRandomAlphaNumeric(int length) {
		return RandomStringUtils.randomAlphanumeric(length);
	}

	public String generateStringWithAllobedSplChars(int length, String allowdSplChrs) {
		String allowedChars = "abcdefghijklmnopqrstuvwxyz" + // alphabets
				"1234567890" + // numbers
				allowdSplChrs;
		return RandomStringUtils.random(length, allowedChars);
	}

	public void kendoLoaderlongwait() {
		checkPageIsReady();
		longwait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class='k-loading-image']")));
	}

	public String generateEmail(int length) {
		String allowedChars = "abcdefghijklmnopqrstuvwxyz" + // alphabets
				"1234567890" + // numbers
				"_-."; // special characters
		String email = "";
		String temp = RandomStringUtils.random(length, allowedChars);
		email = temp.substring(0, temp.length() - 9) + "@test.org";
		return email;
	}
	
	public String generateUrl(int length) {
		String allowedChars = "abcdefghijklmnopqrstuvwxyz" + // alphabets
				"1234567890" + // numbers
				"_-."; // special characters
		String url = "";
		String temp = RandomStringUtils.random(length, allowedChars);
		url = temp.substring(0, 3) + "." + temp.substring(4, temp.length() - 4) + "."
				+ temp.substring(temp.length() - 3);
		return url;
	}

	// =============================================//
	/*
	 * longwait for Elements Objects
	 * 
	 * public void longwaitForElement() { //, 60);
	 * driver.manage().timeouts().implicitlylongwait(15, TimeUnit.SECONDS); }
	 */
	// =============================================//

	/*
	 * longwait until click is enabled and click
	 */
	public void click(WebElement ele, String ElementName) throws Exception {
		try {
			// , 30);
			WebDriverWait longwait = new WebDriverWait(driver,20);
			longwait.until(ExpectedConditions.elementToBeClickable(ele));
			ele.click();
			System.out.println(ElementName+" element clicked");
			logger.log(LogStatus.PASS, "Should click on " + ElementName + " successfully" + "<br/>");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to click on " + ElementName + " due to :: </br>" + e + "<br/>");
			throw e;

		}

	}
	public void clickIfVisible(WebElement ele, String ElementName) throws Exception {
		try {
		
			WebDriverWait longwait = new WebDriverWait(driver,20);
			longwait.until(ExpectedConditions.elementToBeClickable(ele));
			ele.click();
			System.out.println(ElementName+" element clicked");
			logger.log(LogStatus.PASS, "Should click on " + ElementName + " successfully" + "<br/>");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to click on " + ElementName + " due to :: </br>" + e + "<br/>");
			throw e;

		}

	}
	public void clickNOLOG(WebElement ele, String ElementName) throws Exception {
		try {
			// , 30);
			longwait.until(ExpectedConditions.elementToBeClickable(ele));
			ele.click();
			
		} catch (Exception e) {
			
			throw e;

		}

	}

	/*
	 * click on the element using xpath
	 */
	public void driverClick(String xpath, String ElementName) throws Throwable {
		try {
			Thread.sleep(3000);
			driver.findElement(By.xpath(xpath)).click();
			logger.log(LogStatus.PASS,
					"Should click on " + ElementName + "Clicked on " + ElementName + " successfully" + "<br/>");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Should click on " + ElementName + "Failed to click on " + ElementName
					+ " due to :: </br>" + e + "<br/>");

		}

	}

	/***
	 * 
	 * @param Element
	 * @param ElementNameDescription
	 * @throws Exception
	 */
	public void sendKeysTo(WebElement element, String value) throws Exception {
		try {
			shortwait.until(ExpectedConditions.visibilityOf(element));
			// clearing the element
			element.clear();
			element.sendKeys(value);
			logger.log(LogStatus.PASS,
					"Should send text " + value + " in " + element.getTagName() + " successfully" + "<br/>");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL,
					"Failed to send text " + value + " in " + element.getClass() + " due to :: </br>" + e + "<br/>");
			throw e;
		}

	}

	/*
	 * MouseHover on an element
	 */
	public void mouseHover(WebElement ele, String ElementName) throws Throwable {
		try {
			// , 45);

			System.out.println(ele.getText());
			WebDriverWait longwait = new WebDriverWait(driver,20);
			longwait.until(ExpectedConditions.visibilityOf(ele));
			
			action = new Actions(driver);

			action.moveToElement(ele).build().perform();
			Thread.sleep(2000);
			logger.log(LogStatus.PASS, "Should mouse hover on " + ElementName + " successfully" + "<br/>");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to mouse hover on " + ElementName + " due to :: </br>" + e + "<br/>");

		}

	}
	public void mouseHoverClickNoLOG(WebElement ele, String ElementName) throws Throwable {
		try {
			// , 45);

			System.out.println(ele.getText());
			longwait.until(ExpectedConditions.visibilityOf(ele));
			action = new Actions(driver);
			action.moveToElement(ele).click().build().perform();
			//action.moveToElement(ele).build().perform();
			Thread.sleep(2000);
			
		} catch (Exception e) {
			System.out.print(e.getLocalizedMessage());

		}

	}
	

	// get attribute

	public String getAttributes(WebElement ele, String AttributeValue) throws Throwable {
		String Text = "";
		try {

			WebDriverWait longwait = new WebDriverWait(driver,20);
			longwait.until(ExpectedConditions.visibilityOf(ele));
			Text = ele.getAttribute(AttributeValue);
			logger.log(LogStatus.PASS, "Should successfully return " + AttributeValue + "as " + Text + " " + "<br/>");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL,
					"Should return " + AttributeValue + "as" + Text + " due to :: </br>" + e + "<br/>");

		}
		return Text;

	}

	/*
	 * Get Text
	 */
	public String getText(WebElement ele, String ElementName) throws Throwable {
		String Text = "";
		try {

			WebDriverWait longwait = new WebDriverWait(driver,20);
			longwait.until(ExpectedConditions.visibilityOf(ele));
			Text = ele.getText();
			logger.log(LogStatus.PASS, "Should return " + ElementName, "as " + Text + " successfully" + "<br/>");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Should return " + ElementName, "as" + Text + " due to :: </br>" + e + "<br/>");

		}
		return Text;

	}

	public String getCurrentUrl(WebDriver driver) throws Throwable {

		return driver.getCurrentUrl();
	}

	public String getNewTabUrlnClose(WebDriver driver) throws Throwable {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		System.out.println("tabs size" + tabs.size());
		driver.switchTo().window(tabs.get(1));
		String newTabURL = driver.getCurrentUrl();
		driver.close();
		driver.switchTo().window(tabs.get(0));
		return newTabURL;

	}

	public void switchToNewTab(WebDriver driver, int tabValue) throws Throwable {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		System.out.println("tabs size" + tabs.size());
		if (tabValue <= tabs.size()) {
			driver.switchTo().window(tabs.get((tabValue - 1)));
		}

	}

	public void closeCurrentTabnSwitchToFirst(WebDriver driver) throws Throwable {
		driver.close();
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));

	}

	public void closeTabValuenReturnFirstTab(WebDriver driver, int tabValue) throws Throwable {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		System.out.println("tabs size" + tabs.size());
		if (tabValue <= tabs.size()) {
			driver.switchTo().window(tabs.get(tabValue));
		}
		driver.close();
		driver.switchTo().window(tabs.get(0));
	}

	public void navigateToPreviousPage(WebDriver driver) throws Throwable {
		
		driver.navigate().back();
		logger.log(LogStatus.PASS, "Navigated successfully from previous page");
	}

	/*
	 * Click using JavaScript
	 */
	public void jsClick(WebElement ele, String ElementName) throws Throwable {
		try {

			longwait.until(ExpectedConditions.elementToBeClickable(ele));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", ele);
			Thread.sleep(2500);
			logger.log(LogStatus.PASS, "Should click on " + ElementName,
					"Clicked on " + ElementName + " successfully" + "<br/>");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Should click on " + ElementName,
					"Failed to click on " + ElementName + " due to :: </br>" + e + "<br/>");

		}
	}
	public void jsClickNoWait(WebElement ele, String ElementName) throws Throwable {
		try {

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", ele);
			Thread.sleep(2500);
			logger.log(LogStatus.PASS, "Should click on " + ElementName,
					"Clicked on " + ElementName + " successfully" + "<br/>");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Should click on " + ElementName,
					"Failed to click on " + ElementName + " due to :: </br>" + e + "<br/>");

		}
	}
	/*
	 * Verify for an element and click on that element if it is displayed
	 */
	public void verifyIfDisplayAndClick(WebElement ele, String ElementName) throws Throwable {
		Thread.sleep(3000);
		try {
			if (ele.isEnabled()) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", ele);
				logger.log(LogStatus.PASS, "Should click on " + ElementName,
						"Clicked on " + ElementName + " successfully" + "<br/>");
			}
		} catch (Exception e) {
			logger.log(LogStatus.INFO, "Should click on " + ElementName,
					ElementName + " is not displayed and no need to click");

		}

	}

	/*
	 * Verify for an element whether it is displayed or not
	 */
	public void verifyElementDisplayed(WebElement ele, String ElementName) throws Throwable {
		try {

			ele.isDisplayed();
			logger.log(LogStatus.PASS, "Should display " + ElementName,
					"Displayed " + ElementName + " successfully" + "<br/>");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Should display " + ElementName,
					"Failed to display " + ElementName + " due to :: </br>" + e + "<br/>");

		}

	}

	/*
	 * Verify for an element whether it is enabled or not
	 */
	public void verifyElementEnabled(WebElement ele, String ElementName) throws Throwable {
		try {
			ele.isEnabled();
			logger.log(LogStatus.PASS, "Should Enable " + ElementName,
					"Enabled " + ElementName + " successfully" + "<br/>");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Should Enable " + ElementName,
					"Failed to Enable " + ElementName + " due to :: </br>" + e + "<br/>");

		}

	}

	/*
	 * Sending values to the element
	 */
	public void enterValue(WebElement ele, String Value, String ElementName) throws Exception {
		try {
			WebDriverWait longwait = new WebDriverWait(driver,20);
			longwait.until(ExpectedConditions.elementToBeClickable(ele));
			
			ele.clear();
			ele.sendKeys(Value);
			logger.log(LogStatus.PASS, "Should Enter " + Value+" in " + ElementName+ " successfully" + "<br/>");
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(LogStatus.FAIL, "Failed to Enter " + Value+" in " + ElementName+ " successfully" + "<br/>");

		}

	}
	public void selectValueFromList(List<WebElement> dropDownListResults, String valueToSelect, String ElementName) throws Throwable {
		try {

			longwait.until(ExpectedConditions.visibilityOf(dropDownListResults.get(0)));
			for(WebElement values:dropDownListResults) {
				if(values.getText().equals(valueToSelect)) {
					System.out.println("clicking "+values.getText());
					values.click();
				}
			}
			logger.log(LogStatus.PASS, "Should select " + valueToSelect+" in " + ElementName+ " successfully" + "<br/>");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to select " + valueToSelect+" in " + ElementName+ "<br/>");

		}

	}
	/*
	 * Sending values using JavaScript
	 */
	public void jsEnterValue(WebElement ele, String Value, String ElementName) throws Throwable {
		try {
			// , 20);
			longwait.until(ExpectedConditions.elementToBeClickable(ele));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].value='" + Value + "';", ele);
			logger.log(LogStatus.PASS, "Should Enter Value = " + Value,
					"Entered Value = " + Value + " successfully" + "<br/>");

		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Should Enter " + Value,
					"Failed to Enter " + Value + " due to :: </br>" + e + "<br/>");

		}
	}

	/*
	 * Clear value in element
	 */
	public void clearEnterValue(WebElement ele) throws Throwable {
		try {

			longwait.until(ExpectedConditions.elementToBeClickable(ele));
			ele.clear();
			logger.log(LogStatus.PASS, "Should clear the field", "Cleared field successfully" + "<br/>");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Should clear field", "Failed to clear field due to ::" + e);

		}

	}

	/*
	 * Verify for an element whether it is displayed or not
	 */
	public void verifyIsDisplyedAndEnterValue(WebElement ele, String Value, String ElementName) throws Throwable {
		try {

			if (ele.isDisplayed()) {
				ele.clear();
				logger.log(LogStatus.PASS, "Should clear the field", "Cleared field successfully" + "<br/>");
				ele.sendKeys(Value);
				logger.log(LogStatus.PASS, "Should Enter " + Value, "Entered " + Value + " successfully" + "<br/>");
			}
		} catch (Exception e) {
			logger.log(LogStatus.INFO, "Should Enter " + Value, "No Need to enter value");

		}

	}

	public void selectValueByIndex(WebElement ele, int Index, String ElementName) throws Throwable {
		try {

			longwait.until(ExpectedConditions.elementToBeClickable(ele));
			new Select(ele).selectByIndex(Index);
			logger.log(LogStatus.PASS, "Should select " + Index + " for " + ElementName,
					"Selected " + Index + " successfully for " + ElementName + "<br/>");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Should select " + Index + "for " + ElementName,
					"Failed to select " + Index + " for " + ElementName + " due to :: </br>" + e + "<br/>");

		}

	}

	public void selectValueByValue(WebElement ele, String Value, String ElementName) throws Throwable {
		try {

			longwait.until(ExpectedConditions.elementToBeClickable(ele));
			new Select(ele).selectByValue(Value);
			logger.log(LogStatus.PASS, "Should successfully select " + Value + " for " + ElementName+
				"<br/>");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to select " + Value + " for " + ElementName+
					"<br/>" + " due to error "+e.getMessage());

		}

	}

	public void selectValueByVisbileText(WebElement ele, String Value, String ElementName) throws Throwable {
		try {

			longwait.until(ExpectedConditions.elementToBeClickable(ele));
			new Select(ele).selectByVisibleText(Value);
			logger.log(LogStatus.PASS, "Should select " + Value + " for " + ElementName,
					"Selected " + Value + " successfully for " + ElementName + "<br/>");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Should select " + Value + "for " + ElementName,
					"Failed to select " + Value + " for " + ElementName + " due to :: </br>" + e + "<br/>");

		}

	}

	public void switchToFrame(String employeeSearchFrame) throws Throwable {
		try {

			Thread.sleep(5000);
			driver.switchTo().frame(employeeSearchFrame);
			logger.log(LogStatus.PASS, "Should switch to Frame ", "Switched to Frame  successfully" + "<br/>");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Should switch to Frame ",
					"Failed to switch to Frame due to :: </br>" + e + "<br/>");
		}
	}

	// durga added switch to frame by WebElement ( Frame Element)
	public void switchToFrameByWE(WebElement ele) throws Throwable {
		try {

			Thread.sleep(5000);
			driver.switchTo().frame(ele);
			logger.log(LogStatus.PASS, "Should switch to Frame ", "Switched to Frame  successfully" + "<br/>");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Should switch to Frame ",
					"Failed to switch to Frame due to :: </br>" + e + "<br/>");
		}
	}

	public void switchToDefault() throws Throwable {
		try {

			driver.switchTo().defaultContent();
			logger.log(LogStatus.PASS, "Should switch Default window",
					"Switched to default window successfully" + "<br/>");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Should switch Default window",
					"Failed to switch to default window due to :: </br>" + e + "<br/>");
		}
	}

	public void verifyisdisplayed(String Xpath, String ElementName) throws Throwable {
		try {

			if (driver.findElement(By.xpath(Xpath)).isDisplayed()) {
				logger.log(LogStatus.PASS, "Should display " + ElementName,
						"Displayed " + ElementName + " successfully" + "<br/>");
			}
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Should display " + ElementName,
					"Failed to Displayed " + ElementName + " due to :: </br>" + e + "<br/>");

		}
	}

	public void characterValidation(WebElement ele, String value, int length) {

		longwait.until(ExpectedConditions.elementToBeClickable(ele));
		ele.sendKeys(value);
		try {
			if (value.length() <= length) {
				logger.log(LogStatus.PASS, "Validate character Lenght", "Charcter length successful");
			}
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Validate character Lenght",
					"Failed to validated charcter length due to :: " + e);
		}
	}

	public void tabFunction(WebElement ele) throws Throwable {

		Thread.sleep(1000);
		ele.sendKeys(Keys.TAB);
		ele.sendKeys(Keys.ENTER);
		Thread.sleep(2000);

	}

	public void scrolldown(int value) throws Throwable {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0," + value + ")");
	}

	public void scrollUp(int value) throws Throwable {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0," + (-value) + ")");
	}

	public void scrolldown(WebElement ele) throws Throwable {

		ele.sendKeys(Keys.PAGE_DOWN);
	}

	public void datePicker(WebElement ele, int count, String locator) throws Throwable {
		boolean status = false;
		String TextValue = "";
		try {

			longwait.until(ExpectedConditions.elementToBeClickable(ele));
			if (ele.isDisplayed()) {
				logger.log(LogStatus.PASS, "Should display " + locator,
						"Displayed " + locator + " successfully" + "<br/>");
				for (int i = 0; i <= count; i++) {
					ele.click();
				}
				logger.log(LogStatus.PASS, "Should click on " + locator,
						"Clicked on " + locator + " successfully" + "<br/>");
				;

			}

		} catch (Exception e) {
			status = false;
			e.printStackTrace();
			logger.log(LogStatus.FAIL, "Should click on " + locator,
					"Failed to click on " + locator + " due to :: </br>" + e + "<br/>" + e);

			// System.exit(0);
		}

	}
	public  void datePicker(WebDriver driver, WebElement ele, String method_name , int date, String month) throws Throwable {
		boolean status = false;
		String TextValue = "";
		try {
			
			

			shortwait.until(ExpectedConditions.visibilityOf(ele));
			shortwait.until(ExpectedConditions.elementToBeClickable(ele));

			if (ele.isDisplayed()) {
				System.out.println(method_name + "---->element is selected");
				ele.click();

				for (int i = 0; i <= 6; i++) {

					driver.findElement(By.xpath("//*[contains(@class,'PAheader')]//span[contains(@class,'"+month+"')]")).click();
				}

				driver.findElement(
						By.xpath("//*[@class='PAmonth PAfrom']//span[contains(text(),'"+date +"')]")).click();
				System.out.println(method_name + "----is---" + TextValue);
				
			}

			else {
				System.out.println("Text value is---" + TextValue);
			}

		} catch (Exception e) {
			status = false;
			e.printStackTrace();
			
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());

		}

	}
	
	public void backSpaceFunction(WebElement ele) throws Throwable {

		Thread.sleep(1000);
		ele.sendKeys(Keys.BACK_SPACE);
		ele.sendKeys(Keys.ENTER);
		Thread.sleep(2000);

	}



	public void scrollCompletePageUp(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void scrollForElementByLocation(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0," + element.getLocation().x + ")");
	}

	public void scrollForElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight);");
		try {
			Thread.sleep(3000);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void scrollCompletePageUp() {
		System.out.println("scrolling page up");
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight);");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<String> getTestDataAsListAfterSplitting(String valueToSplit, String splitBy) {
		List<String> splitValueList = new ArrayList<String>();
		String[] values = valueToSplit.trim().split(splitBy);
		splitValueList = Arrays.asList(values);
		return splitValueList;
	}

	public String getImmediateAlertMessage(WebElement textelement, WebElement closeElement) {
		shortwait.until(ExpectedConditions.visibilityOf(closeElement));
		String message = textelement.getText();
		return message;
	}

	public String getAlertMessageAfterClose(WebElement textelement, WebElement closeElement) {
		shortwait.until(ExpectedConditions.visibilityOf(closeElement));
		String message = textelement.getText();
		try {
			shortwait.until(ExpectedConditions.invisibilityOf(closeElement));
		} catch (TimeoutException e) {
			closeElement.click();
		}
		return message;
	}
	
	public void imageComparisionWithScreenShot(WebElement ele, String ExpectedImagePath) {

		try {

			BufferedImage expectedImage = ImageIO.read(new File(ExpectedImagePath));

			Thread.sleep(2000);
			Screenshot logoImageScreenshot = new AShot().takeScreenshot(driver, ele);
			BufferedImage actualImage = logoImageScreenshot.getImage();
			ImageIO.write(actualImage, "PNG", new File(System.getProperty("user.dir") + "\\Images\\ActualImage.png"));

			ImageDiffer imgDiff = new ImageDiffer();
			ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
			int difference = imgDiff.makeDiff(actualImage, expectedImage).getDiffSize();
			if (diff.hasDiff() == true) {
				logger.log(LogStatus.PASS, "Actual image and expected image are same");
				System.out.println("Images are not Same");
				System.out.println(difference);
			} else {
				logger.log(LogStatus.WARNING, "Actual image and expected image are not same");
				System.out.println("Images are Same");
			}

		}

		catch (Exception e) {

		}

	}
	
	public  void footerLinkdVerification(WebElement link) throws InterruptedException {

		String linkURL = "";
		logger.log(LogStatus.INFO, "Clicking on Link \" " + link.getText() + " \" ");

		logger.log(LogStatus.INFO, "Link's URL is " + link.getAttribute("href"));

		link.click();

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

		// System.out.println("Text of link " + link.getText());

		Thread.sleep(5000);

		driver.switchTo().window(tabs.get(1));

		// System.out.println("Link of clicked url-> " +

		// webDriverSessionManagement.GetSessionWebDriver().getCurrentUrl());

		String URLNewTab = driver.getCurrentUrl();

		// System.out.println("Page Title is ->

		// "+webDriverSessionManagement.GetSessionWebDriver().getTitle());

		logger.log(LogStatus.INFO, "Page Title of clicked link is -> "

				+ driver.getTitle());

		driver.close();

		driver.switchTo().window(tabs.get(0));

		linkURL = link.getAttribute("href");

		System.out.println("link url -> " + linkURL);

		if (URLNewTab.equals(linkURL)) {

			logger.log(LogStatus.PASS,

					"Current page URL is matching with the link url of the Element");

		}

		else {

			logger.log(LogStatus.FAIL, "URL not matching");

		}

	}
	public static Set<String> findDuplicates(List<String> listContainingDuplicates) {
		 
		 Set<String> setToReturn = new HashSet<String>();
		 Set<String> set1 = new HashSet<String>();

		for (String yourInt : listContainingDuplicates) {
			if (!set1.add(yourInt)) {
				setToReturn.add(yourInt);
			}
		}
		return setToReturn; 
	}
	
}
