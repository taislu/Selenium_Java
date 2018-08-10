package module10;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class POM_m10 {
	
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
	public void searchCourse() throws Exception {
		
		logInfo("----------------------------------");
		logInfo("@Test : searchCourse() ..........");
		
		logInfo("EdurekaPF_Home : searchCourse()");
		EdurekaPF_Home h = new EdurekaPF_Home(driver);
		h.searchCourse();
		
		logInfo("Sleeping for few seconds");
		Thread.sleep(3000);
		
		logInfo("Appending test result to Excel");
		appendTestResult("searchCourse()");
	}
	
	@Test( priority = 3, enabled = true)
	public void pickClass() throws Exception {
		
		logInfo("----------------------------------");
		logInfo("@Test : pickClass() ..........");
		
		logInfo("EdurekaPF_SelectClass : selectDjango()");
		EdurekaPF_SelectClass s = new EdurekaPF_SelectClass(driver);
		s.selectDjango();
		
		logInfo("Sleeping for few seconds");
		Thread.sleep(9000);
		
		logInfo("Appending test result to Excel");
		appendTestResult("enrollClass()");
		
	}
	
	@Test( priority = 4, enabled = false)
	public void enrollClass() throws Exception {
		
		
		logInfo("----------------------------------");
		logInfo("@Test : enrollClass() ..........");
		
		logInfo("EdurekaPF_Enroll : enrollDjango()");
		EdurekaPF_Enroll e = new EdurekaPF_Enroll(driver);
		e.enrollDjango();
		
		logInfo("Sleeping for few seconds");
		Thread.sleep(3000);
		
		logInfo("Appending test result to Excel");
		appendTestResult("enrollClass()");
		
	}
	
	@Test( priority = 5, enabled = true)
	public void logOut() throws Exception {
		
		logInfo("----------------------------------");
		logInfo("@Test : logOut() ..........");
		
		logInfo("EdurekaPF_Home : userLogout()");
		EdurekaPF_Home h = new EdurekaPF_Home(driver);
		h.userLogout();
		
		logInfo("Appending test result to Excel");
		appendTestResult("enrollClass()");
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

class EdurekaPF_SelectClass{
	
	@FindBy(how=How.LINK_TEXT, using = "Python Django Training and Certification") WebElement djangoClick;
	
	public EdurekaPF_SelectClass(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	public void selectDjango() {
		
		djangoClick.click();
	}
}

class EdurekaPF_Enroll{
	
	JavascriptExecutor js;
	
	@FindBy(how=How.XPATH, using = "//*[@id='batches']/div/div[2]/div[1]/div/div[1]/div[2]") WebElement selectClassClick;
	@FindBy(how=How.XPATH, using = "//*[@id='batches']/div/div[2]/div[2]/a/button") WebElement enrollBtn;
	
	public EdurekaPF_Enroll(WebDriver driver) {
		
		js = (JavascriptExecutor) driver;
		
		PageFactory.initElements(driver, this);
	}
	
	public void enrollDjango() throws Exception {
		
		//js.executeScript("arguments[0].scrollIntoView();", enrollBtn);
		js.executeScript("arguments[0].scrollIntoView();", selectClassClick);
		Thread.sleep(6000);
		
		selectClassClick.click();
		enrollBtn.click();
	}
}

class EdurekaPF_Home{
	
	@FindBy(how=How.ID, using = "dropdownMenu4") WebElement communityClick;
	@FindAll(@FindBy(how=How.XPATH, using = "//ul[@class=\"dropdown-menu\"]//li/a")) List<WebElement> communityDropdown;
	
	@FindBy(how=How.ID, using = "homeSearchBar") WebElement searchInput;
	
	@FindBy(how=How.XPATH, using = "//*[@id='header-II']/section/nav/div/ul[2]/li[1]/a/img") WebElement profileClick;
	@FindAll(@FindBy(how=How.XPATH, using = "//ul[@class='dropdown-menu user-menu profile-xs hidden-sm hidden-xs']//li/a")) List<WebElement> profileDropdown;
	
	public EdurekaPF_Home(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	public void searchCourse() {
		
		searchInput.sendKeys("Django");
		searchInput.sendKeys(Keys.ENTER);
	}
	
	public void userLogout() {
		
		profileClick.click();
		for(WebElement e: profileDropdown) {
			String str = e.getAttribute("innerHTML");
			if(str.contentEquals("Log Out")) {
				e.click();
				break;
			}
		}
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

/*Output
 [RemoteTestNG] detected TestNG version 6.14.2
2018-08-10T15:49:43.571 : ------------------------------
2018-08-10T15:49:43.688 : @BeforeTest ........
2018-08-10T15:49:43.688 : Running getUserLogin()
2018-08-10T15:49:43.689 : Retrieve userID and password from Excel sheet 1st row : column 0 & 1
2018-08-10T15:49:44.875 : Start chrome browser
Starting ChromeDriver 2.41.578737 (49da6702b16031c40d63e5618de03a32ff6c197e) on port 31118
Only local connections are allowed.
Aug 10, 2018 3:49:49 PM org.openqa.selenium.remote.ProtocolHandshake createSession
INFO: Detected dialect: OSS
2018-08-10T15:49:49.160 : Set the page load timeout for any page load
2018-08-10T15:49:49.174 : Set implicit wait for all the activities on the browser
2018-08-10T15:49:49.181 : Appending test result to Excel
2018-08-10T15:49:49.226 : ----------------------------------
2018-08-10T15:49:49.226 : @Test : signinEdureka() ..........
2018-08-10T15:49:49.226 : navigate to : https://www.edureka.co/
2018-08-10T15:49:54.182 : Maximize window
2018-08-10T15:49:55.383 : Sleeping for few seconds
2018-08-10T15:49:58.383 : EdurekaPF_Login : userLogin(userID, passwd)
2018-08-10T15:50:02.321 : Sleeping for few seconds
2018-08-10T15:50:05.321 : Appending test result to Excel
2018-08-10T15:50:05.333 : ----------------------------------
2018-08-10T15:50:05.334 : @Test : searchCourse() ..........
2018-08-10T15:50:05.334 : EdurekaPF_Home : searchCourse()
2018-08-10T15:50:08.075 : Sleeping for few seconds
2018-08-10T15:50:11.076 : Appending test result to Excel
2018-08-10T15:50:11.086 : ----------------------------------
2018-08-10T15:50:11.086 : @Test : pickClass() ..........
2018-08-10T15:50:11.087 : EdurekaPF_SelectClass : selectDjango()
2018-08-10T15:50:15.665 : Sleeping for few seconds
2018-08-10T15:50:24.667 : Appending test result to Excel
2018-08-10T15:50:24.676 : ----------------------------------
2018-08-10T15:50:24.676 : @Test : logOut() ..........
2018-08-10T15:50:24.676 : EdurekaPF_Home : userLogout()
2018-08-10T15:50:27.468 : Appending test result to Excel
2018-08-10T15:50:27.474 : ------------------------------
2018-08-10T15:50:27.474 : @AfterTest ........
2018-08-10T15:50:27.474 : Appending test result to Excel
2018-08-10T15:50:27.477 : Close Excel
2018-08-10T15:50:27.743 : Close browser()  ********
2018-08-10T15:50:28.677 : DONE!
PASSED: signinEdureka
PASSED: searchCourse
PASSED: pickClass
PASSED: logOut

===============================================
    Default test
    Tests run: 4, Failures: 0, Skips: 0
===============================================


===============================================
Default suite
Total tests run: 4, Failures: 0, Skips: 0
===============================================


 */
