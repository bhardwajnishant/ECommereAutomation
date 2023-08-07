package Ecommerce;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Ecommerce.pageObjects.LandingPage;
import Ecommerce.pageObjects.ProductCatalouge;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		//  TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		String Country = "India";
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(options);
		
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
		landingPage.LoginApplication("Nishant@gmail.com", "Password@123");
		
		ProductCatalouge productCatalouge = new ProductCatalouge(driver);
		List<WebElement> products = productCatalouge.getProductList();
		productCatalouge.addToCart(productName);
		//find particular product
			
		
		//Add product in cart
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		
		
		System.out.println("Added to cart");
		
		Thread.sleep(5000);		
		driver.findElement(By.xpath("//ul/li[4]/button")).click();
		
		//My Cart products
		Thread.sleep(5000);
		List<WebElement> cartProds = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match=cartProds.stream().anyMatch(cartprod -> 
		cartprod.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		//Place order
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("ind");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		//Thread.sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(".actions a")).click();
		String conformation = driver.findElement(By.className("hero-primary")).getText();
		Assert.assertEquals(conformation, "THANKYOU FOR THE ORDER.");
		
		
		
	}

}
