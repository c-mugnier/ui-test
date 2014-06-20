package org.exoplatform.selenium.platform.plf.functional.homepagegadgets;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.HomePageActivity;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.exoplatform.selenium.platform.HomePageGadget;
import org.exoplatform.selenium.platform.social.Activity;
import org.exoplatform.selenium.platform.social.PeopleConnection;
import org.exoplatform.selenium.platform.social.PeopleProfile;
import org.exoplatform.selenium.platform.social.PeopleSearch;

/**
 * @author HaVTT
 * date: 10/02/2014
 *    
 */

public class PLF_HomepageGadgets_WhoIsOnline extends Activity {

	ManageAccount acc;
	HomePageGadget hg;
	NavigationToolbar navToolBar;
	PeopleConnection peopleC;
	PeopleSearch peopleS;
	PeopleProfile peopleP;
	HomePageActivity homeAct;
	String user = "John Smith"; 
	String user1 = "Mary Williams";
	
	@BeforeMethod
	public void setUpBeforeTest(){
//		getDriverAutoSave();
		initSeleniumTest();
		acc = new ManageAccount(driver);
		hg = new HomePageGadget(driver);
		peopleC = new PeopleConnection(driver);
		peopleS = new PeopleSearch(driver);
		navToolBar = new NavigationToolbar(driver);
		peopleP = new PeopleProfile(driver);
		homeAct = new HomePageActivity(driver);
		
		acc.signIn(DATA_USER1, DATA_PASS);
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * CaseID 79683
	 * Check the display of online user on Who's Online gadget
	 * 
	 * CaseID 79684
	 * Display pop up informations for user who requested a connection
	 * 
	 */
	@Test
	public void test01_checkDisplayOfOnlineUser(){	
		String position = "Tester";
		String activity = "Activity79684";
		
		navToolBar.goToMyProfile();
		peopleP.editCurrentPosition(position);
		peopleP.changeAvatar("/TestData/Winter.jpg");
		navToolBar.goToHomePage();
		addActivity(true, activity, false, null);
		
		info("USER2: Switch to other window to login");
		loginWithAnotherAccOnThesameBrowser(DATA_USER2, DATA_PASS);
		hg=new HomePageGadget(newDriver);
		newDriver.navigate().refresh();
		
		info("USER2: 79683 - Check pop-up information of online user");
		hg.checkUserInfoOnWhoisOnlineGadget(DATA_USER1, user, null, false, null, 0);
		
		info("USER2: Go to My Connection");
		navToolBar = new NavigationToolbar(newDriver);
		navToolBar.goToConnectionPage();
		
		info("USER2: Connect with user acc 2");
		peopleC = new PeopleConnection(newDriver);
		peopleC.connectPeople(user);		
		
		info("USER2: Go to Homepage Intranet");
		navToolBar.goToHomePage();
		
		info("USER2: 79684 - Check pop-up information after sending a connection request");
		hg.checkUserInfoOnWhoisOnlineGadget(DATA_USER1, user, position, true, activity, 2);
				
		info("--Clear Data--");
		navToolBar.goToConnectionPage();
		peopleC.cancelRequest(user);
		newDriver.manage().deleteAllCookies();
		newDriver.quit();
		homeAct.deleteActivity(activity, true, false);
	}

	/**
	 * CaseID 79685
	 * Check the popup information of online user who is a connection
	 * 
	 * CaseID 79686
	 * Display pop up informations for user who's invited to connect
	 */
	@Test
	public void test02_checkDisplayOfOnlineConnectedUser(){
		String activity = "HelloWorld79685";
		String position = "Manager";
		
		navToolBar.goToMyProfile();
		peopleP.editCurrentPosition(position);
		peopleP.changeAvatar("/TestData/Winter.jpg");
		navToolBar.goToHomePage();
		addActivity(true, activity, false,"");
		
		info("USER1: Go to My Connection");
		navToolBar.goToConnectionPage();
		
		info("USER1: Connect with user acc 2");
		peopleC.connectPeople(user1);
						
		info("USER2: Switch to other window to login");
		loginWithAnotherAccOnThesameBrowser(DATA_USER2, DATA_PASS);
		hg=new HomePageGadget(newDriver);
		
		info("USER2: 79686 - Check popup information of online user when receiving a connection request");
		hg.checkUserInfoOnWhoisOnlineGadget(DATA_USER1, user, position, true, activity, 3);
		
		info("USER2: Accept invitation from user acc 1");
		hg.acceptInvitationGadget(user);
		navToolBar = new NavigationToolbar(newDriver);
		navToolBar.goToHomePage();
//		newDriver.navigate().refresh();
	
		info("USER2: 79685 - Check popup information of online user 1 when having a connection");
		hg.checkUserInfoOnWhoisOnlineGadget(DATA_USER1, user, position, true, activity, 4);
		
		info("--Clear Data--");
		peopleC = new PeopleConnection(newDriver);
		navToolBar.goToConnectionPage();
		peopleC.removeConnection(user);
		newDriver.manage().deleteAllCookies();
		newDriver.quit();
		navToolBar = new NavigationToolbar(driver);
		navToolBar.goToHomePage();
		homeAct.deleteActivity(activity, true, false);
	}
	
	/**
	 * CaseID 79678
	 * Connect to User from the information pop up
	 * 
	 * CaseID 79682
	 * Display a long title of an activity in pop up for a connected user
	 */
	@Test
	public void test03_checkUpdateOfOnlineUser(){
		String longActivity = "Merry Xmas and Happy New Year 2014 to every body all around the world. We wish you a merry xmas and a happy new year with lots of joy and happiness and luck !!!";
		
		info("USER1: Share a status on activity stream");
		addActivity(true, longActivity, false,"");
		
		info("USER2: Switch to other window to login");
		loginWithAnotherAccOnThesameBrowser(DATA_USER2, DATA_PASS);
		hg=new HomePageGadget(newDriver);
		newDriver.navigate().refresh();
		
		info("USER2: Check updated long status of user acc 1");
		hg.checkTruncatedStatusOnWhoIsOnlineGadget(DATA_USER1);
		
		info("USER2: Connect to other acc from Who'sOnline");
		hg.connectPeoplefromWhoisOnlineGadget(DATA_USER1);
		
		info("Restore data");
		info("--Cancel Connection--");
		peopleC = new PeopleConnection(newDriver);
		navToolBar = new NavigationToolbar(newDriver);
		navToolBar.goToConnectionPage();
		peopleC.cancelRequest(user);
		info("--Close the 2nd browser window--");
		Utils.pause(500);
		newDriver.manage().deleteAllCookies();
		newDriver.quit();
	}
	
	/**@date 24/4/2014
	 * @author lientm: insert
	 * CaseID 79677
	 * Automatic refresh of the Gadget "Who's online?"
	 **/
	@Test
	public void test04_AutomaticRefresh(){
		loginWithAnotherAccOnThesameBrowser(DATA_USER2, DATA_PASS);
		
		//user1 check user2 display on Who is online gadget after 1 minus
		Utils.pause(31000);//buffer 1s
		hg.checkUserInfoOnWhoisOnlineGadget(DATA_USER2, user1, null, false, null, 0);
		
		//user 2 logout
		acc = new ManageAccount(newDriver);
		acc.signOut();
		newDriver.manage().deleteAllCookies();
		newDriver.quit();
		
		//user1 check user2 not display on who is online gadget after 1 minus
		Utils.pause(31000);//buffer 1s
		waitForElementNotPresent(hg.ELEMENT_ONLINE_USER_AVATAR.replace("${acc}",DATA_USER1));
	}	
}
