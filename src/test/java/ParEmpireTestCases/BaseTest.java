package ParEmpireTestCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utilities.ExtentManager;


public class BaseTest {

	WebDriverWait wait;
	WebDriver driver;
	JavascriptExecutor js;
	
	public static ExtentReports extent;
    public static ExtentTest test;

	@BeforeSuite
	public void setupReport() {
		extent = ExtentManager.getExtentReports();
	}

	@AfterSuite
	public void tearDownReport() {
		extent.flush();
	}
    
	@BeforeMethod
	public void setUp() {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("start-maximized");
		option.addArguments("--headless");
		option.addArguments("--window-size=1920,1080");
	    option.addArguments("--no-sandbox");
		option.addArguments("--disable-dev-shm-usage");
		driver = new ChromeDriver(option);
		driver.get("https://parempire.com/");
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		js = (JavascriptExecutor) driver;

	}

	//@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	public void loginApplication() {
		 By myAccountBtnLocator = By.xpath("(//a[@href=\"/login\"])[1]");
		 WebElement myAccountBtn = wait.until(ExpectedConditions.elementToBeClickable(myAccountBtnLocator));
		 myAccountBtn.click();
		 
		 By emailInputLocator = By.cssSelector("#user_email");
		 wait.until(ExpectedConditions.visibilityOfElementLocated(emailInputLocator)).sendKeys("mukeshkumar01.gh@gmail.com");
		 
		 By passwordInputLocator = By.cssSelector("#user_password");
		 wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInputLocator)).sendKeys("11111111");
		 
		 By submitBtnLocator = By.xpath("//button[@type=\"submit\"]");
		 wait.until(ExpectedConditions.visibilityOfElementLocated(submitBtnLocator)).click();
	}
}
