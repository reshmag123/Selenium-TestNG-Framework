package com.qafox.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qafox.qa.base.TestBase;
import com.qafox.qa.pages.HomePage;
import com.qafox.qa.pages.LoginPage;

public class LoginPageTest extends TestBase {
	
	LoginPage login;
	HomePage homepage;
	
	public LoginPageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void SetUp() throws InterruptedException
	{
		Initialization();
		login = new LoginPage();
		Thread.sleep(2000);
		
	}
	
	@Test(priority = 1)
	public void LoginPageTitleTest()
	{
		String Title = login.ValidatePageTitle();
		Assert.assertEquals(Title, "Your Store");
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
	public void tearDown()
	{
		driver.quit();
	}
	
    //for every test case we are logging and closing the browser to avoid the cache or cookies issue as browser
	//gets exhausted if multiple tests run on the same window
	//test cases should be independent and seperately run
}
