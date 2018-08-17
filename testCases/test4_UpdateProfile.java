package testCases;

import org.testng.annotations.Test;

import pageObjects.EdurekaPF_Home;
import pageObjects.EdurekaPF_Login;
import utilityTools.ExcelReadWrite;
import utilityTools.Log;
import utilityTools.WebExplorer;

import org.testng.annotations.BeforeTest;

import java.time.LocalDateTime;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;

public class test4_UpdateProfile {
	
	Log log;
	WebDriver driver;
	WebExplorer webdriver;
	String url = "https://www.edureka.co/";
	ExcelReadWrite excel;
	ExcelReadWrite excelOut;
	String userID;
	String passwd;
	String[] output = {"test4_UpdateProfile", "function", "PASS", "timestamp"};
	By pageWait = By.xpath("//a[@href='https://www.youtube.com/user/edurekaIN']");
	
	@Test(priority=1, enabled=true)
	public void test_signinEdureka() throws Exception {
		
		log.info("test_signinEdureka()");
		EdurekaPF_Login e = new EdurekaPF_Login(driver);
		e.userLogin(userID, passwd);
		
		log.info("Sleeping for few seconds");
		Thread.sleep(3000);
		
		log.info("Appending test result to Excel");
		appendTestResult("test_signinEdureka()");
		
	}
	
	@Test(priority=2, enabled=true)
	public void test_updateProfessional() throws Exception {
		
		log.info("test_updateProfessional()");
		
		driver.navigate().to("https://learning.edureka.co/my-profile");
		webdriver.WaitForClickable(pageWait);
		
		log.info("Click Editing professional_details");
		webdriver.FindElement( By.id("professional_details"), 4).click();
		
		log.info("Enter Company Name");
		webdriver.FindElement( By.xpath("//input[@name='companyName']"), 4).clear();
		webdriver.FindElement( By.xpath("//input[@name='companyName']"), 4).sendKeys("Tech Mahindra");
		
		log.info("Select Job Level");
		WebElement w1 = webdriver.FindElement( By.xpath("//select[@name='currentjob']"), 4);
		Select job = new Select(w1);
		job.selectByIndex(3);
		
		log.info("Select Industry");
		WebElement w2 = webdriver.FindElement( By.xpath("//select[@name='currentIndustry']"), 4);
		Select industry = new Select(w2);
		industry.selectByIndex(11);
		
		log.info("Enter Linkedin Profile");
		String myLinkedIn = "https://www.linkedin.com/in/tai-sheng-lu-9229b23a";
		webdriver.FindElement( By.xpath("//input[@name='linkedinLink']"), 4).clear();
		webdriver.FindElement( By.xpath("//input[@name='linkedinLink']"), 4).sendKeys(myLinkedIn);
		
		log.info("Enter Skills");
		String mySkills = "Python and Java Programming etc";
		webdriver.FindElement( By.xpath("//input[@name='userSkill']"), 4).clear();
		webdriver.FindElement( By.xpath("//input[@name='userSkill']"), 4).sendKeys(mySkills);
		
		log.info("Click Next");
		webdriver.FindElement( By.xpath("//button[@type='submit']"), 4).click();
		
		log.info("Click Next again");
		webdriver.FindElement( By.xpath("//button[@type='submit']"), 4).click();
		
		Thread.sleep(3000);
		
		log.info("Appending test result to Excel");
		appendTestResult("test_updateProfessional()");
		
		Thread.sleep(3000);
	
	}
	
	@Test(priority=3, enabled=true)
	public void test_userLogOut() {
		
		log.info("test_userLogOut()");
		
		driver.get(url);
		
		webdriver.WaitForClickable(pageWait);
		
		log.info("EdurekaPF_Home : userLogout()");
		EdurekaPF_Home h = new EdurekaPF_Home(driver);
		h.userLogout();
		
		webdriver.WaitForClickable(pageWait);
		
		log.info("Appending test result to Excel");
		appendTestResult("test_userLogOut()");
	}
	
  	@BeforeTest
  	public void beforeTest() {
  		
  		setupLog();
  		
  		getUserLogin();
  		
  		startBrowser();
  	}

  	@AfterTest
  	public void afterTest() {
  		
  		log.info("Appending test result to Excel");
		appendTestResult("afterMethod()");
  		
  		log.info("Close Excel");
		excel.saveAndClose();
		excelOut.saveAndClose();
  		
  		driver.quit();
  		log.info("DONE!");
  	}
  	
  	private void startBrowser() {
  		
  		log.info("startBrowser()");
  		String driverPath = "C:\\2018\\edureka\\selenium\\EXEs\\chromedriver.exe";
  		
  		webdriver = new WebExplorer("Chrome", driverPath, url);
  		driver = webdriver.getWebDriver();
  	}
  
  	private void setupLog() {
  		
  		log = new Log("test1_CheckEnrolledCourses", "log4j.properties");
  		log.info("setUp()");
  	}
  	
  	private void getUserLogin() {
		
  		log.info("getUserLogin()");
		log.info("Retrieve userID and password from Excel sheet 1st row : column 0 & 1");
		excel = new ExcelReadWrite("C:\\2018\\edureka\\selenium\\INPUT\\edureka_m9.xlsx");
		excelOut = new ExcelReadWrite("C:\\2018\\edureka\\selenium\\OUTPUT\\test_output.xlsx");
		userID = excel.getDataCell(0, 0, 0);
		passwd = excel.getDataCell(0, 0, 1);
		//log.info("userID : " + userID + ", passwd : " + passwd);
		
	}
  	
  	private void appendTestResult(String functionName) {
		
		output[1] = functionName;
		output[3] = LocalDateTime.now().toString();
		excelOut.appendNewDataRow(0, output);
	}

}
