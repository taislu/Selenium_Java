package module7;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class CaseStudy_805 {
	
	static WebDriver driver;
	static WebDriverWait wait;
	static int maxWaitSeconds = 30;
	private static String url = "https://www.flipkart.com/";
	private static By pageEndWebE = By.xpath("//*[@href='https://www.flipkart.com/ol?link=http://www.youtube.com/flipkart']");
	
  @Test ( priority = 1, enabled = true)
  public void test_searchSeleniumBook() {
	  
	  	logInfo("-------------------------------------------------------");
		logInfo("@Test Running test_searchSeleniumBook() ---------------");
		
		pageWait();
		
		logInfo("Enter selenium book");
		FindElement(driver, By.xpath("//input[@name='q']"), 10).sendKeys("selenium book");
		
		logInfo("Click search");
		FindElement(driver, By.xpath("//button[@class='vh79eN']"), 10).click();
		
		pageWait();
		
		logInfo("Click a SELENIUM book");
		FindElement(driver, By.xpath("//img[@alt='A Practitoner s Guide To Test Automation Using Selenium 1 Edition']"), 30).click();
		
		pageWait();		
  }
  
  @Test ( priority = 2, dependsOnMethods = {"test_searchSeleniumBook"}, enabled = true)
  public void test_checkDelivery() {
	  
	    logInfo("-------------------------------------------------------");
		logInfo("@Test Running test_checkDelivery() --------------------");
	/*
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());   
		//Use the list of window handles to switch between windows   
		String handleName = tabs.get(1);      
		driver.switchTo().window(handleName); 
	*/
		logInfo("Current Handle : " + driver.getWindowHandle());
		logInfo("Current Title  : " + driver.getTitle());
		
		Set<String> handles = driver.getWindowHandles();
		for(String h : handles ) {
			
			driver.switchTo().window(h);
			
			logInfo("Handle : " + driver.getWindowHandle());
			logInfo("Title  : " + driver.getTitle());
			
			//if(h.equals(handle)) {
			//	continue;
			//}
			 
		}
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='_2aK_gu']")));
		
		logInfo("Enter Delivery Pincode 560034");
		FindElement(driver, By.xpath("//*[@id=\"pincodeInputId\"]"), 30).sendKeys("560034");
		
		logInfo("Click Check");
		FindElement(driver, By.xpath("//span[@class='_2aK_gu']"), 10).click();
		
		String delivery = FindElement(driver, By.xpath("//span[@class='_3nCwDW']"), 10).getText();
		logInfo("Delivery Text : " + delivery);
	
  }
  
  @Test ( priority = 3, dependsOnMethods = {"test_searchSeleniumBook"}, enabled = true)
  public void test_addCartCheckOut() {
	  
	    logInfo("-------------------------------------------------------");
		logInfo("@Test Running test_addCartCheckOut() ------------------");
		
		logInfo("Current Handle : " + driver.getWindowHandle());
		logInfo("Current Title  : " + driver.getTitle());
		
		Set<String> handles = driver.getWindowHandles();
		for(String h : handles ) {
			
			driver.switchTo().window(h);
			
			logInfo("Handle : " + driver.getWindowHandle());
			logInfo("Title  : " + driver.getTitle());
			
			/*
			if(h.equals(handle)) {
				continue;
			}
			 */
		}
		
		logInfo("Click BUY NOW");
		FindElement(driver, By.xpath("//button[@class='_2AkmmA _2Npkh4 _2kuvG8 _7UHT_c']"), 30).click();
		
		logInfo("Enter Email");
		FindElement(driver, By.xpath("//input[@class='_2zrpKA _14H79F']"), 10).sendKeys("test@gmail.com");
		
		logInfo("Click Continue");
		FindElement(driver, By.xpath("//button[@class='_2AkmmA _1poQZq _7UHT_c']"), 10).click();
		
		logInfo("Enter Password");
		FindElement(driver, By.xpath("//input[@class='_2zrpKA _3v41xv _14H79F']"), 10).sendKeys("test@gmail.com");
		
		logInfo("Click LOGIN");
		FindElement(driver, By.xpath("//button[@class='_2AkmmA _1poQZq _7UHT_c']"), 10).click();
		
		try {
			Thread.sleep(3000);
		} catch(Exception e) {
			logInfo("Thread.sleep Exception : " + e.getMessage());
		}
		
		logInfo("Take Screen shot");
		String fn = "C:\\2018\\edureka\\selenium\\Edureka_23July\\screenshots\\Flipkart_login_error.png";
		takeScreenShot(driver, fn);
  }
  
  @BeforeTest
  public void env_Setup() {
	  
	    logInfo("@BeforeTest Running env_Setup() ........");
		
		logInfo("browserSetup()  ********");
		browserSetup();
	  
  }

  @AfterTest
  public void shutdown() {
	  
	    logInfo("@AfterTest Running shutdown() ........");
		
		//logInfo("browserClose()  ********");
		//browserClose();
		
		logInfo("DONE!");
	  
  }
  
  	private static void pageWait() {
  		
  		logInfo("WebDriver Wait on loading complete");
		wait.until(ExpectedConditions.elementToBeClickable(pageEndWebE));
  	}
  
  	private static void browserClose() {
		
		logInfo("Closing chrome");
		driver.quit();
	}

	private static void browserSetup() {
		
		logInfo("Start chrome browser");
		System.setProperty("webdriver.chrome.driver", "C:\\2018\\edureka\\selenium\\EXEs\\chromedriver.exe");
		driver = new ChromeDriver();
		
		logInfo("Set the page load timeout for any page load");
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		
		logInfo("Navigate to url : " + url);
		driver.navigate().to(url);
		
		logInfo("Maximize window");
		driver.manage().window().maximize();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		logInfo("Set implicit wait for all the activities on the browser");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		logInfo("Set selenium script timeout");
		driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
	
		logInfo("Execute any asynchronous script");
		js.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 500);");
		
		logInfo("WebDriver Wait maxWaitSeconds : " + maxWaitSeconds);
		wait = new WebDriverWait(driver, maxWaitSeconds);
		
		logInfo("Close Login pop-up");
		FindElement(driver, By.xpath("//button[@class=\"_2AkmmA _29YdH8\"]"), 30).click();
	}
	
	private static WebElement FindElement(WebDriver driver, By by, int timeoutInSeconds) {
		
		try {
			
			WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			
			logInfo( "FindElement *** " + by + " *** Found");
			return driver.findElement(by);
			
		} catch (Exception e) {
			//e.printStackTrace();
			logInfo("Exception : " + e.getMessage() + " x-x-x-x-x-x");
		}
		
		logInfo( "FindElement --> " + by + " --> Not found");
		return null;
	}
	
	private static void logInfo(String msg) {
		
		System.out.println( LocalDateTime.now() + " : " + msg );
		
	}
	
	public static void takeScreenShot(WebDriver driver, String filename) {
		
		try {
			
			TakesScreenshot ts = (TakesScreenshot) driver;
			
			File f = ts.getScreenshotAs(OutputType.FILE);
			
			FileHandler.copy(f, new File(filename) );
			
		} catch(Exception e) {
			
			logInfo("takeScreenShot() : " + e.getMessage());
		}
		
	}

}

/*Output
[RemoteTestNG] detected TestNG version 6.14.2
2018-08-05T10:20:12.356 : @BeforeTest Running env_Setup() ........
2018-08-05T10:20:12.465 : browserSetup()  ********
2018-08-05T10:20:12.472 : Start chrome browser
Starting ChromeDriver 2.41.578737 (49da6702b16031c40d63e5618de03a32ff6c197e) on port 44158
Only local connections are allowed.
Aug 05, 2018 10:20:19 AM org.openqa.selenium.remote.ProtocolHandshake createSession
INFO: Detected dialect: OSS
2018-08-05T10:20:20.679 : Set the page load timeout for any page load
2018-08-05T10:20:20.816 : Navigate to url : https://www.flipkart.com/
2018-08-05T10:20:29.803 : Maximize window
2018-08-05T10:20:31.154 : Set implicit wait for all the activities on the browser
2018-08-05T10:20:31.162 : Set selenium script timeout
2018-08-05T10:20:31.174 : Execute any asynchronous script
2018-08-05T10:20:32.066 : WebDriver Wait maxWaitSeconds : 30
2018-08-05T10:20:32.073 : Close Login pop-up
2018-08-05T10:20:32.675 : FindElement *** By.xpath: //button[@class="_2AkmmA _29YdH8"] *** Found
2018-08-05T10:20:33.122 : -------------------------------------------------------
2018-08-05T10:20:33.122 : @Test Running test_searchSeleniumBook() ---------------
2018-08-05T10:20:33.122 : WebDriver Wait on loading complete
2018-08-05T10:20:33.247 : Enter selenium book
2018-08-05T10:20:33.310 : FindElement *** By.xpath: //input[@name='q'] *** Found
2018-08-05T10:20:33.627 : Click search
2018-08-05T10:20:33.664 : FindElement *** By.xpath: //button[@class='vh79eN'] *** Found
2018-08-05T10:20:33.939 : WebDriver Wait on loading complete
2018-08-05T10:20:34.270 : Click a SELENIUM book
2018-08-05T10:20:35.660 : FindElement *** By.xpath: //img[@alt='A Practitoner s Guide To Test Automation Using Selenium 1 Edition'] *** Found
2018-08-05T10:20:35.994 : WebDriver Wait on loading complete
2018-08-05T10:20:36.308 : -------------------------------------------------------
2018-08-05T10:20:36.308 : @Test Running test_checkDelivery() --------------------
2018-08-05T10:20:36.311 : Current Handle : CDwindow-DEBBBD1E0867C6103BB0F12750AE86A3
2018-08-05T10:20:36.319 : Current Title  : Online Shopping Site for Mobiles, Fashion, Books, Electronics, Home Appliances and More
2018-08-05T10:20:36.355 : Handle : CDwindow-DEBBBD1E0867C6103BB0F12750AE86A3
2018-08-05T10:20:36.458 : Title  : Online Shopping Site for Mobiles, Fashion, Books, Electronics, Home Appliances and More
2018-08-05T10:20:36.527 : Handle : CDwindow-DB28B258FC5375F217D4D5FE270F9177
2018-08-05T10:20:38.216 : Title  : A Practitoner s Guide To Test Automation Using Selenium 1 Edition - Buy A Practitoner s Guide To Test Automation Using Selenium 1 Edition Online at Best Prices in India - Flipkart.com
2018-08-05T10:20:39.308 : Enter Delivery Pincode 560034
2018-08-05T10:20:39.344 : FindElement *** By.xpath: //*[@id="pincodeInputId"] *** Found
2018-08-05T10:20:39.563 : Click Check
2018-08-05T10:20:39.583 : FindElement *** By.xpath: //span[@class='_2aK_gu'] *** Found
2018-08-05T10:20:39.919 : FindElement *** By.xpath: //span[@class='_3nCwDW'] *** Found
2018-08-05T10:20:40.007 : Delivery Text : 4-5 days
2018-08-05T10:20:40.012 : -------------------------------------------------------
2018-08-05T10:20:40.013 : @Test Running test_addCartCheckOut() ------------------
2018-08-05T10:20:40.018 : Current Handle : CDwindow-DB28B258FC5375F217D4D5FE270F9177
2018-08-05T10:20:40.025 : Current Title  : A Practitoner s Guide To Test Automation Using Selenium 1 Edition - Buy A Practitoner s Guide To Test Automation Using Selenium 1 Edition Online at Best Prices in India - Flipkart.com
2018-08-05T10:20:40.085 : Handle : CDwindow-DEBBBD1E0867C6103BB0F12750AE86A3
2018-08-05T10:20:40.098 : Title  : Online Shopping Site for Mobiles, Fashion, Books, Electronics, Home Appliances and More
2018-08-05T10:20:40.167 : Handle : CDwindow-DB28B258FC5375F217D4D5FE270F9177
2018-08-05T10:20:40.183 : Title  : A Practitoner s Guide To Test Automation Using Selenium 1 Edition - Buy A Practitoner s Guide To Test Automation Using Selenium 1 Edition Online at Best Prices in India - Flipkart.com
2018-08-05T10:20:40.183 : Click BUY NOW
2018-08-05T10:20:41.306 : FindElement *** By.xpath: //button[@class='_2AkmmA _2Npkh4 _2kuvG8 _7UHT_c'] *** Found
2018-08-05T10:20:41.508 : Enter Email
2018-08-05T10:20:47.140 : FindElement *** By.xpath: //input[@class='_2zrpKA _14H79F'] *** Found
2018-08-05T10:20:47.352 : Click Continue
2018-08-05T10:20:47.386 : FindElement *** By.xpath: //button[@class='_2AkmmA _1poQZq _7UHT_c'] *** Found
2018-08-05T10:20:48.386 : Enter Password
2018-08-05T10:20:48.628 : FindElement *** By.xpath: //input[@class='_2zrpKA _3v41xv _14H79F'] *** Found
2018-08-05T10:20:48.919 : Click LOGIN
2018-08-05T10:20:48.939 : FindElement *** By.xpath: //button[@class='_2AkmmA _1poQZq _7UHT_c'] *** Found
2018-08-05T10:20:52.042 : Take Screen shot
2018-08-05T10:20:52.482 : @AfterTest Running shutdown() ........
2018-08-05T10:20:52.482 : DONE!
PASSED: test_searchSeleniumBook
PASSED: test_checkDelivery
PASSED: test_addCartCheckOut

===============================================
    Default test
    Tests run: 3, Failures: 0, Skips: 0
===============================================


===============================================
Default suite
Total tests run: 3, Failures: 0, Skips: 0
===============================================

*/
 

