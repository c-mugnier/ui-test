package org.exoplatform.selenium.platform.user;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PlatformBase;
import org.exoplatform.selenium.platform.UserGroupManagement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author vuna
 * <li>Test Campaign PLF 4.0.7 environment: User name of email address</li> 
 */
public class Platform_Username_EmailAddress extends PlatformBase{
	ManageAccount magAccount;
	NavigationToolbar navBar;
	UserGroupManagement userGroup;

	@BeforeMethod
	public void beforeMethods() {
		initSeleniumTest();
		driver.get(baseUrl);
		driver.manage().window().maximize();
		magAccount = new ManageAccount(driver);
		navBar = new NavigationToolbar(driver);
		userGroup = new UserGroupManagement(driver);
		info("== Creating users with username as an email address ==");
	}

	@AfterMethod
	public void afterMethods() {
		info("== Creating users: successful ==");
		driver.quit();
	}

	//Default users of platform
	String USER_DEFAULT_ROOT = "root";
	String PASS_DEFAULT_ROOT = "gtn";
	
	@Test
	public void test01_CreateUser(){
		info("== Signin to Platform with user... Root");
		magAccount.signIn(USER_DEFAULT_ROOT, PASS_DEFAULT_ROOT);
		
		//Delete old user
		navBar.goToUsersAndGroupsManagement();
		userGroup.deleteUser("john");
		navBar.goToUsersAndGroupsManagement();
		userGroup.deleteUser("mary");
		navBar.goToUsersAndGroupsManagement();
		userGroup.deleteUser("james");
		navBar.goToUsersAndGroupsManagement();
		userGroup.deleteUser("jack");
		
		navBar.goToNewStaff();
		//Publisher
		magAccount.addNewUserAccount(DATA_USER2, DATA_PASS, DATA_PASS, "Mary", "Williams", "", "mary@maryacme.com", "", "", true);
		//Root
		magAccount.addNewUserAccount(USER_ROOT, DATA_PASS, DATA_PASS, "Root", "Root", "", "root@rootacme.com", "", "", true);
		//Admin
		magAccount.addNewUserAccount(DATA_USER1, DATA_PASS, DATA_PASS, "John", "Smith", "", "john@johnacme.com", "", "", true);
		//Redactor
		magAccount.addNewUserAccount(DATA_USER3, DATA_PASS, DATA_PASS, "James", "Davis", "", "james@jamesacme.com", "", "", true);
		//Developer
		magAccount.addNewUserAccount(DATA_USER4, DATA_PASS, DATA_PASS, "Jack", "Miller", "", "jack@jackacme.com", "", "", true);

		//Group Management
		navBar.goToUsersAndGroupsManagement();
		userGroup.chooseGroupTab();
		//Admin
		userGroup.selectGroup("Platform/Administration", true);
		userGroup.addUsersToGroup(DATA_USER1, "*", false, false);
		userGroup.addUsersToGroup(DATA_USER1, "manager", false, false);
		userGroup.addUsersToGroup(USER_ROOT, "manager", false, false);
		userGroup.addUsersToGroup(USER_ROOT, "*", false, false);
		upLevel();

		//platform/users
		userGroup.selectGroup("Platform/Users", true);
		userGroup.addUsersToGroup(DATA_USER1, "*", false, false);
		upLevel();

		//Publisher
		userGroup.selectGroup("Platform/Content Management", true);
		userGroup.addUsersToGroup(USER_ROOT, "*", false, false);
		userGroup.addUsersToGroup(DATA_USER1, "*", false, true);
		userGroup.addUsersToGroup(DATA_USER2, "manager", false, true);
		userGroup.addUsersToGroup(DATA_USER2, "editor", false, true);
		userGroup.addUsersToGroup(DATA_USER3, "author", false, false);
		userGroup.addUsersToGroup(DATA_USER3, "redactor", false, false);

		upLevel();
		//Organization/Employees
		userGroup.selectGroup("Organization/Employees", true);
		userGroup.addUsersToGroup(USER_ROOT, "*", false, false);
		userGroup.addUsersToGroup(DATA_USER1, "*", false, false);
		userGroup.addUsersToGroup(DATA_USER2, "member", true, false);
		userGroup.addUsersToGroup(DATA_USER3, "member", true, false);
		userGroup.addUsersToGroup(DATA_USER4, "member", true, false);

		upLevel();
		//Organization/Management/Executive Board
		userGroup.selectGroup("Organization/Management/Executive Board", true);
		userGroup.addUsersToGroup(DATA_USER1, "*", true, true);
		userGroup.addUsersToGroup(USER_ROOT, "*", false, false);

		upLevel();
		waitForAndGetElement(userGroup.ELEMENT_GROUP_PERMISSION.replace("${groupName}", "Management"));
		upLevel();
		waitForAndGetElement(userGroup.ELEMENT_GROUP_PERMISSION.replace("${groupName}", "Development"));

		//Developer
		userGroup.selectGroup("Development", true);
		userGroup.addUsersToGroup(DATA_USER4, "member", true, true);
		userGroup.addUsersToGroup(DATA_USER1, "member", true, true);
	}
	
	/**
	 * Go to parent node
	 */
	public void upLevel(){
		if (isElementNotPresent(ELEMENT_UP_LEVEL)){
			click(ELEMENT_UP_LEVEL_AUX);
		}else{
			click(ELEMENT_UP_LEVEL);
		}
	}
}