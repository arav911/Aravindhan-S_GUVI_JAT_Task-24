package tests;
import org.testng.annotations.Test;

import base.BaseTest;
import pageObjects.LoginPage;

//This class contains Test Cases for the Login page
public class LoginTests  extends BaseTest{
//	Declaring object for Login page
	LoginPage login;
	
//	Negative Test Case	
	@Test(priority = 1, testName = "Without entering User name and Password")
	public void without_entering_User_name_and_Password() throws Exception {
//		Clicks on log in button from home page
		homePage.openLogInPage();
		
//		Creating object for LoginPage to access methods from its class
		login = new LoginPage(driver);
		
//		Login to the application without entering the credentials 
		login.enterCredentials("", "");
		
//		Validating the alert with expected text
		login.validateAlert("Please fill out Username and Password.");
	}
	
	@Test(priority = 2, testName = "Entering only User name")
	public void entering_only_User_name() throws Exception {
//		Clicks on log in button from home page
		homePage.openLogInPage();
		
//		Creating object for LoginPage to access methods from its class
		login = new LoginPage(driver);
		
//		Getting the User name
		String userName = login.getUserName();
		
//		Signing up the application by providing only User name
		login.enterCredentials(userName, "");
		
//		Validating the alert with expected text
		login.validateAlert("Please fill out Username and Password.");
	}
	
	@Test(priority = 3, testName = "Entering only Password")
	public void entering_only_Password() throws Exception {
//		Clicks on log in button from home page
		homePage.openLogInPage();
		
//		Creating object for LoginPage to access methods from its class
		login = new LoginPage(driver);
		
//		Getting the Password
		String password = login.getPassword();
		
//		Signing up the application by providing only Password
		login.enterCredentials("", password);
		
//		Validating the alert with expected text
		login.validateAlert("Please fill out Username and Password.");
	}
	
	@Test(priority = 4, testName = "Entering Invalid User name")
	public void entering_Invalid_User_name() throws Exception {
//		Clicks on log in button from home page
		homePage.openLogInPage();
		
//		Creating object for LoginPage to access methods from its class
		login = new LoginPage(driver);
		
//		Getting the Password
		String password = login.getPassword();
		
//		Signing up the application by providing Invalid User name and Valid Password 
		login.enterCredentials("abcd1234e", password);
		
//		Validating the alert with expected text
		login.validateAlert("User does not exist.");
	}
	
	@Test(priority = 5, testName = "Entering Invalid Password")
	public void entering_Invalid_Password() throws Exception {
//		Clicks on log in button from home page
		homePage.openLogInPage();
		
//		Creating object for LoginPage to access methods from its class
		login = new LoginPage(driver);
		
//		Getting the User name
		String userName = login.getUserName();
		
//		Signing up the application by providing Valid User name and Invalid Password 
		login.enterCredentials(userName, "abcd1234");
		
//		Validating the alert with expected text
		login.validateAlert("Wrong password.");
	}
	
	@Test(priority = 6, testName = "Entering Valid User name and Password")
	public void entering_Valid_User_name_and_Password() throws Exception {
//		Clicks on log in button from home page
		homePage.openLogInPage();
		
//		Creating object for LoginPage to access methods from its class
		login = new LoginPage(driver);
		
//		Getting the User name and Password
		String userName = login.getUserName();
		String password = login.getPassword();
		
//		Signing up the application by providing Valid User name Password 
		login.enterCredentials(userName, password);
		
//		Validating the User name under profile section, after successfully logged in
		homePage.validateUserName(userName);
	}

}
