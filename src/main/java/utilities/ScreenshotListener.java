package utilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ScreenshotListener implements ITestListener {

	@Override
	public void onTestFailure(ITestResult result) {
		Object testClass = result.getInstance();
		WebDriver driver = ((BaseClass) testClass).getDriver(); // Replace with your actual base class
		String testCaseName = result.getName(); // test method name
		try {
			ScreenshotUtilities.takeScreenshot(driver, testCaseName, "Failure");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
