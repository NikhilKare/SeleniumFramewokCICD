package com.nikhil.testing.seleniumTestingFresh;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nikhil.testComponents.BaseTest;

import seleniumTestingFresh.pageObject.ConfirmationPagePOM;
import seleniumTestingFresh.pageObject.ProductCartPOM;
import seleniumTestingFresh.pageObject.ProductCatalogPOM;
import seleniumTestingFresh.pageObject.ProductCheckoutPOM;
import seleniumTestingFresh.pageObject.ProductOrdersPOM;

public class SubmitOrderTest extends BaseTest{
	//String productName = "ZARA COAT 3";
    
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String,String>> data =  getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//com//nikhil//testData//UserData.json");
		 return new Object[][] {{data.get(0)},{data.get(1)}};
		 }
	
	
	@Test(dataProvider = "getData",groups= {"purchase"})
	public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {
		String countryName = "india";
		String email = input.get("email");
		String pass = input.get("pass");
		boolean expectedResult = Boolean.parseBoolean(input.get("expectedResult"));
		String productName = input.get("productName");
		ProductCatalogPOM productCatalog = landingPage.loginAction(email,pass);
		  if (!expectedResult) {
			  throw new SkipException("Skipping verification for user with failed login for Email : " + email);
			}
	    	productCatalog.getProductsList();
			ProductCartPOM productCartPOM = productCatalog.addProductToCart(productName);
			Boolean match = productCartPOM.cartSection(productName);
			Assert.assertTrue(match);
			ProductCheckoutPOM productCheckoutPOM = productCartPOM.goTocheckOut();
			ConfirmationPagePOM confirmPagePOM = productCheckoutPOM.checkOutPage(countryName);
			String confirmMsg = confirmPagePOM.getOrderConfirmationMessage();
			Assert.assertTrue(confirmMsg.equalsIgnoreCase("Thankyou for the order."));
	}
	@Test(dataProvider = "getData", dependsOnMethods = {"submitOrder"},groups= {"purchase"})
	public void verifyOrder(HashMap<String, String> input) throws InterruptedException {
		String email = input.get("email");
	    String pass = input.get("pass");
	    boolean expectedResult = Boolean.parseBoolean(input.get("expectedResult"));
	    String productName = input.get("productName");
	    driver.get("https://rahulshettyacademy.com/client");
	    if (!expectedResult) {
	        throw new SkipException("Skipping verification for user with failed login for Email : " + email);
	    }

	    ProductCatalogPOM loginResult = landingPage.loginAction(email, pass);
	    if (loginResult == null) {
	        throw new SkipException("Login failed or timed out for: " + email);
	    }

	    ProductOrdersPOM productOrder = new ProductOrdersPOM(driver);
	    Assert.assertTrue(productOrder.verifyOrdersNav(productName));
	}
	

}
