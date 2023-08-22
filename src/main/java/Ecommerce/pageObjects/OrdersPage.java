package Ecommerce.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Ecommerce.AbstractComponents.AbstractComponents;

public class OrdersPage extends AbstractComponents {
	public WebDriver driver;

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> orderProds;	
	
	public Boolean VerifyProductDisplay(String productName) {
		Boolean match = orderProds.stream().anyMatch(orderProd -> orderProd.getText().equalsIgnoreCase(productName));
		return match;
	}
	

}
