package org.exoplatform.selenium.platform.calendar;

import static org.exoplatform.selenium.TestLogger.info;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;

/**
 * @Date 10 Oct 2013
 * @author thuntn
 *
 */
public class Event extends CalendarBase{

	//--------------------Event basic actions------------------------
	public By ELEMENT_RIGHT_CLICK_ADD_EVENT = By.xpath("//*[@id='tmpMenuElement']//*[@class='createEvent']");
	public By ELEMENT_INPUT_EVENT_TITLE = By.id("eventName");
	public By ELEMENT_INPUT_EVENT_DESCRIPTION = By.id("description");
	public By ELEMENT_CHECKBOX_EVENT_ALLDAY = By.xpath("//form[@id='UIQuickAddEvent']//input[@id='allDay']");
	public By ELEMENT_INPUT_EVENT_FROM = By.name("from");
	public By ELEMENT_INPUT_EVENT_TO = By.name("to");
	public By ELEMENT_INPUT_EVENT_FROM_TIME = By.xpath("//*[@id='fromTime']/../input");
	public By ELEMENT_INPUT_EVENT_TO_TIME = By.xpath("//*[@id='toTime']/../input");
	public By ELEMENT_INPUT_EVENT_FROM_TIME_CURRENT_VALUE = By.xpath("//*[@id='UIQuickAddEventPopupWindow']//*[@name='fromTime']");
	public By ELEMENT_INPUT_EVENT_TO_TIME_CURRENT_VALUE = By.xpath("//*[@id='UIQuickAddEventPopupWindow']//*[@name='toTime']");
	public By ELEMENT_INPUT_EVENT_CALENDAR = By.name("calendar");
	public By ELEMENT_INPUT_EVENT_CATEGORY = By.name("category");
	public By ELEMENT_BUTTON_EVENT = By.id("UIActionBarQuickAddEvent");
	public By ELEMENT_MENU_EVENT_EDIT = By.xpath("//*[@id='tmpMenuElement']//i[@class='uiIconEdit uiIconLightGray']");
	public By ELEMENT_ADD_EVENT_POPUP = By.xpath("//span[@class='PopupTitle popupTitle' and text()='Quick Add Event']");
	public By ELEMENT_INPUT_EVENT_FROM_TIME_IN = By.xpath("//*[@id='UIQuickAddEvent']//*[@id='fromTime']/..//*[@class='UIComboboxInput']");
	public By ELEMENT_INPUT_EVENT_TO_TIME_IN = By.xpath("//*[@id='UIQuickAddEvent']//*[@id='toTime']/..//*[@class='UIComboboxInput']");
	public String ELEMENT_EVENT_SELECT_FROM_TIME = "//*[@id='UIQuickAddEvent']//*[@id='fromTime']/..//*[@class='UIComboboxLabel' and text()='${time}']";
	public By ELEMENT_ADD_EVENT_SAVE_QUICK_BUTTON = By.xpath("//*[@id='QuickAddEventContainer']//*[text()='Save']");

	//Preview form
	public String ELEMENT_EVENT_PREVIEW_TITLE = "//form[@id='UIPreviewPopup']//div[@class='titleList']/strong[text()='${event}']";

	//Form Add event details
	public By ELEMENT_ADD_EDIT_EVENT_ALLDAY = By.xpath("//form[@id='UIEventForm']//input[@id='allDay']");
	public By ELEMENT_ADD_EDIT_EVENT_TITLE = By.xpath("//form[@id='UIEventForm']//*[@id='eventName']");
	public By ELEMENT_ADD_EDIT_EVENT_DESC = By.xpath("//form[@id='UIEventForm']//*[@id='description']");
	public By ELEMENT_ADD_EDIT_EVENT_FROM = By.xpath("//form[@id='UIEventForm']//*[@name='from']");
	public By ELEMENT_ADD_EDIT_EVENT_TO = By.xpath("//form[@id='UIEventForm']//*[@name='to']");
	public By ELEMENT_ADD_EDIT_EVENT_TO_TIME = By.xpath("//form[@id='UIEventForm']//*[@id='toTime']/../input[@class='UIComboboxInput']");
	public By ELEMENT_ADD_EDIT_EVENT_FROM_TIME = By.xpath("//form[@id='UIEventForm']//*[@id='fromTime']/../input[@class='UIComboboxInput']");
	public By ELEMENT_ADD_EDIT_EVENT_CALENDAR = By.xpath("//form[@id='UIEventForm']//*[@name='calendar']");
	public By ELEMENT_ADD_EVENT_SAVE_BUTTON = By.xpath("//form[@id='UIEventForm']//*[text()='Save']");
	public By ELEMENT_ADD_EDIT_EVENT_CATEGORY = By.xpath("//form[@id='UIEventForm']//*[@name='category']");
	public By ELEMENT_ADD_EDIT_EVENT_LOCATION = By.id("place");
	public By ELEMENT_EDIT_EVENT_POPUP = By.xpath("//span[@class='PopupTitle popupTitle' and text()='Add/Edit Event']");
	public By ELEMENT_ADD_EDIT_EVENT_FROM_TIME_IN = By.xpath("//*[@id='UIEventForm']//*[@id='fromTime']/..//*[@class='UIComboboxInput']");
	public By ELEMENT_ADD_EDIT_EVENT_TO_TIME_IN = By.xpath("//*[@id='UIEventForm']//*[@id='toTime']/..//*[@class='UIComboboxInput']");
	public String ELEMENT_ADD_EDIT_EVENT_SELECT_FROM_TIME = "//*[@id='UIEventForm']//*[@id='fromTime']/..//*[@class='UIComboboxLabel' and text()='${time}']";
	public String ELEMENT_ADD_EDIT_EVENT_SELECT_TO_TIME = "//*[@id='UIEventForm']//*[@id='toTime']/..//*[@class='UIComboboxLabel' and text()='${time}']";

	/*Recurring event form*/
	public By ELEMENT_BUTTON_EVENT_MORE_DETAILS = By.xpath("//*[@id='UIQuickAddEventPopupWindow']//*[text()='More Details']");
	public By ELEMENT_RECURRING_TYPE_SELECT_BOX = By.xpath("//*[@name='repeatType']");
	public By ELEMENT_INTERVAL_SELECT_BOX = By.xpath("//*[@name='interval']");
	public By ELEMENT_END_AFTER_NUMBER = By.id("endAfterNumber");
	public By ELEMENT_NEVER_END_RECURRING_EVENT = By.id("endNever");
	public By ELEMENT_AFTER_END_RECURRING_EVENT = By.id("endAfter");
	public By ELEMENT_BY_THIS_DATE_END_RECURRING_EVENT = By.id("endByDate");
	public By ELEMENT_DATE_TIME_PICKER = By.xpath("//*[contains(@id, 'DateTimePicker')]");
	public By ELEMENT_IS_REPEAT_CHECKBOX = By.id("isRepeat");
	public By ELEMENT_SAVE_EVENT_OCCURRING = By.xpath("//*[@id='UIRepeatEventForm']//*[contains(text(),'Save')]");

	/*Delete recurring event form*/
	public By ELEMENT_DELETE_RECURRING_EVENT_FORM = By.id("UICalendarPopupWindow");
	public By ELEMENT_EDIT_DELETE_ONE_EVENT = By.xpath("//*[@value='save_one']");
	public By ELEMENT_EDIT_DELETE_FOLLOWING_EVENT = By.xpath("//*[@value='save_follow']");
	public By ELEMENT_EDIT_DELETE_ALL_EVENT = By.xpath("//*[@value='save_all']");
	public By ELEMENT_CONFIRM_DELETE_BUTTON = By.xpath("//*[@id='UIConfirmFormDelete']//*[text()='Delete']");
	public By ELEMENT_CONFIRM_CANCEL_BUTTON = By.xpath("//*[@id='UIConfirmFormDelete']//*[text()='Cancel']");
	public String ELEMENT_CONFIRM_DELETE_MESSAGE = "delete only this event";
	//"Would you like to delete only this event, all events in the series, or this and all following events in the series?";
	public By ELEMENT_CONFIRM_EDIT_DELETE_RECURRING_EVENT = By.xpath("//*[@class='media-body']");

	/*Delete recurring event*/
	public By ELEMENT_CONFIRM_EDIT_BUTTON = By.xpath("//*[@id='UIConfirmFormUpdate']//*[text()='Save']");
	public By ELEMENT_CONFIRM_EDIT_RECURRING_FORM = By.id("UICalendarChildPopupWindow");
	public String ELEMENT_CONFIRM_EDIT_MESSAGE = "change only this event";
	//"Would you like to change only this event, all events in the series, or this and all following events in the series?";

	/*Open a week*/
	public By ELEMENT_NEXT_WEEK = By.xpath("//*[@class='UIWeekView uiBox uiWeekView']//*[@class='uiIconMiniArrowRight uiIconLightGray']");
	public By ELEMENT_PREVIOUS_WEEK = By.xpath("//*[@class='UIWeekView uiBox uiWeekView']//*[@class='uiIconMiniArrowLeft uiIconLightGray']");

	/*Content recurring*/
	public By ELEMENT_TITLE_RECURRING_EVENT = By.xpath("//*[@class='popover-content']/*[@class='title clearfix']/*[@class='text']");
	public By ELEMENT_DATE_TIME_RECURRING_EVENT = By.xpath("//*[@class='popover-content']/*[@class='time clearfix']//*[@class='uiIconCalClockMini']/../../*[@class='text']");
	public By ELEMENT_RECURRING_TEXT_RECURRING_EVENT = By.xpath("//*[@class='popover-content']/*[@class='time clearfix']//*[@class='uiIconCalRecurring']/../../*[@class='text']");
	public By ELEMENT_EDITED_RECURRING_TEXT_RECURRING_EVENT = By.xpath("//*[@class='popover-content']/*[@class='time clearfix']//*[@class='uiIconCalEditRecurring']/../../*[@class='text']");
	public By ELEMENT_DESCRIPTION_EVENT = By.xpath("//*[@class='popover-content']/*[@class='description']");

	//Constructor
	public Event(WebDriver dr, String...plfVersion){
		driver = dr;
		this.plfVersion = plfVersion.length>0?plfVersion[0]:"4.0";
		button = new Button(driver, this.plfVersion);
		alert = new ManageAlert(driver, this.plfVersion);
	}

	/******************Go to******************************/
	/**
	 * Open form Add event
	 * @author thuntn
	 */
	public void goToAddEventFromActionBar(){
		info("Go to Add Event page");
		click(ELEMENT_BUTTON_EVENT);
		waitForAndGetElement(ELEMENT_ADD_EVENT_POPUP);
	}

	/**
	 * @author lientm
	 * @param calendarName
	 */
	public void goToAddEventFromCalendar(String calendarName){
		goToActionOnCalendar(calendarName, "add event");
		waitForAndGetElement(ELEMENT_ADD_EVENT_POPUP);
	}

	/**
	 * @author lientm
	 * @param time: format 12:00
	 */
	public void goToAddEventFromMainPane(String time){
		String current = getCurrentDate("MMM dd yyyy");
		info("Current date is " + current);

		String cell = "//td[contains(@startfull,'" + current + " " + time + ":00')]";
		rightClickOnElement(cell);
		click(ELEMENT_RIGHT_CLICK_ADD_EVENT, 2);
		waitForAndGetElement(ELEMENT_ADD_EVENT_POPUP);
	}

	/**add event by click on block time in main panel
	 * @author lientm
	 * @param time
	 */
	public void goToAddEventByClickOnCell(String time){
		String current = getCurrentDate("MMM dd yyyy");
		info("Current date is " + current);
		for (int i = 0; i < 5; i ++){
			if (waitForAndGetElement(ELEMENT_ADD_EVENT_POPUP, 5000, 0) == null){
				click("//td[contains(@startfull,'" + current + " " + time + ":00')]", 2);
				info("Repeat " + i);
			}
		}
		waitForAndGetElement(ELEMENT_ADD_EVENT_POPUP);
	}



	/**
	 * Open form Add event
	 * @author thuntn
	 */
	public void goToAddEvent(){
		info("Go to Add Event page");
		click(ELEMENT_BUTTON_EVENT);
		waitForAndGetElement(ELEMENT_ADD_EVENT_POPUP);
	}

	/**
	 * Open Edit Event form 
	 * @param oldEvent: old name of event
	 */
	public void goToEditEventForm(String oldEvent){
		if(waitForAndGetElement(ELEMENT_EVENT_TASK_ONE_DAY.replace("${taskName}", oldEvent), 15000, 0) != null){
			rightClickOnElement(ELEMENT_EVENT_TASK_ONE_DAY.replace("${taskName}", oldEvent), 2);
		}else if (waitForAndGetElement(ELEMENT_EVENT_TASK_ONE_DAY_PLF41.replace("${taskName}", oldEvent), 15000, 0) != null){
			rightClickOnElement(ELEMENT_EVENT_TASK_ONE_DAY_PLF41.replace("${taskName}", oldEvent), 2);
		}else{
			rightClickOnElement(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}", oldEvent), 2);
		}
		click(ELEMENT_MENU_EVENT_EDIT);
		waitForAndGetElement(ELEMENT_EDIT_EVENT_POPUP);
	}
	/******************End of Go To Edit An Event**********************/

	/**************Input data form***************/
	/**
	 * Input into basic fields of Add quick event and tab details of Add/Edit event
	 * @param name: name of event
	 * @param description: description of event
	 * @param opt: opt[0]: calendar name of this event (optional)
	 * opt[1]: category name of this event (optional)
	 */
	public void inputBasicQuickEvent(String name, String description, String...opt){
		boolean quick = (waitForAndGetElement(ELEMENT_ADD_EVENT_POPUP,5000,0) != null) ? true : false; 
		if(quick){
			if (name != null){
				type(ELEMENT_INPUT_EVENT_TITLE, name, true);
			}
			if (description != null){
				type(ELEMENT_INPUT_EVENT_DESCRIPTION, description, true);
			}
			if (opt.length > 0 && opt[0] != null){
				select(ELEMENT_INPUT_EVENT_CALENDAR, opt[0]);
			}
			if (opt.length > 1 && opt[1] != null){
				select(ELEMENT_INPUT_EVENT_CATEGORY, opt[1]);
			}
		}else{
			if (name != null){
				type(ELEMENT_ADD_EDIT_EVENT_TITLE, name, true);
			}
			if (description != null){
				type(ELEMENT_ADD_EDIT_EVENT_DESC, description, true);
			}
			if (opt.length > 0 && opt[0] != null){
				select(ELEMENT_ADD_EDIT_EVENT_CALENDAR, opt[0]);
			}
			if (opt.length > 1 && opt[1] != null){
				select(ELEMENT_ADD_EDIT_EVENT_CATEGORY, opt[1]);
			}
		}
	}
	/**
	 * Input into From, To fields of Add quick event and tab details of Add/Edit event
	 * @param from: from date, time, eg: 11/06/2013 14:00
	 * @param to: To date, time fields, eg: 11/06/2013 14:00
	 * @param allDay: all day
	 */
	public void inputFromToEvent(String from, String to, boolean allDay){
		boolean quick = (waitForAndGetElement(ELEMENT_ADD_EVENT_POPUP,5000,0) != null) ? true : false; 
		if(quick){
			if(allDay){
				check(ELEMENT_CHECKBOX_EVENT_ALLDAY,2);
				if((from != "") & (from != null))
					type(ELEMENT_INPUT_EVENT_FROM, from, true);
				if((to != null) & (to != ""))
					type(ELEMENT_INPUT_EVENT_TO, to, true);
			}
			else{
				uncheck(ELEMENT_CHECKBOX_EVENT_ALLDAY,2);
				if((from != "") & (from != null)){
					String[] dateTime = from.split(" ");
					if(dateTime.length > 0)
						type(ELEMENT_INPUT_EVENT_FROM, dateTime[0], true);
					if(dateTime.length > 1)
						type(ELEMENT_INPUT_EVENT_FROM_TIME, dateTime[1], true);
				}
				if((to != null) & (to != "")){
					String[] dateTime = to.split(" ");
					if(dateTime.length > 0)
						type(ELEMENT_INPUT_EVENT_TO, dateTime[0], true);
					if(dateTime.length > 1)
						type(ELEMENT_INPUT_EVENT_TO_TIME, dateTime[1], true);
				}
			}
		}else{
			if(allDay){
				check(ELEMENT_ADD_EDIT_EVENT_ALLDAY,2);
				if((from != "") & (from != null))
					type(ELEMENT_ADD_EDIT_EVENT_FROM, from, true);
				if((to != "") & (to != null))
					type(ELEMENT_ADD_EDIT_EVENT_TO, to, true);
			}else{
				uncheck(ELEMENT_ADD_EDIT_EVENT_ALLDAY,2);
				if((from != "") & (from != null)){
					String[] dateTime = from.split(" ");
					if(dateTime.length > 0)
						type(ELEMENT_ADD_EDIT_EVENT_FROM, dateTime[0], true);
					if(dateTime.length > 1)
						type(ELEMENT_ADD_EDIT_EVENT_FROM_TIME, dateTime[1], true);
				}
				if((to != null) & (to != "")){
					String[] dateTime = to.split(" ");
					if(dateTime.length > 0)
						type(ELEMENT_ADD_EDIT_EVENT_TO, dateTime[0], true);
					if(dateTime.length > 1)
						type(ELEMENT_ADD_EDIT_EVENT_TO_TIME, dateTime[1], true);
				}
			}

		}
	}

	/**
	 * Input into other fields of tab Details of Add/Edit event
	 * @param location: location of event
	 * 
	 */
	public void inputOtherFieldsTabDetailsEvent(String location){
		if(location != null){
			type(ELEMENT_ADD_EDIT_EVENT_LOCATION,location,true);
		}
	}

	/**Input into Add/Edit Event form
	 * @param name: name of event
	 * @param description: description of event
	 * @param location: location of event
	 * @param from: from date, time of event
	 * @param to: to date, time of event
	 * @param allDay: allDay of event
	 * @param opt: opt[0]: calendar name of this event (optional)
	 * opt[1]: category of this event (optional)
	 */
	public void inputAddEventForm(String name, String description, String location, String from, String to, boolean allDay, String...opt){
		inputBasicQuickEvent(name, description, opt);
		inputOtherFieldsTabDetailsEvent(location);
		inputFromToEvent(from, to, allDay);
	}

	/**
	 * Define a type of repeat
	 * Daily
	 * Weekly
	 * Monthly
	 * Yearly
	 */
	public enum repeatType {
		Daily, Weekly, Monthly, Yearly;
	}

	/**
	 * Define a type of repeat on
	 * MO
	 * TU
	 * WE
	 * TH
	 * FR
	 * SA
	 * SU
	 */
	public enum repeatOn {
		MO, TU, WE, TH, FR, SA, SU;
	}

	/**
	 * Define a type of repeat
	 * Never
	 * After
	 * ByThisDate
	 */
	public enum repeatEndType {
		Never, After, ByThisDate;
	}

	/**
	 * input recurring info event
	 * @param repeatType
	 * repeat type: Daily, Weekly, Monthly, Yearly;
	 * @param repeatInterval
	 * @param repeatOn
	 * @param endRepeat
	 * @param option
	 * occurrennumber if endRepeat.equals(repeatEndType.After)
	 * day format if endRepeat.equals(repeatEndType.ByThisDate) -- format: mm/dd/yyyy
	 */
	public void inputRecurringInfoEvent(repeatType repeatType, String repeatInterval, repeatOn[] repeatOn, repeatEndType endRepeat, String...option){
		info("Add recurring information");
		String occurence = option.length > 0 ? option[0]: "";
		if(repeatType!=null){
			select(ELEMENT_RECURRING_TYPE_SELECT_BOX,String.valueOf(repeatType));
		}
		if(repeatInterval!=null){
			select(ELEMENT_RECURRING_TYPE_SELECT_BOX,String.valueOf(repeatInterval));
		}
		if(repeatOn!=null){
			for(int i = 0; i<repeatOn.length; i++){
				check(By.id(String.valueOf(repeatOn[i])));
			}
		}
		if(endRepeat!=null){
			switch(endRepeat){
			case After:
				info("Check After option");
				check(ELEMENT_END_AFTER_NUMBER,2);
				if(occurence!="")
					type(ELEMENT_END_AFTER_NUMBER,option[0],true);
				break;
			case ByThisDate:
				info("Check By this date option");
				check(ELEMENT_BY_THIS_DATE_END_RECURRING_EVENT,2);
				if(occurence!="")
					type(ELEMENT_DATE_TIME_PICKER,option[0],true);
				break;
			case Never:
				info("Check never option");
				check(ELEMENT_NEVER_END_RECURRING_EVENT,2);
				break;
			}
		}
		click(ELEMENT_SAVE_EVENT_OCCURRING);
	}
	/************End of input data form***************/


	/******************Add/Edit Event*************************/
	/** Quick add event
	 * @param name: name of event
	 * @param description: description of event
	 * @param from: from date, time of event
	 * @param to: to date, time of event
	 * @param allDay: allDay of event
	 * @param opt: opt[0]: calendar name of this event (optional)
	 * opt[1]: category of this event (optional)
	 */
	public void addQuickEvent(String name, String description, String from, String to, boolean allDay, String...opt){
		info("--Add an event--");
		goToAddEvent();
		inputBasicQuickEvent(name, description, opt);
		inputFromToEvent(from, to, allDay);
		button.save();
		waitForElementNotPresent(ELEMENT_ADD_EVENT_POPUP);
		Utils.pause(1000);
	}


	/**Choose category opt for Add event
	 * @author havtt
	 * @date 23-Oct-2013
	 * This method is not necessary. There is a command "select(ELEMENT_INPUT_EVENT_CATEGORY, opt[1]);" in the method inputQuickAddEventForm
	 */

	/*public void chooseCategorywhenAddEvent(String categoryName){
		info("----Choose category opt----");
		waitForAndGetElement(ELEMENT_EVENT_CATEGORY_OPT_COMBOBOX.replace("${categoryName}", categoryName));
		click(ELEMENT_EVENT_CATEGORY_OPT_COMBOBOX.replace("${categoryName}", categoryName));
	}
	}*/

	/**Edit an event
	 * @author thuntn
	 * @param oldEvent: old name of event
	 * @param name: new name of event
	 * @param description: new description of event
	 * @param location: new location of event
	 * @param from: new from date, time of event
	 * @param to: new to date, time of event
	 * @param allDay: new allDay of event
	 * @param opt: opt[0]: new calendar name of this event (optional)
	 * opt[1]: new category of this event (optional)
	 */
	public void editEvent(String oldEvent, String name, String description, String location,String from, String to, boolean allDay, String...opt){
		info("Edit an event");
		goToEditEventForm(oldEvent);
		inputAddEventForm(name,description, location,from,to,allDay,opt);
		click(ELEMENT_ADD_EVENT_SAVE_BUTTON);
		waitForElementNotPresent(ELEMENT_EDIT_EVENT_POPUP,60000);
	}

	/****************End of Add/Edit Event*******************/
	/**
	 * Define a type of delete recurring
	 * Only this event
	 * Following events
	 * All events
	 */
	public enum recurringType {
		ONLY_EVENT, FOLLOW_EVENT, ALL_EVENT;
	}

	/**
	 * Delete an event
	 *
	 * @param event
	 * name of event or task
	 * @param options
	 * optional parameter, if not be set, the event/task will be automatically set as "all day" event/task
	 * = selectDayOption.ALLDAY: this function consider the event/task as all day
	 * = selectDayOption.ONEDAY: this function consider the event/task as one day
	 * optDeleteType parameter: if not be set, the event will be automatically set as "all day" event
	 * dateTime parameter: the date of event is deleted. If it is set, the event with datetime will be deleted.
	 */
	public void deleteRecurringEvent(String event, Object... options){
		selectDayOption optDay = (selectDayOption) (options.length > 0 ? options[0]: selectDayOption.ALLDAY);
		recurringType optDeleteType = (recurringType) (options.length > 1 ? options[1]: recurringType.ALL_EVENT);
		String dateTime = (String)(options.length > 2 ? options[2]: "");
		waitForAndGetElement(ELEMENT_WORKING_PANE_23H);

		info("--Delete an Recurring Event--");
		switch (optDay) {
		case ALLDAY:
			info("Delete an Recurring Event...with ALL DAY option");
			if(this.plfVersion.contains("4.0")){
				if(waitForAndGetElement(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}", event), 5000, 0) == null){
					rightClickOnElement(ELEMENT_EVENT_TASK_WORKING_PANE.replace("${event}", event),2);
				}
				else{
					rightClickOnElement(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}", event),2);
				}
			}
			else{ //this.plfVersion.contains("4.1")
				if(dateTime!="")
					rightClickOnElement(By.xpath(ELEMENT_EVENT_TASK_DETAIL_ALL_DAY.replace("${event}", event).replace("${date}", dateTime)),2);
				else{
					if(waitForAndGetElement(ELEMENT_EVENT_TASK_ALL_DAY_PLF41.replace("${event}", event), 5000, 0) == null){
						rightClickOnElement(ELEMENT_EVENT_TASK_WORKING_PANE_PLF41.replace("${event}", event),2);
					}
					else{
						rightClickOnElement(ELEMENT_EVENT_TASK_ALL_DAY_PLF41.replace("${event}", event),2);
					}
				}
			}
			break;
		case ONEDAY:
			info("Delete an Recurring Event...with ONE DAY option");
			Utils.pause(3000);
			if(dateTime!="")
				rightClickOnElement(By.xpath(ELEMENT_EVENT_TASK_DETAIL_DATE.replace("${taskName}", event).replace("${date}", dateTime)),2);
			else
				rightClickOnElement(ELEMENT_EVENT_TASK_ONE_DAY.replace("${taskName}", event),2);
			break;	
		default:
			break;
		}
		click(ELEMENT_EVENT_TASK_DELETE_MENU);
		if(isElementPresent(ELEMENT_DELETE_RECURRING_EVENT_FORM)){
			info("Recurring Event Form...");
			//waitForAndGetElement(ELEMENT_DELETE_RECURRING_EVENT_FORM);
			info(waitForAndGetElement(ELEMENT_CONFIRM_EDIT_DELETE_RECURRING_EVENT).getText());
			assert waitForAndGetElement(ELEMENT_CONFIRM_EDIT_DELETE_RECURRING_EVENT).getText().contains(ELEMENT_CONFIRM_DELETE_MESSAGE);
			assert waitForAndGetElement(ELEMENT_EDIT_DELETE_ONE_EVENT,DEFAULT_TIMEOUT,1,2).isSelected();
			switch (optDeleteType) {
			case ONLY_EVENT:
				info("Delete only event recurring");
				check(ELEMENT_EDIT_DELETE_ONE_EVENT,2);
				break;
			case FOLLOW_EVENT:
				info("Delete following event recurring");
				check(ELEMENT_EDIT_DELETE_FOLLOWING_EVENT,2);
				break;
			case ALL_EVENT:
				info("Delete all event recurring");
				check(ELEMENT_EDIT_DELETE_ALL_EVENT,2);
				break;
			}
			click(ELEMENT_CONFIRM_DELETE_BUTTON);
			waitForElementNotPresent(ELEMENT_DELETE_RECURRING_EVENT_FORM);
		}
		else{
			alert.verifyAlertMessage(MSG_EVENT_TASK_DELETE);
			button.yes();
			driver.navigate().refresh();
			Utils.pause(1000);
			if (optDay.equals(selectDayOption.ALLDAY)){
				if(this.plfVersion.contains("4.0")){
					waitForElementNotPresent(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}", event),5000);
					waitForElementNotPresent(ELEMENT_EVENT_TASK_WORKING_PANE.replace("${event}", event),5000);
				}
				else{
					waitForElementNotPresent(ELEMENT_EVENT_TASK_ALL_DAY_PLF41.replace("${event}", event),5000);
					waitForElementNotPresent(ELEMENT_EVENT_TASK_WORKING_PANE_PLF41.replace("${event}", event),5000);
				}
			}else if (optDay.equals(selectDayOption.ONEDAY)){
				waitForElementNotPresent(ELEMENT_EVENT_TASK_ONE_DAY.replace("${taskName}", event));
			}	
		}
	}

	/**
	 * edit recurring event
	 * @param oldEvent
	 * old name of event
	 * @param name
	 * new name of event
	 * @param description
	 * new description of event
	 * @param location
	 * new location of event
	 * @param from
	 * new from date, time of event
	 * @param to
	 * new to date, time of event
	 * @param allDay
	 * new value of "allDay" option of event
	 * @param options
	 * = selectDayOption.ONEDAY: this function consider the event/task as one day
	 * optDeleteType parameter: if not be set, the event will be automatically set as "all day" event
	 * = dateTime parameter: the date of event is deleted. If it is set, the event with datetime will be deleted.
	 */
	public void editRecurringEvent(String oldEvent, String name, String description, String location, String from, String to, boolean allDay, Object... options){
		info("Edit recurring event");
		selectDayOption optDay = (selectDayOption) (options.length > 0 ? options[0]: selectDayOption.ALLDAY);
		recurringType optEditType = (recurringType) (options.length > 1 ? options[1]: recurringType.ALL_EVENT);
		String dateTime = (String)(options.length > 2 ? options[2]: "");
		switch (optDay) {
		case ALLDAY:
			if(this.plfVersion.contains("4.0")){
				if(waitForAndGetElement(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}", oldEvent), 5000, 0) == null){
					rightClickOnElement(ELEMENT_EVENT_TASK_WORKING_PANE.replace("${event}", oldEvent),2);
				}
				else{
					rightClickOnElement(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}", oldEvent),2);
				}
			}
			else{ //this.plfVersion.contains("4.1")
				if(dateTime!="")
					rightClickOnElement(By.xpath(ELEMENT_EVENT_TASK_DETAIL_ALL_DAY.replace("${event}", oldEvent).replace("${date}", dateTime)),2);
				else{
					if(waitForAndGetElement(ELEMENT_EVENT_TASK_ALL_DAY_PLF41.replace("${event}", oldEvent), 5000, 0) == null){
						rightClickOnElement(ELEMENT_EVENT_TASK_WORKING_PANE_PLF41.replace("${event}", oldEvent),2);
					}
					else{
						rightClickOnElement(ELEMENT_EVENT_TASK_ALL_DAY_PLF41.replace("${event}", oldEvent),2);
					}
				}
			}
			break;
		case ONEDAY:
			if(dateTime!="")
				rightClickOnElement(By.xpath(ELEMENT_EVENT_TASK_DETAIL_DATE.replace("${taskName}", oldEvent).replace("${date}", dateTime)),2);
			else
				rightClickOnElement(ELEMENT_EVENT_TASK_ONE_DAY.replace("${taskName}", oldEvent),2);
			break;
		}
		click(ELEMENT_MENU_EVENT_EDIT);
		if(isElementPresent(ELEMENT_EDIT_EVENT_POPUP)){
			waitForAndGetElement(ELEMENT_EDIT_EVENT_POPUP);
			inputAddEventForm(name,description, location,from,to,allDay);
			click(ELEMENT_ADD_EVENT_SAVE_BUTTON);
			waitForAndGetElement(ELEMENT_CONFIRM_EDIT_RECURRING_FORM);
			info(waitForAndGetElement(ELEMENT_CONFIRM_EDIT_DELETE_RECURRING_EVENT).getText());
			assert waitForAndGetElement(ELEMENT_CONFIRM_EDIT_DELETE_RECURRING_EVENT).getText().contains(ELEMENT_CONFIRM_EDIT_MESSAGE);
			assert waitForAndGetElement(ELEMENT_EDIT_DELETE_ONE_EVENT,DEFAULT_TIMEOUT,1,2).isSelected();
			switch (optEditType) {
			case ONLY_EVENT:
				info("Edit only event recurring");
				check(ELEMENT_EDIT_DELETE_ONE_EVENT,2);
				break;
			case FOLLOW_EVENT:
				info("Edit following event recurring");
				check(ELEMENT_EDIT_DELETE_FOLLOWING_EVENT,2);
				break;
			case ALL_EVENT:
				info("Edit all event recurring");
				check(ELEMENT_EDIT_DELETE_ALL_EVENT,2);
				break;
			}
			click(ELEMENT_CONFIRM_EDIT_BUTTON);
			waitForElementNotPresent(ELEMENT_CONFIRM_EDIT_RECURRING_FORM);
			waitForElementNotPresent(ELEMENT_EDIT_EVENT_POPUP);
		}
		else{
			inputAddEventForm(name,description, location,from,to,allDay);
			click(ELEMENT_ADD_EVENT_SAVE_BUTTON);
			waitForElementNotPresent(ELEMENT_EDIT_EVENT_POPUP);
		}
	}

	/**
	 * verify event exitst or not
	 * @param eventName
	 * @param dateTime
	 * @return: true if event exist, false if event doesn't exist
	 */
	public boolean verifyEventInWeekView(String eventName, String dateTime, Object... options){
		info("Verify even " + eventName + " on " + dateTime);
		selectDayOption optDay = (selectDayOption) (options.length > 0 ? options[0]: selectDayOption.ALLDAY);
		boolean isPresentEvent = false;
		switch (optDay) {
		case ALLDAY:
			info("Verify event all day");
			if(this.plfVersion.contains("4.0")){
				info("Verify in plf 4.0");
				if((waitForElementNotPresent(ELEMENT_EVENT_TASK_ALL_DAY.replace("${event}", eventName)) == null)&&(waitForElementNotPresent(ELEMENT_EVENT_TASK_WORKING_PANE.replace("${event}", eventName))==null)){
					isPresentEvent = false;
				}
				else
					isPresentEvent = true;
			}
			else{ //this.plfVersion.contains("4.1")
				info("Verify in plf 4.1");
				if(dateTime!=""){
					if(waitForElementNotPresent(ELEMENT_EVENT_TASK_DETAIL_ALL_DAY.replace("${event}", eventName).replace("${date}", dateTime),5000,0)==null){
						isPresentEvent = false;
					}
					else{
						isPresentEvent = true;
					}
				}
				else{
					if((waitForElementNotPresent(ELEMENT_EVENT_TASK_ALL_DAY_PLF41.replace("${event}", eventName),5000,0)==null)&&(waitForElementNotPresent(ELEMENT_EVENT_TASK_WORKING_PANE_PLF41.replace("${event}", eventName),5000,0)==null)){
						isPresentEvent = false;
					}
					else
						isPresentEvent = true;
				}
			}
			break;
		case ONEDAY:
			info("Verify event one day");
			if(dateTime!=null&&dateTime!=""){
				if(waitForElementNotPresent(By.xpath(ELEMENT_EVENT_TASK_DETAIL_DATE.replace("${taskName}", eventName).replace("${date}", dateTime)),5000,0)==null){
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;",
							waitForAndGetElement(ELEMENT_EVENT_TASK_WEEK_PANEL));
					if(waitForElementNotPresent(By.xpath(ELEMENT_EVENT_TASK_DETAIL_DATE.replace("${taskName}", eventName).replace("${date}", dateTime)),5000,0)==null){
						click(ELEMENT_NEXT_WEEK);
						if(waitForAndGetElement(By.xpath(ELEMENT_EVENT_TASK_DETAIL_DATE.replace("${taskName}", eventName).replace("${date}", dateTime)),5000,0)!= null){
							isPresentEvent = true;
							info("it is shown in date " + dateTime);
						}
						else{
							isPresentEvent = false;
							info("it is not shown in date " + dateTime);
						}
						click(ELEMENT_PREVIOUS_WEEK);
					}
					else
						isPresentEvent = true;
				}
				else
					isPresentEvent = true;
			}
			else{
				if(waitForAndGetElement(ELEMENT_EVENT_TASK_ONE_DAY.replace("${taskName}", eventName),5000,0) != null)
					isPresentEvent = true;
				else{
					isPresentEvent = false;
				}
			}
			break;
		}
		return isPresentEvent;
	}

	/**
	 * @author lientm
	 * @param from
	 * @param duration
	 */
	public void checkSuggestionEventTime(String from, int duration){
		info("Check date is current date");
		String dateFrom = getValue(ELEMENT_INPUT_EVENT_FROM);
		String dateTo = getValue(ELEMENT_INPUT_EVENT_TO);
		assert dateFrom.equals(getCurrentDate("MM/dd/yyyy"));
		assert dateTo.equals(getCurrentDate("MM/dd/yyyy"));

		info("Check suggestion time");
		if (from == null){
			info("Check time suggestion default");	
		}else {
			info("Check suggesion when select From time");
			click(ELEMENT_INPUT_EVENT_FROM_TIME_IN, 2);
			click(ELEMENT_EVENT_SELECT_FROM_TIME.replace("${time}", from));
			Utils.pause(2000);
		}
		String fromTime = waitForAndGetElement(ELEMENT_INPUT_EVENT_FROM_TIME, DEFAULT_TIMEOUT, 1, 2).getAttribute("value");
		info("From is " + fromTime);
		String toTime = waitForAndGetElement(ELEMENT_INPUT_EVENT_TO_TIME, DEFAULT_TIMEOUT, 1, 2).getAttribute("value");
		info("From is " + toTime);
		DateFormat formatter = new SimpleDateFormat("HH:mm");
		try {
			Date fr = (Date)formatter.parse(fromTime);
			Date to = (Date)formatter.parse(toTime);
			long diff = (to.getTime() - fr.getTime())/60000;
			info("Duration is " + diff + " minus");
			assert duration == (int) diff;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}