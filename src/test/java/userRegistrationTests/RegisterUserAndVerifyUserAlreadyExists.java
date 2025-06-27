package userRegistrationTests;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import commonFunctions.CommonFunctions;
import pageObjects.RegisterUserPage;
import utilities.ConfigReader;
import utilities.ExcelUtil;
import utilities.ScreenshotListener;
import utilities.ScreenshotUtilities;
import utilities.TestListeners;

@Listeners({ScreenshotListener.class,TestListeners.class})
public class RegisterUserAndVerifyUserAlreadyExists extends BaseClass {

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
	public void VerifyUserALreadyExists(Map<String, String> testDataMap) throws InterruptedException, IOException {
		RegisterUserPage rgstrUser = new RegisterUserPage(driver);
		ScreenshotUtilities.takeScreenshot(driver, "VerifyUserALreadyExists", "BeforeRegisterPage");
		rgstrUser.gotoRegisterBtn();
		CommonFunctions.registerUser(driver, testDataMap, "RegisterUserAndVerifyUserAlreadyExists");
		ScreenshotUtilities.takeScreenshot(driver, "VerifyUserALreadyExists", "UserDetailsOnRegisterPage");
		rgstrUser.RegisterUserbtnClick();
		// anjali.sharma@example.com
		// rohitv@example.com
		// vaibhav@example.com
		//
		ScreenshotUtilities.takeScreenshot(driver, "VerifyUserALreadyExists", "AlreadyExistMessage");
		Thread.sleep(1000);
		Assert.assertEquals(rgstrUser.verfiyUserAlreadyRegistered(), "User already exisits with this Email Id!");
		
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
