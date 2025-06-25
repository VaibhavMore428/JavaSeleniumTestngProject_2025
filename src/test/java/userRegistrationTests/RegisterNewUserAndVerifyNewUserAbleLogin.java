package userRegistrationTests;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import commonFunctions.CommonFunctions;
import pageObjects.ClientLoginPage;
import pageObjects.DashboardPage;
import pageObjects.RegisterUserPage;
import utilities.ConfigReader;
import utilities.ExcelUtil;
import utilities.ScreenshotUtilities;

public class RegisterNewUserAndVerifyNewUserAbleLogin extends BaseClass {
	WebDriver driver;

	@DataProvider(name = "exceltestData")
	public Iterator<Object[]> getExcelMapData() throws IOException {

		String projectDir = System.getProperty("user.dir");
		String filePath = projectDir + "/src/main/resources/testdata.xlsx";
		return ExcelUtil.getTestDataAsMap(filePath, "NewUserRegister").iterator();
	}

	@BeforeMethod
	public void setupMethod() {
		driver = InitiateWebDriver("chrome");
		driver.get(ConfigReader.getProperty("url"));
	}

	@Test(dataProvider = "exceltestData")
	public void RegisterAndVerifyNewUser(Map<String, String> testDataMap) throws InterruptedException, IOException {
		RegisterUserPage rgstrUser = new RegisterUserPage(driver);
		ClientLoginPage loginTest = new ClientLoginPage(driver);
		DashboardPage dashboard = new DashboardPage(driver);
		
		ScreenshotUtilities.takeScreenshot(driver, "RegisterAndVerifyNewUser", "BeforeRegisterPage");
		rgstrUser.gotoRegisterBtn();
		CommonFunctions.registerUser(driver, testDataMap);
		ScreenshotUtilities.takeScreenshot(driver, "RegisterAndVerifyNewUser", "UserEnteredDetailsOnRegisterPage");
		rgstrUser.RegisterUserbtnClick();

		// Now verify newly added user is able to login
		ScreenshotUtilities.takeScreenshot(driver, "RegisterAndVerifyNewUser", "BeforeLoginPage");
		loginTest.clickOnLoginBtn();

		loginTest.enterUsename(testDataMap.get("Email"));
		loginTest.enterPassword(testDataMap.get("Password"));
		ScreenshotUtilities.takeScreenshot(driver, "RegisterAndVerifyNewUser", "LoginPageWithUserdetails");
		loginTest.clickOnSubmitBtn();

		Assert.assertEquals(dashboard.getHomePageText(), "Automation Practice", "Test failed=>Home page title is incorrect");
		ScreenshotUtilities.takeScreenshot(driver, "RegisterAndVerifyNewUser", "OnHomePage");
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
