package Utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility {

	static WebDriver driver;

	public static void clickOn(WebDriver driver1, WebElement element, int timeout) {

		new WebDriverWait(driver1, timeout).until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	public static WebElement elementToBeVisible(WebDriver driver2, WebElement element, int timeout) {

		new WebDriverWait(driver2, timeout).until(ExpectedConditions.visibilityOf(element));
		return element;
	}

	public void takeScreenShot() {

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentdir = System.getProperty("user.dir");
		try {
			FileUtils.copyFile(src, new File(currentdir + "/ScreenShots/" + System.currentTimeMillis() + ".jpg"));
			// FileUtils.copyFile(src, new File("D:\\Dheeru\\Work\\TestNgTest\\ScreenShots"
			// + testMethodName +"_"+".jpg"));
			//
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
