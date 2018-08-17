package edurekatest;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;

public class HeadlessBrowsers {
	
	
	@Test(priority=1, enabled=true)
	public void phantomjsBrowsing() throws Exception {
		
		logInfo("phantomjsBrowsing()");
		
		logInfo("Start PhantomJS driver =========");
		DesiredCapabilities caps = new DesiredCapabilities();
		
		caps.setJavascriptEnabled(true);                
		caps.setCapability("takesScreenshot", true);  
		caps.setCapability(
		                        PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
		                        "C:\\2018\\edureka\\selenium\\EXEs\\phantomjs.exe"
		                    );
		
		WebDriver driver = new PhantomJSDriver(caps);
		
		logInfo("Navigate to http://www.google.com");
		driver.get("http://www.google.com");
		
		logInfo("Before search : Page title is : " + driver.getTitle());
		logInfo("Search for Edureka");
		WebElement element = driver.findElement(By.name("q"));
		element.sendKeys("Edureka");
		element.submit();
		
		logInfo("After search : Page title is : " + driver.getTitle());
		
		logInfo("Taking screenshot now");
        
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        System.out.println("File:" + srcFile);
         
        String screenFile = "C:\\2018\\edureka\\selenium\\OUTPUT\\screen_shot1.png";
 		File DestFile=new File(screenFile);
 		FileUtils.copyFile(srcFile, DestFile);
 		
 		logInfo("Screen Shot : " + screenFile);
 		
 		logInfo("Close PhantomJS driver");
 		driver.quit();
	}
	
	@Test(priority=2, enabled=false)
	public void HtmlUnitDriverBrowing() {
		
		logInfo("HtmlUnitDriverBrowing()");
		
		logInfo("Start HTML Unit Driver =======");
		WebDriver driver = new HtmlUnitDriver();
		
		logInfo("Navigate to http://www.google.com");
		driver.get("http://www.google.com");
		
		logInfo("Before search : Page title is : " + driver.getTitle());
		logInfo("Search for Edureka");
		WebElement element = driver.findElement(By.name("q"));
		element.sendKeys("Edureka");
		element.submit();
		
		logInfo("After search : Page title is : " + driver.getTitle());
		
		logInfo("Close HTML Unit driver");
		driver.quit();
		
	}
 
  	private static void logInfo(String msg) {
		
		System.out.println( LocalDateTime.now() + " : " + msg );
		
	}

}

/*
[RemoteTestNG] detected TestNG version 6.14.3
2018-08-15T20:08:33.334 : phantomjsBrowsing()
2018-08-15T20:08:33.444 : Start PhantomJS driver =========
[INFO  - 2018-08-16T03:08:36.513Z] GhostDriver - Main - running on port 11116
[INFO  - 2018-08-16T03:08:36.938Z] Session [aae9d310-a101-11e8-bf53-b989f31794ad] - page.settings - {"XSSAuditingEnabled":false,"javascriptCanCloseWindows":true,"javascriptCanOpenWindows":true,"javascriptEnabled":true,"loadImages":true,"localToRemoteUrlAccessEnabled":false,"userAgent":"Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/538.1 (KHTML, like Gecko) PhantomJS/2.1.1 Safari/538.1","webSecurityEnabled":true}
[INFO  - 2018-08-16T03:08:36.938Z] Session [aae9d310-a101-11e8-bf53-b989f31794ad] - page.customHeaders:  - {}
[INFO  - 2018-08-16T03:08:36.938Z] Session [aae9d310-a101-11e8-bf53-b989f31794ad] - Session.negotiatedCapabilities - {"browserName":"phantomjs","version":"2.1.1","driverName":"ghostdriver","driverVersion":"1.2.0","platform":"windows-10-32bit","javascriptEnabled":true,"takesScreenshot":true,"handlesAlerts":false,"databaseEnabled":false,"locationContextEnabled":false,"applicationCacheEnabled":false,"browserConnectionEnabled":false,"cssSelectorsEnabled":true,"webStorageEnabled":false,"rotatable":false,"acceptSslCerts":false,"nativeEvents":true,"proxy":{"proxyType":"direct"}}
[INFO  - 2018-08-16T03:08:36.938Z] SessionManagerReqHand - _postNewSessionCommand - New Session Created: aae9d310-a101-11e8-bf53-b989f31794ad
2018-08-15T20:08:36.970 : Navigate to http://www.google.com
2018-08-15T20:08:37.700 : Before search : Page title is : Google
2018-08-15T20:08:37.701 : Search for Edureka
2018-08-15T20:08:38.612 : After search : Page title is : Edureka - Google Search
2018-08-15T20:08:38.612 : Taking screenshot now
File:C:\Users\Tai\AppData\Local\Temp\screenshot2564278681096622602.png
2018-08-15T20:08:39.341 : Screen Shot : C:\2018\edureka\selenium\OUTPUT\screen_shot1.png
2018-08-15T20:08:39.342 : Close PhantomJS driver
[INFO  - 2018-08-16T03:08:39.357Z] ShutdownReqHand - _handle - About to shutdown
PASSED: phantomjsBrowsing
*/
