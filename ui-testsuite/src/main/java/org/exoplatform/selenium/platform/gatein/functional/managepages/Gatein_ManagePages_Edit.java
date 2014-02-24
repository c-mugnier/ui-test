/**
 * Generated by havtt at 2014/01/07 09:11:34
 *
 * Copyright (C) 2009 eXo Platform SAS.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.exoplatform.selenium.platform.gatein.functional.managepages;


import static org.exoplatform.selenium.TestLogger.info;
import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.DashBoard;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.exoplatform.selenium.platform.PageEditor;
import org.exoplatform.selenium.platform.PageManagement;
import org.testng.annotations.*;


/**
 * @author havtt
 *
 */
public class Gatein_ManagePages_Edit extends DashBoard{
	ManageAccount magAc;
	NavigationToolbar navTool;
	PageManagement pageMag;
	PageEditor pageE;
	Button button;
	ManageAlert magAlert;

	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		driver.get(baseUrl);
		magAc = new ManageAccount(driver);
		navTool = new NavigationToolbar(driver);
		pageMag = new PageManagement(driver);
		pageE = new PageEditor(driver);
		button = new Button(driver);
		magAlert = new ManageAlert(driver);

		magAc.signIn("john", "gtn");
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 *<li> Case ID:74035.</li>
	 *<li> Test Case Name: Check changes after edit page properties with Save & Finish.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *<li> Done with OSs and browsers : </li>
	 *<li> Generated by havtt at 2014/01/07 09:11:34</li>
	 */
	@Test
	public  void test01_CheckChangesAfterEditPagePropertiesWithSaveFinish() {
		info("Test 1: Check changes after edit page properties with Save & Finish");
		String pageName = "FuncManagePageName13";
		String pageTitle = "FuncManagePageTitle13";
		String pageTitleEdited = "PageTitleEdited";
		String groupPath = "Platform /Content Management ";
		String membership = "*";

		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 2: Show form to edit page properties
		 *Input Data: 
			Click Edit page properties icon from left pane
		 *Expected Outcome: 
			Edit page properties form appears*/

		/*Step number: 2
		 *Step Name: -
		 *Step Description: 
			Step 3: Change page properties
		 *Input Data: 
			- Make changes for page properties
			- Click Save
		 *Expected Outcome: 
			Edit page properties form is closed*/

		info("Go to Page Management");
		navTool.goToManagePages();

		info("Add page for portal");
		pageMag.addNewPageAtManagePages(PageType.PORTAL, pageName, pageTitle, true, null, groupPath, membership, "Page Configs", ELEMENT_PAGE_LAYOUT_OPTION_EMPTY, true);

		info("Edit page and click Save & Finish");
		pageMag.editPageAtManagePages(PageType.PORTAL, pageTitle);
		click(ELEMENT_VIEW_PAGE_PROPERTIES);
		//click(ELEMENT_INPUT_PAGE_TITLE);
		
		//Edit the page title
		//type(ELEMENT_INPUT_PAGE_TITLE, pageTitleEdited, true);
		pageMag.editPageTitleInViewPageProperties(pageTitleEdited);
		
		//Click button SAVE
		button.save();
		//Click button FINISH to turn off the Edit mode
		click(ELEMENT_PAGE_FINISH_BUTTON);

		/*Step number: 3
		 *Step Name: -
		 *Step Description: 
			Step 4: Escape edit page form without saving
		 *Input Data: 
			- Click Finish
		 *Expected Outcome: 
			Return to Pages list all changes for page properties are saved*/ 
		info("Check if page title is changed");
		pageMag.searchPageInManagementPage(PageType.PORTAL, pageTitleEdited, true);

		info("Restore data");
		pageMag.deletePage(PageType.PORTAL, pageTitleEdited);
	}

	/**
	 *<li> Case ID:74034.</li>
	 *<li> Test Case Name: Check changes after edit page properties with  Finish.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *<li> Done with OSs and browsers : </li>
	 *<li> Generated by havtt at 2014/01/07 09:11:34</li>
	 */
	@Test
	public  void test02_CheckChangesAfterEditPagePropertiesWithFinish() {
		info("Test 2: Check changes after edit page properties with  Finish");
		String pageName = "FuncManagePageName12";
		String pageTitle = "FuncManagePageTitle12";
		String pageTitleEdited = "PageTitleEdited";
		String groupPath = "Platform /Content Management ";
		String membership = "*";
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Show form to edit page
		 *Input Data: 
			In Pages management form: 
			- Click Edit icon of group page that you have edit right
		 *Expected Outcome: 
			Edit page form appears*/ 
		
		info("Go to Page Management");
		navTool.goToManagePages();

		info("Add page for portal");
		pageMag.addNewPageAtManagePages(PageType.PORTAL, pageName, pageTitle, true, null, groupPath, membership, "Page Configs", ELEMENT_PAGE_LAYOUT_OPTION_EMPTY, true);

		info("Edit page and click Finish without clicking on SAVE button");
		pageMag.editPageAtManagePages(PageType.PORTAL, pageTitle);
		click(ELEMENT_VIEW_PAGE_PROPERTIES);
		
		//Edit the page title
		//click(ELEMENT_INPUT_PAGE_TITLE);
		//type(ELEMENT_INPUT_PAGE_TITLE, pageTitleEdited, true);
		pageMag.editPageTitleInViewPageProperties(pageTitleEdited);
		
		//Click button CANCEL to show button FINISH
		button.cancel();
		//Click button FINISH to turn off the Edit mode
		click(ELEMENT_PAGE_FINISH_BUTTON);

		info("Check if page title is changed");
		pageMag.searchPageInManagementPage(PageType.PORTAL, pageTitleEdited, false);
		waitForAndGetElement(ELEMENT_NO_RESULT_FOUND_MSG);
		button.ok();

		info("Restore data");
		pageMag.deletePage(PageType.PORTAL, pageTitle);
	}

	/**
	 *<li> Case ID:73998.</li>
	 *<li> Test Case Name: Change edit right on portal page while editing portal page's properties.</li>
	 *<li> Pre-Condition: </li>
	 *<li> Post-Condition: </li>
	 *<li> Done with OSs and browsers : </li>
	 *<li> Generated by havtt at 2014/01/07 09:11:34</li>
	 */
	@Test
	public  void test03_ChangeEditRightOnPortalPageWhileEditingPortalPagesProperties() {
		info("Test 3: Change edit right on portal page while editing portal page's properties");
		String pageName = "FuncPageName11";
		String pageTitle = "FuncManagePageTitle11";
		String groupPath = "Platform /Content Management ";
		String membership = "*";
		String url = baseUrl + "/intranet/home/" + pageName;
		/*Step Number: 1
		 *Step Name: -
		 *Step Description: 
			Step 1: Create portal page
		 *Input Data: 
			- Login by admin or user has right to edit current portal & can access admin tool
			-bar
			- Create new page for current using portal
		 *Expected Outcome: 
			New page is created successfully.As default:
			- Access permission is set to users who can access current portal
			- Edit permission is set to users who can edit current portal*/

		/*Step number: 2
		 *Step Name: -
		 *Step Description: 
			Step 2: Show form to edit page
		 *Input Data: 
			- Login by user who has right to edit added page In Pages Management form:
			- Click Edit icon corresponding to new created page at step 1
		 *Expected Outcome: 
			Edit page form appears*/

		/*Step number: 3
		 *Step Name: -
		 *Step Description: 
			Step 3: Show form to edit page properties
		 *Input Data: 
			- Click [View page properties] button on page editor composer
		 *Expected Outcome: 
			Edit page properties form appears*/

		/*Step number: 4
		 *Step Name: -
		 *Step Description: 
			Step 4: Show form to change edit right for page
		 *Input Data: 
			- Select Permission setting tab
			- Choose Edit permission tab
			- Click Select Permission
		 *Expected Outcome: 
			A form to shown to choose new group/membership for edit right on page*/

		/*Step number: 5
		 *Step Name: -
		 *Step Description: 
			Step 5: Change edit right
		 *Input Data: 
			Choose group then choose membership
		 *Expected Outcome: 
			Current group/membership for edit right are changed by new selected group/membership*/

		/*Step number: 6
		 *Step Name: -
		 *Step Description: 
			Step 6: Complete editing group page
		 *Input Data: 
			Click Save in edit properties form then click Finish
		 *Expected Outcome: 
			Change is saved. After being changed, only users with new selected membership in new selected group can edit this page*/ 
		info("Go to Page Management");
		navTool.goToManagePages();

		info("Add page for portal");
		pageMag.addNewPageAtManagePages(PageType.PORTAL, pageName, pageTitle, true, null, groupPath, membership, "Page Configs", ELEMENT_PAGE_LAYOUT_OPTION_EMPTY, true);

		info("Edit page's EDIT permission");
		pageMag.editPageAtManagePages(PageType.PORTAL, pageTitle);
		click(ELEMENT_VIEW_PAGE_PROPERTIES);
		Utils.pause(3000);
		click(ELEMENT_PERMISSION_SETTING_TAB);
		Utils.pause(2000);
		info("---Change Edit permission of page---");
		waitForAndGetElement(ELEMENT_EDIT_PERMISSION_SETTING);
		click(ELEMENT_EDIT_PERMISSION_SETTING);
		setEditPermissions("Development", membership);
		button.save();
		click(ELEMENT_PAGE_FINISH_BUTTON);

		info("Check if user in the not-granted-permission group can edit page or not");
		magAc.signOut();
		magAc.signIn("mary", "gtn");
		driver.get(url);
		waitForElementNotPresent(ELEMENT_LINK_EDITOR);

		info("Restore data");
		magAc.signOut();
		magAc.signIn("john", "gtn");
		navTool.goToManagePages();
		pageMag.deletePage(PageType.PORTAL, pageTitle);
	}
}