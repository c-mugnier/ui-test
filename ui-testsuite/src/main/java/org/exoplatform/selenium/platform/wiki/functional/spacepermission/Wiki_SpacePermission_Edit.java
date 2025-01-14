package org.exoplatform.selenium.platform.wiki.functional.spacepermission;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.wiki.Permission;

/**
 *
 * @author HangNTT
 * @date: 18/12/2012
 */
public class Wiki_SpacePermission_Edit extends Permission {

	ManageAccount magAc;
	
	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		driver.get(baseUrl);
		magAc = new ManageAccount(driver);

		magAc.signIn(DATA_USER1, DATA_PASS);
	}
	
	//Qmetry ID: 69759
	//Edit permission for space
	@Test
	public void test01_EditPermissionForSpace() {

		String[] user2= {DATA_USER3};

		goToWiki();

		addSpacePermission(0, user2);

		editSpacePermission(DATA_USER3, true, true, true, false, 2);

		deleteSpacePermission(DATA_USER3);
	}
	
	//Qmetry ID: 69757
	//Edit permission for space is blank
	@Test
	public void test02_EditPermissionForSpaceIsBlank() {

		String[] user2= {DATA_USER3};

		goToWiki();

		addSpacePermission(0, user2);

		editSpacePermission(DATA_USER3, false, false, false, false, 2);
	}

	@AfterMethod
	public void afterTest(){
		//signOut();
		driver.manage().deleteAllCookies();
		driver.quit();
	}
}