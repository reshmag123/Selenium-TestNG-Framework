package com.qafox.qa.testcases;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.qafox.qa.base.TestBase;
import com.qafox.qa.pages.HomePage;
import com.qafox.qa.pages.LoginPage;
import com.qafox.qa.utils.TestUtil;

public class LoginPageTest extends TestBase {
	
	LoginPage login;
	HomePage homepage;
	public ExtentReports extent;
	public ExtentTest extentTest;
	
	public LoginPageTest()
	{
		super();
	}
	
	
	@BeforeTest
	public void setExtent() {

	    // Create Spark Reporter
	    ExtentSparkReporter spark = new ExtentSparkReporter(
	            System.getProperty("user.dir") + "/test-output/ExtentReport.html");

	    // Optional: Set Report Name & Title
	    spark.config().setReportName("Automation Test Report");
	    spark.config().setDocumentTitle("QA Automation Results");

	    // Create ExtentReports instance
	    extent = new ExtentReports();
	    extent.attachReporter(spark);

	    // Add System Information
	    extent.setSystemInfo("Host Name", "Reshma J.");
	    extent.setSystemInfo("User Name", "Reshma");
	    extent.setSystemInfo("Environment", "QA");
	}
	
	@AfterTest
	public void endReport() {
	    if (extent != null) {
	        extent.flush();   // Writes everything to the report
	    }
	}
	
	
	
	@BeforeMethod
	public void setUp(Method method) throws Exception {

	    // 1️⃣ Create ExtentTest first
	    extentTest = extent.createTest(method.getName());

	    // 2️⃣ Initialize browser and pages
	    Initialization();  // sets up driver, opens URL
	    login = new LoginPage();
	    Thread.sleep(2000);	
	}
	
	@Test(priority = 1)
	public void LoginPageTitleTest()
	{
		String Title = login.ValidatePageTitle();
		Assert.assertEquals(Title, "Your Store$");
	}
	
	@Test(priority = 2)
	public void MyAccountOptionTest()
	{
		boolean value = login.ValidateMyAccountOption();
		Assert.assertTrue(value);
	}
	
	@Test(priority = 3)
	public void LoginTest() throws InterruptedException
	{
		homepage = login.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {

	    if (result.getStatus() == ITestResult.FAILURE) {
	        TestUtil.logFailure(extentTest, driver, result);
	    }
	    else if (result.getStatus() == ITestResult.SKIP) {
	        extentTest.log(Status.SKIP, "TEST CASE SKIPPED IS: " + result.getName());
	    }
	    else if (result.getStatus() == ITestResult.SUCCESS) {
	        extentTest.log(Status.PASS, "TEST CASE PASSED IS: " + result.getName());
	    }
	    driver.quit();
	}
	
    //for every test case we are logging and closing the browser to avoid the cache or cookies issue as browser
	//gets exhausted if multiple tests run on the same window
	//test cases should be independent and seperately run
}
