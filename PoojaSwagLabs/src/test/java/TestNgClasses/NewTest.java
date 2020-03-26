package TestNgClasses;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import BasicUtilities.Browsers;
import BasicUtilities.JustWait;
import BasicUtilities.ScreenShotss;
import ExcelDataUtils.ReadExcel;
import POM.*;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.WebDriver;

public class NewTest {
	WebDriver dr;JustWait wt;
	Browsers br; LoginPage lg; ReadExcel re;
	ProductsPage pp;;ScreenShotss ss;CartPage cp;CheckOutPage cop;ThankyouPage tp;
	
  @Test(dataProvider = "InvalidData")
  
  public void InvalidLogin(String username, String password, String Exp_res) {
	  
	  br = new Browsers();
	  dr = br.launchBrowser(br.Chrome,"https://www.saucedemo.com/index.html");
	  wt = new JustWait(dr);
	  ss = new ScreenShotss(dr);
	  lg = new LoginPage(dr);
	  lg.DoLogin(username, password);
	  String err =lg.InvalidError();
	  ss.ScreenShott("error_msg.png");
	  SoftAssert sa = new SoftAssert();
	  sa.assertEquals(err, Exp_res);
	  sa.assertAll();
	  dr.quit();
  }
  @DataProvider
  public String[][] InvalidData() {
	  re = new ReadExcel();
	  re.get_data("Login Invalid Credentials",2,3);
	  return re.testdata;
  }
  
  @Test(dataProvider = "validData")
  public void validLogin(String username, String password, String Exp_res) {
	  br = new Browsers();
	  dr = br.launchBrowser(br.Chrome,"https://www.saucedemo.com/index.html");
	  ss = new ScreenShotss(dr);
	  lg = new LoginPage(dr);
	  lg.DoLogin(username, password);
	  pp= new ProductsPage(dr);
	  String uname =pp.VerifyLogin();
	  ss.ScreenShott("AccountName.png");
	  SoftAssert sa = new SoftAssert();
	  sa.assertEquals(uname, Exp_res);
	  sa.assertAll();
	  dr.quit();
  }
  @DataProvider
  public String[][] validData() {
	  re = new ReadExcel();
	  re.get_data("Valid Credentials",2,3);
	  return re.testdata;
  }

  @Test(dataProvider = "AddToCart")
  public void AddToCart(String username, String password) {
	  br = new Browsers();
	  dr = br.launchBrowser(br.Chrome,"https://www.saucedemo.com/index.html");
	  ss = new ScreenShotss(dr);
	  lg = new LoginPage(dr);
	  lg.DoLogin(username, password);
	  pp = new ProductsPage(dr);
	  pp.AddtoCart(1);
	 String ExpName= pp.ItemName(1);
	  float ExpPrice =pp.Price(1);
	 pp.ClickCart();
	 cp = new CartPage(dr);
	 String Actname =cp.CartItemName(1);
	 float ActPrice =cp.cartPrice(1);
	  SoftAssert sa = new SoftAssert();
	  sa.assertEquals(ActPrice, ExpPrice);
	  sa.assertEquals(Actname, ExpName);
	  sa.assertAll();
	  dr.quit();
  }
  @DataProvider
  public String[][] AddToCart() {
	  re = new ReadExcel();
	  re.get_data("Valid Credentials",1,2);
	  return re.testdata;
  }
 
  @Test(dataProvider = "LoginData")
  public void ProductRemoval(String username, String password, String FirstName, String LastName, String ZipCode,String msg) {
	  br = new Browsers();
	  dr = br.launchBrowser(br.Chrome,"https://www.saucedemo.com/index.html");
	  ss = new ScreenShotss(dr);
	  lg = new LoginPage(dr);
	  lg.DoLogin(username, password);
	  pp = new ProductsPage(dr);
	  pp.AddtoCart(1);
	  pp.ClickCart();
	  cp = new CartPage(dr);
	  cp.ContinueShopping();
	  pp.AddtoCart(2);
	  ss.ScreenShott("ConfirmAdded.png");
	  pp.ClickCart();
	  boolean b = cp.RemoveProduct(2);
	  ss.ScreenShott("ConfirmRemoved.png");
	  cp.CheckOut();
	  cop = new CheckOutPage(dr);
	  cop.Continue(FirstName, LastName, ZipCode);
	  tp = new ThankyouPage(dr);
	  String act_msg = tp.ThankYouMsg();
	  ss.ScreenShott("FinalPage.png");
	  SoftAssert sa = new SoftAssert();
	  sa.assertFalse(b);
	  sa.assertEquals(act_msg, msg);
	  sa.assertAll();
	  dr.quit();

	 
  }
  @DataProvider
  public String[][] LoginData() {
	  re = new ReadExcel();
	  re.get_data("CheckOut",1,6);
	  return re.testdata;
  }
  
  
  
}
