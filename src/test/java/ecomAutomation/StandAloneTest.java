package ecomAutomation;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		
		String productName="ZARA COAT 3";
		//String orderId="";
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client/");
		driver.findElement(By.id("userEmail")).sendKeys("dummyf@selenium.com");
		driver.findElement(By.id("userPassword")).sendKeys("Pwd@1234");
		driver.findElement(By.xpath("//input[@id='login']")).click();
		
		
		LandingPage lPage=new LandingPage(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod=products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null); 
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click(); 	 
		
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		
		List<WebElement> productCart=driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean matchResult=productCart.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(matchResult);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		/*
		 * driver.findElement(By.xpath("//input[@placeholder='Select Country']")).
		 * sendKeys("ind");
		 * 
		 * wait.until(ExpectedConditions.elementToBeClickable(By.
		 * xpath("//button/span[text()=' India']"))).click();
		 * 
		 * driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).
		 * click();
		 * 
		 * orderId=driver.findElement(By.xpath("//tr[@class='ng-star-inserted']//label")
		 * ).getText(); orderId=orderId.replace("|", "").trim();
		 * System.out.println("Order ID is :"+orderId);
		 */
		 
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.quit();
		
		
		
		

	}

}
