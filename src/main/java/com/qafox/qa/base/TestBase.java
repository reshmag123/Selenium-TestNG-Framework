package com.qafox.qa.base;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import com.qafox.qa.utils.DriverSetup;
import com.qafox.qa.utils.TestUtil;


public class TestBase {

	public static WebDriver driver;
	public static Properties prop;

	public TestBase() {
        prop = new Properties();

        try {
            InputStream ip = getClass()
                    .getClassLoader()
                    .getResourceAsStream("config.properties");

            if (ip == null) {
                throw new RuntimeException("config.properties not found in classpath");
            }

            prop.load(ip);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }
	
	
	public static void Initialization()
	{
		String browserName = prop.getProperty("browser");
		driver = DriverSetup.getDriver(browserName);	
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIME_OUT));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
		
		driver.get(prop.getProperty("url"));
	}
}
