package com.qafox.qa.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class TestUtil{
	
	public static long PAGE_LOAD_TIME_OUT = 30;
	public static long IMPLICIT_WAIT = 20;
	
	
    public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {

    String dateName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

    TakesScreenshot ts = (TakesScreenshot) driver;
    File source = ts.getScreenshotAs(OutputType.FILE);

    // Create folder if not exists
    String folderPath = System.getProperty("user.dir") + "/FailedTestsScreenshots/";
    File folder = new File(folderPath);
    if (!folder.exists()) {
        folder.mkdir();
    }

    String destination = folderPath + screenshotName + "_" + dateName + ".png";
    File finalDestination = new File(destination);

    FileUtils.copyFile(source, finalDestination);

    return destination;  // return path for ExtentReports attachment
  }
    
    
    public static void logFailure(ExtentTest test, WebDriver driver, ITestResult result) throws IOException {

        test.log(Status.FAIL, "TEST CASE FAILED IS: " + result.getName());
        test.log(Status.FAIL, result.getThrowable());

        String screenshotPath = getScreenshot(driver, result.getName());

        test.fail("Screenshot Below:",
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
    }

}
