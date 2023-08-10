package Ecommerce.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Ecommerce.pageObjects.ProductCatalouge;
import EcommerceTestComponents.BaseTest;

public class ErrorValidation extends BaseTest {
	
	@Test
	public void LoginInvalidUser() {
		
		landingPage.goTo();
		landingPage.LoginApplication("Nishant@gmail.com", "Password@1234");			
		Assert.assertEquals("Incorrect email or password.", landingPage.invalidLoginMessage());
	}

}
