package com.orange.helpers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseLibrary {
	
	// test
	public static WebDriver driver;
	public final int IMPLICIT_TIMEOUT = 10;
	public String baseURL;
	public static ExtentReports extent;
	public ExtentTest logger;
	public WebDriverWait shortwait;
	public WebDriverWait longwait;
	public Actions action;
	public String reportFileName = "Test-Automation-Report";
	public static String fileSeperator = System.getProperty("file.separator");
	public static String reportFilepath = System.getProperty("user.dir") + fileSeperator + "TestReport";
	//public String reportFileLocation = reportFilepath + fileSeperator + reportFileName;
	public String reportFileLocationUpdated = dynamicDateScreenShotsPath + fileSeperator + reportFileName;
	public String SuccessImageLocation = dynamicDateScreenShotsPath + fileSeperator + "SuccessImages";
	public String ErrorImageLocation = dynamicDateScreenShotsPath + fileSeperator + "ErrorImages";
	public static String dateTime = getTime();
	public static String dynamicDateScreenShotsPath = reportFilepath + fileSeperator + dateTime;

	private void validateFolderExists(String folderName) {
		File folder = new File(folderName);
		if (!folder.exists()) {
			folder.mkdir();
		}
	}

	public void setReportNamewithDate() {
		System.out.println("In Extent Report Setup");
		// DateFormat dateFormat = new SimpleDateFormat("hh.mm.ss a");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMdd hh.mm.ss a");
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		reportFileLocationUpdated = reportFileLocationUpdated + "_" + date;
	}

	public static String getTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMdd hh.mm.ss.SS a");
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		return date;
	}

	public static String getTimeNanoSeconds() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMdd hh.mm.ss.SS.n a");
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		return date;
	}

	@BeforeSuite(alwaysRun = true)
	
	
	public ExtentReports getInstance() {
		if (extent == null)
			createInstance();
		return extent;
		
		
	}

	// Create an extent report instance
	public synchronized ExtentReports createInstance() {
		setReportNamewithDate();
		validateFolderExists(dynamicDateScreenShotsPath);
		// String workingDir = System.getProperty("user.dir");
		extent = new ExtentReports(reportFileLocationUpdated + ".html", true);
		return extent;
	}

	public void takeScreenShotSuccess(WebDriver driver, String methodname) throws Throwable {
		String date = getTimeNanoSeconds();
		//String zoomInJs="document.body.style.zoom='50%'";
		//header[contains(@class,'region region-header ')]
		//$('.region region-header bg-bw-white shadow d-flex z-index-20 js-stick-desktop').css('position', 'relative');
		String headerFixed="$('header').removeClass('region region-header bg-bw-white shadow d-flex z-index-20 js-stick-desktop');";
		//headerFixed="$('header').removeClass('region region-header bg-bw-white shadow d-flex z-index-20 js-stick-desktop stuck');";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript(zoomInJs);
		js.executeScript( headerFixed);
		Thread.sleep(5000);
		Screenshot screenshot=new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		BufferedImage scrFile = screenshot.getImage(); 
		validateFolderExists(SuccessImageLocation);
		//File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);	
		ImageIO.write(scrFile, "PNG", new File(SuccessImageLocation+fileSeperator+methodname+ "_" + date + ".png"));
		validateFolderExists(SuccessImageLocation);
		Thread.sleep(5000);
		headerFixed="$('header').attr('class','region region-header bg-bw-white shadow d-flex z-index-20 js-stick-desktop');";
		//headerFixed="$('header').attr('region region-header bg-bw-white shadow d-flex z-index-20 js-stick-desktop stuck');";
		//zoomInJs="document.body.style.zoom='100%'";
		//js.executeScript(zoomInJs);
		js.executeScript( headerFixed);
	}
	
	public void takeScreenShotHalfPage(WebDriver driver, String methodname) throws Throwable {
       // String date=getTimeNanoSeconds();
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        validateFolderExists(SuccessImageLocation);
        FileUtils.copyFile(scrFile, new File(SuccessImageLocation+ fileSeperator+methodname+"_"+".png"));

	}
	public void takeScreenShotHalfPage(WebDriver driver) throws Throwable {
	       // String date=getTimeNanoSeconds();
	        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	        validateFolderExists(SuccessImageLocation);
	        FileUtils.copyFile(scrFile, new File(SuccessImageLocation+ fileSeperator+"_"+".png"));

		}
	public void takeScreenShotError(WebDriver driver, String methodname) throws Throwable {
		String date = getTimeNanoSeconds();
		Screenshot screenshot=new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		BufferedImage scrFile = screenshot.getImage(); 
		//File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		validateFolderExists(ErrorImageLocation);
		//FileUtils.copyFile(scrFile, new File(ErrorImageLocation + fileSeperator + methodname + "_" + date + ".png"));
		ImageIO.write(scrFile, "PNG", new File(ErrorImageLocation+fileSeperator+methodname+ "_" + date + ".png"));

	}

	
	
	public  void startdriver() 
	{
			System.out.println("Driver setup started");
		    WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-logging");
			// options.addArguments("disable-infobars");
			driver = new ChromeDriver(options);
			shortwait = new WebDriverWait(driver, 20);
			longwait = new WebDriverWait(driver, 40);
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
			driver.navigate().to("https://opensource-demo.orangehrmlive.com/");
			System.out.println(
					"Chrome init completed for version " + WebDriverManager.chromedriver().getDownloadedVersion());
	
	}
	
	@AfterSuite(alwaysRun = true)
	public void quitDriver() {
		driver.quit();
		extent.flush();

	}

}
