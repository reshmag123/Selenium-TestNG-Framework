package com.qafox.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qafox.qa.base.TestBase;
import com.qafox.qa.pages.HomePage;
import com.qafox.qa.pages.LoginPage;
import com.qafox.qa.pages.LogoutPage;

public class LogoutPageTest extends TestBase {
	
	LoginPage login;
	HomePage homepage;
	LogoutPage logoutpage;

	LogoutPageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void SetUp() throws InterruptedException
	{
		Initialization();
		login = new LoginPage();
	    homepage = new HomePage();
		logoutpage = new LogoutPage();
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
	public void tearDown()
	{
		driver.quit();
	}
}
