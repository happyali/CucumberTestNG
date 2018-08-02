package com.qbr.pageObjects;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qbr.baseSteps.BasePageObject;

public class LoginPageObjects extends BasePageObject<LoginPageObjects> {
	private static final String url = "https://www-02.sit.qantasbusinessrewards.com/";
	private By loginButton = By.xpath("//div[@id='loginButton']");
	private By emailField = By.xpath("//input[@id='email']");
	private By passwordField = By.xpath("//input[@id='password']");
	private By submitLogin = By.xpath("//input[@id='loginbtnHome']");
	private By ErrMsg = By.xpath("//p[@id='msglogin']");
	
	public LoginPageObjects(WebDriver driver, Logger log) {
		super(driver);
	}

	public void launchLogin() {
		System.out.println("I am in LoginPageObjects Class");
		getPage(url);		
	}
	
	public void waitforLandingPage() {
		waitforVisibilityOf(loginButton, 10);
	}
	
	public void openLoginPage() {
		click(loginButton);
	}
	
	public void enterUsername(String email) { 
		type(email, emailField);
	}

	public void enterPassword(String password) { 
		type(password, passwordField);
	}
	
	public DashboardPgObjects submitLogin() {
		click(submitLogin);
		return new DashboardPgObjects(driver, log) ;		
	}

	public String getLoginErrMsg() {
		waitforVisibilityOf(ErrMsg, 10);
		return getText(ErrMsg);
	}
}
