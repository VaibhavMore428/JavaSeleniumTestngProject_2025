package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentTest;

public class Log {
	private static final Logger logger = LogManager.getLogger(Log.class);

	private static ExtentTest getExtentTest() {
		return TestListeners.testReport.get();
	}

	public static void info(String message) {
		logger.info(message);
		ExtentTest extentTest = getExtentTest();
		if (extentTest != null) {
			extentTest.info(message);
		}
	}

	public static void warn(String message) {
		logger.warn(message);
		ExtentTest extentTest = getExtentTest();
		if (extentTest != null) {
			extentTest.warning(message);
		}
	}

	public static void error(String message,Throwable  throwable) {
		logger.error(message,throwable);
		ExtentTest extentTest = getExtentTest();
		if (extentTest != null) {
			extentTest.fail(message + "<br><pre>" + throwable.toString() + "</pre>");
		}
	}
	public static void error(String message) {
	    logger.error(message);
	    ExtentTest extentTest = getExtentTest();
	    if (extentTest != null) {
	        extentTest.fail(message);
	    }
	}

	public static void debug(String message) {
		logger.debug(message);
		ExtentTest extentTest = getExtentTest();
		if (extentTest != null) {
			extentTest.info("[DEBUG] " + message);
		}
	}
}
