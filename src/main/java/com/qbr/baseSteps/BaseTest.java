package com.qbr.baseSteps;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected Logger log;
	
	protected BaseTest(WebDriver driver, Logger log) {
		this.driver = driver;
		this.log = log;
		System.out.println(driver);
	}

	@BeforeClass
	protected void setUpClass(ITestContext context){
		String testName = context.getCurrentXmlTest().getName();
		log = Logger.getLogger(testName);
		System.out.println("I am in BeforeClass of BaseTest.");
	}
	
	@Parameters({"browser"})
	@BeforeMethod
	protected void methodSetUp(String browser) {
		log.info("the current browser is : "+browser);
		this.driver = (BrowserSetup.getDriver(browser, log));
		System.out.println("I am in in BeforeMethod of BaseTest.");
	}
	
/*	@BeforeMethod
	protected void methodSetUp() {
		//System.setProperty("webdriver.gecko.driver", "src/main/resources/browserDriver/geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", "src/main/resources/browserDriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}*/

	@AfterMethod
	protected void methodTearDown() {
		driver.quit();
	}

}
