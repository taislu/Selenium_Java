package module2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CaseStudy_m2 {
	
	public static void main(String[] args) {
		
		chrome();
		//firefox();
	}

	public static void chrome() {
		
		System.out.println("Start testing using chrome browser");
		
		Testing a = new Testing();
		a.browser("chrome", "C:\\\\2018\\\\edureka\\\\selenium\\\\EXEs\\\\chromedriver.exe");
		
	}
	
	public static void firefox() {
		
		System.out.println("Start testing using firefox browser");
		
		Testing a = new Testing();
		a.browser("firefox", "C:\\\\2018\\\\edureka\\\\selenium\\\\EXEs\\\\geckodriver.exe");
	}
}

class Testing{
	
	public static void browser(String browser, String DriverPath) {
		
		String url = "https://www.edureka.co/";
		WebDriver driver = openBrowser(browser, DriverPath, url);
		
		// Find element using ID locator
		String loc_id = "homeSearchBar";
		WebElement elementById = FindElement(driver, By.id(loc_id), 4);
		
		if(elementById != null) {
			System.out.println("Element is present with the given id : " + loc_id);
		} else {
			System.out.println("Element is not present with the given id : " + loc_id);
		}
		
		closeBrowser(driver);
	}
	
	private static WebElement FindElement(WebDriver driver, By by, int timeoutInSeconds) {
		
		try {
			
			WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
			
			// throws a timeout exception if element not present after waiting timeoutInSeconds
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			
			return driver.findElement(by);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private static WebDriver openBrowser(String browser, String DriverPath, String url) {
		
		WebDriver driver;
		
		if(browser == "chrome") {
			
			System.setProperty("webdriver.chrome.driver", DriverPath);
			driver = new ChromeDriver();
			
		} else {
			
			System.setProperty("webdriver.gecko.driver", DriverPath);
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.navigate().to(url);
		
		return driver;
		
	}
	
	private static void closeBrowser(WebDriver driver) {
		
		try {
			
			driver.quit();
			System.out.println("Browser closed successfully!");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}