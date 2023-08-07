package Ecommerce.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Ecommerce.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		
		this.driver=driver;
		
		//initialization
		
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(id ="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement submit;
	
	public void LoginApplication(String email, String password) {
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}
