package Ecommerce.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

//import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Ecommerce.pageObjects.CartPage;
import Ecommerce.pageObjects.CheckOutPage;
import Ecommerce.pageObjects.ConfirmationPage;
import Ecommerce.pageObjects.LandingPage;
import Ecommerce.pageObjects.OrdersPage;
import Ecommerce.pageObjects.ProductCatalouge;
import EcommerceTestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
//import junit.framework.Assert;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider="getData")
	public void submitOrder(String email, String password, String productName) throws IOException, InterruptedException {

		String Country = "India";
		landingPage.goTo();
		ProductCatalouge productCatalouge = landingPage.LoginApplication(email, password);
		List<WebElement> products = productCatalouge.getProductList();
		productCatalouge.addToCart(productName);
		CartPage cartpage = productCatalouge.goToCart();
		Thread.sleep(5000);
		boolean match = cartpage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartpage.goToCheckoutPage();
		checkOutPage.selectCountry();
		ConfirmationPage confirmationPage = checkOutPage.submitOrder();
		String confirmation = confirmationPage.confirmMessage();
		Assert.assertEquals(confirmation, "THANKYOU FOR THE ORDER.");
	}

	@Test(dependsOnMethods={"submitOrder"})
	public void orderHistory() {
		landingPage.goTo();
		ProductCatalouge productCatalouge = landingPage.LoginApplication("Nishant@gmail.com", "Password@123");
		OrdersPage ordersPage = productCatalouge.goToOrders();
		Boolean match = ordersPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
	}
	
	@DataProvider
	public Object[][] getData() {
		return new Object[][]{{"Nishant@gmail.com", "Password@123","ZARA COAT 3"},{"Nishant@gmail.com", "Password@123","ADIDAS ORIGINAL"}};
	}

}
