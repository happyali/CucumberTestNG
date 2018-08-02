package com.qbr.pages;

import com.qbr.pageObjects.LoginPageObjects;

public class LoginPage {

	protected LoginPageObjects LoginPageObjects = null;

	public LoginPage() {
		// TODO Auto-generated constructor stub
	}

	public String LaunchQBR() {
		System.out.println("I am in LoginPage Class");
		LoginPageObjects.launchLogin();
		String landingPgTitle = LoginPage.getLandingPgTitle();	
		return landingPgTitle;
	}

	public static String getLandingPgTitle() {
		// TODO Auto-generated method stub
		return null;
	}

}
