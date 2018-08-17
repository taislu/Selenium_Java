package testCases;

import org.testng.annotations.Test;

import pageObjects.EdurekaPF_Blog;
import pageObjects.EdurekaPF_Home;
import pageObjects.EdurekaPF_Login;
import utilityTools.ExcelReadWrite;
import utilityTools.Log;
import utilityTools.WebExplorer;

import org.testng.annotations.BeforeTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;

public class test2_ExploreBlogs {
	
	Log log;
	WebDriver driver;
	WebExplorer webdriver;
	String url = "https://www.edureka.co/";
	ExcelReadWrite excel;
	ExcelReadWrite excelOut;
	String userID;
	String passwd;
	String[] output = {"test2_ExploreBlogs", "function", "PASS", "timestamp"};
	By pageWait = By.xpath("//a[@href='https://www.youtube.com/user/edurekaIN']");
	
	@Test(priority=1, enabled=true)
	public void test_signinEdureka() throws Exception {
		
		log.info("test_signinEdureka()");
		EdurekaPF_Login e = new EdurekaPF_Login(driver);
		e.userLogin(userID, passwd);
		
		//log.info("Sleeping for few seconds");
		//Thread.sleep(3000);
		
		webdriver.WaitForClickable(pageWait);
		
		log.info("Appending test result to Excel");
		appendTestResult("test_signinEdureka()");
		
	}
	
	@Test(priority=2, enabled=true)
	public void test_exploreBlogs() throws Exception {
		
		log.info("test_exploreBlogs()");
		
		log.info("EdurekaPF_Home : clickBlog()");
		EdurekaPF_Home h = new EdurekaPF_Home(driver);
		h.clickBlog();
		
		webdriver.WaitForClickable(pageWait);
		
		log.info("driver switch to new tab");
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());   
		String handleName = tabs.get(1);      
		driver.switchTo().window(handleName); 
		
		log.info("EdurekaPF_Blog : searchSelenium()");
		EdurekaPF_Blog b = new EdurekaPF_Blog(driver);
		b.searchSelenium();
		
		log.info("Appending test result to Excel");
		appendTestResult("test_exploreBlogs()");
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
