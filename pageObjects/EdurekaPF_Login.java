package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class EdurekaPF_Login {
	
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
