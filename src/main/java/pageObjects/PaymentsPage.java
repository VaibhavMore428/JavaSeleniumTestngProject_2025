package pageObjects;

import java.util.List;

import org.bouncycastle.its.ITSValidityPeriod.Unit;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.WaitUtilities;

public class PaymentsPage {
	WebDriver driver;

	public PaymentsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement countryInputBox;
	@FindBy(xpath = "//section[contains(@class,'ta-results')]//button")
	List<WebElement> countryList;
	@FindBy(xpath = "//a[contains(text(),'Place Order ')]")
	WebElement placeOrderBtn;
	@FindBy(xpath = "(//div[@class='field']/parent::div//input)[2]")
	WebElement nameOnCard;
	@FindBy(xpath = "(//div[@class='title']/parent::div//input)[2]")
	WebElement cvv;

	public void selectCountryMethod(String countryName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		countryInputBox.clear();
		countryInputBox.sendKeys(countryName);
		wait.until(ExpectedConditions.visibilityOfAllElements(countryList));
		for (WebElement country : countryList) {
			String matchedCountry = country.getText().trim();
			if (matchedCountry.equals(countryName)) {
				System.out.println("country.getText()= " + country.getText());
				wait.until(ExpectedConditions.elementToBeClickable(country));
				country.click();
				System.out.println("insdieee");
				break;
			}
		}
	}

	public void placeOrderMethod() {
		placeOrderBtn.click();
	}

	public void enterNameOnCardMethod(String name) {
		nameOnCard.sendKeys(name);
	}

	public void entercvvMethod(String Cardcvv) {
		cvv.sendKeys(Cardcvv);
	}
}
