package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyCartPage {
	
		WebDriver driver;
		public MyCartPage(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
		
		@FindBy(xpath="//button[contains(text(),'Checkout')]") WebElement checkoutBtn;
		@FindBy(xpath="//button[contains(text(),'Continue Shopping')]") WebElement continueToShoppingBtn;
		@FindBy(xpath= "//ul[contains(@class,'cartWrap')]//h3") List<WebElement> cartProductNames;
		@FindBy(xpath = "//button[@routerlink='/dashboard/cart']") WebElement cartPage;
		By loader = By.xpath("//div[contains(@class, 'ngx-spinner-overlay')]");
		
		
		public void checkoutMethod() {
			checkoutBtn.click();
		}
		
		public void continueToShoppingBtnMethod() {
			continueToShoppingBtn.click();
		}
		
		public void gotoCartpageMethod() {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
			//wait.until(ExpectedConditions.elementToBeClickable(cartPage));
			cartPage.click();
		}
}
