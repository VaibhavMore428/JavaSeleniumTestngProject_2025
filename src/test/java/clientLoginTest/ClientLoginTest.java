package clientLoginTest;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.ClientLoginPage;
import pageObjects.DashboardPage;
import utilities.BaseClass;
import utilities.ConfigReader;
import utilities.ScreenshotUtilities;

public class ClientLoginTest extends BaseClass {

	@BeforeMethod
	public void setupMethod() {
		driver = InitiateWebDriver("chrome");
		driver.get(ConfigReader.getProperty("url"));
	}

	@Test
	public void loginTest() throws IOException {
		ClientLoginPage loginobj = new ClientLoginPage(driver);
		DashboardPage dashboardObj = new DashboardPage(driver);

		loginobj.enterUsename(ConfigReader.getProperty("username"));
		loginobj.enterPassword(ConfigReader.getProperty("password"));
		ScreenshotUtilities.takeScreenshot(driver, "loginTest", "LoginPage");
		loginobj.clickOnSubmitBtn();
		String homepagetext = dashboardObj.getHomePageText();
		ScreenshotUtilities.takeScreenshot(driver, "loginTest", "HomePage");
		Assert.assertEquals(homepagetext, ConfigReader.getProperty("homePageText"), "TestFailed=>Login error");
		System.out.println("login test");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
