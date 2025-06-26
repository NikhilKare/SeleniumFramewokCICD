package seleniumTestingFresh.pageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import seleniumtesting.AbstractComponents.AbstractComponents;

public class ProductOrdersPOM extends AbstractComponents{
	WebDriver driver;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement ordersNav;
	@FindBy(xpath="(//td[contains(text(),'ZARA COAT 3')])")
	List<WebElement> ordersNames;
	//OR
	//@FindBy(css="tr td:nth-child(3)")
	//List<WebElement> ordersNamesCss;
	
	public ProductOrdersPOM(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public Boolean verifyOrdersNav(String productName) {
		ordersNav.click();
		Boolean match = ordersNames.stream().anyMatch(orderName->orderName.getText().
				  equalsIgnoreCase(productName));
		return match;
	}
}
