package pageObjects;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterUserPage {
	WebDriver driver;

	public RegisterUserPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[text()='Register']")
	WebElement registerBtn;
	@FindBy(xpath = "//input[@id='firstName']")
	WebElement firstName;
	@FindBy(xpath = "//input[@id='lastName']")
	WebElement lastName;
	@FindBy(xpath = "//input[@id='userEmail']")
	WebElement userEmail;
	@FindBy(xpath = "//input[@id='userMobile']")
	WebElement userMobile;
	@FindBy(xpath = "//select")
	WebElement occupationDropdown;
	@FindBy(xpath = "//input[@type='radio']")
	List<WebElement> gender;
	@FindBy(xpath = "//input[@id='userPassword']")
	WebElement password;
	@FindBy(xpath = "//input[@id='confirmPassword']")
	WebElement confirmPassword;
	@FindBy(xpath = "//input[@type='checkbox']")
	WebElement checkBox18Year;
	@FindBy(xpath = "//input[@id='login']")
	WebElement registerUser;
	@FindBy(css ="#toast-container .toast-message") WebElement errorMessage;
	
	public void gotoRegisterBtn() {
		registerBtn.click();
	}

	public void enterFirstname(String fname) {
		firstName.sendKeys(fname);
	}

	public void enterlastName(String lname) {
		lastName.sendKeys(lname);
	}

	public void enteruserEmail(String email) {
		userEmail.sendKeys(email);
	}

	public void enterMobilenumber(String mNumber) {
		userMobile.sendKeys(mNumber);
	}

	public void selectOccupation(String occupation) {
		Select select = new Select(occupationDropdown);
		select.selectByVisibleText(occupation);
	}

	public void selectGender(String genderr) {
		for (int i = 0; i < gender.size(); i++) {
			if (gender.get(i).getAttribute("value").equalsIgnoreCase(genderr)) {
				System.out.println("true");
				gender.get(i).click();
				break;
			}
		}
	}

	public void enterpassword(String psswrod) {
		password.sendKeys(psswrod);
	}

	public void enterConfirmPassword(String cPsswrod) {
		confirmPassword.sendKeys(cPsswrod);
	}

	public void selectCheckBox18Year() {
		checkBox18Year.click();
	}

	public void RegisterUserbtnClick() {
		registerUser.click();
	}
	
	public String verfiyUserAlreadyRegistered() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		// Wait for the toast message to be present in DOM
		WebElement toastMessage = wait.until(ExpectedConditions.presenceOfElementLocated(
		    By.cssSelector("#toast-container .toast-message")
		));

		// Wait for it to become visible (optional but safer)
		wait.until(ExpectedConditions.visibilityOf(toastMessage));

		String actualMessage = toastMessage.getText(); 
		return actualMessage;
	}
}
