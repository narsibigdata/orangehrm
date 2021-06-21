package com.orange.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;



public class HelperMethods {
	public static Properties prop;
	public static Properties prop1;
	protected static String path;
	protected static FileInputStream inputProperty;
	
	protected static String scrImagePath="";
	public WebDriver session ;
	// setters
	
	public static String getScreenImagespath() {

		scrImagePath=new File(".").getAbsolutePath().substring(0, path.length() - 1)+"\\ErrImages\\";
		return scrImagePath;

	}

	public void jsClick(WebElement ele, WebDriver driver) {
		session = driver;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		JavascriptExecutor jse = (JavascriptExecutor)session;
		jse.executeScript("arguments[0].click();",ele);
	}

	public String convertCodeToHex(String colorCode) {
		return Color.fromString(colorCode).asHex();
	}

	public Properties readProperty() {
		System.out.println("reading Property file");
		path = new File(".").getAbsolutePath();
		path = path.substring(0, path.length() - 1);
		try {
			prop = new Properties();
			FileInputStream inputReservationProperty = new FileInputStream(
					path + "\\src\\test\\java\\configurationFiles\\config.properties");
			prop.load(inputReservationProperty);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	public Properties writeProperty(String key, String data) {
		System.out.println("writing Property file");
		path = new File(".").getAbsolutePath();
		path = path.substring(0, path.length() - 1);
		FileOutputStream outputProperty = null;
		try {
			prop1 = new Properties();
			outputProperty = new FileOutputStream(
					path + "\\src\\test\\java\\configurationFiles\\ReservationData.properties", true);
			prop1.setProperty(key, data);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			try {
				prop1.store(outputProperty,null);
				outputProperty.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return prop1;
	}

	public static String capture(WebDriver driver,String imageName) throws IOException {
		getScreenImagespath();
		System.out.println("scrImagPath-> "+scrImagePath);
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File Dest = new File(scrImagePath +imageName+dateName + ".png");
		String errflpath = Dest.getAbsolutePath();
		FileUtils.copyFile(scrFile, Dest);
		return errflpath;
	}

	public int randomNumberGenrator(int minValue, int maxValue) {
		int num = 0;
		int min = minValue;
		int max = maxValue;

		// Generate five random number between 10 and max value
		for (int i = 0; i < 1; i++) {
			num = (int) (Math.random() * (max - min + 1)) + min;
		}
		return num;
	}

	public String isLinkBroken(URL url) {

		try {
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", "Mozilla/5.0");
			connection.setConnectTimeout(2000);
			connection.connect();
			int code = connection.getResponseCode();
			// System.out.println(code + "==>" + url);
			String responseMessage = connection.getResponseMessage();
			String resposeOutput = "response Message => " + responseMessage + "  response code => " + code;
			// System.out.println("response output is => " + resposeOutput);

			connection.disconnect();
			return resposeOutput;

		} catch (Exception exp) {
			return exp.getMessage();
		}
	}

	public Map<String, String> validateBrokenImages(List<WebElement> imageList) throws MalformedURLException {
		List<WebElement> allImages = imageList;
		Map<String, String> imageURLSMessageMap = new HashMap<String, String>();

		String[] URLSMessage = new String[allImages.size()];

		String[] imageURLSAlt = new String[allImages.size()];

		for (int i = 0; i < allImages.size(); i++) {
			imageURLSAlt[i] = allImages.get(i).getAttribute("src") + "   - ' " + allImages.get(i).getAttribute("alt")
					+ " ' ";
		}
		for (int i = 0; i < allImages.size(); i++) {
			System.out.println("Image url message=> " + isLinkBroken(new URL(allImages.get(i).getAttribute("src"))));
			URLSMessage[i] = isLinkBroken(new URL(allImages.get(i).getAttribute("src")));
		}
		for (int i = 0; i < allImages.size(); i++) {
			imageURLSMessageMap.put(imageURLSAlt[i], URLSMessage[i]);
		}
		return imageURLSMessageMap;
	}
	public Map<String, String> validateBrokenImagesNoAlt(List<WebElement> imageList)  {
		List<WebElement> allImages = imageList;
		Map<String, String> imageURLSMessageMap = new HashMap<String, String>();

		String[] URLSMessage = new String[allImages.size()];
		for (int i = 0; i < allImages.size(); i++) {
			try {
				System.out.println("Image url message=> " + isLinkBroken(new URL(allImages.get(i).getAttribute("src"))));
				URLSMessage[i] = isLinkBroken(new URL(allImages.get(i).getAttribute("src")));
			} catch (MalformedURLException e) {
				e.printStackTrace();
				System.out.println("Invalid URL is -> "+allImages.get(i).getAttribute("src") );
			}

		}
		for (int i = 0; i < allImages.size(); i++) {
			imageURLSMessageMap.put(allImages.get(i).getAttribute("src"), URLSMessage[i]);
		}
		return imageURLSMessageMap;
	}
	public Map<String, String> validateBrokenLinksNoAlt(List<WebElement> linksList)  {
		List<WebElement> allLinks = linksList;
		Map<String, String> linkURLSMessageMap = new HashMap<String, String>();

		String[] URLSMessage = new String[allLinks.size()];
		for (int i = 0; i < allLinks.size(); i++) {
			try {
				System.out.println("Image url message=> " + isLinkBroken(new URL(allLinks.get(i).getAttribute("href"))));
				URLSMessage[i] = isLinkBroken(new URL(allLinks.get(i).getAttribute("href")));
			} catch (MalformedURLException e) {
				e.printStackTrace();
				System.out.println("Invalid URL is -> "+allLinks.get(i).getAttribute("href") );
			}

		}
		for (int i = 0; i < allLinks.size(); i++) {
			linkURLSMessageMap.put(allLinks.get(i).getAttribute("href"), URLSMessage[i]);
		}
		return linkURLSMessageMap;
	}
	
	public String trimConfirmationNumber(String confirMsg) {
		String line = confirMsg; // "Confirmation number: 4RVZU5KMH";

		String[] words = line.split("\\:");

		System.out.println("String split with delimiter: " + words[1].trim());
		return words[1].trim();

	}

	public String daysIncrementorRLHFormat(String dateToIncrement,int incrementDays) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH);
		LocalDate date = LocalDate.parse(dateToIncrement, formatter);
		date=date.plusDays(incrementDays);
		date.format(formatter);
		return date.format(formatter);
	}

	public List<BigDecimal> getOnlyNumberValues(List<String> rateList){
		List<BigDecimal> ratesListNumbers= new ArrayList<BigDecimal>();
		for(String rate:rateList) {
			if(rate.contains("$")
					|| rate.contains(",")) {
				String newRate=rate.replaceAll("[$,]", "");
				System.out.println(new BigDecimal(newRate));
				ratesListNumbers.add(new BigDecimal(newRate));
			}
		}
		return ratesListNumbers;
	}

	public String getCurrentDateRLHFormat() {
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH);
		System.out.println(localDate.format(formatter));
		return localDate.format(formatter);
	}
}
