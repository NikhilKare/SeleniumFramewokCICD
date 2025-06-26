package seleniumTestingFresh.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import seleniumtesting.AbstractComponents.AbstractComponents;

public class ConfirmationPagePOM extends AbstractComponents{
	WebDriver driver;
	public ConfirmationPagePOM(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css=".hero-primary")
	WebElement comfirmationText;
	
	public String getOrderConfirmationMessage() {
		String confirmMsg = comfirmationText.getText();
		return confirmMsg;
	}

}
