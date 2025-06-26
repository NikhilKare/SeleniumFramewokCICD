package com.nikhil.testing.seleniumTestingFresh;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nikhil.testComponents.BaseTest;
import com.nikhil.testComponents.Retry;

public class ErrorValidationTest extends BaseTest{
	@Test(groups= {"error"},retryAnalyzer = Retry.class)
	public void errorValidation() throws InterruptedException, IOException {

		landingPage.loginAction("test2130@gmail.com", "Test");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}

}
