package Ecommerce.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponents {
	//Web driver
	WebDriver driver;
	WebDriverWait wait;
	public AbstractComponents(WebDriver driver) {
		wait	 = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		this.driver = driver;
	}
	
	public void waitForElementToAppear(By FindBy) {
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(FindBy));
	}
	
	public void waitForElementToDisappear(By findBy) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}

}
