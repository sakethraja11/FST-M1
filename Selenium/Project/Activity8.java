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

public class Activity8 {
	
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
	 public void contactForm() throws InterruptedException {
	 		 WebElement contact = driver.findElement(By.linkText("Contact"));
	 		contact.click();
	 		 
	 		 String title= driver.getTitle();
	 		 
	 		 driver.findElement(By.xpath("//input[@name=\"wpforms[fields][0]\"]")).sendKeys("Ritika");
	 		 driver.findElement(By.xpath("//input[@name=\"wpforms[fields][1]\"]")).sendKeys("Ritika03@gmail.com");
	 		 driver.findElement(By.xpath("//input[@name=\"wpforms[fields][3]\"]")).sendKeys("course enquiry");
	 		 driver.findElement(By.xpath("//div[@data-field-id='2']//child::textarea")).sendKeys("What is cost for Full stack development course?");
	 		 driver.findElement(By.xpath("//button[@type='submit']")).click();
	 		 WebElement confirmation = driver.findElement(By.xpath("//div[contains(@id,'wpforms-confirmation')]/p"));
	 		 wait.until(ExpectedConditions.visibilityOf(confirmation));
	 		 Assert.assertEquals(confirmation.getText(),"Thanks for contacting us! We will be in touch with you shortly." );


	 		 		
	 	}	
	 @AfterClass
	 public void tearDowun() {
		 driver.quit();
		 Reporter.log("Closing Chrome");
	 }
	 
	 


}