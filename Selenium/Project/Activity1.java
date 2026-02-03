package Project;


import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class Activity1 {
	
	WebDriver driver ;
	WebDriverWait wait;
	
	 @BeforeClass
	    public void initiate() {
		 //wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        driver = new ChromeDriver();
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        driver.manage().window().maximize();
	        driver.get("https://alchemy.hguy.co/lms");
	        Reporter.log("Opening Chrome Browser", true);
	    }
	 
	 @Test
	 public void checkTitle() {
		 
		 String title= driver.getTitle();
		 Assert.assertEquals(title,"Alchemy LMS – An LMS Application");
			 
		 
	 }

	
	 @AfterClass
	 public void tearDowun() {
		 driver.quit();
		 Reporter.log("Closing Chrome");
	 }
	 
	 


}