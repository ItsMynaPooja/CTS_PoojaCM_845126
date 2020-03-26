package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import BasicUtilities.JustWait;
import BasicUtilities.ScreenShotss;
import BasicUtilities.logger;

public class CheckOutPage {
	WebDriver dr;
	JustWait wt; logger log; ScreenShotss ss ;ProductsPage pp; 
	
	public CheckOutPage(WebDriver dr)
	{
		this.dr = dr;
		wt = new JustWait(dr);
		log = new logger(dr);
		ss = new ScreenShotss(dr);
		pp = new ProductsPage(dr);
	}
	
	//Details of Customer
	public void Continue(String FirstName, String LastName, String ZipCode)
	{
		By by_ele = By.xpath("//input[@id='first-name']");
		WebElement we = wt.WaitForElement(by_ele, 10);
		we = wt.elementToBeClickable(by_ele, 10);
		we.sendKeys(FirstName);
		
		by_ele = By.xpath("//input[@id='last-name']");
		we = wt.WaitForElement(by_ele, 10);
		we = wt.elementToBeClickable(by_ele, 10);
		we.sendKeys(LastName);
		
		by_ele = By.xpath("//input[@id='postal-code']");
		we = wt.WaitForElement(by_ele, 10);
		we = wt.elementToBeClickable(by_ele, 10);
		we.sendKeys(ZipCode);
		
		by_ele = By.xpath("//input[@value='CONTINUE']");
		we = wt.WaitForElement(by_ele, 10);
		we = wt.elementToBeClickable(by_ele, 10);
		we.click();
		
		ss.ScreenShott("Details.png");
		
		by_ele = By.xpath("//a[text()='FINISH']");
		we = wt.WaitForElement(by_ele, 10);
		we = wt.elementToBeClickable(by_ele, 10);
		we.click();
		
		log.Update_log("Details given and Clicked on finish");
		
	}
}
