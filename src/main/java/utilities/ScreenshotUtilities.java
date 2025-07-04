package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtilities {
	private static final String BASE_FOLDER = "./screenshots/";
	private static Map<String, String> testFolders = new ConcurrentHashMap<String, String>();

	public static String takeScreenshot(WebDriver driver, String testCaseName, String screenshotName)
			throws IOException {

		String folderPath = testFolders.computeIfAbsent(testCaseName, k -> {
			String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
			String fullFolderpath = BASE_FOLDER + testCaseName + "_" + timestamp + "/";
			new File(fullFolderpath).mkdir();
			return fullFolderpath;
		});
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String fullScreenshotPath = folderPath + screenshotName + ".png";
		try {
			FileUtils.copyFile(src, new File(fullScreenshotPath));

		} catch (IOException e) {
			e.getMessage();
		}

		return fullScreenshotPath;
	}
}
