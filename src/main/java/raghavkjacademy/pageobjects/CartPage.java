package raghavkjacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import raghavkjacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".cartSection h3")
	List<WebElement> productsList;
	
	@FindBy(xpath = "//li[@class='totalRow']//button")
	WebElement btnCheckout;
	
	public Boolean verifyProductsList(String productName) {
		Boolean match = productsList.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout() {
		btnCheckout.click();
		return new CheckoutPage(driver);
	}

	

}
