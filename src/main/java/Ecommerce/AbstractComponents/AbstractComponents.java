package Ecommerce.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Ecommerce.pageObjects.CartPage;

public class AbstractComponents {

	WebDriver driver;
	WebDriverWait wait;

	@FindBy(xpath = "//ul/li[4]/button")
	WebElement cartButton;

	public AbstractComponents(WebDriver driver) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		this.driver = driver;
	}

	public void waitForElementToAppear(By FindBy) {

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(FindBy));
	}

	public void waitForElementToAppear(WebElement FindBy) {

		wait.until(ExpectedConditions.visibilityOfAllElements(FindBy));
	}

	public void waitForElementToDisappear() throws InterruptedException {
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
		Thread.sleep(5000);
	}

	public CartPage goToCart() {
		cartButton.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}

}
