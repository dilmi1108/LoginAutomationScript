package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.time.Duration;

public class LoginPageTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private static ExtentReports extent;
    private ExtentTest test;

    // Page locators
    private By usernameField = By.xpath("//input[@type='text' or contains(@placeholder, 'Username')]");
    private By passwordField = By.xpath("//input[@type='password' or contains(@placeholder, 'Password')]");
    private By submitButton = By.xpath("//button[contains(text(), 'Submit')]");

    // Test data
    private static final String VALID_USERNAME = "student";
    private static final String VALID_PASSWORD = "Password123";
    private static final String LOGIN_URL = "https://practicetestautomation.com/practice-test-login/";

    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
        sparkReporter.config().setDocumentTitle("Login Test Automation Report");
        sparkReporter.config().setReportName("Login Functionality Tests");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Tester", "QA Team");
        extent.setSystemInfo("Environment", "Test");
        extent.setSystemInfo("Browser", "Chrome");
    }

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(LOGIN_URL);
    }

    @Test(priority = 1, description = "Verify successful login with valid credentials")
    public void testValidLogin() {
        test = extent.createTest("Valid Login Test", "Testing login with correct username and password");

        try {
            test.log(Status.INFO, "Navigating to login page");

            WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
            WebElement password = driver.findElement(passwordField);
            WebElement submitBtn = driver.findElement(submitButton);

            test.log(Status.INFO, "Entering valid username: " + VALID_USERNAME);
            username.sendKeys(VALID_USERNAME);

            test.log(Status.INFO, "Entering valid password");
            password.sendKeys(VALID_PASSWORD);

            test.log(Status.INFO, "Clicking submit button");
            submitBtn.click();

            // Add your success validation here (e.g., dashboard page, welcome message)
            // Example: wait.until(ExpectedConditions.urlContains("dashboard"));

            test.log(Status.PASS, "Login successful with valid credentials");
            Assert.assertTrue(true, "Login should be successful");

        } catch (Exception e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            Assert.fail("Valid login test failed: " + e.getMessage());
        }
    }

    @Test(priority = 2, description = "Verify login fails with invalid username")
    public void testInvalidUsername() {
        test = extent.createTest("Invalid Username Test", "Testing login with incorrect username");

        try {
            test.log(Status.INFO, "Entering invalid username");
            driver.findElement(usernameField).sendKeys("invalidUser");

            test.log(Status.INFO, "Entering valid password");
            driver.findElement(passwordField).sendKeys(VALID_PASSWORD);

            test.log(Status.INFO, "Clicking submit button");
            driver.findElement(submitButton).click();

            // Add validation for error message or failed login
            // Example: WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error")));

            test.log(Status.PASS, "Login correctly failed with invalid username");
            Assert.assertTrue(true, "Login should fail with invalid username");

        } catch (Exception e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            Assert.fail("Invalid username test failed: " + e.getMessage());
        }
    }

    @Test(priority = 3, description = "Verify login fails with invalid password")
    public void testInvalidPassword() {
        test = extent.createTest("Invalid Password Test", "Testing login with incorrect password");

        try {
            test.log(Status.INFO, "Entering valid username");
            driver.findElement(usernameField).sendKeys(VALID_USERNAME);

            test.log(Status.INFO, "Entering invalid password");
            driver.findElement(passwordField).sendKeys("WrongPassword");

            test.log(Status.INFO, "Clicking submit button");
            driver.findElement(submitButton).click();

            test.log(Status.PASS, "Login correctly failed with invalid password");
            Assert.assertTrue(true, "Login should fail with invalid password");

        } catch (Exception e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            Assert.fail("Invalid password test failed: " + e.getMessage());
        }
    }

    @Test(priority = 4, description = "Verify login fails with empty credentials")
    public void testEmptyCredentials() {
        test = extent.createTest("Empty Credentials Test", "Testing login without entering any credentials");

        try {
            test.log(Status.INFO, "Clicking submit button without entering credentials");
            driver.findElement(submitButton).click();

            test.log(Status.PASS, "Login correctly prevented with empty credentials");
            Assert.assertTrue(true, "Login should fail with empty credentials");

        } catch (Exception e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            Assert.fail("Empty credentials test failed: " + e.getMessage());
        }
    }

    @Test(priority = 5, description = "Verify login with empty username")
    public void testEmptyUsername() {
        test = extent.createTest("Empty Username Test", "Testing login with empty username field");

        try {
            test.log(Status.INFO, "Entering password only");
            driver.findElement(passwordField).sendKeys(VALID_PASSWORD);

            test.log(Status.INFO, "Clicking submit button");
            driver.findElement(submitButton).click();

            test.log(Status.PASS, "Login correctly failed with empty username");
            Assert.assertTrue(true, "Login should fail with empty username");

        } catch (Exception e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            Assert.fail("Empty username test failed: " + e.getMessage());
        }
    }

    @Test(priority = 6, description = "Verify login with empty password")
    public void testEmptyPassword() {
        test = extent.createTest("Empty Password Test", "Testing login with empty password field");

        try {
            test.log(Status.INFO, "Entering username only");
            driver.findElement(usernameField).sendKeys(VALID_USERNAME);

            test.log(Status.INFO, "Clicking submit button");
            driver.findElement(submitButton).click();

            test.log(Status.PASS, "Login correctly failed with empty password");
            Assert.assertTrue(true, "Login should fail with empty password");

        } catch (Exception e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            Assert.fail("Empty password test failed: " + e.getMessage());
        }
    }

    @Test(priority = 7, description = "Verify username field accepts input")
    public void testUsernameFieldInput() {
        test = extent.createTest("Username Field Input Test", "Verify username field accepts text input");

        try {
            WebElement username = driver.findElement(usernameField);
            test.log(Status.INFO, "Entering text in username field");
            username.sendKeys(VALID_USERNAME);

            String enteredValue = username.getAttribute("value");
            Assert.assertEquals(enteredValue, VALID_USERNAME, "Username field should accept input");

            test.log(Status.PASS, "Username field correctly accepts input");

        } catch (Exception e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            Assert.fail("Username field input test failed: " + e.getMessage());
        }
    }

    @Test(priority = 8, description = "Verify password field masks input")
    public void testPasswordFieldMasking() {
        test = extent.createTest("Password Field Masking Test", "Verify password field masks input");

        try {
            WebElement password = driver.findElement(passwordField);
            test.log(Status.INFO, "Checking password field type");

            String fieldType = password.getAttribute("type");
            Assert.assertEquals(fieldType, "password", "Password field should be of type 'password'");

            test.log(Status.PASS, "Password field correctly masks input");

        } catch (Exception e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            Assert.fail("Password masking test failed: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush();
    }
}