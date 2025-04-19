package ecomAutomation;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.AbstractComponent;

public class CartPage extends AbstractComponent{
	WebDriver driver;
	
	
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	
	@FindBy(css = ".totalRow button")
	WebElement checkout;
	
	public Boolean verifyProductDisplay(String productName)
	{
		Boolean match=cartProducts.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
		
	}
	
	
	
	public CheckOutPage goToCheckOut() throws InterruptedException
	{
		Thread.sleep(2000);
		System.out.println("Added method to cover delay");
		checkout.click();
		return new CheckOutPage(driver);
		
	}
	

}
