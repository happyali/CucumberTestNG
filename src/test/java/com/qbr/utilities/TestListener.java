package com.qbr.utilities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.UUID;

import javax.imageio.ImageIO;	
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.qbr.baseSteps.BrowserSetup;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class TestListener implements ITestListener {

	private String screenShotsFilePath = "D:\\Workspace\\testNG_basic\\src\\test\\resources\\com\\qantas\\scrnShots\\";
	private WebDriver driver = null;
	
	@Override
	public void onStart(ITestContext arg0) {
		System.out.println(arg0.getCurrentXmlTest().getName() + " Test Start");
	}
	
	@Override
	public void onFinish(ITestContext arg0) {
		System.out.println(arg0.getCurrentXmlTest().getName() + " Test Finish");
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		System.out.println(arg0.getTestContext().getCurrentXmlTest().getName() + "Test Success"); //arg0.getName() + " -- PASS");
	}
	
	@Override
	public void onTestFailure(ITestResult arg0) {

		System.out.println(arg0.getTestContext().getCurrentXmlTest().getName() + "Test Failure");//arg0.getName() + " -- FAIL");		
		String instanceId = UUID.randomUUID().toString();
		String testName = arg0.getName() + "--" + instanceId;

		if (driver != null) {
			takeScreenShot(testName);
		}
	}

	private void takeScreenShot(String testName) {
		// Take screenshot using AShot
		BufferedImage bufferedImage = new AShot()
			.shootingStrategy(ShootingStrategies.viewportPasting(100))
			.takeScreenshot(driver).getImage();	
		
		// Below code snippet will take the screenshot and save into a folder
		try {
			ImageIO.write(bufferedImage, "PNG", new File(screenShotsFilePath + testName + ".png"));
			Reporter.log("<img src=\""+ screenShotsFilePath + testName + ".png\"/>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {		
	}
	
	@Override
	public void onTestSkipped(ITestResult arg0) {
		System.out.println(arg0.getName() + " -- SKIPPED");
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		driver = BrowserSetup.getDriverInstance();
	}

}
