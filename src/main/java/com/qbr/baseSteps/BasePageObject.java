package com.qbr.baseSteps;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageObject<T> {
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected Logger log;
	
	protected BasePageObject (WebDriver driver) {
		this.driver = driver;
		wait =new WebDriverWait(driver, 30);
	}

	@SuppressWarnings("unchecked")
	protected T getPage(String url) {
		driver.get(url);
		return (T) this;
	}
	
	protected void type(String text, By element){
		find(element).sendKeys(text);
	}

	private WebElement find(By element) {
		return driver.findElement(element);
	}
	
	protected void click(By element){
		find(element).click();
	}
		
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public String getText(By element) {
		return find(element).getText();
	}


	protected void waitforVisibilityOf(By locator, Integer... timeOutInSeconds) { // ... means optional else mandatory
		int attempts =0;
		while (attempts < 2 ) {
			try {
				log.info("Waiting for visibitlity of "+locator);
				log.info(" timeOutInSeconds = "+ timeOutInSeconds[0]);
				log.info(" timeOutInSeconds.length = "+timeOutInSeconds.length);
				waitFor(ExpectedConditions.visibilityOfElementLocated(locator), (timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
				// if length of array `timeOutInSeconds` greater than 0 then return first element of `timeOutInSeconds` array or else return `NULL`
				// waitFor(ExpectedConditions.visibilityOfElementLocated(locator), attempts);
				break;
			} catch (StaleElementReferenceException e) {				
			}
			attempts++;
		}
	}

	private void waitFor(ExpectedCondition<WebElement> condition, Integer timeoutInSeconds) {
		log.info("Waiting time in sec: "+timeoutInSeconds);
		timeoutInSeconds = timeoutInSeconds != null ? timeoutInSeconds : 30;
		// If timeoutInSeconds is not NULL and if is NULL then timeoutInseconds = 30
		WebDriverWait wait =new WebDriverWait(driver, timeoutInSeconds);
		log.info("Waiting new time in sec: "+timeoutInSeconds);
		wait.until(condition);		
	}
	
	
}
