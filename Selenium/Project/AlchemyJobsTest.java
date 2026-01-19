package alchemyjobs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AlchemyJobsTest {

    WebDriver driver;

    public void openBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    // 1. Verify website title
    public void verifyWebsiteTitle() {
        openBrowser();
        driver.get("https://alchemy.hguy.co/jobs/");

        String title = driver.getTitle();
        if (title.equals("Alchemy Jobs – Job Board Application")) {
            System.out.println("Title verified successfully");
        }

        driver.quit();
    }

    // 2. Verify website heading
    public void verifyWebsiteHeading() {
        openBrowser();
        driver.get("https://alchemy.hguy.co/jobs/");

        String heading = driver.findElement(By.tagName("h1")).getText();
        if (heading.equals("Welcome to Alchemy Jobs")) {
            System.out.println("Heading verified successfully");
        }

        driver.quit();
    }

    // 3. Get header image URL
    public void getHeaderImageURL() {
        openBrowser();
        driver.get("https://alchemy.hguy.co/jobs/");

        WebElement image = driver.findElement(By.cssSelector("header img"));
        System.out.println("Header Image URL: " + image.getAttribute("src"));

        driver.quit();
    }

    // 4. Verify second heading
    public void verifySecondHeading() {
        openBrowser();
        driver.get("https://alchemy.hguy.co/jobs/");

        List<WebElement> headings = driver.findElements(By.tagName("h2"));
        if (headings.get(0).getText().equals("Quia quis non")) {
            System.out.println("Second heading verified");
        }

        driver.quit();
    }

    // 5. Navigate to Jobs page
    public void navigateToJobsPage() {
        openBrowser();
        driver.get("https://alchemy.hguy.co/jobs/");

        driver.findElement(By.linkText("Jobs")).click();

        if (driver.getTitle().contains("Jobs")) {
            System.out.println("Navigated to Jobs page successfully");
        }

        driver.quit();
    }

    // 6. Apply to a job
    public void applyToJob() {
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

    // 7. Create job listing (Frontend)
    public void createJobFrontend() {
        openBrowser();
        driver.get("https://alchemy.hguy.co/jobs/");

        driver.findElement(By.linkText("Post a Job")).click();

        driver.findElement(By.id("job_title")).sendKeys("Selenium Test Engineer");
        driver.findElement(By.id("job_location")).sendKeys("Remote");
        driver.findElement(By.id("application")).sendKeys("test@alchemy.com");
        driver.findElement(By.id("company_name")).sendKeys("Alchemy Corp");

        driver.findElement(By.name("submit_job")).click();
        driver.findElement(By.id("job_preview_submit_button")).click();

        System.out.println("Job created from frontend");

        driver.quit();
    }

    // 8. Login to backend
    public void loginBackend() {
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

    // 9. Create job using backend
    public void createJobBackend() {
        openBrowser();
        driver.get("https://alchemy.hguy.co/jobs/wp-admin");

        driver.findElement(By.id("user_login")).sendKeys("root");
        driver.findElement(By.id("user_pass")).sendKeys("pa$$w0rd");
        driver.findElement(By.id("wp-submit")).click();

        driver.findElement(By.linkText("Job Listings")).click();
        driver.findElement(By.linkText("Add New")).click();

        driver.findElement(By.id("title")).sendKeys("Backend Automation Engineer");
        driver.findElement(By.id("publish")).click();

        System.out.println("Job created using backend");

        driver.quit();
    }

    // Main method
    public static void main(String[] args) {

        AlchemyJobsTest test = new AlchemyJobsTest();

        test.verifyWebsiteTitle();
        test.verifyWebsiteHeading();
        test.getHeaderImageURL();
        test.verifySecondHeading();
        test.navigateToJobsPage();
        test.applyToJob();
        test.createJobFrontend();
        test.loginBackend();
        test.createJobBackend();
    }
}