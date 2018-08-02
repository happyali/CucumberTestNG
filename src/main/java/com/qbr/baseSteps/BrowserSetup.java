package com.qbr.baseSteps;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserSetup {

	public static WebDriver driverInstanceForListener = null;
	
	public static WebDriver getDriver(String browser, Logger log) {
		
		WebDriver driver;	
		switch (browser) {
		case "firefox":
			System.setProperty("webdriver.gecko.driver", "src/main/resources/browserDriver/geckodriver.exe");
			driver = new FirefoxDriver();	
			log.info("the browser from setUp is : "+browser);
			break;
			
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "src/main/resources/browserDriver/chromedriver.exe");
			driver = new ChromeDriver();			
			log.info("the browser from setUp is : "+browser);
			System.out.println("I am in Chrome Setup of BrowserSetup Class");
			break;

		default:
			System.setProperty("webdriver.gecko.driver", "src/main/resources/browserDriver/chromedriver.exe");
			driver = new ChromeDriver();
			log.info("the default browser from setUp is : "+browser);
			break;
		}

		driver.manage().window().maximize();
		driverInstanceForListener = driver;
		return driver;
	}

	public static WebDriver getDriverInstance() {
		return driverInstanceForListener;
	}
}
