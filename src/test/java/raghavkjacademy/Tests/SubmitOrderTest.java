package raghavkjacademy.Tests;

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



public class SubmitOrderTest {
	

	public static void main(String[] args) throws InterruptedException {
		String produtName = "ADIDAS ORIGINAL";
		WebDriver driver;
		WebDriverWait wait;
		driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	

		driver.findElement(By.id("userEmail")).sendKeys("ragkj@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("R@ghavkj25");
		driver.findElement(By.id("login")).click();

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".mb-3"))));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement product = products.stream()
				.filter(s -> s.findElement(By.cssSelector("b")).getText().equals(produtName)).findFirst().orElse(null);
		product.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));

		WebElement cartButton = driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']"));
		wait.until(ExpectedConditions.elementToBeClickable(cartButton));
		cartButton.click();

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match = cartProducts.stream().anyMatch(s -> s.getText().equalsIgnoreCase(produtName));

		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		WebElement country = driver.findElement(By.xpath("//input[@placeholder='Select Country']"));

		wait.until(ExpectedConditions.elementToBeClickable(country));
		// country.sendKeys("Ind");

		Actions action = new Actions(driver);
		action.sendKeys(country, "Ind").build().perform();

		List<WebElement> countryList = driver.findElements(By.xpath("//span[@class='ng-star-inserted']"));
		countryList.stream().filter(s -> s.getText().equalsIgnoreCase("India")).findFirst().orElse(null).click();
		driver.findElement(By.cssSelector(".actions a")).click();

		String orderID = driver.findElement(By.cssSelector(".em-spacer-1 .ng-star-inserted")).getText();
		System.out.println("Order ID --->" + orderID.split(" ")[1].trim());

		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		Thread.sleep(10000);
		driver.quit();
	}

}
