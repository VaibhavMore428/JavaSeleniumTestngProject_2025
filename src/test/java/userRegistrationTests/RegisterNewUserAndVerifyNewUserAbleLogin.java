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
import clientLoginTest.ClientLoginTest;
import commonFunctions.CommonFunctions;
import pageObjects.ClientLoginPage;
import pageObjects.DashboardPage;
import pageObjects.RegisterUserPage;
import utilities.ConfigReader;
import utilities.ExcelUtil;

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
	public void RegisterAndVerifyNewUser(Map<String, String> testDataMap) throws InterruptedException {
		RegisterUserPage rgstrUser = new RegisterUserPage(driver);
		ClientLoginPage loginTest = new ClientLoginPage(driver);
		DashboardPage dashboard = new DashboardPage(driver);

		rgstrUser.gotoRegisterBtn();
		CommonFunctions.registerUser(driver, testDataMap);
		rgstrUser.RegisterUserbtnClick();

		// Now verify newly added user is able to login
		loginTest.clickOnLoginBtn();

		loginTest.enterUsename(testDataMap.get("Email"));
		loginTest.enterPassword(testDataMap.get("Password"));
		loginTest.clickOnSubmitBtn();

		Assert.assertEquals(dashboard.getHomePageText(), "Automation Practice", "Test failed");
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
