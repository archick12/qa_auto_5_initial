package ui.pages;

import org.openqa.selenium.By;
import ui.utils.RemoteDriverManager;

public class IssuePage extends BasePage {

    private String pageURL = baseURL + "/browse/%s";

    private HeaderPage headerPage;

    // page header buttons
    private By editIssueButton = By.id("edit-issue");
    private By commentIssueButton = By.id("comment-issue");
    private By assignIssueButton = By.id("assign-issue");
    private By moreButton = By.id("opsbar-operations_more");
    private By workflowButton = By.id("opsbar-transitions_more");
    private By backlogButton = By.xpath("//*[@id='action_id_11']/span[@class='trigger-label']");
    private By selectedForDevButton = By.xpath("//*[@id='action_id_21']/span[@class='trigger-label']");
    private By shareBtnLocator = By.id("jira-share-trigger");
    private By exportBtnLocator = By.id("viewissue-export");

    // issue details
    private By projectIdLocator = By.id("project-name-val");
    private By issueSummaryLocator = By.id("summary-val");
    private By issueTypeField = By.id("type-val");
    private By issuePriorityField = By.id("priority-val");
    private By issueLabelsField = By.xpath("//a[@class='lozenge']/span");
    private By issueAssigneeField = By.id("issue_summary_assignee_" + username);
    private By issueDescriptionLocator = By.id("description-val");

    private By newSubtaskButtonLocator = By.id("stqc_show");
    private By summaryLocator = By.id("summary");
    private By submitButtonLocator = By.id("create-issue-submit");
    private By subtaskLocator = By.linkText("Snizhanna test");
    private By moreButtonLocator = By.id("opsbar-operations_more");
    private By deleteListItemLocator = By.id("delete-issue");
    private By deleteButtonLocator = By.id("delete-issue-submit");
    private By successPopUp = By.xpath("//*[contains(@class,'aui-message-success')]");
    private By commentBtnLocator = By.id("footer-comment-button");
    private By commentAreaLocator = By.id("comment");
    private By commentAddBtnLocator = By.id("issue-comment-add-submit");
    private By commentDeleteBtnLocator = By.xpath("//*[contains(@id,'delete_comment')]");
    private By commentDeletePopUpBtnLocator = By.id("comment-delete-submit");


    private String subTaskSummary = "//*[@class='stsummary']//*[contains(text(),'%s')]";
    private String subTaskNumber = "//*[@class='stsequence']//*[contains(text(),'%s')]";
    private String subTaskAssignee = "//*[@class='assignee']//*[contains(text(),'%s')]";
    private String commentText = "//*[@id='issue_actions_container']//child::*[contains(text(),'%s')]";

    // Comments sections

//    private By commentTextType = By.id("aui-uid-2");
    private By commentTextType = By.id("comment");
//    private By subTaskText = By.xpath("//*[contains(text(),'%s')]");


    public IssuePage() {
        this.driver = RemoteDriverManager.getDriver();
        headerPage = new HeaderPage();
    }

    public void openExistingIssue(String issueId) {
        String url = String.format(pageURL, issueId);
        super.openExistingIssue(url);
    }

    public IssuePage openNewSubTask() throws InterruptedException {
        waitToBePresentAndClick(newSubtaskButtonLocator);
        return this;
    }

    public IssuePage shouldSeeSuccessPopUp() {
        waitToBePresent(successPopUp);
        return this;
    }

    public IssuePage openSubtask() throws InterruptedException {
        waitToBePresentAndClick(subtaskLocator);
        return this;
    }

    public IssuePage clickMoreButton() {
        waitToBePresentAndClick(moreButtonLocator);
        return this;
    }

    public IssuePage clickDeleteListItem() {
        waitToBePresentAndClick(deleteListItemLocator);
        return this;
    }

    public IssuePage deleteSubTask() {
        waitToBePresentAndClick(deleteButtonLocator);
        return this;
    }

    public IssuePage clickOnCommentBtn() {
        waitToBePresentAndClick(commentBtnLocator);
        return this;
    }

    public IssuePage enterComment(String comment) {
        waitToBePresentAndClick(commentTextType);
        waitToBePresentAndSendKeys(commentAreaLocator, comment);
        return this;
    }

    public IssuePage clickOnAddComment() {
        waitToBePresentAndClick(commentAddBtnLocator);
        return this;
    }

    public IssuePage clickOnDeleteComment() {
        waitToBePresentAndClick(commentDeleteBtnLocator);
        return this;
    }

    public IssuePage confirmDeletionOfComment() {
        waitToBePresentAndClick(commentDeletePopUpBtnLocator);
        return this;
    }

    // Checks

    public boolean isOnThePage(String issueId) {

        String url = String.format(pageURL, issueId);
        return super.isOnThePage(url);

    }

    public boolean isSubTaskSummaryPresent(String title) {
        String selector = String.format(subTaskSummary, title);
        return waitToBePresentAndContainsText(By.xpath(selector), title);
    }

    public boolean isSubTaskSummaryMissing(String title) {
        String selector = String.format(subTaskSummary, title);
        return waitToBeMissing(By.xpath(selector));
    }

    public boolean isSubTaskNumberPresent(String name) {
        String selector = String.format(subTaskNumber, name);
        return waitToBePresentAndContainsText(By.xpath(selector), name);
    }

    public boolean isSubTaskAssigneePresent(String name) {
        String selector = String.format(subTaskAssignee, name);
        return waitToBePresentAndContainsText(By.xpath(selector), name);
    }

    public boolean isCommentTextPresent(String text) {
        String selector = String.format(commentText, text);
        return waitToBePresentAndContainsText(By.xpath(selector), text);
    }

    public boolean isCommentTextMissing(String text) {
        String selector = String.format(commentText, text);
        return waitToBeMissing(By.xpath(selector));
    }

    public boolean isProjectIdCorrect(String projectId) {
        return waitToBePresentAndContainsText(projectIdLocator, projectId);
    }

    public boolean isIssueSummaryCorrect(String summary) {
        return waitToBePresentAndContainsText(issueSummaryLocator, summary);
    }

    public boolean isIssueTypeCorrect(String issueType) {
        return waitToBePresentAndContainsText(issueTypeField, issueType);
    }

    public boolean isIssuePriorityCorrect(String priority) {
        return waitToBePresentAndContainsText(issuePriorityField, priority);
    }

    public boolean isIssueLabelCorrect(String label) {
        return waitToBePresentAndContainsText(issueLabelsField, label);
    }

    public boolean isIssueDescriptionCorrect(String description) {
        return waitToBePresentAndContainsText(issueDescriptionLocator, description);
    }

    public boolean isIssueAssigneeCorrect() {
        if (driver.findElement(issueAssigneeField) == null) {return false;}
        return true;
    }



}

