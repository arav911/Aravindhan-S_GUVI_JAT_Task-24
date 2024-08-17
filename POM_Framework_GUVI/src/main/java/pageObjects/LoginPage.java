package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.BaseComponents;

//This class contains Page objects for the Login page
public class LoginPage extends BaseComponents {
//	Declaring object for WebDriver
	WebDriver driver;

//	Constructor for Login class to assign "driver" and PageFactory
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	Storing Web Elements in Login page of the DemoBlaze application using @FindBy annotation
	@FindBy(id = "loginusername")
	WebElement userNameField;
	@FindBy(id = "loginpassword")
	WebElement passwordField;
	@FindBy(xpath = "//div[@id='logInModal']//button[text()='Log in']")
	WebElement loginButton;
	@FindBy(xpath = "//div[@id='logInModal']//button[text()='Close']")
	WebElement closeButton;

//	Action method for getting User name from the Excel file
	public String getUserName() throws Exception {
		return getCredentials("DemoBlaze_Credentials", "User Name");
	}

//	Action method for getting Password from the Excel file
	public String getPassword() throws Exception {
		return getCredentials("DemoBlaze_Credentials", "Password");
	}

//	Action method to enter credentials in the login page
	public void enterCredentials(String userName, String password) {
		enterText(userNameField, userName);
		enterText(passwordField, password);
		clickButton(loginButton);
	}

	
//	Action method to validate Alert text found after entering the credentials	
	public void validateAlert(String expectedText) throws Exception {
		Assert.assertEquals(getAlertText(), expectedText);
	}

//	Action method to close the Login page	
	public void closeLoginPage() {
		closeButton.click();
	}
}
