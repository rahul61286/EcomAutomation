package ecomAutomation;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;
import testComponents.BaseTest;

public class ErrorValidations extends BaseTest{

	@Test
	public void submitOrderInvalidEmail() throws Exception 
	{
		
		landingPage.loginApplication(prop.getProperty("email")+"1234", prop.getProperty("password"));
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	
	
	@Test
	public void submitProductValidation() throws Exception 
	{
		
		String productName="ZARA COAT 3";

		landingPage.loginApplication("dummyS@selenium.com", "Pwd@1234");
		ProductCatalogue ProductCatalogue=new ProductCatalogue(driver);
		List<WebElement> productList=ProductCatalogue.getProductList();
		ProductCatalogue.addProductToCart(productName);
		ProductCatalogue.gotoCartPage();
		
		
		
		CartPage cartPage=new CartPage(driver);
		Boolean match=cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		 
	}

}
