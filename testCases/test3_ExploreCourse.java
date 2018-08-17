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
import org.testng.annotations.AfterTest;

public class test3_ExploreCourse {
	
	Log log;
	WebDriver driver;
	WebExplorer webdriver;
	String url = "https://www.edureka.co/";
	ExcelReadWrite excel;
	ExcelReadWrite excelOut;
	String userID;
	String passwd;
	String[] output = {"test3_ExploreCourse", "function", "PASS", "timestamp"};
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
	public void test_searchCourse() throws Exception {
		
		log.info("test_searchCourse()");
		
		
		webdriver.WaitForClickable(pageWait);
		webdriver.FindElement( By.xpath("//*[@id='dropdownMenu3']"), 10).click();
		
		log.info("Navigate to All Courses");
		List<WebElement> dropdown = driver.findElements(By.xpath("//ul[@class='dropdown-menu']//li/a"));
		webdriver.DropdownClick(dropdown, "All Courses");
	
		webdriver.WaitForClickable(pageWait);
		
		webdriver.FindElement( By.id("search-inp"), 10).sendKeys("Selenium");
		webdriver.FindElement( By.id("search-inp"), 10).sendKeys(Keys.ENTER);
		
		webdriver.WaitForClickable(pageWait);
		
		webdriver.FindElement( By.linkText("Selenium Certification Training"), 10).click();
		
		webdriver.WaitForClickable(pageWait);
		
		log.info("Appending test result to Excel");
		appendTestResult("test_validateMyCourse()");
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
  		
  		//driver.quit();
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
