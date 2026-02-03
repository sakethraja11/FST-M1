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

public class Activity6 {
	
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
	 public void loginSuccess() throws InterruptedException {
	 		 WebElement myAccount = driver.findElement(By.linkText("My Account"));
	 		 myAccount.click();
	 		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[@class=\"uagb-ifb-title\" and contains(text(),'My Account')]")));
	 		 //driver.manage().timeouts().wait(10);
	 		 
	 		 String title= driver.getTitle();
	 		 Assert.assertEquals(title,"My Account – Alchemy LMS");
	 		 driver.findElement(By.xpath("//a[contains(@class,'ld-login')]")).click();
	 		 driver.findElement(By.id("user_login")).sendKeys("root");
	 		 driver.findElement(By.id("user_pass")).sendKeys("pa$$w0rd");
	 		driver.findElement(By.id("wp-submit")).click();
	 		WebElement editprofile = driver.findElement(By.linkText("Edit profile"));
	 		Assert.assertTrue(editprofile.isDisplayed());
	 		
	 	}
		
	 @AfterClass
	 public void tearDowun() {
		 driver.quit();
		 Reporter.log("Closing Chrome");
	 }
	 
	 


}