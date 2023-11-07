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
import raghavkjacademy.pageobjects.OrderPage;
import raghavkjacademy.pageobjects.ProductCataloguePage;

public class SubmitOrderTest_A extends BaseTest {
	public String productName = "ADIDAS ORIGINAL";
	
	@Test
	public void submitOrder() throws IOException, InterruptedException {

		ProductCataloguePage productCatalogue = landingPage.loginApplication("ragkj@gmail.com", "R@ghavkj25");
		productCatalogue.getProductsList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Assert.assertTrue(cartPage.verifyProductsList(productName));
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		Assert.assertTrue(confirmationPage.getConfirmationMessage().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		ProductCataloguePage productCatalogue = landingPage.loginApplication("ragkj@gmail.com", "R@ghavkj25");
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
	}

}
