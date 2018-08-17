package HelloMaven;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Upload_EdurekaPhoto {
	
	static WebDriver driver;
	static WebDriverWait wait;
	static int maxWaitSeconds = 30;
	private static String url = "https://www.edureka.co/";
	static String userID;
	static String passwd;

	@BeforeTest
	public void test_setup() {
		
		logInfo("browserSetup()  ********");
		browserSetup();

	}
	
	@Parameters({"userID", "passWORD"})
	@Test(priority=1)
	public void test_userLogin(String userid, String password) throws Exception {
		
		userID = userid;
		passwd = password;
		logInfo("UserID : " + userID + ", Passwd : " + passwd);
		
		logInfo("test_Login()  ********");
		test_Login();
	}
	
	@Test(priority=2)
	public void test_uploadProfilePhoto() throws Exception {
	
		logInfo("test_UpdatePersonalDetails()  ********");
	    test_UpdatePersonalDetails();
	    
	}
	
	@AfterTest
	public void closeBrowser() {
		
		//Thread.sleep(3000);
	    logInfo("browserClose()  ********");
		browserClose();
		
		logInfo("DONE!");
	}
	
	private static void browserClose() {
		
		logInfo("Closing chrome");
		driver.quit();
	}
	
	
	private static void test_UpdatePersonalDetails() throws Exception {
		
		logInfo("Navigate to My Profile Page");
		driver.navigate().to("https://learning.edureka.co/my-profile");
		
		logInfo("WebDriver Wait on page loading complete");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://www.youtube.com/user/edurekaIN']")));
	
		logInfo("Click Editing personal_details");
		FindElement(driver, By.id("personal_details"), 4).click();
		
		logInfo("Click Camera on profile picture");
		FindElement(driver, By.xpath("//i[@class='icon-camera']"), 4).click();
		
		logInfo("Pause for few seconds");
		Thread.sleep(3000);
		
		logInfo("Click Choose File");
		FindElement(driver, By.id("custom-input"), 4).click();
		
		logInfo("Pause for few seconds");
		Thread.sleep(6000);
		
		logInfo("Run AutoIT script to select the profile photo");
		String Photo = "C:\\2018\\edureka\\selenium\\AutoIT\\profile_photo_1.jpg";
		String upload = "C:\\2018\\edureka\\selenium\\AutoIT\\Upload_Edureka_ProfilePhoto.exe";
		String uploadPhoto = upload + " " + Photo;
		
		Runtime.getRuntime().exec(uploadPhoto);
		
		logInfo("Pause for few seconds");
		Thread.sleep(3000);
		
		logInfo("Click Upload");
		FindElement(driver, By.xpath("//button[@class='submitbtn']"), 4).click();
		
		logInfo("Navigate to home page");
		driver.navigate().to("https://www.edureka.co/");
		
		logInfo("Pause for few seconds");
		Thread.sleep(3000);
		
		logInfo("Click Profile Picture");
		driver.findElement(By.xpath("//*[@id='header-II']/section/nav/div/ul[2]/li[1]/a/img")).click();
		
		logInfo("Pause for few seconds");
		Thread.sleep(3000);
		
		logInfo("Click Log Out");
		List<WebElement> profileDropdown = driver.findElements(By.xpath("//ul[@class='dropdown-menu user-menu profile-xs hidden-sm hidden-xs']//li/a"));
		for(WebElement e: profileDropdown) {
			String str = e.getAttribute("innerHTML");
			if(str.contentEquals("Log Out")) {
				e.click();
				break;
			}
		}
		
		logInfo("Pause for few seconds");
		Thread.sleep(3000);

		
	}
	
	private static void test_Login() throws Exception {
		
		logInfo("Click Log In");
		FindElement(driver, By.linkText("Log In"), 4).click();
		
		logInfo("WebDriver Wait on LOGIN button to be clickable");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@type='submit'])[4]")));
		
		Thread.sleep(3000);
		
		logInfo("Enter Email ID");
		FindElement(driver, By.id("inputName"), 4).sendKeys(userID);
		
		logInfo("Enter Password");
		FindElement(driver, By.id("pwd1"), 4).sendKeys(passwd);
		
		logInfo("Click LOGIN");
		FindElement(driver, By.xpath("(//button[@type='submit'])[4]"), 4).click();
		
		logInfo("WebDriver Wait on login complete");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://www.youtube.com/user/edurekaIN']")));
	
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
		
	}
	

	private static WebElement FindElement(WebDriver driver, By by, int timeoutInSeconds) {
		
		try {
			
			WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			
			logInfo( "FindElement *** " + by + " *** Found");
			return driver.findElement(by);
			
		} catch (Exception e) {
			
			logInfo("Exception : " + e.getMessage() + " x-x-x-x-x-x");
		}
		
		logInfo( "FindElement --> " + by + " --> Not found");
		return null;
	}
	
	private static void logInfo(String msg) {
		
		System.out.println( LocalDateTime.now() + " : " + msg );
		
	}

}

/*
[RemoteTestNG] detected TestNG version 6.14.3
2018-08-15T14:42:44.048 : browserSetup()  ********
2018-08-15T14:42:44.168 : Start chrome browser
Starting ChromeDriver 2.41.578737 (49da6702b16031c40d63e5618de03a32ff6c197e) on port 7066
Only local connections are allowed.
[1534369376.403][WARNING]: Timed out connecting to Chrome, retrying...
Aug 15, 2018 2:42:57 PM org.openqa.selenium.remote.ProtocolHandshake createSession
INFO: Detected dialect: OSS
2018-08-15T14:42:57.704 : Set the page load timeout for any page load
2018-08-15T14:42:57.720 : Navigate to url : https://www.edureka.co/
2018-08-15T14:43:03.209 : Maximize window
2018-08-15T14:43:05.442 : Set implicit wait for all the activities on the browser
2018-08-15T14:43:05.451 : Set selenium script timeout
2018-08-15T14:43:05.461 : Execute any asynchronous script
2018-08-15T14:43:06.070 : WebDriver Wait maxWaitSeconds : 30
2018-08-15T14:43:06.127 : UserID : taislu@hotmail.com, Passwd : Testng99
2018-08-15T14:43:06.127 : test_Login()  ********
2018-08-15T14:43:06.127 : Click Log In
2018-08-15T14:43:06.704 : FindElement *** By.linkText: Log In *** Found
2018-08-15T14:43:07.106 : WebDriver Wait on LOGIN button to be clickable
2018-08-15T14:43:11.903 : Enter Email ID
2018-08-15T14:43:11.946 : FindElement *** By.id: inputName *** Found
2018-08-15T14:43:14.716 : Enter Password
2018-08-15T14:43:14.740 : FindElement *** By.id: pwd1 *** Found
2018-08-15T14:43:15.385 : Click LOGIN
2018-08-15T14:43:15.404 : FindElement *** By.xpath: (//button[@type='submit'])[4] *** Found
2018-08-15T14:43:15.780 : WebDriver Wait on login complete
2018-08-15T14:43:19.986 : test_UpdatePersonalDetails()  ********
2018-08-15T14:43:19.986 : Navigate to My Profile Page
2018-08-15T14:43:24.161 : WebDriver Wait on page loading complete
2018-08-15T14:43:24.268 : Click Editing personal_details
2018-08-15T14:43:24.301 : FindElement *** By.id: personal_details *** Found
2018-08-15T14:43:24.911 : Click Camera on profile picture
2018-08-15T14:43:24.988 : FindElement *** By.xpath: //i[@class='icon-camera'] *** Found
2018-08-15T14:43:25.167 : Pause for few seconds
2018-08-15T14:43:28.167 : Click Choose File
2018-08-15T14:43:28.208 : FindElement *** By.id: custom-input *** Found
2018-08-15T14:43:28.413 : Pause for few seconds
2018-08-15T14:43:34.414 : Run AutoIT script to select the profile photo
2018-08-15T14:43:34.830 : Pause for few seconds
2018-08-15T14:43:37.831 : Click Upload
2018-08-15T14:43:39.228 : FindElement *** By.xpath: //button[@class='submitbtn'] *** Found
2018-08-15T14:43:39.654 : Navigate to home page
2018-08-15T14:43:42.367 : Pause for few seconds
2018-08-15T14:43:45.368 : Click Profile Picture
2018-08-15T14:43:45.525 : Pause for few seconds
2018-08-15T14:43:48.526 : Click Log Out
2018-08-15T14:43:51.364 : Pause for few seconds
2018-08-15T14:43:54.368 : browserClose()  ********
2018-08-15T14:43:54.368 : Closing chrome
2018-08-15T14:43:55.245 : DONE!

===============================================
Suite
Total tests run: 2, Failures: 0, Skips: 0
===============================================
 */


