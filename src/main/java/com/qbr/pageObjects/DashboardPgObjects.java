package com.qbr.pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qbr.baseSteps.BasePageObject;
import com.qbr.baseSteps.BaseTest;

public class DashboardPgObjects extends BasePageObject<DashboardPgObjects>{
	private By qbrAuthHeader = By.xpath("//li[@class='menu-dashboard']");
	private By logoutButton	 = By.xpath("//a[@id='logoutButton']");

	
	
	protected DashboardPgObjects(WebDriver driver, Logger log) {
		super(driver);
	}
	
	public void waitforDashboardPage() {
		waitforVisibilityOf(qbrAuthHeader, 20);	
		log.info("We are on QBR Dashboard...!");
	}
}
