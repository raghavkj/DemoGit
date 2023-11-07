package raghavkjacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import raghavkjacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement country;

	@FindBy(xpath = "//section/button[2]")
	WebElement selectCountry;

	@FindBy(css = ".action__submit")
	WebElement placeOrder;
	
	By results = By.cssSelector(".ta-results");

	public void selectCountry(String countryName) throws InterruptedException {
		waitForElementClickable(country);	
		Actions action = new Actions(driver);
		action.sendKeys(country, countryName).build().perform();
		selectCountry.click();
	}

	public ConfirmationPage submitOrder() {
		placeOrder.click();
		return new ConfirmationPage(driver);
	}

}
