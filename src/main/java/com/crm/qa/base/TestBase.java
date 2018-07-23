package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;

public class TestBase {
	
    public static WebDriver driver ;
    public static Properties prop;
    public static EventFiringWebDriver e_driver;
    public static WebEventListener eventListener;
	
    public TestBase()
    {
     try{
    	prop = new Properties();
		FileInputStream ip = new FileInputStream("H:\\Sweety Lappy Backup\\Selenium3\\PageObjectModel\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
		prop.load(ip);
    }
     catch(FileNotFoundException e){
    	 e.printStackTrace();
     }
     catch(IOException e){
    	 e.printStackTrace();
     }
    }
    
    public static void initization(){
    	String browserName = prop.getProperty("browser");
    	if (browserName.equals("FF"))
		{
			System.setProperty("webdriver.gecko.driver", "H:\\Sweety Lappy Backup\\Selenium3\\geckodriver-v0.19.1-win32\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if (browserName.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "H:\\Sweety Lappy Backup\\Selenium3\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if (browserName.equals("IE"))
		{
			System.setProperty("webdriver.gecko.driver", "H:\\Sweety Lappy Backup\\Selenium3\\geckodriver-v0.19.1-win32\\geckodriver.exe");
			driver = new InternetExplorerDriver();
		}
    	
    	e_driver = new EventFiringWebDriver(driver);
    	// Now create object of EventListerHandler to register it with EventFiringWebDriver
    	eventListener = new WebEventListener();
    	e_driver.register(eventListener);
    	driver = e_driver;
    	
    	driver.manage().window().maximize();
    	driver.manage().deleteAllCookies();
    	driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
    	driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
    	
    	driver.get(prop.getProperty("url"));
    }
	
}
