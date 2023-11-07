package raghavkjacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import raghavkjacademy.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//tbody//td[2]")
	List<WebElement> orderedProducts;

	public Boolean VerifyOrderDisplay(String productName) {
		Boolean match = orderedProducts.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productName));
		return match;
	}
}
