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
import com.qafox.qa.pages.LogoutPage;
import com.qafox.qa.utils.TestUtil;


public class LogoutPageTest extends TestBase {
	
	LoginPage login;
	HomePage homepage;
	LogoutPage logoutpage;
	public ExtentReports extent;
	public ExtentTest extentTest;

	LogoutPageTest()
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
	    homepage = new HomePage();
	    logoutpage = new LogoutPage();

	    // 3️⃣ Perform login & navigate to test page
	    homepage = login.login(prop.getProperty("username"), prop.getProperty("password"));
	    logoutpage = homepage.validateLogoutOption();

	    Thread.sleep(2000);	
	}
	
	@Test(priority = 1)
	public void validateLogoutPageTitleTest()
	{
		String Title = logoutpage.validateLogoutPageTitle();
		System.out.println("Logout Page Title is :"+Title);
		Assert.assertEquals(Title,"Account Logout", "Title not Correct");
	}
	
	@Test(priority = 2)
	public void validateAccLogoutHeaderTest()
	{
		boolean value = logoutpage.validateAccLogoutHeader();
		Assert.assertTrue(value);
	}
	
	@Test(priority = 3)
	public void ClickOnLogoutContinueTest() throws InterruptedException
	{
		login = logoutpage.ClickOnLogoutContinue();
		Thread.sleep(1000);
		String Title = login.ValidatePageTitle();
		System.out.println("Login Page Title :"+Title);
		Assert.assertEquals(Title, "Your Store");
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
}
