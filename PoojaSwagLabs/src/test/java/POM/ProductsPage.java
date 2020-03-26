package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import BasicUtilities.JustWait;
import BasicUtilities.ScreenShotss;
import BasicUtilities.logger;

public class ProductsPage {
	
	WebDriver dr;
	JustWait wt; logger log; ScreenShotss ss; 
	
	public ProductsPage(WebDriver dr)
	{
		this.dr = dr;
		wt = new JustWait(dr);
		log = new logger(dr);
		ss = new ScreenShotss(dr);
	}

	//Verifying Successful login
	public String VerifyLogin()
	{
		By by_ele = By.xpath("//div[@class='product_label']");
		WebElement we = wt.WaitForElement(by_ele, 10);
		we = wt.elementToBeClickable(by_ele, 10);
		return we.getText();
	}
	
	//To get the Price from the item which is added
	public float Price(int i)
	{
		By by_ele = By.xpath("//div[@class='inventory_list']//div[@class='inventory_item']["+i+"]//div[3]//div");
		WebElement we = wt.WaitForElement(by_ele, 10);
		we = wt.elementToBeClickable(by_ele, 10);
		String s=we.getText();
		String s1 = s.substring(1);
		float price = Float.parseFloat(s1);
		return price;
	}
	
	
	public void AddtoCart(int i)
	{
		By by_ele = By.xpath("//div[@class='inventory_list']//div[@class='inventory_item']["+i+"]//button");
		WebElement we = wt.WaitForElement(by_ele, 10);
		we = wt.elementToBeClickable(by_ele, 10);
		we.click();
		log.Update_log("Item added to cart");
	}
	
	//To get the name from the item which is added
	public String ItemName(int i)
	{
		By by_ele = By.xpath("//div[@class='inventory_list']//div[@class='inventory_item']["+i+"]//div[2]//div[@class='inventory_item_name']");
		WebElement we = wt.WaitForElement(by_ele, 10);
		we = wt.elementToBeClickable(by_ele, 10);
		return we.getText();
	}
	
	//Clicking Click Cart
	public void ClickCart()
	{
		By by_ele = By.xpath("//div[@id='shopping_cart_container']");
		WebElement we = wt.WaitForElement(by_ele, 10);
		we = wt.elementToBeClickable(by_ele, 10);
		we.click();
		log.Update_log("Clicked on cart button");
	}
}
