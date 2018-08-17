package utilityTools;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebExplorer {
	
	WebDriver driver;
	
	public WebExplorer(String browser, String DriverPath, String url) {
		
		openBrowser(browser, DriverPath);
		
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.navigate().to(url);
		driver.manage().window().maximize();
	}
	
	public WebDriver getWebDriver() {
		
		return driver;
	}
	
	private void openBrowser(String browser, String DriverPath) {
		
		if(browser == "Chrome") {
			
			System.setProperty("webdriver.chrome.driver", DriverPath);
			driver = new ChromeDriver();
			
		} else {
			
			System.setProperty("webdriver.gecko.driver", DriverPath);
			driver = new FirefoxDriver();
		}
	}
	
	public void DropdownClick(List<WebElement> dropdown, String textValue) {
		
		for ( WebElement e : dropdown ) {
			
			String str = e.getAttribute("innerHTML");
			System.out.println("innerHTML : " + str);
			if( str.contentEquals(textValue)) {
				e.click();
				break;
			}
		}
	}
	
	public WebElement FindElement(By by, int timeoutInSeconds) {
		
		try {
			
			WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			
			//System.out.println( "FindElement *** " + by + " *** Found");
			return driver.findElement(by);
			
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("Exception : " + e.getMessage() + " x-x-x-x-x-x");
		}
		
		//System.out.println( "FindElement --> " + by + " --> Not found");
		
		return null;
	}
	
	public void WaitForClickable(By by) {
		
		try {
			
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(by));
			
		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage() + " x-x-x-x-x-x");
		}
		
	}

}
