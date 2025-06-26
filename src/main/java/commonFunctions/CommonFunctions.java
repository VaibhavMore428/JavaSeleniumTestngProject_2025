package commonFunctions;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import pageObjects.RegisterUserPage;

public class CommonFunctions {
			public static void registerUser(WebDriver driver, Map<String, String> testDataMap) {
				// TODO Auto-generated method stub
				RegisterUserPage rgstrUser = new RegisterUserPage(driver);
				rgstrUser.enterFirstname(testDataMap.get("FirstName"));
				rgstrUser.enterlastName(testDataMap.get("LastName"));
				rgstrUser.enteruserEmail(testDataMap.get("Email"));
				rgstrUser.enterMobilenumber(testDataMap.get("Phone Number"));
				rgstrUser.enterpassword(testDataMap.get("Password"));
				rgstrUser.enterConfirmPassword(testDataMap.get("Password"));
				rgstrUser.selectGender(testDataMap.get("Gender"));
				rgstrUser.selectOccupation(testDataMap.get("Occupation"));
				rgstrUser.selectCheckBox18Year();
			}
			public static void registerUser(WebDriver driver, Map<String, String> testDataMap, String testName) {
				// TODO Auto-generated method stub
				if(testName.equals("RegisterUserAndVerifyUserAlreadyExists"));
				RegisterUserPage rgstrUser = new RegisterUserPage(driver);
				rgstrUser.enterFirstname("Rajesh");
				rgstrUser.enterlastName("Gore");
				rgstrUser.enteruserEmail("Rajesh@gmail.com");
				rgstrUser.enterMobilenumber("8422334433");
				rgstrUser.enterpassword("Rajesh@2345");
				rgstrUser.enterConfirmPassword("Rajesh@2345");
				rgstrUser.selectGender("Male");
				rgstrUser.selectOccupation("Doctor");
				rgstrUser.selectCheckBox18Year();
			}
}
