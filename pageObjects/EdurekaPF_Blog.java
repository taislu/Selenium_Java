package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class EdurekaPF_Blog {
	
	@FindBy(how=How.ID, using = "search-inp") WebElement searchInput;
	@FindBy(how=How.XPATH, using = "//*[@id='remote']/div/span[2]/i[1]") WebElement searchClick;
	@FindBy(how=How.LINK_TEXT, using = "Interview Questions") WebElement interviewClick;
	
	public EdurekaPF_Blog(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	public void searchSelenium() throws Exception {
		
		searchInput.sendKeys("selenium blogs");
		Thread.sleep(3000);
		
		searchClick.click();
		Thread.sleep(3000);
		
		interviewClick.click();
		Thread.sleep(3000);
	}

}
