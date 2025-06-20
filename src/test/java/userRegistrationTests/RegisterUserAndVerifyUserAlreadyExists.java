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
import pageObjects.RegisterUserPage;
import utilities.ConfigReader;
import utilities.ExcelUtil;

public class RegisterUserAndVerifyUserAlreadyExists extends BaseClass {
	WebDriver driver;

	@DataProvider(name = "exceltestData")
	public Iterator<Object[]> getExcelMapData() throws IOException {

		String projectDir = System.getProperty("user.dir");
		String filePath = projectDir + "/src/main/resources/testdata.xlsx";
		return ExcelUtil.getTestDataAsMap(filePath, "Register").iterator();

	}

	@BeforeMethod
	public void setupMethod() {
		driver = InitiateWebDriver("chrome");
		driver.get(ConfigReader.getProperty("url"));
	}

	@Test(dataProvider = "exceltestData")
	public void VerifyUserALreadyExists(Map<String, String> testDataMap) throws InterruptedException {
		RegisterUserPage rgstrUser = new RegisterUserPage(driver);
		rgstrUser.gotoRegisterBtn();
		CommonFunctions.registerUser(driver, testDataMap);
		rgstrUser.RegisterUserbtnClick();
		// anjali.sharma@example.com
		// rohitv@example.com
		// vaibhav@example.com
		//
		Assert.assertEquals(rgstrUser.verfiyUserAlreadyRegistered(), "User already exisits with this Email Id!");
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
