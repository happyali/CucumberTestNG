package com.qbr.cucumberRunner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/com/qantas/features/Login.feature",
								plugin = {"json:target/cucumber-json-reports/login.json"},
								tags = {"~@wip", "~@norun"},
								glue = {"com.qbr.testStepsGlue"},
								monochrome = true)

  
public class LoginRunner extends AbstractTestNGCucumberTests {

}
