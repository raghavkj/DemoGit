package raghavkjacademy.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import raghavkjacademy.TestComponents.BaseTest;
import raghavkjacademy.pageobjects.CartPage;
import raghavkjacademy.pageobjects.CheckoutPage;
import raghavkjacademy.pageobjects.ConfirmationPage;
import raghavkjacademy.pageobjects.LandingPage;
import raghavkjacademy.pageobjects.ProductCataloguePage;

public class ErrorValidationsTest extends BaseTest {

	@Test
	public void loginErrorValidation() throws IOException, InterruptedException {
		String produtName = "ADIDAS ORIGINAL";		
		landingPage.loginApplication("ragkj3@gmail.com", "R@ghavkj25");		
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException {
		String produtName = "ADIDAS ORIGINAL";
		ProductCataloguePage productCatalogue = landingPage.loginApplication("ragkj@gmail.com", "R@ghavkj25");
		productCatalogue.getProductsList();
		productCatalogue.addProductToCart(produtName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Assert.assertFalse(cartPage.verifyProductsList("ADIDAS ORIGINAL 3"));	

	}
}
