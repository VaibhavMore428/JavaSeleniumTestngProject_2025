package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrdersPage {
	WebDriver driver;

	public OrdersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement ordersPage;
	@FindBy(xpath = "//tbody/tr/th")
	List<WebElement> ordersList;

	public void gotoOrderPageMethod() {
		ordersPage.click();
	}

	public Boolean getrequiredOrder(String order) {
		for (WebElement requiredorder : ordersList) {
			if (requiredorder.getText().trim().equals(order.trim())) {
				return true;
			}
		}
		return false;
	}
}
