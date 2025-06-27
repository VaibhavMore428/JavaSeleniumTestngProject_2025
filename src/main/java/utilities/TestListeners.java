package utilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import utilities.Log;

public class TestListeners implements ITestListener {

	private static ExtentReports extent = ExtentReportManager.getExtentReports();
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<>();

	public void onStart(ITestContext context) {
		String timestamp = new java.text.SimpleDateFormat("yyyy_MM_dd_HH-mm-ss").format(new java.util.Date());
		System.setProperty("logFilename", "test-log-" + timestamp + ".log");

		extent = ExtentReportManager.getExtentReports(); // create report once
		System.out.println("Test suite started: " + context.getName());
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ExtentTest test = extent.createTest(result.getMethod().getMethodName());
		testReport.set(test);
		Log.info("=== Test Started: " + result.getMethod().getMethodName() + " ===");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		testReport.get().log(Status.PASS, "Test Passed");
		Log.info("=== Test Passed: " + result.getMethod().getMethodName() + " ===");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver = null;
		Object testInstance = result.getInstance();

		if (testInstance instanceof BaseClass) {
			driver = ((BaseClass) testInstance).getDriver();
		} else {
			Log.warn("Test class does not extend BaseClass, unable to capture WebDriver for screenshot.");
		}

		String methodName = result.getMethod().getMethodName();
		String screenshotPath = "";
		try {
			if (driver != null) {
				screenshotPath = ScreenshotUtilities.takeScreenshot(driver, methodName, "TestFailedScreen");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		Throwable throwable = result.getThrowable();
		String errorMsg = "Test failed: " + throwable.getMessage();
		Log.error("TEST FAILED: " + methodName);
		Log.error(errorMsg);
		Log.error("Stacktrace: ", throwable); // logs full trace to file
		ExtentTest test = testReport.get();
		test.fail(errorMsg);

		if (!screenshotPath.isEmpty()) {
			test.addScreenCaptureFromPath(screenshotPath, "Screenshot on Failure");
		}

		test.log(Status.FAIL, throwable);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		testReport.get().log(Status.SKIP, "Test Skipped: " + result.getThrowable());
		Log.warn("Test skipped: " + result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
		System.out.println("Extent Report initialized");
		System.out.println("Flushing report...");
		utilities.Log.info("=== Test Suite Finished ===");
	}

}
