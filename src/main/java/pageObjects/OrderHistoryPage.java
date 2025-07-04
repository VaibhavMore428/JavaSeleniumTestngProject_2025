package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderHistoryPage {
	WebDriver driver;
	public OrderHistoryPage(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver,this);
	}
	
	
	@FindBy(xpath = "//td[contains(text(),'You can see all the Orders in ')]/following::label[contains(text(),'|')]") WebElement orderIDs;
	
	public String getOrderID(){
		String orderID = orderIDs.getText().split(" ")[1].trim();
		System.out.println("Order id "+orderID);
		return orderID;		
	}
}
