package raghavkjacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import raghavkjacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement email;

	@FindBy(id = "userPassword")
	WebElement pwd;

	@FindBy(id = "login")
	WebElement submit;

	@FindBy(xpath = "//div[contains(@class,'toast-error')]")
	WebElement errorMsg;

	public ProductCataloguePage loginApplication(String usn, String password) {
		email.sendKeys(usn);
		pwd.sendKeys(password);
		submit.click();
		return new ProductCataloguePage(driver);
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

	public String getErrorMessage() {
		waitForWebElementToAppear(errorMsg);
		return errorMsg.getText();
	}

}
