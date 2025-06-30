package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.ConfigReader;
import utilities.WaitUtilities;

public class ClientLoginPage {
	WebDriver driver;

	public ClientLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[@routerlink='/auth']")
	WebElement loginBtn;

	@FindBy(xpath = "//input[@id='userEmail']")
	WebElement usename;

	@FindBy(xpath = "//input[@id='userPassword']")
	WebElement password;

	@FindBy(xpath = "//input[@id='login']")
	WebElement submitbtn;

	public void enterUsename(String username) {
		usename.sendKeys(username);
	}

	public void enterPassword(String pass) {
		password.sendKeys(pass);
	}

	public void clickOnSubmitBtn() {
		submitbtn.click();
	}

	public void clickOnLoginBtn() {
		WaitUtilities wu = new WaitUtilities();
		wu.waitUntilElementToBeVisible(this.driver, loginBtn);
		loginBtn.click();
	}

	public void loginMethod() {
		usename.sendKeys(ConfigReader.getProperty("username"));
		password.sendKeys(ConfigReader.getProperty("password"));
		submitbtn.click();
	}
}
