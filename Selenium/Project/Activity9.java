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

public class Activity9 {
	
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
	 public void completeCourse() throws InterruptedException {
		 WebElement myAccount = driver.findElement(By.linkText("My Account"));
 		 myAccount.click();
 		 
 		 driver.findElement(By.xpath("//a[contains(@class,'ld-login')]")).click();
 		 driver.findElement(By.id("user_login")).sendKeys("root");
 		 driver.findElement(By.id("user_pass")).sendKeys("pa$$w0rd");
 		 driver.findElement(By.id("wp-submit")).click();
 		WebElement allCourses = wait.until(
 		        ExpectedConditions.elementToBeClickable(
 		                By.linkText("All Courses")
 		        )
 		);
 		allCourses.click();

		 driver.findElement(By.xpath("//h3[text()='Social Media Marketing']//following-sibling::p[2]/a")).click();
		 driver.findElement(By.xpath("(//div[@class='ld-item-title'])[1]")).click();
		 String progeress = driver.findElement(By.xpath("//span[@class='ld-lesson-list-progress']")).getText();
		 Assert.assertEquals(progeress, "100% COMPLETE");
		 
		 
	 }
	
	
	 @AfterClass
	 public void tearDowun() {
		 driver.quit();
		 Reporter.log("Closing Chrome");
	 }
	 
	 


}