package seleniumTestingFresh.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import seleniumtesting.AbstractComponents.AbstractComponents;

public class LandingPagePOM extends AbstractComponents{
	WebDriver driver;
	//Parameterized constructor
	public LandingPagePOM(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//Page Factory Design Pattern.
	@FindBy(id="userEmail")
	WebElement email;
	@FindBy(id="userPassword")
	WebElement password;
	@FindBy(id="login")
	WebElement loginBtn;
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMsg;
	@FindBy(css="div[aria-label='Login Successfully']") 
	WebElement loginSucess;
	
	public void navigateURL() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMsg);
		return errorMsg.getText();
	}
	
	public ProductCatalogPOM loginAction(String userEmail, String userPass) throws InterruptedException {
		email.sendKeys(userEmail);
		password.sendKeys(userPass);
		loginBtn.click();
		return new ProductCatalogPOM(driver);

	}
}
