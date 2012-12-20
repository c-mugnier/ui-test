package org.exoplatform.selenium.platform.gatein.functional.group.organization.groupmanagement;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.gatein.ManageAccount.*;
import static org.exoplatform.selenium.gatein.NavigationToolbar.*;

import org.exoplatform.selenium.gatein.GateInBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/**
 *@author NhungVT
 *@date: 18/09/2012
 */

public class GateIn_Group_Organization_GroupManagement_AddUser extends GateInBase
{
	//Define data
	public String GROUP_MANAGEMENT = "//div[text()='Group Management']";
	public String DEVELOPMENT_LINK = "//a[@title='Development']";
	public String USERNAME_INPUT = "//input[@id='username']";
	public String USERNAME_DATA = "Barbie";
	public String SELECT_USER_ICON = "//a[@title='Select User']";
	public String SEARCH_INPUT = "//input[@id='QuickSearch']";
	public String SEARCH_ICON = "//a[@title='Quick Search']";
	public String DEMO_SEARCH_RESULT = "//div[@title='demo']";
	public String JOHN_SEARCH_RESULT = "//div[@title='john']";
	public String JAMES_SEARCH_RESULT = "//div[@title='james']";
	public String EMPTY_DATA_RESULTS = "Empty Data";
	public String ELEMENT_ADD_BUTTON = "//a[text()='Add']";
	
	//Product message
	public String USER_WITHOUT_SPECIFY_MESSAGE = "The field \"Username\" is required.";
	public String UNAVAILABLE_USER_MESSAGE = "User \""+USERNAME_DATA+"\" does not exist.";
	
	//Global variables
	public WebElement ELEMENT = null;
	
	@BeforeMethod()
	public void beforeTest()
	{
		initSeleniumTest();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseUrl);
		actions = new Actions(driver);
		signIn("john", "gtn");
	}
	
	//Add user into group without specify anyone
	@Test()
	public void test01_AddUserIntoGroupWithoutSpecify()
	{
		info("-START test01_AddUserIntoGroupWithoutSpecify");
		
		//Goto Organization Management
		goToUsersAndGroupsManagement();
		
		//Select Group Management
		click(GROUP_MANAGEMENT);
		
		//Click on Management Group
		click(DEVELOPMENT_LINK);
		
		//Not input value for Username then click Save
		save();
		
		//Confirm alert message
		waitForTextPresent(USER_WITHOUT_SPECIFY_MESSAGE);
		click(ELEMENT_OK_BUTTON);
		
		info("-END test01_AddUserIntoGroupWithoutSpecify");
	}
	
	//Add un-available user into group
	@Test()
	public void test02_AddUnavailableUserIntogroup()
	{
		info("-START test02_AddUnavailableUserIntogroup");
		
		//Goto Organization Management
		goToUsersAndGroupsManagement();
		
		//Select Group Management
		click(GROUP_MANAGEMENT);
		
		//Click on Management Group
		click(DEVELOPMENT_LINK);
				
		//Input Username not exist into System
		type(USERNAME_INPUT, USERNAME_DATA, true);
		save();
		
		//Confirm alert message
		waitForTextPresent(UNAVAILABLE_USER_MESSAGE);
		click(ELEMENT_OK_BUTTON);
		
		info("-END test02_AddUnavailableUserIntogroup");
	}
	
	//Check search user function when add user into group
	@Test()
	public void test03_checkSearchUser()
	{
		info("-START test03_checkSearchUser");
		
		//Goto Organization Management
		goToUsersAndGroupsManagement();
		
		//Select Group Management
		click(GROUP_MANAGEMENT);
		
		//Click on Management Group
		click(DEVELOPMENT_LINK);
		
		//Select User icon
		waitForElementPresent(SELECT_USER_ICON);
		click(SELECT_USER_ICON);
		
		//Verify Add button & Search input
		waitForElementPresent(SEARCH_INPUT);
		waitForElementPresent(ELEMENT_ADD_BUTTON);
		
		//Search available user with completed user name
		type(SEARCH_INPUT, "demo", true);
		click(SEARCH_ICON);
		
		//Verify Searched user
		waitForElementPresent(DEMO_SEARCH_RESULT);
		
		//Reset data input
		ELEMENT = waitForAndGetElement(By.xpath(SEARCH_INPUT));
		ELEMENT.clear();
		
		//Search un-available user
		type(SEARCH_INPUT, USERNAME_DATA, true);
		click(SEARCH_ICON);
		
		//Verify no search results
		waitForTextPresent(EMPTY_DATA_RESULTS);
		
		//Reset data input
		ELEMENT = waitForAndGetElement(By.xpath(SEARCH_INPUT));
		ELEMENT.clear();
		
		//Search with incomplete word but has * to find the right
		type(SEARCH_INPUT, "j*", true);
		click(SEARCH_ICON);
		
		//Verify Searched users
		waitForElementPresent(JAMES_SEARCH_RESULT);
		waitForElementPresent(JOHN_SEARCH_RESULT);
		
		//Reset data input
		ELEMENT = waitForAndGetElement(By.xpath(SEARCH_INPUT));
		ELEMENT.clear();
		
		//Search with not completed word
		type(SEARCH_INPUT, "e", true);
		click(SEARCH_ICON);
		
		//Verify Searched users
		waitForElementPresent(JAMES_SEARCH_RESULT);
		waitForElementPresent(DEMO_SEARCH_RESULT);
		
		//Reset data input
		ELEMENT = waitForAndGetElement(By.xpath(SEARCH_INPUT));
		ELEMENT.clear();
		
		//Search with key word includes special characters
		type(SEARCH_INPUT, "demo@", true);
		click(SEARCH_ICON);
		
		//Verify no search results
		waitForTextPresent(EMPTY_DATA_RESULTS);
		
		//Close Select User form
		close();
		
		info("-END test03_checkSearchUser");
	}
	
	@AfterMethod()
	public void afterTest()
	{
		signOut();
		driver.quit();
	}
}