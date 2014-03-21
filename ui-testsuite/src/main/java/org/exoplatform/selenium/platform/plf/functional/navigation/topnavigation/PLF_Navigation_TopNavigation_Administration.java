package org.exoplatform.selenium.platform.plf.functional.navigation.topnavigation;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.By;
import org.testng.annotations.*;

/**
 * @author chinhdtt
 * @date 19 Mar 2014
 */
public class PLF_Navigation_TopNavigation_Administration extends PlatformBase{
	ManageAccount acc;
	NavigationToolbar nav;

	@BeforeMethod
	public void beforeMethods(){	
		initSeleniumTest();
		driver.get(baseUrl);
		acc = new ManageAccount(driver, this.plfVersion);
		nav = new NavigationToolbar(driver, this.plfVersion);				
		acc.signIn(DATA_USER1, DATA_PASS);		
	}

	@AfterMethod
	public void afterMethods() {
		info("Logout portal");
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Case ID:76656.
	 * Test Case Name: Display items on "Administration" entry.
	 * Pre-Condition: The current user is an admin
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/03/19 14:51:02
	 */
	@Test
	public  void test01_DisplayItemsOnAdministrationEntry() {
		info("Test 1: Display items on Administration entry");
		/*
		- Connect to Intranet
		 *Input Data: 
		 *Expected Outcome: 
		- The top navigation bar is displayed
		- The button "Administration" is displayed		*/
		waitForAndGetElement(ELEMENT_NAVIGATION_TOOLBAR_HOMEPAGE);
		waitForAndGetElement(ELEMENT_CATEGORY_ADMINISTRATION);

		/*
		- Mouse over on the button "Administration"
		- Choose "Administration" entry
		 *Input Data: 
		 *Expected Outcome: 
		- The Administration submenu is displayed
		- The following entries are displayed in this order:* Management* Monitoring		*/ 
		mouseOverAndClick(ELEMENT_CATEGORY_ADMINISTRATION);
		waitForAndGetElement(ELEMENT_MENU_ADMININISTRATION);
		mouseOver(ELEMENT_MENU_ADMININISTRATION, true);
		waitForAndGetElement(ELEMENT_TOP_NAVIGATION_MANAGEMENT);
		waitForAndGetElement(ELEMENT_TOP_NAVIGATION_MONITORING);
	}

	/**
	 * Case ID:76655.
	 * Test Case Name: Display items on "Content" entry.
	 * Pre-Condition: The current user is in web
	-contributor group
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/03/19 14:51:02
	 */
	@Test
	public  void test02_DisplayItemsOnContentEntry() {
		info("Test 2: Display items on Content entry");
		/*
		- Connect to Intranet
		 *Input Data: 
		 *Expected Outcome: 
		- The top navigation bar is displayed
		- The button "Administration" is displayed		*/
		waitForAndGetElement(ELEMENT_NAVIGATION_TOOLBAR_HOMEPAGE);
		waitForAndGetElement(ELEMENT_CATEGORY_ADMINISTRATION);

		/*
		- Mouse over on the button "Administration"
		- Choose "Content" entry
		 *Input Data: 
		 *Expected Outcome: 
		- The Content submenu is displayed
		- The following entries are displayed in this order:* Site Explorer* Content Administration* Search 		*/ 
		mouseOverAndClick(ELEMENT_CATEGORY_ADMINISTRATION);
		waitForAndGetElement(ELEMENT_MENU_CONTENT);
		mouseOver(ELEMENT_MENU_CONTENT, true);
		waitForAndGetElement(ELEMENT_MENU_CONTENT_SITE_EXPLORER);
		waitForAndGetElement(ELEMENT_MENU_CONTENT_ADMIN);
		waitForAndGetElement(ELEMENT_MENU_CONTENT_SEARCH);
	}

	/**
	 * Case ID:76654.
	 * Test Case Name: Display items on "Portal" entry.
	 * Pre-Condition: The current user is an admin
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/03/19 14:51:02
	 */
	@Test
	public  void test03_DisplayItemsOnPortalEntry() {
		info("Test 3: Display items on Portal entry");
		/*
		- Connect to Intranet
		 *Input Data: 
		 *Expected Outcome: 
		- The top navigation bar is displayed
		- The button "Administration" is displayed		*/
		waitForAndGetElement(ELEMENT_NAVIGATION_TOOLBAR_HOMEPAGE);
		waitForAndGetElement(ELEMENT_CATEGORY_ADMINISTRATION);

		/*
		- Mouse over on the button "Administration"
		- Choose "Portal" entry
		 *Input Data: 
		 *Expected Outcome: 
		- The Portal submenu is displayed
		- The following entries are displayed in this order:* Pages* Sites* Group Sites* Branding 		*/ 
		mouseOverAndClick(ELEMENT_CATEGORY_ADMINISTRATION);
		waitForAndGetElement(ELEMENT_MENU_PORTAL);
		mouseOver(ELEMENT_MENU_PORTAL, true);
		Utils.pause(1000);
		waitForAndGetElement(By.xpath(ELEMENT_LINK_PAGES));
		waitForAndGetElement(By.xpath(ELEMENT_LINK_SITES));
		waitForAndGetElement(By.xpath(ELEMENT_LINK_GROUP));
		waitForAndGetElement(By.xpath(ELEMENT_LINK_BRANDING));
	}

	/**
	 * Case ID:76653.
	 * Test Case Name: Display items on "User" entry.
	 * Pre-Condition: The current user is an admin
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/03/19 14:51:02
	 */
	@Test
	public  void test04_DisplayItemsOnUserEntry() {
		info("Test 4: Display items on User entry");
		/*
		- Connect to Intranet
		 *Input Data: 
		 *Expected Outcome: 
		- The top navigation bar is displayed
		- The button "Administration" is displayed		*/
		waitForAndGetElement(ELEMENT_NAVIGATION_TOOLBAR_HOMEPAGE);
		waitForAndGetElement(ELEMENT_CATEGORY_ADMINISTRATION);

		/*
		- Mouse over on the button "Administration"
		- Choose "User" entry
		 *Input Data: 
		 *Expected Outcome: 
		- The User submenu is displayed
		- The following entries are displayed in this order:* Add Users* Groups and Roles		*/ 
		mouseOverAndClick(ELEMENT_CATEGORY_ADMINISTRATION);
		waitForAndGetElement(By.xpath(ELEMENT_LINK_USERS));
		mouseOver(By.xpath(ELEMENT_LINK_USERS), true);
		waitForAndGetElement(By.xpath(ELEMENT_LINK_ADD_USERS));
		waitForAndGetElement(By.xpath(ELEMENT_GROUP_AND_ROLE_LINK));
	}

	/**
	 * Case ID:76652.
	 * Test Case Name: Display items on Administration button.
	 * Pre-Condition: The current user is an admin
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/03/19 14:51:02
	 */
	@Test
	public  void test05_DisplayItemsOnAdministrationButton() {
		info("Test 5: Display items on Administration button");
		/*
		- Connect to Intranet
		 *Input Data: 
		 *Expected Outcome: 
		- The top navigation bar is displayed
		- The button "Administration" is displayed		*/
		waitForAndGetElement(ELEMENT_NAVIGATION_TOOLBAR_HOMEPAGE);
		waitForAndGetElement(ELEMENT_CATEGORY_ADMINISTRATION);

		/*
		- Mouse over on the button "Administration"
		 *Input Data: 
		 *Expected Outcome: 
		- The Administration menu is displayed
		- The following entries are displayed in this order:* Users* Applications* Content* Portal* Administration		*/ 
		mouseOverAndClick(ELEMENT_CATEGORY_ADMINISTRATION);
		waitForAndGetElement(By.xpath(ELEMENT_LINK_USERS));
		waitForAndGetElement(ELEMENT_APPLICATIONS_LINK);
		waitForAndGetElement(ELEMENT_MENU_CONTENT);
		waitForAndGetElement(ELEMENT_MENU_ADMININISTRATION);
		waitForAndGetElement(ELEMENT_MENU_PORTAL);
	}
}