package placeOrder;

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
import pageObjects.ClientLoginPage;
import pageObjects.MyCartPage;
import pageObjects.OrderHistoryPage;
import pageObjects.OrdersPage;
import pageObjects.PaymentsPage;
import pageObjects.ProductsPage;
import utilities.BaseClass;
import utilities.ConfigReader;
import utilities.ExcelUtil;
import utilities.Log;
import utilities.ScreenshotUtilities;
import utilities.TestListeners;

@Listeners(TestListeners.class)
public class PlaceOrderAndVerifyByOrderIdTest extends BaseClass {

	@DataProvider(name = "exceltestData")
	public Iterator<Object[]> getExcelMapData() throws IOException {
		String projectDir = System.getProperty("user.dir");
		String filePath = projectDir + "/src/main/resources/testdata.xlsx";
		return ExcelUtil.getTestDataAsMap(filePath, "ProductOrderList").iterator();
	}
	
	@Parameters("browser")
	@BeforeTest
	public void setupMethod(@Optional("chrome") String browser) {
		driver = InitiateWebDriver(browser);
		driver.get(ConfigReader.getProperty("url"));
	}

	@Test(dataProvider = "exceltestData")
	public void placeOrderAndVerifyByOrderIdTest(Map<String, String> testDataMap) throws IOException {
		ClientLoginPage login = new ClientLoginPage(driver);
		ProductsPage product = new ProductsPage(driver);
		MyCartPage cart = new MyCartPage(driver);
		PaymentsPage payments = new PaymentsPage(driver);
		OrderHistoryPage order = new OrderHistoryPage(driver);
		OrdersPage orders = new OrdersPage(driver);
		Log.info("Logged has started");
		login.loginMethod();
		ScreenshotUtilities.takeScreenshot(driver, "placeOrderAndVerifyByOrderIdTest", "LoggedInPage");
		Log.info("Logged in successfully");
		product.selectProduct(testDataMap.get("ProductName"));
		ScreenshotUtilities.takeScreenshot(driver, "placeOrderAndVerifyByOrderIdTest", "ProductPage");
		Log.info(testDataMap.get("ProductName") + " product is added to the cart");
		cart.gotoCartpageMethod();
		ScreenshotUtilities.takeScreenshot(driver, "placeOrderAndVerifyByOrderIdTest", "MyCartPage");
		Log.info("On my Cart page");
		cart.checkoutMethod();
		Log.info("Checkout for product is done");
		payments.selectCountryMethod(testDataMap.get("Country"));
		payments.enterNameOnCardMethod(testDataMap.get("NameOnCard"));
		payments.entercvvMethod(testDataMap.get("CVV"));
		Log.info("Payments details are entered");
		ScreenshotUtilities.takeScreenshot(driver, "placeOrderAndVerifyByOrderIdTest", "PaymentsDetails");
		payments.placeOrderMethod();
		Log.info("Order placed");
		ScreenshotUtilities.takeScreenshot(driver, "placeOrderAndVerifyByOrderIdTest", "OrderPlaced");
		String orderID = order.getOrderID();
		orders.gotoOrderPageMethod();
		Log.info("Order Histoy Page");
		ScreenshotUtilities.takeScreenshot(driver, "placeOrderAndVerifyByOrderIdTest", "OrderHistoyPage");
		Assert.assertTrue(orders.getrequiredOrder(orderID), "Order ID not found in Order History");
	}
	@AfterTest
	public void tearDown() {
		Log.info("Closing browser after text execution");
		driver.quit();
	}
}
