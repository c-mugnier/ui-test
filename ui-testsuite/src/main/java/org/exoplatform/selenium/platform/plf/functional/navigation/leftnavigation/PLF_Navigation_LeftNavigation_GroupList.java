package org.exoplatform.selenium.platform.plf.functional.navigation.leftnavigation;

import static org.exoplatform.selenium.TestLogger.info;

import java.util.HashMap;
import java.util.Map;

import org.exoplatform.selenium.platform.GroupNavigation;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationManagement;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PageManagement;
import org.testng.annotations.*;

/**
 * @author chinhdtt
 *
 */
public class PLF_Navigation_LeftNavigation_GroupList extends GroupNavigation{		
	ManageAccount acc; 
	NavigationToolbar nav; 
	NavigationManagement mNav; 
	PageManagement pageMag;

	@BeforeMethod
	public void beforeMethods(){	
		initSeleniumTest();
		driver.get(baseUrl);
		acc = new ManageAccount(driver);
		nav = new NavigationToolbar(driver);
		mNav = new NavigationManagement(driver, this.plfVersion);
		pageMag = new PageManagement(driver);
		acc.signIn(DATA_USER1, DATA_PASS);		
	}

	@AfterMethod
	public void afterMethods() {
		info("Logout portal");
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Case ID:76810.
	 * Test Case Name: Display a hover state for item in the Group Navigation panel.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/03/18 15:40:41
	 * --Pending this case because can't check hover state displays. 
	 */
	@Test (groups="pending")
	public  void test01_DisplayAHoverStateForItemInTheGroupNavigationPanel() {
		info("Test 1: Display a hover state for item in the Group Navigation panel");
		/*Add group page:
		- Connect to intranet as admin
		- Go to Adminstration/Portal/ Group Sites
		- Click Edit navigation of a group, eg Administration
		- Choose a node, eg Site Management
		- Click Add Node
		- Fill name
		- Choose tab Page Selector
		- Fill name
		- Click Search and Selected Page
		- Select a page
		- Click Save
		- Save
		 *Input Data: 
		 *Expected Outcome: Node is added		*/


		/*
		- Go back to intranet home page
		 *Input Data: 
		 *Expected Outcome: With user in group administration, In the Left Navigation, the Group Navigation is displayed above "MY SPACES" panel
		- Navigation nodes are displayed as Administration Group, then node		*/


		/*
		- Move the mouse over an item of the navigation
		 *Input Data: 
		 *Expected Outcome: 
		- A hover state is displayed for the item		*/ 

	}

	/**
	 * Case ID:76814.
	 * Test Case Name: Display a long name's navigation in Group Navigation panel.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/03/18 15:40:41
	 */
	@Test
	public  void test02_DisplayALongNamesNavigationInGroupNavigationPanel() {
		info("Test 2: Display a long name's navigation in Group Navigation panel");
		String groupAdminDisplayName = "Administration";
		String nodePortalAdministration = "";
		String nodeName = "Node123456789012345678901";
		String pageSelectorName = "test76814pageSelector";
		Map<String, String> languages = new HashMap<String, String>();
		languages.put("English", "");
		String truncateNode = "Node12345678901...";
		
		/*Add group page:
		- Connect to intranet as admin
		- Go to Adminstration/Portal/ Group Sites
		- Click Edit navigation of a group, eg Administration
		- Choose a node, eg Site Management
		- Click Add Node
		- Fill a long name eg 25 characters
		- Choose tab Page Selector
		- Fill a long name eg 25 characters
		- Click Search and Selected Page
		- Select a page
		- Click Save
		- Save
		 *Input Data: 
		 *Expected Outcome: Node is added		*/
		nav.goToGroupSites();
		addNodeForGroup(groupAdminDisplayName, nodePortalAdministration, true, 
				nodeName, true, languages, nodeName, 
				pageSelectorName, pageSelectorName, true, false);
		button.save();	
		waitForElementNotPresent(button.ELEMENT_SAVE_BUTTON);
		waitForAndGetElement(ELEMENT_NAVIGATION_NODE.replace("${nodeName}", nodeName));
		/*
		- Go back to intranet home page
		 *Input Data: 
		 *Expected Outcome: With user in group administration, In the Left Navigation, the Group Navigation is displayed above "MY SPACES" panel
		- Navigation nodes are displayed as Administration Group, then node and subnode
		- A long name's navigation is truncated and append "..."		*/ 
		nav.goToHomePage();
		waitForAndGetElement(ELEMENT_GROUP_NAVIGATION_ICON_LEFT_PANEL_PLF41.replace("${groupName}","Administration"));
		waitForAndGetElement(ELEMENT_GROUP_NAVIGATION_ICON_LEFT_PANEL_PLF41.replace("${groupName}",truncateNode));

		//Delete data test
		info("-- Clear data --");
		nav.goToManagePages();
		pageMag.deletePage(PageType.GROUP, pageSelectorName);
		nav.goToGroupSites();
		mNav.deleteNode(groupAdminDisplayName, "", nodeName, true);
	}

	/**
	 * Case ID:76808.
	 * Test Case Name: Display Group navigation before Spaces list.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/03/18 15:40:41
	 */
	@Test
	public  void test03_DisplayGroupNavigationBeforeSpacesList() {
		info("Test 3: Display Group navigation before Spaces list");
		String groupAdminDisplayName = "Administration";
		String nodePortalAdministration = "";
		String nodeName = "Node76808";
		String pageSelectorName = "test76808pageSelector";
		Map<String, String> languages = new HashMap<String, String>();
		languages.put("English", "");

		/*Add group page:
		- Connect to intranet as admin
		- Go to Adminstration/Portal/ Group Sites
		- Click Edit navigation of a group, eg Administration
		- Choose a node, eg Site Management
		- Click Add Node
		- Fill nameeg sub-navigation1
		- Choose tab Page Selector
		- Fill name, eg sub-navigation1
		- Click Search and Selected Page
		- Select a page
		- Click Save
		- Save
		 *Input Data: 
		 *Expected Outcome: Node is added		*/
		nav.goToGroupSites();
		addNodeForGroup(groupAdminDisplayName, nodePortalAdministration, true, 
				nodeName, true, languages, nodeName, 
				pageSelectorName, pageSelectorName, true, false);
		button.save();	
		waitForElementNotPresent(button.ELEMENT_SAVE_BUTTON);
		waitForAndGetElement(ELEMENT_NAVIGATION_NODE.replace("${nodeName}", nodeName));
		
		/*
		- Go back to intranet home page
		 *Input Data: 
		 *Expected Outcome: With user in group administration, In the Left Navigation, the Group Navigation is displayed above "MY SPACES" 
		- In the Header, the name ofgroup is displayed. i.e Administration Group		*/ 
		nav.goToHomePage();
		waitForAndGetElement(ELEMENT_GROUP_NAVIGATION_ICON_LEFT_PANEL_PLF41.replace("${groupName}","Administration"));
		waitForAndGetElement("//*[@id='LeftNavigation']//*[contains(@class,'UIRowContainer')]/div[3][@id='GroupsNavigationPortlet']");
		waitForAndGetElement("//*[@id='LeftNavigation']//*[contains(@class,'UIRowContainer')]/div[4][@id='SpaceNavigationPortlet']");

		//Delete data test
		info("-- Clear data --");
		nav.goToManagePages();
		pageMag.deletePage(PageType.GROUP, pageSelectorName);
		nav.goToGroupSites();
		mNav.deleteNode(groupAdminDisplayName, "", nodeName, true);
	}

	/**
	 * Case ID:76809.
	 * Test Case Name: Display Subnavigations in Group Navigation panel.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/03/18 15:40:41
	 */
	@Test
	public  void test04_DisplaySubnavigationsInGroupNavigationPanel() {
		info("Test 4: Display Subnavigations in Group Navigation panel");
		String groupAdminDisplayName = "Administration";
		String nodePortalAdministration = "Sites Management";
		String nodeName = "Node76809";
		String pageSelectorName = "test76809pageSelector";
		Map<String, String> languages = new HashMap<String, String>();
		languages.put("English", "");

		/*Add group page:
		- Connect to intranet as admin
		- Go to Adminstration/Portal/ Group Sites
		- Click Edit navigation of a group, eg Administration
		- Choose a node, eg Site Management
		- Click Add Node
		- Fill name eg sub-navigation1
		- Choose tab Page Selector
		- Fill name, eg sub-navigation1
		- Click Search and Selected Page
		- Select a page
		- Click Save
		- Save
		 *Input Data: 
		 *Expected Outcome: Node is added		*/
		nav.goToGroupSites();
		addNodeForGroup(groupAdminDisplayName, nodePortalAdministration, true, 
				nodeName, true, languages, nodeName, 
				pageSelectorName, pageSelectorName, true, false);
		button.save();	
		waitForElementNotPresent(button.ELEMENT_SAVE_BUTTON);

		/*
		- Go back to intranet home page
		 *Input Data: 
		 *Expected Outcome: With user in group administration, In the Left Navigation, the Group Navigation is displayed above "MY SPACES" 
		- In the Group Navigation panel, panel a small button is displayed to fold/unfold sub node		*/ 
		nav.goToHomePage();
		waitForAndGetElement(ELEMENT_GROUP_NAVIGATION_ICON_LEFT_PANEL_PLF41.replace("${groupName}","Administration"));
		click(ELEMENT_GROUP_NAVIGATION_ICON_LEFT_PANEL.replace("${groupName}", nodePortalAdministration));
		waitForAndGetElement(ELEMENT_NAVIGATION_NODE.replace("${nodeName}", nodeName));

		//Delete data test
		info("-- Clear data --");
		nav.goToManagePages();
		pageMag.deletePage(PageType.GROUP, pageSelectorName);
		nav.goToGroupSites();
		mNav.deleteNode(groupAdminDisplayName, nodePortalAdministration, nodeName, false);
	}

	/**
	 * Case ID:76815.
	 * Test Case Name: Display two levels in navigation's tree in Group Navigation panel.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/03/18 15:40:41
	 */
	@Test
	public  void test05_DisplayTwoLevelsInNavigationsTreeInGroupNavigationPanel() {
		info("Test 5: Display two levels in navigation's tree in Group Navigation panel");
		String groupAdminDisplayName = "Administration";
		String nodePortalAdministration = "Sites Management";
		String nodeName = "Node76815";
		String pageSelectorName = "test76815pageSelector";
		Map<String, String> languages = new HashMap<String, String>();
		languages.put("English", "");
		String subNodePath = nodePortalAdministration+"/"+nodeName;
		String subNodeName = "subNodeTest76815";
		String pageSelectorName1 = "subtest76815pageSelector";

		/*Add group page:
		- Connect to intranet as admin
		- Go to Adminstration/Portal/ Group Sites
		- Click Edit navigation of a group, eg Administration
		- Choose a node, eg Site Management
		- Click Add Node
		- Fill name eg sub-navigation1
		- Choose tab Page Selector
		- Fill name, eg sub-navigation1
		- Click Search and Selected Page
		- Select a page
		- Click Save
		- Save
		 *Input Data: 
		 *Expected Outcome: Node is added		*/
		nav.goToGroupSites();
		addNodeForGroup(groupAdminDisplayName, nodePortalAdministration, true, 
				nodeName, true, languages, nodeName, 
				pageSelectorName, pageSelectorName, true, false);
		button.save();	
		waitForElementNotPresent(button.ELEMENT_SAVE_BUTTON);

		/*
		- Go back to intranet home page
		 *Input Data: 
		 *Expected Outcome: With user in group administration, In the Left Navigation, the Group Navigation is displayed above "MY SPACES" panel
		- 2 levels are displayed: Navigations and sub-navigation,i.e Site Management and sub-navigation1		*/
		nav.goToHomePage();
		waitForAndGetElement(ELEMENT_GROUP_NAVIGATION_ICON_LEFT_PANEL_PLF41.replace("${groupName}","Administration"));
		click(ELEMENT_GROUP_NAVIGATION_ICON_LEFT_PANEL.replace("${groupName}", nodePortalAdministration));
		waitForAndGetElement(ELEMENT_NAVIGATION_NODE.replace("${nodeName}", nodeName));

		/*
		- Add a node under the sub-navigation1 above, eg sub-navigation2
		 *Input Data: 
		 *Expected Outcome: 
		- The level 3, sub-navigation2 isn't displayed in the group navigation panel		*/ 
		info("Add node level3");
		nav.goToGroupSites();
		addNodeForGroup(groupAdminDisplayName, subNodePath, false, 
				subNodeName, true, languages, subNodeName, 
				pageSelectorName1, pageSelectorName1, true, false);
		button.save();	
		waitForElementNotPresent(button.ELEMENT_SAVE_BUTTON);
		
		nav.goToHomePage();
		waitForAndGetElement(ELEMENT_GROUP_NAVIGATION_ICON_LEFT_PANEL_PLF41.replace("${groupName}","Administration"));
		click(ELEMENT_GROUP_NAVIGATION_ICON_LEFT_PANEL.replace("${groupName}", nodePortalAdministration));
		waitForAndGetElement(ELEMENT_NAVIGATION_NODE.replace("${nodeName}", nodeName));
		waitForElementNotPresent(ELEMENT_GROUP_NAVIGATION_ICON_LEFT_PANEL.replace("${groupName}", nodeName));
		waitForElementNotPresent(ELEMENT_NAVIGATION_NODE.replace("${nodeName}", subNodeName));

		//Delete data test
		info("-- Clear data --");
		nav.goToManagePages();
		pageMag.deletePage(PageType.GROUP, pageSelectorName1);
		pageMag.deletePage(PageType.GROUP, pageSelectorName);
		nav.goToGroupSites();
		mNav.deleteNode(groupAdminDisplayName, nodePortalAdministration, nodeName, false);
	}

	/**
	 * Case ID:76813.
	 * Test Case Name: Fold a tree in Group Navigation panel.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/03/18 15:40:41
	 */
	@Test
	public  void test06_FoldATreeInGroupNavigationPanel() {
		info("Test 6: Fold a tree in Group Navigation panel");
		String groupAdminDisplayName = "Administration";
		String nodePortalAdministration = "Sites Management";
		String nodeName = "Node76813";
		String pageSelectorName = "test76813pageSelector";
		Map<String, String> languages = new HashMap<String, String>();
		languages.put("English", "");

		/*Add group page:
		- Connect to intranet as admin
		- Go to Adminstration/Portal/ Group Sites
		- Click Edit navigation of a group, eg Administration
		- Choose a node, eg Site Management
		- Click Add Node
		- Fill name
		- Choose tab Page Selector
		- Fill name
		- Click Search and Selected Page
		- Select a page
		- Click Save
		- Save
		 *Input Data: 
		 *Expected Outcome: Node is added		*/
		nav.goToGroupSites();
		addNodeForGroup(groupAdminDisplayName, nodePortalAdministration, true, 
				nodeName, true, languages, nodeName, 
				pageSelectorName, pageSelectorName, true, false);
		button.save();	
		waitForElementNotPresent(button.ELEMENT_SAVE_BUTTON);

		/*
		- Go back to intranet home page
		 *Input Data: 
		 *Expected Outcome: With user in group administration, In the Left Navigation, the Group Navigation is displayed above "MY SPACES" panel
		- Navigation and subnavigations are displayed
		- A small button is displayed to Fold/Unfold the tree		*/
		nav.goToHomePage();
		waitForAndGetElement(ELEMENT_GROUP_NAVIGATION_ICON_LEFT_PANEL_PLF41.replace("${groupName}","Administration"));
		waitForElementNotPresent(ELEMENT_NAVIGATION_NODE.replace("${nodeName}", nodeName));
		/*
		- Click on the button on the page noeud to unfold tree
		 *Input Data: 
		 *Expected Outcome: 
		- The tree is expanded and sub navigations are displayed		*/
		click(ELEMENT_GROUP_NAVIGATION_ICON_LEFT_PANEL_PLF41.replace("${groupName}", nodePortalAdministration));
		waitForAndGetElement(ELEMENT_NAVIGATION_NODE.replace("${nodeName}", nodeName));

		/*
		- Click again in the button
		 *Input Data: 
		 *Expected Outcome: 
		- The tree is collapsed and sub navigations are hidden		*/ 
		click(ELEMENT_GROUP_NAVIGATION_ICON_LEFT_PANEL_PLF41.replace("${groupName}", nodePortalAdministration));
		waitForElementNotPresent(ELEMENT_NAVIGATION_NODE.replace("${nodeName}", nodeName));

		//Delete data test
		info("-- Clear data --");
		nav.goToManagePages();
		pageMag.deletePage(PageType.GROUP, pageSelectorName);
		nav.goToGroupSites();
		mNav.deleteNode(groupAdminDisplayName, nodePortalAdministration, nodeName, false);
	}

	/**
	 * Case ID:76811.
	 * Test Case Name: Select a sub navigation in Group Navigation panel.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/03/18 15:40:41 
	 */
	@Test 
	public  void test07_SelectASubNavigationInGroupNavigationPanel() {
		info("Test 7: Select a sub navigation in Group Navigation panel");
		String groupAdminDisplayName = "Administration";
		String nodePortalAdministration = "Sites Management";
		String nodeName = "Node76811";
		String pageSelectorName = "test76811pageSelector";
		Map<String, String> languages = new HashMap<String, String>();
		languages.put("English", "");

		/*Add group page:
		- Connect to intranet as admin
		- Go to Adminstration/Portal/ Group Sites
		- Click Edit navigation of a group, eg Administration
		- Choose a node, eg Site Management
		- Click Add Node
		- Fill name
		- Choose tab Page Selector
		- Fill name
		- Click Search and Selected Page
		- Select a page
		- Click Save
		- Save
		 *Input Data: 
		 *Expected Outcome: Node is added		*/
		nav.goToGroupSites();
		addNodeForGroup(groupAdminDisplayName, nodePortalAdministration, true, 
				nodeName, true, languages, nodeName, 
				pageSelectorName, pageSelectorName, true, false);
		button.save();	
		waitForElementNotPresent(button.ELEMENT_SAVE_BUTTON);

		/*
		- Go back to intranet home page
		 *Input Data: 
		 *Expected Outcome: With user in group administration, In the Left Navigation, the Group Navigation is displayed above "MY SPACES" panel
		- Navigation nodes are displayed as Administration Group, then node		*/
		nav.goToHomePage();
		waitForAndGetElement(ELEMENT_GROUP_NAVIGATION_ICON_LEFT_PANEL_PLF41.replace("${groupName}","Administration"));
		waitForElementNotPresent(ELEMENT_NAVIGATION_NODE.replace("${nodeName}", nodeName));
		
		/*
		- Select a subnavigation from the list
		 *Input Data: 
		 *Expected Outcome: 
		- The state "Unfold' is kept on the navigation tree		*/
		click(ELEMENT_GROUP_NAVIGATION_ICON_LEFT_PANEL_PLF41.replace("${groupName}", nodePortalAdministration));
		waitForAndGetElement(ELEMENT_NAVIGATION_NODE.replace("${nodeName}", nodeName));

		//Delete data test
		info("-- Clear data --");
		nav.goToManagePages();
		pageMag.deletePage(PageType.GROUP, pageSelectorName);
		nav.goToGroupSites();
		mNav.deleteNode(groupAdminDisplayName, nodePortalAdministration, nodeName, false);
	}

	/**
	 * Case ID:76812.
	 * Test Case Name: Unfold a tree in Group Navigation panel.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/03/18 15:40:41
	 */
	@Test
	public  void test08_UnfoldATreeInGroupNavigationPanel() {
		info("Test 8: Unfold a tree in Group Navigation panel");
		String groupAdminDisplayName = "Administration";
		String nodePortalAdministration = "Sites Management";
		String nodeName = "Node76812";
		String pageSelectorName = "test76812pageSelector";
		Map<String, String> languages = new HashMap<String, String>();
		languages.put("English", "");

		/*Add group page:
		- Connect to intranet as admin
		- Go to Adminstration/Portal/ Group Sites
		- Click Edit navigation of a group, eg Administration
		- Choose a node, eg Site Management
		- Click Add Node
		- Fill name
		- Choose tab Page Selector
		- Fill name
		- Click Search and Selected Page
		- Select a page
		- Click Save
		- Save
		 *Input Data: 
		 *Expected Outcome: Node is added		*/
		nav.goToGroupSites();
		addNodeForGroup(groupAdminDisplayName, nodePortalAdministration, true, 
				nodeName, true, languages, nodeName, 
				pageSelectorName, pageSelectorName, true, false);
		button.save();	
		waitForElementNotPresent(button.ELEMENT_SAVE_BUTTON);

		/*
		- Go back to intranet home page
		 *Input Data: 
		 *Expected Outcome: With user in group administration, In the Left Navigation, the Group Navigation is displayed above "MY SPACES" panel
		- Navigation nodes are displayed as Administration Group, then node		*/
		nav.goToHomePage();
		waitForAndGetElement(ELEMENT_GROUP_NAVIGATION_ICON_LEFT_PANEL_PLF41.replace("${groupName}","Administration"));
		waitForElementNotPresent(ELEMENT_NAVIGATION_NODE.replace("${nodeName}", nodeName));
		
		/*
		- Click on the button on the page node to unfold tree
		 *Input Data: 
		 *Expected Outcome: 
		- The tree is expanded and sub navigations are displayed		*/ 
		click(ELEMENT_GROUP_NAVIGATION_ICON_LEFT_PANEL_PLF41.replace("${groupName}", nodePortalAdministration));
		waitForAndGetElement(ELEMENT_NAVIGATION_NODE.replace("${nodeName}", nodeName));

		//Delete data test
		info("-- Clear data --");
		nav.goToManagePages();
		pageMag.deletePage(PageType.GROUP, pageSelectorName);
		nav.goToGroupSites();
		mNav.deleteNode(groupAdminDisplayName, nodePortalAdministration, nodeName, false);
	}
}