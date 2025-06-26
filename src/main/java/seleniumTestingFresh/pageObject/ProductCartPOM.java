package seleniumTestingFresh.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import seleniumtesting.AbstractComponents.AbstractComponents;

public class ProductCartPOM extends AbstractComponents{
	WebDriver driver;
	public ProductCartPOM(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	@FindBy(css="[routerlink*='cart']")
	WebElement cartNav;
	@FindBy(css=".totalRow button")
	WebElement checkout;
	
	By country = By.cssSelector("[placeholder='Select Country']");
	By result = By.cssSelector(".ta-results");
	By listItem = By.xpath("(//button[contains(@class,'ta-item')])[2]");
	By submitButton = By.cssSelector(".action__submit");
	By comfirmationText = By.cssSelector(".hero-primary");
	
	public Boolean cartSection(String productName) {
		cartNav.click();
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().
				  equalsIgnoreCase(productName));
		return match;
	}
	public ProductCheckoutPOM goTocheckOut() {
		checkout.click();
		ProductCheckoutPOM productCheckoutPOM = new ProductCheckoutPOM(driver);
		return productCheckoutPOM;
	}

}
