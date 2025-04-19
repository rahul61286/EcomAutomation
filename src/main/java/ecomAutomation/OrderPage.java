package ecomAutomation;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.AbstractComponent;

public class OrderPage extends AbstractComponent{
	WebDriver driver;
	
	
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//tr[@class='ng-star-inserted']/td[2]")
	List<WebElement> orderProduct;
	
	
	
	
	public Boolean verifyOrderDisplay(String productName)
	{
		Boolean match=orderProduct.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
		
	}
	
	
	
	

}
