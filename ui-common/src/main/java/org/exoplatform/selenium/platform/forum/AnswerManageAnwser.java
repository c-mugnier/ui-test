package org.exoplatform.selenium.platform.forum;

import static org.exoplatform.selenium.TestLogger.info;

import org.exoplatform.selenium.Button;
import org.exoplatform.selenium.ManageAlert;
import org.exoplatform.selenium.Utils;
import org.exoplatform.selenium.platform.ManageAccount;
import org.exoplatform.selenium.platform.ManageAccount.userType;
import org.exoplatform.selenium.platform.HomePageActivity;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Migrate from plf3.5
 * @author lientm
 * @date 19 Aug 2013
 */
public class AnswerManageAnwser extends AnswerBase {
	AnswerManageCategory magCat;
	ManageAccount magAc;

	HomePageActivity hpAct;
	public AnswerManageAnwser(WebDriver dr,String...plfVersion){
		this.plfVersion = plfVersion.length>0?plfVersion[0]:"4.0";
		driver = dr;
		button = new Button(driver);
		alert = new ManageAlert(driver);
		magCat = new AnswerManageCategory(driver);
		magAc = new ManageAccount(driver,this.plfVersion);

		hpAct = new HomePageActivity(driver);
	}

	AnswerManageQuestion magQuest;

	//Manage answer for question
	public final By ELEMENT_ANSWER_LINK_IN_QUESTION = By.xpath("//*[@class='questionAction']//*[contains(text(),'Answer')]");
	public final By ELEMENT_ANSWER_CONTENTFRAME_1 = By.xpath("//iframe[@id='QuestionRespone___Frame']");
	public final By ELEMENT_ANSWER_CONTENTFRAME_2 = By.xpath("//td[@id='xEditingArea']/iframe");
	public final By ELEMENT_ANSWER_MESSAGE_FRAME_CKEDITOR = By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']");//By.id("scayt_0");
	public final By ELEMENT_ANSWER_CONTENTFRAME_41 = By.xpath("//*[@id='cke_QuestionRespone']//iframe");
	public final By ELEMENT_APPROVED_ANSWER = By.id("QuestionApproved");
	public final By ELEMENT_ACTIVATED_ANSWER = By.id("QuestionShowAnswer");
	public final String ELEMENT_NUMBER_ANSWER = "//a[text()='${question}']/../../../*//a[2]/p[text()='1']";
	public final By ELEMENT_LAGUAGE_SELECTED = By.xpath("//*[@id='Language']/option[@selected='selected']");
	public final By ELEMENT_ANSWER_LANGUAGE = By.id("Language");
	public final String ELEMENT_ANSWER_IN_QUESTION = "//*[contains(@id, 'Answer')]//*[text()='${answer}']";
	public final String ELEMENT_GET_ANSWER_IN_QUESTION = "//*[contains(@class, 'answerContent')]//p";
	public final String ELEMENT_ANSWER_POSITION_IN_LIST = "//*[contains(@id, 'Answer')][${no}]//*[text()='${answer}']";
	public final String MSG_ANSWER_PENDING = "Your answer is pending for moderation. It will be displayed after approval.";
	public final By ELEMENT_MSG_ANSWER_EMPTY = By.xpath("//*[contains(text(),'Please provide an answer to the question.')]");
	public final By ELEMENT_MSG_ANSWER_EMPTY_DIALOG_OK_BUTTON = By.xpath("//*[@class='warningIcon']/../../..//*[text()='OK']");
	public final String MSG_DELETE_ANSWER_CONFIRM = "Are you sure you want to delete this answer ?";
	public final By ELEMENT_ANSWER_PENDING_OK = By.xpath("//span[contains(text(),'Your answer is pending for moderation. It will be displayed after approval.')]/../../..//*[text()='OK']");
	public final String ELEMENT_ANSWER_CONTENT = "//div[@class='answerContent']//p[text()='${answer}']";

	//More action menu
	public final String ELEMENT_MORE_ANSWER_ACTION = "//*[text()='${answer}']/../../../../..//*[contains(text(), 'More Actions')]";
	public final By ELEMENT_APPROVE_ANSWER_MENU = By.xpath("//a[contains(.,'Approve')]");
	public final By ELEMENT_DISAPPROVE_ANSWER_MENU = By.xpath("//a[contains(.,'Disapprove')]");
	public final By ELEMENT_ACTIVATE_ANSWER_MENU = By.xpath("//a[contains(.,'Activate')]");
	public final By ELEMENT_DEACTIVATE_ANSWER_MENU = By.xpath("//a[contains(.,'Deactivate')]");

	//vote answer
	public final String ELEMENT_ANSWER_VOTE_ICON = "//*[text()='${answer}']/../../..//*[contains(@id, 'FAQVoteAnswerUp')]";
	public final String ELEMENT_ANSWER_UNVOTE_ICON = "//*[text()='${answer}']/../../..//*[contains(@id, 'FAQVoteAnswerDown')]";
	public final String ELEMENT_ANSWER_NUMBER_VOTE = "//*[text()='${answer}']/../../..//*[@class='textVoteAnswer']";
	public final By ELEMENT_SORT_BY_RATE = By.xpath("//a[@data-original-title='Sort Answers by Rate']");

	//	Add, remove relation
	public final By ELEMENT_ADD_RELATION = By.xpath("//div[@title='Link to another entry']");
	public final By ELEMENT_CATEGORY_TREE = By.xpath("//div[@class='FAQCategoryTreeView']//a[contains(text(),'categories')]");
	public final String ELEMENT_QUESTION_IN_ADD_RELATION = "//*[text()='${question}']/..//input";
	public final String ELEMENT_REMOVE_RELATION = "//*[contains(text(),'${question}')]//*[@data-original-title='Remove']";

	/*	---------------------------Answer a question-----------------------------------*/

	/**function add relation for a answer
	 * @author lientm
	 * @param questionlink
	 */
	public void addRelationForAnswer(String questionlink){
		String[] question = questionlink.split("/");
		if (question.length > 0){
			click(ELEMENT_ADD_RELATION);
			click(ELEMENT_CATEGORY_TREE);
			for(int i = 0; i < question.length; i ++){
				check(ELEMENT_QUESTION_IN_ADD_RELATION.replace("${question}", question[i]), 2);
			}
			button.save();
			Utils.pause(1000);
		}
	}

	/**
	 * Function remove relation in answer
	 * @param questionlink
	 */
	public  void removeRelationForAnswer(String questionlink){
		String[] question = questionlink.split("/");
		for (int i = 0; i < question.length; i ++) {
			click(ELEMENT_REMOVE_RELATION.replace("${question}", question[i]));
			waitForElementNotPresent(ELEMENT_REMOVE_RELATION.replace("${question}", question[i]));
		}
	}	

	/**
	 * function input data in answer form
	 * @param language
	 * @param answerContent
	 * @param approved
	 * @param activated
	 * @param addRelation
	 * @param questionToLink
	 * @param removeRelation
	 * @param questionRemove
	 */
	public void modifyAnwser(String language, String answerContent, boolean approved, boolean activated, boolean addRelation, 
			String questionToLink, boolean removeRelation, String questionRemove){
		if (language != "" && language != null){
			if(waitForAndGetElement(ELEMENT_ANSWER_LANGUAGE,DEFAULT_TIMEOUT,0)!=null)
				selectOption(ELEMENT_ANSWER_LANGUAGE, language);
		}
		if (answerContent != null){
			if(this.plfVersion.equalsIgnoreCase("4.0")){
				inputDataToFrameInFrame(ELEMENT_ANSWER_CONTENTFRAME_1, ELEMENT_ANSWER_CONTENTFRAME_2, answerContent,true,false);

			}else//(this.plfVersion.equalsIgnoreCase("4.0"))

				inputDataToFrame(ELEMENT_ANSWER_MESSAGE_FRAME_CKEDITOR, answerContent, true,false);
			switchToParentWindow();	
		}
		if (waitForAndGetElement(ELEMENT_APPROVED_ANSWER, 5000, 0, 2) != null){
			if (approved){
				check(ELEMENT_APPROVED_ANSWER, 2);
			}else {
				uncheck(ELEMENT_APPROVED_ANSWER, 2);
			}	
		}
		if (waitForAndGetElement(ELEMENT_ACTIVATED_ANSWER, 5000, 0, 2) != null){
			if (activated){
				check(ELEMENT_ACTIVATED_ANSWER, 2);
			}else {
				uncheck(ELEMENT_ACTIVATED_ANSWER, 2);
			}
		}
		if(addRelation){
			addRelationForAnswer(questionToLink);
		}
		if (removeRelation){
			removeRelationForAnswer(questionRemove);
		}
		button.save();
		if((answerContent == null)||( answerContent == "")){
			waitForAndGetElement(ELEMENT_MSG_ANSWER_EMPTY);
			click(ELEMENT_MSG_ANSWER_EMPTY_DIALOG_OK_BUTTON);
		}
	}

	/**
	 * function answer a question with many way
	 * @param way
	 * @param questionName
	 * @param language
	 * @param answerContent
	 * @param approved
	 * @param activated
	 * @param addRelation
	 * @param questionToLink
	 * @param removeRelation
	 * @param questionRemove
	 */
	public void answerQuestion(int way, String questionName, String language, String answerContent, boolean approved, boolean activated, boolean addRelation, 
			String questionToLink, boolean removeRelation, String questionRemove){
		magQuest = new AnswerManageQuestion(driver);
		switch (way) {
		case 1:
			info("Answer question by right click");
			rightClickOnElement(By.linkText(questionName));
			click(magQuest.ELEMENT_ANSWER_LINK_IN_CONTEXT_MENU);
			break;
		case 2:
			info("Answer question while opening question");
			click(ELEMENT_ANSWER_LINK_IN_QUESTION);
			break;
		case 3:
			info("Answer question by click answer icon in manage question");
			click(magQuest.ELEMENT_ANSWER_QUESTION_IN_LIST.replace("${question}", questionName));
			break;
		default:
			info("Answer question from click language in manage question");
			click(magQuest.ELEMENT_LANGUAGE_LINK_IN_LIST.replace("${question}", questionName).replace("${language}", language));
			break;
		}
		modifyAnwser(language, answerContent, approved, activated, addRelation, questionToLink, removeRelation, questionRemove);
		Utils.pause(1000);
	}

	/**
	 * function go to an action in More Actions link of a answer
	 * @param answer
	 * @param action
	 */
	public void goToMoreActionsOfAnswer(String answer, String action){
		click(ELEMENT_MORE_ANSWER_ACTION.replace("${answer}", answer));
		getElementFromTextByJquery(action).click();
	}
	/**
	 * function edit a answer
	 * @param answerName
	 * @param language
	 * @param answerContent
	 * @param approved
	 * @param activated
	 * @param addRelation
	 * @param questionToLink
	 * @param removeRelation
	 * @param questionRemove
	 */
	public void editAnswer(String answerName, String language, String answerContent, boolean approved, boolean activated, boolean addRelation, 
			String questionToLink, boolean removeRelation, String questionRemove){
		info("Edit answer");	
		goToMoreActionsOfAnswer(answerName, "Edit Answer");
		modifyAnwser(language, answerContent, approved, activated, addRelation, questionToLink, removeRelation, questionRemove);
		waitForElementNotPresent(ELEMENT_ANSWER_IN_QUESTION.replace("${answer}", answerName));
		if(!answerContent.contains("<br/>"))
			waitForAndGetElement(ELEMENT_ANSWER_IN_QUESTION.replace("${answer}", answerContent));
		else{
			info("Answer is "+getText(ELEMENT_GET_ANSWER_IN_QUESTION));
			hpAct.checkNumberOfLineOfContent(getText(ELEMENT_GET_ANSWER_IN_QUESTION), answerContent,false);
		}
	}

	/**
	 * function delete a answer
	 * @param answer
	 */
	public void deleteAnswer(String answer){
		info("Delete answer");
		goToMoreActionsOfAnswer(answer, "Delete Answer");
		waitForMessage(MSG_DELETE_ANSWER_CONFIRM);
		click(ELEMENT_CONFIMATION_OK_POPUP);
		waitForElementNotPresent(ELEMENT_CONFIMATION_OK_POPUP);
		waitForElementNotPresent(ELEMENT_ANSWER_IN_QUESTION.replace("${answer}", answer));
	}

	/**
	 * function approve/disapprove a answer
	 * @param answer
	 * @param approve
	 */
	public void approveAnswer(String answer, boolean approve){
		click(ELEMENT_MORE_ANSWER_ACTION.replace("${answer}", answer));
		if (approve){
			WebElement app = waitForAndGetElement(ELEMENT_APPROVE_ANSWER_MENU);
			if (app != null){
				app.click();
				Utils.pause(1000);
			}else {
				info("Answer has already approved");
			}
		} else {
			WebElement dis_app = waitForAndGetElement(ELEMENT_DISAPPROVE_ANSWER_MENU);
			if (dis_app != null){
				dis_app.click();
				Utils.pause(1000);
			}else {
				info("Answer has already disapproved");
			}
		}
	}

	/**
	 * function activate/deactivate a answer
	 * @param answer
	 * @param active
	 */
	public void activeAnswer(String answer, boolean active){
		click(ELEMENT_MORE_ANSWER_ACTION.replace("${answer}", answer));
		if (active){
			WebElement act = waitForAndGetElement(ELEMENT_ACTIVATE_ANSWER_MENU);
			if (act != null){
				act.click();
				Utils.pause(1000);
			}else {
				info("Answer has already activated");
			}
		} else {
			WebElement de_act = waitForAndGetElement(ELEMENT_DEACTIVATE_ANSWER_MENU);
			if (de_act != null){
				de_act.click();
				Utils.pause(1000);
			}else {
				info("Answer has already deactivated");
			}
		}
	}

	/**
	 * function vote/unvote an answer
	 * @param answer
	 * @param rate
	 */
	public void rateAnswer(String answer, boolean rate){
		if (rate){
			click(ELEMENT_ANSWER_VOTE_ICON.replace("${answer}", answer));
		} else {
			click(ELEMENT_ANSWER_UNVOTE_ICON.replace("${answer}", answer));
		}
		Utils.pause(1000);
	}

	/** View answer list with any user
	 * @author phuongdt
	 * @param user
	 * @param categoryName
	 * @param questionName
	 * @param answerContent
	 * @param view
	 */
	public void viewAnswerWithOtherUser(userType user, String categoryName, String questionName, String answerContent, boolean view){
		magAc.userSignIn(user);
		goToAnswer();
		magCat.openCategoryInAnswer(categoryName);
		click(By.linkText(questionName));
		if (view){
			waitForAndGetElement(ELEMENT_ANSWER_IN_QUESTION.replace("${answer}", answerContent));
		}else {
			waitForElementNotPresent(ELEMENT_ANSWER_IN_QUESTION.replace("${answer}", answerContent));	
		}
		magAc.signOut();
	}
}
