package org.exoplatform.selenium.platform.calendar.sniff;

import static org.exoplatform.selenium.TestLogger.info;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.calendar.CalendarBase;
import org.exoplatform.selenium.platform.calendar.Event;
import org.exoplatform.selenium.platform.calendar.Task;
import org.testng.annotations.*;

/**
 * @author chinhdtt, thuntn
 * @date 4 Aug 2014
 */
public class Calendar_Settings_Setting extends CalendarBase{

	ManageAccount acc;
	Event event;
	Task task;

	@BeforeMethod
	public void setUpBeforeTest(){
		initSeleniumTest();
		acc = new ManageAccount(driver, this.plfVersion);
		event = new Event(driver, this.plfVersion);
		task = new Task(driver, this.plfVersion);
		acc.signIn(DATA_USER1, DATA_PASS);
		button = new Button(driver, plfVersion);
		goToCalendarPage();
	}

	@AfterMethod
	public void afterTest(){
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	/**
	 * Case ID:111882.
	 * Test Case Name: Set invitation option.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/08/04 14:02:05
	 */
	@Test(priority=0)
	public  void test01_SetInvitationOption() {
		info("Test 1: Set invitation option");
		/*Click on Settings from Tool bar
		 *Expected Outcome: Calendar Settings pop up appears.		*/
		goToCalendarSettings();
		waitForAndGetElement(ELEMENT_CALENDAR_POPUP_WINDOW);
		waitForAndGetElement(ELEMENT_SETTINGS_TAB);

		/*
		- Click Always/Never/Ask radio 
		- Click Save
		 *Input Data: 
		 *Expected Outcome: Setting calendar was set 		*/
		settingCalendar(null, null, null, null, null, null, null, 2);

		/*
		- Click [Event]
		- Go to Participant tab
		- Check Send invitation option
		 *Input Data: 
		 *Expected Outcome: 
		- Send invitation option is correct as step 2		*/ 
		event.goToAddEventFromActionBar();
		click(event.ELEMENT_ADD_EVENT_MORE_DETAILS_BUTTON);
		click(event.ELEMENT_PARTICIPANTS_TAB);
		waitForAndGetElement(event.ELEMENT_PARTICIPANT_ASK_CHECKED_RADIO,DEFAULT_TIMEOUT,1,2);
		click(event.ELEMENT_ADD_DETAIL_EVENT_CLOSE);
		goToCalendarSettings();
		settingCalendar(null, null, null, null, null, null, null, 3);
	}

	/**
	 * Case ID:111886.
	 * Test Case Name: Setup a default View type to show calendar.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/08/04 14:02:05
	 * Bug: https://jira.exoplatform.org/browse/CAL-977
	 */
	@Test(groups="error", priority=10)
	public  void test02_SetupADefaultViewTypeToShowCalendar() {
		info("Test 2: Setup a default View type to show calendar");

		/*Click on Calendar Settings icon
		 *Expected Outcome: Calendar Settings pop up appears.		*/
		goToCalendarSettings();
		waitForAndGetElement(ELEMENT_CALENDAR_POPUP_WINDOW);
		waitForAndGetElement(ELEMENT_SETTINGS_TAB);

		/*
		- Select one value in View type
		- Click Save
		 *Input Data: 
		 *Expected Outcome: 
		- New setting for calendar is saved
		- Whenever this user goes to calendar application, new selected view type is shown as default		*/ 
		info("Change Day view setting");
		settingCalendar("Day", null, null, null, null, null, null);
		
		info("Check view default");
		acc.signOut();
		acc.signIn(DATA_USER1, DATA_PASS);
		goToCalendarPage();
		waitForAndGetElement(ELEMENT_VIEW_TAB_ACTIVE.replace("${tab}", "Day"));		
		info("Check the change in setting");
		
		info("Change Month view setting");
		goToCalendarSettings();
		settingCalendar("Month", null, null, null, null, null, null);
		
		info("Check view default");
		acc.signOut();
		acc.signIn(DATA_USER1, DATA_PASS);
		goToCalendarPage();
		waitForAndGetElement(ELEMENT_VIEW_TAB_ACTIVE.replace("${tab}", "Month"));		
		
		info("Change List view setting");
		goToCalendarSettings();
		settingCalendar("List", null, null, null, null, null, null);	
		
		info("Check view default");
		acc.signOut();
		acc.signIn(DATA_USER1, DATA_PASS);
		goToCalendarPage();
		waitForAndGetElement(ELEMENT_VIEW_TAB_ACTIVE.replace("${tab}", "List"));		
		
		info("Change Month view setting");
		goToCalendarSettings();
		settingCalendar("Work Week", null, null, null, null, null, null);
		
		info("Check view default");
		acc.signOut();
		acc.signIn(DATA_USER1, DATA_PASS);
		goToCalendarPage();
		waitForAndGetElement(ELEMENT_VIEW_TAB_ACTIVE.replace("${tab}", "Work Week"));		
		
		goToCalendarSettings();
		settingCalendar("Week", null, null, null, null, null, null);
	}

	/**
	 * Case ID:111888.
	 * Test Case Name: Setup date format to show calendar.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/08/04 14:02:05
	 */
	@Test(priority=1)
	public  void test03_SetupDateFormatToShowCalendar() {
		info("Test 3: Setup date format to show calendar");
		String event1 = "Event 111888";
		String task1 = "Task 111888";
		String currentDate = getCurrentDate("dd/MM/yyyy");

		/*Click on Settings from Tool bar
		 *Expected Outcome: Calendar Settings pop up appears.		*/
		goToCalendarSettings();
		waitForAndGetElement(ELEMENT_CALENDAR_POPUP_WINDOW);
		waitForAndGetElement(ELEMENT_SETTINGS_TAB);

		/*
		- Choose a format from list for Date format
		- Click Save
		 *Input Data: 
		 *Expected Outcome: New setting is saved 		*/
			
		settingCalendar(null, "dd/mm/yyyy", null, null, null, null, null);
		
		info("Check view default");
		info("Add Event/Task");
		event.addQuickEvent(event1, event1,null,null,true,"John Smith","All");
		task.addQuickTask(task1, task1,null,null,true,"John Smith","All");
		
		/*
		- Click Add event/task
		- Check calendar in List View or search result
		 *Input Data: 
		 *Expected Outcome: All fields relate to date are displayed as new selected format		*/
		info("Check format date");
		event.goToAddEventFromActionBar();
		String fromDate = waitForAndGetElement(event.ELEMENT_INPUT_ADD_QUICK_EVENT_FROM).getAttribute("value");
		String toDate = waitForAndGetElement(event.ELEMENT_INPUT_ADD_QUICK_EVENT_TO).getAttribute("value");
		info("from date is " + fromDate + " and current date " + currentDate);
		assert currentDate.equalsIgnoreCase(fromDate): "Wrong format day";
		assert currentDate.equalsIgnoreCase(toDate): "Wrong format day";
		click(event.ELEMENT_ADD_EVENT_CLOSE_BUTTON);
		
		//Check on list view
		click(ELEMENT_BUTTON_LIST_VIEW);
		waitForAndGetElement(ELEMENT_BUTTON_VIEW_ACTIVE.replace("${view}", "List"));
		waitForAndGetElement(EVENT_LIST_VIEW.replace("${eventTitle}",event1));
		waitForAndGetElement(EVENT_LIST_VIEW.replace("${eventTitle}",task1));	
		String startDateEvent = waitForAndGetElement(event.ELEMENT_LIST_VIEW_EVENT_START.replace("${event}", event1)).getText();
		String endDateEvent = waitForAndGetElement(event.ELEMENT_LIST_VIEW_EVENT_END.replace("${event}", event1)).getText();
		String startDateTask = waitForAndGetElement(event.ELEMENT_LIST_VIEW_EVENT_START.replace("${event}", task1)).getText();
		String endDateTask = waitForAndGetElement(event.ELEMENT_LIST_VIEW_EVENT_END.replace("${event}", task1)).getText();
		
		assert startDateEvent.contains(currentDate): "Wrong format day";
		assert endDateEvent.contains(currentDate): "Wrong format day";
		assert endDateTask.contains(currentDate): "Wrong format day";
		assert startDateTask.contains(currentDate): "Wrong format day";

		//Delete data test
		click(ELEMENT_BUTTON_WEEK_VIEW);
		deleteEventTask(event1);
		deleteEventTask(task1);		
		
		goToCalendarSettings();
		settingCalendar(null, "mm/dd/yyyy", null, null, null, null, null);
	}

	/**
	 * Case ID:111889.
	 * Test Case Name: Setup time format to show calendar.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/08/04 14:02:05
	 */
	@Test(priority=2)
	public  void test04_SetupTimeFormatToShowCalendar() {
		String eventName = "Event111889";
		info("Test 4: Setup time format to show calendar");
		/*Click on Settings from Tool bar
		 *Expected Outcome: Calendar Settings pop up appears.		*/
		goToCalendarSettings();
		waitForAndGetElement(ELEMENT_CALENDAR_POPUP_WINDOW);
		waitForAndGetElement(ELEMENT_SETTINGS_TAB);

		/*
		- Choose a format from list for Time format
		- Click Save
		 *Input Data: 
		 *Expected Outcome: New setting is saved 		*/
		settingCalendar(null, null, "AM/PM", null, null, null, null);
		event.addQuickEvent(eventName, eventName, "", "", false);

		/*
		- Click Add event/task
		 *Input Data: 
		 *Expected Outcome: All fields relate to time are displayed as new selected format		*/ 
		event.goToAddEventFromActionBar();
		String fromTime = waitForAndGetElement(event.ELEMENT_INPUT_EVENT_FROM_TIME_IN).getAttribute("value");
		String toTime = waitForAndGetElement(event.ELEMENT_INPUT_EVENT_TO_TIME_IN).getAttribute("value");
		assert fromTime.contains("AM") || fromTime.contains("PM");
		assert toTime.contains("AM") || toTime.contains("PM");
		click(event.ELEMENT_ADD_EVENT_CLOSE_BUTTON);
		waitForElementNotPresent(event.ELEMENT_ADD_EVENT_CLOSE_BUTTON);
		
		//Check on List view
		click(ELEMENT_BUTTON_LIST_VIEW);
		waitForAndGetElement(EVENT_WORK_WEEK_VIEW.replace("${eventTitle}", eventName));
		
		String startTime = waitForAndGetElement(event.ELEMENT_LIST_VIEW_EVENT_START.replace("${event}", eventName)).getText();
		String endTime = waitForAndGetElement(event.ELEMENT_LIST_VIEW_EVENT_END.replace("${event}", eventName)).getText();
		assert startTime.contains("AM") || startTime.contains("PM");
		assert endTime.contains("AM") || endTime.contains("PM");
		
		goToCalendarSettings();
		settingCalendar(null, null, "24 Hours", null, null, "", "");
		
		deleteEventTask(eventName);
	}

	/**
	 * Case ID:111890.
	 * Test Case Name: Setup Time zone to show calendar.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/08/04 14:02:05
	 */
	@Test(priority=3)
	public  void test05_SetupTimeZoneToShowCalendar() {
		info("Test 5: Setup Time zone to show calendar");

		String currentTime = addMinuteToCurrentDateTime(0,"HH:mm");
		/*Click on Settings from Tool bar
		 *Expected Outcome: Calendar Settings pop up appears.		*/
		goToCalendarSettings();
		waitForAndGetElement(ELEMENT_CALENDAR_POPUP_WINDOW);
		waitForAndGetElement(ELEMENT_SETTINGS_TAB);

		/*
		- Choose value from list for Time Zone
		- Click Save
		 *Input Data: 
		 *Expected Outcome: New setting is saved 		*/
		setTimezoneForCalendar("(GMT +07:00) Asia/Ho_Chi_Minh");
		/*
		- Click Add event/task
		 *Input Data: 
		 *Expected Outcome: Time Zone are displayed as selected		*/ 
		event.goToAddEventFromActionBar();
		String fromTime = waitForAndGetElement(event.ELEMENT_INPUT_EVENT_FROM_TIME_IN).getAttribute("value");
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		try{
			Date current = formatter.parse(currentTime);
			Date from = formatter.parse(fromTime);
			assert from.after(current);
			assert from.before(formatter.parse(addMinuteToCurrentDateTime(30,"HH:mm")));
		}catch(Exception e){
			e.printStackTrace();
		}
		click(event.ELEMENT_ADD_EVENT_CLOSE_BUTTON);

	}


	/**
	 * Case ID:111891.
	 * Test Case Name: Setup start day for week.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/08/04 14:02:05
	 */
	@Test(priority=4)
	public  void test06_SetupStartDayForWeek() {
		info("Test 6: Setup start day for week");
		/*Click on Settings from Tool bar
		 *Expected Outcome: Calendar Settings pop up appears.		*/
		goToCalendarSettings();
		waitForAndGetElement(ELEMENT_CALENDAR_POPUP_WINDOW);
		waitForAndGetElement(ELEMENT_SETTINGS_TAB);

		/*
		- Select a day from list form Week start on
		- Click Save
		 *Input Data: 
		 *Expected Outcome: New setting is saved 		*/
		settingCalendar(null, null, null, null, "Tuesday", null, null);

		/*Select Week View
		 *Input Data: 
		 *Expected Outcome: The start date of week on both mini calendar and main calendar is new selected date		*/ 
		waitForAndGetElement(ELEMENT_FIRST_DAY_WEEK.replace("${day}", "Tue"));
		waitForAndGetElement(ELEMENT_MINI_FIRST_DAY_WEEK.replace("${day}", "TU"));
		goToCalendarSettings();
		settingCalendar(null, null, null, null, "Monday", "", "");
	}

	/**
	 * Case ID:111892.
	 * Test Case Name: Setup working time for calendar.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/08/04 14:02:05
	 */
	@Test(priority=5)
	public  void test07_SetupWorkingTimeForCalendar() {
		info("Test 7: Setup working time for calendar");
		/*Click on Settings from Tool bar
		 *Expected Outcome: Calendar Settings pop up appears.		*/
		goToCalendarSettings();
		waitForAndGetElement(ELEMENT_CALENDAR_POPUP_WINDOW);
		waitForAndGetElement(ELEMENT_SETTINGS_TAB);

		/*
		- Check Show working times option
		 *Input Data: 
		 *Expected Outcome: Start and End time fields are shown to choose		*/
		/*
		- Select time for Start and End
		- Click Save
		 *Input Data: 
		 *Expected Outcome: Change is saved		*/

		settingCalendar(null, null, null, null, null, "06:00", "08:00");

		/*
		- Check working pane
		 *Input Data: 
		 *Expected Outcome: Working pane is shown as normal from selected Start to End time, other time in day will be in Gray		*/ 
		for(int i=0; i<6; i++){
			waitForAndGetElement(ELEMENT_WORKING_PANEL_ROW.replace("${hour}", i+ ":00"));
		}
		for(int i=8; i<23; i++){
			waitForAndGetElement(ELEMENT_WORKING_PANEL_ROW.replace("${hour}", i+ ":00"));
		}

		waitForElementNotPresent(ELEMENT_WORKING_PANEL_ROW.replace("${hour}", "06:00"));
		waitForElementNotPresent(ELEMENT_WORKING_PANEL_ROW.replace("${hour}", "07:00"));
		goToCalendarSettings();
		settingCalendar(null, null, null, null, null, "", "");
	}

	/**
	 * Case ID:111893.
	 * Test Case Name: Cancel settings form.
	 * Pre-Condition: 
	 * Post-Condition: 
	 * Done with OSs and browsers : 
	 * Generated by chinhdtt at 2014/08/04 14:02:05
	 */
	@Test(priority=6)
	public  void test08_CancelSettingsForm() {
		info("Test 8: Cancel settings form");
		/*Click on Calendar Settings icon
		 *Expected Outcome: Calendar Settings pop up appears.		*/
		goToCalendarSettings();
		waitForAndGetElement(ELEMENT_CALENDAR_POPUP_WINDOW);
		waitForAndGetElement(ELEMENT_SETTINGS_TAB);

		/*
		- Change something in settings form
		- Click Cancel
		 *Input Data: 
		 *Expected Outcome: 
		- The changes are not saved		*/ 
		info("Change view setting");
		select(ELEMENT_VIEW_TYPE, "Month");
		click(ELEMENT_SETTINGS_FORM_CANCEL_BUTTON);

		info("Check nothing is change in setting");
		goToCalendarSettings();
		String viewType = waitForAndGetElement(ELEMENT_SELECTED_VIEW_TYPE, 5000, 1, 2).getText();
		assert viewType.equalsIgnoreCase("Week");
	}
}