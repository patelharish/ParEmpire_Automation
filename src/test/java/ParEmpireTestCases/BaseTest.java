package ParEmpireTestCases;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
		//option.addArguments("--headless");
	    //option.addArguments("--no-sandbox");
		//option.addArguments("--disable-dev-shm-usage");
		option.addArguments("start-maximized");
		driver = new ChromeDriver(option);
		driver.get("https://www.parempire.com/");
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		js = (JavascriptExecutor) driver;

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
