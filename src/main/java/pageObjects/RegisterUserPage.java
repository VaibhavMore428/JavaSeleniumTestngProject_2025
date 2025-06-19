package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RegisterUserPage {
	WebDriver driver;
	public RegisterUserPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(xpath="//a[text()='Register']") WebElement registerBtn;
	@FindBy(xpath="//input[@id='firstName']") WebElement firstName;
	@FindBy(xpath="//input[@id='lastName']") WebElement lastName;
	@FindBy(xpath="//input[@id='userEmail']") WebElement userEmail;
	@FindBy(xpath="//input[@id='userMobile']") WebElement userMobile;
	@FindBy(xpath="//select") WebElement occupationDropdown;
	@FindBy(xpath="//input[@type='radio']") List<WebElement> gender;
	@FindBy(xpath = "//input[@id='userPassword']") WebElement password;
	@FindBy(xpath = "//input[@id='confirmPassword']") WebElement confirmPassword;
	@FindBy(xpath="//input[@type='checkbox']") WebElement checkBox18Year;
	@FindBy(xpath="//input[@id='login']") WebElement registerUser;
	
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
	
	public void SelectOccupation(String occupation) {
		Select select = new Select(occupationDropdown);
		select.deselectByVisibleText(occupation);
	}
	
	public void selectGender(String genderr) {
		for(int i=0; i<gender.size();i++) {
			if(gender.get(i).getText() == genderr) {
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
	
	public void selectCheckBox18Year(){
		checkBox18Year.click();
	}
	
	public void RegisterUserbtnClick() {
		registerUser.click();
	}
	
	
}
