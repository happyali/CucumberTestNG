package com.qbr.testStepsGlue;

import org.openqa.selenium.WebDriver;
import org.apache.log4j.Logger;

import com.qbr.baseSteps.BaseTest;
import com.qbr.pages.LoginPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginTestSteps{
	WebDriver driver;
	Logger log;

	LoginPage loginPage = new LoginPage();

	@Given("^I am on QBR HomePage$")
	public void i_am_on_QBR_HomePage() throws Throwable {
		System.out.println("1----: " + driver);
		loginPage = new LoginPage();
		loginPage.LaunchQBR();
	}

	@When("^I click button to login$")
	public void i_click_button_to_login() throws Throwable {
		System.out.println("2");
	}

	@When("^I enter username$")
	public void i_enter_username() throws Throwable {
		System.out.println("3");
	}

	@When("^I enter password$")
	public void i_enter_password() throws Throwable {
		System.out.println("4");
	}

	@When("^I click on the login button$")
	public void i_click_on_the_login_button() throws Throwable {
		System.out.println("5");
	}

	@Then("^I land on dashbaord page$")
	public void i_land_on_dashbaord_page() throws Throwable {
		System.out.println("6");
	}

}
