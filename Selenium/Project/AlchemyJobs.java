package alchemyjobs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

public class AlchemyJobs {

    WebDriver driver;

    // Launch Browser
    void openBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    // 1. Verify Website Title
    void verifyTitle() {
        openBrowser();
        driver.get("https://alchemy.hguy.co/jobs/");

        String title = driver.getTitle();
        if (title.equals("Alchemy Jobs – Job Board Application")) {
            System.out.println("Website title verified");
        }
        driver.quit();
    }

    // 2. Verify Website Heading
    void verifyHeading() {
        openBrowser();
        driver.get("https://alchemy.hguy.co/jobs/");

        String heading = driver.findElement(By.tagName("h1")).getText();
        if (heading.equals("Welcome to Alchemy Jobs")) {
            System.out.println("Website heading verified");
        }
        driver.quit();
    }

    // 3. Get Header Image URL
    void getHeaderImageURL() {
        openBrowser();
        driver.get("https://alchemy.hguy.co/jobs/");

        WebElement image = driver.findElement(By.cssSelector("header img"));
        System.out.println("Header Image URL: " + image.getAttribute("src"));

        driver.quit();
    }

    // 4. Verify Second Heading
    void verifySecondHeading() {
        openBrowser();
        driver.get("https://alchemy.hguy.co/jobs/");

        List<WebElement> h2 = driver.findElements(By.tagName("h2"));
        if (h2.get(0).getText().equals("Quia quis non")) {
            System.out.println("Second heading verified");
        }
        driver.quit();
    }

    // 5. Navigate to Jobs Page
    void navigateToJobs() {
        openBrowser();
        driver.get("https://alchemy.hguy.co/jobs/");

        driver.findElement(By.linkText("Jobs")).click();
        if (driver.getTitle().contains("Jobs")) {
            System.out.println("Navigated to Jobs page");
        }
        driver.quit();
    }

    // 6. Apply to a Job
    void applyToJob() {
        openBrowser();
        driver.get("https://alchemy.hguy.co/jobs/");
        driver.findElement(By.linkText("Jobs")).click();

        driver.findElement(By.id("search_keywords")).sendKeys("Developer");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul.job_listings li")));

        driver.findElement(By.cssSelector("ul.job_listings li a")).click();

        String email = driver.findElement(By.className("job_application_email")).getText();
        System.out.println("Apply using email: " + email);

        driver.quit();
    }

    // 7. Create Job Listing (Frontend)
    void createJobFrontend() {
        openBrowser();
        driver.get("https://alchemy.hguy.co/jobs/");

        driver.findElement(By.linkText("Post a Job")).click();

        driver.findElement(By.id("job_title")).sendKeys("Selenium Tester");
        driver.findElement(By.id("job_location")).sendKeys("Remote");
        driver.findElement(By.id("application")).sendKeys("test@alchemy.com");
        driver.findElement(By.id("company_name")).sendKeys("Alchemy Corp");

        driver.findElement(By.name("submit_job")).click();
        driver.findElement(By.id("job_preview_submit_button")).click();

        System.out.println("Job posted from frontend");
        driver.quit();
    }

    // 8. Login to Backend
    void loginBackend() {
        openBrowser();
        driver.get("https://alchemy.hguy.co/jobs/wp-admin");

        driver.findElement(By.id("user_login")).sendKeys("root");
        driver.findElement(By.id("user_pass")).sendKeys("pa$$w0rd");
        driver.findElement(By.id("wp-submit")).click();

        if (driver.findElement(By.id("wpadminbar")).isDisplayed()) {
            System.out.println("Backend login successful");
        }
        driver.quit();
    }

    // 9. Create Job Listing from Backend
    void createJobBackend() {
        openBrowser();
        driver.get("https://alchemy.hguy.co/jobs/wp-admin");

        driver.findElement(By.id("user_login")).sendKeys("root");
        driver.findElement(By.id("user_pass")).sendKeys("pa$$w0rd");
        driver.findElement(By.id("wp-submit")).click();

        driver.findElement(By.linkText("Job Listings")).click();
        driver.findElement(By.linkText("Add New")).click();

        driver.findElement(By.id("title")).sendKeys("Backend QA Engineer");
        driver.findElement(By.id("publish")).click();

        System.out.println("Job created from backend");
        driver.quit();
    }

    // Main Method
    public static void main(String[] args) {

        AlchemyJobs test = new AlchemyJobs();

        test.verifyTitle();
        test.verifyHeading();
        test.getHeaderImageURL();
        test.verifySecondHeading();
        test.navigateToJobs();
        test.applyToJob();
        test.createJobFrontend();
        test.loginBackend();
        test.createJobBackend();
    }
}