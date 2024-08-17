package base;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.HomePage;

//This class is the base to this Framework and initializes the driver instance
public class BaseTest {

//	Declaring variable for URL
	String URL;
	
//	Declaring objects for WebDriver and HomePage 
	public WebDriver driver;
	public HomePage homePage;

//	Declaring String Literals to generate credentials
	String name = "", pwd = "";
	String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

//	Initializing the WebDriver
	public WebDriver initializeDriver() throws IOException {
//		Loading the properties file to get "Browser" and "URL" parameter values
		Properties properties = new Properties();
		FileInputStream file = new FileInputStream("./src/main/resources/GlobalData.properties");
		properties.load(file);
		String browser = properties.getProperty("browser");
		URL = properties.getProperty("url");
		
//		driver assigned as "ChromeDriver", if browser value is "chrome"
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {  // else "FirefoxDriver" would be assigned
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		
//		Adding implicit wait for 5 seconds
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
//		Maximizing the window
		driver.manage().window().maximize();
		return driver;
	}

//	This method will be executed before every @Test method
	@BeforeMethod
	public void launchApplication() throws IOException {
//		getting the driver
		driver = initializeDriver();
		
//		creating object for HomePage to access the methods in it
		homePage = new HomePage(driver);
		
//		Opening the URL from HomePage
		homePage.goToURL(URL);
	}

//	This method will be executed after every @Test method
	@AfterMethod
	public void quitBrowser() {
//		Quits the driver instance and the browser tabs
		driver.quit();
	}

	void generateCredentials() throws Exception {
		Random random = new Random();
//	    Generate random User name
		for (int i = 0; i < 7; i++) {
			int index = random.nextInt(alphabet.length());
			char randomChar = alphabet.charAt(index);
			name += randomChar;
		}

//		Generate Random Password
		for (int i = 0; i < 3; i++) {
			int index = random.nextInt(alphabet.length());
			char randomChar = alphabet.charAt(index);
			pwd += randomChar;
		}
		int num = random.nextInt(999);
		pwd += String.valueOf(num);

		System.out.println("User name: " + name + "\nPassword: " + pwd);
//		Storing the User name and Password in the excel file by passing File name, user name and password as arguments
		storeCredentials("DemoBlaze_Credentials", name, pwd);
	}

	void storeCredentials(String fileName, String name, String pwd) throws Exception {
//		creating object of workbook
		XSSFWorkbook wb = new XSSFWorkbook();

//		Giving file path where the Excel file will create
		String filePath = "./src/main/resources/" + fileName + ".xlsx";
//		creating object of FileOutputStream
		FileOutputStream fos = new FileOutputStream(filePath);

//		creating object of work sheet
		XSSFSheet sheet = wb.createSheet("Sheet1");

//		Creating ArrayList
		ArrayList<Object[]> inputData = new ArrayList<Object[]>();
		inputData.add(new Object[] { "User Name", "Password" });
		inputData.add(new Object[] { name, pwd });

		int rowNum = 0;
//		Outer loop for rows
		for (Object[] input : inputData) {
			XSSFRow row = sheet.createRow(rowNum++);
			int cellNum = 0;
//			Inner loop for columns
			for (Object inp : input) {
				XSSFCell cell = row.createCell(cellNum++);
				if (inp instanceof String)
					cell.setCellValue((String) inp);
			}
		}

//		Writing data to excel
		wb.write(fos);
		
//		Closing FileOutputStream
		fos.close();
		
//		Closing workbook
		wb.close();
		System.out.println("User Credentials successfully stored in Excel!!!");
	}
}
