package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

	private static ExtentReports extent;

	public static ExtentReports getExtentReports() {
		if (extent == null) {

			String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH-mm-ss").format(new Date());
			String reportPath = "reports/ExtentReport_" + timeStamp + ".html";
			ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
			sparkReporter.config().setReportName("Automation Test Report");
			sparkReporter.config().setDocumentTitle("Test Results");

			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);
			extent.setSystemInfo("Tester", "Vaibhav More");
			extent.setSystemInfo("Env", "QA");
		}
		return extent;
	}
}
