package com.nikhil.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import seleniumTestingFresh.pageObject.LandingPagePOM;

public class BaseTest {
	public WebDriver driver;
	public LandingPagePOM landingPage;
	public String email;
	public String pass;
	public String productName;
	public boolean expectedResult;
	public WebDriver initializeDriver() throws IOException {
		//properties
		Properties prop = new Properties();
		FileInputStream fls = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//com//nikhil//resources//GlobalProperties.properties");
		prop.load(fls);
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		//prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	/*
	 * @BeforeMethod(alwaysRun = true) public void setupData(Object[] testData) { //
	 * This method will run before each @Test if (testData.length > 0 && testData[0]
	 * instanceof HashMap) {
	 * 
	 * @SuppressWarnings("unchecked") HashMap<String, String> input =
	 * (HashMap<String, String>) testData[0]; email = input.get("email"); pass =
	 * input.get("pass"); productName = input.get("productName"); expectedResult =
	 * Boolean.parseBoolean(input.get("expectedResult")); } }
	 */
	
	//method for reading data from JSON and passing the data to test case
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		//String to Hash map
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
		});
		return data;
	}
	public String getScreenshot(String testCasename, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//"+ testCasename + ".png");
		FileUtils.copyFile(source,file );
		return System.getProperty("user.dir")+"//reports//"+ testCasename + ".png";
	}
	@BeforeMethod(alwaysRun = true)
	public LandingPagePOM launchApplication() throws IOException {
		driver = initializeDriver(); 
		landingPage = new LandingPagePOM(driver);//object creation
		landingPage.navigateURL();
		return landingPage;
	}
	@AfterMethod(alwaysRun = true)
	public void closeBrowser() {
		driver.close();
	}	
}
