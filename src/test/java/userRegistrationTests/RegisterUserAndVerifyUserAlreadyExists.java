package userRegistrationTests;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utilities.BaseClass;
import commonFunctions.CommonFunctions;
import pageObjects.RegisterUserPage;
import utilities.ConfigReader;
import utilities.ExcelUtil;
import utilities.Log;
import utilities.ScreenshotUtilities;
import utilities.TestListeners;

@Listeners( TestListeners.class )
public class RegisterUserAndVerifyUserAlreadyExists extends BaseClass {

	@DataProvider(name = "exceltestData")
	public Iterator<Object[]> getExcelMapData() throws IOException {

		String projectDir = System.getProperty("user.dir");
		String filePath = projectDir + "/src/main/resources/testdata.xlsx";
		return ExcelUtil.getTestDataAsMap(filePath, "Register").iterator();

	}
	@Parameters("browser")
	@BeforeTest
	public void setupMethod(@Optional("chrome")String browser) {
		driver = InitiateWebDriver(browser);
		driver.get(ConfigReader.getProperty("url"));
	}

	@Test(dataProvider = "exceltestData")
	public void VerifyUserALreadyExists(Map<String, String> testDataMap) throws InterruptedException, IOException {
		RegisterUserPage rgstrUser = new RegisterUserPage(driver);
		Log.info("===== Starting test: VerifyUserALreadyExists =====");
		Log.info("Navigating to Register page");
		ScreenshotUtilities.takeScreenshot(driver, "VerifyUserALreadyExists", "BeforeRegisterPage");
		rgstrUser.gotoRegisterBtn();
		Log.info("Filling registration form with data: " + testDataMap.toString());
		CommonFunctions.registerUser(driver, testDataMap, "RegisterUserAndVerifyUserAlreadyExists");
		ScreenshotUtilities.takeScreenshot(driver, "VerifyUserALreadyExists", "UserDetailsOnRegisterPage");
		Log.info("Submitting registration form");
		rgstrUser.RegisterUserbtnClick();

		Assert.assertEquals(rgstrUser.verfiyUserAlreadyRegistered(), "User already exisits with this Email Id!");
		ScreenshotUtilities.takeScreenshot(driver, "VerifyUserALreadyExists", "AlreadyExistMessage");
		Log.info("Verifying User already exists or not");
	}

	@AfterTest
	public void tearDown() {
		Log.info("Closing browser after text execution");
		driver.quit();
	}
}
