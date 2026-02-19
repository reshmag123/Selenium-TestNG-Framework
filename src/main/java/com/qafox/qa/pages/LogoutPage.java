package com.qafox.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qafox.qa.base.TestBase;

public class LogoutPage extends TestBase {
	
	@FindBy(xpath = "//h1[normalize-space()='Account Logout']")
	WebElement AccLogoutHeader;
	
	@FindBy(xpath = "//a[@class ='btn btn-primary']")
	WebElement ContinueBtn;
	
	
	public LogoutPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	
	public boolean validateAccLogoutHeader()
	{
		return AccLogoutHeader.isDisplayed();
	}
	
	public String validateLogoutPageTitle()
	{
		return driver.getTitle();
	}
	
	public LoginPage ClickOnLogoutContinue()
	{
		ContinueBtn.click();
		return new LoginPage();
	}

}
