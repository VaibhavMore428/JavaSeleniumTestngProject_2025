package clientLoginTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.ClientLoginPage;
import utilities.BaseClass;
import utilities.ConfigReader;
import utilities.ScreenshotUtilities;

public class ClientLoginTest extends BaseClass  {
	
	WebDriver driver;
	@BeforeMethod
	public void setupMethod(){
		driver = InitiateWebDriver("chrome");
		driver.get(ConfigReader.getProperty("url"));
		driver.manage().window().maximize();
	}
	
	@Test
	public void loginTest() throws IOException {
		ClientLoginPage loginobj = new ClientLoginPage(driver);
		
		loginobj.enterUsename(ConfigReader.getProperty("username"));
		loginobj.enterPassword(ConfigReader.getProperty("password"));
		ScreenshotUtilities.takeScreenshot(driver,"loginTest","BeforeSubmit");
		loginobj.clickOnSubmitBtn();
		System.out.println("login test");

	}
}
