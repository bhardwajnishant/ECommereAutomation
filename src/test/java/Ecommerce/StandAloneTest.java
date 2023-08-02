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

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		String Country = "India";
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//Login 
		driver.findElement(By.id("userEmail")).sendKeys("Nishant@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Password@123");	
		driver.findElement(By.id("login")).click();
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		//find particular product
		WebElement prod = products.stream().filter(product -> product.findElement(By.cssSelector("h5[style='text-transform: uppercase;']"))
		.getText().equals(productName)).findFirst().orElse(null);	
		
		//Add product in cart
		prod.findElement(By.cssSelector("button[class='btn w-10 rounded']")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		System.out.println("Added to cart");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ng-animating")));
		
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
