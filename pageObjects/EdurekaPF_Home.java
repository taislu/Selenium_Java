package pageObjects;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class EdurekaPF_Home {
	
	@FindBy(how=How.ID, using = "dropdownMenu4") WebElement communityClick;
	@FindAll(@FindBy(how=How.XPATH, using = "//ul[@class=\"dropdown-menu\"]//li/a")) List<WebElement> communityDropdown;
	
	@FindBy(how=How.ID, using = "homeSearchBar") WebElement searchInput;
	
	@FindBy(how=How.XPATH, using = "//*[@id='header-II']/section/nav/div/ul[2]/li[1]/a/img") WebElement profileClick;
	@FindAll(@FindBy(how=How.XPATH, using = "//ul[@class='dropdown-menu user-menu profile-xs hidden-sm hidden-xs']//li/a")) List<WebElement> profileDropdown;
	
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

}
