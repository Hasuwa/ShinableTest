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
//		assertPageTitle("従業員情報検索");
		// 新規登録画面を開く
		click("/html/body/div[2]/div/div[1]/a");
//		assertPageTitle("従業員情報登録");
		// 社員情報入力
		final String employeeNum = "0001";
		final String mailaddress = "Purota.NIHON@jpd.co.jp";
		inputEmployeeInfo(employeeNum, mailaddress);
		// 登録
		click("//*[@id=\"upload\"]");
		// メッセージバー確認
		assertMessageBar("従業員情報の登録が完了しました。");
//		assertPageTitle("従業員情報詳細");

		//3.検索にて登録内容確認
		click(SEARCH_BUTTON_XPATH);
//		assertPageTitle("従業員情報検索");
		input("//*[@id=\"nameForEmployeeSearch\"]", EMPLOYEE_NAME);
		click("//*[@id=\"employeeSearchButton\"]");
		//一覧に追加されていることを確認
		waitForElementVisible("//*[@id=\"employeeData\"]");
		assertText("//*[@id=\"employeeData\"]/tr/td[1]", EMPLOYEE_NAME);
		click("//*[@id=\"employeeData\"]/tr/td[5]/a");
//		assertPageTitle("従業員情報詳細");
		//登録内容確認
		assertEmployeeInfo(employeeNum, mailaddress);

		//4.登録した社員を削除
		click("/html/body/div[2]/div[1]/button");
		assertText("/html/body/div[2]/div[2]/div[1]/p[1]", "以下の従業員情報を削除してもよろしいでしょうか");
		assertText("/html/body/div[2]/div[2]/div[1]/p[2]", "・" + EMPLOYEE_NAME);
		click("//*[@id=\"modalExecute\"]");
		//メッセージバー確認
		assertMessageBar("従業員情報の削除が完了しました。");
//		assertPageTitle("従業員情報検索");

		//5.ログアウト
		click("/html/body/header/nav/div/ul/li[4]/a");
		click("//*[@id=\"glovalNaviLoginDropMenu2\"]/li[2]/form");
	}

	/**
	 * 1.ログイン
	 * 2.必須項目+社員IDで社員登録
	 * 3.検索にて登録内容確認
	 * 4.登録した社員を削除
	 * 5.ログアウト
	 */
	@Test(groups = "scenario")
	public void scenarioTest002() {
		//1.ログイン
		login("admin@admin.com", "admin");

		//2.必須項目情報のみで社員登録
		// 検索画面を開く
		click(SEARCH_BUTTON_XPATH);
		// 新規登録画面を開く
		click("/html/body/div[2]/div/div[1]/a");
		// 社員情報入力
		final String employeeNum = "0002";
		final String mailaddress = "Purota.NIHON2@jpd.co.jp";
		inputEmployeeInfo(employeeNum, mailaddress);
		// 社員ID
		input("//*[@id=\"employeeId\"]", "pNihon2");
		// 登録
		click("//*[@id=\"upload\"]");
		// メッセージバー確認
		assertMessageBar("従業員情報の登録が完了しました。");

		//3.検索にて登録内容確認
		click(SEARCH_BUTTON_XPATH);
		input("//*[@id=\"nameForEmployeeSearch\"]", EMPLOYEE_NAME);
		click("//*[@id=\"employeeSearchButton\"]");
		//一覧に追加されていることを確認
		waitForElementVisible("//*[@id=\"employeeData\"]");
		assertText("//*[@id=\"employeeData\"]/tr/td[1]", EMPLOYEE_NAME);
		click("//*[@id=\"employeeData\"]/tr/td[5]/a");
		//登録内容確認
		assertEmployeeInfo(employeeNum, mailaddress);
		assertText("/html/body/div[2]/div[3]/div[2]/table/tbody/tr[6]/td", "pNihon2");

		//4.登録した社員を削除
		click("/html/body/div[2]/div[1]/button");
		assertText("/html/body/div[2]/div[2]/div[1]/p[1]", "以下の従業員情報を削除してもよろしいでしょうか");
		assertText("/html/body/div[2]/div[2]/div[1]/p[2]", "・" + EMPLOYEE_NAME);
		click("//*[@id=\"modalExecute\"]");
		//メッセージバー確認
		assertMessageBar("従業員情報の削除が完了しました。");

		//5.ログアウト
		click("/html/body/header/nav/div/ul/li[4]/a");
		click("//*[@id=\"glovalNaviLoginDropMenu2\"]/li[2]/form");
	}

	/**
	 * 1.ログイン
	 * 2.必須項目+入社日で社員登録
	 * 3.検索にて登録内容確認
	 * 4.登録した社員を削除
	 * 5.ログアウト
	 */
	@Test(groups = "scenario")
	public void scenarioTest003() {
		//1.ログイン
		login("admin@admin.com", "admin");

		//2.必須項目情報のみで社員登録
		// 検索画面を開く
		click(SEARCH_BUTTON_XPATH);
		// 新規登録画面を開く
		click("/html/body/div[2]/div/div[1]/a");
		// 社員情報入力
		final String employeeNum = "0003";
		final String mailaddress = "Purota.NIHON3@jpd.co.jp";
		inputEmployeeInfo(employeeNum, mailaddress);
		// 入社日
		input("//*[@id=\"hireDate\"]", "002024/04/01");
		// 登録
		click("//*[@id=\"upload\"]");
		// メッセージバー確認
		assertMessageBar("従業員情報の登録が完了しました。");

		//3.検索にて登録内容確認
		click(SEARCH_BUTTON_XPATH);
		input("//*[@id=\"nameForEmployeeSearch\"]", EMPLOYEE_NAME);
		click("//*[@id=\"employeeSearchButton\"]");
		//一覧に追加されていることを確認
		waitForElementVisible("//*[@id=\"employeeData\"]");
		assertText("//*[@id=\"employeeData\"]/tr/td[1]", EMPLOYEE_NAME);
		click("//*[@id=\"employeeData\"]/tr/td[5]/a");
		//登録内容確認
		assertEmployeeInfo(employeeNum, mailaddress);
		assertText("/html/body/div[2]/div[3]/div[2]/table/tbody/tr[5]/td", "2024-04-01");

		//4.登録した社員を削除
		click("/html/body/div[2]/div[1]/button");
		assertText("/html/body/div[2]/div[2]/div[1]/p[1]", "以下の従業員情報を削除してもよろしいでしょうか");
		assertText("/html/body/div[2]/div[2]/div[1]/p[2]", "・" + EMPLOYEE_NAME);
		click("//*[@id=\"modalExecute\"]");
		//メッセージバー確認
		assertMessageBar("従業員情報の削除が完了しました。");

		//5.ログアウト
		click("/html/body/header/nav/div/ul/li[4]/a");
		click("//*[@id=\"glovalNaviLoginDropMenu2\"]/li[2]/form");
	}

	/**
	 * 1.ログイン
	 * 2.必須項目+生年月日で社員登録
	 * 3.検索にて登録内容確認
	 * 4.登録した社員を削除
	 * 5.ログアウト
	 */
	@Test(groups = "scenario")
	public void scenarioTest004() {
		//1.ログイン
		login("admin@admin.com", "admin");

		//2.必須項目情報のみで社員登録
		// 検索画面を開く
		click(SEARCH_BUTTON_XPATH);
		// 新規登録画面を開く
		click("/html/body/div[2]/div/div[1]/a");
		// 社員情報入力
		final String employeeNum = "0004";
		final String mailaddress = "Purota.NIHON4@jpd.co.jp";
		inputEmployeeInfo(employeeNum, mailaddress);
		// 生年月日
		input("//*[@id=\"birthDate\"]", "002000/08/01");
		// 登録
		click("//*[@id=\"upload\"]");
		// メッセージバー確認
		assertMessageBar("従業員情報の登録が完了しました。");

		//3.検索にて登録内容確認
		click(SEARCH_BUTTON_XPATH);
		input("//*[@id=\"nameForEmployeeSearch\"]", EMPLOYEE_NAME);
		click("//*[@id=\"employeeSearchButton\"]");
		//一覧に追加されていることを確認
		waitForElementVisible("//*[@id=\"employeeData\"]");
		assertText("//*[@id=\"employeeData\"]/tr/td[1]", EMPLOYEE_NAME);
		click("//*[@id=\"employeeData\"]/tr/td[5]/a");
		//登録内容確認
		assertEmployeeInfo(employeeNum, mailaddress);
		assertText("/html/body/div[2]/div[3]/div[2]/table/tbody/tr[7]/td", "2000-08-01");

		//4.登録した社員を削除
		click("/html/body/div[2]/div[1]/button");
		assertText("/html/body/div[2]/div[2]/div[1]/p[1]", "以下の従業員情報を削除してもよろしいでしょうか");
		assertText("/html/body/div[2]/div[2]/div[1]/p[2]", "・" + EMPLOYEE_NAME);
		click("//*[@id=\"modalExecute\"]");
		//メッセージバー確認
		assertMessageBar("従業員情報の削除が完了しました。");

		//5.ログアウト
		click("/html/body/header/nav/div/ul/li[4]/a");
		click("//*[@id=\"glovalNaviLoginDropMenu2\"]/li[2]/form");
	}

	/**
	 * 1.ログイン
	 * 2.必須項目+性別で社員登録
	 * 3.検索にて登録内容確認
	 * 4.登録した社員を削除
	 * 5.ログアウト
	 */
	@Test(groups = "scenario")
	public void scenarioTest005() {
		//1.ログイン
		login("admin@admin.com", "admin");

		//2.必須項目情報のみで社員登録
		// 検索画面を開く
		click(SEARCH_BUTTON_XPATH);
		// 新規登録画面を開く
		click("/html/body/div[2]/div/div[1]/a");
		// 社員情報入力
		final String employeeNum = "0005";
		final String mailaddress = "Purota.NIHON5@jpd.co.jp";
		inputEmployeeInfo(employeeNum, mailaddress);
		// 性別
		click("/html/body/div[2]/div/form/div[4]/div/label[1]/span");
		// 登録
		click("//*[@id=\"upload\"]");
		// メッセージバー確認
		assertMessageBar("従業員情報の登録が完了しました。");

		//3.検索にて登録内容確認
		click(SEARCH_BUTTON_XPATH);
		input("//*[@id=\"nameForEmployeeSearch\"]", EMPLOYEE_NAME);
		click("//*[@id=\"employeeSearchButton\"]");
		//一覧に追加されていることを確認
		waitForElementVisible("//*[@id=\"employeeData\"]");
		assertText("//*[@id=\"employeeData\"]/tr/td[1]", EMPLOYEE_NAME);
		click("//*[@id=\"employeeData\"]/tr/td[5]/a");
		//登録内容確認
		assertEmployeeInfo(employeeNum, mailaddress);
		assertText("/html/body/div[2]/div[3]/div[2]/table/tbody/tr[4]/td", "男");

		//4.登録した社員を削除
		click("/html/body/div[2]/div[1]/button");
		assertText("/html/body/div[2]/div[2]/div[1]/p[1]", "以下の従業員情報を削除してもよろしいでしょうか");
		assertText("/html/body/div[2]/div[2]/div[1]/p[2]", "・" + EMPLOYEE_NAME);
		click("//*[@id=\"modalExecute\"]");
		//メッセージバー確認
		assertMessageBar("従業員情報の削除が完了しました。");

		//5.ログアウト
		click("/html/body/header/nav/div/ul/li[4]/a");
		click("//*[@id=\"glovalNaviLoginDropMenu2\"]/li[2]/form");
	}
}
