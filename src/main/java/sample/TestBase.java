package sample;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {

	WebDriver driver;
	Duration waitTime;
	WebDriverWait wait;
	WebElement inputField;

	final String SHINABLE_PATH = "http://localhost:8080/HRMS/";

	/**
	 * EdgeDriverのパスを指定
	 */
	public void setPropatyPath() {
		final String EDGE_DRIVER_PATH = "edgeDriver/msedgedriver.exe";
		System.setProperty("webdriver.edge.driver", EDGE_DRIVER_PATH);
	}

	/**
	 * SHINABLEを開く
	 */
	public void siteOpen() {
		driver = new EdgeDriver();
		driver.get(SHINABLE_PATH);
		driver.manage().window().maximize();
	}

	/**
	 * 入力
	 */
	public void input(String xpath, String data) {
		waitTime = Duration.ofSeconds(10);
		wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		driver.findElement(By.xpath(xpath)).sendKeys(data);
	}

	public void startShinable() {
		setPropatyPath();
		siteOpen();
	}

	public void closeShinable() {
		driver.quit();
	}
}
