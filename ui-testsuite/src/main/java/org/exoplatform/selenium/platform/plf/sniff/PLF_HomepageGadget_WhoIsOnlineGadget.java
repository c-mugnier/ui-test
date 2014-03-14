package org.exoplatform.selenium.platform.plf.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.HomePageActivity;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.NavigationToolbar;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.exoplatform.selenium.platform.HomePageGadget;
import org.exoplatform.selenium.platform.social.Activity;
import org.exoplatform.selenium.platform.social.PeopleConnection;
import org.exoplatform.selenium.platform.social.PeopleProfile;

/**
 * 
 * @author havtt
 * @date 01-Nov-2013
 */
public class PLF_HomepageGadget_WhoIsOnlineGadget extends Activity {

	ManageAccount acc;
	HomePageGadget hg;
	NavigationToolbar navToolBar;
	PeopleConnection peopleC;
	PeopleProfile peopleP;
	HomePageActivity homeAct;
	
	String User1 = DATA_USER1; //"john";
	String User2 = DATA_USER2; //"mary";
	//String Pass1 = "gtn";
	String fullNameUser2 = "Mary Williams";
	String fullNameUser1 = "John Smith";
	
	@BeforeMethod
	public void setUpBeforeTest(){
//		getDriverAutoSave();
		initSeleniumTest();
		acc = new ManageAccount(driver);
		hg = new HomePageGadget(driver);
		peopleC = new PeopleConnection(driver);
		navToolBar = new NavigationToolbar(driver);
		peopleP = new PeopleProfile(driver);
		homeAct = new HomePageActivity(driver);
		acc.signIn(User1, DATA_PASS);
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}


	/**
	 * Check display of WhoisOnline gadget
	 * CaseID 70763
	 */
	@Test
	public void test01_checkDisplayOfWhoisOnlineGadget(){
		info("Go to Homepage Intranet by user acc 1");
		navToolBar.goToHomePage();
				
		info("Switch to other browser to login by user acc 2");
		loginWithAnotherAccOnThesameBrowser(User2, DATA_PASS);
	
		info("Confirm if WhoisOnline gadget dislays or not with user1");
		hg=new HomePageGadget(newDriver);
		newDriver.findElement(hg.ELEMENT_WHOISONLINE_GADGET);
		Utils.pause(500);
		newDriver.manage().deleteAllCookies();
		newDriver.quit();
	}
	
	/**
	 * Show information of online users
	 * CaseID 70764
	 */
	@Test
	public void test02_showInfoOfOnlineUser(){
		String position = "Developer";
		String activity = "Activity70764";
		
		info("Go to Homepage Intranet by user acc 1");
		navToolBar.goToMyProfile();
		peopleP.editCurrentPosition(position);
		peopleP.changeAvatar("/TestData/Winter.jpg");
		navToolBar.goToHomePage();
		addActivity(true, activity, false, null);
		
		info("Switch to other browser to login by user acc 2");
		loginWithAnotherAccOnThesameBrowser(User2, DATA_PASS);
		
		info("Confirm if WhoisOnline gadget dislays or not with user1");
		hg=new HomePageGadget(newDriver);
		hg.checkUserInfoOnWhoisOnlineGadget(User1, fullNameUser1, position, true, activity, 0);
		newDriver.manage().deleteAllCookies();
		newDriver.quit();
		
		homeAct.deleteActivity(activity, true, false);
	}
	
	/**
	 * Connect with users from Who's online gadget
	 * CaseID 70764
	 */
	@Test
	public void test03_ConnectUserfromWhoisOnlineGadget(){
//		info("Go to Homepage Intranet by user acc 1");
//		navToolBar.goToHomePage();
		
		info("Switch to other browser to login by user acc 2");
		loginWithAnotherAccOnThesameBrowser(User2, DATA_PASS);
		hg=new HomePageGadget(newDriver);
		peopleC = new PeopleConnection(newDriver);
		acc = new ManageAccount(newDriver);
		navToolBar = new NavigationToolbar(newDriver);
		peopleC.resetConnection(fullNameUser1);
		navToolBar.goToHomePage();
		hg.checkUserInfoOnWhoisOnlineGadget(User1, fullNameUser1, null, false, null, 0);
		
		info("User 2 connect with user 1 from Who's Online gadget");
		hg.connectPeoplefromWhoisOnlineGadget(User1);
		acc.signOut();

		info("Check if user 1 received connect invitation from user 2 or not");
		acc.signIn(User1, DATA_PASS);
//		Utils.pause(500);
//		newDriver.findElement(By.xpath(hg.ELEMENT_SHOW_CONNECTIONS_REQUEST_USER.replace("${nameinvitation}","Root Root")));
		waitForAndGetElement(hg.ELEMENT_SHOW_CONNECTIONS_REQUEST_USER.replace("${nameinvitation}",fullNameUser2), DEFAULT_TIMEOUT, 1, 2, newDriver);
//		Utils.pause(500);
		
		info("-- Clear data --");
		navToolBar.goToConnectionPage();
		peopleC.ignoreInvitation(fullNameUser2);
		newDriver.manage().deleteAllCookies();
		newDriver.quit();
	}

	/**
	 * Access activity stream of other user from Who's online gadget
	 * CaseID 70764
	 */
	@Test
	public void test04_accessASfromWhoisOnlineGadget(){
		info("Go to Homepage Intranet by user acc 1");
		navToolBar.goToHomePage();
		
		info("Switch to other browser to login by user acc 2");
		loginWithAnotherAccOnThesameBrowser(User2, DATA_PASS);
		
		info("User 2 connect with user 1 from Who's Online gadget");
		hg=new HomePageGadget(newDriver);
		hg.accessASfromWhoisOnlineGadget(User1);
		Utils.pause(500);
		
		info("Check if user 1 profile page is displayed or not");
		newDriver.findElement(By.xpath(hg.ELEMENT_PROFILE_TAB_USER_INFO.replace("${acc}",User1)));
		Utils.pause(500);
		newDriver.findElement(By.xpath(hg.ELEMENT_MY_AS_TAB.replace("${acc}",User1)));
		Utils.pause(500);
		newDriver.manage().deleteAllCookies();
		newDriver.quit();
	}
}
