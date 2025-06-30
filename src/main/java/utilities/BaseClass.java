package utilities;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public WebDriver driver;
	  long start = System.currentTimeMillis();
	public WebDriver InitiateWebDriver(String browserName) {

		if (browserName.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("profile.password_manager_leak_detection", false);

			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
		    options.addArguments("--disable-extensions");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--remote-allow-origins=*");
            // Uncomment below line for Jenkins/CI
            // options.addArguments("--headless=new");
			driver = new ChromeDriver(options);
		} else if (browserName.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--disable-gpu");
            options.addArguments("--disable-dev-shm-usage");
            options.addPreference("app.update.enabled", false);
            options.addPreference("toolkit.telemetry.reportingpolicy.firstRun", false);
            // Uncomment below line for Jenkins/CI
            // options.addArguments("--headless");

            driver = new FirefoxDriver(options);
		} else if (browserName.equalsIgnoreCase("safari")) {
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
		} else {
            throw new IllegalArgumentException("Unsupported browser: " + browserName);
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
