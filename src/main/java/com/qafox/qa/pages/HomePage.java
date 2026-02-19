package com.qafox.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qafox.qa.base.TestBase;

public class HomePage extends TestBase {

	@FindBy(xpath="//h2[contains(text(),'My Orders')]")
	WebElement MyOrders;
	
	@FindBy(xpath="//h2[contains(text(),'My Account')]")
	WebElement MyAccount;
	
	@FindBy(xpath="//span[contains(text(),'My Account')]")
	WebElement HomeAccOptions;
	
	@FindBy(xpath="//a[normalize-space()='Desktops']")
	WebElement DeskTopOptions; 
	
	@FindBy(xpath="//a[contains(text(),'Show AllDesktops')]")
	WebElement AllDeskTop;
	
	@FindBy(xpath="//span[contains(text(),'Wish List')]")
	WebElement WishList;
	
	@FindBy(xpath="//span[contains(text(),'Shopping Cart')]")
	WebElement ShoppingCart;
	
	
	public HomePage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public String validateHomeTitle()
	{
		return driver.getTitle();
	}
	
	public boolean validateMyAccoutHeading()
	{
		return MyAccount.isDisplayed();
	}
	
	public LogoutPage validateLogoutOption()
	{
		HomeAccOptions.click();
		List<WebElement> list = driver.findElements(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//li//a"));
		
		//System.out.println("Options are as follows: ");
		for(int i= 0;i<list.size();i++)
		{
		//	System.out.println(list.get(i).getText());
			if(list.get(i).getText().equals("Logout"))
			{
				list.get(i).click();
				return new LogoutPage();
			}
			
		}
		throw new NoSuchElementException("Logout option not found in dropdown");
	}
	
	public DeskTopPage DeskTopOptions() throws InterruptedException
	{
		DeskTopOptions.click();
		Thread.sleep(1000);
		AllDeskTop.click();
		
		return new DeskTopPage();
	}
	
	public WishListPage validateWishList() throws InterruptedException
	{
		WishList.click();
		Thread.sleep(1000);
		return new WishListPage();
	}
	
	public ShoppingCartPage validateShoppingCart() throws InterruptedException
	{
		ShoppingCart.click();
		Thread.sleep(1000);
		return new ShoppingCartPage();
	}
	
}
