package utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtility {
				
		public static void screenshotMethod(WebDriver driver) throws IOException {
			File src	= 	((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			File dest = new File("C:\\Users\\Vaibhav\\Desktop\\SeleniumLearning2025\\screenshot.png");
		    FileUtils.copyFile(src, dest);
	        System.out.println("Screenshot saved at: " + dest.getAbsolutePath());
		}
}
 