package module8;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class Test_Gmail {
	
	WebDriver driver;
	private static String url = "https://www.gmail.com/";
	static String userID;
	static String passwd;
	
	@Test ( priority = 1, enabled = true)
	public void signinGmail() throws Exception {
		
		logInfo("------------------------------");
		logInfo("@Test : signinGmail() ........");
		driver.navigate().to(url);
		
		logInfo("Maximize window");
		driver.manage().window().maximize();
		
		//logInfo("Sleeping for few seconds");
		//Thread.sleep(3000);
		
		logInfo("Gmail_SigninUserID : submitUserID(userID)");
		Gmail_SigninUserID s1 = new Gmail_SigninUserID(driver);
		s1.submitUserID(userID);
		
		logInfo("Sleeping for few seconds");
		Thread.sleep(3000);
		
		logInfo("Gmail_SigninPassword : submitPassword(passwd)");
		Gmail_SigninPassword s2 = new Gmail_SigninPassword(driver);
		s2.submitPassword(passwd);
		
		logInfo("Sleeping for few seconds");
		Thread.sleep(6000);
		
	}
	
	@Test (dependsOnMethods = {"signinGmail"}, priority = 2, enabled = true )
	public void composeGmail() throws Exception {
		
		logInfo("-------------------------------");
		logInfo("@Test : composeGmail() ........");
		
		logInfo("Gmail_Compose : Create");
		Gmail_Compose gc = new Gmail_Compose(driver);
		
	/*	
		try {
			gc.closePopup();
		} catch(Exception e) {
			logInfo("Gmail_Compose : closePopup() failed : " + e.getMessage());
		}
	*/
		
		logInfo("Sleeping for few seconds");
		Thread.sleep(3000);
		
		String emailto = "taislu@hotmail.com";
		String subject = "Email Automation Test";
		String content = "This is an email sent by Selenium.";
		
		logInfo("Gmail_Compose : sendEmail(emailto, subject, content)");
		gc.sendEmail(emailto, subject, content);
		
		logInfo("Sleeping for few seconds");
		Thread.sleep(3000);
		
		logInfo("Gmail_Compose : logOut()");
		gc.logOut();
		
		logInfo("Sleeping for few seconds");
		Thread.sleep(3000);
		
	}
	
	@Test ( priority = 3, enabled = true)
	public void signinGmail_wPF() throws Exception {
		
		logInfo("----------------------------------");
		logInfo("@Test : signinGmail_wPF() ........");
		driver.navigate().to(url);
		
		logInfo("Maximize window");
		driver.manage().window().maximize();
		
	/*Only Password is required for the 2nd login after logout
		logInfo("Gmail_SigninUserID_wPF : Create");
		Gmail_SigninUserID_wPF s1 = new Gmail_SigninUserID_wPF(driver);
		s1.submitUserID(userID);
	*/
		
		logInfo("Sleeping for few seconds");
		Thread.sleep(3000);
	
		logInfo("Gmail_SigninPassword_wPF : submitPassword(passwd)");
		Gmail_SigninPassword_wPF s2 = new Gmail_SigninPassword_wPF(driver);
		s2.submitPassword(passwd);
		
		logInfo("Sleeping for few seconds");
		Thread.sleep(6000);
	
	}
	
	@Test (dependsOnMethods = {"signinGmail_wPF"}, priority = 4, enabled = true )
	public void composeGmail_wPF() throws Exception {
		
		logInfo("-----------------------------------");
		logInfo("@Test : composeGmail_wPF() ........");
		
		logInfo("Gmail_Compose_wPF : Create");
		Gmail_Compose_wPF gc = new Gmail_Compose_wPF(driver);
		
		logInfo("Sleeping for few seconds");
		Thread.sleep(3000);
		
		String emailto = "taislu@hotmail.com";
		String subject = "Email Automation Test using Page Factory";
		String content = "This is an email sent by Selenium.";
		
		logInfo("Gmail_Compose : sendEmail(emailto, subject, content)");
		gc.sendEmail(emailto, subject, content);
		
		logInfo("Sleeping for few seconds");
		Thread.sleep(3000);
		
		logInfo("Gmail_Compose : logOut()");
		gc.logOut();
	}
  
  	@BeforeTest
  	public void beforeTest() {
  		
  		logInfo("------------------------------");
  		logInfo("@BeforeTest ........");
  		
  		logInfo("Running getUserLogin()");
  		getUserLogin();
  		
  		logInfo("Start chrome browser");
		System.setProperty("webdriver.chrome.driver", "C:\\2018\\edureka\\selenium\\EXEs\\chromedriver.exe");
		driver = new ChromeDriver();
		
		logInfo("Set the page load timeout for any page load");
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		
		logInfo("Set implicit wait for all the activities on the browser");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
  	}

  	@AfterTest
  	public void afterTest() {
  		
  		logInfo("------------------------------");
  		logInfo("@AfterTest ........");
		
		logInfo("Close browser()  ********");
		driver.quit();
		
		logInfo("DONE!");
  	}
  
   private static void logInfo(String msg) {
		
		System.out.println( LocalDateTime.now() + " : " + msg );
		
	}
   
   private static void getUserLogin() {
		
		logInfo("Retrieve userID and password from Excel sheet 1st row : column 0 & 1");
		ReadExcelSheets excel = new ReadExcelSheets("C:\\2018\\edureka\\selenium\\INPUT\\gmail_input.xlsx");
		userID = excel.getDataCell(0, 0, 0);
		passwd = excel.getDataCell(0, 0, 1);
		//logInfo("UserID : " + userID + ", Passwd : " + passwd);
		
	}

}

class Gmail_SigninUserID {
	
	WebDriver driver;
	
	By userID = By.id("identifierId");
	By nextBtn = By.xpath("//*[@id='identifierNext']/content/span");
	
	public Gmail_SigninUserID(WebDriver driver) {
		
		this.driver = driver;
	}
	
	public void submitUserID(String userid) {
		
		driver.findElement(userID).sendKeys(userid);
		driver.findElement(nextBtn).click();
	}
}

class Gmail_SigninUserID_wPF {
	
	@FindBy(how=How.ID, using = "identifierId") WebElement userID;
	@FindBy(how=How.XPATH, using = "//*[@id='identifierNext']/content/span") WebElement nextBtn;
	
	public Gmail_SigninUserID_wPF(WebDriver driver) {
	
		PageFactory.initElements(driver, this);
	}
	
	public void submitUserID(String userid) {
		
		userID.sendKeys(userid);
		nextBtn.click();
	}
}

class Gmail_Compose_wPF {
	
	@FindBy(how=How.XPATH, using = "//*[@id=\":hd\"]/div/div") WebElement composeBtn;
	@FindBy(how=How.XPATH, using = "/html/body/div[20]/div[2]") WebElement popupCloseBtn;
	
	@FindBy(how=How.NAME, using = "to") WebElement emailTo;
	@FindBy(how=How.NAME, using = "subjectbox") WebElement emailSubject;
	@FindBy(how=How.XPATH, using = "//*[@id=\":nk\"]") WebElement emailContent;
	@FindBy(how=How.XPATH, using = "//*[@id=\":m5\"]") WebElement sendBtn;
	
	@FindBy(how=How.XPATH, using = "//*[@id=\"gb\"]/div[1]/div[1]/div/div[5]/div[1]/a/span") WebElement profilePic;
	@FindBy(how=How.LINK_TEXT, using = "Sign out") WebElement signOut;
	
	public Gmail_Compose_wPF(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	public void closePopup() {
		
		popupCloseBtn.click();
	}
	
	public void sendEmail(String to, String subject, String content) throws Exception {
		
		composeBtn.click();
		Thread.sleep(3000);
		
		emailTo.sendKeys(to);
		emailSubject.sendKeys(subject);
		emailContent.sendKeys(content);
		sendBtn.click();
	}
	
	public void logOut() {
		
		profilePic.click();
		signOut.click();
	}
	
}

class Gmail_SigninPassword_wPF {
	
	@FindBy(how=How.NAME, using = "password") WebElement passWord;
	@FindBy(how=How.XPATH, using = "//*[@id='passwordNext']/content/span") WebElement nextBtn;
	
	public Gmail_SigninPassword_wPF(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	public void submitPassword(String password) {
		
		passWord.sendKeys(password);
		nextBtn.click();
	}
}

class Gmail_SigninPassword {
	
	WebDriver driver;
	
	By passWord = By.name("password");
	By nextBtn = By.xpath("//*[@id='passwordNext']/content/span");
	
	public Gmail_SigninPassword(WebDriver driver) {
		
		this.driver = driver;
	}
	
	public void submitPassword(String password) {
		
		driver.findElement(passWord).sendKeys(password);
		driver.findElement(nextBtn).click();
	}
}



class Gmail_Compose {
	
	WebDriver driver;
	
	By composeBtn = By.xpath("//*[@id=\":hd\"]/div/div");
	By popupCloseBtn = By.xpath("/html/body/div[20]/div[2]");
	
	By emailTo = By.name("to");
	By emailSubject = By.name("subjectbox");
	By emailContent = By.xpath("//*[@id=\":nk\"]");
	By sendBtn = By.xpath("//*[@id=\":m5\"]");
	
	By profilePic = By.xpath("//*[@id=\"gb\"]/div[1]/div[1]/div/div[5]/div[1]/a/span");
	By signOut = By.linkText("Sign out");
	
	public Gmail_Compose(WebDriver driver) {
		
		this.driver = driver;
	}
	
	public void closePopup() {
		
		driver.findElement(popupCloseBtn).click();
	}
	
	public void sendEmail(String to, String subject, String content) throws Exception {
		
		driver.findElement(composeBtn).click();
		Thread.sleep(3000);
		
		driver.findElement(emailTo).sendKeys(to);
		driver.findElement(emailSubject).sendKeys(subject);
		driver.findElement(emailContent).sendKeys(content);
		driver.findElement(sendBtn).click();
	}
	
	public void logOut() {
		
		driver.findElement(profilePic).click();
		driver.findElement(signOut).click();
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
[RemoteTestNG] detected TestNG version 6.14.2
2018-08-07T16:47:58.187 : ------------------------------
2018-08-07T16:47:58.265 : @BeforeTest ........
2018-08-07T16:47:58.265 : Running getUserLogin()
2018-08-07T16:47:58.280 : Retrieve userID and password from Excel sheet 1st row : column 0 & 1
2018-08-07T16:47:59.266 : Start chrome browser
Starting ChromeDriver 2.41.578737 (49da6702b16031c40d63e5618de03a32ff6c197e) on port 6022
Only local connections are allowed.
Aug 07, 2018 4:48:04 PM org.openqa.selenium.remote.ProtocolHandshake createSession
INFO: Detected dialect: OSS
2018-08-07T16:48:04.359 : Set the page load timeout for any page load
2018-08-07T16:48:04.385 : Set implicit wait for all the activities on the browser
2018-08-07T16:48:04.432 : ------------------------------
2018-08-07T16:48:04.432 : @Test : signinGmail() ........
2018-08-07T16:48:08.113 : Maximize window
2018-08-07T16:48:09.290 : Gmail_SigninUserID : submitUserID(userID)
2018-08-07T16:48:09.908 : Sleeping for few seconds
2018-08-07T16:48:12.914 : Gmail_SigninPassword : submitPassword(passwd)
2018-08-07T16:48:13.278 : Sleeping for few seconds
2018-08-07T16:48:19.296 : -------------------------------
2018-08-07T16:48:19.297 : @Test : composeGmail() ........
2018-08-07T16:48:19.297 : Gmail_Compose : Create
2018-08-07T16:48:19.298 : Sleeping for few seconds
2018-08-07T16:48:22.303 : Gmail_Compose : sendEmail(emailto, subject, content)
2018-08-07T16:48:34.323 : Sleeping for few seconds
2018-08-07T16:48:37.336 : Gmail_Compose : logOut()
2018-08-07T16:48:41.769 : Sleeping for few seconds
2018-08-07T16:48:44.776 : ----------------------------------
2018-08-07T16:48:44.776 : @Test : signinGmail_wPF() ........
2018-08-07T16:48:45.445 : Maximize window
2018-08-07T16:48:45.450 : Sleeping for few seconds
2018-08-07T16:48:48.464 : Gmail_SigninPassword_wPF : submitPassword(passwd)
2018-08-07T16:48:48.895 : Sleeping for few seconds
2018-08-07T16:48:54.900 : -----------------------------------
2018-08-07T16:48:54.900 : @Test : composeGmail_wPF() ........
2018-08-07T16:48:54.900 : Gmail_Compose_wPF : Create
2018-08-07T16:48:54.902 : Sleeping for few seconds
2018-08-07T16:48:57.903 : Gmail_Compose : sendEmail(emailto, subject, content)
2018-08-07T16:49:04.190 : Sleeping for few seconds
2018-08-07T16:49:07.203 : Gmail_Compose : logOut()
2018-08-07T16:49:11.098 : ------------------------------
2018-08-07T16:49:11.099 : @AfterTest ........
2018-08-07T16:49:11.099 : Close browser()  ********
2018-08-07T16:49:11.921 : DONE!
PASSED: signinGmail
PASSED: composeGmail
PASSED: signinGmail_wPF
PASSED: composeGmail_wPF

===============================================
    Default test
    Tests run: 4, Failures: 0, Skips: 0
===============================================


===============================================
Default suite
Total tests run: 4, Failures: 0, Skips: 0
===============================================
 * 
*/