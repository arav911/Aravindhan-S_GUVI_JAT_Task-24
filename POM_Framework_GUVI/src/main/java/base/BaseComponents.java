package base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;

//This class contains Action methods and Common utility methods
public class BaseComponents {
//	Declaring object for WebDriver, WebDriverWait, Alert
	WebDriver driver;
	WebDriverWait wait;
	Alert alert;

//	Constructor for BaseComponents class to assign "driver" and PageFactory
	public BaseComponents(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

//	Action method to perform sendKeys operation in a Text box WebElement
	protected void enterText(WebElement element, String text) {
		waitForElementToAppear(element);
		element.sendKeys(text);
	}

//	Action method to perform click operation in a button WebElement
	protected void clickButton(WebElement element) {
		waitForElementToAppear(element);
		element.click();
	}

//	Action method to Handle the Alert
	protected String getAlertText() throws Exception {
		Thread.sleep(6000);
		alert = driver.switchTo().alert();
		String alertText = alert.getText();
		alert.accept();
		String reportInfo = "The Alert Text is \"" + alertText + "\"";
		
//		Setting the attribute for alert text
		ITestResult itr = Reporter.getCurrentTestResult();
        itr.setAttribute("reportInfo", reportInfo);
		return alertText;
	}
	
//	Action method to take screenshot on the web page	
	protected void takeScreenshot(WebDriver driver) throws Exception {
//		Get the stack trace
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        
//      The stack trace element at index 3 represents the caller of this method
        StackTraceElement caller = stackTrace[3];
        
//      Extract the method name
        String testName = caller.getMethodName();		
		
//      Taking Screenshot in the web page
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File finalDestination = new File("./Test_Results/Screenshots/"+testName+".png");
		FileUtils.copyFile(source, finalDestination);
	}

//	Action method to perform clearing of data in a Text box WebElement
	protected void clearData(WebElement element) {
		waitForElementToAppear(element);
		element.clear();
	}

//	Action method to explicitly wait for a WebElement to appear
	protected void waitForElementToAppear(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

//	Action method to explicitly wait for a WebElement to disappear
	protected void waitForElementToDisappear(WebElement element) {
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

//	Action method to get data from the Excel file by specifying file name and column name	
	public String getCredentials(String fileName, String columnName) throws Exception {
		
//		Specify the location of file
		String filePath = "./src/main/resources/" + fileName + ".xlsx";
		String readData = "";
		File file = new File(filePath);

//		Load file
		FileInputStream fis = new FileInputStream(file);

//		Load workbook
		XSSFWorkbook wb = new XSSFWorkbook(fis);

//		Load work sheet
		XSSFSheet sheet = wb.getSheet("Sheet1");

//		Getting value from the respective cell
		for (int i = 0; i < 2; i++) {
			if (sheet.getRow(0).getCell(i).getStringCellValue().equalsIgnoreCase(columnName))
				readData = sheet.getRow(1).getCell(i).getStringCellValue();
		}

		readData = (readData != null) ? readData : "";
		
//		Close workbook
		wb.close();

		return readData;
	}
}