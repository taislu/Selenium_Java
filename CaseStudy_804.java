package module6;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class CaseStudy_804 {
	
	static WebDriver driver;
	static WebDriverWait wait;
	static int maxWaitSeconds = 30;
	private static String url = "https://www.edureka.co/";
	static String userID;
	static String passwd;
	
	@Test ( priority = 1, enabled = true)
	public void test_browseCourses() {
		
		logInfo("-------------------------------------------------------");
		logInfo("@Test Running test_browseCourses() ........");
		
		logInfo("Click Browse Courses");
		FindElement(driver, By.xpath("//button[@data-gaact=\"Browse Courses\"]"), 4).click();
		
		logInfo("WebDriver Wait on loading complete");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://www.youtube.com/user/edurekaIN']")));
	}
	
	@Test ( priority = 2, enabled = true)
	public void test_Logout() {
		
		logInfo("-------------------------------------------------------");
		logInfo("@Test Running test_Logout() ........");
		
		logInfo("Click Profile Picture");
		FindElement(driver, By.xpath("(//img[@class='img30'])[1]"), 4).click();
		
		logInfo("Click Log Out");
		FindElement(driver, By.xpath("//*[@href=\"https://www.edureka.co/signout\"]"), 4).click();
		
		logInfo("WebDriver Wait on logout complete");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://www.youtube.com/user/edurekaIN']")));
		
	}
	
	@Test ( priority = 3, enabled = true)
	public void test_RegisterWithExistedID() {
	
		logInfo("-------------------------------------------------------");
		logInfo("@Test Running test_RegisterWithExistedID() ........");
		
		logInfo("Click Sign Up");
		FindElement(driver, By.linkText("Sign Up"), 4).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[9]/div/div[1]/div[3]/div[2]/div/form/div[6]/button")));
		
		driver.findElement(By.id("signupname")).sendKeys("TEST");
		driver.findElement(By.id("signupemail")).sendKeys(userID);
		driver.findElement(By.id("signuptel")).sendKeys("1234567890");
		driver.findElement(By.id("pwd3")).sendKeys(passwd);
		
		driver.findElement(By.xpath("/html/body/div[9]/div/div[1]/div[3]/div[2]/div/form/div[6]/button")).click();
	}
	
	@BeforeTest
	public void env_Setup() {
		
		logInfo("@BeforeTest Running env_Setup() ........");
		
		logInfo("getUserLogin()  ********");
		getUserLogin();
		
		logInfo("browserSetup()  ********");
		browserSetup();
		
		logInfo("test_Login()  ********");
		test_Login();
		
	}

	@AfterTest
	public void shutdown() {
		
		logInfo("@AfterTest Running shutdown() ........");
		
		logInfo("browserClose()  ********");
		browserClose();
		
		logInfo("DONE!");
	}
	
	private static void browserClose() {
		
		logInfo("Closing chrome");
		driver.quit();
	}
  
  	private static void test_Login() {
		
		logInfo("Click Log In");
		FindElement(driver, By.linkText("Log In"), 4).click();
		
		logInfo("WebDriver Wait on LOGIN button to be clickable");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@type='submit'])[4]")));
		
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
	
	private static void getUserLogin() {
		
		logInfo("Retrieve userID and password from Excel sheet 1st row : column 0 & 1");
		ReadExcelSheets excel = new ReadExcelSheets("C:\\2018\\edureka\\selenium\\INPUT\\edureka_input.xlsx");
		userID = excel.getDataCell(0, 0, 0);
		passwd = excel.getDataCell(0, 0, 1);
		logInfo("UserID : " + userID + ", Passwd : " + passwd);
		
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

}

class ReadExcelSheets {
	
	XSSFWorkbook xlsx;
	//XSSFSheet sheet;
	
	public ReadExcelSheets(String file) {
		
		try {
			
			File f = new File(file);
			FileInputStream fs = new FileInputStream(f);
			xlsx = new XSSFWorkbook(fs);
			//sheet = xlsx.getSheetAt(0);
			
		} catch (Exception e) {
			
			//e.printStackTrace();
			System.out.println(e.getMessage());
		
		}
	}
	
	public String getDataCell(int sheetIndex, int rowIndex, int colIndex) {
		try {
			
			return xlsx.getSheetAt(sheetIndex).getRow(rowIndex).getCell(colIndex).getStringCellValue();
		
		} catch(Exception e) {
			
			System.out.println("getDataCell : " + e.getMessage());
		}
		
		return null;
	}
	
}

/*Output
[RemoteTestNG] detected TestNG version 6.14.2
2018-08-04T08:20:23.031 : @BeforeTest Running env_Setup() ........
2018-08-04T08:20:23.156 : getUserLogin()  ********
2018-08-04T08:20:23.160 : Retrieve userID and password from Excel sheet 1st row : column 0 & 1
2018-08-04T08:20:24.240 : UserID : taislu@hotmail.com, Passwd : New17127!
2018-08-04T08:20:24.240 : browserSetup()  ********
2018-08-04T08:20:24.240 : Start chrome browser
Starting ChromeDriver 2.40.565498 (ea082db3280dd6843ebfb08a625e3eb905c4f5ab) on port 21732
Only local connections are allowed.
[1533396036.320][WARNING]: Timed out connecting to Chrome, retrying...
Aug 04, 2018 8:20:40 AM org.openqa.selenium.remote.ProtocolHandshake createSession
INFO: Detected dialect: OSS
2018-08-04T08:20:40.799 : Set the page load timeout for any page load
2018-08-04T08:20:40.842 : Navigate to url : https://www.edureka.co/
2018-08-04T08:20:53.008 : Maximize window
2018-08-04T08:20:54.457 : Set implicit wait for all the activities on the browser
2018-08-04T08:20:54.464 : Set selenium script timeout
2018-08-04T08:20:54.473 : Execute any asynchronous script
2018-08-04T08:20:55.083 : WebDriver Wait maxWaitSeconds : 30
2018-08-04T08:20:55.128 : test_Login()  ********
2018-08-04T08:20:55.128 : Click Log In
2018-08-04T08:20:55.727 : FindElement *** By.linkText: Log In *** Found
2018-08-04T08:20:55.895 : WebDriver Wait on LOGIN button to be clickable
2018-08-04T08:20:56.511 : Enter Email ID
2018-08-04T08:20:56.542 : FindElement *** By.id: inputName *** Found
2018-08-04T08:20:56.873 : Enter Password
2018-08-04T08:20:56.906 : FindElement *** By.id: pwd1 *** Found
2018-08-04T08:20:57.106 : Click LOGIN
2018-08-04T08:20:57.126 : FindElement *** By.xpath: (//button[@type='submit'])[4] *** Found
2018-08-04T08:20:57.346 : WebDriver Wait on login complete
2018-08-04T08:21:02.149 : -------------------------------------------------------
2018-08-04T08:21:02.149 : @Test Running test_browseCourses() ........
2018-08-04T08:21:02.149 : Click Browse Courses
2018-08-04T08:21:02.194 : FindElement *** By.xpath: //button[@data-gaact="Browse Courses"] *** Found
2018-08-04T08:21:07.738 : WebDriver Wait on loading complete
2018-08-04T08:21:07.811 : -------------------------------------------------------
2018-08-04T08:21:07.811 : @Test Running test_Logout() ........
2018-08-04T08:21:07.811 : Click Profile Picture
2018-08-04T08:21:07.833 : FindElement *** By.xpath: (//img[@class='img30'])[1] *** Found
2018-08-04T08:21:08.014 : Click Log Out
2018-08-04T08:21:08.046 : FindElement *** By.xpath: //*[@href="https://www.edureka.co/signout"] *** Found
2018-08-04T08:21:10.512 : WebDriver Wait on logout complete
2018-08-04T08:21:10.679 : -------------------------------------------------------
2018-08-04T08:21:10.680 : @Test Running test_RegisterWithExistedID() ........
2018-08-04T08:21:10.680 : Click Sign Up
2018-08-04T08:21:12.488 : FindElement *** By.linkText: Sign Up *** Found
2018-08-04T08:21:15.229 : @AfterTest Running shutdown() ........
2018-08-04T08:21:15.229 : browserClose()  ********
2018-08-04T08:21:15.229 : Closing chrome
2018-08-04T08:21:16.290 : DONE!
PASSED: test_browseCourses
PASSED: test_Logout
PASSED: test_RegisterWithExistedID

===============================================
    Default test
    Tests run: 3, Failures: 0, Skips: 0
===============================================


===============================================
Default suite
Total tests run: 3, Failures: 0, Skips: 0
===============================================


 */