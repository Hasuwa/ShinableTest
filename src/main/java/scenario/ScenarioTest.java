package scenario;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.CommonUtil;

public class ScenarioTest extends TestBase {

	// 検索ボタンのxpath
	static final String SEARCH_BUTTON_XPATH = "/html/body/header/nav/div/ul/li[2]/a";

	/**
	 * Chromeでアプリを開く
	 */
	@BeforeMethod(alwaysRun = true)
	public void before() {
		driver = new ChromeDriver();
		driver.get(SHINABLE_CLOUD_PATH);
		driver.manage().window().maximize();
	}

	@AfterMethod(alwaysRun = true)
	public void after(ITestResult result) {
		//failしたらスクショ
		if (result.getStatus() != ITestResult.SUCCESS) {
			CommonUtil.takeScreenShot(driver, ".\\result", result.getMethod().getMethodName());
		}
		//ブラウザを閉じる
		driver.quit();
	}
	
	/**
	 * 1.ログイン
	 * 2.必須項目情報のみで社員登録
	 * 3.登録した社員を削除
	 * 4.ログアウト
	 */
	@Test(groups = "scenario")
	public void scenarioTest001() {
		//1.ログイン
		login("admin@admin.com", "admin");
		// 遷移確認
		assertPageTitle("ホーム");
		
		//2.必須項目情報のみで社員登録
		// 検索画面を開く
		click(SEARCH_BUTTON_XPATH);
		// 遷移確認
		assertPageTitle("従業員情報検索");
		// 新規登録画面を開く
		click("/html/body/div[2]/div/div[1]/a");
		assertPageTitle("従業員情報登録");
		//社員情報入力
		final String employeeNum = "0001";
		final String mailaddress = "Purota.NIHON@jpd.co.jp";
		inputEmployeeInfo(employeeNum, mailaddress);
		// 登録
		click("//*[@id=\"upload\"]");
		//メッセージバー確認
		assertMessageBar("従業員情報の登録が完了しました。");
		assertPageTitleDisplayedMessageBar("従業員情報詳細");

		//4.登録した社員を削除
		click("/html/body/div[2]/div[1]/button");
		assertText("/html/body/div[2]/div[2]/div[1]/p[1]", "以下の従業員情報を削除してもよろしいでしょうか");
		assertText("/html/body/div[2]/div[2]/div[1]/p[2]", "・" + EMPLOYEE_NAME);
		click("//*[@id=\"modalExecute\"]");
		//メッセージバー確認
		assertMessageBar("従業員情報の削除が完了しました。");
		assertPageTitle("従業員情報検索");

		//5.ログアウト
		click("/html/body/header/nav/div/ul/li[4]/a");
		click("//*[@id=\"glovalNaviLoginDropMenu2\"]/li[2]/form");
	}
}
