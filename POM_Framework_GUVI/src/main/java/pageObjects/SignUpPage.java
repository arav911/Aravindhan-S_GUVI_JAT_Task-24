package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.BaseComponents;

//	This class contains Page objects for the Sign Up page
public class SignUpPage extends BaseComponents {
//	Declaring object for WebDriver
	WebDriver driver;

//	Constructor for SignUpPage class to assign "driver" and PageFactory
	public SignUpPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	Storing Web Elements in sign up page of the DemoBlaze application using @FindBy annotation
	@FindBy(id = "sign-username")
	WebElement userNameField;
	@FindBy(id = "sign-password")
	WebElement passwordField;
	@FindBy(xpath = "//div[@id='signInModal']//button[text()='Sign up']")
	WebElement signUpButton;
	@FindBy(xpath = "//div[@id='signInModal']//button[text()='Close']")
	WebElement closeButton;

//	Action method for getting User name from the Excel file
	public String getUserName() throws Exception {
		return getCredentials("DemoBlaze_Credentials", "User Name");
	}

//	Action method for getting Password from the Excel file
	public String getPassword() throws Exception {
		return getCredentials("DemoBlaze_Credentials", "Password");
	}

//	Action method to enter credentials in the sign up page
	public void enterCredentials(String userName, String password) {
		enterText(userNameField, userName);
		enterText(passwordField, password);
		clickButton(signUpButton);
	}

//	Action method to validate Alert text found after entering the credentials	
	public void validateAlert(String expectedText) throws Exception {
		Assert.assertEquals(getAlertText(), expectedText);
	}

//	Action method to close the sign up page	
	public void closeSignUpPage() {
		closeButton.click();
	}
}
