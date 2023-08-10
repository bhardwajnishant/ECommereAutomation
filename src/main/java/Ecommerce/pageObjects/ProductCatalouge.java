package Ecommerce.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Ecommerce.AbstractComponents.AbstractComponents;

public class ProductCatalouge extends AbstractComponents {
	
	WebDriver driver;
	
	public ProductCatalouge(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(css =".mb-3")
	List<WebElement> products;
	
	By productBy = By.cssSelector(".mb-3");
	By particularProduct = By.cssSelector("h5[style='text-transform: uppercase;']");
	By addToCart = By.cssSelector("button[class='btn w-10 rounded']");
	By toastMessage = By.id("toast-container");
	By spinner = By.cssSelector(".ng-animating"); 
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream().filter(product -> product.findElement(particularProduct)
				.getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addToCart(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);		
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear();
	}
	
}
