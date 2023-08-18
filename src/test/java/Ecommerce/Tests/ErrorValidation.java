package Ecommerce.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import Ecommerce.TestComponents.BaseTest;
import Ecommerce.pageObjects.ProductCatalouge;

public class ErrorValidation extends BaseTest {
	
	@Test(retryAnalyzer = Retry.class)
	public void LoginInvalidUser() {
		
		landingPage.goTo();
		landingPage.LoginApplication("Nishant@gmail.com", "Password@1234");			
		Assert.assertEquals("Incorrect email or password.", landingPage.invalidLoginMessage());
	}

}
