package sample;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.CommonUtil;

public class SampleTest extends TestBase {

	@BeforeMethod(alwaysRun = true)
	public void before() {
		startShinable();
	}

	@AfterMethod(alwaysRun = true)
	public void after(ITestResult result) {
		if (result.getStatus() != ITestResult.SUCCESS) {
			CommonUtil.takeScreenShot(driver, ".\\result", result.getMethod().getMethodName());
		}
		closeShinable();
	}

	@Test(groups = "scenario")
	public void scenarioTest() {
		String MAILADDRESS_INPUT_XPATH = "//*[@id=\"mailAddress\"]";
		String PASSWORD_INPUT_XPATH = "//*[@id=\"password\"]";
		input(MAILADDRESS_INPUT_XPATH, "admin@admin.com");
		input(PASSWORD_INPUT_XPATH, "admin");
	}
}
