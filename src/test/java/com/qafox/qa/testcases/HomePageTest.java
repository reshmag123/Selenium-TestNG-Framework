package com.qafox.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qafox.qa.base.TestBase;
import com.qafox.qa.pages.DeskTopPage;
import com.qafox.qa.pages.HomePage;
import com.qafox.qa.pages.LoginPage;
import com.qafox.qa.pages.LogoutPage;
import com.qafox.qa.pages.ShoppingCartPage;
import com.qafox.qa.pages.WishListPage;

public class HomePageTest extends TestBase {
	
	LoginPage login;
	HomePage homepage;
	LogoutPage logoutpage;
	DeskTopPage desktoppage;
	WishListPage wishlistpage;
	ShoppingCartPage shoppingcartpage;
	
	public HomePageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void SetUp() throws InterruptedException
	{
		Initialization();
		login = new LoginPage();
		desktoppage = new DeskTopPage();
		logoutpage = new LogoutPage();
		wishlistpage = new WishListPage();
	    shoppingcartpage = new ShoppingCartPage();
		homepage = login.login(prop.getProperty("username"), prop.getProperty("password"));
		Thread.sleep(2000);	
	}
	
	@Test(priority = 1)
	public void validateHomePageTitle()
	{
		String Title = homepage.validateHomeTitle();
		System.out.println("Title is :"+Title);
		Assert.assertEquals(Title, "My Account", "Title not correct");
	}
	
	@Test(priority = 2)
	public void validatePresentACCHeader()
	{
		boolean value = homepage.validateMyAccoutHeading();
		System.out.println("Value is :"+value);
		Assert.assertTrue(value);
	}
	
	@Test(priority = 3)
	public void validateLogoutOptionTest() throws InterruptedException
	{
		logoutpage = homepage.validateLogoutOption();
		Thread.sleep(1000);
	}
	
	@Test(priority = 4)
	public void DeskTopOptionsTest() throws InterruptedException
	{
		desktoppage = homepage.DeskTopOptions();
		Thread.sleep(1000);
	}
	
	@Test(priority = 5)
	public void validateWishListTest() throws InterruptedException
	{
		wishlistpage = homepage.validateWishList();
		Thread.sleep(1000);
	}
	
	@Test(priority = 6)
	public void validateShoppingCartTest() throws InterruptedException
	{
		shoppingcartpage = homepage.validateShoppingCart();
		Thread.sleep(1000);
	}
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

}
