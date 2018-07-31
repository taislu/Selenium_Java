package module4;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TicketBooking {
	static WebDriver driver;
	private static String url = "https://www.goindigo.in/?linkNav=home_header";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
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
	public void test() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		logInfo("Set implicit wait for all the activities on the browser");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		logInfo("Set selenium script timeout");
		driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
	
		logInfo("Execute any asynchronous script");
		js.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 500);");
		
		// Start to find web elements
		logInfo("Click oneway");
		FindElement(driver, By.xpath("//a[@href='#oneWay']"), 4).click();
		
		sleepPause(1200);
	
		logInfo("Click pop-up ok");
		String xpathPop7 = "(//button[@type='button'])[7]";
		FindElement(driver, By.xpath(xpathPop7), 4).click();
	
		sleepPause(1200);
		
		logInfo("Click From");
		String xpath1 = "(//input[@placeholder='From'])[2]";
		FindElement(driver, By.xpath(xpath1), 4).click();
		
		logInfo("Click Bengaluru");
		String xpath2 = "(//a[contains(text(),'Bengaluru')])[2]";
		FindElement(driver, By.xpath(xpath2), 4).click();
		
		logInfo("Click To");
		String xpath3 = "(//input[@placeholder='To'])[2]";
		FindElement(driver, By.xpath(xpath3), 4).click();

		logInfo("Click Lucknow");
		String xpath4 = "(//a[contains(text(),'Lucknow')])[4]";
		FindElement(driver, By.xpath(xpath4), 4).click();
		
		logInfo("Select adult 3");
		String name0 = "indiGoOneWaySearch.PassengerCounts[0].Count";
		WebElement adult = FindElement(driver, By.name(name0), 4);
		Select adultNo = new Select(adult);
		adultNo.selectByIndex(2);
		
		logInfo("Select children 2");
		String name1 = "indiGoOneWaySearch.PassengerCounts[1].Count";
		WebElement children = FindElement(driver, By.name(name1), 4);
		Select childrenNo = new Select(children);
		childrenNo.selectByIndex(2);
		
		logInfo("Click Search Flight");
		String xpath_submit3 = "(//button[@type='submit'])[3]";
		FindElement(driver, By.xpath(xpath_submit3), 4).click();
	
		FindElement(driver, By.id("continue-button"), 4);
		
		logInfo("Flights list loading complete");
		
		logInfo("Page Title : " + driver.getTitle() );
	
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		logInfo("Closing chrome");
		driver.quit();
		logInfo("DONE!");
	}
	
	public static void sleepPause(int ms) {
		
		try {
			Thread.sleep(ms);
		} catch (Exception e) {
			logInfo("Exception : " + e.toString() );
		}
		
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

/* Output :
 * 
2018-07-30T22:26:15.630 : Start chrome browser
Starting ChromeDriver 2.40.565498 (ea082db3280dd6843ebfb08a625e3eb905c4f5ab) on port 17300
Only local connections are allowed.
Jul 30, 2018 10:26:21 PM org.openqa.selenium.remote.ProtocolHandshake createSession
INFO: Detected dialect: OSS
2018-07-30T22:26:22.036 : Set the page load timeout for any page load
2018-07-30T22:26:22.053 : Navigate to url : https://www.goindigo.in/?linkNav=home_header
2018-07-30T22:26:30.137 : Maximize window
2018-07-30T22:26:31.355 : Set implicit wait for all the activities on the browser
2018-07-30T22:26:31.365 : Set selenium script timeout
2018-07-30T22:26:31.370 : Execute any asynchronous script
2018-07-30T22:26:31.945 : Click oneway
2018-07-30T22:26:31.994 : FindElement *** By.xpath: //a[@href='#oneWay'] *** Found
2018-07-30T22:26:33.411 : Click pop-up ok
2018-07-30T22:26:33.460 : FindElement *** By.xpath: (//button[@type='button'])[7] *** Found
2018-07-30T22:26:34.855 : Click From
2018-07-30T22:26:34.891 : FindElement *** By.xpath: (//input[@placeholder='From'])[2] *** Found
2018-07-30T22:26:35.871 : Click Bengaluru
2018-07-30T22:26:35.951 : FindElement *** By.xpath: (//a[contains(text(),'Bengaluru')])[2] *** Found
2018-07-30T22:26:36.977 : Click To
2018-07-30T22:26:37.026 : FindElement *** By.xpath: (//input[@placeholder='To'])[2] *** Found
2018-07-30T22:26:40.844 : Click Lucknow
2018-07-30T22:26:40.884 : FindElement *** By.xpath: (//a[contains(text(),'Lucknow')])[4] *** Found
2018-07-30T22:26:41.388 : Select adult 3
2018-07-30T22:26:42.757 : FindElement *** By.name: indiGoOneWaySearch.PassengerCounts[0].Count *** Found
2018-07-30T22:26:44.027 : Select children 2
2018-07-30T22:26:44.057 : FindElement *** By.name: indiGoOneWaySearch.PassengerCounts[1].Count *** Found
2018-07-30T22:26:44.327 : Click Search Flight
2018-07-30T22:26:44.347 : FindElement *** By.xpath: (//button[@type='submit'])[3] *** Found
2018-07-30T22:26:48.919 : FindElement *** By.id: continue-button *** Found
2018-07-30T22:26:48.968 : Flights list loading complete
2018-07-30T22:26:48.988 : Page Title : Book flights Online for Domestic & International - IndiGo
2018-07-30T22:26:48.989 : Closing chrome
2018-07-30T22:26:56.769 : DONE!
 * 
 */
