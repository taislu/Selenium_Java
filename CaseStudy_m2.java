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
		
		// Find element using NAME locator
		String loc_name = "user_v1[query]";
		WebElement elementByName = FindElement(driver, By.name(loc_name), 4);
		
		if(elementByName != null) {
			System.out.println("Element is present with the given name : " + loc_name);
		} else {
			System.out.println("Element is not present with the given name : " + loc_name);
		}
		
		// Find element using XPATH locator
		String loc_xpath = "//*[@id='homeSearchBar']";
		WebElement elementByXpath = FindElement(driver, By.xpath(loc_xpath), 4);
		
		if(elementByXpath != null) {
			System.out.println("Element is present with the given xpath : " + loc_xpath);
		} else {
			System.out.println("Element is not present with the given xpath : " + loc_xpath);
		}
		
		// Find element using CSS locator
		String loc_css = "#homeSearchBar";
		WebElement elementByCss = FindElement(driver, By.cssSelector(loc_css), 4);
		
		if(elementByCss != null) {
			System.out.println("Element is present with the given css selector : " + loc_css);
		} else {
			System.out.println("Element is not present with the given css selector : " + loc_css);
		}
		
		// Find element using ClassName locator
		String loc_class = "search_input";
		WebElement elementByClass = FindElement(driver, By.className(loc_class), 4);
		
		if(elementByClass != null) {
			System.out.println("Element is present with the given class name : " + loc_class);
		} else {
			System.out.println("Element is not present with the given class name : " + loc_class);
		}
		
		// Find element using TagName locator
		String loc_tag = "button";
		WebElement elementByTag = FindElement(driver, By.tagName(loc_tag), 4);
		
		if(elementByTag != null) {
			System.out.println("Element is present with the given tag name : " + loc_tag);
		} else {
			System.out.println("Element is not present with the given tag name : " + loc_tag);
		}
		
		// Find element using LinkText locator
		String loc_link = "Log In";
		WebElement elementByLink = FindElement(driver, By.linkText(loc_link), 4);
		
		if(elementByLink != null) {
			System.out.println("Element is present with the given link text : " + loc_link);
		} else {
			System.out.println("Element is not present with the given link text : " + loc_link);
		}
		
		// Find element using Partial Link Text locator
		String loc_partial = "Log";
		WebElement elementByPartial = FindElement(driver, By.partialLinkText(loc_partial), 4);
		
		if(elementByPartial != null) {
			System.out.println("Element is present with the given partial link text : " + loc_partial);
		} else {
			System.out.println("Element is not present with the given partial link text : " + loc_partial);
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