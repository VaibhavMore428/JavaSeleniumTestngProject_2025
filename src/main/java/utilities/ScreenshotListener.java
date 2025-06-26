package utilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ScreenshotListener implements ITestListener {

	@Override
	public void onTestFailure(ITestResult result) {
		Object testClass = result.getInstance();
		System.out.println("Test failed for: " + result.getName());
		System.out.println("testClass actual type: " + testClass.getClass().getName());

		if (testClass instanceof baseClass.BaseClass) {
			WebDriver driver = ((baseClass.BaseClass) testClass).getDriver();
			if (driver != null) {
				try {
					ScreenshotUtilities.takeScreenshot(driver, result.getName(), "TestFailureScreenShot");
					System.out.println("Screenshot captured for: " + result.getName());
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("WebDriver is null. Screenshot not taken.");
			}
		} else {
			System.out.println("testClass is NOT an instance of BaseClass.");
		}
	}
}