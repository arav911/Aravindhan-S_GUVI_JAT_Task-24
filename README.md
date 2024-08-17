Project Title: Selenium E2E Framework
-------------------------------------------
    This project involves End to End Automation Testing of Sign Up and Login functionalities in DemoBlaze (E-Commerce) Application, 
    developed using the tools such as Selenium WebDriver, TestNG, ExtentReport, Maven

Please find the below points to get an overview on the framework designed.
  1. Identified the web pages on https://www.demoblaze.com/ and created separate Java class for each page by adding test suites and test classes to it. 
  2. Base Package and Classes consists of methods to perform actions on each web element, such as clicking a button or entering text into a text field. 
      - Location: src/main/java/base
      - Files: BaseComponents.java, BaseTests.java, Listeners.java
  3. Page Objects creation were defined the web elements using the @FindBy annotation from the Selenium WebDriver library, also used the PageFactory class to initialize the web elements.
      - Location: src/main/java/pageObjects
      - Files: HomePage.java, LoginPage.java, SignUpPage.java 
  4. Written both the Positive and Negative Test Cases, and used assertions to verify that the expected results are achieved.
      - Location: src/test/java/tests
      - Files: LoginTests.java, SignUpTests.java
  5. Extent Reports and Screenshots got generated under Test_Results folder
      - Report Location: Test_Results/Reports
      - Screenshots Location: Test_Results/Screenshots
