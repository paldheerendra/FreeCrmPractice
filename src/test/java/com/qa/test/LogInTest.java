package com.qa.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

public class LogInTest {

	static WebDriver driver;

	Logger log = Logger.getLogger(LogInTest.class);

	@BeforeMethod
	public void setUp() throws IOException {

		log.info("****************************** Starting test cases execution  *****************************************");

		Properties prop = new Properties();
		FileInputStream fip = new FileInputStream("C:\\Users\\Mohsin\\Dheeru\\Work\\FreeCRMPractice\\config.properties");
		prop.load(fip);

		log.info("*****Opening the broweser*****");
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

		log.info("*******Maximizing the Browser*******");
		driver.manage().window().maximize();
		// driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		log.info("*******Entering the URL*********");
		driver.get("https://www.freecrm.com/index.html");
	}

	@Test(enabled = true)
	public void logInTest() throws InterruptedException {

		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("naveenk");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("test@123");

		WebElement login = driver.findElement(By.xpath("//input[@value='Login']"));
		// login.click();
		clickOn(driver, login, 6000);

		driver.switchTo().frame("mainpanel");

		String userXpath = "//a[contains(text(), 'Upgrade your account')]//parent::td//preceding-sibling::td//font";

		WebElement user = driver.findElement(By.xpath(userXpath));

		elementToBeVisible(driver, user, 30);

		Assert.assertEquals(user.getText(), "  User: Naveen K");
		System.out.println(user.getText());

	}

	@Test(enabled = false)
	public void logOutTest() {
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("naveenk");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("test@123");

		WebElement login = driver.findElement(By.xpath("//input[@value='Login']"));
		// login.click();
		clickOn(driver, login, 6000);

		driver.switchTo().frame("mainpanel");

		String logout = "//i[contains(@class, 'sign-out')]//parent::font//parent::a";

		driver.findElement(By.xpath(logout)).click();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();

	}

	public void clickOn(WebDriver driver1, WebElement element, int timeout) {

		new WebDriverWait(driver1, timeout).until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	public WebElement elementToBeVisible(WebDriver driver2, WebElement element, int timeout) {

		new WebDriverWait(driver2, timeout).until(ExpectedConditions.visibilityOf(element));
		return element;
	}

}
