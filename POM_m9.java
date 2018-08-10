package module9;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;

public class POM_m9 {
	
	WebDriver driver;
	String url = "https://www.edureka.co/";
	RW_ExcelSheets excel;
	RW_ExcelSheets excelOut;
	String userID;
	String passwd;
	String[] output = {"POM_m9", "function", "PASS", "timestamp"};
	
	@Test( priority = 1, enabled = true)
	public void signinEdureka() throws Exception {
		
		logInfo("----------------------------------");
		logInfo("@Test : signinEdureka() ..........");
		
		logInfo("navigate to : " + url);
		driver.navigate().to(url);
		
		logInfo("Maximize window");
		driver.manage().window().maximize();
		
		logInfo("Sleeping for few seconds");
		Thread.sleep(3000);
		
		logInfo("EdurekaPF_Login : userLogin(userID, passwd)");
		EdurekaPF_Login e = new EdurekaPF_Login(driver);
		e.userLogin(userID, passwd);
		
		logInfo("Sleeping for few seconds");
		Thread.sleep(3000);
		
		logInfo("Appending test result to Excel");
		appendTestResult("signinEdureka()");
		
	}
	
	@Test( priority = 2, enabled = true)
	public void openBlog() {
		
		logInfo("----------------------------------");
		logInfo("@Test :  openBlog() ..............");
		
		logInfo("EdurekaPF_Home : clickBlog()");
		EdurekaPF_Home h = new EdurekaPF_Home(driver);
		h.clickBlog();
		
		logInfo("driver switch to new tab");
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());   
		String handleName = tabs.get(1);      
		driver.switchTo().window(handleName); 
		
		logInfo("Appending test result to Excel");
		appendTestResult("openBlog()");
		
	}
	
	@Test( priority = 3, enabled = true)
	public void searchBlog() throws Exception {
		
		logInfo("----------------------------------");
		logInfo("@Test :  searchBlog() ..............");
		
		logInfo("EdurekaPF_Blog : searchSelenium()");
		EdurekaPF_Blog b = new EdurekaPF_Blog(driver);
		b.searchSelenium();
		
		logInfo("Sleeping for few seconds");
		Thread.sleep(3000);
		
		logInfo("Appending test result to Excel");
		appendTestResult("searchBlog()");
		
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
		
		logInfo("Appending test result to Excel");
		appendTestResult("beforeTest()");
		
	}

	@AfterTest
	public void afterTest() {
		
		logInfo("------------------------------");
  		logInfo("@AfterTest ........");
  		
  		logInfo("Appending test result to Excel");
		appendTestResult("afterTest()");
		
  		logInfo("Close Excel");
		//excel.writeDataCell(0, 3, 0, text1);
		excel.saveAndClose();
		excelOut.saveAndClose();
		
		logInfo("Close browser()  ********");
		driver.quit();
		
		logInfo("DONE!");
		
	}
	
	private void appendTestResult(String functionName) {
		
		output[1] = functionName;
		output[3] = LocalDateTime.now().toString();
		excelOut.appendNewDataRow(0, output);
	}

	private static void logInfo(String msg) {
		
		System.out.println( LocalDateTime.now() + " : " + msg );
		
	}
	
	private void getUserLogin() {
		
		logInfo("Retrieve userID and password from Excel sheet 1st row : column 0 & 1");
		excel = new RW_ExcelSheets("C:\\2018\\edureka\\selenium\\INPUT\\edureka_m9.xlsx");
		excelOut = new RW_ExcelSheets("C:\\2018\\edureka\\selenium\\OUTPUT\\test_output.xlsx");
		userID = excel.getDataCell(0, 0, 0);
		passwd = excel.getDataCell(0, 0, 1);
		//logInfo("userID : " + userID + ", passwd : " + passwd);
		
	}
}

class EdurekaPF_Home{
	
	@FindBy(how=How.ID, using = "dropdownMenu4") WebElement communityClick;
	@FindAll(@FindBy(how=How.XPATH, using = "//ul[@class=\"dropdown-menu\"]//li/a")) List<WebElement> communityDropdown;
	
	public EdurekaPF_Home(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	public void clickBlog() {
		
		communityClick.click();
		for(WebElement e: communityDropdown) {
			String str = e.getAttribute("innerHTML");
			if(str.contentEquals("Blog")) {
				e.click();
				break;
			}
		}
	}
}

class EdurekaPF_Blog{
	
	@FindBy(how=How.ID, using = "search-inp") WebElement searchInput;
	@FindBy(how=How.XPATH, using = "//*[@id='remote']/div/span[2]/i[1]") WebElement searchClick;
	@FindBy(how=How.LINK_TEXT, using = "Interview Questions") WebElement interviewClick;
	
	public EdurekaPF_Blog(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	public void searchSelenium() {
		
		searchInput.sendKeys("selenium blogs");
		searchClick.click();
		interviewClick.click();
	}
}

class EdurekaPF_Login{
	
	@FindBy(how=How.LINK_TEXT, using = "Log In") WebElement loginBtn1;
	
	@FindBy(how=How.ID, using = "inputName") WebElement userID;
	@FindBy(how=How.ID, using = "pwd1") WebElement passWord;
	@FindBy(how=How.XPATH, using = "//*[@id=\"signinForm\"]/div[4]/button") WebElement loginBtn2;
	
	public EdurekaPF_Login(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	public void userLogin(String userid, String password) throws Exception {
		
		loginBtn1.click();
		Thread.sleep(3000);
		
		userID.sendKeys(userid);
		passWord.sendKeys(password);
		loginBtn2.click();
	}
}

class RW_ExcelSheets {
	
	File f;
	FileInputStream fis;
	XSSFWorkbook wb;
	//XSSFSheet sheet;
	
	public RW_ExcelSheets(String file) {
		
		try {
			
			f = new File(file);	
			fis = new FileInputStream(f);
			wb = new XSSFWorkbook(fis);
			//sheet = xlsx.getSheetAt(0);
			
		} catch (Exception e) {
			
			//e.printStackTrace();
			System.out.println(e.getMessage());
		
		}
	}
	
	public String getDataCell(int sheetIndex, int rowIndex, int colIndex) {
		try {
			
			return wb.getSheetAt(sheetIndex).getRow(rowIndex).getCell(colIndex).getStringCellValue();
		
		} catch(Exception e) {
			
			System.out.println("getDataCell : " + e.getMessage());
		}
		
		return null;
	}
	
	public void writeDataCell(int sheetIndex, int rowIndex, int colIndex, String value) {
		
		try {
			
			wb.getSheetAt(sheetIndex).getRow(rowIndex).createCell(colIndex).setCellValue(value);
			
		} catch(Exception e) {
			
			System.out.println("writeDataCell : Exception : " + e.getMessage());
		}
	}
	
	public void appendNewDataRow(int sheetIndex, String[] dataRow) {
		
		Sheet sheet = wb.getSheetAt(sheetIndex);
		int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
		Row row = sheet.getRow(0);
		Row newRow = sheet.createRow(rowCount+1);
		
		for(int j = 0; j < row.getLastCellNum(); j++){

	        //Fill data in row
	        Cell cell = newRow.createCell(j);
	        cell.setCellValue(dataRow[j]);
	    }
	}
	
	
	public void saveAndClose() {
		try {
			
			fis.close();
			
			FileOutputStream fos = new FileOutputStream(f);
			wb.write(fos);
			fos.close();
			
		} catch(Exception e) {
			
			System.out.println("closeFile : Exception : " + e.getMessage());
		}
	}
	
}

/*
[RemoteTestNG] detected TestNG version 6.14.2
2018-08-09T20:07:51.813 : ------------------------------
2018-08-09T20:07:51.938 : @BeforeTest ........
2018-08-09T20:07:51.938 : Running getUserLogin()
2018-08-09T20:07:51.943 : Retrieve userID and password from Excel sheet 1st row : column 0 & 1
2018-08-09T20:07:53.099 : Start chrome browser
Starting ChromeDriver 2.41.578737 (49da6702b16031c40d63e5618de03a32ff6c197e) on port 47221
Only local connections are allowed.
[1533870492.034][WARNING]: Timed out connecting to Chrome, retrying...
Aug 09, 2018 8:08:16 PM org.openqa.selenium.remote.ProtocolHandshake createSession
INFO: Detected dialect: OSS
2018-08-09T20:08:16.706 : Set the page load timeout for any page load
2018-08-09T20:08:16.777 : Set implicit wait for all the activities on the browser
2018-08-09T20:08:16.793 : Appending test result to Excel
2018-08-09T20:08:17.106 : ----------------------------------
2018-08-09T20:08:17.106 : @Test : signinEdureka() ..........
2018-08-09T20:08:17.106 : navigate to : https://www.edureka.co/
2018-08-09T20:08:26.922 : Maximize window
2018-08-09T20:08:28.692 : Sleeping for few seconds
2018-08-09T20:08:31.694 : EdurekaPF_Login : userLogin(userID, passwd)
2018-08-09T20:08:37.003 : Sleeping for few seconds
2018-08-09T20:08:40.003 : Appending test result to Excel
2018-08-09T20:08:40.023 : ----------------------------------
2018-08-09T20:08:40.024 : @Test :  openBlog() ..............
2018-08-09T20:08:40.024 : EdurekaPF_Home : clickBlog()
2018-08-09T20:08:48.526 : driver switch to new tab
2018-08-09T20:08:48.573 : Appending test result to Excel
2018-08-09T20:08:48.580 : ----------------------------------
2018-08-09T20:08:48.580 : @Test :  searchBlog() ..............
2018-08-09T20:08:48.581 : EdurekaPF_Blog : searchSelenium()
2018-08-09T20:09:09.282 : Sleeping for few seconds
2018-08-09T20:09:12.291 : Appending test result to Excel
2018-08-09T20:09:12.293 : ------------------------------
2018-08-09T20:09:12.293 : @AfterTest ........
2018-08-09T20:09:12.293 : Appending test result to Excel
2018-08-09T20:09:12.293 : Close Excel
2018-08-09T20:09:18.509 : Close browser()  ********
2018-08-09T20:09:20.045 : DONE!
PASSED: signinEdureka
PASSED: openBlog
PASSED: searchBlog

===============================================
    Default test
    Tests run: 3, Failures: 0, Skips: 0
===============================================


===============================================
Default suite
Total tests run: 3, Failures: 0, Skips: 0
=============================================== 
*/
