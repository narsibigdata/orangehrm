package com.orange.helpers;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import org.sikuli.script.Button;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class SikuliImageCompareHelper {

	protected static Screen screen;
	public String parent_directory;
	protected String brandImagesPath;
	protected String footerImagesPath;
	protected String otherImagesPath;
	protected String carRentalServiceImagesPath;
	
	public String getBrandImagesPath() {
		return brandImagesPath;
	}
	
	public String getOtherImagesPath() {
		return otherImagesPath;
	}
	
	public String getFooterImagesPath() {
		return footerImagesPath;
	}
	
	public SikuliImageCompareHelper() {
		screen = new Screen();
		//screen.setAutoWaitTimeout(15000);
		getParentPath();
		brandImagesPath = parent_directory + "/ProjectImagesToCompare/BrandPageLogos";
		footerImagesPath = parent_directory + "/ProjectImagesToCompare/FooterLogos";
		otherImagesPath=parent_directory + "/ProjectImagesToCompare/OtherLogos";
		carRentalServiceImagesPath=parent_directory + "/ProjectImagesToCompare/CarRentalLogos";
	}

	// method to take the path of the image and click on the image on the page.
	// used for brand routing
	public boolean validateImageRoute(String path) throws FindFailed {
		System.out.println("in image comparision -> "+path);
		if(screen.exists(path) != null) {
			screen.wait(new Pattern(path)).click();
			return true;
		}else {
			System.out.println("cannot find element");
			return false;
		}			
	}
	
	public boolean validateImageExists(String path) throws FindFailed {
		System.out.println("in image comparision -> "+path);
		if(screen.exists(path) != null) {
			return true;
		}else {
			System.out.println("cannot find element");
			return false;
		}			
	}
	public String getCarRentalServiceImagesPath() {
		return carRentalServiceImagesPath;
	}

	public Map<String, String> getImagesmap(String path) {
		Map<String, String> imageMap = new LinkedHashMap<String, String>();
		File file = new File(path);
		File[] files = file.listFiles();
		for (File f : files) {
			System.out.println(f.getName());
			// System.out.println( f.getAbsolutePath());
			if (!f.getName().isEmpty()) {
				imageMap.put(f.getName(), f.getAbsolutePath());
			}
		}
		return imageMap;
	}

	public String getParentPath() {
		parent_directory = new File(".").getAbsolutePath();
		parent_directory = parent_directory.substring(0, parent_directory.length() - 1);
		return parent_directory;
	}

	public void wheelDown() {
		screen.wheel(Button.WHEEL_DOWN, 1);
	}
}
