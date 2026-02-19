package com.qafox.qa.utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverSetup {
	
	public static WebDriver getDriver(String browserName) {
        WebDriver driver;

        if(browserName.equalsIgnoreCase("chrome")) {
            // Automatically download & setup ChromeDriver
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } 
        else if(browserName.equalsIgnoreCase("firefox") || browserName.equalsIgnoreCase("ff")) {
            // Automatically download & setup GeckoDriver
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } 
        else {
            throw new IllegalArgumentException("Browser not supported: " + browserName);
        }

        //driver.manage().window().maximize(); // optional
        return driver;
    }
	    
}



