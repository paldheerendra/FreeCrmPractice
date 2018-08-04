package com.qa.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Utilities.Utility;

public class LogOutTest extends Utility {

	static WebDriver driver;
	@BeforeMethod
	public void setUp() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fip = new FileInputStream("C:\\Users\\Mohsin\\Dheeru\\Work\\FreeCRM\\config.properties");
		prop.load(fip);

		
		String browsername = prop.getProperty("browser");
		if (browsername.equals("FF")) {
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\Mohsin\\Dheeru\\Work\\geckodriver-v0.20.0-win32\\geckodriver.exe");
			driver = new FirefoxDriver();
		} 
		
		else if (browsername.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\Mohsin\\Dheeru\\Work\\chromedriver_win32\\chromedriver.exe");

			driver = new ChromeDriver();
		}

		
		driver.manage().window().maximize();
		//driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.get("https://www.freecrm.com/index.html");
	}

	@Test(enabled=false)
	public void logInTest() throws InterruptedException {

		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("naveenk");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("test@123");

		WebElement login = driver.findElement(By.xpath("//input[@value='Login']"));
		//login.click();
		clickOn(driver, login, 6000);

		driver.switchTo().frame("mainpanel");

		String userXpath = "//a[contains(text(), 'Upgrade your account')]//parent::td//preceding-sibling::td//font";

		WebElement user = driver.findElement(By.xpath(userXpath));

		elementToBeVisible(driver, user, 30);

		Assert.assertEquals(user.getText(), "  User: Naveen K");
		System.out.println(user.getText());
		

	}

	@Test
	public void logOutTest() {
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("naveenk");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("test@123");

		WebElement login = driver.findElement(By.xpath("//input[@value='Login']"));
		//login.click();
		clickOn(driver, login, 6000);

		driver.switchTo().frame("mainpanel");

		String logout = "//i[contains(@class, 'sign-out')]//parent::font//parent::a";

		driver.findElement(By.xpath(logout)).click();

		
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();

	}

	
}
