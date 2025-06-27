package userRegistrationTests;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import commonFunctions.CommonFunctions;
import pageObjects.ClientLoginPage;
import pageObjects.DashboardPage;
import pageObjects.RegisterUserPage;
import utilities.ConfigReader;
import utilities.ExcelUtil;
import utilities.ScreenshotListener;
import utilities.ScreenshotUtilities;
import utilities.TestListeners;
import utilities.Log;

@Listeners({ ScreenshotListener.class, TestListeners.class })

public class RegisterNewUserAndVerifyNewUserAbleLogin extends BaseClass {

	@DataProvider(name = "exceltestData")
	public Iterator<Object[]> getExcelMapData() throws IOException {

		String projectDir = System.getProperty("user.dir");
		String filePath = projectDir + "/src/main/resources/testdata.xlsx";
		return ExcelUtil.getTestDataAsMap(filePath, "NewUserRegister").iterator();
	}

	@BeforeMethod
	public void setupMethod() {
		  Log.info("Initializing Chrome browser and navigating to URL");
		driver = InitiateWebDriver("chrome");
		driver.get(ConfigReader.getProperty("url"));
	}

	@Test(dataProvider = "exceltestData")
	public void RegisterAndVerifyNewUser(Map<String, String> testDataMap) throws InterruptedException, IOException {
		RegisterUserPage rgstrUser = new RegisterUserPage(driver);
		ClientLoginPage loginTest = new ClientLoginPage(driver);
		DashboardPage dashboard = new DashboardPage(driver);

		Log.info("===== Starting test: RegisterAndVerifyNewUser =====");
		Log.info("Navigating to Register page");

		ScreenshotUtilities.takeScreenshot(driver, "RegisterAndVerifyNewUser", "BeforeRegisterPage");
		rgstrUser.gotoRegisterBtn();
		Log.info("Filling registration form with data: " + testDataMap.toString());
		CommonFunctions.registerUser(driver, testDataMap);
		ScreenshotUtilities.takeScreenshot(driver, "RegisterAndVerifyNewUser", "UserEnteredDetailsOnRegisterPage");
		Log.info("Submitting registration form");
		rgstrUser.RegisterUserbtnClick();
		Log.info("Navigating to Login page");
		// Now verify newly added user is able to login
		ScreenshotUtilities.takeScreenshot(driver, "RegisterAndVerifyNewUser", "BeforeLoginPage");
		loginTest.clickOnLoginBtn();

		Log.info("Entering login credentials: " + testDataMap.get("Email"));
		loginTest.enterUsename(testDataMap.get("Email"));
		loginTest.enterPassword(testDataMap.get("Password"));
		ScreenshotUtilities.takeScreenshot(driver, "RegisterAndVerifyNewUser", "LoginPageWithUserdetails");
		Log.info("Clicking on Submit button");
		loginTest.clickOnSubmitBtn();
		Log.info("Verifying homepage text");
		Assert.assertEquals(dashboard.getHomePageText(), "Automation Practice",
				"Test failed=>Home page title is incorrect");
		ScreenshotUtilities.takeScreenshot(driver, "RegisterAndVerifyNewUser", "OnHomePage");
		Log.info("Test completed: User registration and login successful");
	}

	@AfterTest
	public void tearDown() {
	    Log.info("Closing browser after test execution");
		driver.quit();
	}
}
