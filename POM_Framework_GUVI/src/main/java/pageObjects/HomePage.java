package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.BaseComponents;

//This class contains Page objects for the Home page
public class HomePage extends BaseComponents {
//	Declaring object for WebDriver
	WebDriver driver;

//	Constructor for HomePage class to assign "driver" and PageFactory
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	Storing Web Elements in Home page of the DemoBlaze application using @FindBy annotation
	@FindBy(linkText = "Sign up")
	WebElement signUpPage;
	@FindBy(linkText = "Log in")
	WebElement logInPage;
	@FindBy(id = "nameofuser")
	WebElement profileName;

//	Action method to launch the URL
	public void goToURL(String URL) {
		driver.get(URL);
	}

//	Action method to open the Sign up page
	public void openSignUpPage() {
		clickButton(signUpPage);
	}

//	Action method to open the Login page
	public void openLogInPage() {
		clickButton(logInPage);
	}
	
//	Action method to validate the User name under profile section, after successfully logged in
	public void validateUserName(String name) throws Exception {
//		Explicitly waiting for the profileName element to appear
		waitForElementToAppear(profileName);
//		Performing the assertion to check name populated has been matching with the expected name
		Assert.assertTrue(profileName.getText().contains(name));
//		Taking screenshot
		takeScreenshot(driver);
	}
}
