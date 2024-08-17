package tests;
import org.testng.annotations.Test;

import base.BaseTest;
import pageObjects.SignUpPage;

//This class contains Test Cases for the Sign Up page
public class SignUpTests extends BaseTest{
//	Declaring object for Sign Up page
	SignUpPage signUp;
	
//	Negative Test Case
	@Test(priority = 1, testName = "Without entering User name and Password")
	public void without_entering_User_name_and_Password() throws Exception {
//		Clicks on sign up button from home page
		homePage.openSignUpPage();
		
//		Creating object for SignUpPage to access methods from its class
		signUp = new SignUpPage(driver);
		
//		Signing up the application without entering the credentials 
		signUp.enterCredentials("", "");
		
//		Validating the alert with expected text
		signUp.validateAlert("Please fill out Username and Password.");
	}

//	Negative Test Case
	@Test(priority = 2, testName = "Entering only User name")
	public void entering_only_User_name() throws Exception {
//		Clicks on sign up button from home page
		homePage.openSignUpPage();
		
//		Creating object for SignUpPage to access methods from this class
		signUp = new SignUpPage(driver);
		
//		Getting the User name
		String userName = signUp.getUserName();
		
//		Signing up the application by providing only User name 
		signUp.enterCredentials(userName, "");
		
//		Validating the alert with expected text
		signUp.validateAlert("Please fill out Username and Password.");
	}
	
//	Negative Test Case
	@Test(priority = 3, testName = "Entering only Password")
	public void entering_only_Password() throws Exception {
//		Clicks on sign up button from home page
		homePage.openSignUpPage();
		
//		Creating object for SignUpPage to access methods from this class
		signUp = new SignUpPage(driver);
		
//		Getting the Password
		String password = signUp.getPassword();
		
//		Signing up the application by providing only Password
		signUp.enterCredentials("", password);
		
//		Validating the alert with expected text
		signUp.validateAlert("Please fill out Username and Password.");
	}
	
//	Positive Test Case
	@Test(priority = 4, testName = "Entering both User name and Password")
	public void entering_both_User_name_and_Password() throws Exception {
//		Clicks on sign up button from home page
		homePage.openSignUpPage();
		
//		Creating object for SignUpPage to access methods from this class
		signUp = new SignUpPage(driver);
		
//		Getting the User name and Password
		String userName = signUp.getUserName();
		String password = signUp.getPassword();
		
//		Signing up the application by providing User name and Password 
		signUp.enterCredentials(userName, password);
		
//		Validating the alert with expected text
		signUp.validateAlert("Sign up successful.");
	}
	
//	Negative Test Case
	@Test(priority = 5, testName = "Entering already existing User name and Password")
	public void entering_already_existing_User_name_and_Password() throws Exception {
//		Clicks on sign up button from home page
		homePage.openSignUpPage();
		
//		Creating object for SignUpPage to access methods from this class
		signUp = new SignUpPage(driver);
		
//		Getting the User name and Password
		String userName = signUp.getUserName();
		String password = signUp.getPassword();
		
//		Signing up the application by providing already existing User name and Password 
		signUp.enterCredentials(userName, password);
		
//		Validating the alert with expected text
		signUp.validateAlert("This user already exist.");
	}

}
