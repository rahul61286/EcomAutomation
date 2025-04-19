package ecomAutomation;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;
import testComponents.BaseTest;

public class SubmitOrderTest extends BaseTest{
	
	//String productName="ZARA COAT 3";
	
	
	@Test(dataProvider="getData" , groups = "Purchase")
	public void submitOrder(String email, String password, String productName) throws Exception 
	{
		landingPage.loginApplication(email, password);
		ProductCatalogue ProductCatalogue=new ProductCatalogue(driver);
		List<WebElement> productList=ProductCatalogue.getProductList();
		ProductCatalogue.addProductToCart(productName);
		ProductCatalogue.gotoCartPage();
		
		
		
		CartPage cartPage=new CartPage(driver);
		Boolean match=cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		cartPage.goToCheckOut();
		 CheckOutPage checkOutPage=new CheckOutPage(driver);
		 checkOutPage.selectCountry("india");
		 checkOutPage.submitOrder();
		 
		 
		 ConfirmationPage confirmationPage=new ConfirmationPage(driver);
		 String confirmMessage=confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	
	
//	@Test(dependsOnMethods = {"submitOrder"})
//	public void orderHistoryTest()
//	{
//		landingPage.loginApplication(prop.getProperty("email"), prop.getProperty("password"));
//		OrderPage orderPage=new OrderPage(driver);
//		orderPage.gotoOrderPage();
//		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
//		
//	}
	
	
	@DataProvider
	public Object[][] getData()
	{
		
		return new Object[][] {{"dummyf@selenium.com","Pwd@1234","ZARA COAT 3"}, {"dummyS@selenium.com","Pwd@1234","ADIDAS ORIGINAL"}};
	}


}
