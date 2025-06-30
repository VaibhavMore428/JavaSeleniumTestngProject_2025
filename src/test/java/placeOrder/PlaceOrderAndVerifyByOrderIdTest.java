package placeOrder;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageObjects.ClientLoginPage;
import pageObjects.MyCartPage;
import pageObjects.PaymentsPage;
import pageObjects.ProductsPage;
import utilities.BaseClass;
import utilities.ConfigReader;
import utilities.ExcelUtil;
import utilities.Log;
import utilities.ScreenshotListener;
import utilities.ScreenshotUtilities;
import utilities.TestListeners;



@Listeners({ ScreenshotListener.class, TestListeners.class })

public class PlaceOrderAndVerifyByOrderIdTest extends BaseClass {

	@DataProvider(name = "exceltestData")
	public Iterator<Object[]> getExcelMapData() throws IOException {
		String projectDir = System.getProperty("user.dir");
		String filePath = projectDir + "/src/main/resources/testdata.xlsx";
		return ExcelUtil.getTestDataAsMap(filePath, "ProductOrderList").iterator();
	}

	@BeforeMethod
	public void setupMethod() {
		driver = InitiateWebDriver("Firefox");
		driver.get(ConfigReader.getProperty("url"));
	}

	@Test(dataProvider = "exceltestData")
	public void placeOrderAndVerifyByOrderIdTest(Map<String, String> testDataMap) throws IOException {
		ClientLoginPage login = new ClientLoginPage(driver);
		ProductsPage product = new ProductsPage(driver);
		MyCartPage cart = new MyCartPage(driver);
		PaymentsPage payments = new PaymentsPage(driver);
		Log.info("Logged has started");
		ScreenshotUtilities.takeScreenshot(driver,"placeOrderAndVerifyByOrderIdTest","Log in Page");
		login.loginMethod();
		ScreenshotUtilities.takeScreenshot(driver,"placeOrderAndVerifyByOrderIdTest","LoggedInSuccessfully");
		Log.info("Logged in successfully");
		product.selectProduct(testDataMap.get("ProductName"));
		ScreenshotUtilities.takeScreenshot(driver,"placeOrderAndVerifyByOrderIdTest","ProductSelected");
		Log.info(testDataMap.get("ProductName")+" product is added to the cart");
		cart.gotoCartpageMethod();
		ScreenshotUtilities.takeScreenshot(driver,"placeOrderAndVerifyByOrderIdTest","MyCartPage");
		Log.info("On my Cart page");
		cart.checkoutMethod();
		Log.info("Checkout for product is done");
		payments.selectCountryMethod(testDataMap.get("Country"));
		payments.enterNameOnCardMethod(testDataMap.get("NameOnCard"));
		payments.entercvvMethod(testDataMap.get("CVV"));
		Log.info("Payments details are entered");
		ScreenshotUtilities.takeScreenshot(driver,"placeOrderAndVerifyByOrderIdTest","PaymentsDetails");
		payments.placeOrderMethod();
		Log.info("Order placed");
		ScreenshotUtilities.takeScreenshot(driver,"placeOrderAndVerifyByOrderIdTest","OrderPlaced");
	}
}
