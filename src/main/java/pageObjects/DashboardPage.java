package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.WaitUtilities;

public class DashboardPage {
	WebDriver driver;
	
	public DashboardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//p[text()='Automation Practice']")
	WebElement homePageText;

	public String getHomePageText() {
		WaitUtilities waitUntil = new WaitUtilities();
		waitUntil.waitUntilElementToBeVisible(driver, homePageText);
		return homePageText.getText();
	}

}
