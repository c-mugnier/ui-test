package org.exoplatform.selenium.platform.forum;

import  static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.PlatformPermission;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Migrate from plf3.5
 * @author lientm
 * @date 20 Aug 2013
 */
public class ForumPermission extends ForumBase {

	public ForumPermission(WebDriver dr,String...plfVersion){
		this.plfVersion = plfVersion.length>0?plfVersion[0]:"4.0";
		driver = dr;
		button = new Button(driver);
		alert = new ManageAlert(driver);
		per = new PlatformPermission(driver);

	}

	//Set permission for category of answer
	public final String ELEMENT_RESTRICTED_CHECKBOX = "//*[contains(text(), '${user}')]/../../td[2]//input[@type='checkbox']";
	public final String ELEMENT_MODERATOR_CHECKBOX = "//*[contains(text(), '${user}')]/../../td[3]//input[@type='checkbox']";
	public final By ELEMENT_USER_NOT_EXIST_OK = By.xpath("//span[contains(text(),'not found, please enter a valid value')]/../../..//a[text()='OK']");
	public final By ELEMENT_CATEGORY_ADD = By.xpath("//form[@id='UICategoryForm']//button[text()='Add']");
	public final String MSG_PERMISSION_NOT_FOUND =" not found, please enter a valid value.";
	public final By ELEMENT_CATEGORY_CANCEL_BUTTON = By.xpath("//form[@id='UICategoryForm']//button[text()='Cancel']");
	public final By ELEMENT_CATEGORY_PERMISSION_BLANK = By.xpath("//div[@id='PermissionTab']");

	//Set permission for category of forum
	public final String ELEMENT_MODERATOR_FORUM_CATEGORY_CHECKBOX = "//*[contains(text(), '${user}')]/../../td[2]//input[@type='checkbox']";
	public final String ELEMENT_START_TOPIC_FORUM_CATEGORY_CHECKBOX = "//*[contains(text(), '${user}')]/../../td[3]//input[@type='checkbox']";
	public final String ELEMENT_POST_FORUM_CATEGORY_CHECKBOX = "//*[contains(text(), '${user}')]/../../td[4]//input[@type='checkbox']";
	public final String ELEMENT_VIEW_POST_FORUM_CATEGORY_CHECKBOX = "//*[contains(text(), '${user}')]/../../td[5]//input[@type='checkbox']";
	public final By ELEMENT_FORUM_CATEGORY_PERMISSION_GRID = By.id("PermissionTab");
	public final String ELEMENT_FORUM_CATEGORY_PERMISSION_DELETE = "//*[contains(text(), '${user}')]/../../td[6]//i[@class='uiIconDelete uiIconLightGray']";
	
	//Set permission for forum of forum
	public final String ELEMENT_MODERATOR_FORUM_FORUM_CHECKBOX = "//*[contains(text(), '${user}')]/../../td[2]//input[@type='checkbox']";
	public final String ELEMENT_VIEW_POST_FORUM_FORUM_CHECKBOX = "//*[contains(text(), '${user}')]/../../td[3]//input[@type='checkbox']";
	public final String ELEMENT_START_TOPIC_FORUM_FORUM_CHECKBOX = "//*[contains(text(), '${user}')]/../../td[4]//input[@type='checkbox']";
	public final String ELEMENT_POST_FORUM_FORUM_CHECKBOX = "//*[contains(text(), '${user}')]/../../td[5]//input[@type='checkbox']";
	public final By ELEMENT_FORUM_FORUM_PERMISSION = By.id("forumPermission");

	//Set permission for topic
	public final String ELEMENT_WHO_CAN_VIEW_CHECKBOX = "//*[contains(text(), '${user}')]/../../td[2]//input[@type='checkbox']";
	public final String ELEMENT_WHO_CAN_POST_CHECKBOX = "//*[contains(text(), '${user}')]/../../td[3]//input[@type='checkbox']";
	public final By ELEMENT_PERMISSION_TAB = By.linkText("Permissions");

	public final String MSG_USER_NOT_EXIST = " not found, please enter a valid value.";

	/*--------------------------common function-----------------------------------*/

	/**
	 * @author lientm
	 * @param type
	 * @param userGroup
	 */
	public void setPermissionWithOption(int type, String[] userGroup){
		//		per = new PlatformPermission(driver);
		click(ELEMENT_PERMISSION_TAB);
		switch (type){
		case 1:
			info("Set permission by type directly");
			//			type(per.ELEMENT_PERMISSION_INPUT, userGroup[0], true);
			if(isElementPresent(per.ELEMENT_PERMISSION_INPUT))
				waitForAndGetElement(per.ELEMENT_PERMISSION_INPUT).sendKeys(userGroup[0]);
			else
				waitForAndGetElement(per.ELEMENT_PERMISSION_INPUT_PLF4_1).sendKeys(userGroup[0]);
			break;
		case 2:	
			info("Set permission for user " + userGroup[0]);
			click(per.ELEMENT_SELECT_USER_ICON);
			waitForAndGetElement(per.ELEMENT_SELECT_USER_POPUP);
			if (userGroup.length > 1){
				per.selectUserPermission(userGroup[0], Integer.parseInt(userGroup[1]));
			}else {
				per.selectUserPermission(userGroup[0]);
			}
			waitForElementNotPresent(per.ELEMENT_SELECT_USER_POPUP);
			break;
		case 3:	
			info("Set permission for group " + userGroup[0]);
			click(per.ELEMENT_SELECT_GROUP_ICON);
			waitForAndGetElement(per.ELEMENT_SELECT_GROUP_POPUP);
			per.selectGroupPermission(userGroup[0]);
			waitForElementNotPresent(per.ELEMENT_SELECT_GROUP_POPUP);
			break;
		case 4:	
			info("Set permission with membership " + userGroup[1] + "/" + userGroup[0]);
			click(per.ELEMENT_SELECT_MEMBERSHIP_ICON);
			waitForAndGetElement(per.ELEMENT_SELECT_ROLE_POPUP);
			per.selectGroupMembership(userGroup[0], userGroup[1]);
			waitForElementNotPresent(per.ELEMENT_SELECT_ROLE_POPUP);
			break;
		default:
			break;
		}
	}

	/**
	 * @author lientm
	 * @param type
	 * @param userGroup
	 * @param restricted
	 * @param moderator
	 */
	public void configPermission4AnswerCategory(int type, String[] userGroup, boolean restricted, boolean moderator, boolean...notFound){
		setPermissionWithOption(type, userGroup);
		click(ELEMENT_CATEGORY_PERMISSION_BLANK);
		Utils.pause(1000);
		click(button.ELEMENT_ADD_BUTTON,2);
		boolean notF = notFound.length > 0 ? notFound[0] : false;
		if (notF){
			waitForMessage(userGroup[0]+MSG_PERMISSION_NOT_FOUND);
			click(ELEMENT_USER_NOT_EXIST_OK);
			click(ELEMENT_CATEGORY_CANCEL_BUTTON);
			return;
		}
		String check = "";
		String[] groups = userGroup[0].split("/");
		if ((type == 4) || (type == 3)){
			for(int i = 0; i < groups.length; i ++){
				if (per.group.get(groups[i]) != null)
					check += "/" + per.group.get(groups[i]).toLowerCase();
				else{
					check += "/" + groups[i].toLowerCase();
				}
			}
		}
		else
			check = userGroup[0].toLowerCase();
		if (userGroup.length > 2){
			check = userGroup[2];
		}
		if (restricted){
			check(ELEMENT_RESTRICTED_CHECKBOX.replace("${user}", check), 2);
		}else {
			uncheck(ELEMENT_RESTRICTED_CHECKBOX.replace("${user}", check), 2);
		}
		if (moderator){
			check(ELEMENT_MODERATOR_CHECKBOX.replace("${user}", check), 2);
		} else {
			uncheck(ELEMENT_MODERATOR_CHECKBOX.replace("${user}", check), 2);
		}
	}

	/**
	 * 
	 * @param type
	 * @param userGroup
	 * @param permission
	 */
	public void configPermission4ForumCategory(int type, String[] userGroup, boolean...permission){
		setPermissionWithOption(type, userGroup);
		click(ELEMENT_FORUM_CATEGORY_PERMISSION_GRID);
		click(button.ELEMENT_ADD_BUTTON);
		String check = "";
		String[] groups = userGroup[0].split("/");
		if ((type == 4) || (type == 3)){
			for(int i = 0; i < groups.length; i ++){
				if (per.group.get(groups[i]) != null)
					check += "/" + per.group.get(groups[i]).toLowerCase();
				else{
					check += "/" + groups[i].toLowerCase();
				}
			}
		}
		else
			check = userGroup[0].toLowerCase();
		if (userGroup.length > 2){
			check = userGroup[2];
		}

		if (permission.length > 0){
			if (permission[0]){
				check(ELEMENT_MODERATOR_FORUM_CATEGORY_CHECKBOX.replace("${user}", check), 2);
			}else {
				uncheck(ELEMENT_MODERATOR_FORUM_CATEGORY_CHECKBOX.replace("${user}", check), 2);
			}
		}
		if (permission.length > 1){
			if (permission[1]){
				check(ELEMENT_START_TOPIC_FORUM_CATEGORY_CHECKBOX.replace("${user}", check), 2);
			} else {
				uncheck(ELEMENT_START_TOPIC_FORUM_CATEGORY_CHECKBOX.replace("${user}", check), 2);
			}
		}
		if (permission.length > 2){
			if (permission[2]){
				check(ELEMENT_POST_FORUM_CATEGORY_CHECKBOX.replace("${user}", check), 2);
			} else {
				uncheck(ELEMENT_POST_FORUM_CATEGORY_CHECKBOX.replace("${user}", check), 2);
			}
		}
		if (permission.length > 3){
			if (permission[3]){
				check(ELEMENT_VIEW_POST_FORUM_CATEGORY_CHECKBOX.replace("${user}", check), 2);
			} else {
				uncheck(ELEMENT_VIEW_POST_FORUM_CATEGORY_CHECKBOX.replace("${user}", check), 2);
			}
		}
	}

	/**
	 * 
	 * @param type
	 * @param userGroup
	 * @param permission
	 */
	public void configPermission4Forum(int type, String[] userGroup, boolean...permission){
		//click(button.ELEMENT_ADD_BUTTON);
		setPermissionWithOption(type, userGroup);
		click(ELEMENT_FORUM_FORUM_PERMISSION);
		Utils.pause(1000);
		click(button.ELEMENT_ADD_BUTTON,2);
		Utils.pause(1000);
		String check = "";
		String[] groups = userGroup[0].split("/");
		if ((type == 4) || (type == 3)){
			for(int i = 0; i < groups.length; i ++){
				if (per.group.get(groups[i]) != null)
					check += "/" + per.group.get(groups[i]).toLowerCase();
				else{
					check += "/" + groups[i].toLowerCase();
				}
			}
		}
		else
			check = userGroup[0].toLowerCase();

		//String check = userGroup[0];
		if (userGroup.length > 2){
			check = userGroup[2];
		}
		if (permission.length > 0){
			if (permission[0]){
				check(ELEMENT_MODERATOR_FORUM_FORUM_CHECKBOX.replace("${user}", check), 2);
			}else {
				uncheck(ELEMENT_MODERATOR_FORUM_FORUM_CHECKBOX.replace("${user}", check), 2);
			}
		}
		if (permission.length > 1){
			if (permission[1]){
				check(ELEMENT_VIEW_POST_FORUM_FORUM_CHECKBOX.replace("${user}", check), 2);
			} else {
				uncheck(ELEMENT_VIEW_POST_FORUM_FORUM_CHECKBOX.replace("${user}", check), 2);
			}
		}
		if (permission.length > 2){
			if (permission[2]){
				check(ELEMENT_START_TOPIC_FORUM_FORUM_CHECKBOX.replace("${user}", check), 2);
			} else {
				uncheck(ELEMENT_START_TOPIC_FORUM_FORUM_CHECKBOX.replace("${user}", check), 2);
			}
		}
		if (permission.length > 3){
			if (permission[3]){
				check(ELEMENT_POST_FORUM_FORUM_CHECKBOX.replace("${user}", check), 2);
			} else {
				uncheck(ELEMENT_POST_FORUM_FORUM_CHECKBOX.replace("${user}", check), 2);
			}
		}
	}

	public void configPermission4Topic(int type, String[] userGroup, boolean...permission){
		click(button.ELEMENT_ADD_BUTTON);
		String check = userGroup[0];
		if (userGroup.length > 2){
			check = userGroup[2];
		}
		if (permission.length > 0){
			if (permission[0]){
				check(ELEMENT_WHO_CAN_VIEW_CHECKBOX.replace("${user}", check), 2);
			}else {
				uncheck(ELEMENT_WHO_CAN_VIEW_CHECKBOX.replace("${user}", check), 2);
			}
		}
		if (permission.length > 1){
			if (permission[1]){
				check(ELEMENT_WHO_CAN_POST_CHECKBOX.replace("${user}", check), 2);
			} else {
				uncheck(ELEMENT_WHO_CAN_POST_CHECKBOX.replace("${user}", check), 2);
			}
		}
	}
	
	/**
	 * 
	 */
	public void deletePermission4ForumCategory(String userGroup){
		click(ELEMENT_PERMISSION_TAB);
		Utils.pause(1000);
		click(ELEMENT_FORUM_CATEGORY_PERMISSION_DELETE.replace("${user}", userGroup));
		waitForElementNotPresent(ELEMENT_FORUM_CATEGORY_PERMISSION_DELETE.replace("${user}", userGroup)); 
	}
}