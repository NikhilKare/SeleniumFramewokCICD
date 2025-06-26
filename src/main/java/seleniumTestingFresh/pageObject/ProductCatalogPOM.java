package seleniumTestingFresh.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import seleniumtesting.AbstractComponents.AbstractComponents;

public class ProductCatalogPOM extends AbstractComponents{
	WebDriver driver;
	//Parameterized constructor
	public ProductCatalogPOM(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//Page Factory Design Pattern.
	//Page factory is specific to driver.constructions only
	@FindBy(css=".mb-3")
	List<WebElement> products;
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastContainer = By.cssSelector("#toast-container");
	
	public  List<WebElement> getProductsList() {
		waitForElementToAppear(productsBy);
		return products;
	}
	public WebElement getProductByName(String productName) {
		return getProductsList().stream().filter(product->product.findElement(By.cssSelector("b"))
		.getText().equals(productName)).findFirst().orElse(null);
	}
	public ProductCartPOM addProductToCart(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastContainer);
		waitForElementToDispappera(spinner);
		ProductCartPOM productCartPOM = new ProductCartPOM(driver);
		return productCartPOM;
	}
	
}
