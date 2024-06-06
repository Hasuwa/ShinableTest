package sample;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.CommonUtil;

public class ScenarioTest extends TestBase {

	static final String SEARCH_BUTTON_XPATH = "/html/body/header/nav/div/ul/li[2]/a";

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

	/**
	 * 1.ログイン
	 * 2.必須項目情報のみで社員登録
	 * 3.検索にて登録内容確認
	 * 4.登録した社員を削除
	 * 5.ログアウト
	 */
	@Test(groups = "scenario")
	public void scenarioTest001() {

		//1.ログイン
		login("admin@admin.com", "admin");

		//2.必須項目情報のみで社員登録
		// 検索画面を開く
		click(SEARCH_BUTTON_XPATH);
		// 新規登録画面を開く
		click("/html/body/div[2]/div/div[1]/a");
		// 社員情報入力
		final String employeeNum = "0001";
		final String mailaddress = "Purota.NIHON@jpd.co.jp";
		inputEmployeeInfo(employeeNum, mailaddress);

		// 登録
		click("//*[@id=\"upload\"]");

		// メッセージバー確認
		assertMessageBar("従業員情報の登録が完了しました。");

		//3.検索にて登録内容確認
		click(SEARCH_BUTTON_XPATH);

		input("//*[@id=\"nameForEmployeeSearch\"]", "日本　プロ太");
		click("//*[@id=\"employeeSearchButton\"]");
		//一覧に追加されていることを確認
		waitForElementVisible("//*[@id=\"employeeData\"]");
		assertText("//*[@id=\"employeeData\"]/tr/td[1]", "日本　プロ太");
		click("//*[@id=\"employeeData\"]/tr/td[5]/a");

		assertEmployeeInfo(employeeNum, mailaddress);

		//4.登録した社員を削除
		click("/html/body/div[2]/div[1]/button");
		assertText("/html/body/div[2]/div[2]/div[1]/p[1]", "以下の従業員情報を削除してもよろしいでしょうか");
		assertText("/html/body/div[2]/div[2]/div[1]/p[2]", "・日本　プロ太");
		click("//*[@id=\"modalExecute\"]");
		//メッセージバー確認
		assertMessageBar("従業員情報の削除が完了しました。");

		//5.ログアウト
		click("/html/body/header/nav/div/ul/li[4]/a");
		click("//*[@id=\"glovalNaviLoginDropMenu2\"]/li[2]/form");
	}
}
