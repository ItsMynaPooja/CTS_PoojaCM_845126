package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import BasicUtilities.JustWait;
import BasicUtilities.ScreenShotss;
import BasicUtilities.logger;

public class CartPage {
	WebDriver dr;
	JustWait wt; logger log; ScreenShotss ss; 
	
	public CartPage(WebDriver dr)
	{
		this.dr = dr;
		wt = new JustWait(dr);
		log = new logger(dr);
		ss = new ScreenShotss(dr);
	}
	
	//To get the name from the item which is added
	public String CartItemName(int i)
	{
		By by_ele = By.xpath("//div[@class='cart_list']//div[@class='cart_item']["+i+"]//a//div");
		WebElement we = wt.WaitForElement(by_ele, 10);
		we = wt.elementToBeClickable(by_ele, 10);
		return we.getText();
	}
	//To get the Price from the item which is added
	public float cartPrice(int i)
	{
		By by_ele = By.xpath("//div[@class='cart_list']//div[@class='cart_item']["+i+"]//div[@class='item_pricebar']//div");
		WebElement we = wt.WaitForElement(by_ele, 10);
		we = wt.elementToBeClickable(by_ele, 10);
		String s=we.getText();
		float price = Float.parseFloat(s);
		return price;
	}
	
	//to continue shopping button
	public void ContinueShopping()
	{
		By by_ele = By.xpath("//div[@class='cart_footer']//a[text()='Continue Shopping']");
		WebElement we = wt.WaitForElement(by_ele, 10);
		we = wt.elementToBeClickable(by_ele, 10);
		we.click();
	}
	
	
	//to confirm removal of product
	public Boolean RemoveProduct(int i)
	{
		By by_ele = By.xpath("//div[@class='cart_list']//div[@class='cart_item']["+i+"]//button");
		WebElement we = wt.WaitForElement(by_ele, 10);
		we = wt.elementToBeClickable(by_ele, 10);
		we.click();
		log.Update_log("Item Removed");
		log.Update_log("Clicked on delete product");
		Boolean s;
		try {
			s=	dr.findElement(By.xpath("//div[@class='cart_list']//div[@class='cart_item']["+i+"]//a//div")).isDisplayed();
		}
		catch(Exception e)
		{
			s=false;
		}
		return s;
	}
	
	
	//to check out
	public void CheckOut()
	{
		By by_ele = By.xpath("//div[@class='cart_footer']//a[text()='CHECKOUT']");
		WebElement we = wt.WaitForElement(by_ele, 10);
		we = wt.elementToBeClickable(by_ele, 10);
		we.click();
	}
	
	

}
