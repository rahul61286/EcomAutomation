package ecomAutomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.AbstractComponent;

public class LandingPage extends AbstractComponent{
	WebDriver driver;
	
	
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(id="userEmail")
	WebElement useremailElement;
	
	@FindBy(id="userPassword")
	WebElement passwordElement;
	
	@FindBy(id="login")
	WebElement loginButton;
	
	@FindBy(id="toast-container")
	WebElement errorMessage;
	
	@FindBy(css=".fa.fa-sign-out")
	WebElement logout;
	
	public ProductCatalogue loginApplication(String email,String password)
	{
		useremailElement.clear();
		useremailElement.sendKeys(email);
		passwordElement.clear();
		passwordElement.sendKeys(password);
		loginButton.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	
	public void navigateToApplication()
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public void logoutApplication()
	{
		logout.click();
	}
	

}
