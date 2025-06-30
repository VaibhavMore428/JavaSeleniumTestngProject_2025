package pageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {
	WebDriver driver;

	public ProductsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='card-body']/h5/b")
	List<WebElement> productList;
	@FindBy(xpath = "//button[text()=' Add To Cart']")
	WebElement addToCartBtn;

	public void selectProduct(String productToBeSelected) {

	/* div[@class='card-body']/h5/b[text()='ADIDASORIGINAL']/ancestor::div[@class='card-body']//button[contains(text(), 'AddTo Cart')]*/

	for (WebElement prod : productList) {
		if (prod.getText().trim().equalsIgnoreCase(productToBeSelected.trim())) {
			// Go up from <b> → <h5> → <div class="card-body"> → find the Add To Cart button
			String parentElementxpath = "./ancestor::div[@class='card-body']";
			WebElement cardBody = prod.findElement(By.xpath(parentElementxpath));
			WebElement addToCartButton = cardBody.findElement(By.xpath(".//button[contains(text(),'Add To Cart')]"));
			addToCartButton.click();
			break;
		}
	}
}

public void addtoCartMethod() {
	addToCartBtn.click();
}
}