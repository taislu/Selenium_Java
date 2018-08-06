package module5;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CaseStudy_802 {
	
	static WebDriver driver;
	static WebDriverWait wait;
	static int maxWaitSeconds = 30;
	private static String url = "https://www.edureka.co/";
	static String userID;
	static String passwd;

	public static void main(String[] args) throws Exception {
		
		logInfo("getUserLogin()  ********");
		getUserLogin();
		
		logInfo("browserSetup()  ********");
		browserSetup();
		
		logInfo("test_Login()  ********");
		test_Login();
		
		logInfo("test_UpdateProfessionalDetails()  ********");
		test_UpdateProfessionalDetails();
	
		logInfo("test_UpdatePersonalDetails()  ********");
	    test_UpdatePersonalDetails();
	    
	    logInfo("test_blogs()  ********");
	    test_blogs();
		
	    logInfo("browserClose()  ********");
		browserClose();
		
		logInfo("DONE!");
	
	}
	
	private static void browserClose() {
		
		logInfo("Closing chrome");
		driver.quit();
	}
	
	private static void test_blogs() {
		
		logInfo("Navigate to Main Page");
		driver.navigate().to("https://www.edureka.co/");
		
		logInfo("WebDriver Wait on page loading complete");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://www.youtube.com/user/edurekaIN']")));
		
		logInfo("Click Community");
		FindElement(driver, By.id("dropdownMenu4"), 4).click();
		
		logInfo("Navigate to Blog");
		driver.navigate().to("https://www.edureka.co/blog");
		
	/*
		WebElement menuUL = driver.findElement(By.xpath("//*[@class='dropdown-menu dropdown-menu-right caret_drop']"));
		List<WebElement> menuList = menuUL.findElements(By.tagName("a"));
		for( WebElement li : menuList) {
		   System.out.println(li.getText());
		   if (li.getText().equals("Blog")) {
		      li.click();
		      //li.isSelected();
		    }	
		}
	*/
		
		logInfo("WebDriver Wait on page loading complete");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://www.youtube.com/user/edurekaIN']")));
		
		logInfo("Click Logout");
		FindElement(driver, By.id("signin-logout"), 4).click();
		
		logInfo("WebDriver Wait on page loading complete");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://www.youtube.com/user/edurekaIN']")));
	}
	
	private static void test_UpdatePersonalDetails() {
		
		logInfo("Navigate to My Profile Page");
		driver.navigate().to("https://learning.edureka.co/my-profile");
		
		logInfo("WebDriver Wait on page loading complete");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://www.youtube.com/user/edurekaIN']")));
		
		logInfo("Click Editing personal_details");
		FindElement(driver, By.id("personal_details"), 4).click();
		
		logInfo("Enter Name");
		FindElement(driver, By.xpath("//input[@name='fullname']"), 4).clear();
		FindElement(driver, By.xpath("//input[@name='fullname']"), 4).sendKeys("Tai Sheng Lu");
		
		logInfo("Click Phone Number country dropdown");
		FindElement(driver, By.xpath("//button[@data-toggle='dropdown']"), 4).click();
		
		logInfo("Select dropdown United States");
		
	    WebElement countryUL = driver.findElement(By.xpath("//*[@class='dropdown-menu countrydrpdwn']"));
	    List<WebElement> countryList = countryUL.findElements(By.tagName("li"));
	    for( WebElement li : countryList) {
	    	//System.out.println(li.getText());
	    	if (li.getText().equals("United States")) {
	    		//li.click();
	    		li.isSelected();
	    	}	
	    }
	 
		logInfo("Enter Current Role");
		FindElement(driver, By.xpath("//input[@name='currentrole']"), 4).clear();
		FindElement(driver, By.xpath("//input[@name='currentrole']"), 4).sendKeys("Software Developer");
		
		logInfo("Select experience");
		WebElement w2 = FindElement(driver, By.xpath("//select[@id='experience']"), 4);
		Select experience = new Select(w2);
		experience.selectByIndex(4);
		
		logInfo("Click Continue");
		FindElement(driver, By.xpath("(//button[@type='button'])[2]"), 4).click();
		
		logInfo("Click Continue again");
		FindElement(driver, By.xpath("(//button[@type='button'])[2]"), 4).click();
		
	}
	
	private static void test_UpdateProfessionalDetails() {
		
		logInfo("Navigate to My Profile Page");
		driver.navigate().to("https://learning.edureka.co/my-profile");
		
		logInfo("WebDriver Wait on page loading complete");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://www.youtube.com/user/edurekaIN']")));
	
		logInfo("Click Editing professional_details");
		FindElement(driver, By.id("professional_details"), 4).click();
		
		logInfo("Enter Company Name");
		FindElement(driver, By.xpath("//input[@name='companyName']"), 4).clear();
		FindElement(driver, By.xpath("//input[@name='companyName']"), 4).sendKeys("Tech Mahindra");
		
		logInfo("Select Job Level");
		WebElement w1 = FindElement(driver, By.xpath("//select[@name='currentjob']"), 4);
		Select job = new Select(w1);
		job.selectByIndex(3);
		
		logInfo("Select Industry");
		WebElement w2 = FindElement(driver, By.xpath("//select[@name='currentIndustry']"), 4);
		Select industry = new Select(w2);
		industry.selectByIndex(11);
		
		logInfo("Enter Linkedin Profile");
		String myLinkedIn = "https://www.linkedin.com/in/tai-sheng-lu-9229b23a";
		FindElement(driver, By.xpath("//input[@name='linkedinLink']"), 4).clear();
		FindElement(driver, By.xpath("//input[@name='linkedinLink']"), 4).sendKeys(myLinkedIn);
		
		logInfo("Enter Skills");
		String mySkills = "Python and Java Programming etc";
		FindElement(driver, By.xpath("//input[@name='userSkill']"), 4).clear();
		FindElement(driver, By.xpath("//input[@name='userSkill']"), 4).sendKeys(mySkills);
		
		logInfo("Click Next");
		FindElement(driver, By.xpath("//button[@type='submit']"), 4).click();
		
		logInfo("Click Next again");
		FindElement(driver, By.xpath("//button[@type='submit']"), 4).click();
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

/* Output
2018-08-02T17:45:18.550 : getUserLogin()  ********
2018-08-02T17:45:18.644 : Retrieve userID and password from Excel sheet 1st row : column 0 & 1
2018-08-02T17:45:19.581 : UserID : taislu@hotmail.com, Passwd : xxxxxxxx
2018-08-02T17:45:19.581 : browserSetup()  ********
2018-08-02T17:45:19.581 : Start chrome browser
Starting ChromeDriver 2.40.565498 (ea082db3280dd6843ebfb08a625e3eb905c4f5ab) on port 43876
Only local connections are allowed.
Aug 02, 2018 5:45:23 PM org.openqa.selenium.remote.ProtocolHandshake createSession
INFO: Detected dialect: OSS
2018-08-02T17:45:23.761 : Set the page load timeout for any page load
2018-08-02T17:45:23.784 : Navigate to url : https://www.edureka.co/
2018-08-02T17:45:28.476 : Maximize window
2018-08-02T17:45:29.652 : Set implicit wait for all the activities on the browser
2018-08-02T17:45:29.663 : Set selenium script timeout
2018-08-02T17:45:29.670 : Execute any asynchronous script
2018-08-02T17:45:30.466 : WebDriver Wait maxWaitSeconds : 30
2018-08-02T17:45:30.483 : test_Login()  ********
2018-08-02T17:45:30.483 : Click Log In
2018-08-02T17:45:31.165 : FindElement *** By.linkText: Log In *** Found
2018-08-02T17:45:31.396 : WebDriver Wait on LOGIN button to be clickable
2018-08-02T17:45:32.045 : Enter Email ID
2018-08-02T17:45:32.076 : FindElement *** By.id: inputName *** Found
2018-08-02T17:45:32.417 : Enter Password
2018-08-02T17:45:32.454 : FindElement *** By.id: pwd1 *** Found
2018-08-02T17:45:32.718 : Click LOGIN
2018-08-02T17:45:32.748 : FindElement *** By.xpath: (//button[@type='submit'])[4] *** Found
2018-08-02T17:45:32.947 : WebDriver Wait on login complete
2018-08-02T17:45:38.449 : test_UpdateProfessionalDetails()  ********
2018-08-02T17:45:38.449 : Navigate to My Profile Page
2018-08-02T17:45:42.104 : WebDriver Wait on page loading complete
2018-08-02T17:45:42.197 : Click Editing professional_details
2018-08-02T17:45:42.231 : FindElement *** By.id: professional_details *** Found
2018-08-02T17:45:42.527 : Enter Company Name
2018-08-02T17:45:42.585 : FindElement *** By.xpath: //input[@name='companyName'] *** Found
2018-08-02T17:45:42.708 : FindElement *** By.xpath: //input[@name='companyName'] *** Found
2018-08-02T17:45:42.918 : Select Job Level
2018-08-02T17:45:42.953 : FindElement *** By.xpath: //select[@name='currentjob'] *** Found
2018-08-02T17:45:43.086 : Select Industry
2018-08-02T17:45:43.108 : FindElement *** By.xpath: //select[@name='currentIndustry'] *** Found
2018-08-02T17:45:43.313 : Enter Linkedin Profile
2018-08-02T17:45:43.336 : FindElement *** By.xpath: //input[@name='linkedinLink'] *** Found
2018-08-02T17:45:43.409 : FindElement *** By.xpath: //input[@name='linkedinLink'] *** Found
2018-08-02T17:45:43.987 : Enter Skills
2018-08-02T17:45:44.012 : FindElement *** By.xpath: //input[@name='userSkill'] *** Found
2018-08-02T17:45:44.085 : FindElement *** By.xpath: //input[@name='userSkill'] *** Found
2018-08-02T17:45:44.639 : Click Next
2018-08-02T17:45:44.666 : FindElement *** By.xpath: //button[@type='submit'] *** Found
2018-08-02T17:45:44.808 : Click Next again
2018-08-02T17:45:44.840 : FindElement *** By.xpath: //button[@type='submit'] *** Found
2018-08-02T17:45:44.918 : test_UpdatePersonalDetails()  ********
2018-08-02T17:45:44.918 : Navigate to My Profile Page
2018-08-02T17:45:46.604 : WebDriver Wait on page loading complete
2018-08-02T17:45:46.658 : Click Editing personal_details
2018-08-02T17:45:46.677 : FindElement *** By.id: personal_details *** Found
2018-08-02T17:45:47.046 : Enter Name
2018-08-02T17:45:47.123 : FindElement *** By.xpath: //input[@name='fullname'] *** Found
2018-08-02T17:45:47.207 : FindElement *** By.xpath: //input[@name='fullname'] *** Found
2018-08-02T17:45:47.459 : Click Phone Number country dropdown
2018-08-02T17:45:47.486 : FindElement *** By.xpath: //button[@data-toggle='dropdown'] *** Found
2018-08-02T17:45:47.682 : Select dropdown United States
2018-08-02T17:45:54.593 : Enter Current Role
2018-08-02T17:45:54.622 : FindElement *** By.xpath: //input[@name='currentrole'] *** Found
2018-08-02T17:45:54.745 : FindElement *** By.xpath: //input[@name='currentrole'] *** Found
2018-08-02T17:45:55.237 : Select experience
2018-08-02T17:45:55.263 : FindElement *** By.xpath: //select[@id='experience'] *** Found
2018-08-02T17:45:55.395 : Click Continue
2018-08-02T17:45:55.418 : FindElement *** By.xpath: (//button[@type='button'])[2] *** Found
2018-08-02T17:45:55.511 : Click Continue again
2018-08-02T17:45:55.527 : FindElement *** By.xpath: (//button[@type='button'])[2] *** Found
2018-08-02T17:45:55.593 : test_blogs()  ********
2018-08-02T17:45:55.593 : Navigate to Main Page
2018-08-02T17:45:57.914 : WebDriver Wait on page loading complete
2018-08-02T17:45:57.988 : Click Community
2018-08-02T17:45:58.019 : FindElement *** By.id: dropdownMenu4 *** Found
2018-08-02T17:45:58.164 : Navigate to Blog
2018-08-02T17:46:00.352 : WebDriver Wait on page loading complete
2018-08-02T17:46:00.426 : Click Logout
2018-08-02T17:46:00.455 : FindElement *** By.id: signin-logout *** Found
2018-08-02T17:46:01.158 : WebDriver Wait on page loading complete
2018-08-02T17:46:01.195 : browserClose()  ********
2018-08-02T17:46:01.195 : Closing chrome
2018-08-02T17:46:01.994 : DONE!

 */
 

