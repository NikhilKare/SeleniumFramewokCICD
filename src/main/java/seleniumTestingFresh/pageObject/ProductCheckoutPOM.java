package seleniumTestingFresh.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import seleniumtesting.AbstractComponents.AbstractComponents;

public class ProductCheckoutPOM extends AbstractComponents{
	WebDriver driver;
	public ProductCheckoutPOM(WebDriver driver) {
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
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	@FindBy(css=".action__submit")
	WebElement submitButton;
	@FindBy(css=".hero-primary")
	WebElement comfirmationText;
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	By result = By.cssSelector(".ta-results");
	
	
	public ConfirmationPagePOM checkOutPage(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(country,countryName).build().perform();
		waitForElementToAppearCart(result);
		selectCountry.click();
		submitButton.click();
		return new ConfirmationPagePOM(driver);
	}

}
