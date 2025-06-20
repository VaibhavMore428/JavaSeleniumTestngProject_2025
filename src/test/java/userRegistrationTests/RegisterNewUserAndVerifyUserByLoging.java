package userRegistrationTests;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import pageObjects.RegisterUserPage;
import utilities.ConfigReader;
import utilities.ExcelUtil;

public class RegisterNewUserAndVerifyUserByLoging extends BaseClass {
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
	public void registerAndVerifyNewUser(Map<String, String> testDataMap) {
		RegisterUserPage rgstrUser = new RegisterUserPage(driver);
		rgstrUser.gotoRegisterBtn();
		// filling the form

		rgstrUser.enterFirstname(testDataMap.get("FirstName"));
	}
}
 