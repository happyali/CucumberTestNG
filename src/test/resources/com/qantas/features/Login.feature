@qbr-dashboard
Feature: STME-1821 QBR Authenticated Dashboard Page

	Background: 
	Given I am on QBR HomePage
	
	Scenario: QBR Authenticated Dashboard
		When I click button to login
		And I enter username
		And I enter password
		And I click on the login button
		Then I land on dashbaord page