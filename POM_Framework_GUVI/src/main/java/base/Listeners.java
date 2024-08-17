package base;

import java.io.File;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

//This class acts as Listener for TestNG abstract methods implemented from ITestListener Interface
public class Listeners extends BaseTest implements ITestListener {
//	Object declaration for Extent Reports
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	BaseComponents baseComp;
	private static ExtentReports extent;
	public static ExtentHtmlReporter htmlReporter;

//	Method executes at the start of the execution
	public void onStart(ITestContext context) {
		try {
//			Used to generate credentials each and every time the execution starts
			generateCredentials();

//			Generating the Extent Report at specified
			String path = "./Test_Results/Reports";
			File directory = new File(path);
	        if (!directory.exists())
	            directory.mkdirs();
			htmlReporter = new ExtentHtmlReporter(path + File.separator + "Aravindhan S_Task-24_extent_report.html");
			htmlReporter.config().setReportName("Aravindhan S - GUVI JAT Task-24 Result");
			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	Method executes at the start of the @Test method
	public void onTestStart(ITestResult result) {
//		Extracting the currently executing test name
		String testName = result.getMethod().getMethodName().replace("_", " ");
		
//		Extracting the currently executing class name
		String className = result.getTestClass().getName();
		testName = testName.substring(0, 1).toUpperCase() + testName.substring(1);
		
//		Attaching the test name to report
		ExtentTest extentTest = extent.createTest(className + " " + testName);
		test.set(extentTest);
		test.get().info("Test Started");
	}

//	Method executes when the @Test method gets "Success" or "Passed"
	public void onTestSuccess(ITestResult result) {
//		Attaching screen shot to the extent report when @Test is Successful
		String screenshotPath = System.getProperty("user.dir") + "/Test_Results/Screenshots/" + result.getMethod().getMethodName() + ".png";
		File file = new File(screenshotPath);
		if (file.exists()) {
			try {
				test.get().info("Screenshots:\n", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		Report displays "NA" if screenshot exists 
		else
			test.get().info("Screenshots:\nNA");
		
//		Getting the attribute if value is available
		if (result.getAttribute("reportInfo") != null)
			test.get().info((String) result.getAttribute("reportInfo"));
		test.get().pass("Test Passed");
	}

//	Method executes when the @Test method gets "Failed"
	public void onTestFailure(ITestResult result) {
//		Attaching screen shot to the extent report when @Test is Failed
		String screenshotPath = System.getProperty("user.dir") + "/Test_Results/Screenshots/" + result.getMethod().getMethodName() + ".png";
		File file = new File(screenshotPath);
		if (file.exists()) {
			try {
				test.get().info("Screenshots:\n", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		Report displays "NA" if screenshot exists
		else
			test.get().info("Screenshots:\nNA");
		
//		Getting the attribute if value is available
		if (result.getAttribute("reportInfo") != null)
			test.get().info((String) result.getAttribute("reportInfo"));
		test.get().fail(result.getThrowable());
	}

//	Method executes when the @Test method gets "Skipped"
	public void onTestSkipped(ITestResult result) {
//		Attaching screen shot to the extent report when @Test is Skipped
		String screenshotPath = System.getProperty("user.dir") + "/Test_Results/Screenshots/" + result.getMethod().getMethodName() + ".png";
		File file = new File(screenshotPath);
		if (file.exists()) {
			try {
				test.get().info("Screenshots:\n", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		Report displays "NA" if screenshot exists
		else
			test.get().info("Screenshots:\nNA");
		
//		Getting the attribute if value is available
		if (result.getAttribute("reportInfo") != null)
			test.get().info((String) result.getAttribute("reportInfo"));
		test.get().skip(result.getThrowable());
	}

//	Method executes at the end of the execution
	public void onFinish(ITestContext context) {
//		Completing the Report generation after adding test cases details and status to it
		extent.flush();
	}

}
