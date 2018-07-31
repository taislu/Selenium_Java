package module3;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Synchronise {
	
	static WebDriver driver;
	private static String url = "https://www.edureka.co";
	
	@BeforeClass
	public static void setupTest() {
		
		logInfo("Start chrome browser");
		System.setProperty("webdriver.chrome.driver", "C:\\2018\\edureka\\selenium\\EXEs\\chromedriver.exe");
		driver = new ChromeDriver();
		
		logInfo("Set the page load timeout for any page load");
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		
		logInfo("Navigate to url : " + url);
		driver.navigate().to(url);
		
		logInfo("Maximize window");
		driver.manage().window().maximize();
		
	}
	
	@Test
	public void browserWaitExampleTest() throws Exception {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		logInfo("Set implicit wait for all the activities on the browser");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		logInfo("Set selenium script timeout");
		driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
	
		logInfo("Execute any asynchronous script");
		js.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 500);");
		
		// Start to find web elements
		logInfo("Search for Selenium course in the search box");
		WebElement searchBox = FindElement(driver, By.id("homeSearchBar"), 4);
		searchBox.sendKeys("Selenium");
		searchBox.sendKeys(Keys.RETURN);
		
		logInfo("Click selenium course");
		FindElement(driver, By.linkText("Selenium Certification Training"), 4).click();
	
		String validTitle = "Selenium 3.0 WebDriver Online Training | Selenium Certification Course | Edureka";
		String pageTitle = driver.getTitle();
		logInfo("getTitle : " + pageTitle);
		boolean result = pageTitle.equals(validTitle);
		logInfo("Validate the page title : Result *** " + result);
		
		logInfo("Navigate back");
		driver.navigate().back();
		
		logInfo("Click All Courses");
		String xpath_AllCourses = "(//a[contains(text(),'All Courses')])[2]";
		FindElement(driver, By.xpath(xpath_AllCourses), 4).click();
		
		WebElement element = FindElement(driver, By.id("loadMoreCoursesAll"), 4);
	
		logInfo("JavaScript page loading complete");
	}
	
	@AfterClass
	public static void quitDriver() {
		
		logInfo("Closing the browser");
		driver.quit();
		logInfo("DONE!");
	}
	
	private static WebElement FindElement(WebDriver driver, By by, int timeoutInSeconds) {
		
		try {
			
			WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			logInfo( "FindElement *** " + by + " *** Found");
			return driver.findElement(by);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logInfo( "FindElement --> " + by + " --> Not found");
		return null;
	}
	
	public static void logInfo(String msg) {
		
		System.out.println( LocalDateTime.now() + " : " + msg );
		
	}

}
