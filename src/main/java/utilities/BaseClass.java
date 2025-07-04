package utilities;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public WebDriver driver;
	long start = System.currentTimeMillis();

	public WebDriver InitiateWebDriver(String browserName) {
		
		if (browserName == null || browserName.trim().isEmpty()) {
			browserName = "chrome";
		}

		switch (browserName.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("profile.password_manager_leak_detection", false);

			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setExperimentalOption("prefs", prefs);
			chromeOptions.addArguments("--disable-extensions");
			chromeOptions.addArguments("--disable-gpu");
			chromeOptions.addArguments("--no-sandbox");
			chromeOptions.addArguments("--disable-dev-shm-usage");
			chromeOptions.addArguments("--remote-allow-origins=*");
			// Uncomment below line for Jenkins/CI
			// options.addArguments("--headless=new");
			chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
			driver = new ChromeDriver(chromeOptions);
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addArguments("--disable-gpu");
			firefoxOptions.addArguments("--disable-dev-shm-usage");
			firefoxOptions.addPreference("app.update.enabled", false);
			firefoxOptions.addPreference("toolkit.telemetry.reportingpolicy.firstRun", false);
			// Uncomment below line for Jenkins/CI
			// options.addArguments("--headless");
			firefoxOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
			driver = new FirefoxDriver(firefoxOptions);
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		default:
			throw new IllegalArgumentException("Unsupported Browser" + browserName);
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		long end = System.currentTimeMillis();
		System.out.println("🚀 Browser launch time (" + browserName + "): " + (end - start) + " ms");
		return driver;
	}

	public WebDriver getDriver() {
		return driver;
	}
}
