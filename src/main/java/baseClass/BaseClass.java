package baseClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {
					public static WebDriver browserStartMethod() {
						System.setProperty("webdriver.chrome.driver","C:\\chromedriver\\chromedriver-win64\\chromedriver.exe");
					//	System.setProperty("WEBDRIVER.CHROME.DRIVER", "C:\\chromedriver\\chromedriver-win32\\chromedriver.exe");
						WebDriver driver = new ChromeDriver();
						driver.manage().window().maximize();
						return driver;
					}
}
