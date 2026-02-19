package com.qafox.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.qafox.qa.base.TestBase;

public class LoginPage extends TestBase {
	
	
	//Page Factory ->OR ->Object Repository
		/*In Selenium (Java), @FindBy is an annotation used with the Page Object Model (POM) 
		 * to locate web elements more cleanly and efficiently.
		 * It allows you to define how a WebElement should be located (instead of calling driver.findElement() everywhere).
		 * @FindBy is an annotation, and annotations must be applied to a variable.

	Y      You must declare a WebElement (or List<WebElement>) like this
		 */
	
	
	@FindBy(xpath = "//span[contains(text(),'My Account')]")
	WebElement MyAccBtn;
	
	@FindBy(xpath = "//a[contains(text(),'Login')]")
	WebElement LoginOption;
	
	@FindBy(xpath = "//input[@name='email']")
	WebElement Username;
	
	@FindBy(xpath = "//input[@name='password']")
	WebElement password;
	
	@FindBy(xpath = "//input[@type = 'submit']")
	WebElement LoginBtn;
	
	
	//Initializing the Page Objects
	//constructor
	public LoginPage()
	{
		PageFactory.initElements(driver, this);//driver is extended from base class and this means the declared page factory/objects
	}
	
	
	public String ValidatePageTitle()
	{
		return driver.getTitle();
	}
	
	
	public boolean ValidateMyAccountOption()
	{
		return MyAccBtn.isDisplayed();
	}
	
	
	public HomePage login(String us , String ps) throws InterruptedException
	{
		MyAccBtn.click();
		Thread.sleep(1000);
		LoginOption.click();
		Thread.sleep(1000);
		Username.sendKeys(us);
		password.sendKeys(ps);
		LoginBtn.click();
		
		return new HomePage();
	}


}
