package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavigationbarPage {

	WebDriver driver;

	public NavigationbarPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Xpath

	@FindBy(xpath = "//button[contains(@routerlink,'/dashboard/cart')]")
	WebElement cartPage;
	@FindBy(xpath = "//button[contains(@routerlink,'/dashboard/myorders')]")
	WebElement myordersPage;
	@FindBy(xpath = "//button[@routerlink='/dashboard/']")
	WebElement homePage;
	@FindBy(xpath = "//button[text()=' Sign Out ']")
	WebElement signOutBtn;

	public void cartpageMethod() {
		cartPage.click();
	}

	public void myordersPageMethod() {
		myordersPage.click();
	}

	public void homePageMethod() {
		homePage.click();
	}

	public void signOutBtnMethod() {
		signOutBtn.click();
	}

}
