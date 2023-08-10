package Ecommerce.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Ecommerce.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProds;
	
	@FindBy(css = ".totalRow button")
	WebElement checkOutButton;	

	public Boolean VerifyProductDisplay(String productName) {
		Boolean match = cartProds.stream().anyMatch(cartprod -> cartprod.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOutPage goToCheckoutPage() {		
		checkOutButton.click();		
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		return checkOutPage;
	}

}
