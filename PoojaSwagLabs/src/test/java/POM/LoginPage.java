package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import BasicUtilities.JustWait;
import BasicUtilities.ScreenShotss;
import BasicUtilities.logger;

public class LoginPage {
	WebDriver dr;
	JustWait wt; logger log; ScreenShotss ss ;ProductsPage pp; 
	
	public LoginPage(WebDriver dr)
	{
		this.dr = dr;
		wt = new JustWait(dr);
		log = new logger(dr);
		ss = new ScreenShotss(dr);
		pp = new ProductsPage(dr);
	}
	
	public void EnterUserName(String username)
	{
		By by_ele = By.xpath("//input[@id ='user-name']");
		WebElement we = wt.WaitForElement(by_ele, 10);
		we = wt.elementToBeClickable(by_ele, 10);
		we.sendKeys(username);
	}
	
	public void EnterPassword(String password)
	{
		By by_ele = By.xpath("//input[@id ='password']");
		WebElement we = wt.WaitForElement(by_ele, 10);
		we = wt.elementToBeClickable(by_ele, 10);
		we.sendKeys(password);
	}
	
	public void ClickLoginBTn()
	{
		By by_ele = By.xpath("//input[@type ='submit']");
		WebElement we = wt.WaitForElement(by_ele, 10);
		we = wt.elementToBeClickable(by_ele, 10);
		we.click();
	}
	
	//Error Message checking for Invalid Details
	public String InvalidError()
	{
		By by_ele = By.xpath("//h3[@data-test='error']");
		WebElement we = wt.WaitForElement(by_ele, 10);
		we = wt.elementToBeClickable(by_ele, 10);
		return we.getText();
	}
	
	//Login Function 
	public void DoLogin(String userName, String Password)
	{
		this.EnterUserName(userName);
		this.EnterPassword(Password);
		ss.ScreenShott("LoginCredentials.png");
		log.Update_log(" login checked for data "+userName+" "+Password);
		this.ClickLoginBTn();		
	}
}
