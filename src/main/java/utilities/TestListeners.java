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

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ExtentTest test = extent.createTest(result.getMethod().getMethodName());
		testReport.set(test);
		Log.info("=== Test Started: " + result.getMethod().getMethodName() + " ===");
	}

	public void onStart(ITestContext context) {
		extent = ExtentReportManager.getExtentReports(); // create report once
		System.out.println("Test suite started: " + context.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		testReport.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		WebDriver driver = ((BaseClass) result.getInstance()).getDriver(); // Adjust based on your base class
		String methodName = result.getMethod().getMethodName();
		String screenshotPath = "";
		try {
			screenshotPath = ScreenshotUtilities.takeScreenshot(driver, methodName, "TestFailedScreen");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExtentTest test = testReport.get();
		test.fail("❌ Test failed: " + result.getThrowable());
		test.addScreenCaptureFromPath(screenshotPath, "Screenshot on Failure");
		testReport.get().log(Status.FAIL, "Test Failed" + result.getThrowable());
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
