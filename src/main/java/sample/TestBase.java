package sample;

import static org.testng.Assert.*;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {

	WebDriver driver;
	Duration waitTime;
	WebDriverWait wait;
	WebElement inputField;

	//eclipseでアプリ実行
	static final String SHINABLE_PATH = "http://localhost:8081/login";
	//Tomcatにデプロイ
	static final String SHINABLE_DEPLOYED_PATH = "http://localhost:8000/login";
	//Azure上にデプロイ
	static final String SHINABLE_CLOUD_PATH = "https://shinable.azurewebsites.net/login";
	static final String EMPLOYEE_NAME = "日本　プロ太";

	/**
	 * EdgeDriverのパスを指定
	 */
	public void setPropatyPath() {
		final String EDGE_DRIVER_PATH = "edgeDriver/msedgedriver.exe";
		System.setProperty("webdriver.edge.driver", EDGE_DRIVER_PATH);
	}
	
//	/**
//	 * ChromeDriverのパスを指定
//	 */
//	public void setPropatyPath() {
//		final String CHOROME_DRIVER_PATH = "chromeDriver/chromedriver.exe";
//		System.setProperty("webdriver.chrome.driver", CHOROME_DRIVER_PATH);
//	}

	/**
	 * SHINABLEを開く
	 */
	public void siteOpen() {
//		driver = new EdgeDriver();
		driver = new ChromeDriver();
		driver.get(SHINABLE_DEPLOYED_PATH);
		driver.manage().window().maximize();
	}

	/**
	 * 入力
	 * @param xpath
	 * @param data
	 */
	public void input(String xpath, String data) {
		waitForElementVisible(xpath);
		driver.findElement(By.xpath(xpath)).sendKeys(data);
	}

	/**
	 * クリック
	 * @param xpath
	 */
	public void click(String xpath) {
		waitForElementVisible(xpath);
		driver.findElement(By.xpath(xpath)).click();
	}

	/**
	 * wait
	 * @param xpath
	 */
	public void waitForElementVisible(String xpath) {
		waitTime = Duration.ofSeconds(10);
		wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}

	/**
	 * assert text
	 * @param xpath
	 * @param name
	 */
	public void assertText(String xpath, String text) {
		waitForElementVisible(xpath);
		assertEquals(driver.findElement(By.xpath(xpath)).getText(), text);
	}

	public void startShinable() {
		setPropatyPath();
		siteOpen();
	}

	public void closeShinable() {
		driver.quit();
	}

	/**
	 * login SHINABLE
	 * @param mailAddress
	 * @param password
	 */
	public void login(String mailAddress, String password) {
		final String MAILADDRESS_INPUT_XPATH = "//*[@id=\"mailAddress\"]";
		final String PASSWORD_INPUT_XPATH = "//*[@id=\"password\"]";
		final String LOGIN_BUTTON_XPATH = "/html/body/div/form/div[3]/button";
		// input mailaddress
		input(MAILADDRESS_INPUT_XPATH, mailAddress);
		// input password
		input(PASSWORD_INPUT_XPATH, password);
		// click login button
		click(LOGIN_BUTTON_XPATH);
	}

	/**
	 * input employee info
	 * @param employeeNum
	 * @param mailaddress
	 */
	public void inputEmployeeInfo(String employeeNum, String mailaddress) {
		// input employee num
		input("//*[@id=\"number\"]", employeeNum);
		// input name
		input("//*[@id=\"fullName\"]", EMPLOYEE_NAME);
		// input mailaddress
		input("//*[@id=\"mailAddress\"]", mailaddress);

		// select rank
		click("/html/body/div[2]/div/form/div[8]/div/div/input");
		click("//*[@class=\"dropdown-content select-dropdown\"]/li[2]");

		// select group
		click("//*[@id=\"open\"]");
		//dialog
		click("//*[@id=\"node0\"]");
		click("//*[@id=\"select\"]");

		//入力出来ていない時があったのでリトライを入れる
		if (driver.findElement(By.xpath("//*[@id=\"organizationName\"]")).getText().equals(null)) {
			// select group
			click("//*[@id=\"open\"]");
			//dialog
			click("//*[@id=\"node0\"]");
			click("//*[@id=\"select\"]");
		}

		//input password
		input("//*[@id=\"password\"]", "P@ssw0rd");
	}
	
	/**
	 * assert text of page title
	 * @param page title
	 */
	public void assertPageTitle(String title) {
		assertText("/html/body/div[2]/h1", title);
		ExpectedConditions.titleIs(title);
	}

	/**
	 * assert added employee info
	 * @param employeeNum
	 * @param mailaddress
	 */
	public void assertEmployeeInfo(String employeeNum, String mailaddress) {
		assertText("/html/body/div[2]/div[3]/div[2]/table/tbody/tr[1]/td", employeeNum);
		assertText("/html/body/div[2]/div[3]/div[2]/table/tbody/tr[2]/td", EMPLOYEE_NAME);
		assertText("/html/body/div[2]/div[3]/div[2]/table/tbody/tr[3]/td", mailaddress);
		assertText("/html/body/div[2]/div[3]/div[2]/table/tbody/tr[8]/td", "AS0");
		assertText("/html/body/div[2]/div[3]/div[2]/table/tbody/tr[9]/td", "ICT");
	}

	/**
	 * assert message of message bar
	 * @param message
	 */
	public void assertMessageBar(String message) {
		waitForElementVisible("//*[@id=\"messageBar\"]");
		assertText("//*[@id=\"messageBar\"]/span", message);
	}
}
