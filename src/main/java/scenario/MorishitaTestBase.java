package scenario;

import org.openqa.selenium.support.ui.ExpectedConditions;

public class MorishitaTestBase extends TestBase {

	/**
	 * input all employee info
	 * @param employeeId
	 * @param hireDate
	 * @param birthDate
	 */
	public void inputOtherEmployeeInfo(String employeeId, String hireDate, String birthDate) {
		// select gender
		click("/html/body/div[2]/div/form/div[4]/div/label[1]/span");
		// input employeeId
		input("//*[@id=\"employeeId\"]", employeeId);
		// input hireDate
		input("//*[@id=\"hireDate\"]", hireDate);
		// input birthDate
		input("//*[@id=\"birthDate\"]", birthDate);		
		// select adminFlg
		click("/html/body/div[2]/div/form/div[11]/label[2]/span");
	}
	
	/**
	 * assert text of page title
	 * @param xpath
	 * @param title
	 */
	public void assertPageTitle(String xpath, String title) {
		waitForElementTextChange(xpath, title);
		ExpectedConditions.titleIs(title);
	}

}
