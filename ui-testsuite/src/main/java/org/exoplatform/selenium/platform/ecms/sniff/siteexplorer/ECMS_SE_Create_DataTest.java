package org.exoplatform.selenium.platform.ecms.sniff.siteexplorer;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.UserGroupManagement;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.exoplatform.selenium.platform.ecms.admin.ECMainFunction;
import org.exoplatform.selenium.platform.ecms.admin.ManageCategory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ECMS_SE_Create_DataTest extends PlatformBase{
	ManageAccount magAccount;
	NavigationToolbar navBar;
	UserGroupManagement userGroup;
	EcmsBase ecms;
	ManageCategory magCa;
	ECMainFunction ecMain;

	@BeforeMethod
	public void beforeMethods() {
		initSeleniumTest();
		driver.get(baseUrl);
		driver.manage().window().maximize();
		magAccount = new ManageAccount(driver);
		navBar = new NavigationToolbar(driver);
		userGroup = new UserGroupManagement(driver);
		magCa = new ManageCategory(driver);
		ecms = new EcmsBase(driver);
		ecMain = new ECMainFunction(driver);
		button = new Button(driver);
		
		magAccount.signIn(USER_ROOT, PASS_ROOT);
	}

	@AfterMethod
	public void afterMethods() {
		info("== Creating datatest: successful ==");
		driver.quit();
	}

	@Test
	public void Create_Data_Test(){
		String file = "metro.pdf";
		String file1 = "offices.jpg";
		String file2 = "conditions.doc";
		info("Create Cateogry: Intranet/Defense/Healing");
		String categoryTreeName = "intranet";
		String categoryName = "Defense";

		navBar.goToContentAdministration();
		click(ELEMENT_ADVANCED_CONFIGURATION_TAB);
		click(ecMain.ELEMENT_MANAGE_CATEGORIES_LINK);
		magCa.addChildCategory(categoryTreeName, categoryName);
		magCa.addChildCategory(categoryName, "Healing",true);
		button.close();

		navBar.goToContentAdministration();
		//click(ELEMENT_ADVANCED_CONFIGURATION_TAB);
		Utils.pause(10000);
		click(ecMain.ELEMENT_MANAGE_CATEGORIES_LINK);
		magCa.addChildCategory(categoryTreeName, "Movement");
		button.close();

		navBar.goToContentAdministration();
		//click(ELEMENT_ADVANCED_CONFIGURATION_TAB);
		Utils.pause(10000);
		click(ecMain.ELEMENT_MANAGE_CATEGORIES_LINK);
		magCa.addChildCategory(categoryTreeName, "Natural Elements");

		info("Upload file into intranet/document");
		navBar.goToSiteExplorer();
		ecms.goToNode("intranet");
		ecms.goToNode("documents");
		ecms.uploadFile("TestData/" + file, true);
		ecms.uploadFile("TestData/" + file1, true);
		ecms.uploadFile("TestData/" + file2, true);
	}
}